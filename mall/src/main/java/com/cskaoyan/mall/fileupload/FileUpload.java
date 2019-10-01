package com.cskaoyan.mall.fileupload;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class FileUpload {
    /**
     * 处理带文件的form表单的方法
     * @param req
     * @param resp
     * @return
     * @throws IOException
     */
    public static HashMap<String, Object> parseRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletContext servletContext = req.getServletContext();
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if(!isMultipart){
            resp.getWriter().println("需要有文件上传功能");
            return null;
        }
        HashMap<String, Object> map = new HashMap<>();//存储表单数据
        DiskFileItemFactory factory = new DiskFileItemFactory();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> items = upload.parseRequest(req);
            Iterator<FileItem> iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = iter.next();
                if (item.isFormField()) {
                    processFormField(item,req,map);
                } else {
                    processUploadedFile(item,req,map);
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 处理普通表单数据(type:text,number等)
     * @param item
     * @param req
     * @param map
     */
    private static void processFormField(FileItem item, HttpServletRequest req, HashMap<String,Object> map) {
        if (item.isFormField()) {
            String name = item.getFieldName();
            String value=null;
            try {
               value = item.getString("utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            map.put(name,value);
        }
    }

    /**
     * 处理上传的文件(type=file)
     * @param item
     * @param req
     */
    private static void processUploadedFile(FileItem item, HttpServletRequest req, HashMap<String,Object> map) {
        if (!item.isFormField()) {
            String fieldName = item.getFieldName();//name 属性名
            String fileName = item.getName();//文件名
            String contentType = item.getContentType();
            boolean isInMemory = item.isInMemory();
            long sizeInBytes = item.getSize();
            String uuid = UUID.randomUUID().toString();
            //第二个问题，一个目录内文件数目过多，应该怎么办？散列
            fileName = uuid + "-" + fileName;
            String upload = req.getServletContext().getRealPath("upload");
            String purl="upload";
            int hashCode = fileName.hashCode();
            //int 32  16  8   1 2 3 4 a b f 3  16^8 40亿左右
            String s = Integer.toHexString(hashCode);
            char[] chars = s.toCharArray();
            for (char aChar : chars) {
                upload = upload + "/" + aChar;
                purl= purl + "/" + aChar;
            }
            purl=purl + "/" + fileName;//文件相对与当前应用下的路径
            map.put(fieldName,purl);
            //System.out.println(purl);
            File file = new File(upload + "/" + fileName);//文件存放位置，可修改
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            try {
                item.write(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

