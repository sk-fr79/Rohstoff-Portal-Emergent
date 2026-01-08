package rohstoff.Echo2BusinessLogic.__SPECIALRECORDS;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_BASIC_WarningMessageWithAddonComponent;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.components.E2_InfoButton;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_EK_VK_BEZUG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_KON_LAGER;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON_LAGER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_STD;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;
import panter.gmbh.indep.myVectors.VectorSingle;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU___CONST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.BUCHUNG.BUCH_StatementBuilder;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.BUCHUNG.VECTOR_BUCH_StatementBuilder;
import rohstoff.utils.bibROHSTOFF;
import rohstoff.utils.SQL_DAEMONS.IF_PRUEF_Interface;
import rohstoff.utils.SQL_DAEMONS.VectorSingle_PRUEF_Interface;

public class PRUEF_RECORD_VPOS_TPA_FUHRE_ORT extends RECORD_VPOS_TPA_FUHRE_ORT  implements IF_PRUEF_Interface
{
	
	private boolean bGeprueft = false;
	private boolean bPrimary = false;

	//der tabellenname im pruefDaemon, der diese klasse initiert
	private String         TABLENAME_CHANGED = null;


	public PRUEF_RECORD_VPOS_TPA_FUHRE_ORT(String ID_Unformated, MyConnection Conn, boolean Primary, String cTablename_changed) throws myException
	{
		super(ID_Unformated, Conn);
		this.TABLENAME_CHANGED = cTablename_changed;
		this.bPrimary = Primary;
	}

	

	
	public PRUEF_RECORD_VPOS_TPA_FUHRE_ORT(String ID_Unformated, MyConnection Conn, boolean Primary) throws myException
	{
		super(ID_Unformated, Conn);
		this.bPrimary = Primary;
	}


	
	public PRUEF_RECORD_VPOS_TPA_FUHRE_ORT(RECORD_VPOS_TPA_FUHRE_ORT recordOrig, boolean Primary, String cTablename_changed)
	{
		super(recordOrig);
		this.TABLENAME_CHANGED = cTablename_changed;

		this.bPrimary = Primary;
	}


	public PRUEF_RECORD_VPOS_TPA_FUHRE_ORT(RECORD_VPOS_TPA_FUHRE_ORT recordOrig, boolean Primary)
	{
		super(recordOrig);
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

	
	/**
	 * prueft, ob die kombination der Angaben ID_ADRESSE_LAGER und WIEGEKARTENKENNER eindeutig sind
	 * @return
	 */
	public MyE2_MessageVector  Check_Lagerort_Wiegekarte_Eindeutig() throws myException
	{
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		if (S.isFull(this.get_WIEGEKARTENKENNER_cUF_NN("")) && S.isFull(this.get_ID_ADRESSE_LAGER_cUF_NN("")))
		{
			String cQuery1 = "SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE " +
					" ((ID_ADRESSE_LAGER_START="+this.get_ID_ADRESSE_LAGER_cUF()+" AND WIEGEKARTENKENNER_LADEN="+this.get_WIEGEKARTENKENNER_VALUE_FOR_SQLSTATEMENT()+") " +
					" OR "+
					" (ID_ADRESSE_LAGER_ZIEL="+this.get_ID_ADRESSE_LAGER_cUF()+" AND WIEGEKARTENKENNER_ABLADEN="+this.get_WIEGEKARTENKENNER_VALUE_FOR_SQLSTATEMENT()+"))";
			
			
			String cQuery2 = "SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT WHERE " +
					" ID_ADRESSE_LAGER="+this.get_ID_ADRESSE_LAGER_cUF()+
					" AND WIEGEKARTENKENNER="+this.get_WIEGEKARTENKENNER_VALUE_FOR_SQLSTATEMENT()+
					" AND ID_VPOS_TPA_FUHRE_ORT<>"+this.get_ID_VPOS_TPA_FUHRE_ORT_cUF();
			
			String[][] cWert1 = bibDB.EinzelAbfrageInArray(cQuery1, this.get_oConn());
			String[][] cWert2 = bibDB.EinzelAbfrageInArray(cQuery2, this.get_oConn());
			
			Long oLong1 = new MyLong(cWert1[0][0]).get_oLong();
			Long oLong2 = new MyLong(cWert2[0][0]).get_oLong();
			
			if (oLong1==null || oLong2==null)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler bei der Prüfung der Eindeutigkeit der Wiegekarte (1) !")));
			}
			else
			{
				if ((oLong1.longValue()+oLong2.longValue())>0)
				{
					MyE2_String oString = new MyE2_String(	"Die Wiegekartennummer ist nicht eindeutig : Lager: ",	true,
															this.get_ID_ADRESSE_LAGER_cUF(),						false,
															": Wiegekarte: ",										true,
															this.get_WIEGEKARTENKENNER_cUF(),						false,
															":Zusatzort zu Fuhre: ",								true,
															this.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre().get_ID_VPOS_TPA_FUHRE_cUF(),false);
					oMV.add_MESSAGE(new MyE2_Alarm_Message(oString));
				}
			}
		}
		return oMV;
	}
	
	
	
	
	
