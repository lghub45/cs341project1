package Storage;
import java.util.HashMap;
import java.util.List;

import TaskPack.Task;
import UserPack.User;

public class UserStorage {

    private static HashMap<String, String> users = new HashMap<>();

    public static boolean register(String username, String password) {
        if (users.containsKey(username)) return false;
        users.put(username, password);
        return true;
    }

    public static boolean login(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }
    
    //returns a password
    public static String findUserPass(String username) {
    	return users.get(username); 
    }
    
}
