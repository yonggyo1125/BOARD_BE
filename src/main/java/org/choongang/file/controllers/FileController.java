package org.choongang.file.controllers;

import lombok.RequiredArgsConstructor;
import org.choongang.commons.rests.JSONData;
import org.choongang.file.entities.FileInfo;
import org.choongang.file.service.FileDeleteService;
import org.choongang.file.service.FileDownloadService;
import org.choongang.file.service.FileInfoService;
import org.choongang.file.service.FileUploadService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/file")
@RequiredArgsConstructor
public class FileController {
    private final FileUploadService uploadService;
    private final FileInfoService infoService;
    private final FileDeleteService deleteService;
    private final FileDownloadService downloadService;

    // 파일 업로드
    @PostMapping
    public JSONData upload(RequestFileUpload form) {
        //form.setFile(file);
        List<FileInfo> items = uploadService.upload(form);

        return new JSONData(items);
    }

    // 파일 삭제
    @DeleteMapping
    public void delete(RequestFileDelete form) {
        Long seq = form.getSeq();
        String gid = form.getGid();
        String location = form.getLocation();

        if (seq != null) {
            deleteService.delete(seq);
        } else if (StringUtils.hasText(gid)) {
            deleteService.delete(gid, location);
        }
    }

    @GetMapping("/download/{seq}")
    public void download(@PathVariable("seq") Long seq) {
        downloadService.download(seq);
    }

    @GetMapping("/{seq}")
    public JSONData info(@PathVariable("seq") Long seq) {
        FileInfo item = infoService.get(seq);

        return new JSONData(item);
    }

    @GetMapping
    public JSONData list(@RequestParam("gid") String gid,
                         @RequestParam(name = "location", required = false) String location,
                         @RequestParam(name = "mode", required = false) String mode) {

        List<FileInfo> items = StringUtils.hasText(mode) ? infoService.getList(gid, location, mode) : infoService.getListDone(gid, location);

        return new JSONData(items);
    }
}
