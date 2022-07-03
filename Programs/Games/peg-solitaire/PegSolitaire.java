import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

/**
 * <h1>PEG SOLITAIRE!</h1>
 * Peg Solitaire class extended from JFrame and implements PegSolitaireGame interface.
 * There are 3 inner classes 
 * - ButtonHandler (implements ActionListener interface)
 * - CellHandler (implements ActionListener interface)
 * - RadioButtonHandler (implements ItemListener)
 * @author Mert Gürşimşir
 */

public class PegSolitaire extends JFrame implements PegSolitaireGame{
	private GridLayout grid;
	private JButton[][] board;
	private	JButton reset;
	private JButton load;
	private JButton save;
	private JButton undo;
	private JButton auto;
	private JButton autoAll;
	private JButton pegNumber;
	private JButton emptyCells;
	private JButton pegsOut;
	private JRadioButton type1;
	private JRadioButton type2;
	private JRadioButton type3;
	private JRadioButton type4;
	private JRadioButton type5;
	private JRadioButton type6;
	private ButtonGroup radios;
	private int size;
	private int boardType;
	private int score;
	private boolean toggle;
	private ArrayList<Integer> moves;

	/** No parameter constructor.
	 * 	Creates buttons and constitute beginning frame.
	 */
	public PegSolitaire(){
		super("Peg Solitaire");
		size = 859;
		toggle = false;
		moves = new ArrayList<Integer>();
		boardType = 0;
		score = -1;

		//RADIO BUTTONS
		type1 = new JRadioButton( "European Style", false );
		type2 = new JRadioButton( "J.C. Wiegleb", false );
		type3 = new JRadioButton( "Asymmetrical", false );
		type4 = new JRadioButton( "English Style", false );
		type5 = new JRadioButton( "Diamond", false );
		type6 = new JRadioButton( "Triangular", false );

		//GAME BUTTONS
		reset = new JButton("RESET");
		reset.setToolTipText("Reset the current game.");
		load = new JButton("LOAD");
		load.setToolTipText("Load game to a file.");
		save = new JButton("SAVE");
		save.setToolTipText("Save game to a file.");
		undo = new JButton("UNDO");
		undo.setToolTipText("Undo last move.");
		auto = new JButton("AUTO");
		auto.setToolTipText("Computer plays 1 move.");
		autoAll = new JButton("AUTO ALL");
		autoAll.setToolTipText("Computer plays until game ends.");
		pegNumber = new JButton("PEG NUMBER");
		pegNumber.setToolTipText("Number of pegs at the board.");
		emptyCells = new JButton("EMPTY CELLS");
		emptyCells.setToolTipText("Number of empty cells at the board.");
		pegsOut = new JButton("PEGS OUT");
		pegsOut.setToolTipText("Number of pegs taken out from the board");

		radios = new ButtonGroup();
		radios.add(type1);
		radios.add(type2);
		radios.add(type3);
		radios.add(type4);
		radios.add(type5);
		radios.add(type6);

		//LAYOUT
		grid = new GridLayout(2, 6, 0, 0);
		setLayout(grid);
		add(type1);add(type2);add(type3);add(type4);add(type5);add(type6);
		add(new JLabel(new ImageIcon("1.png")));
		add(new JLabel(new ImageIcon("2.png")));
		add(new JLabel(new ImageIcon("3.png")));
		add(new JLabel(new ImageIcon("4.png")));
		add(new JLabel(new ImageIcon("5.png")));
		add(new JLabel(new ImageIcon("6.png")));

		//ADDING LISTENERS FOR RADIOBUTTONS AND GAME BUTTONS
		type1.addItemListener(new RadioButtonHandler());
		type2.addItemListener(new RadioButtonHandler());
		type3.addItemListener(new RadioButtonHandler());
		type4.addItemListener(new RadioButtonHandler());
		type5.addItemListener(new RadioButtonHandler());
		type6.addItemListener(new RadioButtonHandler());
		type1.setHorizontalAlignment(SwingConstants.CENTER);
		type2.setHorizontalAlignment(SwingConstants.CENTER);
		type3.setHorizontalAlignment(SwingConstants.CENTER);
		type4.setHorizontalAlignment(SwingConstants.CENTER);
		type5.setHorizontalAlignment(SwingConstants.CENTER);
		type6.setHorizontalAlignment(SwingConstants.CENTER);

		reset.addActionListener(new ButtonHandler());
		load.addActionListener(new ButtonHandler());
		save.addActionListener(new ButtonHandler());
		undo.addActionListener(new ButtonHandler());
		auto.addActionListener(new ButtonHandler());
		autoAll.addActionListener(new ButtonHandler());
		pegNumber.addActionListener(new ButtonHandler());
		emptyCells.addActionListener(new ButtonHandler());
		pegsOut.addActionListener(new ButtonHandler());
	}

