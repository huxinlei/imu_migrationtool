package cn.edu.imu.migration.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import cn.edu.imu.migration.entity.Column;
import cn.edu.imu.migration.entity.Task;
public class UpdateFrame extends JFrame{
	//����MainFrame������
	private MainFrame main;
	//������Ϣ
	private Task task;
	//sql�б�
	private List<String> sqls;
	//�����б�
	private List<Column> cols;
	//������
	private JLabel taskNoL = new JLabel("�����ţ�");
	private JTextField taskNoT = new JTextField(10);
	//���񱣴��ļ���
	private JLabel taskFileL = new JLabel("�����ļ�����");
	private JTextField taskFileT = new JTextField(10);
	//����sql
	private JLabel taskSqlL = new JLabel("sql��䣺");
	private JTextArea taskSqlT = new JTextArea();
	//��β�ָ�Label
	private JLabel seperator1 = new JLabel("");
	private JLabel seperator2 = new JLabel("");
	private JLabel seperator3 = new JLabel("                 ");
	private JLabel seperator4 = new JLabel("");
	//sql JScrollPane
	private JScrollPane sqlScrollPane;
	//���������б�JTable
	private JTable taskColumns;
	private Object[][] cells = {};
	private String[] columnNames = {"��������","��������","�Ƿ��Զ����"};
	private JScrollPane colScrollPane;
	//��������
	private JLabel colNameL = new JLabel("�������ƣ�");
	private JTextField colNameT = new JTextField(10);
	//��������
	private String[] typeList = {"String", "Int", "Long", "Short", "Float",
			"Double", "Boolean", "Date", "BigDecimal"};
	private JLabel colTypeL = new JLabel("�������ͣ�");
	private JComboBox colTypeT;
	//�Ƿ�0��Ϊnull
	private String[] isNull = {"false", "true"};
	private JLabel colNullL = new JLabel("�Ƿ��Զ���ţ�");
	private JComboBox colNullT;
	//���Ӱ�ť
	private JButton addBtn = new JButton("��������");
	//�޸İ�ť
	private JButton updateBtn = new JButton("�޸�����");
	//ɾ����ť
	private JButton deleteBtn = new JButton("ɾ������");
	//���水ť
	private JButton saveBtn = new JButton("����") ;
	//ȡ����ť
	private JButton cancelBtn = new JButton("ȡ��");
	//Ĭ�Ͽ���
	private static final int DEFAULT_WIDTH = 900;
	//Ĭ�ϸ߶�
	private static final int DEFAULT_HIGHT = 600;
	private void initComponents(){
		this.taskColumns = new JTable(new DefaultTableModel(cells, columnNames)){
			//���ñ��񲻿ɱ༭
			public boolean isCellEditable(int row,int col){    
				return false;
			} 
		};
		
		this.taskColumns.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.colTypeT = new JComboBox(typeList);
		this.colNullT = new JComboBox(isNull);	
	}
	private void initListener(){
		//���水ť
		this.saveBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				saveTask();
			}
		});
		//ȡ����ť
		this.cancelBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				cancelAdd();
				//JOptionPane.showMessageDialog(null, "cancel", "alert", JOptionPane.ERROR_MESSAGE); 
			}
		});
		//�������԰�ť
		this.addBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				addColumn();
				//JOptionPane.showMessageDialog(null, "add", "alert", JOptionPane.ERROR_MESSAGE); 
			}
		});
		//�޸����԰�ť
		this.updateBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				updateColumn();
			}
		});
		//ɾ�����԰�ť
		this.deleteBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				deleteColumn(); 
			}
		});
		//����table����ѡ�����
		this.taskColumns.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e){
				selectColumn();
			}
		});
	    this.addWindowListener(new WindowAdapter(){
	         public void windowOpened(WindowEvent e) {
	     		//��ʼ���޸Ĵ���������
	     		initDataShow();
	         }    
	     });
	}
	//�޸��������ݳ�ʼ��
	private void initDataShow(){
		List<Task> taskList = this.main.getTaskList();
		int selectTaskId = this.main.getSelectTaskId();
		for(int i = 0; i < taskList.size(); i++){
			Task task = taskList.get(i);
			if(task.getId() == selectTaskId){
				this.task = task;
				break;
			}
		}
		this.cols = task.getColumns();
		this.taskNoT.setText(String.valueOf(task.getId()));
		this.taskNoT.setEditable(false);
		this.taskFileT.setText(task.getFile());
		String sql = task.getSql().get(0);
		this.taskSqlT.setText(sql);
		DefaultTableModel tableModel = (DefaultTableModel)(this.taskColumns.getModel());
		for(int i = 0; i < this.task.getColumns().size(); i++){
			Column col = this.task.getColumns().get(i);
			Object[] row = {col.getName(), col.getType(), col.getIntNull()};	
			tableModel.addRow(row); 
		}
	}
	//�ر����Ӵ���
	public void cancelAdd(){
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(false);
	}
	//��������
	public void addColumn(){
		if(this.colNameT.getText().equals("")){
			JOptionPane.showMessageDialog(null, "�������Ʋ���Ϊ�գ�", "��ʾ", JOptionPane.ERROR_MESSAGE); 
		} else {
			Column col = new Column();
			col.setName(this.colNameT.getText());
			boolean intNull = Boolean.valueOf((String)this.colNullT.getSelectedItem());
			col.setIntNull(intNull);
			col.setType((String)this.colTypeT.getSelectedItem());
			
			boolean exists = false;
			for(int i = 0; i < this.cols.size(); i++){
				Column colt = this.cols.get(i);
				if(colt.getName().equals(col.getName())){
					exists = true;
					break;
				}
			}
			if(!exists){
				if(this.cols.size() == 0){
					col.setId(1);
				} else {
					Column colt = this.cols.get(this.cols.size() - 1);
					col.setId(colt.getId() + 1);
				}
				this.cols.add(col);
				Object[] row = {col.getName(), col.getType(), col.getIntNull()};	
				DefaultTableModel tableModel = (DefaultTableModel)(this.taskColumns.getModel());
				tableModel.addRow(row); 
			} else{
				JOptionPane.showMessageDialog(null, "�������Ʋ����ظ���", "��ʾ", JOptionPane.ERROR_MESSAGE); 
			}
		}
	}
	//ѡ������
	public void selectColumn(){
	       int selectedRow = this.taskColumns.getSelectedRow();  
	        if(selectedRow >= 0){
	            DefaultTableModel tableModel = (DefaultTableModel)(this.taskColumns.getModel());
	            String name = (String)tableModel.getValueAt(selectedRow, 0);  
	            String type = (String)tableModel.getValueAt(selectedRow, 1);
	            String intNull = String.valueOf(tableModel.getValueAt(selectedRow, 2));
	            this.colNameT.setText(name);
	            //this.colTypeT.setName(type);
	            this.colTypeT.setSelectedItem(type);
	            //this.colNameL.setName(intNull);
	            this.colNullT.setSelectedItem(intNull);
	        }
	}
	//�޸�����
	public void updateColumn(){
        int selectedRow = this.taskColumns.getSelectedRow();  
        if(selectedRow < 0){
        	JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵ������У�", "��ʾ", JOptionPane.ERROR_MESSAGE);
        } else {
            DefaultTableModel tableModel = (DefaultTableModel)(this.taskColumns.getModel());
            String name = (String)tableModel.getValueAt(selectedRow, 0);  
            int selectId = 0;
			Column col = new Column();
			col.setName(this.colNameT.getText());
			boolean intNull = Boolean.valueOf((String)this.colNullT.getSelectedItem());
			col.setIntNull(intNull);
			col.setType((String)this.colTypeT.getSelectedItem());
			
			boolean exists = false;
			for(int i = 0; i < this.cols.size(); i++){
				Column colt = this.cols.get(i);
				if(colt.getName().equals(name)){
					selectId = colt.getId();
					break;
				}
			}
			if(!exists){
				tableModel.setValueAt(col.getName(), selectedRow, 0);
				tableModel.setValueAt(col.getType(), selectedRow, 1);
				tableModel.setValueAt(col.getIntNull(), selectedRow, 2);
				
				for(int i = 0; i < this.cols.size(); i++){
					Column colt = this.cols.get(i);
					if(colt.getId() == selectId){
						colt.setName(col.getName());
						colt.setType(col.getType());
						colt.setIntNull(col.getIntNull());
						break;
					}
				}
			} else{
				JOptionPane.showMessageDialog(null, "�������Ʋ����ظ���", "��ʾ", JOptionPane.ERROR_MESSAGE); 
			}
        }
        this.taskColumns.clearSelection();
	}
	//ɾ������
	public void deleteColumn(){
        int selectedRow = this.taskColumns.getSelectedRow();  
        if(selectedRow < 0){
        	JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ���������У�", "��ʾ", JOptionPane.ERROR_MESSAGE);
        } else {
            DefaultTableModel tableModel = (DefaultTableModel)(this.taskColumns.getModel());
            String name = (String)tableModel.getValueAt(selectedRow, 0);  
			
			boolean finish = false;
			for(int i = 0; i < this.cols.size(); i++){
				Column colt = this.cols.get(i);
				if(colt.getName().equals(name)){
					this.cols.remove(colt);
					finish = true;
					break;
				}
			}
			if(finish){
				tableModel.removeRow(selectedRow);
				JOptionPane.showMessageDialog(null, "��������ɾ���ɹ���", "��ʾ", JOptionPane.INFORMATION_MESSAGE); 
			}
        }
        this.taskColumns.clearSelection();		
	}
	//����������Ϣ
	public void saveTask(){
		String taskNoStr = this.taskNoT.getText();
		List<Task> taskList = this.main.getTaskList();
		int taskNo = 0;
		//����������Ƿ�λ����
        Pattern pattern = Pattern.compile("^\\d+$");
        Matcher isNum = pattern.matcher(taskNoStr);
        if(!isNum.matches()){
        	JOptionPane.showMessageDialog(null, "�������������", "��ʾ", JOptionPane.ERROR_MESSAGE); 
        	return ;
        } else {
        	taskNo = Integer.valueOf(taskNoStr);
        }
        
        //����ļ�������Ϊ��
        String taskFileStr = this.taskFileT.getText();
        if(taskFileStr.equals("")){
        	JOptionPane.showMessageDialog(null, "�����ļ����Ʋ���Ϊ�գ�", "��ʾ", JOptionPane.ERROR_MESSAGE);
        	return ;
        }

        //���sql��䲻��Ϊ��
        String taskSqlStr = this.taskSqlT.getText();
        if(taskSqlStr.equals("")){
        	JOptionPane.showMessageDialog(null, "sql��䲻��Ϊ�գ�", "��ʾ", JOptionPane.ERROR_MESSAGE);
        	return ;
        }
        this.sqls = new ArrayList<String>();
        this.sqls.add(taskSqlStr);
        this.task = new Task();
        this.task.setId(taskNo);
        this.task.setFile(taskFileStr);
        this.task.setSql(this.sqls);
        this.task.setColumns(this.cols);
        
        for(int i = 0; i < taskList.size(); i++){
        	if(taskList.get(i).getId() == this.task.getId()){
        		taskList.get(i).setFile(this.task.getFile());
        		taskList.get(i).setSql(this.task.getSql());
        		taskList.get(i).setColumns(this.task.getColumns());
        		break;
        	}
        }
        this.main.setTaskList(taskList);
        this.main.refreshTree();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(false);
        
	}
	public UpdateFrame(MainFrame mainFrame){
		//MainFrame���ó�ʼ��
		this.main = mainFrame;
		//�����ʼ��
		this.task = new Task();
		//sql�б�
		this.sqls = new ArrayList<String>();
		//�����б�
		this.cols = new ArrayList<Column>();
		//��ʼ���ؼ�����
		initComponents();
		//��ʼ�������¼�
		initListener();
		
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(taskNoL);
		this.add(taskNoT);
		this.add(taskFileL);
		this.add(taskFileT);
		this.add(seperator1);
		this.add(taskSqlL);
		this.taskSqlT.setLineWrap(true);
		this.taskSqlT.setFont(new Font(null, Font.BOLD, 14));
		//this.taskSqlT.setSize(taskSqlT.getWidth(), taskSqlT.getHeight() + 10);
		this.sqlScrollPane = new JScrollPane(this.taskSqlT);
		this.add(sqlScrollPane);
		//this.taskColumns.setSize(taskColumns.getWidth(), taskColumns.getHeight() - 10);
		this.colScrollPane = new JScrollPane(this.taskColumns);
		this.add(colScrollPane);
		this.add(colNameL);
		this.add(colNameT);
		this.add(colTypeL);
		this.add(colTypeT);
		this.add(colNullL);
		this.add(colNullT);
		this.add(addBtn);
		this.add(updateBtn);
		this.add(deleteBtn);
		this.add(seperator2);
		this.add(seperator3);
		this.add(saveBtn);
		this.add(cancelBtn);
		this.add(seperator4);
		GridBagConstraints s = new GridBagConstraints();
		s.insets = new Insets(3, 2, 2, 3);
		s.fill = GridBagConstraints.BOTH;
		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty = 0;
		layout.setConstraints(taskNoL, s);
		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty = 0;
		layout.setConstraints(taskNoT, s);
		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty = 0;
		layout.setConstraints(taskFileL, s);		
		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty = 0;
		layout.setConstraints(taskFileT, s);
		s.gridwidth = 0;
		s.weightx = 0;
		s.weighty = 0;
		layout.setConstraints(seperator1, s);
		
		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty = 1;
		layout.setConstraints(taskSqlL, s);
		s.gridwidth = 0;
		s.weightx = 1;
		s.weighty = 0;
		layout.setConstraints(sqlScrollPane, s);
		
		s.gridwidth = 0;
		s.weightx = 1;
		s.weighty = 0;
		layout.setConstraints(colScrollPane, s);
		
		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty = 0;
		layout.setConstraints(colNameL, s);
		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty = 0;
		layout.setConstraints(colNameT, s);
		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty = 0;
		layout.setConstraints(colTypeL, s);
		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty = 0;
		layout.setConstraints(colTypeT, s);
		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty = 0;
		layout.setConstraints(colNullL, s);
		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty = 0;
		layout.setConstraints(colNullT, s);
		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty = 0;
		layout.setConstraints(addBtn, s);
		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty = 0;
		layout.setConstraints(updateBtn, s);
		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty = 0;
		layout.setConstraints(deleteBtn, s);
		s.gridwidth = 0;
		s.weightx = 0;
		s.weighty = 0;
		layout.setConstraints(seperator2, s);
		
		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty = 0;
		layout.setConstraints(seperator3, s);
		s.fill = GridBagConstraints.RELATIVE;
		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty = 0;
		layout.setConstraints(saveBtn, s);
		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty = 0;
		layout.setConstraints(cancelBtn, s);
		s.gridwidth = 0;
		s.weightx = 0;
		s.weighty = 0;
		s.fill =  GridBagConstraints.BOTH;
		layout.setConstraints(seperator4, s);
		
		this.setTitle("�޸�����");
		this.setLocation(140, 80);
		this.setSize(DEFAULT_WIDTH, DEFAULT_HIGHT);
		this.setResizable(false);
	}
	
}