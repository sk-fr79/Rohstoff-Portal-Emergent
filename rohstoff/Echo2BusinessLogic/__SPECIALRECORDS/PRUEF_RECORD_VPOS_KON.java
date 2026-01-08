package rohstoff.Echo2BusinessLogic.__SPECIALRECORDS;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_BASIC_AlarmMessageWithAddonComponent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_BASIC_WarningMessageWithAddonComponent;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.components.E2_InfoButton;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;
import rohstoff.utils.My_HM_KONTRAKT_POS_INFO;
import rohstoff.utils.SQL_DAEMONS.IF_PRUEF_Interface;
import rohstoff.utils.SQL_DAEMONS.VectorSingle_PRUEF_Interface;

public class PRUEF_RECORD_VPOS_KON extends RECORD_VPOS_KON  implements IF_PRUEF_Interface
{

	private boolean bGeprueft = false;
	private boolean bPrimary = false;

	
	private boolean bEK = true;

	
	private 				My_HM_KONTRAKT_POS_INFO   hmKONTRAKT_POS_INFO = null;
	private Vector<String>  vID_ZUORDNUNGS_KOMPLEMENT = null;

	//der tabellenname im pruefDaemon, der diese klasse initiert
	private String         TABLENAME_CHANGED = null;
	
	
	public PRUEF_RECORD_VPOS_KON(String c_id_or_whereblock, boolean Primary) throws myException
	{
		super(c_id_or_whereblock);
		this.bPrimary = Primary;
	}

	public PRUEF_RECORD_VPOS_KON(RECORD_VPOS_KON recordOrig, boolean Primary)
	{
		super(recordOrig);
		this.bPrimary = Primary;
	}

	public PRUEF_RECORD_VPOS_KON(String c_id_or_whereblock, MyConnection Conn, boolean Primary, String cTablename_changed) 	throws myException
	{
		super(c_id_or_whereblock, Conn);
		this.TABLENAME_CHANGED = cTablename_changed;
		this.bEK = (this.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_VORGANG_TYP_cUF().equals(myCONST.VORGANGSART_EK_KONTRAKT));
		this.bPrimary = Primary;
	}

	public boolean IsDone()
	{
		return this.bGeprueft;
	}
	
