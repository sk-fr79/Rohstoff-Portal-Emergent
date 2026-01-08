/**
 * rohstoff.Echo2BusinessLogic.INTRASTAT.XML
 * @author manfred
 * @date 06.11.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.INTRASTAT.XML;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_Download;
import panter.gmbh.basics4project.ENUM_MANDANT_CONFIG;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.BasicRecords.BASIC_RECORD_MANDANT;
import panter.gmbh.basics4project.DB_ENUMS.INTRASTAT_MELDUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.INTRASTAT.INSTAT_Handler.ENUM_IntrastatType;

/**
 * Erzeugt die Intrastat-Datei/en
 * @author manfred
 * @date 06.11.2020
 *
 */
public class Instat_XML_Handler {

	INSTAT _instat = null;
	String _sMaterialnummer = ""; 
	String _sUnterscheidungsNr = "";
	String _sNachrichtenID = "";
	
	String _bundesland_fa = "";
	String _SteuerNr = "";
	
	private Integer 	_year = null;
	private String 	 	_sYear = null;
	private Integer 	_month = null;
	
	private Integer 	_zaehler = null;
	private ENUM_IntrastatType _type = null;
	
	private String m_sMonat;
	private String m_sJahr;

	
	
	public Instat_XML_Handler() {
		
		_instat = new INSTAT();
				
		// Materialnummer lesen
		_sMaterialnummer = ENUM_MANDANT_CONFIG.INTRASTAT_XML_MATERIALNUMMER.getCheckedValue();
		_sUnterscheidungsNr = StringUtils.right("000" + ENUM_MANDANT_CONFIG.INTRASTAT_XML_UNTERSCHEIDUNGSNUMMER.getCheckedValue(),3);
		
	}
	
	
	
	
	
	/**
	 * Exportiert die Meldungen in eine Datei
	 * @author manfred
	 * @date 10.11.2020
	 *
	 * @param year
	 * @param month
	 * @param zaehler
	 * @param type
	 * @throws myException
	 */
	public void exportIntrastatRecordsToXML(int year, int month, int zaehler, ENUM_IntrastatType type) throws myException
	{
		
		// Materialnummer muss angegeben werden 
		if (S.isEmpty(_sMaterialnummer)) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Die Intrastat-Materialnummer ist nicht gesetzt. Der Export kann nicht durchgeführt werden."));
			return;
		}
		
		

		_year = year;
		_month = month;
		_zaehler = zaehler;
		_type = type;
		
		m_sMonat 	= String.format("%02d", _month);
		m_sJahr 	= String.format("%4d",	_year);
		
		// Nachrichten-Schlüssel für Dateinamen und Interne Kennung setzen...
		createNachrichtenID();

		
		SqlStringExtended _sqlExtended ;
		VEK<ParamDataObject> _sqlParam = new VEK<ParamDataObject>();
		
		
		String sSQLMain  = 	" SELECT * FROM  JT_INTRASTAT_MELDUNG WHERE ANMELDEMONAT = ? " + //'" + m_sMonat + "' " +
							" AND ANMELDEJAHR = ? " + //'" + m_sJahr + "'" +
							" AND NVL(NICHT_MELDEN,'N') = 'N' ";
		
		_sqlParam._a(new Param_String("", m_sMonat))
				 ._a(new Param_String("", m_sJahr));
		
		
		// Meldeart ...
		String sWhereType = " AND MELDEART = '1'";  
		if (type.equals(ENUM_IntrastatType.ENUM_Export)){
			sWhereType = " AND MELDEART = '2'";
		}
		sWhereType += " AND NVL(NICHT_MELDEN,'N') = 'N' ";
		
		
		// export in abhängigkeit vom ausgewählten Zählerstand
		String sZaehlerWhere = "";
		if (zaehler > 0){
			sZaehlerWhere += " AND ZAEHLER_MELDUNG = ?"; //+ String.valueOf(zaehler);
			_sqlParam._a(new Param_Long(zaehler));
		} else {
			sZaehlerWhere += " AND ZAEHLER_MELDUNG IS NULL ";
		}
		
		
		_sqlExtended = new SqlStringExtended(sSQLMain + sWhereType +  sZaehlerWhere + " ORDER BY PAGINIERNUMMER ")
						._addParameters(_sqlParam);
		
