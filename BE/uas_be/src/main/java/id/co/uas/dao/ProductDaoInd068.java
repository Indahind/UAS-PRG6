package id.co.uas.dao;

import id.co.uas.vo.ProductVoInd068;

import java.util.List;

public interface ProductDaoInd068 {

    public List<ProductVoInd068> getAllProduct();

    public ProductVoInd068 getProductById(Integer idProduct);

}
