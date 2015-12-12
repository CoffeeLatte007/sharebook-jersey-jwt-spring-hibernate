package com.lclizhao.sharebook.utils;/**
 * Created by lizhaoz on 2015/12/1.
 */

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import java.io.*;
import java.security.Key;
/**
 * @Name:
 * @Author: lizhao（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2015-11-26（创建日期）
 * @Description:
 */
public class KeyUtil {
    private static final Logger logger=org.apache.logging.log4j.LogManager.getLogger(KeyUtil.class.getName());
    public static Key getKey(ServletContext context) {
        String path=(context.getRealPath("/key"));
        File file=new File(path,"key.txt");
        try {
        if(!file.exists()){
           Key key =MacProvider.generateKey(SignatureAlgorithm.HS512);
            ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(file));
            oo.writeObject(key);
            oo.close();
            return key;
        }
        ObjectInputStream ois = null;

            ois = new ObjectInputStream(new FileInputStream(file));

        Key key= (Key) ois.readObject();
            return key;
        } catch (Exception e) {
            logger.debug(e);
            e.printStackTrace();
            return null;
        }

    }
//    @Test
//    public  void test() throws Exception {
//        getKey();
//    }
}
