/**
 * YmFileUtil.java
 * yumodev
 * 2014-12-3
 */
package com.yumo.common.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import android.text.TextUtils;

import com.yumo.common.define.YmDefine;

public class YmFileUtil {
    /**
     * 检查文件是否存在，不存在则创建该文件
     * @param fileName
     * @param createNewFile
     * @return
     */
    public static boolean isExistFile(String fileName, boolean createNewFile) throws IOException{
        File file = new File(fileName);
        boolean result = file.exists();
        if (!result && createNewFile) {
            result = createFile(file);
        }
        return result;
    }

    /**
     * 新建一个文件。如果其父目录不存在，也同时创建父目录。
     * @param file
     * @return
     * @throws IOException
     */
    public static boolean createFile(File file) throws IOException {
        if(!file.exists()) {
            file.getParentFile().mkdirs();
        }
        return file.createNewFile();
    }

    /**
     * 新建目录
     * @param dir
     */
    public static void makeDir(File dir) {
        if(!dir.getParentFile().exists()) {
            makeDir(dir.getParentFile());
        }
        dir.mkdir();
    }

    /**
     * 目录是否存在
     * @param directory
     * @return
     */
    public static boolean isExistDirectory(String directory) {
        if (TextUtils.isEmpty(directory)){
            return false;
        }

        boolean result = false;
        File file = new File(directory);
        if (file.exists() && file.isDirectory()) {
            result = true;
        }
        return result;
    }

    /**
     * @param dir
     * @return
     * @brief 创建目录
     */
    public static boolean createDirectory(String dir) {
        if (TextUtils.isEmpty(dir)){
            return false;
        }

        boolean result = true;
        File file = new File(dir);
        if (!file.exists()) {
            result = file.mkdirs();
        }
        return result;
    }

    /**
     * 通过一个文件的完整路径获取其文件名字。
     * 本函数未对传递的路径进行合法性检测。
     * @param path 文件路径
     * @return: String 返回文件名字。
     */
    public static String getFileNameFromPath(final String path) {
        if (TextUtils.isEmpty(path)){
            return path;
        }
        String[] arr = path.split(File.separator);
        return arr.length == 0 ? "" : arr[arr.length - 1];
    }

    /**
     * 通过一个文件的完整路径获取文件不含扩展名的文件名.
     * 本函数未对传递的路径进行合法性检测
     * @param path 文件路径
     * @return: String 返回不含扩展名的文件名
     */
    public static String getFileNameNoTensionByPath(final String path) {
        String fileName = getFileNameFromPath(path);
        if (TextUtils.isEmpty(fileName)){
            return fileName;
        }

        int nFind = fileName.indexOf('.');
        if (nFind > 0) {
            return fileName.substring(0, nFind);
        }
        return "";
    }

    /**
     * 返回扩展名
     * @param fileName
     * @return
     */
    public static String getFileExtension(final String fileName){
        if (TextUtils.isEmpty(fileName)){
            return "";
        }
        int nFind = fileName.lastIndexOf('.');
        if (nFind >= 0) {
            return fileName.substring(nFind);
        }
        return "";
    }

    /**
     * 保存Bytes 到一个文件
     * @param file
     * @param bytes
     * @return
     */
    public static int saveFile(String file, byte[] bytes) throws IOException {
        if (!isExistFile(file, true)){
            return YmDefine.FILE_NO_FOUND;
        }

        try {
            FileOutputStream out = new FileOutputStream(file, false);
            out.write(bytes);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return YmDefine.FILE_NO_FOUND;
        }

        return YmDefine.FILE_SUCCESS;
    }

    public static String readString(String fileName) throws IOException{
        if (!isExistFile(fileName, false)){
            return null;
        }

        String inputStr = "";
        File inputFile = new File(fileName);
        Scanner input = new Scanner(inputFile);
        while (input.hasNext()) {
            inputStr += input.next();
        }
        input.close();
        return inputStr;
    }

    /**
     * 删除一个文件
     * @param fileName
     * @return
     */
    public static boolean deleteFile(final String fileName) {
        boolean result = false;
        File file = new File(fileName);
        if (file.isFile() && file.exists()) {
            result = file.delete();
        }
        return result;
    }

    /**
     * 重命名文件，如果新文件已存在，返回false
     * @param oldName
     * @param newName
     * @oaram delNewFile 如果新文件已经存在是否删除删除
     * @return
     */
    public static boolean rename(String oldName, String newName, boolean delNewFile){
        File oldFile = new File(oldName);
        File newFile = new File(newName);

        if (!oldFile.exists()){
            return false;
        }

        boolean result = true;
        if (newFile.exists()){
            if (delNewFile){
                result = deleteFile(newName);
            }
        }

        if(result && oldFile.renameTo(newFile)){
            return true;
        }
        return false;
    }
}
