package rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_ListButtonExtendDaughterObject;
import panter.gmbh.Echo2.ListAndMask.List.E2_ListButtonExtendDaughterObjects_LISTHEADLINE;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ListButton_OpenAdressMASK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SubQueryAgentShowDelGrundOncheckBox;
import rohstoff.Echo2BusinessLogic._4_ALL.DB_BUTTON_FIRMA_WITH_INFO;

public class BSA_K_LIST__ComponentMAP extends E2_ComponentMAP {

	public BSA_K_LIST__ComponentMAP(BSA_K_LIST__ModulContainer	KopfModulcontainerList,E2_NavigationList oNaviList,BS__SETTING SETTING) throws myException 
	{
		super(new BSA_K_LIST_SQLFieldMAP(SETTING));
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		
		E2_CheckBoxForList oCB = new E2_CheckBoxForList();
		oCB.setFocusTraversalParticipant(false);
		
		this.add_Component("CHECK_BOX",oCB,new MyE2_String("?"));
		this.add_Component("MARKER",new E2_ButtonListMarker(),new MyE2_String("?"));
		this.add_Component(BSA__CONST.HASH_KEY_ANZEIGE_LOCKED,new MyE2_Label(BSA__CONST.LABEL_EMPTY),new MyE2_String("A"));
		this.add_Component(BSA__CONST.HASH_KEY_ANZEIGE_POSITIONS,new MyE2_Row(MyE2_Row.STYLE_NO_BORDER()),new MyE2_String("P"));
		
		this.add_Component("EXTENDER",new E2_ListButtonExtendDaughterObject(false, null),new MyE2_String("+"));
	

		
		this.add_Component(	new DB_BUTTON_FIRMA_WITH_INFO(oFM.get_("INFO_ID_ADRESSE"),null,"NAME1","JT_VKOPF_STD.NAME1 ASC","JT_VKOPF_STD.NAME1 DESC",200,100), 
				new MyE2_String("Name "+SETTING.get_cTextFuerAdressArt()),	true,true, new MyE2_String("Name "+SETTING.get_cTextFuerAdressArt()),null,null);

//		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("NAME1")),			new MyE2_String("Name "+SETTING.get_cTextFuerAdressArt()));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("NAME2")),			new MyE2_String("Name 2"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("STRASSE")),			new MyE2_String("Strasse"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ORT")),				new MyE2_String("Ort "+SETTING.get_cTextFuerAdressArt()));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("U_KUERZEL")),		new MyE2_String("K"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ERSTELLUNGSDATUM")),	new MyE2_String("Erstellt"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BUCHUNGSNUMMER")),	new MyE2_String("Buchungsnr."));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_VKOPF_STD")),		new MyE2_String("ID(Kopf)"));
		this.add_Component(new BS_ListButton_OpenAdressMASK(oFM.get_("ID_ADRESSE")),		new MyE2_String("ID(Adresse)"));
		
		// spaltenbreiten
		//this.get__Comp("NAME1").EXT().set_oColExtent(new Extent(150));
		this.get__Comp("NAME2").EXT().set_oColExtent(new Extent(150));
		this.get__Comp("STRASSE").EXT().set_oColExtent(new Extent(150));
		this.get__Comp("ORT").EXT().set_oColExtent(new Extent(160));
		this.get__Comp("U_KUERZEL").EXT().set_oColExtent(new Extent(30));
		this.get__Comp("ID_VKOPF_STD").EXT().set_oColExtent(new Extent(80));
		this.get__Comp("ID_ADRESSE").EXT().set_oColExtent(new Extent(80));
		
		// sichtbarkeit
		this.get__Comp("ID_VKOPF_STD").EXT().set_bIsVisibleInList(false);
		this.get__Comp("ID_ADRESSE").EXT().set_bIsVisibleInList(false);
		
		
		
		// in die extender-spalte einen header-button vorsehen
		//jetzt der spalte mit dem Tochterlisten-extender einen header verpassen
		MyE2IF__Component   oExtender = this.get__Comp("EXTENDER");
		oExtender.EXT().set_oCompTitleInList(new E2_ListButtonExtendDaughterObjects_LISTHEADLINE(oNaviList));

		this.set_List_EXPANDER_4_ComponentMAP(new BSA_K_LIST_EXPANDER_4_ComponentMAP(KopfModulcontainerList,oNaviList,SETTING));
		
		this.set_oSubQueryAgent(new BSA_K_LIST_MarkerSubQueryAgent());
		this.add_oSubQueryAgent(new BS__SubQueryAgentShowDelGrundOncheckBox());

	}

}
