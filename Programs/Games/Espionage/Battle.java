package Espionage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

import Espionage.ActionCard.CardType;
import Espionage.Board.City;
import Espionage.Player.AgentName;
import Espionage.Player.PlayerHandler;
import Espionage.Player.Report;

/**
 * @author Astrid
 * This class contains the game Logic.
 * GuiState is used to handle what to draw on the board
 * and what content is valid to select during the current state.
 */
public class Battle extends MouseAdapter {
	
	public enum GuiState {
		HIDE_GROUP, 
		SHOW_GROUP,
		
		HIDE_FIRST, 
		SHOW_FIRST, 
		GRAB_TOP, 
		
		HIDE_SECOND, 
		SHOW_SECOND,
		MINE_REPORT,
		GRAB_REPORTS, 
		
		RELEASE,
	}

	GuiState spel = null;
	private boolean AUTOPLAY;
	private JLabel 	statusLabel = null;
	
	private List<Player> lPlayers  = null;
	private List<Player> lGroupOne = null;
	private List<Player> lGroupTwo = null;

	//Agents in prison (FIFO)
	private Queue<ActionCard> qPrison = new LinkedList<ActionCard>(); 
	
	//Two visible cards from counterfoil.
	private List<SecretCard> lCounterfoil = new ArrayList<SecretCard>();
	private SecretCard top1;
	private SecretCard top2;

	private List<Long> lPositions;

	private List<Report> lReports = new ArrayList<Report>();
	private List<ActionCard> lTrick = new ArrayList<ActionCard>();

	private Board board = null;
	private String statusMsg;
	private int iDelay;

	
	public Battle(Espionage f) {
		super();
		board = (Board) f.getContentPane();

		// Add a statusbar for game-info
		board.setLayout(new BorderLayout());
		board.add(createStatusBar(), BorderLayout.SOUTH);

		spel = null;
		iDelay=50;
	}
	
	public JPanel createStatusBar() {
		JPanel statuspanel = new JPanel();
		statuspanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		statuspanel.setPreferredSize(new Dimension(board.getWidth(), 25));
		board.getInsets().bottom += 25;
		statusLabel = new JLabel();
		statuspanel.add(statusLabel);
		return statuspanel;
	}

	// To display info during game
	public void setStatus(String arg) {
		statusLabel.setText(arg);
	}
	
	public void init(List<SecretCard> arg) {
		qPrison.clear();
		lCounterfoil.clear();
		lCounterfoil.addAll(arg);
		arg.clear();

		top1 = lCounterfoil.get(0);
		if (top1 != null)
			lCounterfoil.remove(top1);
		
		top2 = lCounterfoil.get(0);
		if (top2 != null)
			lCounterfoil.remove(top2);
		
		if (board != null) {
			board.cleanUp();
			board.settop1(top1);
			board.settop2(top2);
		}
	}


	
	public Predicate<Player> manual() {
		return (u -> u.getHandledBy() == PlayerHandler.MANUAL);
	}

	public void startGame(List<Player> d) {
		System.out.println("=============");
		System.out.println("GAME STARTED!");
		System.out.println("=============");
		setStatus("Game started.");
		lPlayers = d;

		qPrison.clear();
		board.updatePrison(d.size(), qPrison);
		
		// Put the markers on the board
		board.setProgress(d);


		// Start Listener
		board.addMouseListener(this);
		spel = GuiState.HIDE_GROUP;
		AUTOPLAY = (lPlayers.stream().noneMatch(manual()));
		
		autoSelectGroup();
	}

	public void endGame() {
		board.removeMouseListener(this);
		board.setProgress(lPlayers);
		board.setGameOver();

		//The winner is on square No.
		lPositions = lPlayers.stream()
				.map(u -> u.getPosition())
				.sorted(Comparator.reverseOrder())
				.distinct()
				.collect(Collectors.toList());
		
		long maxPos = lPositions.get(0);
		
		//The winner(s)!
		List<Player> lUsers = lPlayers.stream()
				.filter(u -> u.getPosition() == maxPos)
				.collect(Collectors.toList());

		setStatus("Game over.");

		System.out.println("=============");
		System.out.println("GAME OVER!");
		if(lUsers.stream().count() > 1)
		{
			System.out.println("It's a tie!");
		}	
		else
		{
			if(lUsers.get(0).getHandledBy() == Player.PlayerHandler.MANUAL)
			System.out.println("You won this game!");
			else
			System.out.println(lUsers.get(0).getName()+" won this game!");
		}
		System.out.println("=============");
		spel = null;
	}


