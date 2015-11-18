package com.atlas.dao.entities;

import javax.persistence.*;

/**Id;
 * Created by schug2 on 11/18/2015.
 */
@Entity
@Table(name = "Portfolio")
public class PortfolioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    private String name;

    private double NAV;

    private double liability;

    public int getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getNAV() {
        return NAV;
    }

    public void setNAV(Double NAV) {
        this.NAV = NAV;
    }

    public Double getLiability() {
        return liability;
    }

    public void setLiability(Double liability) {
        this.liability = liability;
    }
}
