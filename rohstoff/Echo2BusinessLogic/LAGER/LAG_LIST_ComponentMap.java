package rohstoff.Echo2BusinessLogic.LAGER;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class LAG_LIST_ComponentMap extends E2_ComponentMAP 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 873320494293718631L;

	// Die Component-Map bekommt eine Referenz auf den Vertragsmengen-Ermittler
	private LAG_LagerMengenErmittlung m_oVertragsMengen = null;
	
	// die Component-Map bekommt eine Referenz auf den Saldo-Ermittler
	private LAG_LagerSaldoErmittlung  m_oSaldoErmittlungen = null;
	
	
	public LAG_LIST_ComponentMap(LAG_LagerMengenErmittlung oVertragsMengen, LAG_LagerSaldoErmittlung oSaldoErmittlung) throws myException
	{
		super(new LAG_LIST_SqlFieldMAP());
		
		// objekt zur Vertragsmengen-Ermittlung merken
		this.m_oVertragsMengen = oVertragsMengen;
		this.m_oSaldoErmittlungen = oSaldoErmittlung;
		
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(LAG_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(LAG_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_LAGER_KONTO")), new MyE2_String("Lagerkonto ID"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ADDRESS_INFO")), new MyE2_String("Lager"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ART_INFO")), new MyE2_String("Sorte"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_ARTIKEL_SORTE")), new MyE2_String("ID Sorte"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("LAGERBESTAND")), new MyE2_String("Lagerbestand"));
		
