package com.example.degiuaky1.MyModel;

import java.io.Serializable;

public class SubjectModel implements Serializable {
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
