package com.liuhuiyu.scaffold.utils;

import junit.framework.TestCase;
import lombok.extern.log4j.Log4j2;
import org.aspectj.weaver.ast.Var;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-09-21 8:38
 */
@Log4j2
public class FileUtilTest extends TestCase {
    String file = "E:\\temp\\a.txt";
    String file2 = "E:\\temp\\a2.txt";
    String file3 = "E:\\temp\\a3Bytes.txt";
    String info = "测试录入";

    public void testWriteFile() {
        FileUtil.writeFile(this.file, this.info);
    }

    public void testWriteFile2() throws IOException {
        InputStream inputStream = new FileInputStream(file);
        FileUtil.writeFile(this.file2, inputStream);
    }

    public void testWriteByte() {
        byte[] bytes = new byte[]{0x63, 0x64};
        if(FileUtil.writeByte(this.file3, bytes)) {
            log.debug("OK");
        }
        else {
            log.debug("error");
        }
    }

    public void testDeserializeFromFile() {
        Object o = FileUtil.deserializeFromFile(this.file3);
        assert o != null;
        log.debug(o.toString());
    }

    public void testInputStream2String() throws IOException {
        InputStream inputStream = new FileInputStream(file);
        String info = FileUtil.inputStream2String(inputStream);
        log.debug(info);
    }

    public void testCreateFolderFile() {
        FileUtil.createFolderFile("d://a/b/c");
    }

    public void testRenameFolder() {
        boolean b = FileUtil.renameFolder("d://a/b", "d://a/c");
        log.debug("renameFolder = " + b);
    }

    public void testGetFiles() {
        List<File> fileList = Arrays.asList(FileUtil.getFiles(file));
        fileList.forEach(log::debug);
    }

    public void testGetFileOnly() {
        ArrayList<File> files = FileUtil.getFileOnly(new File("E:\\temp"));
        files.forEach(log::debug);
    }

    public void testGetDirectoryOnly() {
        ArrayList<File> files = FileUtil.getDirectoryOnly(new File("E:\\temp"));
        files.forEach(log::debug);
    }

    public void testGetFileExt() {
        File file = new File("E:\\temp");
        String ext = FileUtil.getFileExt(file);
        log.debug(ext);
    }
}