package cn.edu.imu.migration.ui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TestFrame extends JFrame{
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				JFrame frame = new TestFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
	public TestFrame(){
		setTitle("PlanetTable");
		setSize(DEFAULT_WIDTH, DEFAULT_HIGHT);
		final JTable table = new JTable(cells, columnNames);
		table.setAutoCreateRowSorter(true);
		add(new JScrollPane(table), BorderLayout.CENTER);
		JButton printButton = new JButton("Print");
		printButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				try{
					table.print();
				}catch(java.awt.print.PrinterException e){
					e.printStackTrace();
				}
			}
		});
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(printButton);
		add(buttonPanel, BorderLayout.SOUTH);
	}
	private Object[][] cells = {{"Mercury",2440.0,0,false,Color.yellow},
			{"Mercury",2440.0,0,false,Color.yellow},{"Mercury",2440.0,0,false,Color.yellow},
			{"Mercury",2440.0,0,false,Color.yellow},
			{"Mercury",2440.0,0,false,Color.yellow},
			{"Mercury",2440.0,0,false,Color.yellow},
			{"Mercury",2440.0,0,false,Color.yellow},
			{"Mercury",2440.0,0,false,Color.yellow},
			{"Mercury",2440.0,0,false,Color.yellow}};
	private String[] columnNames = {"Planet","Radius","Moons","Gaseous","Color"};
	
	private static final int DEFAULT_WIDTH = 1000;
	private static final int DEFAULT_HIGHT = 200;
}
