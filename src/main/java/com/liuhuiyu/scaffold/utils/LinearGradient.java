package com.liuhuiyu.scaffold.utils;

/**
 * 线性渐变工具
 *
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-08-26 8:22
 */
public class LinearGradient {
    /**
     * 保存渐变属性信息
     */
    private final int[] boundaryList;

    /**
     * 初始化渐变边界
     *
     * @param boundaryList 要求格式[位置，边界值,......,位置，边界值,位置，边界值]
     */
    public LinearGradient(int[] boundaryList) {
        int v = boundaryList[0] - 1;
        //参数验证
        for (int i = 0; i < boundaryList.length / 2; i += 2) {
            if (v >= boundaryList[i]) {
                throw new IllegalArgumentException("位置数据要求递增变化");
            }
            else {
                v = boundaryList[i];
            }
        }
        this.boundaryList = boundaryList;
    }

    /// <summary>
    /// 渐变信息获取
    /// </summary>
    /// <param name="value">[位置1,颜色1,位置2,颜色2]</param>
    /// <returns></returns>
    public int[] getGradient(int value) {
        int[] res = new int[4];
        boolean begin = false, end = false;
        for (int i = 0; i < this.boundaryList.length; i += 2) {
            if (this.boundaryList[i] >= value) {
                res[2] = this.boundaryList[i];
                res[3] = this.boundaryList[i + 1];
                end = true;
                break;
            }
            else if (this.boundaryList[i] <= value) {
                res[0] = this.boundaryList[i];
                res[1] = this.boundaryList[i + 1];
                begin = true;
            }
        }
        if (!end) {
            res[2] = res[0];
            res[3] = res[1];
        }
        else if (!begin) {
            res[0] = res[2];
            res[1] = res[3];
        }
        return res;
    }

    /**
     * 渐变值获取
     *
     * @param val 转换前数据
     * @return 渐变值数据
     */
    public int getValue(int val) {
        int[] info = this.getGradient(val);
        int valMin = info[0];
        int valMax = info[2];
        int resBegin = info[1];
        int resEnd = info[3];
        return getValue(val, resBegin, resEnd, valMin, valMax);
    }

    /**
     * 渐变值获取
     *
     * @param val      数值
     * @param resBegin 返回初始值
     * @param resEnd   返回结束值
     * @param valMin   开始计算值
     * @param valMax   结束计算值
     * @return 渐变数值
     */
    private static int getValue(int val, int resBegin, int resEnd, int valMin, int valMax) {
        if (val <= valMin) {
            return resBegin;
        }
        else if (val >= valMax) {
            return resEnd;
        }
        else {
            int res = resBegin + ((resEnd - resBegin) * (val - valMin) / (valMax - valMin));
            if (res < Math.min(resBegin, resEnd))
                res = Math.min(resBegin, resEnd);
            else if (res > Math.max(resBegin, resEnd))
                res = Math.max(resBegin, resEnd);
            return res;
        }
    }
}
