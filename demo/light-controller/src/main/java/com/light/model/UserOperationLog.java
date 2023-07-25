package com.light.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @Author light
 * @Date 2023/6/28
 * @Desc
 **/
@Data
@Builder
@AllArgsConstructor
public class UserOperationLog {
    Integer id;
    String userId;
    String ip;
    String opData;
    String attr1;
    String attr2;
    String attr3;
    String attr4;
    String attr5;
    String attr6;
    String attr7;
    String attr8;
    String attr9;
    String attr10;
    String attr11;
    String attr12;
}
