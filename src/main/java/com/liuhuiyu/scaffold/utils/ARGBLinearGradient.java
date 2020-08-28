package com.liuhuiyu.scaffold.utils;

/**
 * 使用线性渐变规则转换颜色
 *
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-08-27 8:15
 */
public class ARGBLinearGradient {
    /**
     * 分量最大值
     */
    static final int COLOR_COMPONENT_MAX = 255;
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
    /**
     * 无渐变纯色
     */
    public final static LinearGradient SolidColor = new LinearGradient(new int[]{0, COLOR_COMPONENT_MAX, COLOR_COMPONENT_MAX, COLOR_COMPONENT_MAX});

    /**
     * 初始化线性渐变色谱
     *
     * @param r     红色分量
     * @param g     绿色分量
     * @param b     蓝色分量
     * @param inMin 输入最小值
     * @param inMax 输入最大值
     */
    public ARGBLinearGradient(LinearGradient r, LinearGradient g, LinearGradient b, int inMin, int inMax) {
        this.a = SolidColor;
        this.r = r;
        this.g = g;
        this.b = b;
        this.inMin = inMin;
        this.scaleFactor = ((float) COLOR_COMPONENT_MAX) / (float) (inMax - inMin);
    }

    public ARGBLinearGradient(LinearGradient a, LinearGradient r, LinearGradient g, LinearGradient b, int inMin, int inMax) {
        this.a = a;
        this.r = r;
        this.g = g;
        this.b = b;
        this.inMin = inMin;
        this.scaleFactor = ((float) COLOR_COMPONENT_MAX) / (float) (inMax - inMin);
    }

    /**
     * 获取转换后的ARGB数值
     *
     * @param value 输入值
     * @return ARGB颜色
     */
    public int getRgb(int value) {
        int val;
        if (value < this.inMin) {
            val = this.inMin;
        }
        else if (value > this.inMax) {
            val = this.inMax;
        }
        else {
            val = (int) this.scaleFactor * (value - inMin);
        }
        int argb = 0;
        argb += (this.a.getValue(val) << 24);
        argb += (this.r.getValue(val) << 16);
        argb += (this.g.getValue(val) << 8);
        argb += this.b.getValue(val);
        return argb;
    }
}
