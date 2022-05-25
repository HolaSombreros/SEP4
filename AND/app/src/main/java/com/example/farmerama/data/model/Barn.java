package com.example.farmerama.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "barn_table")
public class Barn {
    @PrimaryKey
    private int barnId;
    private String barnName;

    public Barn(){}

    public Barn(int barnId, String name) {
        this.barnId = barnId;
        this.barnName = name;
    }

    public int getBarnId() {
        return barnId;
    }

    public void setBarnId(int barnId) {
        this.barnId = barnId;
    }

    public String getBarnName() {
        return barnName;
    }

    public void setBarnName(String barnName) {
        this.barnName = barnName;
    }

    @Override
    public String toString() {
        return barnName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Barn barn = (Barn) o;
        return barnId == barn.barnId && Objects.equals(barnName, barn.barnName);
    }
}
