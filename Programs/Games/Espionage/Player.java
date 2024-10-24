package Espionage;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import Espionage.ActionCard.CardType;
import Espionage.Battle.GuiState;

/**
 *@author Astrid
 * The Player instance holds the player's current position on the board
 * and the current set of ActionCards and SecretCards. 
**/
public class Player {
	
	static enum AgentName {
		MI6		(Color.GREEN), 
		KGB		(Color.RED), 
		SDECE	(Color.MAGENTA), 
		CIA		(Color.BLUE), 
		CCI		(Color.YELLOW);
		public final Color color;
		private AgentName(Color c) {
			color = c;
		}
	};

	static enum PlayerHandler {
		AUTO, 
		MANUAL, 
		NONE,
	};

	private Espionage frame;	

	// Player-info
	private AgentName name;
	private PlayerHandler handledBy;
	private Color myColor;

	//Player's hand of cards
	private List<ActionCard> lmyActionCards;
	private List<SecretCard> lmySecretCards;

	// Game-info
	private Battle battle;
	
	private long position;
	private ActionCard placeholder;
	private ActionCard firstSelection;
	private ActionCard selectedAction;
	private SecretCard selectedSecret;
	private HashMap<AgentName, SecretCard> hmStolen;
	private Report myReport;


	public Player(AgentName idx, Espionage f, Battle b) {
		name = idx;
		frame = f;
		battle = b;

		myColor = idx.color;
		handledBy = PlayerHandler.AUTO;
		position = 0;
		lmyActionCards = new ArrayList<ActionCard>();
		lmySecretCards = new ArrayList<SecretCard>();

		placeholder = new ActionCard(CardType.PLACEHOLDER, 0);
		placeholder.setPlayer(idx);
	}

	public Color getColor() {
		return myColor;
	}

	public void setHandledBy(PlayerHandler sp) {
		handledBy = sp;
	}

	public void addActionCard(ActionCard sk) {
		if (lmyActionCards == null)
			lmyActionCards = new ArrayList<ActionCard>();
		lmyActionCards.add(sk);
	}

	public void addSecretCard(SecretCard gc) {
		if (lmySecretCards == null)
			lmySecretCards = new ArrayList<SecretCard>();
		lmySecretCards.add(gc);
	}

	public void delete(ActionCard sk) {
		if (lmyActionCards != null)
			lmyActionCards.remove(sk);
	}

	public void delete(SecretCard gc) {
		if (lmySecretCards != null)
			lmySecretCards.remove(gc);
	}

	public PlayerHandler getHandledBy() {
		return handledBy;
	}

	public AgentName getName() {
		return name;
	}

	public void setSecretCard(SecretCard sc, GuiState spel) {
		selectedSecret = sc;
	}

	public void setStolenCards(HashMap<AgentName, SecretCard> arg) {
		hmStolen = arg;
	}
	
	public SecretCard getSelectedSecretCard() {
		if (handledBy == PlayerHandler.AUTO) {
			int i = frame.random.nextInt(100);
			if (i <= 50)
				selectedSecret = battle.getTopCard(1); 
			else
				selectedSecret = battle.getTopCard(2); 
		}
		return selectedSecret;
	}

	public HashMap<AgentName, SecretCard> getStolen() {
		return hmStolen;
	}

