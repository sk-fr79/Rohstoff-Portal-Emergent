package panter.gmbh.Echo2.InfoListen;

import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public abstract class E2_InfoList 
{


	private SQLFieldMAP   		oInnerSqlFieldMap = null;
	private E2_ComponentMAP   	oInnerComponentMap = null;
	private E2_NavigationList   oNaviList = null;
	
	
	public E2_InfoList() 
	{
		super();
	}

	
	
	public void startList() throws myException
	{
		this.oNaviList = new E2_NavigationList();
		
		this.oNaviList.INIT_WITH_ComponentMAP(this.oInnerComponentMap, this.get_MutableStyle4NaviListGrid(), this.get_KENNER_4_NAVILIST());
		
	}
	
	
	public abstract void 			initSqlFieldMap_and_ComponentMap() throws myException;
	public abstract String			get_KENNER_4_NAVILIST() throws myException;
	public abstract MutableStyle    get_MutableStyle4NaviListGrid() throws myException;  
	
	
	
	public SQLFieldMAP get_oInnerSqlFieldMap() 
	{
		return oInnerSqlFieldMap;
	}



	public E2_ComponentMAP get_oInnerComponentMap() 
	{
		return oInnerComponentMap;
	}



	public E2_NavigationList get_oNaviList() 
	{
		return oNaviList;
	}
	
	
	
}
