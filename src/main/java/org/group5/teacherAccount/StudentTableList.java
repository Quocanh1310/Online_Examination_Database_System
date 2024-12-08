package org.group5.teacherAccount;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatDarkLaf;
import org.group5.updateRes.GetStudentListInTheCourse;
import org.group5.updateRes.LoadCreatedClass;


import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Window.Type;

public class StudentTableList extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JComboBox<String> comboBox;
    private ArrayList<String> courseNames;
    private LoadCreatedClass l;
    private JButton btnNewButton;
    
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					StudentTableList frame = new StudentTableList();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public StudentTableList() {
		setForeground(new Color(0, 0, 0));
		setBackground(new Color(255, 255, 255));
		setTitle("EduExam");
		setType(Type.UTILITY);
		try {
			UIManager.setLookAndFeel(new FlatDarkLaf());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700,450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 83, 687, 319);
        contentPane.add(scrollPane);
        
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JButton test = new JButton("Student List");
		test.setBackground(new Color(255, 255, 51));
		test.setForeground(new Color(0, 0, 0));
		test.setFont(new Font("Dubai Medium", Font.PLAIN, 12));
		test.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String course = (String) comboBox.getSelectedItem();
				tableModel.setRowCount(0);
				tableModel.setColumnCount(0);
            	getData(tableModel,course,0);
			}
		});
		test.setBounds(125, 40, 109, 32);
		contentPane.add(test);
		
		btnNewButton = new JButton("Score List");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(255, 255, 51));
		btnNewButton.setFont(new Font("Dubai Medium", Font.PLAIN, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String course = (String) comboBox.getSelectedItem();
				tableModel.setRowCount(0);
				tableModel.setColumnCount(0);
            	getData(tableModel,course,1);
			}
		});
		btnNewButton.setBounds(247, 39, 109, 33);
		contentPane.add(btnNewButton);
		
		
		comboBox = new JComboBox<>();
		comboBox.setBackground(new Color(255, 153, 255));
		comboBox.setForeground(new Color(0, 0, 0));
		comboBox.setFont(new Font("Dubai Medium", Font.PLAIN, 12));
		comboBox.setBounds(10, 40, 96, 32);
		contentPane.add(comboBox);
		createComboBox(comboBox);
		
		JLabel selectCourseLabel = new JLabel("Select Course :");
		selectCourseLabel.setForeground(new Color(0, 0, 0));
		selectCourseLabel.setFont(new Font("Dubai Medium", Font.ITALIC, 14));
		selectCourseLabel.setBounds(10, 15, 96, 26);
		contentPane.add(selectCourseLabel);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
        addWindowListener(new WindowAdapter() {
        	@Override
            public void windowClosing(WindowEvent e) {
//                Teacher_UI.frame.getClass().setVisible(false);
				Frame teacherFrame = Teacher_UI.frame;
				teacherFrame.setVisible(false);
                dispose(); 
            }
        });
		
		
	}
	
	private void createComboBox(JComboBox<String> c) {
		l = new LoadCreatedClass();
		try {
			l.getCourseName();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		courseNames = l.getCourseNames();
		for(int i = 0; i < courseNames.size(); i++) {
			c.addItem(courseNames.get(i));
		}courseNames.clear();
		
	}
	
	private void getData(DefaultTableModel tableModel,String courseName,int i) {
		GetStudentListInTheCourse a = new GetStudentListInTheCourse();
        try {
        	if(i == 0)
			   a.getStudentInformation(courseName);
        	else
        	   a.getListWithExam(courseName);	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        
		tableModel.setColumnIdentifiers(a.getColumns());
		for(int j = 0; j < a.getCount(); j++) {
           tableModel.addRow(a.getRows()[j]);
		}

		
	}
}
