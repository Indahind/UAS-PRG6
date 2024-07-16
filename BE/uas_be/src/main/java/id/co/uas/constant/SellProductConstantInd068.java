package id.co.uas.constant;

public class SellProductConstantInd068 {

    public static final String qGetAllSellProduct = "SELECT * FROM DKYTRSELLPRODUCT ORDER BY TranscId DESC";

    public static final String qGetLastId = "SELECT MAX(TranscId) FROM DKYTRSELLPRODUCT  WHERE TranscId LIKE CONCAT(:param, '%')";

    public static final String qGetLaporan =
            "SELECT " +
                    "    t.TranscProdId, " +
                    "    l.ProductName AS namaProduk, " +
                    "    SUM(t.TranscQty) AS jumlah, " +
                    "    SUM(t.TranscPrice) AS totalHarga " +
                    "FROM " +
                    "    DKYTRSELLPRODUCT t " +
                    "JOIN " +
                    "    DKYMSPRODUCT l ON t.TranscProdId = l.ProductId " +
                    "GROUP BY " +
                    "    t.TranscProdId, " +
                    "    l.ProductName " +
                    "ORDER BY " +
                    "    jumlah DESC;";


}
