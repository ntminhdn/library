/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import data.DataAccess;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.jdbc.JDBCCategoryDataset;

/**
 *
 * @author Administrator
 */
public class ChartGUI extends javax.swing.JFrame {

    // a xoa dong conn.close trong DataAccess no moi chay duoc
    DefaultTableModel dm = new DefaultTableModel();
    private String[] tenCot = {"Month", "Count"};
    private DataAccess da = new DataAccess();
    private String sql0 = "Select month(borrowdate) as borrowmonth, count(borrowid) as borrowcount"
            + " from borrowingmanagement group by month(borrowdate)";
    private String sql1 = "Select month(activationdate) as activationmonth, count(rdid) as rdcount\n"
            + " from reader group by month(activationdate)";

    /**
     * Creates new form Chart
     */
    public ChartGUI() {
        super("Chart");
        initComponents();
        setLocationRelativeTo(null);
        this.tbSumBookBorrowed.setModel(dm);
        dm.setColumnIdentifiers(tenCot);
        da.LoadData(sql0, tbSumBookBorrowed);
        this.tbSumReaderNew.setModel(dm);
        dm.setColumnIdentifiers(tenCot);
        da.LoadData(sql1, tbSumReaderNew);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbSumBookBorrowed = new javax.swing.JTable();
        btArea1 = new javax.swing.JButton();
        btBar1 = new javax.swing.JButton();
        btLine1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbSumReaderNew = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        lbSumBook = new javax.swing.JLabel();
        lbSumReader = new javax.swing.JLabel();
        btLine2 = new javax.swing.JButton();
        btBar2 = new javax.swing.JButton();
        btArea2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        tbSumBookBorrowed.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbSumBookBorrowed);

