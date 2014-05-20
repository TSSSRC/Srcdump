package src.scoringsystem.network;

public class Response
{
	public enum SocketResponse
	{
		SUCCESSFULL(1), ERROR_FAILED(2);
		
		private int value;
		
		private SocketResponse(int i)
		{
			this.value = i;
		}
		
		public int getValue()
		{
			return value;
		}
		
	}
	
}
