package com.liuhuiyu.scaffold.constant.enums;

import com.liuhuiyu.scaffold.domain.entity.CO_Member;
import org.jetbrains.annotations.NotNull;

/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-07-28 15:09
 */
public enum CommonEnum {
    //region 会员状态
    CO_MEMBER_STATE(10, "会员状态", "会员状态", CO_Member.class),
    CO_MEMBER_STATE_1(1, "有效", "会员状态", CO_MEMBER_STATE.getCodeId(), 0, true),
    CO_MEMBER_STATE_2(2, "无效", "会员状态", CO_MEMBER_STATE.getCodeId(), 0, true),

    ;
    public static final int DATUM = 1000;
    private Integer codeId;
    private String codeName;
    private Integer parentId;
    private String remark;
    private Integer displayIndex;
    private Boolean entity;
    private Class<?>[] classes;

    private void initialize(Integer codeId, String codeName, String remark, Integer parentId, Integer displayIndex, Boolean entity, Class<?>[] aClass) {
        this.codeId = codeId;
        this.codeName = codeName;
        this.parentId = parentId;
        this.remark = remark;
        this.displayIndex = displayIndex;
        this.entity = entity;
        this.classes = aClass;
    }

    CommonEnum(Integer codeId, String codeName, String remark, Class<?> aClass) {
        Integer parentId = 0;
        Integer displayIndex = 0;
        this.initialize(codeId, codeName, remark, parentId, displayIndex, true, new Class[]{aClass});
    }

    CommonEnum(Integer codeId, String codeName, String remark, Class<?>[] classes) {
        Integer parentId = 0;
        Integer displayIndex = 0;
        this.initialize(codeId, codeName, remark, parentId, displayIndex, true, classes);
    }

    CommonEnum(Integer baseCodeId, String codeName, String remark, Integer parentId, Integer displayIndex, Boolean entity) {
        Integer codeId = parentId * DATUM + baseCodeId;
        Class<?>[] classes = new Class[0];
        this.initialize(codeId, codeName, remark, parentId, displayIndex, entity, classes);
    }

    public Integer getCodeId() {
        return codeId;
    }

    public String getCodeName() {
        return codeName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public String getRemark() {
        return remark;
    }

    public Integer getDisplayIndex() {
        return displayIndex;
    }

    public Boolean getEntity() {
        return entity;
    }

    public @NotNull String getClasses() {
        if(this.classes == null || this.classes.length == 0) {
            return "";
        }
        else {
            StringBuilder classesString = new StringBuilder();
            for(Class<?> c : this.classes) {
                classesString.append(c.getSimpleName());
            }
            return classesString.toString();
        }
    }
}
