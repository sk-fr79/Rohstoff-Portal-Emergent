package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ARTIKELBEZ_LIEF;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.ColumnLayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_SettingAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain_LineThrough;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW_ReplaceDB_Value;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentColumn;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField_WithSelektor;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_MaskActionAfterFound;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LIEFERBEDINGUNGEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ZAHLUNGSBEDINGUNGEN;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.basics4project.specialSelects.SelAvvText;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ComboBox_LIEFERBEDINGUNGEN;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ComboBox_ZAHLUNGSBEDINGUNGEN;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_MASK_ComponentMAP;
import rohstoff.Echo2BusinessLogic._4_ALL.MASK_COMPONENT_SEARCH_EAK_CODES;
import rohstoff.utils.ECHO2.DB_SEARCH_ArtikelBez;

//NEU_09
public class FSA_LIST_ComponentMAP extends E2_ComponentMAP
{

	private FS_MASK_ComponentMAP   	oFS_ComponentMAP_MASK = null; 
	
	private String 					EK_VK = null; 
	
	/**
	 * @param cEK_VK MUSS entweder "EK" oder "VK" sein
	 * @throws myException
	 */
	public FSA_LIST_ComponentMAP(	FS_MASK_ComponentMAP    oFS_ComponentMapMASK,
									String 					cEK_VK) throws myException
	{
		super(new FSA_LIST_SQLFieldMap_Artikelbez(cEK_VK));
		
		this.oFS_ComponentMAP_MASK = oFS_ComponentMapMASK;
		
		this.EK_VK = cEK_VK;
		
		E2_FontPlain oFont = new E2_FontPlain(-2);
		
		
		SQLFieldMAP oSQL_FieldMap = this.get_oSQLFieldMAP();
		
		DB_SEARCH_ArtikelBez		oSearchArtBez = 		new DB_SEARCH_ArtikelBez(	oSQL_FieldMap.get_("ID_ARTIKEL_BEZ"),
																						new Insets(0,0,2,0), new Extent(30), new Extent(300), 
																						oFont, oFont,true,null,true);

		//2012-11-30: artikelbezeichnung sortierbar machen
		oSearchArtBez.EXT_DB().set_cSortAusdruckFuerSortbuttonUP("JT_ARTIKEL.ANR1 ASC,JT_ARTIKEL_BEZ.ANR2 ASC");
		oSearchArtBez.EXT_DB().set_cSortAusdruckFuerSortbuttonDOWN("JT_ARTIKEL.ANR1 DESC,JT_ARTIKEL_BEZ.ANR2 DESC");
		
//		oSearchArtBez.EXT_DB().set_bIsSortable(false);
		
		
		
		
		oSearchArtBez.EXT().set_oColExtent(new Extent(270));

		//oSearchArtBez.get_oTextForAnzeige().setWidth(new Extent(200));
		//oSearchArtBez.get_oTextFieldForSearchInput().setWidth(new Extent(30));

		oSearchArtBez.set_oMaskActionAfterMaskValueIsFound(new actionAfterFoundSortebez());

		E2_DropDownSettings  DDVerunreinigung = 	new E2_DropDownSettings("JT_ARTBEZ_VERUNREINIGUNG", "VERUNREINIGUNG", "ID_ARTBEZ_VERUNREINIGUNG",null, null, true, "VERUNREINIGUNG");
		E2_DropDownSettings  DDMech_Zustand =	 	new E2_DropDownSettings("JT_ARTBEZ_MECH_ZUSTAND", "MECH_ZUSTAND", "ID_ARTBEZ_MECH_ZUSTAND",null, null, true, "MECH_ZUSTAND");
		

//		MASK_COMPONENT_SEARCH_EAK_CODES		oSearchEAKCode = new MASK_COMPONENT_SEARCH_EAK_CODES(	oSQL_FieldMap.get_("ID_EAK_CODE"),
//																									oFS_ComponentMapMASK,
//																									oSearchArtBez);

		//2020-09-24: zwischen eingangs - und ausgangs-zauberstab unterscheiden
		FSA_ListComponentSearchAVVCode		oSearchEAKCode = new FSA_ListComponentSearchAVVCode(	oSQL_FieldMap.get_("ID_EAK_CODE"),
																									oFS_ComponentMapMASK,
																									oSearchArtBez,
																									this.EK_VK.equals("EK"));
		
		
		oSearchEAKCode.get_oTextForAnzeige().setWidth(new Extent(200));
		oSearchEAKCode.get_oTFDatenFeldWithID().setWidth(new Extent(30));
		oSearchEAKCode.EXT().set_oColExtent(new Extent(230));
		
		//20170926: sortierung nach AVV-Code dazu
		oSearchEAKCode.EXT_DB().set_cSortAusdruckFuerSortbuttonUP("("+new SelAvvText(ARTIKELBEZ_LIEF.id_eak_code.tnfn(), true).s()+") ASC");
		oSearchEAKCode.EXT_DB().set_cSortAusdruckFuerSortbuttonDOWN("("+new SelAvvText(ARTIKELBEZ_LIEF.id_eak_code.tnfn(), true).s()+") DESC");
		oSearchEAKCode.EXT_DB().set_bIsSortable(true);
		

		BS_ComboBox_LIEFERBEDINGUNGEN 	oLieferbed = 	new BS_ComboBox_LIEFERBEDINGUNGEN(oSQL_FieldMap,400,3);
		BS_ComboBox_ZAHLUNGSBEDINGUNGEN oZahlungsbed = 	new BS_ComboBox_ZAHLUNGSBEDINGUNGEN(oSQL_FieldMap,true);

		//2011-06-06: neues feld: infos fuer die waage
		//VERARBEITUNGS_INFO
		MyE2_DB_TextArea                oTFVerarbeitungsInfo = new MyE2_DB_TextArea(oSQL_FieldMap.get_("VERARBEITUNGS_INFO"),200,5,null, new E2_FontPlain(-2));
		
		oLieferbed.get_oTextArea().setFont(oFont);
		oZahlungsbed.get_oTextField().setFont(oFont);
		
		ownGridLayoutData  glDataLeft = new ownGridLayoutData(false,false);
		ownColLayoutData   clDataLeft = new ownColLayoutData(false,false);
		
		ownGridLayoutData  glDataRight = new ownGridLayoutData(true,false);
		
		oLieferbed.set_WidthAndHeightOfDropDown(new Extent(230),new Extent(100),null, new Integer(230));
		oZahlungsbed.set_WidthAndHeightOfDropDown(new Extent(230),new Extent(100),null, new Integer(230));
		
		MyE2_DB_TextArea   oTFArtbez2 = 				new MyE2_DB_TextArea(oSQL_FieldMap.get_("ARTBEZ2_ALTERNATIV"));
		oTFArtbez2.setFont(oFont);
		oTFArtbez2.set_iWidthPixel(200);
		oTFArtbez2.set_iRows(4);


		
		MyE2_DB_TextField	oTFFixkosten = new MyE2_DB_TextField(oSQL_FieldMap.get_("FIXKOSTEN"));
		oTFFixkosten.setFont(oFont);
		oTFFixkosten.set_iWidthPixel(50);
		oTFFixkosten.setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		oTFFixkosten.EXT().set_oColExtent(new Extent(70));

		MyE2_DB_MultiComponentColumn oColColumn1 = 		new MyE2_DB_MultiComponentColumn();
		MyE2_DB_MultiComponentColumn oColColumn2 = 		new MyE2_DB_MultiComponentColumn();
		MyE2_DB_MultiComponentColumn oColColumn3 = 		new MyE2_DB_MultiComponentColumn();
		MyE2_DB_MultiComponentColumn oColColumn4 = 		new MyE2_DB_MultiComponentColumn();

		
		oColColumn1.add_Component(oSearchArtBez, 			new MyE2_String("Artikel-Bezeichnung"), null,clDataLeft);
		oColColumn1.add_Component(new ownDD_Verunreinigung(oSQL_FieldMap.get_("ID_ARTBEZ_VERUNREINIGUNG"),DDVerunreinigung.getDD()),	new MyE2_String("Verunreinigung"), null,clDataLeft);
		oColColumn1.add_Component(oSearchEAKCode, 	new MyE2_String("AVV-Code"), null,clDataLeft);

		oColColumn2.add_Component(oTFArtbez2, 		new MyE2_String("Artikelbez.2"), null,clDataLeft);
		oColColumn2.add_Component(new ownDD_MechZustand(oSQL_FieldMap.get_("ID_ARTBEZ_MECH_ZUSTAND"),DDMech_Zustand.getDD()),		new MyE2_String("Mech.Zustand"), null,clDataLeft);

		//2010-11-23: aenderung der spalten
		if (cEK_VK.equals("EK"))
		{
			oColColumn3.add_Component(new MyE2_DB_CheckBox(oSQL_FieldMap.get_("ANGEBOT")),new MyE2_String("Angebot EK?"), null,clDataLeft);
		}
		else
		{
			oColColumn3.add_Component(new MyE2_DB_CheckBox(oSQL_FieldMap.get_("ANGEBOT_ABNEHMER")),new MyE2_String("Angebot VK?"), null,clDataLeft);
		}
		oColColumn3.add_Component(oTFFixkosten,new MyE2_String("Kosten/EH"), null,clDataLeft);
		

		
		//20013-06-28: neue felder fuer die anzeige, ob es ein produkt oder eine dienstleistung ist
		oColColumn3.add_Component(new onwLabelInRowProdukt(oSQL_FieldMap.get_("A_"+_DB.ARTIKEL$IST_PRODUKT)),new MyE2_String("Produkt?"), null,clDataLeft);
		oColColumn3.add_Component(new onwLabelInRowEndoOfWaste(oSQL_FieldMap.get_("A_"+_DB.ARTIKEL$END_OF_WASTE)),new MyE2_String("End-o-W.?"), null,clDataLeft);
		oColColumn3.add_Component(new onwLabelInRowDienst(oSQL_FieldMap.get_("A_"+_DB.ARTIKEL$DIENSTLEISTUNG)),new MyE2_String("Dienstl.?"), null,clDataLeft);
		
		
		oColColumn4.add_Component(oLieferbed, 				new MyE2_String("Lieferbedingungen"), null,clDataLeft);
		//oColColumn4.add_Component(oZahlungsbed, 			new MyE2_String("Zahlungsbedingungen"), null,clDataLeft);
		
		//2012-03-21: umstellung der zahlungsbedinung auf ID
		//aktivieren nach OSTERN
		//oColColumn4.add_Component(oZahlungsbed, 			new MyE2_String("Zahlungsbedingungen(alt)"), null,clDataLeft);
		oColColumn4.add_Component(new SelectField_Zahlungsbedingungen(oSQL_FieldMap), new MyE2_String("Zahlungsbedingungen"), null,clDataLeft);
		oZahlungsbed.EXT().set_bDisabledFromBasic(true);    //nur zum vergleich
		oZahlungsbed.get_oTextField().setFont(new E2_FontPlain_LineThrough(-2));
		//2012-03-21
		
		
		this.add_Component("NAME_OF_CHECKBOX_IN_LIST", new E2_CheckBoxForList(),new MyE2_String("?"));

		this.add_Component("SPALTE_1",oColColumn1,	new MyE2_String("Artbez 1"));
		this.add_Component("SPALTE_2",oColColumn2, 	new MyE2_String("Artbez 2"));
		this.add_Component("SPALTE_3",oColColumn3, 	new MyE2_String("Ang."));
		this.add_Component("SPALTE_4",oColColumn4, 	new MyE2_String("Zahlungs/Lieferbed."));
		this.add_Component(oTFVerarbeitungsInfo, 	new MyE2_String("Verarbeitungsinfo"));

		
		this.set_oMAPSettingAgent(new ownMapSettingAgent());
		this.set_oSubQueryAgent(new ownMarkerSubQueryAgent());
		
	}
	
	
	
	
	//2013-06-28: neue Anzeigefelder fuer kenner produkt / dienstleistung
	private class onwLabelInRowProdukt extends MyE2_DB_Label_INROW_ReplaceDB_Value {

