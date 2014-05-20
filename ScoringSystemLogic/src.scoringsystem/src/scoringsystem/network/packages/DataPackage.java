package src.scoringsystem.network.packages;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author Chase
 * @Category Network
 */
public class DataPackage implements Serializable
{
	private int packageID;
	private DataPackageType dataPackageType;
	private HashMap<String, Object> baseDataMap;
	private HashMap<String, Object> extraDataMap;
	
	public DataPackage()
	{
		baseDataMap = new HashMap<String, Object>();
		extraDataMap = new HashMap<String, Object>();
	}
	
	// ****Logic****//
	/**
	 * Adds an object to the DataPackage
	 * 
	 * @param Key
	 * @param obj
	 *            the object
	 * @return true if added
	 */
	public boolean addBaseObject(String Key, Object obj)
	{
		baseDataMap.put(Key, obj);
		return true;
	}
	
	// ****Getters****//
	
	/**
	 * @return Base Data in DataPackage
	 */
	public HashMap<String, Object> getBaseData()
	{
		return baseDataMap;
	}
	
	/**
	 * @return Extra Data in DataPackage
	 */
	public HashMap<String, Object> getExtraData()
	{
		return extraDataMap;
	}
	
	/**
	 * @return Package's ID for identification
	 */
	public int getID()
	{
		return this.packageID;
	}
	
	/**
	 * @param Key
	 * @return object from BaseData
	 */
	public Object getBaseDataObject(String Key)
	{
		Object obj = this.getBaseData().get(Key);
		if (obj != null)
		{
			return obj;
		}
		return null;
	}
	
	/**
	 * @param Key
	 * @return object from ExtraData
	 */
	public Object getExtraDataObject(String Key)
	{
		Object obj = this.getExtraData().get(Key);
		if (obj != null)
		{
			return obj;
		}
		return null;
	}

	/**
	 * @return the DataPackageType
	 */
    public DataPackageType getDataPackageType()
    {
	    return dataPackageType;
    }

	/**
	 * @param dataPackageType the DataPackageType to set
	 */
    public void setDataPackageType(DataPackageType dataPackageType)
    {
	    this.dataPackageType = dataPackageType;
    }
	
	// ****Setters****//
}
