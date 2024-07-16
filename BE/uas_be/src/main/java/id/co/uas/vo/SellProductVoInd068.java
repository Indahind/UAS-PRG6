package id.co.uas.vo;

import id.co.uas.model.SellProductModelInd068;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellProductVoInd068 {

    private String idTransaksi;

    private Date tanggalTransaksi;

    private Integer idProduk;

    private String namaProduk;

    private Integer idCabang;

    private String namaCabang;

    private Integer qty;

    private Double harga;

    public SellProductVoInd068(SellProductModelInd068 sellProductModelInd068){
        this.idTransaksi = sellProductModelInd068.getIdTransaksi();
        this.tanggalTransaksi = sellProductModelInd068.getTanggalTransaksi();
        this.idProduk = sellProductModelInd068.getIdProduk();
        this.idCabang = sellProductModelInd068.getIdCabang();
        this.qty = sellProductModelInd068.getQty();
        this.harga = sellProductModelInd068.getTotalHarga();
    }

}
