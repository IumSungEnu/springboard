package org.koreait.commons.rests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class JSONData<T> {  //JSON 공통 처리
    private boolean success;
    private HttpStatus status = HttpStatus.OK;  //기본값 200
    private String message;
    private T data;
}
