package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import java.util.HashMap;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentColumn;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_P_MapValidatorBeforeSave;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_SelectField_POSITIONSTYP;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SubQueryAgentShowDelGrundOncheckBox;

public class BSK_P_LIST__ComponentMAP extends E2_ComponentMAP {

	public BSK_P_LIST__ComponentMAP() throws myException 
	{
		super(new BSK_P_LIST_SQLFieldMAP());
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		
		E2_CheckBoxForList oCB = new E2_CheckBoxForList();
		oCB.setFocusTraversalParticipant(false);
		
		this.add_Component("CHECK_BOX",oCB,new MyE2_String("?"));
		this.add_Component("MARKER",new E2_ButtonListMarker(),new MyE2_String("?"));
		this.add_Component(BSK__CONST.HASH_KEY_ANZEIGE_LOCKED_POS,new MyE2_Label(BSK__CONST.LABEL_EMPTY),new MyE2_String("A"));

		
		MyE2_DB_MultiComponentColumn	 	oColPos = new MyE2_DB_MultiComponentColumn();
		oColPos.add_Component(new MyE2_DB_Label_INROW(oFM.get_("POSITIONSNUMMER")),	new MyE2_String("Pos"),null);
		oColPos.add_Component(new BS_SelectField_POSITIONSTYP(oFM.get_("POSITION_TYP"),oFM,false),	new MyE2_String("Pos.Typ"),null);
		
		MyE2_DB_MultiComponentColumn	 	oColArtbez_gueltigkeit = new MyE2_DB_MultiComponentColumn();
		oColArtbez_gueltigkeit.add_Component(new MyE2_DB_Label_INROW(oFM.get_("K_GUELTIG_VON")),		new MyE2_String("gültig von"),null);
		oColArtbez_gueltigkeit.add_Component(new MyE2_DB_Label_INROW(oFM.get_("K_GUELTIG_BIS")),		new MyE2_String("gültig bis"),null);
		
		MyE2_DB_MultiComponentColumn	 	oColBezeichnung = new MyE2_DB_MultiComponentColumn();
		oColBezeichnung.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ARTBEZ1")),new MyE2_String("Artikelbezeichnung 1"),null);

		MyE2_DB_MultiComponentColumn	 	oColANR1_2 = new MyE2_DB_MultiComponentColumn();
		oColANR1_2.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ANR1")),new MyE2_String("ANR 1"),null);
		oColANR1_2.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ANR2")),new MyE2_String("ANR 2"),null);

		MyE2_DB_MultiComponentColumn	 	oColMengePreise = new MyE2_DB_MultiComponentColumn();
		oColMengePreise.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ANZAHL")),			new MyE2_String("Anzahl"),  null);
		oColMengePreise.add_Component(new MyE2_DB_Label_INROW(oFM.get_("EINZELPREIS")),	new MyE2_String("E-Preis"), null);
		oColMengePreise.add_Component(new MyE2_DB_Label_INROW(oFM.get_("GESAMTPREIS")),	new MyE2_String("G-Preis"), null);
		
		MyE2_DB_MultiComponentColumn	 	oColEinheiten = new MyE2_DB_MultiComponentColumn();
		oColEinheiten.add_Component(new MyE2_DB_Label_INROW(oFM.get_("EINHEITKURZ")),new MyE2_String("Mg.-EH"),null);
		oColEinheiten.add_Component(new MyE2_DB_Label_INROW(oFM.get_("EINHEIT_PREIS_KURZ")),new MyE2_String("Pr.-EH"),null);
		oColEinheiten.add_Component(new MyE2_DB_Label_INROW(oFM.get_("MENGENDIVISOR")),new MyE2_String("Divisor"),null);

		MyE2_DB_MultiComponentColumn	 	oColIDs = new MyE2_DB_MultiComponentColumn();
		oColIDs.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_ARTIKEL")),new MyE2_String("ID-Art."),null);
		oColIDs.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_VPOS_KON")),new MyE2_String("ID-VPos-Kon"),null);
		oColIDs.add_Component(new MyE2_DB_Label_INROW(oFM.get_("K_ID_VPOS_KON_TRAKT")),new MyE2_String("ID-VPos-Kontrakt"),null);

	
		this.add_Component("GRUPPENFELD_0",oColPos,new MyE2_String("Pos.-Def."));
		this.add_Component("GRUPPENFELD_1",oColArtbez_gueltigkeit,new MyE2_String("Artikel/Lieferbed."));
		this.add_Component("GRUPPENFELD_2",oColANR1_2,new MyE2_String("ANR1/ANR2"));
		this.add_Component("GRUPPENFELD_3",oColBezeichnung,new MyE2_String("Bezeichnungen"));
		this.add_Component("GRUPPENFELD_4",oColMengePreise,new MyE2_String("Bezeichnungen"));
		this.add_Component("GRUPPENFELD_5",oColEinheiten,new MyE2_String("Einheiten"));
		this.add_Component("GRUPPENFELD_6",oColIDs,new MyE2_String("IDs"));
	
		
		
		// sichtbarkeit
		this.get__Comp("GRUPPENFELD_6").EXT().set_bIsVisibleInList(false);
		
		
		/*
		 * spaltenbreiten
		 */
		HashMap<String, MyE2IF__Component> oHMReal = this.get_REAL_ComponentHashMap();
		
		((MyE2_DB_Label_INROW)oHMReal.get("ARTBEZ1")).setFont(new E2_FontPlain(-2));
			
		((MyE2_DB_Label_INROW)oHMReal.get("ANZAHL")).EXT().set_oLayout_ListElement_AND_Titel(MyE2_Column.LAYOUT_RIGHT(E2_INSETS.I_0_0_0_2));
		((MyE2_DB_Label_INROW)oHMReal.get("EINZELPREIS")).EXT().set_oLayout_ListElement_AND_Titel(MyE2_Column.LAYOUT_RIGHT(E2_INSETS.I_0_0_0_2));
		((MyE2_DB_Label_INROW)oHMReal.get("GESAMTPREIS")).EXT().set_oLayout_ListElement_AND_Titel(MyE2_Column.LAYOUT_RIGHT(E2_INSETS.I_0_0_0_2));
		((MyE2_DB_Label_INROW)oHMReal.get("MENGENDIVISOR")).EXT().set_oLayout_ListElement_AND_Titel(MyE2_Column.LAYOUT_RIGHT(E2_INSETS.I_0_0_0_2));
		((MyE2_DB_Label_INROW)oHMReal.get("EINHEITKURZ")).EXT().set_oLayout_ListElement_AND_Titel(MyE2_Column.LAYOUT_RIGHT(E2_INSETS.I_0_0_0_2));
		((MyE2_DB_Label_INROW)oHMReal.get("EINHEIT_PREIS_KURZ")).EXT().set_oLayout_ListElement_AND_Titel(MyE2_Column.LAYOUT_RIGHT(E2_INSETS.I_0_0_0_2));
		((MyE2_DB_Label_INROW)oHMReal.get("ID_ARTIKEL")).EXT().set_oLayout_ListElement_AND_Titel(MyE2_Column.LAYOUT_RIGHT(E2_INSETS.I_0_0_0_2));
		((MyE2_DB_Label_INROW)oHMReal.get("ID_VPOS_KON")).EXT().set_oLayout_ListElement_AND_Titel(MyE2_Column.LAYOUT_RIGHT(E2_INSETS.I_0_0_0_2));
		((MyE2_DB_Label_INROW)oHMReal.get("K_ID_VPOS_KON_TRAKT")).EXT().set_oLayout_ListElement_AND_Titel(MyE2_Column.LAYOUT_RIGHT(E2_INSETS.I_0_0_0_2));
		
		this.set_oMAPSettingAgent(new BSK_P_MASK_MAP_SETTING_AGENT());
		
		this.add_oMAPValidator(new BS_P_MapValidatorBeforeSave(false));
		
		this.set_oSubQueryAgent(new BSK_P_LIST_MarkerSubQueryAgent());
		this.add_oSubQueryAgent(new BS__SubQueryAgentShowDelGrundOncheckBox());

	
	}

	
	
	
	
	
	
	
//	/*
//	 * mask-setting-agent fuer das laden der adress-werte in die maske
//	 */
//	private class ownMaskActionAfterFound extends XX_MaskActionAfterFound
//	{
//		public void doMaskSettingsAfterValueWrittenInMaskField(String cMaskValue, MyE2_DB_MaskSearchField oSearchField, boolean bAfterAction) throws myException 
//		{
//			// BSAA_ComponentMAP_Mask oThis = BSAA_ComponentMAP_Mask.this;
//			if (bAfterAction)
//			{
//				
//				E2_ComponentMAP 			oMap = 				oSearchField.EXT().get_oComponentMAP();
//				
//				String cQuery = "SELECT  JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ," +				// 0
//										"JT_ARTIKEL.ID_ARTIKEL, " +						// 1
//										"JT_ARTIKEL_BEZ.ARTBEZ1," +						// 2
//										"JT_ARTIKEL_BEZ.ARTBEZ2, " +					// 3
//										"JT_ARTIKEL.ANR1, " +							// 4
//										"JT_ARTIKEL_BEZ.ANR2 " +						// 5
//										"  FROM " +
//										bibE2.cTO()+".JT_ARTIKEL_BEZ, "+bibE2.cTO()+".JT_ARTIKEL " +
//										"  WHERE  JT_ARTIKEL_BEZ.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL  AND " +
//										" JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ="+cMaskValue;
//				
//				MyDBToolBox oDB = bibALL.get_myDBToolBox();
//				String[][] oRueck = oDB.EinzelAbfrageInArray(cQuery,"");
//				bibALL.destroy_myDBToolBox(oDB);
//				
//				
//				if (oRueck == null || oRueck.length != 1)
//				{
//					throw new myException("BSAA_P_ComponentMAP_List:ownMaskSettingAgent:Error filling mask with search-result");
//				}
//				else
//				{
//					HashMap<String, MyE2IF__Component> oHMReal = oMap.get_REAL_ComponentHashMap();
//					
//					
//					
//					// wenn  nur ein gueltiger MWST-Satz fuer diese Art-Bez existiert, dann 
//					// wird dieser 
//					My_MWSTSaetze oBSMW = new My_MWSTSaetze(null,oRueck[0][0]);
//					((MyE2IF__DB_Component)oHMReal.get("STEUERSATZ")).set_cActualMaskValue("");
//					if (oBSMW.get_vMWSTArtBez().size()==1)
//					{
//						My_MWST oBSMWST = (My_MWST)oBSMW.get_vMWSTArtBez().get(0);
//						((MyE2IF__DB_Component)oHMReal.get("STEUERSATZ")).set_cActualMaskValue(oBSMWST.get_cMWST_Formated());
//					}
//					
//					MyArtikel oArtikel = new MyArtikel(oRueck[0][1]);
//					
//					
//					((MyE2IF__DB_Component)oHMReal.get("ANR1")).set_cActualMaskValue(oRueck[0][4]);
//					((MyE2IF__DB_Component)oHMReal.get("ANR2")).set_cActualMaskValue(oRueck[0][5]);
//
//					((MyE2IF__DB_Component)oHMReal.get("ARTBEZ1")).set_cActualMaskValue(oRueck[0][2]);
//					((MyE2IF__DB_Component)oHMReal.get("ARTBEZ2")).set_cActualMaskValue(oRueck[0][3]);
//					
//					((MyE2IF__DB_Component)oHMReal.get("ID_ARTIKEL")).set_cActualMaskValue(oRueck[0][1]);
//					
//					((MyE2IF__DB_Component)oHMReal.get("EINHEITKURZ")).set_cActualMaskValue(oArtikel.get_EINHEIT());
//					((MyE2IF__DB_Component)oHMReal.get("EINHEIT_PREIS_KURZ")).set_cActualMaskValue(oArtikel.get_EINHEIT_PREIS());
//					((MyE2IF__DB_Component)oHMReal.get("MENGENDIVISOR")).set_cActualMaskValue(oArtikel.get_MENGENDIVISOR());
//					
//				}
//				
//			}
//		}
//		
//	}

	
	
	
	
}
