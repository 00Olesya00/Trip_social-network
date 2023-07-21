package org.trip.files.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.trip.files.entity.Images;

@Repository
public interface ImagesRepository extends JpaRepository<Images, Long> {
}
