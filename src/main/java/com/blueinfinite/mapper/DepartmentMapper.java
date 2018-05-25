package com.blueinfinite.mapper;

import com.blueinfinite.model.Department;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentMapper {
    Department getInfo(int ID);
}
