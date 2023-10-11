package com.example.authenticate.Controller;

import com.example.authenticate.Service.SellService;
import com.example.authenticate.model.ProductDetail;
import com.example.authenticate.model.Signup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
public class Sell {

    @Autowired
    private SellService sellService;

    @PostMapping("/sell")
    public Map<String,String> sellProduct(@RequestBody ProductDetail productDetail){
        ProductDetail pd=new ProductDetail();

        pd.setSellerId(productDetail.getSellerId());
        pd.setName(productDetail.getName());
        pd.setAddress(productDetail.getAddress());
        pd.setPrice(productDetail.getPrice());
        pd.setDescription(productDetail.getDescription());
        pd.setSold(false);
        pd.setId(UUID.randomUUID().toString());
        return sellService.save(pd);
    }
}
