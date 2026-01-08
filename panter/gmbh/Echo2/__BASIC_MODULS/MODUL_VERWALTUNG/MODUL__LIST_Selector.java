package panter.gmbh.Echo2.__BASIC_MODULS.MODUL_VERWALTUNG;


import nextapp.echo2.app.Border;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.RecursiveSearch.E2_SEARCH_TAGS;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.exceptions.myException;

public class MODUL__LIST_Selector extends E2_ExpandableRow
{
	
	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	
	private E2_SelectionComponentsVector 	oSelVector = null;
	
	public MODUL__LIST_Selector(E2_NavigationList oNavigationList) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);

		MyE2_SelectField  oSelHauptmenue =new MyE2_SelectField("SELECT MENUETXT,ID_HAUPTMENUE FROM "+bibE2.cTO()+".JD_HAUPTMENUE ORDER BY MENUETXT",false,true,false,false); 
		oSelHauptmenue.EXT().add_SEARCH_TAG(E2_SEARCH_TAGS.SEARCH_TAGS.ST__MODUL_MODUL_MANAGER_LISTE__SELEKTOR_COMPONENT_HAUPT_MENUE);
		
		E2_ListSelectorStandard	oSelectHaupmenue =new E2_ListSelectorStandard( oSelHauptmenue
				,"JD_SERVLETS.ID_HAUPTMENUE=#WERT#", new MyE2_String("Hauptmenü"),10);
		
		oSelVector.add(oSelectHaupmenue);
		
		MyE2_Grid oGridInnen = new MyE2_Grid(4,0);
		this.add(oGridInnen, E2_INSETS.I_4_4_4_4);
		
		
		oGridInnen.add(oSelectHaupmenue.get_oComponentForSelection(), new Insets(4,2,15,2));
		
	}

	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}

}