        btArea1.setText("Area Chart");
        btArea1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btArea1ActionPerformed(evt);
            }
        });

        btBar1.setText("Bar Chart");
        btBar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBar1ActionPerformed(evt);
            }
        });

        btLine1.setText("Line Chart");
        btLine1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLine1ActionPerformed(evt);
            }
        });

        tbSumReaderNew.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tbSumReaderNew);

        lbSumBook.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbSumBook.setText("Statistics of books borrowed in year");

        lbSumReader.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbSumReader.setText("Statistics of members in year");

        btLine2.setText("Line Chart");
        btLine2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLine2ActionPerformed(evt);
            }
        });

        btBar2.setText("Bar Chart");
        btBar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBar2ActionPerformed(evt);
            }
        });

        btArea2.setText("Area Chart");
        btArea2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btArea2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(btArea1)
                        .addGap(29, 29, 29)
                        .addComponent(btBar1)
                        .addGap(27, 27, 27)
                        .addComponent(btLine1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(lbSumBook)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btArea2)
                .addGap(29, 29, 29)
                .addComponent(btBar2)
                .addGap(27, 27, 27)
                .addComponent(btLine2)
                .addGap(46, 46, 46))
            .addGroup(layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(lbSumReader)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btArea1)
                    .addComponent(btBar1)
                    .addComponent(btLine1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbSumBook)
                .addGap(13, 13, 13)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btLine2)
                    .addComponent(btBar2)
                    .addComponent(btArea2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbSumReader)
                .addGap(13, 13, 13))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btLine1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLine1ActionPerformed
        try {
            // TODO add your handling code here:
            JDBCCategoryDataset dataset = new JDBCCategoryDataset(da.getConnection(), sql0);
            JFreeChart chart = ChartFactory.createLineChart3D("Sum Brought Books", "Month", "Count", dataset, PlotOrientation.VERTICAL, false, true, true);
            BarRenderer renderer = null;
            CategoryPlot plot = null;
            renderer = new BarRenderer();
            ChartFrame frame = new ChartFrame("Sum Brought Books Chart", chart);
            frame.setVisible(true);
            frame.setSize(1250, 700);
            frame.setResizable(false);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_btLine1ActionPerformed

    private void btLine2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLine2ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            JDBCCategoryDataset dataset = new JDBCCategoryDataset(da.getConnection(), sql1);
            JFreeChart chart = ChartFactory.createLineChart3D("Sum New Readers", "Month", "Count", dataset, PlotOrientation.VERTICAL, false, true, true);
            BarRenderer renderer = null;
            CategoryPlot plot = null;
            renderer = new BarRenderer();
            ChartFrame frame = new ChartFrame("Sum New Readers Chart", chart);
            frame.setVisible(true);
            frame.setSize(1250, 700);
            frame.setResizable(false);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_btLine2ActionPerformed

    private void btArea1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btArea1ActionPerformed
        try {
            // TODO add your handling code here:
            JDBCCategoryDataset dataset = new JDBCCategoryDataset(da.getConnection(), sql0);
            JFreeChart chart = ChartFactory.createAreaChart("Sum Brought Books", "Month", "Count", dataset, PlotOrientation.VERTICAL, false, true, true);
            BarRenderer renderer = null;
            CategoryPlot plot = null;
            renderer = new BarRenderer();
            ChartFrame frame = new ChartFrame("Sum Brought Books Chart", chart);
            frame.setVisible(true);
            frame.setSize(1250, 700);
            frame.setResizable(false);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_btArea1ActionPerformed

    private void btBar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBar1ActionPerformed
        try {
            // TODO add your handling code here:
            JDBCCategoryDataset dataset = new JDBCCategoryDataset(da.getConnection(), sql0);
            JFreeChart chart = ChartFactory.createBarChart3D("Sum Brought Books", "Month", "Count", dataset, PlotOrientation.VERTICAL, false, true, true);
            BarRenderer renderer = null;
            CategoryPlot plot = null;
            renderer = new BarRenderer();
            ChartFrame frame = new ChartFrame("Sum Brought Books Chart", chart);
            frame.setVisible(true);
            frame.setSize(1250, 700);
            frame.setResizable(false);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_btBar1ActionPerformed

    private void btArea2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btArea2ActionPerformed
        try {
            // TODO add your handling code here:
            JDBCCategoryDataset dataset = new JDBCCategoryDataset(da.getConnection(), sql1);
            JFreeChart chart = ChartFactory.createAreaChart("Sum New Readers", "Month", "Count", dataset, PlotOrientation.VERTICAL, false, true, true);
            BarRenderer renderer = null;
            CategoryPlot plot = null;
            renderer = new BarRenderer();
            ChartFrame frame = new ChartFrame("Sum New Readers Chart", chart);
            frame.setVisible(true);
            frame.setSize(1250, 700);
            frame.setResizable(false);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_btArea2ActionPerformed

    private void btBar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBar2ActionPerformed
        try {
            // TODO add your handling code here:
            JDBCCategoryDataset dataset = new JDBCCategoryDataset(da.getConnection(), sql1);
            JFreeChart chart = ChartFactory.createBarChart3D("Sum New Readers", "Month", "Count", dataset, PlotOrientation.VERTICAL, false, true, true);
            BarRenderer renderer = null;
            CategoryPlot plot = null;
            renderer = new BarRenderer();
            ChartFrame frame = new ChartFrame("Sum New Readers Chart", chart);
            frame.setVisible(true);
            frame.setSize(1250, 700);
            frame.setResizable(false);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_btBar2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChartGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChartGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChartGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChartGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChartGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btArea1;
    private javax.swing.JButton btArea2;
    private javax.swing.JButton btBar1;
    private javax.swing.JButton btBar2;
    private javax.swing.JButton btLine1;
    private javax.swing.JButton btLine2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbSumBook;
    private javax.swing.JLabel lbSumReader;
    private javax.swing.JTable tbSumBookBorrowed;
    private javax.swing.JTable tbSumReaderNew;
    // End of variables declaration//GEN-END:variables
}
