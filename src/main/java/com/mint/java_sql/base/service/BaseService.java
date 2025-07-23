package com.mint.java_sql.base.service;

import com.mint.java_sql.base.dto.BaseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BaseService<Dto extends BaseDto> {
    Dto create(Dto dto);

    Dto getById(Long id);

    List<Dto> getAll();

    Dto update(Long id, Dto dto);

    void delete(Long id);
}
