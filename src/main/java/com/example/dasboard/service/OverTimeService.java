package com.example.dasboard.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

	public double totalOverTime(String fromDate, String toData) {
		
	}

}
