package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import java.util.Locale;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_ValidBeforeSAVE;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.DotFormatter;


/*
 * positionen-validator, der dafuer sorgt, dass 
 * positionen vom typ text den lagerfaktor 0 bekommen
 */
public class BSRG_P_MASK_MapValidator_Check_Status_von_FELDGLEICHHEIT extends XX_MAP_ValidBeforeSAVE
{

	public BSRG_P_MASK_MapValidator_Check_Status_von_FELDGLEICHHEIT()
	{
		super();
	}


	public MyE2_MessageVector _doValidation(E2_ComponentMAP oMap,SQLMaskInputMAP oInputMap, String cSTATUS_MASKE)
	{
		MyE2_MessageVector vRueck = new MyE2_MessageVector();
	
		//pruefen, ob die maske aus der kopf-umgebung aufgerufen wurde
		SQLFieldMAP oFM = oMap.get_oSQLFieldMAP();
		
		try
		{
			if (oFM.get_("ID_VKOPF_RG") instanceof SQLFieldForRestrictTableRange)
			{
				String cID_VKOPF_RG = ((SQLFieldForRestrictTableRange)oFM.get_("ID_VKOPF_RG")).get_cRestictionValue_IN_DB_FORMAT();
				
				String[][] cZustaende_OHNE_STEUER = bibDB.EinzelAbfrageInArray(
						"SELECT DISTINCT NVL(OHNE_STEUER,'N') FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE ID_VKOPF_RG="+cID_VKOPF_RG+" AND "+
						"NVL(DELETED,'N')='N'");
				
				String[][] cZustaende_ZAHLUNGSBED = bibDB.EinzelAbfrageInArray(
						"SELECT DISTINCT ID_ZAHLUNGSBEDINGUNGEN, NVL(ZAHLUNGSBEDINGUNGEN,'-'),NVL(ZAHLTAGE,0),NVL(FIXMONAT,0),NVL(FIXTAG,0),TRIM(TO_CHAR(NVL(SKONTO_PROZENT,0),'9990.999')),ID_WAEHRUNG_FREMD FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE ID_VKOPF_RG="+cID_VKOPF_RG+" AND "+
						"NVL(DELETED,'N')='N'","");

					
				String cAnzahl = bibDB.EinzelAbfrage(
						"SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE ID_VKOPF_RG="+cID_VKOPF_RG+" AND "+
						"NVL(DELETED,'N')='N'");

				
				if (cZustaende_OHNE_STEUER == null || cZustaende_OHNE_STEUER.length>1 || 
					cZustaende_ZAHLUNGSBED == null || cZustaende_ZAHLUNGSBED.length>1 ||
					!bibALL.isLong(cAnzahl))
				{
					vRueck.add(new MyE2_Alarm_Message("Fehler beim Validieren der Ohne-Steuer Zustände !"));
				}
				else
				{
					Integer iAnzahl = new Integer(cAnzahl); 
					
					if (iAnzahl == 0 && cSTATUS_MASKE.equals(E2_ComponentMAP.STATUS_NEW_EMPTY))
					{
						return vRueck;   //neueingabe des ersten datensatzes
					}

					if (iAnzahl == 1 && cSTATUS_MASKE.equals(E2_ComponentMAP.STATUS_EDIT))
					{
						return vRueck;   //aendern des einzigen satzes
					}


					
					Long lID_Zahlungsbedingungen = 	null;
					Long lID_ZAHLTAGE = 			null;
					Long lID_FIXMONAT = 			null;
					Long lID_FIXTAG   = 			null;
					DotFormatter dfSkonto   = 	null;
					Long lID_WAEHRUNG = 			null;

					try
					{
						lID_Zahlungsbedingungen = 	new Long(cZustaende_ZAHLUNGSBED[0][0]);
						lID_ZAHLTAGE = 				new Long(cZustaende_ZAHLUNGSBED[0][2]);
						lID_FIXMONAT = 				new Long(cZustaende_ZAHLUNGSBED[0][3]);
						lID_FIXTAG   = 				new Long(cZustaende_ZAHLUNGSBED[0][4]);
						
						//double muss anders untersucht werden
						dfSkonto = new DotFormatter(bibALL.ReplaceTeilString(cZustaende_ZAHLUNGSBED[0][5], ".", ","),4,Locale.GERMAN,true,3);
						if (!dfSkonto.doFormat())
						{
							vRueck.add_MESSAGE(new MyE2_Alarm_Message("Bitte fügen Sie nur Positionen hinzu, die gleiche <Zahlungsbedingungen> und <Währungen> haben (1)!"));
						}
						lID_WAEHRUNG = 				new Long(cZustaende_ZAHLUNGSBED[0][6]);
					}
					catch (Exception ex)
					{
						vRueck.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Pruefen der gleichen Zustände in allen Positionen !"));
						return vRueck;
					}
					
					
					//dann muss die gerade bearbeitete maske den gleichen zustand tragen, sonst geht es nicht
					if (!oMap.get_cActualDBValueFormated("OHNE_STEUER").equals(cZustaende_OHNE_STEUER[0][0]))
					{
						vRueck.add_MESSAGE(new MyE2_Alarm_Message("Bitte fügen Sie nur Positionen hinzu, die den gleichen Status im Schalter <Ohne Steuer> tragen !"));
					}
					
					String cMaskWertFuerSkonto = oMap.get_cActualDBValueFormated("SKONTO_PROZENT");
					if (S.isEmpty(cMaskWertFuerSkonto))
					{
						cMaskWertFuerSkonto = "0";
					}
					DotFormatter oDF_SkontoMask = new DotFormatter(cMaskWertFuerSkonto,4,Locale.GERMAN,true,3);
					
					if (!oDF_SkontoMask.doFormat())
					{
						vRueck.add_MESSAGE(new MyE2_Alarm_Message("Bitte fügen Sie nur Positionen hinzu, die gleiche <Zahlungsbedingungen> und <Währungen> haben (2)!"));
					}
					else
					{
						//dann muss die gerade bearbeitete maske den gleichen zustand tragen, sonst geht es nicht
						if (!  (oMap.get_LActualDBValue("ID_ZAHLUNGSBEDINGUNGEN", true, true, new Long(0)).longValue()==lID_Zahlungsbedingungen.longValue()   &&
								oMap.get_LActualDBValue("ZAHLTAGE", true, true, new Long(0)).longValue()==lID_ZAHLTAGE.longValue()   &&
								oMap.get_LActualDBValue("FIXMONAT", true, true, new Long(0)).longValue()==lID_FIXMONAT.longValue()   &&
								oMap.get_LActualDBValue("FIXTAG", true, true, new Long(0)).longValue()==lID_FIXTAG.longValue()   &&
								dfSkonto.getStringUnFormated().equals(oDF_SkontoMask.getStringUnFormated()) &&
								oMap.get_LActualDBValue("ID_WAEHRUNG_FREMD", true, true, new Long(0)).longValue()==lID_WAEHRUNG.longValue()   &&
								oMap.get_cActualDBValueFormated("ZAHLUNGSBEDINGUNGEN").equals(cZustaende_ZAHLUNGSBED[0][1])))
						{
							vRueck.add_MESSAGE(new MyE2_Alarm_Message("Bitte fügen Sie nur Positionen hinzu, die gleiche <Zahlungsbedingungen> und <Währungen> haben (3)!"));
						}
					}
				}
			}
		}
		catch (myException ex)
		{
			vRueck.add(ex.get_ErrorMessage());
		}
		
		return vRueck;
	}

	
	
}
