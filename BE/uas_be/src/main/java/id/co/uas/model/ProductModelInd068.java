package id.co.uas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DKYMSPRODUCT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModelInd068 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductId")
    private Integer idProduk;

    @Column(name = "ProductName")
    private String namaProduk;

    @Column(name = "ProductDesc")
    private String deskripsiProduk;

    @Column(name = "ProductPrice")
    private Double hargaProduk;

    @Column(name = "ProductStock")
    private Integer stokProduk;

    @Column(name = "ProductStatus")
    private Integer status;
}
