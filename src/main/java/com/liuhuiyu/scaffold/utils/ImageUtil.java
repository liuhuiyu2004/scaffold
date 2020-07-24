package com.liuhuiyu.scaffold.utils;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
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
     *
     * @param val 单字节
     * @return RGB 伪颜色
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
     *
     * @param alpha 透明度(一般为128)
     * @param red   红色
     * @param green 绿色
     * @param blue  蓝色
     * @return 数字
     */
    public static int rgbToInt(int alpha, int red, int green, int blue) {
        return (alpha << 24) | (red << 16) | (green << 8) | blue;
    }

    /**
     * 动态灰度图(2*byte)转伪彩
     *
     * @param buffer 灰度（2*byte）
     * @param width  宽度
     * @param height 高度
     * @return 伪彩矩阵
     */
    @Contract(pure = true)
    private static int[] @NotNull [] hotBytesToMatrixBytesWithDynamic(byte @NotNull [] buffer, int width, int height) {
        int[][] matrixBytes = new int[height][width];
        //获取最低
        int min = 128 * 128;//最小值
        //获取最高
        int max = 0;//最大值
        for (int i = 0; i < buffer.length / 2; i += 2) {
            byte[] tmpB = new byte[2];
            tmpB[1] = buffer[i * 2];
            tmpB[0] = buffer[i * 2 + 1];
            int value = BytesUtil.byteArrayToInt(tmpB);
            if (value != 0) {
                min = Math.min(min, value);
                max = Math.max(max, value);
            }
        }
        float weight = (float) 255 / (max - min);
        for (int y = 0; y < width; y++) {
            for (int x = 0; x < height; x++) {
                int index = (y * height + x) * 2;
                byte[] tmpB = new byte[2];
                tmpB[1] = buffer[index];
                tmpB[0] = buffer[index + 1];
                int i = BytesUtil.byteArrayToInt(tmpB);
                //灰度计算
                int gray = (i == 0 ? 255 : (int) ((i - min) * weight));
                //灰度转rgb
                matrixBytes[x][y] = greyToRgb(gray);
            }
        }
        return matrixBytes;
    }

    /**
     * 动态灰度图(2*byte)转伪彩
     *
     * @param buffer 灰度（2*byte）
     * @param width  宽度
     * @param height 高度
     * @return 伪彩矩阵
     */
    @Contract(pure = true)
    private static int[] @NotNull [] hotBytesToMatrixBytesWithDynamic(byte @NotNull [] buffer, int width, int height, int byteNum) {
        if (byteNum > 4 || byteNum <= 0) {
            throw new IllegalArgumentException("byteNum 取值范围 1-4");
        }
        int[][] matrixBytes = new int[height][width];
        //获取最低
        int min = 128 * 128;//最小值
        //获取最高
        int max = 0;//最大值
        for (int i = 0; i < buffer.length / byteNum; i += byteNum) {
            byte[] tmpB = new byte[byteNum];
            for (int j = 0; j < byteNum; j++) {
                tmpB[j] = buffer[i * byteNum + byteNum - (j+1)];
            }
//            System.arraycopy(buffer, 0 * byteNum + 0, tmpB, 0, byteNum);
            int value = BytesUtil.byteArrayToInt(tmpB);
            if (value != 0) {
                min = Math.min(min, value);
                max = Math.max(max, value);
            }
        }
        float weight = (float) 255 / (max - min);
        for (int y = 0; y < width; y++) {
            for (int x = 0; x < height; x++) {
                int index = (y * height + x) * 2;
                byte[] tmpB = new byte[2];
                for (int j = 0; j < byteNum; j++) {
                    tmpB[j] = buffer[index + byteNum - (j+1)];
                }
                int i = BytesUtil.byteArrayToInt(tmpB);
                //灰度计算
                int gray = (i == 0 ? 255 : (int) ((i - min) * weight));
                //灰度转rgb
                matrixBytes[x][y] = greyToRgb(gray);
            }
        }
        return matrixBytes;
    }

    /**
     * 左右镜像
     * @param bufImage  图片buf
     */
    @Contract("_ -> param1")
    public static void mirror(@NotNull BufferedImage bufImage) {

        // 获取图片的宽高
        final int width = bufImage.getWidth();
        final int height = bufImage.getHeight();

        // 读取出图片的所有像素
        int[] rgbs = bufImage.getRGB(0, 0, width, height, null, 0, width);

        // 对图片的像素矩阵进行水平镜像
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width / 2; col++) {
                int temp = rgbs[row * width + col];
                rgbs[row * width + col] = rgbs[row * width + (width - 1 - col)];
                rgbs[row * width + (width - 1 - col)] = temp;
            }
        }

        // 把水平镜像后的像素矩阵设置回 bufImage
        bufImage.setRGB(0, 0, width, height, rgbs, 0, width);
    }

    /**
     * 图像旋转
     * @param image 原始图像
     * @param degree    旋转角度
     * @return  旋转后的图像
     * @throws IOException  io错误
     */
    public static BufferedImage rotateImage(BufferedImage image, int degree)
            throws IOException {
        BufferedImage rotatedImage = null;
        try {
            int originalImWidth = image.getWidth();
            int originalImHeight = image.getHeight();
            int rotateImWidth = 0;
            int rotateImHeight = 0;
            int x = 0;
            int y = 0;
            degree = degree % 360;
            if (degree < 0) {
                degree = 360 + degree;
            }
            double radian = Math.toRadians(degree);
            if (degree == 180 || degree == 0 || degree == 360) {
                rotateImWidth = originalImWidth;
                rotateImHeight = originalImHeight;
            } else if (degree == 90 || degree == 270) {
                rotateImWidth = originalImHeight;
                rotateImHeight = originalImWidth;
            } else {
                double cosVal = Math.abs(Math.cos(radian));
                double sinVal = Math.abs(Math.sin(radian));
                rotateImWidth = (int)(sinVal * originalImHeight) + (int)(cosVal * originalImWidth);
                rotateImHeight = (int)(sinVal * originalImWidth) + (int)(cosVal * originalImHeight);
            }
            x = (rotateImWidth / 2) - (originalImWidth / 2);
            y = (rotateImHeight / 2) - (originalImHeight / 2);
            rotatedImage = new BufferedImage(rotateImWidth, rotateImHeight, image.getType());
            Graphics2D gs = (Graphics2D) rotatedImage.getGraphics();
            gs.fillRect(0, 0, rotateImWidth, rotateImHeight);
            AffineTransform at = new AffineTransform();
            at.rotate(radian, rotateImWidth / 2, rotateImHeight / 2);
            at.translate(x, y);
            AffineTransformOp op = new AffineTransformOp(at,
                    AffineTransformOp.TYPE_BICUBIC);
            op.filter(image, rotatedImage);
        } catch (Exception e) {
//            F2Logger.getLogger().log(PhotoConstants.EFUP0002, new Object[] { e.getMessage() });
            throw e;
        }
        return rotatedImage;
    }
}
