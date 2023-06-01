package org.koreait.models.board;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.koreait.entities.Board;
import org.koreait.entities.BoardData;
import org.koreait.models.board.config.BoardConfigInfoService;
import org.koreait.repositories.BoardDataRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardDataInfoService {

    private final BoardDataRepository boardDataRepository;
    private final BoardConfigInfoService configInfoService;

    //많이 사용하는 조회
    public BoardData get(Long id){
        return get(id, "view");
    }

    public BoardData get(Long id, String location) {
        //게시글이 없으면 던진다.
        BoardData boardData = boardDataRepository.findById(id).orElseThrow(BoardDataNotExistException::new);

        //게시판 설정 조회 + 접근 권한체크
        configInfoService.get(boardData.getBoard().getBId(), location);
        
        return boardData;
    }
}