package id.co.uas.rest;

import id.co.uas.model.ProductModelInd068;
import id.co.uas.response.DtoResponse;
import id.co.uas.service.ProductServiceInd068;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produk")
@CrossOrigin
public class ProductRestInd068 {

    @Autowired
    private ProductServiceInd068 serviceProduct;

    public ProductRestInd068(ProductServiceInd068 serviceProduct){
        this.serviceProduct = serviceProduct;
    }

    @GetMapping("/getAllProduct")
    public DtoResponse getAllProduct(){
        return serviceProduct.getAllProduct();
    }

    @GetMapping("/getProductById/{idProduk}")
    public DtoResponse getProductById(@PathVariable Integer idProduk){
        return serviceProduct.getProductById(idProduk);
    }

    @PostMapping("/saveProduct")
    public DtoResponse saveProduct(@RequestBody ProductModelInd068 product){
        return serviceProduct.saveProduct(product);
    }

    @PostMapping("/updateProduct")
    public DtoResponse updateProduct(@RequestBody ProductModelInd068 product){
        return serviceProduct.updateProduct(product);
    }

    @PostMapping("/deleteProduct/{idProduk}")
    public DtoResponse deleteProduct(@PathVariable Integer idProduk){
        return serviceProduct.deleteProduct(idProduk);
    }

}
