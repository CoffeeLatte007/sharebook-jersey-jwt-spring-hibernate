package com.lclizhao.sharebook.utils;

import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * Created by lizhaoz on 2015/12/9.
 */

public class FileUtils {
    private static Logger logger= org.apache.logging.log4j.LogManager.getLogger(FileUtils.class);
    public static String annotionImageUpload(InputStream fileInputStream, FormDataContentDisposition disposition, String model)  {
        String fileName=disposition.getFileName();
        String rootPath=new File("").getAbsolutePath();
        String basepath=File.separator+"upload"+File.separator+model;
        String datepath= DateUtils.dateToStringByFile(new Date());
        String filePath=rootPath+basepath+datepath;
        System.out.println(filePath);
        File file=new File(filePath);
        if (!file.exists()){
            file.mkdirs();
        }
        String prifix=fileName.substring(fileName.lastIndexOf("."));
        String uuidString=UUID.randomUUID().toString();
        String uuidFileName=uuidString +prifix;
        File destFile =new File(file,uuidFileName);
        try {
            org.apache.commons.io.FileUtils.copyInputStreamToFile(fileInputStream,destFile);
        } catch (IOException e) {
            logger.error(e);
            e.printStackTrace();
        }
        // 格式/2015/12/09/uuId
        return datepath+uuidFileName;
    }

    public static File getFile(String filepath, String model) {
        String rootPath=new File("").getAbsolutePath();
        String basepath=File.separator+"upload"+File.separator+model;
        filepath=rootPath+basepath+"/"+filepath;
        File file=new File(filepath);
        return file;
    }
    //对上面静态方法的测试
//    public static void main(String[] args) {
//        annotionImageUpload(null,null,"Annoation");
//    }
}
