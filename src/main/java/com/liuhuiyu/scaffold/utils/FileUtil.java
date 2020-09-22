package com.liuhuiyu.scaffold.utils;

import lombok.extern.log4j.Log4j2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;

/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-09-15 17:19
 */
@Log4j2
public class FileUtil {
    /**
     * 字符串写入文件
     *
     * @param fileFullName 完整文件名
     * @param info         字符串信息
     */
    public static void writeFile(String fileFullName, String info) {
        writeFile(fileFullName, info, "utf-8");
    }

    /**
     * 字符串写入文件
     *
     * @param fileFullName 完整文件名
     * @param info         字符串信息
     * @param charsetName  编码
     */
    public static void writeFile(String fileFullName, String info, String charsetName) {
        try {
            if (createFolder(fileFullName, true)) {
                BufferedWriter localBufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileFullName), charsetName));
                localBufferedWriter.write(info);
                localBufferedWriter.close();
            }
            else {
                log.error("文件创建失败");
            }
        }
        catch (IOException localIOException) {
            log.error("IO异常：" + localIOException.toString());
        }
    }

    /**
     * 将流写入指定文件
     *
     * @param fileFullName 文件名
     * @param inputStream  要写入的流
     * @throws IOException IO错误
     */
    public static void writeFile(String fileFullName, InputStream inputStream)
            throws IOException {
        FileOutputStream localFileOutputStream = new FileOutputStream(fileFullName);
        byte[] arrayOfByte = new byte['Ȁ'];
        int i;
        while ((i = inputStream.read(arrayOfByte)) != -1) {
            localFileOutputStream.write(arrayOfByte, 0, i);
        }
        inputStream.close();
        localFileOutputStream.close();
    }

    /**
     * 读取文件内容
     *
     * @param fileFullName 文件名
     * @return 文件内容
     * @throws IOException IO异常
     */
    public static String readFile(String fileFullName)
            throws IOException {
        File localFile = new File(fileFullName);
        String str1 = getCharset(localFile);
        StringBuilder localStringBuffer = new StringBuilder();
        BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileFullName), str1));
        String str2;
        while ((str2 = localBufferedReader.readLine()) != null) {
            localStringBuffer.append(str2).append("\r\n");
        }
        localBufferedReader.close();
        return localStringBuffer.toString();
    }

    /**
     * 是否是非空文件夹
     *
     * @param fileFullName 完整文件名
     * @return 是否是非空文件夹
     */
    public static boolean isExistFile(String fileFullName) {
        boolean bool = false;
        File localFile = new File(fileFullName);
        if (localFile.isDirectory()) {
            File[] arrayOfFile = localFile.listFiles();
            if ((arrayOfFile != null) && (arrayOfFile.length != 0)) {
                bool = true;
            }
        }
        return bool;
    }

    /**
     * 获取文件编码
     *
     * @param fileFullName 文件名
     * @return 文件编码
     */
    public static String getCharset(File fileFullName) {
        String str = "GBK";
        byte[] arrayOfByte = new byte[3];
        try {
            int i = 0;
            BufferedInputStream localBufferedInputStream = new BufferedInputStream(new FileInputStream(fileFullName));
            localBufferedInputStream.mark(0);
            int j = localBufferedInputStream.read(arrayOfByte, 0, 3);
            if (j == -1) {
                return str;
            }
            if ((arrayOfByte[0] == -1) && (arrayOfByte[1] == -2)) {
                str = "UTF-16LE";
                i = 1;
            }
            else if ((arrayOfByte[0] == -2) && (arrayOfByte[1] == -1)) {
                str = "UTF-16BE";
                i = 1;
            }
            else if ((arrayOfByte[0] == -17) && (arrayOfByte[1] == -69) && (arrayOfByte[2] == -65)) {
                str = "UTF-8";
                i = 1;
            }
            localBufferedInputStream.reset();
            if (i == 0) {
                while ((j = localBufferedInputStream.read()) != -1) {
                    if ((j < 128) || ((j < 240) && (j > 191))) {
                        if ((192 <= j) && (j <= 223)) {
                            j = localBufferedInputStream.read();
                            if (128 > j) {
                                break;
                            }
                            if (j > 191) {
                                break;
                            }
                        }
                        else if (224 <= j) {
                            j = localBufferedInputStream.read();
                            if ((128 <= j) && (j <= 191)) {
                                j = localBufferedInputStream.read();
                                if ((128 <= j) && (j <= 191)) {
                                    str = "UTF-8";
                                }
                            }
                        }
                    }
                }
            }
            localBufferedInputStream.close();
        }
        catch (Exception localException) {
            localException.printStackTrace();
        }
        return str;
    }

    /**
     * 输入流读取字节码
     *
     * @param inputStream 输入流
     * @return 字节码
     */
    public static byte[] readByte(InputStream inputStream) {
        try {
            byte[] arrayOfByte = new byte[inputStream.available()];
            int r = inputStream.read(arrayOfByte);
            if (r != arrayOfByte.length) {
                throw new IOException("读取数据长度错误。");
            }
            return arrayOfByte;
        }
        catch (FileNotFoundException localFileNotFoundException) {
            log.error("文件路径不存在：" + localFileNotFoundException.getMessage());
        }
        catch (Exception localException) {
            localException.printStackTrace();
        }
        return null;
    }

    /**
     * 获取文件字节码
     *
     * @param fileFullName 文件名
     * @return 字节码
     */
    public static byte[] readByte(String fileFullName) {
        try {
            FileInputStream localFileInputStream = new FileInputStream(fileFullName);
            byte[] arrayOfByte = new byte[localFileInputStream.available()];
            int len = localFileInputStream.read(arrayOfByte);
            if (len != localFileInputStream.available()) {
                throw new IOException("文件长度错误。");
            }
            localFileInputStream.close();
            return arrayOfByte;
        }
        catch (FileNotFoundException localFileNotFoundException) {
            log.error("文件路径不存在：" + fileFullName);
        }
        catch (Exception localException) {
            localException.printStackTrace();
        }
        return null;
    }

    /**
     * 字节码写入文件
     *
     * @param fileFullName 文件名
     * @param buffer       写入内容
     * @return 是否写入成功
     */
    public static boolean writeByte(String fileFullName, byte[] buffer) {
        try {
            BufferedOutputStream localBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileFullName));
            localBufferedOutputStream.write(buffer);
            localBufferedOutputStream.close();
            return true;
        }
        catch (Exception localException) {
            localException.printStackTrace();
        }
        return false;
    }

    /**
     * 文件夹删除
     *
     * @param dir   文件夹
     * @return  是否删除成功
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            File[] arrayOfString = dir.listFiles();
            for (File file : arrayOfString) {
                if (deleteDir(file)) {
                    continue;
                }
                return false;
            }
        }
        return dir.delete();
    }

    /**
     * 序列化到文件
     *
     * @param obj          要序列化的对象
     * @param fullFileName 保存的文件名称
     */
    public static void serializeToFile(Object obj, String fullFileName) {
        try {
            ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(new FileOutputStream(fullFileName));
            localObjectOutputStream.writeObject(obj);
            localObjectOutputStream.close();
        }
        catch (IOException localIOException) {
            localIOException.printStackTrace();
        }
    }

    /**
     * 反序列化到对象
     *
     * @param fullFileName 文件名
     * @return 对象
     */
    public static Object deserializeFromFile(String fullFileName) {
        try {
            File localFile = new File(fullFileName);
            ObjectInputStream localObjectInputStream = new ObjectInputStream(new FileInputStream(localFile));
            Object localObject = localObjectInputStream.readObject();
            localObjectInputStream.close();
            return localObject;
        }
        catch (Exception localException) {
            localException.printStackTrace();
        }
        return null;
    }

    /**
     * 输入流到文件
     *
     * @param inputStream  输入流
     * @param fullFileName 文件名
     * @return 输入的内容
     * @throws IOException IOException
     */
    public static String inputStream2String(InputStream inputStream, String fullFileName)
            throws IOException {
        BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(inputStream, fullFileName));
        StringBuilder localStringBuffer = new StringBuilder();
        String str = "";
        while ((str = localBufferedReader.readLine()) != null) {
            localStringBuffer.append(str).append("\n");
        }
        return localStringBuffer.toString();
    }

    /**
     * 输入流内容读取
     *
     * @param inputStream 输入流
     * @return 流内容
     * @throws IOException IOException
     */
    public static String inputStream2String(InputStream inputStream)
            throws IOException {
        return inputStream2String(inputStream, "utf-8");
    }

    /**
     * 获取文件夹内的文件
     *
     * @param dir 文件夹名称
     * @return 内部文件/文件夹
     */
    public static File[] getFiles(String dir) {
        File localFile = new File(dir);
        return localFile.listFiles();
    }

    /**
     * 创建文件夹
     *
     * @param paramString 文件夹名称
     */
    public static void createFolderFile(String paramString) {
        createFolder(paramString, true);
    }

    /**
     * 创建文件夹
     *
     * @param fileFullName 完整文件名称（文件夹名称）
     * @param isFile       传入的是完整文件名称
     * @return 文件夹是否存在
     */
    public static boolean createFolder(String fileFullName, boolean isFile) {
        if (isFile) {
            fileFullName = fileFullName.substring(0, fileFullName.lastIndexOf(File.separator));
        }
        File localFile = new File(fileFullName);
        if (!localFile.exists()) {
            return localFile.mkdirs();
        }
        else {
            return true;
        }
    }
