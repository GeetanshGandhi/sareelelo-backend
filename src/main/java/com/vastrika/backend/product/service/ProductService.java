package com.vastrika.backend.product.service;

import com.vastrika.backend.business.model.Business;
import com.vastrika.backend.business.repository.BusinessRepository;
import com.vastrika.backend.category.model.Category;
import com.vastrika.backend.city.model.City;
import com.vastrika.backend.city.repository.CityRepository;
import com.vastrika.backend.product.model.Product;
import com.vastrika.backend.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {

    public String uploadImage(String path, MultipartFile file, int productId) throws IOException {
        //getting file name
        String fileName = file.getOriginalFilename();

        //create new image name:
        assert fileName != null;
        String imageName = productId + ".jpg";
//                +fileName.substring(fileName.lastIndexOf("."));

        //full path of image target folder
        String filePath = path + File.separator + imageName;

        //create folder if not created
        File file1 = new File(path);
        if(!file1.exists()) file1.mkdir();

        //copy input image to given file path
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return imageName;
    }

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    BusinessRepository businessRepository;
    public Product saveProductToDB(Product product, Business business){
        product.setCity(business.getCity());
        product.setCategory(business.getCategory());
        product.setBusiness(business);
        return productRepository.save(product);
    }

    public Product editProduct(Product newp){
        Product old = productRepository.findById(newp.getProductId()).get();
        old.setPrice(newp.getPrice());
        old.setDiscount(newp.getDiscount());
        old.setDescription(newp.getDescription());
        old.setProductName(newp.getProductName());
        return productRepository.save(old);
    }

    public List<Product> getByOwner(String owner){
        try{
            return productRepository.findAllByBusiness(businessRepository.findById(owner).get());
        } catch (NoSuchElementException e){
            return new ArrayList<>();
        }
    }

    //delete if image not uploaded
    public String deleteProduct(Product product){
        productRepository.delete(product);
        return "Success";
    }
}
