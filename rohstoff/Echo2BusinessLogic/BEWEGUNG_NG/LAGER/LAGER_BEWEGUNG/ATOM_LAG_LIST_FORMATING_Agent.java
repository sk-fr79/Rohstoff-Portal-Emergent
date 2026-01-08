package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWEGUNG;


import java.util.Vector;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.components.MyE2_Row_EveryTimeEnabled;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.LAGER_LISTE.BTN_NAVIGATOR__JUMP_TO_FUHRE;
import rohstoff.utils.bibROHSTOFF;

public class ATOM_LAG_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
{
	Vector<String> m_EigeneLager = null;
	
	public ATOM_LAG_LIST_FORMATING_Agent() {
		super();
		
		try {
			m_EigeneLager = bibROHSTOFF.get_vEigeneLieferadressen(true);
		} catch (myException e) {
			// leerer Vector
			m_EigeneLager = new Vector<String>();
		}
		
	}
	
	
	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
	{
	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{

		// Kundeninfo
		MyE2_DB_TextArea o_KundeInfo = (MyE2_DB_TextArea) oMAP.get__Comp_From_RealComponents("NAME_GEGEN_VPOS");
		

//		String sLieferadresse = oMAP.get_oInternalSQLResultMAP().get_UnFormatedValue("NAME_GEGEN");
		String sLieferadresse = oMAP.get_oInternalSQLResultMAP().get_UnFormatedValue("NAME_GEGEN_VPOS");
		String sKundenadresse = oMAP.get_oInternalSQLResultMAP().get_UnFormatedValue("NAME_BASIS_GEGEN_VPOS");
		if (!bibALL.isEmpty(sKundenadresse)){
			o_KundeInfo.setText(sLieferadresse + "\n(" + sKundenadresse +  ")");
		}
		
		String idLiefer = oUsedResultMAP.get_UnFormatedValue("ID_ADRESSE_GEGEN");
		// Läger kennzeichnen
		if (m_EigeneLager.contains(idLiefer)){
			// formatieren des Lagerstrings
			o_KundeInfo.setFont(new E2_FontItalic());
		}

		
		// Umsetzung EW/FW Bewertet/NichtBewertet 
//		String sEWFW = oMAP.get_oInternalSQLResultMAP().get_UnFormatedValue("EW_FW");
//		MyE2_DB_Label_INROW lbl = (MyE2_DB_Label_INROW) oMAP.get_hmRealComponents().get("EW_FW");
//		/**
//		 * Einstufung der Fremdwaren/Eigenwaren/Abrechenbar/Nicht Abrechenbar
//		 * -------------
//		 * |  2  |  1  |
//		 * |-----|-----|
//		 * |EW=1 |Ab=1 |
//		 * |FW=0 |NA=0 |
//		 * -------------
//		 * EW-Abrechenbar = 11 = 3
//		 * EW-Nicht Abrechenbar = 10 = 2
//		 * FW-Abrechenbar = 01 = 1  (eigentlich nicht möglich)
//		 * FW-Nicht Abrechenbar = 00 = 0
//		 */
//
//		String sTemp = "";
//		int ewfw = Integer.parseInt(sEWFW);
//		int eigenware = 2;
//		int abrechenbar = 1;
//		if ((ewfw & eigenware) == 2){
//			sTemp = "EW";
//		} else {
//			sTemp = "FW";
//		}
//		if ((ewfw & abrechenbar) == 1){
//			sTemp += "/Abr.";
//		} else {
//			sTemp += "/nicht Abr.";
//		}
//		
//		
//		lbl.setText(sEWFW + " - " + sTemp);
		
		
		//
		// vorrübergehend: Sprung zur Fuhre wenn vorhanden
		//
		MyE2_Row_EveryTimeEnabled rowJumperFuhre = (MyE2_Row_EveryTimeEnabled)oMAP.get__Comp_From_RealComponents(ATOM_LAG_CONST.HASH_BUTTON_SHOW_FUHRE);
		rowJumperFuhre.removeAll();
		String idFuhre = oMAP.get_oInternalSQLResultMAP().get_UnFormatedValue("ID_VPOS_TPA_FUHRE");
		
		// button fürs Öffnen der fuhre einbauen
		if(!bibALL.isEmpty(idFuhre)){
			BTN_NAVIGATOR__JUMP_TO_FUHRE oNavigator = new BTN_NAVIGATOR__JUMP_TO_FUHRE(bibALL.get_Vector(idFuhre), null);
			rowJumperFuhre.add(oNavigator,E2_INSETS.I_3_0_0_0);
		}

	}

}
