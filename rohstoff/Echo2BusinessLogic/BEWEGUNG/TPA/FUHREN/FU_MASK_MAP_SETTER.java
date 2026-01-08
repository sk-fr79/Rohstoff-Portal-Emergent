package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_SettingAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.ABZUEGE.BL_Daughter_Abzuege;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.PRUEF_RECORD_VPOS_TPA_FUHRE;

public class FU_MASK_MAP_SETTER extends XX_MAP_SettingAgent
{
	
	/*
	 * existiert eine Buchung, dann ist alles gesperrt
	 */
	public void __doSettings_BEFORE(E2_ComponentMAP oMap, String STATUS_MASKE)	 throws myException
	{

		
		if (!STATUS_MASKE.equals(E2_ComponentMAP.STATUS_VIEW))
		{
			// das zeitstempel-feld fuellen, damit beim speichern automatisch der zeitstempel gesetzt wird
			((MyE2IF__DB_Component)oMap.get_Comp("ZEITSTEMPEL")).set_cActualMaskValue(bibALL.get_cDateTimeNOWInverse());
			
			/*
			 * falls der anwender ueber die maske geht, dann wird ueber die validierung die fuhre geprueft.
			 * dann ist eine evtl. fahrplanposition auch komplett 			 
			 */
			((MyE2_DB_CheckBox)oMap.get_Comp("FUHRE_KOMPLETT")).setSelected(true);
			
		}
		
		
		//zuerst alle enabled schalten
		oMap.set_bDisabledFromInteractive(false, null, false, false);
		
		//Standard-Einstellung der Komplement-Mengen-Felder
//		((MyE2IF__Component)oMap.get("ANTEIL_ABLADEMENGE_LIEF")).EXT().set_bDisabledFromInteractive(true);
//		((MyE2IF__Component)oMap.get("ANTEIL_LADEMENGE_ABN")).EXT().set_bDisabledFromInteractive(true);
		

		/*
		 * Standardmaessig ueberstimmung der AVV-einstellung erzwingen
		 */
		((MyE2_DB_CheckBox)oMap.get("SPEICHERN_TROTZ_INKONSISTENZ")).setSelected(false);

		
		//storno-bereich farblos machen
		((MyE2_Row)oMap.get__Comp(FU___CONST.HASH_KEY_STORNO_ANZEIGE)).setBackground(null);
		
		SQLResultMAP oResult = oMap.get_oInternalSQLResultMAP();

		
		
		PRUEF_RECORD_VPOS_TPA_FUHRE  recFuhre = null;
		/*
		 * dann mal schauen
		 */
		if (STATUS_MASKE.equals(E2_ComponentMAP.STATUS_EDIT))
		{
			//nachschauen, wieviele buchungen es gibt:
			recFuhre = new PRUEF_RECORD_VPOS_TPA_FUHRE(oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID(),true);
	
			int iFuhrenStatus = recFuhre.__Actual_StatusBuchung();
			
			
			/*
			 * liegt eine buchung vor, dann sind alle gesperrt, ausser der kosteneingabe
			 */
			if ((iFuhrenStatus==myCONST.STATUS_FUHRE__TEILSGEBUCHT || iFuhrenStatus==myCONST.STATUS_FUHRE__GANZGEBUCHT) && recFuhre.is_ALT_WIRD_NICHT_GEBUCHT_NO())
			{
				//2011-03-25: tpa-maske muss aufbleiben auch wenn fuhre schon geschlossen ist
				//oMap.set_bDisabledFromInteractive(true,bibALL.get_Vector(FU___CONST.HASH_KEY_MASKE_TOCHTER_KOSTEN),false,true);
				//2011-12-13: weitere felder oeffnen
				Vector<String> vNichtInaktiv = new Vector<String>();
				vNichtInaktiv.add(FU___CONST.HASH_KEY_MASKE_TOCHTER_KOSTEN);
				
				//2013-07-26: das feld "bemerkung fuer sachbearbeiter bleibt auch bei abgeschlossenen fuhre offen
				vNichtInaktiv.add(_DB.VPOS_TPA_FUHRE$BEMERKUNG_SACHBEARBEITER);
				
				//2013-09.16: die felder "Wiegerkarten-kenner" werden von der sperre ausgenommen
				vNichtInaktiv.add(_DB.VPOS_TPA_FUHRE$WIEGEKARTENKENNER_LADEN);
				vNichtInaktiv.add(_DB.VPOS_TPA_FUHRE$WIEGEKARTENKENNER_ABLADEN);
				
				//2014-02-20: Gelangesbestaetigung-schalter ausschliessen
				vNichtInaktiv.add(_DB.VPOS_TPA_FUHRE$GELANGENSBESTAETIGUNG_ERHALTEN);

				
				vNichtInaktiv.add("ID_EAK_CODE");
				vNichtInaktiv.add("EU_BLATT_MENGE");
				vNichtInaktiv.add("EU_BLATT_VOLUMEN");
				
				//2014-06-04: die felder fuer die eingabe der alternativen Lieferbedingungen bleiben auch offen
				vNichtInaktiv.add(_DB.VPOS_TPA_FUHRE$LIEFERBED_ALTERNATIV_EK);
				vNichtInaktiv.add(_DB.VPOS_TPA_FUHRE$LIEFERBED_ALTERNATIV_VK);
				vNichtInaktiv.add(FU___CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV_EK);
				vNichtInaktiv.add(FU___CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV_VK);
				
				
				oMap.set_bDisabledFromInteractive(true,vNichtInaktiv,false,false);
			}
			else    // ist fuhre eines TPAs
			{
				if (recFuhre.get_UP_RECORD_VPOS_TPA_id_vpos_tpa()!= null)
				{
					/*
					 * jetzt, falls es eine vpos_tpa, d.h. einen transportauftrag gibt, muss das feld
					 * ID_ADRESSE_SPEDITION gesperrt werden (z.b. in der fuhrenzentrale beim bearbeiten von
					 * transportauftragsfuhren)
					 */
					if (oMap.containsKey("ID_ADRESSE_SPEDITION"))
					{
						((MyE2IF__Component)oMap.get("ID_ADRESSE_SPEDITION")).EXT().set_bDisabledFromInteractive(true);
					}
				}
			}
		}
		
		
		((Component)oMap.get_Comp(FU___CONST.HASH_KEY_GEFAHRGUT_ANZEIGE)).setVisible(false);

		//storno rot sowohl beim edit als auch beim anzeigen
		if (!oMap.get_bIs_Neueingabe())
		{
			if (recFuhre==null)
			{
				//nachschauen, wieviele buchungen es gibt:
				recFuhre = new PRUEF_RECORD_VPOS_TPA_FUHRE(oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID(),true);
			}
					
			
			//nachsehen, ob storniert
			if (S.NN(oResult.get_UnFormatedValue("IST_STORNIERT")).equals("Y"))
			{
				((MyE2_Row)oMap.get__Comp(FU___CONST.HASH_KEY_STORNO_ANZEIGE)).setBackground(new E2_ColorAlarm());
			}
			
			new FU__Mask_ZEIGE_GEFAHRGUT_Label(oMap.get__Comp("ID_ARTIKEL"));
			
		}

		
		
		
		//jetzt die abzugslisten initialisieren
		BL_Daughter_Abzuege oAbzuege_EK = (BL_Daughter_Abzuege) oMap.get__Comp(FU___CONST.HASH_KEY_MASKE_TOCHTER_ABZUEGE_EK);
		BL_Daughter_Abzuege oAbzuege_VK = (BL_Daughter_Abzuege) oMap.get__Comp(FU___CONST.HASH_KEY_MASKE_TOCHTER_ABZUEGE_VK);

		
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		oAbzuege_EK.bCalc_ganze_AbzugsListe(oMV);
		oAbzuege_VK.bCalc_ganze_AbzugsListe(oMV);
		bibMSG.add_MESSAGE(oMV);

		
		
		//die einstellhilfen fuer die steuervermerke (dropdown) muessen resettet werden
		((FU__MASK_SELECT_FIELD_STEUERVERMERK)oMap.get__Comp(FU___CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT_EK)).set_Neutral();
		((FU__MASK_SELECT_FIELD_STEUERVERMERK)oMap.get__Comp(FU___CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT_VK)).set_Neutral();

		
		//jetzt die Sortenbezeichnungslabels aufbauen
		FU__ARTBEZ_ANZEIGE  oArtbezAnzeigeEK = (FU__ARTBEZ_ANZEIGE)oMap.get__Comp(FU___CONST.HASH_KEY_ARTBEZ_ANZEIGE_EK);
		FU__ARTBEZ_ANZEIGE  oArtbezAnzeigeVK = (FU__ARTBEZ_ANZEIGE)oMap.get__Comp(FU___CONST.HASH_KEY_ARTBEZ_ANZEIGE_VK);
	    oArtbezAnzeigeEK.baue_anzeige("","","EK");
		oArtbezAnzeigeVK.baue_anzeige("","","VK");
		
		if (!oMap.get_bIs_Neueingabe())
		{
			oArtbezAnzeigeEK.baue_anzeige(recFuhre,"EK");
			oArtbezAnzeigeVK.baue_anzeige(recFuhre,"VK");
		}
		
		

		//2012-01-01: den UMA-Button so faerben, dass erkennbar wird, dass eine umarbeitungsfuhre vorliegt
		if (oMap.get_bIs_Edit())
		{
			((FU_MASK_BT_DEFINIERE_UMA_VERTRAG)oMap.get__Comp(FU___CONST.HASH_MASK_BUTTON_UMA_KONTRAKT)).KorrigiereAnzeigeFuer_UMA_STATUS(oMap.get_oInternalSQLResultMAP().get_LActualDBValue("ID_UMA_KONTRAKT", true));
		}
		
		
		
		
		
	}

	public void __doSettings_AFTER(E2_ComponentMAP oMap, String STATUS_MASKE)	throws myException
	{

	}

}
