package id.co.uas.dao;

import id.co.uas.vo.LaporanTransaksiVoInd068;
import id.co.uas.vo.SellProductVoInd068;

import java.util.List;

public interface SellProductDaoInd068 {

    public List<SellProductVoInd068> getAllSellProduct();

    public List<LaporanTransaksiVoInd068> getLaporan();

    public String getLastId(String param);

}
