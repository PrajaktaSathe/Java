package Espionage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Espionage.Battle.GuiState;
import Espionage.Player.AgentName;
import Espionage.Player.PlayerHandler;
import Espionage.Player.Report;
import Espionage.ActionCard.CardType;

/**
 * @author Astrid
 * This class contains the drawing of 
 * the gameboard and
 * the manual player's different cards.
 * 
 * GuiState is used to handle what to draw on the board
 * and what content is valid to select during the current state. 
 * **/
@SuppressWarnings("serial")
public class Board extends JPanel {
	
	public static final long MAX_Position = 40;

	public enum City {
		STOCKHOLM	(2, 1, 5, Color.white), 
		BERLIN		(3, 2, 2, Color.decode("0xff5733")),
		HONG_KONG	(4, 2, 2, Color.decode("0x33e9ff")), 
		MOSCOW		(5, 3, 2, Color.decode("0xfc0329")),
		PARIS		(3, 2, 2, Color.decode("0xa5608d")), 
		SYDNEY		(4, 2, 3, Color.decode("0xc6ad44")),
		PRAGUE		(2, 1, 2, Color.decode("0xbebebd")), 
		BAGHDAD		(3, 2, 2, Color.decode("0x3b81c0")),
		PEKING		(4, 2, 3, Color.decode("0xf4fc03")), 
		SAO_PAULO	(3, 2, 2, Color.decode("0xd41202")),
		LONDON		(4, 2, 2, Color.decode("0x028a02")), 
		CAIRO		(2, 1, 2, Color.decode("0xfdd361")),
		WASHINGTON	(5, 3, 2, Color.decode("0x0321fc")), 
		SUMMIT		(8, 4, 8, Color.white);

		public final int first;
		public final int second;
		public final int rutor;
		public final Color pnt;

		private City(int i, int j, int r, Color c) {
			first = i; // steps for vinner to progress
			second = j; // steps for second to progress
			rutor = r; // inner squares to draw.
			pnt = c;
		}

	};


	public enum Status {
		INACTIVE, 
		STARTED, 
		ENDED,
	};

	private JFrame frame = null;
	private Status gameStatus=Status.INACTIVE;
	private final int TOOLTIP = 25;

	private HashMap<Player, Long> hmPlayerPosition = new HashMap<Player, Long>(); // MARKERS
	private HashMap<Long, City> hmPosBelongToCity = new HashMap<Long, City>();

	private SecretCard top1=null;
	private SecretCard top2=null;
	private int iPrisonlength=0;
	private LinkedList<ActionCard>	qPrison=new LinkedList<ActionCard>();

	// Used to draw the players currently selected action.
	private List<ActionCard> lTrick = null;
	
	// Used to draw the manual player's hand of cards..
	private Player pManual = null; 
	
	// When the manual player choose Grp:2 + Agent and succeed...
	private List<Report> lReportsToStealFrom = null;

	// Event handling
	private Point clicked = null; 
	private GuiState state = null; 		// Used to check if it is allowed to select this card at this time.

	// Variables set during paint event ( handled in handleClick )
	private ActionCard selectedActionCard=null;
	private SecretCard selectedSecretCard=null;
	
	private Report myReport=null;
	
	private boolean bIamDone=false;
	private HashMap<AgentName, SecretCard> hmStolenCards = new HashMap<AgentName, SecretCard>(); 
	//will contain one selected SecretCard from each Reporting Player!

	public Board(JFrame f) {
		frame = f;
		init();
	}

	/*
	 * Predicate for SecretCards
	 */
	Predicate<SecretCard> ok4Click() {
		return (sc -> {
			switch (state) {
			case GRAB_TOP: {
				if (sc == top1 || sc == top2)
					return true;
				else
					return false;
			}
			case GRAB_REPORTS: {
				if (pManual.getAllDocs().contains(sc))
					return false;
				else if (sc == top1 || sc == top2)
					return false;
				else
					return true;
			}
			case MINE_REPORT: {
				if (pManual.getAllDocs().contains(sc))
					return true;
				else
					return false;
			}
			default:
				return false;
			}
		});
	}

