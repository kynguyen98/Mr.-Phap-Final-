package app;

import java.util.*;
import java.util.Date;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.io.*; 
import java.awt.*;

public class mainfile extends JFrame implements ActionListener{
  ImageIcon icon;
  ImageIcon icon2 = new ImageIcon("icons8-forgot-password-24.png");
  ImageIcon icon3 = new ImageIcon("icons8-name-24.png");
  ImageIcon icon1 = new ImageIcon("icons8-login-24.png");
  JPanel p = new JPanel(new GridLayout(3,2));
  JPanel p1,p2;
  JButton blogin = new JButton("Login",icon1);
  JLabel title = new JLabel("Login Form");
  JLabel username = new JLabel(icon3);
  JLabel password = new JLabel(icon2);
  JLabel nul = new JLabel();
  JLabel label;

  JTextField txtadmin = new JTextField("kynguyen");
  JPasswordField pass = new JPasswordField("cobra123");
  JFrame f1 = new JFrame("Welcome");
  project pro;
    public mainfile() {
      try {
      Date now = new Date();
      p1 = new JPanel();
      SimpleDateFormat current = new SimpleDateFormat("E hh:mm:ss");
      label = new JLabel(current.format(now));
      p2 = new JPanel();
      title.setFont(new Font ("Serif",Font.BOLD,20));

      Dimension expectedDimension = new Dimension(400, 100);

      p.setPreferredSize(expectedDimension);
      p.setMaximumSize(expectedDimension);
      p.setMinimumSize(expectedDimension);

      p.add(username);
      p.add(txtadmin);
      p.add(password);
      p.add(pass);
      p.add(nul);
      p.add(blogin);
      blogin.addActionListener(this);
      p2.add(label,BorderLayout.NORTH);


      Box box = new Box(BoxLayout.Y_AXIS);
      box.add(Box.createVerticalGlue());
      box.add(p);
      box.add(Box.createVerticalGlue());
      box.setBounds(0,0,600,400);
      p1.add(label);


      f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     // f1.add(p2);
     f1.add(p1,BorderLayout.NORTH);
      f1.add(box);
      f1.setExtendedState(JFrame.MAXIMIZED_BOTH);

      f1.pack(); //using pack to let the layout manager manage the frame
      f1.setVisible(true);
    } catch (Exception e) {
      e.printStackTrace();
      //TODO: handle exception
    }
    }
    
    public void actionPerformed(ActionEvent e)
    {
      String uname = txtadmin.getText();
      String password = pass.getText();
      if (e.getActionCommand().equals("Login")){
          if (uname.equals("")||password.equals("")) JOptionPane.showMessageDialog(null, "Empty value","Message",JOptionPane.WARNING_MESSAGE);
          else if (uname.equals("kynguyen") && password.equals("cobra123")) {
            JOptionPane.showMessageDialog(null, "Login successful","Message",JOptionPane.INFORMATION_MESSAGE);
            f1.setVisible(false);
            new project("Student management");
          } 
          else JOptionPane.showMessageDialog(null, "Incorect username or password","Message",JOptionPane.ERROR_MESSAGE);
     }
     if (e.getActionCommand().equals("Done")) System.exit(0);
    }

    public static void main(String[] args) {
     new mainfile();
    }
   }
