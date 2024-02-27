package org.choongang.file.service;

import lombok.RequiredArgsConstructor;
import org.choongang.file.controllers.RequestFileUpload;
import org.choongang.file.entities.FileInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FileInfoSaveService {

    public List<FileInfo> save(RequestFileUpload form) {
        List<FileInfo> items = new ArrayList<>();
        MultipartFile[] files = form.getFile();
        String gid = form.getGid();
        String location = form.getLocation();
        boolean imageOnly = Objects.requireNonNullElse(form.getImageOnly(), false);

        for (MultipartFile file : files) {
            // 파일명.확장자, 파일명.파일명.확장자
            String fileName = file.getOriginalFilename();

            FileInfo item = FileInfo.builder()
                    .gid(gid)
                    .location(location)
                    .fileName(fileName)
                    .contentType(file.getContentType())
                    .build();
                    //.extension(extension)
        }

        return null;
    }
}
