package com.jugu.www.pcbonlinev2.domain.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Result implements Serializable {
    private static final long serialVersionUID = -4851302396764126880L;

    private boolean isSuccess;

    private int id;
}
