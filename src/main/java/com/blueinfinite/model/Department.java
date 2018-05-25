package com.blueinfinite.model;

public class Department {
    private int id;
    private String className;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", className='" + className + '\'' +
                '}';
    }
}
