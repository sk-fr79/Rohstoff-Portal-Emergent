 
package rohstoff.businesslogic.bewegung.lager.list_bewegung;
  
import java.util.Vector;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.components.MyE2_Row_EveryTimeEnabled;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWEGUNG.ATOM_LAG_CONST;
import rohstoff.Echo2BusinessLogic.LAGER_LISTE.BTN_NAVIGATOR__JUMP_TO_FUHRE;
import rohstoff.utils.bibROHSTOFF;

@Deprecated
public class BG_Lager_Bewegung_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
{
	
	Vector<String> m_EigeneLager = null;
	
	public BG_Lager_Bewegung_LIST_FORMATING_Agent(){
		try {
			m_EigeneLager = bibROHSTOFF.get_vEigeneLieferadressen(true);
		} catch (myException e) {
			// leerer Vector
			m_EigeneLager = new Vector<String>();
		}
	}
	
	
    public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)    throws myException 
    {
    }
    public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
    {
		// Kundeninfo
    	MyE2_DB_TextArea o_KundeInfo = (MyE2_DB_TextArea) oMAP.get__Comp_From_RealComponents("KUNDEN_INFO");
		

    	String idKDBasis 		= oMAP.get_oInternalSQLResultMAP().get_UnFormatedValue("ADR_KD_B_ID_ADRESSE");
    	String idKD 			= oMAP.get_oInternalSQLResultMAP().get_UnFormatedValue("ADR_KD_ID_ADRESSE");
    	
		String sLieferadresse = oMAP.get_oInternalSQLResultMAP().get_UnFormatedValue("KUNDEN_INFO");
		String sKundenadresse = oMAP.get_oInternalSQLResultMAP().get_UnFormatedValue("KUNDEN_INFO_BASIS");
		
		if (!bibALL.isEmpty(sKundenadresse) && !idKD.equals(idKDBasis)){
			o_KundeInfo.setText(sLieferadresse + "\n(" + sKundenadresse +  ")");
		}
		
		if (m_EigeneLager.contains(idKD)){
			// formatieren des Lagerstrings
			o_KundeInfo.setFont(new E2_FontItalic());
		}
		
		
		
		//
		// vorrübergehend: Sprung zur Fuhre wenn vorhanden
		//
//		MyE2_Row_EveryTimeEnabled rowJumperFuhre = (MyE2_Row_EveryTimeEnabled)oMAP.get__Comp_From_RealComponents(ATOM_LAG_CONST.HASH_BUTTON_SHOW_FUHRE);
//		rowJumperFuhre.removeAll();
//		String idFuhre = oMAP.get_oInternalSQLResultMAP().get_UnFormatedValue("ID_VPOS_TPA_FUHRE");
//		
//		// button fürs Öffnen der fuhre einbauen
//		if(!bibALL.isEmpty(idFuhre)){
//			BTN_NAVIGATOR__JUMP_TO_FUHRE oNavigator = new BTN_NAVIGATOR__JUMP_TO_FUHRE(bibALL.get_Vector(idFuhre), null);
//			rowJumperFuhre.add(oNavigator,E2_INSETS.I_3_0_0_0);
//		}
    }
}
 
