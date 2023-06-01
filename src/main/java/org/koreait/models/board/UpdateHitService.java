package org.koreait.models.board;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.koreait.commons.MemberUtil;
import org.koreait.entities.BoardData;
import org.koreait.entities.BoardView;
import org.koreait.repositories.BoardDataRepository;
import org.koreait.repositories.BoardRepository;
import org.koreait.repositories.BoardViewRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 조회수 업데이트
 */
@Service
@RequiredArgsConstructor
public class UpdateHitService {
    private final BoardViewRepository boardViewRepository;
    private final BoardDataRepository boardDataRepository;
    private final HttpServletRequest request;
    private final MemberUtil memberUtil;

    public void update(Long id){
        try {
            BoardView boardView = new BoardView();
            boardView.setId(id);
            boardView.setUid(""+getUid());
            boardViewRepository.saveAndFlush(boardView);

        }catch (Exception e){
            long cnt = boardViewRepository.getHit(id);
            BoardData boardData = boardDataRepository.findById(id).orElse(null);
            if(boardData != null){
                boardData.setHit((int) cnt);
                boardDataRepository.flush();
            }
        }
    }

    private int getUid(){
        String ip = request.getRemoteAddr();
        String ua = request.getHeader("User-Agent");
        //Long userNo = memberUtil.isLogin() ? memberUtil.getMember().getUserNo() : 0L;
        //0 : 비회원, 숫자 : 회원
        return memberUtil.isLogin() ? memberUtil.getMember().getUserNo().intValue() : Objects.hash(ip, ua);
        //로그인했을때는 회원번호로 / 비로그인 상태일때 현재사용하고 있는 브라우저 ip로..

        //return Objects.hash(ip, ua, userNo);
        //회원일때는 userNo는 ip가 달라져도 명확하게 누구인지 알수있다.
    }
}
