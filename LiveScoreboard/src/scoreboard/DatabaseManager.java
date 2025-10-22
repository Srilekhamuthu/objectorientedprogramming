package scoreboard;

import java.sql.*;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/live_scoreboard";
    private static final String USER = "root";
    private static final String PASSWORD = "srilekha@9514";
    
    private Connection connection;
    
    public DatabaseManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connected successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    public ResultSet getAllMatches() {
        try {
            Statement stmt = connection.createStatement();
            return stmt.executeQuery("SELECT * FROM matches");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void updateScore(int matchId, int team1Score, int team2Score) {
        try {
            // Corrected column name 'id'
            String query = "UPDATE matches SET team1_score = ?, team2_score = ? WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, team1Score);
            pstmt.setInt(2, team2Score);
            pstmt.setInt(3, matchId);
            pstmt.executeUpdate();
            System.out.println("Score updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void closeConnection() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
