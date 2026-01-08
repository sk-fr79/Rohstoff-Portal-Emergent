package rohstoff.Echo2BusinessLogic.ARTIKELSTAMM;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_V2;
import panter.gmbh.Echo2.ListAndMask.QUERYAGENT_MarkiereInaktiveInNaviList;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_ListButtonExtendDaughterObject;
import panter.gmbh.Echo2.ListAndMask.List.E2_ListButtonExtendDaughterObjects_LISTHEADLINE;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList_StandardLayoutFactory;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class AS_LIST_ComponentMAP extends E2_ComponentMAP_V2
{


	
	public AS_LIST_ComponentMAP(AS_BasicModulContainerLIST oListContainer) throws myException
	{
		super(new AS_LIST_SQLFieldMap());
		
		this.add_Component("NAME_OF_CHECKBOX_IN_LIST", new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component("NAME_OF_LISTMARKER_IN_LIST", new E2_ButtonListMarker(),new MyE2_String("?"));
		
		this.add_Component(AS_LIST__DownloadAttechments.LISTKEY4COMPONENTMAP, new AS_LIST__DownloadAttechments(), AS_LIST__DownloadAttechments.LISTINFO4COMPONENTMAP);
		
		this.add_Component("EXTENDER",new E2_ListButtonExtendDaughterObject(false, null),new MyE2_String("+"))._setLongText4ColumnSelection(S.ms("Ausklapper Subliste"));

		
		SQLFieldMAP oSQLFM = this.get_oSQLFieldMAP();

//		bibE2.DEBUG_AUSGABE_HASHKEYS(oSQLFM);
		
		//2012-08-01: jump-button in fuhre
		this.add_Component(AS___CONST.HASH_KEY_LIST_BUTTON_JUMP_TO_FUHRE, new AS_LIST_BT_JUMP_to_FuhrenMitSorte(),new MyE2_String("F"))._setLongText4ColumnSelection(S.ms("Sprung zur Fuhrenzentrale"));
		
		
		this.add_Component(new AS_LIST_ComponentShowUndefinedDanger(), true)._setLongText4ColumnSelection(S.ms("Detailierte Gefahrgut-Anzeige"));
		
		this.add_Component(new AS_LIST_AnzeigeLaenderRcStatus(),true)._setLongText4ColumnSelection(S.ms("Länder-RC"));
		
		//2016-08-08: placeholder-component fuer die info, ob eine sorte eine defekte zolltarifnummer hat
		this.add_Component(new AS_LIST_show_inactiv_zolltarifnummer(oSQLFM.get_("ID2")),new MyE2_String("ZT-Info"))._setLongText4ColumnSelection(S.ms("Information Zolltarifnummer"));
				
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("ANR1")),new MyE2_String("ANR 1"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("ARTBEZ1")),new MyE2_String("Artikelbez.1"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("ARTBEZ2")),new MyE2_String("Artikelbez.2"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("E_EINHEITKURZ")),new MyE2_String("Einheit"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("MENGENDIVISOR")),new MyE2_String("Mengendiv"));
		this.add_Component(AS___CONST.LABEL_EINHEIT_PREIS,new MyE2_Label("-"),new MyE2_String("Preiseinheit"));
		this.add_Component(new MyE2_DB_CheckBox(oSQLFM.get_("AKTIV")),new MyE2_String("Akt."));
		this.add_Component(new MyE2_DB_CheckBox(oSQLFM.get_("DIENSTLEISTUNG")),new MyE2_String("Dienstl."));
		this.add_Component(new MyE2_DB_CheckBox(oSQLFM.get_("IST_PRODUKT")),new MyE2_String("Produkt"));
		this.add_Component(new MyE2_DB_CheckBox(oSQLFM.get_(_DB.ARTIKEL$END_OF_WASTE)),new MyE2_String("EOW"));
		this.add_Component(new MyE2_DB_CheckBox(oSQLFM.get_("GEFAHRGUT")),new MyE2_String("Gefahrg."));
		
		//2013-09-19: den leergutschalter in die maske einfuehren
		this.add_Component(new MyE2_DB_CheckBox(oSQLFM.get_(_DB.ARTIKEL$IST_LEERGUT)), new MyE2_String("Leergut"));

		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("GRUPPE_KURZ")),new MyE2_String("Artikelgruppe"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("CODE_GES_IN")),new MyE2_String("AVV (Bar)"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("EAK_CODE_IN_CODE"),true),new MyE2_String("AVV (Bar)Text "));
		
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("CODE_GES_OUT")),new MyE2_String("AVV ex-Lager"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("EAK_CODE_OUT_CODE"),true),new MyE2_String("AVV ex-Lager Text "));
		
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("ZT_NUMMER")),new MyE2_String("Zolltarif"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("ZOLLTARIFNOTIZ"),true),new MyE2_String("Zolltarif-Text"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("BASEL_CODE")),new MyE2_String("Baselcode"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("BASEL_NOTIZ"),true),new MyE2_String("Basel-Text"));

		MyE2_DB_CheckBox cb_zt = new MyE2_DB_CheckBox(oSQLFM.get_("ZT_REVERSE_CHARGE"));
		cb_zt.EXT().set_oLayout_ListElement(MyE2_Grid.LAYOUT_CENTER_TOP(E2_INSETS.I(2,2,2,2)));
		
		this.add_Component(cb_zt,new MyE2_String("Zolltarif ist RC (für D)"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("ID_ARTIKEL")),new MyE2_String("ID-Artikel"));
		
		
