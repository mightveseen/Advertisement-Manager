package com.senlainc.javacourses.petushokvaliantsin.model.payment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "payment_types")
public class PaymentType {

    @Id
    @Column(name = "type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "type_description")
    private String description;
    @Column(name = "type_duration")
    private Integer duration;
    @Column(name = "type_price")
    private Double price;

    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
    private Set<Payment> payments;

    public PaymentType(String description, Integer duration, Double price) {
        this.description = description;
        this.duration = duration;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentType that = (PaymentType) o;
        return getId().equals(that.getId()) &&
                getDescription().equals(that.getDescription()) &&
                getDuration().equals(that.getDuration()) &&
                getPrice().equals(that.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getDuration(), getPrice());
    }

    @Override
    public String toString() {
        return "PaymentType{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                ", price=" + price +
                '}';
    }
}
