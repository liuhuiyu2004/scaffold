package com.liuhuiyu.scaffold.utils;

import com.google.zxing.WriterException;
import junit.framework.TestCase;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-08-01 17:57
 */
public class QRCodeUtilTest extends TestCase {

    public void testGenerateQRCodeImage() throws IOException, WriterException {
        BufferedImage bufferedImage = BarcodeCodeUtil.generateQrCodeImage("my name is liuhuiyu", 200, 200);
        File outputfile = new File("d:\\image.jpg");
        ImageIO.write(bufferedImage, "jpg", outputfile);
    }
}