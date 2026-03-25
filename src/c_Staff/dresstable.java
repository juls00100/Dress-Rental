package c_Staff;


import b_Admin.updatedress;
import b_Admin.transtable;
import a_Main.login;
import a_Main.landingpage;
import b_Admin.admin_dash;
import b_Admin.manageuser;
import config.config;
import config.session;
import java.awt.Color;
import static java.nio.file.Files.size;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 63936
 */
public class dresstable extends javax.swing.JFrame {

    /**
     * Creates new form dresstable
     */
   public dresstable() {
    initComponents();
    displayDress(); // Load table on startup
}

    void displayDress(){
    config conf = new config();
    String sql = "SELECT dress_id, dress_name, dress_description, dress_size, dress_price, dress_image FROM dress_tbl";
    conf.displayData(sql, dresstable);
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        managepay = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        logout = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        managerent = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        manageusers = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        managedress = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        home = new javax.swing.JPanel();
        dashboard = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dresstable = new javax.swing.JTable();
        edit = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        search = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 204, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/DressRent__6_-removebg-preview.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-160, 0, 220, 190));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 153));
        jLabel2.setText("Online Dress Rental  |  Manage Customers");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, 20));

        jPanel4.setBackground(new java.awt.Color(255, 102, 153));
        jPanel4.setMaximumSize(new java.awt.Dimension(32762, 32767));
        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 760, 10));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 60));

        jPanel10.setBackground(new java.awt.Color(255, 204, 255));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        managepay.setBackground(new java.awt.Color(255, 153, 255));
        managepay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                managepayMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                managepayMouseExited(evt);
            }
        });
        managepay.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setText("Manage Payments");
        managepay.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/payments.png"))); // NOI18N
        managepay.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        jPanel10.add(managepay, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 170, 40));

        logout.setBackground(new java.awt.Color(255, 153, 255));
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutMouseExited(evt);
            }
        });
        logout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel7.setText("Logout");
        logout.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logout-removebg-preview (1).png"))); // NOI18N
        logout.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel10.add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 170, 40));

        managerent.setBackground(new java.awt.Color(255, 153, 255));
        managerent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                managerentMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                managerentMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                managerentMouseExited(evt);
            }
        });
        managerent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel34.setText("Manage Rentals");
        managerent.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/dressRent-removebg-preview.png"))); // NOI18N
        managerent.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        jPanel10.add(managerent, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 170, 40));

        manageusers.setBackground(new java.awt.Color(255, 255, 255));
        manageusers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                manageusersMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                manageusersMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                manageusersMouseExited(evt);
            }
        });
        manageusers.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel28.setText("Manage Rentals");
        jLabel28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel28MouseClicked(evt);
            }
        });
        manageusers.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel4.setText("Managements");
        manageusers.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/users-removebg-preview.png"))); // NOI18N
        manageusers.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        jPanel10.add(manageusers, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 190, 40));

        managedress.setBackground(new java.awt.Color(255, 153, 255));
        managedress.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                managedressMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                managedressMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                managedressMouseExited(evt);
            }
        });
        managedress.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel10.setText("Add Dresses");
        managedress.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/manangedress-removebg-preview.png"))); // NOI18N
        managedress.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        jPanel10.add(managedress, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 170, 40));

        home.setBackground(new java.awt.Color(255, 153, 255));
        home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                homeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                homeMouseExited(evt);
            }
        });
        home.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dashboard.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        dashboard.setText("Dashboard");
        dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboardMouseClicked(evt);
            }
        });
        home.add(dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/home-removebg-preview.png"))); // NOI18N
        home.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel10.add(home, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 170, 40));

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 200, 420));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dresstable.setBackground(new java.awt.Color(255, 204, 255));
        dresstable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(dresstable);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 480, 270));

        edit.setBackground(new java.awt.Color(255, 153, 153));
        edit.setText("EDIT");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        jPanel3.add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 65, -1));

        jButton3.setBackground(new java.awt.Color(255, 153, 153));
        jButton3.setText("DELETE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, -1, -1));

        jButton4.setBackground(new java.awt.Color(255, 153, 153));
        jButton4.setText("REFRESH");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, -1, -1));

        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchKeyTyped(evt);
            }
        });
        jPanel3.add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 160, 40));

        jLabel3.setText("Search");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, -1, 40));

        jButton1.setBackground(new java.awt.Color(255, 153, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton1.setText("Manage Accounts");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 350, 180, 40));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 560, 420));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 480));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void managepayMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managepayMouseEntered
        managepay.setBackground(new Color (255,255,255));
    }//GEN-LAST:event_managepayMouseEntered

    private void managepayMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managepayMouseExited
        managepay.setBackground(new Color (255,153,255));
    }//GEN-LAST:event_managepayMouseExited

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
        landingpage land = new landingpage();
        land.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logoutMouseClicked

    private void logoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseEntered
        logout.setBackground(new Color (255,255,255));
    }//GEN-LAST:event_logoutMouseEntered

    private void logoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseExited
        logout.setBackground(new Color (255,153,255));
    }//GEN-LAST:event_logoutMouseExited

    private void managerentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managerentMouseClicked
        transtable table = new transtable();
        table.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_managerentMouseClicked

    private void managerentMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managerentMouseEntered
        managerent.setBackground(new Color (255,255,255));
    }//GEN-LAST:event_managerentMouseEntered

    private void managerentMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managerentMouseExited
        managerent.setBackground(new Color (255,153,255));
    }//GEN-LAST:event_managerentMouseExited

    private void jLabel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel28MouseClicked

    private void manageusersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageusersMouseClicked
       
    }//GEN-LAST:event_manageusersMouseClicked

    private void manageusersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageusersMouseEntered
     
    }//GEN-LAST:event_manageusersMouseEntered

    private void manageusersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageusersMouseExited
     
    }//GEN-LAST:event_manageusersMouseExited

    private void managedressMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managedressMouseEntered
          managedress.setBackground(new Color (255,255,255));
    }//GEN-LAST:event_managedressMouseEntered

    private void managedressMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managedressMouseExited
          managedress.setBackground(new Color (255,153,255));
    }//GEN-LAST:event_managedressMouseExited

    private void dashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardMouseClicked
        JOptionPane.showMessageDialog(this, "Dresses clicked!");
    }//GEN-LAST:event_dashboardMouseClicked

    private void homeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMouseEntered
        home.setBackground(new Color (255,255,255));
    }//GEN-LAST:event_homeMouseEntered

    private void homeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMouseExited
        home.setBackground(new Color (255,153,255));
    }//GEN-LAST:event_homeMouseExited

    private void managedressMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managedressMouseClicked
       adddress dress = new adddress();
       dress.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_managedressMouseClicked

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
int row = dresstable.getSelectedRow();

