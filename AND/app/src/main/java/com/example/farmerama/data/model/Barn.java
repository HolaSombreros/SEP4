package com.example.farmerama.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "barn_table")
public class Barn {
    @PrimaryKey
    private int barnId;
    private String name;

    public Barn(){}

    public Barn(int barnId, String name) {
        this.barnId = barnId;
        this.name = name;
    }

    public int getBarnId() {
        return barnId;
    }

    public void setBarnId(int barnId) {
        this.barnId = barnId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Barn barn = (Barn) o;
        return barnId == barn.barnId && Objects.equals(name, barn.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
