package com.example.authenticate.Service;

import com.example.authenticate.Entity.ProductEntity;
import com.example.authenticate.Entity.SignupEntity;
import com.example.authenticate.Entity.UserEntity;
import com.example.authenticate.model.ProductDetail;
import com.example.authenticate.model.Signup;
import com.example.authenticate.repository.HomeRepository;
import com.example.authenticate.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class SellService {
    @Autowired
    private ProductRepository productRepository;

    public Map<String,String> save(ProductDetail pd){
        Map<String, String> response = new HashMap<>();
        ProductEntity pe=new ProductEntity();
        BeanUtils.copyProperties(pd, pe);

        Optional<ProductEntity> res=productRepository.findById(pe.getSellerId());

        if(res.isPresent()){
            response.put("status","error");
            response.put("message","Product already exists");

            return response;
        }

        response.put("status","ok");
        response.put("message","product added successfully");
        productRepository.save(pe);

        return response;
    }
}
