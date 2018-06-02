package cn.edu.imu.migration.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import cn.edu.imu.migration.commons.FtpConnection;
import cn.edu.imu.migration.commons.FtpProperties;
import cn.edu.imu.migration.commons.InitTasks;
import cn.edu.imu.migration.commons.ZipUtil;
import cn.edu.imu.migration.entity.Column;
import cn.edu.imu.migration.entity.Task;
import cn.edu.imu.migration.service.TaskService;
import cn.edu.imu.migration.service.impl.TaskServiceImpl;
import cn.edu.imu.migration.ui.MainFrame;
public class Main {
	public static void main(String[] args) {
		MainFrame main = new MainFrame();
		main.setVisible(true);
	}
}
