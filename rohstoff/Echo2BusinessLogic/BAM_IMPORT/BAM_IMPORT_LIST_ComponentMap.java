package rohstoff.Echo2BusinessLogic.BAM_IMPORT;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_ListButtonExtendDaughterObject;
import panter.gmbh.Echo2.ListAndMask.List.E2_ListButtonExtendDaughterObjects_LISTHEADLINE;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Column_IMMER_ENABLED;
import panter.gmbh.Echo2.components.MyE2_Row_EveryTimeEnabled;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentColumn;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.ARTIKELSTAMM.AS_BasicModulContainerLIST;
import rohstoff.Echo2BusinessLogic.ARTIKELSTAMM.AS_LIST_EXPANDER;
import rohstoff.Echo2BusinessLogic.LAGER_LISTE.BTN_NAVIGATOR__JUMP_TO_FUHRE;
import rohstoff.Echo2BusinessLogic.LAGER_LISTE.LAG_KTO_CONST;
import rohstoff.Echo2BusinessLogic.MAIL_INBOX.MAIL_INBOX_Const;

public class BAM_IMPORT_LIST_ComponentMap extends E2_ComponentMAP 
{
	BTN_BAM_IMPORT__JUMP_TO_FUHRE   	oNavigatorFuhrenliste  = null;
	BTN_BAM_IMPORT__JUMP_TO_WIEGEKARTE  oNavigatorWiegekartenListe = null;
	
	public BAM_IMPORT_LIST_ComponentMap(BAM_IMPORT_LIST_BasicModuleContainer oListContainer) throws myException
	{
		super(new BAM_IMPORT_LIST_SqlFieldMAP());
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(BAM_IMPORT_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(BAM_IMPORT_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

		// Spalte für die Anhänge
		MyE2_Row_EveryTimeEnabled 		oRowAnhaenge = new MyE2_Row_EveryTimeEnabled();
		this.add_Component(BAM_IMPORT_Const.ROW_SHOW_ANHANG_LISTE,oRowAnhaenge, new MyE2_String("Anhänge"),true);
		
		
		this.add_Component("EXTENDER",new E2_ListButtonExtendDaughterObject(false, null),new MyE2_String("+"));
		
		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_BAM_IMPORT")), new MyE2_String("Import-ID"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BELEGNUMMER")), new MyE2_String("Übertragene Belegnummer"));
		
		MyE2_DB_MultiComponentColumn oColDevice = new MyE2_DB_MultiComponentColumn();
		oColDevice.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BAM_ANGELEGT_AM")), new MyE2_String("Angelegt im Device am"),null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_5_0_5_0));
		oColDevice.add_Component(new MyE2_DB_Label_INROW(oFM.get_("DEVICE_ID")), new MyE2_String("Device-ID"),null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_5_0_5_0));
		this.add_Component(BAM_IMPORT_Const.COLUMN__DEVICE , oColDevice, new MyE2_String("Device-Info"));
		//this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BAM_ANGELEGT_AM")), new MyE2_String("Angelegt im Device am"));
		
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BAM_GESENDET_AM")), new MyE2_String("Gesendet vom Device am"));
		
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("ABGESCHLOSSEN")), new MyE2_String("Abgeschlossen"));
		
		MyE2_DB_MultiComponentColumn oColWK = new MyE2_DB_MultiComponentColumn();		
		MyE2_Row_EveryTimeEnabled oRowWKNavigator = new MyE2_Row_EveryTimeEnabled();
		oColWK.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_WIEGEKARTE")), new MyE2_String("ID-Wiegekarte"),null,LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_5_0_5_0));
		oColWK.add_Component(new MyE2_DB_Label_INROW(oFM.get_("LFD_NR")), new MyE2_String("WiegekarteNR"),null,LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_5_0_5_0));
		
		oColWK.add_Component(oRowWKNavigator, new MyE2_String("Sprünge"), BAM_IMPORT_Const.ROW_SHOW_WIEGEKARTE, LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_5_0_5_0));
		this.add_Component(BAM_IMPORT_Const.COLUMN_WIEGEKARTE_ID,oColWK, new MyE2_String("Wiegekarte"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("WE_WA")), new MyE2_String("WE/WA"));
		
		MyE2_DB_MultiComponentColumn oColInfo = new MyE2_DB_MultiComponentColumn();		
		oColInfo.add_Component(new MyE2_DB_Label_INROW(oFM.get_("KUNDEN_INFO")), new MyE2_String("Kunde"), null, LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_5_0_5_0));
		oColInfo.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ARTIKEL_INFO")), new MyE2_String("Artikel"), null, LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_5_0_5_0));
		oColInfo.add_Component(new MyE2_DB_Label_INROW(oFM.get_("GEWICHT_NACH_ABZUG_FUHRE")), new MyE2_String("Netto-Gewicht"), null, LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_5_0_5_0));
		this.add_Component(BAM_IMPORT_Const.COLUMN_WK_INFO , oColInfo, new MyE2_String("Wiegekarten-Info"));
		
		
//		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_VPOS_TPA_FUHRE")), new MyE2_String("Zugeordnete Fuhre"));
//		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_VPOS_TPA_FUHRE_ORT")), new MyE2_String("Zugeordneter Fuhren-Ort"));

		MyE2_DB_MultiComponentColumn oColFuhre = new MyE2_DB_MultiComponentColumn();		
		MyE2_Row_EveryTimeEnabled oRowFuhrenNavigator = new MyE2_Row_EveryTimeEnabled();
		oColFuhre.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_VPOS_TPA_FUHRE")), new MyE2_String("Fuhre"), null, LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_5_0_5_0));
		oColFuhre.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_VPOS_TPA_FUHRE_ORT")), new MyE2_String("Fuhrenort"), null, LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_5_0_5_0));
		oColFuhre.add_Component(oRowFuhrenNavigator, new MyE2_String("Sprünge"), BAM_IMPORT_Const.ROW_SHOW_FUHRE, LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_5_0_5_0));

		this.add_Component(BAM_IMPORT_Const.COLUMN_FUHRE_FUHRENORT,oColFuhre, new MyE2_String("Fuhre/Fuhrenort"));

		
		oNavigatorFuhrenliste = new BTN_BAM_IMPORT__JUMP_TO_FUHRE(this, null);
		oRowFuhrenNavigator.EXT().set_oCompTitleInList(oNavigatorFuhrenliste);
		oRowFuhrenNavigator.EXT().set_oLayout_ListTitelElement(LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_5_0_5_0));
		
		oNavigatorWiegekartenListe = new BTN_BAM_IMPORT__JUMP_TO_WIEGEKARTE(this, null);
		oRowWKNavigator.EXT().set_oCompTitleInList(oNavigatorWiegekartenListe);
		oRowWKNavigator.EXT().set_oLayout_ListTitelElement(LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_5_0_5_0));
		
		
		MyE2IF__Component   oExtender = this.get__Comp("EXTENDER");
		oExtender.EXT().set_oCompTitleInList(new E2_ListButtonExtendDaughterObjects_LISTHEADLINE(oListContainer.get_oNaviList(),oListContainer));
		this.set_List_EXPANDER_4_ComponentMAP(new BAM_IMPORT_LIST_EXPANDER(oListContainer));


		this.get__Comp_From_RealComponents("GEWICHT_NACH_ABZUG_FUHRE").EXT().set_oLayout_ListElement_AND_Titel(LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_5_0_5_0));

		
		
		this.set_oSubQueryAgent(new BAM_IMPORT_LIST_FORMATING_Agent());
	}

}
