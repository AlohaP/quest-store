package queststore.model;

public class UserModel {

    private int ID;
    private static int LastID = 0; 
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String status;


    public UserModel(String firstName, String lastName, String email, String password, String status) {
            this.ID = LastID;
            LastID += 1;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.password = password;
            this.status = status;    
    } 
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public String getEmail() {
        return this.email;
    }
    public String getPassword() {
        return this.password;
    }
    public String getStatus() {
        return this.status;
    }
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
    public String toString(){
        return this.firstName + " " + this.lastName + " " + this.email + " " + this.password + " " + this.status;
    }
    public Integer getID() {
        return this.ID;
    }
    public void setName(String name) {
        this.firstName = name;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
