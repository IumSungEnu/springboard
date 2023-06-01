package org.koreait.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity @Data
@IdClass(BoardViewId.class)
public class BoardView {

    @Id
    private Long id; //게시글 번호

    @Id
    @Column(length = 40, name="uid_")
    private String uid;  //IP + UA + 회원번호
    //uid 예약어 -> 오라클에서 오류가 발생해 예약어를 넣어주었다.


}
//@Id가 2개 있으면 오류가 발생할 수 있다.
//이때 BoardViewId 클래스를 만들어 2가지 이상일때 사용하는 방법을 정의한다.
//BoardViewId 클래스를 만들었으면 @IdClass(BoardViewId.class)를 지정한다.
//BoardViewRepository 생성

