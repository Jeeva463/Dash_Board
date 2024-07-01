package com.example.dasboard.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "overtime_analysis")
public class OverTime {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "job_code")
	private String jobCode;
	
	@Column(name = "job_name")
	private String jobName;
	
	@Column(name = "phase_code")
	private String phaseCode;
	
	@Column(name = "phase_name")
	private String phaseName;
	
	@Column(name = "project_code")
	private String projectCode;
	
	@Column(name = "project_name")
	private String projectName;
	
	@Column(name = "estimated_hours")
	private String estimatedHours;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "user_iD")
	private int userID;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "attendance_date")
	private String attendanceDate;
	
	@Column(name = "job_hours")
	private String jobHours;
	
	@Column(name = "job_count")
	private String jobCount;
	
	@Column(name = "ot1")
	private String ot1;
	
	@Column(name = "ot2")
	private String ot2;
	
	@Column(name = "clocked_hours")
	private Float clockedHours;
	
	@Column(name = "overtime_hours")
	private Float overtimeHours;
	
	@Column(name = "department_name")
	private String departmentName;
	
	@Column(name = "designation")
	private String designation;
	
	@Column(name = "designation_name")
	private String designationName;
	
	@Column(name = "organization_name")
	private String organizationName;
	
	@Column(name = "grade_name")
	private String gradeName;
	
	@Column(name = "section_name")
	private String sectionName;
	
	@Column(name = "category_name")
	private String categoryName;
	
	@Column(name = "branch_name")
	private String branchName;
	
	@Column(name = "cost_per_hour")
	private Float costPerHour;
	
	@Column(name = "cost_incurred")
	private Float costIncurred;
	
	@Column(name = "project_iD")
	private String projectID;
	
	@Column(name = "max_end_date")
	private LocalDateTime maxEndDate;
	
	@Column(name = "project_status")
	private String projectStatus;
	
	@Column(name = "has_project_started")
	private String hasProjectStarted;
	
	@Column(name = "active_project")
	private Float activeProject;
	
	@Column(name = "created_date_time")
	private LocalDateTime createdDateTime;
	
	@Column(name = "start_date_time")
	private LocalDateTime startDateTime;
	
	@Column(name = "end_date_time")
	private LocalDateTime endDateTime;
	
	@Column(name = "process_date")
	private LocalDateTime processDate;
	
	@Column(name = "fHSHS")
	private String fHSHS;
	
	@Column(name = "day")
	private String day;
	
	@Column(name = "overtime_percent")
	private String overtimePercent;

	}
	