package rohstoff.Echo2BusinessLogic.__SPECIALRECORDS;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_EK_VK_BEZUG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_KON_LAGER;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX.KFIX_K__JUMPER_TO_RechGut;



public class RECORD_VPOS_KON_EXT extends RECORD_VPOS_KON
{
	
	
	public static enum CLEARINGTAGS {
		MengeKontrakt
		,MengeGegenSeite
		,MengeLager
		,GesamteMengeZuordnung
		,MengeFuhreGesamtPlan_oder_Echt
		,MengeFuhreGesamt_Echt
		,Menge_RG_Positionen
		,MengeLagerInFuhren
		,MengeFuhreGesamtPlan_oder_Echt_netto
		,MengeFuhreGesamt_Echt_netto
		,Menge_RG_Positionen_netto
		,MengeLagerInFuhren_echt_oder_plan_netto
		,RestMengeNochOffen_in_der_Planung
		;
	}
	
	
	//hasmap wird beim ersten aufruf gefuellt
	private HashMap<CLEARINGTAGS, Double> hmClearingInfos = null;
	
	
	private Boolean bEK = null;
//	private String  cSuchText = null;
	private BigDecimal[]  arrayBDMengePlanEcht = null;
	private BigDecimal    bdMengeBerechnet = null;
	

	
	
	
	public RECORD_VPOS_KON_EXT() throws myException 
	{
		super();
	}

	public RECORD_VPOS_KON_EXT(long lID_Unformated, MyConnection Conn) 	throws myException 
	{
		super(lID_Unformated, Conn);
	}

	public RECORD_VPOS_KON_EXT(long lID_Unformated) throws myException 
	{
		super(lID_Unformated);
	}

	public RECORD_VPOS_KON_EXT(MyConnection Conn) throws myException 
	{
		super(Conn);
	}

