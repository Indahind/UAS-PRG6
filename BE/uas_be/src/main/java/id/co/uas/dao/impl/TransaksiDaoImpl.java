package id.co.uas.dao.impl;

import id.co.uas.model.Transaksi;
import id.co.uas.repository.TransaksiRepository;
import id.co.uas.vo.TransaksiVo;
import id.co.uas.dao.TransaksiDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransaksiDaoImpl implements TransaksiDao {

    @Autowired
    private TransaksiRepository transaksiRepository;

    @Override
    public List<TransaksiVo> getAllTransaksi() {
        Iterable<Transaksi> transaksis = transaksiRepository.findAllTransaksi();
        List<TransaksiVo> transaksiVos = new ArrayList<>();
        for (Transaksi item : transaksis) {
            TransaksiVo transaksiVo = new TransaksiVo(item);
            transaksiVos.add(transaksiVo);
        }
        return transaksiVos;
    }

    @Override
    public List<TransaksiVo> getTransaksiByMonth(int month, int year) {
        Iterable<Transaksi> transaksis = transaksiRepository.findByMonthAndYear(month, year);
        List<TransaksiVo> transaksiVos = new ArrayList<>();
        for (Transaksi item : transaksis) {
            TransaksiVo transaksiVo = new TransaksiVo(item);
            transaksiVos.add(transaksiVo);
        }
        return transaksiVos;
    }
}
