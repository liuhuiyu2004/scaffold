package com.liuhuiyu.scaffold.utils;

/**
 * 使用线性渐变规则转换颜色
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-08-27 8:15
 */
public class ARGBLinearGradient {
    /**
     * 分量最大值
     */
    final int COLOR_COMPONENT_MAX = 255;
    /**
     * 透明度线性渐变
     */
    private LinearGradient a;
    /**
     * 红色线性渐变
     */
    private LinearGradient r;
    /**
     * 绿色线性渐变
     */
    private LinearGradient g;
    /**
     * 蓝色线性渐变
     */
    private LinearGradient b;
    /**
     * 输入最小值
     */
    int inMin;
    /**
     * 输入最大值
     */
    int inMax;
    /**
     * 输入输出比例系数
     */
    float scaleFactor;

    public ARGBLinearGradient(LinearGradient r, LinearGradient g, LinearGradient b, int inMin, int inMax) {
        this.a = new LinearGradient(new int[]{0, COLOR_COMPONENT_MAX, COLOR_COMPONENT_MAX, COLOR_COMPONENT_MAX});
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public ARGBLinearGradient(LinearGradient a, LinearGradient r, LinearGradient g, LinearGradient b) {
        this.a = a;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int getRgb(int value) {
        int argb=0;
        argb+=(this.a.getValue(value)<<24);
        argb+=(this.r.getValue(value)<<16);
        argb+=(this.g.getValue(value)<<8);
        argb+=this.b.getValue(value);
        return argb;
    }
}
