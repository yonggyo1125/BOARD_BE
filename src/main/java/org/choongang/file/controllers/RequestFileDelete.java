package org.choongang.file.controllers;

import lombok.Data;

@Data
public class RequestFileDelete {
    private Long seq; // 파일 등록번호
    private String gid;
    private String location;
}
