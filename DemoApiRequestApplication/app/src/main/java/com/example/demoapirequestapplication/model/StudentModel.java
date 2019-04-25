package com.example.demoapirequestapplication.model;

public class StudentModel {
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getStudentCode() {
        return StudentCode;
    }

    public void setStudentCode(String studentCode) {
        StudentCode = studentCode;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    private int Id;
    private String StudentCode;
    private  String Name;
    private  int Age;
    private  String StudentClass;

    public String getStudentClass() {
        return StudentClass;
    }

    public void setStudentClass(String studentClass) {
        StudentClass = studentClass;
    }

    private String Email;
}
