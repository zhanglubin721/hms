package com.wuyaofei.providerservice.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order implements Serializable {
    private Long id;
    private Long rid;
    private String rname;
    private int type;
    private String uname;
    private Long uid;
    private int useTime;
    private LocalDateTime stareTime;
    private LocalDateTime endTime;
    private int state;
}
