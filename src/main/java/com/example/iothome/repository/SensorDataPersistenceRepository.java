package com.example.iothome.repository;

import com.example.iothome.model.entity.ResponseTypeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorDataPersistenceRepository extends JpaRepository<ResponseTypeDTO, Integer> {
}
