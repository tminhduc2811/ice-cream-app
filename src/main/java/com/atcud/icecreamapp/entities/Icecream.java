package com.atcud.icecreamapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "icecream")
public class Icecream {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.EAGER,
            mappedBy = "icecream",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JsonIgnore
    private List<Recipe> recipes;

    public Icecream() {

    }

    public Icecream(Long id, String name, String description) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public String toString() {
        return "Icecream [id=" + id + ", name=" + name + ", description=" + description + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if(!Icecream.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Icecream other = (Icecream) obj;

        // If both names are not equal, return false
        if(!Objects.equals(this.name, other.name)) {
            return false;
        }

        // If both descriptions are not equal, return false
        if(!Objects.equals(this.description, other.description)) {
            return false;
        }

        // If both Ids are not equal, return false
        if(!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
