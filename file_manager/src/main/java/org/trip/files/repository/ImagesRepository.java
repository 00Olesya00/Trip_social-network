package org.trip.files.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.trip.files.entity.Images;

@Repository
@Component
public interface ImagesRepository extends JpaRepository<Images, Long> {
}
