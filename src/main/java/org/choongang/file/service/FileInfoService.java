package org.choongang.file.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.choongang.configs.FileProperties;
import org.choongang.file.entities.FileInfo;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(FileProperties.class)
public class FileInfoService {

    private final FileProperties props;
    private final HttpServletRequest request;

    public void addFileInfo(FileInfo item) {
        long seq = item.getSeq();
        String extension = item.getExtension();
        String fileName = seq + (StringUtils.hasText(extension) ? "." + extension : "");
        String filePath = uploadDir(seq) + "/" + fileName;
        String fileUrl = uploadUrl(seq) + "/" + fileName;

        item.setFilePath(filePath);
        item.setFileUrl(fileUrl);
    }

    public String uploadDir(long seq) {
        String uploadDir = props.getPath() + (seq % 10L);
        File _uploadDir = new File(uploadDir);
        if (!_uploadDir.exists()) {
            _uploadDir.mkdir();
        }

        return uploadDir;
    }

    public String uploadUrl(long seq) {
        // http, https
        String host = String.format("%s://%s:%d",
                    request.getScheme(), request.getServerName(), request.getServerPort());

        return host + props.getUrl() + (seq % 10L);
    }
}
