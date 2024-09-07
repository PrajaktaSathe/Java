import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
  JFrame window;
  // TEXT AREA
  JTextArea textArea;
  JScrollPane scrollPane;
  boolean wordwrapOn;
  // MENU BAR
  JMenuBar menuBar;
  JMenu menuFile,menuEdit,menuFormat,menuColour;
  //FILE
  JMenuItem iNew,iOpen,iSave,iSaveAs,iExit;

  // FORMAT
    JMenuItem iWrap ;
    JMenu iFont ,iFontsize;

    JMenuItem iFontTR,iFontARIAL,iFontuh;
    // FontSize()
    JMenuItem iFont8,iFont12,iFont16,iFont20,iFont24;

    /// COLOR
    JMenuItem iWhite,iBlack,iRed;
    /// Edit
    JMenuItem iUndo,iRedo;
    UndoManager um= new UndoManager();

   Function_File file = new Function_File(this);
   Format format = new Format(this);

   Function_Color functionColor=new Function_Color(this);

   Function_Edit functionEdit= new Function_Edit(this);

   KeyHandler keyHandler = new KeyHandler(this);



    public GUI(){
       createwindow();
       createTextArea();
       createMenu();

       createFileMenu();
       createFormat();
       createColorMenu();

       format.selectfont("Arial");
       format.createFont(16);
       functionColor.changeColor("White");

       format.wrapword();

       createEditMenu();



        window.setVisible(true);
    }
    public static void main(String[] args) {
        new GUI();
    }
    public void createwindow(){
        window=new JFrame("Notepad");
        window.setSize(800,600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void createTextArea(){
        textArea=new JTextArea();
        textArea.addKeyListener(keyHandler);
        textArea.getDocument().addUndoableEditListener(
                new UndoableEditListener() {
                    @Override
                    public void undoableEditHappened(UndoableEditEvent e) {
                        um.addEdit(e.getEdit());
                    }
                }
        );
        scrollPane=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        window.add(scrollPane);

    }
    public void createMenu(){
        menuBar= new JMenuBar();
        window.setJMenuBar(menuBar);
        // file
        menuFile=new JMenu("File");
        menuBar.add(menuFile);
        // Edit
        menuEdit=new JMenu("Edit");
        menuBar.add(menuEdit);
        //Format
        menuFormat=new JMenu("Format");
        menuBar.add(menuFormat);
        //Color
        menuColour=new JMenu("Color");
        menuBar.add(menuColour);
    }
    public void createFileMenu(){
        iNew=new JMenuItem("New");
        iNew.addActionListener(this);
        iNew.setActionCommand("New");
        menuFile.add(iNew);
        iOpen=new JMenuItem("Open");
        iOpen.addActionListener(this);
        iOpen.setActionCommand("Open");
        menuFile.add(iOpen);
        iSave=new JMenuItem("Save");
        iSave.addActionListener(this);
        iSave.setActionCommand("Save");
        menuFile.add(iSave);
        iSaveAs=new JMenuItem("SaveAs");
        iSaveAs.addActionListener(this);
        iSaveAs.setActionCommand("SaveAs");
        menuFile.add(iSaveAs);
        iExit=new JMenuItem("Exit");
        iExit.addActionListener(this);
        iExit.setActionCommand("Exit");
        menuFile.add(iExit);
    }

    public void createFormat(){
        iWrap=new JMenuItem("Word Wrap:Off");
        iWrap.addActionListener(this);
        iWrap.setActionCommand("Word Wrap");
        menuFormat.add(iWrap);
        // font
        iFont= new JMenu("Font");
        menuFormat.add(iFont);
        //font size
        iFontsize=new JMenu("Font Size");
        menuFormat.add(iFontsize);

        // font item
        iFontuh=new JMenuItem("Comic Sans MS");
        iFontuh.addActionListener(this);
        iFontuh.setActionCommand("Comic Sans MS");
        iFont.add(iFontuh);
        iFontARIAL=new JMenuItem("Arial");
        iFontARIAL.addActionListener(this);
        iFontARIAL.setActionCommand("Arial");
        iFont.add(iFontARIAL);
        iFontTR=new JMenuItem("Time Of New Roman");
        iFontTR.addActionListener(this);
        iFontTR.setActionCommand("Time Of New Roman");
        iFont.add(iFontTR);


        //fontSIZE
        iFont8=new JMenuItem("8");
        iFont8.addActionListener(this);
        iFont8.setActionCommand("size8");
        iFontsize.add(iFont8);

        iFont12=new JMenuItem("12");
        iFont12.addActionListener(this);
        iFont12.setActionCommand("size12");
        iFontsize.add(iFont12);

        iFont16=new JMenuItem("16");
        iFont16.addActionListener(this);
        iFont16.setActionCommand("size16");
        iFontsize.add(iFont16);

        iFont20=new JMenuItem("20");
        iFont20.addActionListener(this);
        iFont20.setActionCommand("size20");
        iFontsize.add(iFont20);

        iFont24=new JMenuItem("24");
        iFont24.addActionListener(this);
        iFont24.setActionCommand("size24");
        iFontsize.add(iFont24);
    }

    public void createColorMenu(){
        iWhite=new JMenuItem("White");
        iWhite.addActionListener(this);
        iWhite.setActionCommand("White");
        menuColour.add(iWhite);
        iBlack=new JMenuItem("Black");
        iBlack.addActionListener(this);
        iBlack.setActionCommand("Black");
        menuColour.add(iBlack);
        iRed=new JMenuItem("Red");
        iRed.addActionListener(this);
        iRed.setActionCommand("Red");
        menuColour.add(iRed);
    }

    public void createEditMenu(){
        iUndo=new JMenuItem("Undo");
        iUndo.addActionListener(this);
        iUndo.setActionCommand("Undo");
        menuEdit.add(iUndo);
        iRedo=new JMenuItem("Redo");
        iRedo.addActionListener(this);
        iRedo.setActionCommand("Redo");
        menuEdit.add(iRedo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command=e.getActionCommand();
        switch (command){
            case "New":file.newFile();
                         break;
            case "Open":file.open();
                         break;
            case "SaveAs":file.SaveAs();
                          break;
            case  "Save":file.save();
                           break;
            case   "Exit":file.exit();
                           break;
            case    "Word Wrap":format.wrapword();
                            break;
            case     "size12":format.createFont(12);
                         break;
            case     "size16":format.createFont(16);
                           break;
            case     "size20":format.createFont(20);
                           break;
            case  "size24":format.createFont(24);
                           break;
            case  "Arial":format.selectfont("Arial");
                           break;
            case  "Comic Sans MS":format.selectfont("Comic Sans MS");
                break;


            case  "Time Of New Roman":format.selectfont("Time Of New Roman");
                break;

            case  "White":functionColor.changeColor("White");     break;
            case  "Red":functionColor.changeColor("Red");     break;

            case  "Black":functionColor.changeColor("Black");    break;
            case "Undo": functionEdit.undo();break;
            case "Redo":functionEdit.redo();break;

        }
    }
}
