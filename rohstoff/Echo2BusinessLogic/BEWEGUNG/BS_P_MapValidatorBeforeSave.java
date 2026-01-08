package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import java.util.Locale;

import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_ValidBeforeSAVE;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.DotFormatter;


/*
 * allgemeiner validator fuer Componentmaps von positionen
 */
public class BS_P_MapValidatorBeforeSave extends XX_MAP_ValidBeforeSAVE
{
		private boolean bAllowEmptyID_ARTIKEL_BEZ = false;
	
		public BS_P_MapValidatorBeforeSave(boolean AllowEmptyID_ARTIKEL_BEZ)
		{
			super();
			this.bAllowEmptyID_ARTIKEL_BEZ = AllowEmptyID_ARTIKEL_BEZ;
		}

		public MyE2_MessageVector _doValidation(E2_ComponentMAP oMap, SQLMaskInputMAP oInputMap, String cSTATUS_MASKE) throws myException
		{
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			// verhindern, dass beim typ ARTIKEL oder ALTERNATIVE
			// eine leere ID_ARTIKEL_BEZ vorliegt
			
			String cTYP = 				(String)oInputMap.get("POSITION_TYP");
			String cID_ARTIKEL_BEZ =	(String)oInputMap.get("ID_ARTIKEL_BEZ");
			String cANR1 =				(String)oInputMap.get("ANR1");
			String cANR2 =				(String)oInputMap.get("ANR2");
			String cEINHEIT =			(String)oInputMap.get("EINHEITKURZ");
			String cEINHEITPREIS =		(String)oInputMap.get("EINHEIT_PREIS_KURZ");
			String cMENGENDIVISOR =		(String)oInputMap.get("MENGENDIVISOR");
			String cANZAHL =			(String)oInputMap.get("ANZAHL");
			String cEINZELPREIS =		(String)oInputMap.get("EINZELPREIS");
			String cWAEHRUNGSKURS =    	(String)oInputMap.get("WAEHRUNGSKURS");
			

			// leere menge ausdruecklich zulassen
			if (cANZAHL.trim().equals(""))
				cANZAHL="0";
			
			// leere epreis ausdruecklich zulassen
			if (cEINZELPREIS.trim().equals(""))
				cEINZELPREIS="0";

			
			
			// sicherstellen, dass anzahl, einzelpreis und mengendivisor richtig geschrieben sind
			DotFormatter oDFAnzahl = new DotFormatter(cANZAHL,3,Locale.GERMAN,true,3);
			if (!oDFAnzahl.doFormat())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Menge ist falsch geschrieben !!"), false);
				return oMV;
			}
			

