package rohstoff.Echo2BusinessLogic.LAGER_LISTE;


import java.util.Vector;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row_EveryTimeEnabled;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._4_ALL.POPUP_AND_EDIT_EVERYWHERE_VPOS_TPA_FUHRE;
import rohstoff.utils.bibROHSTOFF;

public class LAG_KTO_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
{
	
	Vector<String> m_EigeneLager = null;
	
	public LAG_KTO_LIST_FORMATING_Agent() {
		super();
		
		try {
			m_EigeneLager = bibROHSTOFF.get_vEigeneLieferadressen();
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
		MyE2_Row_EveryTimeEnabled rowJumperFuhre = (MyE2_Row_EveryTimeEnabled)oMAP.get__Comp_From_RealComponents(LAG_KTO_CONST.HASH_BUTTON_SHOW_FUHRE);
		rowJumperFuhre.removeAll();

		String idFuhre = oUsedResultMAP.get_UnFormatedValue("ID_VPOS_TPA_FUHRE");
		
		// das label id_fuhre rausnehmen und einen neuen einbauen
//		MyE2_DB_Label_INROW lblFuhre = (MyE2_DB_Label_INROW) oMAP.get_hmRealComponents().get("ID_VPOS_TPA_FUHRE");

		
		if (!bibALL.isEmpty(idFuhre)){
			// button fürs Öffnen der fuhre einbauen
			ownPOPUP_AND_EDIT_EVERYWHERE_VPOS_TPA_FUHRE oBtn = new ownPOPUP_AND_EDIT_EVERYWHERE_VPOS_TPA_FUHRE(
					oUsedResultMAP.get_UnFormatedValue("ID_VPOS_TPA_FUHRE"),
					E2_ResourceIcon.get_RI("view.png"),
					null,
					(XX_ActionAgent)null,
					true,
					true
					);
			rowJumperFuhre.add(oBtn);
		
			// button fürs Öffnen der fuhre einbauen
			BTN_NAVIGATOR__JUMP_TO_FUHRE oNavigator = new BTN_NAVIGATOR__JUMP_TO_FUHRE(bibALL.get_Vector(idFuhre), null);
			rowJumperFuhre.add(oNavigator,E2_INSETS.I_3_0_0_0);
		}
		
		
		

		// falls idLager und idKunde nicht übereinstimmen wird der Kunde noch zu den Lagerdaten eingefügt.
		String idLieferLager = oUsedResultMAP.get_UnFormatedValue("LIEFERANT_ID_ADRESSE_LAGER");
		String idLiefer = oUsedResultMAP.get_UnFormatedValue("LIEFERANT_ID_ADRESSE");

		MyE2_DB_TextArea o_KundeInfo = (MyE2_DB_TextArea) oMAP.get__Comp_From_RealComponents("LIEFERANT_INFO");

		String sKundenadresse = oMAP.get_oInternalSQLResultMAP().get_UnFormatedValue("LIEFERANT_BASISADRESSE");
		String sLieferadresse = oMAP.get_oInternalSQLResultMAP().get_UnFormatedValue("LIEFERANT_INFO");
		
		
		if (!idLiefer.equals(idLieferLager)){
			o_KundeInfo.setText(sLieferadresse + "\n(" + sKundenadresse +  ")");
		}
		
		// Läger kennzeichnen
		if (m_EigeneLager.contains(idLiefer)){
			// formatieren des Lagerstrings
			o_KundeInfo.setFont(new E2_FontItalic());
		}
		
		
		//
		// Buchungsstatus
		// 
		String sValue = bibALL.null2leer(oUsedResultMAP.get_UnFormatedValue("STATUS_BUCHUNG"));
		String sText = "-";
		String[][] sStatus  = myCONST.STATUS_FUHRE_Liste_fuer_Dropdown;
		for (int i = 0; i< sStatus.length; i++){
			if (sStatus[i][1].equals(sValue)){
				sText = sStatus[i][0];
				break;
			}
		}
		
		MyE2_DB_Label_INROW lbl = (MyE2_DB_Label_INROW) oMAP.get_hmRealComponents().get("STATUS_BUCHUNG");
		lbl.setText(sText);
		
		//
		// Ohne Abrechnung
		// 
		sValue = bibALL.null2leer(oUsedResultMAP.get_UnFormatedValue("OHNE_ABRECHNUNG"));
		if (sValue.equalsIgnoreCase("Y") ){
			sText = new MyString("Ohne Abrechnung").CTrans();
		} else {
			sText = "-";
		}
		lbl = (MyE2_DB_Label_INROW) oMAP.get_hmRealComponents().get("OHNE_ABRECHNUNG");
		lbl.setText(sText);
		

		// Rechnungs- Kontraktnr
		MyE2_Label lblKontrakt = (MyE2_Label) oMAP.get__Comp_From_RealComponents(LAG_KTO_CONST.HASH_FIELD_KONTRAKTNUMMER);
		MyE2_Label lblRechnr = (MyE2_Label) oMAP.get__Comp_From_RealComponents(LAG_KTO_CONST.HASH_FIELD_RECHNR);
		
		lblKontrakt.set_Text("-");
		lblRechnr.set_Text("-");
		String sID_VKOPF_KON = null;
		String sKONTRAKT_TYP = null;
		
		String sSqlNummern = "" +
				" select RK.BUCHUNGSNUMMER as RECHNUNGSNUMMER, KK.BUCHUNGSNUMMER AS KONTRAKTNUMMER ,KK.ID_VKOPF_KON , KK.VORGANG_TYP from JT_LAGER_KONTO K  " + 
				" left outer join JT_VPOS_RG R   " +
				" on K.ID_VPOS_TPA_FUHRE = R.ID_VPOS_TPA_FUHRE_ZUGEORD  " +
				" and NVL(K.ID_VPOS_TPA_FUHRE_ORT, '-1') = NVL(R.ID_VPOS_TPA_FUHRE_ORT_ZUGEORD,'-1')  " +
				" AND R.ID_VPOS_RG_STORNO_VORGAENGER is null  " + 
				" AND R.ID_VPOS_RG_STORNO_NACHFOLGER is null  " +
				" AND NVL(R.DELETED,'N') = 'N'  " +
				" LEFT OUTER JOIN JT_VKOPF_RG RK   " +
				"         ON RK.ID_VKOPF_RG = R.ID_VKOPF_RG  " +
				"         and NVL(RK.DELETED,'N') = 'N'  " +
				" left outer join JT_VPOS_KON KP  " +
				" on  KP.ID_VPOS_KON = R.ID_VPOS_KON_ZUGEORD  " +
				" left outer join JT_VKOPF_KON KK  " +
				" on KK.ID_MANDANT = KP.ID_MANDANT  " +
				" and KK.ID_VKOPF_KON = KP.ID_VKOPF_KON  " +
				" WHERE K.ID_VPOS_TPA_FUHRE =   " + idFuhre 
				;
		if (!bibALL.isEmpty( idFuhre) ){
			String[][] sResult = bibDB.EinzelAbfrageInArray(sSqlNummern);
			
			if (sResult != null && sResult.length > 0){
				// 1. Eintrag nehmen:
				lblRechnr.set_Text(sResult[0][0]);
				lblKontrakt.set_Text(sResult[0][1]);
				
				// sprungbutton zum Kontrakt
				sID_VKOPF_KON = sResult[0][2];
				if (sID_VKOPF_KON != null ){
					sKONTRAKT_TYP = sResult[0][3];
					boolean bVK = sKONTRAKT_TYP.equalsIgnoreCase("VK_KONTRAKT");
					BTN_NAVIGATOR__JUMP_TO_KONTRAKT oJump = new BTN_NAVIGATOR__JUMP_TO_KONTRAKT(bibALL.get_Vector(sID_VKOPF_KON), null,bVK);
					rowJumperFuhre.add(oJump,E2_INSETS.I_3_0_0_0);
				}
			}
		}
		
		
		
		
	}

	
	
	
	private class ownPOPUP_AND_EDIT_EVERYWHERE_VPOS_TPA_FUHRE extends POPUP_AND_EDIT_EVERYWHERE_VPOS_TPA_FUHRE
	{

		/**
		 * @param cid_vpos_tpa_fuhre_unformated
		 * @param iconAmStart
		 * @param beschriftung
		 * @param actionAgentAfterSaveMask
		 * @param ShowInViewModeWhenEditForbidden
		 * @param autoRefreshContainer
		 * @throws myException
		 */
		public ownPOPUP_AND_EDIT_EVERYWHERE_VPOS_TPA_FUHRE(
				String cid_vpos_tpa_fuhre_unformated,
				E2_ResourceIcon iconAmStart, MyString beschriftung,
				XX_ActionAgent actionAgentAfterSaveMask,
				boolean ShowInViewModeWhenEditForbidden,
				boolean autoRefreshContainer) throws myException
		{
			super(cid_vpos_tpa_fuhre_unformated, iconAmStart, beschriftung,
					actionAgentAfterSaveMask, ShowInViewModeWhenEditForbidden,
					autoRefreshContainer);
			this.setToolTipText("Fuhre im Popup anzeigen");
		}

		
		public void set_bEnabled_For_Edit(boolean enabled) throws myException
		{
		}
	}



}
