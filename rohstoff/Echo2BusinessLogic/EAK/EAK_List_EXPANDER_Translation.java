package rohstoff.Echo2BusinessLogic.EAK;

import java.util.ArrayList;
import java.util.HashMap;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.ListAndMask.XX_List_EXPANDER_4_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class EAK_List_EXPANDER_Translation extends 		XX_List_EXPANDER_4_ComponentMAP
{

	private String 						BASENAME 			= 		null;
	private EAK_BasicModuleContainer  	BasicContainerForAll = 	null;
	
	private E2_NavigationList   		oNavigationSprache = null;
	
	/**
	 * @param NavigationList_Mother (Navigationlist von oben)
	 * @param cBASE_NAME (Kann sein: BRANCHE / GRUPPE / CODE)
	 * @throws myException
	 */
	public EAK_List_EXPANDER_Translation(		E2_NavigationList 			NavigationList_Mother,
												String 						cBASENAME,
												EAK_BasicModuleContainer  	oBasicContainerForAll) throws myException
	{
		super(NavigationList_Mother);
		this.BASENAME = cBASENAME;
		this.BasicContainerForAll = oBasicContainerForAll;
		this.set_iLeftColumnOffset(4);
	}

	public Component get_ComponentDaughter(String cID_ROW_Unformated) throws myException
	{
		MyE2_Row oRow = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());
		
		
		this.oNavigationSprache = 
						new E2_NavigationList();

		EAK_ComponentMapTRANSLATION oComponentMAP = new EAK_ComponentMapTRANSLATION(this.BASENAME,cID_ROW_Unformated,this.BasicContainerForAll,this.oNavigationSprache);
		
		oNavigationSprache.INIT_WITH_ComponentMAP(oComponentMAP,this.BasicContainerForAll.get_StyleForUebersetzungsTables(), null);
		
		oNavigationSprache._REBUILD_COMPLETE_LIST("");
		
		oNavigationSprache.setBorder(new Border(0,Color.BLACK,Border.STYLE_SOLID));
		
		oNavigationSprache.get_vectorSegmentation().set_bOnlyOneSegment(true);
		
		oRow.add(this.oNavigationSprache.get_oDataGrid());
		
		return oRow;
	}


	public void refreshDaughterComponent() throws myException
	{
		this.oNavigationSprache._REBUILD_COMPLETE_LIST("");
	}

	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try
		{
			EAK_List_EXPANDER_Translation oDaughter = new EAK_List_EXPANDER_Translation(this.get_oNavigationList(),this.BASENAME, this.BasicContainerForAll);
			return oDaughter;
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("EAK_DaughterTRANSLATION:get_Copy:"+ex.get_ErrorMessage().get_cMessage().COrig());
		}
	}

	@Override
	public ArrayList<HashMap<String, Component>>  get_ComponentListDaughter(String unformated) throws myException
	{
		return null;
	}
	

}
