package id.co.uas.dao.impl;

import id.co.uas.dao.LayananDao;
import id.co.uas.model.Layanan;
import id.co.uas.repository.LayananRepository;
import id.co.uas.vo.LayananVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LayananDaoImpl implements LayananDao {

    @Autowired
    private LayananRepository layananRepository;

    @Override
    public List<LayananVo> getAllLayanan() {
        Iterable<Layanan> Layanans = layananRepository.findAll();
        List<LayananVo> LayananVos = new ArrayList<>();
        for (Layanan item : Layanans) {
            LayananVo LayananVo = new LayananVo(item);
            LayananVos.add(LayananVo);
        }
        return LayananVos;
    }


    @Override
    public LayananVo getLayananId(int id){
        Layanan layananData = layananRepository.findById(id).orElseThrow();
        if (layananData != null) {
            LayananVo Layanantampil = new LayananVo(layananData);
            return Layanantampil;
        } else {
            return null;
        }
    }
}
