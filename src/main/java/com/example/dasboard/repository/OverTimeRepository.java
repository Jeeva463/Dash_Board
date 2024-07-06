package com.example.dasboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dasboard.entity.OverTime;

@Repository
public interface OverTimeRepository extends JpaRepository<OverTime, Integer> {
	
	                        //Postgresql Query
	  //API-1
	//SELECT  sum(oa.overtime_hours) FROM overtime_analysis oa WHERE oa.overtime_hours>0 and oa.attendance_date BETWEEN '01/04/2024' AND '01/05/2024';-->table Query
	//To_DATE edhu edhukkuna 1 to 30 varaikkum varisaiyaga vara,illana 01/04/2024,01/05/2024 indha date ulladhudha mattume sollum
	@Query("SELECT SUM(o.overtimeHours) FROM OverTime o WHERE o.overtimeHours>0 and To_DATE(o.attendanceDate,'dd/MM/yyyy' ) BETWEEN To_DATE(:fromDate,'dd/MM/yyyy') AND To_DATE(:toDate,'dd/MM/yyyy')")//Psql query--> entityla vulla details eduththa 
	Double findTotalovertime(@RequestParam String fromDate,@RequestParam String toDate);//01/04/2024 to 01/05/2024
	
	  //API-2
	//SELECT  count(DISTINCT oa.user_name) FROM overtime_analysis oa WHERE oa.overtime_hours>0 and oa.attendance_date BETWEEN '01/04/2024' AND '01/05/2024';-->table query
	@Query("SELECT count(DISTINCT oa.userName) FROM OverTime oa WHERE oa.overtimeHours>0 and To_DATE(oa.attendanceDate,'dd/MM/yyyy') BETWEEN To_DATE(:fromDate,'dd/MM/yyyy') AND To_DATE(:toDate,'dd/MM/yyyy')")
	Integer findUserName(@RequestParam String fromDate,@RequestParam String toDate);
	
	  //API-3
	//SELECT  sum(oa.overtime_hours*15) FROM overtime_analysis oa WHERE oa.overtime_hours>0 and oa.attendance_date BETWEEN '01/04/2024' AND '01/05/2024';
	@Query("SELECT SUM(o.overtimeHours*15) FROM OverTime o WHERE o.overtimeHours>0 and To_DATE(o.attendanceDate,'dd/MM/yyyy' ) BETWEEN To_DATE(:fromDate,'dd/MM/yyyy') AND To_DATE(:toDate,'dd/MM/yyyy')")
	Double findTotalCost(@RequestParam String fromDate,@RequestParam String toDate);
	
	   //API-4
	//SELECT SUM(CASE WHEN oa.estimated_hours = '' THEN 0.0 ELSE EXTRACT(EPOCH FROM CAST(oa.estimated_hours AS interval)) / 3600.0 END) FROM overtime_analysis oa WHERE oa.attendance_date BETWEEN '01/04/2023' AND '01/05/2024';
	//EXPLAIN-->from-to date ulla estimatehour eduthu patha null irundha 0.0 set pannu value irundha textla irundhu doublekku valuve change panni 3600 vaguthu sum la add panni lasta total show pannum
    @Query(value = "SELECT SUM(CASE WHEN oa.estimated_hours = '' THEN 0.0 ELSE EXTRACT(EPOCH FROM CAST(oa.estimated_hours AS interval)) / 3600.0 END) FROM overtime_analysis oa WHERE To_DATE(oa.attendance_date,'dd/MM/yyyy') BETWEEN To_DATE(:fromDate,'dd/MM/yyyy') AND To_DATE(:toDate,'dd/MM/yyyy')",nativeQuery = true) 
    Double findEstimatedHours(@RequestParam String fromDate,@RequestParam String toDate);
    
       //API-5
    //SELECT  DISTINCT oa.project_name FROM overtime_analysis oa WHERE oa.attendance_date BETWEEN '01/04/2024' AND '01/05/2024' AND oa.project_name IS NOT NULL AND oa.project_name != '';//AND oa.project_name IS NOT NULL AND oa.project_name != ''--""<--indha projectname avoid panna
    @Query(value = "SELECT  DISTINCT oa.project_name FROM overtime_analysis oa WHERE oa.overtime_hours>0 and To_DATE(oa.attendance_date,'dd/MM/yyy') BETWEEN To_DATE(:fromDate,'dd/MM/yyyy') AND To_DATE(:toDate,'dd/MM/yyyy') AND oa.project_name IS NOT NULL AND oa.project_name != ''",nativeQuery = true)
    List<String> findProjectName(@RequestParam String fromDate,@RequestParam String toDate);
    
