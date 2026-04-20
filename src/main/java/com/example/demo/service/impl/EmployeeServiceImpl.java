package com.example.demo.service.impl;

import com.example.demo.common.BusinessException;
import com.example.demo.common.ResultEnum;
import com.example.demo.entity.Employee;
import com.example.demo.entity.EmployeeExperience;
import com.example.demo.dto.EmployeePageQueryDTO;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.mapper.EmployeeExperienceMapper;
import com.example.demo.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * 员工服务实现类
 */
@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    
    @Autowired
    private EmployeeExperienceMapper employeeExperienceMapper;

    @Override
    public PageInfo<Employee> page(EmployeePageQueryDTO queryDTO) {
        PageHelper.startPage(queryDTO.getPage(), queryDTO.getPageSize());
        List<Employee> list = employeeMapper.page(queryDTO);
        return new PageInfo<>(list);
    }

    @Override
    public Employee getById(Integer id) {
        // 查询员工基本信息
        Employee employee = employeeMapper.getById(id);
        if (employee == null) {
            throw new BusinessException(ResultEnum.DATA_NOT_FOUND);
        }
        
        // 查询工作经历
        List<EmployeeExperience> exprList = employeeExperienceMapper.getByEmpId(id);
        employee.setExprList(exprList);
        
        return employee;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Employee employee) {
        // 校验用户名是否已存在
        Employee existEmployee = employeeMapper.getByUsername(employee.getUsername());
        if (existEmployee != null) {
            throw new BusinessException(ResultEnum.USERNAME_EXIST);
        }
        
        // 校验手机号是否已存在
        existEmployee = employeeMapper.getByPhone(employee.getPhone());
        if (existEmployee != null) {
            throw new BusinessException(ResultEnum.PHONE_EXIST);
        }
        
        // 设置默认密码
        employee.setPassword("123456");
        
        // 设置创建时间和更新时间
        LocalDateTime now = LocalDateTime.now();
        employee.setCreateTime(now);
        employee.setUpdateTime(now);
        
        // 保存员工基本信息
        employeeMapper.insert(employee);
        
        // 保存工作经历
        List<EmployeeExperience> exprList = employee.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(expr -> expr.setEmpId(employee.getId()));
            employeeExperienceMapper.insertBatch(exprList);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Employee employee) {
        // 校验员工是否存在
        Employee existEmployee = employeeMapper.getById(employee.getId());
        if (existEmployee == null) {
            throw new BusinessException(ResultEnum.DATA_NOT_FOUND);
        }
        
        // 如果修改了用户名，校验用户名是否已存在
        if (!Objects.equals(existEmployee.getUsername(), employee.getUsername())) {
            Employee tempEmployee = employeeMapper.getByUsername(employee.getUsername());
            if (tempEmployee != null) {
                throw new BusinessException(ResultEnum.USERNAME_EXIST);
            }
        }
        
        // 如果修改了手机号，校验手机号是否已存在
        if (!Objects.equals(existEmployee.getPhone(), employee.getPhone())) {
            Employee tempEmployee = employeeMapper.getByPhone(employee.getPhone());
            if (tempEmployee != null) {
                throw new BusinessException(ResultEnum.PHONE_EXIST);
            }
        }
        
        // 设置更新时间
        employee.setUpdateTime(LocalDateTime.now());
        
        // 更新员工基本信息
        employeeMapper.update(employee);
        
        // 删除原有工作经历
        employeeExperienceMapper.deleteByEmpId(employee.getId());
        
        // 保存新的工作经历
        List<EmployeeExperience> exprList = employee.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(expr -> expr.setEmpId(employee.getId()));
            employeeExperienceMapper.insertBatch(exprList);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            throw new BusinessException(ResultEnum.PARAM_ERROR);
        }
        
        // 删除员工基本信息
        employeeMapper.deleteByIds(ids);
        
        // 删除工作经历
        ids.forEach(employeeExperienceMapper::deleteByEmpId);
    }

    @Override
    public List<Employee> list() {
        return employeeMapper.list();
    }
} 