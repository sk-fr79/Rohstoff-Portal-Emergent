package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_SettingAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.ABZUEGE.BL_Daughter_Abzuege;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU__MASK_SELECT_FIELD_STEUERVERMERK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU___CONST;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.PRUEF_RECORD_VPOS_TPA_FUHRE;

public class FUO_MASK_MapSettingAgent extends XX_MAP_SettingAgent 
{
    private String 				cEK_VK = 					null;
	private FU_DAUGHTER_ORTE  	oFUO_DaughterComponent = 	null;


	public FUO_MASK_MapSettingAgent(String EK_VK, FU_DAUGHTER_ORTE FUO_DaugherComponent)
	{
		super();
		cEK_VK = EK_VK;
		this.oFUO_DaughterComponent = FUO_DaugherComponent;
	}


	public void __doSettings_BEFORE(E2_ComponentMAP oMap, String STATUS_MASKE)	throws myException 
	{
		
		((Component)oMap.get_Comp(FU___CONST.HASH_KEY_GEFAHRGUT_ANZEIGE)).setVisible(false);
		
		RECORD_VPOS_TPA_FUHRE_ORT 		recFuhreOrt  = null;
		PRUEF_RECORD_VPOS_TPA_FUHRE  	recFuhre = null; 
		
		if (!STATUS_MASKE.equals(E2_ComponentMAP.STATUS_VIEW))
		{
			// das zeitstempel-feld fuellen, damit beim speichern automatisch der zeitstempel gesetzt wird
			((MyE2IF__DB_Component)oMap.get_Comp("ZEITSTEMPEL")).set_cActualMaskValue(bibALL.get_cDateTimeNOWInverse());
			
		}
		
		//wenn es ein VK-ort ist, dann werden die Sortencodes (AVV usw.) aus der Hauptmaske geladen
		if (STATUS_MASKE.equals(E2_ComponentMAP.STATUS_NEW_EMPTY))
		{
			if (this.cEK_VK.equals("VK"))
			{
				new FUO__CopySortenCodesFromMainMap(this.oFUO_DaughterComponent.EXT().get_oComponentMAP(),oMap);
			}
		}
		

		
		/*
		 * die einstellungen im mengenpruefblock
		 */
		if (STATUS_MASKE.equals(E2_ComponentMAP.STATUS_EDIT))
		{
			//nachschauen, wieviele buchungen es gibt:
			recFuhreOrt = 	new RECORD_VPOS_TPA_FUHRE_ORT(oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
			recFuhre = 		new PRUEF_RECORD_VPOS_TPA_FUHRE(recFuhreOrt.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre(),true);
	
			int iFuhrenStatus = recFuhre.__Actual_StatusBuchung();
			
			
			/*
			 * liegt eine buchung vor, dann sind alle gesperrt, ausser der kosteneingabe
			 */
			if ((iFuhrenStatus==myCONST.STATUS_FUHRE__TEILSGEBUCHT || iFuhrenStatus==myCONST.STATUS_FUHRE__GANZGEBUCHT) && recFuhre.is_ALT_WIRD_NICHT_GEBUCHT_NO())
			{
				oMap.set_bDisabledFromInteractive(true,bibALL.get_Vector(FU___CONST.HASH_KEY_MASKE_TOCHTER_KOSTEN),false,true);  //ausser den kosten alles disabled
			}
//			else  
//			{
//				//jetzt die Komplementaeren mengenfelder oeffnen oder schliessen
//				if (this.cEK_VK.equals("EK"))
//				{
//					((MyE2IF__Component)oMap.get("ANTEIL_ABLADEMENGE")).EXT().set_bDisabledFromInteractive(recFuhre.is_LADEMENGE_GUTSCHRIFT_YES());
//				}
//				else
//				{
//					((MyE2IF__Component)oMap.get("ANTEIL_LADEMENGE")).EXT().set_bDisabledFromInteractive(recFuhre.is_ABLADEMENGE_RECHNUNG_YES());
//				}
//
//				
//				//jetzt die Fuhren-Pruefbloecke einstellen (je nach schalterstellung) --> betrifft auch die komplement-MengenFelder
//				((FU__PRUEFBLOCK_MengenEingabe)oMap.get__Comp(FUO__CONST.HASHKEY_PRUEFBLOCK_MENGE)).EINSTELLER_ForMapSettingAgent(false);
//			}
			
		}

		
		
		
		
		//storno rot sowohl beim edit als auch beim anzeigen
		if (!oMap.get_bIs_Neueingabe())
		{
			if (recFuhreOrt==null)
			{
				recFuhreOrt = new RECORD_VPOS_TPA_FUHRE_ORT(oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
			}
					
			/*
			 * status des gefahrgut-anzeigers pruefen
			 */
			if (recFuhreOrt.get_UP_RECORD_ARTIKEL_id_artikel()!=null)
			{
				if (recFuhreOrt.get_UP_RECORD_ARTIKEL_id_artikel().is_GEFAHRGUT_YES())
				{
					((Component)oMap.get_Comp(FU___CONST.HASH_KEY_GEFAHRGUT_ANZEIGE)).setVisible(true);				
				}
			}
		}

		 
		//jetzt die abzugsliste initialisieren
		BL_Daughter_Abzuege oAbzuege = (BL_Daughter_Abzuege) oMap.get__Comp(FUO__CONST.HASHKEY_MASK_ABZUG_FUHRE_ORT);
		
//		if (oMap.get_bIs_Neueingabe())
//		{
//			oAbzuege.Set_Werte_fuer_Nutzung_IN_FUHRE("0",null);
//		}
//		else
//		{
//			oAbzuege.Set_Werte_fuer_Nutzung_IN_FUHRE(	this.cEK_VK.equals("EK")?
//														recFuhreOrt.get_ANTEIL_LADEMENGE_cF_NN(recFuhreOrt.get_ANTEIL_PLANMENGE_cF_NN("0")):
//														recFuhreOrt.get_ANTEIL_ABLADEMENGE_cF_NN(recFuhreOrt.get_ANTEIL_PLANMENGE_cF_NN("0")),
//														recFuhreOrt.get_UP_RECORD_ARTIKEL_id_artikel());
//		}
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		oAbzuege.bCalc_ganze_AbzugsListe(oMV);
		bibMSG.add_MESSAGE(oMV);
		
		//die einstellhilfen fuer die steuervermerke (dropdown) muessen resettet werden
		((FU__MASK_SELECT_FIELD_STEUERVERMERK)oMap.get__Comp(FUO__CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT)).set_Neutral();

		
	}

	public void __doSettings_AFTER(E2_ComponentMAP oMap, String STATUS_MASKE) throws myException 
	{
	}

}
