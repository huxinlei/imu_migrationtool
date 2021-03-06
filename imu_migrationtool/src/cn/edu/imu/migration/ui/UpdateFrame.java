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
	//添加MainFrame的引用
	private MainFrame main;
	//任务信息
	private Task task;
	//sql列表
	private List<String> sqls;
	//属性列表
	private List<Column> cols;
	//任务编号
	private JLabel taskNoL = new JLabel("任务编号：");
	private JTextField taskNoT = new JTextField(10);
	//任务保存文件名
	private JLabel taskFileL = new JLabel("保存文件名：");
	private JTextField taskFileT = new JTextField(10);
	//任务sql
	private JLabel taskSqlL = new JLabel("sql语句：");
	private JTextArea taskSqlT = new JTextArea();
	//结尾分割Label
	private JLabel seperator1 = new JLabel("");
	private JLabel seperator2 = new JLabel("");
	private JLabel seperator3 = new JLabel("                 ");
	private JLabel seperator4 = new JLabel("");
	//sql JScrollPane
	private JScrollPane sqlScrollPane;
	//任务属性列表JTable
	private JTable taskColumns;
	private Object[][] cells = {};
	private String[] columnNames = {"属性名称","属性类型","是否自动编号"};
	private JScrollPane colScrollPane;
	//属性名称
	private JLabel colNameL = new JLabel("属性名称：");
	private JTextField colNameT = new JTextField(10);
	//属性类型
	private String[] typeList = {"String", "Int", "Long", "Short", "Float",
			"Double", "Boolean", "Date", "BigDecimal"};
	private JLabel colTypeL = new JLabel("属性类型：");
	private JComboBox colTypeT;
	//是否将0设为null
	private String[] isNull = {"false", "true"};
	private JLabel colNullL = new JLabel("是否自动编号：");
	private JComboBox colNullT;
	//添加按钮
	private JButton addBtn = new JButton("添加属性");
	//修改按钮
	private JButton updateBtn = new JButton("修改属性");
	//删除按钮
	private JButton deleteBtn = new JButton("删除属性");
	//保存按钮
	private JButton saveBtn = new JButton("保存") ;
	//取消按钮
	private JButton cancelBtn = new JButton("取消");
	//默认宽度
	private static final int DEFAULT_WIDTH = 900;
	//默认高度
	private static final int DEFAULT_HIGHT = 600;
	private void initComponents(){
		this.taskColumns = new JTable(new DefaultTableModel(cells, columnNames)){
			//设置表格不可编辑
			public boolean isCellEditable(int row,int col){    
				return false;
			} 
		};
		
		this.taskColumns.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.colTypeT = new JComboBox(typeList);
		this.colNullT = new JComboBox(isNull);	
	}
	private void initListener(){
		//保存按钮
		this.saveBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				saveTask();
			}
		});
		//取消按钮
		this.cancelBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				cancelAdd();
				//JOptionPane.showMessageDialog(null, "cancel", "alert", JOptionPane.ERROR_MESSAGE); 
			}
		});
		//添加属性按钮
		this.addBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				addColumn();
				//JOptionPane.showMessageDialog(null, "add", "alert", JOptionPane.ERROR_MESSAGE); 
			}
		});
		//修改属性按钮
		this.updateBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				updateColumn();
			}
		});
		//删除属性按钮
		this.deleteBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				deleteColumn(); 
			}
		});
		//添加table的行选择监听
		this.taskColumns.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e){
				selectColumn();
			}
		});
	    this.addWindowListener(new WindowAdapter(){
	         public void windowOpened(WindowEvent e) {
	     		//初始化修改窗体中数据
	     		initDataShow();
	         }    
	     });
	}
	//修改任务数据初始化
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
	//关闭添加窗体
	public void cancelAdd(){
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(false);
	}
	//添加属性
	public void addColumn(){
		if(this.colNameT.getText().equals("")){
			JOptionPane.showMessageDialog(null, "属性名称不能为空！", "提示", JOptionPane.ERROR_MESSAGE); 
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
				JOptionPane.showMessageDialog(null, "属性名称不能重复！", "提示", JOptionPane.ERROR_MESSAGE); 
			}
		}
	}
	//选中属性
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
	//修改属性
	public void updateColumn(){
        int selectedRow = this.taskColumns.getSelectedRow();  
        if(selectedRow < 0){
        	JOptionPane.showMessageDialog(null, "请选择要修改的属性行！", "提示", JOptionPane.ERROR_MESSAGE);
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
				JOptionPane.showMessageDialog(null, "属性名称不能重复！", "提示", JOptionPane.ERROR_MESSAGE); 
			}
        }
        this.taskColumns.clearSelection();
	}
	//删除属性
	public void deleteColumn(){
        int selectedRow = this.taskColumns.getSelectedRow();  
        if(selectedRow < 0){
        	JOptionPane.showMessageDialog(null, "请选择要删除的属性行！", "提示", JOptionPane.ERROR_MESSAGE);
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
				JOptionPane.showMessageDialog(null, "属性名称删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE); 
			}
        }
        this.taskColumns.clearSelection();		
	}
	//保存任务信息
	public void saveTask(){
		String taskNoStr = this.taskNoT.getText();
		List<Task> taskList = this.main.getTaskList();
		int taskNo = 0;
		//检查编号输入是否位数字
        Pattern pattern = Pattern.compile("^\\d+$");
        Matcher isNum = pattern.matcher(taskNoStr);
        if(!isNum.matches()){
        	JOptionPane.showMessageDialog(null, "任务编号输入错误！", "提示", JOptionPane.ERROR_MESSAGE); 
        	return ;
        } else {
        	taskNo = Integer.valueOf(taskNoStr);
        }
        
        //检查文件名不能为空
        String taskFileStr = this.taskFileT.getText();
        if(taskFileStr.equals("")){
        	JOptionPane.showMessageDialog(null, "保存文件名称不能为空！", "提示", JOptionPane.ERROR_MESSAGE);
        	return ;
        }

        //检查sql语句不能为空
        String taskSqlStr = this.taskSqlT.getText();
        if(taskSqlStr.equals("")){
        	JOptionPane.showMessageDialog(null, "sql语句不能为空！", "提示", JOptionPane.ERROR_MESSAGE);
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
		//MainFrame引用初始化
		this.main = mainFrame;
		//任务初始化
		this.task = new Task();
		//sql列表
		this.sqls = new ArrayList<String>();
		//属性列表
		this.cols = new ArrayList<Column>();
		//初始化控件内容
		initComponents();
		//初始化监听事件
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
		
		this.setTitle("修改任务");
		this.setLocation(140, 80);
		this.setSize(DEFAULT_WIDTH, DEFAULT_HIGHT);
		this.setResizable(false);
	}
	
}
