package Org.BBG.Profile;

import Org.BBG.Org.BBG.Api.BaseClass;

public class getCustomerAddresses extends BaseClass
{
	public void GetCustomerAddresses() throws Exception 
	{
		connectWithDataBase();
		dataBaseQuery("select id_customer from customer");
		
	}
}
