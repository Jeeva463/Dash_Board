package com.example.dasboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dasboard.entity.OverTime;

public interface OverTimeRepository extends JpaRepository<OverTime, Integer> {
	
	@Query("SELECT SUM(overtime_analysis.overtime_hours) FROM overtime_analysis WHERE overtime_analysis.attendance_date BETWEEN :fromDate AND :toData")
double findTotalOverTime(@RequestParam String fromDate,@RequestParam String toData);
}
