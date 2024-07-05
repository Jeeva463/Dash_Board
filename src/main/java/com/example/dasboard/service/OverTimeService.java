package com.example.dasboard.service;
import java.util.ArrayList;
import java.util.HashMap;
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
			obj.setData("OverTimeHours "+"="+" "+overtimehowers);
		}
			return ResponseEntity.ok(obj);
			
	}
	public ResponseEntity<?> userName(String fromDate, String toDate) {
		Dto obj = new Dto();
		if (fromDate != null && toDate != null) {
		Integer count = overTimeRepository.findUserName(fromDate, toDate);
		obj.setData("EmployeesWithOvertime "+"="+" "+count);
	}
		return ResponseEntity.ok(obj);
		
}
	public ResponseEntity<?> totalCost(String fromDate, String toDate) {
		Dto obj = new Dto();
		if (fromDate != null && toDate != null) {
		Double total = overTimeRepository.findTotalCost(fromDate, toDate);
		obj.setData("TotalCostIncuried "+"="+"$"+" "+total);
	}
		return ResponseEntity.ok(obj);
		
}
	public ResponseEntity<?> getPercentage(String fromDate, String toDate) {
		Dto obj = new Dto();
		if(fromDate != null || toDate != null) {
			Double totalTime = overTimeRepository.findTotalovertime(fromDate, toDate);
			Double estimateTime = overTimeRepository.findEstimatedHours(fromDate, toDate);
			Double overallPercentage = (totalTime/estimateTime)*100;
			obj.setData("OverTimeHoursPercentage "+"="+" "+overallPercentage+"%");
		}
		return ResponseEntity.ok(obj);
		
	}
	public ResponseEntity<?> getDashBoard(String fromDate, String toDate) {
		Dto obj = new Dto();
		if(fromDate != null || toDate != null) {
			Double overtimehowers = overTimeRepository.findTotalovertime(fromDate, toDate);
			Integer count = overTimeRepository.findUserName(fromDate, toDate);
			Double total = overTimeRepository.findTotalCost(fromDate, toDate);
			Double estimateTime = overTimeRepository.findEstimatedHours(fromDate, toDate);
			Double overallPercentage = (overtimehowers/estimateTime)*100;
			HashMap<String, Object>map = new HashMap<String, Object>();
			map.put("OverTimeHours",overtimehowers);
			map.put("EmployeesWithOvertime",count);
			map.put("TotalCostIncuried",total);
			map.put("OverTimeHoursPercentage",overallPercentage);
			obj.setData(map);
		}
		return ResponseEntity.ok(obj);
		
	}
	public ResponseEntity<?>  projectNameOverTime(String fromDate, String toDate) {
		Dto obj = new Dto();
		 HashMap<String, Object>map = new HashMap<String, Object>();	
		if(fromDate != null&&toDate != null) {
		List<String> projectNam = overTimeRepository.findProjectName(fromDate, toDate);
		List<Double> workDaylist = new ArrayList<Double>();
		List<Double> weekOflist = new ArrayList<Double>();
		List<Double> holidaylist = new ArrayList<Double>();
		for(int i=0;i<projectNam.size();i++) {
			Double overAll = overTimeRepository.findProjectNameOverTime(projectNam.get(i), fromDate, toDate);
			Double workDay = overTimeRepository.findDayOverTime(projectNam.get(i),fromDate, toDate,"Working Day");
			Double weekOf = overTimeRepository.findDayOverTime(projectNam.get(i),fromDate, toDate, "Weekoff");
			Double holiday = overTimeRepository.findDayOverTime(projectNam.get(i), fromDate, toDate,"Public Holiday");

			Double workDay1 = Calucate(workDay,overAll);
			Double weekOf1 = Calucate(weekOf,overAll);
			Double holiday1 = Calucate(holiday,overAll);
			
			workDaylist.add(workDay1);
			weekOflist.add(weekOf1);
			holidaylist.add(holiday1);		
		}		
		map.put("projectName", projectNam);
		map.put("workDaylist", workDaylist);
		map.put("weekOflist", weekOflist);
		map.put("holidaylist", holidaylist);
		obj.setData(map);
		}
		return ResponseEntity.ok(obj);
	}
	public static Double Calucate(Double hour,Double overAll) {
		if(hour == null || hour == 0) {
			return 0.0;
		}
		Double result = (hour/overAll)*100;
		return result;		
	}
	public ResponseEntity<?>  phaseNameOverTime(String fromDate, String toDate) {
		Dto obj = new Dto();
		 HashMap<String, Object>map = new HashMap<String, Object>();	
		if(fromDate != null&&toDate != null) {
		List<String> phaseNam = overTimeRepository.findPhaseName(fromDate, toDate);
		List<Double> workDaylist = new ArrayList<Double>();
		List<Double> weekOflist = new ArrayList<Double>();
		List<Double> holidaylist = new ArrayList<Double>();
		for(int i=0;i<phaseNam.size();i++) {
			Double overAll = overTimeRepository.findPhaseNameOverTime(phaseNam.get(i), fromDate, toDate);
			Double workDay = overTimeRepository.findDayOverTime(phaseNam.get(i),fromDate, toDate,"Working Day");
			Double weekOf = overTimeRepository.findDayOverTime(phaseNam.get(i),fromDate, toDate, "Weekoff");
			Double holiday = overTimeRepository.findDayOverTime(phaseNam.get(i), fromDate, toDate,"Public Holiday");

			Double workDay1 = Calucate(workDay,overAll);
			Double weekOf1 = Calucate(weekOf,overAll);
			Double holiday1 = Calucate(holiday,overAll);
			
			workDaylist.add(workDay1);
			weekOflist.add(weekOf1);
			holidaylist.add(holiday1);		
		}		
		map.put("projectName", phaseNam);
		map.put("workDaylist", workDaylist);
		map.put("weekOflist", weekOflist);
		map.put("holidaylist", holidaylist);
		obj.setData(map);
		}
		return ResponseEntity.ok(obj);
	}
	public ResponseEntity<?>  jobNameOverTime(String fromDate, String toDate) {
		Dto obj = new Dto();
		 HashMap<String, Object>map = new HashMap<String, Object>();	
		if(fromDate != null&&toDate != null) {
		List<String> jobnam = overTimeRepository.findJobName(fromDate, toDate);
		List<Double> workDaylist = new ArrayList<Double>();
		List<Double> weekOflist = new ArrayList<Double>();
		List<Double> holidaylist = new ArrayList<Double>();
		for(int i=0;i<jobnam.size();i++) {
			Double overAll = overTimeRepository.findJobNameOverTime(jobnam.get(i), fromDate, toDate);
			Double workDay = overTimeRepository.findDayOverTime(jobnam.get(i),fromDate, toDate,"Working Day");
			Double weekOf = overTimeRepository.findDayOverTime(jobnam.get(i),fromDate, toDate, "Weekoff");
			Double holiday = overTimeRepository.findDayOverTime(jobnam.get(i), fromDate, toDate,"Public Holiday");

			Double workDay1 = Calucate(workDay,overAll);
			Double weekOf1 = Calucate(weekOf,overAll);
			Double holiday1 = Calucate(holiday,overAll);
			
			workDaylist.add(workDay1);
			weekOflist.add(weekOf1);
			holidaylist.add(holiday1);		
		}		
		map.put("projectName", jobnam);
		map.put("workDaylist", workDaylist);
		map.put("weekOflist", weekOflist);
		map.put("holidaylist", holidaylist);
		obj.setData(map);
		}
		return ResponseEntity.ok(obj);
	}
}	
	
