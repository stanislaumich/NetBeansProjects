/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package programm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lz42
 */
public class TProda extends javax.swing.JFrame {

    String oldName = "";

    private void createtable() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load class.");
            e.printStackTrace();
        }
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:base.sqlite";
            conn = DriverManager.getConnection(url);
            Statement stmt = null;
            stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS PRODA("
                    + " ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + " TOVID            TEXT    NOT NULL, "
                    + " NUM           INTEGER, "
                    + " SKLADID           TEXT, "
                    + " DT           TEXT, "
                    + " ZAKID           TEXT"
                    + "    price   REAL, "
                    + "    summa   REAL"
                    + ")";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    private void filltable() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load class.");
            e.printStackTrace();
        }
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:base.sqlite";
            conn = DriverManager.getConnection(url);
            Statement stmt = null;
            createtable();
            stmt = conn.createStatement();
            String sql = "select count(*) C from PRODA P, TOVAR T, SKLAD S, ZAK Z WHERE "
                    + "P.ZAKID = Z.ID and P.SKLADID = S.ID and P.TOVID = T.ID";
            ResultSet rs = stmt.executeQuery(sql);
            int rowcount = rs.getInt("C");
            sql = "select T.Name Name, p.num num, s.name sklad, p.dt dt, z.name zname, p.id id, p.price price, p.summa summa from PRODA P, TOVAR T, SKLAD S, ZAK Z \n"
                    + "WHERE P.ZAKID = Z.ID and P.SKLADID = S.ID and P.TOVID = T.ID";
            rs = stmt.executeQuery(sql);
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(rowcount);
            int i = 0;
            while (rs.next()) {
                jTable1.setValueAt(rs.getString("NAME"), i, 0);
                jTable1.setValueAt(rs.getString("price"), i, 1);
                jTable1.setValueAt(rs.getString("num"), i, 2);
                jTable1.setValueAt(rs.getString("summa"), i, 3);
                jTable1.setValueAt(rs.getString("sklad"), i, 4);
                jTable1.setValueAt(rs.getString("dt"), i, 5);
                jTable1.setValueAt(rs.getString("ZNAME"), i, 6);
                jTable1.setValueAt(rs.getString("ID"), i, 7);
                i++;
            }
            //+++++++++++++
            jComboBox1.removeAllItems();
            sql = "select * from TOVAR";
            rs = stmt.executeQuery(sql);
            i = 0;
            while (rs.next()) {
                jComboBox1.addItem(rs.getString("NAME"));
                i++;
            }
            stmt.close();

            //+++++++++++++
            jComboBox2.removeAllItems();
            sql = "select * from SKLAD";
            rs = stmt.executeQuery(sql);
            i = 0;
            while (rs.next()) {
                jComboBox2.addItem(rs.getString("NAME"));
                i++;
            }
            stmt.close();

            //+++++++++++++
            jComboBox3.removeAllItems();
            sql = "select * from ZAK";
            rs = stmt.executeQuery(sql);
            i = 0;
            while (rs.next()) {
                jComboBox3.addItem(rs.getString("NAME"));
                i++;
            }
            stmt.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public TProda() {
        initComponents();
        setTitle("Продажи");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        filltable();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Товар", "Цена", "Кол-во", "Сумма", "Склад", "Дата", "Покупатель", "ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Закрыть");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Записать");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Удалить");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jComboBox1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBox1PropertyChange(evt);
            }
        });

        jLabel1.setText("Товар:");

        jLabel2.setText("Цена:");

        jTextField1.setText("0");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel3.setText("0");

        jLabel4.setText("Кол-во:");

        jLabel5.setText("Сумма:");

        jLabel6.setText("0");

        jLabel7.setText("Склад:");

        jLabel8.setText("Покупатель:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jComboBox2, 0, 302, Short.MAX_VALUE)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(0, 9, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Записать продажу
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load class.");
            e.printStackTrace();
        }

        Connection conn = null;
        try {
            String url = "jdbc:sqlite:base.sqlite";
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            String sql = "select * from TOVAR where name='" + jComboBox1.getItemAt(jComboBox1.getSelectedIndex()) + "'";
            ResultSet rs = stmt.executeQuery(sql);
            int tovid = 0;
            //while (rs.next()) {
            tovid = rs.getInt("ID");
            //}
            stmt.close();

            stmt = conn.createStatement();
            sql = "select * from ZAK where name='" + jComboBox3.getItemAt(jComboBox3.getSelectedIndex()) + "'";
            rs = stmt.executeQuery(sql);
            int zakid = rs.getInt("ID");
            stmt.close();
            stmt = conn.createStatement();
            sql = "select * from SKLAD where name='" + jComboBox2.getItemAt(jComboBox2.getSelectedIndex()) + "'";
            rs = stmt.executeQuery(sql);
            int skladid = rs.getInt("ID");
            stmt.close();
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            String strDate = formatter.format(date);
            stmt = conn.createStatement();
            sql = "INSERT INTO PRODA ("
                    + "ID, "
                    + "TOVID, "
                    + "NUM, "
                    + "SKLADID, "
                    + "DT, "
                    + "ZAKID, "
                    + "PRICE, "
                    + "summa)"
                    + "VALUES ("
                    + "NULL, "
                    + tovid + ", '"
                    + jTextField1.getText() + "', "
                    + skladid + ", '"
                    + strDate + "', "
                    + zakid + ", "
                    + jLabel3.getText() + ", "
                    + jLabel6.getText() + ")";
            stmt.executeUpdate(sql);

            sql = "update TOVAR set num = num - " + jTextField1.getText() + "  where name='" + jComboBox2.getItemAt(jComboBox2.getSelectedIndex()) + "'";
            stmt.executeUpdate(sql);
            filltable();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // Удалить продажу
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load class.");
            e.printStackTrace();
        }
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:base.sqlite";
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            String sql = "DELETE from PRODA where ID=" + jTable1.getValueAt(jTable1.getSelectedRow(), 7).toString() + "";
            stmt.executeUpdate(sql);
            filltable();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        //
        jTextField1.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
        //jTextField5.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
        //jTextField3.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 6).toString());
        //jTextField4.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 7).toString());
        oldName = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        //+++++++++++++++++ sklad

        int j = 0;
        for (int i = 0; i < jComboBox2.getItemCount(); i++) {
            //System.out.println((String)jComboBox1.getItemAt(i)); 
            if (jComboBox2.getItemAt(i).equals(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString())) {
                j = i;
                //System.out.println("."); 
            }
        }
        jComboBox2.setSelectedIndex(j);

        //+++++++++++++++++ sklad
        j = 0;
        for (int i = 0; i < jComboBox3.getItemCount(); i++) {
            //System.out.println((String)jComboBox1.getItemAt(i)); 
            if (jComboBox3.getItemAt(i).equals(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString())) {
                j = i;
                //System.out.println("."); 
            }
        }
        jComboBox3.setSelectedIndex(j);
        //+++++++++++++++++ GR

        j = 0;
        for (int i = 0; i < jComboBox1.getItemCount(); i++) {
            //System.out.println((String)jComboBox1.getItemAt(i)); 
            if (jComboBox1.getItemAt(i).equals(jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString())) {
                j = i;
                //System.out.println("."); 
            }
        }
        jComboBox1.setSelectedIndex(j);


    }//GEN-LAST:event_jTable1MouseClicked

    private void jComboBox1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBox1PropertyChange
        

    }//GEN-LAST:event_jComboBox1PropertyChange

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
       try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load class.");
            e.printStackTrace();
        }

        Connection conn = null;
        try {
            String url = "jdbc:sqlite:base.sqlite";
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            String sql = "select * from TOVAR where name='" + jComboBox1.getItemAt(jComboBox1.getSelectedIndex()) + "'";
            ResultSet rs = stmt.executeQuery(sql);

            jLabel3.setText(rs.getString("PRICE"));
            
            stmt.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        Integer i1 = new Integer(jTextField1.getText());
        Integer i2 = new Integer(jLabel3.getText());
        Integer i3 = i1*i2;
        jLabel6.setText(i3.toString());
    }//GEN-LAST:event_jTextField1ActionPerformed

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TProda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TProda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TProda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TProda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TProda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