	public void  setDone(boolean bDone)
	{
		this.bGeprueft=bDone;
	}

	
	public boolean equals(Object oVGL)
	{
		if (oVGL instanceof PRUEF_RECORD_VPOS_KON)
		{
			try
			{
				if  (   ((PRUEF_RECORD_VPOS_KON)oVGL).get_ID_VPOS_KON_cUF().equals(this.get_ID_VPOS_KON_cUF()))
				{
					return true;
				}
				else
				{
					return false;
				}
			} catch (myException e)
			{
				e.printStackTrace();
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	
	
	public MyE2_MessageVector  __Check(VectorSingle_PRUEF_Interface vSammler, VectorSingle vAusgangsTabs) throws myException
	{
		//der check() darf nur aufgerufen werden, wenn die TABLENAME_CHANGED-variable besetzt ist
		if (S.isEmpty(this.TABLENAME_CHANGED))
		{
			throw new myException(this,"Error: Tablename_changed is not set !!");
		}

		
		
		if (this.IsDone())
		{
			return new MyE2_MessageVector();
		}
		this.setDone(true);

		
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		

		//jetzt die menge der zuordnung ermitteln
		double dSummeMengenAusPlanung = 0;
		double dSummeMengeAusFuhren  = 0;
		
		
		dSummeMengenAusPlanung += this.get_DOWN_RECORD_LIST_EK_VK_BEZUG_id_vpos_kon_vk().get_ANZAHL_d_Summe(null);
		dSummeMengenAusPlanung += this.get_DOWN_RECORD_LIST_EK_VK_BEZUG_id_vpos_kon_ek().get_ANZAHL_d_Summe(null);
		dSummeMengenAusPlanung += this.get_DOWN_RECORD_LIST_VPOS_KON_LAGER_id_vpos_kon().get_LAGERMENGE_d_Summe(null);
		
		if (dSummeMengenAusPlanung>this.get_ANZAHL_dValue(new Double(0)).doubleValue()*(1+this.get_MENGEN_TOLERANZ_PROZENT_dValue(new Double(0))/100))	{
			if (this.bPrimary && this.is_TYP_LADUNG_NO()) {
				if (this.get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).is_UEBERLIEFER_OK_YES())	{
					oMV.add_MESSAGE(
						new MyE2_BASIC_WarningMessageWithAddonComponent(new MyE2_String("Kontraktposition ist in der Planung überliefert, aber erlaubt !"),
						this.get_ownInfoButton(new MyE2_String("Kontraktposition ist in der Planung überliefert, aber erlaubt !")),
						new Extent(95,Extent.PERCENT),
						new Extent(5,Extent.PERCENT)));
				} else	{
					oMV.add_MESSAGE(new MyE2_BASIC_AlarmMessageWithAddonComponent(
						new MyE2_String("Kontraktposition ist in der Planung überliefert !!"),
						this.get_ownInfoButton(new MyE2_String("Kontraktposition ist in der Planung überliefert !!")),
						new Extent(95,Extent.PERCENT),
						new Extent(5,Extent.PERCENT)));
				}
			}
		}
		
		
		//dubletten pruefen (nur ein EK-VK-Kontrakt in einem zeitraum mit einer sortenbez
		String cPruefQuery = "SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_KON " +
											" LEFT OUTER JOIN JT_VPOS_KON_TRAKT ON (JT_VPOS_KON.ID_VPOS_KON=JT_VPOS_KON_TRAKT.ID_VPOS_KON )" +
											" LEFT OUTER JOIN JT_VKOPF_KON ON (JT_VKOPF_KON.ID_VKOPF_KON=JT_VPOS_KON.ID_VKOPF_KON )" +
											" WHERE " +
											" NVL(JT_VPOS_KON.DELETED,'N')='N' AND" +
											" JT_VKOPF_KON.VORGANG_TYP="+this.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_VORGANG_TYP_VALUE_FOR_SQLSTATEMENT()+" AND "+
											" JT_VPOS_KON_TRAKT.GUELTIG_VON="+this.get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).get_GUELTIG_VON_VALUE_FOR_SQLSTATEMENT()+" AND "+
											" JT_VPOS_KON_TRAKT.GUELTIG_BIS="+this.get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).get_GUELTIG_BIS_VALUE_FOR_SQLSTATEMENT()+" AND "+
											" JT_VKOPF_KON.ID_ADRESSE="+this.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_ID_ADRESSE_VALUE_FOR_SQLSTATEMENT()+" AND "+
											" JT_VPOS_KON.ID_ARTIKEL_BEZ="+this.get_ID_ARTIKEL_BEZ_VALUE_FOR_SQLSTATEMENT();
		
		String cAnzahl = bibDB.EinzelAbfrage(cPruefQuery,"","","");
		if (S.isEmpty(cAnzahl))
		{
			oMV.add_MESSAGE(new MyE2_Alarm_Message("Prüfung auf doppelte Kontrakt-Position war nicht moeglich !"));
			return oMV;
		}
		
		if (new Integer(cAnzahl.trim()).intValue()>1)
		{
			oMV.add_MESSAGE(new MyE2_Warning_Message("Prüfung auf doppelte Kontrakt-Position: Es gibt bereits einen Kontrakt mit diesen Rahmendaten / Kunde / Zeitraum / Sorte"));
		}
		
		
		RECLIST_VPOS_TPA_FUHRE  		reclistFuhren = null;
		RECLIST_VPOS_TPA_FUHRE_ORT  	reclistFuhrenOrte = this.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_kon(" NVL(DELETED,'N')='N' ", null, true);;
		
		//jetzt die mengen aus den Fuhren und orten checken
		if (this.bEK)
		{
			reclistFuhren = this.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_kon_ek("NVL(IST_STORNIERT,'N')='N' AND NVL(DELETED,'N')='N'",null,true);
			
			summeFuhren oSum = new summeFuhren(this.bEK);
			reclistFuhren.DoAnyThing(oSum);
			dSummeMengeAusFuhren += oSum.get_dSumme(); 
			
			summeFuhrenOrteEK  oSumOrte = new summeFuhrenOrteEK();
			reclistFuhrenOrte.DoAnyThing(oSumOrte);
			dSummeMengeAusFuhren += oSumOrte.get_dSumme();
		}
		else
		{
			reclistFuhren = this.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_kon_vk("NVL(IST_STORNIERT,'N')='N' AND NVL(DELETED,'N')='N'",null,true);

			summeFuhren oSum = new summeFuhren(this.bEK);
			reclistFuhren.DoAnyThing(oSum);
			dSummeMengeAusFuhren += oSum.get_dSumme();

			summeFuhrenOrteVK  oSumOrte = new summeFuhrenOrteVK();
			reclistFuhrenOrte.DoAnyThing(oSumOrte);
			dSummeMengeAusFuhren += oSumOrte.get_dSumme();
		}
		
		if (dSummeMengeAusFuhren>this.get_ANZAHL_dValue(new Double(0)).doubleValue()*(1+this.get_MENGEN_TOLERANZ_PROZENT_dValue(new Double(0))/100)) {
			if (this.bPrimary && this.is_TYP_LADUNG_NO()) {
			
				if (this.get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).is_UEBERLIEFER_OK_YES()) 	{
					oMV.add_MESSAGE(
						new MyE2_BASIC_WarningMessageWithAddonComponent(
								new MyE2_String("Kontraktposition ist in der Fuhrenmenge überliefert, aber erlaubt !"),
								this.get_ownInfoButton(new MyE2_String("Kontraktposition ist in der Fuhrenmenge überliefert !!")),
								new Extent(95,Extent.PERCENT),
								new Extent(5,Extent.PERCENT)
								));
				} else	{
					oMV.add_MESSAGE(
							new MyE2_BASIC_AlarmMessageWithAddonComponent(
									new MyE2_String("Kontraktposition ist in der Fuhrenmenge überliefert !"),
									this.get_ownInfoButton(new MyE2_String("Kontraktposition ist in der Fuhrenmenge überliefert !!")),
									new Extent(95,Extent.PERCENT),
									new Extent(5,Extent.PERCENT)
									));
				}
			}
		}
		
