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
	//ʱ����
	private JLabel interLField = new JLabel("���ʱ�䣨�죩��");
	private JTextField interTField = new JTextField(10);
	//ִ��ʱ��
	private JLabel exeTimeL = new JLabel("ִ��ʱ�䣺");
	private JComboBox exeTimeCom;
	//ȷ����ť
	private JButton saveButton = new JButton("ȷ��");
	//ȡ����ť
	private JButton cancelButton = new JButton("ȡ��");
	//��ťBox
	private Box buttonBox = Box.createHorizontalBox();
	//ִ��ʱ��Box
	private Box exeBox = Box.createHorizontalBox();
	//ʱ����Box
	private Box interBox = Box.createHorizontalBox();
	//����Box
	private Box mainBox = Box.createVerticalBox();
	//��ʼ�����
	private void initComponent(){
		String[] timeList = {"1", "2", "3", "4", "5","6", "7", "8", "9","10", "11", 
				"12", "13", "14", "15","16", "17", "18", "19","20","21","22","23"};
		this.exeTimeCom = new JComboBox(timeList);
	}
	//��ʼ��������
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
	//����ʱ������ִ��ʱ��
	private void saveSetup(){
		int interval = Integer.parseInt(Config.INTERVAL);//���Ĭ������
		try{
			interval = Integer.parseInt(this.interTField.getText());
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "ʱ���������������֣�", "��ʾ", JOptionPane.ERROR_MESSAGE);
			return ;
		}
		String intervalStr =  String.valueOf(interval);
		String dateStr = (String)this.exeTimeCom.getSelectedItem();
		Config.config(intervalStr, dateStr);
		//�رյ�ǰ����
		cancelSetup();
	}
	//�ر���Ӵ���
	private void cancelSetup(){
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(false);
	}
	//��ʼ���������
	private void initData(){
		this.interTField.setText(Config.INTERVAL);
		this.exeTimeCom.setSelectedItem(Config.DATE);
	}
	public SetupFrame(){
		//��ʼ���ؼ�
		initComponent();
		//ʱ����
		this.interBox.add(Box.createHorizontalStrut(30));
		this.interBox.add(interLField);
		this.interBox.add(Box.createHorizontalStrut(20));
		this.interBox.add(interTField);
		this.interBox.add(Box.createHorizontalStrut(30));
		//ִ��ʱ��
		this.exeBox.add(Box.createHorizontalStrut(30));
		this.exeBox.add(exeTimeL);
		this.exeBox.add(Box.createHorizontalStrut(20));
		this.exeBox.add(exeTimeCom);
		this.exeBox.add(Box.createHorizontalStrut(30));
		//��ť
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
		this.setTitle("ϵͳ����");
		this.setResizable(false);
		//��ʼ����������
		initData();
		//��ʼ�� �����¼�
		initListener();
	}
}
