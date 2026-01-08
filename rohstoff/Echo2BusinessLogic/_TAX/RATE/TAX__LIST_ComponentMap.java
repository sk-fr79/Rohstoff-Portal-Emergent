package rohstoff.Echo2BusinessLogic._TAX.RATE;


import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_QUERYAGENT_MarkiereInaktiveInNaviList;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_LAND_DROPDOWN;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectFieldV2;
import panter.gmbh.basics4project.DB_ENUMS.TAX;
import panter.gmbh.basics4project.DB_ENUMS.TAX_GROUP;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._TAX.TAX_CONST;

public class TAX__LIST_ComponentMap extends E2_ComponentMAP 
{

	public TAX__LIST_ComponentMap() throws myException
	{
		super(new TAX__LIST_SqlFieldMAP());
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(TAX__LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(TAX__LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

		
		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_TAX")), 									new MyE2_String("ID"));
		this.add_Component(new TAX__LIST_CB_AktivAnAus(oFM.get_(_DB.TAX$AKTIV)), 							new MyE2_String("Akt."));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("HISTORISCH")), 									new MyE2_String("Historisch?"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("DROPDOWN_TEXT"),true),		 					new MyE2_String("Beschriftung des DropDown-Eintrags"));
		this.add_Component(new DB_Component_LAND_DROPDOWN(oFM.get_("ID_LAND"),80), 							new MyE2_String("Land (Gültigkeit)"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("STEUERSATZ")), 								new MyE2_String("Steuersatz in Prozent"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("STEUERVERMERK"),true),		 					new MyE2_String("Steuervermerk für Belege"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("INFO_TEXT_USER"),true), 						new MyE2_String("Info-Text für Benutzer"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("WECHSELDATUM")), 								new MyE2_String("Wechseldatum für Steuersatz"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("STEUERSATZ_NEU")), 							new MyE2_String("Steuersatz nach dem Wechsel"));
		this.add_Component(new MyE2_DB_SelectField(oFM.get_("TAXTYP"), TAX_CONST.TAXTYP_DD_ARRAY, true),	new MyE2_String("Typ des Steuersachverhaltes"));
		this.add_Component(new OwnSelectSteuersatzGruppe(oFM.get_(TAX.id_tax_group)),						new MyE2_String("Gruppe"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(TAX.sort)),										new MyE2_String("Sortierung"));


		this.get__Comp("DROPDOWN_TEXT").EXT().set_bLineWrapListHeader(true);
		this.get__Comp("STEUERSATZ").EXT().set_bLineWrapListHeader(true);
		this.get__Comp("STEUERVERMERK").EXT().set_bLineWrapListHeader(true);
		this.get__Comp("WECHSELDATUM").EXT().set_bLineWrapListHeader(true);
		this.get__Comp("STEUERSATZ_NEU").EXT().set_bLineWrapListHeader(true);
		this.get__Comp("TAXTYP").EXT().set_bLineWrapListHeader(true);
		
		this.get__Comp("STEUERSATZ").EXT().set_oLayout_ListTitelElement(MyE2_Grid.LAYOUT_RIGHT_TOP(E2_INSETS.I_1_1_1_1));
		this.get__Comp("STEUERSATZ_NEU").EXT().set_oLayout_ListTitelElement(MyE2_Grid.LAYOUT_RIGHT_TOP(E2_INSETS.I_1_1_1_1));
		
		
		
		this.set_oSubQueryAgent(new TAX__LIST_FORMATING_Agent());
		this.add_oSubQueryAgent(new E2_QUERYAGENT_MarkiereInaktiveInNaviList("AKTIV",Color.DARKGRAY,Color.BLACK));

	}

	private class OwnSelectSteuersatzGruppe extends MyE2_DB_SelectFieldV2 {
		/**
		 * @param osqlField
		 * @throws myException
		 */
		public OwnSelectSteuersatzGruppe(SQLField osqlField) throws myException {
			super(osqlField);
			SEL  selActive = new SEL(TAX_GROUP.kurztext,TAX_GROUP.id_tax_group).FROM(_TAB.tax_group).ORDERUP(TAX_GROUP.kurztext).WHERE(new vgl_YN(TAX_GROUP.aktiv, true));
			SEL  selInActive = new SEL(TAX_GROUP.kurztext,TAX_GROUP.id_tax_group).FROM(_TAB.tax_group).ORDERUP(TAX_GROUP.kurztext).WHERE(new vgl_YN(TAX_GROUP.aktiv, false));
			this.populateCombobox(selActive.s(), selInActive.s(), false, true, true, false);
		}
		
	}
	
}
