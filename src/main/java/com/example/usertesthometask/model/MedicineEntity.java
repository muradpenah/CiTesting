package com.example.usertesthometask.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medicines")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class MedicineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String brend;
    String description;
}
