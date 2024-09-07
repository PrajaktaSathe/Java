import java.awt.*;

public class Format {
    GUI gui;
    String SELECTEDString;


    Font arial ,ComicSansMS,timeNewRoman;
    Format(GUI gui){
        this.gui=gui;
    }

    public void  wrapword(){

        if (gui.wordwrapOn==false){
            gui.wordwrapOn=true;
            gui.textArea.setLineWrap(true);
            gui.textArea.setWrapStyleWord(true);
            gui.iWrap.setText("Wrap Word:On");

        }
        else if (gui.wordwrapOn==true){
            gui.wordwrapOn=false;
            gui.textArea.setLineWrap(false);
            gui.textArea.setWrapStyleWord(false);
            gui.iWrap.setText("Wrap Word:Off");
        }

    }

    public void createFont(int fontSize){

        arial=new Font("Arial",Font.PLAIN,fontSize);
        ComicSansMS=new Font("ComicSansMS",Font.PLAIN,fontSize);
        timeNewRoman=new Font("TimeNewRoman",Font.PLAIN,fontSize);
        selectfont(SELECTEDString);

    }
    public void selectfont(String font){
        SELECTEDString=font;
        switch(SELECTEDString){
            case "Time Of New Roman":gui.textArea.setFont(timeNewRoman); break;
            case  "Comic Sans MS":gui.textArea.setFont(ComicSansMS); break;
            case "Arial":gui.textArea.setFont(arial);
        }
    }

}
