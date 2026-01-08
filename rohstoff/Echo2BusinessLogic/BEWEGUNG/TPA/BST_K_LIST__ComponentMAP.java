package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_ListButtonExtendDaughterObject;
import panter.gmbh.Echo2.ListAndMask.List.E2_ListButtonExtendDaughterObjects_LISTHEADLINE;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList_StandardLayoutFactory;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SubQueryAgentShowDelGrundOncheckBox;
import rohstoff.Echo2BusinessLogic._4_ALL.DB_BUTTON_FIRMA_WITH_INFO;

public class BST_K_LIST__ComponentMAP extends E2_ComponentMAP {

	public BST_K_LIST__ComponentMAP(BST_K_LIST__ModulContainer	KopfModulcontainerList,E2_NavigationList oNaviList) throws myException 
	{
		super(new BST_K_LIST_SQLFieldMAP());
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		
		E2_CheckBoxForList oCB = new E2_CheckBoxForList();
		oCB.setFocusTraversalParticipant(false);
		
		this.add_Component("CHECK_BOX",oCB,new MyE2_String("?"));
		this.add_Component("MARKER",new E2_ButtonListMarker(),new MyE2_String("?"));
		this.add_Component(BST__CONST.HASH_KEY_ANZEIGE_LOCKED,new MyE2_Label(BST__CONST.LABEL_EMPTY),new MyE2_String("A"));
		this.add_Component(BST__CONST.HASH_KEY_ANZEIGE_POSITIONS,new MyE2_Row(MyE2_Row.STYLE_NO_BORDER()),new MyE2_String("P"));
		
		this.add_Component(BST__CONST.HASH_KEY_ANZEIGE_LADESTAUS,new MyE2_Row(MyE2_Row.STYLE_NO_BORDER()),new MyE2_String("L"));   //NEU10
		
		//2012-08-01: jumper in fuhrenzentrale
		BST_K_LIST_BT_JUMP_TO_Fuhren bt_jump = new BST_K_LIST_BT_JUMP_TO_Fuhren();
		bt_jump.EXT().set_oCompTitleInList(new BST_K_LIST_BT_JUMP_TO_Fuhren_multi(oNaviList));
		this.add_Component(BST__CONST.HASH_KEY_LIST_BT_JUMP_TPA_TO_FUHRE,bt_jump,new MyE2_String("Sprung in Fuhren"));   //NEU10
		
		this.add_Component("EXTENDER",new E2_ListButtonExtendDaughterObject(false, null),new MyE2_String("+"));
	
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ERSTELLUNGSDATUM")),	new MyE2_String("Erstellt am"));
		
		//2011-03-02: button-with-info - komponente, die name1 anzeiget und adresse oeffnet
		this.add_Component(	new DB_BUTTON_FIRMA_WITH_INFO(oFM.get_("INFO_ID_ADRESSE"),null,"NAME1","JT_VKOPF_TPA.NAME1 ASC","JT_VKOPF_TPA.NAME1 DESC",200,100), 
				new MyE2_String("Spedition Name 1"),	true,true, new MyE2_String("Spedition dieses Transportauftrags"),null,null);
//		((DB_BUTTON_FIRMA_WITH_INFO)this.get__Comp("INFO_ID_ADRESSE")).EXT_DB().set_cSortAusdruckFuerSortbuttonDOWN("JT_VKOPF_TPA.NAME1 DESC");
//		((DB_BUTTON_FIRMA_WITH_INFO)this.get__Comp("INFO_ID_ADRESSE")).EXT_DB().set_cSortAusdruckFuerSortbuttonUP("JT_VKOPF_TPA.NAME1 ASC");
		
		
//		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("NAME1")),			new MyE2_String("Spedition Name 1"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("NAME2")),				new MyE2_String("Name 2"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("STRASSE")),			new MyE2_String("Strasse"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ORT")),				new MyE2_String("Ort "));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("U_KUERZEL")),			new MyE2_String("K"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BUCHUNGSNUMMER")),		new MyE2_String("Buch.Nr"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_VKOPF_TPA")),		new MyE2_String("ID(Kopf)"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_ADRESSE")),			new MyE2_String("ID(Adresse)"));
		//this.add_Component(new BS_ListButton_OpenAdressMASK(oFM.get_("ID_ADRESSE")),  new MyE2_String("ID(Adresse)"));
		
		// spaltenbreiten
		//this.get__Comp("NAME1").EXT().set_oColExtent(new Extent(150));
		this.get__Comp("NAME2").EXT().set_oColExtent(new Extent(150));
		this.get__Comp("STRASSE").EXT().set_oColExtent(new Extent(150));
		this.get__Comp("ORT").EXT().set_oColExtent(new Extent(160));
		this.get__Comp("U_KUERZEL").EXT().set_oColExtent(new Extent(30));
		this.get__Comp("ID_VKOPF_TPA").EXT().set_oColExtent(new Extent(80));
		this.get__Comp("ID_ADRESSE").EXT().set_oColExtent(new Extent(80));
		
		// sichtbarkeit
		this.get__Comp("ID_VKOPF_TPA").EXT().set_bIsVisibleInList(false);
		this.get__Comp("ID_ADRESSE").EXT().set_bIsVisibleInList(false);
		
		this.get__Comp("BUCHUNGSNUMMER").EXT().set_oLayout_ListElement(E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOP());
		this.get__Comp("BUCHUNGSNUMMER").EXT().set_oLayout_ListTitelElement(E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOPHeader());
		
		
		// in die extender-spalte einen header-button vorsehen
		//jetzt der spalte mit dem Tochterlisten-extender einen header verpassen
		MyE2IF__Component   oExtender = this.get__Comp("EXTENDER");
		oExtender.EXT().set_oCompTitleInList(new E2_ListButtonExtendDaughterObjects_LISTHEADLINE(oNaviList));

		this.set_List_EXPANDER_4_ComponentMAP(new BST_K_LIST_EXPANDER_4_ComponentMAP(KopfModulcontainerList,oNaviList));
		
		this.set_oSubQueryAgent(new BST_K_LIST_MarkerSubQueryAgent());
		this.add_oSubQueryAgent(new BS__SubQueryAgentShowDelGrundOncheckBox());

	
	}

}
