package com.xdclass.mobile.xdclassmobileredis.domain;

import lombok.Data;

import java.io.Serializable;
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -4415438719697624729L;

    private String id;

    private String userName;

}
