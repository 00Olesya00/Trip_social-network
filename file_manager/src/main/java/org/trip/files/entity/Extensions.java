package org.trip.files.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "image_extensions")
@NoArgsConstructor
public class Extensions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "extension")
    private String extension;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private Long active;
}
