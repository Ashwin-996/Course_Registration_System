import java.util.ArrayList;

public abstract class Users {
    private static ArrayList<User> users = new ArrayList<User>();
    private static final String Admin_pwd = "x2@jh";

    public static <T extends User> void add_user(T _user) {
        users.add(_user);
    }

    public static <T extends User> void remove_user(T _user) {
        users.remove(_user);
    }

    public static ArrayList<User> get_users() {
        return users;
    }

    public static String get_pwd() {
        return Admin_pwd;
    }
}