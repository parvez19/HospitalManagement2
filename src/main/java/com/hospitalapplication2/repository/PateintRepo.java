package com.hospitalapplication2.repository;

import com.hospitalapplication2.entity.Pateint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PateintRepo extends JpaRepository<Pateint,Integer> {
}
