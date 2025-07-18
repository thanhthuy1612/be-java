package com.mint.java_sql.base.repository;

import com.mint.java_sql.base.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseRepository<Entity extends BaseEntity> extends JpaRepository<Entity, Long> {
}
