
import com.itextpdf.text.pdf.PdfReader;

import javax.swing.*;

import javax.swing.plaf.basic.BasicArrowButton;

import javax.swing.table.DefaultTableModel;

import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.Image;
import java.awt.event.*;
import java.io.*;


public class app {

    public static void main(String[] args) throws IOException {
        final JFrame frame=new JFrame();
        String [] values ={"Czarny","Szary","Czerwony","Niebieski"};

        final JTable table=new JTable();


        frame.setLayout(null);

        JButton button4=new JButton();
        JButton button3=new JButton();
        BasicArrowButton button2=new BasicArrowButton(BasicArrowButton.SOUTH);
        BasicArrowButton button1=new BasicArrowButton(BasicArrowButton.NORTH);
        ImageIcon imageIcon= new ImageIcon(app.class.getResource("folder.png"));
        Image image1=imageIcon.getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH);
        ImageIcon imageIcon2= new ImageIcon(app.class.getResource("copy.jpg"));
        ImageIcon imageIcon3= new ImageIcon(app.class.getResource("merge.png"));
        ImageIcon imageIcon4= new ImageIcon(app.class.getResource("watermark.png"));
        ImageIcon imageIcon5= new ImageIcon(app.class.getResource("delete.png"));
        ImageIcon imageIcon6=new ImageIcon(app.class.getResource("plus.png"));
        ImageIcon imageIcon7=new ImageIcon(app.class.getResource("minus.png"));
        Image image6 = imageIcon6.getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH);
        Image image7=imageIcon7.getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image image2=imageIcon2.getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH);
        Image image3=imageIcon3.getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH);
        Image image4=imageIcon4.getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH);
        Image image5=imageIcon5.getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH);


        JButton button6 = new JButton();
        JButton button7 = new JButton();
        JButton button8 = new JButton();
        JButton button9 = new JButton();

        button7.setIcon(new ImageIcon(image2));
        button6.setIcon(new ImageIcon(image1));
        button8.setIcon(new ImageIcon(image3));
        button9.setIcon(new ImageIcon(image5));
        button3.setIcon(new ImageIcon(image7));
        button4.setIcon(new ImageIcon(image6));
        button4.setToolTipText("Dodaj kolejny plik pdf");
        button3.setToolTipText("Usuń zaznaczony plik");
        button1.setToolTipText("Przesuń zaznaczony plik do góry w tabeli");
        button2.setToolTipText("Przesuń zaznaczony plik do dołu w tabeli");



        JButton button5=new JButton();
        button5.setIcon(new ImageIcon(image4));
        button5.setToolTipText("Dodaj znak wodny dla plików z zaznaczonym statusem");
        button6.setToolTipText("Otwórz zaznaczony plik pdf");
        button7.setToolTipText("Otwórz plik ze znakiem wodnym (jeśli został utworzony)");
        button8.setToolTipText("Umożliwia utworzenie pliku pdf z połączonych plików z tabeli o zaznaczonym statusie");
        button9.setToolTipText("Usuń wszystkie pliki ze znakiem wodnym");


        button1.setBounds(950,50,25,25);

        button2.setBounds(950,75,25,25);
        button3.setBounds(950,100,25,25);
        button4.setBounds(950,125,25,25);
        button6.setBounds(950,150,25,25);
        button7.setBounds(950,175,25,25);
        button5.setBounds(950,200,25,25);
        button8.setBounds(950,225,25,25);
        button9.setBounds(950,250,25,25);
        frame.add(button8);
        frame.add(button7);
        frame.add(button6);
        frame.add(button1);
        frame.add(button9);
        frame.add(button5);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);

        final DefaultTableModel model=new DefaultTableModel(){

            public Class<?> getColumnClass (int C){
                switch (C){
                    case 0:
                        return Boolean.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return Integer.class;
                    case 4:
                        return Integer.class;
                    case 5:
                        return Integer.class;
                    case 6:
                        return Integer.class;
                    case  7:
                        return String.class;
                    case 8:
                        return Integer.class;
                    case 9:
                        return Double.class;
                    case 10:
                        return Boolean.class;

                    default:
                        return String.class;
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                //return super.isCellEditable(row, column);
                if(column==1 ){
                    return false;}
                return true;
            }


        };

        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(table.getSelectedRow()!=0 && table.getSelectedRow()!=-1){

                    model.moveRow(table.getSelectedRow(),table.getSelectedRow(),table.getSelectedRow()-1);
                    table.setRowSelectionInterval(table.getSelectedRow()-1,table.getSelectedRow()-1);

                }


            }
        });


        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(table.getSelectedRow()!=table.getRowCount()-1 && table.getSelectedRow()!=-1) {
                    model.moveRow(table.getSelectedRow(), table.getSelectedRow(), table.getSelectedRow() + 1);
                    table.setRowSelectionInterval(table.getSelectedRow() + 1, table.getSelectedRow() + 1);
                }
            }
        });
        button3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(table.getSelectedRow()!=-1){
                    model.removeRow(table.getSelectedRow());

                }
            }
        });
        button4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FileDialog dialog=new FileDialog(frame,"Wybierz plik pdf");
                dialog.setMode(FileDialog.LOAD);
                dialog.setFile("*.pdf");
                dialog.setVisible(true);
                String nazwaPliku=null;
                try {
                    nazwaPliku=dialog.getDirectory().concat(dialog.getFile());
                }catch (Exception x){
                    System.out.println("Błąd zapisu");
                    return;
                }

                if(!walidacjaPlikuPdf(nazwaPliku,table,model)){
                    return;
                }

                model.addRow(new Object[0]);
                model.setValueAt(false,table.getRowCount()-1,0);
                model.setValueAt(nazwaPliku,table.getRowCount()-1,1);
                model.setValueAt("Załącznik" + table.getRowCount()+ ": ",table.getRowCount()-1,2);
                model.setValueAt(120,table.getRowCount()-1,3);
                model.setValueAt(800,table.getRowCount()-1,4);
                model.setValueAt(100,table.getRowCount()-1,5);
                model.setValueAt(100,table.getRowCount()-1,6);
                model.setValueAt(12,table.getRowCount()-1,8);
                model.setValueAt(1.0,model.getRowCount()-1,9);
                model.setValueAt(false,table.getRowCount()-1,10);



            }
        });
        button5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {





                TestyPdfThread testyPdfThread=new TestyPdfThread(table,model);
            }


        });
        button6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(table.getSelectedRow()!=-1){

                    File plik= new File( (String) model.getValueAt(table.getSelectedRow(),1));
                    try {
                        Desktop.getDesktop().open(plik);
                    } catch (IOException e1) {
                        System.out.println("Błąd otwarcia");
                        return;
                    }
                }

            }
        });
        button7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(table.getSelectedRow()!=-1){
                    String adresPdf=(String) model.getValueAt(table.getSelectedRow(),1);
                    File plik =new File (adresPdf.substring(0,adresPdf.length()-4).concat("Wynik.pdf"));
                    try {
                        Desktop.getDesktop().open(plik);
                    } catch (IOException e1) {
                        return;
                    }
                }
            }
        });

        button9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(table.getRowCount()>0){
                    for (int i = 0; i <table.getRowCount() ; i++) {
                        String adresPdf=(String) model.getValueAt(i,1);
                        File plik=new File(adresPdf.substring(0,adresPdf.length()-4).concat("Wynik.pdf"));

                        if (plik.exists()){

                            plik.delete();}

                    }
                }
            }
        });

        table.setModel(model);
        frame.setBounds(250,250,1100,600);
        JScrollPane pane=new JScrollPane(table);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        pane.setBounds(50,50,900,250);
        frame.add(pane);
        model.addColumn("Numerowanie");
        model.addColumn("Pdf");
        model.addColumn("Opis");
        model.addColumn("x");
        model.addColumn("y");
        model.addColumn("xLandscape");
        model.addColumn("yLandscape");
        model.addColumn("Kolor czcionki");
        model.addColumn("Wielkosc Czionki");
        model.addColumn("Transparentnosc");
        model.addColumn("Bez znaku wodnego");
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(250);
        table.getColumnModel().getColumn(2).setPreferredWidth(350);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        table.getColumnModel().getColumn(6).setPreferredWidth(100);
        table.getColumnModel().getColumn(7).setPreferredWidth(100);
        table.getColumnModel().getColumn(8).setPreferredWidth(100);
        table.getColumnModel().getColumn(9).setPreferredWidth(100);
        table.getColumnModel().getColumn(10).setPreferredWidth(100);
        table.getColumnModel().getColumn(7).setCellEditor(new ComboBoxEditor(values));
        table.getColumnModel().getColumn(7).setCellRenderer(new ComboBoxERenderer(values));
        table.setRowHeight(22);

        button8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(table.getRowCount()>1){
                    FileDialog fileDialog=new FileDialog(frame,"Zapisz plik");
                    fileDialog.setMode(FileDialog.SAVE);
                    fileDialog.setVisible(true);
                    String pdfName=null;

                    try{
                        pdfName=fileDialog.getDirectory()+fileDialog.getFile();
                        MergePdfThread mergePdfThread=new MergePdfThread(table,pdfName.concat(".pdf"),model);
                    }catch (Exception y){
                        return;
                    }


                }
            }
        });




        frame.setVisible(true);







    }

    private static boolean walidacjaPlikuPdf(String link,JTable table,DefaultTableModel model) {
        try {
            PdfReader pdfReader = new PdfReader(link);
           if(!pdfReader.isEncrypted()){
               for (int i = 0; i <table.getRowCount() ; i++) {
                    if(link.equals(model.getValueAt(i,1))){
                        JOptionPane.showMessageDialog(null,"Plik o tej nazwie już na liście");
                        pdfReader.close();
                        return false;
                    }
               }
               pdfReader.close();
               return true;
           }
            pdfReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null,"Plik chroniony");

        return false;

    }


    private static boolean walidacja(JTable table,int row,DefaultTableModel model) {
        for (int i = 0; i <table.getColumnCount() ; i++) {
            if(model.getValueAt(row,i)==null || model.getValueAt(row,i)==""){
                return false;
            }
        }
        return true;
    }



static class ComboBoxERenderer extends JComboBox implements TableCellRenderer{
    public ComboBoxERenderer(String [] items) {
        super(items);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        if(isSelected){
            setForeground(table.getSelectionForeground());
            super.setBackground(table.getSelectionBackground());
        }else {
            setForeground(table.getForeground());
            setBackground(table.getBackground());

        }
        setSelectedItem(value);
        return this;
    }
}
static class ComboBoxEditor extends DefaultCellEditor{


    public ComboBoxEditor(String [] items) {
        super(new JComboBox(items));
    }
}}









