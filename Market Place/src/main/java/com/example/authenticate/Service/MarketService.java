package com.example.authenticate.Service;

import com.example.authenticate.Entity.OfferEntity;
import com.example.authenticate.Entity.ProductEntity;
import com.example.authenticate.Entity.PurchasedEntity;
import com.example.authenticate.model.Offer;
import com.example.authenticate.model.ProductDetail;
import com.example.authenticate.repository.OfferRepository;
import com.example.authenticate.repository.ProductRepository;
import com.example.authenticate.repository.PurchaseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MarketService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDetail> getProducts(){
        List<ProductEntity> pe=productRepository.findAll();
        List<ProductDetail> pdList=new ArrayList<ProductDetail>();
        for (ProductEntity productEntity : pe) {
            if (!productEntity.getSold()) {
                ProductDetail pd = new ProductDetail();
                BeanUtils.copyProperties(productEntity, pd);
                pdList.add(pd);
            }
        }

        return pdList;
    }

    @Autowired
    private OfferRepository offerRepository;
    public void placeBid(Offer of){
        OfferEntity oe=new OfferEntity();
        System.out.println(of.getOffer());
        BeanUtils.copyProperties(of,oe);
        offerRepository.save(oe);

    }

    public List<OfferEntity> offerMade(String id){
        List<OfferEntity> oe =offerRepository.findBySellerId(id);
//        List<Offer> of=new ArrayList<Offer>();
//        for(OfferEntity ofe: oe){
//            Offer o=new Offer();
//            BeanUtils.copyProperties(oe,o);
//            System.out.println(o.getProductId());
//            of.add(o);
//        }

        return oe;
    }

    public Optional<ProductEntity> getProduct(String id){
        Optional<ProductEntity> pe=productRepository.findById(id);
//        ProductDetail pd=new ProductDetail();
//        BeanUtils.copyProperties(pe,pd);
        if(pe.isPresent()){
            System.out.println(pe.get().getName());
        }
        else{
            System.out.println("nothing found");
        }
        return pe;
    }

    public void deleteOffer(String id){
        System.out.println("delete offer"+id);
        offerRepository.deleteById(id);
        return;
    }

    @Autowired
    private SellService sellService;

    @Autowired
    private PurchaseRepository purchaseRepository;
    public void acceptOffer(String id, String offerId){
        System.out.println(offerId);
        List<OfferEntity> or=offerRepository.findByProductId(id);
        Optional<OfferEntity>oe=offerRepository.findById(offerId);
        offerRepository.deleteAllInBatch(or);

        Optional<ProductEntity> pe=productRepository.findById(id);

        if(pe.isPresent()){
            System.out.println("accept offer"+id);
            ProductEntity product = pe.get();
            product.setSold(true);
            System.out.println(product.getName()+product.getId());
            productRepository.save(product);
        }
        System.out.println("adding data to purchased table");
        if(oe.isPresent()){
            System.out.println("adding data to purchased table");
            PurchasedEntity purEnt=new PurchasedEntity();
            purEnt.setId(UUID.randomUUID().toString());
            purEnt.setBuyerId(oe.get().getBuyerId());
            purEnt.setSellerId(oe.get().getSellerId());
            purEnt.setProductId(oe.get().getProductId());
            purchaseRepository.save(purEnt);
        }
    }

    public List<Optional<ProductEntity>> getPurchaseList(String id){
        List<PurchasedEntity>pe=purchaseRepository.findByBuyerId(id);
        List<Optional<ProductEntity>> listOfPurchase =new ArrayList<>();
        for(PurchasedEntity tempPe:pe){
            Optional<ProductEntity> ope=productRepository.findById(tempPe.getProductId());
            listOfPurchase.add(ope);
        }
        return listOfPurchase;
    }
}
