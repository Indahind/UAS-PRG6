package id.co.uas.service;

import id.co.uas.model.SellProductModelInd068;
import id.co.uas.response.DtoResponse;

public interface SellProductServiceInd068 {

    public DtoResponse saveTransaksi(SellProductModelInd068 transaksi);

    public DtoResponse getAllTransaksi();

    public DtoResponse getAllCabang();

    public DtoResponse getLaporan();

}
