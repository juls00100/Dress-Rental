package config;

import java.awt.Color;
import java.sql.*;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;


public class config {
    //Connection Method to SQLITE
public static Connection connectDB() {
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC"); // Load the SQLite JDBC driver
            con = DriverManager.getConnection("jdbc:sqlite:dressrental.db"); // Establish connection
            System.out.println("Connection Successful");
        } catch (Exception e) {
            System.out.println("Connection Failed: " + e);
        }
        return con;
    }


    public int addRecord(String sql, Object... values) {
    int rowsInserted = 0;
    try (Connection conn = config.connectDB();

    PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        // Loop through the values and set them in the prepared statement dynamically
        for (int i = 0; i < values.length; i++) {
            if (values[i] instanceof Integer) {
                pstmt.setInt(i + 1, (Integer) values[i]); // If the value is Integer
            } else if (values[i] instanceof Double) {
                pstmt.setDouble(i + 1, (Double) values[i]); // If the value is Double
            } else if (values[i] instanceof Float) {
                pstmt.setFloat(i + 1, (Float) values[i]); // If the value is Float
            } else if (values[i] instanceof Long) {
                pstmt.setLong(i + 1, (Long) values[i]); // If the value is Long
            } else if (values[i] instanceof Boolean) {
                pstmt.setBoolean(i + 1, (Boolean) values[i]); // If the value is Boolean
            } else if (values[i] instanceof java.util.Date) {
                pstmt.setDate(i + 1, new java.sql.Date(((java.util.Date) values[i]).getTime())); // If the value is Date
            } else if (values[i] instanceof java.sql.Date) {
                pstmt.setDate(i + 1, (java.sql.Date) values[i]); // If it's already a SQL Date
            } else if (values[i] instanceof java.sql.Timestamp) {
                pstmt.setTimestamp(i + 1, (java.sql.Timestamp) values[i]); // If the value is Timestamp
            } else {
                pstmt.setString(i + 1, values[i].toString()); // Default to String for other types
            }
        }

        rowsInserted = pstmt.executeUpdate();
        try (ResultSet rs = pstmt.getGeneratedKeys()) {
            if (rs.next()) rowsInserted = rs.getInt(1); 
        }
        System.out.println("Record added successfully!");
    } catch (SQLException e) {
        System.out.println("Error adding record: " + e.getMessage());
    }
    return rowsInserted;
}
    public void viewRecords(String sqlQuery, String[] columnHeaders, String[] columnNames) {
        // Check that columnHeaders and columnNames arrays are the same length
        if (columnHeaders.length != columnNames.length) {
            System.out.println("Error: Mismatch between column headers and column names.");
            return;
        }

        try (Connection conn = this.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
             ResultSet rs = pstmt.executeQuery()) {

            // Print the headers dynamically
            StringBuilder headerLine = new StringBuilder();
            headerLine.append("--------------------------------------------------------------------------------\n| ");
            for (String header : columnHeaders) {
                headerLine.append(String.format("%-20s | ", header)); // Adjust formatting as needed
            }
            headerLine.append("\n--------------------------------------------------------------------------------");

            System.out.println(headerLine.toString());

            // Print the rows dynamically based on the provided column names
            while (rs.next()) {
                StringBuilder row = new StringBuilder("| ");
                for (String colName : columnNames) {
                    String value = rs.getString(colName);
                    row.append(String.format("%-20s | ", value != null ? value : "")); // Adjust formatting
                }
                System.out.println(row.toString());
            }
            System.out.println("--------------------------------------------------------------------------------");

        } catch (SQLException e) {
            System.out.println("Error retrieving records: " + e.getMessage());
        }
    }
   
     public void updateRecord(String sql, Object... values) {
        try (Connection conn = this.connectDB(); // Use the connectDB method
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Loop through the values and set them in the prepared statement dynamically
            for (int i = 0; i < values.length; i++) {
                if (values[i] instanceof Integer) {
                    pstmt.setInt(i + 1, (Integer) values[i]); // If the value is Integer
                } else if (values[i] instanceof Double) {
                    pstmt.setDouble(i + 1, (Double) values[i]); // If the value is Double
                } else if (values[i] instanceof Float) {
                    pstmt.setFloat(i + 1, (Float) values[i]); // If the value is Float
                } else if (values[i] instanceof Long) {
                    pstmt.setLong(i + 1, (Long) values[i]); // If the value is Long
                } else if (values[i] instanceof Boolean) {
                    pstmt.setBoolean(i + 1, (Boolean) values[i]); // If the value is Boolean
                } else if (values[i] instanceof java.util.Date) {
                    pstmt.setDate(i + 1, new java.sql.Date(((java.util.Date) values[i]).getTime())); // If the value is Date
                } else if (values[i] instanceof java.sql.Date) {
                    pstmt.setDate(i + 1, (java.sql.Date) values[i]); // If it's already a SQL Date
                } else if (values[i] instanceof java.sql.Timestamp) {
                    pstmt.setTimestamp(i + 1, (java.sql.Timestamp) values[i]); // If the value is Timestamp
                } else {
                    pstmt.setString(i + 1, values[i].toString()); // Default to String for other types
                }
            }

            pstmt.executeUpdate();
            System.out.println("Record updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error updating record: " + e.getMessage());
        }
    }
     
