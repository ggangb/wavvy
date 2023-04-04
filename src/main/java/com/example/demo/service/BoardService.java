package com.example.demo.service;

import java.io.File;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

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
		
		//boardVO 에는 제목과 내용만 적혀있음.
		//나머지 값을 서비스에서 채우기.
		board.setBoardCount(0); //조회수
		board.setBoardDate(new Date()); //작성일시
		board.setBoardWriter(loginId);
		
		return dao.insert(board);
		
	}
	
	public int writeContent(ContentVO content, String loginId, MultipartFile imgFile,MultipartFile videoFile) throws Exception {
		
		
		String projectPath = System.getProperty("user.dir") + "/files";
		String projectPath1 = System.getProperty("user.dir") + "/contentImg";
		
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
	
	public boolean updateContent(ContentVO content, String loginId, MultipartFile imgFile,MultipartFile videoFile) throws Exception {
		if(videoFile == null) {
			String projectPath1 = System.getProperty("user.dir") + "/contentImg";
			UUID uuid = UUID.randomUUID();
			String fileName = uuid + "_" + imgFile.getOriginalFilename();
			File saveFile = new File(projectPath1, fileName);
			imgFile.transferTo(saveFile);
			content.setContentImgName(fileName);
			content.setContentImg("/contentImg/"+fileName);
			if(loginId != null && loginId.equals("admin")) {
				dao.updateContentImg(content);
				return true;
			} else {
				return false;
			}
		} else if(imgFile == null) {
			String projectPath = System.getProperty("user.dir") + "/files";
			UUID uuid = UUID.randomUUID();
			String fileName1 = uuid + "_" + videoFile.getOriginalFilename();
			File saveFile1 = new File(projectPath, fileName1);
			videoFile.transferTo(saveFile1);
			content.setContentName(fileName1);
			content.setContentVideo("/files/"+fileName1);
			if(loginId != null && loginId.equals("admin")) {
				dao.updateContentVideo(content);
				return true;
			} else {
				return false;
			}
		} else {
			String projectPath = System.getProperty("user.dir") + "/files";
			String projectPath1 = System.getProperty("user.dir") + "/contentImg";
		
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
	}
	public boolean updateContentFile(String fileName, int contentNum, String loginId) {
		String projectPath = System.getProperty("user.dir") + "/files";
		String projectPath1 = System.getProperty("user.dir") + "/contentImg";
		
		File file = new File(projectPath +"/"+fileName);
		File file1 = new File(projectPath1 +"/"+ fileName);
		System.out.println(file);
		System.out.println(file1);
		if(file.exists() && loginId.equals("admin"))  {
			file.delete();
			dao.updateContentVideoFile(contentNum);
			return true;
		} else if(file1.exists() && loginId.equals("admin")) {
			file1.delete();
			dao.updateContentImgFile(contentNum);
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
	
	public boolean deleteContent(int contentNum, String loginId, String videoName, String imgName) {
		if((loginId != null && loginId.equals("admin"))) {
			String projectPath = System.getProperty("user.dir") + "/files";
			String projectPath1 = System.getProperty("user.dir") + "/contentImg";
			File file = new File(projectPath + videoName);
			File file1 = new File(projectPath1 + imgName);
			if(file.exists() && loginId.equals("admin") && file1.exists()) {
				file.delete();
				file1.delete();
			} else {
				System.out.println("삭제실패");
			}
			dao.deleteContent(contentNum);
			return true;
		} else {
			return false;
		}
	}
	
	
	
	
		
}
