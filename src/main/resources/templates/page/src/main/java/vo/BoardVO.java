package vo;

import java.util.Date;

public class BoardVO {

	private int boardNum;
	private String boardTitle;
	private String boardWriter;
	private String boardContent;
	private int boardCount;
	private int boardLike;
	private int boardCategory;
	private Date boardDate;
	
	public BoardVO() {
		
	}
	
	
	public BoardVO(String boardTitle, String boardWriter, String boardContent, int boardCount, int boardLike,
			int boardCategory, Date boardDate) {
		super();
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.boardContent = boardContent;
		this.boardCount = boardCount;
		this.boardLike = boardLike;
		this.boardCategory = boardCategory;
		this.boardDate = boardDate;
	}




	public BoardVO(int boardNum, String boardTitle, String boardWriter, String boardContent, int boardCount,
			int boardLike, int boardCategory, Date boardDate) {
		super();
		this.boardNum = boardNum;
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.boardContent = boardContent;
		this.boardCount = boardCount;
		this.boardLike = boardLike;
		this.boardCategory = boardCategory;
		this.boardDate = boardDate;
	}


//////////////////////////////////////////////////////////////

	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public int getBoardCount() {
		return boardCount;
	}
	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}
	public int getBoardLike() {
		return boardLike;
	}
	public void setBoardLike(int boardLike) {
		this.boardLike = boardLike;
	}
	public int getBoardCategory() {
		return boardCategory;
	}
	public void setBoardCategory(int boardCategory) {
		this.boardCategory = boardCategory;
	}
	public Date getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}
	
	@Override
	public String toString() {
		return "BoardVO [boardNum=" + boardNum + ", boardTitle=" + boardTitle + ", boardWriter=" + boardWriter
				+ ", boardContent=" + boardContent + ", boardCount=" + boardCount + ", boardLike=" + boardLike
				+ ", boardCategory=" + boardCategory + ", boardDate=" + boardDate + "]";
	}

	
}
