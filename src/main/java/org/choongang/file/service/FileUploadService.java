package org.choongang.file.service;

import lombok.RequiredArgsConstructor;
import org.choongang.file.controllers.RequestFileUpload;
import org.choongang.file.entities.FileInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileUploadService {
    private final FileInfoSaveService saveService;

    public List<FileInfo> upload(RequestFileUpload form) {


    }
}
