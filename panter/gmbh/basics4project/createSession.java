package panter.gmbh.basics4project;

import java.io.File;
import java.lang.ref.WeakReference;
import java.net.InetAddress;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_UserSetting_TextSaver;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;
import panter.gmbh.basics4project.BasicRecords.BASIC_RECORD_MANDANT;
import panter.gmbh.basics4project.BasicRecords.BASIC_RECORD_USER;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES_ENUM;
import panter.gmbh.indep.bib_Settigs_Mandant;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.DB_STATICS;
import panter.gmbh.indep.dataTools.DB_TransaktionsLock;
import panter.gmbh.indep.dataTools.ENUM_DB_PARAMETER;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MyDBToolBox_FAB;
import panter.gmbh.indep.dataTools.Mandatory_DB_Objects.Mandatory_DB_Objects_Handler;
import panter.gmbh.indep.dataTools.Mandatory_DB_Objects.ORACLE.Mandatory_DB_Object_DATA_Sonderlager;
import panter.gmbh.indep.dataTools.Mandatory_DB_Objects.ORACLE.Mandatory_DB_Object_Function_by_file;
import panter.gmbh.indep.dataTools.Mandatory_DB_Objects.ORACLE.Mandatory_DB_Object_OracleSysContextHandlerPackage;
import panter.gmbh.indep.dataTools.Mandatory_DB_Objects.ORACLE.Mandatory_DB_Object_Trigger_BG_ATOM_AUSWERT;
import panter.gmbh.indep.dataTools.Mandatory_DB_Objects.ORACLE.Mandatory_DB_Object_Trigger_BG_ATOM_AUSWERT_2;
import panter.gmbh.indep.dataTools.Mandatory_DB_Objects.ORACLE.Mandatory_DB_Object_Trigger_BG_VEKTOR_SETIDS;
import panter.gmbh.indep.dataTools.Mandatory_DB_Objects.ORACLE.Mandatory_DB_Object_Trigger_CalcValues_IN_BEWEGUNG_ATOM;
import panter.gmbh.indep.dataTools.Mandatory_DB_Objects.ORACLE.Mandatory_DB_Object_Trigger_SETZKASTEN_ATOM_LOG;
import panter.gmbh.indep.dataTools.Mandatory_DB_Objects.ORACLE.Mandatory_DB_Object_Trigger_SETZKASTEN_STATION_LOG;
import panter.gmbh.indep.dataTools.Mandatory_DB_Objects.ORACLE.Mandatory_DB_Object_Trigger_SETZKASTEN_VERBUCHT;
import panter.gmbh.indep.dataTools.Mandatory_DB_Objects.ORACLE.Mandatory_DB_Object_Trigger_SETZKASTEN_VERBUCHT_K;
import panter.gmbh.indep.dataTools.Mandatory_DB_Objects.ORACLE._waste.Mandatory_DB_ObjectCreateView4BG_List;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN._VALID_DAEMON.FUHREN_SQL_DAEMON_4_VALIDATION;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_BEWEGUNGTYP;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_SONDERLAGER;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTORPOS_TYP;
import rohstoff.utils.SQL_DAEMONS.BEWEGUNGSDATEN_PRUEF_Daemon;
import rohstoff.utils.SQL_DAEMONS.ENDPREIS_CALC_Daemon;
import rohstoff.utils.SQL_DAEMONS.FIBU_Daemon;
import rohstoff.utils.SQL_DAEMONS.INTEGRITY_WATCHER_Daemon;
import rohstoff.utils.SQL_DAEMONS.LAGER_KONTO_Daemon;
import rohstoff.utils.SQL_DAEMONS.LAGER_KONTO_PREIS_Daemon;


// hier werden alle infos abgelegt, die beim start der applikation
// in der session gespeichert werden
// dies kann in verschiedenen logins passieren (standard oder echo ...)
// in der session werden alle werte in einem vektor-paar gespeichert,
// der erste vector __JIL_NAMES enthält die namen der elemente
// der zweite       __JIL_OBJECTS enthält die objekte selber
// dieses konstrukt soll zufällige namenskollisionen innerhalb der
// session vermeiden helfen
public class createSession
{
	//~ Instance fields ====================================================================================================================================================================================================================================================================================

	private String  cErrorMessage = ""; // wird via getErrorMessage() verfügbar
	private boolean bSessionOK = false; // wird via getSessionOK()    verfügbar

	//~ Constructors =======================================================================================================================================================================================================================================================================================