	public RECORD_VPOS_KON_EXT(RECORD_VPOS_KON recordOrig) 
	{
		super(recordOrig);
	}

	
	public RECORD_VPOS_KON_EXT(String c_ID_or_WHEREBLOCK_OR_SQL,MyConnection Conn) throws myException 
	{
		super(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}

	public RECORD_VPOS_KON_EXT(String c_ID_or_WHEREBLOCK_OR_SQL)	throws myException 
	{
		super(c_ID_or_WHEREBLOCK_OR_SQL);
	}

	
	/**
	 * 
	 * @return s Bigdecimal[2]: (EK:) plan oder lade / lade    (VK:) plan oder ablade / ablade
	 *           [0] = plan
	 *           [1] = lade
	 *           [2] = realer Abzug
	 * @throws myException
	 */
	public BigDecimal[]  get_MengeGeliefertPlanEcht() throws myException
	{
		if (this.bEK==null)
		{
			this.bEK = (this.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_VORGANG_TYP_cUF_NN("").equals(myCONST.VORGANGSART_EK_KONTRAKT));
		}
		
		if (this.arrayBDMengePlanEcht!=null)
		{
			return this.arrayBDMengePlanEcht;        //nur einmal berechnen
		}
		else
		{
		
			this.arrayBDMengePlanEcht = new BigDecimal[3];
			
			this.arrayBDMengePlanEcht[0] = new BigDecimal(0);  //plan
			this.arrayBDMengePlanEcht[1] = new BigDecimal(0);  //echt
			this.arrayBDMengePlanEcht[2] = new BigDecimal(0);  //abzug (nicht inhalt)
			
	
			if (this.bEK)
			{
				RECLIST_VPOS_TPA_FUHRE   			reclistFuhre = 		this.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_kon_ek("NVL(DELETED,'N')='N'",null,true);
				RECLIST_VPOS_TPA_FUHRE_ORT   		reclistFuhreOrt = 	this.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_kon("NVL(DELETED,'N')='N' AND DEF_QUELLE_ZIEL='EK'",null,true);
				
				Iterator<RECORD_VPOS_TPA_FUHRE> 	iteratorFuhre =  	reclistFuhre.values().iterator();
				Iterator<RECORD_VPOS_TPA_FUHRE_ORT> iteratorFuhreOrt =  reclistFuhreOrt.values().iterator();
	
				while (iteratorFuhre.hasNext())
				{
					RECORD_VPOS_TPA_FUHRE recFuhre = iteratorFuhre.next();
					if (!(recFuhre.is_DELETED_YES() || recFuhre.is_IST_STORNIERT_YES()))
					{
						this.arrayBDMengePlanEcht[0]=this.arrayBDMengePlanEcht[0].add(recFuhre.get_ANTEIL_LADEMENGE_LIEF_bdValue(recFuhre.get_ANTEIL_ABLADEMENGE_LIEF_bdValue(recFuhre.get_ANTEIL_PLANMENGE_LIEF_bdValue(new BigDecimal(0)))));
	
						this.arrayBDMengePlanEcht[1]=this.arrayBDMengePlanEcht[1].add(recFuhre.get_ANTEIL_LADEMENGE_LIEF_bdValue(recFuhre.get_ANTEIL_ABLADEMENGE_LIEF_bdValue(new BigDecimal(0))));
						
						this.arrayBDMengePlanEcht[2]=this.arrayBDMengePlanEcht[2].add(recFuhre.get_ABZUG_LADEMENGE_LIEF_bdValue(new BigDecimal(0)));
					}
				}
				
				while (iteratorFuhreOrt.hasNext())
				{
					RECORD_VPOS_TPA_FUHRE_ORT recFuhreOrt = iteratorFuhreOrt.next();
					if (!(recFuhreOrt.is_DELETED_YES() || recFuhreOrt.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre().is_IST_STORNIERT_YES()))
					{
						this.arrayBDMengePlanEcht[0]=this.arrayBDMengePlanEcht[0].add(recFuhreOrt.get_ANTEIL_LADEMENGE_bdValue(recFuhreOrt.get_ANTEIL_ABLADEMENGE_bdValue(recFuhreOrt.get_ANTEIL_PLANMENGE_bdValue(new BigDecimal(0)))));
	
						this.arrayBDMengePlanEcht[1]=this.arrayBDMengePlanEcht[1].add(recFuhreOrt.get_ANTEIL_LADEMENGE_bdValue(recFuhreOrt.get_ANTEIL_ABLADEMENGE_bdValue(new BigDecimal(0))));
						
						this.arrayBDMengePlanEcht[2]=this.arrayBDMengePlanEcht[2].add(recFuhreOrt.get_ABZUG_MENGE_bdValue(new BigDecimal(0)));
					}
				}
			}
			else
			{
				RECLIST_VPOS_TPA_FUHRE   			reclistFuhre = 		this.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_kon_vk("NVL(DELETED,'N')='N'",null,true);
				RECLIST_VPOS_TPA_FUHRE_ORT   		reclistFuhreOrt = 	this.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_kon("NVL(DELETED,'N')='N' AND DEF_QUELLE_ZIEL='VK'",null,true);
				
				Iterator<RECORD_VPOS_TPA_FUHRE> 	iteratorFuhre =  	reclistFuhre.values().iterator();
				Iterator<RECORD_VPOS_TPA_FUHRE_ORT> iteratorFuhreOrt =  reclistFuhreOrt.values().iterator();
	
				while (iteratorFuhre.hasNext())
				{
					RECORD_VPOS_TPA_FUHRE recFuhre = iteratorFuhre.next();
					if (!(recFuhre.is_DELETED_YES() || recFuhre.is_IST_STORNIERT_YES()))
					{
						this.arrayBDMengePlanEcht[0]=this.arrayBDMengePlanEcht[0].add(recFuhre.get_ANTEIL_ABLADEMENGE_ABN_bdValue(recFuhre.get_ANTEIL_LADEMENGE_ABN_bdValue(recFuhre.get_ANTEIL_PLANMENGE_ABN_bdValue(new BigDecimal(0)))));
	
						this.arrayBDMengePlanEcht[1]=this.arrayBDMengePlanEcht[1].add(recFuhre.get_ANTEIL_ABLADEMENGE_ABN_bdValue(recFuhre.get_ANTEIL_LADEMENGE_ABN_bdValue(new BigDecimal(0))));
						
						this.arrayBDMengePlanEcht[2]=this.arrayBDMengePlanEcht[2].add(recFuhre.get_ABZUG_ABLADEMENGE_ABN_bdValue(new BigDecimal(0)));
					}
				}
				
				while (iteratorFuhreOrt.hasNext())
				{
					RECORD_VPOS_TPA_FUHRE_ORT recFuhreOrt = iteratorFuhreOrt.next();
					if (!(recFuhreOrt.is_DELETED_YES() || recFuhreOrt.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre().is_IST_STORNIERT_YES()))
					{
						this.arrayBDMengePlanEcht[0]=this.arrayBDMengePlanEcht[0].add(recFuhreOrt.get_ANTEIL_ABLADEMENGE_bdValue(recFuhreOrt.get_ANTEIL_LADEMENGE_bdValue(recFuhreOrt.get_ANTEIL_PLANMENGE_bdValue(new BigDecimal(0)))));
	
						this.arrayBDMengePlanEcht[1]=this.arrayBDMengePlanEcht[1].add(recFuhreOrt.get_ANTEIL_ABLADEMENGE_bdValue(recFuhreOrt.get_ANTEIL_LADEMENGE_bdValue(new BigDecimal(0))));
						
						this.arrayBDMengePlanEcht[2]=this.arrayBDMengePlanEcht[2].add(recFuhreOrt.get_ABZUG_MENGE_bdValue(new BigDecimal(0)));
					}
				}
			}
			return this.arrayBDMengePlanEcht;
		}
	}
	

	
	public BigDecimal  get_MengeBerechnet() throws myException
	{
		if (this.bEK==null)
		{
			this.bEK = (this.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_VORGANG_TYP_cUF_NN("").equals(myCONST.VORGANGSART_EK_KONTRAKT));
		}

		if (this.bdMengeBerechnet!=null)
		{
			return this.bdMengeBerechnet;
		}
		else
		{
		
			this.bdMengeBerechnet = new BigDecimal(0);
			RECLIST_VPOS_RG 			reclistVPOS_RG =    this.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_kon_zugeord("NVL(DELETED,'N')='N'","NVL(TO_CHAR(JT_VPOS_RG.AUSFUEHRUNGSDATUM,'YYYY-MM-DD'),'0000-00-00')",true);
			Iterator<RECORD_VPOS_RG> 	iteratorVposRG  =   reclistVPOS_RG.values().iterator();
	
			//bereits abgerechnete positionen summieren (gedruckte rechnungen)
			while (iteratorVposRG.hasNext())
			{
				RECORD_VPOS_RG recRG = iteratorVposRG.next();
				if (	recRG.is_DELETED_NO() && 
						S.isEmpty(recRG.get_ID_VPOS_RG_STORNO_VORGAENGER_cUF_NN("")) && 
						S.isEmpty(recRG.get_ID_VPOS_RG_STORNO_NACHFOLGER_cUF_NN("")) &&
						recRG.get_UP_RECORD_VKOPF_RG_id_vkopf_rg()!=null &&
						recRG.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().is_ABGESCHLOSSEN_YES()) 
				{
					//2012-03-16: anzeige der nettomenge und des resultierenden e-preis auf netto
					//ALT: this.bdMengeBerechnet=this.bdMengeBerechnet.add(recRG.get_ANZAHL_bdValue(new BigDecimal(0)));
					this.bdMengeBerechnet=this.bdMengeBerechnet.add(new RECORD_VPOS_RG_EXT(recRG)._get_ANZAHL_NETTO_bd());
				}
			}
			
			return this.bdMengeBerechnet;
		}
	}

	


	

	/*
	 * liefer hashmap mit 2 keys und 2 grids Keys: RECHINFOS  und PREIS
	 */
	public HashMap<String,MyE2_Grid>  get_grid_Gutschrift_Rechnungs_Nummern_undZusaetze(GridLayoutData GLLinks, GridLayoutData GLRechts,int[] iSpalten,int[] iSpalten2) throws myException
	{
		if (this.bEK==null)
		{
			this.bEK = (this.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_VORGANG_TYP_cUF_NN("").equals(myCONST.VORGANGSART_EK_KONTRAKT));
		}
		
		
		RECLIST_VPOS_RG 			reclistVPOS_RG =    this.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_kon_zugeord("NVL(DELETED,'N')='N' AND ID_VPOS_RG_STORNO_VORGAENGER IS NULL AND ID_VPOS_RG_STORNO_NACHFOLGER IS NULL",
																												"NVL(TO_CHAR(JT_VPOS_RG.AUSFUEHRUNGSDATUM,'YYYY-MM-DD'),'0000-00-00')",false);
//		Iterator<RECORD_VPOS_RG> 	iteratorVposRG  =   reclistVPOS_RG.values().iterator();

		VectorSingle   vSort =         new VectorSingle();  //ausschluss von doppelten Positionen in einer rechnung und sortierung
		HashMap<String, MyE2_Label[]>  hmHelp = new HashMap<String, MyE2_Label[]>();
		HashMap<String, Component[]>   hmHelp2 = new HashMap<String, Component[]>();

		//jetzt die labels in das grid eintragen
		MyE2_Grid oGridRECHINFOS = 		new MyE2_Grid(iSpalten, MyE2_Grid.STYLE_GRID_BORDER(Color.BLACK));
		MyE2_Grid oGridPreisUndJumper = new MyE2_Grid(iSpalten2, MyE2_Grid.STYLE_GRID_BORDER(Color.BLACK));

		HashMap<String,MyE2_Grid> hmRueck = new HashMap<String, MyE2_Grid>();
		hmRueck.put("RECHINFOS", oGridRECHINFOS);
		hmRueck.put("PREIS", oGridPreisUndJumper);
		
		//bereits abgerechnete positionen anzeigen (gedruckte rechnungen)
//		while (iteratorVposRG.hasNext())
		for (int i=0;i<reclistVPOS_RG.get_vKeyValues().size();i++)
		{
			//RECORD_VPOS_RG recRG = iteratorVposRG.next();
			RECORD_VPOS_RG recRG = reclistVPOS_RG.get(i);
			if (	recRG.is_DELETED_NO() &&
					S.isEmpty(recRG.get_ID_VPOS_RG_STORNO_VORGAENGER_cUF_NN("")) && 
					S.isEmpty(recRG.get_ID_VPOS_RG_STORNO_NACHFOLGER_cUF_NN("")) &&
					recRG.get_UP_RECORD_VKOPF_RG_id_vkopf_rg()!=null &&
					recRG.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().is_ABGESCHLOSSEN_YES())
			{
				
				//posnummer mit mit 0 aufgefuettert, wenn es einstellige sind
				String cPosNummer = recRG.get_POSITIONSNUMMER_cF_NN("<POS>").length()==1
				                     ?
				                         "0"+recRG.get_POSITIONSNUMMER_cF_NN("<POS>")
				                    :
				                    	recRG.get_POSITIONSNUMMER_cF_NN("<POS>");
				                         
				String cBuchungsnummer = recRG.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().get_BUCHUNGSNUMMER_cUF_NN("??")+"-"+cPosNummer;
				
				MyE2_Label[] labelArr = new MyE2_Label[3];
				labelArr[0]=new MyE2_Label(cBuchungsnummer);
				//2012-03-16: anzeige der nettomenge und des resultierenden e-preis auf netto
				//ALT: labelArr[1]=new MyE2_Label(MyNumberFormater.formatDez(recRG.get_ANZAHL_bdValue(new BigDecimal(0)).longValue(),true));
				labelArr[1]=new MyE2_Label(MyNumberFormater.formatDez(new RECORD_VPOS_RG_EXT(recRG)._get_ANZAHL_NETTO_bd().longValue(),true), new MyE2_String("Rechnungs-/Gutschrifts-Nettomenge (Positionsmenge minus reale Abzüge)"));
				//---
				
				
				labelArr[2]=new MyE2_Label(myDateHelper.ChangeFormatStringToDateWithoutYear(recRG.get_AUSFUEHRUNGSDATUM_cF_NN("??.??.????")));
				
				Component[] labelArr2 = new Component[2];
				
				//2012-03-16: anzeige der nettomenge und des resultierenden e-preis auf netto
				//ALT: labelArr2[0]=new MyE2_Label(MyNumberFormater.formatDez(recRG.get_EINZELPREIS_RESULT_FW_bdValue(new BigDecimal(0)),2,true));
				labelArr2[0]=new MyE2_Label(MyNumberFormater.formatDez(recRG.get_EPREIS_RESULT_NETTO_MGE_FW_bdValue(new BigDecimal(0)),2,true), new MyE2_String("Rechnungs-/Gutschrift, Effektivpreis auf Nettomenge"));
				
				if (recRG.get_UP_RECORD_VKOPF_RG_id_vkopf_rg()!=null)
				{
					labelArr2[1]=new KFIX_K__JUMPER_TO_RechGut(recRG);
				}
				else
				{
					labelArr2[1]=new MyE2_Label("");
				}
				
				String cSortString = myDateHelper.ChangeNormalString2DBFormatString(recRG.get_AUSFUEHRUNGSDATUM_cF_NN("0000.00.00"))+cBuchungsnummer;
				hmHelp.put(cSortString, labelArr);
				hmHelp2.put(cSortString, labelArr2);
				vSort.add(cSortString);
			}
		}
		
		Collections.sort(vSort);
		
		for (int i=0;i<vSort.size();i++)
		{
			MyE2_Label[] arrayLAB = hmHelp.get(vSort.get(i));
			oGridRECHINFOS.add(arrayLAB[0], GLLinks);    //rechnung/Gutschrift-buchungsnummer
			oGridRECHINFOS.add(arrayLAB[2], GLRechts);    //leistungsdatum
			oGridRECHINFOS.add(arrayLAB[1], GLRechts);    //Menge
			
			Component[] comps = hmHelp2.get(vSort.get(i));
			oGridPreisUndJumper.add(comps[0], GLRechts);
			oGridPreisUndJumper.add(comps[1], GLRechts);
		}
		
		return hmRueck;
	}

	
	public BigDecimal  get_bdRestMenge() throws myException
	{
		BigDecimal bdRest = new BigDecimal(0);
		bdRest=bdRest.add(this.get_ANZAHL_bdValue(BigDecimal.ZERO));
		BigDecimal[] bdMengen = this.get_MengeGeliefertPlanEcht();
		bdRest=bdRest.add(bdMengen[2]);
		bdRest=bdRest.subtract(bdMengen[1]);
		
		return bdRest;

	}
	
	
	public boolean get_bIsEK() throws myException
	{
		if (this.bEK==null)
		{
			this.bEK = (this.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_VORGANG_TYP_cUF_NN("").equals(myCONST.VORGANGSART_EK_KONTRAKT));
		}

		return this.bEK.booleanValue();
	}
	
	
	public boolean isEK() throws myException {
		return this.get_bIsEK();
	}
	
	public boolean isVK() throws myException {
		return !this.isEK();
	}
	
	
	/**
	 * berechnet die relationsmengen zu dieser kontraktposition
	 * @return
	 * @throws myException 
	 */
	public HashMap<CLEARINGTAGS, Double> getClearingInfos() throws myException {
		
		if (this.hmClearingInfos==null) {
		
			hmClearingInfos = new HashMap<>();
			
			//initialisieren
			for (CLEARINGTAGS ct: CLEARINGTAGS.values()) {
				hmClearingInfos.put(ct, 0d);
			}
			
	
			//summe der zuordnungsmengen bei der anderen seite
			RECLIST_EK_VK_BEZUG 		reclistBezug = this.isEK()?this.get_DOWN_RECORD_LIST_EK_VK_BEZUG_id_vpos_kon_ek():this.get_DOWN_RECORD_LIST_EK_VK_BEZUG_id_vpos_kon_vk();
			RECLIST_VPOS_KON_LAGER  	reclistLager = this.get_DOWN_RECORD_LIST_VPOS_KON_LAGER_id_vpos_kon();
			RECLIST_VPOS_TPA_FUHRE  	reclistFuhre = this.isEK()?this.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_kon_ek():this.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_kon_vk();
			RECLIST_VPOS_TPA_FUHRE_ORT 	reclistFuhreOrt = this.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_kon();
			RECLIST_VPOS_RG 			recListVPosRG  = this.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_kon_zugeord();
			
			//this.dMengeKontrakt = 	
			double dMengeKontrakt = 	this.get_ANZAHL_dValue(0d);
			hmClearingInfos.put(CLEARINGTAGS.MengeKontrakt, dMengeKontrakt);
			
			//this.dMengeGegenSeite =   	reclistBezug.get_ANZAHL_d_Summe(null);
			double dMengeGegenSeite = 	reclistBezug.get_ANZAHL_d_Summe(null);
			hmClearingInfos.put(CLEARINGTAGS.MengeGegenSeite, dMengeGegenSeite);
			
			//this.dMengeLager =  			reclistLager.get_LAGERMENGE_d_Summe(null);
			double dMengeLager = 		reclistLager.get_LAGERMENGE_d_Summe(null);
			hmClearingInfos.put(CLEARINGTAGS.MengeLager, dMengeLager);
			
			Double d0 = new Double(0);
	
			double dMengeFuhreGesamtPlan_oder_Echt=0;
			double dMengeFuhreGesamt_Echt=0;
			double dMengeLagerInFuhren=0;
			
			double dMengeFuhreGesamtPlan_oder_Echt_netto=0;
			double dMengeFuhreGesamt_Echt_netto=0;
			double dMengeLagerInFuhren_echt_oder_plan_netto=0;
			
			double dMenge_RG_Positionen=0;
			double dMenge_RG_Positionen_netto=0;
			
			
			//teil 1:  Fuhrenbasis
			for (Entry<String, RECORD_VPOS_TPA_FUHRE> oEntry: reclistFuhre.entrySet()) {
				RECORD_VPOS_TPA_FUHRE recFuhreBasis = oEntry.getValue();
	
				//2012-10-26: spezielle RECORDs mit angepassten rechenmethoden
				RECORD_VPOS_TPA_FUHRE_SPEZ recFuhre = new RECORD_VPOS_TPA_FUHRE_SPEZ(recFuhreBasis);
				
				if (recFuhre.is_DELETED_NO() && recFuhre.is_IST_STORNIERT_NO()) {
					if (this.isEK()) 	{
						dMengeFuhreGesamtPlan_oder_Echt += recFuhre.get_ANTEIL_LADEMENGE_LIEF_dValue(recFuhre.get_ANTEIL_PLANMENGE_LIEF_dValue(d0));
						dMengeFuhreGesamt_Echt += recFuhre.get_ANTEIL_LADEMENGE_LIEF_dValue(d0);
						if (recFuhre.get_ID_ADRESSE_ZIEL_cUF_NN("-").equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("--"))) {
							dMengeLagerInFuhren += recFuhre.get_ANTEIL_LADEMENGE_LIEF_dValue(recFuhre.get_ANTEIL_PLANMENGE_LIEF_dValue(d0));
						}
						
						//2012-10-26: nettomengen (evtl. gedrehte abrechungsverhaeltnisse beruecksichtigt)
						dMengeFuhreGesamtPlan_oder_Echt_netto += 	(recFuhre.get__MengeZu_EK_Kontrakt_oder_PlanMenge_EK_netto());
						dMengeFuhreGesamt_Echt_netto += 			(recFuhre.get__MengeZu_EK_Kontrakt_netto());
						if (recFuhre.get_ID_ADRESSE_ZIEL_cUF_NN("-").equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("--")))	{
							//dMengeLagerInFuhren_echt_oder_plan_netto += (recFuhre.get__LadeMengeNetto_oder_planmenge());
							//2012-11-16: bug
							dMengeLagerInFuhren_echt_oder_plan_netto += (recFuhre.get__Abrechnungs_oder_Planmenge_Ladeseite_netto());
						}
						
					} else	{
						dMengeFuhreGesamtPlan_oder_Echt += recFuhre.get_ANTEIL_ABLADEMENGE_ABN_dValue(recFuhre.get_ANTEIL_PLANMENGE_ABN_dValue(new Double(0)));
						dMengeFuhreGesamt_Echt += recFuhre.get_ANTEIL_ABLADEMENGE_ABN_dValue(new Double(0));
						if (recFuhre.get_ID_ADRESSE_START_cUF_NN("-").equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("--")))
						{
							dMengeLagerInFuhren += recFuhre.get_ANTEIL_ABLADEMENGE_ABN_dValue(recFuhre.get_ANTEIL_PLANMENGE_ABN_dValue(new Double(0)));
						}
						
						//2012-10-26: nettomengen (evtl. gedrehte abrechungsverhaeltnisse beruecksichtigt)
						dMengeFuhreGesamtPlan_oder_Echt_netto += 	(recFuhre.get__MengeZu_VK_Kontrakt_oder_PlanMenge_VK_netto());
						dMengeFuhreGesamt_Echt_netto += 			(recFuhre.get__MengeZu_VK_Kontrakt_netto());
						if (recFuhre.get_ID_ADRESSE_START_cUF_NN("-").equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("--")))
						{
							//dMengeLagerInFuhren_echt_oder_plan_netto += (recFuhre.get__AbladeMengeNetto_oder_planmenge());
							//2012-11-16: bug
							dMengeLagerInFuhren_echt_oder_plan_netto += (recFuhre.get__Abrechnungs_oder_Planmenge_Abladeseite_netto());
						}
						
					}
				}
			}
			
			
			//teil 2:  Fuhrenbasis-Orte
			for (Entry<String, RECORD_VPOS_TPA_FUHRE_ORT> oEntry: reclistFuhreOrt.entrySet())
			{
				RECORD_VPOS_TPA_FUHRE_ORT 	recFuhreOrtBasis = 	oEntry.getValue();
				RECORD_VPOS_TPA_FUHRE	 	recFuhreBasis = 	recFuhreOrtBasis.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre();
				
				//2012-10-26: spezielle RECORDs mit angepassten rechenmethoden
				RECORD_VPOS_TPA_FUHRE_ORT_SPEZ recFuhreOrt = new RECORD_VPOS_TPA_FUHRE_ORT_SPEZ(recFuhreOrtBasis);
				RECORD_VPOS_TPA_FUHRE_SPEZ recFuhre = 		new RECORD_VPOS_TPA_FUHRE_SPEZ(recFuhreBasis);
				
				
				if (recFuhreOrt.is_DELETED_NO()) {
					if (this.isEK()) {
						//alt
						dMengeFuhreGesamtPlan_oder_Echt += 	recFuhreOrt.get_ANTEIL_LADEMENGE_dValue(recFuhreOrt.get_ANTEIL_PLANMENGE_dValue(new Double(0)));
						dMengeFuhreGesamt_Echt += 			recFuhreOrt.get_ANTEIL_LADEMENGE_dValue(new Double(0));
						if (recFuhre.get_ID_ADRESSE_ZIEL_cUF_NN("-").equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("--")))	{
							dMengeLagerInFuhren += recFuhreOrt.get_ANTEIL_LADEMENGE_dValue(recFuhreOrt.get_ANTEIL_PLANMENGE_dValue(new Double(0)));
						}
						
						//2012-10-26: nettomengen (evtl. gedrehte abrechungsverhaeltnisse beruecksichtigt)
						dMengeFuhreGesamtPlan_oder_Echt_netto += 	(recFuhreOrt.get__MengeZu_Kontrakt_oder_PlanMenge_netto());
						dMengeFuhreGesamt_Echt_netto += 			(recFuhreOrt.get__MengeZu_Kontrakt_netto());
						if (recFuhre.get_ID_ADRESSE_ZIEL_cUF_NN("-").equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("--")))	{
							//dMengeLagerInFuhren_echt_oder_plan_netto += (recFuhreOrt.get__MengeNetto_oder_planmenge());
							dMengeLagerInFuhren_echt_oder_plan_netto += (recFuhreOrt.get__Abrechnungs_oder_Planmenge_netto());
						}
					} else 	{
						dMengeFuhreGesamtPlan_oder_Echt += recFuhreOrt.get_ANTEIL_ABLADEMENGE_dValue(recFuhreOrt.get_ANTEIL_PLANMENGE_dValue(new Double(0)));
						dMengeFuhreGesamt_Echt += recFuhreOrt.get_ANTEIL_ABLADEMENGE_dValue(new Double(0));
						if (recFuhre.get_ID_ADRESSE_START_cUF_NN("-").equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("--"))) {
							dMengeLagerInFuhren += recFuhreOrt.get_ANTEIL_ABLADEMENGE_dValue(recFuhreOrt.get_ANTEIL_PLANMENGE_dValue(new Double(0)));
						}
						
						//2012-10-26: nettomengen (evtl. gedrehte abrechungsverhaeltnisse beruecksichtigt)
						dMengeFuhreGesamtPlan_oder_Echt_netto += 	(recFuhreOrt.get__MengeZu_Kontrakt_oder_PlanMenge_netto());
						dMengeFuhreGesamt_Echt_netto += 			(recFuhreOrt.get__MengeZu_Kontrakt_netto());
						if (recFuhre.get_ID_ADRESSE_ZIEL_cUF_NN("-").equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("--")))	{
							//dMengeLagerInFuhren_echt_oder_plan_netto += (recFuhreOrt.get__MengeNetto_oder_planmenge());
							dMengeLagerInFuhren_echt_oder_plan_netto += (recFuhreOrt.get__Abrechnungs_oder_Planmenge_netto());
						}
					}
				}			
			}
			
			
			//teil 3: menge in nicht geloeschten Rechnungen
			//teil 2:  Fuhrenbasis-Orte
			for (Entry<String, RECORD_VPOS_RG> oEntry: recListVPosRG.entrySet()) {
				if (oEntry.getValue().is_DELETED_NO())	{
					//bei EK-Kontrakten werden nur gutschriften summiert
					if (this.isEK() && oEntry.getValue().get_LAGER_VORZEICHEN_lValue(null)==1) 	{
						dMenge_RG_Positionen+=oEntry.getValue().get_ANZAHL_dValue(d0);
						dMenge_RG_Positionen_netto +=(oEntry.getValue().get_ANZAHL_dValue(d0)-oEntry.getValue().get_ANZAHL_ABZUG_LAGER_dValue(d0));
					}
					//bei VK-Kontrakten nur rechnungen
					if (this.isVK() && oEntry.getValue().get_LAGER_VORZEICHEN_lValue(null)==-1)	{
						dMenge_RG_Positionen+=oEntry.getValue().get_ANZAHL_dValue(d0);
						dMenge_RG_Positionen_netto +=(oEntry.getValue().get_ANZAHL_dValue(d0)-oEntry.getValue().get_ANZAHL_ABZUG_LAGER_dValue(d0));
					}
				}			
			}
	
			hmClearingInfos.put(CLEARINGTAGS.MengeFuhreGesamtPlan_oder_Echt, 		dMengeFuhreGesamtPlan_oder_Echt);
			hmClearingInfos.put(CLEARINGTAGS.MengeFuhreGesamt_Echt, 				dMengeFuhreGesamt_Echt);
			hmClearingInfos.put(CLEARINGTAGS.MengeLagerInFuhren, 					dMengeLagerInFuhren);
			
			hmClearingInfos.put(CLEARINGTAGS.MengeFuhreGesamtPlan_oder_Echt_netto, 	dMengeFuhreGesamtPlan_oder_Echt_netto);
			hmClearingInfos.put(CLEARINGTAGS.MengeFuhreGesamt_Echt_netto, 			dMengeFuhreGesamt_Echt_netto);
			hmClearingInfos.put(CLEARINGTAGS.MengeLagerInFuhren_echt_oder_plan_netto, dMengeLagerInFuhren_echt_oder_plan_netto);
			
			hmClearingInfos.put(CLEARINGTAGS.Menge_RG_Positionen, 					dMenge_RG_Positionen);
			hmClearingInfos.put(CLEARINGTAGS.Menge_RG_Positionen_netto, 			dMenge_RG_Positionen_netto);
			hmClearingInfos.put(CLEARINGTAGS.GesamteMengeZuordnung, 				dMengeGegenSeite+dMengeLager);
			hmClearingInfos.put(CLEARINGTAGS.RestMengeNochOffen_in_der_Planung, 	dMengeKontrakt-dMengeGegenSeite-dMengeLager);
			
		}
		return hmClearingInfos;
	}
	
	
}