	/**	
	 * Properly overridden clone method
	 * @return A copy of PegSolitaire object.
	 */
	public PegSolitaire clone(){
		try{
			//CLONING DATA FIELDS
			PegSolitaire copy = (PegSolitaire) super.clone();
			copy.grid = new GridLayout(grid.getRows(), grid.getColumns(), grid.getHgap(), grid.getVgap());

			copy.board = new JButton[board.length][board.length];
			for (int i = 0; i < board.length; ++i){
				for (int j = 0; j < board.length; ++j){
					copy.board[i][j] = new JButton();
					copy.board[i][j].setBackground(board[i][j].getBackground());
				}
			}

			copy.moves = new ArrayList<Integer>(moves);

			copy.reset = new JButton(reset.getText());
			copy.load = new JButton(load.getText());
			copy.save = new JButton(save.getText());
			copy.undo = new JButton(undo.getText());
			copy.auto = new JButton(auto.getText());
			copy.autoAll = new JButton(autoAll.getText());
			copy.pegNumber = new JButton(pegNumber.getText());
			copy.emptyCells = new JButton(emptyCells.getText());
			copy.pegsOut = new JButton(pegsOut.getText());

			copy.type1 = new JRadioButton(type1.getText());
			copy.type2 = new JRadioButton(type2.getText());
			copy.type3 = new JRadioButton(type3.getText());
			copy.type4 = new JRadioButton(type4.getText());
			copy.type5 = new JRadioButton(type5.getText());
			copy.type6 = new JRadioButton(type6.getText());

			copy.radios = new ButtonGroup();
			radios.add(copy.type1);
			radios.add(copy.type2);
			radios.add(copy.type3);
			radios.add(copy.type4);
			radios.add(copy.type5);
			radios.add(copy.type6);

			return copy;
		}
		catch (CloneNotSupportedException e){
			return null; //never happen
		}
	}

	/**
	 * Overridden equals method comparing PegSolitaire objects.
	 * @param other Another PegSolitaire object to compare
	 * @return boolean Returns true if two objects are equal, otherwise false
	 */
	public boolean equals(PegSolitaire other){
		var flag = true;
		
		if (size!=other.size || boardType!=other.boardType || score!=other.score || toggle!=other.toggle) flag = false;
		if (board.length != other.board.length) flag = false;
		for (int i=0; i<board.length;++i)
			for (int j=0; j<board[0].length; ++j)
				if (board[i][j].getBackground() != other.board[i][j].getBackground()) flag = false;
		if (grid.getRows()!=other.grid.getRows() || grid.getColumns()!=other.grid.getColumns() ||
			grid.getVgap()!=other.grid.getVgap() || grid.getHgap()!=other.grid.getHgap()) flag = false;

		return flag;
	}

	/** 
	 * Returns the hash code value.
	 * @return hash code value integer
	 *  
	 */
	public int hashCode(){
		return moves.hashCode();
	}

	/**
	 * Returns a string that indicates the game name.
	 * @return a string representation of the game.
	 */
	public String toString(){
		return "PEG SOLITAIRE GAME";
	}

	/**
	 * Method to create first board from scratch.
	 * @param is_loaded True if the board is loaded from a file.
	 */
	public void Board1(boolean is_loaded){
		reset_screen();
		grid = new GridLayout(10, 7, 2, 2);
		setLayout(grid);
		boardType = 1;
		score = -1;
		moves.clear();

		//Fill the board if it was not loaded from a file.
		if(!is_loaded){
			board = new JButton[7][7];
			for (int i = 0; i < 7; ++i){
				for (int j = 0; j < 7; ++j){
					board[i][j] = new JButton();
					if ( ((i==0 || i==6) && (j==0 || j==1 || j==5 || j==6)) ||
						 ((i==1 || i==5) && (j==0 || j==6)) )
						board[i][j].setBackground(Color.WHITE);
					else board[i][j].setBackground(Color.BLACK);
				}
			}
			board[2][3].setBackground(Color.GRAY);
		}

		//Adding cells to frame.
		addBoard();

		//Adding game buttons to frame.
		add(reset); add(load); add(save); add(undo); add(pegNumber); add(emptyCells); add(pegsOut);
		add(new JLabel()); add(new JLabel()); 
		add (auto);
		add(new JLabel());
		add(autoAll);
		add(new JLabel()); add(new JLabel()); 

		//Adding radio buttons to frame.
		add(type1);add(type2);add(type3);add(type4);add(type5);add(type6);
		addingListenersType();
	}

	/**
	 * Method to create second board from scratch.
	 * @param is_loaded True if the board is loaded from a file.
	 */
	public void Board2(boolean is_loaded){
		reset_screen();
		grid = new GridLayout(11, 9, 2, 2);
		setLayout(grid);
		boardType = 2;
		score = -1;
		moves.clear();

		//Fill the board if it was not loaded from a file.
		if (!is_loaded){
			board = new JButton[9][9];
			for (int i = 0; i < 9; ++i){
				if (i==0 || i==1 || i==2 || i==6 || i==7 || i==8){
					for (int j = 0; j < 9; ++j){
						board[i][j] = new JButton();
						if (j==0 || j==1 || j==2 || j==6 || j==7 || j==8)
							board[i][j].setBackground(Color.WHITE);
						else 
							board[i][j].setBackground(Color.BLACK);
					}
				}
				else{
					for(int j = 0; j < 9; ++j){
						board[i][j] = new JButton();
						board[i][j].setBackground(Color.BLACK);
					}
				}
			}
			board[4][4].setBackground(Color.GRAY);
		}
		
		//Adding cells to frame.
		addBoard();

		//Adding game buttons to frame.
		add(reset);
		add(load);	add(save);
		add(undo);
		add(pegNumber); add(emptyCells); add(pegsOut);
		add (auto);		add(autoAll);

		//Adding radio buttons to frame.
		add(type1);add(type2);add(type3);add(type4);add(type5);add(type6);
		addingListenersType();
	}

