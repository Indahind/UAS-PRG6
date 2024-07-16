package id.co.uas.dao;

import id.co.uas.vo.LayananVo;

import java.util.List;

public interface LayananDao {
    List<LayananVo> getAllLayanan();
    LayananVo getLayananId(int id);
}
