package org.trip.files.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.trip.files.entity.Extensions;

@Repository
@Component
public interface ExtensionsRepository extends JpaRepository<Extensions, Long> {

    @Query("select e from Extensions e where e.extension = ?1")
    Extensions findByExtension(String extension);
}