	// Adds the card for group-selection (1|2) to theTrick 
	public void addSelectedGroup(List<ActionCard> theTrick) {
		//first assert that you have at least one card to play 
		//in the next step if you chose SECRET MISSION at this time.
		long cnt = lmyActionCards.stream()
				.filter(card -> (card.getType() == CardType.BRIBE 
				              || card.getType() == CardType.AGENT))
				.count();

		// --------------------------------------------
		// Set the card Enabled or Disabled
		ActionCard card_1 = lmyActionCards.stream()
				.filter(card -> (card.getType() == CardType._1_SECRET_MISSION))
				.findFirst().orElseThrow();

		if (cnt == 0)
			card_1.setDisabled(true);
		else
			card_1.setDisabled(false);
		// --------------------------------------------
		if (handledBy == PlayerHandler.AUTO) {
			List<ActionCard> cards4FirstSelection = lmyActionCards.stream()
					.filter(card -> (card.getType() == CardType._1_SECRET_MISSION
							      || card.getType() == CardType._2_EMBASSY_MEETING) 
							&& (card.getDisabled() == false))
					.collect(Collectors.toList());

			firstSelection = null;
			if (!cards4FirstSelection.isEmpty()) {
				int i = frame.random.nextInt(cards4FirstSelection.size());
				firstSelection = selectedAction = cards4FirstSelection.get(i);
				theTrick.add(selectedAction);
			} else {
				// we should not get here, ever!
				firstSelection = selectedAction = placeholder;
			} 
		} 
		else {
			theTrick.add(placeholder);
		}

	}

	public ActionCard getFirstSelection() 
	{
		return firstSelection;
	}
	
	// called from Board on MouseClick in Battle.
	public void setActionCard(ActionCard r, GuiState spel) {
		selectedAction = r;
		
		//internal for this.toString()
		if (spel == GuiState.HIDE_GROUP)	
			firstSelection = r;
	}

	// Adds the card for the chosen action in Group 1 (Agent|Bribe) to theTrick 
	// The player can have max 2 Agents (but many bribes)
	// Note that the player can run out of both Agents and Bribes!
	public void setAction4_G1_inAutoPlayer(List<ActionCard> theTrick) {
		selectedAction = null;
		if (handledBy == PlayerHandler.AUTO) {
			List<ActionCard> validCards = lmyActionCards.stream().filter(
					card -> (card.getType() == CardType.BRIBE) 
					     || (card.getType() == CardType.AGENT))
					.collect(Collectors.toList());

			selectedAction = null;
			if (!validCards.isEmpty()) {
				int i = frame.random.nextInt(validCards.size());
				selectedAction = validCards.get(i);
				selectedAction.setPlayer(this.name);	
				theTrick.add(selectedAction);
			} else {
				// we should not get here, ever!
				selectedAction = placeholder;
			}
		} else {
			theTrick.add(placeholder);
		}

	}

	// Adds the card for the chosen action in Group 2 (Agent|Counter-espionage|Report) to theTrick 
	// The player can have max 2 Agents(they might end up in jail). 
	// To use the Report card the player need to have a valid Report
	// Counter-espionage can always be used.
	public void setAction4_G2_inAutoPlayer(List<ActionCard> theTrick) {
		selectedAction = null;
		
		boolean bValid = ReportUtil.hasValidReport(this);

		//Check if the user has cards enough for a valid Report
		//and enable or disable the ActionCard Report.
		lmyActionCards.stream()
		.filter(c -> c.getType() == CardType.REPORT)
		.forEach(c -> c.setDisabled(!bValid));

		if (handledBy == PlayerHandler.AUTO) {
			List<ActionCard> lEmbassay;

			lEmbassay = lmyActionCards.stream()
					.filter(card -> (	card.getType() == CardType.REPORT
									|| 	card.getType() == CardType.AGENT
									|| 	card.getType() == CardType.COUNTERESPIONAGE) 
					&& (card.getDisabled() == false))
					.collect(Collectors.toList());

			selectedAction = null;
			if (!lEmbassay.isEmpty()) {
				int i = frame.random.nextInt(lEmbassay.size());
				selectedAction = lEmbassay.get(i);
				selectedAction.setPlayer(this.name);	
				theTrick.add(selectedAction);
			} else {
				// we should not get here, ever!
				selectedAction = placeholder;
			}

		} else {

			theTrick.add(placeholder);
		}
	}

	public void setReport(Report r) {
		myReport = r;
	}

	public Report getReport() {
		return myReport;
	}