	/*
	 * Predicate for ActionCards
	 */
	Predicate<ActionCard> ok2Click() {
		return (ac -> {
			switch (state) {
			case HIDE_GROUP:
				return (ac.getDisabled() == false 
				&& (ac.getType() == CardType._1_SECRET_MISSION 
				 || ac.getType() == CardType._2_EMBASSY_MEETING));
				
			case HIDE_FIRST:
				return (ac.getDisabled() == false 
				&& pManual != null 
				&& pManual.getFirstSelection().getType() == CardType._1_SECRET_MISSION  
				&& (ac.getType() == CardType.AGENT 
				 || ac.getType() == CardType.BRIBE));
				
			case HIDE_SECOND:
				return (ac.getDisabled() == false 
				&& pManual != null 
				&& pManual.getFirstSelection().getType() == CardType._2_EMBASSY_MEETING  
				&& (ac.getType() == CardType.REPORT 
				 || ac.getType() == CardType.AGENT
				 || ac.getType() == CardType.COUNTERESPIONAGE));
			
			case RELEASE:
			default:
				return false;
			}

		});
	}

	public Status getGameStatus()
	{
		return gameStatus;
	}
	
	public void cleanUp() 
	{
		pManual = null;
		myReport = null;
		bIamDone = false;
		top1 = null;
		top2 = null;
		
		hmPlayerPosition.clear();
		if(lTrick != null) lTrick.clear();
		if(lReportsToStealFrom != null) lReportsToStealFrom.clear();
		qPrison.clear();
		if(hmStolenCards != null) hmStolenCards.clear();
		
		gameStatus = Status.INACTIVE;
		clicked = null;
		state = null; 
		
		selectedActionCard = null;
		selectedSecretCard = null;
		
	}
	
	private void init() {
		gameStatus = Status.INACTIVE;
		setBackground(Color.decode("0xacacac"));

		City[] cities = City.values();
		int stad = 0;
		
		// squares on the board...
		for (long i = 0; i < MAX_Position; i++) 
		{
			// All squares need to be in a city.
			hmPosBelongToCity.put(i, cities[stad]); 

			if (stad < cities.length - 1) 
			{
				// 3 squares in the right corners
				if (i > 9 && i < 17)
				{
					if (i % 2 == 1)	// Uneven.
						stad++;
				} 
				else 
					if (i % 2 == 0)	// Even.
						stad++; 
			}
		}
		
		cleanUp();
	}

	public HashMap<Long, City> getCityTable() {
		return hmPosBelongToCity;
	}

	/*
	 * Set data to be drawn on the board at next paint event
	 */
	public void settop1(SecretCard t1) {
		top1 = t1;
		frame.repaint();
	}

	public void settop2(SecretCard t2) {
		top2 = t2;
		frame.repaint();
	}

	public void setTrick(List<ActionCard> l, GuiState s) {
		state = s;
		lTrick = l;
		frame.repaint();
	}

	public void setGameOver() {
		gameStatus = Status.ENDED;
		frame.repaint();
	}

	public void setProgress(List<Player> deltagare) {
		gameStatus = Status.STARTED;
		hmPlayerPosition.clear();
		for (Player u : deltagare) {
			hmPlayerPosition.put(u, u.getPosition());
		}
		frame.repaint();
	}

	public void setRedovisningar(List<Report> r, GuiState s) {
		state = s;
		lReportsToStealFrom = r;
		hmStolenCards.clear();
		if (lReportsToStealFrom != null && !lReportsToStealFrom.isEmpty()) {
			lReportsToStealFrom.stream().forEach(redov -> {
				if(redov.getCardList() != null && !redov.getCardList().isEmpty())
					hmStolenCards.put(redov.getName(), null);
			});
		}
		frame.repaint();
	}

	public void setCards(Player usr, GuiState s) {
		pManual = usr;
		myReport = usr.getEmptyReport();
		bIamDone = false;
		state = s;
		frame.repaint();
	}
	
	public void updatePrison(int len, Queue<ActionCard> arg)
	{
		iPrisonlength = len;
		qPrison.clear();
		if(arg != null) {
			qPrison.addAll(arg);
			Collections.reverse(qPrison);
		}
	}
	
