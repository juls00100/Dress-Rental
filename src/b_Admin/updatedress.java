package b_Admin;


import c_Staff.dresstable;
import c_Staff.dresstable;
import config.config;
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 63936
 */
public class updatedress extends javax.swing.JFrame {

    private String selectedImagePath = null;
    private int dressId = 0;
    private String currentImagePath = null;

    
    public updatedress() {
        initComponents();
    }
// Set the dress ID for updating
public void setDressId(int id) {
    this.dressId = id;
}

// Set the name field
public void setName(String name) {
    txtName.setText(name);
}

// Set the description field
public void setDescription(String description) {
    txtDescription.setText(description);
}

// Set the size combobox
public void setSize(String size) {
    txtSize.setSelectedItem(size);
}

// Set the price field
public void setPrice(double price) {
    txtPrice.setText(String.valueOf(price));
}

// Set the image preview and store path
public void setImagePath(String path) {
    if (path != null && !path.isEmpty()) {
        this.currentImagePath = path;
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage().getScaledInstance(
            lblImagePreview.getWidth(),
            lblImagePreview.getHeight(),
            Image.SCALE_SMOOTH
        );
        lblImagePreview.setIcon(new ImageIcon(img));
    }
}

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtName = new javax.swing.JTextField();
        txtDescription = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtSize = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        btnChooseImage = new javax.swing.JButton();
        lblImagePreview = new javax.swing.JLabel();
        dressupdate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });
        jPanel2.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 210, 50));

        txtDescription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescriptionActionPerformed(evt);
            }
        });
        jPanel2.add(txtDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 210, 50));
        jPanel2.add(txtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, 100, 40));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setText("Dress Name:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, 50));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setText("Description:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, 50));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel8.setText("Size:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, -1, 40));

        txtSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "XS", "S", "M", "L", "XL", "XXL" }));
        jPanel2.add(txtSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 220, 100, 40));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel11.setText("Price:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, -1, 40));

        btnChooseImage.setText("Add Image");
        btnChooseImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseImageActionPerformed(evt);
            }
        });
        jPanel2.add(btnChooseImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 280, 140, -1));

        lblImagePreview.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/home-removebg-preview.png"))); // NOI18N
        jPanel2.add(lblImagePreview, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, 180, 160));

        dressupdate.setBackground(new java.awt.Color(255, 204, 255));
        dressupdate.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        dressupdate.setText("Update Dress");
        dressupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dressupdateActionPerformed(evt);
            }
        });
        jPanel2.add(dressupdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 350, 190, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 560, 400));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 470));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void txtDescriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescriptionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescriptionActionPerformed

    private void btnChooseImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseImageActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Dress Image");

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Image Files", "jpg", "png", "jpeg", "gif");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            // Resize and display in JLabel
            ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());
            Image img = icon.getImage().getScaledInstance(
                lblImagePreview.getWidth(),
                lblImagePreview.getHeight(),
                Image.SCALE_SMOOTH
            );
            lblImagePreview.setIcon(new ImageIcon(img));

            // STORE THE PATH
            selectedImagePath = selectedFile.getAbsolutePath();
            System.out.println("Selected image path: " + selectedImagePath);
        }
    }//GEN-LAST:event_btnChooseImageActionPerformed

    private void dressupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dressupdateActionPerformed
       String name = txtName.getText().trim();
String description = txtDescription.getText().trim();
String size = (String) txtSize.getSelectedItem();
String priceText = txtPrice.getText().trim();
String imagePath = selectedImagePath;

// Basic validation
if (name.isEmpty() || description.isEmpty() || size == null || priceText.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Please fill in all required fields!");
    return;
}

double price;
try {
    price = Double.parseDouble(priceText);
} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(this, "Invalid price. Please enter a numeric value.");
    return;
}

// If user selected a NEW image → copy it
if (imagePath != null && !imagePath.isEmpty()) {
    try {
        File srcFile = new File(imagePath);
        File destDir = new File("images");
        if (!destDir.exists()) destDir.mkdirs();

        File destFile = new File(destDir, srcFile.getName());
        java.nio.file.Files.copy(
            srcFile.toPath(),
            destFile.toPath(),
            java.nio.file.StandardCopyOption.REPLACE_EXISTING
        );

        imagePath = "images/" + srcFile.getName();

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Failed to save image: " + ex.getMessage());
        return;
    }
}

// UPDATE query
String sql = "UPDATE dress_tbl SET "
        + "dress_name = ?, "
        + "dress_description = ?, "
        + "dress_size = ?, "
        + "dress_price = ?, "
        + "dress_image = ? "
        + "WHERE dress_id = ?";

try (Connection conn = config.connectDB();
     PreparedStatement pst = conn.prepareStatement(sql)) {

    pst.setString(1, name);
    pst.setString(2, description);
    pst.setString(3, size);
    pst.setDouble(4, price);

    // If no new image selected → keep old one
    if (imagePath == null || imagePath.isEmpty()) {
        pst.setString(5, currentImagePath); // ← store original image path here
    } else {
        pst.setString(5, imagePath);
    }

    pst.setInt(6, dressId); // ← IMPORTANT: ID of dress being updated

    int updated = pst.executeUpdate();
    if (updated > 0) {
        JOptionPane.showMessageDialog(this, "Dress updated successfully!");
    } else {
        JOptionPane.showMessageDialog(this, "Update failed.");
    }
 
    dresstable table = new dresstable();
    table.setVisible(true);
    this.dispose();
    
} catch (SQLException ex) {
    ex.printStackTrace();
    JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
}
    }//GEN-LAST:event_dressupdateActionPerformed

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
            java.util.logging.Logger.getLogger(updatedress.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(updatedress.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(updatedress.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(updatedress.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new updatedress().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChooseImage;
    private javax.swing.JButton dressupdate;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblImagePreview;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JComboBox<String> txtSize;
    // End of variables declaration//GEN-END:variables
}
