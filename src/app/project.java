package app;
import java.util.*;
import java.io.*;
import java.util.regex.PatternSyntaxException;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.*;
import java.awt.*;
import java.awt.List;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.border.TitledBorder;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
import java.text.SimpleDateFormat;  
class project extends JFrame implements ActionListener,MouseListener{


    private int selectedrow=0;
    private int index=0;
	private String[] status = {"Index","ID","Name","Gender","Status","Date/Time"};
	private String[] title1 ={"ID","Name","Gender","Math","Phys","Chem","GPA","Scholar"};
	public static Object[] G= {"Male","Female"};
	private Object[][] Data1 ={{"1","Colin Wardle","Male","8","9","7","8","true"},
	{"2","Dolcie Hail","Female","9","9","9","9","true"},
	{"3","Kyle Nava","Male","8","9","10","9","true"},	
	{"5"," Valeriu Iseul ","Female","9.5","7","9.5","8","true"},
	{"6"," Kondwani Clemence ","Male","8.5","9","9.5","9","true"},
	{"7"," Yiskah Morgen ","Female","8.5","9","9.5","9","true"},
	{"8"," Drust Henrike ","Male","8.5","8","9.5","9","true"},
   };
    private Object[][]Data2={};
	private Vector vData=new Vector();
	private Vector vTitle=new Vector();	
	private Vector statData = new Vector();
	private Vector statTitle = new Vector();
    private JScrollPane tableResult1,tableresult2;
	private DefaultTableModel model1,model2;
	private TableRowSorter<TableModel> sorter1;
	private TableRowSorter<TableModel> sorter2;
	private JButton input,edit,exit,delete,export,logout;
	public static JPanel p1,p2,p3,p4,p5,p6,p7;
	public static JTable tb1,tb2; 
	private JTextField filter;
	private JLabel lb,lb2,lb3,lb4,lb5;
	private static final long serialVersionUID = 1L;
	JFrame f = new JFrame("Student Management");
	ExcelExporter excel;
	form form;
	public project(String s){
		super(s);
		try{
		
            load1(); //TODO: Load the student table		
			load2(); //TODO: Load the logs table

			p1 = new JPanel();
            p2 = new JPanel(new BorderLayout());
			p3 = new JPanel(new BorderLayout());
			p5 = new JPanel(new BorderLayout());
			p6 = new JPanel(new BorderLayout());
			p7 = new JPanel(new BorderLayout());
			p1.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "Options", TitledBorder.CENTER, TitledBorder.TOP));
			p2.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "List Student", TitledBorder.CENTER, TitledBorder.TOP));
			p5.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "Logs", TitledBorder.LEFT, TitledBorder.TOP));
			
			ImageIcon icon3 = new ImageIcon("icons8-add-new-24.png");
			ImageIcon icon4 = new ImageIcon("icons8-full-tool-storage-box-24.png");
			ImageIcon icon5 = new ImageIcon("icons8-delete-bin-24.png");
			ImageIcon icon6 = new ImageIcon("icons8-microsoft-excel-24.png");
			ImageIcon icon7 = new ImageIcon("icons8-sign-out-24.png");
			ImageIcon icon8 = new ImageIcon("icons8-exit-sign-24.png");
			
			
			//! Setting up Button 
			input = new JButton("Insert",icon3);
			delete = new JButton("Delele",icon5);
			edit = new JButton("Edit",icon4);
			exit = new JButton ("Exit",icon8);
			filter = new JTextField();
			lb = new JLabel(new ImageIcon("icons8-find-user-male-filled-24.png"));
			export = new JButton("Export",icon6);
			logout = new JButton("Logout",icon7);

			//! Setting up table 
			model1 = new DefaultTableModel(vData,vTitle);
            model2 = new DefaultTableModel(statData,statTitle);
            tb1 = new JTable(model1);
			tb2 = new JTable(model2);

			//TODO: to disable cells editing
			tb2.setDefaultEditor(Object.class, null);
			tb1.setDefaultEditor(Object.class, null);


			sorter1 = new TableRowSorter<TableModel>(model1);
			sorter2 = new TableRowSorter<TableModel>(model2);
            tableResult1 = new JScrollPane(tb1);
			tableresult2 = new JScrollPane(tb2);
			tb1.setRowSorter(sorter1);
			tb2.setRowSorter(sorter2);

			//! Setting up Action for button
			input.addActionListener(this);
			export.addActionListener(this);
			exit.addActionListener(this);
			edit.addActionListener(this);
			delete.addActionListener(this);
			logout.addActionListener(this);
			 
			//! Setting up Button Alignment 
			input.setAlignmentX(Component.CENTER_ALIGNMENT);
			exit.setAlignmentX(Component.CENTER_ALIGNMENT);
			edit.setAlignmentX(Component.CENTER_ALIGNMENT);
			delete.setAlignmentX(Component.CENTER_ALIGNMENT);
			export.setAlignmentX(Component.CENTER_ALIGNMENT);
			logout.setAlignmentX(Component.CENTER_ALIGNMENT);

			//! Adding button to the panels
			p1.add(input);
			p1.add(edit);
            p1.add(delete);
			p1.add(export);
			p1.add(logout);
			p1.add(exit);
			p1.setBackground(Color.gray);
			p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
			p3.add(lb,BorderLayout.LINE_START);
			p3.add(filter);
			p2.add(p3,BorderLayout.SOUTH);
			tb1.addMouseListener(this);
			p2.add(tableResult1);
            p5.add(tableresult2);

			//! Add panels to frame f
			f.add(p1,BorderLayout.WEST);
			f.add(p2,BorderLayout.LINE_END);
			f.add(p5,BorderLayout.SOUTH);
			f.setVisible(true);
			f.setExtendedState(JFrame.MAXIMIZED_BOTH);
			filter.getDocument().addDocumentListener(new DocumentListener(){
				public void insertUpdate(DocumentEvent e) {
					newFilter();
				}
				public void removeUpdate(DocumentEvent e) {
					newFilter();
				}
				public void changedUpdate(DocumentEvent e) {
					newFilter();
				}
			});
		}catch(Exception e)
		//TODO: handle exception
		 {
			System.out.println(e.getMessage());
			}
	}


	//! row filter 
	public void newFilter(){
		RowFilter<TableModel,Object> rf = null;
		try {
			rf = RowFilter.regexFilter(filter.getText());
		} catch (PatternSyntaxException e) {
			//TODO: handle exception
			return;
		}
        sorter1.setRowFilter(rf);
	}
	
	//! export to excel
	public void export(){
		try {
			ExcelExporter exp = new ExcelExporter();
			String extension = ".xls";
			String name = JOptionPane.showInputDialog(null, "Name of the file","Save as",JOptionPane.INFORMATION_MESSAGE);
			exp.exporttable(tb1,new  File (name+extension));
			JOptionPane.showMessageDialog(null, "Data Saved ", "Message", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			//TODO: handle exception
			e.printStackTrace();
		}
	}

	//! button listener
	public void actionPerformed(ActionEvent e){
		if (e.getSource()==exit) System.exit(0);
		else if (e.getSource()==input) new form("Insert form",this,"","","","0","0","0");
		else if (e.getSource()==edit){
			Vector st = (Vector)vData.elementAt(selectedrow);
			new form("Edit form",this,(String)st.elementAt(0),(String)st.elementAt(1),(String)st.elementAt(2),(String)st.elementAt(3),(String)st.elementAt(4),(String)st.elementAt(5));
		}
		else if (e.getSource()==delete) {
			int a = JOptionPane.showConfirmDialog(null, "Confirm delete?","Message",JOptionPane.YES_NO_OPTION);
			if (a==JOptionPane.YES_OPTION){
				delete();
			}

		}
		else if (e.getSource()==export) export();
		else if (e.getSource()==logout) {
			JOptionPane.showMessageDialog(null, "Logged Out","Message",JOptionPane.INFORMATION_MESSAGE);
			f.setVisible(false);
			new mainfile();
		}
		}
	
	public void mouseClicked(MouseEvent e){
		selectedrow = tb1.getSelectedRow();
	}

	public void mouseEntered(MouseEvent e){

	}
	public void mouseExited(MouseEvent e){

	}
	public void mousePressed(MouseEvent e){

	}
	public void mouseReleased(MouseEvent e){

	}
 	//! load data for student table
    public void load1(){
		vTitle.clear(); 	  
		vData.clear();  
		int num_column = title1.length;
		for (int i=0; i<num_column;i++) 
		{	    	   
			vTitle.add(title1[i]);
		}
		for (int i=0; i<Data1.length;i++) 
		{
			Vector row = new Vector(num_column);
			for (int j=0; j<num_column;j++) {
					  row.add(Data1[i][j]);
			} 

		 vData.add(row);

		}
	}
	//! Load data for logs table
    public void load2(){
        statData.clear();
        statTitle.clear();
        int num_column=status.length;
        for (int i=0;i<num_column;i++){
            statTitle.add(status[i]);
        }
        for (int i=0;i<Data2.length;i++){
            Vector row = new Vector(num_column);
            for (int j=0;j<num_column;j++){
                row.add(Data2[i][j]);
            }
            statData.add(row);
        }
    }
	
	//! delete feature
	public void delete(){
        Vector st = (Vector)vData.elementAt(selectedrow);
        Vector row = new Vector();
        vData.remove(selectedrow);
        row.add(index++);
        row.add(st.get(0));
		row.add(st.get(1));
		row.add(st.get(2));
		row.add("Removed");
		Date now = new Date();
		SimpleDateFormat current = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
		//E day of weak
		//y in four digit and similar to m and d,hh,mm,ss
		//a A.M./P.M. marker
		//z is time zone 
        row.add(current.format(now));
        statData.add(row);
        model1.fireTableDataChanged();
        model2.fireTableDataChanged();
	}

	//! insert feature
	public void insertList(String id, String name,String gender, float math, float phys, float chem, float aver,Boolean s){
        Vector row1 = new Vector();
        Vector row2 = new Vector();
		row1.add(id);
		row1.add(name);
		row1.add(gender);
		row1.add(math);
		row1.add(phys);
		row1.add(chem);
		row1.add(aver);
		row1.add(s);
        row2.add(index++);
        row2.add(id);
		row2.add(name);
		row2.add(gender);
		row2.add("New");
		Date now = new Date();
		SimpleDateFormat current = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
        row2.add(current.format(now));
        vData.add(row1);
        statData.add(row2);
        model1.fireTableDataChanged();
        model2.fireTableDataChanged();
	}

	//! edit feature
	public void editList(String id, String name,String gender, float math, float phys, float chem, float aver,Boolean s){
		Enumeration e = vData.elements();
		int i=0;
			    	
		while (e.hasMoreElements())
		{
			Vector st = (Vector)e.nextElement();
			if (st.elementAt(0).equals(id)) 
			{
				vData.remove(i);
				break;
			}
				i++;
		}
        Vector row1 = new Vector();
        Vector row2 = new Vector();
		row1.add(id);
		row1.add(name);
		row1.add(gender);
		row1.add(math);
		row1.add(phys);
		row1.add(chem);
		row1.add(aver);
		row1.add(s);
        row2.add(index++);
        row2.add(id);
		row2.add(name);
		row2.add(gender);
        row2.add("Modified");
        row2.add(new SimpleDateFormat("E yyyy.MM.dd.HH.mm.ss").format(new Date()));
        vData.add(i,row1);
        statData.add(row2);
        model1.fireTableDataChanged();
        model2.fireTableDataChanged();
	}
}