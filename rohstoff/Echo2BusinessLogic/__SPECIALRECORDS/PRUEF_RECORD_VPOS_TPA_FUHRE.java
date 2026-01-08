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
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_EK_VK_BEZUG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_KON_LAGER;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_WIEGEKARTE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_STD;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WIEGEKARTE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;
import panter.gmbh.indep.myVectors.VectorSingle;
import panter.gmbh.indep.myVectors.bibVECTOR;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU___CONST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.BUCHUNG.BUCH_StatementBuilder;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.BUCHUNG.VECTOR_BUCH_StatementBuilder;
import rohstoff.utils.bibROHSTOFF;
import rohstoff.utils.SQL_DAEMONS.IF_PRUEF_Interface;
import rohstoff.utils.SQL_DAEMONS.VectorSingle_PRUEF_Interface;

public class PRUEF_RECORD_VPOS_TPA_FUHRE extends RECORD_VPOS_TPA_FUHRE  implements IF_PRUEF_Interface
{
	private boolean bGeprueft = false;
	private boolean bPrimary = false;

	//der tabellenname im pruefDaemon, der diese klasse initiert
	private String         TABLENAME_CHANGED = null;

	
	public PRUEF_RECORD_VPOS_TPA_FUHRE(String cId_VposTpaFuhren, boolean Primary) throws myException
	{
		super(cId_VposTpaFuhren);
		this.bPrimary = Primary;
	}

	
	public PRUEF_RECORD_VPOS_TPA_FUHRE(String cId_VposTpaFuhren, MyConnection Conn, boolean Primary, String cTablename_changed) throws myException
	{
		super(cId_VposTpaFuhren, Conn);
		this.TABLENAME_CHANGED = cTablename_changed;
		this.bPrimary = Primary;
	}

	
	public PRUEF_RECORD_VPOS_TPA_FUHRE(String cId_VposTpaFuhren, MyConnection Conn, boolean Primary) throws myException
	{
		super(cId_VposTpaFuhren, Conn);
		this.bPrimary = Primary;
	}

	
	
	public PRUEF_RECORD_VPOS_TPA_FUHRE(RECORD_VPOS_TPA_FUHRE recordOrig, boolean Primary)
	{
		super(recordOrig);
		this.bPrimary = Primary;
	}


	public PRUEF_RECORD_VPOS_TPA_FUHRE(RECORD_VPOS_TPA_FUHRE recordOrig, boolean Primary, String cTablename_changed)
	{
		super(recordOrig);
		this.TABLENAME_CHANGED = cTablename_changed;

		this.bPrimary = Primary;
	}

	
	
	
	@Override
	public String get_TABLENAME_CHANGED() throws myException
	{
		return this.TABLENAME_CHANGED;
	}

	
	public boolean equals(Object oVGL)
	{
		if (oVGL instanceof PRUEF_RECORD_VPOS_TPA_FUHRE)
		{
			try
			{
				if  (   ((PRUEF_RECORD_VPOS_TPA_FUHRE)oVGL).get_ID_VPOS_TPA_FUHRE_cUF().equals(this.get_ID_VPOS_TPA_FUHRE_cUF()))
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



	
	public boolean IsDone()
	{
		return this.bGeprueft;
	}
	
	public void  setDone(boolean bDone)
	{
		this.bGeprueft=bDone;
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
	
		String cFuhreAbholDatumVon = this.get_DATUM_ABHOLUNG_cF();
		String cFuhreAbholDatumBis = this.get_DATUM_ABHOLUNG_ENDE_cF_NN(cFuhreAbholDatumVon);
		
		String cFuhreLieferDatumVon = this.get_DATUM_ANLIEFERUNG_cF();
		String cFuhreLieferDatumBis = this.get_DATUM_ANLIEFERUNG_ENDE_cF_NN(cFuhreLieferDatumVon);
	
		//Zeiten EK-Seite pruefen
		if (S.isFull(this.get_ID_VPOS_KON_EK_cUF_NN("")))
		{
			String cDateKonGueltigVon = this.get_UP_RECORD_VPOS_KON_id_vpos_kon_ek().get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).get_GUELTIG_VON_cF();
			String cDateKonGueltigBis = this.get_UP_RECORD_VPOS_KON_id_vpos_kon_ek().get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).get_GUELTIG_BIS_cF();
	
			//2011-10-04: der fall wenn bei einer fuhrenkopie die zeiten leergemacht werden muss separat abgefangen werden
			boolean bEinDatumIstLeer=S.isEmpty(cDateKonGueltigVon) || S.isEmpty(cDateKonGueltigBis) || S.isEmpty(cFuhreAbholDatumVon) || S.isEmpty(cFuhreAbholDatumBis);
			
			if (bEinDatumIstLeer || (!myDateHelper.get_bDatumsBereicheHabenSchnittmengen(cDateKonGueltigVon, cDateKonGueltigBis, cFuhreAbholDatumVon, cFuhreAbholDatumBis)))
			{
				if (this.bPrimary) oMV.add_MESSAGE(
						new MyE2_BASIC_WarningMessageWithAddonComponent(
								new MyE2_String("Gültigkeitszeitraum EK-Kontrakt und Fuhre passen nicht (oder ein Datum ist nicht gesetzt)  !!!"),
								this.get_ownInfoButton(new MyE2_String("Gültigkeitszeitraum EK-Kontrakt und Fuhre passen nicht (oder ein Datum ist nicht gesetzt) !!!")),
								new Extent(95,Extent.PERCENT),
								new Extent(5,Extent.PERCENT)));
			}
		}
	
		//Zeiten VK-Seite pruefen
		if (S.isFull(this.get_ID_VPOS_KON_VK_cUF_NN("")))
		{
			String cDateKonGueltigVon = this.get_UP_RECORD_VPOS_KON_id_vpos_kon_vk().get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).get_GUELTIG_VON_cF();
			String cDateKonGueltigBis = this.get_UP_RECORD_VPOS_KON_id_vpos_kon_vk().get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).get_GUELTIG_BIS_cF();

			//2011-10-04: der fall wenn bei einer fuhrenkopie die zeiten leergemacht werden muss separat abgefangen werden
			boolean bEinDatumIstLeer=S.isEmpty(cDateKonGueltigVon) || S.isEmpty(cDateKonGueltigBis) || S.isEmpty(cFuhreLieferDatumVon) || S.isEmpty(cFuhreLieferDatumBis);
	
			if (bEinDatumIstLeer || (!myDateHelper.get_bDatumsBereicheHabenSchnittmengen(cDateKonGueltigVon, cDateKonGueltigBis, cFuhreLieferDatumVon, cFuhreLieferDatumBis)))
			{
				if (this.bPrimary) oMV.add_MESSAGE(
						new MyE2_BASIC_WarningMessageWithAddonComponent(
								new MyE2_String("Gültigkeitszeitraum VK-Kontrakt und Fuhre passen nicht (oder ein Datum ist nicht gesetzt)  !!!"),
								this.get_ownInfoButton(new MyE2_String("Gültigkeitszeitraum VK-Kontrakt und Fuhre passen nicht (oder ein Datum ist nicht gesetzt) !!!")),
								new Extent(95,Extent.PERCENT),
								new Extent(5,Extent.PERCENT)));
			}
		}
		
		
		
		//jetzt die kontrakte pruefen
		if (S.isFull(this.get_ID_VPOS_KON_EK_cUF()))
		{
			if (this.TABLENAME_CHANGED.equals(this.get_TABLE_NAME()))
			{
				vSammler.add(new PRUEF_RECORD_VPOS_KON(this.get_ID_VPOS_KON_EK_cUF(),this.get_oConn(),this.bPrimary, this.TABLENAME_CHANGED));
			}
		}
		if (S.isFull(this.get_ID_VPOS_KON_VK_cUF()))
		{
			if (this.TABLENAME_CHANGED.equals(this.get_TABLE_NAME()))
			{
				vSammler.add(new PRUEF_RECORD_VPOS_KON(this.get_ID_VPOS_KON_VK_cUF(),this.get_oConn(),this.bPrimary, this.TABLENAME_CHANGED));
			}
		}

		if (S.isFull(this.get_ID_VPOS_KON_EK_cUF()) && S.isFull(this.get_ID_VPOS_KON_VK_cUF()))
		{
			//sind beide kontrakte gefuellt, dann kann es eine zuordnung geben - dies pruefen
			RECLIST_EK_VK_BEZUG oRL_EK_VK = new RECLIST_EK_VK_BEZUG("SELECT * FROM "+bibE2.cTO()+".JT_EK_VK_BEZUG WHERE ID_VPOS_KON_EK="+this.get_ID_VPOS_KON_EK_cUF()+" AND "+
																			"ID_VPOS_KON_VK="+this.get_ID_VPOS_KON_VK_cUF(),this.get_oConn());
			for (String cID_EK_VK_BEZUG: oRL_EK_VK.get_vKeyValues())
			{
				if (this.TABLENAME_CHANGED.equals(this.get_TABLE_NAME()))
				{
					vSammler.add(new PRUEF_RECORD_EK_VK_BEZUG(oRL_EK_VK.get(cID_EK_VK_BEZUG),this.bPrimary,this.TABLENAME_CHANGED));
				}
			}
		}
		
		//jetzt pruefen, ob die fuhre eine lagerzuordnung tangiert
		/*
		 *  dann koennte das eine lagerzuoerdung tangieren (die existiert, dann muss deren mengenplan geprueft werden)
		 *  es koennte aber auch sein, dann die kombination kontrakt zu lager ueberhaupt nicht vorgesehen ist,
		 *  das wird mit dem booleschen wert lager_kontrakt_zugeordnet
		 *
		 */
		boolean blager_kontrakt_zugeordnet = true;
		if ((this.get_ID_ADRESSE_START_cUF_NN("").equals(bibALL.get_ID_ADRESS_MANDANT()) && S.isFull(this.get_ID_VPOS_KON_VK_cUF()) ) ||
			(this.get_ID_ADRESSE_ZIEL_cUF_NN("").equals(bibALL.get_ID_ADRESS_MANDANT()) && S.isFull(this.get_ID_VPOS_KON_EK_cUF()) ))
		{
			blager_kontrakt_zugeordnet = false;
		}
		
