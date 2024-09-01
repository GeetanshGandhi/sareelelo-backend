package com.vastrika.backend.product.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vastrika.backend.business.model.Business;
import com.vastrika.backend.product.model.Product;
import com.vastrika.backend.product.payload.FileResponse;
import com.vastrika.backend.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Value("${project.image}")
    private String imageFilePath;

    @PostMapping("/saveNew")
    public ResponseEntity<FileResponse> saveNewProduct(
            @RequestParam("productDet") String productString,
            @RequestParam("businessDet") String businessString,
            @RequestParam("productImage") MultipartFile inputImage){
        //save to db
        ObjectMapper mapper = new ObjectMapper();
        Product product = null;
        try{
            product = mapper.readValue(productString, Product.class);
        } catch (JsonProcessingException e){
            return new ResponseEntity<>(new FileResponse(null, "Invalid product"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Business business = null;
        try{
            business = mapper.readValue(businessString, Business.class);
        } catch(JsonProcessingException e){
            return new ResponseEntity<>(new FileResponse(null, "Invalid product"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Product dbOut = productService.saveProductToDB(product, business);

        //upload image to file system
        String imageFileName;
        try {
            imageFileName = productService.uploadImage(imageFilePath, inputImage, dbOut.getProductId());
        } catch (IOException e) {
            e.printStackTrace();
            productService.deleteDueToException(dbOut.getProductId());
            return new ResponseEntity<>(new FileResponse(null, "Invalid image"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new FileResponse(imageFileName, "Successfully Uploaded"), HttpStatus.OK);
    }

    @PostMapping("/getByOwner")
    public List<Product> getProductsByOwner(@RequestBody String ownerEmail){
        return productService.getByOwner(ownerEmail);
    }
}
