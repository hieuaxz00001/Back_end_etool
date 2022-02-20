package com.rdsic.back_end_e_tool.controller;


import com.rdsic.back_end_e_tool.entities.JsonResult;
import com.rdsic.back_end_e_tool.entities.PageJson;
import com.rdsic.back_end_e_tool.service.BaseService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class BaseFinder<T> extends Thread{
    public final BaseService<T> service;

    public BaseFinder(BaseService<T> service) {
        this.service = service;
    }



    @GetMapping("/{id}")
    public ResponseEntity<JsonResult> findById(@PathVariable("id") Integer id) throws Exception {
        return JsonResult.success(service.findById(id));
    }


    @GetMapping
    public ResponseEntity<JsonResult> search(@RequestParam(value = "queries", required = false) List<String> queries,
                                          @RequestParam(value = "sort", required = false) String sort) throws Exception {
        System.out.println("queryyyy:        " + queries.toString());
        return JsonResult.success(service.filterWithSort(queries, sort));
    }

    @GetMapping("/page")
    public ResponseEntity<JsonResult> searchWithPage(@RequestParam(value = "queries", required = false) List<String> queries,
                                                      @RequestParam(value = "sort", required = false) String sort, Pageable pageable) throws Exception {

        return JsonResult.success(service.filterByPageWithSort(queries, pageable, sort));
    }

    @GetMapping("/page-counter")
    public ResponseEntity<JsonResult> countPage(@RequestParam(value = "queries", required = false) List<String> queries,
                                             @RequestParam(value = "size", required = true) int size) throws Exception {
        return JsonResult.success(service.countPage(queries, size));
    }
}