public void deleteRecord(String sql, Object... values) {
    try (Connection conn = this.connectDB();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        for (int i = 0; i < values.length; i++) {
            if (values[i] instanceof Integer) {
                pstmt.setInt(i + 1, (Integer) values[i]);
            } else {
                pstmt.setString(i + 1, values[i].toString()); 
            }
        }

        pstmt.executeUpdate();
        System.out.println("Record deleted successfully!");
    } catch (SQLException e) {
        System.out.println("Error deleting record: " + e.getMessage());
    }
}

public java.util.List<java.util.Map<String, Object>> fetchRecords(String sqlQuery, Object... values) {
    java.util.List<java.util.Map<String, Object>> records = new java.util.ArrayList<>();

    try (Connection conn = this.connectDB();
         PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {

        for (int i = 0; i < values.length; i++) {
            pstmt.setObject(i + 1, values[i]);
        }

        ResultSet rs = pstmt.executeQuery();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (rs.next()) {
            java.util.Map<String, Object> row = new java.util.HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                row.put(metaData.getColumnName(i), rs.getObject(i));
            }
            records.add(row);
        }

    } catch (SQLException e) {
        System.out.println("Error fetching records: " + e.getMessage());
    }

    return records;
}

    public boolean recordExists(String sql, String username, String password) {
        try (Connection conn = connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }
    
    public void displayData(String sql, javax.swing.JTable table) {
    try (Connection conn = connectDB();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {
        
        table.setModel(DbUtils.resultSetToTableModel(rs));
        
    } catch (SQLException e) {
        System.out.println("Error displaying data: " + e.getMessage());
    }
}
    

public static String hashPassword(String password) {
    try {
        java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(password.getBytes(java.nio.charset.StandardCharsets.UTF_8));
        
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashedBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    } catch (java.security.NoSuchAlgorithmException e) {
        System.out.println("Error hashing password: " + e.getMessage());
        return null;
    }
}

   public boolean updateData(String sql) {
    updateRecord(sql);
    return true; 
}

public boolean deleteData(String sql) {
    deleteRecord(sql);
    return true;
}

public boolean insertData(String sql) {
    addRecord(sql);
    return true;
}

    public ResultSet getData(String query) {
        try {
            Connection conn = connectDB();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        } catch (SQLException e) {
            System.out.println("Error getting data: " + e.getMessage());
            return null;
        }
    }

    public void displayData(String sql, JTable user_tbl, String s1, String s2, String s3, String s4) {
        try (Connection conn = connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, s1);
            pstmt.setString(2, s2);
            pstmt.setString(3, s3);
            pstmt.setString(4, s4);
            ResultSet rs = pstmt.executeQuery();
            user_tbl.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            System.out.println("Error in search display: " + e.getMessage());
        }
    }

public static class session {
    private static session instance;
    private int userId;
    private String userName, userEmail, userStatus, userRole;

    private session() {} 

    public static session getInstance() {
        if (instance == null) {
            instance = new session();
        }
        return instance;
    }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getUserRole() { return userRole; }
    public void setUserRole(String userRole) { this.userRole = userRole; }
    
    }    
    public void viewTable(String sql, JTable table) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = connectDB(); 
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close(); 
                System.out.println("Connection Closed Safely.");
            } catch (SQLException ex) {
                System.out.println("Closing Error: " + ex.getMessage());
            }
        }
    }
    
    public boolean executeRentalTransaction(String d_id, int u_id, double price, String rDate, String retDate, String notes, String cName, String cPhone, String tDate) {
    String checkQuery = "SELECT COUNT(*) FROM tbl_rentals WHERE d_id = ? AND ((? BETWEEN r_date AND r_return) OR (? BETWEEN r_date AND r_return) OR (r_date BETWEEN ? AND ?))";
    String insertSql = "INSERT INTO tbl_rentals (u_id, d_id, r_total, r_date, r_return, r_status, r_notes, r_cust_name, r_cust_contact, r_transaction_date) VALUES (?, ?, ?, ?, ?, 'Pending', ?, ?, ?, ?)";
    String updateSql = "UPDATE tbl_dresses SET d_status = 'Rented' WHERE d_id = ?";

    try (Connection conn = config.connectDB()) {
        if (conn == null) return false;
        conn.setAutoCommit(false); 

        try (PreparedStatement pstmtCheck = conn.prepareStatement(checkQuery);
             PreparedStatement pstmtInsert = conn.prepareStatement(insertSql);
             PreparedStatement pstmtUpdate = conn.prepareStatement(updateSql)) {

            pstmtCheck.setString(1, d_id);
            pstmtCheck.setString(2, rDate);
            pstmtCheck.setString(3, retDate);
            pstmtCheck.setString(4, rDate);
            pstmtCheck.setString(5, retDate);
            try (ResultSet rs = pstmtCheck.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    conn.rollback();
                    return false; 
                }
            }

            pstmtInsert.setInt(1, u_id);
            pstmtInsert.setString(2, d_id);
            pstmtInsert.setDouble(3, price);
            pstmtInsert.setString(4, rDate);
            pstmtInsert.setString(5, retDate);
            pstmtInsert.setString(6, notes);
            pstmtInsert.setString(7, cName);
            pstmtInsert.setString(8, cPhone);
            pstmtInsert.setString(9, tDate);
            pstmtInsert.executeUpdate();

            pstmtUpdate.setString(1, d_id);
            pstmtUpdate.executeUpdate();

            conn.commit(); 
            return true;
        } catch (SQLException e) {
            conn.rollback();
            System.out.println("Transaction Error: " + e.getMessage());
            return false;
        }
    } catch (SQLException e) {
        System.out.println("Connection Error: " + e.getMessage());
        return false;
    }
}
    public static void styleTable(JTable table) {
    table.getTableHeader().setFont(new java.awt.Font("Georgia", java.awt.Font.BOLD, 14));
    table.getTableHeader().setOpaque(true);
    table.getTableHeader().setBackground(new java.awt.Color(52, 73, 94)); 
    table.getTableHeader().setForeground(new java.awt.Color(0,0,0)); 
    
    table.setFont(new java.awt.Font("Georgia", java.awt.Font.PLAIN, 12));
    table.setRowHeight(30);
    table.setSelectionBackground(new java.awt.Color(41, 128, 185)); 
    table.setSelectionForeground(java.awt.Color.WHITE);
    
    table.setShowGrid(true);
    table.setGridColor(new java.awt.Color(230, 230, 230));
    table.setBackground(java.awt.Color.PINK);
}
    public static void printReceipt(String customer, String contact, String item, String price, String pickUp, String returnDate) {
    double amt = Double.parseDouble(price);
    double vat = amt * 0.12; 
    double subtotal = amt - vat;

    String receiptText = 
          "          TOLENTIN DRESS RENTAL          \n"
        + "  TOLENTIN, SOUTH COTABATO, PHILIPPINES   \n"
        + "          TIN: 123-456-789-000            \n"
        + "------------------------------------------\n"
        + "              SALES INVOICE               \n"
        + "------------------------------------------\n"
        + String.format(" Date: %-25s\n", new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(new java.util.Date()))
        + " Customer: " + customer + "\n"
        + " Contact:  " + contact + "\n"
        + "------------------------------------------\n"
        + " QTY  ITEM DESCRIPTION           AMOUNT   \n"
        + "------------------------------------------\n"
        + String.format(" 1    %-25s %8.2f\n", item, amt)
        + "------------------------------------------\n"
        + String.format("      SUBTOTAL:                %10.2f\n", subtotal)
        + String.format("      VAT (12%%):               %10.2f\n", vat)
        + "------------------------------------------\n"
        + String.format("      TOTAL AMOUNT:          P %8.2f\n", amt)
        + "------------------------------------------\n"
        + "\n"
        + " ######################################## \n"
        + " #          RENTAL PERIOD (DUE)         # \n"
        + " #                                      # \n"
        + String.format(" #  FROM: %-29s # \n", pickUp)
        + String.format(" #  TO  : %-29s # \n", returnDate)
        + " ######################################## \n"
        + "\n"
        + "------------------------------------------\n"
        + "      THIS SERVES AS YOUR OFFICIAL        \n"
        + "           SALES INVOICE.                 \n"
        + "    Please present this upon return.      \n"
        + "------------------------------------------\n"
        + "        THANK YOU FOR SHOPPING!           \n"
        + "__________________________________________\n"    
        
        ;

    javax.swing.JTextArea receiptArea = new javax.swing.JTextArea();
    receiptArea.setText(receiptText);
    
    receiptArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.BOLD, 9));

    try {
        boolean complete = receiptArea.print();
        if (complete) {
            javax.swing.JOptionPane.showMessageDialog(null, "Invoice Generated!");
        }
    } catch (java.awt.print.PrinterException e) {
        javax.swing.JOptionPane.showMessageDialog(null, "Printer Error: " + e.getMessage());
    }
}
    public int updateRecord(String sql) {
    int rowsUpdated = 0;
    try (Connection conn = config.connectDB(); 
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        rowsUpdated = pstmt.executeUpdate();
        System.out.println("Update Successful");
    } catch (SQLException e) {
        System.out.println("Update Error: " + e);
    }
    return rowsUpdated; }
    public int getCount(String sql) {
    int count = 0;
    try (Connection conn = connectDB(); 
         Statement stmt = conn.createStatement(); 
         ResultSet rs = stmt.executeQuery(sql)) {
        if (rs.next()) {
            count = rs.getInt(1);
        }
    } catch (SQLException e) { System.out.println(e); }
    return count;
}
public int updateRecordWithValues(String sql, Object... values) {
    int rowsUpdated = 0;
    try (Connection conn = config.connectDB(); 
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        for (int i = 0; i < values.length; i++) {
            pstmt.setObject(i + 1, values[i]);
        }
        
        rowsUpdated = pstmt.executeUpdate();
    } catch (SQLException e) {
        System.out.println("Update Error: " + e.getMessage());
    }
    return rowsUpdated; 
}
public boolean executeRentalTransaction(String d_id, int u_id, double price, String rdate, String redate, String notes, String cname, String ccontact, String tdate, String status) {
    String sql = "INSERT INTO tbl_rentals (d_id, u_id, r_total, r_date, r_return, r_notes, r_cust_name, r_cust_contact, r_transaction_date, r_status) "
               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    try (Connection conn = connectDB();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, d_id);
        pstmt.setInt(2, u_id);
        pstmt.setDouble(3, price);
        pstmt.setString(4, rdate);
        pstmt.setString(5, redate);
        pstmt.setString(6, notes);
        pstmt.setString(7, cname);
        pstmt.setString(8, ccontact);
        pstmt.setString(9, tdate);
        pstmt.setString(10, status);
        
        int rows = pstmt.executeUpdate();
        
        if (rows > 0) { String updateDress = "UPDATE tbl_dresses SET d_status = 'Rented' WHERE d_id = ?";
            try (PreparedStatement pstmtDress = conn.prepareStatement(updateDress)) {
                pstmtDress.setString(1, d_id);
                pstmtDress.executeUpdate();
            }
            return true;
        }
    } catch (SQLException e) {
        System.out.println("Rental Error: " + e.getMessage());
    }
    return false;
}
    public double getAggregate(String sql) {
        try (java.sql.Connection conn = connectDB();
             java.sql.PreparedStatement pstmt = conn.prepareStatement(sql);
             java.sql.ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) { return rs.getDouble(1); }
        } catch (java.sql.SQLException e) { System.out.println("Error: " + e.getMessage()); }
        return 0;
    }
    public static void manageHover(javax.swing.JPanel panel) {
    try {
        java.awt.Color originalColor = panel.getBackground();
        
        java.awt.Color hoverColor = new java.awt.Color(0, 51, 102); 

        java.awt.event.MouseAdapter hoverEffect = new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel.setBackground(hoverColor);
                panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                // 3. Return to the EXACT color it had before
                panel.setBackground(originalColor);
            }
        };

        panel.addMouseListener(hoverEffect);

        for (java.awt.Component comp : panel.getComponents()) {
            comp.addMouseListener(hoverEffect);
        }
    } catch (Exception e) {
        System.out.println("Hover Error: " + e.getMessage());
    }
    
}
    // Inside your config.java
