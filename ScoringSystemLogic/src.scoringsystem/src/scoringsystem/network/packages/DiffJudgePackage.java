package src.scoringsystem.network.packages;

public class DiffJudgePackage extends DataPackage
{
	public DiffJudgePackage(int ID, double score)
	{
		super();
		this.setDataPackageType(DataPackageType.DiffJudge);
		this.addBaseObject("id", ID);
		this.addBaseObject("score", score);
	}
}
