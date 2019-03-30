package com.example.degiuaky1.MyModel;

public class RegisterSubjectModel {
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getSubjectId() {
        return SubjectId;
    }

    public void setSubjectId(int subjectId) {
        SubjectId = subjectId;
    }

    public int getStudentId() {
        return StudentId;
    }

    public void setStudentId(int studentId) {
        StudentId = studentId;
    }

    private int Id;
    private int SubjectId;
    private int StudentId;

    @Override
    public String toString() {
        return this.Id + " - " + this.SubjectId + " - " + this.StudentId;
    }
}
