package org.choongang.file.service;

import lombok.RequiredArgsConstructor;
import org.choongang.file.controllers.RequestFileUpload;
import org.choongang.file.entities.FileInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileUploadService {
    private final FileInfoSaveService saveService;

    public List<FileInfo> upload(RequestFileUpload form) {

        List<FileInfo> items = saveService.save(form);

        for (FileInfo item : items) {
            MultipartFile file = item.getFile();
        }
    }
}
