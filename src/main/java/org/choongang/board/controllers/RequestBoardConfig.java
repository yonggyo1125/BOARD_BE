package org.choongang.board.controllers;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.choongang.member.constants.Authority;

@Data
public class RequestBoardConfig {

    private String mode = "add";

    @NotBlank
    private String bid;

    @NotBlank
    private String bName;

    private boolean active;

    private int pagePerRows = 20;

    private int pageRanges = 10;

    private String category;

    private String writeAuthority = Authority.ALL.name();

    private String listAuthority = Authority.ALL.name();

    private String viewAuthority = Authority.ALL.name();

    private String commentAuthority = Authority.ALL.name();
}
