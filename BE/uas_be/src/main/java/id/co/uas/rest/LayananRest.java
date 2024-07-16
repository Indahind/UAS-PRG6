package id.co.uas.rest;

import id.co.uas.DtoResponse;
import id.co.uas.model.Layanan;
import id.co.uas.service.LayananService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/Layanans")
public class LayananRest {
    @Autowired
    private LayananService layananService;

    public LayananRest(LayananService layananService) {
        this.layananService = layananService;
    }

    @GetMapping("/getLayanans")
    public DtoResponse getLayanans() {
        return layananService.getAllLayanan();
    }

    @GetMapping("/getLayanansId/{id}")
    public DtoResponse getLayanansById(@PathVariable int id) {
        return layananService.getLayananId(id);
    }

    @PostMapping("/saveLayanans")
    public DtoResponse saveLayanans(@RequestBody Layanan layanan) {
        return layananService.saveLayanan(layanan);
    }

    @PostMapping("/updateLayanans")
    public DtoResponse updateLayanans(@RequestBody Layanan layanan) {
        return layananService.updateLayanan(layanan);
    }

//    @PostMapping("/deleteLayanans")
//    public DtoResponse deleteLayanans(@RequestBody Layanan layanan) {
//        return layananService.deleteLayanan(layanan);
//    }

    @PostMapping("/deleteLayanans/{idLayanan}")
    public DtoResponse deleteLayanans(@PathVariable int idLayanan) {
        return layananService.deleteLayananById(idLayanan);
    }
}