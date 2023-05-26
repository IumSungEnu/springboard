package org.koreait.models.board;

import org.koreait.commons.CommonException;
import org.springframework.http.HttpStatus;

public class BoardDataNotExistException extends CommonException {  //게시글이 존재하지 않을시 예외발생
    public BoardDataNotExistException() {
        super(bundleValidation.getString("Validation.boardData.notExists"), HttpStatus.BAD_REQUEST);
    }
}
