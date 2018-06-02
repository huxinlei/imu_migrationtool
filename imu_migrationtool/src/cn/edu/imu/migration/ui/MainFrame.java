package cn.edu.imu.migration.ui;

import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Dimension;

import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.JProgressBar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import cn.edu.imu.migration.commons.Config;
import cn.edu.imu.migration.commons.DateUtil;
import cn.edu.imu.migration.commons.FileOper;
import cn.edu.imu.migration.commons.InitTasks;
import cn.edu.imu.migration.entity.Task;
import cn.edu.imu.migration.service.TaskService;
import cn.edu.imu.migration.service.impl.TaskServiceImpl;

public class MainFrame extends JFrame{
	//任务列表
	private List<Task> taskList; 
	//选中任务编号
	private int selectTaskId;
	//分割任务列表树和右边内容的JSplitPane
	private JSplitPane mainSplitPane;
	//显示进度条的JSplitPane
	private JScrollPane proScrollPane;
	//显示日志信息的JSplitPane
	private JScrollPane logScrollPane;
	//内容JSplitPane的滚动条
	private JSplitPane contSplitPane;
	//任务列表树JSplitPane
	private JScrollPane treePane;
	//任务列表树
	private JTree tree;
	//tree root内容
	private DefaultMutableTreeNode root;
	//日志显示
	private JTextArea logTextArea = new JTextArea(10, 80);
	//进度条显示
	private JProgressBar progressBar = new JProgressBar(0, 100);
	//工具栏
	private JToolBar toolBar = new JToolBar();
	//时间格式
	private static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	//时间格式2
	private static String TIME_FORMAT2 = "yyyy_MM_dd_HH_mm_ss";
	//任务执行线程
	private Thread thread;
	//线程结束标志 true 运行 false未结束
	private boolean threadFlag = true;
	//系统启动时间
	private Date startDate;
	//最后一次结束时间
	private Date exeEndDate;
	//添加任务
	private Action add = new AbstractAction("添加任务",new ImageIcon("images/envelop-open.gif")){
		public void actionPerformed(ActionEvent e){
			//JOptionPane.showMessageDialog(null, "add", "alert", JOptionPane.ERROR_MESSAGE); 
			openAdd();
		}
	};
	//修改任务
	private Action update = new AbstractAction("修改任务",new ImageIcon("images/envelop-open.gif")){
		public void actionPerformed(ActionEvent e){
			//JOptionPane.showMessageDialog(null, "update", "alert", JOptionPane.ERROR_MESSAGE); 
			openUpdate();
		}
	};
	//删除任务
	private Action delete = new AbstractAction("删除任务",new ImageIcon("images/envelop-open.gif")){
		public void actionPerformed(ActionEvent e){
			deleteTask();
		}
	};
	//执行任务
	private Action execute = new AbstractAction("执行任务",new ImageIcon("images/envelop-open.gif")){
		public void actionPerformed(ActionEvent e){
			//executeTask();
			//开启任务线程
			startTaskThread();
		}
	};
	//停止任务执行
	private Action stop = new AbstractAction("停止任务", new ImageIcon("images/envelop-open.gif")) {
		public void actionPerformed(ActionEvent e) {
			//结束任务线程
			stopTaskThread();
		}
	};
	//设置
	private Action setup = new AbstractAction("设置", new ImageIcon("images/envelop-open.gif")) {
		public void actionPerformed(ActionEvent e) {
			openSetup();
		}
	};
	//初始化程序数据
	private void initData(){
		//初始化任务信息
		InitTasks initTasks = new InitTasks();
		this.taskList = initTasks.initTasks();
	}
	//创建任务树
	private JTree createTree(){
		//创建根节点
		this.root = new DefaultMutableTreeNode();
		//this.taskList.remove(0);
		for(int i = 0; i < this.taskList.size(); i++){
			Task task = this.taskList.get(i);
			String title = "任务" + task.getId() + "." + task.getFile();
			//加入格子节点
			root.add(new DefaultMutableTreeNode(new TreeBox(title)));
		}

		//创建树
		JTree tree = new JTree(root);
		//加入鼠标监听器
		tree.addMouseListener(new SailTreeListener(this));
		//隐藏根节点
		tree.setRootVisible(false);
		//设置节点处理类
		SailTreeCellRenderer cellRenderer = new SailTreeCellRenderer();
		tree.setCellRenderer(cellRenderer);
		return tree;
	}
	//刷新树形控件
	public void refreshTree(){
		this.root.removeAllChildren();
		this.tree.clearSelection();
		for(int i = 0; i < this.taskList.size(); i++){
			Task task = this.taskList.get(i);
			String title = "任务" + task.getId() + "." + task.getFile();
			//加入格子节点
			root.add(new DefaultMutableTreeNode(new TreeBox(title)));
		}
		this.tree.updateUI();
	}
	//创建工具条
	private void createToolBar(){
		this.toolBar.add(this.add).setText("添加任务");
		this.toolBar.add(this.update).setText("修改任务");
		this.toolBar.add(this.delete).setText("删除任务");
		this.toolBar.addSeparator(new Dimension(20, 0));
		this.toolBar.add(this.execute).setText("执行任务");
		this.toolBar.add(this.stop).setText("停止任务");
		this.toolBar.addSeparator(new Dimension(20, 0));
		this.toolBar.add(this.setup).setText("设置");
		//关闭按钮初始化不可用
		this.stop.setEnabled(false);
		//设置工具栏不可移动
		this.toolBar.setFloatable(false);
		//设置工具栏的边距
		this.toolBar.setMargin(new Insets(5, 10, 5, 5));
		this.add(this.toolBar, BorderLayout.NORTH);
	}
	//树控件事件
	public void select(){
		JOptionPane.showMessageDialog(null, "select", "alert", JOptionPane.ERROR_MESSAGE); 
	}
	//打开添加任务窗体
	private void openAdd(){
		AddFrame add = new AddFrame(this);
		add.setVisible(true);
	}
	//打开修改任务窗体
	private void openUpdate(){
		if(this.taskList.size() == 0){
			JOptionPane.showMessageDialog(null, "当前任务列表为空！", "提示", JOptionPane.WARNING_MESSAGE); 
		} else if(this.tree.getSelectionCount() == 0){
			JOptionPane.showMessageDialog(null, "请选择要修改的任务！", "提示", JOptionPane.WARNING_MESSAGE);
		} else {
			TreePath treePath = this.tree.getSelectionPath();
			if (treePath != null){
				//获得选中的TreeNode
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)treePath.getLastPathComponent();
				TreeBox box = (TreeBox)node.getUserObject();
				String taskInfo = box.getText();
				taskInfo = taskInfo.replaceFirst("任务", "");
				int position = taskInfo.indexOf(".");
				String bh = taskInfo.substring(0, position);
				int id = 0;
				try{
					id = Integer.valueOf(bh);
				}catch(Exception e){
					id = 0;
				}
				this.selectTaskId = id;
				UpdateFrame update = new UpdateFrame(this);
				update.setVisible(true);
			}
		}

	}
	//打开配置窗体
	private void openSetup(){
		SetupFrame setup = new SetupFrame();
		setup.setVisible(true);
	}
	//删除选定任务
	private void deleteTask(){
		if(this.taskList.size() == 0){
			JOptionPane.showMessageDialog(null, "当前任务列表为空！", "提示", JOptionPane.WARNING_MESSAGE); 
		} else if(this.tree.getSelectionCount() == 0){
			JOptionPane.showMessageDialog(null, "请选择要删除的任务！", "提示", JOptionPane.WARNING_MESSAGE);
		} else {
			TreePath treePath = this.tree.getSelectionPath();
			if (treePath != null){
				//获得选中的TreeNode
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)treePath.getLastPathComponent();
				TreeBox box = (TreeBox)node.getUserObject();
				String taskInfo = box.getText();
				taskInfo = taskInfo.replaceFirst("任务", "");
				int position = taskInfo.indexOf(".");
				String bh = taskInfo.substring(0, position);
				int id = 0;
				try{
					id = Integer.valueOf(bh);
				}catch(Exception e){
					id = 0;
				}
				for(int i = 0; i < this.taskList.size(); i++){
					Task task = this.taskList.get(i);
					if(task.getId() == id){
						this.taskList.remove(i);
						break;
					}
				}
				this.refreshTree();
			}
		}
	}
	//开始任务执行线程
	private void startTaskThread(){
		//改变按钮状态
		disableBtn();
		//设置线程状态为可执行状态
		this.threadFlag = true;
		//状态初始化
		this.logTextArea.setText("任务线程正在运行，等待任务执行！");
		//启动任务线程
		this.thread = new Thread(new Runnable(){
			public void run(){
				while(getThreadFlag()){
					if(isExecuteTask()){
						executeTask();
						try{
							Thread.sleep(5000);
						}catch(Exception e){
							e.printStackTrace();
						}
					} else {
						try{
							Thread.sleep(5000);
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}
			}
		});
		thread.start();
		//关闭按钮变为可用
		this.stop.setEnabled(true);
	}
	//获取线程执行标志
	private boolean getThreadFlag(){
		return this.threadFlag;
	}
	//获得启动日期
	private Date getStartDate(){
		return this.startDate;
	}
	//执行任务
	private void executeTask(){
			//更新最后结束时间
			this.exeEndDate = new Date();
			//设置结束按钮不可用
			//this.stop.setEnabled(false);
			if(this.taskList.size() == 0) {
				JOptionPane.showMessageDialog(null, "当前任务列表为空！", "提示", JOptionPane.WARNING_MESSAGE); 
			} else if(this.taskList.size() > 0) {
				TaskService taskService = new TaskServiceImpl();
				//计算进度条
				double count = this.taskList.size() + 3;
				String log = "";
				SimpleDateFormat sf = new SimpleDateFormat(TIME_FORMAT);
				//执行任务数据获取
				for(int i = 0; i < this.taskList.size(); i++){
					Task task = this.taskList.get(i);
					Date date = new Date();
					String str = sf.format(date);
					System.out.println(str);
					taskService.taskExecute(task);
					System.out.println(task.getSql());
					log = log + "任务" + task.getId() + "." + task.getFile() + ",数据总条数为" + task.getCount();
					log = log + ",导出时间：" + str + "\r\n";
					this.logTextArea.setText(log);
					int pValue = (int)((i + 1) / count * 100);
					this.progressBar.setValue(pValue);
				}
				
				int pValue1 = (int)(this.taskList.size() + 1 / count * 100);
				//数据打包
				String file = taskService.tasksCompress(this.taskList);
				Date dateC = new Date();
				String str = sf.format(dateC);
				log = log + "数据压缩成功,压缩完成时间为：" + str + "\r\n";
				System.out.println(log);
				this.logTextArea.setText(log);
				this.progressBar.setValue(pValue1);
				
				pValue1 = (int)(this.taskList.size() + 2 / count * 100);
				boolean result1 = taskService.tasksUpload(file);
				Date dateU = new Date();
				str = sf.format(dateU);
				log = log + "数据上传成功,上传完成时间为：" + str + "\r\n";
				System.out.println(log);
				this.logTextArea.setText(log);
				this.progressBar.setValue(pValue1);
				
				boolean result2 = taskService.tasksDelete(this.taskList, file);
				Date dateD = new Date();
				str = sf.format(dateD);
				log = log + "数据删除成功,删除完成时间为：" + str + "\r\n";
				System.out.println(log);
				this.logTextArea.setText(log);
				this.progressBar.setValue(100);
				
				if(result1 && result2){
					Date dateS = new Date();
					str = sf.format(dateS);
					log = log + "任务正确执行完成,完成时间为：" + str + "\r\n";
					System.out.println(log);
					this.logTextArea.setText(log);
					//JOptionPane.showMessageDialog(null, "任务执行完毕！", "提示", JOptionPane.INFORMATION_MESSAGE);
				} else {
					Date dateF = new Date();
					str = sf.format(dateF);
					log = log + "任务执行失败,失败时间为：" + str + "\r\n";
					System.out.println(log);
					this.logTextArea.setText(log);
					JOptionPane.showMessageDialog(null, "任务线程执行出错！", "提示", JOptionPane.ERROR_MESSAGE);
				}
				Date dateL = new Date();
				sf = new SimpleDateFormat(TIME_FORMAT2);
				str = sf.format(dateL);
				String filel = "log" + str + ".txt";
				FileOper.saveStrToFile(filel, log);
			}
			//设置显示状态
			this.logTextArea.setText("任务程序正在运行，等待任务执行！");
			//设置进度条滚动状态
			this.progressBar.setValue(0);
			//设计结束按钮可用
			//this.stop.setEnabled(true);
	}
	//结束任务执行线程
	private void stopTaskThread(){
		this.threadFlag = false;
		if(this.thread != null){
			this.thread.stop();
		}
		this.thread = null;
		this.progressBar.setValue(0);
		this.logTextArea.setText("任务线程未执行！");
		//改变按钮状态
		enableBtn();
		//关闭按钮变为不可用
		this.stop.setEnabled(false);
	}
	//按钮状态不可用
	private void disableBtn(){
		this.add.setEnabled(false);
		this.update.setEnabled(false);
		this.delete.setEnabled(false);
		this.execute.setEnabled(false);
		this.setup.setEnabled(false);
	}
	//按钮状态可用
	private void enableBtn(){
		this.add.setEnabled(true);
		this.update.setEnabled(true);
		this.delete.setEnabled(true);
		this.execute.setEnabled(true);
		this.setup.setEnabled(true);
	}
	//获取任务列表信息
	public List<Task> getTaskList() {
		return taskList;
	}
	//修改任务列表信息
	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}
	//获取选中任务下标
	public int getSelectTaskId() {
		return selectTaskId;
	}
	//判断是否满足执行任务的条件
	private boolean isExecuteTask(){
		//获取当前日期
		Date now = new Date();
		Date start = getStartDate();
		int days;
		try {
			days = DateUtil.daysBetween(start, now);
		} catch (ParseException e1) {
			days = 1;
		}
		int interval = Integer.parseInt(Config.INTERVAL);
		int exeDate = Integer.parseInt(Config.DATE);
		boolean interFlag = (days % interval == 0);
		boolean timeFlag = (exeDate == now.getHours());
		boolean dateFlag = true;
		if(exeEndDate != null){
			dateFlag = (now.getYear() == exeEndDate.getYear())&&
					(now.getMonth() == exeEndDate.getMonth())&&(now.getDate() == exeEndDate.getDate());
		} else {
			dateFlag = false;
		}
		return interFlag && timeFlag && !dateFlag;
	}
	public MainFrame(){
		//系统启动时间
		this.startDate = new Date();
		//初始化任务相关数据
		initData();
		//初始化任务树形控件
		this.tree = createTree();
		
		this.proScrollPane = new JScrollPane(this.progressBar);
		this.progressBar.setValue(0);
		
		this.logTextArea.setLineWrap(true);
		this.logTextArea.setEditable(false);
		this.logTextArea.setFont(new Font(null, Font.BOLD, 14));
		this.logTextArea.setText("任务线程未执行！");
		this.logScrollPane = new JScrollPane(this.logTextArea);
		
		this.contSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				this.proScrollPane, this.logScrollPane);
		this.contSplitPane.setDividerSize(1);
		this.treePane = new JScrollPane(this.tree);
		this.mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				this.treePane, this.contSplitPane);
		this.mainSplitPane.setDividerLocation(200);
		this.mainSplitPane.setDividerSize(1);
		//创建工具条
		createToolBar();
		//设置JFrame的各个属性
		this.add(mainSplitPane);
		this.setTitle("数据迁移工具内大版");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);	
	}
}
