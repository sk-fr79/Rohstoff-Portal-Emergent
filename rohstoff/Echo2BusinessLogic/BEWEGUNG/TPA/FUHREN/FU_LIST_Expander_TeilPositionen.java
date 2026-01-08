package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import panter.gmbh.BasicInterfaces.Service.PdServiceCheckGelangensbestaetigungIsValid;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.ListAndMask.XX_List_EXPANDER_4_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.EnumMessageType;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Grid_InLIST;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.Triple;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibServer;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VEK;
import panter.gmbh.indep.myVectors.bibVECTOR;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.ENUM_BEWGUNGSATZ_TYP;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.Warenbewegung_InfoBlockFremdWaehrung;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.__FU_FUO_gridWithAH7_elements;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_VPOS_TPA_FUHRE_EXT;

public class FU_LIST_Expander_TeilPositionen extends  XX_List_EXPANDER_4_ComponentMAP
{
	private MyE2_Row	oRow = 						new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
	private String 		cID_VPOS_TPA_FUHRE = 		null;

	
	//2014-02-18: record veroeffentlicht
	private RECORD_VPOS_TPA_FUHRE_EXT  		recFuhreTOP  = 			null;

	
	public FU_LIST_Expander_TeilPositionen(E2_NavigationList NavigationList) 	throws myException
	{
		super(NavigationList);
		this.set_iLeftColumnOffset(6);
	}

	@Override
	public Component get_ComponentDaughter(String ID_VPOS_TPA_FUHRE)	throws myException
	{
		this.cID_VPOS_TPA_FUHRE = ID_VPOS_TPA_FUHRE;
		this.fill_Component();
		return this.oRow;
	}

	
	private void fill_Component() throws myException
	{
		this.oRow.removeAll();
		
		MyE2_Grid oGridHelpAussen = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		RECORD_VPOS_TPA_FUHRE  		recFuhre = 			new RECORD_VPOS_TPA_FUHRE(this.cID_VPOS_TPA_FUHRE);
		this.recFuhreTOP = new RECORD_VPOS_TPA_FUHRE_EXT(recFuhre);
		
		RECLIST_VPOS_TPA_FUHRE_ORT  recListFUO_EK = 	recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre("DEF_QUELLE_ZIEL='EK' AND NVL(DELETED,'N')='N'","ID_VPOS_TPA_FUHRE_ORT", true);
		RECLIST_VPOS_TPA_FUHRE_ORT  recListFUO_VK = 	recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre("DEF_QUELLE_ZIEL='VK' AND NVL(DELETED,'N')='N'","ID_VPOS_TPA_FUHRE_ORT", true);
		
		oGridHelpAussen.add(get_Grid(recListFUO_EK,true), MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I_0_0_10_2));
		oGridHelpAussen.add(get_Grid(recListFUO_VK,false),MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I_0_0_0_2));
		this.oRow.add(oGridHelpAussen);
	}
	
	
	private MyE2_Grid get_Grid(RECLIST_VPOS_TPA_FUHRE_ORT recListFUO, boolean bEK) throws myException
	{
		MyE2_Grid  oGrid = new MyE2_Grid(bEK?9:10,MyE2_Grid.STYLE_GRID_DDARK_BORDER_INSETS_11());
		oGrid.add(new MyE2_Label("BAM"),																								MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2));
		oGrid.add(new MyE2_Label(bEK?new MyE2_String("Ladeort"):new MyE2_String("Abladeort"),MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),	MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2));
		oGrid.add(new MyE2_Label(new MyE2_String("PlanM"), MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),										MyE2_Grid.LAYOUT_RIGHT(E2_INSETS.I_2_2_2_2));
		oGrid.add(new MyE2_Label(bEK?new MyE2_String("LadeM"):new MyE2_String("AblM"),MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),			MyE2_Grid.LAYOUT_RIGHT(E2_INSETS.I_2_2_2_2));
		oGrid.add(new MyE2_Label(new MyE2_String("Code"), MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),										MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2));
		oGrid.add(new MyE2_Label(new MyE2_String("Sortenbez."), MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),									MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2));
