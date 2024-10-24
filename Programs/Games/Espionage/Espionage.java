package Espionage;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Espionage.Player.AgentName;
import Espionage.Player.PlayerHandler;
import Espionage.ActionCard.CardType;

/**
 * @author Astrid
 * A game based on the Rock Paper Scissor idea.
 * This is the Application main starting the GUI
**/

@SuppressWarnings("serial")
public class Espionage extends JFrame implements ItemListener, ChangeListener {
	
	public  Random random = null;;
	private Battle battle = null;
	private JFrame frame  = null;
	private List<Player> lPlayers = null;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new Espionage();
				frame.setVisible(true);
			}
		});
	}

	// GUI to handle the game
	public Espionage() {
		frame = this;
		random = new Random(42);
		setTitle("Espionage");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(getJMenuBar());
		setBounds(100, 100, 800, 600);

		// Board is painted on this JPanel.
		setContentPane(new Board(this));

		// Game logic 
		battle = new Battle(this);

		// Create all the players with default options.
		createUsers();

		setVisible(true);
	}



	// Menu to change player options.
	public JMenuBar getJMenuBar() {
		JMenuBar jmb = new JMenuBar();
		JMenu jm = new JMenu("File");
		jm.add(get_MI_PlayerOptions());
		jmb.add(jm);
		return jmb;
	}
	
	JMenuItem get_MI_PlayerOptions() {
		JMenuItem z = new JMenuItem("Start new Game");
		z.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ---------------------------------------
				Object[] message = { getMessagePanel() };
				String[] buttons = { ("Start Game") };
				
				int iReturns = JOptionPane.showOptionDialog(frame, message, "Espionage", 
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, buttons, buttons[0]);
				
				if(iReturns == JOptionPane.CLOSED_OPTION) {
				} else {	
					if(iReturns == JOptionPane.OK_OPTION) {
						
						// Exclude non-participants
						List<Player> lActivePlayers = lPlayers.stream()
								.filter(d -> d.getHandledBy() != PlayerHandler.NONE)
								.sorted(Comparator.comparing(d -> ((Player) d).getHandledBy()))
								.collect(Collectors.toList());

						dealCards(battle);
						battle.startGame(lActivePlayers);
					}	
				}
			}
		});
		return z;
	}

	//GUI for the menu.
	public JPanel getMessagePanel() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));

		JPanel title = new JPanel();
		title.setLayout(new BoxLayout(title, BoxLayout.LINE_AXIS));
		title.add(new JLabel("Select your agency to play:"));
		title.add(Box.createHorizontalGlue());
		p.add(title);
		p.add(Box.createVerticalStrut(10));

		ButtonGroup btnGrp = new ButtonGroup();
		lPlayers.stream().forEach(u -> {
			p.add(getRadioButton4Player(u, btnGrp));
			p.add(Box.createVerticalStrut(2));
		});
		p.add(Box.createVerticalStrut(15));
		
		p.add(getRadioButton4Autoplay("None", btnGrp));
		
		JSlider autodelay = new JSlider(JSlider.HORIZONTAL, 0, 100, battle.getAutoDelay() );
		Dictionary<Integer, JLabel> dict = new Hashtable<Integer, JLabel>();
		dict.put(  0, new JLabel("Fast"));
		dict.put(100, new JLabel("Slow"));
		autodelay.setLabelTable(dict);
		
		autodelay.setMajorTickSpacing(25);
		autodelay.setMinorTickSpacing(5);
		autodelay.setPaintTicks(true);
		autodelay.setPaintLabels(true);
		autodelay.addChangeListener(this);
		p.add(autodelay);
		

		p.add(Box.createVerticalStrut(15));

		return p;
	}

	public JPanel getRadioButton4Autoplay(String a, ButtonGroup bg) {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.LINE_AXIS));

		JRadioButton rb = new JRadioButton("Let the computer play on its own!");
		rb.setName("None");

		if (lPlayers.stream().filter(u -> u.getHandledBy() == PlayerHandler.MANUAL).count() == 0)
			rb.setSelected(true);

		rb.addItemListener(this);
		bg.add(rb);

		p.add(rb);
		p.add(Box.createHorizontalGlue());
		return p;
	}

	public JPanel getRadioButton4Player(Player u, ButtonGroup bg) {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.LINE_AXIS));
		p.setBorder(new BevelBorder(BevelBorder.RAISED, u.getColor(), u.getColor()));

		JRadioButton rb = new JRadioButton(u.getName().toString());
		rb.setName(u.getName().toString());
		rb.setSelected(u.getHandledBy() == PlayerHandler.MANUAL);
		rb.addItemListener(this);
		bg.add(rb);
		p.add(rb);
		p.add(Box.createHorizontalGlue());

		JCheckBox cb = new JCheckBox("Excluded");
		cb.setName(u.getName().toString());
		cb.setHorizontalTextPosition(SwingConstants.LEFT);
		cb.setSelected(u.getHandledBy() == PlayerHandler.NONE);
		cb.addItemListener(this);
		p.add(cb);
		return p;
	}

	//This is called once - from the constructor of this class.
	public void createUsers() {
		Stream.Builder<Player> builder = Stream.<Player>builder();
		for (AgentName usr : AgentName.values()) {
			builder.add(new Player(usr, this, battle));
		}
		Stream<Player> stream = builder.build();
		lPlayers = stream.collect(Collectors.toList());
	}

	//This is done every time you start a new game.
	public void dealCards(Battle battle) {
		
		lPlayers.forEach((u) -> {
			u.setPosition(0);
			u.clearCards();
		});		
		//Empty the prison
		((Board) getContentPane()).updatePrison(lPlayers.size(), null );
		
		// -----------------------------------------------
		// All bribes have unique amounts!
		// first we create them - then we deal them.
		// -----------------------------------------------
		Stream.Builder<ActionCard> builder = Stream.<ActionCard>builder();
		for (int index = 1; index <= (lPlayers.size() * 4); index++) {
			long value = index * 100; 
			builder.add(new ActionCard(CardType.BRIBE, value));
		}
		Stream<ActionCard> stream = builder.build();
		List<ActionCard> lBribes = stream.collect(Collectors.toList());

		// -----------------------------------------------
		// All agents have unique work-experience.
		// -----------------------------------------------
		builder = Stream.<ActionCard>builder();
		for (int index = 1; index <= (lPlayers.size() * 2); index++) {
			int value = (index <= lPlayers.size()) ? index : index + 3;	//values 1-5, 8-12
			builder.add(new ActionCard(CardType.AGENT, value));
		}
		stream = builder.build();
		List<ActionCard> lAgents = stream.collect(Collectors.toList());

		// -----------------------------------------------
		// All secret documents have
		// a letter (A-F) and a unique amount of pages.
		// -----------------------------------------------
		List<SecretCard> lSecrets = SecretCards();

		// -----------------------------------------------
		// All the cards are now instantiated.
		// Now deal them to the users.
		// An even number of iterations and
		// every second iteration in reversed order
		// give all users the same total amounts
		// Deal:
		// 4 Secret documents per USER
		// 4 Bribes per USER
		// 2 Agents per USER
		// 1 per USER of everything else
		// -----------------------------------------------

		for (int i = 0; i < 4; i++) {
			int idx = i;
			lPlayers.forEach((u) -> {
				// Only one card per User of these!
				if (idx == 0) {
					u.addActionCard(new ActionCard(CardType._1_SECRET_MISSION, 	0).setPlayer(u.getName())); 
					u.addActionCard(new ActionCard(CardType._2_EMBASSY_MEETING, 0).setPlayer(u.getName())); 
					u.addActionCard(new ActionCard(CardType.REPORT, 			0).setPlayer(u.getName())); 
					u.addActionCard(new ActionCard(CardType.COUNTERESPIONAGE, 	0).setPlayer(u.getName())); 
				}

				// Two cards per User
				if (idx < 2) {
					ActionCard s = lAgents.get(0);
					if (s != null) {
						u.addActionCard(s.setPlayer(u.getName()));
						lAgents.remove(s);
					}
				}

				// Four cards per User
				ActionCard m = lBribes.get(0);
				if (m != null) {
					u.addActionCard(m.setPlayer(u.getName()));
					lBribes.remove(m);
				}

				// Four cards per User
				SecretCard secret = lSecrets.get(0);
				if (secret != null) {
					u.addSecretCard(secret);
					lSecrets.remove(secret);
				}

			});
			// ---------------------------------
			// REVERSE SORT TO MAKE EQUAL TOTALS FOR ALL USERS
			// ---------------------------------
			Collections.reverse(lPlayers);
		}

		// PLACE REMAINING lSecrets IN counterfoil.
		battle.init(lSecrets);
		// lSecrets should now be empty.

	}

	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
		Set<Object> seen = ConcurrentHashMap.newKeySet();
		return t -> seen.add(keyExtractor.apply(t));
	}

	public List<SecretCard> SecretCards() {
		List<SecretCard> lSecretCards;
		
		Supplier<SecretCard> newSecretCard = () -> {
			return new SecretCard((char) (random.nextInt(6) + 65), // A-F
					random.nextInt(300), // 0-300
					"Something a spy would want..."); //
		};

		Stream.Builder<SecretCard> builder = Stream.<SecretCard>builder();
		for (int index = 0; index < 120; index++) {
			builder.add(newSecretCard.get());
		}
		List<SecretCard> list = builder.build().collect(Collectors.toList());

		lSecretCards = list.stream().filter(distinctByKey(SecretCard::getPages)).collect(Collectors.toList());

		return lSecretCards;
	}

	// Listeners-------------------------------------------------
	JRadioButton rbOLD = null;

	public void itemStateChanged(ItemEvent e) {
		Object o = e.getSource();
		if (o instanceof JCheckBox) {
			lPlayers.stream().forEach(u -> {
				if (u.getName().toString().equalsIgnoreCase(((JCheckBox) e.getSource()).getName())) {
					if (u.getHandledBy() == PlayerHandler.MANUAL) {
						if (e.getStateChange() == ItemEvent.SELECTED)
							JOptionPane.showMessageDialog(frame, "You can't exclude a manual player!");
						((JCheckBox) o).setSelected(false);
						return;
					}

					// Toggle
					if (e.getStateChange() == ItemEvent.SELECTED) {
						long cnt = lPlayers.stream().filter(p->p.getHandledBy() != PlayerHandler.NONE).count();
						if(cnt < 2) 
						{
							JOptionPane.showMessageDialog(frame, "You can't exclude all the players!");
						}
						else
							u.setHandledBy(PlayerHandler.NONE);
					}
					else
						u.setHandledBy(PlayerHandler.AUTO);
				}
			});
		}
		if (o instanceof JRadioButton) {
			if (e.getStateChange() == ItemEvent.DESELECTED && ((JRadioButton) o).getName().equals("None")) {
				rbOLD = ((JRadioButton) o);
				return;
			}

			lPlayers.stream().forEach(u -> {
				if (u.getName().toString().equalsIgnoreCase(((JRadioButton) e.getSource()).getName())) {
					if (u.getHandledBy() == PlayerHandler.NONE) {
						if (e.getStateChange() == ItemEvent.SELECTED)
							JOptionPane.showMessageDialog(frame, "You can't choose an excluded player!");

						if (rbOLD != null && !rbOLD.getName().equals(((JRadioButton) o).getName())) {
							rbOLD.doClick();
						}
						return;
					}

					if (e.getStateChange() == ItemEvent.DESELECTED && u.getHandledBy() != PlayerHandler.NONE) {
						rbOLD = ((JRadioButton) o);
					}

					// Toggle
					if (e.getStateChange() == ItemEvent.SELECTED)
						u.setHandledBy(PlayerHandler.MANUAL);
					else
						u.setHandledBy(PlayerHandler.AUTO);

				}
			});

		}

	}
	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
	    if (!source.getValueIsAdjusting()) {
	        int fps = (int)source.getValue();
	        battle.setAutoDelay(fps);
	    }
	}
}