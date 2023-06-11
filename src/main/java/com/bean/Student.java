package com.bean;

public class Student {
    private int stuId;
    private String stuName;
    private String grade;

    public Student() {
    }

    public Student(int stuId, String stuName, String grade) {
        this.stuId = stuId;
        this.stuName = stuName;
        this.grade = grade;
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

}
