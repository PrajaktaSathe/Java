import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GUI gui;

    KeyHandler(GUI gui){

        this.gui=gui;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.isControlDown() && e.getKeyCode()==KeyEvent.VK_S){
            gui.file.save();
        }
        if (e.isShiftDown() && e.getKeyCode()==KeyEvent.VK_S){
            gui.file.SaveAs();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
