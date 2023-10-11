package com.example.authenticate.Controller;

import com.example.authenticate.Entity.OfferEntity;
import com.example.authenticate.Entity.ProductEntity;
import com.example.authenticate.Entity.PurchasedEntity;
import com.example.authenticate.Service.MarketService;
import com.example.authenticate.model.Offer;
import com.example.authenticate.model.ProductDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class market {

    @Autowired
    private MarketService marketService;
    @GetMapping("/market")
    public List<ProductDetail> getListOfProducts(){
        return marketService.getProducts();
    }



    @PutMapping("/bid")
    public void setBid(@RequestBody Offer offer){
        Offer ac=new Offer();
        System.out.println(offer.toString());
        ac.setId(UUID.randomUUID().toString());
        ac.setBuyerId(offer.getBuyerId());
        ac.setSellerId(offer.getSellerId());
        ac.setProductId(offer.getProductId());
        ac.setOffer(offer.getOffer());

        marketService.placeBid(ac);
    }

    @GetMapping("/offerReceived/{id}")
    public List<OfferEntity> offerReceived(@PathVariable String id){
        return marketService.offerMade(id);
    }


    @GetMapping("/getProduct/{id}")
    public Optional<ProductEntity> getProduct(@PathVariable String id){
        return marketService.getProduct(id);
    }

    @PutMapping("/denyOffer/{id}")
    public void deleteOfferById(@PathVariable String id){
        marketService.deleteOffer(id);
    }

    @PutMapping("/acceptOffer/{id}/{offerId}")
    public void acceptOfferById(@PathVariable String id,@PathVariable String offerId){
        System.out.println("Product id:"+id+"Offer id:"+offerId);
        marketService.acceptOffer(id,offerId);
    }

    @GetMapping("/purchase/{id}")
    public List<Optional<ProductEntity>> getPurchase(@PathVariable String id){
        return marketService.getPurchaseList(id);
    }
}
