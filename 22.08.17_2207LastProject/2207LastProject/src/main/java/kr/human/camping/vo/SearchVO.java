package kr.human.camping.vo;

public class SearchVO {
	//현재 페이지
	private int pageNum;
	
	//한페이지당 보여질 게시물 갯수
	private int amount;
	//기본 생성자 -> 기본세팅 : pageNum =1, amount=10
	public SearchVO(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public SearchVO() {
		this(1,10);
	}

	public int getPageNum() {
		return pageNum;
	}
	@Override
	public String toString() {
		return "SearchVO [pageNum=" + pageNum + ", amount=" + amount + "]";
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
