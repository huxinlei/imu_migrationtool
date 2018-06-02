package cn.edu.imu.migration.entity;

public class Column {
	private int id;
	private String name;
	private String type;
	//����������ͱ������Ϊnull����
	private boolean intNull;
	
	public Column(){
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Column(int id, String name, String type){
		this.id = id;
		this.name = name;
		this.type = type;
		//Ĭ��Ϊfalse
		this.intNull = false;
	}
	public Column(int id, String name, String type, boolean intNull){
		this.id = id;
		this.name = name;
		this.type = type;
		this.intNull = intNull;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean getIntNull() {
		return intNull;
	}
	public void setIntNull(boolean intNull) {
		this.intNull = intNull;
	}
	
}