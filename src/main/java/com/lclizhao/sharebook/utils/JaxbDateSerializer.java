package com.lclizhao.sharebook.utils;/**
 * Created by lizhaoz on 2015/11/30.
 */

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Name:
 * @Author: lizhao（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2015-11-26（创建日期）
 * @Description:
 */
public class JaxbDateSerializer extends XmlAdapter<String,Date> {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

    public JaxbDateSerializer() {
        super();


    }
    @Override
    public Date unmarshal(String v) throws Exception {
        return dateFormat.parse(v);
    }

    @Override
    public String marshal(Date v) throws Exception {
        return dateFormat.format(v);
    }
}
