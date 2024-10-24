package Espionage;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * @author Astrid
 * The Agents want to steal these cards
 * and they use Bribes to get them.
 * This class produces different images of the card.
 */
public record SecretCard(char letter, int pages, String Text) {
	int getPages() {
		return pages;
	}

	char getLetter() {
		return letter;
	}

	enum State{
		DISABLED,
		ENABLED,
		CLICKED
	}
	
	//Creates Image to show the SecretCard on the Board.
	public BufferedImage createImage(int width, int height, State state) {
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bufferedImage.createGraphics();
		// ---------------------------------------------------------
		g2d.setColor(Color.lightGray);
		g2d.fillRect(0, 0, width, height);

		if (state == State.CLICKED)
			g2d.setColor(Color.yellow);
		else 
		if(state == State.DISABLED)
			g2d.setColor(Color.gray);
		else
			g2d.setColor(Color.black);
		g2d.drawRect(2, 2, width - 4, height - 4);

		Font myFont1 = g2d.getFont().deriveFont(Font.BOLD, height / 2 );
		
		FontMetrics qqq = g2d.getFontMetrics(myFont1);
		g2d.setFont(myFont1);

		int w = g2d.getFontMetrics().charWidth(letter);
		int q = (width - w) / 2;
		g2d.drawString(Character.toString(letter), q, qqq.getAscent());
		
		Font myFont2 = g2d.getFont().deriveFont(Font.BOLD, height / 6 );
		
		qqq = g2d.getFontMetrics(myFont2);
		g2d.setFont(myFont2);

		w = g2d.getFontMetrics().stringWidth(Integer.toString(pages));
		q = (width - w) / 2;
		g2d.drawString(Integer.toString(pages), q, height - qqq.getDescent());

		g2d.dispose();
		return bufferedImage;
	}

	public BufferedImage getClickedImage(int width, int height) {
		if (width > height) {
			return rotate( createImage(height, width, State.CLICKED));
		} else		return createImage(width, height, State.CLICKED);
	}

	public BufferedImage getFrontImage(int width, int height) {
		if (width > height) {
			return rotate( createImage(height, width, State.ENABLED));
		} else		return createImage(width, height, State.ENABLED);
	}

	public BufferedImage getDisabledImage(int width, int height) {
		if (width > height) {
			return rotate( createImage(height, width, State.DISABLED));
		} else		return createImage(width, height, State.DISABLED);
	}

	public BufferedImage rotate(BufferedImage src) {
		double theta = (Math.PI * 2) / 360 * 270;

		int width = src.getWidth();
		int height = src.getHeight();

		BufferedImage dest = new BufferedImage(height, width, src.getType());
		Graphics2D graphics2D = dest.createGraphics();
		graphics2D.translate((width - height) / 2, (width - height) / 2);
		graphics2D.rotate(theta, height / 2, width / 2);
		graphics2D.drawRenderedImage(src, null);
		return dest;
	}

}
