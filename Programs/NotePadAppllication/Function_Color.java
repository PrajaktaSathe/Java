import java.awt.*;

public class Function_Color {
    GUI gui;

    Function_Color(GUI gui){
        this.gui=gui;
    }

    public void changeColor(String color){
        switch (color){
            case "White":
                gui.window.getContentPane().setBackground(Color.WHITE);
                gui.textArea.setBackground(Color.WHITE);
                gui.textArea.setForeground(Color.BLACK);
                break;
            case "Black":
                gui.window.setBackground(Color.BLACK);
                gui.textArea.setBackground(Color.BLACK);
                gui.textArea.setForeground(Color.white);
                break;
            case "Red":
                gui.window.setBackground(Color.red);
                gui.textArea.setBackground(Color.red);
                gui.textArea.setForeground(Color.WHITE);
                break;
            
        }

    }
}
