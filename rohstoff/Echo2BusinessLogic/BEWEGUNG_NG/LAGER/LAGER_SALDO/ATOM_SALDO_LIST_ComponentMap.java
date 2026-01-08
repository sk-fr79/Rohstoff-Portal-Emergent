package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_SALDO;

import java.util.ArrayList;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.SALDO.ATOM_LagerSaldoErmittlung;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.SALDO.VERTRAGSMENGEN.ATOM_Lager_KontraktmengenErmittlung;

public class ATOM_SALDO_LIST_ComponentMap extends E2_ComponentMAP 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 873320494293718631L;

	// Die Component-Map bekommt eine Referenz auf den Vertragsmengen-Ermittler
	private ATOM_Lager_KontraktmengenErmittlung m_oVertragsMengen = null;
	
	// die Component-Map bekommt eine Referenz auf den Saldo-Ermittler
	private ATOM_LagerSaldoErmittlung  m_oSaldoErmittlungen = null;
//	private ATOM_LagerSaldoErmittlung  m_oSaldoErmittlungenAdditional = null;
	
	
	public ATOM_SALDO_LIST_ComponentMap(ATOM_Lager_KontraktmengenErmittlung oVertragsMengen, 
										ATOM_LagerSaldoErmittlung oSaldoErmittlung,
										ArrayList<ATOM_LagerSaldoErmittlung> alSaldoErmittlungAdd
										 ) throws myException
	{
		super(new ATOM_SALDO_LIST_SqlFieldMAP());
		
		// Objekt zur Vertragsmengen-Ermittlung merken
		this.m_oVertragsMengen = oVertragsMengen;
		this.m_oSaldoErmittlungen = oSaldoErmittlung;
		
		// zusätzliches Saldo für ein beliebiges Datum
//		this.m_oSaldoErmittlungenAdditional = oSaldoErmittlungAdd1;
		
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(ATOM_SALDO_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(ATOM_SALDO_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_BEWEGUNG_STATION")), new MyE2_String("Bewegungsatom ID"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_ADRESSE")), new MyE2_String("ID Adresse"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ADDRESS_INFO")), new MyE2_String("Adresse"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_ARTIKEL")), new MyE2_String("ID Sorte"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ART_INFO")), new MyE2_String("Sorte"));
		
		
		this.add_Component(ATOM_SALDO_LIST_CONST.HASH_SaldoMitInventur, new MyE2_Label(),new MyE2_String("Lagerbestand aktuell"));
		this.add_Component(ATOM_SALDO_LIST_CONST.HASH_SaldoDynamisch1, new MyE2_Label(),new MyE2_String("Lagerbestand1 zusätzlich"));
		this.add_Component(ATOM_SALDO_LIST_CONST.HASH_SaldoDynamisch2, new MyE2_Label(),new MyE2_String("Lagerbestand2 zusätzlich"));
		this.add_Component(ATOM_SALDO_LIST_CONST.HASH_SaldoDelta, new MyE2_Label(),new MyE2_String("Lagerbestandsänderung"));
		
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("EINHEITKURZ")), new MyE2_String("Einheit"));
		this.add_Component(ATOM_SALDO_LIST_CONST.HASH_InventurDatum, new MyE2_Label(),new MyE2_String("Inventurdatum"));
		this.add_Component(ATOM_SALDO_LIST_CONST.HASH_InventurMenge, new MyE2_Label(),new MyE2_String("Inventurmenge"));
		
		this.add_Component(ATOM_SALDO_LIST_CONST.HASH_GesamtRestmenge, new MyE2_Label(),new MyE2_String("Mögl. Saldo mit Kontrakten"));
		this.add_Component(ATOM_SALDO_LIST_CONST.HASH_EKLagermenge, new MyE2_Label(),new MyE2_String("Summe EK-Kontr."));
		this.add_Component(ATOM_SALDO_LIST_CONST.HASH_EKRestmenge, new MyE2_Label(),new MyE2_String("Restmengen EK-Kontr."));
		this.add_Component(ATOM_SALDO_LIST_CONST.HASH_VKLagermenge, new MyE2_Label(),new MyE2_String("Summe VK-Kontr."));
		this.add_Component(ATOM_SALDO_LIST_CONST.HASH_VKRestmenge, new MyE2_Label(),new MyE2_String("Restmengen VK-Kontr."));
		
		
		
		this.get__Comp("ID_BEWEGUNG_STATION").EXT().set_bIsVisibleInList(false);

		
		// Gridlayout für den Header
		GridLayoutData  GLHead = new GridLayoutData();
		GLHead.setAlignment(new Alignment(Alignment.RIGHT, Alignment.TOP));
		GLHead.setBackground(new E2_ColorDark());
		
		// Gridlayout für die Liste
		GridLayoutData  GLList = new GridLayoutData();
		GLList.setAlignment(new Alignment(Alignment.RIGHT, Alignment.CENTER));
		

		this.get(ATOM_SALDO_LIST_CONST.HASH_InventurDatum).EXT().set_oLayout_ListTitelElement(GLHead);
		this.get(ATOM_SALDO_LIST_CONST.HASH_InventurDatum).EXT().set_oLayout_ListElement(GLList);
		this.get(ATOM_SALDO_LIST_CONST.HASH_InventurDatum).EXT().set_ToolTipStringForListHeader(new MyE2_String("Inventurdatum "));

		this.get(ATOM_SALDO_LIST_CONST.HASH_InventurMenge).EXT().set_oLayout_ListTitelElement(GLHead);
		this.get(ATOM_SALDO_LIST_CONST.HASH_InventurMenge).EXT().set_oLayout_ListElement(GLList);
		this.get(ATOM_SALDO_LIST_CONST.HASH_InventurMenge).EXT().set_ToolTipStringForListHeader(new MyE2_String("Inventurmenge "));

		
		

		this.get(ATOM_SALDO_LIST_CONST.HASH_EKLagermenge).EXT().set_oLayout_ListTitelElement(GLHead);
		this.get(ATOM_SALDO_LIST_CONST.HASH_EKLagermenge).EXT().set_oLayout_ListElement(GLList);
		this.get(ATOM_SALDO_LIST_CONST.HASH_EKLagermenge).EXT().set_ToolTipStringForListHeader(new MyE2_String("Gesamtmenge der offenen EK-Kontrakte."));
		
		this.get(ATOM_SALDO_LIST_CONST.HASH_GesamtRestmenge).EXT().set_oLayout_ListTitelElement(GLHead);
		this.get(ATOM_SALDO_LIST_CONST.HASH_GesamtRestmenge).EXT().set_oLayout_ListElement(GLList);
		this.get(ATOM_SALDO_LIST_CONST.HASH_GesamtRestmenge).EXT().set_ToolTipStringForListHeader(new MyE2_String("Mögliche Gesamtmenge, d.h. aktueller Saldo plus der Restmengen der EK-Kontrakte, abzüglich der Restmengen der VK-Kontrakte."));
		
		this.get(ATOM_SALDO_LIST_CONST.HASH_EKRestmenge).EXT().set_oLayout_ListTitelElement(GLHead);
		this.get(ATOM_SALDO_LIST_CONST.HASH_EKRestmenge).EXT().set_oLayout_ListElement(GLList);
		this.get(ATOM_SALDO_LIST_CONST.HASH_EKRestmenge).EXT().set_ToolTipStringForListHeader(new MyE2_String("Restmengen der offenen EK-Kontrakte."));
		
		
		this.get(ATOM_SALDO_LIST_CONST.HASH_VKLagermenge).EXT().set_oLayout_ListTitelElement(GLHead);
		this.get(ATOM_SALDO_LIST_CONST.HASH_VKLagermenge).EXT().set_oLayout_ListElement(GLList);
		this.get(ATOM_SALDO_LIST_CONST.HASH_VKLagermenge).EXT().set_ToolTipStringForListHeader(new MyE2_String("Gesamtmenge der offenen VK-Kontrakte"));
		
		this.get(ATOM_SALDO_LIST_CONST.HASH_VKRestmenge).EXT().set_oLayout_ListTitelElement(GLHead);
		this.get(ATOM_SALDO_LIST_CONST.HASH_VKRestmenge).EXT().set_oLayout_ListElement(GLList);
		this.get(ATOM_SALDO_LIST_CONST.HASH_VKRestmenge).EXT().set_ToolTipStringForListHeader(new MyE2_String("Restmengen der offenen VK-Kontrakte."));

		
		
		// Layout der Datenspalten
		GridLayoutData layout = new GridLayoutData();
		layout.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
		
		this.get__Comp("ADDRESS_INFO").EXT().set_oLayout_ListElement(layout);
		this.get__Comp("ADDRESS_INFO").EXT().set_oLayout_ListTitelElement(LayoutDataFactory.get_GridLayoutGridLeft_DARK(null));
		this.get__Comp("ADDRESS_INFO").EXT().set_ToolTipStringForListHeader(new MyE2_String("Lager-Adresse"));

		this.get__Comp("ART_INFO").EXT().set_oLayout_ListElement(layout);
		this.get__Comp("ART_INFO").EXT().set_oLayout_ListTitelElement(LayoutDataFactory.get_GridLayoutGridLeft_DARK(null));
		this.get__Comp("ART_INFO").EXT().set_ToolTipStringForListHeader(new MyE2_String("Sortennr. und Bezeichnung."));
		
		this.get__Comp("ID_ARTIKEL").EXT().set_oLayout_ListTitelElement(LayoutDataFactory.get_GridLayoutGridLeft_DARK(null));
		this.get__Comp("ID_ARTIKEL").EXT().set_ToolTipStringForListHeader(new MyE2_String("ArtikelID"));

		this.get__Comp(ATOM_SALDO_LIST_CONST.HASH_SaldoMitInventur).EXT().set_oLayout_ListTitelElement(GLHead);
		this.get__Comp(ATOM_SALDO_LIST_CONST.HASH_SaldoMitInventur).EXT().set_oLayout_ListElement(GLList);
		this.get__Comp(ATOM_SALDO_LIST_CONST.HASH_SaldoMitInventur).EXT().set_ToolTipStringForListHeader(new MyE2_String("Aktuelle Lagermenge"));
		

		this.get__Comp(ATOM_SALDO_LIST_CONST.HASH_SaldoDynamisch1).EXT().set_oLayout_ListTitelElement(GLHead);
		this.get__Comp(ATOM_SALDO_LIST_CONST.HASH_SaldoDynamisch1).EXT().set_oLayout_ListElement(GLList);
		this.get__Comp(ATOM_SALDO_LIST_CONST.HASH_SaldoDynamisch1).EXT().set_ToolTipStringForListHeader(new MyE2_String("Lagersaldo1 zum angegebenen Datum"));
		
		this.get__Comp(ATOM_SALDO_LIST_CONST.HASH_SaldoDynamisch2).EXT().set_oLayout_ListTitelElement(GLHead);
		this.get__Comp(ATOM_SALDO_LIST_CONST.HASH_SaldoDynamisch2).EXT().set_oLayout_ListElement(GLList);
		this.get__Comp(ATOM_SALDO_LIST_CONST.HASH_SaldoDynamisch2).EXT().set_ToolTipStringForListHeader(new MyE2_String("Lagersaldo2 zum angegebenen Datum"));
		
		this.get__Comp(ATOM_SALDO_LIST_CONST.HASH_SaldoDelta).EXT().set_oLayout_ListTitelElement(GLHead);
		this.get__Comp(ATOM_SALDO_LIST_CONST.HASH_SaldoDelta).EXT().set_oLayout_ListElement(GLList);
		this.get__Comp(ATOM_SALDO_LIST_CONST.HASH_SaldoDelta).EXT().set_ToolTipStringForListHeader(new MyE2_String("Lagersaldo-Änderung"));
		
		ATOM_SALDO_LIST_FORMATING_Agent oFormattingAgent =new ATOM_SALDO_LIST_FORMATING_Agent ( m_oVertragsMengen, m_oSaldoErmittlungen)
																	.setAdditionalSaldo(alSaldoErmittlungAdd);
		
		this.set_oSubQueryAgent( oFormattingAgent );
		
	}
	
	
	

}
