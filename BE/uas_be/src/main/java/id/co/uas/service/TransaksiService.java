package id.co.uas.service;

import id.co.uas.DtoResponse;
import id.co.uas.model.Transaksi;

public interface TransaksiService {
    DtoResponse getAllTransaksi();
    DtoResponse getAllLayanan();
    DtoResponse saveTransaksi(Transaksi transaksi);
    DtoResponse getTransaksiByMonth(int month, int year);
}
