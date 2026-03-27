/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c_Staff;

import b_Admin.*;
import a_Main.landingpage;
import a_Main.login;
import config.config;
import config.session;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author juls
 */
public class alldress extends javax.swing.JFrame {

private String currentMode = "Available";
public alldress(String mode) {
    this.currentMode = mode; // Save the mode sent from the menu
    initComponents();
    
    // Change the title based on the mode
    if(currentMode.equals("Rented")){
        jLabel2.setText("Rented Dresses List");
    } else {
        jLabel2.setText("Available Dresses List");
    }
    
    session sess = session.getInstance();
    dressContainerPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 15));

    javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(dressContainerPanel);
    scrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPane.getVerticalScrollBar().setUnitIncrement(16);
    scrollPane.setBorder(null);

    jPanel2.remove(dressContainerPanel); 
    jPanel2.add(scrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 550, 400)); 
    
    jPanel2.revalidate();
    jPanel2.repaint();
    
    loadDresses();
}
      
    public alldress() {
        this("Available");
        initComponents();
        
        config conf = new config();
        conf.manageHover(logout);
        conf.manageHover(managerent);
        conf.manageHover(managepay);
        conf.manageHover(managepay1);
        conf.manageHover(managedress);
        conf.manageHover(home);


        session sess = session.getInstance();

        dressContainerPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 15));

        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(dressContainerPanel);

        scrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Makes scrolling smoother
        scrollPane.setBorder(null); // Recommended for a cleaner, flush look

        jPanel2.remove(dressContainerPanel); // Remove the small static panel
        jPanel2.add(scrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 550, 400)); 

        // 3. Refresh the UI so the logo and text stay visible
        jPanel2.revalidate();
        jPanel2.repaint();

        loadDresses();
    }

       private void loadDresses() {
        dressContainerPanel.removeAll();  

        config db = new config();
        int count = 0;

        String query;
        if (currentMode.equals("Rented")) {
            query = "SELECT d.*, r.r_return, u.full_name, u.number " +
            "FROM tbl_dresses d " +
            "JOIN tbl_rentals r ON d.d_id = r.d_id " +
            "JOIN Users u ON r.u_id = u.user_id " +
            "WHERE d.d_status = 'Rented'";
        } else {
            query = "SELECT * FROM tbl_dresses WHERE d_status = 'Available'";
        }

        try {
            // FIX 1: Use the 'query' variable instead of a hardcoded string
            ResultSet rs = db.getData(query);

            while (rs.next()) {
                count++;
                String id = rs.getString("d_id");
                String name = rs.getString("d_name");
                String price = rs.getString("d_price");
                String imagePath = rs.getString("d_image");

                JPanel card = new JPanel();
                card.setPreferredSize(new Dimension(210, 310)); 
                card.setBackground(Color.WHITE);
                card.setLayout(new java.awt.BorderLayout(5, 5));
                card.setBorder(BorderFactory.createLineBorder(new Color(255, 209, 220), 2));

                // Image handling
                JLabel imgLabel = new JLabel();
                imgLabel.setHorizontalAlignment(JLabel.CENTER);
                try {
                    ImageIcon icon = new ImageIcon(imagePath);
                    Image img = icon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
                    imgLabel.setIcon(new ImageIcon(img));
                } catch (Exception e) {
                    imgLabel.setText("No Image");
                }
                card.add(imgLabel, java.awt.BorderLayout.CENTER);

                // Name and Price Panel (North)
                JPanel infoPanel = new JPanel(new java.awt.GridLayout(2, 1));
                infoPanel.setBackground(Color.WHITE);
                JLabel nameLabel = new JLabel(name, JLabel.CENTER);
                nameLabel.setFont(new java.awt.Font("Georgia", 1, 16));
                JLabel priceLabel = new JLabel("₱" + price, JLabel.CENTER);
                priceLabel.setForeground(new Color(74, 44, 64));
                infoPanel.add(nameLabel);
                infoPanel.add(priceLabel);
                card.add(infoPanel, java.awt.BorderLayout.NORTH);

                // FIX 2: Only add the button IF we are in Available mode
                if (currentMode.equals("Rented")) {
                    String returnDate = rs.getString("r_return");
                    String customerName = rs.getString("full_name");
                    String contact = rs.getString("number");
                   JPanel rentalInfo = new JPanel(new java.awt.GridLayout(3, 1));
                    rentalInfo.setBackground(new Color(255, 240, 245)); // Light pink background

                    JLabel dateLabel = new JLabel("Return: " + returnDate, JLabel.CENTER);
                    dateLabel.setFont(new java.awt.Font("Georgia", 3, 12));
                    dateLabel.setForeground(new Color(165, 42, 42));

                    JLabel custLabel = new JLabel("By: " + customerName, JLabel.CENTER);
                    custLabel.setFont(new java.awt.Font("Georgia", 1, 12));

                    JLabel contactLabel = new JLabel("Ph: " + contact, JLabel.CENTER);
                    contactLabel.setFont(new java.awt.Font("Georgia", 0, 11));

                    rentalInfo.add(dateLabel);
                    rentalInfo.add(custLabel);
                    rentalInfo.add(contactLabel);

                    card.add(rentalInfo, java.awt.BorderLayout.SOUTH);
                } else {
                    javax.swing.JButton rentBtn = new javax.swing.JButton("Rent Now");
                    rentBtn.setBackground(new Color(74, 44, 64));
                    rentBtn.setFont(new java.awt.Font("Georgia", 1, 16));
                    rentBtn.setForeground(Color.WHITE);
                    rentBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            session sess = session.getInstance();
                            sess.setDressId(id);
                            sess.setDressName(name);
                            sess.setDressPrice(Double.parseDouble(price));
                            sess.setDressImage(imagePath);

                            rentprocess rp = new rentprocess(); 
                            rp.setVisible(true);
                            alldress.this.dispose(); 
                        }
                    });
                    card.add(rentBtn, java.awt.BorderLayout.SOUTH);
                }

                dressContainerPanel.add(card);
            }

            // Update panel height for scrolling
            int cardsPerRow = 2; // Adjusted based on your 550 panel width
            int cardHeight = 310;
            int rowGap = 15; 
            int rows = (int) Math.ceil(count / (double)cardsPerRow);
            int totalCalculatedHeight = (rows * (cardHeight + rowGap)) + 20;

            dressContainerPanel.setPreferredSize(new Dimension(500, totalCalculatedHeight));

        } catch (Exception e) {
            System.out.println("Error loading dresses: " + e.getMessage());
        }

        dressContainerPanel.revalidate();
        dressContainerPanel.repaint();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        dressContainerPanel = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        managepay = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        logout = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        managerent = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        customers = new javax.swing.JLabel();
        managedress = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        home = new javax.swing.JPanel();
        dashboard = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        managepay1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 209, 220));
        jPanel2.setPreferredSize(new java.awt.Dimension(360, 480));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 153));
        jLabel2.setText("Staff Dashboard  ");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/DressRent__6_-removebg-preview.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-160, 0, 220, 190));

        dressContainerPanel.setBackground(new java.awt.Color(255, 209, 220));
        dressContainerPanel.setMaximumSize(new java.awt.Dimension(300, 300));
        dressContainerPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dressContainerPanelMouseClicked(evt);
            }
        });
        dressContainerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(dressContainerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 550, 260));

        jPanel10.setBackground(new java.awt.Color(255, 235, 239));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        managepay.setBackground(new java.awt.Color(255, 183, 201));
        managepay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                managepayMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                managepayMouseExited(evt);
            }
        });
        managepay.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Georgia", 1, 16)); // NOI18N
        jLabel6.setText("Profile");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        managepay.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/payments.png"))); // NOI18N
        managepay.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        jPanel10.add(managepay, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 150, 40));

        logout.setBackground(new java.awt.Color(165, 42, 42));
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutMouseExited(evt);
            }
        });
        logout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Georgia", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(240, 240, 240));
        jLabel7.setText("Back");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        logout.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 9, -1, 20));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logout-removebg-preview (1).png"))); // NOI18N
        logout.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel10.add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 150, 40));

        managerent.setBackground(new java.awt.Color(255, 183, 201));
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

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/dressRent-removebg-preview.png"))); // NOI18N
        managerent.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        customers.setFont(new java.awt.Font("Georgia", 1, 16)); // NOI18N
        customers.setText("Customers");
        customers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customersMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                customersMouseEntered(evt);
            }
        });
        managerent.add(customers, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jPanel10.add(managerent, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 150, 40));

        managedress.setBackground(new java.awt.Color(255, 209, 220));
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

        jLabel10.setFont(new java.awt.Font("Georgia", 1, 16)); // NOI18N
        jLabel10.setText("Dress");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        managedress.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 60, -1));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/manangedress-removebg-preview.png"))); // NOI18N
        managedress.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        jPanel10.add(managedress, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 160, 40));

        home.setBackground(new java.awt.Color(255, 183, 201));
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

        dashboard.setFont(new java.awt.Font("Georgia", 1, 16)); // NOI18N
        dashboard.setText("Dashboard");
        dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboardMouseClicked(evt);
            }
        });
        home.add(dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 100, 40));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/home-removebg-preview.png"))); // NOI18N
        home.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel10.add(home, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 150, 40));

        managepay1.setBackground(new java.awt.Color(255, 183, 201));
        managepay1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                managepay1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                managepay1MouseExited(evt);
            }
        });
        managepay1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Georgia", 1, 15)); // NOI18N
        jLabel8.setText("Manage Rents");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        managepay1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/payments.png"))); // NOI18N
        managepay1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        jPanel10.add(managepay1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 150, 40));

        jPanel2.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 170, 420));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 480));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        userprofile pay = new userprofile();
        pay.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void managepayMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managepayMouseEntered
        // managepay.setBackground(new Color (255,255,255));
    }//GEN-LAST:event_managepayMouseEntered

    private void managepayMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managepayMouseExited
        // managepay.setBackground(new Color (255,153,255));
    }//GEN-LAST:event_managepayMouseExited

    private void logoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseEntered
        // logout.setBackground(new Color (255,255,255));
    }//GEN-LAST:event_logoutMouseEntered

    private void logoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseExited
        //logout.setBackground(new Color (255,153,255));
    }//GEN-LAST:event_logoutMouseExited

    private void managerentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managerentMouseClicked
        //transtable table = new transtable();
        // table.setVisible(true);
        //this.dispose();
    }//GEN-LAST:event_managerentMouseClicked

    private void managerentMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managerentMouseEntered
        //managerent.setBackground(new Color (255,255,255));
    }//GEN-LAST:event_managerentMouseEntered

    private void managerentMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managerentMouseExited
        // managerent.setBackground(new Color (255,153,255));
    }//GEN-LAST:event_managerentMouseExited

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        alldress dress = new alldress();
        dress.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel10MouseClicked

    private void managedressMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managedressMouseClicked
        //adddress dress = new adddress();
        //dress.setVisible(true);
        //this.dispose();
    }//GEN-LAST:event_managedressMouseClicked

    private void managedressMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managedressMouseEntered
        //managedress.setBackground(new Color (255,255,255));
    }//GEN-LAST:event_managedressMouseEntered

    private void managedressMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managedressMouseExited
        // managedress.setBackground(new Color (255,153,255));
    }//GEN-LAST:event_managedressMouseExited

    private void dashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardMouseClicked
        staff_dashboard dash = new staff_dashboard();
        dash.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_dashboardMouseClicked

    private void homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMouseClicked
        staff_dashboard dash = new staff_dashboard();
        dash.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_homeMouseClicked

    private void homeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMouseEntered
        //  home.setBackground(new Color (255,255,255));
    }//GEN-LAST:event_homeMouseEntered

    private void homeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMouseExited
        //home.setBackground(new Color (255,153,255));
    }//GEN-LAST:event_homeMouseExited

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        rentals r = new rentals();
        r.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void managepay1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managepay1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_managepay1MouseEntered

    private void managepay1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managepay1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_managepay1MouseExited

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        staff_dashboard dash = new staff_dashboard();
        dash.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void dressContainerPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dressContainerPanelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_dressContainerPanelMouseClicked

    private void customersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customersMouseClicked
        customers c = new customers();
        c.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_customersMouseClicked

    private void customersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customersMouseEntered

    }//GEN-LAST:event_customersMouseEntered

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
            java.util.logging.Logger.getLogger(alldress.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(alldress.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(alldress.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(alldress.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new alldress().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel customers;
    private javax.swing.JLabel dashboard;
    private javax.swing.JPanel dressContainerPanel;
    private javax.swing.JPanel home;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel logout;
    private javax.swing.JPanel managedress;
    private javax.swing.JPanel managepay;
    private javax.swing.JPanel managepay1;
    private javax.swing.JPanel managerent;
    // End of variables declaration//GEN-END:variables
}
