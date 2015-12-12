package com.lclizhao.sharebook;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.junit.Test;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;


/**
 * @Name:TestClient.java
 * @Create Date: 15/12/07（创建日期）
 * @Description:
 * 客户端测试
 *
 * @version        1.0, 15/12/07
 * @author         Lizhao    
 */
public class TestClient  {


    @Test
    public void testFile() throws IOException{
        File file =new File("F://","p.png");
        Client client= ClientBuilder.newBuilder().register(MultiPartFeature.class).build();

        WebTarget target=client.target("http://localhost:8080/share/webapi").path("annotation/2bd8c978517204340151720763250000");
        FileDataBodyPart bodyPart=new FileDataBodyPart("file",file);
        FormDataMultiPart formDataMultiPart=new FormDataMultiPart();
        formDataMultiPart.field("content","为大家说哪家快递件萨克第九届奥斯卡的健康撒阿红的教科书的健康会撒娇看到三大阿斯达").field("page","142").bodyPart(bodyPart);

        String response=target.request().post(Entity.entity(formDataMultiPart, formDataMultiPart.getMediaType()), String.class);

        System.out.println(response);
    }


}


//~ Formatted by Jindent --- http://www.jindent.com
