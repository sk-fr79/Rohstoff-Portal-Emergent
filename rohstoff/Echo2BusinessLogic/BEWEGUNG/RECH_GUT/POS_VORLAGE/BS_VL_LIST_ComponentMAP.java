package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.POS_VORLAGE;

import java.util.HashMap;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.layout.ColumnLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentColumn;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_SELECTFIELD_WAEHRUNG_FREMD;

public class BS_VL_LIST_ComponentMAP extends E2_ComponentMAP
{

	/**
	 * @param oContentPane
	 * @param cID_VPOS_TPA_FUHRE (wenn == null, dann standalone-modul-betrieb)
	 * @throws myException
	 */
	public BS_VL_LIST_ComponentMAP() throws myException
	{
		super(new BS_VL_LIST_SQLFieldMAP());
		
		SQLFieldMAP   oFM = this.get_oSQLFieldMAP();
		
		this.add_Component("CHECK_BOX",new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component("MARKER",new E2_ButtonListMarker(),new MyE2_String("?"));
		
		ColumnLayoutData  oColLayLeft = LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0);
		ColumnLayoutData  oColLayRight = LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_2_0_2_0);
		ColumnLayoutData  oColLayLeftTopSpace = LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_2_2_0);
		oColLayLeft.setInsets(E2_INSETS.I_2_0_2_0);
		oColLayRight.setInsets(E2_INSETS.I_2_0_2_0);
		oColLayLeftTopSpace.setInsets(E2_INSETS.I_2_2_2_0);
		
		MyE2_DB_MultiComponentColumn	 	oColANR1_2 = new MyE2_DB_MultiComponentColumn();
		oColANR1_2.add_Component(new MyE2_DB_Label(oFM.get_("ANR1_ANR2")),new MyE2_String("Anr 1-2"),null, oColLayRight);
		
		MyE2_DB_MultiComponentColumn	 	oColBezeichnung = new MyE2_DB_MultiComponentColumn();
		oColBezeichnung.add_Component(new MyE2_DB_Label(oFM.get_("ARTBEZ1")),new MyE2_String("Artikelbezeichnung 1"),null,oColLayLeft);
		

		MyE2_DB_MultiComponentColumn	 	oColIDs = new MyE2_DB_MultiComponentColumn();
		oColIDs.add_Component(new MyE2_DB_Label(oFM.get_("ID_ARTIKEL")),new MyE2_String("ID-Art."),null, oColLayRight);
		oColIDs.add_Component(new MyE2_DB_Label(oFM.get_("ID_VPOS_RG_VL")),new MyE2_String("ID-VPos"),null, oColLayRight);

		MyE2_DB_MultiComponentColumn	 	oCol_Liefbed_Zahlbed = new MyE2_DB_MultiComponentColumn();
		oCol_Liefbed_Zahlbed.add_Component(new MyE2_DB_Label(oFM.get_("LIEFERBEDINGUNGEN"),MyE2_Label.STYLE_SMALL_ITALIC()),			new MyE2_String("Lieferbed."),null);
		oCol_Liefbed_Zahlbed.add_Component(new MyE2_DB_Label(oFM.get_("ZAHLUNGSBEDINGUNGEN"),MyE2_Label.STYLE_SMALL_ITALIC()),		new MyE2_String("Zahlungsbed."),null);

		
		this.add_Component(new MyE2_DB_TextField(oFM.get_("EINTRAG_FUER_DROPDOWN"),true,300),new MyE2_String("Anzeige in der Auswahl"));
		this.add_Component(new BS_VL_DropDown_Verteiler(oFM.get_("VERTEILER")),new MyE2_String("Verteiler"));
		
		
		this.add_Component("GRUPPENFELD_0",oColANR1_2,new MyE2_String("ANR1/ANR2"));
		this.add_Component("GRUPPENFELD_1",oColBezeichnung,new MyE2_String("Bezeichnungen"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("ANZAHL")),new MyE2_String("Menge"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("EINHEITKURZ")),new MyE2_String("EH"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("EINZELPREIS")),new MyE2_String("E-Preis "+bibE2.get_cBASISWAEHRUNG_SYMBOL()));
		this.add_Component(new MyE2_DB_Label(oFM.get_("GESAMTPREIS")),new MyE2_String("G-Preis "+bibE2.get_cBASISWAEHRUNG_SYMBOL()));
		
		//fremdwaehrungsblock
		this.add_Component(new BS_SELECTFIELD_WAEHRUNG_FREMD(oFM, true), new MyE2_String("Fremdwährung"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("WAEHRUNGSKURS")),	new MyE2_String("Währungskurs"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("EINZELPREIS_FW")),	new MyE2_String("E-Preis-FW"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("GESAMTPREIS_FW")),	new MyE2_String("G-Preis-FW"));

		this.add_Component(new MyE2_DB_Label(oFM.get_("STEUERSATZ")),new MyE2_String("MWSt"));
		//this.add_Component(new BSFP_LIST_BT_ED_IN_LIST_MWST(oFM.get_("STEUERSATZ")),new MyE2_String("St"));
		
		
		this.add_Component(new MyE2_DB_Label(oFM.get_("EINH_PREIS")),new MyE2_String("Pr.Einh."));
		this.add_Component("GRUPPENFELD_4",oCol_Liefbed_Zahlbed,new MyE2_String("Zahl./Lieferbed."));
		this.add_Component("GRUPPENFELD_5",oColIDs,new MyE2_String("IDs"));

		this.add_Component(new MyE2_DB_TextField(oFM.get_("AUSFUEHRUNGSDATUM"),true,80),new MyE2_String("Leist.Dat."));
		
		
		// sichtbarkeit
		this.get__Comp("GRUPPENFELD_4").EXT().set_bIsVisibleInList(false);
		this.get__Comp("GRUPPENFELD_5").EXT().set_bIsVisibleInList(false);
		
		this.get__Comp("ID_WAEHRUNG_FREMD").EXT().set_bIsVisibleInList(false);
		this.get__Comp("WAEHRUNGSKURS").EXT().set_bIsVisibleInList(false);
		this.get__Comp("EINZELPREIS_FW").EXT().set_bIsVisibleInList(false);
		this.get__Comp("GESAMTPREIS_FW").EXT().set_bIsVisibleInList(false);
		this.get__Comp("GESAMTPREIS").EXT().set_bIsVisibleInList(false);
		this.get__Comp("AUSFUEHRUNGSDATUM").EXT().set_bIsVisibleInList(false);
		this.get__Comp("EINH_PREIS").EXT().set_bIsVisibleInList(false);
		
		/*
		 * spaltenbreiten
		 */
		HashMap<String, MyE2IF__Component> oHMReal = this.get_REAL_ComponentHashMap();
		((MyE2IF__Component)oHMReal.get("ANZAHL")).EXT().set_oLayout_ListElement(
				LayoutDataFactory.get_GridLayoutGridRight(E2_INSETS.I_2_2_2_0, new E2_ColorBase(), new Alignment(Alignment.RIGHT,Alignment.TOP)));
		((MyE2IF__Component)oHMReal.get("ANZAHL")).EXT().set_oLayout_ListTitelElement(LayoutDataFactory.get_GridLayoutGridRight_DARK_TOP(null));

		((MyE2_DB_TextField)oHMReal.get("AUSFUEHRUNGSDATUM")).EXT().set_oLayout_ListTitelElement(LayoutDataFactory.get_GridLayoutGridRight_DARK_TOP(null));

	}

}
