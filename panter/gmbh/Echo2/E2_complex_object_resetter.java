package panter.gmbh.Echo2;

import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentAbstract;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.indep.exceptions.myException;

public class E2_complex_object_resetter
{
	private E2_ListSelectorContainer  	oListSelector = null;
	private E2_DataSearch   			oDataSearch = null;
	private E2_NavigationList      		oNaviList = null;
	
	public E2_complex_object_resetter(E2_ListSelectorContainer ListSelector, E2_DataSearch DataSearch, E2_NavigationList  NaviList)
	{
		super();
		this.oListSelector = 	ListSelector;
		this.oDataSearch = 		DataSearch;
		this.oNaviList = 		NaviList;
	}
	
	
	public void prepare_4_start() throws myException
	{
		
		if (this.oListSelector!=null)
		{
			this.oListSelector.SONDERSTATUS_AUSSCHALTEN();
		}
		
		if (this.oDataSearch!=null)
		{
			E2_DataSearchAgentAbstract	osearchAgent = this.oDataSearch.get_oSearchAgent();
			this.oDataSearch.get_vSearchFields().removeAllElements();
			this.oDataSearch.add_SearchTextField();
			this.oDataSearch.rebuild_SearchTextFieldColumn();
			if (osearchAgent != null)
			{
				try
				{
					osearchAgent.ResetSearch(this.oDataSearch,true);
				}
				catch(myException ex)
				{
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
				}
			}
		}
		
	}
	
	
	
	public void clean_list() throws myException
	{
		if (this.oNaviList != null)
		{
			this.oNaviList.get_vectorSegmentation().removeAllElements();
			this.oNaviList.get_vActualID_Segment().removeAllElements();
			this.oNaviList.get_vIDs_Found_ButNotInSelektion().removeAllElements();
			this.oNaviList.get_vIDs_From_Search().removeAllElements();
			this.oNaviList.get_vIDs_From_Selector().removeAllElements();

			this.oNaviList.get_vComponentMAPS().removeAllElements();
			this.oNaviList.get_vComponentMAPS_NEW().removeAllElements();
			
			this.oNaviList.get_oDataGrid().removeAll();
		}
	}
	
	
	
}
