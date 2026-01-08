package rohstoff.Echo2BusinessLogic.BEWEGUNG.ABNAHMEANGEBOTELISTE;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList_StandardLayoutFactory;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_ComboBoxErsatz;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_STD;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ListButton_OpenAdressMASK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SubQueryAgentShowDelGrundOncheckBox;

public class BSAAL_ComponentMAP_List extends E2_ComponentMAP {

	private E2_NavigationList  	oNaviLIST = null;
	
	public BSAAL_ComponentMAP_List(	E2_NavigationList		 	oNaviList,
									BSAAL__ModulContainerLIST 	oModulContainer) throws myException 
	{
		super(new BSAAL_SQLFieldMAP());
		
		this.oNaviLIST = oNaviList;
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		
		E2_CheckBoxForList oCB = new E2_CheckBoxForList();
		oCB.setFocusTraversalParticipant(false);
		
		this.add_Component("CHECK_BOX",oCB,new MyE2_String("?"));
		this.add_Component("MARKER",new E2_ButtonListMarker(),new MyE2_String("?"));

		//2015-12-14: kundeninfo-button
		this.add_Component(new BSAAL_List_BUTTON_INFO(oFM.get_(BSAAL__CONST.H_ID_VPOS_STD)), new MyE2_String("I"));
		this.add_Component(BSAAL__CONST.SONDERSPALTEN.WWW_BUTTON.name(),new BSAAL_LIST_comp_showWebsites(), new MyE2_String("WWW"));
		
		this.add_Component(BSAAL__CONST.HASH_KEY_ANZEIGE_SYMBOL,new MyE2_Label(BSAAL__CONST.LABEL_EMPTY),new MyE2_String("G"));
		this.add_Component(BSAAL__CONST.HASH_KEY_ANZEIGE_LOCKED,new MyE2_Label(BSAAL__CONST.LABEL_EMPTY),new MyE2_String("A"));

		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("K_BUCHUNGSNUMMER")),	new MyE2_String("BelegNr"),true,false,
															E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOPHeader(),
															E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOP());


		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("K_NAME1")),			new MyE2_String("Name 1 (Angeb.Kopf)"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("K_NAME2")),			new MyE2_String("Name 2 (Angeb.Kopf)"));
		this.add_Component((BSAAL_listCompLieferant)new BSAAL_listCompLieferant(this.oNaviLIST)._setWidth(250));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("K_STRASSE")),			new MyE2_String("Strasse"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("K_ORT")),				new MyE2_String("Ort Lieferant"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("U_KUERZEL")),			new MyE2_String("K"));
		this.add_Component(new BSAAL_ButtonChangeGueltigkeit_IN_LIST(oFM.get_("G_DATUMSBEREICH"),oModulContainer),	new MyE2_String("Gültigkeit"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ANR1")),				new MyE2_String("ANR1"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ANR2")),				new MyE2_String("-2"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ARTBEZ1")),			new MyE2_String("Artikelbez.1"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ARTBEZ2")),			new MyE2_String("Artikelbez.2"));
		
		this.add_Component(new MyE2_DB_ComboBoxErsatz(oFM.get_("LIEFERBEDINGUNGEN"),"SELECT KURZBEZEICHNUNG a,KURZBEZEICHNUNG b FROM "+bibE2.cTO()+".JT_LIEFERBEDINGUNGEN ORDER BY KURZBEZEICHNUNG", false),
				new MyE2_String("Lieferbedingungen"));

		
		//NEU_09  zusatz-Zahlungsbedingungen
		this.add_Component(new MyE2_DB_ComboBoxErsatz(oFM.get_("ZAHLUNGSBEDINGUNGEN"),"SELECT KURZBEZEICHNUNG a,KURZBEZEICHNUNG b FROM "+bibE2.cTO()+".JT_ZAHLUNGSBEDINGUNGEN ORDER BY KURZBEZEICHNUNG", false),
				new MyE2_String("Zahlungsbedingungen"));
		
		this.add_Component(BSAAL__CONST.HASH_KEY_ANZEIGE_VORMONAT,new MyE2_Label("--"),new MyE2_String("Vormonat"));
		
		this.add_Component(new MyE2_DB_TextField(oFM.get_("EINZELPREIS")), new MyE2_String("Einzelpreis"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("A_LIEF_NR_TEIL")),	new MyE2_String("Lief-Nr(alt)"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("K_ID_VKOPF_STD")),	new MyE2_String("ID(Kopf)"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_VPOS_STD")),		new MyE2_String("ID(Pos)"));
		this.add_Component(new BS_ListButton_OpenAdressMASK(oFM.get_("K_ID_ADRESSE")),		new MyE2_String("ID(Adresse)"));

		
		// spaltenbreiten
		this.get__Comp(BSAAL__CONST.HASH_KEY_ANZEIGE_SYMBOL).EXT().set_oColExtent(new Extent(30));
		this.get__Comp(BSAAL__CONST.HASH_KEY_ANZEIGE_LOCKED).EXT().set_oColExtent(new Extent(30));
		this.get__Comp("K_NAME1").EXT().set_oColExtent(new Extent(150));
		this.get__Comp("K_NAME2").EXT().set_oColExtent(new Extent(150));
		this.get__Comp("K_STRASSE").EXT().set_oColExtent(new Extent(150));
		this.get__Comp("K_ORT").EXT().set_oColExtent(new Extent(160));
		this.get__Comp("U_KUERZEL").EXT().set_oColExtent(new Extent(30));
		this.get__Comp("G_DATUMSBEREICH").EXT().set_oColExtent(new Extent(110));
		this.get__Comp("ANR1").EXT().set_oColExtent(new Extent(50));
		this.get__Comp("ANR2").EXT().set_oColExtent(new Extent(50));
		this.get__Comp("ARTBEZ1").EXT().set_oColExtent(new Extent(150));
		this.get__Comp(BSAAL__CONST.HASH_KEY_ANZEIGE_VORMONAT).EXT().set_oColExtent(new Extent(100));
		this.get__Comp("EINZELPREIS").EXT().set_oColExtent(new Extent(100));
		this.get__Comp("A_LIEF_NR_TEIL").EXT().set_oColExtent(new Extent(100));
		this.get__Comp("K_ID_VKOPF_STD").EXT().set_oColExtent(new Extent(80));
		this.get__Comp("ID_VPOS_STD").EXT().set_oColExtent(new Extent(80));
		this.get__Comp("K_ID_ADRESSE").EXT().set_oColExtent(new Extent(80));
		
		// sichtbarkeit
		this.get__Comp("K_NAME2").EXT().set_bIsVisibleInList(false);
		this.get__Comp("K_STRASSE").EXT().set_bIsVisibleInList(false);
		this.get__Comp("K_ID_VKOPF_STD").EXT().set_bIsVisibleInList(false);
		this.get__Comp("ID_VPOS_STD").EXT().set_bIsVisibleInList(false);
		this.get__Comp("K_ID_ADRESSE").EXT().set_bIsVisibleInList(false);
		this.get__Comp("A_LIEF_NR_TEIL").EXT().set_bIsVisibleInList(false);
		
		// weitere
		((MyE2_DB_TextField)this.get__Comp("EINZELPREIS")).setAlignment(new Alignment(Alignment.RIGHT,Alignment.DEFAULT));
		((MyE2_DB_TextField)this.get__Comp("EINZELPREIS")).set_iWidthPixel(120);
		this.get__Comp("EINZELPREIS").EXT().set_oLayout_ListTitelElement(E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOPHeader());
		
		this.get__Comp(BSAAL__CONST.HASH_KEY_ANZEIGE_VORMONAT).EXT().set_oLayout_ListElement(E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOP());
		this.get__Comp(BSAAL__CONST.HASH_KEY_ANZEIGE_VORMONAT).EXT().set_oLayout_ListTitelElement(E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOPHeader());
		
		
		// tabulator nur auf textfeld
		((MyE2_DB_ComboBoxErsatz)this.get__Comp("LIEFERBEDINGUNGEN")).get_oPopUp().setFocusTraversalParticipant(false);
		((MyE2_DB_ComboBoxErsatz)this.get__Comp("LIEFERBEDINGUNGEN")).get_oTextField().setFocusTraversalParticipant(false);
		
		// lieferbedingungen schmaler machen
		((MyE2_DB_ComboBoxErsatz)this.get__Comp("LIEFERBEDINGUNGEN")).get_oTextField().set_iWidthPixel(100);
		((MyE2_DB_ComboBoxErsatz)this.get__Comp("LIEFERBEDINGUNGEN")).get_oPopUp().setPopUpLeftOffset(-100);
		
		
		//NEU_09
		// tabulator nur auf textfeld
		((MyE2_DB_ComboBoxErsatz)this.get__Comp("ZAHLUNGSBEDINGUNGEN")).get_oPopUp().setFocusTraversalParticipant(false);
		((MyE2_DB_ComboBoxErsatz)this.get__Comp("ZAHLUNGSBEDINGUNGEN")).get_oTextField().setFocusTraversalParticipant(false);
		
		// lieferbedingungen schmaler machen
		((MyE2_DB_ComboBoxErsatz)this.get__Comp("ZAHLUNGSBEDINGUNGEN")).get_oTextField().set_iWidthPixel(100);
		((MyE2_DB_ComboBoxErsatz)this.get__Comp("ZAHLUNGSBEDINGUNGEN")).get_oPopUp().setPopUpLeftOffset(-100);
		
		((MyE2_DB_ComboBoxErsatz)this.get__Comp("ZAHLUNGSBEDINGUNGEN")).EXT().set_bIsVisibleInList(false);
		//NEU_09
		
		// sortierung ausschalten
		Vector<MyE2IF__Component> vRealComponents = this.get_REAL_ComponentVector();
		for (int i=0;i<vRealComponents.size();i++)
			if (vRealComponents.get(i) instanceof MyE2IF__DB_Component)
				((MyE2IF__DB_Component)vRealComponents.get(i)).EXT_DB().set_bIsSortable(false);
		
		// einzelne wieder einschalten
		((MyE2IF__DB_Component)this.get__Comp("K_ORT")).EXT_DB().set_bIsSortable(true);
		((MyE2IF__DB_Component)this.get__Comp("ANR1")).EXT_DB().set_bIsSortable(true);
		((MyE2IF__DB_Component)this.get__Comp("ARTBEZ1")).EXT_DB().set_bIsSortable(true);
		
		// zwei spezialsorter einbauen
		((MyE2IF__Component)this.get("K_NAME1")).EXT().set_oCompTitleInList(new SortName());
		((MyE2IF__Component)this.get("A_LIEF_NR_TEIL")).EXT().set_oCompTitleInList(new SortLIEF_NR());
		
		//2019-04-11: sortierung der gruppierungsspalte
		((MyE2IF__Component)this.get(BSAAL__CONST.HASH_KEY_ANZEIGE_SYMBOL)).EXT().set_oCompTitleInList(new SortSymbol());
		
		
		this.set_oSubQueryAgent(new BSAAL_MarkerSubQueryAgent(oNaviList));
		this.add_oSubQueryAgent(new BS__SubQueryAgentShowDelGrundOncheckBox());
		
		//gefaehrliche sorten rot hinterlegen
		this.add_oSubQueryAgent(new subQueryAgentShowGefahr());

	}

	

	/**
	 * sortierung im Symbol-Button
	 */
	public class SortSymbol extends MyE2_Button
	{

		public SortSymbol() throws myException 		{
			super(new MyE2_String("Grp"));
			
			this._ttt(S.ms("Sortierung für die Angebotsklammern"));
			
			this.add_oActionAgent(new XX_ActionAgent()	{
				public void executeAgentCode(ExecINFO oExecInfo){
					BSAAL_ComponentMAP_List 	oThis = BSAAL_ComponentMAP_List.this;
					
					// sortierung festlegen
					oThis.get_oSQLFieldMAP().clear_ORDER_SEGMENT();
					oThis.get_oSQLFieldMAP().add_ORDER_SEGMENT("JT_VKOPF_STD.NAME1");
					oThis.get_oSQLFieldMAP().add_ORDER_SEGMENT("JT_VKOPF_STD.ORT");
					oThis.get_oSQLFieldMAP().add_ORDER_SEGMENT("JT_VKOPF_STD.ID_VKOPF_STD");
					oThis.get_oSQLFieldMAP().add_ORDER_SEGMENT("JT_VPOS_STD.ANR1");
					oThis.get_oSQLFieldMAP().add_ORDER_SEGMENT("JT_VPOS_STD.ANR2");
							
					try	{
						oThis.oNaviLIST.ResetSortButtons();
						oThis.oNaviLIST._REBUILD_COMPLETE_LIST("");
					} catch (myException ex){
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Neu-Aufbau der Liste !"));
					}
				}
			});
		}
	}

	
	
	public class SortName extends MyE2_Button
	{

		public SortName()
		{
			super(new MyE2_String("Name1 (Angeb.Kopf)"));

			this.add_oActionAgent(new XX_ActionAgent()
			{
				public void executeAgentCode(ExecINFO oExecInfo)
				{
					BSAAL_ComponentMAP_List 	oThis = BSAAL_ComponentMAP_List.this;
					
					// sortierung festlegen
					oThis.get_oSQLFieldMAP().clear_ORDER_SEGMENT();
					oThis.get_oSQLFieldMAP().add_ORDER_SEGMENT("JT_VKOPF_STD.NAME1");
					oThis.get_oSQLFieldMAP().add_ORDER_SEGMENT("JT_VKOPF_STD.ORT");
					oThis.get_oSQLFieldMAP().add_ORDER_SEGMENT("JT_VKOPF_STD.ID_VKOPF_STD");
					oThis.get_oSQLFieldMAP().add_ORDER_SEGMENT("JT_VPOS_STD.ANR1");
					oThis.get_oSQLFieldMAP().add_ORDER_SEGMENT("JT_VPOS_STD.ANR2");
							
					try
					{
						oThis.oNaviLIST.ResetSortButtons();
						oThis.oNaviLIST._REBUILD_COMPLETE_LIST("");
					}
					catch (myException ex)
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Neu-Aufbau der Liste !"));
					}
					
					
				}
				
			});
		}
	}


	private class SortLIEF_NR extends MyE2_Button
	{

		public SortLIEF_NR()
		{
			super(new MyE2_String("Lief-Nr (Alt)"));
				
			this.add_oActionAgent(new XX_ActionAgent()
			{
				public void executeAgentCode(ExecINFO oExecInfo)
				{
					BSAAL_ComponentMAP_List 	oThis = BSAAL_ComponentMAP_List.this;
	
					// sortierung festlegen
					oThis.get_oSQLFieldMAP().clear_ORDER_SEGMENT();
					oThis.get_oSQLFieldMAP().add_ORDER_SEGMENT("JT_ADRESSE.LIEF_NR");
					oThis.get_oSQLFieldMAP().add_ORDER_SEGMENT("JT_VKOPF_STD.ORT");
					oThis.get_oSQLFieldMAP().add_ORDER_SEGMENT("JT_VKOPF_STD.ID_VKOPF_STD");
					oThis.get_oSQLFieldMAP().add_ORDER_SEGMENT("JT_VPOS_STD.ANR1");
					oThis.get_oSQLFieldMAP().add_ORDER_SEGMENT("JT_VPOS_STD.ANR2");
					try
					{
						oThis.oNaviLIST.ResetSortButtons();
						oThis.oNaviLIST._REBUILD_COMPLETE_LIST("");
					}
					catch (myException ex)
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("AAL_ModulContainerLIST:Sortname:Fehler beim Neu-Aufbau der Liste !"));
					}

				}
			});
		}
		
	}


	private class subQueryAgentShowGefahr extends XX_ComponentMAP_SubqueryAGENT
	{

		@Override
		public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
		{
		}

		@Override
		public void fillComponents(E2_ComponentMAP oMAP,SQLResultMAP oUsedResultMAP) throws myException 
		{
			int ID_ARTIKEL_BEZ = oUsedResultMAP.get_bdActualValue("ID_ARTIKEL_BEZ", false).intValue();
			
			GridLayoutData  oGL_Norm = E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutLeftTOP();
			GridLayoutData  oGL_RED  = LayoutDataFactory.get_GL_Copy(oGL_Norm, new E2_ColorAlarm());
			
			
			RECORD_VPOS_STD  recSTD = new RECORD_VPOS_STD(oUsedResultMAP.get_cUNFormatedROW_ID());
			
			boolean bMarkAsGefahr = false;
			
			RECLIST_ARTIKELBEZ_LIEF reclistArtbezLief =  
				recSTD.get_UP_RECORD_VKOPF_STD_id_vkopf_std().get_UP_RECORD_ADRESSE_id_adresse().get_DOWN_RECORD_LIST_ARTIKELBEZ_LIEF_id_adresse("ID_ARTIKEL_BEZ="+ID_ARTIKEL_BEZ,null,true);
			
			if (reclistArtbezLief.get_vKeyValues().size()==0)
			{
				bMarkAsGefahr = true;
			}
			else
			{
				RECORD_ARTIKELBEZ_LIEF recArtbezLief = reclistArtbezLief.get(0);
				
				if (recArtbezLief.get_UP_RECORD_EAK_CODE_id_eak_code()==null)
				{
					bMarkAsGefahr = true;
				}
				else
				{
					bMarkAsGefahr=recArtbezLief.get_UP_RECORD_EAK_CODE_id_eak_code().is_GEFAEHRLICH_YES();
				}
				
			}

			MyE2_DB_Label_INROW  ANR1 = (MyE2_DB_Label_INROW)oMAP.get_Comp("ANR1");

			ANR1.EXT().set_oLayout_ListElement(oGL_Norm);
			ANR1.get_oRowContainer().setLayoutData(oGL_Norm);

			if (bMarkAsGefahr)
			{
				ANR1.EXT().set_oLayout_ListElement(oGL_RED);
				ANR1.get_oRowContainer().setLayoutData(oGL_RED);
			}
		}
	}
}
