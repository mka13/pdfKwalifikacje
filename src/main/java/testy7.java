import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class testy7 {
    //static DefaultTableModel model;
    static JScrollPane pane=null;
    public static void main(String[] args) {

        final KlasaTestowa2 klasaTestowa=new KlasaTestowa2("x","y");
        final KlasaTestowa2 klasaTestowa2=new KlasaTestowa2("test1","test2");
        final JFrame frame=new JFrame();


        frame.setVisible(true);
        frame.setLayout(null);
        frame.setBounds(250,250,500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final List<KlasaTestowa2> lista=new ArrayList();
        final String [] kolumny={"kol1","kol2"};
        lista.add(klasaTestowa);
        lista.add(klasaTestowa2);
        JButton button=new JButton();
        button.setBounds(50,50,50,50);
        frame.add(button);
        JButton button2=new JButton();
        button2.setBounds(400,50,50,50);
        frame.add(button2);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("usun");
                if (pane!=null){
                    pane.setVisible(false);}
            }
        });
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

//             final    DefaultTableModel  model=new DefaultTableModel(){
//                    @Override
//                    public boolean isCellEditable(int row, int column) {
//                       return true;
//                    }
//
//                };
//                table.setModel(model);
//                model.addColumn("kol1");
//                model.addColumn("kol2");
//                model.addRow(new Object[0]);
//                model.setValueAt("Test1",0,0);
//                model.setValueAt("Test2",0,1);
                AbstractTableModel model1=new AbstractTableModel() {

                    @Override
                    public int getRowCount() {
                        return lista.size();
                    }

                    @Override
                    public int getColumnCount() {
                        return 2;
                    }

                    @Override
                    public Object getValueAt(int rowIndex, int columnIndex) {

                        KlasaTestowa2 klasaTestowa21 = lista.get(rowIndex);
                        if(columnIndex==0){
                            return klasaTestowa21.getX();
                        }
                        return klasaTestowa21.getY();
                    }

                    @Override
                    public String getColumnName(int column) {
                        return kolumny[column];
                    }

                    @Override
                    public Class<?> getColumnClass(int columnIndex) {
                        return String.class;
                    }

                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return true;
                    }

                    @Override
                    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                        KlasaTestowa2 klasaTestowa21 = lista.get(rowIndex);
                        if (columnIndex==1){
                        klasaTestowa21.setY((String) aValue );}
                        else {
                            klasaTestowa21.setX((String) aValue );
                        }
                        fireTableCellUpdated(rowIndex,columnIndex);

                    }
                };
                final JTable table=new JTable(model1);
                 pane=new JScrollPane();
                pane.setViewportView(table);
                pane.setBounds(100,100,300,300);

                frame.add(pane);




//                table.getModel().addTableModelListener(new TableModelListener() {
//                    @Override
//                    public void tableChanged(TableModelEvent e) {
//                        klasaTestowa.setX((String) model.getValueAt(0,0));
//                        klasaTestowa.setY((String) model.getValueAt(0,1));
//                        System.out.println(klasaTestowa.getX());
//                        System.out.println(klasaTestowa.getY());
//
//                    }
//                });
            }
        });

    }
}
