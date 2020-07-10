package com.jugu.www.pcbonlinev2.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserQueryDTO implements Serializable {

    private static final long serialVersionUID = 7501174233045276429L;

    private String username;
}
