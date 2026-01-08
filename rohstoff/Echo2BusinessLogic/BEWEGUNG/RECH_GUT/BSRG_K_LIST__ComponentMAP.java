package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_V2;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListSorter;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_ListButtonExtendDaughterObject;
import panter.gmbh.Echo2.ListAndMask.List.E2_ListButtonExtendDaughterObjects_LISTHEADLINE;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList_StandardLayoutFactory;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SubQueryAgentShowDelGrundOncheckBox;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.ORIG_MAIL.RGOM_K_LIST_IconToShowSendStatusOrigMail;
import rohstoff.Echo2BusinessLogic._4_ALL.DB_BUTTON_FIRMA_WITH_INFO;

public class BSRG_K_LIST__ComponentMAP extends E2_ComponentMAP_V2 {

//	//2015-01-15: ein bewertungsobjekt fuer die original-eMail-versendung
//	private RGOM_RG_OrigIsEmail  oRGOM_RG_OrigIsEmail = null;
	
	public BSRG_K_LIST__ComponentMAP(BSRG_K_LIST__ModulContainer	KopfModulcontainerList,E2_NavigationList oNaviList,BS__SETTING SETTING) throws myException 
	{
		super(new BSRG_K_LIST_SQLFieldMAP(SETTING));
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		
		E2_CheckBoxForList oCB = new E2_CheckBoxForList();
		oCB.setFocusTraversalParticipant(false);
		
		this.add_Component("CHECK_BOX",oCB,new MyE2_String("?"));
		this.add_Component("MARKER",new E2_ButtonListMarker(),new MyE2_String("?"));
		this.add_Component(BSRG__CONST.HASH_KEY_ANZEIGE_LOCKED,new MyE2_Label(BSRG__CONST.LABEL_EMPTY),new MyE2_String("A"));
		this.add_Component(BSRG__CONST.HASH_KEY_ANZEIGE_POSITIONS,new MyE2_Row(MyE2_Row.STYLE_NO_BORDER()),new MyE2_String("P"));
		this.add_Component(BSRG__CONST.HASH_KEY_ANZEIGE_STORNO_STATUS,new MyE2_Row(MyE2_Row.STYLE_NO_BORDER()),new MyE2_String("S"));
		this.add_Component("EXTENDER",new E2_ListButtonExtendDaughterObject(false, null),new MyE2_String("+"));

		//2015-01-15: spalte mit symbol fuer den versandstatus der email
		this.add_Component(new RGOM_K_LIST_IconToShowSendStatusOrigMail(oFM.get(BSRG__CONST.HASH_KEY_ID_VKOPF_RG_2),oNaviList), new MyE2_String("eMail"));
		
		
		//2011-06-07: neuer jumper
		BSRG_K_LIST_BT_JUMPER oJumper = new BSRG_K_LIST_BT_JUMPER();
		oJumper.EXT().set_oColExtent(new Extent(15));
		this.add_Component(BSRG__CONST.HASH_KEY_JUMPER_RG_KOPF,oJumper,new MyE2_String(">>"));
		
	
		//2011-03-02: button-with-info - komponente, die name1 anzeiget und adresse oeffnet
		MyE2_String cTextInfo = new MyE2_String(SETTING.get_cBELEGTYP().equals(myCONST.VORGANGSART_RECHNUNG)?"Name 1 (Abnehmer)":"Name 1 (Lieferant)");
		this.add_Component(	new DB_BUTTON_FIRMA_WITH_INFO(oFM.get_("INFO_ID_ADRESSE"),null,"NAME1","JT_VKOPF_RG.NAME1 ASC","JT_VKOPF_RG.NAME1 DESC",200,100), 
				cTextInfo,	true,true, cTextInfo,null,null);
//		((DB_BUTTON_FIRMA_WITH_INFO)this.get__Comp("INFO_ID_ADRESSE")).EXT_DB().set_cSortAusdruckFuerSortbuttonDOWN("JT_VKOPF_RG.NAME1 DESC");
//		((DB_BUTTON_FIRMA_WITH_INFO)this.get__Comp("INFO_ID_ADRESSE")).EXT_DB().set_cSortAusdruckFuerSortbuttonUP("JT_VKOPF_RG.NAME1 ASC");

		
		//this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("NAME1")),				new MyE2_String("Name "+SETTING.get_cTextFuerAdressArt()));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("NAME2")),				new MyE2_String("Name 2"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("STRASSE")),			new MyE2_String("Strasse"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ORT")),				new MyE2_String("Ort "+SETTING.get_cTextFuerAdressArt()));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("U_KUERZEL")),			new MyE2_String("K"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("SUBQUERY__ENDBETRAG_RG")),	new MyE2_String("Endbetrag"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ERSTELLUNGSDATUM")),	new MyE2_String("Erstellt"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("DRUCKDATUM")),			new MyE2_String("Rech.Dat."));
		
		//2015-07-27: neue spalte: leistungsdatum
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(BSRG__CONST.SQLFIELDMAP_FIELDS_RG.LEISTUNGSDAT_MIN._hash())), new MyE2_String("L.Dat."));
		
		
		//2019-06-28: anzeige fuer ueberschrittene faktfrist
		this.add_Component(new BSRG_K_ListAnzeigeAblaufFakturierungsFrist());

		
		//neu am 20100623 - Zahlungsbed. in der liste
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("A_ZAHLBED_TEIL")),	new MyE2_String("Zahlungsbedingungen"));
		
		//2012-01-18: keine mahnung
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("KEINE_MAHNUNGEN")),new MyE2_String("Keine Mahn."));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("SUBQUERY__KEINE_MAHNUNGEN")),new MyE2_String("Keine Mahn. Firma"));
		
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BUCHUNGSNUMMER")),	new MyE2_String("Buchungsnr."));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_VKOPF_RG")),		new MyE2_String("ID(Kopf)"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_ADRESSE")),		new MyE2_String("ID(Adresse)"));
		//this.add_Component(new BS_ListButton_OpenAdressMASK(oFM.get_("ID_ADRESSE")),		new MyE2_String("ID(Adresse)"));
		
		// spaltenbreiten
		//this.get__Comp("NAME1").EXT().set_oColExtent(new Extent(150));
		this.get__Comp("NAME2").EXT().set_oColExtent(new Extent(150));
		this.get__Comp("STRASSE").EXT().set_oColExtent(new Extent(150));
		this.get__Comp("ORT").EXT().set_oColExtent(new Extent(160));
		this.get__Comp("U_KUERZEL").EXT().set_oColExtent(new Extent(30));
		this.get__Comp("ID_VKOPF_RG").EXT().set_oColExtent(new Extent(80));
		this.get__Comp("ID_ADRESSE").EXT().set_oColExtent(new Extent(80));
		
		// sichtbarkeit
		this.get__Comp("ID_VKOPF_RG").EXT().set_bIsVisibleInList(false);
		this.get__Comp("ID_ADRESSE").EXT().set_bIsVisibleInList(false);
		
		
		//formatierung
		this.get__Comp("SUBQUERY__ENDBETRAG_RG").EXT().set_oLayout_ListTitelElement(E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOPHeader());
		
		// in die extender-spalte einen header-button vorsehen
		//jetzt der spalte mit dem Tochterlisten-extender einen header verpassen
		MyE2IF__Component   oExtender = this.get__Comp("EXTENDER");
		oExtender.EXT().set_oCompTitleInList(new E2_ListButtonExtendDaughterObjects_LISTHEADLINE(oNaviList));


		//einen multi-jumper in den kope der sprung-spalte setzen
		this.get__Comp(BSRG__CONST.HASH_KEY_JUMPER_RG_KOPF).EXT().set_oCompTitleInList(new BSRG_K_LIST_BT_JUMPER_HEAD(this));
		this.get__Comp(BSRG__CONST.HASH_KEY_JUMPER_RG_KOPF).EXT().set_oLayout_ListTitelElement(LayoutDataFactory.get_GridLayoutGridCenter_TOP(E2_INSETS.I_1_1_1_1, new E2_ColorDark()));
		
		this.set_List_EXPANDER_4_ComponentMAP(new BSRG_K_LIST_EXPANDER_4_ComponentMAP(KopfModulcontainerList,oNaviList,SETTING));
		
		//2015-07-27: leistungsdatum
		E2_ButtonListSorter  sortLeitungsDat = new E2_ButtonListSorter(new MyE2_String("Leist.Dat."), 
																		BSRG__CONST.SQLFIELDMAP_FIELDS_RG.LEISTUNGSDAT_MIN._query()+" ASC" ,
																		BSRG__CONST.SQLFIELDMAP_FIELDS_RG.LEISTUNGSDAT_MIN._query()+" DESC", 
																		oNaviList, false);
		this.get__Comp(BSRG__CONST.SQLFIELDMAP_FIELDS_RG.LEISTUNGSDAT_MIN._hash()).EXT().set_oCompTitleInList(sortLeitungsDat);
		//2015-07-27: leistungsdatum
		
		
		this.set_oSubQueryAgent(new BSRG_K_LIST_MarkerSubQueryAgent());
		this.add_oSubQueryAgent(new BS__SubQueryAgentShowDelGrundOncheckBox());


	
	}

	
	
	
	
	
