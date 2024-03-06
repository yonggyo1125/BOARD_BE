package org.choongang.board.service;

import lombok.RequiredArgsConstructor;
import org.choongang.board.entities.Board;
import org.choongang.board.repositories.BoardRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardConfigDeleteService {

    private final BoardRepository boardRepository;

    public void delete(String bid) {
        Board board = boardRepository.findById(bid).orElseThrow(BoardNotFoundException::new);

        boardRepository.delete(board);
        boardRepository.flush();
    }
}
