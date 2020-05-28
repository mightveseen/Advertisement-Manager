package com.senlainc.javacourses.petushokvaliantsin.model.payment;

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
@Table(name = "payment_types")
public class PaymentType {

    @Id
    @Column(name = "type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long index;
    @Column(name = "type_description")
    private String description;
    @Column(name = "type_duration")
    private Integer duration;
    @Column(name = "type_price")
    private Double price;
    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
    private Set<Payment> payments;

    public PaymentType() {
    }

    public PaymentType(String description, Integer duration, Double price) {
        this.description = description;
        this.duration = duration;
        this.price = price;
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentType that = (PaymentType) o;
        return index.equals(that.index) &&
                description.equals(that.description) &&
                duration.equals(that.duration) &&
                price.equals(that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, description, duration, price);
    }

    @Override
    public String toString() {
        return "PaymentType{" +
                "index=" + index +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                ", price=" + price +
                '}';
    }
}
