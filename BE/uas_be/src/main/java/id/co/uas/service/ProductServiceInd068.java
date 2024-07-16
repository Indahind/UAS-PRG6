package id.co.uas.service;

import id.co.uas.model.ProductModelInd068;
import id.co.uas.response.DtoResponse;

public interface ProductServiceInd068 {

    public DtoResponse getAllProduct();

    public DtoResponse getProductById(Integer idProduct);

    public DtoResponse saveProduct(ProductModelInd068 product);

    public DtoResponse updateProduct(ProductModelInd068 product);

    public DtoResponse deleteProduct(Integer idProduct);

}
