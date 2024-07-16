package id.co.uas.service.impl;

import id.co.uas.dao.SellProductDaoInd068;
import id.co.uas.model.CabangModelInd068;
import id.co.uas.model.ProductModelInd068;
import id.co.uas.model.SellProductModelInd068;
import id.co.uas.repository.CabangRepositoryInd068;
import id.co.uas.repository.ProductRepositoryInd068;
import id.co.uas.repository.SellProductRepositoryInd068;
import id.co.uas.response.DtoResponse;
import id.co.uas.service.SellProductServiceInd068;
import id.co.uas.vo.LaporanTransaksiVoInd068;
import id.co.uas.vo.SellProductVoInd068;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SellProductServiceImplInd068 implements SellProductServiceInd068 {

    @Autowired
    private SellProductRepositoryInd068 repositorySellProduct;

    @Autowired
    private CabangRepositoryInd068 repositoryCabang;

    @Autowired
    private ProductRepositoryInd068 repositoryProduct;

    @Autowired
    private SellProductDaoInd068 daoSellProduct;

    @Override
    public DtoResponse saveTransaksi(SellProductModelInd068 transaksi) {
        try {
            ProductModelInd068 product = repositoryProduct.findById(transaksi.getIdProduk()).orElse(null);
            transaksi.setTotalHarga(transaksi.getQty() * product.getHargaProduk());
            transaksi.setIdTransaksi(getIdTransaksi());
            transaksi.setTanggalTransaksi(new Date());

            //Kurangi stok
            product.setStokProduk(product.getStokProduk() - transaksi.getQty());
            repositoryProduct.save(product);

            repositorySellProduct.save(transaksi);
            return new DtoResponse(200, transaksi, "Sukes Menyimpan data");
        }catch (Exception e){
            return new DtoResponse(500, transaksi, "Gagal Menyimpan Data");
        }
    }

    @Override
    public DtoResponse getAllTransaksi() {
        try {
            List<SellProductVoInd068> data = daoSellProduct.getAllSellProduct();
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
    public DtoResponse getAllCabang() {
        try {
            Iterable<CabangModelInd068> data = repositoryCabang.findAll();
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
    public DtoResponse getLaporan() {
        try {
            List<LaporanTransaksiVoInd068> data = daoSellProduct.getLaporan();
            if (data != null) {
                return new DtoResponse(200, data);
            }else {
                return new DtoResponse(404, null, "mNotFound");
            }
        } catch (Exception e) {
            return new DtoResponse(500, null, "mNotFound");
        }
    }

    private String getIdTransaksi() {
        String idTransaksi = null;

        // Mendapatkan tanggal saat ini dan memformatnya menjadi yyyyMM
        Date tanggal = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
        String tahunBulan = formatter.format(tanggal);

        // Mendapatkan ID terakhir dari repository untuk bulan saat ini
        String getLastId = repositorySellProduct.getLastId(tahunBulan);

        if (getLastId != null) {
            // Jika ada ID terakhir, ambil 4 digit terakhir, tambah 1, dan format kembali
            int lastNumber = Integer.parseInt(getLastId.substring(6));
            int newNumber = lastNumber + 1;
            idTransaksi = tahunBulan + String.format("%04d", newNumber);
        } else {
            // Jika tidak ada ID terakhir, mulai dari 0001
            idTransaksi = tahunBulan + "0001";
        }

        return idTransaksi;
    }


}
