import com.itextpdf.text.pdf.PdfReader;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Testy2 {
    public static void main(String[] args) {
//        JFrame frame=new JFrame();
//        frame.setBounds(250,250,400,400);
//        frame.setVisible(true);
//        frame.setLayout(null);
//        final JTable table=new JTable();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        final DefaultTableModel model=new DefaultTableModel()
//        {
//
//            public Class<?> getColumnClass(int columnIndex) {
//              switch (columnIndex){
//                  case 0:
//                      return String.class;
//
//                  case 1:
//                      return Integer.class;
//                      default:
//                          return String.class;
//              }
//            }
//
//            @Override
//            public boolean isCellEditable(int row, int column) {
//                if (column==1){
//                    return true;
//                }
//                return false;
//            }
//        };
//
//        //frame.add(table);
//        //table.setBounds(50,50,150,150);
//        model.addColumn("Kolumna1");
//        model.addColumn("Kolumn2");
//        JScrollPane pane=new JScrollPane();
//        pane.setViewportView(table);
//        pane.setBounds(50,50,150,150);
//        frame.add(pane);
//        model.addRow(new Object[0]);
//        model.setValueAt("Test",0,0);
//        table.setModel(model);
//        table.getModel().addTableModelListener(new TableModelListener() {
//            @Override
//            public void tableChanged(TableModelEvent e) {
//                System.out.println(model.getValueAt(0,1));
//            }
//        });

        KlasaTestowa klasaTestowa=new KlasaTestowa("Opis1",1);
        KlasaTestowa klasaTestowa1=new KlasaTestowa("Opis1",5);
        KlasaTestowa klasaTestowa2=new KlasaTestowa("Opis2",3);
        KlasaTestowa klasaTestowa3=new KlasaTestowa("Opis3",4);

        Set<KlasaTestowa>lista=new HashSet();
        lista.add(klasaTestowa);
        lista.add(klasaTestowa1);
        lista.add(klasaTestowa2);
        lista.add(klasaTestowa3);
        for (KlasaTestowa x:lista
             ) {
            System.out.println(x.id);


        }}

}