//2011-11-18: postennummer
		oGrid.add(new MyE2_Label(new MyE2_String("PostenNr."), MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),								MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2));
//2014-06-05: lieferbedingung
		oGrid.add(new MyE2_Label(new MyE2_String("Lieferbed."), MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),								MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2));
		
		//2014-02-18: gelangensbestaetigung
		if (!bEK) {
			oGrid.add(new MyE2_Label(new MyE2_String("GB"), MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),								MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2));
		}
		
//2013-09-23: wiegekarte
		oGrid.add(new MyE2_Label(new MyE2_String("Wiegekarte"), MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),								MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2));
		
		int widthCombiField = 60;
		if (this.is_Anhang7Visible()) {
			widthCombiField+=20;
		}
		if (this.is_WaehrungVisible()) {
			widthCombiField+=35;
		}

		oGrid.setColumnWidth(0, new Extent(widthCombiField));
		oGrid.setColumnWidth(1, new Extent(100));
		oGrid.setColumnWidth(2, new Extent(50));
		oGrid.setColumnWidth(3, new Extent(50));
		oGrid.setColumnWidth(4, new Extent(70));
		oGrid.setColumnWidth(5, new Extent(120));
		oGrid.setColumnWidth(6, new Extent(100));
		oGrid.setColumnWidth(7, new Extent(100));  //lieferbed
		if (bEK) {
			oGrid.setColumnWidth(8, new Extent(100));
		} else {
			oGrid.setColumnWidth(8, new Extent(10));
			oGrid.setColumnWidth(9, new Extent(100));
		}
		
		for (int i=0;i<recListFUO.get_vKeyValues().size();i++)
		{
			RECORD_VPOS_TPA_FUHRE_ORT recOrt = recListFUO.get(i);

			String cPlan = MyNumberFormater.formatDez(recOrt.get_ANTEIL_PLANMENGE_bdValue(BigDecimal.ZERO), 0, true);
			if (cPlan.equals("0")) cPlan = "-";
			
			String cLadeAblade = bEK?MyNumberFormater.formatDez(recOrt.get_ANTEIL_LADEMENGE_bdValue(BigDecimal.ZERO), 0, true):
									MyNumberFormater.formatDez(recOrt.get_ANTEIL_ABLADEMENGE_bdValue(BigDecimal.ZERO), 0, true);
			if (cLadeAblade.equals("0")) cLadeAblade = "-";
			
			
			//2015-06-06: 	alt: oGrid.add(new FU_LIST_BT_OPEN_BAM(null,recOrt.get_ID_VPOS_TPA_FUHRE_ORT_cUF(),this.get_oNavigationList()));
			//neu:
			oGrid.add(new ownBamAndBefundungsGrid(recOrt,this.get_oNavigationList()));
			oGrid.add(new MyE2_Label(recOrt.get___KETTE(bibALL.get_Vector("NAME1", "ORT")),		MyE2_Label.STYLE_SMALL_PLAIN(),true),MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2));
			oGrid.add(new MyE2_Label(cPlan, 													MyE2_Label.STYLE_SMALL_PLAIN()),MyE2_Grid.LAYOUT_RIGHT(E2_INSETS.I_2_2_2_2));
			oGrid.add(new MyE2_Label(cLadeAblade,  												MyE2_Label.STYLE_SMALL_PLAIN()),MyE2_Grid.LAYOUT_RIGHT(E2_INSETS.I_2_2_2_2));
			oGrid.add(new MyE2_Label(recOrt.get___KETTE(bibALL.get_Vector("ANR1", "ANR2")),		MyE2_Label.STYLE_SMALL_PLAIN(),true),MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2));
			oGrid.add(new MyE2_Label(recOrt.get_ARTBEZ1_cUF_NN(""), 							MyE2_Label.STYLE_SMALL_PLAIN(),true),MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2));
			//2011-11-18: postennummer			
			oGrid.add(new FU_LIST_Expander_TeilPositionen_BT_EDIT_Postennummer(recOrt),			MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2));
			//2014-06-05: lieferbedingung
			oGrid.add(new FU_LIST_Expander_Teilpositionen_BT_EditLieferbedingung(recOrt,
												this.get_E2_ComponentMAP_this_BelongsTo()),		MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2));
			
			//2014-02-18: gelangensbestaetigung
			if (!bEK) {
				oGrid.add(new CB_GelangensbestaetigungAnAus(recOrt),							MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2));
			}
			
			//2013-09-23: wiegekarte
			oGrid.add(new FU_LIST_Expander_TeilPositionen_BT_EDIT_WIEGEKARTE(recOrt,this.get_E2_ComponentMAP_this_BelongsTo()),			
																								MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2));
		}
		
		return oGrid;
	}
	
	
	
	//2015-06-06: statt des bam-buttons wird ein grid eingebaug
	private class ownBamAndBefundungsGrid extends MyE2_Grid_InLIST {

		public ownBamAndBefundungsGrid(RECORD_VPOS_TPA_FUHRE_ORT record_ort, E2_NavigationList naviList) throws myException {
			super(1, MyE2_Grid_InLIST.STYLE_GRID_NO_BORDER_NO_INSETS());
			
			FU_LIST_Expander_TeilPositionen oThis = FU_LIST_Expander_TeilPositionen.this;
			
			this.add(new FU_LIST_BT_OPEN_BAM(null,record_ort.get_ID_VPOS_TPA_FUHRE_ORT_cUF(),naviList),E2_INSETS.I(0,0,0,0));
			if (oThis.is_BefundungVisible()) {
				this.setSize(this.getSize()+1);
				
				__FU_FUO_COL_ANHAENGE_BEFUNDUNGEN befundungsElemente = new __FU_FUO_COL_ANHAENGE_BEFUNDUNGEN(null,
																			ENUM_BEWGUNGSATZ_TYP.JT_VPOS_TPA_FUHRE_ORT, naviList);
				befundungsElemente.set_cActual_Formated_DBContent_To_Mask(record_ort.get_ID_VPOS_TPA_FUHRE_ORT_cUF(),null,null);
				this.add(befundungsElemente, E2_INSETS.I(2,0,0,0));
			}
			
			if (oThis.is_Anhang7Visible()) {
				this.setSize(this.getSize()+1);
				Rec21 rFuhre = new Rec21(_TAB.vpos_tpa_fuhre)._fill_id(record_ort.get_ID_VPOS_TPA_FUHRE_lValue(0l));
				Long id_adresse_start_geo = null;
				Long id_adresse_ziel_geo = null;
				boolean isAH7needed = record_ort.is_PRINT_EU_AMTSBLATT_YES();
				if (record_ort.get_DEF_QUELLE_ZIEL_cUF().equals("EK")) {
					id_adresse_start_geo = 	record_ort.get_ID_ADRESSE_LAGER_lValue(0l);
					id_adresse_ziel_geo  = 	rFuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_lager_ziel,0L);
				} else {
					id_adresse_start_geo  = rFuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_lager_start,0L);
					id_adresse_ziel_geo = 	record_ort.get_ID_ADRESSE_LAGER_lValue(0l);
				}
				
				__FU_FUO_gridWithAH7_elements grid = new __FU_FUO_gridWithAH7_elements(isAH7needed,id_adresse_start_geo,id_adresse_ziel_geo,new ActionRefresh());
				
				this.add(grid, E2_INSETS.I(2,0,0,0));
			}
			
			if (oThis.is_WaehrungVisible()) {
				this.setSize(this.getSize()+1);
				this.add(new OwnWaehrungsAnzeiger(record_ort));
			}
			
		}
		
		private class ActionRefresh extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				FU_LIST_Expander_TeilPositionen.this.refreshDaughterComponent();
			}
		}
	}
	
	
	
	//2015-06-03: ueberschreiben der positions-getter-methode
	public int get_iLeftColumnOffset()
	{
//		try
//		{
//			if (this.is_BefundungVisible()) {
//				return 6;
//			}
//		}
//		catch (myException e)
//		{
//			e.printStackTrace();
//		}
		//in der componentmap feststellen, an welcher spalte der ausklapper steht
		try {
			if (this.get_oNavigationList().get_oComponentMAP__REF()!=null) {   //wirkt erst bei kopien
				int pos = 0;
				for (String s : this.get_oNavigationList().get_oComponentMAP__REF().get_vComponentHashKeys()) {
					if (s.equals(FU_LIST_ModulContainer.NAME_OF_EXTENDER_ORTE)) {
						break;
					}
					if (this.get_oNavigationList().get_oComponentMAP__REF().get(s).EXT().get_bIsVisibleInList()) {
						pos++;
					}
				}
				
				if (pos>10) {pos=10;}
				if (pos<3) {pos=3;}
				
				return pos;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 

	
		
		
		
		return 5;
	}

	
	private boolean is_BefundungVisible() throws myException {
		if (this.get_oNavigationList().get_oComponentMAP__REF().get__Comp(FU___CONST.FIELDNAME_ID_VPOS_TPA_FUHRE_3).EXT().get_bIsVisibleInList()) {
			return true;
		}
		return false;
	}
	
	private boolean is_Anhang7Visible() throws myException {
		if (this.get_oNavigationList().get_oComponentMAP__REF().get__Comp(FU_LIST_ModulContainer.NAME_OF_AH7_COL).EXT().get_bIsVisibleInList()) {
			return true;
		}
		return false;
	}

	
	private boolean is_WaehrungVisible() throws myException {
		if (this.get_oNavigationList().get_oComponentMAP__REF().get__Comp(FU_LIST_ModulContainer.NAME_OF_WAEHRUNG_ANZEIGE).EXT().get_bIsVisibleInList()) {
			return true;
		}
		return false;
	}


	
	@Override
	public ArrayList<HashMap<String, Component>> get_ComponentListDaughter(String unformated) throws myException
	{
		return null;
	}

	@Override
	public void refreshDaughterComponent() throws myException
	{
		this.fill_Component();
	}

	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		FU_LIST_Expander_TeilPositionen oRueck;
		try
		{
			oRueck = new FU_LIST_Expander_TeilPositionen(this.get_oNavigationList());
		}
		catch (myException e)
		{
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
		
		return oRueck;
	}

	
	
	//-----------------------------------------------
	private class CB_GelangensbestaetigungAnAus extends MyE2_CheckBox	{

		private RECORD_VPOS_TPA_FUHRE_ORT recORT = null;
		
		public CB_GelangensbestaetigungAnAus(RECORD_VPOS_TPA_FUHRE_ORT rec_ORT) throws myException
		{
			super();
			this.recORT = rec_ORT;
			this.setSelected(this.recORT.is_GELANGENSBESTAETIGUNG_ERHALTEN_YES());
			this.add_oActionAgent(this.get_ToggleAction());
			this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO(FU___CONST.VALIDKEY_GELANGENSBESTAETIGUNG_ERHALTEN));
			
			this.setToolTipText(bibServer.get_TooltipInfosFromDBDescription(_DB.VPOS_TPA_FUHRE_ORT, _DB.VPOS_TPA_FUHRE_ORT$GELANGENSBESTAETIGUNG_ERHALTEN));

			
		}


		public XX_ActionAgent get_ToggleAction() throws myException
		{
			
			return new XX_ActionAgent()
			{
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException
				{
					FU_LIST_Expander_TeilPositionen ooThis = 	FU_LIST_Expander_TeilPositionen.this;
					CB_GelangensbestaetigungAnAus 	oThis = 	CB_GelangensbestaetigungAnAus.this;

					boolean oldValue = recORT.is_GELANGENSBESTAETIGUNG_ERHALTEN_YES();
					boolean newValue = oThis.isSelected();
					
					if (recORT.get_DEF_QUELLE_ZIEL_cUF_NN("EK").equals("EK")) {
						bibMSG.MV()._addAlarm(PdServiceCheckGelangensbestaetigungIsValid.EnumGelangensbestaetigungStatus.GELANG_BEST_NOT_ALLOWED_FUHRENORT_IST_QUELLE.getMessage());
						oThis.setSelected(false);
					} else {
						boolean isFremdWare = recORT.is_OHNE_ABRECHNUNG_YES() || recFuhreTOP.get_bFremdwarenFuhre();
						
						PdServiceCheckGelangensbestaetigungIsValid.EnumGelangensbestaetigungStatus statusGelangensBestaetigungAllowed = 
								new PdServiceCheckGelangensbestaetigungIsValid().getStatusValidierung(
										 recORT.get_ID_ADRESSE_LAGER_lValue(-1l)
										,isFremdWare
										,recFuhreTOP.get_TP_VERANTWORTUNG_cUF_NN(""));
						
						if (statusGelangensBestaetigungAllowed.getMessageType()==EnumMessageType.ALARM) {
							//der alte status wiederherstellen, checkbox wie vorher
							oThis.setSelected(oldValue);
							bibMSG.MV()._addAlarm(statusGelangensBestaetigungAllowed.getMessage());
						} else {
							MyE2_MessageVector oMV_OK = bibDB.ExecMultiSQLVector(bibVECTOR.get_Vector("UPDATE "+bibE2.cTO()+"."+_DB.VPOS_TPA_FUHRE_ORT+" SET "+
									_DB.VPOS_TPA_FUHRE_ORT$GELANGENSBESTAETIGUNG_ERHALTEN+"="+
									(newValue?"'Y'":"'N'")+" WHERE "+_DB.VPOS_TPA_FUHRE_ORT$ID_VPOS_TPA_FUHRE_ORT+"="+recORT.get_ID_VPOS_TPA_FUHRE_ORT_cUF()), 
									true);
							if (oMV_OK.isOK()) {
								oThis.setSelected(newValue);
								bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Erhalt der Gelangesbestätigung wurde auf "+(newValue?"<ERHALTEN>":"<FEHLT>")+" gesetzt!")));
								if (statusGelangensBestaetigungAllowed.getMessageType()==EnumMessageType.WARNING && newValue) {
									bibMSG.MV()._addWarn(statusGelangensBestaetigungAllowed.getMessage());
								}
							} else {
								oThis.setSelected(oldValue);
								bibMSG.add_MESSAGE(oMV_OK);
							}
						}
					}

					
//					if (!ooThis.recFuhreTOP.get_bFremdwarenFuhre()) {
//					
//						//jetzt pruefen, ob der schalter sinnvoll ist
//						if (oThis.recORT.get_ID_ADRESSE_lValue(0l).intValue()!=bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_lValue(-1l).intValue()) {
//							
//							if (oThis.recORT.get_UP_RECORD_ADRESSE_id_adresse_lager()!=null && 
//								oThis.recORT.get_UP_RECORD_ADRESSE_id_adresse_lager().get_UP_RECORD_LAND_id_land()!=null &&
//								oThis.recORT.get_UP_RECORD_ADRESSE_id_adresse_lager().get_UP_RECORD_LAND_id_land().is_INTRASTAT_JN_YES() &&
//								oThis.recORT.get_UP_RECORD_ADRESSE_id_adresse_lager().get_UP_RECORD_LAND_id_land().get_ID_LAND_lValue(-1l).intValue()!=
//									bibALL.get_RECORD_MANDANT().get_ID_LAND_lValue(-1l).intValue()) {
//								
//								boolean bNew = !oThis.recORT.is_GELANGENSBESTAETIGUNG_ERHALTEN_YES();
//								
//								MyE2_MessageVector oMV_OK = bibDB.ExecMultiSQLVector(bibVECTOR.get_Vector("UPDATE "+bibE2.cTO()+"."+_DB.VPOS_TPA_FUHRE_ORT+" SET "+
//																				_DB.VPOS_TPA_FUHRE_ORT$GELANGENSBESTAETIGUNG_ERHALTEN+"="+
//																				(bNew?"'Y'":"'N'")+" WHERE "+_DB.VPOS_TPA_FUHRE_ORT$ID_VPOS_TPA_FUHRE_ORT+"="+recORT.get_ID_VPOS_TPA_FUHRE_ORT_cUF()), 
//																				true);
//								
//								if (oMV_OK.get_bIsOK()) {
//									//FU__LIST_CB_GelangensbestaetigungAnAus.this.EXT().get_oComponentMAP()._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW, true, false);
//									oThis.setSelected(bNew);
//									bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Erhalt der Gelangesbestätigung wurde auf "+(bNew?"<ERHALTEN>":"<FEHLT>")+" gesetzt!")));
//								} else {
//									bibMSG.add_MESSAGE(oMV_OK);
//								} 
//								
//							} else {
//								bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Das Land der Abladestation ist kein EU-Ausland (laut Länderstamm)!")));
//								oThis.setSelected(!oThis.isSelected());
//							}
//							
//						} else {
//							bibMSG.add_MESSAGE(
//									new MyE2_Alarm_Message(
//											new MyE2_String("Die Abladestelle ist ein Lager von "+bibALL.get_RECORD_MANDANT().get___KETTE(
//													bibVECTOR.get_Vector(_DB.MANDANT$NAME1,_DB.MANDANT$NAME2,_DB.MANDANT$ORT)))));
//							oThis.setSelected(!oThis.isSelected());
//						} 
//					} else {
//						bibMSG.add_MESSAGE(
//								new MyE2_Alarm_Message(
//										new MyE2_String("Bei Fremdwarenfuhren kann es keine Gelangensbestätigung geben !")));
//						oThis.setSelected(!oThis.isSelected());
//					}
				}
					
			};
		}

	}
	//-----------------------------------------------
	
	
	
	private class OwnWaehrungsAnzeiger extends Warenbewegung_InfoBlockFremdWaehrung {
	
		private RECORD_VPOS_TPA_FUHRE_ORT m_recOrt;

		public OwnWaehrungsAnzeiger(RECORD_VPOS_TPA_FUHRE_ORT recOrt) {
			super();
			this.m_recOrt = recOrt;
			this.fill();
		}

		@Override
		protected VEK<Triple<Long>> getIdAdressAndIdVposKonAndIdVposAngebot() {

			Long id_adresse = null;
			Long id_vpos_kon = 	null;
			Long id_vpos_std = 	null;
			
			
			try {
				id_adresse =  this.m_recOrt.get_ID_ADRESSE_lValue(null);
				id_vpos_kon = 	this.m_recOrt.get_ID_VPOS_KON_lValue(null);
				id_vpos_std = 	this.m_recOrt.get_ID_VPOS_STD_lValue(null);
			} catch (myException e) {
				id_adresse = null;
				id_vpos_kon = 	null;
				e.printStackTrace();
			}
			
			VEK<Triple<Long>> v = new VEK<Triple<Long>>()	._a(new Triple<Long>(id_adresse, id_vpos_kon,id_vpos_std))
														;
			return v;
		}

		@Override
		protected int getSpaltenBreite() {
			return 35;
		}

		@Override
		protected int getBlockHeigth() {
			return 30;
		}

		@Override
		protected Color getBlockBackColorHighlight() {
			return new E2_ColorAlarm();
		}

		@Override
		protected Font getBlockTextFont() {
			return new E2_FontBold();
		}

		@Override
		protected Color getBlockBackColorNormal() {
			return new E2_ColorBase();
		}
		
	}

	
}