	// init-methode für das tomcat-eigene web.xml - file, bereich CONTEXT-PARAM
	@SuppressWarnings("unchecked")
	public createSession(String					cName,
						 String					cPassword,
						 HttpSession			SES,
						 ServletContext			oContext,
						 HttpServletRequest		oRequest) throws myException
	{
		boolean bXmlError = false; // wird true, wenn ein wert implements xml-file fehlt
		
		
		boolean bNoMandatoryObjectsCheck = false;  // wird true, wenn beim Anmeldename ein Hash vorangestellt wird z.B. #manfred anstatt manfred
											// der Hash wird gelöscht
		
		// prüfen auf Triggerausführung und korrigieren des Logins
		if (cName.startsWith("#")){
			bNoMandatoryObjectsCheck = true;
			cName = cName.substring(1);
		}
		
		
		
		oContext.log("");
		oContext.log("================================================================================");
		oContext.log("Start Session ...");
		oContext.log("HttpSession ID..." + SES.getId());
		
		
		String  cFehlendeXML = ""; // liefert in der fehlermeldung die info, welcher wert im  xml-file


		
		// fehlt
		String cTO; // übernimmt den tableowner
//		Vector __JIL_NAMES   = new Vector();
//		Vector __JIL_OBJECTS = new Vector();
		HashMap<String, Object> __JIL_VALUES = new HashMap<String, Object>();
		
//		SES.setAttribute("__JIL_OBJECTS", __JIL_OBJECTS);
//		SES.setAttribute("__JIL_NAMES", __JIL_NAMES);
		SES.setAttribute("__JIL_VALUES", __JIL_VALUES);

		// localisierung festlegen, damit diese von servereinstellungen
		// unabhängig wird
		Locale oLoc = new Locale("de", "DE");
		Locale.setDefault(oLoc);

		
//		///
//		for (MandantZusatzNamen name: MandantZusatzNamen.values()) {
//			System.out.println(name.toString());
//		}
//		///

		// in den servlet-context beim ersten user einen sammler fuer die sessions einbauen
		if (oContext.getAttribute("applications.users")==null)
		{
			HashMap hashSessions = new HashMap();
			oContext.setAttribute("applications.users",hashSessions);
			System.out.println("Session-Sammler initialisiert ...");
		}
		else
		{
			System.out.println("Session-Sammler vorhanden!");
		}
		
		// jetzt die sessionid pruefen und evtl. loeschen falls der server diese recylced
		HashMap<String,WeakReference<HttpSession>> hmSessions = (HashMap)oContext.getAttribute("applications.users");
		
		if (hmSessions.containsKey(SES.getId()))
			hmSessions.remove(SES.getId());
		
		
		//SES.setAttribute(arg0, arg1)
		
		// jetzt die session reinschreiben
		WeakReference<HttpSession> wrSession = new WeakReference<HttpSession>(SES);
		hmSessions.put(SES.getId(),wrSession);
		
		System.out.println("Count Sessions ... "+hmSessions.size());
		

		
//		System.out.println(bibALL.increase_Trigger_Counter());
//		System.out.println(bibALL.increase_Trigger_Counter());
//		System.out.println(bibALL.increase_Trigger_Counter());
//		System.out.println(bibALL.increase_Trigger_Counter());
//		
//		System.out.println(bibALL.reset_Trigger_Counter());
		
		/*
		 * eine weak-reference auf ein popup-fenster hinterlegen, das letzte
		 * gepoppte fenster wird in der session hinterlegt
		 */
		WeakReference oRefPopupWindow = new WeakReference(null);
		bibALL.setSessionValue("LASTPOPUPWINDOW",oRefPopupWindow);
		
		
		String[] xmlWerte = 
							{
								"dbkennung", // oracle8 oder sapdb7
								"classname", // classenname des jdbc-treibers (com.sap.dbtech.jdbc.DriverSapDB)
								"jdbc_string", // jdbc-url
								"java_login", // datenbanklogin für den server / Besitzer der Datenbanktabellen
								"java_passwort", // ...
								"datum_format", // definiert das format, wie ein datum in den
												// sql-string überführt wird
								"endpage",      // die seite, auf die nach dem ende-kommando verzweigt wird
								"idletime", // zeit bis zur zerstörung des session-objekts
								"outputpath", // in webrootpath liegender pfad für temporäre dateien
								"archivpath", // in webrootpath liegender pfad archiv- und dauerhafte protokoll-dateien
								"protokollpath", // in webrootpath liegender pfad fuer zip-archive von sendeprotokolle, in jedem liegt wiederum eine mandant-nummer
								"adresszusatzpath", // in webrootpath liegender ordner für die adresszusatz (jede adress hat hierin wieder einen ordner mit den daten)
								"reportbasepath", // in reportbasepath hat jeder mandant einen eigenen ordner mit reports
								"temppath", // in webrootpath liegender ordner für alle möglichen temprären daten (kann regelmäßig geputzt werden)
								"debug", // debugging-schalter
								"minimaltableseq",    // falls die tablesequenzen bei einem minimalwert starten sollen
								"xmldefpath"
							}; // encoding für echo         

		
		/*
		 * context in die session schreiben
		 */
		bibALL.setSessionValue("CONTEXT",oContext);
		bibALL.setSessionValue("NAME", cName);

		String cSessionID = SES.getId();
		bibALL.setSessionValue("SESSIONID", cSessionID);

		// die ip-adressen von client/server feststellen, damit session-laufzeit definiert werden kann
		// diese muss lokal unendlich sein
		bibALL.setSessionValue("CLIENT_IP_ADRESSE", oRequest.getRemoteAddr()); // client-Ip-Adresse

		String cServerIP = "";

		try
		{
			cServerIP = InetAddress.getLocalHost().toString(); // serverid kommt in der form: lxprog/192.168.0.223
			cServerIP = cServerIP.substring(cServerIP.indexOf("/") + 1);
			bibALL.setSessionValue("SERVER_IP_ADRESSE", cServerIP); // client-Ip-Adresse
		}
		catch (Exception e)
		{
			bibALL.setSessionValue("SERVER_IP_ADRESSE", ""); // client-Ip-Adresse
		}

		
		// statement-trigger-vector einfuehren
		bibALL.setSessionValue("SQLTRIGGER", new Vector());   
		
		// platz fuer die validierer einfuegen, die global operieren
		bibALL.setSessionValue("SQL_TRIGGER_AND_VALIDATOR", new Vector());   

		
		
		/*
		 * den kompletten namen und den namen der weg_applikation kann aus der oContext.getRealPath("") rausgelesen werden
		 */
		String cHelp = oContext.getRealPath("");
		// der name der applikation (aus tomcat auslesen)
		bibALL.setSessionValue("WEBROOTPATH", cHelp.substring(1));   // web-root-path ohne führendes /-zeichen
		
	
		
		/*
		 * der Webname ist rechts vom letzten trenn-zeichen
		 */
		Vector vTeile = bibALL.TrenneZeile(cHelp,File.separator);
		String cWebName = (String)vTeile.get(vTeile.size()-1);
		
		// der name der applikation (aus tomcat auslesen)
		// bib.setSessionValue(SES, "WEBNAME", request.getContextPath());
		bibALL.setSessionValue("WEBNAME", File.separator+cWebName);
		

		bXmlError = false;

		for (int i = 0; i < xmlWerte.length; i++)
		{
			bibALL.setSessionValue(xmlWerte [i].toUpperCase(), oContext.getInitParameter(xmlWerte [i]));

			if ((bibALL.getSessionValue(xmlWerte [i].toUpperCase()) == null) || ((String) bibALL.getSessionValue(xmlWerte [i].toUpperCase())).equals(""))
			{
				bXmlError = true; // dann erfolgt fehlermeldung,

				// weil alle xml-angaben nötig sind
				cFehlendeXML += ("/" + xmlWerte [i] + "/");
			}
		}

		
		//weitere parameter, die aber leer sein duerfen
        String[] cZusatzwerte = 
        {
        		"testmail",
        		"testmail_cc1",
        		"testmail_cc2",
        		"testmail_cc3",
        		"titelhinweis",
        		"character_encoding",
        		"test-report-mandant",                  // parameter, der es ermoeglicht, reports eines anderen mandanten zu testen
        		"disable_garbage_collection",			// wenn Y dann wird die händische Garbage-Collection nicht durchgeführt beim messaging
        		"debug_flags",							// die Debug-Flags die man auswerten möchte, getrennt mit "|" , z.B. "SQL_EXEC|SQL_QUERYS" 
														// DEBUG_FLAG_SQL_EXEC = 		"SQL_EXEC";
														// DEBUG_FLAG_SQL_QUERY = 		"SQL_QUERYS";
														// DEBUG_FLAG_SQL_ERROR = 		"SQL_ERROR";
														// DEBUG_FLAG_SQL_TIMESTAMP = 	"DEBUG_FLAG_SQL_TIMESTAMP";
														// DEBUG_FLAG_DIVERS1 =	"DIVERS1";
														// DEBUG_FLAG_DIVERS2 =	"DIVERS2";
														// DEBUG_FLAG_DIVERS3 =	"DIVERS3";
        		"prefetch_size"							// die Größe des Oracle-Prefetch (Standard == 10) des Oracle-SqlStatements
        		
        		
        };
        
		for (int i = 0; i < cZusatzwerte.length; i++)
		{
			bibALL.setSessionValue(cZusatzwerte [i].toUpperCase(), oContext.getInitParameter(cZusatzwerte [i]));
		}
		
		

		if (bXmlError)
		{
		    cErrorMessage = "Fehlende Werte in WEB.XML:\n" + cFehlendeXML;
		    return;
		}
		
		
		if (bibALL.String2Integer((String) bibALL.getSessionValue("IDLETIME")) == null)
		{
		    cErrorMessage = "Falsche Angabe idletime in XML:<" + (String) bibALL.getSessionValue("IDLETIME") + ">";
		    return;
		}

		
		int		 iIdleTime = (bibALL.String2Integer((String) bibALL.getSessionValue("IDLETIME"))).intValue();

		String[] cIPServer = new String[4];
		String[] cIPClient = new String[4];

		String   ccIPServer = (String) bibALL.getSessionValue("SERVER_IP_ADRESSE");
		String   ccIPClient = (String) bibALL.getSessionValue("CLIENT_IP_ADRESSE");

		if (	ccIPServer.equals("") || 
		        ccIPClient.equals("") || 
		        !bibALL.TrenneZeile(((String) bibALL.getSessionValue("CLIENT_IP_ADRESSE")), ".", cIPClient, 4) || 
		        !bibALL.TrenneZeile(((String) bibALL.getSessionValue("SERVER_IP_ADRESSE")), ".", cIPServer, 4))
		{
		    cErrorMessage = "Die IP-Adressen von Server/Client konnten nicht ermittelt werden";
		    return;
		}
		
		
		// wenn server und client implements gleiche netz sind, dann faktisch keine trennung nach unaktiver zeit
		if (cIPClient [0].equals(cIPServer [0]) && cIPClient [1].equals(cIPServer [1]) && cIPClient [2].equals(cIPServer [2]))
		{
			iIdleTime = 7200; // 2 stunden, damit ueber den tag nicht zu viele aktive sessions auflaufen
		}

		SES.setMaxInactiveInterval(iIdleTime);

		// dann datenbankbverbindung intialisieren ; diese wird anschliessend der session übergeben
		MyConnection oConnNormal	= null;
		try
		{
			String cKennung = DB_META.DB_SAP;
			if (((String) bibALL.getSessionValue("DBKENNUNG")).toUpperCase().startsWith("ORA"))
				cKennung = DB_META.DB_ORA;
			
			bibALL.setSessionValue("DBKENNUNG", cKennung);         // dbkennung korrigieren
			
			
			oConnNormal = new MyConnection(	(String) bibALL.getSessionValue("CLASSNAME"),
			                				(String) bibALL.getSessionValue("JDBC_STRING"),
			                				(String) bibALL.getSessionValue("JAVA_LOGIN"),
			                				(String) bibALL.getSessionValue("JAVA_PASSWORT"),
			                				(String) bibALL.getSessionValue("DBKENNUNG"));
			
			try
			{
		        //logeintrage, die aelter als 5 tage sind, loeschen
	            Statement rsStmt2 = oConnNormal.get_oConnection().createStatement(); // statement-object für result-set
		        //zuerst alte entraege mit der oracle-connection-nummer loeschen
	            rsStmt2.execute("DELETE FROM JD_DB_LOG WHERE SUBSTR(TIMESTAMPMILLISECS,1,8)<=TO_CHAR(SYSDATE-1,'YYYYMMDD')");
	            long lAnzahl = rsStmt2.getUpdateCount();
	            rsStmt2.close();
	            System.out.println("OLD JD_DB_LOG - Number deleted rows : "+lAnzahl);
	            
			}
			catch (SQLException ex)
			{
			    cErrorMessage = "Error deleting old JD_DB_LOG - Rows:"+ex.getLocalizedMessage();
			    return;
			}
			
			
		}
		catch (myException ex)
		{
		    cErrorMessage = "createSession:Error creating MyConnection-Objects:"+ex.getLocalizedMessage();
		    return;
		}

					
		bibALL.setSessionValue("CONN_NORMAL", oConnNormal); 	// die connection mit dem normalen usernamen

		MyDBToolBox menueDB = null;
		try
		{
		    menueDB = new MyDBToolBox(oConnNormal);
		}
		catch (myException ex)
		{
		    cErrorMessage = "createSession:Error creating MyDBToolBox-Object";
		    return;
		}

		
		String cDBSessionID = "-1";
		// jetzt die sessionID mitschreiben (wird in 99 % der falle die groesste id sein in der datenbank-liste
		// damit koennen dann spaeter locks zugeordnet werden und gezielt session gekillt werden
		
		String cDBSessionNumber = "-2";
		
		try
		{
			cDBSessionID = menueDB.EinzelAbfrage(DB_META.get_SessionQuery(	(String)bibALL.getSessionValue("DBKENNUNG"),  
																			(String)bibALL.getSessionValue("JAVA_LOGIN")	));
			
			cDBSessionNumber = menueDB.EinzelAbfrage(DB_META.ORA_SESSION_QUERY_COMPLETE);
		}
		catch (myException ex)
		{
		    cErrorMessage = "createSession:Error querying session-id !";
		    return;
		}
		
		bibALL.setSessionValue("DBSESSION",cDBSessionID);
		bibALL.setSessionValue("DBSESSIONNUMBER",cDBSessionNumber);
		System.out.println("----------- Oracle-Session-ID: --------------- "+cDBSessionID);
		System.out.println("----------- DBSESSION: --------------- "+cDBSessionNumber);
		

		//die werte auch direkt in die session schreibe, damit sie im sitzungsmanagement auch wieder ausgelesen werden koennen
		bibE2.get_CurrSession().setAttribute("@@@SESSION_4_LOCK_CHECK@@@",cDBSessionID);
		bibE2.get_CurrSession().setAttribute("@@@SESSION_IN_TRIGGER@@@",cDBSessionNumber);
		bibE2.get_CurrSession().setAttribute("@@@SESSION_USERNAME@@@",cName);
		
		
		cTO = (String) bibALL.getSessionValue("JAVA_LOGIN");
		cTO = cTO.trim();

		
//		String cSql3 = "select count(*) from  " + cTO + ".JD_USER ju" + " where upper(ju.name)=" + bibALL.MakeSql(cName.toUpperCase().trim()) + " and upper(ju.passwort)=" + bibALL.MakeSql(cPassword.toUpperCase().trim());
		
		// geaendert 2015-05-06: hier wird ein toolbox-generator benoetigt
		BASIC_RECORD_USER  		oRecUser = 		new BASIC_RECORD_USER();
		BASIC_RECORD_MANDANT 	oRecMandant = 	new BASIC_RECORD_MANDANT();
		
		oRecUser.set_DBToolBox_FAB(new ownToolBoxGenerator());
		oRecMandant.set_DBToolBox_FAB(new ownToolBoxGenerator());
		try
		{
			String cWhereUser = "upper(JD_USER.NAME)=" + bibALL.MakeSql(cName.toUpperCase().trim()) + " and upper(JD_USER.PASSWORT)=" + bibALL.MakeSql(cPassword.toUpperCase().trim());
			oRecUser.BuildRecord("SELECT * FROM "+_DB.USER+" WHERE "+cWhereUser);
			
			// geaendert 2015-05-06: hier wird ein toolbox-generator benoetigt
			//oRecUser = new BASIC_RECORD_USER("upper(JD_USER.NAME)=" + bibALL.MakeSql(cName.toUpperCase().trim()) + " and upper(JD_USER.PASSWORT)=" + bibALL.MakeSql(cPassword.toUpperCase().trim()));
			
			//2013-01-07: an dieser stelle muss die dbconnection den parameter id_benutzer bekommen
			//bibDB.ExecSQL("call dbms_session.set_identifier('"+oRecUser.get_ID_USER_cUF()+"')", true);
			//2015-05-07: ersetzt durch RAW-methode (martin)
			//bibDB.ExecSQL("call dbms_session.set_identifier('"+oRecUser.get_ID_USER_cUF()+"')", true);
			MyDBToolBox tb_Raw = MyDBToolBox_FAB.get_myDBToolBox(false, false);
			tb_Raw.ExecSQL("call dbms_session.set_identifier('"+oRecUser.get_ID_USER_cUF()+"')", true);
			bibALL.destroy_myDBToolBox(tb_Raw);
			//damit ist diese connection mit der benutzer-ID getagged
		} 
		catch (myException e1)
		{
			e1.printStackTrace();
			oRecUser = null;
		}
		
//		String cCount = menueDB.EinzelAbfrage(cSql3, "", "", "");

		if (oRecUser==null)
		{
		    cErrorMessage = "Benutzer unbekannt ! KEIN ZUTRITT ";
		    return;
		}
		
		try
		{
			String SqlMandant = "SELECT * FROM "+_DB.MANDANT+" WHERE ID_MANDANT="+oRecUser.get_ID_MANDANT_cUF_NN("-1");
			oRecMandant.BuildRecord(SqlMandant);
			//oRecMandant = new BASIC_RECORD_MANDANT(oRecUser.get_ID_MANDANT_lValue(new Long(-1)));
		}
		catch (myException e1)
		{
			e1.printStackTrace();
			oRecMandant = null;
		}
		if (oRecMandant==null)
		{
		    cErrorMessage = "Mandant undefiniert ! KEIN ZUTRITT ";
		    return;
		}

		
		//ein BASIC_RECORD_USER wird in der session gespeichert, ebenso ein Mandant
		bibALL.setSessionValue("RECORD_USER", 		oRecUser);
		bibALL.setSessionValue("RECORD_MANDANT", 	oRecMandant);
		
		
		//jetzt die nativen werte ID_MANDANT und USER_KUERZEL in die session schreiben
		bibALL.setSessionValue(bibSES_ENUM.ID_MANDANT.toString(), oRecMandant.get_ID_MANDANT_cUF());
		bibALL.setSessionValue(bibSES_ENUM.USER_KUERZEL.toString(), oRecUser.get_KUERZEL_cUF_NN(""));
		

		if (! createSession.fillSESSION_Values(menueDB))
		{
		    cErrorMessage = "Fehler beim Aufbau der Session-Parameter !";
		    return ;
		}
		

		try
		{
			String cAbfrage = DB_META.get_TablesQuery((String)bibALL.getSessionValue("DBKENNUNG"), (String)bibALL.getSessionValue("JAVA_LOGIN"),false,false);
			String[][] cTabellen1		  = menueDB.EinzelAbfrageInArray(cAbfrage, "");
			String[][] cErsetzungsTabelle = new String[cTabellen1.length][2];

			for (int ij = 0; ij < cTabellen1.length; ij++)
			{
				cErsetzungsTabelle [ij] [0] = cTabellen1 [ij] [0];
				cErsetzungsTabelle [ij] [1] = "V" + oRecMandant.get_ID_MANDANT_cUF().trim() + "_" + cTabellen1 [ij] [0].substring(3);
			}

			
			// 2014-04-01
			// Manfred: Alle JT_ werden in V<ID_MANDANT>_ gewandelt...
			cErsetzungsTabelle = new String[][]{{"JT_","V" + oRecMandant.get_ID_MANDANT_cUF().trim() +"_"}};
			
			// Ersetzungstabelle...
			bibALL.setSessionValue("ERSETZUNGSTABELLE", cErsetzungsTabelle);
			
			// jetzt noch die sortierte Ersetzungstabelle
			bibALL.setSessionValue("ERSETZUNGSTABELLE_SORT",menueDB.sort_ErsetzungTableView(cErsetzungsTabelle) );
			
		}
		catch (myException ex)
		{
		    cErrorMessage = "createSession:Error querying Tables of user !";
		    return;
		}

		
		// jetzt die idletime aus der session lesen (user-datenbank-eintrag)
		try
		{
			SES.setMaxInactiveInterval(bibALL.get_RECORD_USER().get_LAUFZEIT_SESSION_lValue(null).intValue());
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			System.out.println("Fehler beim definieren der Sessionlaufzeit !");
		}

		// -------------------
		

		
		
		
//		// ende ersetzungstabellen für die views 
//		// hier werden die zusatzfelder für das objekt oDB erzeugt und übergeben
//		String[][] cZusatzFelder = new String[3][2];
//		cZusatzFelder [0] [0] = "ID_MANDANT";
//		cZusatzFelder [0] [1] = cID_MANDANT;
//		cZusatzFelder [1] [0] = "GEAENDERT_VON";
//		cZusatzFelder [1] [1] = bibALL.MakeSql(cKuerzel);
//		cZusatzFelder [2] [0] = "LETZTE_AENDERUNG";
//		cZusatzFelder [2] [1] = DB_META.get_tStampString((String)bibALL.getSessionValue("DBKENNUNG"));
		

		//nur sicherheitshalber noch drin
		bibALL.setSessionValue("ZUSATZFELDER", DB_STATICS.get_DB_ZusatzFelder_STD());
		menueDB.setZusatzFelder(bibALL.get_DB_ZusatzFelder(true, true, true, oRecMandant.get_ID_MANDANT_cUF().trim(), oRecUser.get_KUERZEL_cUF()));

		
	//	bibALL.getSessionValue("ZUSATZFELDER");

		
		
		//ganz zum schluss muss eine Transaktions-Sperr-Objekt erzeugt und abgelegt werden
		try 
		{
			DB_TransaktionsLock oLock = new DB_TransaktionsLock();
			bibALL.setSessionValue("TRANSAKTIONSLOCK",oLock);
		} 
		catch (myException e) 
		{
			e.printStackTrace();
			this.cErrorMessage = "Fehler beim erzeugen des locking-objektes !";
			return;
		}

		
		
		
		
		
		String cSql = "INSERT INTO  " + cTO + ".JD_LOGIN (NAME,ZEIT,ID_LOGIN) VALUES(" + 
							bibALL.MakeSql(cName.trim()) + ","+
							DB_META.get_tStampString((String)bibALL.getSessionValue("DBKENNUNG"))+
							",SEQ_LOGIN.NEXTVAL)";

		
		if (! menueDB.ExecSQL(cSql,true))
		{
		    cErrorMessage = "Fehler beim Erstellen des Login-Protokolls ...-->>"+cSql;
		}

		// jetzt eine log-datei erstellen für den user
		String outPath     = ((String) bibALL.getSessionValue("OUTPUTPATH")).trim();
		String webRootPath = ((String) bibALL.getSessionValue("WEBROOTPATH")).trim();

		// prüfen, ob outpath rechts oder links einen / hat, wenn ja weg
		if (outPath.substring(outPath.length() - 1).equals("/"))
			outPath = outPath.substring(0, outPath.length() - 1);

		if (outPath.substring(0, 1).equals("/"))
			outPath = outPath.substring(1);

		// prüfen, ob webRootPath rechts oder links einen / hat, wenn ja weg
		if (webRootPath.substring(webRootPath.length() - 1).equals("/"))
			webRootPath = webRootPath.substring(0, webRootPath.length() - 1);

		if (webRootPath.substring(0, 1).equals("/"))
			webRootPath = webRootPath.substring(1);


		oContext.log("Benutzeranmeldung: NAME:  " + cName + "/ Zeit: " + bibALL.get_cDateNOW());
		oContext.log("                   IP:    Client-IP / Server-IP: " + (String) bibALL.getSessionValue("CLIENT_IP_ADRESSE") + "/" + (String) bibALL.getSessionValue("SERVER_IP_ADRESSE"));
		oContext.log("                   IDLE:  Maximale Leerlaufzeit: " + SES.getMaxInactiveInterval());
		oContext.log("Connect-String   : " + bibALL.getSessionValue("JDBC_STRING"));

		// message und translator-objekt
		myTranslator oTRANSLATOR = new myTranslator();

		if (oTRANSLATOR.get_bIS_OK())
		{
			// popup-window-eintrag
			bibALL.setSessionValue("TRANSLATOR", oTRANSLATOR);
//			bibALL.setSessionValue("USERMESSAGE", new MyE2_MessageVector());
			
			
			/*
			 * validierer, der in jedem MyDBToolBox._ExecMultiSql(...) - statemtent beruecksichtigt wird und
			 * nach die mengen der kontrakte ueberwacht
			 *  bibALL.ADD_Global_TRIGGER_AND_VALIDATOR(new FUHRE_STATUS_Daemon());
			 */
			try 
			{
				bibALL.ADD_Global_TRIGGER_AND_VALIDATOR(new FUHREN_SQL_DAEMON_4_VALIDATION());
				bibALL.ADD_Global_TRIGGER_AND_VALIDATOR(new ENDPREIS_CALC_Daemon());
				bibALL.ADD_Global_TRIGGER_AND_VALIDATOR(new INTEGRITY_WATCHER_Daemon());
				bibALL.ADD_Global_TRIGGER_AND_VALIDATOR(new LAGER_KONTO_Daemon());
				bibALL.ADD_Global_TRIGGER_AND_VALIDATOR(new LAGER_KONTO_PREIS_Daemon());
				
		
				bibALL.ADD_Global_TRIGGER_AND_VALIDATOR(new FIBU_Daemon());
				
				//wichtig! der BEWEGUNGSDATEN_PRUEF_Daemon muss ans ende, damit aenderungen, die innerhalb der daemones ausgefuehrt wurde, hier beruecksichtigt werden
				bibALL.ADD_Global_TRIGGER_AND_VALIDATOR(new BEWEGUNGSDATEN_PRUEF_Daemon());
				
				
			} 
			catch (myException e) 
			{
				e.printStackTrace();
				throw new RuntimeException("CreateSession--Error--"+e.getMessage());
 			}

			
			
			
			if (bNoMandatoryObjectsCheck == false){
				/**
				 * global genutzte DB-Objekte die auf jeden Fall in der DB vorhanden sein müssen
				 */
				try{
					
					if (!bib_Settigs_Mandant.IS__Value("BEWEGUNGSSATZ_KEINE_SONDERLAGER_ERZEUGEN", "N", "N"))
					{
						
						Mandatory_DB_Objects_Handler o_MandatoryDBObjectsHandler = new Mandatory_DB_Objects_Handler();
						//
						
//						o_MandatoryDBObjectsHandler.Register_Mandatory_DB_Object(new Mandatory_DB_Object_DATA_Sonderlager("STRECKE", "Streckenlager"));
//						o_MandatoryDBObjectsHandler.Register_Mandatory_DB_Object(new Mandatory_DB_Object_DATA_Sonderlager("MK", "Mengenkorrektur"));
//						o_MandatoryDBObjectsHandler.Register_Mandatory_DB_Object(new Mandatory_DB_Object_DATA_Sonderlager("KH", "Korrektur Hand"));
//						o_MandatoryDBObjectsHandler.Register_Mandatory_DB_Object(new Mandatory_DB_Object_DATA_Sonderlager("UH", "Umbuchung Hand"));
//						o_MandatoryDBObjectsHandler.Register_Mandatory_DB_Object(new Mandatory_DB_Object_DATA_Sonderlager("SW", "Sortenwechsel"));
//						o_MandatoryDBObjectsHandler.Register_Mandatory_DB_Object(new Mandatory_DB_Object_DATA_Sonderlager("MI", "Mixed"));
//						o_MandatoryDBObjectsHandler.Register_Mandatory_DB_Object(new Mandatory_DB_Object_DATA_Sonderlager("LL", "Lager-Lager"));

						// neu mit ENUM
						o_MandatoryDBObjectsHandler.Register_Mandatory_DB_Object(new Mandatory_DB_Object_DATA_Sonderlager(ENUM_SONDERLAGER.STRECKE.db_val(), "Streckenlager"));
						o_MandatoryDBObjectsHandler.Register_Mandatory_DB_Object(new Mandatory_DB_Object_DATA_Sonderlager(ENUM_SONDERLAGER.MK.db_val(), "Mengenkorrektur"));
						o_MandatoryDBObjectsHandler.Register_Mandatory_DB_Object(new Mandatory_DB_Object_DATA_Sonderlager(ENUM_SONDERLAGER.KH.db_val(), "Korrektur Hand"));
						o_MandatoryDBObjectsHandler.Register_Mandatory_DB_Object(new Mandatory_DB_Object_DATA_Sonderlager(ENUM_SONDERLAGER.UH.db_val(), "Umbuchung Hand"));
						o_MandatoryDBObjectsHandler.Register_Mandatory_DB_Object(new Mandatory_DB_Object_DATA_Sonderlager(ENUM_SONDERLAGER.SW.db_val(), "Sortenwechsel"));
						o_MandatoryDBObjectsHandler.Register_Mandatory_DB_Object(new Mandatory_DB_Object_DATA_Sonderlager(ENUM_SONDERLAGER.MI.db_val(), "Mixed"));
						o_MandatoryDBObjectsHandler.Register_Mandatory_DB_Object(new Mandatory_DB_Object_DATA_Sonderlager(ENUM_SONDERLAGER.LL.db_val(), "Lager-Lager"));
						o_MandatoryDBObjectsHandler.Register_Mandatory_DB_Object(new Mandatory_DB_Object_DATA_Sonderlager(ENUM_SONDERLAGER.ZWE.db_val(), "Zwischenlager WE"));
						o_MandatoryDBObjectsHandler.Register_Mandatory_DB_Object(new Mandatory_DB_Object_DATA_Sonderlager(ENUM_SONDERLAGER.ZWA.db_val(), "Zwischenlager WA"));
						o_MandatoryDBObjectsHandler.Register_Mandatory_DB_Object(new Mandatory_DB_Object_DATA_Sonderlager(ENUM_SONDERLAGER.ZWE_UMB.db_val(), "Sonderlager  Zwischenlager"));
						o_MandatoryDBObjectsHandler.Register_Mandatory_DB_Object(new Mandatory_DB_Object_DATA_Sonderlager(ENUM_SONDERLAGER.ZWA_UMB.db_val(), "Ausbuchung Zwischenlager"));
						o_MandatoryDBObjectsHandler.Register_Mandatory_DB_Object(new Mandatory_DB_Object_DATA_Sonderlager(ENUM_SONDERLAGER.ZW_UMB.db_val(), "Umbuchung Zwischenlager"));
						o_MandatoryDBObjectsHandler.Register_Mandatory_DB_Object(new Mandatory_DB_Object_DATA_Sonderlager(ENUM_SONDERLAGER.OFFLG_EIN.db_val(), ENUM_SONDERLAGER.OFFLG_EIN.user_text()));
						o_MandatoryDBObjectsHandler.Register_Mandatory_DB_Object(new Mandatory_DB_Object_DATA_Sonderlager(ENUM_SONDERLAGER.OFFLG_AUS.db_val(), ENUM_SONDERLAGER.OFFLG_AUS.user_text()));
						
						
						
						
						
						
						// Prüfung der DB-Objekte
						boolean dbObjectsOK = o_MandatoryDBObjectsHandler.Check_Mandatory_DB_Objects();
						
						// Handler wird nicht mehr benötigt.
						o_MandatoryDBObjectsHandler = null;
						
					}
					
					
					//trigger separat behandeln
					Mandatory_DB_Objects_Handler  oHandler4Triggers = new Mandatory_DB_Objects_Handler();
					// alle wichtigen Trigger hier einfuegen
					
					//oObjectsHandler4Triggers.Register_Mandatory_DB_Object(new Mandatory_DB_Object_Trigger_ID_ADRESSE_LAGER());
					
					//2013-09-09: 
					oHandler4Triggers.Register_Mandatory_DB_Object(new Mandatory_DB_Object_Trigger_CalcValues_IN_BEWEGUNG_ATOM());
					
					// 2014-02-04 
					oHandler4Triggers.Register_Mandatory_DB_Object(new Mandatory_DB_Object_Trigger_SETZKASTEN_ATOM_LOG());
					
					// 2014-02-04
					oHandler4Triggers.Register_Mandatory_DB_Object(new Mandatory_DB_Object_Trigger_SETZKASTEN_STATION_LOG());
					
					// 2014-04-29
					oHandler4Triggers.Register_Mandatory_DB_Object(new Mandatory_DB_Object_Trigger_SETZKASTEN_VERBUCHT());
					oHandler4Triggers.Register_Mandatory_DB_Object(new Mandatory_DB_Object_Trigger_SETZKASTEN_VERBUCHT_K());
					
					
					
					//2016-12-19: erzeugen eines syscontextes, der es erlaubt variable in die oracle-session zu schreiben
					oHandler4Triggers.Register_Mandatory_DB_Object(new Mandatory_DB_Object_OracleSysContextHandlerPackage());
					
					//2017-01-12: neue pruefobjekte: die funktions fuer den vektor
					oHandler4Triggers.Register_Mandatory_DB_Object(new Mandatory_DB_Object_Function_by_file("START_ATOM", "Function_START_ATOM.txt"));
					oHandler4Triggers.Register_Mandatory_DB_Object(new Mandatory_DB_Object_Function_by_file("ZIEL_ATOM", "Function_ZIEL_ATOM.txt"));
					oHandler4Triggers.Register_Mandatory_DB_Object(new Mandatory_DB_Object_Function_by_file("RECHPOS", "Function_RECHPOS.txt"));
					oHandler4Triggers.Register_Mandatory_DB_Object(new Mandatory_DB_Object_Function_by_file("GUTPOS", "Function_GUTPOS.txt"));

					//2019-02-21: trigger fuer query mit volltext
					oHandler4Triggers.Register_Mandatory_DB_Object(new Mandatory_DB_Object_Function_by_file("STR_NORMALIZE", "Function_STR_NORMALIZE.txt"));
					
					// 2020-01-14: trigger zum Füllen von JT_BG_AUSWERT
					oHandler4Triggers.Register_Mandatory_DB_Object(new Mandatory_DB_Object_Trigger_BG_ATOM_AUSWERT());
					oHandler4Triggers.Register_Mandatory_DB_Object(new Mandatory_DB_Object_Trigger_BG_ATOM_AUSWERT_2());
					
					//2020-07-30: trigger, der beim speichern eines jd_bg_vektors die id_bg_vektor an die jt_bg_atom und jt_bg_station verteilt
					oHandler4Triggers.Register_Mandatory_DB_Object(new Mandatory_DB_Object_Trigger_BG_VEKTOR_SETIDS());
					
					
					boolean bDefTriggersOK = oHandler4Triggers.Check_Mandatory_DB_Objects();
					if (bDefTriggersOK) {
						try {
							oHandler4Triggers.Is_Valid_Mandatory_DB_Objects();   //falls fehlerhaft, dann exception
						} catch (myException e1) {
							e1.printStackTrace();
							cErrorMessage = "Fehler beim Aufbau der notwendigen Datenbank-Objekte ..."+"\n"+S.NN(e1.ZusatzInfo);
							return;
						}
					} else {
						cErrorMessage = "Fehler beim Erstellen und Prüfen der notwendigen Datenbank-Objekte !";
						return;
					}
					oHandler4Triggers = null;		
					
					
					//2017-01-12: sicherstellen, dass die VEKTOR_POS_TYP-enums in der datenbank stehen
					MyE2_MessageVector mv = new MyE2_MessageVector();
					boolean create_ok = ENUM_VEKTORPOS_TYP.LG_MAIN.delete_recs_not_fitting(mv) &&
										ENUM_VEKTORPOS_TYP.LG_MAIN.create_recs_missing(mv);
					if (!create_ok) {
						cErrorMessage = "Fehler beim Ableich der Datenbank-Tabelle <JD_ENUM_VEKTOR_POS_TYP>";
						return;
					}
					
					//2017-01-12: sicherstellen, dass die VEKTOR_POS_TYP-enums in der datenbank stehen
					mv = new MyE2_MessageVector();
					create_ok = ENUM_BEWEGUNGTYP.FAHRPLAN.delete_recs_not_fitting(mv) &&
								ENUM_BEWEGUNGTYP.FAHRPLAN.create_recs_missing(mv);
					if (!create_ok) {
						cErrorMessage = "Fehler beim Ableich der Datenbank-Tabelle <JD_ENUM_BEWEGUNG_TYP>";
						return;
					}
					
					//2017-12-18: neuer view fuer jeden mandanten, der die grundlage der neuen warenbewegungsliste liefert

					//Mandatory_DB_Objects_Handler  oHandler4BGView = new Mandatory_DB_Objects_Handler();
					//TODO: problem am lade, sucht die Tabelle JT_BG_LADUNG 
//					oHandler4BGView.Register_Mandatory_DB_Object(new Mandatory_DB_ObjectCreateView4BG_List("BG_VEKT",oRecMandant.get_ID_MANDANT_cUF()));
					
//					boolean bView4BG = oHandler4BGView.Check_Mandatory_DB_Objects();
//					if (!bView4BG) {
//						cErrorMessage = "Fehler beim Ableich der View fuer den neuen Bewegungssatz !";
//						return;
//
//					}
					
				} catch( Exception ex){
					ex.printStackTrace();
					cErrorMessage = "Fehler in den notwendigen Datenbankobjekten";
					return;
				}
			}
			
			
//			//2016-12-19: hier werden einige DB-Werte gesetzt
			MyE2_MessageVector mv = ENUM_DB_PARAMETER.ID_USER.save_value(bibALL.get_ID_USER());
			mv.add_MESSAGE(ENUM_DB_PARAMETER.ID_MANDANT.save_value(bibALL.get_ID_MANDANT()));
			if (!mv.get_bIsOK()) {
				cErrorMessage = "Fehler beim Definieren der Datenbank-Session-Parameter";
				return;
			}
			
			//2015-05-28: martin
			//hier wird der eintrag von DEBUG_FLAG geloescht und damit durch die aus der usersetting ersetzt
			//System.out.println("Alte flags: <"+bibALL.get_DEBUG_FLAGS()+">");
			String cDEBUG_SETTINGS = S.NN(new E2_UserSetting_TextSaver(ENUM_USER_SAVEKEY.SESSION_HASH_SAVE_DELEVELOPER_DEBUG_LEVEL).get_values());
			bibALL.set_DEBUG_FLAGS(cDEBUG_SETTINGS);
			//jetzt aus den (vorhandenen) usersettings lesen
			//System.out.println("neue Flags: <" +S.NN(bibALL.get_DEBUG_FLAGS())+">");
			
			bSessionOK = true;
		}
		else
		{
			cErrorMessage = "Erstellen des Translationsobjektes !";
		}
		
		
		bibALL.destroy_myDBToolBox(menueDB);

	}

	
	

	
	//~ Methods ============================================================================================================================================================================================================================================================================================

