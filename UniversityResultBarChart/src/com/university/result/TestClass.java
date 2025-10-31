package com.university.result;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class TestClass {

    public static void main(String[] args) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Step 1: Load JDBC driver and connect to MySQL database
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/university",
                    "root",
                    "srilekha@9514"
            );

            // Step 2: Execute SQL query to fetch student data
            stmt = con.createStatement();
            String query = "SELECT student_name, marks FROM student_result";
            rs = stmt.executeQuery(query);

            // Step 3: Create dataset for the bar chart
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            while (rs.next()) {
                String name = rs.getString("student_name");
                int marks = rs.getInt("marks");
                dataset.addValue(marks, "Marks", name);
            }

            // Step 4: Create the bar chart
            JFreeChart chart = ChartFactory.createBarChart(
                    "University Student Results",  // Chart title
                    "Student Name",                // X-axis label
                    "Marks",                       // Y-axis label
                    dataset                        // Data
            );

            // Step 5: Display the chart in a frame
            ChartFrame frame = new ChartFrame("Result Bar Chart", chart);
            frame.setSize(700, 500);
            frame.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Step 6: Close all connections
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
