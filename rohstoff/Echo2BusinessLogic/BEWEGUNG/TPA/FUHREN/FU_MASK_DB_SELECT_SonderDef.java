package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_NavigationList;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_ButtonMarkForDelete;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_SONDER;
import panter.gmbh.indep.MyDropDownSettings;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.FU__PRUEFUNG_FREMDWARE_AUF_FREMDLAGER;


public class FU_MASK_DB_SELECT_SonderDef extends MyE2_DB_SelectField
{

	/*
	 * variable fuer die event-abwicklung
	 */
	private String						cOLD_ID_VPOS_TPA_FUHRE_SONDER = "";
	private String						cOLD_OHNE_ABRECHNUNG = "N";
	private String						cOLD_KEIN_KONTRAKT_NOETIG = "N";
	private String						cOLD_OHNE_AVV_VERTRAG_CHECK = "N";

	
	private RECORD_VPOS_TPA_FUHRE 		recFuhre = null;
	private RECLIST_VPOS_TPA_FUHRE_ORT  reclistOrte = null;
	
	public FU_MASK_DB_SELECT_SonderDef(SQLField osqlField) throws myException
	{
		super(osqlField);
		
		MyDropDownSettings oDD = new MyDropDownSettings(bibE2.cTO(),"JT_VPOS_TPA_FUHRE_SONDER","AUSNAHME","ID_VPOS_TPA_FUHRE_SONDER",null,null,true,"AUSNAHME");
		this.set_ListenInhalt(oDD.getDD(),false);

		this.add_oActionAgent(new ownAction_Show_Yes_no_POPUP());
	}
	
	
	//2013-01-16: zwischenabfrage einschieben
	//2011-11-18: vor dem neuerfassen eines fuhrenortes muss die fuhre gepeichert werden, damit in der pruefung der maske kein fehler auftaucht
	//            wenn es ein leeres sortenfeld gibt.
	private class ownAction_Show_Yes_no_POPUP extends XX_ActionAgent
	{
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{

			FU_MASK_DB_SELECT_SonderDef oThis = FU_MASK_DB_SELECT_SonderDef.this;

			//ACHTUNG! Container wird nur angezeigt, wenn im editmode und es fuhrenort gibt, nur dann muss der fremdwarenstatus weitergegeben werden
			//         sonst ist das Verhalten wie bisher
			
			oThis.reset_alte_werte();
			
			if (oThis.EXT().get_oComponentMAP().get_bIs_Edit())   //wenn editiert wird
			{
				
				//nur dann wird der container zum speichern angezeigt
				oThis.HoleAlteWerte((FU_MASK_ComponentMAP)oThis.EXT().get_oComponentMAP());
				oThis.fuelle_RECORD_UND_RECLIST();
				
				if (oThis.reclistOrte.get_vKeyValues().size()>0)
				{
					new ownConfirmcontainer().show_POPUP_BOX();
				}
				else
				{
					oThis.setzeCheckboxWerte_Auf_Maske();
				}
			}
			else
			{
				oThis.setzeCheckboxWerte_Auf_Maske();
			}
		}

	}
	
	
	
	private class ownConfirmcontainer extends E2_ConfirmBasicModuleContainer
	{
		public ownConfirmcontainer() throws myException
		{
			super(	new MyE2_String("Achtung !"), 
					new MyE2_String("Bitte beachten ...."), 
					bibVECTOR.get_Vector(new MyE2_String("Dieser Vorgang speichert diese Fuhre sofort!"), 
										new MyE2_String("Bei vorhandenen Fuhrenorten wird der Status <Fremdwaren>"), 
										new MyE2_String("in die Fuhrenorte übertragen...")),
					new MyE2_String("OK-Speichern"), new MyE2_String("Nein - Abbruch"), new Extent(400), new Extent(400));
			
			
			//hier die alten werte sichern (fuer den abbruch-fall) 
			this.add_ActionAgentForOK_AfterCloseEvent(new ownActionOK_SAVE());
			this.get_oButtonCancel().add_oActionAgent(new ownAction_Cancel());
		}
	}

	
	
	private class ownActionOK_SAVE extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			FU_MASK_DB_SELECT_SonderDef oThis = FU_MASK_DB_SELECT_SonderDef.this;
			
			oThis.setzeCheckboxWerte_Auf_Maske();
			
			Vector<String>  vSQLs = oThis.get_SQL_SaveStack((FU_MASK_ComponentMAP)oThis.EXT().get_oComponentMAP());
			