		if (this.get_ID_ADRESSE_START_cUF_NN("").equals(bibALL.get_ID_ADRESS_MANDANT()))
		{
			String cID_ADRESSE_LAGER = this.get_ID_ADRESSE_LAGER_START_cUF_NN("");

			if (S.isFull(this.get_ID_VPOS_KON_VK_cUF()))
			{
				String cID_VPOS_KON_VK = this.get_ID_VPOS_KON_VK_cUF_NN("");
				
				if (S.isFull(cID_VPOS_KON_VK))
				{
					RECLIST_VPOS_KON_LAGER oRecList = new RECLIST_VPOS_KON_LAGER("SELECT * FROM "+bibE2.cTO()+".JT_VPOS_KON_LAGER WHERE ID_VPOS_KON="+cID_VPOS_KON_VK+" AND "+
																				 " ID_ADRESSE_LAGER="+cID_ADRESSE_LAGER,this.get_oConn());
					if (oRecList.size()>0)
					{
						blager_kontrakt_zugeordnet = true;
					}
					for (String cID_VPOS_KON_LAGER: oRecList.get_vKeyValues())
					{
						if (this.TABLENAME_CHANGED.equals(this.get_TABLE_NAME()))
						{
							vSammler.add(new PRUEF_RECORD_VPOS_KON_LAGER(oRecList.get(cID_VPOS_KON_LAGER),this.bPrimary,this.TABLENAME_CHANGED));   //wird in der fuhre primaer geschaltet
						}
					}
				}
			}
		}

		if (this.get_ID_ADRESSE_ZIEL_cUF_NN("").equals(bibALL.get_ID_ADRESS_MANDANT()))
		{
			String cID_ADRESSE_LAGER = this.get_ID_ADRESSE_LAGER_ZIEL_cUF_NN("");

			if (S.isFull(this.get_ID_VPOS_KON_EK_cUF()))
			{
				String cID_VPOS_KON_EK = this.get_ID_VPOS_KON_EK_cUF_NN("");
				
				if (S.isFull(cID_VPOS_KON_EK))
				{
					RECLIST_VPOS_KON_LAGER oRecList = new RECLIST_VPOS_KON_LAGER("SELECT * FROM "+bibE2.cTO()+".JT_VPOS_KON_LAGER WHERE ID_VPOS_KON="+cID_VPOS_KON_EK+" AND "+
																				 " ID_ADRESSE_LAGER="+cID_ADRESSE_LAGER,this.get_oConn());

					if (oRecList.size()>0)
					{
						blager_kontrakt_zugeordnet = true;
					}
					for (String cID_VPOS_KON_LAGER: oRecList.get_vKeyValues())
					{
						if (this.TABLENAME_CHANGED.equals(this.get_TABLE_NAME()))
						{
							vSammler.add(new PRUEF_RECORD_VPOS_KON_LAGER(oRecList.get(cID_VPOS_KON_LAGER),this.bPrimary,this.TABLENAME_CHANGED));
						}
					}
				}
			}
		}
		
		/*
		 * falls die lager-kontrakt-zuordnung der fuhre nicht vorhanden ist, dann 
		 * warnung
		 */
		if (!blager_kontrakt_zugeordnet)
		{
			oMV.add_MESSAGE(new MyE2_Warning_Message("Die Zuordnung zwischen Lager und Kontrakt ist in der Kontrakt-Definition nicht vorgesehen !!"));
		}
		
		
		
		oMV.add_MESSAGE(this.Check_Lagerort_Wiegekarte_Eindeutig());
		
		//jetzt die orte noch in den pruefvector einhaengen
		//jetzt die zusatzorte noch in die pruefung mit aufnehmen
//		Vector<String> vZusatzorte = new Vector<String>();
		
		RECLIST_VPOS_TPA_FUHRE_ORT  reclistOrte = this.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre("NVL(DELETED,'N')='N'",null,true);
		
//		vZusatzorte.addAll(this.get_vIDsZusatzorteEK_NotDeleted());
//		vZusatzorte.addAll(this.get_vIDsZusatzorteVK_NotDeleted());
//		
		
		//for (String IDOrt: vZusatzorte)
		for (Map.Entry<String,RECORD_VPOS_TPA_FUHRE_ORT> oEntry: reclistOrte.entrySet())	
		{
			if (this.TABLENAME_CHANGED.equals(this.get_TABLE_NAME()))
			{
				vSammler.add(new PRUEF_RECORD_VPOS_TPA_FUHRE_ORT(oEntry.getValue(),this.bPrimary,this.TABLENAME_CHANGED));
			}
		}

