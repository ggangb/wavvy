package com.example.demo.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.repository.mapper.BoardMapper;

import vo.BoardVO;
import vo.ContentVO;

@Component
public class BoardDao {
	
	@Autowired
	private SqlSessionTemplate session;
	
	public int insert(BoardVO board) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.insert(board);
	}
	
	public int insertContent(ContentVO content) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.insertContent(content);
	}
	
	public int selectCount() {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.selectTotalCount();
	}
	
	public int selectLikeCount() {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.selectLikeCount();
	}
	
	public int selectNormalCount() {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.selectNormalCount();
	}
	
	public int searchBoardCount(String keyword) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.searchBoardCount(keyword);
	}
	
	public int selectMainCount() {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.selectMainCount();
	}
	
	public int selectMoreCount(String contentCategory) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.selectMoreCount(contentCategory);
	}
	public int selectContentListCount(String contentTitle) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.selectContentListCount(contentTitle);
	}
	
	public ContentVO selectContent(int contentEp, String contentTitle) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.selectContent(contentEp, contentTitle);
	}
	
	public List<ContentVO> searchContent(String keyword) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.searchContent(keyword);
	}
	public BoardVO select(int boardNum) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.select(boardNum);
	}
	public BoardVO prevBoard(int boardNum) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.prevBoard(boardNum);
	}
	public BoardVO nextBoard(int boardNum) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.nextBoard(boardNum);
	}
	
	public List<BoardVO> selectList(int startRow, int count) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.selectList(startRow, count);
	}
	
	public List<BoardVO> searchBoard(int startRow, int count, String keyword) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.searchBoard(startRow, count, keyword);
	}
	
	public List<BoardVO> selectListLike(int startRow, int count) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.selectListLike(startRow, count);
	}
	public List<BoardVO> selectListNormal(int startRow, int count) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.selectListNormal(startRow, count);
	}
	
	public List<ContentVO> selectMainList(int startRow,int count) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.selectMainList(startRow, count);
	}
	
	public List<ContentVO> selectMoreList(int startRow,int count, String contentCategory) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.selectMoreList(startRow, count, contentCategory);
	}
	
	public List<ContentVO> selectContentList(int startRow, int count, String contentTitle) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.selectContentList(startRow, count, contentTitle);
	}
	
	public int updateReadCount(int boardNum) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.updateReadCount(boardNum);
	}
	
	public int updateContentCount(int contentNum) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.updateContentCount(contentNum);
	}
	
	public int updateContentImgFile(int contentNum) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.updateContentImgFile(contentNum);
	}
	public int updateContentVideoFile(int contentNum) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.updateContentVideoFile(contentNum);
	}
	
	public int update(BoardVO board) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.update(board);
	}
	
	public int updateContent(ContentVO content) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.updateContent(content);
	}
	public int updateContentVideo(ContentVO content) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.updateContentVideo(content);
	}
	public int updateContentImg(ContentVO content) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.updateContentImg(content);
	}
	
	public int delete(int boardNum) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.delete(boardNum);
	}
	
	public int deleteContent(int contentNum) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		return mapper.deleteContent(contentNum);
	}
}