//	/**
//	 * 
//	 * @return s RGOM_RG_OrigIsEmail - object, can be null
//	 * @throws myException
//	 */
//	public RGOM_RG_OrigIsEmail get_oRGOM_RG_OrigIsEmail() throws myException {
//		if (this.oRGOM_RG_OrigIsEmail==null) {
//			if (this.get_oInternalSQLResultMAP()!=null && S.isFull(this.get_oInternalSQLResultMAP().get_UnFormatedValue(BSRG__CONST.HASH_KEY_ID_VKOPF_RG_2))) {
//				this.oRGOM_RG_OrigIsEmail = new RGOM_RG_OrigIsEmail(this.get_oInternalSQLResultMAP().get_UnFormatedValue(BSRG__CONST.HASH_KEY_ID_VKOPF_RG_2));
//			}
//		}
//		return this.oRGOM_RG_OrigIsEmail;
//	}

	
	//2015-01-15: neuer konstruktor fuer die kopie
	public BSRG_K_LIST__ComponentMAP(SQLFieldMAP oFM) throws myException 
	{
		super(oFM);
	}
	
	
	//2015-01-15: eigene copy-methode, damit das erzeugte objekt nicht als E2_ComponentMAP erzeugt wird, sondern als BSRG_K_LIST__ComponentMAP
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		BSRG_K_LIST__ComponentMAP oRueck = null;
		try {
			oRueck = new BSRG_K_LIST__ComponentMAP(this.get_oSQLFieldMAP());
			BSRG_K_LIST__ComponentMAP.Copy_FieldsAndSettings(this, oRueck);
			return oRueck;
		} catch (myException e) {
			throw new myExceptionCopy("Error Copy BSRG_K_LIST__ComponentMAP");
		}
	}
	

	
}
