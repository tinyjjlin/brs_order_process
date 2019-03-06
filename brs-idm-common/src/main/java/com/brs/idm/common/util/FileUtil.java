package com.brs.idm.common.util;

import com.brs.idm.common.service.exception.CustomException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @author tiny lin
 * @date 2019/1/7
 */
public class FileUtil {

    /**
     * 上传文件
     * @param file
     * @param uploadDir
     * @throws Exception
     */
    public static String uploadFile(MultipartFile file,String uploadDir) {
        //generate new file name
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String targetFileName =UUID.randomUUID()+suffix;
        String targetFilePath = uploadDir + targetFileName;
        File targetDir = new File(uploadDir);
        if(!targetDir.exists()){
            targetDir.mkdirs();
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(targetFilePath);
            out.write(file.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new CustomException("上传文件：目标文件不存在！");
        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomException("上传文件：目标文件失败！");
        }finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        System.out.println("upload relative path================>"+targetFilePath);
        return targetFilePath;
    }


    /**
     * 上传多个文件到服务器
     * @param files
     * @param uploadDir
     * @return
     */
    public static  String  uploadMultFile(MultipartFile[] files,String uploadDir) throws Exception {
        StringBuilder multFileNames = new StringBuilder();
        if(files != null){
            for(int i=0;i<files.length;i++){
                String  fileName = uploadFile(files[i],uploadDir );
                multFileNames.append(fileName+";");
            }
        }
        return multFileNames.toString();
    }

    /**
     * 上传文件到服务器，并返回新的文件名称
     * @param file
     * @param uploadDir
     * @return
     * @throws IOException
     */
    public static String  uploadFile2(MultipartFile file,String uploadDir) throws IOException {
        //generate new file name
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String targetFileName  = UUID.randomUUID()+suffix;
        String targetFilePath = uploadDir+targetFileName;
        File serverFile = new File(uploadDir+targetFileName);
        if(!serverFile.getParentFile().exists()){
            serverFile.getParentFile().mkdirs();
        }
        file.transferTo(serverFile);
        System.out.println("upload relative path================>"+targetFilePath);
        return targetFileName;
    }

    /**
     * 上传多个文件到服务器
     * @param files
     * @param uploadDir
     * @return
     */
    public static  String  uploadMultFile2(MultipartFile[] files,String uploadDir) throws IOException {
        StringBuilder multFileNames = new StringBuilder();
        if(files != null){
            for(int i=0;i<files.length;i++){
                String  fileName = uploadFile2(files[i],uploadDir );
                multFileNames.append(fileName+";");
            }
        }
        return multFileNames.toString();
    }


}
