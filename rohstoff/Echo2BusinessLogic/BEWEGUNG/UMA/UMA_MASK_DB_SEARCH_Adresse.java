package rohstoff.Echo2BusinessLogic.BEWEGUNG.UMA;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.utils.AdressPopUP_FOR_DB_SEARCH_Adresse;
import rohstoff.utils.ECHO2.DB_SEARCH_Adress;

public class UMA_MASK_DB_SEARCH_Adresse extends DB_SEARCH_Adress
{

	
	public UMA_MASK_DB_SEARCH_Adresse(SQLField osqlField) throws myException
	{
		super(osqlField,true,true, E2_INSETS.I_0_0_1_0);
		
		this.get_vAddOnComponents().removeAllElements();
		

		this.add_AddOnComponent(new AdressPopUP_FOR_DB_SEARCH_Adresse("EDIT_ADRESSE_AUS_MASKE"));
		
		this.get_oTextFieldForSearchInput().set_iWidthPixel(100);
		
		this.get_oTextForAnzeige().setFont(new E2_FontBold());
		this.set_bLabel4AnzeigeStattText4Anzeige(true);
		this.get_oLabel4Anzeige().get_oLabel().setFont(new E2_FontPlain());
		this.get_oLabel4Anzeige().set_iWidth(400);
		
		this.set_bShowSearchButtonLeft(true);
	}


}