			if (bibMSG.get_bIsOK())
			{
				MyE2_MessageVector  oMVRueck = bibDB.ExecMultiSQLVector(vSQLs, true);
			
				if (oMVRueck.get_bIsOK())
				{
					
					E2_vCombinedComponentMAPs vCombinedComponentMAPs = oThis.EXT().get_oComponentMAP().get_oModulContainerMASK_This_BelongsTo().get_vCombinedComponentMAPs();
					
					vCombinedComponentMAPs.MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_EDIT,oThis.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
					
					oThis.RefreshNaviList_Below((FU_MASK_ComponentMAP)oThis.EXT().get_oComponentMAP());
				}
				else
				{
					oThis.setzeMaskeAufAlteWerte();
					bibMSG.add_MESSAGE(oMVRueck);
				}
			}
			else
			{
				oThis.setzeMaskeAufAlteWerte();
			}
		}
	}
	

	
	private class ownAction_Cancel extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			FU_MASK_DB_SELECT_SonderDef.this.setzeMaskeAufAlteWerte();
		}
	}

	
	
	private void reset_alte_werte()
	{
		this.recFuhre = null;
		this.reclistOrte = null;
		this.cOLD_ID_VPOS_TPA_FUHRE_SONDER = "";
		this.cOLD_OHNE_ABRECHNUNG = "N";
		this.cOLD_KEIN_KONTRAKT_NOETIG = "N";
		this.cOLD_OHNE_AVV_VERTRAG_CHECK = "N";
	}
	
	
	private void HoleAlteWerte(FU_MASK_ComponentMAP  oMAP) throws myException
	{
		this.cOLD_ID_VPOS_TPA_FUHRE_SONDER = 	oMAP.get_oInternalSQLResultMAP().get_FormatedValue(RECORD_VPOS_TPA_FUHRE.FIELD__ID_VPOS_TPA_FUHRE_SONDER, "");
		this.cOLD_OHNE_ABRECHNUNG = 			oMAP.get_oInternalSQLResultMAP().get_FormatedValue(RECORD_VPOS_TPA_FUHRE.FIELD__OHNE_ABRECHNUNG, "");
		this.cOLD_KEIN_KONTRAKT_NOETIG = 		oMAP.get_oInternalSQLResultMAP().get_FormatedValue(RECORD_VPOS_TPA_FUHRE.FIELD__KEIN_KONTRAKT_NOETIG, "");
		this.cOLD_OHNE_AVV_VERTRAG_CHECK = 		oMAP.get_oInternalSQLResultMAP().get_FormatedValue(RECORD_VPOS_TPA_FUHRE.FIELD__OHNE_AVV_VERTRAG_CHECK, "");
	}
	
	
	private void fuelle_RECORD_UND_RECLIST() throws myException
	{
		this.recFuhre = null;
		this.reclistOrte = null;
		
		this.recFuhre = 	new RECORD_VPOS_TPA_FUHRE(this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
		this.reclistOrte = 	recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre("NVL(JT_VPOS_TPA_FUHRE_ORT.DELETED,'N')='N'", null, true);
	}
	
	
	
	
	
	
	private void setzeCheckboxWerte_Auf_Maske() throws myException
	{
		E2_ComponentMAP 				oMap = this.EXT().get_oComponentMAP();
		
		/*
		 * 	OHNE_ABRECHNUNG
			KEIN_KONTRAKT_NOETIG
			OHNE_AVV_VERTRAG_CHECK
		 */
		
		if (!bibALL.isEmpty(this.get_ActualWert()))
		{
			Long lWert= this.EXT_DB().get_LActualDBValue(true, true, new Long(0));
			
			RECORD_VPOS_TPA_FUHRE_SONDER oMapSonder = new RECORD_VPOS_TPA_FUHRE_SONDER(lWert.longValue());
			
			((MyE2_DB_CheckBox)oMap.get__Comp("OHNE_ABRECHNUNG")).setSelected(oMapSonder.is_OHNE_ABRECHNUNG_YES());
			((MyE2_DB_CheckBox)oMap.get__Comp("KEIN_KONTRAKT_NOETIG")).setSelected(oMapSonder.is_KEIN_KONTRAKT_NOETIG_YES());
			((MyE2_DB_CheckBox)oMap.get__Comp("OHNE_AVV_VERTRAG_CHECK")).setSelected(oMapSonder.is_OHNE_AVV_VERTRAG_CHECK_YES());

			
			//  ----- das tochterelement raussuchen
			FU_MASK_DAUGHTER_POS_VL oDaughterSonderPos = (FU_MASK_DAUGHTER_POS_VL)oMap.get__Comp(FU___CONST.HASH_KEY_DAUGHTER_POS_VORLAGEN);
			
			// alle eintraege mit der QUELLEN-Angabe SONDER loeschmarkieren (wenn diese noch nicht erzeugt wurden)
			Vector<E2_ComponentMAP>  vAlleMaps = new Vector<E2_ComponentMAP>();
			vAlleMaps.addAll(oDaughterSonderPos.get_oNavigationList().get_vComponentMAPS());
			vAlleMaps.addAll(oDaughterSonderPos.get_oNavigationList().get_vComponentMAPS_NEW());
			
			for (E2_ComponentMAP oMapZeile: vAlleMaps)
			{
				MyE2_DB_SelectField oSelQuelle = (MyE2_DB_SelectField)oMapZeile.get__Comp("QUELLE");
				if (oSelQuelle.get_ActualWert().equals(FU___CONST.RG_VL_QUELLE_VARIANTE_SONDER))
				{
					if (!((MyE2_DB_CheckBox)oMapZeile.get__Comp("WURDE_ERZEUGT")).isSelected())
					{
						((MyE2_ButtonMarkForDelete)oMapZeile.get__Comp("DELETE_SELECTOR")).set_bMarkToDelete(true);
						oMapZeile.set_AllComponentsAsDeleted();
					}
				}
			}

			
			if (oMapSonder.get_DOWN_RECORD_LIST_FUHRE_SONDER_VL_id_vpos_tpa_fuhre_sonder().get_vKeyValues().size()>0)
			{
				
				//jetzt neue eintraege vornehmen (so wie sie in der MAP-Sonder vorliegen
				for (int i=0;i<oMapSonder.get_DOWN_RECORD_LIST_FUHRE_SONDER_VL_id_vpos_tpa_fuhre_sonder().get_vKeyValues().size();i++)
				{
					// String cID_VPOS_RG_VL = oMapSonder.get_DOWN_MAP_FUHRE_SONDER_VL().get_cF_ID_VPOS_RG_VL(cID_FUHRE_SONDER_VL);
					String cID_VPOS_RG_VL = oMapSonder.get_DOWN_RECORD_LIST_FUHRE_SONDER_VL_id_vpos_tpa_fuhre_sonder().get(i).get_ID_VPOS_RG_VL_cF();

					//neue zeile an NaviList
					oDaughterSonderPos.get_oNavigationList().add_Row_MAP_FOR_NEW_INPUT(true, true, true);
					E2_ComponentMAP oMapNEW = oDaughterSonderPos.get_oNavigationList().get_vComponentMAPS_NEW().get(oDaughterSonderPos.get_oNavigationList().get_vComponentMAPS_NEW().size()-1);

					//fuellen
					((MyE2_DB_SelectField)  oMapNEW.get__Comp("ID_VPOS_RG_VL")).set_ActiveValue(cID_VPOS_RG_VL);
					((MyE2_DB_CheckBox) 	oMapNEW.get__Comp("WURDE_ERZEUGT")).setSelected(false);
					((MyE2_DB_SelectField) 	oMapNEW.get__Comp("QUELLE")).set_ActiveValue(FU___CONST.RG_VL_QUELLE_VARIANTE_SONDER);
					
					oDaughterSonderPos.get_oNavigationList().FILL_GRID_From_InternalComponentMAPs(true, true);
					
				}
				
			}
		}
		else
		{
			//dann die standardvorgabe, alle ausnahmen ausschalten
			((MyE2_DB_CheckBox)oMap.get__Comp("OHNE_ABRECHNUNG")).setSelected(false);
			((MyE2_DB_CheckBox)oMap.get__Comp("KEIN_KONTRAKT_NOETIG")).setSelected(false);
			((MyE2_DB_CheckBox)oMap.get__Comp("OHNE_AVV_VERTRAG_CHECK")).setSelected(false);
		}
	}
	

	
	private void setzeMaskeAufAlteWerte() throws myException
	{
		E2_ComponentMAP 				oMap = this.EXT().get_oComponentMAP();
		
		((MyE2_DB_SelectField)oMap.get__Comp(RECORD_VPOS_TPA_FUHRE.FIELD__ID_VPOS_TPA_FUHRE_SONDER)).set_ActiveValue_OR_FirstValue(this.cOLD_ID_VPOS_TPA_FUHRE_SONDER);
		
		//dann die standardvorgabe, alle ausnahmen ausschalten
		((MyE2_DB_CheckBox)oMap.get__Comp(RECORD_VPOS_TPA_FUHRE.FIELD__OHNE_ABRECHNUNG)).setSelected(this.cOLD_OHNE_ABRECHNUNG.equals("Y"));
		((MyE2_DB_CheckBox)oMap.get__Comp(RECORD_VPOS_TPA_FUHRE.FIELD__KEIN_KONTRAKT_NOETIG)).setSelected(this.cOLD_KEIN_KONTRAKT_NOETIG.equals("Y"));
		((MyE2_DB_CheckBox)oMap.get__Comp(RECORD_VPOS_TPA_FUHRE.FIELD__OHNE_AVV_VERTRAG_CHECK)).setSelected(this.cOLD_OHNE_AVV_VERTRAG_CHECK.equals("Y"));;
	}
	

	
	
	private boolean RefreshNaviList_Below(FU_MASK_ComponentMAP  oMAP) throws myException
	{
		E2_BasicModuleContainer_MASK oMaskContainer = oMAP.get_oModulContainerMASK_This_BelongsTo();
		
		boolean bRueck = true;
		
		//jetzt den rufenden Container 
		E2_BasicModuleContainer     oContainerWithNavilist = oMaskContainer.get_vBasicContainerHierarchie().get(oMaskContainer.get_vBasicContainerHierarchie().size()-1);

		if (oContainerWithNavilist != null)
		{
			Vector<E2_NavigationList>  vNaviLists = new E2_RecursiveSearch_NavigationList(oContainerWithNavilist).get_vNavigationLists();
				
			//es muss eine navilist eindeutig gefunden werden
			if (vNaviLists.size()==1)
			{
				E2_NavigationList  oNaviList = vNaviLists.get(0);
					
				SQLFieldMAP  oSQLFieldMap = oNaviList.get_oComponentMAP__REF().get_oSQLFieldMAP();
					
				if (oSQLFieldMap!=null)
				{
					String cLeadingTableOfNaviList = oSQLFieldMap.get_cMAIN_TABLE();
					
					if (cLeadingTableOfNaviList.toUpperCase().equals("JT_VPOS_TPA_FUHRE"))
					{
						oNaviList.Refresh_ComponentMAP(recFuhre.get_ID_VPOS_TPA_FUHRE_cUF(), E2_ComponentMAP.STATUS_VIEW);
					}
					else if (cLeadingTableOfNaviList.toUpperCase().equals("JT_VPOS_TPA"))
					{
						oNaviList.Refresh_ComponentMAP(recFuhre.get_UP_RECORD_VPOS_TPA_id_vpos_tpa().get_ID_VPOS_TPA_cUF(), E2_ComponentMAP.STATUS_VIEW);
					}
					else if (cLeadingTableOfNaviList.toUpperCase().equals("JT_VKOPF_TPA"))
					{
						oNaviList.Refresh_ComponentMAP(recFuhre.get_UP_RECORD_VPOS_TPA_id_vpos_tpa().get_UP_RECORD_VKOPF_TPA_id_vkopf_tpa().get_ID_VKOPF_TPA_cUF(), E2_ComponentMAP.STATUS_VIEW);
					}
					else if (cLeadingTableOfNaviList.toUpperCase().equals("JT_FBAM"))
					{
						//hier muss nichts aktualisiert werden
					}
					else
					{
						bRueck = false;
					}
				}
			}
		}
		
		return bRueck;
	}
	
	
	
	private Vector<String>  get_SQL_SaveStack(FU_MASK_ComponentMAP  oMAP) throws myException
	{
		Vector<String>  vSQL = new Vector<String>();
		
		bibMSG.add_MESSAGE(new E2_SaveMaskStandard(oMAP.get_oModulContainerMASK_This_BelongsTo(),null).doSaveMask_DUMMY(vSQL,true));
		
		String cNewValueFieldFremdwaren = "N";

		if (S.isFull(this.get_ActualWert()))
		{
			RECORD_VPOS_TPA_FUHRE_SONDER recSonder = new RECORD_VPOS_TPA_FUHRE_SONDER(bibALL.ReplaceTeilString(this.get_ActualWert(), ".", ""));
			cNewValueFieldFremdwaren = recSonder.is_OHNE_ABRECHNUNG_YES()?"Y":"N";
		}
		

		for (RECORD_VPOS_TPA_FUHRE_ORT recORT: this.reclistOrte.values())
		{
			if (!recORT.get_OHNE_ABRECHNUNG_cUF_NN("N").equals(cNewValueFieldFremdwaren))
			{
				recORT.set_NEW_VALUE_OHNE_ABRECHNUNG(cNewValueFieldFremdwaren);
				vSQL.add(recORT.get_SQL_UPDATE_STATEMENT(null, true));
			}
			
			//hier zusaetzlich pruefen, ob es sich um ein eigenes Lager im fuhrenort handelt, und ob 
			//der fremdlagerstatus dazu passt
			bibMSG.add_MESSAGE(new FU__PRUEFUNG_FREMDWARE_AUF_FREMDLAGER(
						recORT.get_ID_ADRESSE_lValue(-1l), 
						recORT.get_ID_ADRESSE_LAGER_lValue(-1l), 
						cNewValueFieldFremdwaren.equals("Y"),
						"Speicherung Fuhrenort: Fehler: ",
						recORT.get_DEF_QUELLE_ZIEL_cUF_NN("").equals("EK"),
						recORT.get_ID_VPOS_TPA_FUHRE_SONDER_lValue(-1l)).get_oMV_Rueck());
			
			
		}
		
		return vSQL;
	}
	
}
