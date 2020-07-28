package com.liuhuiyu.scaffold.domain.entity;

import javax.persistence.*;

/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-07-28 14:17
 */
@Entity
@Table
public class CO_Member {
    public static final int COLUMN_ACCOUNT_MAX_LENGTH = 8;
    public static final int COLUMN_PASSWORD_MAX_LENGTH = 128;
    public static final int COLUMN_CNAME_MAX_LENGTH = 4;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int COMMENT 'id'")
    private int id;
    @Column(nullable = false, columnDefinition = "varchar(" + COLUMN_ACCOUNT_MAX_LENGTH + ") COMMENT '账号'")
    private String account;
    @Column(nullable = false, columnDefinition = "varchar(" + COLUMN_PASSWORD_MAX_LENGTH + ") COMMENT '加密密码'")
    private String password;
    @Column(nullable = false, columnDefinition = "varchar(" + COLUMN_CNAME_MAX_LENGTH + ") COMMENT '名称'")
    private String cname;
    @Column(columnDefinition = "int COMMENT '状态(1)'")
    private int stateId;
    @Column(columnDefinition = "bigint COMMENT '创建时间'")
    private Long createTime;

    public CO_Member() {
    }
}