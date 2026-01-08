package rohstoff.utils;

import panter.gmbh.indep.dataTools.MyDataRecordHashMap;
import panter.gmbh.indep.exceptions.myException;

public class MyVPOS_ANGEBOT extends MyDataRecordHashMap
{
	
	private MyDataRecordHashMap  hmVPOS_STD_ANGEBOT = null;
	private MyDataRecordHashMap  hmVKOPF_STD = null;
	
	public MyVPOS_ANGEBOT(String keyvalue_unformated) throws myException
	{
		super("JT_VPOS_STD", "ID_VPOS_STD", keyvalue_unformated);
		
		this.hmVPOS_STD_ANGEBOT = 	new MyDataRecordHashMap("SELECT * FROM JT_VPOS_STD_ANGEBOT WHERE ID_VPOS_STD="+	this.get_UnFormatedValue("ID_VPOS_STD"));
		this.hmVKOPF_STD = 			new MyDataRecordHashMap("SELECT * FROM JT_VKOPF_STD WHERE ID_VKOPF_STD="+		this.get_UnFormatedValue("ID_VKOPF_STD"));
	}

	public MyDataRecordHashMap get_hmVPOS_STD_ANGEBOT()
	{
		return this.hmVPOS_STD_ANGEBOT;
	}

	public MyDataRecordHashMap get_hmVKOPF_STD()
	{
		return this.hmVKOPF_STD;
	}

}
