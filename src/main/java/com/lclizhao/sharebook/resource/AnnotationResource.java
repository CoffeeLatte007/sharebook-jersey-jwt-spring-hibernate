package com.lclizhao.sharebook.resource;

//~--- non-JDK imports --------------------------------------------------------

import com.lclizhao.sharebook.daomain.Annotation;
import com.lclizhao.sharebook.daomain.User_Book;
import com.lclizhao.sharebook.resource.Exception.AppException;
import com.lclizhao.sharebook.service.AnnotationService;
import com.lclizhao.sharebook.service.CollectionService;
import com.lclizhao.sharebook.service.UserService;
import com.lclizhao.sharebook.utils.AnnotionUtil;
import com.lclizhao.sharebook.utils.FileUtils;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

//~--- JDK imports ------------------------------------------------------------

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.List;

import javax.activation.MimetypesFileTypeMap;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * @Name:AnnotationResource.java
 * @Create Date: 15/12/07（创建日期）
 * @Description:
 *     笔记资源类
 * @version        1.0, 15/12/07
 * @author         Lizhao
 */
@PermitAll()
@Path("/annotation")
public class AnnotationResource {
    @Autowired
    AnnotationService annotationService;
    @Autowired
    CollectionService collectionService;
    @Autowired
    UserService       userService;
    @Context
    SecurityContext   securityContext;

    /**
     * author：Lizhao
     * Date:15/12/09
     * version:1.0
     *
     * @param fileInputStream
     * @param disposition
     * @param content
     * @param page_No
     * @param digest
     * @param ubId
     * @return Response HTTP状态码201
     *
     * @throws AppException
     */
    @POST
    @Consumes({ MediaType.MULTIPART_FORM_DATA })
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/collection/{ubId:[0-9A-Za-z]{32}}")
    public Response saveAnnotionResource(@FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition disposition, @FormDataParam("content") String content,
            @FormDataParam("page") String page_No, @FormDataParam("digest") String digest,
            @PathParam("ubId") String ubId)
            throws AppException {

//      System.out.println(fileInputStream.toString());
//      System.out.println(disposition.getName()+" "+disposition.getFileName()+" "+disposition.getSize()+" "+disposition.getType()+" "+disposition.getParameters());
//      System.out.println(context);
        // 摘要和摘要图片二选一，没有摘要文字的时候就传这个
        AnnotionUtil.checkAnnotionResource(disposition, content, page_No, digest);

        String    tel       = securityContext.getUserPrincipal().getName();
        String    userId    = userService.findUserByTel(tel).getUserID();
        User_Book user_book = collectionService.getById(ubId);

        if ((user_book == null) &&!user_book.getUserId().equals(userId)) {
            throw new AppException(404, 4008, "link", "NO Collection", "No Collection");
        }

        Annotation annotation = annotationService.saveAnnotion(fileInputStream, disposition, content, page_No, digest,
                                    ubId);

        return Response.status(201).entity(annotation).build();
    }

    /**
     * author：Lizhao
     * Date:15/12/10
     * version:1.0
     *
     * @param fileInputStream
     * @param disposition
     * @param content
     * @param page_No
     * @param digest
     * @param annotationId
     *
     * @return
     *
     * @throws AppException
     */
    @PUT
    @Consumes({ MediaType.MULTIPART_FORM_DATA })
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{annotationId:[0-9A-Za-z]{32}}")
    public Response updateAnnotionResource(@FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition disposition, @FormDataParam("content") String content,
            @FormDataParam("page") String page_No, @FormDataParam("digest") String digest,
            @PathParam("annotationId") String annotationId)
            throws AppException {
        AnnotionUtil.checkAnnotionResource(disposition, content, page_No, digest);

        String tel    = securityContext.getUserPrincipal().getName();
        String userId = userService.findUserByTel(tel).getUserID();

        if (!annotationService.getbooleanById(annotationId, userId)) {
            throw new AppException(404, 4009, "link", "NO Annotation", "No Annotation");
        }

        Annotation annotation = annotationService.updateAnnotion(fileInputStream, disposition, content, page_No,
                                    digest, annotationId);

        return Response.status(201).entity(annotation).build();
    }

    /**
     * author：Lizhao
     * Date:15/12/10
     * version:1.0
     *
     * @param annotationId
     *
     * @return
     *
     * @throws AppException
     */
    @DELETE
    @Path("/{annotationId:[0-9A-Za-z]{32}}")
    public Response deletannotation(@PathParam("annotationId") final String annotationId) throws AppException {
        String tel    = securityContext.getUserPrincipal().getName();
        String userId = userService.findUserByTel(tel).getUserID();

        if (!annotationService.getbooleanById(annotationId, userId)) {
            throw new AppException(404, 4009, "link", "NO Annotation", "No Annotation");
        }

        annotationService.delete(annotationId);

        return Response.status(204).entity("deleted annotation success id="
                               + annotationId).type("application/json").build();
    }

    /**
     * author：Lizhao
     * Date:15/12/10
     * version:1.0
     *
     * @param ubId
     *
     * @return
     *
     * @throws AppException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/collection/{ubId:[0-9A-Za-z]{32}}")
    public Response getAnnotationByCollection(@PathParam("ubId") String ubId) throws AppException {
        String    tel       = securityContext.getUserPrincipal().getName();
        String    userId    = userService.findUserByTel(tel).getUserID();
        User_Book user_book = collectionService.getById(ubId);

        if ((user_book == null) &&!user_book.getUserId().equals(userId)) {
            throw new AppException(404, 4008, "link", "NO Collection", "No Collection");
        }

        List<Annotation> annotations = annotationService.getAnnotationByCollection(ubId);

        return Response.status(200).entity(annotations).build();
    }

    /**
     * author：Lizhao
     * Date:15/12/10
     * version:1.0
     *
     * @param uuid
     * @param year
     * @param month
     * @param day
     * @param response
     *
     * @throws IOException
     * http://localhost:8080/share/webapi/annotation/2015/12/09/9ac2ace1-ea57-4765-ae70-4049ba896658.png
     */
    @GET
    @Path("{year:[0-9]{4}}/{month:[0-9]{1,2}}/{day:[0-9]{1,2}}/{uuidname}")
    @Produces("image/*")
    public void getAnnotationImage(@PathParam("uuidname") String uuid, @PathParam("year") String year,
                                   @PathParam("month") String month, @PathParam("day") String day,
                                   @Context HttpServletResponse response)
            throws IOException {
        String filepath = year + "/" + month + "/" + day + "/" + uuid;
        File   file     = FileUtils.getFile(filepath, "annotionImage");

        // org.apache.commons.io.FileUtils.copyFile();
//      String mt=new MimetypesFileTypeMap().getContentType(file);
        FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
