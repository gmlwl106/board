--테이블 삭제
DROP TABLE files;
DROP TABLE comments;
DROP TABLE post;
DROP TABLE category;
DROP TABLE users;

--시퀀스 삭제
DROP SEQUENCE SEQ_USERS_NO;
DROP SEQUENCE SEQ_POST_NO;
DROP SEQUENCE SEQ_CATE_NO;
DROP SEQUENCE SEQ_CMT_NO;
DROP SEQUENCE SEQ_FILE_NO;







--USERS 테이블 생성
CREATE TABLE USERS (
	user_no NUMBER, /* 회원번호 */
    name VARCHAR2(100) NOT NULL, /* 회원이름 */
	id VARCHAR2(20) NOT NULL UNIQUE, /* 아이디 */
	password VARCHAR2(20) NOT NULL, /* 비밀번호 */
	gender VARCHAR2(20) NOT NULL, /* 성별 */
	Primary Key (user_no)
);
--USERS 시퀀스 생성
CREATE SEQUENCE SEQ_USERS_NO
START WITH 1
INCREMENT BY 1
NOCACHE;


--CATEGORY 테이블 생성
CREATE TABLE CATEGORY (
	category_no NUMBER, /* 카테고리 번호 */
    category_name VARCHAR2(100) NOT NULL, /* 카테고리 이름 */
	Primary Key (category_no)
);
--CATEGORY 시퀀스 생성
CREATE SEQUENCE SEQ_CATE_NO
START WITH 1
INCREMENT BY 1
NOCACHE;
--카테고리 생성
INSERT INTO CATEGORY VALUES(SEQ_CATE_NO.nextval, '공지');
INSERT INTO CATEGORY VALUES(SEQ_CATE_NO.nextval, 'QnA');
INSERT INTO CATEGORY VALUES(SEQ_CATE_NO.nextval, '자유');
INSERT INTO CATEGORY VALUES(SEQ_CATE_NO.nextval, '소식');
INSERT INTO CATEGORY VALUES(SEQ_CATE_NO.nextval, '유머');


--POST 테이블 생성
CREATE TABLE POST (
	post_no NUMBER, /* 글 번호 */
    category_no NUMBER NOT NULL, /* 카테고리 번호 */
	title VARCHAR2(100) NOT NULL, /* 제목 */
    user_no NUMBER NOT NULL, /* 회원 번호 */
	reg_date DATE NOT NULL, /* 작성일 */
    hit NUMBER NOT NULL, /* 조회수 */
    content VARCHAR2(1000) NOT NULL, /* 글내용 */
	Primary Key (post_no),
    CONSTRAINT post_user_fk Foreign Key(user_no)
	references users(user_no),
    CONSTRAINT post_cate_fk Foreign Key(category_no)
	references category(category_no)
);
--POST 시퀀스 생성
CREATE SEQUENCE SEQ_POST_NO
START WITH 1
INCREMENT BY 1
NOCACHE;




--COMMENTS 테이블 생성
CREATE TABLE COMMENTS (
	cmt_no NUMBER, /* 댓글 번호 */
    post_no NUMBER NOT NULL, /* 글 번호 */
    user_no NUMBER NOT NULL, /* 회원 번호 */
    content VARCHAR2(100) NOT NULL, /* 댓글 내용 */
    reg_date DATE NOT NULL, /* 작성일 */
    group_no NUMBER NOT NULL, /* 댓글 그룹 번호 */
    depth NUMBER NOT NULL, /* 댓글 깊이 */
	Primary Key (cmt_no),
    CONSTRAINT cmt_post_fk Foreign Key(post_no)
	references post(post_no),
    CONSTRAINT cmt_user_fk Foreign Key(user_no)
	references users(user_no)
);
--COMMENTS 시퀀스 생성
CREATE SEQUENCE SEQ_CMT_NO
START WITH 1
INCREMENT BY 1
NOCACHE;




--FILES 테이블 생성
CREATE TABLE FILES (
	file_no NUMBER, /* 파일 번호 */
    post_no NUMBER NOT NULL, /* 글 번호 */
    original_name VARCHAR2(500) NOT NULL, /* 원본 파일명 */
    save_name VARCHAR2(500) NOT NULL, /* 저장명 */
    file_path VARCHAR2(2000) NOT NULL, /* 파일 경로 */
	Primary Key (file_no),
    CONSTRAINT file_post_fk Foreign Key(post_no)
	references post(post_no)
);
--FILES 시퀀스 생성
CREATE SEQUENCE SEQ_FILE_NO
START WITH 1
INCREMENT BY 1
NOCACHE;

COMMIT;