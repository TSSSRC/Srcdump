package net.window.frames.login;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.util.ProgramUtil;

public class LoginFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 265329133736795911L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField pwbPassword;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public LoginFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				ProgramUtil.exit();
			}
		});
		btnExit.setBounds(10, 407, 89, 23);
		contentPane.add(btnExit);
		
		JLabel lblWelcome = new JLabel("Welcome to the Trampoline Scoring System");
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblWelcome.setBounds(105, 0, 471, 91);
		contentPane.add(lblWelcome);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(114, 113, 92, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(114, 152, 92, 14);
		contentPane.add(lblNewLabel_1);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(216, 113, 175, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		pwbPassword = new JPasswordField();
		pwbPassword.setEchoChar('*');
		pwbPassword.setBounds(216, 149, 175, 20);
		contentPane.add(pwbPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				LoginActions.TestLogin(txtUsername.getText(), pwbPassword.getPassword());
			}
		});
		btnLogin.setBounds(565, 407, 89, 23);
		contentPane.add(btnLogin);
	}
}
