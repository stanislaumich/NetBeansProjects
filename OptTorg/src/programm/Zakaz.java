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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Zakaz extends javax.swing.JFrame {

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
            String sql = "CREATE TABLE IF NOT EXISTS ZAKAZ("
                    + " ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + " TOVID            TEXT    NOT NULL, "
                    + " NUM           INTEGER, "
                    + " SKLADID           TEXT, "
                    + " DT           TEXT, "
                    + " ZAKID           TEXT, STATUS TEXT)";
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
            String sql = "select count(*) C from ZAKAZ P, TOVAR T, SKLAD S, ZAK Z WHERE "
                    + "P.ZAKID = Z.ID and P.SKLADID = S.ID and P.TOVID = T.ID";
            ResultSet rs = stmt.executeQuery(sql);
            int rowcount = rs.getInt("C");
            sql = "select t.id id, T.Name Name, p.num num, s.name sklad, p.dt dtt, z.name zname, p.status w, p.id zid from ZAKAZ P, TOVAR T, SKLAD S, ZAK Z "
                    + "WHERE P.ZAKID = Z.ID and P.SKLADID = S.ID and P.TOVID = T.ID";
            rs = stmt.executeQuery(sql);
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(rowcount);
            int i = 0;
            while (rs.next()) {
                jTable1.setValueAt(rs.getString("NAME"), i, 0);
                jTable1.setValueAt(rs.getString("NUM"), i, 1);
                jTable1.setValueAt(rs.getString("SKLAD"), i, 2);
                jTable1.setValueAt(rs.getString("DTT"), i, 3);
                jTable1.setValueAt(rs.getString("ZNAME"), i, 4);
                jTable1.setValueAt(rs.getString("W"), i, 5);
                jTable1.setValueAt(rs.getString("ID"), i, 6);
                jTable1.setValueAt(rs.getString("zID"), i, 7);
                i++;
            }
//++++++++++++
            jComboBox1.removeAllItems();
            sql = "select * from ZAK";
            rs = stmt.executeQuery(sql);
            i = 0;
            while (rs.next()) {
                jComboBox1.addItem(rs.getString("NAME"));
                i++;
            }
            stmt.close();

            //++++++++++++
            jComboBox3.removeAllItems();
            sql = "select * from SKLAD";
            rs = stmt.executeQuery(sql);
            i = 0;
            while (rs.next()) {
                jComboBox3.addItem(rs.getString("NAME"));
                i++;
            }
            stmt.close();

            //++++++++++++
            jComboBox2.removeAllItems();
            sql = "select * from TOVAR";
            rs = stmt.executeQuery(sql);
            i = 0;
            while (rs.next()) {
                jComboBox2.addItem(rs.getString("NAME"));
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

    public Zakaz() {
        initComponents();
        setTitle("Заказы");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        filltable();
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
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Товар", "Количество", "Склад", "Дата", "Покупатель", "Статус", "ID", "ZID"
            }
        ));
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

        jLabel1.setText("Заказчик:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Товар:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Количество:");

        jTextField1.setText("0");

        jLabel4.setText("Склад:");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton2.setText("Записать заказ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Удалить заказ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox3, 0, 244, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Записать заказ

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
            String sql = "select * from TOVAR where name='" + jComboBox2.getItemAt(jComboBox2.getSelectedIndex()) + "'";
            ResultSet rs = stmt.executeQuery(sql);
            int tovid = 0;
            //while (rs.next()) {
            tovid = rs.getInt("ID");
            //}
            stmt.close();

            stmt = conn.createStatement();
            sql = "select * from ZAK where name='" + jComboBox1.getItemAt(jComboBox1.getSelectedIndex()) + "'";
            rs = stmt.executeQuery(sql);
            int zakid = rs.getInt("ID");
            stmt.close();
            stmt = conn.createStatement();
            sql = "select * from SKLAD where name='" + jComboBox3.getItemAt(jComboBox3.getSelectedIndex()) + "'";
            rs = stmt.executeQuery(sql);
            int skladid = rs.getInt("ID");
            stmt.close();
            /*
            stmt = conn.createStatement();
            sql = "select num from TOVAR where name='" + jComboBox3.getItemAt(jComboBox3.getSelectedIndex()) + "' "
                    + "AND sklad='" + jComboBox3.getItemAt(jComboBox3.getSelectedIndex()) + "'";
            rs = stmt.executeQuery(sql);
            int num = rs.getInt("num");
            stmt.close();
             */
//if (num > Byte.valueOf(jTextField1.getText())) {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            String strDate = formatter.format(date);
            stmt = conn.createStatement();
            sql = "INSERT INTO ZAKAZ ("
                    + "ID, "
                    + "TOVID, "
                    + "NUM, "
                    + "SKLADID, "
                    + "DT, "
                    + "ZAKID, "
                    + "STATUS)"
                    + "VALUES ("
                    + "NULL, "
                    + tovid + ", '"
                    + jTextField1.getText() + "', "
                    + skladid + ", '"
                    + strDate + "', "
                    + zakid + ", "
                    + "'Заказан')";
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
        // Удалить заказ
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
            String sql = "DELETE from ZAKAZ where ID=" + jTable1.getValueAt(jTable1.getSelectedRow(), 7).toString() + "";
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
            java.util.logging.Logger.getLogger(Zakaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Zakaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Zakaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Zakaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Zakaz().setVisible(true);
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
