package org.koreait.models.file;

import org.koreait.commons.CommonException;
import org.springframework.http.HttpStatus;

public class FileNotFoundException extends CommonException {
    public FileNotFoundException(){ //파일이 없을시 예외 발생
        super(bundleValidation.getString("File.notExists"), HttpStatus.BAD_REQUEST);
    }
}
