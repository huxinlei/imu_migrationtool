package cn.edu.imu.migration.ui;

import javax.swing.ImageIcon;

public class TreeBox implements TreeBoxIn {
	//该box所对应的图片(显示在树上的图片)
	private ImageIcon icon;
	private String text;
	//带参构造函数
	public TreeBox(String text){
		this.text = text;
	}
	//实现接口的方法
	public ImageIcon getImageIcon(String imagePath) {
		if (this.icon == null) {
			this.icon = new ImageIcon(imagePath);
		}
		return this.icon;
	}
	//设置text函数
	public void setText(String text){
		this.text = text;
	}
	//获取text函数
	public String getText() {
		return this.text;
	}
	public ImageIcon getImageIcon() {
		return getImageIcon("images/envelop-close.gif");
	}
	//重写toString方法, 调用接口的getText方法, getText方法由子类去实现
	public String toString() {
		return getText();
	}
}
