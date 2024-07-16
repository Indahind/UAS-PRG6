package id.co.uas.rest;

import id.co.uas.model.SellProductModelInd068;
import id.co.uas.response.DtoResponse;
import id.co.uas.service.SellProductServiceInd068;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaksi")
@CrossOrigin
public class SellProductRestInd068 {

    @Autowired
    private SellProductServiceInd068 serviceTransaksi;

    public SellProductRestInd068(SellProductServiceInd068 serviceTransaksi){
        this.serviceTransaksi = serviceTransaksi;
    }

    @PostMapping("/saveTransaksi")
    public DtoResponse saveTransaksi(@RequestBody SellProductModelInd068 transaksi){
        return serviceTransaksi.saveTransaksi(transaksi);
    }

    @GetMapping("/getAllTransaksi")
    public DtoResponse getAllTransaksi(){
        return serviceTransaksi.getAllTransaksi();
    }

    @GetMapping("/getAllCabang")
    public DtoResponse getAllCabang(){
        return serviceTransaksi.getAllCabang();
    }

    @GetMapping("/getLaporan")
    public DtoResponse getLaporan(){
        return serviceTransaksi.getLaporan();
    }
}
