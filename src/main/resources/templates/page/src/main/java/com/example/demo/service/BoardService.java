package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.repository.BoardDao;

import vo.BoardPageVO;
import vo.BoardVO;
import vo.ContentPageVO;
import vo.ContentVO;

@Component
public class BoardService {
	@Autowired
	private BoardDao dao;
	
	private static final int COUNT_PER_PAGE = 10;
	private static final int CONTENT_PER_PAGE = 20;
	private static final int MAIN_PER_PAGE = 100;
	public BoardPageVO makeBoardPage(int currentPage) {
		int totalCount = dao.selectCount();
		
		int totalPage = totalCount/COUNT_PER_PAGE;
		
		if(totalCount%COUNT_PER_PAGE!=0) {
			totalPage++;
		}
		
		int startPage = (currentPage-1)/10*10+1;
		
		int endPage = startPage +9;
		
		if(totalPage < endPage) {
			endPage = totalPage;
		}
		
		int startRow = (currentPage-1)*COUNT_PER_PAGE;
		
		List<BoardVO> boardList=dao.selectList(startRow, COUNT_PER_PAGE);
		
		return new BoardPageVO(boardList,currentPage,startPage,endPage,totalPage);
	}
	
	public BoardPageVO makeBoardPageL(int currentPage) {
		int totalCount = dao.selectLikeCount();
		
		int totalPage = totalCount/COUNT_PER_PAGE;
		
		if(totalCount%COUNT_PER_PAGE!=0) {
			totalPage++;
		}
		
		int startPage = (currentPage-1)/10*10+1;
		
		int endPage = startPage +9;
		
		if(totalPage < endPage) {
			endPage = totalPage;
		}
		
		int startRow = (currentPage-1)*COUNT_PER_PAGE;
		
		List<BoardVO> boardList=dao.selectListLike(startRow, COUNT_PER_PAGE);
		
		return new BoardPageVO(boardList,currentPage,startPage,endPage,totalPage);
	}
	
	public BoardPageVO makeBoardPageNormal(int currentPage) {
		int totalCount = dao.selectNormalCount();
		
		int totalPage = totalCount/COUNT_PER_PAGE;
		
		if(totalCount%COUNT_PER_PAGE!=0) {
			totalPage++;
		}
		
		int startPage = (currentPage-1)/10*10+1;
		
		int endPage = startPage +9;
		
		if(totalPage < endPage) {
			endPage = totalPage;
		}
		
		int startRow = (currentPage-1)*COUNT_PER_PAGE;
		
		List<BoardVO> boardList=dao.selectListNormal(startRow, COUNT_PER_PAGE);
		return new BoardPageVO(boardList,currentPage,startPage,endPage,totalPage);
	}
	
	public ContentPageVO mainPage(int currentPage) {
		int totalCount = dao.selectMainCount();
		
		int totalPage = totalCount/MAIN_PER_PAGE;
		
		if(totalCount%MAIN_PER_PAGE!=0) {
			totalPage++;
		}
		
		int startPage = (currentPage-1)/100*100+1;
		
		int endPage = startPage +19;
		
		if(totalPage < endPage) {
			endPage = totalPage;
		}
		
		int startRow = (currentPage-1)*MAIN_PER_PAGE;
		
		List<ContentVO> contentList=dao.selectMainList(startRow, MAIN_PER_PAGE);
		return new ContentPageVO(contentList,currentPage,startPage,endPage,totalPage);
	}
	
	public ContentPageVO morePage(int currentPage, String contentCategory) {
		int totalCount = dao.selectMoreCount(contentCategory);
		
		int totalPage = totalCount/CONTENT_PER_PAGE;
		
		if(totalCount%CONTENT_PER_PAGE!=0) {
			totalPage++;
		}
		
		int startPage = (currentPage-1)/20*20+1;
		
		int endPage = startPage +19;
		
		if(totalPage < endPage) {
			endPage = totalPage;
		}
		
		int startRow = (currentPage-1)*CONTENT_PER_PAGE;
		
		List<ContentVO> contentList=dao.selectMoreList(startRow, CONTENT_PER_PAGE, contentCategory);
		return new ContentPageVO(contentList,currentPage,startPage,endPage,totalPage,contentCategory);
	}
	

	
	
