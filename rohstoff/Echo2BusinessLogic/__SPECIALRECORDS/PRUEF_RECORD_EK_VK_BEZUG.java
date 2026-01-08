package rohstoff.Echo2BusinessLogic.__SPECIALRECORDS;

import java.util.Map;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.components.E2_InfoButton;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EK_VK_BEZUG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;
import rohstoff.utils.SQL_DAEMONS.IF_PRUEF_Interface;
import rohstoff.utils.SQL_DAEMONS.VectorSingle_PRUEF_Interface;


//pruefdatensatz, der einen EK-VK-Bezug prueft, ob mengenmaessig alles stimmt
public class PRUEF_RECORD_EK_VK_BEZUG extends RECORD_EK_VK_BEZUG implements IF_PRUEF_Interface
{
	
	private boolean 		bGeprueft = false;
	private boolean 		bPrimary = false;
	private String         TABLENAME_CHANGED = null;

	
	public PRUEF_RECORD_EK_VK_BEZUG(String c_id_or_whereblock, MyConnection Conn, boolean Primary, String cTablename_changed) throws myException
	{
		super(c_id_or_whereblock, Conn);
		this.TABLENAME_CHANGED = cTablename_changed;
		this.bPrimary = Primary;
	}

	
	

	public PRUEF_RECORD_EK_VK_BEZUG(RECORD_EK_VK_BEZUG recordOrig, boolean Primary)
	{
		super(recordOrig);
		this.bPrimary = Primary;
	}


