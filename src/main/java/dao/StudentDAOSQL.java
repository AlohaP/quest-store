package dao;

import exceptions.DataAccessException;
import model.Group;
import model.Wallet;
import model.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOSQL extends ManipulationDAOSQL implements StudentDAO {
    private WalletDAO wdao = new WalletDAOSQL();
    private GroupDAO gdao = new GroupDAOSQL();
    private final int USER_CATEGORY_ID = 1;


    @Override
    public Student getStudentById(int id) throws DataAccessException {
        try{
            PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM user WHERE id=? AND user_category_id=?;");
            ps.setInt(1, id);
            ps.setInt(2, USER_CATEGORY_ID);
            ResultSet rs = ps.executeQuery();
            return getCodecoolerFromResultSet(rs);
        }catch (SQLException e) {
            throw new DataAccessException("Get student failed!");
        }
    }

    @Override
    public List<Student> getStudentsCollection() throws DataAccessException {
        try{
            List<Student> mentors = new ArrayList<>();
            PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM user WHERE user_category_id=?;");
            ps.setInt(1, USER_CATEGORY_ID);
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ) {
                mentors.add(getCodecoolerFromResultSet(rs));
            }
            return mentors;
        }catch (SQLException e) {
            throw new DataAccessException("Getting student collection failed!");
        }
    }

    @Override
    public void addStudent(Student student) throws DataAccessException {
        try {
            PreparedStatement ps = getConnection().prepareStatement(
                    "INSERT INTO user (login, password, name, surname, email, user_category_id) " +
                            "VALUES(?, ?, ?, ?, ?, ?)");
            ps.setString(1, student.getLogin());
            ps.setString(2, student.getPassword());
            ps.setString(3, student.getName());
            ps.setString(4, student.getSurname());
            ps.setString(5, student.getEmail());
            ps.setInt(6, USER_CATEGORY_ID);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Add student failed!");
        }
    }
    @Override
    public void deleteStudent(Student student) throws DataAccessException {
        try{
            PreparedStatement ps = getConnection().prepareStatement("DELETE FROM user WHERE id = ? AND user_category_id=?;");
            ps.setInt(1, student.getID());
            ps.executeUpdate();
        }catch (SQLException e) {
            throw new DataAccessException("Remove student failed!");
        }
    }

    @Override
    public void updateStudent(Student student) throws DataAccessException {
        try{
            PreparedStatement ps = getConnection().prepareStatement(
                    "UPDATE user SET login=?, password=?, name=?, surname=?, email=? WHERE id=?;");
            ps.setString(1, student.getLogin());
            ps.setString(2, student.getPassword());
            ps.setString(3, student.getName());
            ps.setString(4, student.getSurname());
            ps.setString(5, student.getEmail());
            ps.setInt(6, student.getID());
            ps.executeUpdate();
        }catch (SQLException e) {
            throw new DataAccessException("Update student failed!");
        }
    }

    private Student getCodecoolerFromResultSet(ResultSet rs) throws DataAccessException {
        try {
            int userId = rs.getInt("id");
            String login = rs.getString("login");
            String password = rs.getString("password");
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String email = rs.getString("email");
            Group group = gdao.getByID(userId);
            Wallet wallet = wdao.getByID(userId);
            return new Student(userId, login, password, name, surname, email, group, wallet);
        }catch (SQLException e){
            throw new DataAccessException("Get student from result set failed!");
        }
    }
}