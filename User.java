public abstract class User {
    protected String name, email, contact_no, password;

    public User() {
        name = email = contact_no = password = "";
    }

    public User(String name, String email, String contact_no, String password) {
        this.name = name;
        this.email = email;
        this.contact_no = contact_no;
        this.password = password;
    }
}