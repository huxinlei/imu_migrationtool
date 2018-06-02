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
	private int id;//任务编号
	private String file;//保存文件名称
	private List<String> sqls;//查询sql
	private ResultSet rs;//本任务的数据集
	private long count;//数据条数
	private List<Column> columns;//列名称
	
	
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