//
//    public static void createFolder(String paramString1, String paramString2)
//    {
//        paramString1 = StringUtil.trimSufffix(paramString1, File.separator) + File.separator + paramString2;
//        File localFile = new File(paramString1);
//        localFile.mkdir();
//    }

    /**
     * 文件夹名改名
     *
     * @param paramString1 原始名称
     * @param paramString2 修改后名称
     * @return 修改是否成功
     */
    public static boolean renameFolder(String paramString1, String paramString2) {
        File localFile = new File(paramString1);
        if (localFile.exists()) {
            return localFile.renameTo(new File(paramString2));
        }
        return false;
    }

    public static ArrayList<File> getDiretoryOnly(File paramFile) {
        ArrayList localArrayList = new ArrayList();
        if ((paramFile != null) && (paramFile.exists()) && (paramFile.isDirectory())) {
            File[] arrayOfFile = paramFile.listFiles(new FileFilter() {
                public boolean accept(File paramAnonymousFile) {
                    return paramAnonymousFile.isDirectory();
                }
            });
            for (int i = 0; i < arrayOfFile.length; i++) {
                localArrayList.add(arrayOfFile[i]);
            }
        }
        return localArrayList;
    }

    public ArrayList<File> getFileOnly(File paramFile) {
        ArrayList localArrayList = new ArrayList();
        File[] arrayOfFile = paramFile.listFiles(new FileFilter() {
            public boolean accept(File paramAnonymousFile) {
                return paramAnonymousFile.isFile();
            }
        });
        for (int i = 0; i < arrayOfFile.length; i++) {
            localArrayList.add(arrayOfFile[i]);
        }
        return localArrayList;
    }

    public static boolean deleteFile(String paramString) {
        File localFile = new File(paramString);
        return localFile.delete();
    }

    public static boolean copyFile(String paramString1, String paramString2) {
        File localFile1 = new File(paramString1);
        File localFile2 = new File(paramString2);
        FileInputStream localFileInputStream = null;
        FileOutputStream localFileOutputStream = null;
        try {
            localFileInputStream = new FileInputStream(localFile1);
            localFileOutputStream = new FileOutputStream(localFile2);
            byte[] arrayOfByte = new byte['က'];
            int i;
            while ((i = localFileInputStream.read(arrayOfByte)) != -1) {
                localFileOutputStream.write(arrayOfByte, 0, i);
            }
            localFileOutputStream.flush();
            localFileOutputStream.close();
            localFileInputStream.close();
        }
        catch (IOException localIOException) {
            localIOException.printStackTrace();
            return false;
        }
        return true;
    }

    public static void backupFile(String paramString) {
        String str = paramString + ".bak";
        File localFile = new File(str);
        if (localFile.exists()) {
            localFile.delete();
        }
        copyFile(paramString, str);
    }

    public static String getFileExt(File paramFile) {
        if (paramFile.isFile()) {
            return getFileExt(paramFile.getName());
        }
        return "";
    }

    public static String getFileExt(String paramString) {
        int i = paramString.lastIndexOf(".");
        if (i > -1) {
            return paramString.substring(i + 1).toLowerCase();
        }
        return "";
    }

    public static void copyDir(String paramString1, String paramString2)
            throws IOException {
        new File(paramString2).mkdirs();
        File[] arrayOfFile = new File(paramString1).listFiles();
        for (int i = 0; i < arrayOfFile.length; i++) {
            if (arrayOfFile[i].isFile()) {
                String str1 = arrayOfFile[i].getAbsolutePath();
                String str2 = paramString2 + "/" + arrayOfFile[i].getName();
                copyFile(str1, str2);
            }
            if (arrayOfFile[i].isDirectory()) {
                copyDirectiory(paramString1 + "/" + arrayOfFile[i].getName(), paramString2 + "/" + arrayOfFile[i].getName());
            }
        }
    }

    private static void copyDirectiory(String paramString1, String paramString2)
            throws IOException {
        new File(paramString2).mkdirs();
        File[] arrayOfFile = new File(paramString1).listFiles();
        for (int i = 0; i < arrayOfFile.length; i++) {
            if (arrayOfFile[i].isFile()) {
                String str1 = arrayOfFile[i].getAbsolutePath();
                String str2 = paramString2 + "/" + arrayOfFile[i].getName();
                copyFile(str1, str2);
            }
            if (arrayOfFile[i].isDirectory()) {
                copyDirectiory(paramString1 + "/" + arrayOfFile[i].getName(), paramString2 + "/" + arrayOfFile[i].getName());
            }
        }
    }

    public static String getFileSize(File paramFile)
            throws IOException {
        if (paramFile.isFile()) {
            FileInputStream localFileInputStream = new FileInputStream(paramFile);
            int i = localFileInputStream.available();
            localFileInputStream.close();
            return getSize(i);
        }
        return "";
    }

    public static String getSize(double paramDouble) {
        DecimalFormat localDecimalFormat = new DecimalFormat("0.00");
        double d;
        if (paramDouble > 1048576.0D) {
            d = paramDouble / 1048576.0D;
            return localDecimalFormat.format(d) + " M";
        }
        if (paramDouble > 1024.0D) {
            d = paramDouble / 1024.0D;
            return localDecimalFormat.format(d) + " KB";
        }
        return paramDouble + " bytes";
    }
