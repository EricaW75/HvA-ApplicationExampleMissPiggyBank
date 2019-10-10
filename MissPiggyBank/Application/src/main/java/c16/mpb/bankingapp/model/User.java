package c16.mpb.bankingapp.model;


import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String password;
    private String role;
    private String emailaddress;

    public User() {
        this("", "", 0);
    }

    public User(String username, String password, int id) {
        this(username, password, id, "", "");
    }

    public User(String username, String password, int id, String role) {
        this(username, password, id, role, "");
    }

    public User(String username, String password, int id, String role, String emailaddress) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.role = role;
        this.emailaddress = emailaddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getUserId() {
        return this.id;
    }

    public void setUserId(int id) {
        this.id= id;
    }
}