	public boolean manualHasTheWinningBribe() 
	{
		long lBribes = lTrick.stream().filter(m -> m.getType() == CardType.BRIBE).count();
		if(lBribes == 0) return false;

		ActionCard winner = lTrick.stream()
			.filter(m -> m.getType() == CardType.BRIBE)
			.max(Comparator.comparingLong( m ->  m.getValue() ))
			.get();

		long x = lGroupOne.stream()
			.filter(p -> p.getName() == winner.getPlayer()
					  && p.getHandledBy() == PlayerHandler.MANUAL ).count();
		
		if(x == 1) 	return true;
		else		return false;
	}
	
	
	//Action to take after Player input
	//GUIState spel is used to control valid mouse input
	public void runNextStep() {
		if (spel == null) return;

		switch (spel) {
		case HIDE_GROUP: {
			spel = GuiState.SHOW_GROUP;
			showSelectedGroups();
			break;
		}
		case SHOW_GROUP: {
			spel = GuiState.HIDE_FIRST;
			autoSelect4Group1();
			break;
		}
		case HIDE_FIRST: {
			spel = GuiState.SHOW_FIRST;
			showSelection4Group1();
			
			if( manualHasTheWinningBribe() ) 
			{
				spel = GuiState.GRAB_TOP;
				grabMyTopCard();
				break;
			}			

			break;
		}
		case SHOW_FIRST: {
			spel = GuiState.HIDE_SECOND;
			autoSelect4Group2();
			break;
		}
		case GRAB_TOP: {
			spel = GuiState.HIDE_SECOND; 
			autoSelect4Group2 ( ); 
			break; 
		}
		case HIDE_SECOND: {
			spel = GuiState.SHOW_SECOND;
			showSelction4Group2();
			break;
		}
		case SHOW_SECOND: {
			spel = GuiState.RELEASE;
			releasePrisoners();
			break;
		}
		case MINE_REPORT: {
			spel = GuiState.RELEASE;
			embassyMeeting();
			releasePrisoners(); 
			break;
		}

		case RELEASE: {
			spel = GuiState.HIDE_GROUP;
			autoSelectGroup();
			break;
		}
		default:
		}

	}

