package panter.gmbh.Echo2.__CONTAINER_ADDONS.TODOS;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class TODO_LIST_Selector extends E2_ExpandableRow
{
	
	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	
	private E2_SelectionComponentsVector 	oSelVector = null;
	
	public TODO_LIST_Selector(E2_NavigationList oNavigationList) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);

		
		String cID_USER = bibALL.get_ID_USER();
		MyE2_CheckBox oCBNurEigene = new MyE2_CheckBox("Nur mit eigener Beteiligung");
		oCBNurEigene.setSelected(true);
		
		oSelVector.add(new E2_ListSelectorStandard(oCBNurEigene,"(JT_TODO.ID_USER="+cID_USER+" OR JT_TODO.ID_TODO IN (SELECT ID_TODO FROM "+bibE2.cTO()+".JT_TODO_TEILNEHMER WHERE ID_USER="+cID_USER+"))",""));

		E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_TODO_WICHTIGKEIT","BESCHREIBUNG","ID_TODO_WICHTIGKEIT","ISTSTANDARD",true);

		MyE2_SelectField	oSelectWichtigkeit = new MyE2_SelectField(oDDWichtigkeit.getDD(),null,false);
		oSelVector.add(new E2_ListSelectorStandard(oSelectWichtigkeit,"JT_TODO.ID_TODO_WICHTIGKEIT=#WERT#", null, null));

		
		MyE2_Grid oGridInnen = new MyE2_Grid(4,0);
		this.add(oGridInnen, E2_INSETS.I_4_4_4_4);
		
		oGridInnen.add(new MyE2_Label("Wichtigkeit:"), new Insets(4,2,20,2));
		oGridInnen.add(oSelectWichtigkeit, new Insets(4,2,15,2));
		oGridInnen.add(oCBNurEigene, new Insets(4,2,15,2));
		
	}

	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}

}
