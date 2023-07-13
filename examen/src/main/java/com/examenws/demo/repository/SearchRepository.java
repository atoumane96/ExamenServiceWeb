package com.examenws.demo.repository;

import com.examenws.demo.entity.DayInfo;
import com.examenws.demo.entity.Search;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRepository extends JpaRepository<Search, Integer> {

}