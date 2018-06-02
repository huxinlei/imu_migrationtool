package cn.edu.imu.migration.ui;

import javax.swing.ImageIcon;

public class TreeBox implements TreeBoxIn {
	//��box����Ӧ��ͼƬ(��ʾ�����ϵ�ͼƬ)
	private ImageIcon icon;
	private String text;
	//���ι��캯��
	public TreeBox(String text){
		this.text = text;
	}
	//ʵ�ֽӿڵķ���
	public ImageIcon getImageIcon(String imagePath) {
		if (this.icon == null) {
			this.icon = new ImageIcon(imagePath);
		}
		return this.icon;
	}
	//����text����
	public void setText(String text){
		this.text = text;
	}
	//��ȡtext����
	public String getText() {
		return this.text;
	}
	public ImageIcon getImageIcon() {
		return getImageIcon("images/envelop-close.gif");
	}
	//��дtoString����, ���ýӿڵ�getText����, getText����������ȥʵ��
	public String toString() {
		return getText();
	}
}
