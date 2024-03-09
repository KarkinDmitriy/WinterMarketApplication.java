package ru.geekbrains.winter.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.winter.market.api.ProductDto;
import ru.geekbrains.winter.market.api.ResourceNotFoundException;
import ru.geekbrains.winter.market.core.converters.ProductConverter;

import ru.geekbrains.winter.market.core.entities.Product;
import ru.geekbrains.winter.market.core.services.ProductService;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;


    @GetMapping
    public List<ProductDto> findAllProducts() {
       return productService.findAll().stream().map(productConverter::entityToDto).collect(Collectors.toList());
    }


//    @GetMapping("/{id}")
//    public ResponseEntity<?> findProductById(@PathVariable Long id) {
//        Optional<Product> product= productService.findById(id);
//        if (!product.isPresent()) {
//            ResponseEntity<AppError> err = new ResponseEntity<>(new AppError(NOT_FOUND.value(),"Продукт не найден, id:"+id), NOT_FOUND);
//            return err;
//        }
//        return  new ResponseEntity<>(product.get(), HttpStatus.OK);
//
//    }


//    @GetMapping("/{id}")
//    public Product findProductById(@PathVariable Long id) {
//        return  productService.findById(id).orElseThrow(()-> new ResourceNotFoundException("Продукт не найден, id:"+id));

//    }
//    @ExceptionHandler
//    public ResponseEntity<AppError> exceptionHandler(ResourceNotFoundException e){
//        return  new ResponseEntity<>(new AppError(NOT_FOUND.value(), e.getMessage()), NOT_FOUND);


    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        Product p = productService.findById(id).orElseThrow(()-> new ResourceNotFoundException("Продукт не найден, id:"+id));
        return  productConverter.entityToDto(p);

    }

    @PostMapping
    public ProductDto createNewProduct(@RequestBody ProductDto productDto){
       Product p = productService.createNewProduct(productDto);
            return  productConverter.entityToDto(p);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }

}
