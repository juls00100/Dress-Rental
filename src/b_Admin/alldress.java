/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b_Admin;

import a_Main.landingpage;
import a_Main.login;
import c_Staff.rentprocess;
import config.config;
import config.session;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author juls
 */
public class alldress extends javax.swing.JFrame {

private String currentMode = "Available";

public alldress(String mode) {
    this.currentMode = mode; // Save the mode sent from the menu
    initComponents();
    javax.swing.JPanel[] nav = {home, managedress, managerent, managepay, manageusers, logout};
    
    for (javax.swing.JPanel p : nav) {
        // 1. CLEAR: This removes the "sticking" by killing the NetBeans events
        for (java.awt.event.MouseListener ml : p.getMouseListeners()) {
            p.removeMouseListener(ml);
        }
        
        // 2. APPLY: Now your existing method will work perfectly
        config.manageHover(p);
        
        // 3. REFRESH: Ensures the UI acknowledges the color change
        p.setOpaque(true);
    
    }
    
    
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
    conf.manageHover(home);
    conf.manageHover(managedress);
    conf.manageHover(managerent);
    conf.manageHover(manageusers);
    conf.manageHover(logout1);
    conf.manageHover(logout);
    conf.manageHover(managepay);


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
    try {
        config conf = new config();
        // Clear the panel first so it doesn't duplicate when refreshing
        dressContainerPanel.removeAll();
        
        // SQL pulls Name, Price, Size, and Image path based on the mode (Available/Rented)
        String sql = "SELECT d_name, d_price, d_size, d_image FROM tbl_dresses WHERE d_status = '" + currentMode + "'";
        ResultSet rs = conf.getData(sql);

        while (rs.next()) {
            // 1. Create the Main Card Panel
            JPanel card = new JPanel();
            card.setLayout(new javax.swing.BoxLayout(card, javax.swing.BoxLayout.Y_AXIS));
            card.setPreferredSize(new Dimension(180, 240)); // Made taller for the Size label
            card.setBackground(Color.WHITE);
            card.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

            // 2. Handle the Image
            String imagePath = rs.getString("d_image");
            JLabel imageLabel = new JLabel();
            imageLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            
            try {
                ImageIcon rawIcon = new ImageIcon(imagePath);
                // Resize image to fit the top of the card (160x140)
                Image scaledImg = rawIcon.getImage().getScaledInstance(160, 140, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaledImg));
            } catch (Exception e) {
                imageLabel.setText("No Image"); // Backup if path is broken
            }

            // 3. Create Information Labels
            JLabel nameLabel = new JLabel(rs.getString("d_name"));
            nameLabel.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 14));
            nameLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

            // Here's your d_size and d_price logic
            JLabel detailsLabel = new JLabel("Size: " + rs.getString("d_size") + " | P" + rs.getString("d_price"));
            detailsLabel.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
            detailsLabel.setForeground(Color.GRAY);
            detailsLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

            // 4. Assemble the Card
            card.add(imageLabel);
            card.add(Box.createVerticalStrut(10)); // Gap between image and name
            card.add(nameLabel);
            card.add(Box.createVerticalStrut(5));  // Gap between name and details
            card.add(detailsLabel);

