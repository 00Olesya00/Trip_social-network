package org.trip.files.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;


@Entity
@Table(name = "images")
@Data
@NoArgsConstructor
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "url")
    private String url;
    
    @Column(name = "description")
    private String description;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "extension_id")
    private Extensions extensions;

    public Images(String url, String filename) {
        this.url = url;
        this.description = filename;
    }

    public void setExtensionId(Extensions extensions) {
        this.extensions = extensions;
    }
}