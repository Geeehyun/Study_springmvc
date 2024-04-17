package org.fullstack4.springmvc.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
    @Select("Select now()")
    String getTime();
}
