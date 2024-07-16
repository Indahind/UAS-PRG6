package id.co.uas.service.impl;

import id.co.uas.DtoResponse;
import id.co.uas.dao.TransaksiDao;
import id.co.uas.model.Layanan;
import id.co.uas.model.Transaksi;
import id.co.uas.repository.LayananRepository;
import id.co.uas.repository.TransaksiRepository;
import id.co.uas.service.TransaksiService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.Date;

import static id.co.uas.constant.TransaksiConstantInd068.*;

@Service
@Transactional
public class TransaksiServiceImpl implements TransaksiService {
    @Autowired
    private TransaksiDao transaksiDao;
    @Autowired
    private TransaksiRepository transaksiRepository;
    @Autowired
    private LayananRepository LayananRepository;

    @Override
    public DtoResponse getAllTransaksi() {
        if (transaksiDao.getAllTransaksi() != null) {
            return new DtoResponse(200,transaksiDao.getAllTransaksi());
        }
        return new DtoResponse(200,null, mEmptyData);
    }

    public String generateNextId() {
        String latestId = transaksiRepository.findLatestId(); // Implement this method in your repository

        int currentYear = Year.now().getValue();
        int nextSequence = 1; // Default starting sequence

        if (latestId != null && latestId.length() == 8) { // Assuming ID format like "20240001"
            int year = Integer.parseInt(latestId.substring(0, 4));
            int sequence = Integer.parseInt(latestId.substring(4));

            if (year == currentYear) {
                nextSequence = sequence + 1; // Increment sequence for the same year
            } else {
                nextSequence = 1; // Reset sequence for the new year
            }
        }

        String formattedSequence = String.format("%04d", nextSequence); // Format sequence as 4 digits (e.g., 0001)

        return currentYear + formattedSequence; // Combine current year and sequence
    }

    @Override
    public DtoResponse getAllLayanan() {
        try {
            Iterable<Layanan> data = LayananRepository.findAll();
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
    public DtoResponse saveTransaksi(Transaksi transaksi) {
        try {
            String newId = generateNextId();

            System.out.println("inin di"+newId);
            transaksi.setIdTransaksi(newId);
            transaksi.setTanggalTransaksi(new Date());

            Layanan layanan = LayananRepository.findByIdLayanan(transaksi.getIdLayanan());

            // Calculate the total price
            if (layanan != null) {
                System.out.println(transaksi.getQtyTransaksi());
                System.out.println(layanan.getHargaLayanan());
                transaksi.setHargaTotalTransaksi(transaksi.getQtyTransaksi() * layanan.getHargaLayanan());
                System.out.println(transaksi.getHargaTotalTransaksi());
            } else {
                throw new RuntimeException("Layanan not found");
            }
            // Calculate the total price
            if (layanan != null) {
                transaksi.setHargaTotalTransaksi(transaksi.getQtyTransaksi() * layanan.getHargaLayanan());
            } else {
                throw new RuntimeException("Layanan not found");
            }

            transaksiRepository.save(transaksi);
            return new DtoResponse(200, transaksi, mCreateSuccess);
        } catch (Exception e) {
            return new DtoResponse(500, transaksi, mCreateFailed);
        }
    }

    @Override
    public DtoResponse getTransaksiByMonth(int month, int year) {
        if (transaksiDao.getTransaksiByMonth(month, year) != null) {
            return new DtoResponse(200,transaksiDao.getTransaksiByMonth(month, year));
        }
        return new DtoResponse(200,null, mEmptyData);
    }

}
