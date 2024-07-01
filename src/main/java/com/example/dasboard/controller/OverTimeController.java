package com.example.dasboard.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dasboard.entity.OverTime;
import com.example.dasboard.service.OverTimeService;

@RestController
@RequestMapping("/api/dash")
public class OverTimeController {
	@Autowired
	OverTimeService overTimeService;
	
	@GetMapping("/get/{id}")
	
	public Optional<OverTime> getId(@PathVariable int id){
		return overTimeService.getId(id);
	}
	@GetMapping("/getAll")
	
	public List<OverTime> getDetails(){
		return overTimeService.getDetails();
	}
	@GetMapping("/overTime")
	
	public double totalOverTime(@RequestParam String fromDate,@RequestParam String toData){
		return overTimeService.totalOverTime(fromDate,toData);
	}

}
