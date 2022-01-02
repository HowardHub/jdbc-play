package com.ln.jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author HeZhipeng
 * @Date 2022/1/2 10:33
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dept {

    private Long id;

    private Integer deptno;

    private String dname;

    private String loc;


}