//
//    public static void downLoadFile(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, String paramString1, String paramString2)
//            throws IOException
//    {
//        ServletOutputStream localServletOutputStream = paramHttpServletResponse.getOutputStream();
//        File localFile = new File(paramString1);
//        if (localFile.exists())
//        {
//            paramHttpServletResponse.setContentType("APPLICATION/OCTET-STREAM");
//            String str1 = getFileExt(paramString1);
//            if ((!Validation.isEmpty(str1)) && (str1.toLowerCase().equals("apk"))) {
//                paramHttpServletResponse.setContentType("application/vnd.android.package-archive");
//            }
//            String str2 = paramString2;
//            String str3 = paramHttpServletRequest.getHeader("USER-AGENT");
//            if ((str3 != null) && (str3.indexOf("MSIE") == -1))
//            {
//                try
//                {
//                    String localObject1 = transCharacter(paramHttpServletRequest, str2);
//                    paramHttpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + (String)localObject1);
//                }
//                catch (Exception localException1)
//                {
//                    localException1.printStackTrace();
//                }
//            }
//            else
//            {
//                str2 = URLEncoder.encode(str2, "utf-8");
//                paramHttpServletResponse.addHeader("Content-Disposition", "attachment;filename=" + str2);
//            }
//            Object localObject1 = null;
//            try
//            {
//                localServletOutputStream = paramHttpServletResponse.getOutputStream();
//                localObject1 = new FileInputStream(paramString1);
//                byte[] arrayOfByte = new byte['Ѐ'];
//                int i = 0;
//                while ((i = ((FileInputStream)localObject1).read(arrayOfByte)) > 0) {
//                    localServletOutputStream.write(arrayOfByte, 0, i);
//                }
//                localServletOutputStream.flush();
//            }
//            catch (Exception localException2)
//            {
//                localException2.printStackTrace();
//            }
//            finally
//            {
//                if (localObject1 != null)
//                {
//                    ((FileInputStream)localObject1).close();
//                    localObject1 = null;
//                }
//                if (localServletOutputStream != null)
//                {
//                    localServletOutputStream.close();
//                    localServletOutputStream = null;
//                    paramHttpServletResponse.flushBuffer();
//                }
//            }
//        }
//        else
//        {
//            localServletOutputStream.write("文件不存在!".getBytes("utf-8"));
//        }
//    }

    private static String transCharacter(HttpServletRequest paramHttpServletRequest, String paramString)
            throws Exception {
        if (paramHttpServletRequest.getHeader("USER-AGENT").toLowerCase().indexOf("msie") > 0) {
            return URLEncoder.encode(paramString, "UTF-8");
        }
        if (paramHttpServletRequest.getHeader("USER-AGENT").toLowerCase().indexOf("firefox") > 0) {
            return new String(paramString.getBytes("UTF-8"), "ISO8859-1");
        }
        return new String(paramString.getBytes("gbk"), "ISO8859-1");
    }

    public static String getParentDir(String paramString1, String paramString2) {
        File localFile = new File(paramString2);
        String str1 = localFile.getParent();
        String str2 = str1.replace(paramString1, "");
        return str2.replace(File.separator, "/");
    }
