package dao;

import model.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private Connection connect() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/talentpool", "root", "srilekha@9514");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    // ✅ Add Student
    public void addStudent(Student s) {
        String sql = "INSERT INTO student(name, rollNo, branch, year, cgpa, skills) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getRollNo());
            ps.setString(3, s.getBranch());
            ps.setInt(4, s.getYear());
            ps.setDouble(5, s.getCgpa());
            ps.setString(6, s.getSkills());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ Update Student
    public void updateStudent(Student s) {
        String sql = "UPDATE student SET name=?, rollNo=?, branch=?, year=?, cgpa=?, skills=? WHERE id=?";
        try (Connection con = connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getRollNo());
            ps.setString(3, s.getBranch());
            ps.setInt(4, s.getYear());
            ps.setDouble(5, s.getCgpa());
            ps.setString(6, s.getSkills());
            ps.setInt(7, s.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ Delete Student by ID
    public void deleteStudent(int id) {
        String sql = "DELETE FROM student WHERE id=?";
        try (Connection con = connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ Retrieve all students
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM student";
        try (Connection con = connect(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Student s = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("rollNo"),
                        rs.getString("branch"),
                        rs.getInt("year"),
                        rs.getDouble("cgpa"),
                        rs.getString("skills")
                );
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ✅ Search
    public List<Student> searchStudents(String keyword) {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM student WHERE name LIKE ? OR rollNo LIKE ? OR skills LIKE ?";
        try (Connection con = connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            String key = "%" + keyword + "%";
            ps.setString(1, key);
            ps.setString(2, key);
            ps.setString(3, key);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Student s = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("rollNo"),
                        rs.getString("branch"),
                        rs.getInt("year"),
                        rs.getDouble("cgpa"),
                        rs.getString("skills")
                );
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
