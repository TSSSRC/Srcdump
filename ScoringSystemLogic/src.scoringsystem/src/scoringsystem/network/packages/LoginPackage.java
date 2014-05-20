package src.scoringsystem.network.packages;


public class LoginPackage extends DataPackage
{
	public LoginPackage(String Username, String Password)
	{
		super();
		this.setDataPackageType(DataPackageType.Login);
		this.addBaseObject("username", Username);
		this.addBaseObject("password", Password);
		//TODO Remove Debugger Code
		System.out.println(this.getBaseDataObject("username"));
		System.out.println(this.getBaseDataObject("password"));
	}
}
