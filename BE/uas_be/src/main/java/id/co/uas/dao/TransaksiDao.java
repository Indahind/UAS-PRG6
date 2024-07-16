package id.co.uas.dao;

import id.co.uas.vo.TransaksiVo;

import java.util.List;

public interface TransaksiDao {
    List<TransaksiVo> getAllTransaksi();
    List<TransaksiVo> getTransaksiByMonth(int month, int year);
}
