package com.blueinfinite.mapper;

import com.blueinfinite.model.Department;
import org.springframework.stereotype.Repository;

/**
 * 部门
 */
@Repository
public interface DepartmentMapper {
    Department getInfo(int ID);
}