       //API-5-common
    //SELECT sum(oa.overtime_hours) FROM overtime_analysis oa WHERE oa.project_name = 'ADX_PF_996_2024' AND oa.day = 'Working Day' AND oa.overtime_hours>0 and oa.attendance_date BETWEEN '01/04/2024' AND '01/05/2024';
    @Query(value = " select sum(oa.overtime_hours) from overtime_analysis oa where (oa.project_name=:value or oa.phase_name =:value or oa.job_name = :value or oa.user_name =:value) and oa.day=:dayType and oa.overtime_hours > 0 and To_DATE(oa.attendance_date, 'dd/MM/yyyy') BETWEEN To_DATE(:fromDate,'dd/MM/yyyy') AND To_DATE(:toDate,'dd/MM/yyyy') ", nativeQuery = true)
	Double findDayOverTime(String value, String fromDate, String toDate,String dayType);//Working Day Weekoff Public Holiday--ipdidha vachchikanum
    
        //API-5
    //SELECT sum(oa.overtime_hours) FROM overtime_analysis oa WHERE oa.project_name = 'ADX_PF_996_2024' AND oa.overtime_hours>0 and oa.attendance_date BETWEEN '01/04/2024' AND '01/05/2024';
    @Query(value = "SELECT sum(oa.overtime_hours) FROM overtime_analysis oa WHERE oa.overtime_hours>0  and To_DATE(oa.attendance_date,'dd/MM/yyyy') BETWEEN To_DATE(:fromDate,'dd/MM/yyyy') AND To_DATE(:toDate,'dd/MM/yyyy') AND oa.project_name =:value",nativeQuery = true)
     Double findProjectNameOverTime(String value,String fromDate,String toDate);
    
         //API-6
    //SELECT  DISTINCT oa.phase_name FROM overtime_analysis oa WHERE oa.overtime_hours>0 and oa.attendance_date BETWEEN '01/04/2024' AND '01/05/2024'  AND oa.phase_name IS NOT NULL AND oa.phase_name != '';
    @Query(value = "SELECT  DISTINCT oa.phase_name FROM overtime_analysis oa WHERE oa.overtime_hours>0 and To_DATE(oa.attendance_date,'dd/MM/yyyy') BETWEEN To_DATE(:fromDate,'dd/MM/yyyy') AND To_DATE(:toDate,'dd/MM/yyyy')  AND oa.phase_name IS NOT NULL AND oa.phase_name != ''",nativeQuery = true)
    List<String> findPhaseName(@RequestParam String fromDate,@RequestParam String toDate);
    
        //API-6 and use this API-5-common Query
    //SELECT sum(oa.overtime_hours) FROM overtime_analysis oa WHERE oa.phase_name = 'ADX_AMC_322_2023 AMT MENA' AND oa.overtime_hours>0 and oa.attendance_date BETWEEN '01/04/2024' AND '01/05/2024';
    @Query(value = "SELECT sum(oa.overtime_hours) FROM overtime_analysis oa WHERE oa.overtime_hours>0  and To_DATE(oa.attendance_date,'dd/MM/yyyy') BETWEEN To_DATE(:fromDate,'dd/MM/yyyy') AND To_DATE(:toDate,'dd/MM/yyyy') AND oa.phase_name =:value",nativeQuery = true)
     Double findPhaseNameOverTime(String value,String fromDate,String toDate);
    
         //API-7
    //SELECT  DISTINCT oa.job_name FROM overtime_analysis oa WHERE oa.overtime_hours>0 and oa.attendance_date BETWEEN '01/04/2024' AND '01/05/2024'  AND oa.job_name IS NOT NULL AND oa.job_name != '';
    @Query(value = "SELECT  DISTINCT oa.job_name FROM overtime_analysis oa WHERE oa.overtime_hours>0 and To_DATE(oa.attendance_date,'dd/MM/yyyy') BETWEEN To_DATE(:fromDate,'dd/MM/yyyy') AND To_DATE(:toDate,'dd/MM/yyyy') AND oa.job_name IS NOT NULL AND oa.job_name != ''",nativeQuery = true)
    List<String> findJobName(@RequestParam String fromDate,@RequestParam String toDate);
    