		public onwLabelInRowProdukt(SQLField osqlField) throws myException {
			super(osqlField, false, 100);
			
			HashMap<String, String> hmTextErsatz = new HashMap<String, String>();
			hmTextErsatz.put("", "");
			hmTextErsatz.put("N", "");
			hmTextErsatz.put("Y", new MyE2_String("Produkt").CTrans());
			
			HashMap<String, Color> hmBackGround = new HashMap<String, Color>();
			hmBackGround.put("",  null);
			hmBackGround.put("N", null);
			hmBackGround.put("Y", new E2_ColorHelpBackground());
			
			
			this.set_ReplaceOptions(hmTextErsatz, null, null, hmBackGround, hmBackGround);
		}
	}

	
	//2013-06-28: neue Anzeigefelder fuer kenner produkt / dienstleistung
	private class onwLabelInRowEndoOfWaste extends MyE2_DB_Label_INROW_ReplaceDB_Value {

		public onwLabelInRowEndoOfWaste(SQLField osqlField) throws myException {
			super(osqlField, false, 100);
			
			HashMap<String, String> hmTextErsatz = new HashMap<String, String>();
			hmTextErsatz.put("", "");
			hmTextErsatz.put("N", "");
			hmTextErsatz.put("Y", new MyE2_String("EndOfWaste").CTrans());
			
			HashMap<String, Color> hmBackGround = new HashMap<String, Color>();
			hmBackGround.put("",  null);
			hmBackGround.put("N", null);
			hmBackGround.put("Y", new E2_ColorHelpBackground());
			
			
			this.set_ReplaceOptions(hmTextErsatz, null, null, hmBackGround, hmBackGround);
		}
	}

	
	
	
	private class onwLabelInRowDienst extends MyE2_DB_Label_INROW_ReplaceDB_Value {

