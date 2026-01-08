package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT;


import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.Label;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.exceptions.myException;

public class FIBU_EXPORT_LIST_Selector extends E2_ExpandableRow
{
	private E2_SelectionComponentsVector 	oSelVector = null;
	private final Label statusLabel;
	private MyE2_SelectField exportSelectField;
	private final MyE2_Button button;
	
	private final E2_NavigationList navigationList; 
	
	
	public void emptyCache() {
		exports = null;
		incompleteExportMonths = null;
	}
	
	
	/** Cached */
	private ArrayList<HashMap<String, Object>> exports = new ArrayList<HashMap<String, Object>>();
	private ArrayList<HashMap<String, Object>> incompleteExportMonths = new ArrayList<HashMap<String, Object>>();
	
	/** This is where the selection Dropdown is built upon */
	HashMap<String, String> hmWertZuSQL = new HashMap<String, String>();
	ArrayList<HashMap<String, String>> arrBeschriftungZuWert = new ArrayList<HashMap<String,String>>();
	HashMap<String, ExportProperties> hmBeschriftungZuExportProperties = new HashMap<String, ExportProperties>();

	/** 
	 * Creates the correct entries for the Dropdown in the panel; this dropdown contains the list 
	 * of all "open" months which yet contain data to be exported and the list of old exports.
	 * It writes the queried data to the arguments provided, and, if an ID of an export to 
	 * be selected is provided in idSelected param, the index of that selection is returned. We
	 * already build an {@link ExportProperties} object for each entry in the list; for the "open"
	 * months, this object will contain date ranges only. For all exports that already have an ID,
	 * the export id will be contained in that object. 
	 * @param hmWertZuSQL
	 * @param hmBeschriftungZuExportProperties
	 * @param arrBeschriftungZuWert
	 * @param idSelected
	 * @return
	 * @throws myException
	 */
	private int createExportActionSelectListModel(HashMap<String, String> hmWertZuSQL, 
		HashMap<String, ExportProperties> hmBeschriftungZuExportProperties, ArrayList<HashMap<String, String>> arrBeschriftungZuWert, int idSelected) throws myException {
		
		int entryCount = 0;
		if (incompleteExportMonths == null || incompleteExportMonths.size() == 0) {
			try {
				incompleteExportMonths = DBUtil.getAllExportRelevantMonts();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		// The "false" value is for "nothing selected" (minus) or an export set which does not have an ID yet
		hmWertZuSQL.put("false", "EL.ID_EXPORT_LOG = -100");

		// First, build a list of all months which yet contain data to exported. 
		Iterator<HashMap<String, Object>> it = incompleteExportMonths.iterator();
		while (it.hasNext()) {
			HashMap<String, Object> current = it.next();
			
			// The properties object for this entry
			ExportProperties ep = new ExportProperties();
			
			Calendar c = Calendar.getInstance(); // get local calendar with current time
			Calendar now = Calendar.getInstance(); // get local calendar with current time

			// That will be our startdate, 00:00 in the morning
			c.set(Integer.parseInt((String)current.get("JAHR")), Integer.parseInt((String)current.get("MONAT"))-1, 1, 0, 0, 0);
			String currentMonthName = c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.GERMAN) + " " + c.get(Calendar.YEAR);

			ep.setStart(c.getTime());
			
			// We add one month, so we'll get the end of this month
			c.add(Calendar.MONTH, 1);
			c.add(Calendar.SECOND, -1);

			String name = (String)current.get("MONAT")+"/"+(String)current.get("JAHR")+" ("+currentMonthName+")";
			if (!c.before(now)) {
				name+= " (offener Monat)";
				ep.setEnd(now.getTime());
			} else {
				ep.setEnd(c.getTime());
			}
			ep.setName(name);
			
			// For all those "open" months, we do not yet have an EXPORT_ID; the query sting
			// will so be given an invalid/negative id, resulting in an empty list
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(ep.toString(), "false");
			arrBeschriftungZuWert.add(hm);
			entryCount++;

			hmBeschriftungZuExportProperties.put(ep.toString(), ep);

		}
		
		// Now, we loop through all old exports
		if (exports == null || exports.size() == 0) {
			try {
				exports = DBUtil.getOldExports();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		int answer = -1;
		Iterator<HashMap<String, Object>> it2 = exports.iterator();
		while (it2.hasNext()) {
			HashMap<String, Object> entry = it2.next();
			BigDecimal id = (BigDecimal) entry.get("ID_EXPORT_LOG");
			String comments = "";
			String festgeschrieben = "";
			if (entry.get("BEMERKUNGEN") != null) {
				comments = (String) entry.get("BEMERKUNGEN");
				festgeschrieben = (String) entry.get("MATERIALISIERT");
			}
			ExportProperties ep = new ExportProperties();
			ep.setId(id.intValue());
			ep.setStart(new java.sql.Date(((java.sql.Timestamp)entry.get("VON")).getTime()));
			ep.setEnd(new java.sql.Date(((java.sql.Timestamp)entry.get("BIS")).getTime()));
			ep.setIsMaterialized(festgeschrieben != null && festgeschrieben.equals("Y"));
			ep.setName(comments);
			// The SQL query will be fed with the coorect ID
			hmWertZuSQL.put("" + ep.getId(), "EL.ID_EXPORT_LOG = " + ep.getId());
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(ep.toString(), "" + ep.getId());
			arrBeschriftungZuWert.add(hm);
			entryCount++;

			// Save selected index
			if (id.intValue() == idSelected) {
				//System.out.println("We generated "+idSelected+", this is "+entryCount);
				answer = entryCount;
			}

			hmBeschriftungZuExportProperties.put(ep.toString(), ep);
		}
		return answer;
	}

	public E2_NavigationList getNavigationList() {
		return navigationList;
	}
	
	/** This will create the SelectField (the Dropdown) */
	private MyE2_SelectField buildSelectField(int idSelected) throws myException {
		createExportActionSelectListModel(hmWertZuSQL, hmBeschriftungZuExportProperties, arrBeschriftungZuWert, 0);

		// Erstelle neues Selectfield, gebe Beschriftungen und SQL-Werte dazu, füge am Anfang eine Default-Option ein
		exportSelectField = new MyE2_SelectField(bibARR.get_Array_KeyValue(arrBeschriftungZuWert, new String[][]{{"-", "false"}}),"",false);
		E2_ListSelectorStandard oSelektor = new E2_ListSelectorStandard(exportSelectField, hmWertZuSQL, new MyE2_String(""),100);
		
		exportSelectField.EXT().set_O_PLACE_FOR_EVERYTHING(hmBeschriftungZuExportProperties);
		exportSelectField.add_oActionAgent(new AASetButtonPropertiesForSelection());
		oSelVector.add(oSelektor);
		return exportSelectField;
	}
	
	/** This will refresh the SelectField (the Dropdown */
	public void refreshSelectField(int idSelected) throws myException {
		if (idSelected == -1) {
			// nach dem Löschen einzelner VPOS muss die Liste aktualisiert werden; die Export-Set-ID
			// ist dort auf -1 gesetzt und muss hier wieder korrekt gesetzt werden!
			ExportProperties ep = (ExportProperties) button.EXT().get_O_PLACE_FOR_EVERYTHING();
			idSelected = ep.getId();
		}
		
		// Reset cached data
		exports = new ArrayList<HashMap<String, Object>>();
		incompleteExportMonths = new ArrayList<HashMap<String, Object>>();
		
		// We empty our caches
		HashMap<String, String> hmWertZuSQL = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> arrBeschriftungZuWert = new ArrayList<HashMap<String,String>>();
		HashMap<String, ExportProperties> hmBeschriftungZuExportProperties = new HashMap<String, ExportProperties>();
		
		// Erzeuge mir meine Dropdown-Beschrifungen und Konditionen
		int index = createExportActionSelectListModel(hmWertZuSQL, hmBeschriftungZuExportProperties, arrBeschriftungZuWert, idSelected);

		// Erstelle neues Selectfield, gebe Beschriftungen und SQL-Werte dazu, füge am Anfang eine Default-Option ein
		exportSelectField.set_ListenInhalt(bibARR.get_Array_KeyValue(arrBeschriftungZuWert, new String[][]{{"-", "false"}}), false);

		exportSelectField.EXT().set_O_PLACE_FOR_EVERYTHING(hmBeschriftungZuExportProperties);
		
		// Update selector
		E2_ListSelectorStandard oSelektor = (E2_ListSelectorStandard) oSelVector.get(0);
		oSelektor.set_hmValuePlusWhereblock(hmWertZuSQL);
		exportSelectField.setSelectedIndex(index);

		// Fire action agents (they trigger an update of the list according to the selection)
		Vector<XX_ActionAgent>	vaa = exportSelectField.get_vActionAgents();
		for (XX_ActionAgent aa : vaa) {
			aa.executeAgentCode(null);
		}
				
		//exportSelectField.add_oActionAgent(new AASetButtonPropertiesForSelection());
	}
	
	
	public FIBU_EXPORT_LIST_Selector(final E2_NavigationList oNavigationList) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		navigationList = oNavigationList;
		
		this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);

		exportSelectField = buildSelectField(0);
		MyE2_Row oGridInnen = new MyE2_Row();
		this.add(oGridInnen);
		
		oGridInnen.add(new MyE2_Label("Aktion auswählen:"), new Insets(4,2,20,2));
		oGridInnen.add(exportSelectField, new Insets(4,2,15,2));
		
		button = new MyE2_Button(new MyE2_String("(Aktion auswählen)"));
		button.setEnabled(false);
		// Action-Agenten für Button
		button.add_oActionAgent(new AAButtonDoExportClicked());
		button.add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				oNavigationList._REBUILD_COMPLETE_LIST("");
			}
		});
		oGridInnen.add(button);
		statusLabel = new MyE2_Label("");
		oGridInnen.add(statusLabel);
	}


		

	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}

	/**
	 * Action agent that is run once the SelectField (dropdown) changes.
	 */
	private class AASetButtonPropertiesForSelection extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if (S.isFull(exportSelectField.get_ActualWert())) {
				
				String selectionKey = exportSelectField.get_ActualView();
				@SuppressWarnings("unchecked")
				HashMap<String, ExportProperties> hmEP = (HashMap<String, ExportProperties>) exportSelectField.EXT().get_O_PLACE_FOR_EVERYTHING();
				
				ExportProperties currentSelected = hmEP.get(selectionKey);
				
				//System.out.println("currentSelected "+currentSelected);
				
				if (selectionKey.equals("-")) {
					button.setText("(Aktion auswählen)");
					button.setEnabled(false);
				} else if (currentSelected != null && currentSelected.getId() > 0) {
					// Export ist bereits ausgewählt und hat schon eine ID
					button.setText("Export "+(currentSelected.isMaterialized() ? "erneut " : "")+"festschreiben");
					button.setEnabled(true);
					
				} else if (currentSelected != null && currentSelected.getId() == 0) {
					// Export muss erst noch erzeugt werden; Zeitraum ergibt sich aus
					// den in den ExportProperties gespeicherten Parametern (Daten)
					button.setText("Export erzeugen");
					button.setEnabled(true);
				}
				
				//System.out.println(currentSelected.getStart()+" bis "+currentSelected.getEnd());
				
				// The selection ExportProperties is passed to the button
				button.EXT().set_O_PLACE_FOR_EVERYTHING(currentSelected);
			} else {
			}
			
		}
	}
	
	/**
	 * Action agent that runs once the button is clicked.
	 */
	private class AAButtonDoExportClicked extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			button.setEnabled(false);
			statusLabel.setForeground(new Color(0, 0, 0));
			statusLabel.setText("Export gestartet.");
			String messageText;
			ExportProperties ep = (ExportProperties) button.EXT().get_O_PLACE_FOR_EVERYTHING();
			int oldId = ep.getId();
			try {
				if (ep != null) {
					System.out.println("Doing export with id "+ep.getId()+ "("+ep.toString()+")");
					new DatevExporter().setExportProperties(ep).start();
					if (ep.getFileName() == null  || ep.getFileName().equals("")) {
						messageText = "Export mit ID "+ep.getId()+" erfolgreich erzeugt.";
					} else {
						messageText = "Export mit ID "+ep.getId()+" erfolgreich geschrieben in "+ep.getFileName();
					}
					//statusLabel.setText(messageText);
					//statusLabel.setForeground(new Color(0, 200, 200));
					bibMSG.add_MESSAGE(new MyE2_Info_Message(messageText));
					// Update select field and put selection on the just generated export set (via id)
					refreshSelectField(ep.getId());
				}
			} catch (Exception ex) {
				if (ep.getId() != oldId) {
					System.out.println("Rollback für ID"+ep.getId());
					DBUtil.deleteExport(ep.getId());
					ep.setId(0);
					ep.setFileName(null);
				}
				ex.printStackTrace();
				messageText = "Export beendet mit Fehler: "+ex.getMessage();
				//statusLabel.setText(messageText);
				//statusLabel.setForeground(new Color(255, 0, 0));
				bibMSG.add_MESSAGE(new MyE2_Warning_Message(messageText));

			}
			statusLabel.setText("");
			button.setEnabled(true);
		}
	}
		
}
