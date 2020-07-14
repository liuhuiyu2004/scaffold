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
}
