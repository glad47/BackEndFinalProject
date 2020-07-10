package com.jugu.www.pcbonlinev2.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVO implements Serializable {
    private static final long serialVersionUID = 6673725029189523002L;


    private Integer id;

    private String userName;

    private String email;

    private String password;
}
