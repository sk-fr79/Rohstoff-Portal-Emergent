package rohstoff.Echo2BusinessLogic.__SPECIALRECORDS;

import java.util.Map;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_BASIC_WarningMessageWithAddonComponent;
import panter.gmbh.Echo2.components.E2_InfoButton;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON_LAGER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;
import rohstoff.utils.SQL_DAEMONS.IF_PRUEF_Interface;
import rohstoff.utils.SQL_DAEMONS.VectorSingle_PRUEF_Interface;

public class PRUEF_RECORD_VPOS_KON_LAGER extends RECORD_VPOS_KON_LAGER  implements IF_PRUEF_Interface
{
	
	private boolean bGeprueft = false;
	private boolean bPrimary = false;

	//der tabellenname im pruefDaemon, der diese klasse initiert
	private String         TABLENAME_CHANGED = null;


	public PRUEF_RECORD_VPOS_KON_LAGER(String  cIdUnformated, MyConnection Conn, boolean Primary, String cTablename_changed) throws myException
	{
		super(cIdUnformated, Conn);
		this.TABLENAME_CHANGED = cTablename_changed;
		this.bPrimary = Primary;
	}
	
	
	
	
//	
//	
//	public PRUEF_RECORD_VPOS_KON_LAGER(RECORD_VPOS_KON_LAGER recordOrig, boolean Primary)
//	{
//		super(recordOrig);
//		this.bPrimary = Primary;
//	}
//

	
	public PRUEF_RECORD_VPOS_KON_LAGER(RECORD_VPOS_KON_LAGER recordOrig, boolean Primary, String cTablename_changed)
	{
		super(recordOrig);
		this.TABLENAME_CHANGED = cTablename_changed;
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
		if (oVGL instanceof PRUEF_RECORD_VPOS_KON_LAGER)
		{
			try
			{
				if  (   ((PRUEF_RECORD_VPOS_KON_LAGER)oVGL).get_ID_VPOS_KON_LAGER_cUF().equals(this.get_ID_VPOS_KON_LAGER_cUF()))
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
		
		String cID_VPOS_KON = 		this.get_ID_VPOS_KON_cUF();
		String cID_ADRESSE_LAGER = 	this.get_ID_ADRESSE_LAGER_cUF();
		
		
		//alle fuhren, die direkt diese kombination lager-kontrakt tangieren (direkt, in der hauptzuordnung)
		RECLIST_VPOS_TPA_FUHRE oListFuhren = new RECLIST_VPOS_TPA_FUHRE("SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE NVL(DELETED,'N')='N' AND " +
																	"ID_VPOS_KON_EK="+cID_VPOS_KON+" AND "+
																	"ID_VPOS_KON_VK IS NULL AND ID_ADRESSE_LAGER_ZIEL="+cID_ADRESSE_LAGER+" AND " +
																	"ID_ADRESSE_ZIEL="+bibALL.get_ID_ADRESS_MANDANT(),this.get_oConn());
		
		//umgedreht
		oListFuhren.putAll(new RECLIST_VPOS_TPA_FUHRE("SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE NVL(DELETED,'N')='N' AND " +
													"ID_VPOS_KON_VK="+cID_VPOS_KON+" AND "+
													"ID_VPOS_KON_EK IS NULL AND ID_ADRESSE_LAGER_START="+cID_ADRESSE_LAGER+" AND " +
													"ID_ADRESSE_START="+bibALL.get_ID_ADRESS_MANDANT(),this.get_oConn()),false);
		

		
		
		//jetzt alle fuhren dazuholen, die in einem ladeort / abladeort einen der kontrakte der zuordnung enthalten
		oListFuhren.putAll( new RECLIST_VPOS_TPA_FUHRE("SELECT DISTINCT JT_VPOS_TPA_FUHRE.* FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT " +
																	" INNER JOIN " +
																	bibE2.cTO()+".JT_VPOS_TPA_FUHRE " +
																	"  ON " +
																	" ("+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_TPA_FUHRE = "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE)" +
																	" WHERE  NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='N' AND NVL(JT_VPOS_TPA_FUHRE_ORT.DELETED,'N')='N' AND" +
																	" JT_VPOS_TPA_FUHRE.ID_VPOS_KON_EK="+cID_VPOS_KON+" AND  " +
																	"JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_KON IS NULL AND JT_VPOS_TPA_FUHRE_ORT.ID_ADRESSE_LAGER="+cID_ADRESSE_LAGER+
																	" AND  JT_VPOS_TPA_FUHRE_ORT.ID_ADRESSE="+bibALL.get_ID_ADRESS_MANDANT(),this.get_oConn()),true);

		
		//jetzt alle fuhren dazuholen, die in einem ladeort / abladeort einen der kontrakte der zuordnung enthalten
		oListFuhren.putAll( new RECLIST_VPOS_TPA_FUHRE("SELECT DISTINCT JT_VPOS_TPA_FUHRE.* FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT " +
																	" INNER JOIN " +
																	bibE2.cTO()+".JT_VPOS_TPA_FUHRE " +
																	"  ON " +
																	" ("+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_TPA_FUHRE = "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE)" +
																	" WHERE  NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='N' AND NVL(JT_VPOS_TPA_FUHRE_ORT.DELETED,'N')='N' AND" +
																	" JT_VPOS_TPA_FUHRE.ID_VPOS_KON_VK="+cID_VPOS_KON+" AND  " +
																	" JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_KON IS NULL AND JT_VPOS_TPA_FUHRE_ORT.ID_ADRESSE_LAGER="+cID_ADRESSE_LAGER+
																	" AND  JT_VPOS_TPA_FUHRE_ORT.ID_ADRESSE="+bibALL.get_ID_ADRESS_MANDANT(),this.get_oConn()),true);


		//jetzt die menge der zuordnung ermitteln
		double dSummeAllerMengenDieserLagerZuordnung = 0;

		
		//jetzt alle fuhren, die beteiligt sind, anschauen
		for (Map.Entry<String, RECORD_VPOS_TPA_FUHRE> fuhreEntry: oListFuhren.entrySet())
		{
			//jetzt pruefen, ob eine fuhre links- und rechtsseitig zusatzorte hat, d.h. diese zuordnungen koennen nicht geprueft werden 
			RECORD_VPOS_TPA_FUHRE oFuhre = fuhreEntry.getValue();
			pruefHelferFuhreOrteAnzahl_EK_VK_Seite oAnzahlCheck = new pruefHelferFuhreOrteAnzahl_EK_VK_Seite();
			oFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre().DoAnyThing(oAnzahlCheck);

			
			/*
			 * es gibt 4 fuhrentypen, die bezueglich zuordnungen unterschiedlich zu werten sind:
			 * 1. 1 zu 1  -fuhren (keine zusatzorte), zuordnungsmenge ist mittelwert zwischen lade- und ablademenge
			 * 2. N zu 1  -fuhre  (zusaetzliche ladeorte)
			 * 3. 1 zu N  -fuhren (zusaetzliche abladeorte)
			 * 4. N zu N  -fuhren (nicht bewertbar - Warnmeldung)
			 */ 
			if (oAnzahlCheck.iCountEK==0 && oAnzahlCheck.iCountVK==0)
			{
				if ( (oFuhre.get_ID_VPOS_KON_EK_cUF_NN("").equals(cID_VPOS_KON) && oFuhre.get_ID_ADRESSE_LAGER_ZIEL_cUF_NN("").equals(cID_ADRESSE_LAGER)) ||
					 (oFuhre.get_ID_VPOS_KON_VK_cUF_NN("").equals(cID_VPOS_KON) && oFuhre.get_ID_ADRESSE_LAGER_START_cUF_NN("").equals(cID_ADRESSE_LAGER)) )		
				{
					double dLadeMenge = oFuhre.get_ANTEIL_LADEMENGE_LIEF_dValue(oFuhre.get_ANTEIL_PLANMENGE_LIEF_dValue(new Double(0))).doubleValue();
					double dAbLadeMenge = oFuhre.get_ANTEIL_ABLADEMENGE_LIEF_dValue(oFuhre.get_ANTEIL_PLANMENGE_LIEF_dValue(new Double(0))).doubleValue();
					dSummeAllerMengenDieserLagerZuordnung += ((dLadeMenge+dAbLadeMenge)/2);
				}
					
			}
			else if (oAnzahlCheck.iCountEK>0 && oAnzahlCheck.iCountVK==0)
			{
				//dann muss der VK-kontrakt der aus der zuordnung sein
				if (oFuhre.get_ID_VPOS_KON_VK_cUF_NN("").equals(cID_VPOS_KON))
				{
					for (Map.Entry<String, RECORD_VPOS_TPA_FUHRE_ORT> ortEntry: oFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre().entrySet())
					{
						RECORD_VPOS_TPA_FUHRE_ORT ort = ortEntry.getValue();
						if (ort.get_ID_ADRESSE_LAGER_cUF_NN("").equals(cID_ADRESSE_LAGER) && ort.is_DELETED_NO())
						{
							double dLadeMenge = ort.get_ANTEIL_LADEMENGE_dValue(ort.get_ANTEIL_PLANMENGE_dValue(new Double(0))).doubleValue();
							double dAbLadeMenge = ort.get_ANTEIL_ABLADEMENGE_dValue(ort.get_ANTEIL_PLANMENGE_dValue(new Double(0))).doubleValue();
							dSummeAllerMengenDieserLagerZuordnung += ((dLadeMenge+dAbLadeMenge)/2);
						}
					}
				}
			}
			else if (oAnzahlCheck.iCountEK==0 && oAnzahlCheck.iCountVK>0)
			{
				//dann muss der EK-kontrakt der aus der zuordnung sein
				if (oFuhre.get_ID_VPOS_KON_EK_cUF_NN("").equals(cID_VPOS_KON))
				{
					for (Map.Entry<String, RECORD_VPOS_TPA_FUHRE_ORT> ortEntry: oFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre().entrySet())
					{
						RECORD_VPOS_TPA_FUHRE_ORT ort = ortEntry.getValue();
						if (ort.get_ID_ADRESSE_LAGER_cUF_NN("").equals(cID_ADRESSE_LAGER) && ort.is_DELETED_NO())
						{
							double dLadeMenge = ort.get_ANTEIL_LADEMENGE_dValue(ort.get_ANTEIL_PLANMENGE_dValue(new Double(0))).doubleValue();
							double dAbLadeMenge = ort.get_ANTEIL_ABLADEMENGE_dValue(ort.get_ANTEIL_PLANMENGE_dValue(new Double(0))).doubleValue();
							dSummeAllerMengenDieserLagerZuordnung += ((dLadeMenge+dAbLadeMenge)/2);
						}
					}
				}
			}
			else 
			{
				if (this.bPrimary) oMV.add_MESSAGE(new MyE2_BASIC_WarningMessageWithAddonComponent(
						new MyE2_String("In der Lager-Zuordnung kommen Mehrfachorte auf Lade- und Abladeseite vor: Keine Prüfung der Zuordnungsmenge möglich !"),
						this.get_ownInfoButton(new MyE2_String("In der Lager-Zuordnung kommen Mehrfachorte auf Lade- und Abladeseite vor: Keine Prüfung der Zuordnungsmenge möglich !")),
						new Extent(95,Extent.PERCENT),
						new Extent(5,Extent.PERCENT)));
			}
		}
			
		
		if (dSummeAllerMengenDieserLagerZuordnung > this.get_LAGERMENGE_dValue(new Double(0)).doubleValue())
		{
			if (this.bPrimary) oMV.add_MESSAGE(new MyE2_BASIC_WarningMessageWithAddonComponent(
										new MyE2_String("Die Lagerzuordnungen zum Kontrakt sind überliefert !"),
										this.get_ownInfoButton(new MyE2_String("Die Lagerzuordnungen zum Kontrakt sind überliefert !")),
										new Extent(95,Extent.PERCENT),
										new Extent(5,Extent.PERCENT)));
		}
	
		
		return oMV;
	}

	
	//pruefklasse, die eine gesamtmenge auf einer ek-vk-zuordnung prueft
	private class pruefHelferFuhreOrteAnzahl_EK_VK_Seite extends RECLIST_VPOS_TPA_FUHRE_ORT.DoAnyThingWithAll
	{
		
		private int iCountEK = 0;
		private int iCountVK = 0;
		
		public pruefHelferFuhreOrteAnzahl_EK_VK_Seite()
		{
			super();
		}


		@Override
		public void doAnyThingWith(String hashKey, RECORD_VPOS_TPA_FUHRE_ORT oOrt)	throws myException
		{
			if (oOrt.get_DELETED_cUF_NN("N").equals("N"))
			{
				if (oOrt.get_DEF_QUELLE_ZIEL_cUF().equals("EK"))
				{
					iCountEK ++;
				}
				else
				{
					iCountVK ++;
				}
			}
		}

		public int get_iCountEK() { return iCountEK;}
		public int get_iCountVK() { return iCountVK;}

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
			RECORD_VPOS_KON  			KON = this.get_UP_RECORD_VPOS_KON_id_vpos_kon();
			RECORD_ADRESSE              ADR_LAGER = this.get_UP_RECORD_ADRESSE_id_adresse_lager();  
			RECORD_ADRESSE              ADR_FIRMA = this.get_UP_RECORD_ADRESSE_id_adresse_lager();
			
			if (ADR_LAGER.get_ADRESSTYP_cUF_NN("").equals(myCONST.ADRESSTYP_LIEFERADRESSE))
			{
				ADR_FIRMA = ADR_LAGER.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer().get(0).get_UP_RECORD_ADRESSE_id_adresse_basis();
			}
			
			vInfo.add(new MyE2_String("Fehlerinformation :"));
			vInfo.add(new MyE2_String("Die Prüfung der Lagerzuordnung hat einen Fehler ergeben:"));
			vInfo.add(new MyE2_String("EK-Kontrakt: ",true,"(ID: "+KON.get_ID_VPOS_KON_cUF()+")",false,
										KON.get_ANZAHL_cF_NN("0")+" "+KON.get_ANR1_cUF_NN("")+" "+KON.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_NAME1_cUF_NN("")+" "+KON.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_ORT_cUF_NN(""),false));

			vInfo.add(new MyE2_String("Lager: ",true,
									ADR_LAGER.get_NAME1_cUF_NN("")+" "+ADR_LAGER.get_ORT_cUF_NN(""),false,": Erlaubte Lagermenge: ",true,this.get_LAGERMENGE_cF_NN(""),false));

			vInfo.add(new MyE2_String("Firma: ",true,
									ADR_FIRMA.get_NAME1_cUF_NN("")+" "+ADR_FIRMA.get_ORT_cUF_NN(""),false));
			
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
	
	
	
	@Override
	public String get_ID() throws myException
	{
		return this.get_ID_VPOS_KON_LAGER_cUF();
	}

	

	@Override
	public String get_TABLENAME_CHANGED() throws myException
	{
		return this.TABLENAME_CHANGED;
	}

	
	
	
	
	
	
}



