package org.koreait.controllers.boards;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.koreait.commons.CommonException;
import org.koreait.entities.Board;
import org.koreait.models.board.config.BoardConfigInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    
    private final BoardConfigInfoService boardConfigInfoService;
    
    /**
     * 게시글 목록
     *
     * @param bId
     * @return
     */
    @GetMapping("/list/{bId}")
    public String list(@PathVariable String bId, Model model){
        //설정에 따라 제목이나
        commonProcess(bId, "list", model);
        return "board/list";
    }

    /**
     * 게시글 작성
     *
     * @param bId
     * @return
     */
    @GetMapping("/write/{bId}")
    public String write(@PathVariable String bId, Model model){
        //자바스크립트 폼을 넣어줄수 있다.
        commonProcess(bId, "write", model);
        return "board/write";
    }

    /**
     * 게시글 수정
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}/update")
    public String update(@PathVariable Long id, Model model){

        commonProcess(null, "update", model);
        return "board/update";
    }
    
    @PostMapping("/save")
    public String save(Model model){
        //수정, 추가

        //commonProcess(null, "save", model);
        return null;  //임시
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id, Model model){

        commonProcess(null, "view", model);
        return "board/view";
    }

    private void commonProcess(String bId, String action, Model model){

        /**
         * 1. bId 게시판 설정 조회
         * 2. action - write, update - 공통 스크립트, 공통 CSS
         *           - 에디터 사용 -> 에디터 스크립트 추가
         *           - 에디터 미사용 -> 에디터 스크립트 미추가
         *           - write, list, view -> 권한 체크
         *           - update - 본인이 게시글만 수정 가능
         *                    - 회원 - 회원번호
         *                    - 비회원 - 비회원 비밀번호
         *                    - 관리자는 다 가능
         */

        Board board = boardConfigInfoService.get(bId, action);
        List<String> addCss = new ArrayList<>();    //css
        List<String> addScript = new ArrayList<>(); //자바스크립트
        // 공통 스타일 css 추가
        addCss.add("board/style"); 
        addCss.add(String.format("board/%s_style", board.getSkin()));  //스킨별로

        // 글 작성, 수정시 필요한 자바스크립트와 css
        if(action.equals("write") || action.equals("update")){
            if(board.isUseEditor()){ //에디터 사용 경우 -> 설정에 따라 필요할때가 있고 없을때가 있다.
                addScript.add("ckeditor/ckeditor");
            }
            addScript.add("board/form");
        }

        //공통 필요 속성 추가
        model.addAttribute("board",board); //게시판 설정
        model.addAttribute("addCss", addCss);
        model.addAttribute("addScript", addScript);

    }

    @ExceptionHandler(CommonException.class) //페이지 이동x -> 알림창으로 오류 확인
    public String errorHandler(CommonException e, Model model, HttpServletResponse response){
        e.printStackTrace();
        
        String message = e.getMessage();
        //상태코드 가져오기
        HttpStatus status = e.getStatus();
        response.setStatus(status.value());
        
        String script = String.format("alert('%s');history.back();", message);
        model.addAttribute("script", script);
        return "commons/execute_script";
    }
}
