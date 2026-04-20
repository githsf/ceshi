package com.example.demo.mapper;

import com.example.demo.entity.EmployeeExperience;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工工作经历Mapper接口
 */
@Mapper
public interface EmployeeExperienceMapper {
    /**
     * 根据员工ID查询工作经历
     * @param empId 员工ID
     * @return 工作经历列表
     */
    List<EmployeeExperience> getByEmpId(Integer empId);

    /**
     * 批量新增工作经历
     * @param list 工作经历列表
     */
    void insertBatch(@Param("list") List<EmployeeExperience> list);

    /**
     * 根据员工ID删除工作经历
     * @param empId 员工ID
     */
    void deleteByEmpId(Integer empId);
} 