package com.liuhuiyu.scaffold.domain.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-07-28 15:08
 */
@Entity
@Table
@Data
public class CO_CommonCode {
    public static final int COLUMN_CODE_NAME_MAX_LENGTH = 26;
    public static final int COLUMN_REMARK_MAX_LENGTH = 26;
    @Id
    @Column(columnDefinition = "int COMMENT '编号'")
    private Integer id;
    @Column(nullable = false, columnDefinition = "varchar(" + COLUMN_CODE_NAME_MAX_LENGTH + ") COMMENT '名称'")
    private String codeName;
    @Column(nullable = false, columnDefinition = "int COMMENT '上级编号'")
    private Integer parentId;
    @Column(nullable = false, columnDefinition = "varchar(" + COLUMN_REMARK_MAX_LENGTH + ") COMMENT '备注'")
    private String remark;
    @Column(nullable = false, columnDefinition = "int COMMENT '显示索引'")
    private Integer displayIndex;
    @Column(nullable = false, columnDefinition = "bit COMMENT '实体类'")
    private Boolean entity;
    @Column(nullable = false, columnDefinition = "text COMMENT '相关应用表名称'")
    private String tableNames;

    public CO_CommonCode() {
    }

    public CO_CommonCode(Integer id, String codeName, Integer parentId, String remark, Integer displayIndex, Boolean entity, String tableNames) {
        this.id = id;
        this.codeName = codeName;
        this.parentId = parentId;
        this.remark = remark;
        this.displayIndex = displayIndex;
        this.entity = entity;
        this.tableNames = tableNames;
    }
}