package com.wl.swagger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel("响应实体")
public class ServiceResponse {
    @ApiModelProperty(value = "响应码", required = true)
    private String code;
    @ApiModelProperty(value = "描述", required = true)
    private String message;
}
