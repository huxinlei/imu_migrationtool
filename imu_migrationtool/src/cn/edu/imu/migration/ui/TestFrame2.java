package cn.edu.imu.migration.ui;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

public class TestFrame2 extends JFrame{
	public static void main(String args[]){
		TestFrame2 test = new TestFrame2();
		//test.setVisible(true);
	}
	public TestFrame2(){
		init();
		this.setSize(600, 600);
		this.setVisible(true);
	}
	
	public void init(){
		j1 = new JButton("打开");
		j2 = new JButton("保存");
		j3 = new JButton("另存为");
		j4 = new JPanel();
		
		String[] str = {"java笔记", "C#笔记", "HTML5笔记"};
		j5 = new JComboBox(str);
		j6 = new JTextField();
		j7 = new JButton("清空");
		j8 = new JList(str);
		j9 = new JTextArea();
		//为了看出效果，设置了颜色
		j9.setBackground(Color.PINK);
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(j1);
		this.add(j2);
		this.add(j3);
		this.add(j4);
		this.add(j5);
		this.add(j6);
		this.add(j7);
		this.add(j8);
		this.add(j9);
		//定义一个GridBagConstraints
		GridBagConstraints s = new GridBagConstraints();
		//是用来控制添加进的组件的显示位置
		s.fill = GridBagConstraints.BOTH;
		//该方法是为了设置如果组件所在的区域比组件本身要大是的显示情况
		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty = 0;
		layout.setConstraints(j1, s);
		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty = 0;
		layout.setConstraints(j2, s);
		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty = 0;
		layout.setConstraints(j3, s);
		s.gridwidth = 0;
		s.weightx = 0;
		s.weighty = 0;
		layout.setConstraints(j4, s);
		s.gridwidth = 2;
		s.weightx = 0;
		s.weighty = 0;
		layout.setConstraints(j5, s);
		s.gridwidth = 4;
		s.weightx = 1;
		s.weighty = 0;
		layout.setConstraints(j6, s);
		s.gridwidth = 0;
		s.weightx = 0;
		s.weighty = 0;
		layout.setConstraints(j7, s);
		s.gridwidth = 2;
		s.weightx = 0;
		s.weighty = 1;
		layout.setConstraints(j8, s);
		s.gridwidth = 5;
		s.weightx = 0;
		s.weighty = 1;
		layout.setConstraints(j9, s);
		
	}
	JButton j1;
	JButton j2;
	JButton j3;
	JPanel j4;
	JComboBox j5;
	JTextField j6;
	JButton j7;
	JList j8;
	JTextArea j9;
}
