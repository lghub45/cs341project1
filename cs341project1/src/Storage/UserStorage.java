package Storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.security.MessageDigest;

/**
 * SQLite-backed user storage.
 * - register(username, password)
 * - login(username, password)
 *
 * Note: Uses SHA-256 hashing here for simplicity. Replace with bcrypt/jBCrypt
 * if you want stronger password hashing (recommended).
 */
public class UserStorage {

    private static final String CHECK_USER_SQL = "SELECT id FROM users WHERE username = ?";
    private static final String INSERT_USER_SQL = "INSERT INTO users(username, password_hash) VALUES(?, ?)";
    private static final String SELECT_PASSWORD_SQL = "SELECT password_hash FROM users WHERE username = ?";
    
    public static boolean register(String username, String password) {
        try (Connection conn = DB.getInstance().getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(CHECK_USER_SQL)) {
                ps.setString(1, username);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) return false; // already exists
                }
            }
            try (PreparedStatement ps = conn.prepareStatement(INSERT_USER_SQL)) {
                ps.setString(1, username);
                ps.setString(2, sha256(password));
                ps.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean login(String username, String password) {
        try (Connection conn = DB.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_PASSWORD_SQL)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String hash = rs.getString("password_hash");
                    return hash.equals(sha256(password));
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //this is used for the sendwindow
    public static String findUserPass(String username) {
    	 try (Connection conn = DB.getInstance().getConnection();
                 PreparedStatement ps = conn.prepareStatement(SELECT_PASSWORD_SQL)) {
                ps.setString(1, username);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String hash = rs.getString("password_hash");
                        return hash;
                    } else {
                        return "errorA passwords dont match";
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "errorb catch activated";
            }
    }
    
    
    
    // Simple SHA-256 hash helper
    public static String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] out = md.digest(input.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : out) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
