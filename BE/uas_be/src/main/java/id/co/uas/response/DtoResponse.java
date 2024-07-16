package id.co.uas.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoResponse {

    private Integer status;

    private Object data;

    private String message;

    public DtoResponse(Integer status, Object data){
        this.status = status;
        this.data = data;
    }

}
