package com.martin.projects.persistence.repository;

import com.martin.projects.persistence.entity.School;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {

  List<School> findByNameContainingIgnoreCase(String name);

  List<School> findByCityContainingIgnoreCase(String city);

  List<School> findByStateContainingIgnoreCase(String state);

  List<School> findByPostalCodeContainingIgnoreCase(String postalCode);
}
