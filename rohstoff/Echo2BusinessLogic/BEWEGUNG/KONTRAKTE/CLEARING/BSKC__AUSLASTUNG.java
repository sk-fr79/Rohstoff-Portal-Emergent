package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.CLEARING;

import java.util.HashMap;

import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_VPOS_KON_EXT;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_VPOS_KON_EXT.CLEARINGTAGS;

public class BSKC__AUSLASTUNG 
{
	private RECORD_VPOS_KON 		recVPOS_KON = null;
	

	private String   				cEK_VK= null;
	
	private double dMengeKontrakt = 					0;
	private double dMengeGegenSeite =   				0;
	private double dMengeLager =  						0;
	
	private double dMengeFuhreGesamtPlan_oder_Echt = 	0;
	private double dMengeFuhreGesamt_Echt = 			0;
	private double dMenge_RG_Positionen = 				0;
	private double dMengeLagerInFuhren = 				0;

	//2012-10-26: betrachtung nettomengen (beruecksichtigte mengenabzuege auf dem lager)
	private double dMengeFuhreGesamtPlan_oder_Echt_netto = 	0;
	private double dMengeFuhreGesamt_Echt_netto = 			 	0;
	private double dMenge_RG_Positionen_netto = 				0;
	private double dMengeLagerInFuhren_echt_oder_plan_netto = 	0;
	
	
	
	private boolean  bIstAktiv = false;
	


