package org.trip.files.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "images")
@Data
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "url")
    private String url;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "extension_id")
    private Long extensionId;

    public Images(String s, String filename) {
    }

    public Images() {}
}