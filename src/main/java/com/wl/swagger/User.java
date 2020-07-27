package com.wl.swagger;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class User {

    @NotNull(message = "id 不能为 null")
    private Long id;

    @NotBlank(groups = Add.class, message = "名称不能为空")
    private String name;

    @StartWithValidation(message = "参数必须以 wl 开头", start = "wl")
    private String address;

    public interface Add {}

    public interface Update{}

}
