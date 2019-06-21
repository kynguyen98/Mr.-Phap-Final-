package app;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.TitledBorder;
 
class form extends JFrame implements ActionListener{
    project pro;
    JLabel Idlb,mathlb,physlb,namelb,chemlb,gender; 
    JTextField Id,name,math,phys,chem;  
    JButton ok,done;
    boolean s;
String g;
    JComboBox Gender = new JComboBox(pro.G);
    int offset1,offset2;
    public form(String s,project proj,String id, String na,String g, String m, String ph, String ch){
        super(s);
        try {
        ImageIcon icon1 = new ImageIcon("icons8-ok-24.png");	
        ImageIcon icon2 = new ImageIcon("icons8-cancel-24.png");
        pro=proj;
        pro.p4 = new JPanel();
        pro.p4.setLayout(new GridLayout(7,2));

        Idlb = new JLabel("Id");
        Id = new JTextField(id);
        gender = new JLabel("Gender");
        if (s.equals("Edit form")) Id.setEditable(false);
        namelb = new JLabel("Name");
        Gender.setSelectedItem(null);
        name = new JTextField(na);
        mathlb= new JLabel("Math");
        math= new JTextField(m);
        physlb= new JLabel("Physics");
        phys= new JTextField(ph);
        chemlb= new JLabel("Chemistry");
        chem= new JTextField(ch);			
        ok = new JButton("OK",icon1);
        done = new JButton("Done",icon2);

        pro.p4.add(Idlb);
        pro.p4.add(Id);	
        pro.p4.add(namelb);
        pro.p4.add(name);
        pro.p4.add(gender);
        pro.p4.add(Gender);
        pro.p4.add(mathlb);
        pro.p4.add(math);
        pro.p4.add(physlb);
        pro.p4.add(phys);
        pro.p4.add(chemlb);
        pro.p4.add(chem);
        pro.p4.add(ok);
        pro.p4.add(done);
        pro.p7.add(pro.p4);
        pro.f.add(pro.p7,BorderLayout.CENTER);
        
        pro.p4.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "Information", TitledBorder.CENTER, TitledBorder.TOP));

        ok.addActionListener(this);
        done.addActionListener(this);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pro.f.setVisible(true);
    } catch (Exception e) {
        e.printStackTrace();
        //TODO: handle exception
    }
    }
    public void actionPerformed(ActionEvent e){
        if (e.getActionCommand().equals("OK")){
            insertDB();
            }
        else pro.p4.setVisible(false);

    }
    public void insertDB(){
        g = (String)Gender.getSelectedItem().toString();
        if ( Id.getText().equals("")||name.getText().equals("")||math.getText().equals("")||phys.getText().equals("")||chem.getText().equals("")||g.equals("")){
            JOptionPane.showMessageDialog(null, "Empty Value", "Message", JOptionPane.ERROR_MESSAGE);
        }
        else {
            String id = Id.getText();		 
            String na = name.getText();
            
            float m =Float.parseFloat(math.getText());
            float ph =Float.parseFloat(phys.getText());
            float ch =Float.parseFloat(chem.getText());	
            float gpa = (m+ph+ch)/3;

            if ((m+ph+ch)/3 > 8){
                s = true;
            }
            else   s = false;
            if (this.getTitle().equals("Insert form"))
            pro.insertList(id,na,g,m,ph,ch,gpa,s);
        else
        pro.editList(id,na,g,m,ph,ch,gpa,s);
        }
    }

}