package com.senlainc.javacourses.petushokvaliantsin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "states")
public class State {
    @Id
    @Column(name = "state_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long index;
    @Column(name = "state_description")
    private String description;
    @OneToMany(mappedBy = "state", fetch = FetchType.EAGER)
    private Set<Object> objects;

    public State() {
    }

    public State(Long index, String description, Set<Object> objects) {
        this.index = index;
        this.description = description;
        this.objects = objects;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Object> getObjects() {
        return objects;
    }

    public void setObjects(Set<Object> objects) {
        this.objects = objects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return index.equals(state.index) &&
                description.equals(state.description) &&
                objects.equals(state.objects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, description, objects);
    }

    @Override
    public String toString() {
        return "State{" +
                "index=" + index +
                ", description='" + description + '\'' +
                ", objects=" + objects +
                '}';
    }
}