		return oMV;
	}
	
	
	
	
	
	/**
	 * @return s Buchungsstatus einer Fuhre aufgrund der Dateninhalte in den positionssaetzen
	 * @throws myException
	 * 	public static int     	STATUS_FUHRE__IST_STORNIERT = 					-2;
	 *  public static int     	STATUS_FUHRE__IST_ALT_WID_NICHT_BEGUCHT = 		-1;
	 *  public static int     	STATUS_FUHRE__EINGABE_IST_NOCH_NICHT_FERTIG = 	1;
	 *  public static int     	STATUS_FUHRE__BESITZT_KEINE_BUCHUNGSPOS = 		2;
	 *  public static int     	STATUS_FUHRE__UNGEBUCHT= 						3;
	 *  public static int     	STATUS_FUHRE__TEILSGEBUCHT= 					4;
	 *  public static int     	STATUS_FUHRE__GANZGEBUCHT = 					5;
	 */
	public int __Actual_StatusBuchung() throws myException
	{
		
		VECTOR_BUCH_StatementBuilder 	vHelp = 	this.get_vAbrechnungsStatements(true);    //liefert abrechnungsstatements der fuhre und der zugehoerigen orte

		return vHelp.get_FUHREN_STATUS(this);
		
		
	}

	
	



	
	/**
	 * @param bCommit MUSS in den Daemon-Routinen = false sein
	 */
	public MyE2_MessageVector  __writeSQLStatemtents_MengenSituation_undFuhrenStatus(boolean bCommit) throws myException
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		RECORD_VPOS_TPA_FUHRE  recEigeneFuhre = new RECORD_VPOS_TPA_FUHRE(this);
		
		RECLIST_VPOS_TPA_FUHRE_ORT reclistFUHRE_ORT_EK = recEigeneFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre("NVL(DELETED,'N')='N' AND DEF_QUELLE_ZIEL='EK' ","ID_VPOS_TPA_FUHRE_ORT",true);
		RECLIST_VPOS_TPA_FUHRE_ORT reclistFUHRE_ORT_VK = recEigeneFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre("NVL(DELETED,'N')='N' AND DEF_QUELLE_ZIEL='VK' ","ID_VPOS_TPA_FUHRE_ORT",true);
		
		int  iAnzahlNachKomma = 3;
		if (this.get_UP_RECORD_ARTIKEL_id_artikel() != null)
		{
			iAnzahlNachKomma = this.get_UP_RECORD_ARTIKEL_id_artikel().get_GENAUIGKEIT_MENGEN_lValue(new Long(3)).intValue();
		}
		
		
		//pruefen, ob die kompletten mengen-felder auf der jeweiligen seite richtig gefuellt sind
		//step 1: planmengen, eigegeben auf der ek-seite
		boolean bPlanmengeIst_Voll = true;
		BigDecimal bdSummePlanMenge = new BigDecimal(0);
		if (recEigeneFuhre.get_ANTEIL_PLANMENGE_LIEF_bdValue(null)==null)
		{
			bPlanmengeIst_Voll=false;
		}
		else
		{
			bdSummePlanMenge = bdSummePlanMenge.add(this.get_ANTEIL_PLANMENGE_LIEF_bdValue(null,3));
		}
		for (int i=0;i<reclistFUHRE_ORT_EK.get_vKeyValues().size();i++)
		{
			if (reclistFUHRE_ORT_EK.get(i).get_ANTEIL_PLANMENGE_bdValue(null)==null)
			{
				bPlanmengeIst_Voll=false;
			}
			else
			{
				bdSummePlanMenge = bdSummePlanMenge.add(reclistFUHRE_ORT_EK.get(i).get_ANTEIL_PLANMENGE_bdValue(null,3));
			}
		}

		//step 2: lademengen, eigegeben auf der ek-seite
		boolean bLademengeIst_Voll = true;
		BigDecimal bdSummeLadeMenge = new BigDecimal(0);
		if (recEigeneFuhre.get_ANTEIL_LADEMENGE_LIEF_bdValue(null)==null)
		{
			bLademengeIst_Voll=false;
		}
		else
		{
			bdSummeLadeMenge = bdSummeLadeMenge.add(this.get_ANTEIL_LADEMENGE_LIEF_bdValue(null,3));
		}
		for (int i=0;i<reclistFUHRE_ORT_EK.get_vKeyValues().size();i++)
		{
			if (reclistFUHRE_ORT_EK.get(i).get_ANTEIL_LADEMENGE_bdValue(null)==null)
			{
				bLademengeIst_Voll=false;
			}
			else
			{
				bdSummeLadeMenge = bdSummeLadeMenge.add(reclistFUHRE_ORT_EK.get(i).get_ANTEIL_LADEMENGE_bdValue(null,3));
			}
		}
		
		
		//step 3: ablademengen, eingegeben auf der vk-seite
		boolean bALademengeIst_Voll = true;
		BigDecimal bdSummeAbladeMenge = new BigDecimal(0);
		if (recEigeneFuhre.get_ANTEIL_ABLADEMENGE_ABN_bdValue(null)==null)
		{
			bALademengeIst_Voll=false;
		}
		else
		{
			bdSummeAbladeMenge = bdSummeAbladeMenge.add(this.get_ANTEIL_ABLADEMENGE_ABN_bdValue(null,3));
		}
		for (int i=0;i<reclistFUHRE_ORT_VK.get_vKeyValues().size();i++)
		{
			if (reclistFUHRE_ORT_VK.get(i).get_ANTEIL_ABLADEMENGE_bdValue(null)==null)
			{
				bALademengeIst_Voll=false;
			}
			else
			{
				bdSummeAbladeMenge = bdSummeAbladeMenge.add(reclistFUHRE_ORT_VK.get(i).get_ANTEIL_ABLADEMENGE_bdValue(null,3));
			}
		}
		
		
		//sind bestimmte mengen gefuellt, dann die haupt-mengen-felder in der fuhre fuellen
		recEigeneFuhre.set_NEW_VALUE_MENGE_VORGABE_KO(bPlanmengeIst_Voll?this.MDBSTR(bdSummePlanMenge):null);
		recEigeneFuhre.set_NEW_VALUE_MENGE_AUFLADEN_KO(bLademengeIst_Voll?this.MDBSTR(bdSummeLadeMenge):null);
		recEigeneFuhre.set_NEW_VALUE_MENGE_ABLADEN_KO(bALademengeIst_Voll?this.MDBSTR(bdSummeAbladeMenge):null);


		/*
		 * sonderfall: keine zusatzorte, planmenge auf der ladeseite ist vorhanden, auf der abladeseite nicht,
		 * dann wird diese ruebergeschrieben, um sich tipperei zu sparen
		 */
		if (  reclistFUHRE_ORT_EK.get_vKeyValues().size()==0 && 
			  reclistFUHRE_ORT_VK.get_vKeyValues().size()==0 &&
			  S.isEmpty(recEigeneFuhre.get_ANTEIL_PLANMENGE_ABN_cUF()) &&
			  S.isFull(recEigeneFuhre.get_ANTEIL_PLANMENGE_LIEF_cUF()))
		{
			recEigeneFuhre.set_NEW_VALUE_ANTEIL_PLANMENGE_ABN(recEigeneFuhre.get_ANTEIL_PLANMENGE_LIEF_cF());
		}
			  
		

		/*
		 * geaendert am: 18.02.2010 von: martin
		 * Es werden nur noch die planmengen verteilt, die komplementaeren lade- oder ablademengen muessen haendisch eingetragen werden
		 */
		
		//wenn die Planmengen auf der EK-Seite voll sind, dann werden die komplementaeren planfelder auf der VK-Seite aufgeteilt
		if (bPlanmengeIst_Voll && bLademengeIst_Voll && bALademengeIst_Voll)
		{
			//zuerst die linken seiten fuellen
			bildeVerhaeltnis bdV_PLAN_VK = new bildeVerhaeltnis(bdSummeAbladeMenge,bdSummePlanMenge, iAnzahlNachKomma);
			
			//dann die rechten seiten
			for (int i=0; i<reclistFUHRE_ORT_VK.get_vKeyValues().size();i++)
			{
				reclistFUHRE_ORT_VK.get(i).set_NEW_VALUE_ANTEIL_PLANMENGE(bdV_PLAN_VK.get_FormatedNewValue_gegenWert(reclistFUHRE_ORT_VK.get(i).get_ANTEIL_ABLADEMENGE_bdValue(null)));
			}
		}

		
		
		//2011-08-17: neues feld TYP_STRECKE_LAGER_MIXED gleich mit in die fuhre reihschreiben
		recEigeneFuhre.set_NEW_VALUE_TYP_STRECKE_LAGER_MIXED(this.get_TYP_STRECKE_LAGER_MIXED(recEigeneFuhre,reclistFUHRE_ORT_EK,reclistFUHRE_ORT_VK));
		

		
		Vector<String> vSQL = new Vector<String>();
		
		MySqlStatementBuilder oSTMT = recEigeneFuhre.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
		if (oSTMT.size()>0) 
		{
			vSQL.add(oSTMT.get_CompleteUPDATEString("JT_VPOS_TPA_FUHRE", bibE2.cTO(), "ID_VPOS_TPA_FUHRE="+recEigeneFuhre.get_ID_VPOS_TPA_FUHRE_cUF(),null));
		}
			
		for (int i=0; i<reclistFUHRE_ORT_EK.get_vKeyValues().size();i++)
		{
			oSTMT = reclistFUHRE_ORT_EK.get(i).get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
			if (oSTMT.size()>0) 
			{
				vSQL.add(oSTMT.get_CompleteUPDATEString("JT_VPOS_TPA_FUHRE_ORT", bibE2.cTO(), "ID_VPOS_TPA_FUHRE_ORT="+reclistFUHRE_ORT_EK.get(i).get_ID_VPOS_TPA_FUHRE_ORT_cUF(),null));
			}
		}		
		
		for (int i=0; i<reclistFUHRE_ORT_VK.get_vKeyValues().size();i++)
		{
			oSTMT = reclistFUHRE_ORT_VK.get(i).get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS();
			if (oSTMT.size()>0) 
			{
				vSQL.add(oSTMT.get_CompleteUPDATEString("JT_VPOS_TPA_FUHRE_ORT", bibE2.cTO(), "ID_VPOS_TPA_FUHRE_ORT="+reclistFUHRE_ORT_VK.get(i).get_ID_VPOS_TPA_FUHRE_ORT_cUF(),null));
			}
		}		

	
		boolean bSQLOK = false;

		/*
		 * wichtig! der sql-stack darf hier nur einzeln (NICHT mit ExecMultiSQLVector()) ausgefuehrt werden, da sonst eine endlosschleife im daemon erfolgt !
		 */
		if (vSQL.size()>0)
		{
			MyDBToolBox oDB =  (this.get_oConn()==null?bibALL.get_myDBToolBox():bibALL.get_myDBToolBox(this.get_oConn()));
			for (int i=0;i<vSQL.size();i++)
			{
				bSQLOK = oDB.ExecSQL(vSQL.get(i), bCommit);
				if (!bSQLOK)
				{
					break;
				}
			}
			bibALL.destroy_myDBToolBox(oDB);
			
			if (!bSQLOK)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Die Mengensituation der Fuhre konnte nicht geschrieben werden !"));
				return oMV;
			}
		}
		
		this.REBUILD();
		
		int iStatusBuchung = this.__Actual_StatusBuchung();
		String cActualStatus = ""+iStatusBuchung;
		
		String cSQL = null;
		
		if (!bibALL.null2leer(this.get_STATUS_BUCHUNG_cUF()).equals(""+cActualStatus))
		{
			cSQL = "UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE SET STATUS_BUCHUNG="+cActualStatus+" WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_VALUE_FOR_SQLSTATEMENT();
			
			//2013-01-24: wenn der statusbuchung = 5 / fertig gebucht ist, dann wird ein Fuhrenabschluss gesetzt, wenn er noch offen ist
			if (iStatusBuchung==myCONST.STATUS_FUHRE__GANZGEBUCHT)
			{
				cSQL = "UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE " +
						" SET "+ 
						RECORD_VPOS_TPA_FUHRE.FIELD__STATUS_BUCHUNG + "="+cActualStatus+", "+
						RECORD_VPOS_TPA_FUHRE.FIELD__SCHLIESSE_FUHRE + "='Y', "+
						RECORD_VPOS_TPA_FUHRE.FIELD__SCHLIESSE_FUHRE_VON +"="+bibALL.get_RECORD_USER().get_KUERZEL_VALUE_FOR_SQLSTATEMENT()+", "+
						RECORD_VPOS_TPA_FUHRE.FIELD__SCHLIESSE_FUHRE_AM + "=SYSDATE "+
						" WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_VALUE_FOR_SQLSTATEMENT();
			}
			else if (bibALL.null2leer(this.get_STATUS_BUCHUNG_cUF()).equals(""+myCONST.STATUS_FUHRE__GANZGEBUCHT))
//			else if (this.get_STATUS_BUCHUNG_cUF().equals(""+myCONST.STATUS_FUHRE__GANZGEBUCHT))
			{
				//falls der status vorher 5 war und jetzt reduziert wird, da z.b. die fuhre wieder aufgeht, dann muessen die schliessmerkmale wieder weg
				cSQL = "UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE " +
							" SET "+ 
							RECORD_VPOS_TPA_FUHRE.FIELD__STATUS_BUCHUNG + "="+cActualStatus+", "+
							RECORD_VPOS_TPA_FUHRE.FIELD__SCHLIESSE_FUHRE + "='N', "+
							RECORD_VPOS_TPA_FUHRE.FIELD__SCHLIESSE_FUHRE_VON +"=NULL, "+
							RECORD_VPOS_TPA_FUHRE.FIELD__SCHLIESSE_FUHRE_AM + "=NULL "+
							" WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_VALUE_FOR_SQLSTATEMENT();
			}
			
			
			bSQLOK = false;
			if (this.get_oConn() == null)
			{
				bSQLOK = bibDB.ExecSQL(cSQL, bCommit);
			}
			else
			{
				MyDBToolBox oDB = bibALL.get_myDBToolBox(this.get_oConn());
				bSQLOK = oDB.ExecSQL(cSQL, bCommit);
				bibALL.destroy_myDBToolBox(oDB);
			}
			if (!bSQLOK)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Der Mengenstatus der Fuhre / der Abschlussvermerk der Fuhre konnte nicht geschrieben werden !"));
				return oMV;
			}
			
			this.REBUILD();
		}
		
		return oMV;
	}	
	
	
	
	
	//2011-08-17: weiteres feld mit dem status mitschreiben
	//4 = LAGER-ZU-LAGER
	//0 = STRECKE
	//1 = IN-LAGER
	//2 = EX-LAGER
	//3 = MIXED
	private String get_TYP_STRECKE_LAGER_MIXED(RECORD_VPOS_TPA_FUHRE recfuhre, RECLIST_VPOS_TPA_FUHRE_ORT  reclistFUO_EK, RECLIST_VPOS_TPA_FUHRE_ORT  reclistFUO_VK) throws myException
	{
		/*
		 * CASE WHEN    VIEW1.ANZAHL_FREMDLAGER_START>0
             AND VIEW1.ANZAHL_FREMDLAGER_ZIEL>0
             AND VIEW1.ANZAHL_EIGENLAGER_START=0
             AND VIEW1.ANZAHL_EIGENLAGER_ZIEL=0 THEN
	     '0'
	     WHEN    VIEW1.ANZAHL_FREMDLAGER_START>0
	             AND VIEW1.ANZAHL_FREMDLAGER_ZIEL=0
	             AND VIEW1.ANZAHL_EIGENLAGER_START=0
	             AND VIEW1.ANZAHL_EIGENLAGER_ZIEL=1 THEN
	     '1'
	     WHEN    VIEW1.ANZAHL_FREMDLAGER_START=0
	             AND VIEW1.ANZAHL_FREMDLAGER_ZIEL>0
	             AND VIEW1.ANZAHL_EIGENLAGER_START=1
	             AND VIEW1.ANZAHL_EIGENLAGER_ZIEL=0 THEN
	     '2'
	     ELSE
	     '3'
		 */
		
		int iAnzahlFremdlagerStart=0;
		int iAnzahlFremdlagerZiel=0;
		int iAnzahlEigenlagerStart=0;
		int iAnzahlEigenlagerZiel=0;
		
		
		String cID_ADRESSE_MANDANT = bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF().trim();
		
		if (recfuhre.get_ID_ADRESSE_START_cUF_NN("-1").trim().equals(cID_ADRESSE_MANDANT))
		{
			iAnzahlEigenlagerStart++;
		}
		else
		{
			iAnzahlFremdlagerStart++;
		}
		
		if (recfuhre.get_ID_ADRESSE_ZIEL_cUF_NN("-1").trim().equals(cID_ADRESSE_MANDANT))
		{
			iAnzahlEigenlagerZiel++;
		}
		else
		{
			iAnzahlFremdlagerZiel++;
		}
		
		
		for (int i=0;i<reclistFUO_EK.get_vKeyValues().size();i++)
		{
			if (reclistFUO_EK.get(i).get_ID_ADRESSE_cUF_NN("-1").trim().equals(cID_ADRESSE_MANDANT))
			{
				iAnzahlEigenlagerStart++;
			}
			else
			{
				iAnzahlFremdlagerStart++;
			}
		}
		
		for (int i=0;i<reclistFUO_VK.get_vKeyValues().size();i++)
		{
			if (reclistFUO_VK.get(i).get_ID_ADRESSE_cUF_NN("-1").trim().equals(cID_ADRESSE_MANDANT))
			{
				iAnzahlEigenlagerZiel++;
			}
			else
			{
				iAnzahlFremdlagerZiel++;
			}
		}

		if (iAnzahlFremdlagerStart>0 && iAnzahlFremdlagerZiel>0 && iAnzahlEigenlagerStart==0 && iAnzahlEigenlagerZiel==0)
		{
			return "0";     //STRECKE
		}
		else if (iAnzahlFremdlagerStart>0 && iAnzahlFremdlagerZiel==0 && iAnzahlEigenlagerStart==0 && iAnzahlEigenlagerZiel==1)
		{
			return "1";     //IN-LAGER
		}
		else if (iAnzahlFremdlagerStart==0 && iAnzahlFremdlagerZiel>0 && iAnzahlEigenlagerStart==1 && iAnzahlEigenlagerZiel==0)
		{
			return "2";     //EX-LAGER
		}
		else if (iAnzahlFremdlagerStart==0 && iAnzahlFremdlagerZiel==0 && iAnzahlEigenlagerStart>0 && iAnzahlEigenlagerZiel>0)
		{
			return "4";     //LAGER-ZU-LAGER
		}
		else
		{
			return "3";     //MIXED
		}
		
	}
	
	
	
	/**
	 * liefert einen string, der einem datenbank-feld zugeteilt werden kann
	 * @param bdValue
	 * @return
	 */
	private String MDBSTR(BigDecimal bdValue) 
	{
		String cRueck = "";
		if (bdValue != null)
		{
			cRueck = bibALL.ReplaceTeilString(bdValue.setScale(3,  BigDecimal.ROUND_HALF_UP).toPlainString(), ".", ",");
		}
		return cRueck;
	}
	
	
	private class bildeVerhaeltnis
	{
		private BigDecimal bdSummeEigeneSeite = null;
		private BigDecimal bdSummeGegenSeite = null;
		private int        iAnzahlNachkomma = 3;
		public bildeVerhaeltnis(BigDecimal bdSummeEigeneSeite,BigDecimal bdSummeGegenSeite, int anzahlNachkomma)
		{
			super();
			this.bdSummeEigeneSeite = bdSummeEigeneSeite;
			this.bdSummeGegenSeite = bdSummeGegenSeite;
			this.iAnzahlNachkomma = anzahlNachkomma;
		}
		public String get_FormatedNewValue_gegenWert(BigDecimal bdAnteilEigenerWert)
		{
			String cRueck = "";
			if (!(  this.bdSummeEigeneSeite==null || this.bdSummeEigeneSeite.compareTo(new BigDecimal(0))==0))
			{
				BigDecimal bdGegenWert = bdSummeGegenSeite.multiply(bdAnteilEigenerWert).divide(this.bdSummeEigeneSeite,this.iAnzahlNachkomma,BigDecimal.ROUND_HALF_UP);
				cRueck =  bibALL.ReplaceTeilString(bdGegenWert.toPlainString(), ".", ",");
			}
			else
			{
				cRueck = "0";          // bei potentieller division/0 werden die werte zu 0 gesetzt
			}
			
			return cRueck;
		}
		
		
		
		
	}
	

	/**
	 * 
	 * @return s array[2], number orte EK , number orte VK
	 * @throws myException
	 */
	public long[] get_arrayAnzahlZusatzorte() throws myException
	{
		long[] iRueck = new long[2];
		
		iRueck[0] = this.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre().get_ID_VPOS_TPA_FUHRE_ORT_l_Count_NotNull(
				new RECLIST_VPOS_TPA_FUHRE_ORT.Validation()
				{
					@Override
					public boolean isValid(RECORD_VPOS_TPA_FUHRE_ORT orecord) throws myException
					{
						if (orecord.get_DEF_QUELLE_ZIEL_cUF().equals("EK") && orecord.is_DELETED_NO())
						{
							return true;
						}
						return false;
					}
				});
		
		iRueck[1] = this.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre().get_ID_VPOS_TPA_FUHRE_ORT_l_Count_NotNull(
				new RECLIST_VPOS_TPA_FUHRE_ORT.Validation()
				{
					@Override
					public boolean isValid(RECORD_VPOS_TPA_FUHRE_ORT orecord) throws myException
					{
						if (orecord.get_DEF_QUELLE_ZIEL_cUF().equals("VK") && orecord.is_DELETED_NO())
						{
							return true;
						}
						return false;
					}
				});

		
		return iRueck;
		
	}
	
	
	
