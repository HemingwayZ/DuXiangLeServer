package com.zhm.duxiangle.bean;

import java.util.List;
/**
 * ��ҳ
 * @author zhuanghm
 *
 */
public class Page {
	// ҳ��˵�����
	private int thispage; // ��ǰҳ��
	private int rowperpage; // ÿҳ��¼��
	private int countrow; // �ܼ�¼��
	private int countpage; // ��ҳ��
	private int firstpage; // ��ҳ
	private int lastpage; // βҳ
	private int prepage; // ��һҳ
	private int nextpage; // ��һҳ
	private String sql;
	private List<Object> list;// ҳ����������

	public int getThispage() {
		return thispage;
	}

	public void setThispage(int thispage) {
		this.thispage = thispage;
	}

	public int getRowperpage() {
		return rowperpage;
	}

	public void setRowperpage(int rowperpage) {
		this.rowperpage = rowperpage;
	}

	public int getCountrow() {
		return countrow;
	}

	public void setCountrow(int countrow) {
		this.countrow = countrow;
	}

	public int getCountpage() {
		return countpage;
	}

	public void setCountpage(int countpage) {
		this.countpage = countpage;
	}

	public int getFirstpage() {
		return firstpage;
	}

	public void setFirstpage(int firstpage) {
		this.firstpage = firstpage;
	}

	public int getLastpage() {
		return lastpage;
	}

	public void setLastpage(int lastpage) {
		this.lastpage = lastpage;
	}

	public int getPrepage() {
		return prepage;
	}

	public void setPrepage(int prepage) {
		this.prepage = prepage;
	}

	public int getNextpage() {
		return nextpage;
	}

	public void setNextpage(int nextpage) {
		this.nextpage = nextpage;
	}

	public List<Object> getList() {
		return list;
	}

	@SuppressWarnings("unchecked")
	public <T> void setList(List<T> list) {
		this.list = (List<Object>) list;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	@Override
	public String toString() {
		return "Page [thispage=" + thispage + ", rowperpage=" + rowperpage + ", countrow=" + countrow + ", countpage="
				+ countpage + ", firstpage=" + firstpage + ", lastpage=" + lastpage + ", prepage=" + prepage
				+ ", nextpage=" + nextpage + ", sql=" + sql + ", list=" + list + "]";
	}

}
