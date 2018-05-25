package com.blueinfinite.mapper;

import com.blueinfinite.model.Custom;
import org.springframework.stereotype.Repository;

/**
 * 客户
 */
@Repository
public interface CustomMapper {
//    @Select("select * from sync_custom where id=#{ID}")
    Custom getCustom(int ID);
}
