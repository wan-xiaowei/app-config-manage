package com.wxw.web.json;

import java.util.List;

public class TableResponse {

	private String checkedKey;

	private Data data;

	private boolean isSuccess;

	private String message;

	private String ResponseError;

	public String getCheckedKey() {
		return checkedKey;
	}

	public void setCheckedKey(String checkedKey) {
		this.checkedKey = checkedKey;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResponseError() {
		return ResponseError;
	}

	public void setResponseError(String responseError) {
		ResponseError = responseError;
	}

	public static class Data {

		private Integer iTotalDisplayRecords;
		private Integer iTotalRecords;
		private Integer page;
		private List<?> rows;
		private Long total;

		public Integer getiTotalDisplayRecords() {
			return iTotalDisplayRecords;
		}

		public void setiTotalDisplayRecords(Integer iTotalDisplayRecords) {
			this.iTotalDisplayRecords = iTotalDisplayRecords;
		}

		public Integer getiTotalRecords() {
			return iTotalRecords;
		}

		public void setiTotalRecords(Integer iTotalRecords) {
			this.iTotalRecords = iTotalRecords;
		}

		public Integer getPage() {
			return page;
		}

		public void setPage(Integer page) {
			this.page = page;
		}

		public List<?> getRows() {
			return rows;
		}

		public void setRows(List<?> rows) {
			this.rows = rows;
		}

		public Long getTotal() {
			return total;
		}

		public void setTotal(Long total) {
			this.total = total;
		}

	}

}
