package id.co.uas.service.impl;

import id.co.uas.dao.ProductDaoInd068;
import id.co.uas.model.ProductModelInd068;
import id.co.uas.repository.ProductRepositoryInd068;
import id.co.uas.response.DtoResponse;
import id.co.uas.service.ProductServiceInd068;
import id.co.uas.vo.ProductVoInd068;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class ProductServiceImplInd068 implements ProductServiceInd068 {

    @Autowired
    private ProductDaoInd068 daoProduct;

    @Autowired
    private ProductRepositoryInd068 repositoryProduct;

    @Override
    public DtoResponse getAllProduct() {
        try {
            List<ProductVoInd068> data = daoProduct.getAllProduct();
            if (data != null) return new DtoResponse(200, data);
            return new DtoResponse(200, null, "Empty Data");
        } catch (Exception e) {
            return new DtoResponse(500, null, "Not Found");
        }
    }

    @Override
    public DtoResponse getProductById(Integer idProduct) {
        try {
            ProductVoInd068 data = daoProduct.getProductById(idProduct);
            if (data != null) {
                return new DtoResponse(200, data);
            }else {
                return new DtoResponse(404, null, "mNotFound");
            }
        } catch (Exception e) {
            return new DtoResponse(500, null, "mNotFound");
        }
    }

    @Override
    public DtoResponse saveProduct(ProductModelInd068 product) {
        try {
            product.setStatus(1);
            repositoryProduct.save(product);
            return new DtoResponse(200, product, "mCreateSuccess");
        } catch (Exception e) {
            return new DtoResponse(500, null, "mCreateFailed");
        }
    }

    @Override
    public DtoResponse updateProduct(ProductModelInd068 product) {
        try {
            ProductModelInd068 existingData = repositoryProduct.findById(product.getIdProduk()).orElse(null);
            existingData.setNamaProduk(product.getNamaProduk());
            existingData.setHargaProduk(product.getHargaProduk());
            existingData.setStokProduk(product.getStokProduk());

            ProductModelInd068 updatedData = repositoryProduct.save(existingData);
            if (updatedData != null)
                return new DtoResponse(200, updatedData, "mUpdateSuccess");
            else
                return new DtoResponse(404, null, "mNotFound");
        } catch (Exception e) {
            return new DtoResponse(500, null, "mUpdateFailed");
        }
    }

    @Override
    public DtoResponse deleteProduct(Integer idProduct) {
        try {
            ProductModelInd068 existingData = repositoryProduct.findById(idProduct).orElse(null);
            existingData.setStatus(0);

            ProductModelInd068 deletedData = repositoryProduct.save(existingData);
            if (deletedData != null)
                return new DtoResponse(200, deletedData, "mDeleteSuccess");
            else
                return new DtoResponse(404, null, "mNotFound");
        } catch (Exception e) {
            return new DtoResponse(500, null, "mDeleteFailed");
        }
    }

}
