package id.co.uas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "TRPENJUALANSALON")
public class Transaksi {
    @Id
    @Column(name = "IdTransaksi")
    private String IdTransaksi;

    @Column(name = "IdLayanan")
    private Integer IdLayanan;

    @Column(name = "QtyTransaksi")
    private Integer QtyTransaksi;

    @Column(name = "HargaTotalTransaksi")
    private Integer HargaTotalTransaksi;

    @Column(name = "TanggalTransaksi")
    private Date TanggalTransaksi;

    public Transaksi() {
    }

    public Transaksi(String idTransaksi, Integer idLayanan, Integer qtyTransaksi, Integer hargaTotalTransaksi, Date tanggalTransaksi) {
        IdTransaksi = idTransaksi;
        IdLayanan = idLayanan;
        QtyTransaksi = qtyTransaksi;
        HargaTotalTransaksi = hargaTotalTransaksi;
        TanggalTransaksi = tanggalTransaksi;
    }
}
