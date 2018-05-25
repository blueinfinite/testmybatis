package com.blueinfinite.model;

public class Custom {
    private int id;
    private String customName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    @Override
    public String toString() {
        return "Custom{" +
                "id=" + id +
                ", customName='" + customName + '\'' +
                '}';
    }
}