	public boolean equals(Object oVGL)
	{
		if (oVGL instanceof PRUEF_RECORD_VPOS_TPA_FUHRE_ORT)
		{
			try
			{
				if  (   ((PRUEF_RECORD_VPOS_TPA_FUHRE_ORT)oVGL).get_ID_VPOS_TPA_FUHRE_ORT_cUF().equals(this.get_ID_VPOS_TPA_FUHRE_ORT_cUF()))
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


	public double __MengeZurAbrechnung() throws myException
	{
		double dMengeLadeOderPlan = 	this.get_ANTEIL_LADEMENGE_dValue(this.get_ANTEIL_PLANMENGE_dValue(new Double(0))).doubleValue();
		double dMengeAbladeOderPlan = 	this.get_ANTEIL_ABLADEMENGE_dValue(this.get_ANTEIL_PLANMENGE_dValue(new Double(0))).doubleValue();
		
		if (this.is_OHNE_ABRECHNUNG_YES() || this.is_DELETED_YES())
		{
			return 0;
		}
		
		
		if (this.get_DEF_QUELLE_ZIEL_cUF().equals("EK"))
		{
			if (this.is_LADEMENGE_GUTSCHRIFT_YES())
			{
				return dMengeLadeOderPlan;
			}
			else
			{
				return dMengeAbladeOderPlan;
			}
		}
		else
		{
			if (this.is_ABLADEMENGE_RECHNUNG_YES())
			{
				return dMengeAbladeOderPlan;
			}
			else
			{
				return dMengeLadeOderPlan;
			}
		}
	}
	
	
	
	public boolean __isLagerOrt() throws myException
	{
		return this.get_ID_ADRESSE_cUF_NN("").equals(bibALL.get_ID_ADRESS_MANDANT());
	}
	
	
	public boolean __isEKOrt() throws myException
	{
		return this.get_DEF_QUELLE_ZIEL_cUF_NN("").equals("EK");
	}
	
	public boolean __isVKOrt() throws myException
	{
		return this.get_DEF_QUELLE_ZIEL_cUF_NN("").equals("VK");
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
		
		String cFuhreAbholDatumVon = this.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre().get_DATUM_ABHOLUNG_cF();
		String cFuhreAbholDatumBis = this.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre().get_DATUM_ABHOLUNG_ENDE_cF_NN(cFuhreAbholDatumVon);
		
		String cFuhreLieferDatumVon = this.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre().get_DATUM_ANLIEFERUNG_cF();
		String cFuhreLieferDatumBis = this.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre().get_DATUM_ANLIEFERUNG_ENDE_cF_NN(cFuhreLieferDatumVon);
	

		if (S.isFull(this.get_ID_VPOS_KON_cUF_NN("")))
		{
			//Zeiten der fuhre mit gueltigkeitszeitraum des kontrakts pruefen
			
			String cDateKonGueltigVon = this.get_UP_RECORD_VPOS_KON_id_vpos_kon().get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).get_GUELTIG_VON_cF();
			String cDateKonGueltigBis = this.get_UP_RECORD_VPOS_KON_id_vpos_kon().get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).get_GUELTIG_BIS_cF();

			if (this.get_DEF_QUELLE_ZIEL_cUF().equals("EK"))
			{
				if (!myDateHelper.get_bDatumsBereicheHabenSchnittmengen(cDateKonGueltigVon, cDateKonGueltigBis, cFuhreAbholDatumVon, cFuhreAbholDatumBis))
				{
					if (this.bPrimary) oMV.add_MESSAGE((new MyE2_BASIC_WarningMessageWithAddonComponent(
											new MyE2_String("Gültigkeitszeitraum EK-Kontrakt im Zusatzort und Fuhre passen nicht !!!"),
											this.get_ownInfoButton(new MyE2_String("Gültigkeitszeitraum EK-Kontrakt im Zusatzort und Fuhre passen nicht !!!")),
											new Extent(95,Extent.PERCENT),
											new Extent(5,Extent.PERCENT))));
				}
			}
			else
			{
				if (!myDateHelper.get_bDatumsBereicheHabenSchnittmengen(cDateKonGueltigVon, cDateKonGueltigBis, cFuhreLieferDatumVon, cFuhreLieferDatumBis))
				{
					if (this.bPrimary) oMV.add_MESSAGE(new MyE2_BASIC_WarningMessageWithAddonComponent(
											new MyE2_String("Gültigkeitszeitraum VK-Kontrakt im Zusatzort und Fuhre passen nicht !!!"),
											this.get_ownInfoButton(new MyE2_String("Gültigkeitszeitraum VK-Kontrakt im Zusatzort und Fuhre passen nicht !!!")),
											new Extent(95,Extent.PERCENT),
											new Extent(5,Extent.PERCENT)));
				}
			}
			
			
			//kontrakt zur pruefung uebergeben
			if (this.TABLENAME_CHANGED.equals(this.get_TABLE_NAME()))
			{
				vSammler.add(new PRUEF_RECORD_VPOS_KON(this.get_ID_VPOS_KON_cUF(),this.get_oConn(),this.bPrimary,this.TABLENAME_CHANGED));
			}
			
			
			//pruefen, ob eine EK-VK-zuordnung vorliegt
			if (this.get_DEF_QUELLE_ZIEL_cUF().equals("EK"))
			{
				String cID_VPOS_KON_VK = this.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre().get_ID_VPOS_KON_VK_cUF_NN("");
				
				if (S.isFull(cID_VPOS_KON_VK))
				{
					//sind beide kontrakte gefuellt, dann kann es eine zuordnung geben - dies pruefen
					RECLIST_EK_VK_BEZUG oRL_EK_VK = new RECLIST_EK_VK_BEZUG("SELECT * FROM "+bibE2.cTO()+".JT_EK_VK_BEZUG WHERE ID_VPOS_KON_EK="+this.get_ID_VPOS_KON_cUF()+" AND "+
																					"ID_VPOS_KON_VK="+cID_VPOS_KON_VK,this.get_oConn());
					for (String cID_EK_VK_BEZUG: oRL_EK_VK.get_vKeyValues())
					{
						if (this.TABLENAME_CHANGED.equals(this.get_TABLE_NAME()))
						{
							vSammler.add(new PRUEF_RECORD_EK_VK_BEZUG(cID_EK_VK_BEZUG,this.get_oConn(),this.bPrimary, this.TABLENAME_CHANGED));
						}
					}
				}
			}
			else
			{
				String cID_VPOS_KON_EK = this.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre().get_ID_VPOS_KON_EK_cUF_NN("");
				
				if (S.isFull(cID_VPOS_KON_EK))
				{
					//sind beide kontrakte gefuellt, dann kann es eine zuordnung geben - dies pruefen
					RECLIST_EK_VK_BEZUG oRL_EK_VK = new RECLIST_EK_VK_BEZUG("SELECT * FROM "+bibE2.cTO()+".JT_EK_VK_BEZUG WHERE ID_VPOS_KON_VK="+this.get_ID_VPOS_KON_cUF()+" AND "+
																					"ID_VPOS_KON_EK="+cID_VPOS_KON_EK,this.get_oConn());
					for (String cID_EK_VK_BEZUG: oRL_EK_VK.get_vKeyValues())
					{
						if (this.TABLENAME_CHANGED.equals(this.get_TABLE_NAME()))
						{
							vSammler.add(new PRUEF_RECORD_EK_VK_BEZUG(cID_EK_VK_BEZUG,this.get_oConn(),this.bPrimary, this.TABLENAME_CHANGED));
						}
					}
				}
			}

			
			
		}
		else
		{
			//dann koennte das eine lagerzuoerdung tangieren
			if (this.get_ID_ADRESSE_cUF().equals(bibALL.get_ID_ADRESS_MANDANT()))
			{
				String cID_ADRESSE_LAGER = this.get_ID_ADRESSE_LAGER_cUF_NN("");

				if (this.get_DEF_QUELLE_ZIEL_cUF().equals("EK"))
				{
					String cID_VPOS_KON_VK = this.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre().get_ID_VPOS_KON_VK_cUF_NN("");
					
					if (S.isFull(cID_VPOS_KON_VK))
					{
						RECLIST_VPOS_KON_LAGER oRecList = new RECLIST_VPOS_KON_LAGER("SELECT * FROM "+bibE2.cTO()+".JT_VPOS_KON_LAGER WHERE ID_VPOS_KON="+cID_VPOS_KON_VK+" AND "+
																					 " ID_ADRESSE_LAGER="+cID_ADRESSE_LAGER,this.get_oConn());
						
						if (oRecList.size()>0)
						{
							if (this.TABLENAME_CHANGED.equals(this.get_TABLE_NAME()))
							{
								for (Map.Entry<String, RECORD_VPOS_KON_LAGER> oEntry: oRecList.entrySet())
								{
									vSammler.add(new PRUEF_RECORD_VPOS_KON_LAGER(oEntry.getValue(),this.bPrimary, this.TABLENAME_CHANGED));
								}
							}
						}
						else
						{
							oMV.add_MESSAGE(new MyE2_Warning_Message("Die Zuordnung zwischen Lager und Kontrakt ist in der Kontrakt-Definition nicht vorgesehen !!"));
						}
					}
				}
				else
				{
					//VK-zusatzort
					String cID_VPOS_KON_EK = this.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre().get_ID_VPOS_KON_EK_cUF_NN("");
					
					if (S.isFull(cID_VPOS_KON_EK))
					{
						RECLIST_VPOS_KON_LAGER oRecList = new RECLIST_VPOS_KON_LAGER("SELECT * FROM "+bibE2.cTO()+".JT_VPOS_KON_LAGER WHERE ID_VPOS_KON="+cID_VPOS_KON_EK+" AND "+
																					 " ID_ADRESSE_LAGER="+cID_ADRESSE_LAGER,this.get_oConn());
						if (oRecList.size()>0)
						{
							if (this.TABLENAME_CHANGED.equals(this.get_TABLE_NAME()))
							{
								for (Map.Entry<String, RECORD_VPOS_KON_LAGER> oEntry: oRecList.entrySet())
								{
									vSammler.add(new PRUEF_RECORD_VPOS_KON_LAGER(oEntry.getValue(),this.bPrimary, this.TABLENAME_CHANGED));
								}
							}
						}
						else
						{
							oMV.add_MESSAGE(new MyE2_Warning_Message("Die Zuordnung zwischen Lager und Kontrakt ist in der Kontrakt-Definition nicht vorgesehen !!"));
						}
					}
				}
			}
		}
		
		
		
		oMV.add_MESSAGE(this.Check_Lagerort_Wiegekarte_Eindeutig());
	
		return oMV;
	}
	
	
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
			RECORD_VPOS_TPA_FUHRE  		FUHRE = this.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre();
			RECORD_ADRESSE              START = FUHRE.get_UP_RECORD_ADRESSE_id_adresse_start();
			RECORD_ADRESSE              ZIEL = 	FUHRE.get_UP_RECORD_ADRESSE_id_adresse_ziel();
			
			vInfo.add(new MyE2_String("Fehlerinformation :"));
			vInfo.add(new MyE2_String("Die Prüfung der Fuhre hat einen Fehler ergeben:"));
			vInfo.add(new MyE2_String("Fuhre: ",true,"(ID: "+FUHRE.get_ID_VPOS_TPA_FUHRE_cUF()+")",false,
												FUHRE.get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN("")+" "+FUHRE.get_UP_RECORD_ARTIKEL_id_artikel().get_ARTBEZ1_cUF_NN(""),false));

			vInfo.add(new MyE2_String("Lieferant/Start: ",true,
					START.get_NAME1_cUF_NN("")+" "+START.get_ORT_cUF_NN(""),false));

			vInfo.add(new MyE2_String("Abnehmer/Ziel: ",true,
					ZIEL.get_NAME1_cUF_NN("")+" "+ZIEL.get_ORT_cUF_NN(""),false));
			
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
	

	
	
	/**
	 * methode sammelt alle statements einer fuhre und gibt den Vector mit statements zurueck
	 * @return
	 * @throws myException
	 */
	public VECTOR_BUCH_StatementBuilder get_vAbrechnungsStatements(boolean bNurZumTest) throws myException
	{
		VECTOR_BUCH_StatementBuilder  vRueck = new VECTOR_BUCH_StatementBuilder();
		
		if (this.is_DELETED_YES())
		{
			return vRueck;
		}
		
		boolean bFremdauftrag = S.isFull(this.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre().get_ID_ADRESSE_FREMDAUFTRAG_cUF_NN(""));
		if (bibROHSTOFF.get_vEigeneLieferadressen().contains(this.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre().get_ID_ADRESSE_FREMDAUFTRAG_cUF_NN("")))
		{
			bFremdauftrag=false;
		}

		if (bFremdauftrag)
		{
			return vRueck;			//fremdauftrag wirkt auf fuhren und orte
		}

		
		if (this.is_OHNE_ABRECHNUNG_NO())
		{
			vRueck.add(this.get_StatementBuilderBuchung(bNurZumTest));
		}
		
		return vRueck;
	}

	
	
	
	
	public BigDecimal get_bdMenge() throws myException
	{
		if (this.get_DEF_QUELLE_ZIEL_cUF_NN("").equals("EK"))
		{
			if (this.is_LADEMENGE_GUTSCHRIFT_YES())
			{
				return this.get_ANTEIL_LADEMENGE_bdValue(null,3);
			}
			else
			{
				return  this.get_ANTEIL_ABLADEMENGE_bdValue(null,3);
			}
		}
		else
		{
			if (this.is_ABLADEMENGE_RECHNUNG_YES())
			{
				return this.get_ANTEIL_ABLADEMENGE_bdValue(null,3);
			}
			else
			{
				return this.get_ANTEIL_LADEMENGE_bdValue(null,3);
			}
		}
	}
	

	
	private BUCH_StatementBuilder  get_StatementBuilderBuchung(boolean bNurZumTest) throws myException
	{
		BUCH_StatementBuilder oStatmt = new BUCH_StatementBuilder(this);
		
		boolean bEK = this.get_DEF_QUELLE_ZIEL_cUF().equals("EK");

		
		if (!bNurZumTest)
		{
			BigDecimal 	bdMenge = this.get_bdMengeZurVerbuchung();
			String 		cMenge = null;
			if (bdMenge != null)
			{
				cMenge = MyNumberFormater.formatDez(bdMenge, 3, false, '.', ',', true);
			}

			RECORD_ADRESSE  recKunde = this.get_UP_RECORD_ADRESSE_id_adresse();
			
			RECORD_ADRESSE    recLieferant = null;
			RECORD_ADRESSE    recAbnehmer = null;
			if (bEK)
			{
				recLieferant = recKunde;
				recAbnehmer  = this.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre().get_UP_RECORD_ADRESSE_id_adresse_ziel();
			}
			else
			{
				recLieferant = this.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre().get_UP_RECORD_ADRESSE_id_adresse_start();;
				recAbnehmer  = recKunde;
			}
			
			
			oStatmt.fill_baseFieldsFromAdress(recKunde);
			
			oStatmt.addSQL_Paar("ID_ADRESSE",					this.get_ID_ADRESSE_VALUE_FOR_SQLSTATEMENT());
			oStatmt.addSQL_Paar("ID_VPOS_TPA_FUHRE_ZUGEORD",	this.get_ID_VPOS_TPA_FUHRE_VALUE_FOR_SQLSTATEMENT(),	false);
			oStatmt.addSQL_Paar("ID_VPOS_TPA_FUHRE_ORT_ZUGEORD",this.get_ID_VPOS_TPA_FUHRE_ORT_VALUE_FOR_SQLSTATEMENT(),false);
			oStatmt.addSQL_Paar("LAGER_VORZEICHEN",				bEK?"1":"-1");

			/*
			 * geaendert am: 15.02.2010 von: martin
			 */
			oStatmt.addSQL_Paar("BESTELLNUMMER",				this.get_BESTELLNUMMER_VALUE_FOR_SQLSTATEMENT());

			oStatmt.addSQL_Paar("ID_ARTIKEL_BEZ",				this.get_ID_ARTIKEL_BEZ_VALUE_FOR_SQLSTATEMENT());
			oStatmt.addSQL_Paar("ID_ARTIKEL",					this.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_ID_ARTIKEL_VALUE_FOR_SQLSTATEMENT());
			oStatmt.addSQL_Paar("ARTBEZ1",						this.get_ARTBEZ1_VALUE_FOR_SQLSTATEMENT());
			oStatmt.addSQL_Paar("ARTBEZ2",						this.get_ARTBEZ2_VALUE_FOR_SQLSTATEMENT());
			oStatmt.addSQL_Paar("EINHEITKURZ",					this.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_EINHEIT_id_einheit().get_EINHEITKURZ_VALUE_FOR_SQLSTATEMENT());
			oStatmt.addSQL_Paar("EINHEIT_PREIS_KURZ",			this.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_EINHEIT_id_einheit_preis()==null?
																this.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_EINHEIT_id_einheit().get_EINHEITKURZ_VALUE_FOR_SQLSTATEMENT():
																this.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_EINHEIT_id_einheit_preis().get_EINHEITKURZ_VALUE_FOR_SQLSTATEMENT());
			oStatmt.addSQL_Paar("MENGENDIVISOR",				this.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_MENGENDIVISOR_VALUE_FOR_SQLSTATEMENT());
			oStatmt.addSQL_Paar("ANR1",							this.get_ANR1_VALUE_FOR_SQLSTATEMENT());
			oStatmt.addSQL_Paar("ANR2",							this.get_ANR2_VALUE_FOR_SQLSTATEMENT());
			oStatmt.addSQL_Paar("ZOLLTARIFNR",					this.get_ZOLLTARIFNR_VALUE_FOR_SQLSTATEMENT());

			
			oStatmt.addSQL_Paar("ANZAHL",						cMenge);
			
			oStatmt.addSQL_Paar("EINZELPREIS",					this.get_EINZELPREIS_VALUE_FOR_SQLSTATEMENT());
			oStatmt.addSQL_Paar("STEUERSATZ",					this.get_STEUERSATZ_VALUE_FOR_SQLSTATEMENT());
			
			//lager-vermerk und Leer-Vermerk werden nicht in positionen geschrieben
			String cHelpString = this.get_EU_STEUER_VERMERK_cUF_NN("").trim();
			if (cHelpString.equals(FU___CONST.EU_STEUERVERMERK_LAGER) || cHelpString.equals(FU___CONST.EU_STEUERVERMERK_LEER))
			{
				cHelpString = null;
			}
			else
			{
				cHelpString = this.get_EU_STEUER_VERMERK_VALUE_FOR_SQLSTATEMENT();
			}
			oStatmt.addSQL_Paar("EU_STEUER_VERMERK",			cHelpString);

			//2014-10-28: id_tax mit uebergeben
			//lager-vermerk und Leer-Vermerk werden nicht in positionen geschrieben
			oStatmt.addSQL_Paar(_DB.VPOS_RG$ID_TAX,	this.get_ID_TAX_cUF_NN(""));
			//2014-10-28: id_tax mit uebergeben

			
			
			oStatmt.addSQL_Paar("EUNOTIZ",						this.get_EUNOTIZ_VALUE_FOR_SQLSTATEMENT());
			oStatmt.addSQL_Paar("EUCODE",						this.get_EUCODE_VALUE_FOR_SQLSTATEMENT());
			oStatmt.addSQL_Paar("WIEGEKARTENKENNER",			this.get_WIEGEKARTENKENNER_VALUE_FOR_SQLSTATEMENT());
			
			//2011-03-10: leistungsdatum im verkauf ist das ladedatum (es sei denn, es gibt keines) 
			                                                           //ab V 2.33 sollte dies nicht mehr vorkommen
			//beim EK ist das leistungsdatum das ladedatum
			String cAusfuehrungsdatum = this.get_DATUM_LADE_ABLADE_VALUE_FOR_SQLSTATEMENT();
			
			if (!bEK)
			{
				
				//2011-03-18: vorbereiten fuer die finale version: ueber die lieferbedingungen
				boolean bVK_bekommt_ladedatum = false;
				
				if (bVK_bekommt_ladedatum)
				{
					Vector<String>  vLadeDaten = this.get_vAlleBeteiligtenLadeDaten_SQL_NOTATION_NOT_NULL_SORTED();
					
					if (vLadeDaten.size()>0)            //ab V 2.33 sollte dies immer der fall sein
					{
						cAusfuehrungsdatum = vLadeDaten.get(0);
					}
				}
			}
			
			//oStatmt.addSQL_Paar("AUSFUEHRUNGSDATUM",			this.get_DATUM_LADE_ABLADE_VALUE_FOR_SQLSTATEMENT());
			oStatmt.addSQL_Paar("AUSFUEHRUNGSDATUM",			cAusfuehrungsdatum);
			//2011-03-10: leistungsdatum und im verkauf ist das ladedatum
			
			
	
			String cStrecke = "Y";
			if (recLieferant.get_ID_ADRESSE_lValue(null).longValue()==	bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_lValue(null) ||
				recAbnehmer.get_ID_ADRESSE_lValue(null).longValue()==	bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_lValue(null))
			{
				cStrecke = "N";
			}
			oStatmt.addSQL_Paar("STRECKENGESCHAEFT",			cStrecke,true);
			
			RECORD_VPOS_KON  recKON = this.get_UP_RECORD_VPOS_KON_id_vpos_kon();
			
			//2012-05-22: zusaetzlich auch zahlungsbedingungen aus angeboten holen
			RECORD_VPOS_STD  recAngebot = this.get_UP_RECORD_VPOS_STD_id_vpos_std();

			
			if (recKON!=null)
			{
	
				oStatmt.addSQL_Paar("ID_VPOS_KON_ZUGEORD",		recKON.get_ID_VPOS_KON_VALUE_FOR_SQLSTATEMENT());
				oStatmt.addSQL_Paar("ZAHLUNGSBEDINGUNGEN",		recKON.get_ZAHLUNGSBEDINGUNGEN_VALUE_FOR_SQLSTATEMENT());
				oStatmt.addSQL_Paar("ID_ZAHLUNGSBEDINGUNGEN",	recKON.get_ID_ZAHLUNGSBEDINGUNGEN_VALUE_FOR_SQLSTATEMENT());
				oStatmt.addSQL_Paar("ZAHLTAGE",					recKON.get_ZAHLTAGE_VALUE_FOR_SQLSTATEMENT());
				oStatmt.addSQL_Paar("FIXMONAT",					recKON.get_FIXMONAT_VALUE_FOR_SQLSTATEMENT());
				oStatmt.addSQL_Paar("FIXTAG",					recKON.get_FIXTAG_VALUE_FOR_SQLSTATEMENT());
				oStatmt.addSQL_Paar("SKONTO_PROZENT",			recKON.get_SKONTO_PROZENT_VALUE_FOR_SQLSTATEMENT());
				oStatmt.addSQL_Paar("LIEFERBEDINGUNGEN",		recKON.get_LIEFERBEDINGUNGEN_VALUE_FOR_SQLSTATEMENT());
				
				/*
				 *fremdwaehrung
				 */
				oStatmt.addSQL_Paar("WAEHRUNGSKURS",			recKON.get_WAEHRUNGSKURS_VALUE_FOR_SQLSTATEMENT());
				oStatmt.addSQL_Paar("ID_WAEHRUNG_FREMD",		recKON.get_ID_WAEHRUNG_FREMD_VALUE_FOR_SQLSTATEMENT());
				
				
				//2012-10-16: strengere handhabung der einheit-einpreis-mengendivisor-felder:werden auch aus den kontraktfeldern gelesen
				if (S.isEmpty(recKON.get_EINHEITKURZ_cUF_NN("")) || S.isEmpty(recKON.get_EINHEIT_PREIS_KURZ_cUF_NN("")) || S.isEmpty(recKON.get_MENGENDIVISOR_cUF_NN("")))
				{
					throw new myExceptionForUser(new MyE2_String("Bei der Abrechnung ist ein Kontrakt im Spiel, bei dem eines der Felder: Einheit/EinheitPreis/Mengendivisor leer ist ! Dies ist verboten!"));
				}
				oStatmt.addSQL_Paar(RECORD_VPOS_RG.FIELD__EINHEITKURZ,			recKON.get_EINHEITKURZ_VALUE_FOR_SQLSTATEMENT());
				oStatmt.addSQL_Paar(RECORD_VPOS_RG.FIELD__EINHEIT_PREIS_KURZ,	recKON.get_EINHEIT_PREIS_KURZ_VALUE_FOR_SQLSTATEMENT());
				oStatmt.addSQL_Paar(RECORD_VPOS_RG.FIELD__MENGENDIVISOR,		recKON.get_MENGENDIVISOR_VALUE_FOR_SQLSTATEMENT());
				//2012-10-16 ------------------------------------------------------
	
				
				
			}
			else if (recAngebot != null)
			{
				//2012-05-28: weiterer sonderfall: es kann passieren, dass es keine zahlungsbedingungen gibt,
				//            da in alten angeboten die id_zahlungsbedingung nicht gesetzt war
				if (recAngebot.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen()!=null)
				{
					//2012-05-22: zusaetzlich auch zahlungsbedingungen aus angeboten holen (wurde bisher ignoriert)
					oStatmt.addSQL_Paar("ZAHLUNGSBEDINGUNGEN",		recAngebot.get_ZAHLUNGSBEDINGUNGEN_VALUE_FOR_SQLSTATEMENT());
					oStatmt.addSQL_Paar("ID_ZAHLUNGSBEDINGUNGEN",	recAngebot.get_ID_ZAHLUNGSBEDINGUNGEN_VALUE_FOR_SQLSTATEMENT());
					oStatmt.addSQL_Paar("ZAHLTAGE",					recAngebot.get_ZAHLTAGE_VALUE_FOR_SQLSTATEMENT());
					oStatmt.addSQL_Paar("FIXMONAT",					recAngebot.get_FIXMONAT_VALUE_FOR_SQLSTATEMENT());
					oStatmt.addSQL_Paar("FIXTAG",					recAngebot.get_FIXTAG_VALUE_FOR_SQLSTATEMENT());
					oStatmt.addSQL_Paar("SKONTO_PROZENT",			recAngebot.get_SKONTO_PROZENT_VALUE_FOR_SQLSTATEMENT());
				}

				if (S.isFull(recAngebot.get_LIEFERBEDINGUNGEN_cUF_NN("")))
				{
					oStatmt.addSQL_Paar("LIEFERBEDINGUNGEN",		recAngebot.get_LIEFERBEDINGUNGEN_VALUE_FOR_SQLSTATEMENT());
				}

				/*
				 *fremdwaehrung
				 */
				oStatmt.addSQL_Paar("WAEHRUNGSKURS",			recAngebot.get_WAEHRUNGSKURS_VALUE_FOR_SQLSTATEMENT());
				oStatmt.addSQL_Paar("ID_WAEHRUNG_FREMD",		recAngebot.get_ID_WAEHRUNG_FREMD_VALUE_FOR_SQLSTATEMENT());
				
				
				//2012-10-16: strengere handhabung der einheit-einpreis-mengendivisor-felder:werden auch aus den kontraktfeldern gelesen
				if (S.isEmpty(recAngebot.get_EINHEITKURZ_cUF_NN("")) || S.isEmpty(recAngebot.get_EINHEIT_PREIS_KURZ_cUF_NN("")) || S.isEmpty(recAngebot.get_MENGENDIVISOR_cUF_NN("")))
				{
					throw new myExceptionForUser(new MyE2_String("Bei der Abrechnung ist ein Angebot im Spiel, bei dem eines der Felder: Einheit/EinheitPreis/Mengendivisor leer ist ! Dies ist verboten!"));
				}
				oStatmt.addSQL_Paar(RECORD_VPOS_RG.FIELD__EINHEITKURZ,			recAngebot.get_EINHEITKURZ_VALUE_FOR_SQLSTATEMENT());
				oStatmt.addSQL_Paar(RECORD_VPOS_RG.FIELD__EINHEIT_PREIS_KURZ,	recAngebot.get_EINHEIT_PREIS_KURZ_VALUE_FOR_SQLSTATEMENT());
				oStatmt.addSQL_Paar(RECORD_VPOS_RG.FIELD__MENGENDIVISOR,		recAngebot.get_MENGENDIVISOR_VALUE_FOR_SQLSTATEMENT());
				//2012-10-16 ------------------------------------------------------

			}
			
			//2014-06-05: hier wird optional nach der alternativen lieferbedingung geschaut und diese falls vorhanden eingebaut
			if (S.isFull(this.get_LIEFERBED_ALTERNATIV_cUF_NN(""))) {
				oStatmt.addSQL_Paar("LIEFERBEDINGUNGEN",		this.get_LIEFERBED_ALTERNATIV_VALUE_FOR_SQLSTATEMENT());
			}
			//2014-06-05

			
			
			
		}
		
		return oStatmt;
	}
	

	/**
	 * Sucht alle Ladedaten der betreffenden Fuhre heraus (im Format NULL oder '2011-12-31')
	 * @return
	 * @throws myException
	 */
	public Vector<String> get_vAlleBeteiligtenLadeDaten_SQL_NOTATION() throws myException
	{
		Vector<String>  vRueck = new Vector<String>();

		RECORD_VPOS_TPA_FUHRE  recFuhre = this.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre();

		vRueck.add(recFuhre.get_DATUM_AUFLADEN_VALUE_FOR_SQLSTATEMENT());
		
		RECLIST_VPOS_TPA_FUHRE_ORT recListFuhrenOrte = 
			new RECLIST_VPOS_TPA_FUHRE_ORT("SELECT * FROM JT_VPOS_TPA_FUHRE_ORT " +
					" WHERE NVL(JT_VPOS_TPA_FUHRE_ORT.DELETED,'N')='N' " +
					" AND NVL(JT_VPOS_TPA_FUHRE_ORT.DEF_QUELLE_ZIEL,'XX')='EK'" +
					" AND JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_TPA_FUHRE="+recFuhre.get_ID_VPOS_TPA_FUHRE_cUF());
		
		for (int i=0;i<recListFuhrenOrte.get_vKeyValues().size();i++)
		{
			vRueck.add(recListFuhrenOrte.get(i).get_DATUM_LADE_ABLADE_VALUE_FOR_SQLSTATEMENT());
		}

		return vRueck;
	}

	
	
	/**
	 * Sucht alle NICHT LEEREN Ladedaten der betreffenden Fuhre heraus (im Format NULL oder '2011-12-31')
	 * @return
	 * @throws myException
	 */
	public Vector<String> get_vAlleBeteiligtenLadeDaten_SQL_NOTATION_NOT_NULL_SORTED() throws myException
	{
		Vector<String> V1 = this.get_vAlleBeteiligtenLadeDaten_SQL_NOTATION();
		
		Vector<String>  vRueck = new Vector<String>();
		
		for (int i=0;i<V1.size();i++)
		{
			if (S.isFull(V1.get(i)))
			{
				if (!(S.NN(V1.get(i)).trim().toUpperCase().equals("NULL")))
				{
					vRueck.add(V1.get(i));
				}
			}
		}
		Collections.sort(vRueck);
		
		return vRueck;
	}	

	
	

	
	public BigDecimal get_bdMengeZurVerbuchung() throws myException 
	{
		boolean bEK = this.get_DEF_QUELLE_ZIEL_cUF().equals("EK");

		BigDecimal bdMenge = null;
		
		if (bEK)
		{
			bdMenge = this.get_ANTEIL_LADEMENGE_bdValue(null, 3);
			if (this.is_LADEMENGE_GUTSCHRIFT_NO())
			{
				bdMenge = this.get_ANTEIL_ABLADEMENGE_bdValue(null, 3);
			}
		}
		else
		{
			bdMenge = this.get_ANTEIL_ABLADEMENGE_bdValue(null, 3);
			if (this.is_ABLADEMENGE_RECHNUNG_NO())
			{
				bdMenge = this.get_ANTEIL_LADEMENGE_bdValue(null, 3);
			}
		}
		
		return bdMenge;
	}

	
	@Override
	public String get_ID() throws myException
	{
		return this.get_ID_VPOS_TPA_FUHRE_ORT_cUF();
	}

	

	@Override
	public String get_TABLENAME_CHANGED() throws myException
	{
		return this.TABLENAME_CHANGED;
	}

	
}
