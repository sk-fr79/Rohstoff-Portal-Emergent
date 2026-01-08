package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_ListButtonExtendDaughterObject;
import panter.gmbh.Echo2.ListAndMask.List.E2_ListButtonExtendDaughterObjects_LISTHEADLINE;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList_StandardLayoutFactory;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_LIST_comp_anlagen_email;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_LabelButton4ListLineSelection;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.myCONST_ENUM.VORGANGSART;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_RG;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SubQueryAgentShowDelGrundOncheckBox;
import rohstoff.Echo2BusinessLogic._4_ALL.DB_BUTTON_FIRMA_WITH_INFO;

public class KFIX_K_L__ComponentMAP extends E2_ComponentMAP {

	public KFIX_K_L__ComponentMAP(KFIX_K_L__ModulContainer	KopfModulcontainerList,E2_NavigationList oNaviList,VORGANGSART oBelegTyp) throws myException 
	{
		super(new KFIX_K_L__SQLFieldMAP(oBelegTyp));
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		
		E2_CheckBoxForList oCB = new E2_CheckBoxForList();
		oCB.setFocusTraversalParticipant(false);
		
		this.add_Component("CHECK_BOX",oCB,new MyE2_String("?"));
		this.add_Component("MARKER",new E2_ButtonListMarker(),new MyE2_String("?"));


	
//		this.add_Component(BSK__CONST_NG.HASH_KEY_ANZEIGE_LOCKED,new MyE2_Label(BSK__CONST_NG.LABEL_EMPTY),new MyE2_String("A"));
	
		this.add_Component(KFIX___CONST.HASH_KEY_ANZEIGE_LOCKED,new KFIX_K_L_BT_Schliessen_Kontrakte(oNaviList),new MyE2_String("A"));
		
		
		this.add_Component(KFIX___CONST.HASH_KEY_ANZEIGE_POSITIONS,new MyE2_LabelButton4ListLineSelection()._colExt(new Extent(50))._titleLayout(1, 2, 1, 1, new Alignment(Alignment.CENTER, Alignment.TOP), new E2_ColorDark()),new MyE2_String("P"));
		this.add_Component(KFIX___CONST.HASH_KEY_ANZEIGE_FIX, new MyE2_LabelButton4ListLineSelection()._set_icon(KFIX___CONST.LABEL_EMPTY), new MyE2_String("F"));
		
		this.add_Component(KFIX___CONST.HASH_KEY_ANZEIGE_FIX_GESAMT_MENGE, new MyE2_LabelButton4ListLineSelection(), new MyString("F. Menge")
				,true, null,E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOPHeader(),
				E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOP());
		
		this.add_Component(KFIX___CONST.HASH_KEY_ANZEIGE_FIX_AKTUELL_MENGE, new MyE2_LabelButton4ListLineSelection(), new MyString("Fixiert")
				,true,null,E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOPHeader(),
				E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOP());
				
		
		this.add_Component("EXTENDER",new E2_ListButtonExtendDaughterObject(false, null),new MyE2_String("+"));

		//2011-12-05:  neuer sprungbutton von kontraktkopf-liste in fuhrenzentrale
		this.add_Component(KFIX___CONST.BUTTON_JUMP_VON_KOPF_TO_FUHRENZENTRALE,new KFIX_K_L_JUMPER_TO_Fuhre(),new MyE2_String("SF"));
		
		this.add_Component(KFIX___CONST.HASH_KEY_BUTTON_ANLAGE, new E2_LIST_comp_anlagen_email(oNaviList),new MyE2_String("A/E"),	true,new MyE2_String("Zeige Anlagen- und eMail-Popup"),null,null);
		
		//2011-03-02: button-with-info - komponente, die name1 anzeiget und adresse oeffnet
		MyE2_String cTextInfo = new MyE2_String(oBelegTyp.equals(myCONST.VORGANGSART_EK_KONTRAKT)?"Name 1 (Lieferant)":"Name 1 (Abnehmer)");
		this.add_Component(	new DB_BUTTON_FIRMA_WITH_INFO(oFM.get_("INFO_ID_ADRESSE"),null,"NAME1","JT_VKOPF_KON.NAME1 ASC","JT_VKOPF_KON.NAME1 DESC",200,100), 
				cTextInfo,	true,true, cTextInfo,null,null);

		
		String adresseStart = "";
		if(oBelegTyp.equals(myCONST.VORGANGSART_EK_KONTRAKT)){
			adresseStart = "Lieferant";
		}else{
			adresseStart = "Kunde";
		}
		
		
		//this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("NAME1")),			new MyE2_String("Name "+SETTING.get_cTextFuerAdressArt()));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("NAME2")),			new MyE2_String("Name 2"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("STRASSE")),		new MyE2_String("Strasse"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ORT")),			new MyE2_String("Ort "+adresseStart));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("C_HAENDLER")),		new MyE2_String("Händler"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("C_BETREUER_FIRMA")),		new MyE2_String("Betreuer(1) Firma"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("U_KUERZEL")),		new MyE2_String("K"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ERSTELLUNGSDATUM")),	new MyE2_String("Erstellt am"),
										true,true,E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOPHeader(),
										E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOP());
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(VKOPF_RG.druckdatum.fn())),	new MyE2_String("Druckdatum"),
										true,true,E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOPHeader(),
										E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOP());

		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BUCHUNGSNUMMER")),	new MyE2_String("Buchungsnr."),
													true,true,E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOPHeader(),
													E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOP());
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_VKOPF_KON")),		new MyE2_String("ID(Kopf)"),
													true,true,E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOPHeader(),
													E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOP());

		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_ADRESSE")),		new MyE2_String("ID(Adresse)"),
													true,true,E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOPHeader(),
													E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOP());

		
