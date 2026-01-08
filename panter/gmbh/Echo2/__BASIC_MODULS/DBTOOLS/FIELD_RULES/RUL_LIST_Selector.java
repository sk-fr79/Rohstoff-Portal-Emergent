package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.FIELD_RULES;


import static panter.gmbh.indep.dataTools.DB_META.get_TablesQuerySort_A_to_Z;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown_STD;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.USER_SELECTOR_GENERATOR;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class RUL_LIST_Selector extends E2_ListSelectorContainer
{
	
	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	
	private E2_SelectionComponentsVector 	oSelVector = null;
	
	public RUL_LIST_Selector(E2_NavigationList oNavigationList) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);

		ownMultiDropDownSelectorTABLES 	oSEL_TABS = new ownMultiDropDownSelectorTABLES();
		ownMultiDropDownSelectorUSERS 	oSEL_USER = new ownMultiDropDownSelectorUSERS();
		
		this.oSelVector.add(oSEL_TABS);
		this.oSelVector.add(oSEL_USER);
		
	
		MyE2_Grid oGridInnen = new MyE2_Grid(4,0);
		this.add(oGridInnen, E2_INSETS.I(4,4,4,4));
		
		oGridInnen.add(new MyE2_Label("Tabellen"), E2_INSETS.I(4,2,5,2));
		oGridInnen.add(oSEL_TABS.get_oComponentForSelection(), E2_INSETS.I(4,2,30,2));
		oGridInnen.add(new MyE2_Label("Benutzer"), E2_INSETS.I(4,2,5,2));
		oGridInnen.add(oSEL_USER.get_oComponentForSelection(), E2_INSETS.I(4,2,15,2));

	
	}

	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}

	
	
	
	
	private class ownMultiDropDownSelectorTABLES extends E2_ListSelectorMultiDropDown_STD {

		public ownMultiDropDownSelectorTABLES() throws myException {
			super(new ownTableSelektorDropDown(), "NVL(SUBSTR("+_DB.FIELD_RULE$TABLE_NAME+",4),'-')=TRIM(SUBSTR('#WERT#',4))");
		}

		@Override
		public E2_BasicModuleContainer get_PopupContainer() throws myException {
			return new ownPopup();
		}
		

		private class ownPopup extends E2_BasicModuleContainer {
			
		}
	}

	private class ownTableSelektorDropDown extends MyE2_SelectField {
		
		public ownTableSelektorDropDown() throws myException {
			super(new Extent(200));
			
			String cAbfrageTable = get_TablesQuerySort_A_to_Z(bibE2.cTO(),true,true,false);
			String[][] 	cTabellen = bibDB.EinzelAbfrageInArray(cAbfrageTable, false);
			
			cTabellen = bibARR.add_emtpy_db_value_inFront(cTabellen);
			this.set_ListenInhalt(cTabellen, false);
			
		}
		
	}
	

	
	private class ownMultiDropDownSelectorUSERS extends E2_ListSelectorMultiDropDown_STD {

		public ownMultiDropDownSelectorUSERS() throws myException {
			super(new ownUserSelektorDropDown(), "NVL("+_DB.FIELD_RULE$ID_USER+",-1)=#WERT#");
		}

		@Override
		public E2_BasicModuleContainer get_PopupContainer() throws myException {
			return new ownPopup();
		}
		

		private class ownPopup extends E2_BasicModuleContainer {
			
		}
	}

	private class ownUserSelektorDropDown extends MyE2_SelectField {
		
		public ownUserSelektorDropDown() throws myException {
			super(new Extent(200));
			USER_SELECTOR_GENERATOR   oUserSelector = new USER_SELECTOR_GENERATOR(false);
			String[][] cUSER = bibARR.add_emtpy_db_value_inFront(oUserSelector.get_AktiveBenutzer(false, null));
			this.set_ListenInhalt(cUSER, false);
		}
		
	}
	

	
	
}


