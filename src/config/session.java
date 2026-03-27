package config;

public class session {
    private static session instance;
    
    // 1. ALL USER DATA MUST BE STATIC TO PERSIST ACROSS WINDOWS
    private static int userId; 
    private static String userName;
    private static String userStatus;
    private static String userEmail;
    private static String userRole;
    private static String userContact;
    private static String userAddress;
    private static String userImage;
    
    // Dress data stays non-static as it changes per transaction
    private String d_id, d_name, d_image;
    private double d_price;

    private session(){}
     
    public static session getInstance(){
        if (instance == null){
            instance = new session();
        }
        return instance;
    }
 public static int getUserId() { return userId; }
    public static void setUserId(int aUserId) { userId = aUserId; }
    
    public static void setUserImage(String image) { userImage = image; }
    public static String getUserImage() { return userImage; }

    public static void setUserName(String name) { userName = name; }
    public static String getUserName() { return userName; }

    public static void setUserStatus(String status) { userStatus = status; }
    public static String getUserStatus() { return userStatus; }

    public static void setUserEmail(String email) { userEmail = email; }
    public static String getUserEmail() { return userEmail; }

    public static void setUserRole(String role) { userRole = role; }
    public static String getUserRole() { return userRole; }

    public static void setUserContact(String contact) { userContact = contact; }
    public static String getUserContact() { return userContact; }
    
    public static void setUserAddress(String address) { userAddress = address; }
    public static String getUserAddress() { return userAddress; }
    
    // Non-static dress details
    public void setDressId(String id) { this.d_id = id; }
    public String getDressId() { return d_id; }
    public void setDressName(String name) { this.d_name = name; }
    public String getDressName() { return d_name; }
    public void setDressPrice(double price) { this.d_price = price; }
    public double getDressPrice() { return d_price; }
    public void setDressImage(String image) { this.d_image = image; }
    public String getDressImage() { return d_image; }
}