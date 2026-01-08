package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

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
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;



public class __RECORD_VPOS_KON_EXT extends RECORD_VPOS_KON
{
	
	private Boolean bEK = null;
//	private String  cSuchText = null;
	private BigDecimal[]  arrayBDMengePlanEcht = null;
	private BigDecimal    bdMengeBerechnet = null;
	

	public __RECORD_VPOS_KON_EXT() throws myException 
	{
		super();
	}

	public __RECORD_VPOS_KON_EXT(long lID_Unformated, MyConnection Conn) 	throws myException 
	{
		super(lID_Unformated, Conn);
	}

	public __RECORD_VPOS_KON_EXT(long lID_Unformated) throws myException 
	{
		super(lID_Unformated);
	}

	public __RECORD_VPOS_KON_EXT(MyConnection Conn) throws myException 
	{
		super(Conn);
	}

	public __RECORD_VPOS_KON_EXT(RECORD_VPOS_KON recordOrig) 
	{
		super(recordOrig);
	}

	
	public __RECORD_VPOS_KON_EXT(String c_ID_or_WHEREBLOCK_OR_SQL,MyConnection Conn) throws myException 
	{
		super(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}

	public __RECORD_VPOS_KON_EXT(String c_ID_or_WHEREBLOCK_OR_SQL)	throws myException 
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
					this.bdMengeBerechnet=this.bdMengeBerechnet.add(new __RECORD_VPOS_RG(recRG)._get_ANZAHL_NETTO_bd());
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
				labelArr[1]=new MyE2_Label(MyNumberFormater.formatDez(new __RECORD_VPOS_RG(recRG)._get_ANZAHL_NETTO_bd().longValue(),true), new MyE2_String("Rechnungs-/Gutschrifts-Nettomenge (Positionsmenge minus reale Abzüge)"));
				//---
				
				
				labelArr[2]=new MyE2_Label(myDateHelper.ChangeFormatStringToDateWithoutYear(recRG.get_AUSFUEHRUNGSDATUM_cF_NN("??.??.????")));
				
				Component[] labelArr2 = new Component[2];
				
				//2012-03-16: anzeige der nettomenge und des resultierenden e-preis auf netto
				//ALT: labelArr2[0]=new MyE2_Label(MyNumberFormater.formatDez(recRG.get_EINZELPREIS_RESULT_FW_bdValue(new BigDecimal(0)),2,true));
				labelArr2[0]=new MyE2_Label(MyNumberFormater.formatDez(recRG.get_EPREIS_RESULT_NETTO_MGE_FW_bdValue(new BigDecimal(0)),2,true), new MyE2_String("Rechnungs-/Gutschrift, Effektivpreis auf Nettomenge"));
				
				if (recRG.get_UP_RECORD_VKOPF_RG_id_vkopf_rg()!=null)
				{
					labelArr2[1]=new BSK___JUMPER_TO_RechGut(recRG);
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
}
