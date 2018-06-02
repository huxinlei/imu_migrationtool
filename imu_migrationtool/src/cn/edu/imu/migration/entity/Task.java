package cn.edu.imu.migration.entity;

import java.sql.ResultSet;
import java.util.Map;
import java.util.List;

import cn.edu.imu.migration.entity.Column;
/**
 * @author hp
 * @2015-09-25
 */
public class Task {
	private int id;//������
	private String file;//�����ļ�����
	private List<String> sqls;//��ѯsql
	private ResultSet rs;//����������ݼ�
	private long count;//��������
	private List<Column> columns;//������
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public List<String> getSql() {
		return sqls;
	}
	public void setSql(List<String> sqls) {
		this.sqls = sqls;
	}
	public ResultSet getRs() {
		return rs;
	}
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public List<Column> getColumns() {
		return columns;
	}
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
}