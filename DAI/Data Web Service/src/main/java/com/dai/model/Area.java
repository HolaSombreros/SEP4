package com.dai.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "area")
public class Area {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "area_id")
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="barn_id")
    private Barn barn;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "number_of_pigs")
    private int numberOfPigs;
    //private List<Measurement> measurements;


    protected Area() {
    }

    public Area(Barn barn, String name, String description, int numberOfPigs) {
        this.barn = barn;
        this.name = name;
        this.description = description;
        this.numberOfPigs = numberOfPigs;
    }

}
