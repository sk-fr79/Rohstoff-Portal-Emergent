package rohstoff.utils.SQL_DAEMONS;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.XX_SQL_STACK_DAEMON;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MyDBToolBox_LOG_INFO;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;


/**
 * stackdaemon prueft eine reihe von tabellen, ob es bei der tabellen-ID eintraege
 * gibt, die seit dem letzten zeitstempel in der eigenen abfolge eine aenderung aus 
 * einer anderen session gibt. wenn ja erfolgt fehlermeldung 
 */
public class INTEGRITY_WATCHER_Daemon extends XX_SQL_STACK_DAEMON {

	private Vector<String> vTablesToControll = new Vector<String>();

	
	/*
	 * kontrollvariable, die einen neu generierten timestamp fuer die JD_DB_LOG mit dem stamp vergleicht, 
	 * die in der validierung aus dem Start-ModulContainer gezogen wurde.
	 * Falls der Speicherknopf ebenfalls im StartContainer steht, muss 		oButton.get_bMustSet_MILLISECONDSTAMP_TO_StartContainer()
	 * auf false gesetzt werden.
	 * Falls das vergessen wurdem dann setzt der speicherknopf unmittelbar vor der einen neuen Stamp und die validierung geht dann natuerlich
	 * schief.  
	 */

	public INTEGRITY_WATCHER_Daemon() 
	{
		super();
		this.vTablesToControll.add("JT_VKOPF_KON");
		this.vTablesToControll.add("JT_VPOS_KON");
		this.vTablesToControll.add("JT_VPOS_KON_TRAKT");
		this.vTablesToControll.add("JT_VPOS_KON_LAGER");
		this.vTablesToControll.add("JT_EK_VK_BEZUG");
		
		this.vTablesToControll.add("JT_VKOPF_RG");
		this.vTablesToControll.add("JT_VPOS_RG");

		this.vTablesToControll.add("JT_VKOPF_STD");
		this.vTablesToControll.add("JT_VPOS_STD");

		this.vTablesToControll.add("JT_VKOPF_TPA");
		this.vTablesToControll.add("JT_VPOS_TPA");
		this.vTablesToControll.add("JT_VPOS_TPA_FUHRE");
		this.vTablesToControll.add("JT_VPOS_TPA_FUHRE_ORT");
		
		this.vTablesToControll.add("JT_ADRESSE");
	}

	
	
