package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import tn.bettaieb.real_app.services.TodoServicesRemote;

public class TaskManagementFrame extends JFrame {
	private Context context;
	private TodoServicesRemote todoServicesRemote;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaskManagementFrame frame = new TaskManagementFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TaskManagementFrame() {
		try {
			context = new InitialContext();
			todoServicesRemote = (TodoServicesRemote) context
					.lookup("real-app-ear/real-app-ejb/TodoServices!tn.bettaieb.real_app.services.TodoServicesRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblTaskDescription = new JLabel("task description");

		textField = new JTextField();
		textField.setColumns(10);
		JTextArea textArea = new JTextArea();
		JButton btnAddTask = new JButton("add task");
		btnAddTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				todoServicesRemote.addTask(textField.getText());
			}
		});

		JButton btnListAllTasks = new JButton("list all tasks");
		btnListAllTasks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				List<String> listOfTasks = todoServicesRemote.findAllTasks();
				for (String s : listOfTasks) {
					textArea.setText(textArea.getText() + "\n" + s);
				}
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap().addComponent(lblTaskDescription).addGap(61)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(btnAddTask)
										.addComponent(btnListAllTasks))
								.addPreferredGap(ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
								.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTaskDescription).addComponent(textField, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 142,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(77, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(btnAddTask)
										.addPreferredGap(ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
										.addComponent(btnListAllTasks).addGap(85)))));
		contentPane.setLayout(gl_contentPane);
	}
}
