package com.example.demo.mapper;

import com.example.demo.entity.Employee;
import com.example.demo.dto.EmployeePageQueryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工Mapper接口
 */
@Mapper
public interface EmployeeMapper {
    /**
     * 分页查询员工列表
     * @param queryDTO 查询参数
     * @return 员工列表
     */
    List<Employee> page(EmployeePageQueryDTO queryDTO);

    /**
     * 根据ID查询员工
     * @param id 员工ID
     * @return 员工信息
     */
    Employee getById(Integer id);

    /**
     * 新增员工
     * @param employee 员工信息
     */
    void insert(Employee employee);

    /**
     * 更新员工信息
     * @param employee 员工信息
     */
    void update(Employee employee);

    /**
     * 删除员工
     * @param ids 员工ID列表
     */
    void deleteByIds(@Param("ids") List<Integer> ids);

    /**
     * 查询所有员工
     * @return 员工列表
     */
    List<Employee> list();

    /**
     * 根据用户名查询员工
     * @param username 用户名
     * @return 员工信息
     */
    Employee getByUsername(String username);

    /**
     * 根据手机号查询员工
     * @param phone 手机号
     * @return 员工信息
     */
    Employee getByPhone(String phone);
} 