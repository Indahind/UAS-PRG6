package id.co.uas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Table(name = "DKYTRSELLPRODUCT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellProductModelInd068 {

    @Id
    @Column(name = "TranscId")
    private String idTransaksi;

    @Column(name = "TranscDate")
    private Date tanggalTransaksi;

    @Column(name = "TranscProdId")
    private Integer idProduk;

    @Column(name = "TranscBranchId")
    private Integer idCabang;

    @Column(name = "TranscQty")
    private Integer qty;

    @Column(name = "TranscPrice")
    private Double totalHarga;

}