	public PRUEF_RECORD_EK_VK_BEZUG(RECORD_EK_VK_BEZUG recordOrig, boolean Primary, String cTablename_changed)
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
		if (oVGL instanceof PRUEF_RECORD_EK_VK_BEZUG)
		{
			try
			{
				if  (   ((PRUEF_RECORD_EK_VK_BEZUG)oVGL).get_ID_EK_VK_BEZUG_cUF().equals(this.get_ID_EK_VK_BEZUG_cUF()))
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
		
		String cID_VPOS_KON_EK = this.get_ID_VPOS_KON_EK_cUF();
		String cID_VPOS_KON_VK = this.get_ID_VPOS_KON_VK_cUF();
		
		
		//alle fuhren, die diese ek-vk-zuordnung tangieren (direkt, in der hauptzuordnung)
		RECLIST_VPOS_TPA_FUHRE oListFuhren = new RECLIST_VPOS_TPA_FUHRE("SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE NVL(DELETED,'N')='N' AND " +
																	"ID_VPOS_KON_EK="+cID_VPOS_KON_EK+" AND "+
																	"ID_VPOS_KON_VK="+cID_VPOS_KON_VK,this.get_oConn());
		
		//jetzt alle fuhren dazuholen, die in einem ladeort / abladeort einen der kontrakte der zuordnung enthalten
		oListFuhren.putAll( new RECLIST_VPOS_TPA_FUHRE("SELECT DISTINCT JT_VPOS_TPA_FUHRE.* FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT " +
																	" INNER JOIN " +
																	bibE2.cTO()+".JT_VPOS_TPA_FUHRE " +
																	"  ON " +
																	" ("+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_TPA_FUHRE = "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE)" +
																	" WHERE  NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='N' AND NVL(JT_VPOS_TPA_FUHRE_ORT.DELETED,'N')='N' AND" +
																	" JT_VPOS_TPA_FUHRE.ID_VPOS_KON_EK="+cID_VPOS_KON_EK+" AND  " +
																			"JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_KON="+cID_VPOS_KON_VK,this.get_oConn()),true);

		
		//jetzt alle fuhren dazuholen, die in einem ladeort / abladeort einen der kontrakte der zuordnung enthalten
		oListFuhren.putAll( new RECLIST_VPOS_TPA_FUHRE("SELECT DISTINCT JT_VPOS_TPA_FUHRE.* FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT " +
																" INNER JOIN " +
																bibE2.cTO()+".JT_VPOS_TPA_FUHRE " +
																"  ON " +
																" ("+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_TPA_FUHRE = "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE)" +
																" WHERE  NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='N' AND NVL(JT_VPOS_TPA_FUHRE_ORT.DELETED,'N')='N' AND " +
																" JT_VPOS_TPA_FUHRE.ID_VPOS_KON_VK="+cID_VPOS_KON_VK+" AND  " +
																		"JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_KON="+cID_VPOS_KON_EK,this.get_oConn()),true);

		//jetzt die menge der zuordnung ermitteln
		double dSummeAllerMengenDieserZuordnung = 0;

		
		//jetzt alle fuhren, die beteiligt sind, anschauen
		for (Map.Entry<String, RECORD_VPOS_TPA_FUHRE> fuhreEntry: oListFuhren.entrySet())
		{
			//jetzt pruefen, ob eine fuhre links- und rechtsseitig zusatzorte hat, d.h. diese zuordnungen koennen nicht geprueft werden 
			RECORD_VPOS_TPA_FUHRE oFuhre = fuhreEntry.getValue();
			pruefHelferFuhreOrteAnzahl_EK_VK_Seite oAnzahlCheck = new pruefHelferFuhreOrteAnzahl_EK_VK_Seite();
			oFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre().DoAnyThing(oAnzahlCheck);

			//die fuhren-objekte in den sammler stecken
			/*
			 * geaendert am: 25.02.2010 von: martin
			 */
			if (this.TABLENAME_CHANGED.equals(this.get_TABLE_NAME()))
			{
				vSammler.add(new PRUEF_RECORD_VPOS_TPA_FUHRE(oFuhre,this.bPrimary,this.TABLENAME_CHANGED));
			}
			
			/*
			 * es gibt 4 fuhrentypen, die bezueglich zuordnungen unterschiedlich zu werten sind:
			 * 1. 1 zu 1  -fuhren (keine zusatzorte), zuordnungsmenge ist mittelwert zwischen lade- und ablademenge
			 * 2. N zu 1  -fuhre  (zusaetzliche ladeorte)
			 * 3. 1 zu N  -fuhren (zusaetzliche abladeorte)
			 * 4. N zu N  -fuhren (nicht bewertbar - Warnmeldung)
			 */ 
			if (oAnzahlCheck.iCountEK==0 && oAnzahlCheck.iCountVK==0)
			{
				if (oFuhre.get_ID_VPOS_KON_EK_cUF_NN("").equals(cID_VPOS_KON_EK) && oFuhre.get_ID_VPOS_KON_VK_cUF_NN("").equals(cID_VPOS_KON_VK) )
				{
					double dLadeMenge = oFuhre.get_ANTEIL_LADEMENGE_LIEF_dValue(oFuhre.get_ANTEIL_PLANMENGE_LIEF_dValue(new Double(0))).doubleValue();
					double dAbLadeMenge = oFuhre.get_ANTEIL_ABLADEMENGE_LIEF_dValue(oFuhre.get_ANTEIL_PLANMENGE_LIEF_dValue(new Double(0))).doubleValue();
					dSummeAllerMengenDieserZuordnung += ((dLadeMenge+dAbLadeMenge)/2);
				}
			}
			else if (oAnzahlCheck.iCountEK>0 && oAnzahlCheck.iCountVK==0)
			{
				//dann muss der VK-kontrakt der aus der zuordnung sein
				if (oFuhre.get_ID_VPOS_KON_VK_cUF_NN("").equals(cID_VPOS_KON_VK))
				{
					for (Map.Entry<String, RECORD_VPOS_TPA_FUHRE_ORT> ortEntry: oFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre().entrySet())
					{
						RECORD_VPOS_TPA_FUHRE_ORT ort = ortEntry.getValue();
						
						//die ort-objekte in den sammler stecken
						/*
						 * geaendert am: 25.02.2010 von: martin
						 */
						if (this.TABLENAME_CHANGED.equals(this.get_TABLE_NAME()))
						{
							vSammler.add(new PRUEF_RECORD_VPOS_TPA_FUHRE_ORT(ort,this.bPrimary,this.TABLENAME_CHANGED));
						}

						
						if (ort.get_ID_VPOS_KON_cUF_NN("").equals(cID_VPOS_KON_EK) && ort.is_DELETED_NO())
						{
							double dLadeMenge = ort.get_ANTEIL_LADEMENGE_dValue(ort.get_ANTEIL_PLANMENGE_dValue(new Double(0))).doubleValue();
							double dAbLadeMenge = ort.get_ANTEIL_ABLADEMENGE_dValue(ort.get_ANTEIL_PLANMENGE_dValue(new Double(0))).doubleValue();
							dSummeAllerMengenDieserZuordnung += ((dLadeMenge+dAbLadeMenge)/2);
						}
					}
				}
			}
			else if (oAnzahlCheck.iCountEK==0 && oAnzahlCheck.iCountVK>0)
			{
				//dann muss der EK-kontrakt der aus der zuordnung sein
				if (oFuhre.get_ID_VPOS_KON_EK_cUF_NN("").equals(cID_VPOS_KON_EK))
				{
					for (Map.Entry<String, RECORD_VPOS_TPA_FUHRE_ORT> ortEntry: oFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre().entrySet())
					{
						RECORD_VPOS_TPA_FUHRE_ORT ort = ortEntry.getValue();
						if (this.TABLENAME_CHANGED.equals(this.get_TABLE_NAME()))
						{
							vSammler.add(new PRUEF_RECORD_VPOS_TPA_FUHRE_ORT(ort,this.bPrimary,this.TABLENAME_CHANGED));
						}

						
						if (ort.get_ID_VPOS_KON_cUF_NN("").equals(cID_VPOS_KON_VK) && ort.is_DELETED_NO())
						{
							double dLadeMenge = ort.get_ANTEIL_LADEMENGE_dValue(ort.get_ANTEIL_PLANMENGE_dValue(new Double(0))).doubleValue();
							double dAbLadeMenge = ort.get_ANTEIL_ABLADEMENGE_dValue(ort.get_ANTEIL_PLANMENGE_dValue(new Double(0))).doubleValue();
							dSummeAllerMengenDieserZuordnung += ((dLadeMenge+dAbLadeMenge)/2);
						}
					}
				}
			}
			else 
			{
				if (this.bPrimary) oMV.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("In der Zuordnung kommen Mehrfachorte auf Lade- und Abladeseite vor: Keine Prüfung der Zuordnungsmenge möglich !")));
			}
		}
			
		
		if (dSummeAllerMengenDieserZuordnung > this.get_ANZAHL_dValue(new Double(0)).doubleValue())
		{
			if (this.bPrimary) oMV.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Die Zuordnung EK-Kontrakt zu VK-Kontrakt ist überliefert !  ")));
		}
		
		
		//die weiteren PRUEF-Objekte in den sammler
		if (this.TABLENAME_CHANGED.equals(this.get_TABLE_NAME()))
		{
			vSammler.add(new PRUEF_RECORD_VPOS_KON(this.get_ID_VPOS_KON_EK_cUF(),this.get_oConn(),this.bPrimary,this.TABLENAME_CHANGED));
			vSammler.add(new PRUEF_RECORD_VPOS_KON(this.get_ID_VPOS_KON_VK_cUF(),this.get_oConn(),this.bPrimary,this.TABLENAME_CHANGED));
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
			RECORD_VPOS_KON  EK = this.get_UP_RECORD_VPOS_KON_id_vpos_kon_ek();
			RECORD_VPOS_KON  VK = this.get_UP_RECORD_VPOS_KON_id_vpos_kon_vk();
			
			vInfo.add(new MyE2_String("Fehlerinformation :"));
			vInfo.add(new MyE2_String("Die Prüfung der EK-VK-Zuordnung hat einen Fehler ergeben:"));
			vInfo.add(new MyE2_String("EK-Kontrakt-Position: ",true,"(ID: "+EK.get_ID_VPOS_KON_cUF()+")",false,
										EK.get_ANZAHL_cF_NN("0")+" "+EK.get_ANR1_cUF_NN("")+" "+EK.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_NAME1_cUF_NN("")+" "+EK.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_ORT_cUF_NN(""),false));
			vInfo.add(new MyE2_String("VK-Kontrakt-Position: ",true,"(ID: "+VK.get_ID_VPOS_KON_cUF()+")",false,
										VK.get_ANZAHL_cF_NN("0")+" "+VK.get_ANR1_cUF_NN("")+" "+VK.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_NAME1_cUF_NN("")+" "+VK.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_ORT_cUF_NN(""),false));

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
		return this.get_ID_EK_VK_BEZUG_cUF();
	}



	
	
	@Override
	public String get_TABLENAME_CHANGED() throws myException
	{
		return this.TABLENAME_CHANGED;
	}

	
}

	
	

