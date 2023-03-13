package com.example.demo.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import vo.BoardVO;
import vo.ContentVO;

public interface BoardMapper {
	
	public int insert(BoardVO board);
	public int selectTotalCount();
	public int selectLikeCount();
	public int selectNormalCount();
	public int searchBoardCount(String keyword);
	public int selectMainCount();
	public int selectMoreCount(String contentCategory);
	public int selectContentListCount(String contentTitle);
	public List<BoardVO> selectList(@Param("startRow")int startRow, @Param("count")int count);
	public BoardVO select(int boardNum);
	public ContentVO selectContent(@Param("contentEp")int contentEp, @Param("contentTitle")String contentTitle);
	public List<ContentVO> searchContent(@Param("keyword") String keyword);
	public BoardVO prevBoard(int boardNum);
	public BoardVO nextBoard(int boardNum);
	public int updateReadCount(int boardNum);
	public int updateContentCount(int contentNum);
	public int update(BoardVO board);
	public int updateContent(ContentVO content);
	public int delete(int boardNum);
	public int deleteContent(int contentNum);
	public List<BoardVO> selectListLike(@Param("startRow")int startRow, @Param("count")int count);
	public List<BoardVO> searchBoard(@Param("startRow")int startRow, @Param("count")int count, @Param("keyword")String keyword);
	public List<BoardVO> selectListNormal(@Param("startRow")int startRow, @Param("count")int count);
	public List<ContentVO> selectMainList(@Param("startRow")int startRow, @Param("count")int count);
	public List<ContentVO> selectMoreList(@Param("startRow")int startRow, @Param("count")int count, @Param("contentCategory")String contentCategory);
	public List<ContentVO> selectContentList(@Param("startRow")int startRow, @Param("count")int count, @Param("contentTitle")String contentTitle);
	public int insertContent(ContentVO content);
}
