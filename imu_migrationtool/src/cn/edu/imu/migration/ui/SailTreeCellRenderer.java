package cn.edu.imu.migration.ui;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

public class SailTreeCellRenderer extends DefaultTreeCellRenderer  {
	//���ڵ㱻ѡ��ʱ������
	private Font selectFont;
	
	public SailTreeCellRenderer() {
		this.selectFont = new Font(null, Font.BOLD, 12);
	}
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
		TreeBoxIn box = (TreeBoxIn)node.getUserObject();
		if(box != null){
			this.setText(box.getText());
			//�ж��Ƿ�ѡ��, �پ���ʹ������
			if (isSelected(node, tree)) {
				this.setFont(this.selectFont);
			} else {
				this.setFont(null);
			}
			this.setIcon(box.getImageIcon());
		}
		return this;
	}
	
	//�ж�һ��node�Ƿ�ѡ��
	private boolean isSelected(DefaultMutableTreeNode node, JTree tree) {
		//�õ�ѡ�е�TreePath
		TreePath treePath = tree.getSelectionPath();
		if (treePath == null) return false;
		//�õ���ѡ�еĽڵ�
		DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode)treePath.getLastPathComponent();
		if (node.equals(selectNode)) {
			return true;
		}
		return false;
	}
	
}