	public void addMyReport(List<Report> arg) {
		// At least 3 secret cards without gap.
		if (handledBy == PlayerHandler.AUTO) {
			//Auto always use the best possible Report
			Report r = ReportUtil.getBestReport(this);
			if (r != null)
				arg.add(r); 

		} else {
			if (myReport != null)
				arg.add(myReport);
		}
	}

	//Autoplayer choose witch card to steal from the Report in the argument 
	//if there are any cards left to steal.
	public SecretCard stealCard(Report r) {
		if (r == null || r.getCardList() == null || r.getCardList().isEmpty())
			return null;

		if (handledBy == PlayerHandler.AUTO) {
			int i = frame.random.nextInt(r.getCardList().size());
			
			SecretCard card = r.getCardList().get(i);
			if (card != null)
				r.getCardList().remove(card);

			return card;
		} else {
			//is handled in Battle.mouseEvent for the manual player.
		}
		return null;
	}

	public long getPosition() {
		return position;
	}
	public void setPosition(long l)	{
		position = l;
	}

	public void add2Position(long arg) {
		if (position + arg < Board.MAX_Position)
			position += arg;
		else
			position = (Board.MAX_Position - 1);
	}

	public ActionCard getSelectedAction() {
		if(selectedAction != null)
			selectedAction.setPlayer(this.getName());
		return selectedAction;
	}

	List<ActionCard> getAllCards() {
		return lmyActionCards;
	}

	List<SecretCard> getAllDocs() {
		return lmySecretCards;
	}

	public void clearCards() {
		if (lmyActionCards != null)
			lmyActionCards.clear();
		if (lmySecretCards != null)
			lmySecretCards.clear();
	}

	public String toString() {
		String message = String.format("%-7s", ((name != null) ? name : "No name"));

		if (firstSelection == null && lmyActionCards != null) {
			long m = lmyActionCards.stream().filter(card -> (card.getType() == CardType.BRIBE))
					.collect(Collectors.summingLong(s -> s.getValue()));

			long y = lmyActionCards.stream().filter(card -> (card.getType() == CardType.AGENT))
					.collect(Collectors.summingLong(s -> s.getValue()));

			message = message.concat(" has " + lmyActionCards.size() + " actionCards");
			message = message.concat(" with $" + m + " in bribes");
			message = message.concat(" and agents withd a total of " + y + " active years.");
		} else if (firstSelection != null) {
			message = message.concat(" Attends: " + firstSelection.getType().toString());
		}

		if (selectedAction != null) {
			message = message.concat(", selected " + selectedAction.getType().toString());
		}

		return message;
	}

	
	/**
	 * A valid Report consist of at least 3 cards with
	 * the same or adjoining letter(s).
	 * Reports are compared and valued by
	 * 1. number of cards.
	 * 2. highest number of pages(unique).
	 **/
	class Report {
		private AgentName name;
		private long cardCount;
		private long maxValue;
		private List<SecretCard> cardList;

		Report(AgentName arg) {
			name = arg;
			cardCount = 0;
			maxValue = 0;
			cardList = new ArrayList<SecretCard>();
		}

		public void setMaxPages() {
			maxValue = cardList.stream()
					.collect(Collectors.maxBy(Comparator.comparingInt(SecretCard::pages)))
					.orElseThrow().getPages();
		}

		public long getCardCount() {
			return cardCount;
		}

		public long getMaxValue() {
			return maxValue;
		}

		public List<SecretCard> getCardList() {
			return cardList;
		}

		public AgentName getName() {
			return name;
		}

		public void clear() {
			cardCount = 0;
			maxValue = 0;
			cardList.clear();
		};

		public void add(long cnt, List<SecretCard> l) {
			cardCount += cnt;
			cardList.addAll(l);
			setMaxPages();
		};

		public void add(SecretCard c) {
			cardCount++;
			cardList.add(c);
			setMaxPages();
		};

	};
	
	public Report getEmptyReport() {
		return (this.new Report(name));
	}


	
}