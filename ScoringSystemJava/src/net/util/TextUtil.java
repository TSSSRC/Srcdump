package net.util;

public class TextUtil
{
	public static class Convert
	{
		public static String toString(char[] array)
		{
			String finalString = "";
			for(char a : array)
			{
				finalString += a;
			}
			return finalString;
		}
	}
}