	/**
	 * Method to create third board from scratch.
	 * @param is_loaded True if the board is loaded from a file.
	 */
	public void Board3(boolean is_loaded){
		reset_screen();
		grid = new GridLayout(11, 8, 2, 2);
		setLayout(grid);
		boardType=3;
		score = -1;
		moves.clear();

		//Fill the board if it was not loaded from a file.
		if (!is_loaded){
			board = new JButton[8][8];
			for (int i = 0; i < 8; ++i){
				for (int j = 0; j < 8; ++j){
					board[i][j] = new JButton();
					if ( (i==0||i==1||i==2||i==6||i==7) &&
						 (j!=2&&j!=3&&j!=4) )
						board[i][j].setBackground(Color.WHITE);
					else board[i][j].setBackground(Color.BLACK);
				}
			}
			board[4][3].setBackground(Color.GRAY);
		}

		//Adding cells to frame.
		addBoard();

		//Adding game buttons to frame.
		add(reset); 		
		add(load);			
		add(save);			
		add(undo);			
		add(pegNumber); 	
		add(emptyCells); 	
		add(pegsOut);
		add (auto);	
		add(new JLabel());add(new JLabel());add(new JLabel());
		add(autoAll);
		add(new JLabel());add(new JLabel());add(new JLabel());add(new JLabel());

		//Adding radio buttons to frame.
		add(new JLabel()); add(type1);add(type2);add(type3);add(type4);add(type5);add(type6); add(new JLabel());
		addingListenersType();
	}

	/**
	 * Method to create fourth board from scratch.
	 * @param is_loaded True if the board is loaded from a file.
	 */
	public void Board4(boolean is_loaded){
		reset_screen();
		grid = new GridLayout(10, 7, 2, 2);
		setLayout(grid);
		boardType = 4;
		score = -1;
		moves.clear();

		//Fill the board if it was not loaded from a file.
		if (!is_loaded){
			board = new JButton[7][7];
			for (int i = 0; i < 7; ++i){
				for (int j = 0; j < 7; ++j){
					board[i][j] = new JButton();
					if ( (i==0||i==1||i==5||i==6) && (j!=2&&j!=3&&j!=4) )
						board[i][j].setBackground(Color.WHITE);
					else board[i][j].setBackground(Color.BLACK);
				}
			}
			board[3][3].setBackground(Color.GRAY);
		}

		//Adding cells to frame.
		addBoard();

		//Adding game buttons to frame.
		add(reset); add(load); add(save); add(undo); add(pegNumber); add(emptyCells); add(pegsOut);
		add(new JLabel()); add(new JLabel()); 
		add (auto);
		add(new JLabel());
		add(autoAll);
		add(new JLabel()); add(new JLabel()); 

		//Adding radio buttons to frame.
		add(type1);add(type2);add(type3);add(type4);add(type5);add(type6);
		addingListenersType();
	}

	/**
	 * Method to create fifth board from scratch.
	 * @param is_loaded True if the board is loaded from a file.
	 */
	public void Board5(boolean is_loaded){
		reset_screen();
		grid = new GridLayout(11, 9, 2, 2);
		setLayout(grid);
		boardType = 5;
		score = -1;
		moves.clear();

		//Fill the board if it was not loaded from a file.
		if (!is_loaded){
			board = new JButton[9][9];
			for (int i = 0; i < 9; ++i){
				for (int j = 0; j < 9; ++j){
					board[i][j] = new JButton();
					if (i==0 || i==8){
						if (j==4) board[i][j].setBackground(Color.BLACK);
						else board[i][j].setBackground(Color.WHITE);
					}
					else if (i==1 || i==7){
						if (j==3 || j==4 || j==5) board[i][j].setBackground(Color.BLACK);
						else board[i][j].setBackground(Color.WHITE);
					}
					else if (i==2 || i==6){
						if (j==0 || j==1 || j==7 || j==8) board[i][j].setBackground(Color.WHITE);
						else board[i][j].setBackground(Color.BLACK);
					}
					else if (i==3 || i==5){
						if (j==0 || j==8) board[i][j].setBackground(Color.WHITE);
						else board[i][j].setBackground(Color.BLACK);
					}
					else board[i][j].setBackground(Color.BLACK);
				}
			}
			board[4][4].setBackground(Color.GRAY);
		}

		//Adding cells to frame.
		addBoard();

		//Adding game buttons to frame.
		add(reset);
		add(load);	add(save);
		add(undo);
		add(pegNumber); add(emptyCells); add(pegsOut);
		add (auto);		add(autoAll);

		//Adding radio buttons to frame.
		add(type1);add(type2);add(type3);add(type4);add(type5);add(type6);
		addingListenersType();
	}

