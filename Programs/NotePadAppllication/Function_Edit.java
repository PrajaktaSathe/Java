public class Function_Edit {
    GUI gui;
    Function_Edit(GUI gui){
       this.gui=gui;
    }

    public void undo(){
        gui.um.undo();
    }
    public  void redo(){
        gui.um.redo();
    }
}
