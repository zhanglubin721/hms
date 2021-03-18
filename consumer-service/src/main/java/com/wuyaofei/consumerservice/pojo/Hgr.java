package com.wuyaofei.consumerservice.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Hgr implements Serializable {
    private Long rid;
    private String rname;
    private int type;
}
