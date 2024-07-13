package com.example.whb.common.controller;


import com.example.whb.common.response.AjaxResult;
import com.example.whb.exception.CoderitlException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


@RestController
@RequestMapping("/file")
public class UploadController extends BaseController{

    @Value("${file.upload-dir:'D://upload'}")
    private String uploadDir;

    @PostMapping("/upload/avatar")
    public AjaxResult uploadAvatar(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return AjaxResult.error(500, "文件不能为空!");
        }

        // 获取当前日期并生成文件路径
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String uuid = UUID.randomUUID().toString().toLowerCase();
        String fileExtension = getFileExtension(file.getOriginalFilename());
        String fileName = currentDate + "-" + uuid + fileExtension;
        String relativePath = "/uploads/" + fileName;

        try {
            Path path = Paths.get(uploadDir + File.separator + relativePath);
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());
            return success("上传成功", relativePath);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.error(500, "文件上传失败");
        }
    }

    @GetMapping("/download")
    public void downloadFile(@RequestParam("fileName") String fileName, HttpServletResponse response) {
        try {
            Path path = Paths.get(uploadDir + File.separator + fileName);
            if (Files.exists(path)) {
                response.setContentType(Files.probeContentType(path));
                response.addHeader("Content-Disposition", "attachment; filename=" + path.getFileName());
                Files.copy(path, response.getOutputStream());
                response.getOutputStream().flush();
            } else {
                throw new CoderitlException(404, "文件未找到");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new CoderitlException(500, "文件下载失败");
        }
    }

    private String getFileExtension(String fileName) {
        if (fileName == null) {
            return "";
        }
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex);
    }
}
