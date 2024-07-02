package com.example.dasboard.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.dasboard.dto.Dto;
import com.example.dasboard.entity.OverTime;
import com.example.dasboard.repository.OverTimeRepository;
@Service
public class OverTimeService {
	@Autowired
	OverTimeRepository overTimeRepository;

	public Optional<OverTime> getId(int id) {
	     return overTimeRepository.findById(id);
		
	}

	public List<OverTime> getDetails() {
		return overTimeRepository.findAll();
		
	}

	public ResponseEntity<?> totalOverTime(String fromDate, String toDate) {
		Dto obj = new Dto();
		if (fromDate != null && toDate != null) {
			Double overtimehowers = overTimeRepository.findTotalovertime(fromDate, toDate);	
			obj.setData("OverTimeHours"+"="+overtimehowers);
		}
			return ResponseEntity.ok(obj);	
	}
	public ResponseEntity<?> userName(String fromDate, String toDate) {
		Dto obj = new Dto();
		if (fromDate != null && toDate != null) {
		Integer count = overTimeRepository.findUserName(fromDate, toDate);
		obj.setData("EmployeesWithOvertime"+"="+count);
	}
		return ResponseEntity.ok(obj);
}
	public ResponseEntity<?> totalCost(String fromDate, String toDate) {
		Dto obj = new Dto();
		if (fromDate != null && toDate != null) {
		Double total = overTimeRepository.findTotalCost(fromDate, toDate);
		obj.setData("TotalCostIncuried"+"="+"$"+total);
	}
		return ResponseEntity.ok(obj);
}	
}	
	
