package com.mint.java_sql.base.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseDto {
    public BaseDto(){}

    public Long id;
}
