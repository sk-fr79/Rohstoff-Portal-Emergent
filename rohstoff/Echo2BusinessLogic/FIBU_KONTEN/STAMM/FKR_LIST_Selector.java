package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.STAMM;


import nextapp.echo2.app.Border;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.indep.exceptions.myException;

public class FKR_LIST_Selector extends E2_ExpandableRow
{
	
	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	
	private E2_SelectionComponentsVector 	oSelVector = null;
	
	public FKR_LIST_Selector(E2_NavigationList oNavigationList) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);

		/*
		 * Beispiele
		 *
		String cID_USER = bibALL.get_ID_USER(bibE2.get_CurrSession());
		MyE2_CheckBox oCBNurEigene = new MyE2_CheckBox("Nur mit eigener Beteiligung");
		oSelVector.add_(new E2_ListSelectorStandard(oCBNurEigene,"(JT_FIBU_KONTENREGEL.ID_USER="+cID_USER+" OR JT_FIBU_KONTENREGEL.ID_FKR IN (SELECT ID_FKR FROM "+bibE2.cTO()+".JT_FIBU_KONTENREGEL_TEILNEHMER WHERE ID_USER="+cID_USER+"))",""));

		E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_FIBU_KONTENREGEL_WICHTIGKEIT","BESCHREIBUNG","ID_FKR_WICHTIGKEIT","ISTSTANDARD",true);

		MyE2_SelectField	oSelectWichtigkeit = new MyE2_SelectField(oDDWichtigkeit.getDD(),null,false);
		oSelVector.add_(new E2_ListSelectorStandard(oSelectWichtigkeit,"JT_FIBU_KONTENREGEL.ID_FKR_WICHTIGKEIT=#WERT#"));

		
		MyE2_Grid oGridInnen = new MyE2_Grid(4,0);
		this.add(oGridInnen, E2_INSETS.I_4_4_4_4);
		
		oGridInnen.add(new MyE2_Label("Wichtigkeit:"), new Insets(4,2,20,2));
		oGridInnen.add(oSelectWichtigkeit, new Insets(4,2,15,2));
		oGridInnen.add(oCBNurEigene, new Insets(4,2,15,2));
		*/
	}

	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}

}
