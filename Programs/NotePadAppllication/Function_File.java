import java.awt.*;
import java.io.*;

public class Function_File {
    GUI gui;
    String fileName;
    String fileAddress;
    Function_File( GUI gui){
        this.gui=gui;

    }
    public void newFile(){
        gui.textArea.setText("");
        gui.window.setTitle("New");
        fileName=null;
        fileAddress=null;
    }
    public void open(){
        FileDialog fileDialog=new FileDialog(gui.window,"Open",FileDialog.LOAD);
        fileDialog.setVisible(true);

        if (fileDialog.getFile()!=null){
            fileName= fileDialog.getFile();
            fileAddress=fileDialog.getDirectory();
            gui.window.setTitle(fileName);

            try{
                BufferedReader br= new BufferedReader(new FileReader(fileAddress+fileName));
                gui.textArea.setText("");
                String line=null;
                while((line= br.readLine())!=null){
                    gui.textArea.append(line+"\n");
                }
                br.close();

            }catch (Exception e){
                System.out.println("File not found");
            }

        }
    }
    public void save(){
        if (fileName==null){
            SaveAs();
        }
        else {
            try{
                FileWriter fileWriter= new FileWriter(fileAddress+fileName);
                fileWriter.write(gui.textArea.getText());
                fileWriter.close();
            }catch (Exception  e){
                System.out.println("Something Went Wrong");
            }
        }

    }
    public void SaveAs(){
        FileDialog fileDialog = new FileDialog(gui.window,"SAVE",FileDialog.SAVE);
        fileDialog.setVisible(true);
        if(fileDialog.getFile()!=null){
            fileName=fileDialog.getFile();
            fileAddress=fileDialog.getDirectory();
            gui.window.setTitle(fileName);
        }
        try{
            FileWriter fileWriter= new FileWriter(fileAddress+fileName);
            fileWriter.write(gui.textArea.getText());
            fileWriter.close();
        }catch (Exception  e){
            System.out.println("Something Went Wrong");
        }
    }
    public void exit(){
        System.exit(0);
    }

    public static void main(String[] args) {

    }
}
