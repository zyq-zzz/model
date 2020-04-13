package emum;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StrtusResponse {
    @ApiModelProperty(value = "状态真实值")
    private Integer realValue;
    @ApiModelProperty(value = "状态展示值")
    private String displayValue;
}