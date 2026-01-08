package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH;

import java.util.ArrayList;
import java.util.HashMap;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.ListAndMask.XX_List_EXPANDER_4_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_KREDITVERS_POS;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_CONST;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_MASK_FullDaughter_Kreditversicherung;

public class KV_HEAD_ListExpander_SHOW_POS_LIST extends XX_List_EXPANDER_4_ComponentMAP  
{
	
	private E2_NavigationList   			oNaviListVersKopf = null;
	private KV_HEAD_LIST_BasicModule_Inlay   oBasicContainerVersKopf = null;
	private KV_POS_LIST_BasicModule_Inlay  	oListInlay = null;
	
	
	
	public KV_HEAD_ListExpander_SHOW_POS_LIST(E2_NavigationList NavigationList, KV_HEAD_LIST_BasicModule_Inlay  BasicContainerVersKopf) throws myException 
	{
		super(NavigationList);
		
		this.oNaviListVersKopf = 		NavigationList;
		this.oBasicContainerVersKopf = 	BasicContainerVersKopf;
		
		this.set_iLeftColumnOffset(3);
		
	}

	
	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy 
	{
		try 
		{
			KV_HEAD_ListExpander_SHOW_POS_LIST oPosExtender = new KV_HEAD_ListExpander_SHOW_POS_LIST(this.oNaviListVersKopf, this.oBasicContainerVersKopf);
			return oPosExtender;
		} 
		catch (myException e) 
		{
			e.printStackTrace();
			throw new myExceptionCopy(e.get_ErrorMessage().get_cMessage().COrig());
		}

	}

	@Override
	public Component get_ComponentDaughter(String cID_ROW_Unformated)	throws myException 
	{
		//this.oListInlay = new KV_POS_LIST_BasicModule_Inlay(this.oBasicContainerVersKopf);
		
		
		
		
		this.get_ownListInlay().set_ID_From_Calling_Record(cID_ROW_Unformated);
		this.get_ownListInlay().get_oNaviListFirstAdded()._REBUILD_COMPLETE_LIST("");
		
		if (this.oBasicContainerVersKopf.get_oCallingAdresseContainer().get_oComponentMAP() !=null)    //beim aufbau kann das null sein
		{
			//jetzt nachsehen, welchen status das darueberliegende object hat, und die buttons evtl. inaktiv schalten
			FS_MASK_FullDaughter_Kreditversicherung  MaskDaughter_Kredit_VS = 
				(FS_MASK_FullDaughter_Kreditversicherung)this.oBasicContainerVersKopf.get_oCallingAdresseContainer().get_oComponentMAP().get__Comp(FS_CONST.MASK_FIELD_DAUGHTER_KREDITVERSICHERUNG);
			
			this.get_ownListInlay().set_ListButtonsEnabled(MaskDaughter_Kredit_VS.get_bMaskStatus_IS_Edit(), true);
		}
		
				
		return this.get_ownListInlay();
	}

	@Override
	public ArrayList<HashMap<String, Component>> get_ComponentListDaughter(String cID_ROW_Unformated) throws myException 
	{
		return null;
	}

	@Override
	public void refreshDaughterComponent() throws myException 
	{
		//this.get_ownListInlay().get_oNaviListFirstAdded()._REBUILD_COMPLETE_LIST("");
		this.oBasicContainerVersKopf.get_oSelVector().doActionPassiv();
	}

	private KV_POS_LIST_BasicModule_Inlay get_ownListInlay() throws myException
	{
		if (this.oListInlay==null)
		{
			this.oListInlay = new KV_POS_LIST_BasicModule_Inlay(this.oBasicContainerVersKopf);
			//2012-01-31: Sortierung am Start ist immer, der neueste Aktive vorne
			String cSortString = RECORD_KREDITVERS_POS.FIELD__AKTIV+" DESC, "+RECORD_KREDITVERS_POS.FIELD__GUELTIG_AB+" DESC";
			this.get_ownListInlay().get_oNaviListFirstAdded().get_oComponentMAP__REF().get_oSQLFieldMAP().clear_ORDER_SEGMENT();
			this.get_ownListInlay().get_oNaviListFirstAdded().get_oComponentMAP__REF().get_oSQLFieldMAP().add_ORDER_SEGMENT(cSortString);

		}
		return this.oListInlay;
	}
	
}
