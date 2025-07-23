package com.mint.java_sql.base.service.impl;

import com.mint.java_sql.base.dto.BaseDto;
import com.mint.java_sql.base.entity.BaseEntity;
import com.mint.java_sql.base.mapper.BaseMapper;
import com.mint.java_sql.base.repository.BaseRepository;
import com.mint.java_sql.base.service.BaseService;
import com.mint.java_sql.exception.ResourceNotFoundException;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.List;
import java.util.stream.Collectors;

@Service
public abstract class BaseServiceImpl<Entity extends BaseEntity, Dto extends BaseDto, Repository extends BaseRepository<Entity>, Mapper extends BaseMapper<Entity, Dto>> implements BaseService<Dto> {
    public abstract Repository repository();

    public abstract Mapper mapper();

    @Override
    public Dto create(Dto dto) {
        Entity entity = mapper().mapToEntity(dto);
        Entity saved = repository().save(entity);

        return mapper().mapToDto(saved);
    }

    @SneakyThrows
    @Override
    public Dto getById(Long id) throws ResourceNotFoundException {
        Entity entity = repository().findById(id)
                .orElseThrow(() -> {
                    System.out.println("Entity"+ id);
                    return new ResourceNotFoundException("Is not exits with given id: " + id);
                });
        return mapper().mapToDto(entity);
    }

    @Override
    public List<Dto> getAll() {
        List<Entity> entities = repository().findAll();
        return entities.stream().map(mapper()::mapToDto).collect(Collectors.toList());
    }

    @SneakyThrows
    @Override
    public Dto update(Long id, Dto dto) throws ResourceNotFoundException {
        repository().findById(id).orElseThrow(() -> new ResourceNotFoundException("Is not exits with given id: " + id));
        Entity newEntity = mapper().mapToEntity(dto);
        newEntity.setId(id);
        Entity updated = repository().save(newEntity);
        return mapper().mapToDto(updated);
    }

    @SneakyThrows
    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        repository().findById(id).orElseThrow(() -> new ResourceNotFoundException("Is not exits with given id: " + id));
        repository().deleteById(id);
    }
}
