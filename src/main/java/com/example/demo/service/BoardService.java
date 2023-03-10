package com.example.demo.service;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

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
	
	public BoardPageVO searchBoardPage(int currentPage, String keyword) {
		int totalCount = dao.searchBoardCount(keyword);
		
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
		
		List<BoardVO> boardList=dao.searchBoard(startRow, COUNT_PER_PAGE, keyword);
		
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
		
		//boardVO ?????? ????????? ????????? ????????????.
		//????????? ?????? ??????????????? ?????????.
		board.setBoardCount(0); //?????????
		board.setBoardDate(new Date()); //????????????
		board.setBoardWriter(loginId);
		
		return dao.insert(board);
		
	}
	
	public int writeContent(ContentVO content, String loginId, MultipartFile imgFile,MultipartFile videoFile) throws Exception {
		
		String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
		String projectPath1 = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\contentImg";
		
		UUID uuid = UUID.randomUUID();
		
		String fileName = uuid + "_" + imgFile.getOriginalFilename();
		String fileName1 = uuid + "_" + videoFile.getOriginalFilename();
		
		File saveFile = new File(projectPath1, fileName);
		File saveFile1 = new File(projectPath, fileName1);
		
		imgFile.transferTo(saveFile);
		videoFile.transferTo(saveFile1);
		
		content.setContentImgName(fileName);
		content.setContentName(fileName1);
		
		content.setContentImg("/contentImg/"+fileName);
		content.setContentVideo("/files/"+fileName1);
		
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
	
	public List<ContentVO> searchContent(String keyword) {
		List<ContentVO> content = dao.searchContent(keyword);
		return content;
	}
	public BoardVO read(int boardNum,String loginId) {
		BoardVO board = dao.select(boardNum);
		
		if(loginId != null && loginId.equals(board.getBoardWriter())) {
			//????????? ????????? ?????? ?????? ???????????? ????????? 
			//????????? ?????? ??????.
			return board;
		} else {
			//????????? ??????????????? ?????? ????????????.
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
		
		//?????? ???????????? ???????????? ????????? ??? ???????????? ????????????
		if(loginId != null && loginId.equals(original.getBoardWriter())){
			//???????????? ?????? ?????? ??????
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
	
	public boolean updateContent(ContentVO content, String loginId, MultipartFile imgFile,MultipartFile videoFile) throws Exception {
		
		String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
		String projectPath1 = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\contentImg";
		
		UUID uuid = UUID.randomUUID();
		
		String fileName = uuid + "_" + imgFile.getOriginalFilename();
		String fileName1 = uuid + "_" + videoFile.getOriginalFilename();
		
		File saveFile = new File(projectPath1, fileName);
		File saveFile1 = new File(projectPath, fileName1);
		
		imgFile.transferTo(saveFile);
		videoFile.transferTo(saveFile1);
		
		content.setContentImgName(fileName);
		content.setContentName(fileName1);
		
		content.setContentImg("/contentImg/"+fileName);
		content.setContentVideo("/files/"+fileName1);
		
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
