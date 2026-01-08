package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_K_LIST_DATASEARCH;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

public class BST_K_LIST_DATASEARCH extends BS_K_LIST_DATASEARCH {

	public BST_K_LIST_DATASEARCH(	E2_NavigationList 	oNaviList, 
									BS__SETTING			SETTING,
									String MODULE_KENNER) throws myException
	{
		super(oNaviList, SETTING, MODULE_KENNER, null);
		
		// einige Suchmoeglichkeiten in den Positionen
		String cHelpLieferant = "SELECT DISTINCT JT_VKOPF_TPA.ID_VKOPF_TPA " +
							"  FROM "+bibE2.cTO()+".JT_VKOPF_TPA,"+bibE2.cTO()+".JT_VPOS_TPA,"+bibE2.cTO()+".JT_VPOS_TPA_FUHRE " +
							"  WHERE " +
							" JT_VKOPF_TPA.ID_VKOPF_TPA = JT_VPOS_TPA.ID_VKOPF_TPA  AND " +
							" JT_VPOS_TPA.ID_VPOS_TPA = JT_VPOS_TPA_FUHRE.ID_VPOS_TPA  AND "+ 
							 " ( "+
							 " UPPER(JT_VPOS_TPA_FUHRE.L_NAME1) LIKE UPPER('%#WERT#%') OR "+
							 " UPPER(JT_VPOS_TPA_FUHRE.L_NAME2) LIKE UPPER('%#WERT#%') OR "+
							 " UPPER(JT_VPOS_TPA_FUHRE.L_NAME3) LIKE UPPER('%#WERT#%') OR "+
							 " UPPER(JT_VPOS_TPA_FUHRE.L_PLZ) LIKE UPPER('%#WERT#%') OR "+
							 " UPPER(JT_VPOS_TPA_FUHRE.L_ORT) LIKE UPPER('%#WERT#%')"+
							 " ) ";

		String cHelpAbnehmer = "SELECT DISTINCT JT_VKOPF_TPA.ID_VKOPF_TPA " +
							"  FROM "+bibE2.cTO()+".JT_VKOPF_TPA,"+bibE2.cTO()+".JT_VPOS_TPA,"+bibE2.cTO()+".JT_VPOS_TPA_FUHRE " +
							"  WHERE " +
							" JT_VKOPF_TPA.ID_VKOPF_TPA = JT_VPOS_TPA.ID_VKOPF_TPA  AND " +
							" JT_VPOS_TPA.ID_VPOS_TPA = JT_VPOS_TPA_FUHRE.ID_VPOS_TPA  AND "+ 
							 " ( "+
							 " UPPER(JT_VPOS_TPA_FUHRE.A_NAME1) LIKE UPPER('%#WERT#%') OR "+
							 " UPPER(JT_VPOS_TPA_FUHRE.A_NAME2) LIKE UPPER('%#WERT#%') OR "+
							 " UPPER(JT_VPOS_TPA_FUHRE.A_NAME3) LIKE UPPER('%#WERT#%') OR "+
							 " UPPER(JT_VPOS_TPA_FUHRE.A_PLZ) LIKE UPPER('%#WERT#%') OR "+
							 " UPPER(JT_VPOS_TPA_FUHRE.A_ORT) LIKE UPPER('%#WERT#%')"+
							 " ) ";

		//ZEITSTEMPEL
		String cHelpBuchungsNrFuhre = "SELECT DISTINCT JT_VKOPF_TPA.ID_VKOPF_TPA " +
							"  FROM "+bibE2.cTO()+".JT_VKOPF_TPA,"+bibE2.cTO()+".JT_VPOS_TPA,"+bibE2.cTO()+".JT_VPOS_TPA_FUHRE " +
							"  WHERE " +
							" JT_VKOPF_TPA.ID_VKOPF_TPA = JT_VPOS_TPA.ID_VKOPF_TPA  AND " +
							" JT_VPOS_TPA.ID_VPOS_TPA = JT_VPOS_TPA_FUHRE.ID_VPOS_TPA  AND "+ 
							 " JT_VPOS_TPA_FUHRE.BUCHUNGSNR_FUHRE like '%#WERT#%'";
		
		
		String cHelp_ID_POS = "SELECT DISTINCT JT_VKOPF_TPA.ID_VKOPF_TPA " +
							"  FROM "+bibE2.cTO()+".JT_VKOPF_TPA,"+bibE2.cTO()+".JT_VPOS_TPA,"+bibE2.cTO()+".JT_VPOS_TPA_FUHRE " +
							"  WHERE " +
							" JT_VKOPF_TPA.ID_VKOPF_TPA = JT_VPOS_TPA.ID_VKOPF_TPA  AND " +
							" JT_VPOS_TPA.ID_VPOS_TPA = JT_VPOS_TPA_FUHRE.ID_VPOS_TPA  AND "+ 
							 " TO_CHAR(JT_VPOS_TPA_FUHRE.ID_VPOS_TPA) = '#WERT#'";
		
		String cHelp_ID_FUHRE = "SELECT DISTINCT JT_VKOPF_TPA.ID_VKOPF_TPA " +
							"  FROM "+bibE2.cTO()+".JT_VKOPF_TPA,"+bibE2.cTO()+".JT_VPOS_TPA,"+bibE2.cTO()+".JT_VPOS_TPA_FUHRE " +
							"  WHERE " +
							" JT_VKOPF_TPA.ID_VKOPF_TPA = JT_VPOS_TPA.ID_VKOPF_TPA  AND " +
							" JT_VPOS_TPA.ID_VPOS_TPA = JT_VPOS_TPA_FUHRE.ID_VPOS_TPA  AND "+ 
							 " TO_CHAR(JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE) = '#WERT#'";
		

		String cHelp_ID_LIEFERANT = "SELECT DISTINCT JT_VKOPF_TPA.ID_VKOPF_TPA " +
							"  FROM "+bibE2.cTO()+".JT_VKOPF_TPA,"+bibE2.cTO()+".JT_VPOS_TPA,"+bibE2.cTO()+".JT_VPOS_TPA_FUHRE " +
							"  WHERE " +
							" JT_VKOPF_TPA.ID_VKOPF_TPA = JT_VPOS_TPA.ID_VKOPF_TPA  AND " +
							" JT_VPOS_TPA.ID_VPOS_TPA = JT_VPOS_TPA_FUHRE.ID_VPOS_TPA  AND "+ 
							 " TO_CHAR(JT_VPOS_TPA_FUHRE.ID_ADRESSE_START) = '#WERT#'";

		String cHelp_ID_ABNEHMER = "SELECT DISTINCT JT_VKOPF_TPA.ID_VKOPF_TPA " +
							"  FROM "+bibE2.cTO()+".JT_VKOPF_TPA,"+bibE2.cTO()+".JT_VPOS_TPA,"+bibE2.cTO()+".JT_VPOS_TPA_FUHRE " +
							"  WHERE " +
							" JT_VKOPF_TPA.ID_VKOPF_TPA = JT_VPOS_TPA.ID_VKOPF_TPA  AND " +
							" JT_VPOS_TPA.ID_VPOS_TPA = JT_VPOS_TPA_FUHRE.ID_VPOS_TPA  AND "+ 
							 " TO_CHAR(JT_VPOS_TPA_FUHRE.ID_ADRESSE_ZIEL) = '#WERT#'";
		
		
		this.add_SearchElement(cHelpLieferant,				new MyE2_String("Anschrift Lieferant"));
		this.add_SearchElement(cHelpAbnehmer,				new MyE2_String("Anschrift Abnehmer"));
		this.add_SearchElement(cHelpBuchungsNrFuhre,		new MyE2_String("Buchungsnummer TP-Position"));

		this.add_SearchElement(cHelp_ID_LIEFERANT,			new MyE2_String("Adress-ID Lieferant"));
		this.add_SearchElement(cHelp_ID_ABNEHMER,			new MyE2_String("Adress-ID Abnehmer"));

		this.add_SearchElement(cHelp_ID_POS,				new MyE2_String("ID (TPA-Position)"));
		this.add_SearchElement(cHelp_ID_FUHRE,				new MyE2_String("ID (TP-Position)"));

		
		
	}


	
}
