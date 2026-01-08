package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN._VALID_DAEMON;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_UMA_KON_ARTB_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_UMA_KON_ARTB_RUECKLIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_UMA_KONTRAKT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_UMA_KON_ARTB_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_UMA_KON_ARTB_RUECKLIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class FUHREN_PRUEFUNG_UMA_KONTRAKT  extends XX_FUHREN_PRUEFUNG
{
	
	public FUHREN_PRUEFUNG_UMA_KONTRAKT(RECLIST_VPOS_TPA_FUHRE rec_ListFuhre)
	{
		super(rec_ListFuhre);
	}

	//test
	
	@Override
	public MyE2_MessageVector mache_Pruefung() throws myException
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		for (int i=0;i<this.get_recListFuhre().get_vKeyValues().size();i++)
		{
			RECORD_VPOS_TPA_FUHRE  recFuhre = this.get_recListFuhre().get(i);
			
			if (S.isFull(recFuhre.get_ID_UMA_KONTRAKT_cUF_NN("")))
			{
				if (recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre("NVL(DELETED,'N')='N'",null,true).get_vKeyValues().size()>0)
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fuhre ",true,"("+recFuhre.get_ID_VPOS_TPA_FUHRE_cUF_NN("")+")",false," besitzt gleichzeitig Fuhrenorte und UMA-Eintrag ! ",true)));
				}
				else
				{
					//dann das zusammenpassen pruefen
					RECORD_UMA_KONTRAKT  recUMA = recFuhre.get_UP_RECORD_UMA_KONTRAKT_id_uma_kontrakt();
					
					String cID_ARTIKEL_FUHRE_left = recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek()!=null? 
													recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek().get_UP_RECORD_ARTIKEL_id_artikel().get_ID_ARTIKEL_cUF():
													"";
													
					String cID_ARTIKEL_FUHRE_right = recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_vk()!=null? 
													recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_vk().get_UP_RECORD_ARTIKEL_id_artikel().get_ID_ARTIKEL_cUF():
													"";
													
					String cID_Fuhre_Adresse_left = recFuhre.get_ID_ADRESSE_START_cUF_NN("");
					String cID_Fuhre_Adresse_right = recFuhre.get_ID_ADRESSE_ZIEL_cUF_NN("");
					
					if (S.isEmpty(cID_ARTIKEL_FUHRE_left) || 
						S.isEmpty(cID_ARTIKEL_FUHRE_right) ||
						S.isEmpty(cID_Fuhre_Adresse_left) ||
						S.isEmpty(cID_Fuhre_Adresse_right) )
					{
						oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fuhre mit UMA: ",true,"("+recFuhre.get_ID_VPOS_TPA_FUHRE_cUF_NN("")+")",false," ist nicht komplett ausgefüllt: Sortenbezeichner und Firmen MÜSSEN gefüllt sein ! ",true)));
					}
					else
					{
						
						String cID_ADRESSE_UMA = 		recUMA.get_ID_ADRESSE_cUF_NN("");
						
						if (S.isEmpty(cID_ADRESSE_UMA))
						{
							oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("UMA: ",true,"("+recUMA.get_ID_UMA_KONTRAKT_cUF_NN("")+")",false," ist nicht komplett ausgefüllt: Firma MUSS gefüllt sein ! ",true)));							
						}
						else
						{
							RECLIST_UMA_KON_ARTB_LIEF  		recListArtbezLief = 		recUMA.get_DOWN_RECORD_LIST_UMA_KON_ARTB_LIEF_id_uma_kontrakt();
							RECLIST_UMA_KON_ARTB_RUECKLIEF  recListArtbezRueckLief = 	recUMA.get_DOWN_RECORD_LIST_UMA_KON_ARTB_RUECKLIEF_id_uma_kontrakt();
							
							if (recListArtbezLief.get_vKeyValues().size()==0)
							{
								oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("UMA: ",true,"("+recUMA.get_ID_UMA_KONTRAKT_cUF_NN("")+")",false," ist nicht komplett ausgefüllt: Es muss eine Liefersorte angegeben sein! ",true)));							
							}
							else
							{
								if (recListArtbezRueckLief.get_vKeyValues().size()==0)
								{
									oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("UMA: ",true,"("+recUMA.get_ID_UMA_KONTRAKT_cUF_NN("")+")",false," ist nicht komplett ausgefüllt: Es muss eine Rückliefersorte angegeben sein! ",true)));							
								}
								else
								{
									if (!(cID_ADRESSE_UMA.equals(cID_Fuhre_Adresse_left) ||  cID_ADRESSE_UMA.equals(cID_Fuhre_Adresse_right)))
									{
										oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("UMA: ",true,"("+recUMA.get_ID_UMA_KONTRAKT_cUF_NN("")+")",false," Die Firmenangaben des UMA-Kontraktes stimmen nicht mit der Angabe der Fuhre überein ! ",true)));
									}
									else
									{
										//hier muessen jetzt sortengleichheiten ueberprueft werden 
										boolean bPasst = false;    

										for (int l=0;l<recListArtbezLief.get_vKeyValues().size();l++)
										{
											RECORD_UMA_KON_ARTB_LIEF recUmaArtbez = recListArtbezLief.get(l);
											if (this.vergleiche(recFuhre, cID_ADRESSE_UMA, recUmaArtbez.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_ID_ARTIKEL_cUF(), true))
											{
												bPasst=true;
												break;
											}
										}

										if (!bPasst)    // dann noch auf der anderen seiten gucken
										{
											for (int l=0;l<recListArtbezRueckLief.get_vKeyValues().size();l++)
											{
												RECORD_UMA_KON_ARTB_RUECKLIEF recUmaArtbez = recListArtbezRueckLief.get(l);
												if (this.vergleiche(recFuhre, cID_ADRESSE_UMA, recUmaArtbez.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_ID_ARTIKEL_cUF(), false))
												{
													bPasst=true;
													break;
												}
											}
										}

										if (!bPasst)
										{
											oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("UMA: ",true,"("+recUMA.get_ID_UMA_KONTRAKT_cUF_NN("")+")",false," Es gibt keine passende Lade- oder Abladesorte, die zu einer Seite der Fuhre passt! ",true)));
										}
										else
										{
											//2012-01-09: keine uma-kontrakte bei fremdwaren/fremdauftrag
											if (recFuhre.is_OHNE_ABRECHNUNG_YES() || S.isFull(recFuhre.get_ID_ADRESSE_FREMDAUFTRAG_cUF_NN("")))
											{
												oMV.add_MESSAGE(
														new MyE2_Alarm_Message(new MyE2_String(
																"UMA: ",true,"("+recUMA.get_ID_UMA_KONTRAKT_cUF_NN("")+")",false," Fuhren im Fremdauftrag/Fremdware kann keinem UMA-Kontrakt zugeordnet werden! ",true)));
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return oMV;
	}

	
	private boolean vergleiche(RECORD_VPOS_TPA_FUHRE  recFuhre, String cID_ADRESSE_UMA_UF, String cID_ARTIKEL_UMA_UF, boolean bIstLieferseite) throws myException
	{
		boolean bRueck = false;
		
		String cID_ADRESSE_FU = bIstLieferseite?recFuhre.get_ID_ADRESSE_START_cUF():recFuhre.get_ID_ADRESSE_ZIEL_cUF();
		String cID_ARTIKEL_FU = bIstLieferseite?recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek().get_UP_RECORD_ARTIKEL_id_artikel().get_ID_ARTIKEL_cUF():
												recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_vk().get_UP_RECORD_ARTIKEL_id_artikel().get_ID_ARTIKEL_cUF();
			
		if (cID_ADRESSE_FU.equals(cID_ADRESSE_UMA_UF) && cID_ARTIKEL_FU.equals(cID_ARTIKEL_UMA_UF))      //dann passt sorte und firma
		{
			bRueck = true;
		}
		
		return bRueck;
	}
	
	
}
