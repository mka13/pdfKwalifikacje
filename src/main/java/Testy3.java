import javax.activation.ActivationDataFlavor;
import javax.activation.DataHandler;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Testy3  {
    public static void main(String[] args) {
        JFrame frame=new JFrame();
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JTable table=new JTable();
        JScrollPane pane=new JScrollPane();
        pane.setViewportView(table);
        frame.add(pane);
        frame.setBounds(250,250,500,500);
        frame.setVisible(true);
        final DefaultTableModel model=new DefaultTableModel();
        table.setModel(model);
        model.addColumn("kol1");
        model.addColumn("kol2");
        model.addRow(new Object[0]);
        model.setValueAt("test",0,0);
        pane.setBounds(50,50,400,400);
        table.setDropMode(DropMode.INSERT_ROWS);
//        table.setDropTarget(new DropTarget(){
//            @Override
//            public synchronized void drop(DropTargetDropEvent dtde) {
//                dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
//                Transferable transferable = dtde.getTransferable();
//                try {
//
//                    java.util.List transferData = (java.util.List) transferable.getTransferData(DataFlavor.javaFileListFlavor);
//                    File file = (File) transferData.get(0);
//                    System.out.println(file.getAbsolutePath());
//
//                } catch (UnsupportedFlavorException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
    pane.setDropTarget(new DropTarget(){
        @Override
        public synchronized void drop(DropTargetDropEvent dtde) {
            dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
            Transferable transferable = dtde.getTransferable();
            File file=null;
            List transferData=null;
            try {
                transferData = (List) transferable.getTransferData(DataFlavor.javaFileListFlavor);



                for (int i = 0; i < transferData.size(); i++) {
                    file = (File) transferData.get(i);
                    model.addRow(new Object[0]);
                    model.setValueAt(file.getName(),table.getRowCount()-1,0);
                }

            } catch (UnsupportedFlavorException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }




        }



    });



    }


}
