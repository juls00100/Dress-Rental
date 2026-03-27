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
    try (Connection conn = config.connectDB();

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
        System.out.println("Record added successfully!");
    } catch (SQLException e) {
        System.out.println("Error adding record: " + e.getMessage());
    }
    return 0;
}
// Dynamic view method to display records from any table
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

    private session() {} // Private constructor

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
    
    public boolean executeRentalTransaction(String d_id, int u_id, double price, String rDate, String retDate, String notes, String cName, String cPhone) {
    String checkQuery = "SELECT COUNT(*) FROM tbl_rentals WHERE d_id = ? AND ((? BETWEEN r_date AND r_return) OR (? BETWEEN r_date AND r_return) OR (r_date BETWEEN ? AND ?))";
    String insertSql = "INSERT INTO tbl_rentals (u_id, d_id, r_total, r_date, r_return, r_status, r_notes, r_cust_name, r_cust_contact) VALUES (?, ?, ?, ?, ?, 'Pending', ?, ?, ?)";
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
}