		// Ergebnisse lesen 
		RecList21 oList = new RecList21(_TAB.intrastat_meldung)._fill(_sqlExtended);
		
		
		
		// Prüfung, ob noch Intrastat-Meldunge mit Menge oder Preis == 0 vorhanden sind. Diese müssen Korrigiert werden (Schätzwert)
		for (Rec21 o: oList){
			if (o.get_ufs_dbVal(INTRASTAT_MELDUNG.eigenmasse,"0").equals("0") ||
				(o.get_ufs_dbVal(INTRASTAT_MELDUNG.rechbetrag,"0").equals("0") && o.get_ufs_dbVal(INTRASTAT_MELDUNG.export_no_price,"N").equals("N") )
				){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es gibt noch Intrastat-Sätze, die einen 0-Betrag oder eine 0-Menge haben. Bitte Schätzwert eintragen, 0-Preis bestätigen oder löschen! "));
				break;
			}
		}

		// Prüfung, ob noch Intrastat-Meldunge vorhanden sind, die keine Warennummer haben
		for (Rec21 o: oList){
			if (o.get_ufs_dbVal(INTRASTAT_MELDUNG.warennr)== null || o.get_ufs_dbVal(INTRASTAT_MELDUNG.warennr).trim().equals(""))
				{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es gibt noch Intrastat-Sätze, die keine Warennummer haben. Bitte prüfen Sie die Einträge und korrigieren Sie den Wert in der Fuhre oder dem Artikelstamm."));
				break;
			}
		}

