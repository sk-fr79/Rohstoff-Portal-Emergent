package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAND;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EAK_CODE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAND;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class __FU_Pruefer_auf_AVV_UND_NOTI
{

	/*
	 * wenn die klasse benutzt wird, um zu entscheiden, ob ein AVV-code gedruckt wird oder nicht, dann
	 * steht das ergebnis hier 
	 */
	private boolean bMustHaveAVV_Blatt = false;
	
	
	



	public __FU_Pruefer_auf_AVV_UND_NOTI()
	{
		super();

	}

	/*
	 * fuer die fuhrenschnellerfassung
	 */
	public boolean get_bMustHaveAVV_Blatt(	String cID_ARTIKEL, 
											String cID_EAK_CODE, 
											String cBASEL_CODE, 
											String cEUCODE, 
											long l_ID_ADRESSE_LAGER_START, 
											long l_ID_ADRESSE_LAGER_ZIEL,
											boolean bFremdVerantwortung
										  ) throws myException
	{
		this.FU__PruefeMaskeWegenNotifikation(
									cID_ARTIKEL, 
									cID_EAK_CODE, 
									cBASEL_CODE, 
									cEUCODE, 
									null, 
									l_ID_ADRESSE_LAGER_START, 
									l_ID_ADRESSE_LAGER_ZIEL, 
									null, 
									null, 
									null, 
									false, 
									false, 
									false,
									bFremdVerantwortung);
		return this.bMustHaveAVV_Blatt;
	}


	public MyE2_MessageVector FU__PruefeMaskeWegenNotifikation(	String cID_ARTIKEL, 
																String cID_EAK_CODE, 
																String cBASEL_CODE, 
																String cEUCODE, 
																String cNOTIFIKATION_NR, 
																long l_ID_ADRESSE_LAGER_START, 
																long l_ID_ADRESSE_LAGER_ZIEL,
																String cLAENDERCODE_TRANSIT1, 
																String cLAENDERCODE_TRANSIT2, 
																String cLAENDERCODE_TRANSIT3, 
																boolean b_AVV_Blatt_EIN, 
																boolean b_OHNE_AVV_VERTRAGS_CHECK,
																boolean bIstFuhrenort,
																boolean bFremdVerantwortung    //2014-06-20: kein Anhang7 / EU-blatt bei Fremdverantwortung der Fuhre
																) throws myException  
	{

		MyE2_MessageVector oMV = new MyE2_MessageVector();

		boolean bHasTransit = S.isFull(cLAENDERCODE_TRANSIT1) || S.isFull(cLAENDERCODE_TRANSIT2) || S.isFull(cLAENDERCODE_TRANSIT3);

		String cZusatzinfo = bIstFuhrenort ? " (Fuhrenort) " : " (Hauptfuhre) ";

		long lID_ARTIKEL = new MyLong(cID_ARTIKEL, new Long(-1), new Long(-1)).get_lValue();
		long lID_EAK_CODE = new MyLong(cID_EAK_CODE, new Long(-1), new Long(-1)).get_lValue();

		if (l_ID_ADRESSE_LAGER_START <= 0 || l_ID_ADRESSE_LAGER_ZIEL <= 0 || lID_ARTIKEL == -1)
		{
			return oMV; // dann geht die maske sowieso nicht durch
		}

		RECORD_ARTIKEL oRECArtikel = new RECORD_ARTIKEL(lID_ARTIKEL);

		/*
		 * 2014-06-20: EU-Blatt/Anhang7 nur bei eigenverantwortung
		 */
		if (bFremdVerantwortung && b_AVV_Blatt_EIN) {
			oMV.add_MESSAGE(new MyE2_Alarm_Message("Achtung! Bei Fremdverantwortung der Fuhre KEIN EU-Blatt/Anhang 7 drucken !!" + cZusatzinfo));
			return oMV;
		}
		
		
		
		/*
		 * geaendert am: 18.01.2010 von: martin sicherstellen, dass nur produkte
		 * oder dienstleistungen, die im artikelstamm ohne AVV sind, gefahren
		 * werden duerfen
		 */
		if (oRECArtikel.is_DIENSTLEISTUNG_NO() && oRECArtikel.is_IST_PRODUKT_NO() && oRECArtikel.is_END_OF_WASTE_NO())
		{
			if (lID_EAK_CODE == -1)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Achtung! Rohstoffe ohne AVV-Code sind nicht zulässig !!" + cZusatzinfo));
				return oMV;
			}
		}

		// jetzt pruefen, ob der schalter OHNE_AVV_VERTRAG_CHECK gesetzt ist,
		// wenn ja, muss die sorte ein produkt oder ENDOFWASTE sein
		if (b_OHNE_AVV_VERTRAGS_CHECK)
		{
			if (oRECArtikel.is_DIENSTLEISTUNG_NO() && oRECArtikel.is_IST_PRODUKT_NO() && oRECArtikel.is_END_OF_WASTE_NO())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Der Schalter <Keine Prüf.AVV-Vertr.> ist nur erlaubt, wenn die Fuhre ein Produkt/EoW betrifft!" + cZusatzinfo));
				return oMV;
			}
		}

		RECORD_LAND recLAND_START = new RECORD_ADRESSE(l_ID_ADRESSE_LAGER_START).get_UP_RECORD_LAND_id_land();
		RECORD_LAND recLAND_ZIEL = new RECORD_ADRESSE(l_ID_ADRESSE_LAGER_ZIEL).get_UP_RECORD_LAND_id_land();
		RECORD_LAND recTransfer1 = this.get_recLAND(cLAENDERCODE_TRANSIT1, oMV);
		RECORD_LAND recTransfer2 = this.get_recLAND(cLAENDERCODE_TRANSIT2, oMV);
		RECORD_LAND recTransfer3 = this.get_recLAND(cLAENDERCODE_TRANSIT3, oMV);

		if (oMV.get_bHasAlarms())
		{
			return oMV;
		}

		/*
		 * sonderfall bedeutet: land will immer, ob gefaehrlich oder nicht, eine
		 * NOTIFIKATION
		 */
		boolean bPolenSonderfall_Alles_mit_NOTI = false;
		// jetzt sonderfall pruefen, ob ein land IMMER NOTIFIKATION will
		if (recLAND_ZIEL.is_GENERELLE_EINFUHR_NOTI_YES())
		{
			bPolenSonderfall_Alles_mit_NOTI = true;
		}
		if (recTransfer1 != null && recTransfer1.is_GENERELLE_EINFUHR_NOTI_YES())
		{
			bPolenSonderfall_Alles_mit_NOTI = true;
		}
		if (recTransfer2 != null && recTransfer2.is_GENERELLE_EINFUHR_NOTI_YES())
		{
			bPolenSonderfall_Alles_mit_NOTI = true;
		}
		if (recTransfer3 != null && recTransfer3.is_GENERELLE_EINFUHR_NOTI_YES())
		{
			bPolenSonderfall_Alles_mit_NOTI = true;
		}

		// bedingung fuer verbringung von gefaehrlichen stoffen ist: kein
		// AVV-blatt und notifikations-nummer
		boolean bHatNofifikation = S.isFull(cNOTIFIKATION_NR);
		boolean bLaenderCodesUnterschiedlich = !(recLAND_START.get_ID_LAND_cUF().equals(recLAND_ZIEL.get_ID_LAND_cUF()));
		boolean b_IST_GrenzVerkehr = bHasTransit || bLaenderCodesUnterschiedlich;

		boolean bAVV_GEFAHR_oder_leer = true;

		if (lID_EAK_CODE != -1)
		{
			bAVV_GEFAHR_oder_leer = !(new RECORD_EAK_CODE(lID_EAK_CODE).is_GEFAEHRLICH_NO());
		}

		boolean bBASEL_GEFAHR = bibALL.null2leer(cBASEL_CODE).trim().toUpperCase().startsWith("A");
		boolean bOECD_GEFAHR = bibALL.null2leer(cEUCODE).trim().toUpperCase().startsWith("A");

		boolean b_gefaehrlich_oder_leere_AVV = bAVV_GEFAHR_oder_leer || bBASEL_GEFAHR || bOECD_GEFAHR;

		// jetzt noch pruefen, ob es ein produkt ist, dann darf auch kein
		// begleitschein gedruckt werden
		//boolean bIst_Produkt = oRECArtikel.is_IST_PRODUKT_YES();

		// zuerst die varianten fuer produkt ausfuehren
		if (oRECArtikel.is_IST_PRODUKT_YES() || oRECArtikel.is_END_OF_WASTE_YES())
		{
			if (b_AVV_Blatt_EIN || bHatNofifikation)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("AVV-Blatt darf nicht gedruckt werden/ Notifikation/Begleitformular nicht erlaubt, da ein PRODUKT oder END OF WASTE transportiert wird !" + cZusatzinfo), false);
				return oMV;
			}
			else
			{
				return oMV;
			}
		}

		/*
		 * neu: dienstleistung wird wie produkt behandelt
		 */
		// jetzt noch pruefen, ob es eine dienstleistung ist, dann darf auch
		// kein begleitschein gedruckt werden
		boolean bIst_Dienstleitung = oRECArtikel.is_DIENSTLEISTUNG_YES();

		// zuerst die varianten fuer produkt ausfuehren
		if (bIst_Dienstleitung)
		{
			if (b_AVV_Blatt_EIN || bHatNofifikation)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("AVV-Blatt darf nicht gedruckt werden/ Notifikation/Begleitformular nicht erlaubt, da der Vorgang eine DIENSTLEISTUNG betriff!" + cZusatzinfo), false);
				return oMV;
			}
			else
			{
				return oMV;
			}
		}
		// ende dienstleistung

		// dann innerlaendisch/grenzverkehr
		if (b_IST_GrenzVerkehr)
		{
			// hier der "polen-sonderfall" ALLE ABFALL-EINFUHREN MUESSEN
			// NOTIFIKATION HABEN
			if (bPolenSonderfall_Alles_mit_NOTI)
			{
				if ((!bHatNofifikation) || b_AVV_Blatt_EIN)
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Für Einfuhren nach ", true, recLAND_ZIEL.get_LAENDERCODE_cUF() + " MUSS IMMER eine Notifizierung vorhanden sein, kein EU-Blatt" + cZusatzinfo, true)));
					return oMV;
				}
			}
			else
			{
				if (b_gefaehrlich_oder_leere_AVV)
				{
					if (b_AVV_Blatt_EIN)
					{
						oMV.add_MESSAGE(new MyE2_Alarm_Message("Grenzüberschreitung:KEIN EU-Blatt bei begleitscheinpflichtigen Sorten (oder Sorten ohne AVV-Code)!" + cZusatzinfo), false);
						return oMV;
					}
					else
					{
						if (!bHatNofifikation)
						{
							oMV.add_MESSAGE(new MyE2_Alarm_Message("Grenzüberschreitung:Bitte Noti./Begleitformular-Nr eingeben bei begleitscheinpflichtigen Sorten (oder Sorten ohne AVV-Code)!" + cZusatzinfo), false);
							return oMV;
						}
						else
						{
							return oMV; // ALLES OK
						}

					}
				}
				else
				{
					if (bHatNofifikation)
					{
						oMV.add_MESSAGE(new MyE2_Alarm_Message("Grenzüberschreitung: Noti./Begleitformular-Nr nicht erlaubt bei ungefährlichen Sorten !" + cZusatzinfo), false);
						return oMV;
					}
					else
					{
						if (!b_AVV_Blatt_EIN && !bFremdVerantwortung)
						{
							oMV.add_MESSAGE(new MyE2_Alarm_Message("Grenzüberschreitung: EU-Blatt-Druck ist erforderlich !" + cZusatzinfo), false);

							this.bMustHaveAVV_Blatt=true;
							
							return oMV;
						}
						else
						{
							return oMV;
						}
					}
				}
			}
		}
		else
		// innerdeutsch
		{
			if (b_gefaehrlich_oder_leere_AVV)
			{
				if (b_AVV_Blatt_EIN)
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message("INLÄNDISCH: KEIN EU-Blatt bei begleitscheinpflichtigen Sorten (oder Sorten ohne AVV-Code)!" + cZusatzinfo), false);
					return oMV;
				}
				else
				{
					if (!bHatNofifikation)
					{
						oMV.add_MESSAGE(new MyE2_Alarm_Message("INLÄNDISCH: Bitte Noti./Begleitformular-Nr eingeben bei begleitscheinpflichtigen Sorten (oder Sorten ohne AVV-Code)!" + cZusatzinfo), false);
						return oMV;
					}
					else
					{
						return oMV; // ALLES OK
					}

				}
			}
			else
			{
				if (bHatNofifikation)
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message("INLÄNDISCH: Noti./Begleitformular-Nr nicht erlaubt bei ungefährlichen Sorten !" + cZusatzinfo), false);
					return oMV;
				}
				else
				{
					if (b_AVV_Blatt_EIN)
					{
						oMV.add_MESSAGE(new MyE2_Alarm_Message("INLÄNDISCH: Kein EU-Blatt-Druck erforderlich !" + cZusatzinfo), false);
						return oMV;
					}
					else
					{
						return oMV;
					}
				}
			}
		}
		return oMV;
	}

	
	
	private RECORD_LAND get_recLAND(String cLaenderCode, MyE2_MessageVector oMV) throws myException
	{
		RECORD_LAND recRueck = null;
		
		if (S.isFull(cLaenderCode))
		{
			RECLIST_LAND recListTRANS = new RECLIST_LAND("SELECT * FROM "+bibE2.cTO()+".JD_LAND WHERE TRIM(LAENDERCODE)="+bibALL.MakeSql(cLaenderCode.trim()));
			
			if (recListTRANS.get_vKeyValues().size()!=1)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Undefiniertes Länderkürzel in Transferkennzeichen 1: "+cLaenderCode)));
			}
			else
			{
				recRueck = recListTRANS.get(0);
			}
		}

		return recRueck;
	}

}
