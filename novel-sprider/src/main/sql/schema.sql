/*连接数据库控制台*/
/*mysql -uroot -p*/

/*数据库初始化脚本*/

/*创建数据库*/
CREATE DATABASE jj_reader DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

/*使用数据库*/
USE jj_reader;

/* Drop Tables */
DROP TABLE IF EXISTS t_system_block;
DROP TABLE IF EXISTS t_bookcase;
DROP TABLE IF EXISTS t_chapter;
DROP TABLE IF EXISTS t_message;
DROP TABLE IF EXISTS t_review;
DROP TABLE IF EXISTS t_credit_history;
DROP TABLE IF EXISTS t_user;
DROP TABLE IF EXISTS t_article;
DROP TABLE IF EXISTS t_subscribe;


/* Create Tables */

CREATE TABLE t_system_block
(
    blockno bigint(20) NOT NULL AUTO_INCREMENT,
    blockid varchar(32),
    blockname varchar(32),
    type tinyint(4),
    category int,
    sortcol varchar(32),
    isasc tinyint(4) COMMENT '是否正序	0：否	1：是',
    isfinish tinyint(4) COMMENT '是否完成	0：否	1：是',
    limitnum int,
    content text,
    target tinyint(4),
    deleteflag tinyint(4) DEFAULT '1' COMMENT '删除标识：0：删除	1：正常',
    modifyuserno int(11),
    modifytime timestamp ,
    PRIMARY KEY (blockno)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;


CREATE TABLE t_bookcase
(
    bookcaseno bigint(20) NOT NULL AUTO_INCREMENT,
    articleno int,
    articlename varchar(100),
    category int,
    userno int,
    username varchar(50),
    chapterno int,
    chaptername varchar(100),
	modifytime timestamp ,
    lastvisit timestamp,
    createtime timestamp,
    deleteflag tinyint(4) DEFAULT '1' COMMENT '删除标识：0：删除	1：正常',
    modifyuserno int(11),
    PRIMARY KEY (bookcaseno)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;


CREATE TABLE t_message
(
    messageno bigint(20) NOT NULL AUTO_INCREMENT,
    userno int,
    loginid varchar(32),
    touserno int,
    tologinid varchar(32),
    title varchar(32),
    content varchar(255),
    category tinyint(4),
    isread tinyint(4),
	modifytime timestamp ,
    postdate timestamp ,
    deleteflag tinyint(4) DEFAULT '1' COMMENT '删除标识：0：删除	1：正常',
    modifyuserno int(11),
    
    PRIMARY KEY (messageno)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;


CREATE TABLE t_review
(
    reviewno bigint(20) NOT NULL AUTO_INCREMENT,
    userno int,
    loginid varchar(50),
    articleno int,
    articlename varchar(100),
    chapterno int,
    chaptername varchar(100),
    title varchar(30),
    review varchar(500),
    email varchar(60),
	modifytime timestamp ,
    postdate timestamp,
    deleteflag tinyint(4) DEFAULT '1' COMMENT '删除标识：0：删除	1：正常',
    modifyuserno int(11),
    
    PRIMARY KEY (reviewno)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;


CREATE TABLE t_credit_history
(
    credithistoryno bigint(20) NOT NULL AUTO_INCREMENT,
    userno int,
    loginid varchar(32),
    articleno int,
    articlename varchar(100),
    chapterno int,
    chaptername varchar(100),
	modifytime timestamp ,
    timestamp timestamp,
    creditpoint int,
    deleteflag tinyint(4) DEFAULT '1' COMMENT '删除标识：0：删除	1：正常',
    modifyuserno int(11),
    
    PRIMARY KEY (credithistoryno)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;


CREATE TABLE t_user
(
    userno bigint(20) NOT NULL AUTO_INCREMENT,
    loginid varchar(32) NOT NULL,
    password varchar(32),
    username varchar(50),
    email varchar(60),
	modifytime timestamp ,
    regdate timestamp,
    sex tinyint(4),
    qq varchar(15),
    lastlogin timestamp,
    lineno varchar(32),
    type tinyint(4),
    votecount int,
    realname varchar(32),
    id varchar(18),
    mobileno varchar(11),
    branch varchar(50),
    bankno varchar(20),
    alipayacount varchar(50),
    category int,
    subcategory int,
    openid varchar(50),
    activedflag tinyint(4) DEFAULT '1' COMMENT '是否活动	0：否	1：是',
    mailtoken varchar(32),
    deleteflag tinyint(4) DEFAULT '1' COMMENT '删除标识：0：删除	1：正常',
    modifyuserno int(11),
    
    PRIMARY KEY (userno)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;


CREATE TABLE t_chapter
(
    chapterno bigint(20) NOT NULL AUTO_INCREMENT,
    articleno int,
    articlename varchar(100),
    chaptertype tinyint(4),
    chaptername varchar(100),
    size int DEFAULT 0,
    isvip tinyint(4),
	modifytime timestamp ,
    postdate timestamp,
    publishtime timestamp,
    ispublish tinyint(4) DEFAULT '0' COMMENT '是否发布：0：否	1：是',
    lastchecktime timestamp,
    deleteflag tinyint(4) DEFAULT '1' COMMENT '删除标识：0：删除	1：正常',
    modifyuserno int(11),
    
    PRIMARY KEY (chapterno)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;


CREATE TABLE t_article
(
    articleno bigint(20) NOT NULL AUTO_INCREMENT,
    articlename varchar(100),
    pinyin varchar(400),
    pinyinheadchar varchar(32),
    initial char(1),
    keywords varchar(500),
    authorid int,
    author varchar(50),
    category int DEFAULT 0,
    subcategory int,
    intro text,
    lastchapterno int,
    lastchapter varchar(50),
    chapters int,
    size int DEFAULT 0,
    fullflag tinyint(4),
    imgflag tinyint(4),
    agent varchar(50),
    firstflag tinyint(4),
    permission int,
    authorflag tinyint(4),
	modifytime timestamp ,
    postdate timestamp,
    lastupdate timestamp,
    dayvisit int DEFAULT 0,
    weekvisit int DEFAULT 0,
    monthvisit int DEFAULT 0,
    allvisit int DEFAULT 0,
    dayvote int DEFAULT 0,
    weekvote int DEFAULT 0,
    monthvote int DEFAULT 0,
    allvote int DEFAULT 0,
    deleteflag tinyint(4) DEFAULT '1' COMMENT '删除标识：0：删除	1：正常',
    publicflag int,
    createuserno int(11),
    createtime timestamp ,
    modifyuserno int(11),
    
    usecustomizeinfotitle tinyint(4),
    infotitle character varying(150),
    infokeywords character varying(150),
    infodescription character varying(200),
    usecustomizelisttitle tinyint(4),
    listtitle character varying(150),
    listkeywords character varying(150),
    listdescription character varying(200),
    PRIMARY KEY (articleno)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE t_subscribe
(
    subscribeno bigint(20) NOT NULL AUTO_INCREMENT,
    userno int,
    articleno int,
    PRIMARY KEY (subscribeno)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE INDEX t_article_articlename_index  ON t_article (articlename);
CREATE INDEX t_article_pinyin_index  ON t_article (pinyin);
CREATE INDEX t_article_author_index  ON t_article (author);
CREATE INDEX t_article_category_idx  ON t_article (category);
CREATE INDEX t_article_category_lastupdate_lastchapterno_deleteflag_idx  ON t_article (category, lastupdate, lastchapterno, deleteflag );
CREATE INDEX t_article_lastupdate_index ON t_article  (lastupdate);
CREATE INDEX t_article_allvisit_index ON t_article  (allvisit);
CREATE INDEX t_article_allvote_index ON t_article  (allvote);
CREATE INDEX t_article_monthvisit_index ON t_article  (monthvisit);
CREATE INDEX t_article_weekvote_index ON t_article  (weekvote);
CREATE INDEX t_article_dayvisit_index ON t_article  (dayvisit);
CREATE INDEX t_article_dayvote_index ON t_article  (dayvote);
CREATE INDEX t_article_postdate_index ON t_article  (postdate);
CREATE INDEX t_article_size_index ON t_article  (size);
CREATE INDEX t_chapter_articleno_index  ON t_chapter (articleno);
CREATE INDEX t_chapter_chaptername_index  ON t_chapter (chaptername);
CREATE INDEX t_credithistory_userno_index ON t_credit_history (userno);
CREATE INDEX t_review_articleno ON t_review (articleno);
CREATE INDEX t_user_openid_deleteflag_index ON t_user (openid,deleteflag);
CREATE INDEX t_user_loginid_password_deleteflag_index ON t_user (loginid,password,deleteflag);

/*使用多线程，在数据库中增加唯一索引*/
create unique index unique_index_articleno_chaptername ON t_chapter(articleno,chaptername);
create unique index unique_index_articlename_author ON t_article(articlename,author);

INSERT INTO t_system_block( blockid,blockname,TYPE,sortcol,isasc,limitnum,target) VALUES ('last_update_list','最新更新列表',10,'lastupdate',0,15,6);
INSERT INTO t_system_block( blockid,blockname,TYPE,sortcol,isasc,limitnum,target) VALUES ('last_insert_list','最新入库',10,'postdate',0,15,6);
INSERT INTO t_system_block( blockid,blockname,TYPE,content,target) VALUES ('friend_link','友情链接',30,'<a href="http://www.51yd.org" target="_blank">易读小说系统</a>',6);
INSERT INTO t_system_block( blockid,blockname,TYPE,content,target) VALUES ('index_yanqing_tuijian','首页言情推荐',20,'1,2,3,4',6);
INSERT INTO t_system_block( blockid,blockname,TYPE,content,target) VALUES ('index_xuanhuan_tuijian','首页玄幻推荐',20,'1,2,3,4',6);
INSERT INTO t_system_block( blockid,blockname,TYPE,content,target) VALUES ('index_junshi_tuijian','首页军事推荐',20,'1,2,3,4',6);
INSERT INTO t_system_block( blockid,blockname,TYPE,content,target) VALUES ('index_kongbu_tuijian','首页恐怖灵异推荐',20,'1,2,3,4',6);
INSERT INTO t_system_block( blockid,blockname,TYPE,content,target) VALUES ('index_wuxia_tuijian','首页武侠修真推荐',20,'1,2,3,4',6);
INSERT INTO t_system_block( blockid, blockname, type, sortcol, isasc, limitnum, target, deleteflag) VALUES ('last_update_list_mobile','手机页面更新列表',10,'lastupdate',0,6,6,1);
INSERT INTO t_system_block( blockid, blockname, type, isasc, content,  target, deleteflag) VALUES ('index_hot_list_mobile','手机页热点',20,0,'58755,58754,58753',6,1);
INSERT INTO t_system_block( blockid, blockname, type, limitnum, target, deleteflag ) VALUES ('info_random_recommand_list', '简介页随机推荐列表', 40, 6, 2, 1);
INSERT INTO t_system_block( blockid, blockname, type, limitnum, target, deleteflag) VALUES ('chapterList_randomrecommand_list', '章节列表页随机推荐列表', 40, 6, 3, 1);
INSERT INTO t_system_block( blockid, blockname, type, limitnum, target, deleteflag) VALUES ('reader_random_recommand_list', '阅读页随机推荐列表', 40, 6, 4, 1);
INSERT INTO t_system_block( blockid, blockname, type, limitnum, target, deleteflag) VALUES ('reader_recommand_list', '阅读页推荐列表', 50, 6, 4, 1);
INSERT INTO t_system_block( blockid, blockname, type, limitnum, target, deleteflag) VALUES ('info_recommand_list', '简介页推荐列表', 50, 6, 2, 1);
INSERT INTO t_system_block( blockid, blockname, type, limitnum, target, deleteflag) VALUES ('chapterList_recommand_list', '章节列表页推荐列表', 50, 6, 3, 1);

INSERT INTO t_user(loginid, password,type,deleteflag,activedflag) VALUES ('{0}', '{1}', 30 ,0,1);