	/**
	 * Method to create sixth board from scratch.
	 * @param is_loaded True if the board is loaded from a file.
	 */
	public void Board6(boolean is_loaded){
		reset_screen();
		grid = new GridLayout(7, 9, 2, 2);
		setLayout(grid);
		boardType = 6;
		score = -1;
		moves.clear();

		//Fill the board if it was not loaded from a file.
		if (!is_loaded){
			board = new JButton[5][5];
			for (int i = 0; i < 5; ++i){
				for (int j = 0; j < 5; ++j){
					board[i][j] = new JButton();
					if ( (i==0 && (j==1||j==2||j==3||j==4)) ||
						 (i==1 && (j==2||j==3||j==4)) || 
						 (i==2 && (j==3||j==4)) ||
						 (i==3 && j==4)) 
						board[i][j].setBackground(Color.WHITE);
					else board[i][j].setBackground(Color.BLACK);
				}
			}
			board[0][0].setBackground(Color.GRAY);
		}

		//Adding cells to frame.
		addBoardTriangle();

		//Adding game buttons to frame.
		add(reset);
		add(load);	
		add(save);
		add(undo);
		add(pegNumber); 
		add(emptyCells); 
		add(pegsOut);
		add (auto);		
		add(autoAll);

		//Adding radio buttons to frame.
		add(type1);add(type2);add(type3);add(type4);add(type5);add(type6);
		addingListenersType();
	}

	/**
	 * Method to add cell buttons to the frame for first 5 types.
	 */
	public void addBoard(){
		for (int i = 0; i < board.length; ++i){
			for (int j = 0; j < board[0].length; ++j){
				if (board[i][j].getBackground() == Color.WHITE) add(new JLabel());
				else add(board[i][j]);
			}
		}
	}

	/**
	 * Method to add cell buttons to the frame for last type.
	 */
	public void addBoardTriangle(){
		add(new JLabel()); add(new JLabel()); add(new JLabel()); add(new JLabel()); 
		add(board[0][0]);
		add(new JLabel()); add(new JLabel()); add(new JLabel()); add(new JLabel()); 
		
		add(new JLabel()); add(new JLabel()); add(new JLabel()); 
		add(board[1][0]); add(new JLabel()); add(board[1][1]);
		add(new JLabel()); add(new JLabel()); add(new JLabel()); 
		
		add(new JLabel()); add(new JLabel()); 
		add(board[2][0]); add(new JLabel()); add(board[2][1]); add(new JLabel()); add(board[2][2]);
		add(new JLabel()); add(new JLabel()); 

		add(new JLabel()); add(board[3][0]); add(new JLabel()); add(board[3][1]);
		add(new JLabel()); add(board[3][2]); add(new JLabel()); add(board[3][3]); add(new JLabel());

		add(board[4][0]); add(new JLabel()); add(board[4][1]);
		add(new JLabel()); add(board[4][2]); add(new JLabel()); add(board[4][3]);
		add(new JLabel()); add(board[4][4]);
	}

	/**
	 * Method to add listeners for the cell buttons.
	 */
	public void addingListenersType(){
		for (int i = 0; i < board.length; ++i)
			for (int j = 0; j < board.length; ++j)
				if (board[i][j].getBackground() != Color.WHITE) board[i][j].addActionListener(new CellHandler(i, j));
	}

	/**
	 * Method to determine if move is valid for the up.
	 * @param yRow Row value of the selected cell.
	 * @param yCol Column value of the selected cell.
	 * @param row Row value of the slot that user wants to go.
	 * @param col Column value of the slot that user wants to go.
	 * @return boolean to specify if the move is valid.
	 */
	public boolean is_valid_up(int yRow, int yCol, int row, int col){
		if (yRow-2 < 0) return false;
		return ((yRow-2)==row && board[yRow-1][yCol].getBackground() == Color.BLACK && yCol == col) ? true : false;
	}

	/**
	 * Method to determine if move is valid for the down.
	 * @param yRow Row value of the selected cell.
	 * @param yCol Column value of the selected cell.
	 * @param row Row value of the slot that user wants to go.
	 * @param col Column value of the slot that user wants to go.
	 * @return boolean to specify if the move is valid.
	 */
	public boolean is_valid_down(int yRow, int yCol, int row, int col){
		if (yRow+2 >= board.length) return false;
		return ((yRow+2)==row && board[yRow+1][yCol].getBackground() == Color.BLACK && yCol == col) ? true : false;
	}