		public onwLabelInRowDienst(SQLField osqlField) throws myException {
			super(osqlField, false, 100);
			
			HashMap<String, String> hmTextErsatz = new HashMap<String, String>();
			hmTextErsatz.put("", "");
			hmTextErsatz.put("N", "");
			hmTextErsatz.put("Y", new MyE2_String("Dienstl.").CTrans());
			
			
			HashMap<String, Color> hmBackGround = new HashMap<String, Color>();
			hmBackGround.put("",  null);
			hmBackGround.put("N", null);
			hmBackGround.put("Y", new E2_ColorHelpBackground());
			
			
			this.set_ReplaceOptions(hmTextErsatz, null, null, hmBackGround, hmBackGround);
		}
	}
	
	
	//2012-03-21: zahlungsbedingung wird auf id umgestellt
	private class SelectField_Zahlungsbedingungen extends MyE2_DB_SelectField
	{
		public SelectField_Zahlungsbedingungen(SQLFieldMAP oSQLFieldMAP) throws myException
		{
			super(	oSQLFieldMAP.get_("ID_ZAHLUNGSBEDINGUNGEN"),
					new E2_DropDownSettings( "JT_ZAHLUNGSBEDINGUNGEN", "KURZBEZEICHNUNG", "ID_ZAHLUNGSBEDINGUNGEN", null, true).getDD(),false);
			
			this.setFont(new E2_FontPlain(-2));
			this.setWidth(new Extent(230));
		}
	}
	

	
	private class ownDD_Verunreinigung extends MyE2_DB_SelectField
	{
		public ownDD_Verunreinigung(SQLField osqlField, String[][] dd)	throws myException
		{
			super(osqlField, dd, false);
			this.setFont(new E2_FontPlain(-2));
			this.setWidth(new Extent(360));
		}
	}

