package net.window;

import java.awt.EventQueue;

import javax.swing.JFrame;

import net.window.frames.login.LoginFrame;

public class MainWindow
{
	public static MainWindow window;
	private JFrame frame;
	
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
					window = new MainWindow();
					window.frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public MainWindow()
	{
		initialize(new LoginFrame());
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JFrame jframe)
	{		
		frame = jframe;
		//frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setEnabled(true);
	}
	
}