	/**
	 * Method to determine if move is valid for the right.
	 * @param yRow Row value of the selected cell.
	 * @param yCol Column value of the selected cell.
	 * @param row Row value of the slot that user wants to go.
	 * @param col Column value of the slot that user wants to go.
	 * @return boolean to specify if the move is valid.
	 */
	public boolean is_valid_right(int yRow, int yCol, int row, int col){
		if(yCol+2 >= board[0].length) return false;
		return (yRow==row && board[yRow][yCol+1].getBackground() == Color.BLACK && (yCol+2) == col) ? true : false;
	}

	/**
	 * Method to determine if move is valid for the left.
	 * @param yRow Row value of the selected cell.
	 * @param yCol Column value of the selected cell.
	 * @param row Row value of the slot that user wants to go.
	 * @param col Column value of the slot that user wants to go.
	 * @return boolean to specify if the move is valid.
	 */
	public boolean is_valid_left(int yRow, int yCol, int row, int col){
		if (yCol-2 < 0) return false;
		return (yRow==row && board[yRow][yCol-1].getBackground() == Color.BLACK && (yCol-2) == col) ? true : false;
	}

	/**
	 * Method to determine if the game is finished.
	 * @return boolean to specify if the game is finished.
	 */
	public boolean is_finished(){
		boolean flag = true;
		
		//Checking if the game ended
		for (int i = 2; i < board.length; ++i){
			for (int j = 0; j < board.length; ++j){
				if (board[i][j].getBackground() == Color.BLACK){
					if (board[i-1][j].getBackground()==Color.BLACK && board[i-2][j].getBackground() == Color.GRAY) flag = false;
				}
			}
		}
		for (int i = 0; i < board.length-2; ++i){
			for (int j = 0; j < board.length; ++j){
				if (board[i][j].getBackground()==Color.BLACK){
					if (board[i+1][j].getBackground()==Color.BLACK && board[i+2][j].getBackground() == Color.GRAY) flag = false;
				}
			}
		}
		for (int i = 0; i < board.length; ++i){
			for (int j = 2; j < board.length; ++j){
				if (board[i][j].getBackground()==Color.BLACK){
					if (board[i][j-1].getBackground()==Color.BLACK && board[i][j-2].getBackground() == Color.GRAY) flag = false;
				}
			}
		}
		for (int i = 0; i < board.length; ++i){
			for (int j = 0; j < board.length-2; ++j){
				if (board[i][j].getBackground()==Color.BLACK){
					if (board[i][j+1].getBackground()==Color.BLACK && board[i][j+2].getBackground() == Color.GRAY) flag = false;
				}
			}
		}
		if (boardType == 6){
			//Upleft for triangle board
			for (int i = 2; i < board.length; ++i){
				for (int j = 2; j < board.length; ++j){
					if (board[i][j].getBackground()==Color.BLACK){
						if (board[i-1][j-1].getBackground()==Color.BLACK && board[i-2][j-2].getBackground()==Color.GRAY) flag = false;
					}
				}
			}

			//Downright for triangle board
			for (int i = 0; i < board.length-2; ++i){
				for (int j = 0; j < board.length-2; ++j){
					if (board[i][j].getBackground()==Color.BLACK){
						if (board[i+1][j+1].getBackground()==Color.BLACK && board[i+2][j+2].getBackground()==Color.GRAY) flag = false;
					}
				}
			}
		}

		//If game is finished
		if (flag){
			//Calculating the score and prints the message.
			score=0;
			for (int i = 0; i < board.length; ++i)
				for (int j = 0; j < board[0].length; ++j)
					if (board[i][j].getBackground() == Color.BLACK || board[i][j].getBackground() == Color.YELLOW) score++;
			JOptionPane.showMessageDialog(null, "GAME OVER! THERE ARE " + score + " PEGS LEFT!",
												"CONGRATS!", JOptionPane.INFORMATION_MESSAGE);
			score = 0;
		}
		return flag;
	}

	/**
	 * Method to refresh the current screen.
	 */
	public void reset_screen(){
		getContentPane().removeAll();
		getContentPane().repaint();
		if (size > 861) size = 860;
		setSize(1200, size++);
	}

