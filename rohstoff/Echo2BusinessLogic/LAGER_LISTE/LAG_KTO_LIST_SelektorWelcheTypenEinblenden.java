package rohstoff.Echo2BusinessLogic.LAGER_LISTE;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class LAG_KTO_LIST_SelektorWelcheTypenEinblenden extends E2_ListSelektorMultiselektionStatusFeld_STD
{
	
	
	public LAG_KTO_LIST_SelektorWelcheTypenEinblenden(E2_NavigationList oNavigationList) throws myException
	{
		super(1,true,new MyE2_String("Zeige: "),new Extent(40));
		
		String sColDef = oNavigationList.get_oComponentMAP__REF().get_hmRealDBComponents().get("LIEFERANT_ID_ADRESSE").EXT_DB().get_oSQLField().get_cFieldAusdruck();
		
		// die Selektion wird nicht auf die gesetzte Checkbox gemacht, sondern auf die NICHT-gesetzte,
		// damit man die AND-Verknüpfung nehmen kann anstatt der OR (schneller)

		String sIDAdresseMandant = bibALL.get_ID_ADRESS_MANDANT();
		String s_NICHT_INStandardFuhren = "(" + sColDef + " = " + sIDAdresseMandant + " OR JT_LAGER_KONTO.BUCHUNG_HAND IS NOT NULL ) ";
		String s_NICHT_INLagerLagerFuhren = "(" + sColDef + " != " + sIDAdresseMandant + " OR JT_LAGER_KONTO.BUCHUNG_HAND IS NOT NULL ) ";

		this.ADD_STATUS_TO_Selector(true,	s_NICHT_INStandardFuhren,						new MyE2_String("Eingangs-/Ausgangsfuhren"),	new MyE2_String("Alle Fuhren von und zu Kunden aus einem oder in ein Lager"));
		this.ADD_STATUS_TO_Selector(true,	s_NICHT_INLagerLagerFuhren,						new MyE2_String("Lager-Lager-Fuhren"),   		new MyE2_String("Lager zu Lager Fuhren"));
		this.ADD_STATUS_TO_Selector(true,	" ( JT_LAGER_KONTO.BUCHUNG_HAND IS NULL OR JT_LAGER_KONTO.BUCHUNG_HAND	!=	'K' )",		new MyE2_String("Korrekturbuchungen"),   		new MyE2_String("Buchung ist eine Korrekturbuchung"));
		this.ADD_STATUS_TO_Selector(true,	" ( JT_LAGER_KONTO.BUCHUNG_HAND IS NULL OR JT_LAGER_KONTO.BUCHUNG_HAND	!=	'U' )",		new MyE2_String("Umbuchungen"),   				new MyE2_String("Buchung ist eine Umbuchung"));
	}

	
	
	/**
	 * an-/ ausschalten einer Checkbox 
	 * @author manfred
	 * @date   13.02.2012
	 * @param index
	 * @param bTrue
	 */
	public void selectCheckbox(int index, boolean bSelected){
		int count = get_vCheckBoxTypen().size();
		if (index > count -1 || index < 0) return;
		
		get_vCheckBoxTypen().get(index).setSelected(bSelected);
	}
	
	
	@Override
	public String get_WhereBlock() throws myException
	{
		/*		
		 * Der Block soll eine Bedinung wie folgt ergeben!
		 * 				
				) )
		 */		
		String cWHERE = "";
		boolean bHasWhereblock = false;
		String sAND = "";
		for (MyE2_CheckBox oCB: get_vCheckBoxTypen())
		{
			if ( !oCB.isSelected())
			{
				
				if (bHasWhereblock == false){
					bHasWhereblock = true;
				} else {
					sAND = " AND ";
				}
				
				cWHERE += sAND + oCB.EXT().get_C_MERKMAL();
			}
		}
		
		if (bHasWhereblock)
		{
			cWHERE = "(" + cWHERE + " )";
		}
		
		return cWHERE;
	}

	
	
}
