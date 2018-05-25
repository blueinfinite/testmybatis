package com.blueinfinite.mapper;

import com.blueinfinite.model.Custom;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomMapper {
   // @Select("select * from sync_custom where id=#{ID} limit 1")
    Custom getCustom(int ID);
}