if (row == -1) {
    JOptionPane.showMessageDialog(this, "Please select a row to edit!");
    return;
}

// Get data from table
int id = Integer.parseInt(dresstable.getValueAt(row, 0).toString());
String name = dresstable.getValueAt(row, 1).toString();
String description = dresstable.getValueAt(row, 2).toString();
String size = dresstable.getValueAt(row, 3).toString();
double price = Double.parseDouble(dresstable.getValueAt(row, 4).toString());
String imagePath = dresstable.getValueAt(row, 5).toString();

// Open update form WITH data
updatedress upd = new updatedress();
upd.setDressId(id);
upd.setName(name);
upd.setDescription(description);
upd.setSize(size);
upd.setPrice(price);
upd.setImagePath(imagePath);
upd.setVisible(true);


    }//GEN-LAST:event_editActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int row = dresstable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a record to delete!");
        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "Delete this record?", "Warning", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                config conf = new config();
                String id = dresstable.getValueAt(row, 0).toString();
                String sql = "DELETE FROM dress_tbl WHERE dress_id = '" + id + "'";

                if (conf.deleteData(sql)) {
                    JOptionPane.showMessageDialog(this, "Deleted Successfully!");
                    displayDress(); // Refresh the table
                }
            }

    }//GEN-LAST:event_jButton3ActionPerformed

    }
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

    }//GEN-LAST:event_jButton4ActionPerformed

    private void searchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyTyped
       String text = search.getText(); 
    
    String sql = "SELECT dress_id, dress_name, dress_description, dress_size, dress_price, dress_image FROM dress_tbl WHERE "
           + "dress_name LIKE '%" + text + "%' OR "
           + "dress_description LIKE '%" + text + "%' OR "
           + "dress_size LIKE '%" + text + "%' OR "
           + "dress_price LIKE '%" + text + "%'";

    
    try {
        config conf = new config();
        // Uses the getData method you have in your config.java
        java.sql.ResultSet rs = conf.getData(sql);
        
        if (rs != null) {
            // Update the table with matching results only
            dresstable.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
        }
    } catch (Exception e) {
        System.out.println("Search Error: " + e.getMessage());
    }

    }//GEN-LAST:event_searchKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      manageuser user = new manageuser();
      user.setVisible(true);
      this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMouseClicked
         admin_dash dash = new admin_dash();
       dash.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_homeMouseClicked

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
            java.util.logging.Logger.getLogger(dresstable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dresstable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dresstable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dresstable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                        if (session.getInstance().getUserId() == 0) {
        JOptionPane.showMessageDialog(null, "Please log in first!");
        login login = new login();
        login.setVisible(true);
        } else {
                             // User logged in → show dashboard
    dresstable dash = new dresstable();
    dash.setVisible(true);
}
                        
             
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dashboard;
    private javax.swing.JTable dresstable;
    private javax.swing.JButton edit;
    private javax.swing.JPanel home;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel logout;
    private javax.swing.JPanel managedress;
    private javax.swing.JPanel managepay;
    private javax.swing.JPanel managerent;
    private javax.swing.JPanel manageusers;
    private javax.swing.JTextField search;
    // End of variables declaration//GEN-END:variables
}