public static void applyProHover(javax.swing.JPanel panel) {
    panel.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) {
            panel.setBackground(new java.awt.Color(60, 63, 65)); 
           
        }
        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {
            panel.setBackground(new java.awt.Color(45, 45, 45)); 
        }
    });
}
    public void secureLogout(javax.swing.JFrame currentFrame) {
    int confirm = javax.swing.JOptionPane.showConfirmDialog(
            currentFrame, 
            "Are you sure you want to log out?", 
            "Logout Confirmation", 
            javax.swing.JOptionPane.YES_NO_OPTION, 
            javax.swing.JOptionPane.QUESTION_MESSAGE
    );

    if (confirm == javax.swing.JOptionPane.YES_OPTION) {
        try {
            a_Main.landingpage land = new a_Main.landingpage();
            land.setVisible(true);

            currentFrame.dispose();
            
            System.out.println("Logout Clean: Frame disposed.");
        } catch (Exception e) {
            System.out.println("Logout Error: " + e.getMessage());
            currentFrame.dispose();
        } finally {
            System.gc();
        }
        
        
    }
}
    public boolean duplicateCheck(String sql) {
    try (Connection conn = connectDB();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
            
        return rs.next(); 
        
    } catch (SQLException e) {
        System.out.println("Duplicate Check Error: " + e.getMessage());
        return false;
    }
}
    public boolean insertRecord(String sql, Object... values) {
    try (Connection conn = connectDB();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        for (int i = 0; i < values.length; i++) {
            pstmt.setObject(i + 1, values[i]);
        }

        int rowsAffected = pstmt.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        System.out.println("Insert Error: " + e.getMessage());
        return false;
    }
}
}