		//jetzt die fuhren, die mit dem kontrakt zu tun haben, mit in den sammler haengen, damit evtl. zeitraum-probleme
		//gefunden werden.
//		Vector<String> vIDFuhren = this.get_vIDs_tangierteFuhren_NotStorniert();
		
		if (this.TABLENAME_CHANGED.equals(this.get_TABLE_NAME()))
		{
		
			for (int i=0;i<reclistFuhren.size();i++)
			{
				vSammler.add(new PRUEF_RECORD_VPOS_TPA_FUHRE(reclistFuhren.get(i),this.bPrimary,this.TABLENAME_CHANGED));
			}

			//und die fuhrenorte, die mit dem kontrakt zu tun haben, mit in den sammler haengen, damit evtl. zeitraum-probleme
			//gefunden werden.
			//Vector<String> vIDFuhrenOrte = this.get_vIDs_tangierteFuhrenOrte();
			for (int i=0;i<reclistFuhrenOrte.size();i++)
			{
				vSammler.add(new PRUEF_RECORD_VPOS_TPA_FUHRE_ORT(reclistFuhrenOrte.get(i),this.bPrimary,this.TABLENAME_CHANGED));
			}
		}
		return oMV;
	}
	

	
	
	
	
	
	private class summeFuhren extends RECLIST_VPOS_TPA_FUHRE.DoAnyThingWithAll
	{
		private boolean EK = true;
		
		private double dSumme = 0;

		public summeFuhren(boolean bek)
		{
			super();
			EK = bek;
		}
		

		@Override
		public void doAnyThingWith(String hashKey, RECORD_VPOS_TPA_FUHRE orecord) throws myException
		{
			/*
			 * geaendert am: 18.01.2010 von: martin
			 * orecord.is_IST_STORNIERT_NO()
			 */
			if (orecord.is_DELETED_NO() && orecord.is_IST_STORNIERT_NO())
			{
				if (EK)
				{
					double dPlanMenge = orecord.get_ANTEIL_PLANMENGE_LIEF_dValue(new Double(0));
					double dLadeMenge = orecord.get_ANTEIL_LADEMENGE_LIEF_dValue(new Double(0));
					double dAbladeMenge = orecord.get_ANTEIL_ABLADEMENGE_LIEF_dValue(new Double(0));
					
					if (orecord.is_LADEMENGE_GUTSCHRIFT_YES())
					{
						dSumme += (dLadeMenge>0?dLadeMenge:dPlanMenge);
					}
					else
					{
						dSumme += (dAbladeMenge>0?dAbladeMenge:dPlanMenge);
					}
				}
				else
				{
					/*
					 * da auf der abladeseite zuerst mal keine planmenge steht (bis zur vollstaendigen verteilen der Mengen)
					 * wird die planmenge der lieferseite im falle es null ist, genommen
					 */
					double dPlanMenge = orecord.get_ANTEIL_PLANMENGE_ABN_dValue(orecord.get_ANTEIL_PLANMENGE_LIEF_dValue(new Double(0)));
					double dLadeMenge = orecord.get_ANTEIL_LADEMENGE_ABN_dValue(new Double(0));
					double dAbladeMenge = orecord.get_ANTEIL_ABLADEMENGE_ABN_dValue(new Double(0));
					
					if (orecord.is_ABLADEMENGE_RECHNUNG_YES())
					{
						dSumme += (dAbladeMenge>0?dAbladeMenge:dPlanMenge);
					}
					else
					{
						dSumme += (dLadeMenge>0?dLadeMenge:dPlanMenge);
					}
				}
			}
		}


		public double get_dSumme()	{return dSumme;	}
	}
	

	
	private class summeFuhrenOrteEK extends RECLIST_VPOS_TPA_FUHRE_ORT.DoAnyThingWithAll
	{
		private double dSumme = 0;

		@Override
		public void doAnyThingWith(String hashKey, RECORD_VPOS_TPA_FUHRE_ORT orecord) throws myException
		{
			if (orecord.is_DELETED_NO())
			{
				double dPlanMenge = orecord.get_ANTEIL_PLANMENGE_dValue(new Double(0));
				double dLadeMenge = orecord.get_ANTEIL_LADEMENGE_dValue(new Double(0));
				double dAbladeMenge = orecord.get_ANTEIL_ABLADEMENGE_dValue(new Double(0));
				
				if (orecord.is_LADEMENGE_GUTSCHRIFT_YES())
				{
					dSumme += (dLadeMenge>0?dLadeMenge:dPlanMenge);
				}
				else
				{
					dSumme += (dAbladeMenge>0?dAbladeMenge:dPlanMenge);
				}
			}
		}
		public double get_dSumme()	{return dSumme;	}
	}

	
	private class summeFuhrenOrteVK extends RECLIST_VPOS_TPA_FUHRE_ORT.DoAnyThingWithAll
	{
		private double dSumme = 0;

		@Override
		public void doAnyThingWith(String hashKey, RECORD_VPOS_TPA_FUHRE_ORT orecord) throws myException
		{
			if (orecord.is_DELETED_NO())
			{
				double dPlanMenge = orecord.get_ANTEIL_PLANMENGE_dValue(new Double(0));
				double dLadeMenge = orecord.get_ANTEIL_LADEMENGE_dValue(new Double(0));
				double dAbladeMenge = orecord.get_ANTEIL_ABLADEMENGE_dValue(new Double(0));
				
				if (orecord.is_ABLADEMENGE_RECHNUNG_YES())
				{
					dSumme += (dAbladeMenge>0?dAbladeMenge:dPlanMenge);
				}
				else
				{
					dSumme += (dLadeMenge>0?dLadeMenge:dPlanMenge);
				}
			}
		}
		public double get_dSumme()	{return dSumme;	}
	}

	
	
