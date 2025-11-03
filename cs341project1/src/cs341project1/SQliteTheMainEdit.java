package cs341project1;

import Storage.DB;
import WindowsPack.LoginFrame;

public class TheMain {

    public static void main(String[] args) {
        try {
            // Ensure DB exists + tables are created
            DB.getInstance().initialize();
        } catch (Exception e) {
            e.printStackTrace();
            // If DB initialization fails, we'll still continue but the app won't persist
        }

        javax.swing.SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}
