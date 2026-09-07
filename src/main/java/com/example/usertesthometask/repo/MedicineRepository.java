package com.example.usertesthometask.repo;

import com.example.usertesthometask.model.MedicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<MedicineEntity, Long> {
}
