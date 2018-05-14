package Org.BBG.store;

import Org.BBG.Org.BBG.Api.BaseClass;

public class getNearBypickupStore extends BaseClass
{
	public void GetNearByPickupStore() throws Exception
	{
		connectWithDataBase();
		dataBaseQuery("");
		while(resultSet.next())
		{
			
		}
	}
}
