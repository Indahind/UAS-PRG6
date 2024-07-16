package id.co.uas.dao.impl;

import id.co.uas.constant.SellProductConstantInd068;
import id.co.uas.dao.SellProductDaoInd068;
import id.co.uas.model.CabangModelInd068;
import id.co.uas.model.ProductModelInd068;
import id.co.uas.model.SellProductModelInd068;
import id.co.uas.repository.CabangRepositoryInd068;
import id.co.uas.repository.ProductRepositoryInd068;
import id.co.uas.repository.SellProductRepositoryInd068;
import id.co.uas.vo.LaporanTransaksiVoInd068;
import id.co.uas.vo.SellProductVoInd068;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SellProductDaoImplInd068 implements SellProductDaoInd068 {

    @Autowired
    private SellProductRepositoryInd068 repositoryTransaksi;

    @Autowired
    private ProductRepositoryInd068 repositoryProduct;

    @Autowired
    private CabangRepositoryInd068 repositoryCabang;

    @Autowired
    private JdbcTemplate template;

    @Override
    public List<SellProductVoInd068> getAllSellProduct() {
        Iterable<SellProductModelInd068> sellProduct = repositoryTransaksi.getAllSellProduct();
        List<SellProductVoInd068> sellProductVos = new ArrayList<>();
        for (SellProductModelInd068 item: sellProduct){
            SellProductVoInd068 sellProductVo = new SellProductVoInd068(item);

            ProductModelInd068 product = repositoryProduct.findById(sellProductVo.getIdProduk()).orElse(null);
            CabangModelInd068 cabang = repositoryCabang.findById(sellProductVo.getIdCabang()).orElse(null);

            sellProductVo.setNamaProduk(product.getNamaProduk());
            sellProductVo.setNamaCabang(cabang.getNamaCabang());

            sellProductVos.add(sellProductVo);
        }
        return sellProductVos;
    }

    @Override
    public List<LaporanTransaksiVoInd068> getLaporan() {
        String sql = SellProductConstantInd068.qGetLaporan;
        List<LaporanTransaksiVoInd068> vos = template.query(sql, (resultSet, rowNum) -> {
            LaporanTransaksiVoInd068 vo = new LaporanTransaksiVoInd068();
            vo.setNamaProduk(resultSet.getString("namaProduk"));
            vo.setTotalQty(resultSet.getString("jumlah"));
            vo.setTotalHarga(resultSet.getString("totalHarga"));
            return vo;
        });
        return vos;
    }

    @Override
    public String getLastId(String param) {
        String lastId = null;
        lastId = repositoryTransaksi.getLastId(param);
        return lastId;
    }
}
