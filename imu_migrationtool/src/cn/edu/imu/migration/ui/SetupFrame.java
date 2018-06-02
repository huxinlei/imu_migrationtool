package cn.edu.imu.migration.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cn.edu.imu.migration.commons.Config;

public class SetupFrame extends JFrame{
	//时间间隔
	private JLabel interLField = new JLabel("间隔时间（天）：");
	private JTextField interTField = new JTextField(10);
	//执行时间
	private JLabel exeTimeL = new JLabel("执行时间：");
	private JComboBox exeTimeCom;
	//确定按钮
	private JButton saveButton = new JButton("确定");
	//取消按钮
	private JButton cancelButton = new JButton("取消");
	//按钮Box
	private Box buttonBox = Box.createHorizontalBox();
	//执行时间Box
	private Box exeBox = Box.createHorizontalBox();
	//时间间隔Box
	private Box interBox = Box.createHorizontalBox();
	//最大的Box
	private Box mainBox = Box.createVerticalBox();
	//初始化组件
	private void initComponent(){
		String[] timeList = {"1", "2", "3", "4", "5","6", "7", "8", "9","10", "11", 
				"12", "13", "14", "15","16", "17", "18", "19","20","21","22","23"};
		this.exeTimeCom = new JComboBox(timeList);
	}
	//初始化监听器
	private void initListener() {
		this.saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//confirm();
				saveSetup();
			}
		});
		this.cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelSetup();
			}
		});
	}
	//保存时间间隔和执行时间
	private void saveSetup(){
		int interval = Integer.parseInt(Config.INTERVAL);//获得默认数据
		try{
			interval = Integer.parseInt(this.interTField.getText());
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "时间间隔必须输入数字！", "提示", JOptionPane.ERROR_MESSAGE);
			return ;
		}
		String intervalStr =  String.valueOf(interval);
		String dateStr = (String)this.exeTimeCom.getSelectedItem();
		Config.config(intervalStr, dateStr);
		//关闭当前窗体
		cancelSetup();
	}
	//关闭添加窗体
	private void cancelSetup(){
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(false);
	}
	//初始化相关数据
	private void initData(){
		this.interTField.setText(Config.INTERVAL);
		this.exeTimeCom.setSelectedItem(Config.DATE);
	}
	public SetupFrame(){
		//初始化控件
		initComponent();
		//时间间隔
		this.interBox.add(Box.createHorizontalStrut(30));
		this.interBox.add(interLField);
		this.interBox.add(Box.createHorizontalStrut(20));
		this.interBox.add(interTField);
		this.interBox.add(Box.createHorizontalStrut(30));
		//执行时间
		this.exeBox.add(Box.createHorizontalStrut(30));
		this.exeBox.add(exeTimeL);
		this.exeBox.add(Box.createHorizontalStrut(20));
		this.exeBox.add(exeTimeCom);
		this.exeBox.add(Box.createHorizontalStrut(30));
		//按钮
		this.buttonBox.add(Box.createHorizontalStrut(30));
		this.buttonBox.add(saveButton);
		this.buttonBox.add(Box.createHorizontalStrut(20));
		this.buttonBox.add(cancelButton);
		this.buttonBox.add(Box.createHorizontalStrut(30));
		//main
		this.mainBox.add(Box.createVerticalStrut(20));
		this.mainBox.add(interBox);
		this.mainBox.add(Box.createVerticalStrut(20));
		this.mainBox.add(exeBox);
		this.mainBox.add(Box.createVerticalStrut(20));
		this.mainBox.add(buttonBox);
		this.mainBox.add(Box.createVerticalStrut(20));
		this.add(mainBox);
		this.pack();
		this.setLocation(300, 200);
		this.setTitle("系统配置");
		this.setResizable(false);
		//初始化配置数据
		initData();
		//初始化 监听事件
		initListener();
	}
}