			// sicherstellen, dass anzahl, einzelpreis und mengendivisor richtig geschrieben sind
			DotFormatter oDFEPreis = new DotFormatter(cEINZELPREIS,3,Locale.GERMAN,true,3);
			if (!oDFEPreis.doFormat())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Einzelpreis ist falsch geschrieben !!"), false);
				return oMV;
			}

			// sicherstellen, dass anzahl, einzelpreis und mengendivisor richtig geschrieben sind
			DotFormatter oDFMENGENDIVISOR = new DotFormatter(cMENGENDIVISOR,3,Locale.GERMAN,true,3);
			if (!oDFMENGENDIVISOR.doFormat())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Mengendivisor ist falsch geschrieben !!"), false);
				return oMV;
			}

			
			if (!this.bAllowEmptyID_ARTIKEL_BEZ)
			{
				/*
				 * falls nicht korrektes suchfeld, werden ANR1 und ANR2 gelöscht,
				 * diese felder sind schreibgeschuetzt
				 * Ansonsten werden die Sortenbezeichner neu geladen
				 */
				String cID_ARTBEZ = bibALL.ReplaceTeilString(bibALL.null2leer(cID_ARTIKEL_BEZ),".","");
				if (bibALL.isEmpty(cID_ARTBEZ) || !bibALL.isInteger(cID_ARTBEZ))
				{
					cANR1="";
					cANR2="";
					oInputMap.put("ANR1","");
					oInputMap.put("ANR2","");
				}
				else
				{
					RECORD_ARTIKEL_BEZ   recARTBEZ = new RECORD_ARTIKEL_BEZ(cID_ARTBEZ);
					cANR1=recARTBEZ.get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN("");
					cANR2=recARTBEZ.get_ANR2_cUF_NN("");
					oInputMap.put("ANR1",cANR1);
					oInputMap.put("ANR2",cANR2);
				}
			}
			
			
			if (cTYP.equals(myCONST.VG_POSITION_TYP_ARTIKEL))
			{
				if (  (bibALL.isEmpty(cID_ARTIKEL_BEZ) && !this.bAllowEmptyID_ARTIKEL_BEZ) || 
					bibALL.isEmpty(cANR1) ||
					bibALL.isEmpty(cANR2) ||
					bibALL.isEmpty(cEINHEIT) ||
					bibALL.isEmpty(cEINHEITPREIS) ||
					bibALL.isEmpty(cMENGENDIVISOR)
					)
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message("Positionstyp: Artikel: Bitte Artikel korrekt laden !"), false);
				}
				else
				{
					if (cMENGENDIVISOR.trim().startsWith("0"))
					{
						oMV.add_MESSAGE(new MyE2_Alarm_Message("Positionstyp: Artikel: Mengendivisor 0 ist verboten !!!"), false);
					}
					
					
					
					if (cEINHEIT.equals(cEINHEITPREIS))
					{
						try
						{
							Integer iMD = new Integer(bibALL.ReplaceTeilString(cMENGENDIVISOR,".",""));
							if (iMD.intValue() != 1)
								oMV.add_MESSAGE(new MyE2_Alarm_Message("Positionstyp: Artikel: Bei gleichen Einheiten MUSS der Divisor 1 sein!"), false);
						}
						catch ( Exception ex)
						{
							oMV.add_MESSAGE(new MyE2_Alarm_Message("Positionstyp: Artikel: Bitte korrekten Mengendivisor eingeben !"), false);
						}
					}
					else
					{
						try
						{
							Integer iMD = new Integer(bibALL.ReplaceTeilString(cMENGENDIVISOR,".",""));
							if (iMD.intValue() == 1)
								oMV.add_MESSAGE(new MyE2_Alarm_Message("Positionstyp: Artikel: Bei verschiednen Einheiten DARF der Divisor nicht 1 sein!"), false);
						}
						catch ( Exception ex)
						{
							oMV.add_MESSAGE(new MyE2_Alarm_Message("Positionstyp: Artikel: Bitte korrekten Mengendivisor eingeben !"), false);
						}
						
					}
				}
				
			
				
			
				// GESAMTPREIS berechnen
				if (	! bibALL.isEmpty(cANZAHL) && 
						! bibALL.isEmpty(cEINZELPREIS) && 
						! bibALL.isEmpty(cMENGENDIVISOR) && 
						! bibALL.isEmpty(cWAEHRUNGSKURS) && 
						! cMENGENDIVISOR.trim().equals("0"))
				{
					try
					{
						BS_PreisCalculator oPC = new BS_PreisCalculator(cANZAHL,cEINZELPREIS,cMENGENDIVISOR,cWAEHRUNGSKURS,true);
						oInputMap.put("GESAMTPREIS", MyNumberFormater.formatDez(bibALL.Round(oPC.get_dGesamtPreis().doubleValue(),2),2,true));
						oInputMap.put("EINZELPREIS_FW", MyNumberFormater.formatDez(bibALL.Round(oPC.get_dEinzelPreis_FW().doubleValue(),2),2,true));
						oInputMap.put("GESAMTPREIS_FW", MyNumberFormater.formatDez(bibALL.Round(oPC.get_dGesamtPreis_FW().doubleValue(),2),2,true));
					}
					catch (myException ex)
					{
						oMV.add_MESSAGE(new MyE2_Alarm_Message("Positionstyp: Artikel: Bitte korrekte Anzahl/Einzelpreis/Mengendivisor/Währungskurs eingeben !"), false);
					}
				}

				// waehrungskurs darf nicht null sein
				if (!bibALL.isEmpty(cWAEHRUNGSKURS))
				{
					DotFormatter oDF = new DotFormatter(cWAEHRUNGSKURS,4,Locale.GERMAN,true,3);
					if (oDF.doFormat())
					{
						if (oDF.get_oDouble().doubleValue()==0)
						{
							oMV.add_MESSAGE(new MyE2_Alarm_Message("Der Wert 0 ist nicht als Währungskurs zugelassen !!"), false);
						}
					}
					
					// wenn Fremdwaehrung gleich der eigenen waehrung ist, dann muss der kurs = 1 sein
					if (oMap.get_LActualDBValue("ID_WAEHRUNG_FREMD", true, false, new Long(0)).longValue()==new Long(bibALL.get_ID_BASISWAEHRUNG()).longValue())
					{
						if (oDF.get_oDouble().doubleValue()!=1)
						{
							oMV.add_MESSAGE(new MyE2_Alarm_Message("Bei gleichen Währungen MUSS der Währungskurs 1 sein !!"), false);
						}
					}
				}
			}

			return oMV;
		}

}