//		//testsummen
//		this.get__Comp(_DB.ARTIKEL$MENGENDIVISOR).EXT().get_vZusatzKomponentenInListenTitel().add(new 
//				LIST_TITEL_CalcButton(	this.get__Comp(_DB.ARTIKEL$MENGENDIVISOR), 
//										oListContainer.get_MODUL_IDENTIFIER(), 
//										new MyE2_String("Summe Mengendiv"),
//										new MyE2_String("Summe Mengendiv"),
//										new MyE2_String("Summe Mengendiv"),
//										1, true)
//				);
				

		
		this.get__Comp("ANR1").EXT().set_oLayout_ListElement(E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOP());
		
		this.get__Comp("E_EINHEITKURZ").EXT().set_oLayout_ListElement(E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutCenterTOP());
		this.get__Comp(AS___CONST.LABEL_EINHEIT_PREIS).EXT().set_oLayout_ListElement(E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutCenterTOP());

		this.get__Comp("DIENSTLEISTUNG").EXT().set_oLayout_ListElement(E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutCenterTOP());
		this.get__Comp("AKTIV").EXT().set_oLayout_ListElement(E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutCenterTOP());
		this.get__Comp("GEFAHRGUT").EXT().set_oLayout_ListElement(E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutCenterTOP());
		
		((MyE2_DB_Label_INROW)this.get__Comp("ARTBEZ1")).setLineWrap(true);
		((MyE2_DB_Label_INROW)this.get__Comp("ARTBEZ2")).setLineWrap(true);
		((MyE2_DB_Label_INROW)this.get__Comp("GRUPPE_KURZ")).setLineWrap(true);
		
		MyE2IF__Component   oExtender = this.get__Comp("EXTENDER");
		oExtender.EXT().set_oCompTitleInList(new E2_ListButtonExtendDaughterObjects_LISTHEADLINE(oListContainer.get_oNaviList(),oListContainer));

		this.set_List_EXPANDER_4_ComponentMAP(new AS_LIST_EXPANDER(oListContainer));
		
		
		this.set_oSubQueryAgent(new AS_LIST_ComponentMAP_SubqueryAgent());
		this.add_oSubQueryAgent(new QUERYAGENT_MarkiereInaktiveInNaviList("AKTIV"));
	}

}
