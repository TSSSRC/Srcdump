package net.window.frames.login;

import javax.swing.JOptionPane;

import net.util.TextUtil;

public class LoginActions
{
	public static boolean TestLogin(String username, char[] password)
	{
		if (username.equals("test") && TextUtil.Convert.toString((password)).equals("1234"))
		{
			JOptionPane.showMessageDialog(null, "Logged in!");
			return true;
		}
		JOptionPane.showMessageDialog(null, "Login failed!");
		return false;
	}
}
