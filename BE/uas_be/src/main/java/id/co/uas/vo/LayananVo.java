package id.co.uas.vo;

import id.co.uas.model.Layanan;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LayananVo {
    private Integer IdLayanan;
    private String NamaLayanan;
    private String DeskripsiLayanan;
    private Integer HargaLayanan;
    private Integer StatusLayanan;
    public LayananVo() {
    }

    public LayananVo(Layanan vo) {
        IdLayanan = vo.getIdLayanan();
        NamaLayanan = vo.getNamaLayanan();
        DeskripsiLayanan = vo.getDeskripsiLayanan();
        HargaLayanan = vo.getHargaLayanan();
        StatusLayanan = vo.getStatusLayanan();
    }
}
