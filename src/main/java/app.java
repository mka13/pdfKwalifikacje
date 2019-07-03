
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.exceptions.InvalidPdfException;
import com.itextpdf.text.pdf.*;

import javax.swing.*;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.basic.BasicArrowButton;

import javax.swing.table.DefaultTableModel;

import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;


public class app {
    static Map<String,plikPdf>pliki;
    public static void main(String[] args) throws IOException {
        final JFrame frame=new JFrame();
        final String [] values ={"Czarny","Szary","Czerwony","Niebieski"};

        final JTable table=new JTable();


        frame.setLayout(null);
        pliki=new HashMap<>();
        JButton button4=new JButton();
        JButton button3=new JButton();
        //final JTextArea textArea=new JTextArea();
        //JScrollPane pane2=new JScrollPane();

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
                        return Boolean.class;
                    case 2:
                        return Boolean.class;


                    case 3: return String.class; //pdf sciezka

                    case 4: return String.class; //pdf opis

                    case 5: return Color.class;

                    case 6: return Integer.class;
                    case 7: return Double.class;

                    case 8: return Integer.class;
                    case 9: return Integer.class;


                    default:
                        return String.class;
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                //return super.isCellEditable(row, column);
                if(column==3 ){
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
                    pliki.remove(model.getValueAt(table.getSelectedRow(),1));
                    model.removeRow(table.getSelectedRow());
                    //textArea.setText("");

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
                    return;
                }
                PdfReader pdfReader=null;
                try {
                     pdfReader=new PdfReader(nazwaPliku);
                } catch (IOException e1) {
                   JOptionPane.showMessageDialog(null,"Plik " + nazwaPliku + " jest uszkodzony lub ma niepoprawny format i nie da się go wczytać");
                return;
                }
               nazwaPliku= czyPlikJestChroniony(nazwaPliku,pdfReader);
                if(nazwaPliku.equals("")){
                    pdfReader.close();
                    return;

                }

                if(!walidacjaPlikuPdf(nazwaPliku,table,model,pdfReader)){
                    pdfReader.close();
                    return;
                }
                pdfReader.close();
                model.addRow(new Object[0]);
                model.setValueAt(true,table.getRowCount()-1,0);
                model.setValueAt(true,table.getRowCount()-1,1);
                model.setValueAt(false,table.getRowCount()-1,2);




                model.setValueAt(nazwaPliku,table.getRowCount()-1,3);
                model.setValueAt("Załącznik" + table.getRowCount()+ ": ",table.getRowCount()-1,4);
                model.setValueAt(values[0],table.getRowCount()-1,5);
                model.setValueAt(12,table.getRowCount()-1,6);
                model.setValueAt(1.0,model.getRowCount()-1,7);


                model.setValueAt(1,table.getRowCount()-1,8);
                Integer maxStrona=pliki.get(nazwaPliku).iloscStron;
                model.setValueAt(maxStrona,table.getRowCount()-1,9);


            }
        });
        button5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {






                TestyPdfThread testyPdfThread=new TestyPdfThread(table,model,pliki);
            }


        });
        button6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(table.getSelectedRow()!=-1){

                    File plik= new File( (String) model.getValueAt(table.getSelectedRow(),3));
                    try {
                        Desktop.getDesktop().open(plik);
                    } catch (IOException e1) {

                        return;
                    }
                }

            }
        });
        button7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(table.getSelectedRow()!=-1){
                    String adresPdf=(String) model.getValueAt(table.getSelectedRow(),3);
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
                        String adresPdf=(String) model.getValueAt(i,3);
                        File plik=new File(adresPdf.substring(0,adresPdf.length()-4).concat("Wynik.pdf"));

                        if (plik.exists()){

                            plik.delete();}

                    }
                }
            }
        });

        table.setModel(model);
        frame.setBounds(250,250,1100,600);
        final JScrollPane pane=new JScrollPane(table);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        pane.setBounds(50,50,900,250);
        pane.setDropTarget(new DropTarget(){
            @Override
            public synchronized void drop(DropTargetDropEvent dtde) {
                dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                Transferable transferable = dtde.getTransferable();
                List listaPlikow=null;
                File plik=null;
                String nazwaPliku;
                try {
                    listaPlikow=(List) transferable.getTransferData(DataFlavor.javaFileListFlavor);
                    for (int i = 0; i <listaPlikow.size() ; i++) {
                        plik=(File) listaPlikow.get(i);
                        nazwaPliku=plik.getPath();
                        PdfReader pdfReader= null;
                        try {
                            pdfReader = new PdfReader(nazwaPliku);
                        } catch (IOException e) {
                            JOptionPane.showMessageDialog(null,"Plik " + nazwaPliku + " jest uszkodzony lub ma niepoprawny format i nie da się go otworzyć ");
                            continue;
                        }
                        nazwaPliku= czyPlikJestChroniony(nazwaPliku,pdfReader);


                        if (walidacjaPlikuPdf(nazwaPliku,table,model,pdfReader)&&!nazwaPliku.equals("")){

                            model.addRow(new Object[0]);
                            model.setValueAt(true,table.getRowCount()-1,0);
                            model.setValueAt(true,table.getRowCount()-1,1);
                            model.setValueAt(false,table.getRowCount()-1,2);




                            model.setValueAt(nazwaPliku,table.getRowCount()-1,3);
                            model.setValueAt("Załącznik" + table.getRowCount()+ ": ",table.getRowCount()-1,4);
                            model.setValueAt(values[0],table.getRowCount()-1,5);
                            model.setValueAt(12,table.getRowCount()-1,6);
                            model.setValueAt(1.0,model.getRowCount()-1,7);


                            model.setValueAt(1,table.getRowCount()-1,8);
                            Integer maxStrona=pliki.get(nazwaPliku).iloscStron;
                            model.setValueAt(maxStrona,table.getRowCount()-1,9);






                        }
                        pdfReader.close();

                    }
                } catch (UnsupportedFlavorException e) {
                    e.printStackTrace();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

//        textArea.setBounds(50,300,900,250);
        //pane2.setBounds(50,300,900,250);
        //pane2.setViewportView(textArea);
        //frame.add(pane2);
//        frame.add(textArea);
        frame.add(pane);
        model.addColumn("Numerowanie?");
        model.addColumn("Znak wodny?");
        model.addColumn("Łącz bez znaku wodnego");
        model.addColumn("Scieżka");
        model.addColumn("Opis");
        model.addColumn("Kolor czcionki");
        model.addColumn("Wielkosc Czionki");
        model.addColumn("Transparentnosc");
        model.addColumn("Str min");
        model.addColumn("Str max");
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(300);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        table.getColumnModel().getColumn(6).setPreferredWidth(100);
        table.getColumnModel().getColumn(7).setPreferredWidth(100);
        table.getColumnModel().getColumn(8).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setCellEditor(new ComboBoxEditor(values));
        table.getColumnModel().getColumn(5).setCellRenderer(new ComboBoxERenderer(values));
        table.setRowHeight(22);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                String text;
//                text="Ilość stron: " + pliki.get(model.getValueAt(table.getSelectedRow(), 1)).iloscStron + '\n';
//                text=text + "Znalezione formaty: " + '\n';
//                for (String x: pliki.get(model.getValueAt(table.getSelectedRow(), 1)).getFormaty()
//                     ) {
//                        text=text + x + '\n';
//
//                }
//
//                textArea.setText(text);
                final JTable table1=new JTable();
                JScrollPane pane1=new JScrollPane();
                pane1.setViewportView(table1);
                pane1.setBounds(50,300,900,250);

                final DefaultTableModel model1=new DefaultTableModel(){
                    @Override
                    public Class<?> getColumnClass(int columnIndex) {
                        switch (columnIndex){
                            case 0:
                                return String.class;
                            case 1:
                                return Integer.class;
                            case 2:
                                return Integer.class;
                            case 3:
                                return Integer.class;
                                default:
                                    return String.class;
                        }
                    }

                    @Override
                    public boolean isCellEditable(int row, int column) {
                        if(column==2 || column==3){
                            return true;
                        }
                        return false;
                    }
                };
                table1.setModel(model1);
                model1.addColumn("Formaty");
                model1.addColumn("Rotacja");
                model1.addColumn("x");
                model1.addColumn("y");
                frame.add(pane1);
                for (Format x:pliki.get(model.getValueAt(table.getSelectedRow(), 3)).getFormaty()
                     ) {
                    model1.addRow(new Object[0]);
                    model1.setValueAt(x.getOpis(),table1.getRowCount()-1,0);
                    model1.setValueAt(x.getRotacja(),table1.getRowCount()-1,1);
                    model1.setValueAt(x.getX(),table1.getRowCount()-1,2);
                    model1.setValueAt(x.getY(),table1.getRowCount()-1,3);
                }
            table1.getModel().addTableModelListener(new TableModelListener() {
                @Override
                public void tableChanged(TableModelEvent e) {


                        for (Format x: pliki.get(model.getValueAt(table.getSelectedRow(), 3)).getFormaty()
                             ) {
                            if(x.getOpis().equals(model1.getValueAt(table1.getSelectedRow(),0))){
                                x.setX((Integer) model1.getValueAt(table1.getSelectedRow(),2));
                                x.setY((Integer) model1.getValueAt(table1.getSelectedRow(),3));
                                System.out.println("zmiana");

                        }
                    }

                }
            });
            }

        }

        );
        button8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(table.getRowCount()>0){
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

    private static String czyPlikJestChroniony(String link,PdfReader pdfReader){
        String bufor;
        if(pdfReader.isEncrypted()){

            int odp = JOptionPane.showConfirmDialog(null, "Plik chroniony. Odblokować?","Plik chroniony", JOptionPane.YES_NO_OPTION);
            if(odp==JOptionPane.YES_OPTION){
                PdfReader.unethicalreading=true;

                try {

                    PdfStamper stamper = new PdfStamper(pdfReader,new FileOutputStream(link.substring(0,link.length()-4).concat("_odblokowany.pdf")));

                    bufor=link.substring(0,link.length()-4).concat("_odblokowany.pdf");
                    stamper.close();
                    return bufor;
                } catch (DocumentException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }else{

                return "";
            }

        }

        return link;



    }


    private static boolean walidacjaPlikuPdf(String link,JTable table,DefaultTableModel model,PdfReader pdfReader) {




               for (int i = 0; i <table.getRowCount() ; i++) {
                    if(link.equals(model.getValueAt(i,3))){
                        JOptionPane.showMessageDialog(null,"Plik o tej nazwie już na liście");

                        return false;
                    }
               }

               int iloscStron;
               iloscStron=pdfReader.getNumberOfPages();
                Set<Format>formaty=new HashSet<>();
                String opis;
               for (int i = 1; i <=iloscStron ; i++) {

                   Format format=new Format(String.valueOf(pdfReader.getPageSize(i)),pdfReader.getPageRotation(i),(int) pdfReader.getPageSize(i).getWidth()-50,(int) pdfReader.getPageSize(i).getHeight()-20);
                   formaty.add(format);
                   //                    format= String.valueOf(pdfReader.getPageSize(i));
//                    formaty.add(format);
               }

               plikPdf pdf=new plikPdf(formaty,iloscStron);
               pliki.put(link,pdf);
               //plikPdf pdf= new plikPdf()

               return true;








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

            switch ((String) value){
                case "Czarny":
                    setForeground(Color.BLACK);
                    break;
                case "Szary":
                    setForeground(Color.GRAY);
                    break;
                case "Czerwony":
                    setForeground(Color.RED);
                    break;
                case "Niebieski":
                    setForeground(Color.BLUE);
                    break;
                    default:
                        setForeground(Color.BLACK);

            }


            super.setBackground(table.getSelectionBackground());
        }else {
            switch ((String) value){
                case "Czarny":
                    setForeground(Color.BLACK);
                    break;
                case "Szary":
                    setForeground(Color.GRAY);
                    break;
                case "Czerwony":
                    setForeground(Color.RED);
                    break;
                case "Niebieski":
                    setForeground(Color.BLUE);
                    break;
                default:
                    setForeground(Color.BLACK);

            }
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