	public void mousePressed(MouseEvent e) {
		if (spel == null || AUTOPLAY) {
			return;
		}
		
		Point p2 = e.getPoint();
		for (Player usr : lPlayers) {
			if (usr.getHandledBy() == PlayerHandler.MANUAL) {

				//Will paint the board. If the click occurred 
				//in an allowed position, the clicked object
				//will be added to the Player object(usr).
				board.handleClick(usr, spel, p2);

				
				//Wait for the paint cycle to finish and check
				//if the player object contains the wanted object.
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						//------------------------------
						if (spel == GuiState.GRAB_REPORTS) {
							HashMap<AgentName, SecretCard> STOLEN = usr.getStolen();
							if (STOLEN == null || STOLEN.isEmpty()) 
							{
								return;
							} else {
								System.out.print("\t* You stole secretCard(s) from: ");
								STOLEN.forEach((k, v) -> {
									for (Player u2 : lGroupTwo) {
										if (u2.getName() == k) {
											usr.addSecretCard(v);
											u2.delete(v);
											System.out.print(k+" ");
											lReports.stream().forEach(r->
											{
												if(r.getName() == k) 
													r.getCardList().remove(v);	
											});
										}
									}
								});
								System.out.print("\n");
								if(Battle.this.currentTask != null)
								{
									Battle.this.currentTask.goForward();
									Battle.this.currentTask = null;
								}
									
								
							}
						}
						//------------------------------
						else if (spel == GuiState.MINE_REPORT) {
							if (usr.getReport() == null) {
								return;
							} else {
								runNextStep();
							}
						} 
						//------------------------------
						else if (spel == GuiState.GRAB_TOP) {
							if (usr.getSelectedSecretCard() == null) {
								setStatus("Select a secret card from the board!");
								return;
							} else {
								SecretCard selected = usr.getSelectedSecretCard();
								if (selected == top1) {
									usr.addSecretCard(top1);
									top1 = lCounterfoil.get(0);
									lCounterfoil.remove(top1);
									board.settop1(top1);
								} else if (selected == top2) {
									usr.addSecretCard(top2);
									top2 = lCounterfoil.get(0);
									lCounterfoil.remove(top2);
									board.settop2(top2);
								} else {
									System.out.println("No secret card grabbed???");
								}
								runNextStep();
							}
						} 
						//------------------------------
						else {
							if (usr.getSelectedAction() == null) {
								return;
							} else {
								runNextStep();
							}
						}
						//------------------------------
					}
				});
				return;
			}
		}
	}

	// Showing group selection back-side up (1|2).
	public void autoSelectGroup() {
		
		// Current positions!
		lPositions = lPlayers.stream()
				.map(u -> u.getPosition())
				.sorted(Comparator.reverseOrder())
				.distinct()
				.collect(Collectors.toList());

		// If the leading marker has entered the last city - end the game.
		if (board.getCityTable().get(lPositions.get(0)) == City.SUMMIT) {
			System.out.println("\t============================");
			System.out.println("\tLAST CITY. DO FINAL REPORT! ");
			System.out.println("\t============================");
			doReport(true);
			endGame();
			return;
		}
		// -------------------------------------
		//Clear temp.vars 
		for (Player usr : lPlayers) {
			usr.getAllCards().forEach(c->c.setSelected(false));
			usr.setActionCard(null, spel);
			usr.setSecretCard(null, spel);
			usr.setReport(null);
			usr.setStolenCards(null);
		}
		board.setProgress(lPlayers);
		// -------------------------------------
		// Showing group selection back side up.
		lTrick.clear();
		for (Player usr : lPlayers) {
			if (usr.getHandledBy() == PlayerHandler.MANUAL) {
				// A placeholder is added.
				usr.addSelectedGroup(lTrick); 	// may disable some cards.
				board.setCards(usr, spel); 		// update user's visible cards.
				setStatus("Join group 1 (secret mission) or 2 (embassy meeting).");
			} else
				usr.addSelectedGroup(lTrick);
		}
		board.setTrick(lTrick, spel);
		// -------------------------------------

		if (AUTOPLAY)
			proceed();
		// wait for manual user to select group...
	}

	// Showing group selection front up (1|2).
	public void showSelectedGroups() 
	{
		lTrick.clear();
		for (Player usr : lPlayers) {
			if (usr.getHandledBy() == PlayerHandler.MANUAL)
				setStatus("Click to continue....");

			lTrick.add(usr.getSelectedAction());
		}
		board.setTrick(lTrick, spel);

		//manual user will be sorted last.
		lGroupOne = lPlayers.stream()
				.filter(d -> d.getSelectedAction().getType() == CardType._1_SECRET_MISSION)
				.sorted(Comparator.comparing(d -> ((Player) d).getHandledBy()))
				.collect(Collectors.toList()); 

		lGroupTwo = lPlayers.stream()
				.filter(d -> d.getSelectedAction().getType() == CardType._2_EMBASSY_MEETING)
				.sorted(Comparator.comparing(d -> ((Player) d).getHandledBy()))
				.collect(Collectors.toList());

		if (AUTOPLAY)
			proceed();

	}

	// showing group1's selection back side up(Agent|Bribe)
	public void autoSelect4Group1() 
	{
		lTrick.clear();

		if (lGroupOne != null && !lGroupOne.isEmpty())
			for (Player usr : lGroupOne) {
				usr.setAction4_G1_inAutoPlayer(lTrick);
				if (usr.getHandledBy() == PlayerHandler.MANUAL) {
					board.setCards(usr, spel);
					setStatus("Group1:  Choose Agent or Bribe.");
				}
			}
		board.setTrick(lTrick, spel);

		if (AUTOPLAY)
			proceed();
		
		// if no manual participant in this group continue...
		else 
		if (lGroupOne.stream().filter(manual()).count() == 0) {
			runNextStep();
		}
	}

	// showing group1's selection front side up(Agent|Bribe)
	public void showSelection4Group1() 
	{
		lTrick.clear();
		if (lGroupOne != null && !lGroupOne.isEmpty())
			secretMission(lGroupOne);
		else
			board.setTrick(lTrick, spel);

		if (AUTOPLAY)
			proceed();

		// if no manual participant in this group continue...
		else
		if (lGroupOne.stream().filter(manual()).count() == 0) {
			runNextStep();
		}
	}

	// Group2 Show the hidden trick back side up (Agent|Counter-espionage|Report).
	public void autoSelect4Group2() {

		lTrick.clear();
		for (Player usr : lGroupTwo) {
			if (usr.getHandledBy() == PlayerHandler.MANUAL) {
				usr.setAction4_G2_inAutoPlayer(lTrick); // may disable some cards, add a placeholder
				board.setCards(usr, spel);
				setStatus("Group2:  Choose Agent, Report or Counterespionage.");
			} else
				usr.setAction4_G2_inAutoPlayer(lTrick);
		}
		board.setTrick(lTrick, spel);

		if (AUTOPLAY)
			proceed();
		
		else
		if (lGroupTwo.stream().filter(manual()).count() == 0) {
			runNextStep();
		}

	}

	// Group2 Show the trick (Agent|Counter-espionage|Report)
	public void showSelction4Group2() 
	{

		lTrick.clear();
		if (lGroupTwo != null && !lGroupTwo.isEmpty())
		for (Player usr : lGroupTwo) {
			if (usr.getHandledBy() == PlayerHandler.MANUAL) {
				setStatus("Click to continue...");
				
				if (usr.getSelectedAction().getType() == CardType.REPORT) {
					spel = GuiState.MINE_REPORT;
					usr.setReport(null);
					setStatus("Select cards for the Report.");
				}
			}
			lTrick.add(usr.getSelectedAction());
		}
		board.setTrick(lTrick, spel);

		if (spel != GuiState.MINE_REPORT)
			embassyMeeting();

		if (AUTOPLAY)
			proceed();
		
		else 
		if (lGroupTwo.stream().filter(manual()).count() == 0) {
			runNextStep();
		}
	}

	//If the prison contains more than one Agent per player
	//The agents exceeding the number of players will be released.
	public void releasePrisoners() {

		while (qPrison.size() > lPlayers.size()) {
			ActionCard agent = qPrison.remove();
			if (agent != null) {
				Player zzz = lPlayers.stream().filter(m -> m.getName() == agent.getPlayer()).findFirst().orElseThrow();
				zzz.addActionCard(agent);
				
				if(zzz.getHandledBy() == PlayerHandler.MANUAL)
					System.out.println("\t* Your agent (" + agent.getValue() + ") was released.");
				else
					System.out.println("\t* "+zzz.getName() + "s agent (" + agent.getValue() + ") was released.");
					
			}
		}
		
		//both types present?
		if( lGroupTwo.stream()
				.map(m -> m.getSelectedAction().getType() )
				.filter(m -> m == CardType.AGENT 
				          || m == CardType.COUNTERESPIONAGE)
				.distinct()
				.limit( 2 )
				.count() == 2 )
		{
			//Remove the Agents from the trick.
			lTrick = lTrick.stream()
				    .filter(m -> m.getType() != CardType.AGENT)
				    .collect(Collectors.toList());
		}

		board.setProgress(lPlayers);
		board.updatePrison(lPlayers.size(), qPrison);
		board.setTrick(lTrick, spel);
		setStatus("click to continue...");
		
		if (AUTOPLAY )
			proceed();

		else 
		if (lGroupTwo.stream().filter(manual()).count() == 0) {
			runNextStep();
		}
	}

	//Handle Auto Playback speed.
	public int getAutoDelay()
	{
		return iDelay;
	}
	public void setAutoDelay(int speed)
	{
		iDelay = speed;
	}
	public void proceed() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					Thread.sleep(iDelay);
				} catch (InterruptedException e) {
					;
				}
				if (spel != null)
					runNextStep();
			}
		});
	}

	public void grabMyTopCard() 
	{
		Player x = lGroupOne.stream().filter( manual() ).findFirst().get();
		board.setCards(x, spel);
		setStatus( "Group1:  You won a SecretCard.  Pick it from the board! " );
	
	}
	
	public SecretCard getTopCard(int no)
	{
		if(no == 1) return top1;
		else		return top2;
	}
	
	public void secretMission(List<Player> deltagare) {
		System.out.println("\n\tSECRET MISSION  - " + deltagare.size() + " participants");
		if (deltagare.size() == 0)
			return;


		// If no Bribe was played - return!
		long bribe_cnt = deltagare.stream().filter(m -> m.getSelectedAction().getType() == CardType.BRIBE).count();
		if (bribe_cnt == 0) {
			setStatus("Group1:  No bribe was used - click to continue...");
			System.out.println("\t* No bribe was used.");

			//Show the actioncards
			lTrick.clear();
			for (Player usr : deltagare) {
				lTrick.add(usr.getSelectedAction());
			}
			board.setTrick(lTrick, spel);
			return;
		}

		long agent_cnt = deltagare.stream()
				.filter(m -> m.getSelectedAction().getType() == CardType.AGENT)
				.count();

		// 1. Who played the bribe with the greatest amount?
		Player x = deltagare.stream()
				.filter(m -> m.getSelectedAction().getType() == CardType.BRIBE)
				.max(Comparator.comparing(m -> (long) (m.getSelectedAction().getValue())))
				.get();
		

		if (x != null) {
			String msg="";

			//highlights the winning bribe.
			x.getSelectedAction().setSelected( true );
			
			if (x.getHandledBy() == PlayerHandler.MANUAL) {
				msg="Group1:  ";
				System.out.println("\t* You won a secretCard. " );
			} else {
				msg = "Group1: " + x.getName() + " won a secret card. ";
				System.out.println("\t* "+x.getName()+" won a secretCard. Winning bribe "+x.getSelectedAction().getValue() );
				// Select top1 or top2!
				SecretCard selected = x.getSelectedSecretCard();
				if (selected == top1) {
					x.addSecretCard(top1);
					top1 = lCounterfoil.get(0); // Tills lCounterfoil tar slut.
					lCounterfoil.remove(top1);
					if (board != null) {
						board.settop1(top1);
					}

				} else if (selected == top2) {
					x.addSecretCard(top2);
					top2 = lCounterfoil.get(0); // Tills lCounterfoil tar slut.
					lCounterfoil.remove(top2);
					if (board != null) {
						board.settop2(top2);
					}

				} else {
					//Should never get here
					System.out.println("AUTO did not select a secretcard???");
					msg += x.getName() + " did not select a secretcard???  ";
				}
			}

			// Handle Agents
			if (agent_cnt != 1) {
				msg += " The Bribe was not stolen. ";
				System.out.println("\t* "+agent_cnt+" agents - the bribe was NOT stolen.");
				x.delete(x.getSelectedAction());
			} else {
				Player z = deltagare.stream().filter(m -> m.getSelectedAction().getType() == CardType.AGENT)
						.findFirst().orElseThrow();
				if( z.getHandledBy() == PlayerHandler.MANUAL) 
				{
					msg += "You stole the bribe. ";
					System.out.println("\t* You stole "+x.getName()+"'s bribe.");
				} else {
					msg += z.getName()+" stole the bribe. ";
					System.out.println("\t* "+z.getName()+" stole the bribe.");
				}
				
				
				x.delete(x.getSelectedAction());
				z.addActionCard(x.getSelectedAction());
			}

			if(spel != GuiState.GRAB_TOP)
				msg += " click to continue...";
			
				setStatus(msg);
		}
		
		//Show the actioncards
		lTrick.clear();
		for (Player usr : deltagare) {
			lTrick.add(usr.getSelectedAction());
		}
		board.setTrick(lTrick, spel);

	}

	public String doReport(boolean gameOver) {

		lReports.clear();

		if (gameOver) {
			lPlayers.stream().forEach(u -> {
				Report r = ReportUtil.getBestReport(u);
				if( r != null)
					lReports.add( r );
			}); 
		} else {
			lPlayers.stream().filter(m -> m.getSelectedAction().getType() == CardType.REPORT)
					.collect(Collectors.toList())
					.forEach(u -> u.addMyReport(lReports)); 
		}

		if (lReports == null || lReports.isEmpty()) {
			return " ";
		}
		System.out.println("\t* We got " + lReports.size() + " Report(s).");

		// Find the best Report
		List<Report> sorterad = lReports.stream()
				.sorted(Comparator.comparingLong(c -> ((Report) c).getCardCount())
						  	  .thenComparingLong(c -> ((Report) c).getMaxValue()).reversed())
				.collect(Collectors.toList());

		// The leading participant's position is used...
		long lLeader = lPositions.get(0);
		City stad = board.getCityTable().get(lLeader);

		// The two best will move forward on the board.
		String msg = "Group2:  ";
		int max = sorterad.size();
		if (max > 0) {
			msg = "Moves due to Report ";
			Report No1 = sorterad.get(0);
			Player p1 = lPlayers.stream().filter(p->p.getName() == No1.getName()).findFirst().get();
			
			if(p1.getHandledBy() == PlayerHandler.MANUAL) {
				msg +=          "(You " + stad.first ;
				System.out.println("\t* You had the best Report("+No1.getCardCount()+", "+No1.getMaxValue()+") and is moved forward "+stad.first+" step(s)." );
			}
			else {
				msg +=        "("+No1.getName() + " " + stad.first ;
				System.out.println("\t* "+No1.getName()+" had the best Report("+No1.getCardCount()+", "+No1.getMaxValue()+") and is moved forward "+stad.first+" step(s)." );
			}
			p1.add2Position(stad.first);
			if(!gameOver)
				p1.getSelectedAction().setSelected(true);	//Highlight the ReportCard
		}
		if (max > 1) {
			Report No2 = sorterad.get(1);
			Player p2 = lPlayers.stream().filter(p->p.getName() == No2.getName()).findFirst().get();
			
			if(p2.getHandledBy() == PlayerHandler.MANUAL) {
				msg += ", You " + stad.second ;
				System.out.println("\t* You had the second best Report ("+No2.getCardCount()+", "+No2.getMaxValue()+") and is moved "+stad.second+" step(s)." );

			}
			else {
				msg += ", "+No2.getName() + " " + stad.second;
				System.out.println("\t* "+No2.getName()+" had the second best Report ("+No2.getCardCount()+", "+No2.getMaxValue()+") and is moved "+stad.second+" step(s)." );
				
			}
			p2.add2Position(stad.second);
			if(!gameOver)
				p2.getSelectedAction().setSelected(true);	//Highlight the ReportCard
		}
		if (max > 0) msg += ") ";

		
		setStatus(msg);
		board.setProgress(lPlayers);
		return msg;
	}

	public void embassyMeeting() {
		System.out.println("\n\tEMBASSY MEETING - " + lGroupTwo.size() + " participants.");
		if (lGroupTwo.size() == 0)
			return;
		
		statusMsg = "Group2:  ";

		long reportCnt = lGroupTwo.stream()
				.filter(m -> m.getSelectedAction().getType() == CardType.REPORT)
				.count();

		long AgentCnt = lGroupTwo.stream()
				.filter(m -> m.getSelectedAction().getType() == CardType.AGENT)
				.count();

		long CounterCnt = lGroupTwo.stream()
				.filter(m -> m.getSelectedAction().getType() == CardType.COUNTERESPIONAGE)
				.count(); 

		if((reportCnt == 0 && AgentCnt == 0)
		|| (reportCnt == 0 && CounterCnt  == 0)	)
		{
			statusMsg += "click to continue";
			System.out.println("\tNo action in Group2.");
			setStatus(statusMsg);
			return;
		}

		
		if (reportCnt != 0) {
			boolean GameOver = false;
			statusMsg = doReport(GameOver);
		}
		
		// All the agents may steal one card from each Report
		if (CounterCnt == 0 && AgentCnt != 0 && reportCnt != 0) {

			//highlights agents
			lGroupTwo.stream()
			.filter(m -> m.getSelectedAction().getType() == CardType.AGENT)
			.forEach(c->c.getSelectedAction().setSelected(true));
			
			// all the agents will choose in order - we need to wait for the manual one to pick his card(s).
			Thread t = new Thread(new Runnable() {
				Object monitor = new Object();

				@Override
				public void run() {

					lGroupTwo.stream().filter(m -> m.getSelectedAction().getType() == CardType.AGENT)
							.sorted(Comparator.comparingLong(m -> ((Player) m).getSelectedAction().getValue()).reversed())
							.collect(Collectors.toList()).forEach(u -> {
								// -----------------------------------------------------------------
								if (u.getHandledBy() == Player.PlayerHandler.MANUAL) {
									// Wait for user to select the secret cards...
									spel = GuiState.GRAB_REPORTS;
									Thread t2 = new Thread(new Task(monitor, u));
									t2.start();
									synchronized (monitor) {
										try {
											monitor.wait();
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
										spel = GuiState.SHOW_SECOND;
									}
								}
								// -----------------------------------------------------------------
								else {
									System.out.print("\t* " + u.getName() + " stole secretCard(s) from: ");
									Battle.this.statusMsg += " "+u.getName() + " stole secretCard(s)  from  ";
									lReports.forEach(r -> {
										SecretCard gc = u.stealCard(r);
										if (gc != null) {
											for (Player u2 : lGroupTwo) {
												if (u2.getName() == r.getName()) {
													if(u2.getHandledBy() == PlayerHandler.MANUAL)
														System.out.print( "You " );
													else
														System.out.print( r.getName()+" " );
													
													u.addSecretCard(gc);
													u2.delete(gc);
													Battle.this.statusMsg += r.getName()+" "; 
												}
											}
										}
									});
									System.out.print("\n");
								}
								// -----------------------------------------------------------------
							});// User
					//Get rid of the display when all the agents are done.
					board.setRedovisningar(null, spel);
					lReports.clear();
				} // Run
			}); // Thread
			t.start();
			// -----------------------------------------------------------
		}
		

		// All the agents in the Trick will be imprisoned
		if (CounterCnt != 0 && AgentCnt != 0) {
			if(reportCnt == 0)
				statusMsg += "Moves due to Counter...(";
			else
				statusMsg += ", Counter...(";
			
			lGroupTwo.stream().filter(m -> m.getSelectedAction().getType() == CardType.COUNTERESPIONAGE)
					.collect(Collectors.toList()).forEach(u -> {
						
						
					//lPosition contain all square# with at least one marker.
					for (long qqq : lPositions) {
						
						if (qqq == u.getPosition()) {
							long idx = lPositions.indexOf(qqq) + 1;
							
							if(!statusMsg.endsWith("Counter...("))
								statusMsg += ", ";
								
							if(u.getHandledBy() == PlayerHandler.MANUAL) {
								statusMsg += "you "+idx;
								System.out.print("\t* You selected counterespionage and is moved forward ");
							}
							else {
								statusMsg += u.getName()+" "+idx;
								System.out.print("\t* " + u.getName() + " selected counterespionage and is moved forward ");
							}
							System.out.println(idx + " step(s).");

							u.add2Position(idx);
						}
					}
					
			});

			statusMsg += ") ";
			statusMsg += "and "+ AgentCnt+" agent(s) was put in jail. ";


			lGroupTwo.stream().filter(m -> m.getSelectedAction().getType() == CardType.AGENT)
			.sorted(Comparator.comparing(m -> m.getSelectedAction().getValue()))
			.collect(Collectors.toList())
			.forEach(u -> {
				ActionCard agent = u.getSelectedAction();
				u.delete(agent);
				
				//So the Player can get it back.
				agent.setPlayer(u.getName()); 
				qPrison.add(agent);
				
				if(u.getHandledBy() == PlayerHandler.MANUAL)
					System.out.println("\t* Your agent ("+agent.getValue()+") was put in jail.");
				else
					System.out.println("\t* " + u.getName() + "'s agent ("+agent.getValue()+") was put in jail.");
			});
			
		}
		
		setStatus(statusMsg);
		board.setProgress(lPlayers);

		
	}

//	//======================================================================
	class Task implements Runnable {
		Object monitor;
		Player u;

		public Task(Object monitor, Player usr) {
			this.monitor = monitor;
			this.u = usr;
		}

		@Override
		public void run() {
			spel = GuiState.GRAB_REPORTS;
			setStatus("Pick one SecretCard from each Report.");
			board.setRedovisningar(lReports, spel);
			Battle.this.currentTask = this;
		}

		public void goForward() {
			spel = GuiState.SHOW_SECOND;
			synchronized (monitor) {
				monitor.notify();
			}
		}
	}

	// ======================================================================
	Task currentTask;
	// ======================================================================
}
