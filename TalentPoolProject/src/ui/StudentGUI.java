package ui;

import dao.StudentDAO;
import model.Student;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentGUI extends JFrame {

    private JTextField nameField, rollField, branchField, yearField, cgpaField, skillsField, searchField, idField;
    private JTable table;
    private DefaultTableModel tableModel;
    private StudentDAO studentDAO;

    public StudentGUI() {
        studentDAO = new StudentDAO();

        setTitle("🎓 Talent Pool - Student Management System");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 🌈 Gradient background
        JPanel background = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0,
                        new Color(235, 210, 255), // lavender
                        getWidth(), getHeight(),
                        new Color(255, 210, 220)); // peach-pink
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        background.setLayout(new BorderLayout(15, 15));
        background.setBorder(new EmptyBorder(15, 15, 15, 15));
        add(background);

        // 🟪 Left Panel (Form)
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(new Color(255, 240, 250, 230));
        formPanel.setBorder(new LineBorder(new Color(200, 160, 255), 1, true));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel title = new JLabel("Talent Pool - Student Management System");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(new Color(80, 40, 120));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        background.add(title, BorderLayout.NORTH);

        int y = 0;

        gbc.gridx = 0; gbc.gridy = y; formPanel.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1; idField = new JTextField(10); formPanel.add(idField, gbc);

        gbc.gridx = 0; gbc.gridy = ++y; formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1; nameField = new JTextField(15); formPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = ++y; formPanel.add(new JLabel("Roll No:"), gbc);
        gbc.gridx = 1; rollField = new JTextField(15); formPanel.add(rollField, gbc);

        gbc.gridx = 0; gbc.gridy = ++y; formPanel.add(new JLabel("Branch:"), gbc);
        gbc.gridx = 1; branchField = new JTextField(15); formPanel.add(branchField, gbc);

        gbc.gridx = 0; gbc.gridy = ++y; formPanel.add(new JLabel("Year:"), gbc);
        gbc.gridx = 1; yearField = new JTextField(15); formPanel.add(yearField, gbc);

        gbc.gridx = 0; gbc.gridy = ++y; formPanel.add(new JLabel("CGPA:"), gbc);
        gbc.gridx = 1; cgpaField = new JTextField(15); formPanel.add(cgpaField, gbc);

        gbc.gridx = 0; gbc.gridy = ++y; formPanel.add(new JLabel("Skills:"), gbc);
        gbc.gridx = 1; skillsField = new JTextField(15); formPanel.add(skillsField, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        buttonPanel.setOpaque(false);

        JButton addBtn = createButton("➕ Add");
        JButton updateBtn = createButton("🔄 Update");
        JButton deleteBtn = createButton("🗑 Delete");
        JButton viewBtn = createButton("📋 View All");

        buttonPanel.add(addBtn);
        buttonPanel.add(viewBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);

        gbc.gridx = 0; gbc.gridy = ++y; gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);

        background.add(formPanel, BorderLayout.WEST);

        // 🩶 Table (Right Side)
        String[] cols = {"ID", "Name", "Roll No", "Branch", "Year", "CGPA", "Skills"};
        tableModel = new DefaultTableModel(cols, 0);
        table = new JTable(tableModel);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(24);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new LineBorder(new Color(200, 160, 255), 1, true));
        background.add(scrollPane, BorderLayout.CENTER);

        // 🔍 Search Bar
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        searchPanel.setOpaque(false);
        searchField = new JTextField("Search by Name / Roll No / Skill...", 30);
        JButton searchBtn = createButton("🔍 Search");

        searchPanel.add(searchField);
        searchPanel.add(searchBtn);
        background.add(searchPanel, BorderLayout.SOUTH);

        // 🧠 Button Actions
        addBtn.addActionListener(e -> addStudent());
        viewBtn.addActionListener(e -> loadStudents());
        updateBtn.addActionListener(e -> updateStudent());
        deleteBtn.addActionListener(e -> deleteStudent());
        searchBtn.addActionListener(e -> searchStudents());

        loadStudents();
    }

    // Button Style
    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBackground(new Color(200, 170, 255));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(6, 12, 6, 12));
        btn.addChangeListener(e -> {
            if (btn.getModel().isRollover())
                btn.setBackground(new Color(255, 180, 200));
            else
                btn.setBackground(new Color(200, 170, 255));
        });
        return btn;
    }

    // CRUD Operations
    private void addStudent() {
        try {
            if (nameField.getText().isEmpty() || rollField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name & Roll No required!");
                return;
            }
            Student s = new Student(0, nameField.getText(), rollField.getText(),
                    branchField.getText(),
                    parseIntSafe(yearField.getText()),
                    parseDoubleSafe(cgpaField.getText()),
                    skillsField.getText());
            studentDAO.addStudent(s);
            loadStudents();
            clearFields();
        } catch (Exception e) {
            showError(e);
        }
    }

    private void updateStudent() {
        try {
            if (idField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter ID to update!");
                return;
            }
            Student s = new Student(
                    Integer.parseInt(idField.getText()),
                    nameField.getText(),
                    rollField.getText(),
                    branchField.getText(),
                    parseIntSafe(yearField.getText()),
                    parseDoubleSafe(cgpaField.getText()),
                    skillsField.getText());
            studentDAO.updateStudent(s);
            loadStudents();
            clearFields();
        } catch (Exception e) {
            showError(e);
        }
    }

    private void deleteStudent() {
        try {
            if (idField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter ID to delete!");
                return;
            }
            int id = Integer.parseInt(idField.getText());
            studentDAO.deleteStudent(id);
            loadStudents();
            clearFields();
        } catch (Exception e) {
            showError(e);
        }
    }

    private void searchStudents() {
        String keyword = searchField.getText().trim();
        if (keyword.isEmpty()) {
            loadStudents();
            return;
        }
        List<Student> list = studentDAO.searchStudents(keyword);
        fillTable(list);
    }

    private void loadStudents() {
        List<Student> list = studentDAO.getAllStudents();
        fillTable(list);
    }

    private void fillTable(List<Student> students) {
        tableModel.setRowCount(0);
        for (Student s : students) {
            tableModel.addRow(new Object[]{
                    s.getId(), s.getName(), s.getRollNo(), s.getBranch(),
                    s.getYear(), s.getCgpa(), s.getSkills()
            });
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        rollField.setText("");
        branchField.setText("");
        yearField.setText("");
        cgpaField.setText("");
        skillsField.setText("");
    }

    private int parseIntSafe(String text) {
        if (text == null || text.isEmpty()) return 0;
        return Integer.parseInt(text);
    }

    private double parseDoubleSafe(String text) {
        if (text == null || text.isEmpty()) return 0.0;
        return Double.parseDouble(text);
    }

    private void showError(Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        e.printStackTrace();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentGUI().setVisible(true));
    }
}
