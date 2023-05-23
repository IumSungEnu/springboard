package org.koreait.models.board.config;

import org.koreait.commons.CommonException;
import org.springframework.http.HttpStatus;

public class BoardConfigNotExistException extends CommonException {
    public BoardConfigNotExistException(){ //게시판 등록 여부
        super(bundleValidation.getString("Validation.board.notExists"), HttpStatus.BAD_REQUEST);
    }
}
