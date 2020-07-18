package com.liuhuiyu.scaffold.utils;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 图像工具类
 *
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-07-14 18:47
 */
public class ImageUtil {

    /**
     * 图像字节码转换成像素矩阵
     *
     * @param buffer 图像字节码
     * @return 像素矩阵
     * @throws IOException 输入错误
     */
    public static int[] @NotNull [] imageToPixelMatrix(byte[] buffer) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(buffer);
        BufferedImage imageBuffer = ImageIO.read(inputStream);
        int[][] data = new int[imageBuffer.getWidth()][imageBuffer.getHeight()];
        for (int i = 0; i < imageBuffer.getWidth(); i++) {
            for (int j = 0; j < imageBuffer.getHeight(); j++) {
                data[i][j] = imageBuffer.getRGB(i, j);
            }
        }
        return data;

    }

    /**
     * 将像素矩阵 转换成图片
     *
     * @param pixelMatrix 像素矩阵
     * @return 图片字节码
     * @throws IOException 输入输出异常
     */
    public static byte[] pixelMatrixToImageBytes(int[] @NotNull [] pixelMatrix) throws IOException {
        BufferedImage imageBuffer = new BufferedImage(pixelMatrix.length, pixelMatrix[0].length, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < imageBuffer.getWidth(); i++) {
            for (int j = 0; j < imageBuffer.getHeight(); j++) {
                imageBuffer.setRGB(i, j, pixelMatrix[i][j]);
            }
        }
        ByteOutputStream byteOutputStream = new ByteOutputStream();
        ImageIO.write(imageBuffer, "jpg", byteOutputStream);
        return byteOutputStream.getBytes();
    }

    /**
     * 单字节 转换成 RGB 伪颜色
     * @param val   单字节
     * @return  RGB 伪颜色
     */
    public static int greyToRgb(int val) {
        int r, g, b;

        //red
        if (val < 128) {
            r = 0;
        }
        else if (val < 192) {
            r = 255 / 64 * (val - 128);
        }
        else {
            r = 255;
        }
        //green
        if (val < 64) {
            g = 255 / 64 * val;
        }
        else if (val < 192) {
            g = 255;
        }
        else {
            g = -255 / 63 * (val - 192) + 255;
        }

        //blue
        if (val < 64) {
            b = 255;
        }
        else if (val < 128) {
            b = -255 / 63 * (val - 192) + 255;
        }
        else {
            b = 0;
        }
//        Vec3b rgb;
//        rgb[0] = b;
//        rgb[1] = g;
//        rgb[2] = r;
        return hexToInt(convertRGBToHex(r, g, b));
    }

    /**
     * 16进制字符串颜色转换成 int
     *
     * @param hex 16进制字符串
     * @return 代表颜色的 int
     */
    public static int hexToInt(String hex) {
        hex = hex.replace("#", "");
        if (hex.equals("")) return 0;
        return Integer.parseInt(hex, 16);
    }

    /**
     * RGB转换成十六进制
     *
     * @param r 红色
     * @param g 绿色
     * @param b 蓝色
     * @return 16进制颜色字符串
     */
    public static @NotNull String convertRGBToHex(int r, int g, int b) {
        String s = "0123456789ABCDEF";
        String hex;
        if (r >= 0 && r < 256 && g >= 0 && g < 256 && b >= 0 && b < 256) {
            int x, y, z;
            x = r % 16;
            r = (r - x) / 16;
            y = g % 16;
            g = (g - y) / 16;
            z = b % 16;
            b = (b - z) / 16;
            hex = s.substring(r, r + 1) + s.substring(x, x + 1) + s.substring(g, g + 1) + s.substring(y, y + 1) + s.substring(b, b + 1) + s.substring(z, z + 1);
        }
        else {
            hex = "000000";
        }
        return hex;
    }

    /**
     * argb转数字
     * @param alpha 透明度(一般为128)
     * @param red   红色
     * @param green 绿色
     * @param blue  蓝色
     * @return  数字
     */
    public static int rgbToInt(int alpha,int red,int green,int blue){
        return (alpha << 24) | (red<< 16) | (green << 8) | blue;
    }
}
