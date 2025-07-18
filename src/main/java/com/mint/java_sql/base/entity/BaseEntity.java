package com.mint.java_sql.base.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
    public BaseEntity(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
}
