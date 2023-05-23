/** 게시판 양식 공통 - 글쓰기, 수정 */
window.addEventListener("DOMContentLoaded", function() {
    try{ //에디터를 사용하는 경우
        CKEDITOR.replace("content", {
                    height: 350,
                });
            } catch(e) {}
        });