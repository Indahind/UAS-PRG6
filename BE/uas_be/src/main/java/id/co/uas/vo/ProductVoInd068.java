package id.co.uas.vo;

import id.co.uas.model.ProductModelInd068;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductVoInd068 {

    private Integer idProduk;

    private String namaProduk;

    private String deskripsiProduk;

    private Double hargaProduk;

    private Integer stokProduk;

    private Integer status;

    public ProductVoInd068(ProductModelInd068 productModelInd068){
        this.idProduk = productModelInd068.getIdProduk();
        this.namaProduk = productModelInd068.getNamaProduk();
        this.deskripsiProduk = productModelInd068.getDeskripsiProduk();
        this.hargaProduk = productModelInd068.getHargaProduk();
        this.stokProduk = productModelInd068.getStokProduk();
        this.status = productModelInd068.getStatus();
    }

}
