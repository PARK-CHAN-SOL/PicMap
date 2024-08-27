package com.picmap.app.util;

public class Pager {

	// 페이지 번호
	private Long page;

	private Long startRow;
	private Long lastRow;

	// ------------------------
	private Long startNum;
	private Long lastNum;
	private boolean pre;
	private boolean next;
	private long perPage = 10L;

	// rownum을 계산하는 메서드
	public void makeRow() throws Exception {
		this.startRow = (this.getPage() - 1) * perPage + 1;
		this.lastRow = this.getPage() * perPage;

	}

	// 페이징 처리 하는 메서드
	public void makeNum(long totalCount) throws Exception {

		// 1. 총갯수를 이용해서 총 페이지수 구하기
		long totalPage = totalCount / perPage;
		if (totalCount % perPage != 0) {
			totalPage++;
		}

		// 2. 총페이지수로 총블럭수 구하기
		long perBlock = 5L; // 한페이지에 보여질 페이지번호의 갯수

		long totalBlock = 0; // 총블럭의 수

		totalBlock = totalPage / perBlock;

		if (totalPage % perBlock != 0) {
			totalBlock++;
		}

		// 3. 현재페이지번호로 현재블럭 번호를 구하기

		// page 1 2 3 4 5 6 7 10 11
		// 블럭번호 1 1 1 1 1 2 2 2 3
		long curBlock = 0;
		curBlock = page / perBlock;

		if (page % perBlock != 0) {
			curBlock++;
		}

		// 4. 현재 블럭 번호로 시작번호와 끝 번호 구하기

		// curBlock 1 2 3
		// start 1 6 11
		// last 5 10 15
		this.startNum = (curBlock - 1) * perBlock + 1;
		this.lastNum = curBlock * perBlock;

		// 5. 이전블럭, 다음 블럭 유무 판단
		this.pre = true; // true면 이전블럭이 존재, false면 이전블럭이 X
		this.next = true;// true면 다음블럭이 존재, false면 다음블럭이 X
		if (curBlock == 1) {
			pre = false;
		}

		if (curBlock == totalBlock) {
			next = false;

			lastNum = totalPage;
		}

	}

	public long getPerPage() {
		return perPage;
	}

	public void setPerPage(long perPage) {
		this.perPage = perPage;
	}

	public Long getStartNum() {
		return startNum;
	}

	public void setStartNum(Long startNum) {
		this.startNum = startNum;
	}

	public Long getLastNum() {
		return lastNum;
	}

	public void setLastNum(Long lastNum) {
		this.lastNum = lastNum;
	}

	public boolean isPre() {
		return pre;
	}

	public void setPre(boolean pre) {
		this.pre = pre;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public Long getPage() {
		if (this.page == null || this.page < 1) {
			this.page = 1L;
		}
		return page;
	}

	public void setPage(Long page) {
		this.page = page;
	}

	public Long getStartRow() {
		return startRow;
	}

	public void setStartRow(Long startRow) {
		this.startRow = startRow;
	}

	public Long getLastRow() {
		return lastRow;
	}

	public void setLastRow(Long lastRow) {
		this.lastRow = lastRow;
	}

}
