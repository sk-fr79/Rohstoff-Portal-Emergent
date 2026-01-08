package panter.gmbh.basics4project.SANKTION_FREIGABE;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown2;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.USER_SELECTOR_GENERATOR_NT;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.ENUM_USER_TYP;
import panter.gmbh.basics4project.DB_ENUMS.SANKTION_PRUEFUNG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.exceptions.myException;
public class ADR_FREIGABE_LIST_Selector extends E2_ExpandableRow {

	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */

	private E2_SelectionComponentsVector     oSelVector = null;

	public ADR_FREIGABE_LIST_Selector(E2_NavigationList oNavigationList) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));

		
		ownSelectorGeaendertVon sel_geaendert_von = new ownSelectorGeaendertVon();
		ownFreigabeSelector sel_freigabe = 	new ownFreigabeSelector();
//		ownSelectorFreigabeWegen sel_wegen = new ownSelectorFreigabeWegen();
//		ownAdressTypSelector sel_adress_typ = new ownAdressTypSelector();
		ownAktivSelector sel_aktiv = new ownAktivSelector();
		
		this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);
		this.oSelVector.add(sel_aktiv);
		this.oSelVector.add(sel_geaendert_von);
		this.oSelVector.add(sel_freigabe);
//		this.oSelVector.add(sel_wegen);
//		this.oSelVector.add(sel_adress_typ);
		
		E2_Grid oGridInnen = new E2_Grid()._s(4);

		oGridInnen
		._a(sel_geaendert_von.get_oComponentForSelection(), new RB_gld()._left_top()._ins(2))
		._a(sel_aktiv.get_oComponentForSelection(), 		new RB_gld()._right_top()._ins(10,6,10,2))	
		._a(sel_freigabe.get_oComponentForSelection(), 		new RB_gld()._right_top()._ins(10,6,10,2))
//		._a(sel_wegen.get_oComponentForSelection(), 		new RB_gld()._right_top()._ins(10,2,10,2))
//		._a(sel_adress_typ.get_oComponentForSelection(), 	new RB_gld()._right_top()._ins(10,6,10,2))
		;
		
		this.add(oGridInnen, E2_INSETS.I_4_4_4_4);
	}

	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}

	private class ownFreigabeSelector extends E2_ListSelektorMultiselektionStatusFeld_STD{

		public ownFreigabeSelector() {
			super(new int[] {40,55}, true, S.ms("Freigabe") ,new Extent(60));

			this.ADD_STATUS_TO_Selector(true,	"(NVL("+SANKTION_PRUEFUNG.freigabe.tnfn()+",'N')='Y')",	 S.ms("Ja"),  S.ms("Zeige Adressen mit  Freigabe"));		
			this.ADD_STATUS_TO_Selector(true,	"(NVL("+SANKTION_PRUEFUNG.freigabe.tnfn()+",'N')='N')",	 S.ms("Nein"),S.ms("Zeige Adresse onhe Freigabe"));		
		}

	}
	
	private class ownAktivSelector extends E2_ListSelektorMultiselektionStatusFeld_STD{

		public ownAktivSelector() {
			super(new int[] {40,55}, true, S.ms("") ,new Extent(60));

			this.ADD_STATUS_TO_Selector(true,	"(NVL("+SANKTION_PRUEFUNG.aktiv.tnfn()+",'N')='Y')", S.ms("Aktiv"),  S.ms("Aktiv"));		
			this.ADD_STATUS_TO_Selector(false,	"(NVL("+SANKTION_PRUEFUNG.aktiv.tnfn()+",'N')='N')", S.ms("Inaktiv"),S.ms("Inaktiv"));		
		}

	}

	/**
	 * selektor fuer die auswahl von modulen ....
	 * @author martin
	 *
	 */
	private class ownSelectorGeaendertVon extends E2_ListSelectorMultiDropDown2 {
		public ownSelectorGeaendertVon() throws myException {
			super();

			String[][] userList = new USER_SELECTOR_GENERATOR_NT(false,ENUM_USER_TYP.BUERO).get_selUsers(true);

			MyE2_SelectField  selFieldKenner = new MyE2_SelectField(userList,"", false);    

			And  bed = new And(SANKTION_PRUEFUNG.freigabe_user,"'#WERT#'");
			this.INIT(selFieldKenner, bed.s(), null);
		}

		@Override
		public E2_BasicModuleContainer get_PopupContainer() throws myException {
			return new ownBasicContainer();
		}

		private class ownBasicContainer extends E2_BasicModuleContainer {}
		@Override
		public Component get_oComponentForSelection() throws myException {
			E2_Grid  gridHelp = new E2_Grid()._setSize(120,100);
			gridHelp._a(new RB_lab("Freigabe Benutzer")	,new RB_gld()._ins(0,2,10,2)._left_mid());
			gridHelp._a(this.get_oComponentWithoutText(),new RB_gld()._ins(0,2,10,2)._left_mid());
			return gridHelp;
		}

	}

	/*private class ownSelectorFreigabeWegen extends E2_ListSelectorMultiDropDown2{

		public ownSelectorFreigabeWegen() throws myException {
			super();
			String[][] freigabe_list_array = ENUM_SANKTION_Error.ADRESSE.get_critical_ddArray(true);

			MyE2_SelectField  selFieldKenner = new MyE2_SelectField(freigabe_list_array,"", false);  

			And  bed = new And(SANKTION_PRUEFUNG.sanktion_wegen,"'#WERT#'");
			this.INIT(selFieldKenner, bed.s(), null);
		}

		@Override
		public E2_BasicModuleContainer get_PopupContainer() throws myException {
			return new ownBasicContainer();
		}

		private class ownBasicContainer extends E2_BasicModuleContainer {}
		@Override
		public Component get_oComponentForSelection() throws myException {
			E2_Grid  gridHelp = new E2_Grid()._setSize(120,100);
			gridHelp._a(new RB_lab("Freigabe Wegen")	,new RB_gld()._ins(0,2,10,2)._left_mid());
			gridHelp._a(this.get_oComponentWithoutText(),new RB_gld()._ins(0,2,10,2)._left_mid());
			return gridHelp;
		}

	}*/

	/*private class ownAdressTypSelector extends E2_ListSelektorMultiselektionStatusFeld_STD{

		public ownAdressTypSelector() {
			super(new int[] {40,150}, true, S.ms("Adresse Typ:") ,new Extent(60));

			this.ADD_STATUS_TO_Selector(true,	"(NVL("+ADRESSE.adresstyp.fn()+",1)="+myCONST.ADRESSTYP_MITARBEITER+")",	new MyE2_String("Mitarbeiter"),  S.ms("Zeigt die Mitarbeitern"));		
			this.ADD_STATUS_TO_Selector(true,	"(NVL("+ADRESSE.adresstyp.fn()+",1)="+myCONST.ADRESSTYP_LIEFERADRESSE+")",	new MyE2_String("Lieferadresse"),S.ms("Zeigt die Liefernadressen"));		
			this.ADD_STATUS_TO_Selector(true,	"(NVL("+ADRESSE.adresstyp.fn()+",1)="+myCONST.ADRESSTYP_FIRMENINFO+")",		new MyE2_String("Hauptadresse"), S.ms("Zeigt die Hauptadressen"));		

		}

	}*/
	
}

