package com.rdsic.back_end_e_tool.controller;



import com.rdsic.back_end_e_tool.entities.JsonResult;
import com.rdsic.back_end_e_tool.service.BaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.Date;

public class BaseCommand<T> {
    protected final BaseService<T> service;

    public BaseCommand(BaseService<T> service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<JsonResult> upload(@RequestBody T data) throws Exception {
        Field[] fields = data.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().equalsIgnoreCase("id")) {
                field.set(data, null);
            }
            if (field.getName().equalsIgnoreCase("created")) {
                field.set(data, new Date().getTime());
            }
        }
        data = service.save(data);
        return JsonResult.success(data);
    }

    @PutMapping
    public ResponseEntity<JsonResult> update(@RequestBody T data) throws Exception {
        return JsonResult.success(service.save(data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResult> delete(@PathVariable("id") int id) throws Exception {
        service.delete(id);
        return JsonResult.success("Delete ok");
    }

    @DeleteMapping
    public ResponseEntity<JsonResult> deleteEmbeddedId(@RequestBody Object id) throws Exception {
        service.delete(id);
        return JsonResult.success("Delete ok");
    }
}
