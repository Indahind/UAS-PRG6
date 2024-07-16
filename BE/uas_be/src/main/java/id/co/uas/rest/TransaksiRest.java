package id.co.uas.rest;

import id.co.uas.DtoResponse;
import id.co.uas.model.Transaksi;
import id.co.uas.service.TransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/transaksiSalon")
public class TransaksiRest {
    @Autowired
    private TransaksiService transaksiService;

    public TransaksiRest(TransaksiService transaksiService) {
        this.transaksiService = transaksiService;
    }

    @GetMapping("/getTransaksi")
    public DtoResponse getTransaksi(){
        return transaksiService.getAllTransaksi();
    }

    @GetMapping("/getAllLayanan")
    public DtoResponse getAllLayanan(){
        return transaksiService.getAllLayanan();
    }

    @GetMapping("/getTransaksiByMonth")
    public DtoResponse getTransaksiByMonth(@RequestParam int month, @RequestParam int year) {
        return transaksiService.getTransaksiByMonth(month, year);
    }

    @PostMapping("/saveTransaksiSalon")
    public DtoResponse createTransaksi(@RequestBody Transaksi transaksi){
        return transaksiService.saveTransaksi(transaksi);
    }
}