//	public Vector<String> get_vIDsZusatzorteEK_NotDeleted() throws myException
//	{
//		doZaehleOrte  oZaehler = new doZaehleOrte("EK");
//	 	this.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre().DoAnyThing(oZaehler);
//		
//		return oZaehler.get_vRueck();
//	}
//
//	public Vector<String> get_vIDsZusatzorteVK_NotDeleted() throws myException
//	{
//		doZaehleOrte  oZaehler = new doZaehleOrte("VK");
//	 	this.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre().DoAnyThing(oZaehler);
//		
//		return oZaehler.get_vRueck();
//	}
//
	
	
//	private class doZaehleOrte extends RECLIST_VPOS_TPA_FUHRE_ORT.DoAnyThingWithAll
//	{
//		private String 				cEK_VK = null;
//		private Vector<String>		vRueck = new Vector<String>();
//
//		public doZaehleOrte(String cek_vk)
//		{
//			super();
//			cEK_VK = cek_vk;
//		}
//
//		@Override
//		public void doAnyThingWith(String hashKey,RECORD_VPOS_TPA_FUHRE_ORT orecord) throws myException
//		{
//			if (orecord.is_DELETED_NO() && orecord.get_DEF_QUELLE_ZIEL_cUF().equals(this.cEK_VK))
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
//		
//	}
	
	
	
	public boolean get_bHasMengenEintraege() throws myException
	{
		boolean bRueck = false;
		if (	S.isFull(this.get_ANTEIL_LADEMENGE_LIEF_cUF()) || 
				S.isFull(this.get_ANTEIL_PLANMENGE_LIEF_cUF()) || 
				S.isFull(this.get_ANTEIL_ABLADEMENGE_LIEF_cUF()) ||
				S.isFull(this.get_ANTEIL_LADEMENGE_ABN_cUF()) || 
				S.isFull(this.get_ANTEIL_PLANMENGE_ABN_cUF()) || 
				S.isFull(this.get_ANTEIL_ABLADEMENGE_ABN_cUF())
				)
		{
			return true;
		}
		else
		{
			//zusatzorte pruefen
			long lAnzahlMitMenge = 	 this.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre().get_ID_VPOS_TPA_FUHRE_ORT_l_Count_NotNull(
						new RECLIST_VPOS_TPA_FUHRE_ORT.Validation()
						{
							@Override
							public boolean isValid(RECORD_VPOS_TPA_FUHRE_ORT orecord) throws myException
							{
								if (	orecord.is_DELETED_NO() && 
										    (S.isFull(orecord.get_ANTEIL_PLANMENGE_cUF()) ||  
										     S.isFull(orecord.get_ANTEIL_LADEMENGE_cUF()) ||  
										     S.isFull(orecord.get_ANTEIL_ABLADEMENGE_cUF())))
								{
									return true;
								}
								return false;
							}
						});
			
			if (lAnzahlMitMenge>0)
			{
				return true;
			}
		}
		
		return bRueck;
		
	}
	
	
	
	
	
	
	
	public boolean get_bHasWiegekarte() throws myException
	{
		boolean bRueck = false;
		if (S.isFull(this.get_WIEGEKARTENKENNER_LADEN_cUF()) || S.isFull(this.get_WIEGEKARTENKENNER_ABLADEN_cUF()))
		{
			return true;
		}
		else
		{
			//zusatzorte pruefen
			long lAnzahlMitMenge = 	 this.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre().get_ID_VPOS_TPA_FUHRE_ORT_l_Count_NotNull(
						new RECLIST_VPOS_TPA_FUHRE_ORT.Validation()
						{
							@Override
							public boolean isValid(RECORD_VPOS_TPA_FUHRE_ORT orecord) throws myException
							{
								if (	orecord.is_DELETED_NO() && 
										    (S.isFull(orecord.get_WIEGEKARTENKENNER_cUF())))
								{
									return true;
								}
								return false;
							}
						});
			
			if (lAnzahlMitMenge>0)
			{
				return true;
			}
		}
		
		return bRueck;
		
	}

	
	
	
	/**
	 * prueft, ob die kombination der Angaben ID_ADRESSE_LAGER und WIEGEKARTENKENNER eindeutig sind
	 * @return
	 */
	public MyE2_MessageVector  Check_Lagerort_Wiegekarte_Eindeutig() throws myException
	{
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		if (S.isFull(this.get_WIEGEKARTENKENNER_LADEN_cUF_NN("")) && S.isFull(this.get_ID_ADRESSE_LAGER_START_cUF_NN("")))
		{
			oMV.add_MESSAGE(this.__pruefe_wiegekarte(this.get_ID_ADRESSE_LAGER_START_cUF(),this.get_WIEGEKARTENKENNER_LADEN_cUF() ));
		}

		if (S.isFull(this.get_WIEGEKARTENKENNER_ABLADEN_cUF_NN("")) && S.isFull(this.get_ID_ADRESSE_LAGER_ZIEL_cUF_NN("")))
		{
			oMV.add_MESSAGE(this.__pruefe_wiegekarte(this.get_ID_ADRESSE_LAGER_ZIEL_cUF(), this.get_WIEGEKARTENKENNER_ABLADEN_cUF()));
		}
		return oMV;
	}
	

	private MyE2_MessageVector __pruefe_wiegekarte(String ID_LAGER,String WIEGEKARTE) throws myException
	{
		MyE2_MessageVector  oMV = new MyE2_MessageVector();

		
		String cQuery1 = "SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_VPOS_TPA_FUHRE<>" +this.get_ID_VPOS_TPA_FUHRE_cUF()+" AND "+
				" ((ID_ADRESSE_LAGER_START="+ID_LAGER+" AND WIEGEKARTENKENNER_LADEN="+bibALL.MakeSql(WIEGEKARTE)+") " +
				" OR "+
				" (ID_ADRESSE_LAGER_ZIEL="+ID_LAGER+" AND WIEGEKARTENKENNER_ABLADEN="+bibALL.MakeSql(WIEGEKARTE)+"))";
		
		String cQuery2 = "SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT WHERE " +
				" ID_ADRESSE_LAGER="+ID_LAGER+
				" AND WIEGEKARTENKENNER="+bibALL.MakeSql(WIEGEKARTE);
		
		String[][] cWert1 = bibDB.EinzelAbfrageInArray(cQuery1, this.get_oConn());
		String[][] cWert2 = bibDB.EinzelAbfrageInArray(cQuery2, this.get_oConn());
		
		Long oLong1 = new MyLong(cWert1[0][0]).get_oLong();
		Long oLong2 = new MyLong(cWert2[0][0]).get_oLong();
		
		if (oLong1==null || oLong2==null)
		{
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler bei der Prüfung der Eindeutigkeit der Wiegekarte (2) !")));
		}
		else
		{
			if ((oLong1.longValue()+oLong2.longValue())>0)
			{
				MyE2_String oString = new MyE2_String("Die Wiegekartennummer ist nicht eindeutig : Lager: ",true,ID_LAGER,false,": Wiegekarte: ",true,WIEGEKARTE,false,": Fuhre:",true,this.get_ID_VPOS_TPA_FUHRE_cUF(),false);
				oMV.add_MESSAGE(new MyE2_Alarm_Message(oString));
			}
		}

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
			RECORD_VPOS_TPA_FUHRE  		FUHRE = this;
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
	 * methode sammelt alle statements einer fuhre und gibt den Vector mit statetements zurueck
	 * @return
	 * @throws myException
	 */
	public VECTOR_BUCH_StatementBuilder get_vAbrechnungsStatements(boolean bNurZumTest) throws myException
	{
		VECTOR_BUCH_StatementBuilder  vRueck = new VECTOR_BUCH_StatementBuilder();
		
		if (this.is_DELETED_YES() || this.is_IST_STORNIERT_YES())
		{
			return vRueck;
		}
		
		boolean bFremdauftrag = S.isFull(this.get_ID_ADRESSE_FREMDAUFTRAG_cUF_NN(""));
		if (bibROHSTOFF.get_vEigeneLieferadressen().contains(this.get_ID_ADRESSE_FREMDAUFTRAG_cUF_NN("")))
		{
			bFremdauftrag = false;
		}

		if (bFremdauftrag)
		{
			return vRueck;			//fremdauftrag wirkt auf fuhren und orte
		}
		
		if (this.is_OHNE_ABRECHNUNG_NO())     //es kann fuhren geben, die ohne abrechnung definiert sind, aber der ort mit abrechnung
		{
			vRueck.add(this.get_StatementBuilderBuchung(true,bNurZumTest));
			vRueck.add(this.get_StatementBuilderBuchung(false,bNurZumTest));
		}
		
		for (int i=0;i<this.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre().get_vKeyValues().size();i++)
		{
			vRueck.addAll(new PRUEF_RECORD_VPOS_TPA_FUHRE_ORT(
					this.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre().get(i),false).get_vAbrechnungsStatements(bNurZumTest));
		}
		
		return vRueck;
	}


	
	
	

	
	private BUCH_StatementBuilder  get_StatementBuilderBuchung(boolean bEK, boolean bNurZumTest) throws myException
	{
		BUCH_StatementBuilder oStatmt = new BUCH_StatementBuilder(this, bEK);
		
		if (!bNurZumTest)
		{
			BigDecimal 	bdMenge = this.get_bdMengeZurVerbuchung(bEK);
			String 		cMenge = null;
			if (bdMenge != null)
			{
				cMenge = MyNumberFormater.formatDez(bdMenge, 3, false, '.', ',', true);
			}
			
			
			RECORD_ADRESSE    recLieferant = new RECORD_ADRESSE(this.get_ID_ADRESSE_START_cUF());
			RECORD_ADRESSE    recAbnehmer = new RECORD_ADRESSE(this.get_ID_ADRESSE_ZIEL_cUF());
			
			RECORD_ADRESSE    recKunde = bEK?recLieferant:recAbnehmer;
			
			oStatmt.fill_baseFieldsFromAdress(recKunde);
	
			
			oStatmt.addSQL_Paar("ID_ADRESSE",					bEK?this.get_ID_ADRESSE_START_VALUE_FOR_SQLSTATEMENT():this.get_ID_ADRESSE_ZIEL_VALUE_FOR_SQLSTATEMENT());
			oStatmt.addSQL_Paar("ID_VPOS_TPA_FUHRE_ZUGEORD",	this.get_ID_VPOS_TPA_FUHRE_VALUE_FOR_SQLSTATEMENT());
			oStatmt.addSQL_Paar("ID_VPOS_TPA_FUHRE_ORT_ZUGEORD",null);
			oStatmt.addSQL_Paar("LAGER_VORZEICHEN",				bEK?"1":"-1");

			/*
			 * geaendert am: 15.02.2010 von: martin
			 */
			oStatmt.addSQL_Paar("BESTELLNUMMER",				bEK?this.get_BESTELLNUMMER_EK_VALUE_FOR_SQLSTATEMENT():this.get_BESTELLNUMMER_VK_VALUE_FOR_SQLSTATEMENT());

			
			RECORD_ARTIKEL_BEZ recArtBEZ = bEK?this.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek():this.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_vk();
			
			oStatmt.addSQL_Paar("ID_ARTIKEL_BEZ",				recArtBEZ.get_ID_ARTIKEL_BEZ_VALUE_FOR_SQLSTATEMENT());
			oStatmt.addSQL_Paar("ID_ARTIKEL",					recArtBEZ.get_UP_RECORD_ARTIKEL_id_artikel().get_ID_ARTIKEL_VALUE_FOR_SQLSTATEMENT());
			oStatmt.addSQL_Paar("ARTBEZ1",						bEK?this.get_ARTBEZ1_EK_VALUE_FOR_SQLSTATEMENT():this.get_ARTBEZ1_VK_VALUE_FOR_SQLSTATEMENT());
			oStatmt.addSQL_Paar("ARTBEZ2",						bEK?this.get_ARTBEZ2_EK_VALUE_FOR_SQLSTATEMENT():this.get_ARTBEZ2_VK_VALUE_FOR_SQLSTATEMENT());
			oStatmt.addSQL_Paar("EINHEITKURZ",					recArtBEZ.get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_EINHEIT_id_einheit().get_EINHEITKURZ_VALUE_FOR_SQLSTATEMENT());
			oStatmt.addSQL_Paar("EINHEIT_PREIS_KURZ",			recArtBEZ.get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_EINHEIT_id_einheit_preis()==null?
																recArtBEZ.get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_EINHEIT_id_einheit().get_EINHEITKURZ_VALUE_FOR_SQLSTATEMENT():
																recArtBEZ.get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_EINHEIT_id_einheit_preis().get_EINHEITKURZ_VALUE_FOR_SQLSTATEMENT());
			oStatmt.addSQL_Paar("MENGENDIVISOR",				recArtBEZ.get_UP_RECORD_ARTIKEL_id_artikel().get_MENGENDIVISOR_VALUE_FOR_SQLSTATEMENT());
			oStatmt.addSQL_Paar("ANR1",							bEK?this.get_ANR1_EK_VALUE_FOR_SQLSTATEMENT():this.get_ANR1_VK_VALUE_FOR_SQLSTATEMENT());
			oStatmt.addSQL_Paar("ANR2",							bEK?this.get_ANR2_EK_VALUE_FOR_SQLSTATEMENT():this.get_ANR2_VK_VALUE_FOR_SQLSTATEMENT());
			oStatmt.addSQL_Paar("ZOLLTARIFNR",					this.get_ZOLLTARIFNR_VALUE_FOR_SQLSTATEMENT());
			
			oStatmt.addSQL_Paar("ANZAHL",						cMenge);
			
			oStatmt.addSQL_Paar("EINZELPREIS",					bEK?this.get_EINZELPREIS_EK_VALUE_FOR_SQLSTATEMENT():this.get_EINZELPREIS_VK_VALUE_FOR_SQLSTATEMENT());
			oStatmt.addSQL_Paar("STEUERSATZ",					bEK?this.get_STEUERSATZ_EK_VALUE_FOR_SQLSTATEMENT():this.get_STEUERSATZ_VK_VALUE_FOR_SQLSTATEMENT());
			
			
			//lager-vermerk und Leer-Vermerk werden nicht in positionen geschrieben
			String cHelpString = (bEK?this.get_EU_STEUER_VERMERK_EK_cUF_NN(""):this.get_EU_STEUER_VERMERK_VK_cUF_NN("")).trim();
			if (cHelpString.equals(FU___CONST.EU_STEUERVERMERK_LAGER) || cHelpString.equals(FU___CONST.EU_STEUERVERMERK_LEER))
			{
				cHelpString = null;
			}
			else
			{
				cHelpString = bEK?this.get_EU_STEUER_VERMERK_EK_VALUE_FOR_SQLSTATEMENT():this.get_EU_STEUER_VERMERK_VK_VALUE_FOR_SQLSTATEMENT();
			}
			oStatmt.addSQL_Paar("EU_STEUER_VERMERK",			cHelpString);
//
			
			//2014-10-28: id_tax mit uebergeben
			//lager-vermerk und Leer-Vermerk werden nicht in positionen geschrieben
			String cID_TAX = (bEK?this.get_ID_TAX_EK_cUF_NN(""):this.get_ID_TAX_VK_cUF_NN("")).trim();
			oStatmt.addSQL_Paar(_DB.VPOS_RG$ID_TAX,	cID_TAX);
			//2014-10-28: id_tax mit uebergeben
			
			
			
//			
			oStatmt.addSQL_Paar("EUNOTIZ",						this.get_EUNOTIZ_VALUE_FOR_SQLSTATEMENT());
			oStatmt.addSQL_Paar("EUCODE",						this.get_EUCODE_VALUE_FOR_SQLSTATEMENT());
			oStatmt.addSQL_Paar("WIEGEKARTENKENNER",			bEK?this.get_WIEGEKARTENKENNER_LADEN_VALUE_FOR_SQLSTATEMENT():this.get_WIEGEKARTENKENNER_ABLADEN_VALUE_FOR_SQLSTATEMENT());
			
			
			//2011-03-10: leistungsdatum und im verkauf ist das ladedatum
			//beim EK ist das leistungsdatum das ladedatum
			String cAusfuehrungsdatum = this.get_DATUM_AUFLADEN_VALUE_FOR_SQLSTATEMENT();

			
			if (!bEK)
			{
				cAusfuehrungsdatum = this.get_DATUM_ABLADEN_VALUE_FOR_SQLSTATEMENT();         //es koennte immer noch sein, dass kein ladedatum vorhanden ist 
																							  //ab V 2.33 sollte dies nicht mehr vorkommen
				
//				// dann das kleinste moegliche ladedatum suchen
//				Vector<String> vLadeDaten = new Vector<String>();
//				if (S.isFull(this.get_DATUM_AUFLADEN_cUF_NN("")))
//				{
//					vLadeDaten.add(this.get_DATUM_AUFLADEN_VALUE_FOR_SQLSTATEMENT());
//				}
//				
//				RECLIST_VPOS_TPA_FUHRE_ORT recListFuhrenOrte = 
//					new RECLIST_VPOS_TPA_FUHRE_ORT("SELECT * FROM JT_VPOS_TPA_FUHRE_ORT " +
//							" WHERE NVL(JT_VPOS_TPA_FUHRE_ORT.DELETED,'N')='N' " +
//							" AND NVL(JT_VPOS_TPA_FUHRE_ORT.DEF_QUELLE_ZIEL,'XX')='EK'" +
//							" AND JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF());
//				
//				for (int i=0;i<recListFuhrenOrte.get_vKeyValues().size();i++)
//				{
//					if (S.isFull(recListFuhrenOrte.get(i).get_DATUM_LADE_ABLADE_cUF_NN("")))
//					{
//						vLadeDaten.add(recListFuhrenOrte.get(i).get_DATUM_LADE_ABLADE_VALUE_FOR_SQLSTATEMENT());
//					}
//				}

				//2011-03-18: vorbereiten fuer die finale version: ueber die lieferbedingungen
				boolean bVK_bekommt_ladedatum = false;
				
				if (bVK_bekommt_ladedatum)
				{
					Vector<String>  vLadeDaten = this.get_vAlleBeteiligtenLadeDaten_SQL_NOTATION_NOT_NULL_SORTED();
				
					if (vLadeDaten.size()>0)                  //ab V 2.33 sollte dies immer der fall sein
					{
						cAusfuehrungsdatum = vLadeDaten.get(0);
					}
				}
			}
			
			
			//oStatmt.addSQL_Paar("AUSFUEHRUNGSDATUM",			bEK?this.get_DATUM_AUFLADEN_VALUE_FOR_SQLSTATEMENT():this.get_DATUM_ABLADEN_VALUE_FOR_SQLSTATEMENT());
			oStatmt.addSQL_Paar("AUSFUEHRUNGSDATUM",cAusfuehrungsdatum);
			//2011-03-10: leistungsdatum und im verkauf ist das ladedatum
			
			
			
			String cStrecke = "Y";
			if (recLieferant.get_ID_ADRESSE_lValue(null).longValue()==bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_lValue(null) ||
				recAbnehmer.get_ID_ADRESSE_lValue(null).longValue()==bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_lValue(null))
			{
				cStrecke = "N";
			}
			oStatmt.addSQL_Paar("STRECKENGESCHAEFT",			cStrecke,true);
			
//			//falls der kunde nur eine MWST hat, diese auf jeden fall vorschlagen
//			if (bibALL.get_RECORD_MANDANT().is_AUTOMATIK_BUCHUNGSMWST_YES())
//			{
//				if (recKunde.get_DOWN_RECORD_LIST_KUNDE_MWST_id_adresse().get_vKeyValues().size()==1)
//				{
//					oStatmt.addSQL_Paar("STEUERSATZ",	recKunde.get_DOWN_RECORD_LIST_KUNDE_MWST_id_adresse().get(0).get_UP_RECORD_MWSTSCHLUESSEL_id_mwstschluessel().get_STEUERSATZ_VALUE_FOR_SQLSTATEMENT());
//				}
//			}
			
			
			//jetzt die kontrakt-info
			RECORD_VPOS_KON  recKON = bEK?this.get_UP_RECORD_VPOS_KON_id_vpos_kon_ek():this.get_UP_RECORD_VPOS_KON_id_vpos_kon_vk();
			
			//2012-05-22: zusaetzlich auch zahlungsbedingungen aus angeboten holen
			RECORD_VPOS_STD  recAngebot = bEK?this.get_UP_RECORD_VPOS_STD_id_vpos_std_ek():this.get_UP_RECORD_VPOS_STD_id_vpos_std_vk();
			
			
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

				//throw new myExceptionForUser(new MyE2_String("Bei der Abrechnung ist ein Kontrakt im Spiel, bei dem eines der Felder: Einheit/EinheitPreis/Mengendivisor leer ist ! Dies ist verboten!"));
			}
			else if (recAngebot!=null)
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
			if (bEK) {
				if (S.isFull(this.get_LIEFERBED_ALTERNATIV_EK_cUF_NN(""))) {
					oStatmt.addSQL_Paar("LIEFERBEDINGUNGEN",		this.get_LIEFERBED_ALTERNATIV_EK_VALUE_FOR_SQLSTATEMENT());
				}
			} else {
				if (S.isFull(this.get_LIEFERBED_ALTERNATIV_VK_cUF_NN(""))) {
					oStatmt.addSQL_Paar("LIEFERBEDINGUNGEN",		this.get_LIEFERBED_ALTERNATIV_VK_VALUE_FOR_SQLSTATEMENT());
				}
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

		vRueck.add(this.get_DATUM_AUFLADEN_VALUE_FOR_SQLSTATEMENT());
		
		RECLIST_VPOS_TPA_FUHRE_ORT recListFuhrenOrte = 
			new RECLIST_VPOS_TPA_FUHRE_ORT("SELECT * FROM JT_VPOS_TPA_FUHRE_ORT " +
					" WHERE NVL(JT_VPOS_TPA_FUHRE_ORT.DELETED,'N')='N' " +
					" AND NVL(JT_VPOS_TPA_FUHRE_ORT.DEF_QUELLE_ZIEL,'XX')='EK'" +
					" AND JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF());
		
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
	
	
	
	
	public BigDecimal get_bdMengeGutschrift() throws myException
	{
		if (this.is_LADEMENGE_GUTSCHRIFT_YES())
		{
			return this.get_ANTEIL_LADEMENGE_LIEF_bdValue(null,3);
		}
		else
		{
			return this.get_ANTEIL_ABLADEMENGE_LIEF_bdValue(null,3);
		}
	}
	

	public BigDecimal get_bdMengeRechnung() throws myException
	{
		if (this.is_ABLADEMENGE_RECHNUNG_YES())
		{
			return  this.get_ANTEIL_ABLADEMENGE_ABN_bdValue(null,3);
		}
		else
		{
			return this.get_ANTEIL_LADEMENGE_ABN_bdValue(null,3);
		}
	}


	
	public BigDecimal get_bdMengeZurVerbuchung(boolean bEK) throws myException 
	{

		BigDecimal dMenge = null;
		
		if (bEK)
		{
			dMenge = this.get_ANTEIL_LADEMENGE_LIEF_bdValue(null, 3);
			if (this.is_LADEMENGE_GUTSCHRIFT_NO())
			{
				dMenge = this.get_ANTEIL_ABLADEMENGE_LIEF_bdValue(null, 3);
			}
		}
		else
		{
			dMenge = this.get_ANTEIL_ABLADEMENGE_ABN_bdValue(null, 3);
			if (this.is_ABLADEMENGE_RECHNUNG_NO())
			{
				dMenge = this.get_ANTEIL_LADEMENGE_ABN_bdValue(null, 3);
			}
		}
		
		return dMenge;
	}

	
	
	
	/**
	 * 
	 * @return s Array aus 3 BgDecimal-werten: EK-Seite Summe / VK-Seite Summe / Verhaeltnis
	 * @throws myException
	 */
	public BigDecimal[] get_MengenVorAbrechnung() throws myException
	{
		
		BigDecimal[] dRueck = new BigDecimal[3];
		dRueck[0] = new BigDecimal(0);
		dRueck[1] = new BigDecimal(0);
		dRueck[2] = new BigDecimal(0);
		
		RECLIST_VPOS_TPA_FUHRE_ORT recListFU_ORT_EK = this.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre("NVL(DELETED,'N')='N' AND DEF_QUELLE_ZIEL='EK'", null, true);
		RECLIST_VPOS_TPA_FUHRE_ORT recListFU_ORT_VK = this.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre("NVL(DELETED,'N')='N' AND DEF_QUELLE_ZIEL='VK'", null, true);

		//zuerst EK-Seite:
		dRueck[0]=dRueck[0].add(this.get_ANTEIL_LADEMENGE_LIEF_bdValue(new BigDecimal(0), 3));
		dRueck[0]=dRueck[0].subtract(this.get_ABZUG_LADEMENGE_LIEF_bdValue(new BigDecimal(0), 3));
		
		//dann VK-Seite:
		dRueck[1]=dRueck[1].add(this.get_ANTEIL_ABLADEMENGE_ABN_bdValue(new BigDecimal(0), 3));
		dRueck[1]=dRueck[1].subtract(this.get_ABZUG_ABLADEMENGE_ABN_bdValue(new BigDecimal(0), 3));
		


		//jetzt die zusatzorte-EK
		for (int i=0;i<recListFU_ORT_EK.size();i++)
		{
			RECORD_VPOS_TPA_FUHRE_ORT  recOrt = recListFU_ORT_EK.get(i);

			dRueck[0]=dRueck[0].add(recOrt.get_ANTEIL_LADEMENGE_bdValue(new BigDecimal(0), 3));
			dRueck[0]=dRueck[0].subtract(recOrt.get_ABZUG_MENGE_bdValue(new BigDecimal(0), 3));
			
		}
		
		//dann die zusatzorte-VK
		for (int i=0;i<recListFU_ORT_VK.size();i++)
		{
			RECORD_VPOS_TPA_FUHRE_ORT  recOrt = recListFU_ORT_VK.get(i);

			dRueck[1]=dRueck[1].add(recOrt.get_ANTEIL_ABLADEMENGE_bdValue(new BigDecimal(0), 3));
			dRueck[1]=dRueck[1].subtract(recOrt.get_ABZUG_MENGE_bdValue(new BigDecimal(0), 3));
		}
	
		if (dRueck[0].compareTo(new BigDecimal(0))!=0 && dRueck[1].compareTo(new BigDecimal(0))!=0)
		{
			BigDecimal bdRelation = 	dRueck[0].compareTo(dRueck[1])>0?
										dRueck[1].divide(dRueck[0],3,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)):
										dRueck[0].divide(dRueck[1],3,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
			dRueck[2] = new BigDecimal(100).subtract(bdRelation);
		}
		
		return dRueck;
		
	}

	

	
	
	
	public boolean IS_STATUS_GANZGEBUCHT() throws myException
	{
		return (this.get_STATUS_BUCHUNG_lValue(new Long(-10))==myCONST.STATUS_FUHRE__GANZGEBUCHT);
	}

	public boolean IS_STATUS_TEILSGEBUCHT() throws myException
	{
		return (this.get_STATUS_BUCHUNG_lValue(new Long(-10))==myCONST.STATUS_FUHRE__TEILSGEBUCHT);
	}

	public boolean IS_STATUS_UNGEBUCHT() throws myException
	{
		return (this.get_STATUS_BUCHUNG_lValue(new Long(-10))==myCONST.STATUS_FUHRE__UNGEBUCHT);
	}

	public boolean IS_STATUS_BESITZT_KEINE_BUCHUNGSPOS() throws myException
	{
		return (this.get_STATUS_BUCHUNG_lValue(new Long(-10))==myCONST.STATUS_FUHRE__BESITZT_KEINE_BUCHUNGSPOS);
	}

	public boolean IS_STATUS_EINGABE_IST_NOCH_NICHT_FERTIG() throws myException
	{
		return (this.get_STATUS_BUCHUNG_lValue(new Long(-10))==myCONST.STATUS_FUHRE__EINGABE_IST_NOCH_NICHT_FERTIG);
	}

	public boolean IS_STATUS_IST_ALT_WID_NICHT_BEGUCHT() throws myException
	{
		return (this.get_STATUS_BUCHUNG_lValue(new Long(-10))==myCONST.STATUS_FUHRE__IST_ALT_WID_NICHT_BEGUCHT);
	}

	public boolean IS_STATUS_IST_STORNIERT() throws myException
	{
		return (this.get_STATUS_BUCHUNG_lValue(new Long(-10))==myCONST.STATUS_FUHRE__IST_STORNIERT);
	}

	
	
	
	
	@Override
	public String get_ID() throws myException
	{
		return this.get_ID_VPOS_TPA_FUHRE_cUF();
	}


	
	
	
	
	
	/*
	 * 2012-04-11: pruefung, ob die Fuhre einen Preis aus dem Vormonat hat
	 */
	public Vector<MyE2_String> get_PruefListe_Angebot_korreliert_mit_Leistungsdatum() throws myException
	{
		Vector<MyE2_String> vRueck = new Vector<MyE2_String>();
		
		RECORD_VPOS_STD  recSTD_EK = this.get_UP_RECORD_VPOS_STD_id_vpos_std_ek();
		RECORD_VPOS_STD  recSTD_VK = this.get_UP_RECORD_VPOS_STD_id_vpos_std_vk();
		
		String           cLeistungsdatEK = this.get_DATUM_AUFLADEN_cF_NN("");
		String           cLeistungsdatVK = this.get_DATUM_ABLADEN_cF_NN("");
		
		Vector<ownDatumsChecker>  vCheckliste = new Vector<PRUEF_RECORD_VPOS_TPA_FUHRE.ownDatumsChecker>();
		
		//nur die saetze pruefen, die ein datum haben und ein angebot
		if (recSTD_EK!=null && S.isFull(cLeistungsdatEK))
		{
			vCheckliste.add(new ownDatumsChecker(recSTD_EK, cLeistungsdatEK,"EK",true));
		}
		if (recSTD_VK!=null && S.isFull(cLeistungsdatVK))
		{
			vCheckliste.add(new ownDatumsChecker(recSTD_VK, cLeistungsdatVK,"VK",true));
		}
		
		RECLIST_VPOS_TPA_FUHRE_ORT  recListOrte = this.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre();
		
		for (RECORD_VPOS_TPA_FUHRE_ORT  recOrt: recListOrte.values())
		{
			if (recOrt.is_DELETED_NO() && recOrt.is_OHNE_ABRECHNUNG_NO())
			{
				RECORD_VPOS_STD  recSTD_FO = recOrt.get_UP_RECORD_VPOS_STD_id_vpos_std();
				String           cLeistungsdat = recOrt.get_DATUM_LADE_ABLADE_cF_NN("");
				if (recSTD_FO!=null && S.isFull(cLeistungsdat))
				{
					vCheckliste.add(new ownDatumsChecker(recSTD_FO, cLeistungsdat,recOrt.get_DEF_QUELLE_ZIEL_cUF(),false));
				}
			}
		}
		
		for (ownDatumsChecker ownChecker: vCheckliste)
		{
			MyE2_String cMeldung = ownChecker.get_Meldung();
			if (cMeldung != null)
			{
				vRueck.add(cMeldung);
			}
		}
		
		
		
		
		return vRueck;
		
	}
	
	
	private class ownDatumsChecker
	{
		private RECORD_VPOS_STD  recVpos =					null;
		private String           cLeistungsDatumFormated = 	null;
		private String           cDefQuelleZiel = 			null;
		private boolean          bHauptFuhre = true;
		
		public ownDatumsChecker(RECORD_VPOS_STD RecVpos, String LeistungsDatumFormated, String DefQuelleZiel, boolean HauptFuhre)
		{
			super();
			this.recVpos = RecVpos;
			this.cLeistungsDatumFormated = LeistungsDatumFormated;
			this.cDefQuelleZiel = DefQuelleZiel;
			this.bHauptFuhre = HauptFuhre;
		}
		
		//fehler ist dann, wenn es eine meldung gibt
		public MyE2_String get_Meldung() throws myException
		{
			MyE2_String cMeldung = null;
			
			
			if (!myDateHelper.get_bDatumsBereicheHabenSchnittmengen(
					this.recVpos.get_DOWN_RECORD_LIST_VPOS_STD_ANGEBOT_id_vpos_std().get(0).get_GUELTIG_VON_cF_NN(""),
					this.recVpos.get_DOWN_RECORD_LIST_VPOS_STD_ANGEBOT_id_vpos_std().get(0).get_GUELTIG_BIS_cF_NN(""),
					this.cLeistungsDatumFormated,
					this.cLeistungsDatumFormated))
			{
				cMeldung = new MyE2_String("Leistungsdatum liegt nicht im Angebotszeitraum !!! // ", true, 
											this.cDefQuelleZiel+"-Seite // ", true, 
											this.bHauptFuhre?" (Hauptfuhre) ":" (Zusatzort) ", true, 
											this.recVpos.get_UP_RECORD_VKOPF_STD_id_vkopf_std().get___KETTE(bibVECTOR.get_Vector("NAME1","ORT")), false);
			}
			
			
			return cMeldung;
			
		}
		
		
	}
	
	
	
	
	//2012-05-07: fuhrenpruefung, sortenvergleich: fuhrensorte zur wiegesorte
	public Vector<MyE2_String> get_PruefListe_WiegescheinSorteGleichFuhrenSorte() throws myException
	{
		//sammler fuer die meldungen
		Vector<MyE2_String> vRueck = new Vector<MyE2_String>();

		//Vector mit ID_ADRESSE@ANR1 Wareneingangswiegescheine dieser fuhre
		VectorSingle        vANR1_WE_Wiegescheine = new VectorSingle();
		
		//Vector mit ID_ADRESSE@ANR1 Warenausgangswiegescheine dieser fuhre
		VectorSingle        vANR1_WA_Wiegescheine = new VectorSingle();
		
		//Vector mit ID_ADRESSE@ANR1 aller gutschriftssorten dieser fuhre
		VectorSingle        vANR1_GS_Positionen = new VectorSingle();
		
		//Vector mit ID_ADRESSE@ANR1 aller rechnungssorten dieser fuhre
		VectorSingle        vANR1_RE_Positionen = new VectorSingle();
		
		
		//alle wiegescheine holen, die zu einer fuhre oder zuehoerigem ort gehoeren
		RECLIST_WIEGEKARTE  oRecListWiegeKarte = new RECLIST_WIEGEKARTE("SELECT * FROM "+bibE2.cTO()+".JT_WIEGEKARTE WHERE ID_VPOS_TPA_FUHRE="+this.get_ID_VPOS_TPA_FUHRE_cUF_NN(""));
		
		
		for (RECORD_WIEGEKARTE recWK: oRecListWiegeKarte.values())
		{
			if (recWK.is_STORNO_NO())
			{
				/*
				 * 2014-12-02: Sachverhalt: Es wurde eine Fuhre gebucht, die in den Wiegekarten vermerkt war, dort war aber keine Adresse_Lieferant angegeben,
				 *             d.h. die Fuhre wurde in der Wiegung geladen aber es wurde danach die lieferantenadresse geaendert !! diese ist nicht erlaubt und wird 
				 *             ab jetzt mit einer Meldung angezeigt !!! 
				 */
				if (S.isEmpty(recWK.get_ID_ADRESSE_LIEFERANT_cUF_NN(""))) {
					vRueck.add(new MyE2_String("Zu dieser Fuhre existiert ein Wiegeschein, aber im Wiegeschein ist die Adresse nicht gesetzt ! Bitte korrigieren !!",true,"ID-WK:"+recWK.get_ID_WIEGEKARTE_cUF_NN(""),false));
				} else {
					if (recWK.is_IST_LIEFERANT_YES())
					{
						this._ADD_SORTE_TO_Vector(recWK.get_ID_ARTIKEL_BEZ_cUF_NN(""), recWK.get_ID_ADRESSE_LIEFERANT_cUF_NN(""), vANR1_WE_Wiegescheine);
					}
					else
					{
						this._ADD_SORTE_TO_Vector(recWK.get_ID_ARTIKEL_BEZ_cUF_NN(""), recWK.get_ID_ADRESSE_LIEFERANT_cUF_NN(""), vANR1_WA_Wiegescheine);
					}
				}
			}
		}
		
		
		//jetzt die fuhre pruefen und alle abrechenbaren (=fremd-adress-id-sorten sammeln)
		if (S.isFull(this.get_ID_ADRESSE_START_cUF_NN("")) && bibALL.get_bIstFremdAdresse(this.get_ID_ADRESSE_START_cUF_NN("")))
		{
			this._ADD_SORTE_TO_Vector(this.get_ID_ARTIKEL_BEZ_EK_cUF_NN(""), this.get_ID_ADRESSE_START_cUF_NN(""), vANR1_GS_Positionen);
		}
		if (S.isFull(this.get_ID_ADRESSE_ZIEL_cUF_NN("")) && bibALL.get_bIstFremdAdresse(this.get_ID_ADRESSE_ZIEL_cUF_NN("")))
		{
			this._ADD_SORTE_TO_Vector(this.get_ID_ARTIKEL_BEZ_VK_cUF_NN(""), this.get_ID_ADRESSE_ZIEL_cUF_NN(""), vANR1_RE_Positionen);
		}
		
		RECLIST_VPOS_TPA_FUHRE_ORT  rlFO = this.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre();
		
		for (RECORD_VPOS_TPA_FUHRE_ORT FO: rlFO.values())
		{
			if (FO.is_DELETED_NO() && S.isFull(FO.get_ID_ADRESSE_cUF_NN("")) && bibALL.get_bIstFremdAdresse(FO.get_ID_ADRESSE_cUF_NN("")))
			{
				if (FO.get_DEF_QUELLE_ZIEL_cUF_NN("").equals("EK"))
				{
					this._ADD_SORTE_TO_Vector(FO.get_ID_ARTIKEL_BEZ_cUF_NN(""), FO.get_ID_ADRESSE_cUF_NN(""),vANR1_GS_Positionen);
				}
				else
				{
					this._ADD_SORTE_TO_Vector(FO.get_ID_ARTIKEL_BEZ_cUF_NN(""), FO.get_ID_ADRESSE_cUF_NN(""), vANR1_RE_Positionen);
				}
			}
		}
		
		// Stimmigkeit, wenn jede wiegeschein-kombi im vANR1_WE_Wiegescheine eine entsprechung im  vANR1_GS_Positionen bzw.
		//                                             vANR1_WA_Wiegescheine eine im vANR1_RE_Positionen hat                         
		for (String cAdressSorteKombi: vANR1_WE_Wiegescheine)
		{
			if (!vANR1_GS_Positionen.contains(cAdressSorteKombi))
			{
				String cSorte=cAdressSorteKombi.substring(cAdressSorteKombi.indexOf("@")+1);
				vRueck.add(new MyE2_String("Eingangs-Wiegeschein-Sorte ",true,cSorte,false," findet sich nicht in den Gutschriftspositionen (oder Firma falsch) - bitte PRÜFEN !!",true));
			}
		}
		for (String cAdressSorteKombi: vANR1_WA_Wiegescheine)
		{
			if (!vANR1_RE_Positionen.contains(cAdressSorteKombi))
			{
				String cSorte=cAdressSorteKombi.substring(cAdressSorteKombi.indexOf("@")+1);
				vRueck.add(new MyE2_String("Ausgangs-Wiegeschein-Sorte ",true,cSorte,false," findet sich nicht in den Rechnungspositionen (oder Firma falsch) - bitte PRÜFEN !!",true));
			}
		}
		
		return vRueck;
	}

	
	private void _ADD_SORTE_TO_Vector(String cID_ARTKELBEZ, String cID_ADRESSE_UF, VectorSingle vSorten) throws myException
	{
		//2012-06-11: fix eines fehlers: im wiegeschein sind lager-ids, die muessen zuerst umgesetzt werden in hauptadressen
		RECORD_ADRESSE  recAdresse = new RECORD_ADRESSE(cID_ADRESSE_UF);
		
		if (S.isFull(cID_ARTKELBEZ) && S.isFull(cID_ADRESSE_UF))
		{
			String cID_ADRESSE_FIRMA = cID_ADRESSE_UF;
			
			if (recAdresse.get_ADRESSTYP_lValue(-1l).intValue()==myCONST.ADRESSTYP_LIEFERADRESSE)
			{
				RECORD_ADRESSE recFirma = recAdresse.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer().get(0).get_UP_RECORD_ADRESSE_id_adresse_basis();
				cID_ADRESSE_FIRMA = recFirma.get_ID_ADRESSE_cUF();
			}

			vSorten.add(S.NN(cID_ADRESSE_FIRMA)+"@"+new RECORD_ARTIKEL_BEZ(cID_ARTKELBEZ).get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN(""));
		}
	}
	
	
	/*
	 * 2012-08-02: fuer pruefung auf loescherlaubnis bei fuhren aus tpa
	 */
	public MyE2_MessageVector Check_if_can_be_deleted()
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		try
		{
			if (this.is_DELETED_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Eine Fuhre wurde bereits gelöscht !")));
			}
			else if (this.__Actual_StatusBuchung()==myCONST.STATUS_FUHRE__GANZGEBUCHT)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Löschen verboten! Es liegen (bei einer Fuhre) schon alle Rechnungs- und/oder Gutschriftsbuchungen vor !")));
			}
			else if (this.__Actual_StatusBuchung()==myCONST.STATUS_FUHRE__TEILSGEBUCHT)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Löschen verboten! Es liegen  (bei einer Fuhre) schon Teile der Rechnungs- und/oder Gutschriftsbuchungen vor !")));
			}
			else if (this.get_bHasMengenEintraege())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Löschen verboten! Es wurden  (bei einer Fuhre) bereits Buchungsmengen eingetragen !")));
			}
			else if (this.get_bHasWiegekarte())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Mindestens eine Fuhre hat bereits eine zugeordnete Wiegekarte")));
			}
			else if (!this.get_DAT_FAHRPLAN_FP_cUF_NN("").equals(""))
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Mindestens eine Fuhre ist eine Fahrplan-Position und wurde bereits einem Fahrplan zugeordnet!")));
			}

			RECLIST_VPOS_TPA_FUHRE_ORT  reclistOrt = this.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre(
					"NVL("+RECORD_VPOS_TPA_FUHRE_ORT.FIELD__DELETED+",'N')='N'",null,true);
			
			if (reclistOrt.get_vKeyValues().size()>0)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Vor dem Löschen müssen zuerst alle Fuhren-Zusatzorte gelöscht werden !")));
			}
			
		}
		catch (myException ex)
		{
			oMV.add_MESSAGE(ex.get_ErrorMessage());
		}
		
		return oMV;
	}
	
	
}