	/**
	 * Method to make the computer play 1 move.
	 * @return boolean to specify if the game is finished.
	 */
	public boolean autoOne(){
		if (is_finished()) return true;
		
		//Reset the toggle if the user touched any cell before.
		toggle = false;
		for (int i = 0; i < board.length; ++i)
			for (int j = 0; j < board.length; ++j)
				if (board[i][j].getBackground() == Color.YELLOW) board[i][j].setBackground(Color.BLACK);
		
		Random random = new Random();
		boolean flag = false;
		int row, col, direction;
		while (!flag){
			//Selecting random numbers for the move.
			row = random.nextInt(board.length);
			col = random.nextInt(board[0].length);
			if (boardType != 6) direction = random.nextInt(4);
			else direction = random.nextInt(6);

			//Trying to move until selected row, column, and direction values are valid.
			if (board[row][col].getBackground() != Color.BLACK) continue;
			if (direction == 0){
				//UPWARDS
				if (is_valid_up(row, col, row-2, col)){
					if (board[row-2][col].getBackground() != Color.GRAY) continue;
					board[row][col].setBackground(Color.GRAY);
					board[row-1][col].setBackground(Color.GRAY);
					board[row-2][col].setBackground(Color.BLACK);
					flag = true;
					moves.add(row*100+col*10+0);
				}
			}
			else if (direction == 1){
				//DOWNWARDS
				if (is_valid_down(row, col, row+2, col)){
					if (board[row+2][col].getBackground() != Color.GRAY) continue;
					board[row][col].setBackground(Color.GRAY);
					board[row+1][col].setBackground(Color.GRAY);
					board[row+2][col].setBackground(Color.BLACK);
					flag = true;
					moves.add(row*100+col*10+1);
				}
			}
			else if (direction == 2){
				//RIGHT
				if (is_valid_right(row, col, row, col+2)){
					if (board[row][col+2].getBackground() != Color.GRAY) continue;
					board[row][col].setBackground(Color.GRAY);
					board[row][col+1].setBackground(Color.GRAY);
					board[row][col+2].setBackground(Color.BLACK);
					flag = true;
					moves.add(row*100+col*10+2);
				}
			}
			else if (direction == 3){
				//LEFT
				if (is_valid_left(row, col, row, col-2)){
					if (board[row][col-2].getBackground() != Color.GRAY) continue;
					board[row][col].setBackground(Color.GRAY);
					board[row][col-1].setBackground(Color.GRAY);
					board[row][col-2].setBackground(Color.BLACK);
					flag = true;
					moves.add(row*100+col*10+3);
				}
			}
			else if (direction == 4){
				//UPLEFT FOR TRIANGLE BOARD
				if (col-2 < 0) continue;
				if (is_valid_up(row, col-1, row-2, col-1)){
					if (board[row-2][col-2].getBackground() != Color.GRAY) continue;
					board[row][col].setBackground(Color.GRAY);
					board[row-1][col-1].setBackground(Color.GRAY);
					board[row-2][col-2].setBackground(Color.BLACK);
					flag = true;
					moves.add(row*100+col*10+4);
				}
			}
			else if (direction == 5){
				//DOWNRIGHT FOR TRIANGLE BOARD
				if (is_valid_down(row, col+1, row+2, col+1)){
					if (board[row+2][col+2].getBackground() != Color.GRAY) continue;
					board[row][col].setBackground(Color.GRAY);
					board[row+1][col+1].setBackground(Color.GRAY);
					board[row+2][col+2].setBackground(Color.BLACK);
					flag = true;
					moves.add(row*100+col*10+5);
				}
			}
		}
		return (is_finished());
	}

