package rohstoff.Echo2BusinessLogic.LAGER_LISTE;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class LAG_KTO_LIST_SelektorStatusFuhre extends E2_ListSelektorMultiselektionStatusFeld_STD
{
	
	
	public LAG_KTO_LIST_SelektorStatusFuhre(E2_NavigationList oNavigationList) throws myException
	{
		super(1,true,new MyE2_String("Fuhrenstatus: "),new Extent(60));
		this.ADD_STATUS_TO_Selector(true,	" JT_VPOS_TPA_FUHRE.STATUS_BUCHUNG != 1 ", new MyE2_String("Eingabe unvollständig"),	new MyE2_String("Fuhren mit dem Status unvollständig"));
		this.ADD_STATUS_TO_Selector(true,	" JT_VPOS_TPA_FUHRE.STATUS_BUCHUNG != 2 ", new MyE2_String("Keine Buchungspositionen vorhanden"),	new MyE2_String("Fuhren mit dem Status 'Keine Buchungspositionen vorhanden' "));
		this.ADD_STATUS_TO_Selector(true,	" JT_VPOS_TPA_FUHRE.STATUS_BUCHUNG != 3 ", new MyE2_String("Ungebucht"),	new MyE2_String("Fuhren mit dem Status 'Ungebucht'"));
		this.ADD_STATUS_TO_Selector(true,	" JT_VPOS_TPA_FUHRE.STATUS_BUCHUNG != 4 ", new MyE2_String("Teilgebucht"),	new MyE2_String("Fuhren mit dem Status 'Teilgebucht'"));
		this.ADD_STATUS_TO_Selector(true,	" JT_VPOS_TPA_FUHRE.STATUS_BUCHUNG != 5 ", new MyE2_String("Gebucht"),	new MyE2_String("Fuhren mit dem Status 'Gebucht'"));

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
		 * 				( JT_LAGER_KONTO.ID_VPOS_TPA_FUHRE IS NULL 	OR (  
				    JT_VPOS_TPA_FUHRE.STATUS_BUCHUNG != 1
				AND JT_VPOS_TPA_FUHRE.STATUS_BUCHUNG != 2
				AND JT_VPOS_TPA_FUHRE.STATUS_BUCHUNG != 3
				AND JT_VPOS_TPA_FUHRE.STATUS_BUCHUNG != 4
				AND JT_VPOS_TPA_FUHRE.STATUS_BUCHUNG != 5
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
				
				cWHERE += ( sAND + oCB.EXT().get_C_MERKMAL() );
			}
		}
		
		if (bHasWhereblock)
		{
			cWHERE = "( JT_LAGER_KONTO.ID_VPOS_TPA_FUHRE IS NULL OR (" + cWHERE + " ) )";
		}
		
		return cWHERE;
	}

	
	
	
	
	public String get_WhereBlock_1() throws myException
	{
		/*		
		 * Der Block soll eine Bedinung wie folgt ergeben!
		 * 				( JT_LAGER_KONTO.ID_VPOS_TPA_FUHRE IS NULL 	OR (  1=1 
				AND JT_VPOS_TPA_FUHRE.STATUS_BUCHUNG != 1
				AND JT_VPOS_TPA_FUHRE.STATUS_BUCHUNG != 2
				AND JT_VPOS_TPA_FUHRE.STATUS_BUCHUNG != 3
				AND JT_VPOS_TPA_FUHRE.STATUS_BUCHUNG != 4
				AND JT_VPOS_TPA_FUHRE.STATUS_BUCHUNG != 5
				) )
		 */		

		String cWHERE = " JT_LAGER_KONTO.ID_VPOS_TPA_FUHRE IS NULL OR ( 1=1 ";

		for (MyE2_CheckBox oCB: get_vCheckBoxTypen())
		{
			if ( !oCB.isSelected())
			{
				cWHERE += (" AND " + oCB.EXT().get_C_MERKMAL() );
			}
		}
		
		if (S.isFull(cWHERE))
		{
			cWHERE = "(" + cWHERE + " ) )";
		}
		
		return cWHERE;
	}
	
}
