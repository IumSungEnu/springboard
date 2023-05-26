package org.koreait.repositories;

import org.koreait.entities.BoardView;
import org.koreait.entities.BoardViewId;
import org.koreait.entities.QBoardView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BoardViewRepository extends JpaRepository<BoardView, BoardViewId>, QuerydslPredicateExecutor<BoardView> {

    /**
     * 게시글별 조회수 조회(카운트 횟수)
     *
     * @param id
     * @return
     */
    default long getHit(Long id){
        QBoardView boardView = QBoardView.boardView;
        return count(boardView.id.eq(id));
    }
}
//동일 id일 경우 오류발생(try~cache)