//
//    public static String getClassesPath()
//    {
//        String str = StringUtil.trimSufffix(AppUtil.getRealPath("/"), File.separator) + "\\WEB-INF\\classes\\".replace("\\", File.separator);
//        return str;
//    }
//
//    public static String getRootPath()
//    {
//        String str = StringUtil.trimSufffix(AppUtil.getRealPath("/"), File.separator) + File.separator;
//        return str;
//    }

    public static String readFromProperties(String paramString1, String paramString2) {
        String str1 = "";
        BufferedInputStream localBufferedInputStream = null;
        try {
            localBufferedInputStream = new BufferedInputStream(new FileInputStream(paramString1));
            Properties localProperties = new Properties();
            localProperties.load(localBufferedInputStream);
            str1 = localProperties.getProperty(paramString2);
            String str2 = str1;
            return str2;
        }
        catch (Exception localException) {
            localException.printStackTrace();
        }
        finally {
            if (localBufferedInputStream != null) {
                try {
                    localBufferedInputStream.close();
                }
                catch (IOException localIOException3) {
                    localIOException3.printStackTrace();
                }
            }
        }
        return str1;
    }

    public static boolean saveProperties(String paramString1, String paramString2, String paramString3) {
        StringBuffer localStringBuffer = new StringBuffer();
        int i = 0;
        BufferedReader localBufferedReader = null;
        try {
            localBufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(paramString1), "utf-8"));
            String str;
            while ((str = localBufferedReader.readLine()) != null) {
                if (str.startsWith(paramString2)) {
                    localStringBuffer.append(paramString2 + "=" + paramString3 + "\r\n");
                    i = 1;
                }
                else {
                    localStringBuffer.append(str + "\r\n");
                }
            }
            if (i == 0) {
                localStringBuffer.append(paramString2 + "=" + paramString3 + "\r\n");
            }
            writeFile(paramString1, localStringBuffer.toString(), "utf-8");
            boolean bool1 = true;
            return bool1;
        }
        catch (Exception localException) {
            localException.printStackTrace();
            boolean bool2 = false;
            return bool2;
        }
        finally {
            if (localBufferedReader != null) {
                try {
                    localBufferedReader.close();
                }
                catch (IOException localIOException3) {
                    localIOException3.printStackTrace();
                }
            }
        }
    }

    public static boolean delProperties(String paramString1, String paramString2) {
        StringBuffer localStringBuffer = new StringBuffer();
        BufferedReader localBufferedReader = null;
        try {
            localBufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(paramString1), "utf-8"));
            String str;
            while ((str = localBufferedReader.readLine()) != null) {
                if (!str.startsWith(paramString2)) {
                    localStringBuffer.append(str + "\r\n");
                }
            }
            writeFile(paramString1, localStringBuffer.toString(), "utf-8");
            boolean bool1 = true;
            return bool1;
        }
        catch (Exception localException) {
            localException.printStackTrace();
            boolean bool2 = false;
            return bool2;
        }
        finally {
            if (localBufferedReader != null) {
                try {
                    localBufferedReader.close();
                }
                catch (IOException localIOException3) {
                    localIOException3.printStackTrace();
                }
            }
        }
    }

    public static List<Class<?>> getAllClassesByInterface(Class<?> paramClass, boolean paramBoolean)
            throws IOException, ClassNotFoundException, IllegalStateException {
        if (!paramClass.isInterface()) {
            throw new IllegalStateException("Class not a interface.");
        }
        ClassLoader localClassLoader = paramClass.getClassLoader();
        String str = paramBoolean ? paramClass.getPackage().getName() : "/";
        return findClasses(paramClass, localClassLoader, str);
    }

    private static List<Class<?>> findClasses(Class<?> paramClass, ClassLoader paramClassLoader, String paramString)
            throws IOException, ClassNotFoundException {
        ArrayList localArrayList = new ArrayList();
        String str = paramString.replace(".", "/");
        Object localObject;
        if (!str.equals("/")) {
            localObject = paramClassLoader.getResources(str);
            while (((Enumeration) localObject).hasMoreElements()) {
                URL localURL = (URL) ((Enumeration) localObject).nextElement();
                localArrayList.addAll(findResources(paramClass, new File(localURL.getFile()), paramString));
            }
        }
        else {
            localObject = paramClassLoader.getResource("").getPath();
            localArrayList.addAll(findResources(paramClass, new File((String) localObject), paramString));
        }
        return localArrayList;
    }

    private static List<Class<?>> findResources(Class<?> paramClass, File paramFile, String paramString)
            throws ClassNotFoundException {
        ArrayList localArrayList = new ArrayList();
        if (!paramFile.exists()) {
            return Collections.EMPTY_LIST;
        }
        File[] arrayOfFile1 = paramFile.listFiles();
        for (File localFile : arrayOfFile1) {
            if (localFile.isDirectory()) {
                if (!localFile.getName().contains(".")) {
                    if (!paramString.equals("/")) {
                        localArrayList.addAll(findResources(paramClass, localFile, paramString + "." + localFile.getName()));
                    }
                    else {
                        localArrayList.addAll(findResources(paramClass, localFile, localFile.getName()));
                    }
                }
            }
            else if (localFile.getName().endsWith(".class")) {
                Class localClass = null;
                if (!paramString.equals("/")) {
                    localClass = Class.forName(paramString + "." + localFile.getName().substring(0, localFile.getName().length() - 6));
                }
                else {
                    localClass = Class.forName(localFile.getName().substring(0, localFile.getName().length() - 6));
                }
                if ((paramClass.isAssignableFrom(localClass)) && (!paramClass.equals(localClass))) {
                    localArrayList.add(localClass);
                }
            }
        }
        return localArrayList;
    }

    public static Object cloneObject(Object paramObject)
            throws Exception {
        ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(localByteArrayOutputStream);
        localObjectOutputStream.writeObject(paramObject);
        ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(localByteArrayOutputStream.toByteArray());
        ObjectInputStream localObjectInputStream = new ObjectInputStream(localByteArrayInputStream);
        return localObjectInputStream.readObject();
    }

    public static boolean isFileType(String paramString1, String paramString2) {
        boolean bool = false;
        if (("IMAGE".equals(paramString2)) && ((paramString1.toUpperCase().equals("JPG")) || (paramString1.toUpperCase().equals("PNG")) || (paramString1.toUpperCase().equals("GIF")) || (paramString1.toUpperCase().equals("JPEG")))) {
            bool = true;
        }
        return bool;
    }

    public static boolean isFileType(String paramString, String[] paramArrayOfString) {
        boolean bool = false;
        if ((paramArrayOfString != null) && (paramArrayOfString.length > 0)) {
            for (int i = 0; i < paramArrayOfString.length; i++) {
                if (paramString.toUpperCase().equals(paramArrayOfString[i].toUpperCase())) {
                    return true;
                }
            }
        }
        return bool;
    }

//    public static boolean isErrorFileType(String paramString)
//    {
//        String[] arrayOfString = null;
//        String str = AppConfigUtil.get("File_Filter_Val");
//        if ((AppConfigUtil.getICache("FILEFILTER_KEY") == null) && (str != null))
//        {
//            arrayOfString = str.split("\\|");
//            AppConfigUtil.setICache("FILEFILTER_KEY", arrayOfString);
//        }
//        else
//        {
//            arrayOfString = (String[])AppConfigUtil.getICache("FILEFILTER_KEY");
//        }
//        return isFileType(paramString, arrayOfString);
//    }
}
