package org.koreait.models.board.config;

import lombok.RequiredArgsConstructor;
import org.koreait.commons.constants.Role;
import org.koreait.controllers.admins.BoardForm;
import org.koreait.entities.Board;
import org.koreait.repositories.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

/**
 * 게시판 설정 추가, 수정
 * 
 */
@Service
@RequiredArgsConstructor
public class BoardConfigSaveService {
    private final BoardRepository boardRepository;

    public void save(BoardForm boardForm){
        save(boardForm, null);  //단일 테스트때 오류발생하지 않기 위해 추가
    }

    public void save(BoardForm boardForm, Errors errors){

        if(errors != null && errors.hasErrors()){
            //에러가 있으면 리턴해서 실행하지 않는다.
            //test할때 오류발생을 막기위해 위 save를 추가했다.
            return;
        }

        /**
         * 게시판 설정 조회 -> 없으면 기본 엔티티 생성
         * 게시판 등록 모드인 경우는 중복 여부 체크
         */
        String bId = boardForm.getBId();
        Board board = boardRepository.findById(bId).orElseGet(Board::new);

        //게시판 등록 모드인 경우는 중복 여부 체크(mode 값이 들어가 있으면 수정된다)
        String mode = boardForm.getMode();
        if((mode == null || !mode.equals("update")) && board.getBId() != null){ //게시판 등록 -> 중복여부 체크
            throw new DuplicateBoardConfigException();
        }

        //기존 값들을 대체
        board.setBId(bId);
        board.setBName(boardForm.getBName());
        board.setUse(boardForm.isUse());
        board.setRowsOfPage(boardForm.getRowsOfPage());
        board.setShowViewList(boardForm.isShowViewList());
        board.setCategory(boardForm.getCategory());

        board.setListAccessRole(Role.valueOf(boardForm.getListAccessRole()));
        board.setViewAccessRole(Role.valueOf(boardForm.getViewAccessRole()));
        board.setWriteAccessRole(Role.valueOf(boardForm.getWriteAccessRole()));
        board.setReplyAccessRole(Role.valueOf(boardForm.getReplyAccessRole()));
        board.setCommentAccessRole(Role.valueOf(boardForm.getCommentAccessRole()));

        board.setUseEditor(boardForm.isUseEditor());
        board.setUseAttachFile(boardForm.isUseAttachFile());
        board.setUseAttachImage(boardForm.isUseAttachImage());

        board.setLocationAfterWriting(boardForm.getLocationAfterWriting());

        board.setUseReply(boardForm.isUseReply());
        board.setUseComment(boardForm.isUseComment());
        board.setSkin(boardForm.getSkin());

        boardRepository.saveAndFlush(board);
    }
}
