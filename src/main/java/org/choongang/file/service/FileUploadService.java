package org.choongang.file.service;

import lombok.RequiredArgsConstructor;
import org.choongang.configs.FileProperties;
import org.choongang.file.controllers.RequestFileUpload;
import org.choongang.file.entities.FileInfo;
import org.choongang.file.repositories.FileInfoRepository;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(FileProperties.class)
public class FileUploadService {
    private final FileInfoSaveService saveService;
    private final FileProperties props;
    private final FileInfoRepository repository;

    public List<FileInfo> upload(RequestFileUpload form) {

        List<FileInfo> items = saveService.save(form);

        for (FileInfo item : items) {
            MultipartFile file = item.getFile();

            long seq = item.getSeq();
            String uploadDir = props.getPath() + (seq % 10L);
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            String extension = item.getExtension();
            File uploadFile = new File(uploadDir + "/" + seq +
                    (StringUtils.hasText(extension) ? "." + extension : ""));
            try {
                file.transferTo(uploadFile);
                // 파일 부가정보 - 파일 path, 파일 url

            } catch (IOException e) {
                // 업로드 실패 -> 파일 정보 제거
                repository.delete(item);
                items.remove(item);
            }
        }

        repository.flush();

        return items;
    }
}
