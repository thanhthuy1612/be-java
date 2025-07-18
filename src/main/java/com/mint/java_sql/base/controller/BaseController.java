package com.mint.java_sql.base.controller;

import com.mint.java_sql.base.dto.BaseDto;
import com.mint.java_sql.base.service.BaseService;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public abstract class BaseController<Dto extends BaseDto, Service extends BaseService<Dto>> {
    public abstract Service service();

    // Build Add REST API
    @PostMapping
    public ResponseEntity<Dto> create(@RequestBody Dto entity) {
        Dto saved = service().create(entity);
        return new ResponseEntity<Dto>(saved, HttpStatus.CREATED);
    }

    // Build Get REST API
    @SneakyThrows
    @GetMapping("{id}")
    public ResponseEntity<Dto> getById(@PathVariable("id") Long id) {
        Dto dto = service().getById(id);
        return ResponseEntity.ok(dto);
    }

    // Build Get All REST API
    @GetMapping
    public ResponseEntity<List<Dto>> getAll() {
        List<Dto> listDto = service().getAll();
        return ResponseEntity.ok(listDto);
    }

    // Build Get  REST API
    @SneakyThrows
    @PutMapping("{id}")
    public ResponseEntity<Dto> update(@PathVariable("id") Long id, @RequestBody Dto dto) {
        Dto newDto = service().update(id, dto);
        return ResponseEntity.ok(newDto);
    }

    // Build Get  REST API
    @SneakyThrows
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        service().delete(id);
        return ResponseEntity.ok("Deleted successfully!");
    }
}
