package Storage; //github program

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * SQLite connection manager.
 * - DB file: data/app.db (create data/ directory if missing)
 * - Initializes schema if missing
 * - Requires sqlite-jdbc-3.50.30.jar on the classpath
 */
public class DB {
    private static final String DB_FILE = "data/app.db";
    private static final String URL = "jdbc:sqlite:" + DB_FILE;

    private static DB instance;
    //private final Connection conn; 

    private DB() throws SQLException {
    	
    	File dir = new File("data");
    	if (!dir.exists()) {
    	    dir.mkdirs();
    	}
    	
    	 try {
    	        Class.forName("org.sqlite.JDBC");
    	    } catch (ClassNotFoundException e) {
    	        throw new SQLException("SQLite JDBC driver not found", e);
    	    }
    	
         Connection conn = DriverManager.getConnection(URL);
        try (Statement st = conn.createStatement()) {
            st.execute("PRAGMA foreign_keys = ON;");
        }
    }

    public static synchronized DB getInstance() throws SQLException {
        if (instance == null) {
            instance = new DB();
        }
        return instance;
    }

    //opens new connection each time
    public Connection getConnection()  throws SQLException {
        Connection conn = DriverManager.getConnection(URL);
        try (Statement st = conn.createStatement()) {
            st.execute("PRAGMA foreign_keys = ON;");
        }
        return conn;
    }

    /** Create tables if they don't exist. Safe to call multiple times. */
   public void initialize() throws SQLException {
        String usersSql = ""
                + "CREATE TABLE IF NOT EXISTS users ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "username TEXT NOT NULL UNIQUE,"
                + "password_hash TEXT NOT NULL,"
                + "created_at DATETIME DEFAULT CURRENT_TIMESTAMP"
                + ");";
        String tasksSql = ""
                + "CREATE TABLE IF NOT EXISTS tasks ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "user_id INTEGER NOT NULL,"
                + "description TEXT NOT NULL,"
                + "completed INTEGER NOT NULL DEFAULT 0,"
                + "created_at DATETIME DEFAULT CURRENT_TIMESTAMP,"
                + "FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE"
                + ");";
        try (Connection conn = getConnection();
        		Statement st = conn.createStatement()) {
            st.execute(usersSql);
            st.execute(tasksSql);
            st.execute("CREATE INDEX IF NOT EXISTS idx_tasks_user_id ON tasks(user_id);"); //makes an index if the table exists
        }
    }
}