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
	
	public ResponseEntity<?> totalOverTime(@RequestParam String fromDate,@RequestParam String toDate){
		 return overTimeService.totalOverTime(fromDate,toDate);
		 
	}
	@GetMapping("/getUserName")
	
	public ResponseEntity<?> userName(@RequestParam String fromDate,@RequestParam String toDate){
		return overTimeService.userName(fromDate,toDate);
		
	}
   @GetMapping("/getCost")
	
	public ResponseEntity<?> totalCost(@RequestParam String fromDate,@RequestParam String toDate){
		return overTimeService.totalCost(fromDate,toDate);
		
	}
   @GetMapping("/percentage")
   
   public ResponseEntity<?> getPercentage(@RequestParam String fromDate,@RequestParam String toDate){
		return overTimeService.getPercentage(fromDate,toDate);

}
   //four value get a one API
   @GetMapping("/getAllData")
   
   public ResponseEntity<?> getDashBoard(@RequestParam String fromDate,@RequestParam String toDate){
	   return overTimeService.getDashBoard(fromDate,toDate);
	   
   }  
   @GetMapping("/projectName")
   
   public ResponseEntity<?> projectNameOverTime(@RequestParam String fromDate,@RequestParam String toDate){
	    return overTimeService.projectNameOverTime(fromDate,toDate);
 
   } 
   @GetMapping("/phaseName")
   
   public ResponseEntity<?> phaseNameOverTime(@RequestParam String fromDate,@RequestParam String toDate){
	     return overTimeService.phaseNameOverTime(fromDate,toDate);

   }
  @GetMapping("/jobName")
   
   public ResponseEntity<?> jobNameOverTime(@RequestParam String fromDate,@RequestParam String toDate){
	     return overTimeService.jobNameOverTime(fromDate,toDate);

   }
   
}