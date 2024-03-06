package org.choongang.board.service;

import lombok.RequiredArgsConstructor;
import org.choongang.board.controllers.RequestBoardConfig;
import org.choongang.board.entities.Board;
import org.choongang.board.repositories.BoardRepository;
import org.choongang.member.constants.Authority;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class BoardConfigSaveService {
    private final BoardRepository boardRepository;

    public void save(RequestBoardConfig form) {
        String mode = form.getMode();
        mode = StringUtils.hasText(mode) ? mode : "add";
        String bid = form.getBid();

        Board board = null;
        if (mode.equals("edit") && StringUtils.hasText(bid)) {
            board = boardRepository.findById(bid).orElseThrow(BoardNotFoundException::new);
        } else {
            board = Board.builder()
                    .bid(bid)
                    .build();
        }

        board.setBName(form.getBName());
        board.setActive(form.isActive());
        board.setPagePerRows(form.getPagePerRows());
        board.setPageRanges(form.getPageRanges());
        board.setCategory(form.getCategory());

        board.setWriteAuthority(Authority.valueOf(form.getWriteAuthority()));
        board.setListAuthority(Authority.valueOf(form.getListAuthority()));
        board.setViewAuthority(Authority.valueOf(form.getViewAuthority()));
        board.setCommentAuthority(Authority.valueOf(form.getCommentAuthority()));

        boardRepository.saveAndFlush(board);
    }
}
