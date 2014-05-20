package src.scoringsystem.network.packages;

public class JudgeResetPackage extends DataPackage
{
	public JudgeResetPackage(boolean Reset)
	{
		super();
		this.setDataPackageType(DataPackageType.JudgeReset);
		this.addBaseObject("reset", Reset);
	}
}
