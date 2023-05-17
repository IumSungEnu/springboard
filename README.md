# 게시판 포트폴리오
***
## 1일차
* 스프링 부트 기본 설정

## 2일차
* 타임리프 레이아웃 템플릿 완성
* 스프링 시큐리티 설정
  - 회원가입 엔티티, 레포지토리
  - 서비스 부분 추가
* 관리지 페이지
  - 기본설정
  - 게시판 설정

## 3일차
* 스프링 시큐리티 설정
  - 로그인 양식
  - UserDetails, UserDtailsSercive 인터페이스 구현 클래스
  - Spring Data JAP + Spring Security - 수정자(AwareAuditor 인터페이스 구현체)
  - 스프링 시큐리티에서 회원 정보 조회 방법
    - 요청 처리 메서드 주입
      - Principal principal - String getName() : 아이디
      - @AuthenticationPrincipal UserDetails 구현 클래스의 객체
    - 직접 회원정보 가져오기
      - SecurityContextHolder
        - getContext().getAuthentication()
        - Object getPrincipal() : 비회원 (String) : anonymousUser, 회원 : UserDetails 구현 객체

* 기본 에러 응답 코드 처리
  - 템플릿 경로 /error/응답코드.html
    - timestamp - 오류 발생 시각
    - status - HTTP 상태 코드
    - error - 오류 발생 원인
    - exception - 예외 객체
    - errors - Error 객체
    - trace - printStackTrace()
    - path - 오류의 유입 URL

* 공통 오류 페이지
  - @ExceptionHandler, @