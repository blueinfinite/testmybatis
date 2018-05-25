package com.blueinfinite.mapper;

import com.blueinfinite.model.Class2;
import org.springframework.stereotype.Repository;

@Repository
public interface Class2Mapper {
    Class2 getInfo(int ID);
}