	public BSKC__AUSLASTUNG(RECORD_VPOS_KON recVPOSKON, boolean bAuswertungInConstructor) throws myException
	{
		super();
		this.recVPOS_KON = recVPOSKON;
		
		if (this.recVPOS_KON==null)
			throw new myException("Error: record_vpos_kon MUST be set !!");
		
		this.cEK_VK="VK";
		if (this.recVPOS_KON.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_VORGANG_TYP_cUF().equals(myCONST.VORGANGSART_EK_KONTRAKT))
		{
			this.cEK_VK="EK";
		}
		
		if (bAuswertungInConstructor)
		{
			this.MakeAktiv();
		}
	}
	
	
	//umstellung auf record-berechnung 
	public void MakeAktiv() throws myException {

		
		//vor 20170830
//		this.bIstAktiv = true;
//		
//		//summe der zuordnungsmengen bei der anderen seite
//		RECLIST_EK_VK_BEZUG 		reclistBezug = this.cEK_VK.equals("EK")?this.recVPOS_KON.get_DOWN_RECORD_LIST_EK_VK_BEZUG_id_vpos_kon_ek():this.recVPOS_KON.get_DOWN_RECORD_LIST_EK_VK_BEZUG_id_vpos_kon_vk();
//		RECLIST_VPOS_KON_LAGER  	reclistLager = this.recVPOS_KON.get_DOWN_RECORD_LIST_VPOS_KON_LAGER_id_vpos_kon();
//		RECLIST_VPOS_TPA_FUHRE  	reclistFuhre = this.cEK_VK.equals("EK")?this.recVPOS_KON.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_kon_ek():this.recVPOS_KON.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_kon_vk();
//		RECLIST_VPOS_TPA_FUHRE_ORT 	reclistFuhreOrt = this.recVPOS_KON.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_kon();
//		RECLIST_VPOS_RG 			recListVPosRG  = this.recVPOS_KON.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_kon_zugeord();
//		
//		this.dMengeKontrakt = 		this.recVPOS_KON.get_ANZAHL_dValue(new Double(0));
//		this.dMengeGegenSeite =   	reclistBezug.get_ANZAHL_d_Summe(null);
//		this.dMengeLager =  			reclistLager.get_LAGERMENGE_d_Summe(null);
//		
//		this.dMengeFuhreGesamtPlan_oder_Echt = 		0;
//		this.dMengeFuhreGesamt_Echt = 			0;
//		this.dMenge_RG_Positionen = 	0;
//		this.dMengeLagerInFuhren = 	0;
//
//		this.dMengeFuhreGesamtPlan_oder_Echt_netto = 		0;
//		this.dMengeFuhreGesamt_Echt_netto = 			0;
//		this.dMenge_RG_Positionen_netto = 	0;
//		this.dMengeLagerInFuhren_echt_oder_plan_netto = 	0;
//		
//		
//		Double d0 = new Double(0);
//		
//		//teil 1:  Fuhrenbasis
//		for (Entry<String, RECORD_VPOS_TPA_FUHRE> oEntry: reclistFuhre.entrySet())
//		{
//			RECORD_VPOS_TPA_FUHRE recFuhreBasis = oEntry.getValue();
//
//			//2012-10-26: spezielle RECORDs mit angepassten rechenmethoden
//			RECORD_VPOS_TPA_FUHRE_SPEZ recFuhre = new RECORD_VPOS_TPA_FUHRE_SPEZ(recFuhreBasis);
//			
//			if (recFuhre.is_DELETED_NO() && recFuhre.is_IST_STORNIERT_NO())
//			{
//				if (cEK_VK.equals("EK"))
//				{
//					//alt
//					dMengeFuhreGesamtPlan_oder_Echt += recFuhre.get_ANTEIL_LADEMENGE_LIEF_dValue(recFuhre.get_ANTEIL_PLANMENGE_LIEF_dValue(d0));
//					dMengeFuhreGesamt_Echt += recFuhre.get_ANTEIL_LADEMENGE_LIEF_dValue(d0);
//					if (recFuhre.get_ID_ADRESSE_ZIEL_cUF_NN("-").equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("--")))
//					{
//						dMengeLagerInFuhren += recFuhre.get_ANTEIL_LADEMENGE_LIEF_dValue(recFuhre.get_ANTEIL_PLANMENGE_LIEF_dValue(d0));
//					}
//					
//					//2012-10-26: nettomengen (evtl. gedrehte abrechungsverhaeltnisse beruecksichtigt)
//					dMengeFuhreGesamtPlan_oder_Echt_netto += 	(recFuhre.get__MengeZu_EK_Kontrakt_oder_PlanMenge_EK_netto());
//					dMengeFuhreGesamt_Echt_netto += 			(recFuhre.get__MengeZu_EK_Kontrakt_netto());
//					if (recFuhre.get_ID_ADRESSE_ZIEL_cUF_NN("-").equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("--")))
//					{
//						//dMengeLagerInFuhren_echt_oder_plan_netto += (recFuhre.get__LadeMengeNetto_oder_planmenge());
//						//2012-11-16: bug
//						dMengeLagerInFuhren_echt_oder_plan_netto += (recFuhre.get__Abrechnungs_oder_Planmenge_Ladeseite_netto());
//					}
//					
//				}
//				else
//				{
//					//alt
//					dMengeFuhreGesamtPlan_oder_Echt += recFuhre.get_ANTEIL_ABLADEMENGE_ABN_dValue(recFuhre.get_ANTEIL_PLANMENGE_ABN_dValue(new Double(0)));
//					dMengeFuhreGesamt_Echt += recFuhre.get_ANTEIL_ABLADEMENGE_ABN_dValue(new Double(0));
//					if (recFuhre.get_ID_ADRESSE_START_cUF_NN("-").equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("--")))
//					{
//						dMengeLagerInFuhren += recFuhre.get_ANTEIL_ABLADEMENGE_ABN_dValue(recFuhre.get_ANTEIL_PLANMENGE_ABN_dValue(new Double(0)));
//					}
//					
//					//2012-10-26: nettomengen (evtl. gedrehte abrechungsverhaeltnisse beruecksichtigt)
//					dMengeFuhreGesamtPlan_oder_Echt_netto += 	(recFuhre.get__MengeZu_VK_Kontrakt_oder_PlanMenge_VK_netto());
//					dMengeFuhreGesamt_Echt_netto += 			(recFuhre.get__MengeZu_VK_Kontrakt_netto());
//					if (recFuhre.get_ID_ADRESSE_START_cUF_NN("-").equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("--")))
//					{
//						//dMengeLagerInFuhren_echt_oder_plan_netto += (recFuhre.get__AbladeMengeNetto_oder_planmenge());
//						//2012-11-16: bug
//						dMengeLagerInFuhren_echt_oder_plan_netto += (recFuhre.get__Abrechnungs_oder_Planmenge_Abladeseite_netto());
//					}
//					
//				}
//			}
//		}
//		
//		
//		//teil 2:  Fuhrenbasis-Orte
//		for (Entry<String, RECORD_VPOS_TPA_FUHRE_ORT> oEntry: reclistFuhreOrt.entrySet())
//		{
//			RECORD_VPOS_TPA_FUHRE_ORT 	recFuhreOrtBasis = 	oEntry.getValue();
//			RECORD_VPOS_TPA_FUHRE	 	recFuhreBasis = 	recFuhreOrtBasis.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre();
//			
//			//2012-10-26: spezielle RECORDs mit angepassten rechenmethoden
//			RECORD_VPOS_TPA_FUHRE_ORT_SPEZ recFuhreOrt = new RECORD_VPOS_TPA_FUHRE_ORT_SPEZ(recFuhreOrtBasis);
//			RECORD_VPOS_TPA_FUHRE_SPEZ recFuhre = 		new RECORD_VPOS_TPA_FUHRE_SPEZ(recFuhreBasis);
//			
//			
//			if (recFuhreOrt.is_DELETED_NO())
//			{
//				if (cEK_VK.equals("EK"))
//				{
//					//alt
//					dMengeFuhreGesamtPlan_oder_Echt += 	recFuhreOrt.get_ANTEIL_LADEMENGE_dValue(recFuhreOrt.get_ANTEIL_PLANMENGE_dValue(new Double(0)));
//					dMengeFuhreGesamt_Echt += 			recFuhreOrt.get_ANTEIL_LADEMENGE_dValue(new Double(0));
//					if (recFuhre.get_ID_ADRESSE_ZIEL_cUF_NN("-").equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("--")))
//					{
//						dMengeLagerInFuhren += recFuhreOrt.get_ANTEIL_LADEMENGE_dValue(recFuhreOrt.get_ANTEIL_PLANMENGE_dValue(new Double(0)));
//					}
//					
//					//2012-10-26: nettomengen (evtl. gedrehte abrechungsverhaeltnisse beruecksichtigt)
//					dMengeFuhreGesamtPlan_oder_Echt_netto += 	(recFuhreOrt.get__MengeZu_Kontrakt_oder_PlanMenge_netto());
//					dMengeFuhreGesamt_Echt_netto += 			(recFuhreOrt.get__MengeZu_Kontrakt_netto());
//					if (recFuhre.get_ID_ADRESSE_ZIEL_cUF_NN("-").equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("--")))
//					{
//						//dMengeLagerInFuhren_echt_oder_plan_netto += (recFuhreOrt.get__MengeNetto_oder_planmenge());
//						//2012-11-16: bug
//						dMengeLagerInFuhren_echt_oder_plan_netto += (recFuhreOrt.get__Abrechnungs_oder_Planmenge_netto());
//
//					}
//					
//				}
//				else
//				{
//					//alt
//					dMengeFuhreGesamtPlan_oder_Echt += recFuhreOrt.get_ANTEIL_ABLADEMENGE_dValue(recFuhreOrt.get_ANTEIL_PLANMENGE_dValue(new Double(0)));
//					dMengeFuhreGesamt_Echt += recFuhreOrt.get_ANTEIL_ABLADEMENGE_dValue(new Double(0));
//					if (recFuhre.get_ID_ADRESSE_START_cUF_NN("-").equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("--")))
//					{
//						dMengeLagerInFuhren += recFuhreOrt.get_ANTEIL_ABLADEMENGE_dValue(recFuhreOrt.get_ANTEIL_PLANMENGE_dValue(new Double(0)));
//					}
//					
//					//2012-10-26: nettomengen (evtl. gedrehte abrechungsverhaeltnisse beruecksichtigt)
//					dMengeFuhreGesamtPlan_oder_Echt_netto += 	(recFuhreOrt.get__MengeZu_Kontrakt_oder_PlanMenge_netto());
//					dMengeFuhreGesamt_Echt_netto += 			(recFuhreOrt.get__MengeZu_Kontrakt_netto());
//					if (recFuhre.get_ID_ADRESSE_ZIEL_cUF_NN("-").equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("--")))
//					{
//						//dMengeLagerInFuhren_echt_oder_plan_netto += (recFuhreOrt.get__MengeNetto_oder_planmenge());
//						//2012-11-16: bug
//						dMengeLagerInFuhren_echt_oder_plan_netto += (recFuhreOrt.get__Abrechnungs_oder_Planmenge_netto());
//					}
//				}
//			}			
//		}
//		
//		
//		//teil 3: menge in nicht geloeschten Rechnungen
//		//teil 2:  Fuhrenbasis-Orte
//		for (Entry<String, RECORD_VPOS_RG> oEntry: recListVPosRG.entrySet())
//		{
//			if (oEntry.getValue().is_DELETED_NO())
//			{
//				//bei EK-Kontrakten werden nur gutschriften summiert
//				if (this.cEK_VK.equals("EK") && oEntry.getValue().get_LAGER_VORZEICHEN_lValue(null)==1)
//				{
//					dMenge_RG_Positionen+=oEntry.getValue().get_ANZAHL_dValue(d0);
//					
//					//2012-10-26: nettomengen
//					dMenge_RG_Positionen_netto +=(oEntry.getValue().get_ANZAHL_dValue(d0)-oEntry.getValue().get_ANZAHL_ABZUG_LAGER_dValue(d0));
//					
//				}
//				//bei VK-Kontrakten nur rechnungen
//				if (this.cEK_VK.equals("VK") && oEntry.getValue().get_LAGER_VORZEICHEN_lValue(null)==-1)
//				{
//					dMenge_RG_Positionen+=oEntry.getValue().get_ANZAHL_dValue(d0);
//					
//					//2012-10-26: nettomengen
//					dMenge_RG_Positionen_netto +=(oEntry.getValue().get_ANZAHL_dValue(d0)-oEntry.getValue().get_ANZAHL_ABZUG_LAGER_dValue(d0));
//
//				}
//				
//			}			
//		}

		
		//ab20170830:
		this.bIstAktiv = true;

		HashMap<CLEARINGTAGS, Double> 						hmValues = new RECORD_VPOS_KON_EXT(this.recVPOS_KON).getClearingInfos();
		
		this.dMengeKontrakt = 								hmValues.get(RECORD_VPOS_KON_EXT.CLEARINGTAGS.MengeKontrakt);
		this.dMengeGegenSeite =   							hmValues.get(RECORD_VPOS_KON_EXT.CLEARINGTAGS.MengeGegenSeite);
		this.dMengeLager =  								hmValues.get(RECORD_VPOS_KON_EXT.CLEARINGTAGS.MengeLager);
		
		this.dMengeFuhreGesamtPlan_oder_Echt = 				hmValues.get(RECORD_VPOS_KON_EXT.CLEARINGTAGS.MengeFuhreGesamtPlan_oder_Echt);
		this.dMengeFuhreGesamt_Echt = 						hmValues.get(RECORD_VPOS_KON_EXT.CLEARINGTAGS.MengeFuhreGesamt_Echt);
		this.dMenge_RG_Positionen = 						hmValues.get(RECORD_VPOS_KON_EXT.CLEARINGTAGS.Menge_RG_Positionen);
		this.dMengeLagerInFuhren = 							hmValues.get(RECORD_VPOS_KON_EXT.CLEARINGTAGS.MengeLagerInFuhren);

		this.dMengeFuhreGesamtPlan_oder_Echt_netto = 		hmValues.get(RECORD_VPOS_KON_EXT.CLEARINGTAGS.MengeFuhreGesamtPlan_oder_Echt_netto);
		this.dMengeFuhreGesamt_Echt_netto = 				hmValues.get(RECORD_VPOS_KON_EXT.CLEARINGTAGS.MengeFuhreGesamt_Echt_netto);
		this.dMenge_RG_Positionen_netto = 					hmValues.get(RECORD_VPOS_KON_EXT.CLEARINGTAGS.Menge_RG_Positionen_netto);
		this.dMengeLagerInFuhren_echt_oder_plan_netto = 	hmValues.get(RECORD_VPOS_KON_EXT.CLEARINGTAGS.MengeLagerInFuhren_echt_oder_plan_netto);
		
	}
	
