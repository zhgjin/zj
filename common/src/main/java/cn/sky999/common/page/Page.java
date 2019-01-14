package cn.sky999.common.page;

public class Page {

	private int page = 1;
	private int rows = 15;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public void setLimit(int limit) {
		this.rows = limit;
	}

	public void setPageIndex(int pageIndex) {
		this.page = pageIndex;
	}

	public void setPageSize(int pageSize) {
		this.rows = pageSize;
	}

}
