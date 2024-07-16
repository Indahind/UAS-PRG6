package id.co.uas.dao.impl;

import id.co.uas.dao.ProductDaoInd068;
import id.co.uas.model.ProductModelInd068;
import id.co.uas.repository.ProductRepositoryInd068;
import id.co.uas.vo.ProductVoInd068;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDaoImplInd068 implements ProductDaoInd068 {

    @Autowired
    private ProductRepositoryInd068 repository;

    @Override
    public List<ProductVoInd068> getAllProduct() {
        Iterable<ProductModelInd068> product = repository.getAllProduct();
        List<ProductVoInd068> productVos = new ArrayList<>();
        for (ProductModelInd068 item: product){
            ProductVoInd068 productVo = new ProductVoInd068(item);
            productVos.add(productVo);
        }
        return productVos;
    }

    @Override
    public ProductVoInd068 getProductById(Integer idProduct) {
        ProductModelInd068 productDb = repository.findById(idProduct).orElse(null);
        ProductVoInd068 data = null;
        if (productDb != null) {
            data = new ProductVoInd068(productDb);
        }
        return data;
    }
}
