package rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.Component_USER_DROPDOWN_NEW;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_STD;
import panter.gmbh.indep.exceptions.myException;

public class BSA_K_LIST_Selector extends E2_ListSelectorContainer 
{

	private static int[] CheckBoxSelektorSpalten = {40,55};

	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	private E2_SelectionComponentsVector 	oSelVector = null;
	private Component_USER_DROPDOWN_NEW		oSelectMitarbeiterADR = null;
	private Component_USER_DROPDOWN_NEW		oSelectMitarbeiterANG = null;
	private MyE2_SelectField				oSelectLand = null;
	private MyE2_SelectField				oSelectAdressKlasse = null;
	private MyE2_CheckBox  					oCB_ShowDeletedRows = new MyE2_CheckBox(new MyE2_String("Zeige gelöschte Sätze"));
	
	private AGB_Selektor_OffenGeschlossen   oSelektorOffenGeschlossen = null;
	
	//ein kombinierter listselector monat/jahr gueltigkeit in position
	private BSA_K_LIST_SelektorMonatJahr 	oSelMonatJahr = new BSA_K_LIST_SelektorMonatJahr();

	public BSA_K_LIST_Selector(E2_NavigationList oNavigationList, String cMODUL_KENNER) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		this.oSelVector = 				new E2_SelectionComponentsVector(oNavigationList);

		this.oSelectMitarbeiterADR  = 	new Component_USER_DROPDOWN_NEW(false, 120);
		this.oSelectMitarbeiterANG  =	new Component_USER_DROPDOWN_NEW(false, 120);
		
//		this.oSelectMitarbeiterADR = new MyE2_SelectField(
//				"select   NVL(NAME,'-')||' ('|| NVL(KUERZEL,'-')||')',id_user from "+bibE2.cTO()+".jd_user where id_mandant="+bibALL.get_ID_MANDANT()+" order by name",
//				false,true,false,false);

//		this.oSelectMitarbeiterANG = new MyE2_SelectField(
//				"select   NVL(NAME,'-')||' ('|| NVL(KUERZEL,'-')||')',id_user from "+bibE2.cTO()+".jd_user where id_mandant="+bibALL.get_ID_MANDANT()+" order by name",
//				false,true,false,false);

		
		this.oSelectLand = new MyE2_SelectField(
				"select laendername,laendercode from "+bibE2.cTO()+".jd_land order by laendername",
				false,true,false,false);

		
		
		this.oSelectAdressKlasse = new MyE2_SelectField(
				"select kurzbezeichnung,id_adressklasse_def  from "+bibE2.cTO()+".jt_adressklasse_def",
				false,true,false,false);

		oSelVector.add(new E2_ListSelectorStandard(oSelectMitarbeiterADR,"JT_ADRESSE.ID_USER = #WERT#", null, null));
		oSelVector.add(new E2_ListSelectorStandard(oSelectMitarbeiterANG,"JT_VKOPF_STD.ID_USER = #WERT#", null, null));
		oSelVector.add(new E2_ListSelectorStandard(oSelectLand,"JT_VKOPF_STD.LAENDERCODE = '#WERT#'", null, null));
		oSelVector.add(oSelMonatJahr);
		oSelVector.add(new E2_ListSelectorStandard(oSelectAdressKlasse,"JT_VKOPF_STD.ID_ADRESSE in (select JT_ADRESSKLASSE.ID_ADRESSE from "+bibE2.cTO()+".JT_ADRESSKLASSE where ID_ADRESSKLASSE_DEF=#WERT#)", null, null));
		oSelVector.add(oSelektorOffenGeschlossen=new AGB_Selektor_OffenGeschlossen());
		oSelVector.add(new E2_ListSelectorStandard(this.oCB_ShowDeletedRows,"","  NVL(JT_VKOPF_STD.DELETED,'N')='N'"));

		//2013-02-28: neuer db-gestuetzter listselektor
		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(cMODUL_KENNER);
		oSelVector.add(oDB_BasedSelektor);

		
		
		MyE2_Grid oGrid = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_VERTICAL());
		
		
		Insets oIN = new Insets(0,2,2,2);
		Insets oIN2 = new Insets(0,2,20,2);
		
		oGrid.add(new MyE2_Label(new MyE2_String("Bearbeiter (Adr):")),1,oIN);		
		oGrid.add(new MyE2_Label(new MyE2_String("Bearbeiter (Angeb):")),1,oIN);	
		oGrid.add(oSelectMitarbeiterADR,1,oIN2);		
		oGrid.add(oSelectMitarbeiterANG,1,oIN2);

		oGrid.add(new MyE2_Label(new MyE2_String("Land:")),1,oIN);					
		oGrid.add(new MyE2_Label(new MyE2_String("Adr.Klasse:")),1,oIN);			
		oGrid.add(oSelectLand,1,oIN2);				
		oGrid.add(oSelectAdressKlasse,1,oIN2);
		
		oGrid.add(new MyE2_Label(new MyE2_String("Status der Angebote:")),1,oIN);	
		oGrid.add(new MyE2_Label(new MyE2_String("Gültig:")),1,oIN);				

		oGrid.add(oSelektorOffenGeschlossen.get_oComponentWithoutText(),1,oIN2);
		oGrid.add(oSelMonatJahr.get_oComponentForSelection(),1,oIN2);
		
		oGrid.add(this.oCB_ShowDeletedRows,1,oIN2);
		oGrid.add(oDB_BasedSelektor.get_oComponentForSelection(new MyE2_String("Weitere:"), 100), new Insets(4,2,10,2));

		
		this.add(oGrid);
		
	}

	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}


	public MyE2_CheckBox get_oCB_ShowDeletedRows()
	{
		return oCB_ShowDeletedRows;
	}

	
	
	private class AGB_Selektor_OffenGeschlossen extends E2_ListSelektorMultiselektionStatusFeld_STD
	{
		public AGB_Selektor_OffenGeschlossen()
		{
			super(BSA_K_LIST_Selector.CheckBoxSelektorSpalten,true,null,null);
			
			String cOffenGeschlossen = RECORD_VKOPF_STD.FIELD__ABGESCHLOSSEN;
			
			this.ADD_STATUS_TO_Selector(true,	"(NVL(JT_VKOPF_STD.#FELD#,'N')='N')".replace("#FELD#",cOffenGeschlossen),	new MyE2_String("Offene"),   	new MyE2_String("Zeige Adressen, die offen sind, d.h. noch nicht final gedruckt wurden"));		
			this.ADD_STATUS_TO_Selector(true,	"(NVL(JT_VKOPF_STD.#FELD#,'N')='Y')".replace("#FELD#",cOffenGeschlossen),	new MyE2_String("Geschl."), new MyE2_String("Zeige Angebote, die geschlossen sind, d.h. bereits final gedruckt wurden"));		
		}
	}

	
	
}