	public void handleClick(Player usr, GuiState s, Point p) {
		clicked = p;
		state = s;
		
		frame.repaint();
		if (p != null) {
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {

					if (selectedSecretCard != null) 
					{
						usr.setSecretCard(selectedSecretCard, state);
						clicked = null;
						selectedSecretCard = null;
					}
					if (selectedActionCard != null) 
					{
						usr.setActionCard(selectedActionCard, state);
						clicked = null;
						selectedActionCard = null;
					}
					if (usr != null && myReport != null && bIamDone && ReportUtil.isValid(usr,  myReport) ) 
					{
						usr.setReport(myReport);
						clicked = null;
						myReport = null;
						bIamDone = false;
					}

					if (hmStolenCards != null && !hmStolenCards.isEmpty() && hmStolenCards.values().stream().filter(x -> x == null).count() == 0) {
						usr.setStolenCards(hmStolenCards);
						clicked = null;
						// Don't set lReportsToStealFrom to null until all the agents are done with it!
					}
				}
			});
		}

	}





	public boolean test4Click(SecretCard card, int x1, int y1, int width, int height, AgentName namn) {
		if (ok4Click().test(card) 
		&& clicked != null 
		&& clicked.x > x1 && clicked.x < x1 + width 
		&& clicked.y > y1 && clicked.y < y1 + height) {
			
			if (state == GuiState.GRAB_REPORTS) {
				
				if(hmStolenCards.containsValue( card )) {
					hmStolenCards.put(namn, null);
					return false;
				}// toggle	
				else {
					hmStolenCards.put(namn, card);
					return true;
				}
			} 
			else 
			if (state == GuiState.MINE_REPORT) {
				if (myReport.getCardList().contains(card)) // toggle
				{
					myReport.getCardList().remove(card);
					return false;
				} else {
					myReport.add(card);
					return true;
				}
			} else {
				//GuiState.GRAB_TOP
				selectedSecretCard = card;
				return true;
			}
		} 
		else 
		if (ok4Click().test(card) 
		&& state == GuiState.MINE_REPORT 
		&& myReport != null
		&& myReport.getCardList().contains(card)) {
			return true;
		}
		return false;
	}
	
	

	public Rectangle getPanelSize() {
		int width = this.getWidth();
		int height = this.getHeight();
		return new Rectangle(width, height);
	}

	public Rectangle stepSize(Rectangle panel, int w, int h) {
		int width = panel.width / w; 
		int height = panel.height / h; 
		return new Rectangle(width, height);
	}
	
	//Paint other agencies Reports to steal cards from
	public int paintRedov(Graphics g, int width, int height) {
		if (lReportsToStealFrom == null || lReportsToStealFrom.isEmpty())
			return 0;
		Rectangle ReportPanel = new Rectangle(width, height);

		//Divide the width between the Players who selected to Report
//		int antal = REPORTS.size();
		Rectangle zzzz = stepSize(ReportPanel, lReportsToStealFrom.size(), 1);
		
//		int q = width - (antal * zzzz.width);
//		int gap = 0;
//		if (antal > 1 && q > 0)
//			gap = q / (antal - 1);

		//No rotated cards here.
		int maxWidth = height / 2;

		int x = 0;
		int y = 0;
		for (Report r : lReportsToStealFrom) {
			
			List<SecretCard> cardsToStealFrom = r.getCardList();
			
			//If all the cards in the Report already got stolen by other agents?
			if (cardsToStealFrom == null || cardsToStealFrom.isEmpty()) {
				hmStolenCards.remove(r.getName());
			} 
			else 
			{
				//Margin(L+R) = Size of one card
				int card_width = zzzz.width / (1 + cardsToStealFrom.size()) ;

				// Paint Background in users color.
				Player user = hmPlayerPosition.keySet().stream().filter(u -> u.getName() == r.getName()).findFirst().get();
				if (user != null)
					g.setColor(user.getColor());
				
				card_width = (card_width > maxWidth) ? maxWidth : card_width;
//				g.fillRect(x, 0, zzzz.width, height); 
				g.fillRect(x, 0, card_width * (1+cardsToStealFrom.size()), height); 
				
				//Left margin
				x += card_width / 2 ;
				
				for (SecretCard s : cardsToStealFrom) {
					test4Click(s, x + 1, y + 1, card_width - 2, height - 2, r.getName());
					if (hmStolenCards.containsValue(s))
							g.drawImage(s.getClickedImage(card_width - 2, height - 2), x + 1, y + 1, frame);
					else	g.drawImage(  s.getFrontImage(card_width - 2, height - 2), x + 1, y + 1, frame);
					x += card_width;
				}
				
				//Right margin
				x += card_width / 2 ;
//				x += gap;
			}
		}
		return height;

	}
	
	public int getCityFontSize(Graphics g, String text, int width, int height) 
	{
		//Shrink till it fits
		int iMax = (width > height) ? height : width;
		float textSize = iMax/4;
		
		Font myFont1 = g.getFont().deriveFont(Font.BOLD, width/4);
		
		java.awt.FontMetrics fmf = g.getFontMetrics(myFont1);
		java.awt.geom.Rectangle2D textBox = fmf.getStringBounds(text, g);
		
		while(textBox.getWidth() > iMax)
		{
			myFont1 = myFont1.deriveFont(--textSize);
			fmf = g.getFontMetrics(myFont1);
			textBox = fmf.getStringBounds(text, g);
		}
		return (int)textSize;
	}
	public void drawCityText(Graphics g, int x, int y, int width, int height, String text, char pos, int size) 
	{
		Font myFont1 = g.getFont().deriveFont(Font.BOLD, size);	
		
		g.setFont(myFont1);
		java.awt.FontMetrics fm = g.getFontMetrics();
		java.awt.geom.Rectangle2D textBox = fm.getStringBounds(text, g);
		
		double kat = Math.sqrt( Math.pow( textBox.getWidth(), 2 ) / 2 );
		int wq = (width  - (int)textBox.getWidth()) / 2;
		int advance = fm.getMaxAdvance()/4;
		
		g.setColor(Color.BLACK);
		
		if(pos == 'T')
			g.drawString(text, x+wq, (int)(y+textBox.getHeight()));
		
		if(pos == 't') //SYDNEY
		{
			AffineTransform affineTransform = new AffineTransform();
			affineTransform.rotate(Math.toRadians(-45), 0, 0);
			Font rotatedFont = myFont1.deriveFont(affineTransform);
			g.setFont(rotatedFont);
			g.drawString(text, (int)(x+advance), (int)(y+kat+textBox.getHeight()-advance));		
//			g.drawLine(x, (int)(y+kat), (int)(x+kat), y);	
			g.setFont(myFont1);
		}
		if(pos == 'b')//PEKING
		{
			AffineTransform affineTransform = new AffineTransform();
			affineTransform.rotate(Math.toRadians(45), 0, 0);
			Font rotatedFont = myFont1.deriveFont(affineTransform);
			g.setFont(rotatedFont);
			g.drawString(text, (int)(x), (int)(y+height-kat));
//			g.drawLine(x, (int)(y+height-kat), (int)(x+kat), y+height);
			g.setFont(myFont1);
			
		}
		if(pos == 'L') {
			AffineTransform affineTransform = new AffineTransform();
			affineTransform.rotate(Math.toRadians(-90), 0, 0);
			Font rotatedFont = myFont1.deriveFont(affineTransform);

			g.setFont(rotatedFont);
			fm = g.getFontMetrics();
			textBox = fm.getStringBounds(text, g);
			wq = (height - (int)textBox.getWidth()) / 2;
			g.drawString(text, (int)(x+textBox.getHeight()), (int)(y+height-wq));
			g.setFont(myFont1);
		}
		if(pos == 'B')
			g.drawString(text, x+wq, (int)(y+height-1)-fm.getDescent());
				
	}
	

	public void paint(Graphics g) {
		super.paint(g);
		if (gameStatus == Status.INACTIVE) {
			g.dispose();
			return;
		}

		// -------------------------------------------------------------------
		// Divide the area between
		// * gameboard and
		// * user's card-display.
		// -------------------------------------------------------------------		
		Rectangle panel = getPanelSize();
		Rectangle step = stepSize(panel, 13, 13);

		// CardDisplay at the bottom.
		int cardDisplay = (pManual != null) ? step.height * 2 + TOOLTIP : TOOLTIP;

		//Black background in the game-board area.
		g.setColor(Color.black);
		g.fillRect(0, 0, panel.width, panel.height-cardDisplay);

		// Leave some margins around the board.
		panel.width -= step.width * 1.5;
		panel.height -= (cardDisplay + 2);
		int startX = step.width+(int)(step.width*0.10);

		// Re-Calculate the GameBoard part of the area..
		step = stepSize(panel, 12, 9);
		Rectangle city = new Rectangle(step.width * 2, step.height * 2);

		// -------------------------------------------------------------------
		// Draw the City boxes
		// bottom left to right, then up, and finally right to left.
		// -------------------------------------------------------------------
		int x = startX;
		int y = panel.height - city.height - 2;
		int xChange = city.width;
		int yChange = 0;
		char pos='T';
		
		//Get best possible font-size using the longest city-name.
		int fontsize = getCityFontSize(g, "WASHINGTON 5/3", city.width, city.height);
		
		for (City c : City.values()) {
			g.setColor(c.pnt);

			//The final one is completely different.
			if (c == City.SUMMIT) {
				g.fillRect(x - step.width, y, city.width + step.width, city.height * 3);
				drawCityText(g, x, y, city.width, city.height, c.toString()+" "+c.first+" / "+c.second , pos, fontsize);
			}

			else if (c == City.SYDNEY 	// Corner cities
				 ||  c == City.PEKING) 	// are painted a bit bigger...
			{
				y -= (step.height / 2);
				if (c == City.PEKING) {
					x -= (step.width / 2);
					pos = 'b';	
				} else pos ='t';
				
				g.fillRect(x, y, city.width + (step.width / 2), city.height + (step.height / 2));
				drawCityText(g, x, y, city.width, city.height+ (step.height / 2), c.toString()+" "+c.first+" / "+c.second , pos, fontsize);

				if (c == City.SYDNEY)
					x += (step.width / 2);
			} 
			else 
			{
				g.fillRect(x, y, city.width, city.height);
				drawCityText(g, x, y, city.width, city.height, c.toString()+" "+c.first+" / "+c.second , pos, fontsize);
			}
			

			// calculate position for the next city.
			// change direction at the corner cities.
			if (c == City.SYDNEY) {
				pos = 'L';
				xChange = 0;
				yChange = -city.height;
			} // CORNERS
			if (c == City.PEKING) {
				pos = 'B';
				xChange = -city.width;
				yChange = 0;
			} // CORNERS
			x += xChange;
			y += yChange;
		}
		// -------------------------------------------------------------------
		// Draw the 40 inner stepping squares
		// bottom left to right
		// then up, then right to left.
		// then down, right and up again.
		// -------------------------------------------------------------------
		x = startX + city.width - step.width + 1;
		y = panel.height - city.height + step.height;
		xChange = step.width;
		yChange = 0;
		g.setColor(Color.black);
		
		for (long i = 0; i < MAX_Position; i++) {
			// Corner cities SIDNEY/PEKING middle steps are a bit bigger 
			if (i == 10 || i == 17) 
			{
				y -= (step.height / 2);
				if (i == 17)
					x -= (step.width / 2);
				
				g.setColor(Color.black);
				g.drawRect(x, y, step.width - 2 + (step.width / 2), step.height - 2 + (step.height / 2));
				
				if (i == 10)
					x += (step.width / 2);
				
			} 
			else {
				g.setColor(Color.black);
				g.drawRect(x, y, step.width - 2, step.height - 2);
			}
			//------------------------------------------------
			// Check if this square contains player's marker?
			//------------------------------------------------
			if (hmPlayerPosition != null && hmPlayerPosition.containsValue(i)) {
				Map<Object, Long> antal_markers = hmPlayerPosition.values().stream()
						.collect(Collectors.groupingBy(progress_val -> progress_val, Collectors.counting()));

				//Found markers will share a circle evenly
				if (antal_markers.get(i) >= 1) {
					int degrees = 360 / Math.toIntExact(antal_markers.get(i));
					int start_degree = 0;
					for (Player u : hmPlayerPosition.keySet()) {
						if (u.getPosition() == i) {
							g.setColor(u.getColor());
							g.fillArc(x+3, y+3, step.width - 6, step.height - 6, start_degree, degrees);
							start_degree += degrees;
						}
					}
					//Make a black contour around the circle
					g.setColor(Color.BLACK);
					g.drawOval(x+3, y+3, step.width - 6, step.height - 6);
					g.drawOval(x+4, y+4, step.width - 8, step.height - 8);
				}

			}
			//------------------------------------------------
			//Change direction at the corners
			//UP
			if (i == 10 || i == 36) {
				xChange = 0;
				yChange = -step.height;
			} // CORNERS
			
			//LEFT
			if (i == 17) {
				xChange = -step.width;
				yChange = 0;
			} // CORNERS

			//DOWN
			if (i == 29) {
				xChange = 0;
				yChange = +step.height;
			}
			//RIGHT
			if (i == 34) {
				xChange = +step.width;
				yChange = 0;
			}
			x += xChange;
			y += yChange;
		}
		// -------------------------------------------------------------------
		// Paint the other players Report cards for you to choose from
		// Will be painted above the board, but only while you choose.
		paintRedov(g, this.getWidth(), step.height * 2);
		// -------------------------------------------------------------------

		if (gameStatus != Status.ENDED) {
			// -------------------------------------------------------------------
			// Paint top cards of counterfoil 
			// -------------------------------------------------------------------
			if (top1 != null || top2 != null) {
				selectedSecretCard = null;
				int x1 = x + (int)(step.width  * 1.5);
				int y1 = y + (int)(step.height * 1.5);
				g.setColor(Color.yellow);
				if (top1 != null) {
					boolean b =  test4Click(top1, x1, y1,        step.width * 2, step.height, null);
					if (b)		g.drawImage(top1.getClickedImage(step.width * 2, step.height), x1, y1, frame);
					else {
						if(ok4Click().test(top1))
							g.drawImage(top1.getFrontImage  (step.width * 2, step.height), x1, y1, frame);
						else
							g.drawImage(top1.getDisabledImage  (step.width * 2, step.height), x1, y1, frame);
					}
					y1 += step.height;
				}
				if (top2 != null) {
					boolean b =  test4Click(top2, x1, y1,        step.width * 2, step.height, null);
					if (b)		g.drawImage(top2.getClickedImage(step.width * 2, step.height), x1, y1, frame);
					else {
						if(ok4Click().test(top2))
							g.drawImage(top2.   getFrontImage(step.width * 2, step.height), x1, y1, frame);
						else
							g.drawImage(top2.getDisabledImage(step.width * 2, step.height), x1, y1, frame);
					}
				}
			}
			// -------------------------------------------------------------------
			// Paint agents in prison 
			// -------------------------------------------------------------------
			{
				int x1 = x + (int)(step.width  * 3.5);
				int y1 = y + (int)(step.height * 3.5);
				int cnt = iPrisonlength;
				y1 += 5;
				g.setColor(Color.gray);
				if(qPrison != null && !qPrison.isEmpty()) 
				{
					for(ActionCard ac : qPrison)
					{
						g.drawImage(ac.getDisabledFrontImage(step.width, (int)(step.height*2)), x1, y1, frame);
						x1 += step.width;
					}
					cnt -= qPrison.size();
				}
				//Draw empty cells if there are any.
				while(cnt > 0)
				{
					cnt--;
					g.drawRect(x1, y1, step.width, (int)(step.height*2) );
					x1 += step.width;
				}
			}
			
			// -------------------------------------------------------------------
			// Paint the current trick - back or front depending on Spel
			// -------------------------------------------------------------------
			if (lTrick != null) {
				int x1 = x + (int)(step.width  * 3.5);
				int y1 = y + (int)(step.height * 1.5);
				g.setColor(Color.yellow);
				if (state == GuiState.HIDE_GROUP 
				|| 	state == GuiState.HIDE_FIRST 
				|| 	state == GuiState.HIDE_SECOND)
					for (ActionCard k : lTrick) {
						g.drawImage(k.getBackImage(step.width, step.height * 2), x1, y1, frame);
						x1 += step.width;
					}
				else
//				if (spel == Spel.SHOW_GROUP 
//				||  spel == Spel.SHOW_FIRST 
//				||  spel == Spel.GRAB_TOP 
//				||  spel == Spel.SHOW_SECOND
//				||  spel == Spel.GRAB_REPORTS 
//				||  spel == Spel.MINE_REPORT
//				||  spel == Spel.MINE_DONE
//				||  spel == Spel.RELEASE)
					for (ActionCard k : lTrick) {
						if (k != null)
							g.drawImage(k.getDisabledFrontImage(step.width, step.height * 2), x1, y1, frame);
						x1 += step.width;
					}

			}
		}
		// -------------------------------------------------------------------
		// Print info about the winner
		// -------------------------------------------------------------------
		if (gameStatus == Status.ENDED) 
		{
			x += step.width * 2;
			y += step.height * 3;

			long maxPos = hmPlayerPosition.values().stream().collect(Collectors.maxBy(Comparator.comparingLong(z -> z))).get();
			
			//The winner(s)!
			List<Player> lUsers = hmPlayerPosition.keySet().stream()
					.filter(u -> u.getPosition() == maxPos)
					.collect(Collectors.toList());

			Map<Object, Long> antal_markers = hmPlayerPosition.values().stream()
					.collect(Collectors.groupingBy(progress_val -> progress_val, Collectors.counting()));

			
			
			String sFinalMessage="";
			if (antal_markers.get(maxPos) > 1) {
				sFinalMessage = "it's a tie!";
			}
			else if (antal_markers.get(maxPos) == 1) {
				if(lUsers.get(0).getHandledBy() == PlayerHandler.MANUAL)
					sFinalMessage = "Congratulations, you won this game!";
				else
					sFinalMessage = lUsers.get(0).getName().toString() + " won this game!";
			}

			float fontSize= getCityFontSize(g, sFinalMessage, step.width*6, step.width*6 );
			Font f2 = g.getFont().deriveFont(Font.BOLD, fontSize );
			g.setFont(f2);			
			g.setColor(Color.yellow);
			g.drawString(sFinalMessage, x, y);

		}
		// -------------------------------------------------------------------
		// Paint user's hand of cards
		// -------------------------------------------------------------------
		if (pManual != null) {
			g.setColor(Color.decode("0x008080"));
			g.fillRect(0, this.getHeight() - cardDisplay, this.getWidth(), cardDisplay - TOOLTIP); 
			
			long lEventCards = pManual.getAllCards().stream().count();
			long lSecretDocs = pManual.getAllDocs().stream().count();

			int xx = 0;
			int yy = this.getHeight() - cardDisplay;

			if (lEventCards + lSecretDocs > 0) {
				int cardw = this.getWidth() / (int) (lEventCards + lSecretDocs);

				List<ActionCard> hk = pManual.getAllCards().stream().sorted(Comparator
						.comparing(c -> ((ActionCard) c).getType().toString()).thenComparingLong(c -> ((ActionCard) c).getValue()))
						.collect(Collectors.toList());

				List<SecretCard> gc = pManual.getAllDocs().stream().sorted(Comparator
						.comparing(c -> ((SecretCard) c).getLetter()).thenComparingLong(c -> ((SecretCard) c).getPages()))
						.collect(Collectors.toList());

				for (ActionCard h : hk) {
					if (ok2Click().test(h) 
					&& clicked != null 
					&& clicked.x > xx && clicked.x < xx + cardw
					&& clicked.y > yy && clicked.y < yy + (cardDisplay - 25)) {
						selectedActionCard = (ActionCard) h;
					}
					
					if(ok2Click().test(h))
							g.drawImage(h.getFrontImage        (cardw - 2, cardDisplay - 25), xx + 1, yy, frame);
					else	g.drawImage(h.getDisabledFrontImage(cardw - 2, cardDisplay - 25), xx + 1, yy, frame);
					xx += cardw;
				}
				
				int mine_xx_fom = xx;
				
				for (SecretCard c : gc) {
					if(cardw > cardDisplay-25)
						cardw = cardDisplay-25;
					boolean b = test4Click(c, xx + 1, yy, cardw - 2, cardDisplay - 25, pManual.getName());
					if (b)  g.drawImage(c.getClickedImage(cardw - 2, cardDisplay - 25), xx + 1, yy, frame);
					else{
						if(ok4Click().test(c))
								g.drawImage(  c.getFrontImage	(cardw - 2, cardDisplay - 25), xx + 1, yy, frame);
						else	g.drawImage(  c.getDisabledImage(cardw - 2, cardDisplay - 25), xx + 1, yy, frame);
					}
					xx += cardw;
				}

				int mine_xx_tom = xx;
				
				if(state == GuiState.MINE_REPORT) 
				{
					if( myReport != null) 
					{
						if(ReportUtil.isValid(pManual, myReport))
						{
							int width = mine_xx_tom - mine_xx_fom;
							
							g.setColor(Color.decode("0x808080"));
							g.fillRect(mine_xx_fom ,yy-40 , width , 40 );
							
							float fontSize = getCityFontSize(g, "Click here when you are done!", width, width);
							g.setFont( g.getFont().deriveFont(fontSize));
							g.setColor(Color.yellow);
							g.drawString("Click here when you are done!", mine_xx_fom , yy-15 );
							
							if(clicked != null 
							&& clicked.x > mine_xx_fom && clicked.x < mine_xx_tom
							&& clicked.y > yy-40    && clicked.y < yy) {
								
								bIamDone = true;
							}
						}
					}
				}
			}
		}
		g.dispose();
	}

}