	public boolean get_bAktiv() 
	{
		return bIstAktiv;
	}
	
	
	
	public RECORD_VPOS_KON get_oVPOS_KON() 
	{
		return recVPOS_KON;
	}

	public String get_cEK_VK() 
	{
		return cEK_VK;
	}

	public double get_dMengeKontrakt() 
	{
		return dMengeKontrakt;
	}

	public double get_dMengeGegenSeite() 
	{
		return dMengeGegenSeite;
	}

	public double get_dMengeLager() 
	{
		return dMengeLager;
	}

	public double get_dMengeFuhreGesamtPlan_oder_Echt() 
	{
		return dMengeFuhreGesamtPlan_oder_Echt;
	}

	public double get_dMengeFuhreGesamt_Echt() 
	{
		return dMengeFuhreGesamt_Echt;
	}

	public double get_dMenge_RG_Positionen() 
	{
		return dMenge_RG_Positionen;
	}

	public double get_dMengeLagerInFuhren() 
	{
		return dMengeLagerInFuhren;
	}

	public double get_dGesamteMengeZuordnung()
	{
		return this.get_dMengeGegenSeite()+this.get_dMengeLager();
	}
	
	
	public double get_dRestMengeNochOffen_in_der_Planung()
	{
		return this.get_dMengeKontrakt()-this.get_dMengeGegenSeite()-this.get_dMengeLager();
	}
	

	
	 
