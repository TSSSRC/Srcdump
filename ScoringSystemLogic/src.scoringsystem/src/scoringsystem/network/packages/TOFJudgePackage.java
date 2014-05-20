package src.scoringsystem.network.packages;

public class TOFJudgePackage extends DataPackage
{
	public TOFJudgePackage(int ID, double score)
	{
		super();
		this.setDataPackageType(DataPackageType.TOFJudge);
		this.addBaseObject("id", ID);
		this.addBaseObject("score", score);
	}
}
