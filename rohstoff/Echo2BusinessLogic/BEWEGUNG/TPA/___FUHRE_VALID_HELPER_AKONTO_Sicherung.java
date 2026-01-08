package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import java.math.BigDecimal;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


//hilfsklasse, um fuhren bezueglich ihres akonto-status abzusichern:
//keine lieferpapiere, wenn akonto-fuhre ohne geldeingang
public class ___FUHRE_VALID_HELPER_AKONTO_Sicherung
{


	
	public MyE2_MessageVector  pruefe_akonto_status(RECORD_VPOS_TPA_FUHRE  recFuhre) throws myException
	{
		
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		
		if (!recFuhre.get_ID_ADRESSE_ZIEL_cUF_NN("-1").equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("-1")))
		{
			if (recFuhre.is_OHNE_ABRECHNUNG_NO())
			{
				if (recFuhre.get_UP_RECORD_ADRESSE_id_adresse_ziel().get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0).is_AKONTO_YES())
				{
					// jetzt pruefen, ob die fuhren einen status <unverbucht> besitzt, das koennte auch bei bereit vorhandenen und stornierten positionen (gegenseitig verbuchten) der fall sein,
					// so dass die pruefung auf vorhandene und nicht verbuchte positionen nicht ausreicht
					if (recFuhre.get_STATUS_BUCHUNG_bdValue(new BigDecimal(-100)).intValue() != myCONST.STATUS_FUHRE__GANZGEBUCHT)
					{
						//dann wurde noch keine Rechnung erzeugt ---> nicht geht raus
						MyE2_String  oStringAlarm = new MyE2_String("Die betrachtet Fuhre hat Abnehmer mit AKONTO-Status. Lieferpapiere koennen erst gedruckt werden, wenn komplett fakturiert und bezahlt wurde !  AKONTO!!!", true);
						oMV.add_MESSAGE(new MyE2_Alarm_Message(oStringAlarm));
						return oMV;
					}
					
					
					//VK-positionen fuer die fuhre suchen
					RECLIST_VPOS_RG  reclistRG = recFuhre.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord("NVL(JT_VPOS_RG.DELETED,'N')='N'  AND " +
																												" NVL(JT_VPOS_RG.ID_ADRESSE,-1)="+recFuhre.get_ID_ADRESSE_ZIEL_cUF_NN("-1")+" AND "+
																												" NVL(JT_VPOS_RG.ID_VPOS_TPA_FUHRE_ZUGEORD,-1)="+recFuhre.get_ID_VPOS_TPA_FUHRE_cUF()+" AND "+
																												" NVL(JT_VPOS_RG.ID_VPOS_TPA_FUHRE_ORT_ZUGEORD,-1)=-1","",true);
					
					oMV.add_MESSAGE(this.pruefe_reclistVpos(recFuhre.get_UP_RECORD_ADRESSE_id_adresse_ziel(), reclistRG));
				}
			}
		}
		
		RECLIST_VPOS_TPA_FUHRE_ORT  reclistFuhreORT = recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre();
		
		for (int i=0;i<reclistFuhreORT.get_vKeyValues().size();i++)
		{
			RECORD_VPOS_TPA_FUHRE_ORT  recOrt = reclistFuhreORT.get(i);
			
			if (	recOrt.is_DELETED_NO() && 
					recOrt.get_DEF_QUELLE_ZIEL_cUF().equals("VK") && 
					(!recOrt.get_ID_ADRESSE_cUF_NN("-1").equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("-1"))))
			{
				if (recOrt.is_OHNE_ABRECHNUNG_NO())
				{
					if (recOrt.get_UP_RECORD_ADRESSE_id_adresse().get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0).is_AKONTO_YES())
					{
						
						// jetzt pruefen, ob die fuhren einen status <unverbucht> besitzt, das koennte auch bei bereit vorhandenen und stornierten positionen (gegenseitig verbuchten) der fall sein,
						// so dass die pruefung auf vorhandene und nicht verbuchte positionen nicht ausreicht
						if (recFuhre.get_STATUS_BUCHUNG_bdValue(new BigDecimal(-100)).intValue() != myCONST.STATUS_FUHRE__GANZGEBUCHT)
						{
							//dann wurde noch keine Rechnung erzeugt ---> nicht geht raus
							MyE2_String  oStringAlarm = new MyE2_String("Die betrachtet Fuhre hat Abnehmer (ZUSATZORT) mit AKONTO-Status. Lieferpapiere koennen erst gedruckt werden, wenn komplett fakturiert und bezahlt wurde!  AKONTO!!!", true);
							oMV.add_MESSAGE(new MyE2_Alarm_Message(oStringAlarm));
							return oMV;
						}

						
						
						
						//VK-positionen fuer die fuhre suchen
						RECLIST_VPOS_RG  reclistRG = recOrt.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_ort_zugeord("NVL(JT_VPOS_RG.DELETED,'N')='N'  AND " +
																													  " NVL(JT_VPOS_RG.ID_ADRESSE,-1)="+recFuhre.get_ID_ADRESSE_ZIEL_cUF_NN("-1")+" AND "+
																												      " NVL(JT_VPOS_RG.ID_VPOS_TPA_FUHRE_ZUGEORD,-1)="+recFuhre.get_ID_VPOS_TPA_FUHRE_cUF()+" AND "+
																													  " NVL(JT_VPOS_RG.ID_VPOS_TPA_FUHRE_ORT_ZUGEORD,-1)="+recOrt.get_ID_VPOS_TPA_FUHRE_ORT_cUF(),"",true);
						
						oMV.add_MESSAGE(this.pruefe_reclistVpos(recOrt.get_UP_RECORD_ADRESSE_id_adresse(), reclistRG));
					}
				}
			}
		}
		return oMV;

	}
	
	
	private MyE2_MessageVector  pruefe_reclistVpos(RECORD_ADRESSE recAdresse, RECLIST_VPOS_RG  reclistRG) throws myException
	{
		MyE2_MessageVector  oMV = new MyE2_MessageVector();

		
		if (reclistRG.get_vKeyValues().size()==0)
		{
			//dann wurde noch keine Rechnung erzeugt ---> nicht geht raus
			MyE2_String  oStringAlarm = new MyE2_String("Für die Lieferung an ", true, 
												recAdresse.get_NAME1_cUF_NN("-"), false, 
											" wurde noch keine Rechnung fakturiert ! AKONTO-KUNDE !!!", true);
			oMV.add_MESSAGE(new MyE2_Alarm_Message(oStringAlarm));
			return oMV;
		}
		else
		{
			//jetzt alle rechnungen auf vorhanden-sein in Fibu und status bezahlt pruefen
			for (int i=0;i<reclistRG.get_vKeyValues().size();i++)
			{
				RECORD_VPOS_RG  recRG_POS =  reclistRG.get(i);
				
				if (recRG_POS.get_UP_RECORD_VKOPF_RG_id_vkopf_rg()==null)
				{
					//dann wurde noch keine Rechnung erzeugt ---> nicht geht raus
					MyE2_String  oStringAlarm = new MyE2_String("Für die Lieferung an ", true, 
														recAdresse.get_NAME1_cUF_NN("-"), false, 
													" wurde noch keine Rechnung fakturiert ! AKONTO-KUNDE !!!", true);
					oMV.add_MESSAGE(new MyE2_Alarm_Message(oStringAlarm));
					return oMV;
				}
				else
				{
					RECORD_VKOPF_RG recRG_KOPF = recRG_POS.get_UP_RECORD_VKOPF_RG_id_vkopf_rg();
					
					if (recRG_KOPF.is_ABGESCHLOSSEN_NO())
					{
						MyE2_String  oStringAlarm = new MyE2_String("Für die Lieferung an ", true, 
								recAdresse.get_NAME1_cUF_NN("-"), false, 
								" wurde eine Rechnung erzeugt, aber noch nicht gedruckt ! AKONTO-KUNDE !!!", true);
					
						oMV.add_MESSAGE(new MyE2_Alarm_Message(oStringAlarm));
						return oMV;
					}
					else
					{
						RECLIST_FIBU  recListFibu = recRG_KOPF.get_DOWN_RECORD_LIST_FIBU_id_vkopf_rg();
						
						if (recListFibu.get_vKeyValues().size()==0)    //duerfte es nicht geben
						{
							MyE2_String  oStringAlarm = new MyE2_String("Für die Lieferung an ", true, 
									recAdresse.get_NAME1_cUF_NN("-"), false, 
									" findet sich kein Rechnungsausgang ! AKONTO-KUNDE !!!", true);

							oMV.add_MESSAGE(new MyE2_Alarm_Message(oStringAlarm));
							return oMV;
						}
						else
						{
							for (int k=0;k<recListFibu.get_vKeyValues().size();k++)
							{
								RECORD_FIBU  recFibu = recListFibu.get(k);
								
								if (recFibu.is_BUCHUNG_GESCHLOSSEN_NO())
								{
									MyE2_String  oStringAlarm = new MyE2_String("Für die Lieferung an ", true, 
											recAdresse.get_NAME1_cUF_NN("-"), false, 
											" wurde eine Rechnung gedruckt, aber noch nicht bezahlt ! AKONTO-KUNDE !!!", true);

									oMV.add_MESSAGE(new MyE2_Alarm_Message(oStringAlarm));
									return oMV;
								}
							}
						}
					}
				}
			}
		}
		return oMV;
	}

	
	
}