	public String getErrorMessage()
	{
		return (this.cErrorMessage);
	}





	public boolean getSessionOK()
	{
		return (this.bSessionOK);
	}





	// methode schreibt anhand vom username alle relevante benutzerinfos und
	// mandanteninfos in die session
	public static boolean fillSESSION_Values(MyDBToolBox oDB) throws myException
	{
		boolean bRueck = true;

		
		int iFontSize = bibALL.get_RECORD_USER().get_EIGENDEF_SCHRIFTGROESSE_lValue(new Long(12)).intValue();

		// berechnet die fontsize-definition in %
		bibALL.setSessionValue("FONT_SIZE", "" + 		 iFontSize);
		bibALL.setSessionValue("FONT_SIZE_PLUS", "" + 	(iFontSize + 2));
		bibALL.setSessionValue("FONT_SIZE_PPLUS", "" + 	(iFontSize + 4));
		bibALL.setSessionValue("FONT_SIZE_MINUS", ""+ 	(iFontSize - 2));
		bibALL.setSessionValue("FONT_SIZE_MMINUS", ""+ 	(iFontSize - 4));
	
//2015-05-21: ausgelagert wegen adhoc-einlesen	
//		// jetzt noch die button-validierungen des users einlesen
//		String cSQL3 = "SELECT MODULKENNER,BUTTONKENNER " + "FROM " + bibE2.cTO() + ".JD_BUTTON," + bibE2.cTO() + ".JD_BUTTON_USER WHERE JD_BUTTON.ID_BUTTON = JD_BUTTON_USER.ID_BUTTON AND JD_BUTTON_USER.ID_USER=" + bibALL.get_RECORD_USER().get_ID_USER_cUF();
//		String cSQL4 = "SELECT MODULKENNER,BUTTONKENNER " + "FROM " + bibE2.cTO() + ".JD_BUTTON";
//
//		String[][] allowedButtons 	= 	oDB.EinzelAbfrageInArray(cSQL3, "@@@");
//		String[][] allButtons 		=	oDB.EinzelAbfrageInArray(cSQL4, "@@@");
//
//		if (allowedButtons != null && allButtons != null)
//		{
//			
//			String[][] kennerArray = new String[allButtons.length][3];
//			for (int i=0;i<allButtons.length;i++)
//			{
//				kennerArray[i][0]=allButtons[i][0];
//				kennerArray[i][1]=allButtons[i][1];
//				kennerArray[i][2]="N";                 // erstmal nicht erlaubt
//				
//				// jetzt schauen, ob der geprüfte button in allowedbuttons ist
//				for (int k=0;k<allowedButtons.length;k++)
//				{
//					if (allowedButtons[k][0].equals(allButtons[i][0]) &&
//						allowedButtons[k][1].equals(allButtons[i][1]))
//					{
//						kennerArray[i][2]="Y";
//						break;
//					}
//				}
//			}
//			
//			bibALL.setSessionValue("ALLOWEDBUTTONS", kennerArray);
//		}
//		else
//		{
//			bibALL.WriteLogInfoLine("createSession:fillSESSION_Values:Die erlaubten Buttons konnten nicht abgefragt werden !");
//			bRueck = false;
//		}
		return bRueck; 
	}
	
	
	private class ownToolBoxGenerator extends MyDBToolBox_FAB {

		@Override
		public MyDBToolBox generate_INDIVIDUELL_DBToolBox(MyConnection conn) throws myException {
		    MyDBToolBox oDB = new MyDBToolBox(bibALL.get_oConnectionNormal());
		    oDB.set_bErsetzungTableView(false);
	        return oDB;
		}
		
	}
	
	
	
}
