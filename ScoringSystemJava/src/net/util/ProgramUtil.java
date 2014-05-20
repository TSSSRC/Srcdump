package net.util;

import javax.swing.JOptionPane;

public class ProgramUtil
{
	public static void exit()
	{
		if (JOptionPane.showConfirmDialog(null, "Do you want to exit?") == 0) System.exit(0);
	}
}
