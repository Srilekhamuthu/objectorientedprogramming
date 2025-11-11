package model;

public class Student {
    private int id;
    private String name;
    private String rollNo;
    private String branch;
    private int year;
    private double cgpa;
    private String skills;

    public Student(int id, String name, String rollNo, String branch, int year, double cgpa, String skills) {
        this.id = id;
        this.name = name;
        this.rollNo = rollNo;
        this.branch = branch;
        this.year = year;
        this.cgpa = cgpa;
        this.skills = skills;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getRollNo() { return rollNo; }
    public String getBranch() { return branch; }
    public int getYear() { return year; }
    public double getCgpa() { return cgpa; }
    public String getSkills() { return skills; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setRollNo(String rollNo) { this.rollNo = rollNo; }
    public void setBranch(String branch) { this.branch = branch; }
    public void setYear(int year) { this.year = year; }
    public void setCgpa(double cgpa) { this.cgpa = cgpa; }
    public void setSkills(String skills) { this.skills = skills; }
}
