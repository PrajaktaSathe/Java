package Espionage;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import Espionage.Player.AgentName;

/**
 * @author Astrid
 * This is the cards to make your choices with.
 * This class produces different images of the card.
 **/
public class ActionCard {
	
	static enum CardType {
		_1_SECRET_MISSION, 
		_2_EMBASSY_MEETING, 
		AGENT, 
		BRIBE, 
		COUNTERESPIONAGE, 
		REPORT, 
		
		PLACEHOLDER
	}

	private CardType type;
	private AgentName player;
	private boolean disabled;
	private boolean selected;
	private long value;

	public ActionCard(CardType kt, long idx) {
		value = idx;
		type = kt;
		disabled = false;
		selected = false;
	}

	public CardType getType() {
		return type;
	}

	public long getValue() {
		return value;
	}


	public ActionCard setPlayer(AgentName arg) {
		player = arg;
		return this;
	}

	public AgentName getPlayer() {
		return player;
	}

	public void setDisabled(boolean b) {
		disabled = b;
	}
	
	public boolean getDisabled() {
		return disabled;
	}
	
	public void setSelected(boolean b) {
		selected = b;
	}
	public boolean getSelected() {
		return selected;
	}
	
	public BufferedImage getDisabledFrontImage(int width, int height) {
		return getFrontImage( width,  height,  true );
	}
	
	public BufferedImage getFrontImage(int width, int height) {
		return getFrontImage( width,  height,  disabled);
	}
	
	//Creates Image to show the ActionCard on the Board.
	public BufferedImage getFrontImage(int width, int height, boolean gray) {
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB); 
		Graphics2D g2d = bufferedImage.createGraphics();

		// fill the inner part with white or yellow
		if(selected) 	g2d.setColor(Color.decode("0xFFFFC3"));	//pale yellow
		else			g2d.setColor(Color.white);
		g2d.fillRect(4, 4, width - 8, height - 8);

		// draw 4px border in player's color
		Stroke line4px = new BasicStroke(4f);	
		g2d.setColor(getPlayer().color);
		g2d.setStroke(line4px);
		g2d.drawRoundRect(4, 4, width-8, height-8, 5, 5);


		if (gray)			g2d.setColor(Color.lightGray);
		else 				g2d.setColor(Color.black);

		String msg1 = "";
		String msg2 = "";

		switch (type) {
		case _1_SECRET_MISSION:		msg1 = "1";			msg2 = "";							break;
		case _2_EMBASSY_MEETING:	msg1 = "2";			msg2 = "";							break;
		case AGENT:					msg1 = "A";			msg2 = Long.toString(getValue());	break;
		case BRIBE:					msg1 = "B";			msg2 = Long.toString(getValue());	break;
		case COUNTERESPIONAGE:		msg1 = "C";			msg2 = "";							break;
		case REPORT:				msg1 = "R";			msg2 = "";							break;
		default:					msg1 = "";			msg2 = "";							break;

		}

		//Vary the fontsize
		Font myFont1 = g2d.getFont().deriveFont(Font.BOLD, height / 2 );
		Font myFont2 = g2d.getFont().deriveFont(Font.BOLD, height / 5 );

		g2d.setFont(myFont1);
		{
			int w = g2d.getFontMetrics().stringWidth(msg1);
			int q = (width - w) / 2;
			g2d.drawString(msg1, q, g2d.getFontMetrics().getAscent());
		}

		g2d.setFont(myFont2);
		{
			int w = g2d.getFontMetrics().stringWidth(msg2);
			int q = (width - w) / 2;
			g2d.drawString(msg2, q, height - g2d.getFontMetrics().getDescent()-4);
		}

		g2d.dispose();
		return bufferedImage;
	}

	public BufferedImage getBackImage(int width, int height) {
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = bufferedImage.createGraphics();
		// --------------------------------------------------------------------------
		if (type == CardType.PLACEHOLDER) {
			g2d.setBackground(new Color(0, 0, 0, 0));
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.0f));
			g2d.setColor(getPlayer().color);
			float[] pattern = {5f, 5f};
			Stroke dotted = new BasicStroke(2f,	BasicStroke.CAP_BUTT,	BasicStroke.JOIN_MITER, 1.0f, pattern, 0f);
			g2d.setPaintMode();
			g2d.setStroke( dotted );
			g2d.drawRoundRect(5, 5, width - 10, height - 10, 5, 5);
			g2d.dispose();
			return bufferedImage;
		}

		// ---------------------------------------------------------
		// draw 4px border in white
		Stroke line4px = new BasicStroke(4f);	
		g2d.setStroke(line4px);
		g2d.setColor(Color.white);
		g2d.drawRoundRect(5, 5, width-10, height-10,5,5);

		// fill inner image with black
		g2d.setColor(Color.black);
		g2d.fillRect(5, 5, width - 10, height - 10);

		// draw a question-mark in player's color.
		g2d.setColor(getPlayer().color);
		Font myFont = g2d.getFont().deriveFont(Font.BOLD, height / 2 );
		g2d.setFont(myFont);
		
		FontMetrics qqq = g2d.getFontMetrics();
		Rectangle2D r = qqq.getStringBounds("?", g2d);
		double wq = r.getWidth();
		double hq = r.getHeight();
		double ds = qqq.getDescent();

		double w2 = (width - wq) / 2;
		double h2 = (height -hq) / 2;
		g2d.drawString("?", (int)w2, (int)(h2+hq-ds));

		g2d.dispose();
		return bufferedImage;
	}
}