//		this.add_Component(new BS_ListButton_OpenAdressMASK(oFM.get_("ID_ADRESSE")),  new MyE2_String("ID(Adresse)"),
//													true,true,E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOPHeader(),
//													E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOP());
		
		// spaltenbreiten
		//this.get__Comp("NAME1").EXT().set_oColExtent(new Extent(150));
		this.get__Comp(KFIX___CONST.BUTTON_JUMP_VON_KOPF_TO_FUHRENZENTRALE).EXT().set_oColExtent(new Extent(20));
		this.get__Comp("NAME2").EXT().set_oColExtent(new Extent(150));
		this.get__Comp("STRASSE").EXT().set_oColExtent(new Extent(150));
		this.get__Comp("ORT").EXT().set_oColExtent(new Extent(160));
		this.get__Comp("U_KUERZEL").EXT().set_oColExtent(new Extent(30));
		this.get__Comp("ID_VKOPF_KON").EXT().set_oColExtent(new Extent(80));
		this.get__Comp("ID_ADRESSE").EXT().set_oColExtent(new Extent(80));
		
		// sichtbarkeit
		this.get__Comp("ID_VKOPF_KON").EXT().set_bIsVisibleInList(false);
		this.get__Comp("ID_ADRESSE").EXT().set_bIsVisibleInList(false);
		
		
		
		// in die extender-spalte einen header-button vorsehen
		//jetzt der spalte mit dem Tochterlisten-extender einen header verpassen
		MyE2IF__Component   oExtender = this.get__Comp("EXTENDER");
		oExtender.EXT().set_oCompTitleInList(new E2_ListButtonExtendDaughterObjects_LISTHEADLINE(oNaviList));

		this.set_List_EXPANDER_4_ComponentMAP(new KFIX_K_L_EXPANDER_4_ComponentMAP_NG(KopfModulcontainerList,oNaviList,oBelegTyp));
		
		this.set_oSubQueryAgent(new KFIX_K_L_MarkerSubQueryAgent());
		this.add_oSubQueryAgent(new BS__SubQueryAgentShowDelGrundOncheckBox());

	
	}

}
