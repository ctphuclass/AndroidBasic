package com.example.degiuaky1.MyModel;

import java.io.Serializable;

public class StudentModel implements Serializable {
    public StudentModel(){

    }
    public StudentModel(String name){
        this.Name = name;
    }
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    private int Id;
    private String Name;
}
