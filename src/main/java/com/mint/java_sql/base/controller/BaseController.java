package com.mint.java_sql.base.controller;

import com.mint.java_sql.base.dto.BaseDto;
import com.mint.java_sql.base.service.BaseService;
import com.mint.java_sql.dto.response.ResponseData;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public abstract class BaseController<Dto extends BaseDto, Service extends BaseService<Dto>> {
    public abstract Service service();

    // Build Add REST API
    @PostMapping
    public ResponseData<Dto> create(@RequestBody Dto entity) {
        Dto saved = service().create(entity);
        return new ResponseData<Dto>(HttpStatus.CREATED.value(), "Created successfully", saved);
    }

    // Build Get REST API
    @SneakyThrows
    @GetMapping("{id}")
    public ResponseData<Dto> getById(@PathVariable("id") Long id) {
        Dto dto = service().getById(id);
        return new ResponseData<>(HttpStatus.OK.value(), "", dto);
    }

    // Build Get All REST API
    @GetMapping
    public ResponseData<List<Dto>> getAll() {
        List<Dto> listDto = service().getAll();
        return new ResponseData<>(HttpStatus.OK.value(), "", listDto);
    }

    // Build Get  REST API
    @SneakyThrows
    @PutMapping("{id}")
    public ResponseData<Dto> update(@PathVariable("id") Long id, @RequestBody Dto dto) {
        Dto newDto = service().update(id, dto);
        return new ResponseData<>(HttpStatus.OK.value(), "", newDto);
    }

    // Build Get  REST API
    @SneakyThrows
    @DeleteMapping("{id}")
    public ResponseData<String> delete(@PathVariable("id") Long id) {
        service().delete(id);
        return new ResponseData<>(HttpStatus.OK.value(), "Deleted successfully!");
    }
}
