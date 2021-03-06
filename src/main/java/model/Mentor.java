package model;

public class Mentor extends User {

    private String userCategory = "Mentor";
    private int groupID;

    public Mentor(String login, String password,
                  String name, String surname, String email){
        super(login, password, name, surname, email);
    }

    public Mentor(String login, String password,
                  String name, String surname, String email, int groupID){
        super(login, password, name, surname, email);
        this.groupID = groupID;
    }

    public Mentor(int ID, String login, String password,
                  String name, String surname, String email){
        super(ID, login, password, name, surname, email);
    }

    public String getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(String userCategory) {
        this.userCategory = userCategory;
    }

    public int getGroupID() {
        return groupID;
    }
}