	/**
	 * Inner class ButtonHandler that implements ActionListener
	 */
	private class ButtonHandler implements ActionListener{
		/** 
		 * actionPerformed method, invoked when an action occurs.
		 * @param event Indicates an action.
		 */
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == reset){
				//RESET THE BOARD BY CALLING PROPER BOARD FUNCTION 
				toggle = false;
				if (boardType == 1) Board1(false);
				else if (boardType == 2) Board2(false);
				else if (boardType == 3) Board3(false);
				else if (boardType == 4) Board4(false);
				else if (boardType == 5) Board5(false);
				else if (boardType == 6) Board6(false);
			}
			else if (event.getSource() == save){
				String input = JOptionPane.showInputDialog("Enter file name");
				if (input == null || input.isEmpty()) return;
				try{
					//SAVE THE GAME BY COLOR VALUES OF THE BUTTONS
					//	TO THE GIVEN FILE
					toggle = false;
					for (int i = 0; i < board.length; ++i)
						for (int j = 0; j < board.length; ++j)
							if (board[i][j].getBackground() == Color.YELLOW) board[i][j].setBackground(Color.BLACK);
					FileWriter save = new FileWriter(input + ".txt");

					JOptionPane.showMessageDialog(null, "Game is successfully saved to " + input,
												"Save", JOptionPane.PLAIN_MESSAGE);
					save.write(String.valueOf(boardType));

					for (int i = 0; i < board.length; ++i)
						for (int j = 0; j < board[0].length; ++j){
							if (board[i][j].getBackground() == Color.BLACK) save.write("\nBLACK");
							else if (board[i][j].getBackground() == Color.WHITE) save.write("\nWHITE");
							else save.write("\nGRAY");
						}
					save.close();
				}
				catch(IOException e){
					JOptionPane.showMessageDialog(null, "Error occurred during opening the file",
												"ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if (event.getSource() == load){
				try{
					//LOAD THE GAME FROM GIVEN FILE 
					String input = JOptionPane.showInputDialog("Enter file name");
					if (input == null) return;
					File load = new File(input + ".txt");
					Scanner read = new Scanner(load);
					boardType = Integer.parseInt(read.nextLine());

					if (boardType == 1 || boardType == 4) board = new JButton[7][7];
					else if (boardType == 2 || boardType == 5) board = new JButton[9][9];
					else if (boardType == 3) board = new JButton[8][8];
					else if (boardType == 6) board = new JButton[5][5];

					//FILL THE BOARD
					for(int i = 0; i < board.length; ++i)
						for(int j = 0; j < board[0].length; ++j){
							board[i][j] = new JButton();
							String line = read.nextLine();
							if (line.equals("WHITE")) board[i][j].setBackground(Color.WHITE);
							else if (line.equals("BLACK") || line.equals("YELLOW")) board[i][j].setBackground(Color.BLACK);
							else if (line.equals("GRAY")) board[i][j].setBackground(Color.GRAY);
						}

					read.close();
					toggle = false;

					//BEGIN THE GAME
					if (boardType == 1) Board1(true);
					else if (boardType == 2) Board2(true);
					else if (boardType == 3) Board3(true);
					else if (boardType == 4) Board4(true);
					else if (boardType == 5) Board5(true);
					else if (boardType == 6) Board6(true);

				}
				catch (FileNotFoundException e){
					JOptionPane.showMessageDialog(null, "THIS FILE DOES NOT EXIST!",
												"ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if (event.getSource() == undo){
				if (moves.size()!=0){
					toggle = false;
					for (int i = 0; i < board.length; ++i)
						for (int j = 0; j < board.length; ++j)
							if (board[i][j].getBackground() == Color.YELLOW) board[i][j].setBackground(Color.BLACK);

					//moves is ArrayList that stores moves by 3-digit number.
					//1st digit is for row, 2nd digit is for column, last digit is for direction
					int d = (moves.get(moves.size()-1)) % 10;
					int r = (moves.get(moves.size()-1) / 100) % 10;
					int c = (moves.get(moves.size()-1) / 10) % 10;

					if (d==0){
						//UNDO IF LAST MOVE IS UP
						board[r-2][c].setBackground(Color.GRAY);
						board[r-1][c].setBackground(Color.BLACK);
						board[r][c].setBackground(Color.BLACK);
					}
					else if (d==1){
						//UNDO IF LAST MOVE IS DOWN
						board[r+2][c].setBackground(Color.GRAY);
						board[r+1][c].setBackground(Color.BLACK);
						board[r][c].setBackground(Color.BLACK);
					}
					else if (d==2){
						//UNDO IF LAST MOVE IS RIGHT
						board[r][c+2].setBackground(Color.GRAY);
						board[r][c+1].setBackground(Color.BLACK);
						board[r][c].setBackground(Color.BLACK);
					}
					else if (d==3){
						//UNDO IF LAST MOVE IS LEFT
						board[r][c-2].setBackground(Color.GRAY);
						board[r][c-1].setBackground(Color.BLACK);
						board[r][c].setBackground(Color.BLACK);
					}
					else if (d==4){
						//UNDO IF LAST MOVE IS UP-LEFT (FOR TRIANGLE BOARD)
						board[r-2][c-2].setBackground(Color.GRAY);
						board[r-1][c-1].setBackground(Color.BLACK);
						board[r][c].setBackground(Color.BLACK);
					}
					else if (d==5){
						//UNDO IF LAST MOVE IS DOWN-RIGHT (FOR TRIANGLE BOARD)
						board[r+2][c+2].setBackground(Color.GRAY);
						board[r+1][c+1].setBackground(Color.BLACK);
						board[r][c].setBackground(Color.BLACK);
					}
					
					moves.remove(moves.size()-1);
				}
			}
			else if (event.getSource() == pegNumber){
				//CALCULATING THE NUMBER OF PEGS AT THE BOARD
				int num = 0;
				for (int i = 0; i < board.length; ++i)
					for (int j = 0; j< board[0].length; ++j)
						if (board[i][j].getBackground() == Color.BLACK || board[i][j].getBackground() == Color.YELLOW) num++;
				JOptionPane.showMessageDialog(null, "THERE ARE " + num + " PEGS AT THE BOARD!",
												"NUMBER OF PEGS", JOptionPane.INFORMATION_MESSAGE);
			}
			else if (event.getSource() == pegsOut){
				//CALCULATING THE NUMBER OF PEGS TAKEN OUT FROM THE BOARD
				int num = -1;
				for (int i = 0; i < board.length; ++i)
					for (int j = 0; j< board[0].length; ++j)
						if (board[i][j].getBackground() == Color.GRAY) num++;
				JOptionPane.showMessageDialog(null, num + " PEGS TAKEN OUT FROM BOARD!",
											"NUMBER OF PEGS OUT", JOptionPane.INFORMATION_MESSAGE);

			}
			else if (event.getSource() == emptyCells){
				//CALCULATING THE NUMBER OF EMPTY CELLS AT THE BOARD
				int num = 0;
				for (int i = 0; i < board.length; ++i)
					for (int j = 0; j< board[0].length; ++j)
						if (board[i][j].getBackground() == Color.GRAY) num++;
				JOptionPane.showMessageDialog(null, "THERE ARE " +num+ " EMPTY CELLS AT THE BOARD!",
											"EMPTY CELLS", JOptionPane.INFORMATION_MESSAGE);

			}
			else if (event.getSource() == auto){
				//PLAY 1 MOVE AUTOMATICALLY
				autoOne();
			}
			else if (event.getSource() == autoAll){
				//PLAY AUTOMATICALLY UNTIL GAME ENDS
				while(!autoOne()){/* Intentionally Empty*/}
			}
		}
	}

	/** Inner class CellHandler that implements ActionListener */
	private class CellHandler implements ActionListener{
		int row;
		int col;
		boolean flag;
		
		/** 
		 * Constructor of inner class with row and column values. 
		 * Assign row, col to given parameters and flag to false.
		 */
		public CellHandler(int rowVal, int colVal){
			row = rowVal; col = colVal; flag = false;
		}
		
		/** 
		 * actionPerformed method, invoked when an action occurs.
		 * @param event Indicates an action.
		 */
		public void actionPerformed(ActionEvent event){
			//IF USER FIRST CLICK BLACK CELL
			//toggle becomes true, selected cell becomse yellow
			if (board[row][col].getBackground() == Color.BLACK && !toggle){
				toggle = true;
				board[row][col].setBackground(Color.YELLOW);
			}

			//IF USER CLICK ANY OTHER CELL AFTER CLICKING ONE BLACK CELL
			else if (toggle){
				//Finding user's first selected row
				int tempRow=0, tempCol=0;
				for (int i = 0; i < board.length; ++i)
					for (int j = 0; j < board.length; ++j)
						if (board[i][j].getBackground() == Color.YELLOW){tempRow = i; tempCol = j; break;}
				int yRow = tempRow, yCol = tempCol;

				//If user clicks another black cell, make it yellow, make former one black again
				if (board[row][col].getBackground() == Color.BLACK){ 
					board[row][col].setBackground(Color.YELLOW);
					board[yRow][yCol].setBackground(Color.BLACK);
				}
				
				//If user clicks yellow cell again, make it black and make toggle false.
				else if (board[row][col].getBackground() == Color.YELLOW){ board[row][col].setBackground(Color.BLACK); toggle = false; }
				
				//If user clicks gray cell (empty slot), check if move is valid
				else if (board[row][col].getBackground() == Color.GRAY) {
					//CHECK UP
					if (is_valid_up(yRow, yCol, row, col)){	//UP-RIGHT FOR BOARD 6
						board[yRow][yCol].setBackground(Color.GRAY);
						board[row+1][col].setBackground(Color.GRAY);
						board[row][col].setBackground(Color.BLACK);
						toggle = false;
						flag = true;
						moves.add(yRow*100+yCol*10+0);
					}
					//CHECK DOWN
					else if(is_valid_down(yRow, yCol, row, col)){ //DOWN-LEFT FOR BOARD 6
						board[yRow][yCol].setBackground(Color.GRAY);
						board[row-1][col].setBackground(Color.GRAY);
						board[row][col].setBackground(Color.BLACK);
						toggle = false;
						flag = true;
						moves.add(yRow*100+yCol*10+1);
					}
					//CHECK RIGHT
					else if (is_valid_right(yRow, yCol, row, col)){
						board[yRow][yCol].setBackground(Color.GRAY);
						board[yRow][yCol+1].setBackground(Color.GRAY);
						board[row][col].setBackground(Color.BLACK);
						toggle = false;
						flag = true;
						moves.add(yRow*100+yCol*10+2);
					}
					//CHECK LEFT
					else if (is_valid_left(yRow, yCol, row, col)){
						board[yRow][yCol].setBackground(Color.GRAY);
						board[yRow][yCol-1].setBackground(Color.GRAY);
						board[row][col].setBackground(Color.BLACK);
						toggle = false;
						flag = true;
						moves.add(yRow*100+yCol*10+3);
					}

					if(boardType == 6 && !flag){
						//CHECK UP-LEFT FOR BOARD 6
						if (is_valid_up(yRow, yCol-1, row, col+1)){
							board[yRow][yCol].setBackground(Color.GRAY);
							board[row+1][col+1].setBackground(Color.GRAY);
							board[row][col].setBackground(Color.BLACK);
							toggle = false;
							flag = true;
							moves.add(yRow*100+yCol*10+4);
						}
						//CHECK DOWN-RIGHT FOR BOARD 6
						else if (is_valid_down(yRow, yCol+1, row, col-1)){
							board[yRow][yCol].setBackground(Color.GRAY);
							board[row-1][col-1].setBackground(Color.GRAY);
							board[row][col].setBackground(Color.BLACK);
							toggle = false;
							flag = true;
							moves.add(yRow*100+yCol*10+5);
						}
					}

					if (flag) is_finished();
					flag = false;
				}
			}
		}
	}

	/** Inner class RadioButtonHandler that implements ItemListener. */
	private class RadioButtonHandler implements ItemListener{
		public void itemStateChanged( ItemEvent event ){
			//Restart the game
			toggle = false;
			if (event.getSource() == type1) Board1(false);
			else if (event.getSource() == type2) Board2(false);
			else if (event.getSource() == type3) Board3(false);
			else if (event.getSource() == type4) Board4(false);
			else if (event.getSource() == type5) Board5(false); 
			else if (event.getSource() == type6) Board6(false);
		}
	}
}