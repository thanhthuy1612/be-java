package com.mint.java_sql.base.mapper;

import com.mint.java_sql.base.dto.BaseDto;
import com.mint.java_sql.base.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseMapper<Entity extends BaseEntity, Dto extends BaseDto> {
    @Autowired
    public abstract Dto mapToDto(Entity entity);

    @Autowired
    public abstract Entity mapToEntity(Dto dto);
}
