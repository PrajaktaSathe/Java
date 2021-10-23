//GUI.java 

import javax.util.Arrays;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
public class GUI extends JFrame implements ActionListener{ 
    JFrame jf;
    JLabel jl1,jl2,jl3,jl4,jl5,jl6,jl7;
    JTextField jtf1,jtf2,jtf3,jtf4;
    JPasswordField jpf1,jpf2;
    JButton jb1,jb2;
    JComboBox jcb;
    Container c;
    GUI(){
        jf=new JFrame("Registration Form"); 
        c=jf.getContentPane();
        jf.setLocation(100,100);
        jf.setSize(350,350);
        c.setBackground(Color.PINK);
        jf.setLayout(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jl1=new JLabel("NAME"); 
        jl2=new JLabel("GENDER");
        jl3=new JLabel("FATHER NAME"); 
        jl4=new JLabel("PASSWORD");
        jl5=new JLabel("CONFIRM PASSWORD"); 
        jl6=new JLabel("CITY");
        jl7=new JLabel("EMAIL");
        jl1.setBounds(10,50,200,20);
        jl2.setBounds(10,70,200,20);
        jl3.setBounds(10,90,200,20);
        jl4.setBounds(10,110,200,20);
        jl5.setBounds(10,130,200,20); 
        jl6.setBounds(10,150,200,20);
        jl7.setBounds(10,170,200,20);
        c.add(jl1);
        c.add(jl2);
        c.add(jl3);
        c.add(jl4);
        c.add(jl5);
        c.add(jl6);
        c.add(jl7);
        jtf1=new JTextField();
        jtf1.setBounds(150,50,150,20);
        c.add(jtf1);
        String[] str={"SELECT","Male","Female","Other"};
        jcb=new JComboBox(str);
        jcb.setBounds(150,70,150,20);
        c.add(jcb);
        jtf2=new JTextField();
        jtf2.setBounds(150,90,150,20);
        c.add(jtf2);
        jpf1=new JPasswordField();
        jpf1.setBounds(150,110,150,20);
        c.add(jpf1);
        jpf2=new JPasswordField();
        jpf2.setBounds(150,130,150,20);
        c.add(jpf2);
        jtf3=new JTextField();
        jtf3.setBounds(150,150,150,20);
        c.add(jtf3);
        jtf4=new JTextField();
        jtf4.setBounds(150,170,150,20);
        c.add(jtf4);
        jb1=new JButton("Register");
        jb1.setBounds(40,250,100,20);
        jb1.addActionListener(this);
        c.add(jb1);
        jb2=new JButton("Reset");
        jb2.setBounds(180,250,70,20);
        jb2.addActionListener(this);
        c.add(jb2);
        jf.setVisible(true);
    }
    public void actionPerformed(ActionEvent ae) { 
        if(ae.getSource()==jb1)
        {   String p1 = jpf1.getText();
        String p2 = jpf2.getText();
          

        if(p1.equals(p2))
        {   
            JOptionPane.showMessageDialog(jf,"Registered Succesfully"); 
        }
        else{
        JOptionPane.showMessageDialog(jf,"Passwords didnt Matched","Alert",JOptionPane.WARNING_MESSAGE);  }
        }
        else if(ae.getSource()==jb2){    
          jtf1.setText(null);
          jtf2.setText(null);
          jtf3.setText(null);
          jtf4.setText(null);
          jpf1.setText(null);
          jpf2.setText(null);
          jcb.setSelectedIndex(0);
        }
    }
}
//Main.java

public class Main {
    public static void main(String[] args) {
        new gui1(); 
      }
}
