package id.co.uas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DKYMSCABANG")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CabangModelInd068 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BranchId")
    private Integer idCabang;

    @Column(name = "BranchName")
    private String namaCabang;

}
