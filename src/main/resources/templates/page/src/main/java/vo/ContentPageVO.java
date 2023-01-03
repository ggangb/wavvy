package vo;

import java.util.List;

public class ContentPageVO {
	private List<ContentVO> contentList;
	
private int currentPage;
	
	private int startPage;
	
	
	private int endPage;
	
	private int totalPage;
	
	private String contentCategory;
	
	
	
	public ContentPageVO(List<ContentVO> contentList, int currentPage, int startPage, int endPage, int totalPage, String contentCategory) {
		super();
		this.contentList = contentList;
		this.currentPage = currentPage;
		this.startPage = startPage;
		this.endPage = endPage;
		this.totalPage = totalPage;
		this.contentCategory = contentCategory;
	}
	

	public ContentPageVO(List<ContentVO> contentList, int currentPage, int startPage, int endPage, int totalPage) {
		super();
		this.contentList = contentList;
		this.currentPage = currentPage;
		this.startPage = startPage;
		this.endPage = endPage;
		this.totalPage = totalPage;
	}



	public List<ContentVO> getContentList() {
		return contentList;
	}

	public void setContentList(List<ContentVO> contentList) {
		this.contentList = contentList;
	}

	public int getCurrentPage() {
		return currentPage;
	}
	
	
	
	public String getContentCategory() {
		return contentCategory;
	}

	public void setContentCategory(String contentCategory) {
		this.contentCategory = contentCategory;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}



	
}
