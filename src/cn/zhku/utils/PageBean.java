package cn.zhku.utils;

import java.util.List;

public class PageBean {
	//页面显示数，前台传来
	private Integer pageSize = 10;
	//当前页，前台传来
	private Integer currentPage = 1;
	//总记录数
	private Integer totalCount;
	//页数，计算得来
	private Integer pageCount;
	//list显示
	private List list;
	public PageBean(Integer currentPage,Integer pageSize,Integer totalCount) {
		//赋值操作
		if (totalCount!=null) {
			this.totalCount = totalCount;
		}else {
			this.totalCount = 0;
		}
		if (pageSize!=null&&pageSize>0) {
			this.pageSize = pageSize;
		}
		if (currentPage!=null&&currentPage>0) {
			this.currentPage = currentPage;
		}
		this.pageCount = (int) Math.ceil((double)totalCount/this.pageSize);
		//当前页不超出范围
		if(this.currentPage>this.pageCount) {
			this.currentPage = this.pageCount;
		}
		
	}
	//应业务需求，显示每页的customerList应该有有起始索引，故这里封装一个计算索引的函数来获得start索引
	public int getStart() {
		return (this.currentPage-1)*this.pageSize;
	}
	
	public PageBean() {
		// TODO Auto-generated constructor stub
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	
	
	
}
