package id.co.uas.vo;

import id.co.uas.model.Transaksi;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class TransaksiVo implements Serializable {
    private Integer IdLayanan;
    private Integer QtyTransaksi;
    private Integer HargaTotalTransaksi;
    private Date TanggalTransaksi;

    public TransaksiVo() {
    }

    public TransaksiVo(Transaksi transaksi) {
       this.IdLayanan = transaksi.getIdLayanan();
       this.QtyTransaksi = transaksi.getQtyTransaksi();
       this.HargaTotalTransaksi = transaksi.getHargaTotalTransaksi();
       this.TanggalTransaksi = transaksi.getTanggalTransaksi();
    }
}
