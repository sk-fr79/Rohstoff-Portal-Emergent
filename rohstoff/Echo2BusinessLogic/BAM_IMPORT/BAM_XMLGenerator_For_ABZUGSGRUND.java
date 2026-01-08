package rohstoff.Echo2BusinessLogic.BAM_IMPORT;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.ENUM_MANDANT_ZUSATZ_FELDNAMEN;
import panter.gmbh.basics4project.DB_ENUMS.ABZUGSGRUND;
import panter.gmbh.basics4project.DB_ENUMS.MANDANT;
import panter.gmbh.basics4project.DB_ENUMS.NACHRICHT;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ABZUGSGRUND;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ABZUGSGRUND;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bib_Settigs_Mandant;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.TERM.is_null;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;


/**
 * Generiert ein XML mit den Abzugsgründen, welche in der ANDROID-App Lagerplatz genutzt wird
 * @author manfred
 *
 */
public class BAM_XMLGenerator_For_ABZUGSGRUND {

	private String m_sOutputFile = "";
	
	
	public BAM_XMLGenerator_For_ABZUGSGRUND() {
		
	}
	
	/**
	 * Startet den Export der Abzugsgrund-XML-Datei
	 */
	public void runExport(){
		// ermitteln des Ausgabepfades für die XML-Datei.
		// Das File wird vom Webservice benötigt. Der Dateipafd muss händisch abgeglichen werden
		// in der Rohstoff-App liegt der Dateipfad in den Mandanten-Settings
		

		// Der Exportpfad liegt immer direkt unterhalb des Archivpfades in der Anwendung
		String sBasePath = File.separator + bibALL.get_WEBROOTPATH() +  File.separator + bibALL.get_ARCHIVPATH() + File.separator;
		String sFilename = bib_Settigs_Mandant.get__Value(ENUM_MANDANT_ZUSATZ_FELDNAMEN.EXPORT_PATH_FOR_ABZUGSGRUND_XML.toString(), 	"");
		if (bibALL.isEmpty(sFilename) ){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Der Dateiname für die Export-XML-Datei ist nicht gesetzt. Bitte im Mandantenstamm setzen."));
			return;
		}
		if (sFilename.startsWith(File.separator)) {
			sFilename = sFilename.substring(1);
		}
			
		m_sOutputFile =  sBasePath + sFilename;
		
		String sXML = generateXML();
		
		// speichern des Textes in der Datei
		try {
			org.apache.commons.io.FileUtils.writeStringToFile(new File(m_sOutputFile), sXML,"UTF-8");
			bibMSG.add_MESSAGE(new MyE2_Info_Message("Die XML-Datei der Abzugsgründe wurde für die APP bereit gestellt."));
			
		} catch (IOException e) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Schreiben der XML-Datei."));
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * generiert die XML-Datei
	 * @return
	 *  
	 */
	private String generateXML() {
		SEL sel;
		SEL selMand;
		String sSelect = "";
		String sSelectMand = "";
		String sXML = "";

		
		try {
			sel = new SEL()
					   .ADDFIELD(ABZUGSGRUND.id_abzugsgrund)
					   .ADDFIELD(ABZUGSGRUND.id_mandant)
					   .ADDFIELD(ABZUGSGRUND.abzugsgrund)
					   .ADDFIELD(MANDANT.kurzname)
					   .FROM ( ABZUGSGRUND.T() )
					   .INNERJOIN(MANDANT.T(), MANDANT.id_mandant, ABZUGSGRUND.id_mandant)
					   .WHERE ( new vgl(ABZUGSGRUND.export_erfassung,"Y") );
			
			sSelect = sel.s();
			String[][] sResult = bibDB.EinzelAbfrageInArray(sSelect, false);
		
			selMand = new SEL()
						.ADD_Distinct()
						.ADDFIELD(ABZUGSGRUND.id_mandant)
						.ADDFIELD(MANDANT.kurzname)
						.FROM ( ABZUGSGRUND.T() )
						.INNERJOIN(MANDANT.T(), MANDANT.id_mandant, ABZUGSGRUND.id_mandant)
						.WHERE ( new vgl(ABZUGSGRUND.export_erfassung,"Y") );
		
			sSelectMand = selMand.s();
			String[][] sResultMand = bibDB.EinzelAbfrageInArray(sSelectMand, false);
			
			
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			Document doc = docBuilder.newDocument();
			
			Element rootElement = doc.createElement("abzugsliste");
			doc.appendChild(rootElement);
			
			// mandanten 
			Element mandanten = doc.createElement("mandanten");
	
			for (int i = 0; i < sResultMand.length; i++){
				int col = 0;
				String sID_Mandant 		= sResultMand[i][col++];
				String sKurzname	 	= sResultMand[i][col++];
				
				Element mandant = doc.createElement("mandant");
				
				
				Element id_mandant = doc.createElement("id_mandant");
				id_mandant.appendChild(doc.createCDATASection(sID_Mandant));
				mandant.appendChild(id_mandant);
				
				Element mandant_kurz = doc.createElement("kurzname");
				mandant_kurz.appendChild(doc.createCDATASection(sKurzname));
				mandant.appendChild(mandant_kurz);
			
				// 
				mandanten.appendChild(mandant);
			}
			rootElement.appendChild(mandanten);
			
			
			// Abzuege
			Element abzugliste = doc.createElement("abzuege");
			
			for (int i = 0; i < sResult.length; i++){
				int col = 0;
				String sID_Abzugsgrund 	= sResult[i][col++];
				String sID_Mandant 		= sResult[i][col++];
				String sAbzugsgrund	 	= sResult[i][col++];
				String sKurzname	 	= sResult[i][col++];
				
				Element abzug = doc.createElement("abzug");
				
				Element id_abzugsgrund = doc.createElement("id_abzugsgrund");
				id_abzugsgrund.appendChild(doc.createCDATASection(sID_Abzugsgrund));
				abzug.appendChild(id_abzugsgrund);
				
				Element abzugsgrund = doc.createElement("abzugsgrund");
				abzugsgrund.appendChild(doc.createCDATASection(sAbzugsgrund));
				abzug.appendChild(abzugsgrund);
				
				Element id_mandant = doc.createElement("id_mandant");
				id_mandant.appendChild(doc.createCDATASection(sID_Mandant));
				abzug.appendChild(id_mandant);
				
				Element mandant_kurz = doc.createElement("kurzname");
				mandant_kurz.appendChild(doc.createCDATASection(sKurzname));
				abzug.appendChild(mandant_kurz);
			
				// 
				abzugliste.appendChild(abzug);
			}
			rootElement.appendChild(abzugliste);
			
			
			
			TransformerFactory 	transformFactory 	= TransformerFactory.newInstance();
			Transformer        	transformer			= transformFactory.newTransformer();
			DOMSource			source 				= new DOMSource(doc);
			StringWriter		writer				= new StringWriter();
			
			// pretty print
			transformer.setOutputProperty(OutputKeys.INDENT,"yes");
		
			transformer.transform(source, new StreamResult(writer));
			sXML = writer.getBuffer().toString();
			
		} catch (myException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		
		return sXML;
	}
	
	
	
	@Deprecated
	private String generateXML_old() {
		String sXML = "";
		
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("abzugsliste");
			
			doc.appendChild(rootElement);

			RECLIST_ABZUGSGRUND rlAbzugsgrund = new RECLIST_ABZUGSGRUND("", RECORD_ABZUGSGRUND.FIELD__ABZUGSGRUND);
			
			for (RECORD_ABZUGSGRUND rec: rlAbzugsgrund){
				Element abzug = doc.createElement("abzug");
				
				Element id_abzugsgrund = doc.createElement("id_abzugsgrund");
				id_abzugsgrund.appendChild(doc.createCDATASection(rec.get_ID_ABZUGSGRUND_cUF_NN("-1")));
				abzug.appendChild(id_abzugsgrund);
				
				Element abzugsgrund = doc.createElement("abzugsgrund");
				abzugsgrund.appendChild(doc.createCDATASection(rec.get_ABZUGSGRUND_cUF_NN("-1")));
				abzug.appendChild(abzugsgrund);
				
				Element id_mandant = doc.createElement("id_mandant");
				id_mandant.appendChild(doc.createCDATASection(rec.get_ID_MANDANT_cUF_NN("0")));
				abzug.appendChild(id_mandant);
			
				// 
				rootElement.appendChild(abzug);
			}
			
			TransformerFactory 	transformFactory 	= TransformerFactory.newInstance();
			Transformer        	transformer			= transformFactory.newTransformer();
			DOMSource			source 				= new DOMSource(doc);
			StringWriter		writer				= new StringWriter();
			
			// pretty print
			transformer.setOutputProperty(OutputKeys.INDENT,"yes");
		
			transformer.transform(source, new StreamResult(writer));
			sXML = writer.getBuffer().toString();
			
		} catch (myException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		
		return sXML;
	}

	
}
