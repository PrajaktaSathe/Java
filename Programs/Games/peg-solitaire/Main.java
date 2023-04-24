import javax.swing.JFrame;
/*

STARTER "Main" CLASS FOR PEG SOLITAIRE GAME
THIS IS A PROGRAM FOR PEG SOLITAIRE GAME, TRY TO WIPE OFF AS MANY PEG AS POSSIBLE

*/
public class Main{
	public static void main(String args[]){
		//Creating game object
		PegSolitaire game = new PegSolitaire();

		//exit when frame is closed
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//setting beginning size
		game.setSize(1000, 300);
		
		//make frame visible
		game.setVisible(true);
	}
}