//	public Vector<String> get_vIDs_tangierteFuhren_NotStorniert() throws myException
//	{
//		Vector<String> vRueck = new Vector<String>();
//		
//		doZaehleFuhrenFuhren  oZaehler1 = new doZaehleFuhrenFuhren();
//	 	this.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_kon_ek().DoAnyThing(oZaehler1);
//		
//	 	vRueck.addAll(oZaehler1.get_vRueck());
//
//		doZaehleFuhrenFuhren  oZaehler2 = new doZaehleFuhrenFuhren();
//	 	this.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_kon_vk().DoAnyThing(oZaehler2);
//		
//	 	vRueck.addAll(oZaehler2.get_vRueck());
// 	
//	 	
//		return vRueck;
//	}
//
//	
//	
//	private class doZaehleFuhrenFuhren extends RECLIST_VPOS_TPA_FUHRE.DoAnyThingWithAll
//	{
//		private Vector<String>		vRueck = new Vector<String>();
//
//		@Override
//		public void doAnyThingWith(String hashKey,RECORD_VPOS_TPA_FUHRE orecord) throws myException
//		{
//			if (orecord.is_DELETED_NO() && orecord.is_IST_STORNIERT_NO())
//			{
//				vRueck.add(hashKey);
//			}
//		}
//
//		public Vector<String> get_vRueck()
//		{
//			return vRueck;
//		}
//		
//	}
	

	
//	public Vector<String> get_vIDs_tangierteFuhrenOrte() throws myException
//	{
//		doZaehleFuhrenOrte  oZaehler = new doZaehleFuhrenOrte();
//	 	this.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_kon().DoAnyThing(oZaehler);
//		
//		return oZaehler.get_vRueck();
//	}
//	
//	private class doZaehleFuhrenOrte extends RECLIST_VPOS_TPA_FUHRE_ORT.DoAnyThingWithAll
//	{
//		private Vector<String>		vRueck = new Vector<String>();
//
//		@Override
//		public void doAnyThingWith(String hashKey,RECORD_VPOS_TPA_FUHRE_ORT orecord) throws myException
//		{
//			if (orecord.is_DELETED_NO() && orecord.is_DELETED_NO())
//			{
//				vRueck.add(hashKey);
//			}
//		}
//
//		public Vector<String> get_vRueck()
//		{
//			return vRueck;
//		}
//		
//	}
	
	@Override
	public boolean IsPrimary()
	{
		return this.bPrimary;
	}

	@Override
	public void setPrimary(boolean primary)
	{
		this.bPrimary = primary;
	}


	@Override
	public E2_InfoButton get_ownInfoButton(MyString cFehler)
	{
		Vector<MyString> vInfo = new Vector<MyString>();
		
		try
		{
			
			
			vInfo.add(new MyE2_String("Fehlerinformation :"));
			vInfo.add(new MyE2_String("Die Prüfung der Kontraktposition hat einen Fehler ergeben:"));
			
			String cHelp = "EK-Kontrakt";
			if (this.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_VORGANG_TYP_cUF_NN("").equals(myCONST.VORGANGSART_VK_KONTRAKT))
			{
				cHelp = "VK-Kontrakt";
			}
			vInfo.add(new MyE2_String(cHelp,true,"(ID: "+this.get_ID_VPOS_KON_cUF()+")",false,
					this.get_ANZAHL_cF_NN("0")+" "+this.get_ANR1_cUF_NN("")+" "+this.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_NAME1_cUF_NN("")+" "+this.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_ORT_cUF_NN(""),false));
			
			vInfo.add(new MyE2_String("-----------------------------",false));
			vInfo.add(cFehler);  
			
		} 
		catch (myException e)
		{
			e.printStackTrace();
			vInfo.add(new MyE2_String("INTERNER FEHLER !!"));
		}
		return new ownInfoButton(vInfo);
	}
	
	
	
	private class ownInfoButton extends E2_InfoButton
	{

		public ownInfoButton(Vector<MyString> infos)
		{
			super(infos);
		}
		
	}



	public My_HM_KONTRAKT_POS_INFO get_hmKONTRAKT_POS_INFO() throws myException
	{
		if (this.hmKONTRAKT_POS_INFO==null)
		{
			this.hmKONTRAKT_POS_INFO = new My_HM_KONTRAKT_POS_INFO(this.get_ID_VPOS_KON_cUF());
		}
		
		return hmKONTRAKT_POS_INFO;
	}
	

    public boolean IS_EK_KONTRAKT() throws myException
    {
    	if (this.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_VORGANG_TYP_cUF().equals(myCONST.VORGANGSART_EK_KONTRAKT))
    		return true;
    	else
    		return false;
    }

    
    public boolean IS_VK_KONTRAKT() throws myException
    {
    	if (this.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_VORGANG_TYP_cUF().equals(myCONST.VORGANGSART_VK_KONTRAKT))
    		return true;
    	else
    		return false;
    }


	
	public Vector<String>  get_vID_RELATION_EK_VK_KOMPLEMENT() throws myException
	{
		
		if (this.vID_ZUORDNUNGS_KOMPLEMENT == null)
		{
		
	        String cSQL = "";
	        if (this.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_VORGANG_TYP_cUF_NN("").equals(myCONST.VORGANGSART_EK_KONTRAKT))
	            cSQL = "SELECT ID_VPOS_KON_VK FROM "+bibE2.cTO()+".JT_EK_VK_BEZUG WHERE ID_VPOS_KON_EK="+this.get_ID_VPOS_KON_cUF();
	        else if (this.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_VORGANG_TYP_cUF_NN("").equals(myCONST.VORGANGSART_VK_KONTRAKT))
	            cSQL = "SELECT ID_VPOS_KON_EK FROM "+bibE2.cTO()+".JT_EK_VK_BEZUG WHERE ID_VPOS_KON_VK="+this.get_ID_VPOS_KON_cUF();
	        else
	            throw new myException("myKontrakt: Type not defined: "+this.get_ID_VPOS_KON_cUF());
	        
	        String[][] cResult = bibDB.EinzelAbfrageInArray(cSQL,""); ;
	        if (cResult == null)
	            throw new myException("BSK__KONTRAKTPOS_META_MAP: Error Querying relationlist: "+this.get_ID_VPOS_KON_cUF());
	        
	        
	        this.vID_ZUORDNUNGS_KOMPLEMENT = new Vector<String>();
	        for (int i=0; i<cResult.length;i++)
	        {
	        	this.vID_ZUORDNUNGS_KOMPLEMENT.add(cResult[i][0]);
	        }
		}
		
        return vID_ZUORDNUNGS_KOMPLEMENT;
        
	}
	
	
	
	
	public Double get_NichtZugeordnete_RestMenge() throws myException
	{
		
		try
		{
			
			Double dLagerMenge = this.get_hmKONTRAKT_POS_INFO().get_GESAMTE_PLAN_LAGERMENGE();
			Double dZugeordnetMenge = this.get_hmKONTRAKT_POS_INFO().get_ZUGEORDNETE_KONTRAKTMENGE();
			Double dKomplett  = this.get_ANZAHL_dValue(new Double(0));
			
			double dRest = dKomplett.doubleValue()-dZugeordnetMenge.doubleValue()-dLagerMenge.doubleValue();
			
			return new Double(dRest);
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new myException("BSK__KONTRAKTPOS_META_MAP:get_NichtZugeordnete_RestMenge:Error:"+ex.getLocalizedMessage());
		}
	}
	
	
	
	@Override
	public String get_ID() throws myException
	{
		return this.get_ID_VPOS_KON_cUF();
	}


	@Override
	public String get_TABLENAME_CHANGED() throws myException
	{
		return this.TABLENAME_CHANGED;
	}

	
	
}
