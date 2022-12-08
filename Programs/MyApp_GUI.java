//creating a simple avg calculator using GUI using JFRAME

import java.awt.FlowLayout;
import javax.swing.*;
import java.awt.event.*;

public class MyApp_GUI extends JFrame
{
        private JLabel name_surname;
        private JTextField name_text;
        private JLabel regno;
        private JTextField regno_text;
        private JLabel grade1;
        private JTextField grade1_text;
        private JLabel grade2;
        private JTextField grade2_text;
        private JLabel grade3;
        private JTextField grade3_text;
        private JLabel grade4;
        private JTextField grade4_text;
        private JLabel grade5;
        private JTextField grade5_text;
        private JButton calculate_avg;
        private JButton clear;
        private JTextField show_avg;

        private void clear(){       //FUCNTION TO CLEAR TEXTFIELDS
            name_text.setText("");
            regno_text.setText("");
            grade1_text.setText("");
            grade2_text.setText("");
            grade3_text.setText("");
            grade4_text.setText("");
            grade5_text.setText("");
        }

        private void calculate(){
            try {       
                int grade1 = Integer.parseInt(grade1_text.getText());   //  CONVERT THE STRING TO INTEGER
                int grade2 = Integer.parseInt(grade2_text.getText());
                int grade3 = Integer.parseInt(grade3_text.getText());
                int grade4 = Integer.parseInt(grade4_text.getText());
                int grade5 = Integer.parseInt(grade5_text.getText());
                int sum = (grade1 + grade2 + grade3 + grade4 + grade5)/5;
                show_avg.setText(Integer.toString(sum));
            } catch (Exception e) {     // IF NOT CONVERTABLE CATCH THE ERROR SO THE PROGRAM DOESNT CRASH
                
                JFrame popFrame  = new JFrame();        //SETTING A POPUP BOX TO SHOW THE ERROR
                JLabel error = new JLabel();
                popFrame.setVisible(true);
                popFrame.setLocation(500,400);
                popFrame.setSize(270,100);
                popFrame.setResizable(false);
                error.setText("You have to enter numbers in the grades!!");
                popFrame.add(error);        
            }
        }

    public MyApp_GUI()
    {     
        super("Grade Calculator");      //ADDING THE BUTTONS LABELS AND TEXTFIELDS TO THE APPLICATION
        name_surname = new JLabel("Enter name & surname: ");
        name_text = new JTextField("",20);
        add(name_surname);
        add(name_text);

        regno = new JLabel("Enter you registration number: ");
        regno_text = new JTextField("",10);
        add(regno);
        add(regno_text);

        grade1 = new JLabel("Grade for your first class: ");
        grade1_text = new JTextField("",15);
        add(grade1);
        add(grade1_text);

        grade2 = new JLabel("Grade for your second class: ");
        grade2_text = new JTextField("",13);
        add(grade2);
        add(grade2_text);

        grade3 = new JLabel("Grade for your third class: ");
        grade3_text = new JTextField("",15);
        add(grade3);
        add(grade3_text);

        grade4 = new JLabel("Grade for your fourth class: ");
        grade4_text = new JTextField("",14);
        add(grade4);
        add(grade4_text);

        grade5 = new JLabel("Grade for your fifth class: ");
        grade5_text = new JTextField("",15);
        add(grade5);
        add(grade5_text);
        
        calculate_avg = new JButton("Calculate avg: ");
        add(calculate_avg);
        show_avg = new JTextField("",3);
        add(show_avg);
        calculate_avg.addActionListener(new ActionListener() //FUNCTION WHEN THE CALCULATE_AVG BUTTON IS PRESSED 
        {  
            public void actionPerformed(ActionEvent e)
            {
                calculate();
            }
        });
        clear = new JButton("Clear all");
        add(clear);
        clear.addActionListener(new ActionListener() //FUNCTION WHEN THE CLEAR BUTTON IS PRESSED 
        {  
            public void actionPerformed(ActionEvent e)
            {
                clear();
            }
        });
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        setVisible(true);
        setSize(430,300);
        setLocation(450,300);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] args) 
    {
         new MyApp_GUI();   //SETTING UP THE MAIN APP WINDOW
        
    }
}