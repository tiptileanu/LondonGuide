package mic.alliwanna.be.londonguide;



public class User extends LoginScreen {

    String email, password;


    public User(String email, String password) {
        this.email = email;
        this.password = password;

    }

    public User(){}

    // getter and setter for username and password

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}