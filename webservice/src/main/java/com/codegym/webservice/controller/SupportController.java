package com.codegym.webservice.controller;

import com.codegym.dao.model.Support;
import com.codegym.service.SupportService;
import com.codegym.webservice.payload.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "/api/v1/supports")
public class SupportController {
    private SupportService supportService;

    @Autowired
    public void setSupportService(SupportService supportService) {
        this.supportService = supportService;
    }

    //-------------------Get All Supports--------------------------------------------------------

    @GetMapping()
    public ResponseEntity<Object> findAllSupports(Pageable pageable){
        return new ResponseEntity<>(supportService.findAll(pageable), HttpStatus.OK);
    }

    //-------------------Get One Support By Id--------------------------------------------------------

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findSupportById(@PathVariable("id")Long id){
        Support support = supportService.findById(id);
        if (support == null){
            return new ResponseEntity<>(new ApiResponse(false, "Can not find support!"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(support,HttpStatus.OK);
    }

    //-------------------Create a Support--------------------------------------------------------

    @PostMapping
    public ResponseEntity<Object> createSupport(@RequestBody Support support) {
        supportService.save(support);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(support.getId()).toUri();
        return ResponseEntity.created(location)
                .body(support);
    }

    //-------------------Update a Support--------------------------------------------------------
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> updateSupport(@PathVariable Long id, @RequestBody Support support) {
        support.setId(id);
        if (supportService.findById(id) == null) {
            return new ResponseEntity<>(new ApiResponse(false, "Can not find this support!"), HttpStatus.NOT_FOUND);
        }
        supportService.save(support);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(support.getId()).toUri();
        return ResponseEntity.created(location)
                .body(support);
    }

    //-------------------Delete a Support--------------------------------------------------------

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSupport(@PathVariable("id") Long id){
        Support support = supportService.findById(id);
        if (support == null){
            return new ResponseEntity<>(new ApiResponse(false, "Can not find support!"), HttpStatus.NOT_FOUND);
        }
        supportService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