            // 5. Add to the Scrollable Container
            dressContainerPanel.add(card);
        }
        rs.close();

        // 6. Final UI Refresh
        dressContainerPanel.revalidate();
        dressContainerPanel.repaint();
        
    } catch (Exception ex) {
        System.out.println("Error Loading Dresses: " + ex.getMessage());
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
        jPanel10 = new javax.swing.JPanel();
        managepay = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
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
        logout = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        dressContainerPanel = new javax.swing.JPanel();
        logout1 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 209, 220));
        jPanel2.setPreferredSize(new java.awt.Dimension(360, 480));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 153));
        jLabel2.setText("Admin Dashboard  ");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/DressRent__6_-removebg-preview.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-160, 0, 220, 190));

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

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/payments.png"))); // NOI18N
        managepay.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        jLabel6.setFont(new java.awt.Font("Georgia", 1, 16)); // NOI18N
        jLabel6.setText("Payments");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        managepay.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jPanel10.add(managepay, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 150, 40));

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

        jLabel34.setFont(new java.awt.Font("Georgia", 1, 16)); // NOI18N
        jLabel34.setText("Rentals");
        jLabel34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel34MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel34MouseEntered(evt);
            }
        });
        managerent.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/dressRent-removebg-preview.png"))); // NOI18N
        managerent.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        jPanel10.add(managerent, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 150, 40));

        manageusers.setBackground(new java.awt.Color(255, 183, 201));
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

        jLabel4.setFont(new java.awt.Font("Georgia", 1, 16)); // NOI18N
        jLabel4.setText("Users");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        manageusers.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/users-removebg-preview.png"))); // NOI18N
        manageusers.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        jPanel10.add(manageusers, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 150, 40));

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
        managedress.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 70, -1));

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

        jPanel10.add(home, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 170, 40));

        logout.setBackground(new java.awt.Color(165, 42, 42));
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMouseClicked(evt);
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

        jPanel2.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 170, 420));

        dressContainerPanel.setBackground(new java.awt.Color(255, 209, 220));
        dressContainerPanel.setMaximumSize(new java.awt.Dimension(300, 300));
        dressContainerPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dressContainerPanelMouseClicked(evt);
            }
        });
        dressContainerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(dressContainerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 550, 260));

        logout1.setBackground(new java.awt.Color(46, 139, 8));
        logout1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logout1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logout1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logout1MouseExited(evt);
            }
        });
        logout1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Georgia", 1, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(240, 240, 240));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Add New Dress");
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });
        logout1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 170, 20));

        jPanel2.add(logout1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 380, 170, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 480));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void managepayMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managepayMouseEntered
        // managepay.setBackground(new Color (255,255,255));
    }//GEN-LAST:event_managepayMouseEntered

    private void managepayMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managepayMouseExited
        // managepay.setBackground(new Color (255,153,255));
    }//GEN-LAST:event_managepayMouseExited

    private void jLabel34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseClicked
        rentals rent = new rentals();
        rent.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel34MouseClicked

    private void jLabel34MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseEntered
       
    }//GEN-LAST:event_jLabel34MouseEntered

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

    private void jLabel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel28MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        manageuser user = new manageuser();
        user.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void manageusersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageusersMouseClicked

    }//GEN-LAST:event_manageusersMouseClicked

    private void manageusersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageusersMouseEntered

    }//GEN-LAST:event_manageusersMouseEntered

    private void manageusersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageusersMouseExited

    }//GEN-LAST:event_manageusersMouseExited

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
        admin_dash dash = new admin_dash();
        dash.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_dashboardMouseClicked

    private void homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMouseClicked
        admin_dash dash = new admin_dash();
        dash.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_homeMouseClicked

    private void homeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMouseEntered
        //  home.setBackground(new Color (255,255,255));
    }//GEN-LAST:event_homeMouseEntered

    private void homeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMouseExited
        //home.setBackground(new Color (255,153,255));
    }//GEN-LAST:event_homeMouseExited

    private void dressContainerPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dressContainerPanelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_dressContainerPanelMouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        payments pay = new payments();
        pay.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        managedress manage = new managedress();
    manage.setVisible(true);
    this.dispose(); 
    }//GEN-LAST:event_jLabel18MouseClicked

    private void logout1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logout1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_logout1MouseClicked

    private void logout1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logout1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_logout1MouseEntered

    private void logout1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logout1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_logout1MouseExited

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel7MouseClicked

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
        admin_dash dash = new admin_dash();
        dash.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logoutMouseClicked

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new alldress().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dashboard;
    private javax.swing.JPanel dressContainerPanel;
    private javax.swing.JPanel home;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel logout;
    private javax.swing.JPanel logout1;
    private javax.swing.JPanel managedress;
    private javax.swing.JPanel managepay;
    private javax.swing.JPanel managerent;
    private javax.swing.JPanel manageusers;
    // End of variables declaration//GEN-END:variables
}