//		this.add_Component(LAG_LIST_CONST.HASH_SaldoInventur, new MyE2_Label(),new MyE2_String("Lagerbestand"));
		this.add_Component(LAG_LIST_CONST.HASH_InventurDatum, new MyE2_Label(),new MyE2_String("Inventurdatum"));
		this.add_Component(LAG_LIST_CONST.HASH_InventurMenge, new MyE2_Label(),new MyE2_String("Inventurmenge"));
		
		this.add_Component(LAG_LIST_CONST.HASH_GesamtRestmenge, new MyE2_Label(),new MyE2_String("Mögl. Saldo mit Kontrakten"));
		this.add_Component(LAG_LIST_CONST.HASH_EKLagermenge, new MyE2_Label(),new MyE2_String("Summe EK-Kontr."));
		this.add_Component(LAG_LIST_CONST.HASH_EKRestmenge, new MyE2_Label(),new MyE2_String("Restmengen EK-Kontr."));
		this.add_Component(LAG_LIST_CONST.HASH_VKLagermenge, new MyE2_Label(),new MyE2_String("Summe VK-Kontr."));
		this.add_Component(LAG_LIST_CONST.HASH_VKRestmenge, new MyE2_Label(),new MyE2_String("Restmengen VK-Kontr."));
		
		
		this.get__Comp("ID_LAGER_KONTO").EXT().set_bIsVisibleInList(false);

		
		// Gridlayout für den Header
		GridLayoutData  GLHead = new GridLayoutData();
		GLHead.setAlignment(new Alignment(Alignment.RIGHT, Alignment.CENTER));
		GLHead.setBackground(new E2_ColorDark());
		
		// Gridlayout für die Liste
		GridLayoutData  GLList = new GridLayoutData();
		GLList.setAlignment(new Alignment(Alignment.RIGHT, Alignment.CENTER));
		

		this.get(LAG_LIST_CONST.HASH_InventurDatum).EXT().set_oLayout_ListTitelElement(GLHead);
		this.get(LAG_LIST_CONST.HASH_InventurDatum).EXT().set_oLayout_ListElement(GLList);
		this.get(LAG_LIST_CONST.HASH_InventurDatum).EXT().set_ToolTipStringForListHeader(new MyE2_String("Lagermenge (seit der letzten Inventur)"));

		this.get(LAG_LIST_CONST.HASH_InventurMenge).EXT().set_oLayout_ListTitelElement(GLHead);
		this.get(LAG_LIST_CONST.HASH_InventurMenge).EXT().set_oLayout_ListElement(GLList);
		this.get(LAG_LIST_CONST.HASH_InventurMenge).EXT().set_ToolTipStringForListHeader(new MyE2_String("Lagermenge (seit der letzten Inventur)"));

		
		

		this.get(LAG_LIST_CONST.HASH_EKLagermenge).EXT().set_oLayout_ListTitelElement(GLHead);
		this.get(LAG_LIST_CONST.HASH_EKLagermenge).EXT().set_oLayout_ListElement(GLList);
		this.get(LAG_LIST_CONST.HASH_EKLagermenge).EXT().set_ToolTipStringForListHeader(new MyE2_String("Gesamtmenge der offenen EK-Kontrakte."));
		
		this.get(LAG_LIST_CONST.HASH_GesamtRestmenge).EXT().set_oLayout_ListTitelElement(GLHead);
		this.get(LAG_LIST_CONST.HASH_GesamtRestmenge).EXT().set_oLayout_ListElement(GLList);
		this.get(LAG_LIST_CONST.HASH_GesamtRestmenge).EXT().set_ToolTipStringForListHeader(new MyE2_String("Mögliche Gesamtmenge, d.h. aktueller Saldo plus der Restmengen der EK-Kontrakte, abzüglich der Restmengen der VK-Kontrakte."));
		
		this.get(LAG_LIST_CONST.HASH_EKRestmenge).EXT().set_oLayout_ListTitelElement(GLHead);
		this.get(LAG_LIST_CONST.HASH_EKRestmenge).EXT().set_oLayout_ListElement(GLList);
		this.get(LAG_LIST_CONST.HASH_EKRestmenge).EXT().set_ToolTipStringForListHeader(new MyE2_String("Restmengen der offenen EK-Kontrakte."));
		
		
		this.get(LAG_LIST_CONST.HASH_VKLagermenge).EXT().set_oLayout_ListTitelElement(GLHead);
		this.get(LAG_LIST_CONST.HASH_VKLagermenge).EXT().set_oLayout_ListElement(GLList);
		this.get(LAG_LIST_CONST.HASH_VKLagermenge).EXT().set_ToolTipStringForListHeader(new MyE2_String("Gesamtmenge der offenen VK-Kontrakte"));
		
		this.get(LAG_LIST_CONST.HASH_VKRestmenge).EXT().set_oLayout_ListTitelElement(GLHead);
		this.get(LAG_LIST_CONST.HASH_VKRestmenge).EXT().set_oLayout_ListElement(GLList);
		this.get(LAG_LIST_CONST.HASH_VKRestmenge).EXT().set_ToolTipStringForListHeader(new MyE2_String("Restmengen der offenen VK-Kontrakte."));

		
		
		// Layout der Datenspalten
		GridLayoutData layout = new GridLayoutData();
		layout.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
		
		
		this.get__Comp("ID_LAGER_KONTO").EXT().set_oLayout_ListTitelElement(LayoutDataFactory.get_GridLayoutGridLeft_DARK(null));
		this.get__Comp("ID_LAGER_KONTO").EXT().set_ToolTipStringForListHeader(new MyE2_String("ID der Konto-Buchung"));
		
		this.get__Comp("ADDRESS_INFO").EXT().set_oLayout_ListElement(layout);
		this.get__Comp("ADDRESS_INFO").EXT().set_oLayout_ListTitelElement(LayoutDataFactory.get_GridLayoutGridLeft_DARK(null));
		this.get__Comp("ADDRESS_INFO").EXT().set_ToolTipStringForListHeader(new MyE2_String("Lager-Adresse"));

		this.get__Comp("ART_INFO").EXT().set_oLayout_ListElement(layout);
		this.get__Comp("ART_INFO").EXT().set_oLayout_ListTitelElement(LayoutDataFactory.get_GridLayoutGridLeft_DARK(null));
		this.get__Comp("ART_INFO").EXT().set_ToolTipStringForListHeader(new MyE2_String("Sortennr. und Bezeichnung."));
		
		this.get__Comp("ID_ARTIKEL_SORTE").EXT().set_oLayout_ListTitelElement(LayoutDataFactory.get_GridLayoutGridLeft_DARK(null));
		this.get__Comp("ID_ARTIKEL_SORTE").EXT().set_ToolTipStringForListHeader(new MyE2_String("ArtikelID"));

		this.get__Comp("LAGERBESTAND").EXT().set_oLayout_ListTitelElement(GLHead);
		this.get__Comp("LAGERBESTAND").EXT().set_oLayout_ListElement(GLList);
		this.get__Comp("LAGERBESTAND").EXT().set_ToolTipStringForListHeader(new MyE2_String("Lagermenge (seit der letzten Inventur)"));

		// Defaultbreite der Spalten
		this.get__Comp("ADDRESS_INFO").EXT().set_oColExtent(new Extent(400));
		this.get__Comp("ART_INFO").EXT().set_oColExtent(new Extent(250));
		
		String sSortAdresse   = this.get_hmRealDBComponents().get("ADDRESS_INFO").EXT_DB().get_oSQLField().get_cFieldAusdruck();
		this.get_hmRealDBComponents().get("ADDRESS_INFO").EXT_DB().set_cSortAusdruckFuerSortbuttonUP(sSortAdresse  + ", ANR1 ASC");
		this.get_hmRealDBComponents().get("ADDRESS_INFO").EXT_DB().set_cSortAusdruckFuerSortbuttonDOWN( sSortAdresse + "DESC , ANR1 ASC");
		this.get__Comp_From_RealComponents("ADDRESS_INFO").EXT().set_ToolTipStringForListHeader(new MyE2_String("Sortiert nach Lager (auf/ab) und Sorte (auf)"));

		String sSortSorte   = this.get_hmRealDBComponents().get("ART_INFO").EXT_DB().get_oSQLField().get_cFieldAusdruck();
		this.get_hmRealDBComponents().get("ART_INFO").EXT_DB().set_cSortAusdruckFuerSortbuttonUP(sSortSorte  + ", " + sSortAdresse);
		this.get_hmRealDBComponents().get("ART_INFO").EXT_DB().set_cSortAusdruckFuerSortbuttonDOWN( sSortSorte + " DESC, " + sSortAdresse);
		this.get__Comp_From_RealComponents("ART_INFO").EXT().set_ToolTipStringForListHeader(new MyE2_String("Sortiert nach Sorte (auf/ab) und Lager (auf)"));
	
		
		this.set_oSubQueryAgent(new LAG_LIST_FORMATING_Agent( m_oVertragsMengen, m_oSaldoErmittlungen));
	}

}
