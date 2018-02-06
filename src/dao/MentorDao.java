package dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;


import model.MentorModel;

public class MentorDao extends UserDao{

    public void insertNewMentor(String mentorName, String mentorLastName, String mentorEmail, String mentorPassword) {
        LoginDao loginDao = new LoginDao();
        int idStatus = loginDao.findStatusIdByName("Mentor");
        loginDao.insertNewLogin(mentorEmail, mentorPassword, idStatus);
        int idLogin = loginDao.findLoginId(mentorEmail, mentorPassword);
        //here db connection is closed
        UserDao newDao = new UserDao();
        int id_group = 1;
        String table = "Mentor";
        String columns = "(first_name, last_name, id_login, id_status, id_group)";
        String values = "('" + mentorName + "', '" + mentorLastName + "', " + idLogin +", "+ idStatus + ", " + id_group + ");";
        newDao.insertDataIntoTable(table, columns, values);
    }

    private int getIdLogin(int id_mentor) {
        ResultSet result = selectDataFromTable("Mentor", "id_login", "id_mentor=" + id_mentor);
        return getIntFromResult(result, "id_login");
    }

    public void updateMentorData(MentorModel mentor) {
        String name = mentor.getFirstName();
        String lastName = mentor.getLastName();
        String email = mentor.getEmail();
        String password = mentor.getPassword();
        int idMentor = mentor.getID();
        int idLogin = getIdLogin(idMentor);
        updateDataInTable("Mentor", "first_name='"+name+"', last_name='"+lastName+"'", "id_mentor=" + idMentor);
        updateDataInTable("Login", "email='"+email+"', password='"+password+"'", "id_login="+idLogin);
    }

    public List<MentorModel> getAllMentorsCollection() {

        List<MentorModel> mentorCollection = new ArrayList<>();
        String columns = "email, password, Mentor.first_name, Mentor.last_name, Mentor.id_mentor";
        String joinStatement = "Mentor.id_login = Login.id_login";
        ResultSet result = selectFromJoinedTables(columns, "Login", "Mentor", joinStatement);
        try {
            while (result.next()) {
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                String email = result.getString("email");
                String password = result.getString("password");
                int id = result.getInt("id_mentor");
                MentorModel mentor = new MentorModel(id, firstName, lastName, email, password);
                mentorCollection.add(mentor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mentorCollection;
    }

}