	public String get_cMengeKontrakt(int iNachkomma) 
	{
		return MyNumberFormater.formatDez(dMengeKontrakt,iNachkomma,true);
	}

	public String get_cMengeGegenSeite(int iNachkomma) 
	{
		return MyNumberFormater.formatDez(dMengeGegenSeite,iNachkomma,true);
	}

	public String get_cMengeLager(int iNachkomma) 
	{
		return MyNumberFormater.formatDez(dMengeLager,iNachkomma,true);
	}

	public String get_cMengeFuhreGesamtPlan_oder_Echt(int iNachkomma) 
	{
		return MyNumberFormater.formatDez(dMengeFuhreGesamtPlan_oder_Echt,iNachkomma,true);
	}

	public String get_cMengeFuhreGesamt_Echt(int iNachkomma) 
	{
		return MyNumberFormater.formatDez(dMengeFuhreGesamt_Echt,iNachkomma,true);
	}

	public String get_cMenge_RG_Positionen(int iNachkomma) 
	{
		return MyNumberFormater.formatDez(dMenge_RG_Positionen,iNachkomma,true);
	}

	public String get_cMengeLagerInFuhren(int iNachkomma) 
	{
		return MyNumberFormater.formatDez(dMengeLagerInFuhren,iNachkomma,true);
	}

	public String get_cGesamteMengeZuordnung(int iNachkomma)
	{
		return MyNumberFormater.formatDez(this.get_dMengeGegenSeite()+this.get_dMengeLager(),iNachkomma,true);
	}
	
	
	public String get_cRestMengeNochOffen_in_der_Planung(int iNachkomma)
	{
		return MyNumberFormater.formatDez(this.get_dMengeKontrakt()-this.get_dMengeGegenSeite()-this.get_dMengeLager(),iNachkomma,true);
	}


	//2012-10-26: netto-mengen
	public double get_dMengeFuhreGesamtPlan_oder_Echt_netto()
	{
		return this.dMengeFuhreGesamtPlan_oder_Echt_netto;
	}


	public double get_dMengeFuhreGesamt_Echt_netto()
	{
		return this.dMengeFuhreGesamt_Echt_netto;
	}


	public double get_dMenge_RG_Positionen_netto()
	{
		return this.dMenge_RG_Positionen_netto;
	}


	public double get_ddMengeLagerInFuhren_echt_oder_plan_netto()
	{
		return this.dMengeLagerInFuhren_echt_oder_plan_netto;
	}

	
	
	
	
	
	
	
}