	private class ownDD_MechZustand extends MyE2_DB_SelectField
	{
		public ownDD_MechZustand(SQLField osqlField, String[][] dd)	throws myException
		{
			super(osqlField, dd, false);
			this.setFont(new E2_FontPlain(-2));
		}
	}

	
	private class ownGridLayoutData extends GridLayoutData
	{
		public ownGridLayoutData(boolean bRight, boolean bTitle)
		{
			super();
			this.setAlignment(bRight?new Alignment(Alignment.RIGHT,Alignment.CENTER):new Alignment(Alignment.LEFT,Alignment.CENTER));
			this.setBackground(bTitle?new E2_ColorDark():new E2_ColorBase());
			this.setInsets(E2_INSETS.I_2_0_0_0);
		}
	}
	
	private class ownColLayoutData extends ColumnLayoutData
	{
		public ownColLayoutData(boolean bRight, boolean bTitle)
		{
			super();
			this.setAlignment(bRight?new Alignment(Alignment.RIGHT,Alignment.CENTER):new Alignment(Alignment.LEFT,Alignment.CENTER));
			this.setBackground(bTitle?new E2_ColorDark():new E2_ColorBase());
			this.setInsets(E2_INSETS.I_2_0_0_0);
		}
	}
	
	
	// actionagent fuer die suche nach einer artikelbez. wenn der searchknopf gedrueckt wurde
	private class actionAfterFoundSortebez extends XX_MaskActionAfterFound
	{
		public void doMaskSettingsAfterValueWrittenInMaskField(String cMaskValue, MyE2_DB_MaskSearchField oSearchField, boolean bAfterAction, boolean bIS_PrimaryCall) throws myException 
		{
			FSA_LIST_ComponentMAP oThis = FSA_LIST_ComponentMAP.this;
			
			
			if (!bAfterAction)          // soll nur bei neueingabe benutzt werden
				return;
			
			String cTestWert = bibALL.ReplaceTeilString(cMaskValue, ".", "");
			if (!bibALL.isLong(cTestWert))
				return;
			
			E2_ComponentMAP oMap = oSearchField.EXT().get_oComponentMAP();
			
			HashMap<String,MyE2IF__Component> hmRealComponentMap = oMap.get_REAL_ComponentHashMap();
			
			String[][] cWerte = bibDB.EinzelAbfrageInArray("SELECT ARTBEZ2 FROM "+bibE2.cTO()+".JT_ARTIKEL_BEZ WHERE ID_ARTIKEL_BEZ="+cTestWert,"");
			
			if (cWerte == null || cWerte.length != 1)
				throw new myException("FSA_LIST_ComponentMAP:actionAfterFoundSortebez:doMaskSettingsAfterValueWrittenInMaskField:Error quering ARTBEZ1/2");
			
			((MyE2IF__DB_Component)hmRealComponentMap.get("ARTBEZ2_ALTERNATIV")).set_cActualMaskValue(cWerte[0][0]);
			
			// in der maske nachsehen, welche lieferbedingungen und zahlungsbedingungungen die adresse hat
			String cID_LIEFERBEDINGUNGEN = bibALL.null2leer(((MyE2IF__DB_Component)oThis.oFS_ComponentMAP_MASK.get__Comp("ID_LIEFERBEDINGUNGEN")).get_cActualDBValueFormated());
			String cID_ZAHLUNGSBEDINGUNGEN = bibALL.null2leer(((MyE2IF__DB_Component)oThis.oFS_ComponentMAP_MASK.get__Comp("ID_ZAHLUNGSBEDINGUNGEN")).get_cActualDBValueFormated());

			String cKlarTextLieferbed = "";
//			String cKlarTextZahlungsbed = "";
			
			cID_LIEFERBEDINGUNGEN = bibALL.ReplaceTeilString(cID_LIEFERBEDINGUNGEN, ".", "");
			cID_ZAHLUNGSBEDINGUNGEN = bibALL.ReplaceTeilString(cID_ZAHLUNGSBEDINGUNGEN, ".", "");
			
			if (bibALL.isLong(cID_LIEFERBEDINGUNGEN))
			{
				cKlarTextLieferbed = bibDB.EinzelAbfrage("SELECT KURZBEZEICHNUNG FROM "+
						bibE2.cTO()+".JT_LIEFERBEDINGUNGEN WHERE ID_LIEFERBEDINGUNGEN="+cID_LIEFERBEDINGUNGEN);
			}
			
			//2012-05-22: zahlungsbedinungen mussen nicht mehr geladen werden, da id_basierte dropdown-liste
//			RECORD_ZAHLUNGSBEDINGUNGEN  recZahl = null;
//			
//			if (bibALL.isLong(cID_ZAHLUNGSBEDINGUNGEN))
//			{
//				cKlarTextZahlungsbed = bibDB.EinzelAbfrage("SELECT KURZBEZEICHNUNG FROM "+
//						bibE2.cTO()+".JT_ZAHLUNGSBEDINGUNGEN WHERE ID_ZAHLUNGSBEDINGUNGEN="+cID_ZAHLUNGSBEDINGUNGEN);
//				
//				recZahl = new RECORD_ZAHLUNGSBEDINGUNGEN(cID_ZAHLUNGSBEDINGUNGEN);
//			}
			((MyE2IF__DB_Component)hmRealComponentMap.get("LIEFERBEDINGUNGEN")).set_cActualMaskValue(cKlarTextLieferbed);
			
//			if (recZahl!=null)
//			{
//				((MyE2IF__DB_Component)hmRealComponentMap.get("ID_ZAHLUNGSBEDINGUNGEN")).set_cActualMaskValue(cKlarTextZahlungsbed);
//			}
	
			
			//2010-12-07:  jetzt abhaengig vom Mandanten-Schalter nach suchautomatik 
			String cAutomatikAVV_Holen  = bibALL.get_TEXTVAL_FROM_RECLIST_MANDANTEN_ZUSATZ("ADRESS_EK_SORTE_AVV_AUTOMATISCH");
			if (S.isFull(cAutomatikAVV_Holen) && cAutomatikAVV_Holen.equals("Y"))
			{
				//ZUERST DIE FELDER dieser componentmap finden:
				DB_SEARCH_ArtikelBez oSearchArtBez = (DB_SEARCH_ArtikelBez)oSearchField;
				MASK_COMPONENT_SEARCH_EAK_CODES		oSearchEAKCode = (MASK_COMPONENT_SEARCH_EAK_CODES)oMap.get_hmRealComponents().get("ID_EAK_CODE");
				oSearchEAKCode.suche_StandardAVV_Code(oSearchArtBez);
			}
			
		}
	}
	
	
	
