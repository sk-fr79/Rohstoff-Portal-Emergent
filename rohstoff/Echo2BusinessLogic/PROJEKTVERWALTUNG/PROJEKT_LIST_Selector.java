package rohstoff.Echo2BusinessLogic.PROJEKTVERWALTUNG;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class PROJEKT_LIST_Selector extends E2_ListSelectorContainer
{
	
	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	
	private E2_SelectionComponentsVector 	oSelVector = null;
	
	public PROJEKT_LIST_Selector(E2_NavigationList oNavigationList) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);

		/*
		 * Beispiele
		 *
		 *		*/

		String cID_USER = bibALL.get_ID_USER();
		MyE2_CheckBox oCBNurEigene = new MyE2_CheckBox("Nur mit eigener Beteiligung");
		oSelVector.add(new E2_ListSelectorStandard(oCBNurEigene,"(JT_PROJEKT.ID_USER="+cID_USER+" OR JT_PROJEKT.ID_PROJEKT IN (SELECT ID_PROJEKT FROM "+bibE2.cTO()+".JT_PRO_MITARB WHERE ID_USER="+cID_USER+"))",""));
		oCBNurEigene.setSelected(false);
		
		MyE2_CheckBox oCBNurAktive = new MyE2_CheckBox("Nur aktive anzeigen");
		oSelVector.add(new E2_ListSelectorStandard(oCBNurAktive,"NVL(JT_PROJEKT.AKTIV,'N')='Y'",""));
		oCBNurAktive.setSelected(false);

		MyE2_CheckBox oCBZeigeFertige = new MyE2_CheckBox("Auch beendete anzeigen");
		oSelVector.add(new E2_ListSelectorStandard(oCBZeigeFertige,"","NVL(JT_PROJEKT.BEENDET,'N')='N'"));
		oCBZeigeFertige.setSelected(false);
		
		MyE2_Grid oGridInnen = new MyE2_Grid(3,0);
		this.add(oGridInnen, E2_INSETS.I_4_4_4_4);
		
		oGridInnen.add(oCBNurEigene, new Insets(4,2,20,2));
		oGridInnen.add(oCBNurAktive, new Insets(4,2,15,2));
		oGridInnen.add(oCBZeigeFertige, new Insets(4,2,15,2));
	}

	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}

}
