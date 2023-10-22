package com.example.clase9ws20232.controller;

import com.example.clase9ws20232.entity.Product;
import com.example.clase9ws20232.entity.Supplier;
import com.example.clase9ws20232.repository.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductoController {

    final ProductRepository productRepository;

    public ProductoController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //LISTAR
    @GetMapping(value = {"/list", ""}, produces = MediaType.TEXT_PLAIN_VALUE + "; charset=utf-8")
    public List<Product> listaProductos() {
        return productRepository.findAll();
    }

    //OBTENER
    @GetMapping(value = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE + "; charset=utf-8")
    public ResponseEntity<HashMap<String, Object>> buscarProducto(@PathVariable("id") String idStr) {


        try {
            int id = Integer.parseInt(idStr);
            Optional<Product> byId = productRepository.findById(id);

            HashMap<String, Object> respuesta = new HashMap<>();

            if (byId.isPresent()) {
                respuesta.put("result", "ok");
                respuesta.put("producto", byId.get());
            } else {
                respuesta.put("result", "no existe");
            }
            return ResponseEntity.ok(respuesta);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // CREAR /product y /product/
    @PostMapping(value = {"", "/"}, produces = MediaType.TEXT_PLAIN_VALUE + "; charset=utf-8")
    public ResponseEntity<HashMap<String, Object>> guardarProducto(
            @RequestBody Product product,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> responseJson = new HashMap<>();

        productRepository.save(product);
        if (fetchId) {
            responseJson.put("id", product.getId());
        }
        responseJson.put("estado", "creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseJson);
    }

    // ACTUALIZAR
    @PutMapping(value = {"", "/"}, produces = MediaType.TEXT_PLAIN_VALUE + "; charset=utf-8", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<HashMap<String, Object>> actualizar(Product productRecibido) {

        HashMap<String, Object> rpta = new HashMap<>();

        if (productRecibido.getId() != null && productRecibido.getId() > 0) {

            Optional<Product> byId = productRepository.findById(productRecibido.getId());
            if (byId.isPresent()) {
                Product productFromDb = byId.get();

                if (productRecibido.getProductName() != null)
                    productFromDb.setProductName(productRecibido.getProductName());

                if (productRecibido.getUnitPrice() != null)
                    productFromDb.setUnitPrice(productRecibido.getUnitPrice());

                if (productRecibido.getUnitsInStock() != null)
                    productFromDb.setUnitsInStock(productRecibido.getUnitsInStock());

                if (productRecibido.getUnitsOnOrder() != null)
                    productFromDb.setUnitsOnOrder(productRecibido.getUnitsOnOrder());

                if (productRecibido.getSupplier() != null)
                    productFromDb.setSupplier(productRecibido.getSupplier());

                if (productRecibido.getCategory() != null)
                    productFromDb.setCategory(productRecibido.getCategory());

                if (productRecibido.getQuantityPerUnit() != null)
                    productFromDb.setQuantityPerUnit(productRecibido.getQuantityPerUnit());

                if (productRecibido.getReorderLevel() != null)
                    productFromDb.setReorderLevel(productRecibido.getReorderLevel());

                if (productRecibido.getDiscontinued() != null)
                    productFromDb.setDiscontinued(productRecibido.getDiscontinued());

                productRepository.save(productFromDb);
                rpta.put("result", "ok");
                return ResponseEntity.ok(rpta);
            } else {
                rpta.put("result", "error");
                rpta.put("msg", "El ID del producto enviado no existe");
                return ResponseEntity.badRequest().body(rpta);
            }
        } else {
            rpta.put("result", "error");
            rpta.put("msg", "debe enviar un producto con ID");
            return ResponseEntity.badRequest().body(rpta);
        }
    }

    // /Product?id
    @DeleteMapping(value = "", produces = MediaType.TEXT_PLAIN_VALUE + "; charset=utf-8")
    public ResponseEntity<HashMap<String, Object>> borrar(@RequestParam("id") String idStr){

        try{
            int id = Integer.parseInt(idStr);

            HashMap<String, Object> rpta = new HashMap<>();

            Optional<Product> byId = productRepository.findById(id);
            if(byId.isPresent()){
                productRepository.deleteById(id);
                rpta.put("result","ok");
            }else{
                rpta.put("result","no ok");
                rpta.put("msg","el ID enviado no existe");
            }

            return ResponseEntity.ok(rpta);
        }catch (NumberFormatException e){
            return ResponseEntity.badRequest().body(null);
        }
    }


    @GetMapping(value = "/prueba", produces = MediaType.APPLICATION_JSON_VALUE)
    public String prueba() {
        return "{\"msg\": \"esto es una prueba\"}";
    }

    @GetMapping("/buscar/{id}")
    public Product buscarF1(@PathVariable("id") int id) {
        Optional<Product> byId = productRepository.findById(id);
        return byId.orElse(null);
    }

    /*
    si id existe -> {result: "ok", producto: <producto>}
    si el id no existe -> {result: "no existe"}
     */
    @GetMapping("/buscar2/{id}")
    public HashMap<String, Object> buscarF2(@PathVariable("id") int id) {

        HashMap<String, Object> respuesta = new HashMap<>();

        Optional<Product> byId = productRepository.findById(id);
        if (byId.isPresent()) {
            respuesta.put("result", "ok");
            respuesta.put("producto", byId.get());
            return respuesta;
        } else {
            respuesta.put("result", "no existe");
            return respuesta;
        }
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String, String>> gestionException(HttpServletRequest request) {
        HashMap<String, String> responseMap = new HashMap<>();
        if (request.getMethod().equals("POST") || request.getMethod().equals("PUT")) {
            responseMap.put("estado", "error");
            responseMap.put("msg", "Debe enviar un producto");
        }
        return ResponseEntity.badRequest().body(responseMap);
    }


}