	public ContentPageVO contentList(int currentPage, String contentTitle) {
		int totalCount = dao.selectContentListCount(contentTitle);
		
		int totalPage = totalCount/COUNT_PER_PAGE;
		
		if(totalCount%COUNT_PER_PAGE!=0) {
			totalPage++;
		}
		
		int startPage = (currentPage-1)/10*10+1;
		
		int endPage = startPage +9;
		
		if(totalPage < endPage) {
			endPage = totalPage;
		}
		
		int startRow = (currentPage-1)*COUNT_PER_PAGE;
		
		List<ContentVO> contentList=dao.selectContentList(startRow, COUNT_PER_PAGE, contentTitle);
		return new ContentPageVO(contentList,currentPage,startPage,endPage,totalPage);
	}
	
	
	public int write(BoardVO board, String loginId) {
		
		//boardVO 에는 제목과 내용만 적혀있음.
		//나머지 값을 서비스에서 채우기.
		board.setBoardCount(0); //조회수
		board.setBoardDate(new Date()); //작성일시
		board.setBoardWriter(loginId);
		
		return dao.insert(board);
		
	}
	
	public int writeContent(ContentVO content, String loginId) {
		return dao.insertContent(content);
	}
	
	public ContentVO contentRead(int contentEp, String contentTitle, String loginId, int contentNum) {
		ContentVO content = dao.selectContent(contentEp, contentTitle);
		
		if(loginId != null) {
			dao.updateContentCount(contentNum);
			return dao.selectContent(contentEp, contentTitle);
		} else {
			return content;
		}
		
	}
	public BoardVO read(int boardNum,String loginId) {
		BoardVO board = dao.select(boardNum);
		
		if(loginId != null && loginId.equals(board.getBoardWriter())) {
			//로그인 정보가 있고 현재 작성자와 같으면 
			//조회수 증가 안함.
			return board;
		} else {
			//조회수 증가시키고 다시 조회하기.
			dao.updateReadCount(boardNum);
			return dao.select(boardNum);
		}
	}
	public BoardVO prevBoard(int boardNum, String loginId) {
		BoardVO board = dao.prevBoard(boardNum);
		return board;
	}
	
	public BoardVO nextBoard(int boardNum, String loginId) {
		BoardVO board = dao.nextBoard(boardNum);
		return board;
	}
	
	public BoardVO readNoCount(int boardNum) {
		return dao.select(boardNum);
	}
	
	public boolean update(BoardVO board, String loginId) {
		BoardVO original = dao.select(board.getBoardNum());
		
		//현재 로그인된 사용자와 수정전 글 작성자가 일치하면
		if(loginId != null && loginId.equals(original.getBoardWriter())){
			//수정하는 시점 시간 기록
			board.setBoardDate(new Date());
			dao.update(board);
			return true;
		} else {
			return false;
		}
	}
	
	public ContentVO readContent(int contentEp, String contentTitle) {
		return dao.selectContent(contentEp, contentTitle);
	}
	
	public boolean updateContent(ContentVO content, String loginId) {
		
		if(loginId != null && loginId.equals("admin")) {
			dao.updateContent(content);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean delete(int boardNum, String loginId) {
		BoardVO board = dao.select(boardNum);
		if((loginId != null && loginId.equals(board.getBoardWriter())) || loginId.equals("admin")) {
			dao.delete(boardNum);
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean deleteContent(int contentNum, String loginId) {
		if((loginId != null && loginId.equals("admin"))) {
			dao.deleteContent(contentNum);
			return true;
		} else {
			return false;
		}
	}
	
		
}
