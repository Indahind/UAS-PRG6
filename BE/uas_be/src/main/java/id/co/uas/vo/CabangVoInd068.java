package id.co.uas.vo;

import id.co.uas.model.CabangModelInd068;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CabangVoInd068 {

    private Integer idCabang;

    private String namaCabang;

    public CabangVoInd068(CabangModelInd068 cabangModelInd068){
        this.idCabang = cabangModelInd068.getIdCabang();
        this.namaCabang = cabangModelInd068.getNamaCabang();
    }
}
