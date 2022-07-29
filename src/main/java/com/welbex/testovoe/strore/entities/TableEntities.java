package com.welbex.testovoe.strore.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)//все поля приватные
@Entity
@Table(name = "testable")
@Builder
@NoArgsConstructor
public class TableEntities {
    public TableEntities(Long id, @NonNull LocalDate date,
                         @NonNull String name, @NonNull int
                                 quantity, @NonNull Long distance) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.quantity = quantity;
        this.distance = distance;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;
    @NonNull
    @Column(name = "date")
    private LocalDate date;

    @NonNull
    @Column(name = "name")
    String name;

    @NonNull
    @Column(name = "quantity")
    int quantity;
    @NonNull
    @Column(name = "distance")
    Long distance;

}
