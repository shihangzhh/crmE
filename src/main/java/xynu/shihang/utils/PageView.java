package xynu.shihang.utils;

import java.util.List;


public class PageView<T> {


	private int currentPage=1;//当前页默认为1
	private int pageSize;  //一个页面中的数据
	private long totalPages;//总页数
	private long totalRecoreds;//一共有多少条记录
	private List<T> dataList;   //保存查询出来的记录
	public PageView() {}

	public PageView(int currentPage,int pageSize){
		this.currentPage=currentPage;
		this.pageSize=pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
		
	}
	public long getTotalRecoreds() {
		return totalRecoreds;
	}
	public void setTotalRecoreds(long totalRecoreds) {
		this.totalRecoreds = totalRecoreds;
		this.totalPages = totalRecoreds % this.pageSize ==0 ? totalRecoreds/this.pageSize:totalRecoreds/this.pageSize+1 ;
	}
	public List<T> getDataList() {
		return dataList;
	}
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	
	
	
	
	
	
}
