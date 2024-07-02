package com.example.dasboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dasboard.entity.OverTime;

@Repository
public interface OverTimeRepository extends JpaRepository<OverTime, Integer> {
	
	//SELECT  sum(oa.overtime_hours) FROM overtime_analysis oa WHERE oa.overtime_hours>0 and oa.attendance_date BETWEEN '01/04/2024' AND '01/05/2024';-->table Query
	//To_DATE edhu edhukkuna 1 to 30 varaikkum varisaiyaga vara,illana 01/04/2024,01/05/2024 indha date ulladhudha mattume sollum
	@Query("SELECT SUM(o.overtimeHours) FROM OverTime o WHERE o.overtimeHours>0 and To_DATE(o.attendanceDate,'dd/MM/yyyy' ) BETWEEN To_DATE(:fromDate,'dd/MM/yyyy') AND To_DATE(:toDate,'dd/MM/yyyy')")
	Double findTotalovertime(@RequestParam String fromDate,@RequestParam String toDate);//01/04/2024 to 01/05/2024
	
	//SELECT  count(DISTINCT oa.user_name) FROM overtime_analysis oa WHERE oa.overtime_hours>0 and oa.attendance_date BETWEEN '01/04/2024' AND '01/05/2024';-->table query
	@Query("SELECT count(DISTINCT oa.userName) FROM OverTime oa WHERE oa.overtimeHours>0 and To_DATE(oa.attendanceDate,'dd/MM/yyyy') BETWEEN To_DATE(:fromDate,'dd/MM/yyyy') AND To_DATE(:toDate,'dd/MM/yyyy')")
	Integer findUserName(@RequestParam String fromDate,@RequestParam String toDate);
	
	//SELECT  sum(oa.overtime_hours*15) FROM overtime_analysis oa WHERE oa.overtime_hours>0 and oa.attendance_date BETWEEN '01/04/2024' AND '01/05/2024';
	@Query("SELECT SUM(o.overtimeHours*15) FROM OverTime o WHERE o.overtimeHours>0 and To_DATE(o.attendanceDate,'dd/MM/yyyy' ) BETWEEN To_DATE(:fromDate,'dd/MM/yyyy') AND To_DATE(:toDate,'dd/MM/yyyy')")
	Double findTotalCost(@RequestParam String fromDate,@RequestParam String toDate);
}