	public MyE2_MessageVector collect_LOG_INFOs(Vector<MyDBToolBox_LOG_INFO> vLogInfos,MyConnection oConn) throws myException 
	{
		//sammelt nur relevante MyDBToolBox_LOG_INFO - eintraege
		MyE2_MessageVector 	oMV = 			new MyE2_MessageVector();
		
		//die betroffenen tabellen-LogInfos sammeln
		for (int i=0;i<vLogInfos.size();i++)
		{
			if (this.vTablesToControll.contains(vLogInfos.get(i).get_cTABLENAME()))
			{
				if (!vLogInfos.get(i).get_IS_DELETE())           //nur insert und update werden beruecksichtigt !
				{
					this.get_vLogInfos().add(vLogInfos.get(i));
				}
			}
		}
		return oMV;
	}

	


	

	
	
	
	public MyE2_MessageVector doValidate(MyConnection oConn) throws myException 
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		
		/*
		 * aus dem MyActionEvent den vorgaenger-Timestamp rausholen und zum vergleich heranziehen
		 */
		String cOLD_MILLISECONDSTAMP = bibE2.get_LAST_DB_TIMESTAMP();
		if (bibALL.isEmpty(cOLD_MILLISECONDSTAMP))                  //den timestamp MUSS er immer finden
		{
			throw new myException(this,": Strange Error: Cannot find Session-Timestamp !");
		}

		
		for (int i=0;i<this.get_vLogInfos().size();i++)
		{
			String cSQL_KontrollQuery = "SELECT COUNT(*) FROM "+bibE2.cTO()+".JD_DB_LOG WHERE " +
											" TABLENAME="+bibALL.MakeSql(this.get_vLogInfos().get(i).get_cTABLENAME())+" AND " +
											" TIMESTAMPMILLISECS>="+bibALL.MakeSql(cOLD_MILLISECONDSTAMP)+" AND " +
											" ID_TABLE="+this.get_vLogInfos().get(i).get_cID_TABLE()+" AND" +
											" DBSESSION<>"+bibALL.MakeSql(this.get_vLogInfos().get(i).get_cDB_SESSION()); 

			String[][] cNumber = bibDB.EinzelAbfrageInArray(cSQL_KontrollQuery,false);
			
			if (cNumber.length  == 1)
			{
				if (!cNumber[0][0].trim().equals("0"))
				{
					MyE2_String cHelp = new MyE2_String("Fehler ! Die Tabelle ");
					cHelp.addUnTranslated(this.get_vLogInfos().get(i).get_cTABLENAME());
					cHelp.addTranslated(" wurde von anderen Benutzer(n) geändert ! - Deshalb kann der Vorgang nicht ausgeführt werden !");
					oMV.add_MESSAGE(new MyE2_Alarm_Message(cHelp));
				}
			}
			else
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("IntegrityWatcherDaemon:doValidate:Error Checking JD_DB_LOG !",false));
			}
		}
		return oMV;
	}

	
	
	
	public Vector<String> getTriggerStatementsAfterSQL(MyConnection oConn, MyE2_MessageVector oMV) throws myException 
	{
		Vector<String> vRueck = new Vector<String>();
		
		for (int i=0;i<this.get_vLogInfos().size();i++)
		{
			vRueck.addAll(this.createTriggers(this.get_vLogInfos().get(i).get_cTABLENAME(), this.get_vLogInfos().get(i).get_cID_TABLE()));
		}
		return vRueck;
	}

	
	/*
	 * hier werden die sperrstatements fuer andere benutzer geschrieben
	 */
	private Vector<String> createTriggers(String cTablename, String cTableID) throws myException
	{
		Vector<String> vRueck = new Vector<String>();
		
		// es werden nur dummy-updates geschrieben, damit die datenbank-triggerung anstartet
		if 	(cTablename.equals("JT_VKOPF_STD"))
		{
			vRueck.add(this.get_Standard_Add_Ons_zu_Kopf("STD", cTableID));
		}
		
		
		if 	(cTablename.equals("JT_VPOS_STD"))
		{
			vRueck.add(this.get_Standard_Add_Ons_zu_Pos("STD", cTableID));
		}
		
		
		if 	(cTablename.equals("JT_VKOPF_KON"))
		{
			// POSITION
			vRueck.add(this.get_Standard_Add_Ons_zu_Kopf("KON", cTableID));

			//LAGER dazu
			vRueck.add("UPDATE JT_VPOS_KON_LAGER SET LETZTE_AENDERUNG=LETZTE_AENDERUNG WHERE ID_VPOS_KON IN" +
					" (SELECT ID_VPOS_KON FROM JT_VPOS_KON WHERE ID_VKOPF_KON="+cTableID+")");

			//EK_VK_BEZUG dazu
			vRueck.add("UPDATE JT_EK_VK_BEZUG SET LETZTE_AENDERUNG=LETZTE_AENDERUNG WHERE ID_VPOS_KON_EK IN" +
					" (SELECT ID_VPOS_KON FROM JT_VPOS_KON WHERE ID_VKOPF_KON="+cTableID+")");

			vRueck.add("UPDATE JT_EK_VK_BEZUG SET LETZTE_AENDERUNG=LETZTE_AENDERUNG WHERE ID_VPOS_KON_VK IN" +
					" (SELECT ID_VPOS_KON FROM JT_VPOS_KON WHERE ID_VKOPF_KON="+cTableID+")");
			
			
		}
		
		
		if 	(cTablename.equals("JT_VPOS_KON"))
		{
			//KOPF
			vRueck.add(this.get_Standard_Add_Ons_zu_Pos("KON", cTableID));
			
			//LAGER dazu
			vRueck.add("UPDATE JT_VPOS_KON_LAGER SET LETZTE_AENDERUNG=LETZTE_AENDERUNG WHERE ID_VPOS_KON="+cTableID);
			
			//EK_VK_BEZUG dazu
			vRueck.add("UPDATE JT_EK_VK_BEZUG SET LETZTE_AENDERUNG=LETZTE_AENDERUNG WHERE ID_VPOS_KON_EK="+cTableID);
			vRueck.add("UPDATE JT_EK_VK_BEZUG SET LETZTE_AENDERUNG=LETZTE_AENDERUNG WHERE ID_VPOS_KON_VK="+cTableID);
			
		}
		
	
		if 	(cTablename.equals("JT_EK_VK_BEZUG"))
		{
			//POSITION dazu
			vRueck.add("UPDATE JT_VPOS_KON SET LETZTE_AENDERUNG=LETZTE_AENDERUNG WHERE ID_VPOS_KON IN " +
					" (SELECT ID_VPOS_KON_EK FROM JT_EK_VK_BEZUG WHERE ID_EK_VK_BEZUG="+cTableID+")");
			vRueck.add("UPDATE JT_VPOS_KON SET LETZTE_AENDERUNG=LETZTE_AENDERUNG WHERE ID_VPOS_KON IN " +
					" (SELECT ID_VPOS_KON_VK FROM JT_EK_VK_BEZUG WHERE ID_EK_VK_BEZUG="+cTableID+")");

			
			//KOPF dazu
			vRueck.add("UPDATE JT_VKOPF_KON  SET LETZTE_AENDERUNG=LETZTE_AENDERUNG WHERE" +
					" ID_VKOPF_KON IN " +
						" (SELECT ID_VKOPF_KON FROM JT_VPOS_KON WHERE ID_VPOS_KON IN " +
								" (SELECT ID_VPOS_KON_EK FROM JT_EK_VK_BEZUG WHERE ID_EK_VK_BEZUG="+cTableID+"))");

			vRueck.add("UPDATE JT_VKOPF_KON  SET LETZTE_AENDERUNG=LETZTE_AENDERUNG WHERE" +
					" ID_VKOPF_KON IN " +
						" (SELECT ID_VKOPF_KON FROM JT_VPOS_KON WHERE ID_VPOS_KON IN " +
								" (SELECT ID_VPOS_KON_VK FROM JT_EK_VK_BEZUG WHERE ID_EK_VK_BEZUG="+cTableID+"))");

			//LAGER
			vRueck.add("UPDATE JT_VPOS_KON_LAGER  SET LETZTE_AENDERUNG=LETZTE_AENDERUNG WHERE" +
					" ID_VPOS_KON IN " +
						" (SELECT ID_VPOS_KON_EK FROM JT_EK_VK_BEZUG WHERE ID_EK_VK_BEZUG="+cTableID+")");
			vRueck.add("UPDATE JT_VPOS_KON_LAGER  SET LETZTE_AENDERUNG=LETZTE_AENDERUNG WHERE" +
					" ID_VPOS_KON IN " +
						" (SELECT ID_VPOS_KON_VK FROM JT_EK_VK_BEZUG WHERE ID_EK_VK_BEZUG="+cTableID+")");

			
		}

		
		if 	(cTablename.equals("JT_VPOS_KON_LAGER"))
		{
			//POS dazu
			vRueck.add("UPDATE JT_VPOS_KON SET LETZTE_AENDERUNG=LETZTE_AENDERUNG WHERE ID_VPOS_KON  IN" +
						" (SELECT ID_VPOS_KON FROM JT_VPOS_KON_LAGER WHERE ID_VPOS_KON_LAGER="+cTableID+")");
			
			//KOPF dazu
			vRueck.add("UPDATE JT_VKOPF_KON  SET LETZTE_AENDERUNG=LETZTE_AENDERUNG WHERE" +
					" ID_VKOPF_KON IN " +
						" (SELECT ID_VKOPF_KON FROM JT_VPOS_KON WHERE ID_VPOS_KON IN " +
								" (SELECT ID_VPOS_KON FROM JT_VPOS_KON_LAGER WHERE ID_VPOS_KON_LAGER="+cTableID+"))");
			
			//BEZUEG
			vRueck.add("UPDATE JT_EK_VK_BEZUG  SET LETZTE_AENDERUNG=LETZTE_AENDERUNG WHERE" +
					" ID_VPOS_KON_EK IN " +
						" (SELECT ID_VPOS_KON FROM JT_VPOS_KON_LAGER WHERE ID_VPOS_KON_LAGER="+cTableID+")");
			
			vRueck.add("UPDATE JT_EK_VK_BEZUG  SET LETZTE_AENDERUNG=LETZTE_AENDERUNG WHERE" +
					" ID_VPOS_KON_VK IN " +
						" (SELECT ID_VPOS_KON FROM JT_VPOS_KON_LAGER WHERE ID_VPOS_KON_LAGER="+cTableID+")");
			
		}

		
		
		if 	(cTablename.equals("JT_VKOPF_RG"))
		{
			vRueck.add(this.get_Standard_Add_Ons_zu_Kopf("RG", cTableID));
		}
		
		
		if 	(cTablename.equals("JT_VPOS_RG"))
		{
			vRueck.add(this.get_Standard_Add_Ons_zu_Pos("RG", cTableID));
		}
		
		
		if	(cTablename.equals("JT_VKOPF_TPA"))
		{
			vRueck.add(this.get_Standard_Add_Ons_zu_Kopf("TPA", cTableID));
			//Fuhren dazu
			vRueck.add("UPDATE JT_VPOS_TPA_FUHRE SET LETZTE_AENDERUNG=LETZTE_AENDERUNG WHERE ID_VPOS_TPA IN" +
						" (SELECT ID_VPOS_TPA FROM JT_VPOS_TPA WHERE ID_VKOPF_TPA="+cTableID+")");
		}
		
		
		if 	(cTablename.equals("JT_VPOS_TPA"))
		{
			vRueck.add(this.get_Standard_Add_Ons_zu_Pos("TPA", cTableID));
			//Fuhre dazu
			vRueck.add("UPDATE JT_VPOS_TPA_FUHRE SET LETZTE_AENDERUNG=LETZTE_AENDERUNG WHERE ID_VPOS_TPA="+cTableID);
		}

		//fuhre muss pos_tpa und kopf_tpa informieren
		if 	(cTablename.equals("JT_VPOS_TPA_FUHRE"))
		{
			vRueck.add("UPDATE JT_VPOS_TPA SET LETZTE_AENDERUNG=LETZTE_AENDERUNG WHERE ID_VPOS_TPA  IN" +
						" (SELECT ID_VPOS_TPA FROM JT_VPOS_TPA_FUHRE WHERE ID_VPOS_TPA_FUHRE="+cTableID+")");
			
			vRueck.add("UPDATE JT_VKOPF_TPA  SET LETZTE_AENDERUNG=LETZTE_AENDERUNG WHERE" +
					" ID_VKOPF_TPA IN " +
						" (SELECT ID_VKOPF_TPA FROM JT_VPOS_TPA WHERE ID_VPOS_TPA IN " +
								" (SELECT ID_VPOS_TPA FROM JT_VPOS_TPA_FUHRE WHERE ID_VPOS_TPA_FUHRE="+cTableID+"))");
			
		}

		
		//fuhre muss pos_tpa und kopf_tpa informieren
		if 	(cTablename.equals("JT_VPOS_TPA_FUHRE_ORT"))
		{
			vRueck.add("UPDATE JT_VPOS_TPA_FUHRE SET LETZTE_AENDERUNG=LETZTE_AENDERUNG WHERE ID_VPOS_TPA_FUHRE IN" +
					" (SELECT ID_VPOS_TPA_FUHRE FROM JT_VPOS_TPA_FUHRE_ORT WHERE ID_VPOS_TPA_FUHRE_ORT="+cTableID+")");
			
			vRueck.add("UPDATE JT_VPOS_TPA SET LETZTE_AENDERUNG=LETZTE_AENDERUNG WHERE ID_VPOS_TPA  IN" +
						" (SELECT ID_VPOS_TPA FROM JT_VPOS_TPA_FUHRE WHERE ID_VPOS_TPA_FUHRE IN" +
							" (SELECT ID_VPOS_TPA_FUHRE FROM JT_VPOS_TPA_FUHRE_ORT WHERE ID_VPOS_TPA_FUHRE_ORT="+cTableID+"))");
			
			vRueck.add("UPDATE JT_VKOPF_TPA  SET LETZTE_AENDERUNG=LETZTE_AENDERUNG WHERE" +
					" ID_VKOPF_TPA IN " +
						" (SELECT ID_VKOPF_TPA FROM JT_VPOS_TPA WHERE ID_VPOS_TPA  IN" +
							" (SELECT ID_VPOS_TPA FROM JT_VPOS_TPA_FUHRE WHERE ID_VPOS_TPA_FUHRE IN" +
								" (SELECT ID_VPOS_TPA_FUHRE FROM JT_VPOS_TPA_FUHRE_ORT WHERE ID_VPOS_TPA_FUHRE_ORT="+cTableID+")))");
			
		}

	
		
		
		//vrueck schreibt immer eines der zusatzfelder, deshalb muessen diese ohne zusatzfelder geschrieben werden
		Vector<String> vNeu = new Vector<String>();
		
		for (String cHelp:vRueck)
		{
			vNeu.add(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+cHelp);
		}
		
		return vNeu;
	}
	


	private String get_Standard_Add_Ons_zu_Kopf(String cTyp, String cTableID)
	{
		return "UPDATE JT_VPOS_"+cTyp+" SET LETZTE_AENDERUNG=LETZTE_AENDERUNG WHERE ID_VKOPF_"+cTyp+"="+cTableID;
	}

	private String get_Standard_Add_Ons_zu_Pos(String cTyp, String cTableID)
	{
		return "UPDATE JT_VKOPF_"+cTyp+" SET LETZTE_AENDERUNG=LETZTE_AENDERUNG WHERE ID_VKOPF_"+cTyp+" IN" +
				" (SELECT ID_VKOPF_"+cTyp+" FROM JT_VPOS_"+cTyp+" WHERE ID_VPOS_"+cTyp+"="+cTableID+")";

	}
	

	

}
