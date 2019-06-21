package app;
import java.io.*;
import javax.swing.*;
import javax.swing.table.TableModel;
 
class ExcelExporter{
    project pro ;
    public ExcelExporter(){}
        public void exporttable(JTable tb1,File file) throws IOException{
            TableModel model1 = tb1.getModel();//create a model by tb1 from project.java
            FileWriter out = new FileWriter(file); //creating file 
            for (int i=0;i<model1.getColumnCount();i++){
                out.write(model1.getColumnName(i)+"\t");
            }
            out.write("\n");
            for (int i=0;i<model1.getRowCount();i++){
                for (int j=0;j<model1.getColumnCount();j++){
                        out.write(model1.getValueAt(i, j).toString()+"\t");
                }
                out.write("\n");
            }
            out.close();
        }


    }