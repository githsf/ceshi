package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

/**
 * 员工工作经历实体类
 */
@Data
public class EmployeeExperience {
    /**
     * 工作经历ID
     */
    private Integer id;
    
    /**
     * 员工ID
     */
    private Integer empId;
    
    /**
     * 工作开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    
    /**
     * 工作结束日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    
    /**
     * 公司名称
     */
    private String company;
    
    /**
     * 职位名称
     */
    private String position;
} 