	//	 markiert die dienstleistungen gelb
	private class ownMarkerSubQueryAgent extends XX_ComponentMAP_SubqueryAGENT {

		// speicher fuer die dienstleistungen
		private Vector<String>			vDienstleistungen = new Vector<String>();
		
		
		public ownMarkerSubQueryAgent()
		{
			super();
			// dienstleistungen sammeln
			String[][] cHelp = bibDB.EinzelAbfrageInArray("SELECT ANR1 FROM "+bibE2.cTO()+".JT_ARTIKEL WHERE DIENSTLEISTUNG='Y'");
			for (int i=0;i<cHelp.length;i++)
				this.vDienstleistungen.add(cHelp[i][0]);
		}

		
		public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) throws myException 
		{
		}

		public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
		{ 
			
			// jetzt dienstleistungen markieren
			HashMap<String,MyE2IF__Component> hmRealComponentMAP = oMAP.get_REAL_ComponentHashMap();
			
			String cANR1 = bibALL.null2leer((String)oUsedResultMAP.get_UnFormatedValue("A_ANR1"));
			if (!cANR1.equals(""))
			{
				if (this.vDienstleistungen.contains(cANR1))
				{
					((DB_SEARCH_ArtikelBez)hmRealComponentMAP.get("ID_ARTIKEL_BEZ")).get_oTextForAnzeige().setBackground(new E2_ColorHelpBackground());
				}
			}
			
			//jetzt nachsehen, ob in der textanzeige der EAK-Codes (*) vorhanden ist, dann die anzeige rot
			if (((MASK_COMPONENT_SEARCH_EAK_CODES)hmRealComponentMAP.get("ID_EAK_CODE")).get_oTextForAnzeige().getText().indexOf("(*)")>0)
				((MASK_COMPONENT_SEARCH_EAK_CODES)hmRealComponentMAP.get("ID_EAK_CODE")).get_oTextForAnzeige().setBackground(new E2_ColorAlarm());
			
			
			//inative durchstreichen
			boolean bAktiv = oUsedResultMAP.get_FormatedValue("A_AKTIV").equals("Y") && oUsedResultMAP.get_FormatedValue("B_AKTIV").equals("Y");

			if (!bAktiv)
			{
				Font oDelFont =new Font( new Font.Typeface("Arial"), Font.ITALIC+Font.LINE_THROUGH, new Extent(bibALL.get_FONT_SIZE()-2,Extent.PT));
				
				for (Map.Entry<String, MyE2IF__Component> oEntrys:hmRealComponentMAP.entrySet())
				{
					
					if 	(oEntrys.getValue() instanceof MyE2_Label)
						((MyE2_Label)oEntrys.getValue() ).setFont(oDelFont);
					else if (oEntrys.getValue()  instanceof MyE2_TextField)
						((MyE2_TextField)oEntrys.getValue() ).setFont(oDelFont);
					else if (oEntrys.getValue()  instanceof MyE2_TextArea)
						((MyE2_TextArea)oEntrys.getValue() ).setFont(oDelFont);
					else if (oEntrys.getValue()  instanceof MyE2_Button)
						((MyE2_Button)oEntrys.getValue() ).setFont(oDelFont);
					else if (oEntrys.getValue()  instanceof MASK_COMPONENT_SEARCH_EAK_CODES)
					{
						((MASK_COMPONENT_SEARCH_EAK_CODES)oEntrys.getValue() ).get_oTextForAnzeige().setFont(oDelFont);
					}
					else if (oEntrys.getValue()  instanceof MyE2_DB_MaskSearchField)
					{
						((MyE2_DB_MaskSearchField)oEntrys.getValue() ).get_oTextForAnzeige().setFont(oDelFont);
						((MyE2_DB_MaskSearchField)oEntrys.getValue() ).get_oTextFieldForSearchInput().setFont(oDelFont);
					}
					else if (oEntrys.getValue()  instanceof MyE2_DB_TextField_WithSelektor)
					{
						((MyE2_DB_TextField_WithSelektor)oEntrys.getValue() ).get_oTextField().setFont(oDelFont);
					}
				}
			}
		}
	}


	
	private class ownMapSettingAgent extends XX_MAP_SettingAgent
	{

		@Override
		public void __doSettings_AFTER(E2_ComponentMAP map, String STATUS_MASKE) throws myException
		{
		}

		@Override
		public void __doSettings_BEFORE(E2_ComponentMAP map, String STATUS_MASKE) throws myException
		{
			FSA_LIST_ComponentMAP oThis = FSA_LIST_ComponentMAP.this;
			
			if (STATUS_MASKE.equals(E2_ComponentMAP.STATUS_NEW_EMPTY))
			{
				
				long lID_Zahlungsbedingungen = 		oThis.oFS_ComponentMAP_MASK.get_LActualDBValue("ID_ZAHLUNGSBEDINGUNGEN", true, true, new Long(-1)).longValue();
				long lID_ZahlungsbedingungenVK = 	oThis.oFS_ComponentMAP_MASK.get_LActualDBValue("ID_ZAHLUNGSBEDINGUNGEN_VK", true, true, new Long(-1)).longValue();
				
				long lID_Lieferbedingungen =	oThis.oFS_ComponentMAP_MASK.get_LActualDBValue("ID_LIEFERBEDINGUNGEN", true, true, new Long(-1)).longValue();
				
				if (FSA_LIST_ComponentMAP.this.EK_VK.equals("EK"))
				{
					if (lID_Zahlungsbedingungen>=0)
						((MyE2IF__DB_Component)map.get_hmRealComponents().get("ID_ZAHLUNGSBEDINGUNGEN")).set_cActualMaskValue(new RECORD_ZAHLUNGSBEDINGUNGEN(lID_Zahlungsbedingungen).get_ID_ZAHLUNGSBEDINGUNGEN_cF());
				}
				else
				{
					if (lID_ZahlungsbedingungenVK>=0)
						((MyE2IF__DB_Component)map.get_hmRealComponents().get("ID_ZAHLUNGSBEDINGUNGEN")).set_cActualMaskValue(new RECORD_ZAHLUNGSBEDINGUNGEN(lID_ZahlungsbedingungenVK).get_ID_ZAHLUNGSBEDINGUNGEN_cF());
				}
				
				if (lID_Lieferbedingungen>=0)
					((MyE2IF__DB_Component)map.get_hmRealComponents().get("LIEFERBEDINGUNGEN")).set_cActualMaskValue(new RECORD_LIEFERBEDINGUNGEN(lID_Lieferbedingungen).get_KURZBEZEICHNUNG_cUF_NN(""));
			}
		}
		
	}
	
	
	
}
