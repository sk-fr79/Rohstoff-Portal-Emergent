package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.CLEARING;

import java.util.HashMap;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.BSK__CONST;


public class BSKC_MarkerSubQueryAgent extends XX_ComponentMAP_SubqueryAGENT {

	private String 				    cEK_VK = null;
	
	public BSKC_MarkerSubQueryAgent(String EK_VK)
	{
		super();
		this.cEK_VK = EK_VK;
	}

	
	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) throws myException 
	{
	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{ 
		HashMap<String, MyE2IF__Component>  oHashReal = oMAP.get_REAL_ComponentHashMap();
		
		

		RECORD_VPOS_KON   recVPOS = new RECORD_VPOS_KON(oUsedResultMAP.get_cUNFormatedROW_ID());
	
		MyE2_Label oLabLocked =   (MyE2_Label)oHashReal.get(BSKC__CONST.HASH_LABEL_LOCKED);
		// jetzt den locked-button verarzten
		if (recVPOS.get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).is_ABGESCHLOSSEN_YES())
		{
			oLabLocked.setIcon(BSKC__CONST.LABEL_POSITION_LOCKED);
			oLabLocked.setToolTipText(new MyE2_String("Kontraktposition wurde bereits abgeschlossen").CTrans());
			
			((MyE2_Button)oHashReal.get(BSKC__CONST.HASH_BUTTON_LOCK_UNLOCK_POS)).__setImages(E2_ResourceIcon.get_RI("locked.png"),true);			

			((MyE2_Button)oHashReal.get(BSKC__CONST.HASH_BUTTON_LOCK_UNLOCK_POS)).setLayoutData(BSK__CONST.get_RedLayout4OpenCloseButton());
			((MyE2_Button)oHashReal.get(BSKC__CONST.HASH_BUTTON_LOCK_UNLOCK_POS)).EXT().set_oLayout_ListElement(BSK__CONST.get_RedLayout4OpenCloseButton());
			
		}
		else
		{
			oLabLocked.setIcon(BSKC__CONST.LABEL_POSITION_UNLOCKED);
			oLabLocked.setToolTipText(new MyE2_String("Kontraktposition ist noch nicht abgeschlossen").CTrans());

			((MyE2_Button)oHashReal.get(BSKC__CONST.HASH_BUTTON_LOCK_UNLOCK_POS)).__setImages(E2_ResourceIcon.get_RI("unlocked.png"),true);
			
			((MyE2_Button)oHashReal.get(BSKC__CONST.HASH_BUTTON_LOCK_UNLOCK_POS)).setLayoutData(BSK__CONST.get_GreenLayout4OpenCloseButton());
			((MyE2_Button)oHashReal.get(BSKC__CONST.HASH_BUTTON_LOCK_UNLOCK_POS)).EXT().set_oLayout_ListElement(BSK__CONST.get_GreenLayout4OpenCloseButton());

		}
		
		
		BSKC__AUSLASTUNG  oLast = new BSKC__AUSLASTUNG(recVPOS,true);
		
		
		
			
		// jetzt noch formatieren und werte der fuhrensummen und rechnungsummen reinschreiben
		try
		{
			
			((MyE2_Label)oHashReal.get(BSKC__CONST.HASH_MENGE_SUMME_GEGENSEITE)).setText(MyNumberFormater.formatDez(oLast.get_dMengeGegenSeite(), 0, true));
			((MyE2_Label)oHashReal.get(BSKC__CONST.HASH_MENGE_SUMME_LAGER)).setText(MyNumberFormater.formatDez(oLast.get_dMengeLager(), 0, true));
			
			// differenz fuellen und farbig darstellen
			((BSK_GridWithNumber)oHashReal.get(BSKC__CONST.FILTERCOLUMN.HASH_R_MENGE_SUMME_ZUORDNUNG.key())).set_Number(oLast.get_dGesamteMengeZuordnung(),0,new Double(oLast.get_dMengeKontrakt()), false);
			((BSK_GridWithNumber)oHashReal.get(BSKC__CONST.FILTERCOLUMN.HASH_R_MENGE_SUMME_DIFFERENZ.key())).set_Number(oLast.get_dRestMengeNochOffen_in_der_Planung(),0,new Double(oLast.get_dMengeKontrakt()), true);
			
			((BSK_GridWithNumber)oHashReal.get(BSKC__CONST.FILTERCOLUMN.HASH_R_SUMME_EK_VK_FUHREN_PLANMENGE.key())).set_Number(oLast.get_dMengeFuhreGesamtPlan_oder_Echt_netto(),0,new Double(oLast.get_dMengeKontrakt()),false);
			((BSK_GridWithNumber)oHashReal.get(BSKC__CONST.FILTERCOLUMN.HASH_R_SUMME_EK_VK_FUHREN_REALMENGE.key())).set_Number(oLast.get_dMengeFuhreGesamt_Echt_netto(),0,new Double(oLast.get_dMengeKontrakt()),false);
			((BSK_GridWithNumber)oHashReal.get(BSKC__CONST.FILTERCOLUMN.HASH_R_SUMME_RECH_GUT_POS.key())).set_Number(oLast.get_dMenge_RG_Positionen_netto(),0,new Double(oLast.get_dMengeKontrakt()),false);

			
			// jetzt noch den zuordnungsbutton bestuecken
			((BSKC_BT_ZUORDNUNG)oHashReal.get(BSKC__CONST.HASH_BUTTON_ZUORDNUNG)).set_cID_VPOS_KON(recVPOS.get_ID_VPOS_KON_cUF());

			// jetzt noch nachsehen, was auf die lagerzugordnung schon gefahren wurde
			((BSK_GridWithNumber)oHashReal.get(BSKC__CONST.FILTERCOLUMN.HASH_R_SUMME_LAGER_IN_FUHRE.key())).set_Number(oLast.get_ddMengeLagerInFuhren_echt_oder_plan_netto(),0,oLast.get_dMengeLager(),false);
			
		}
		catch (Exception ex)
		{}
		
		
		// jetzt noch die IDs auf den popup-buttons ersetzen durch die buchungsnummern (falls bereits vorhanden)
		String cKopfBuchungsNummer 	= oUsedResultMAP.get_UnFormatedValue("K_BUCHUNGSNUMMER");
		String cPosNummer 			= S.NN(oUsedResultMAP.get_UnFormatedValue("POSITIONSNUMMER"));
		if (S.isFull(cKopfBuchungsNummer))
		{
			((MyE2_Button)oHashReal.get("ID_VPOS_KON")).setText(cKopfBuchungsNummer+"-"+cPosNummer);
			((MyE2_Button)oHashReal.get("K_ID_VKOPF_KON")).setText(cKopfBuchungsNummer);
			
			((MyE2_Button)oHashReal.get("ID_VPOS_KON")).setFont(new E2_FontItalic(-2));
			((MyE2_Button)oHashReal.get("K_ID_VKOPF_KON")).setFont(new E2_FontItalic(-2));
			
			((MyE2_Button)oHashReal.get("ID_VPOS_KON")).setToolTipText("ID: "+oUsedResultMAP.get_FormatedValue("ID_VPOS_KON"));
			((MyE2_Button)oHashReal.get("K_ID_VKOPF_KON")).setToolTipText("ID: "+oUsedResultMAP.get_FormatedValue("ID_VKOPF_KON"));
		}
		
		
//		//jetzt die position- lock-unlock-buttons definieren
//		if (oUsedResultMAP.get_UnFormatedValue("KO_ABGESCHLOSSEN").equals("Y"))
//		{
//			((MyE2_Button)oHashReal.get(BSKC__CONST.HASH_BUTTON_LOCK_UNLOCK_POS)).setIcon(E2_ResourceIcon.get_RI("locked.png"));			
//		}
//		else
//		{
//			((MyE2_Button)oHashReal.get(BSKC__CONST.HASH_BUTTON_LOCK_UNLOCK_POS)).setIcon(E2_ResourceIcon.get_RI("unlocked.png"));			
//		}

		
	}
 
	
	
}