		// Prüfung, ob Intrastat-Meldungen noch keine USTID haben
		for (Rec21 o: oList){
			if (o.get_ufs_dbVal(INTRASTAT_MELDUNG.umsatzsteuerid,"").trim().equals("") || o.get_ufs_dbVal(INTRASTAT_MELDUNG.umsatzsteuer_lkz,"").trim().equals(""))	{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es gibt noch Intrastat-Sätze, die keine vollständige UST-LKZ / UST-ID haben. Bitte prüfen Sie die Einträge und korrigieren Sie den Wert in der Fuhre oder dem Artikelstamm."));
				break;
			}
		}
		
		
		if (oList.size() == 0) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es gibt keine Intrastat-Sätze."));
		}
		
		// Wenn aus der Prüfung ein Alarm kommt, beenden!
		if (bibMSG.get_bHasAlarms())
		{
			return;
		}
		
		
		//
		// Die XML-Struktur erzeugen...
		//
		_fill_INSTAT_Structure(oList);
		
		
		
		// Dateinamen lesen aus dem Mandanten:
		String sFileName = _sNachrichtenID + ".xml";
		myTempFile oFile = new myTempFile(_sNachrichtenID , ".tmp", false);
		
		
		
		// xml-document erzeugen
		DocumentHelper docHelper;
		Document doc;
		Element element;
		doc = DocumentHelper.createDocument();
		doc.setXMLEncoding("ISO-8859-1");
		doc.add(_instat.getElement());
		
		OutputFormat outputFormat = OutputFormat.createPrettyPrint();
		outputFormat.setEncoding("ISO-8859-1");
		XMLWriter writer;
		try {
			
			writer = new XMLWriter(new FileWriter(oFile.getFile()),outputFormat);
			
			//writer = new XMLWriter(System.out,outputFormat);
			writer.write(doc);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		
//		oFile.WriteTextBlock(sb.toString());
		oFile.close();
		E2_Download oDown = new E2_Download();
		oDown.starteFileDownload(oFile.getFileName(),sFileName ,"text/plain");

		
		//
		// dann noch das Exportdatum setzen und den Meldezähler, für die die noch nicht exportiert wurden
		// setzen
		SqlStringExtended sqlZaehler = new SqlStringExtended(sSQLMain.replace("*", "max(nvl(ZAEHLER_MELDUNG,0)) + 1 ") + sWhereType);
		sqlZaehler	._addParameter(new Param_String("", m_sMonat))
					._addParameter(new Param_String("", m_sJahr));
		
		String[][] sResZaehler ;
		String sZaehler;
		sResZaehler = bibDB.EinzelAbfrageInArray(sqlZaehler );
		if (sResZaehler == null || sResZaehler.length ==0){
			sZaehler = "1";
		} else {
			sZaehler = sResZaehler[0][0];
		}
		
		String sUpdate = "UPDATE  " + bibALL.get_TABLEOWNER() + ".JT_INTRASTAT_MELDUNG " +
				" SET EXPORTIERT_AM = trunc(SYSDATE,'DD') ," +
				" ZAEHLER_MELDUNG = " + sZaehler +
				" WHERE ANMELDEMONAT = '" + m_sMonat + "' " +
				" AND ANMELDEJAHR = '" + m_sJahr + "'" + 
				sWhereType +
				" AND EXPORTIERT_AM IS NULL ";
		
		bibDB.ExecSQL(sUpdate, true);

	}

	
	
	/**
	 * erzeugt die Nachrichten-ID in der FORM
	 * matnr + '-'+refbzr+'-'+datum+'-'+uhrzeit
	 * matnr wie vergeben
	 * refbzr ist der Meldezeitraum, d.h. der Meldemonat: jjjjmm
	 * datum in jjjjmmtt
	 * uhrzeit in hhmm
	 * @author manfred
	 * @date 10.11.2020
	 */
	private void createNachrichtenID() {
		String _SessionToken = "@TEMP_VAR_INTRASTAT_NACHRICHTEN_ID#";
		String ref = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd-HHmm");
		ref += _sMaterialnummer;
		ref += "-";
		ref += m_sJahr + m_sMonat;
		ref += "-";
		
		
		// timepart of ID
		LocalDateTime n = LocalDateTime.now();
		DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyyMMdd-HHmm");
		
		String sTimePart= dtFormat.format(n);
				
		// prüfen, ob die Sessin-Variable schon gesetzt war, und ob es der gleiche Wert war..
		String _oldRef = (String)bibSES.getSessionValue(_SessionToken);
		
		// Werte vergleichen....
		if(_oldRef != null ) {
			while(_oldRef.compareTo(ref+sTimePart) >= 0 ) {
				n= n.plus(Duration.ofMinutes(1));
				sTimePart = dtFormat.format(n);
			}
		}
		
		ref += sTimePart;
		_sNachrichtenID =  ref;

		// um zu verhindern, dass 2 gleiche Nachrichten in der gleichen Minute erzeugt werden (einfuhr/ausfuhr) wird beim erstellen eine Session-Variable geschrieben.
		bibSES.setSessionValue(_SessionToken, _sNachrichtenID);
	}
	
	/**
	 * prüft, ob im XML-Format gemeldet werden soll...
	 * @author manfred
	 * @date 10.11.2020
	 *
	 * @return
	 */
	private boolean isActive() {
		boolean bRet = false;
		try {
			bRet = ENUM_MANDANT_DECISION.INTRASTAT_XML_FORMAT.is_YES();
		} catch (myException e) {
			bRet = false;
		}
		return bRet;
	}
	
	
	
	

	
	/**
	 * erzeugen der XML-Struktur
	 * @author manfred
	 * @date 10.11.2020
	 *
	 * @param oList
	 * @throws myException
	 */
	private void _fill_INSTAT_Structure( RecList21 oList  ) throws myException {
		// Basiswerte aus erstem Satz der Liste ermitteln...
		if (oList.size() > 0) {
			try {
				Rec21 r = oList.get(0);
				_bundesland_fa = r.get_ufs_dbVal(INTRASTAT_MELDUNG.bundesland_fa);
				_SteuerNr = r.get_ufs_dbVal(INTRASTAT_MELDUNG.steuernr);
			} catch (myException e1) {
				e1.printStackTrace();
			}
		}
		
		//
		// ENVELOPE...
		//
		Envelope e = _instat.get_Envelope();
		e.get_envelopeId().setText(_sNachrichtenID);
		Date dtNow = new Date();
		
		
		e.get_DateTime().get_Date().setText(new SimpleDateFormat("yyyy-MM-dd").format(dtNow));
		e.get_DateTime().get_Time().setText(new SimpleDateFormat("HH:mm:ss").format(dtNow));
		
		// Party Receiver
		e.set_Party_receiver(createPartyReceiver());
		
		// Party Sender
		e.set_Party_sender	(createPartySender());
		
		// wenn testindicator, dann schalter setzen
		if (_sMaterialnummer.equalsIgnoreCase("XGTEST")) {
			e.get_testIndicator().setText("true");
		}
		
		e.get_softwareUsed().setText("PDRohstoffPortal");
				
		// DECLARATION...
		Declaration d = _instat.get_Envelope().get_Declaration();
		d.get_DateTime().get_Date().setText(new SimpleDateFormat("yyyy-MM-dd").format(dtNow));
		d.get_DateTime().get_Time().setText(new SimpleDateFormat("HH:mm:ss").format(dtNow));
		d.get_referencePeriod().setText(m_sJahr + "-" + m_sMonat);
		d.get_PSIId().setText(createPartyId_Sender());
		d.get_declaration_id().setText("1");
		d.get_flowCode().setText(_type.equals(ENUM_IntrastatType.ENUM_Import) ? "A" : "D");
		d.get_currencyCode().setText("2"); // IMMER 2 == EUR
		
		
		// ITEMS
		Rec21_Intrastat_Meldung oRec;
		
		for (int k=0;k<oList.size();k++)
		{
			try {
				oRec = new Rec21_Intrastat_Meldung (oList.get(k));
				
				// ITEM erzeugen...
				Item item = new Item().fillFrom(oRec);
				
				// und anhängen...
				e.get_Declaration().addItem(item);
				
			} catch (Exception ex) {
			}
		}
		
	}
	
	
	

	/**
	 * erzeugt die PartyID des Senders aus Steuer-Daten und Unterscheidungsnummern
	 * @author manfred
	 * @date 11.11.2020
	 *
	 * @return
	 */
	private String createPartyId_Sender() {
		String sAdd = (_SteuerNr.length() == 10 ? "0" : "");
		String sPartyID = _bundesland_fa + _SteuerNr + sAdd + _sUnterscheidungsNr;
		return sPartyID;
	}
	

	
	
	/**
	 * Party-Receiver immer die Destatis, FIX
	 * @author manfred
	 * @date 10.11.2020
	 *
	 * @return
	 */
	private Party createPartyReceiver() {
		Party p = new Party();
		// Attribute
		p.set_attribute_partyRole("receiver");
		p.set_attribute_partyType("CC");
		// Felder
		p.get_partyID().setText("00");
		p.get_partyName().setText("Statistisches Bundesamt");
		p.get_Address().get_streetName().setText("Gustav-Stresemann-Ring 11");
		p.get_Address().get_postalCode().setText("65189");
		p.get_Address().get_cityName().setText("Wiesbaden");
		return p;
	}
	
		
	
	/**
	 * Party des Melders..
	 * @author manfred
	 * @date 10.11.2020
	 *
	 * @return
	 * @throws myException 
	 */
	private Party createPartySender() throws myException {
		BASIC_RECORD_MANDANT recMandant = bibALL.get_RECORD_MANDANT();
		
		
		Party p = new Party();
		p.set_attribute_partyRole("sender");
		p.set_attribute_partyType("PSI");
		p.get_partyID().setText(createPartyId_Sender());
		
		p.get_partyName().setText(recMandant.get_NAME1_cF());
		p.get_Address().get_streetName().setText(recMandant.get_STRASSE_cUF());
		p.get_Address().get_postalCode().setText(recMandant.get_PLZ_cF());
		p.get_Address().get_cityName().setText(recMandant.get_ORT_cF());
		p.get_Address().get_countryName().setText(recMandant.get_LANDKURZ_cF());
		
		return p;
	}
	
	
	
	
	
	
	
	
	


}
