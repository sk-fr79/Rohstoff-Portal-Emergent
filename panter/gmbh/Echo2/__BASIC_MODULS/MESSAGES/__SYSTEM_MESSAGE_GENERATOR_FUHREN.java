package panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES;

import java.util.Vector;

import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class __SYSTEM_MESSAGE_GENERATOR_FUHREN extends __SYSTEM_MESSAGE_GENERATOR
{

	private String cEK_VK = null;
	
	public __SYSTEM_MESSAGE_GENERATOR_FUHREN(String cID_ADRESSE, String EK_VK) throws myException
	{
		super(cID_ADRESSE);
		this.cEK_VK = EK_VK;
	}

	@Override
	public Vector<String> get_VectorSammleMeldungsTypen() throws myException
	{
		if (S.isEmpty(this.cEK_VK))
		{
			throw new myException(this,"EK-VK-typedef is not defined !!!");
		}
		
		Vector<String> vTypSelection = new Vector<String>();
		
		if (this.cEK_VK.equals("EK"))
		{
			vTypSelection.add(bibALL.MakeSql(myCONST.ADRESS_INFO_TYP_ALLGEMEIN));
			vTypSelection.add(bibALL.MakeSql(myCONST.ADRESS_INFO_TYP_EINKAUF));
		}
		else if (this.cEK_VK.equals("VK"))
		{
			vTypSelection.add(bibALL.MakeSql(myCONST.ADRESS_INFO_TYP_ALLGEMEIN));
			vTypSelection.add(bibALL.MakeSql(myCONST.ADRESS_INFO_TYP_VERKAUF));
		}
		else
		{
			throw new myException(this,"Typedef: "+this.cEK_VK+" is not allowed !!");
		}

		return vTypSelection;
	}

}
