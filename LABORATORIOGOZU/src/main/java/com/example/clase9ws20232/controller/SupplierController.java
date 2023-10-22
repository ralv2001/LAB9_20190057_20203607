package com.example.clase9ws20232.controller;

import com.example.clase9ws20232.entity.Supplier;
import com.example.clase9ws20232.repository.SupplierRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/supplier")
public class SupplierController {

    final SupplierRepository supplierRepository;

    public SupplierController(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @GetMapping(value = "", produces = MediaType.TEXT_PLAIN_VALUE + "; charset=utf-8")
    public List<Supplier> listar() {
        return supplierRepository.findAll();
    }

}