         //API-7 and use this API-5-common Query
    // SELECT sum(oa.overtime_hours) FROM overtime_analysis oa WHERE oa.job_name = 'AMC_265_2022 QDB B1' AND oa.overtime_hours>0 and oa.attendance_date BETWEEN '01/04/2024' AND '01/05/2024';
     @Query(value = " SELECT sum(oa.overtime_hours) FROM overtime_analysis oa WHERE oa.job_name = :value AND oa.overtime_hours>0 and To_DATE(oa.attendance_date,'dd/MM/yyyy') BETWEEN To_DATE(:fromDate,'dd/MM/yyyy') AND To_DATE(:toDate,'dd/MM/yyyy')",nativeQuery = true)
     Double findJobNameOverTime(String value,String fromDate,String toDate);
     
         //API-8
     //SELECT  DISTINCT oa.user_name FROM overtime_analysis oa WHERE oa.overtime_hours>0 and oa.attendance_date BETWEEN '01/04/2024' AND '01/04/2024'AND oa.user_name IS NOT NULL AND oa.user_name != '';
    @Query(value = "SELECT DISTINCT oa.user_name  FROM overtime_analysis oa WHERE oa.overtime_hours>0 and To_DATE(oa.attendance_date,'dd/MM/yyyy') BETWEEN To_DATE(:fromDate,'dd/MM/yyyy') AND To_DATE(:toDate,'dd/MM/yyyy')AND oa.user_name IS NOT NULL AND oa.user_name != ''",nativeQuery = true)
    List<String> findUserName1(@RequestParam String fromDate,@RequestParam String toDate);
    
         //API-9 and use this API-5-common Query
    // SELECT sum(oa.overtime_hours) FROM overtime_analysis oa WHERE oa.user_name = 'Arjun Vadakadathu Unni' AND oa.overtime_hours>0 and oa.attendance_date BETWEEN '01/04/2024' AND '01/04/2024';
     @Query(value = " SELECT sum(oa.overtime_hours) FROM overtime_analysis oa WHERE oa.user_name =:value AND oa.overtime_hours>0 and To_DATE(oa.attendance_date,'dd/MM/yyyy') BETWEEN To_DATE(:fromDate,'dd/MM/yyyy') AND To_DATE(:toDate,'dd/MM/yyyy')",nativeQuery = true)
     Double findUserNameOverTime(String value,String fromDate,String toDate);
     
     /*SELECT *
     FROM overtime_analysis oa
     WHERE  oa.attendance_date BETWEEN '14/08/2024' AND '20/08/2024'
       AND (oa.organization_name IS NULL or oa.organization_name = 'Adax Security Systems')
       AND (oa.branch_name IS NULL or oa.branch_name = 'Qatar')
       AND (oa.department_name IS NULL or oa.department_name = 'Technical')
       AND (oa.category_name IS NULL or oa.category_name = 'Support')
       AND (oa.designation_name IS NULL or oa.designation_name = 'Support Engineer')
       AND (oa.grade_name IS NULL or oa.grade_name = 'Grade-1')
       AND (oa.project_name IS NULL or oa.project_name = 'MEKHANIS CDD AND POLICE STATIO')
       AND (oa.job_name is null or oa.job_name ='MEKHANIS CDD AND POLICE STATIO')
       AND (oa.phase_name IS NULL or oa.phase_name = 'MEKHANIS CDD AND POLICE STATIO')
       AND (oa.user_name IS NULL or oa.user_name = 'Vaishak Chembalipurath')
       AND (oa.section_name IS NULL or oa.section_name = 'Support Team');*/
     @Query(value = "SELECT *"
     		+ "FROM overtime_analysis oa "
     		+ "WHERE  To_DATE(oa.attendance_date,'dd/MM/yyyy') BETWEEN To_DATE(:fromDate,'dd/MM/yyyy') AND To_DATE(:toDate,'dd/MM/yyyy')"
     		+ "  AND (oa.organization_name IS NULL or oa.organization_name =:value)"
     		+ "  AND (oa.branch_name IS NULL or oa.branch_name =:value)"
     		+ "  AND (oa.department_name IS NULL or oa.department_name =:value)"
     		+ "  AND (oa.category_name IS NULL or oa.category_name =:value)"
     		+ "  AND (oa.designation_name IS NULL or oa.designation_name =:value)"
     		+ "  AND (oa.grade_name IS NULL or oa.grade_name =:value)"
     		+ "  AND (oa.project_name IS NULL or oa.project_name =:value)"
     		+ "  AND (oa.job_name is null or oa.job_name =:value)"
     		+ "  AND (oa.phase_name IS NULL or oa.phase_name =:value)"
     		+ "  AND (oa.user_name IS NULL or oa.user_name =:value)"
     		+ "  AND (oa.section_name IS NULL or oa.section_name =:value)",nativeQuery = true)
           List<String> findFilterList();
}
