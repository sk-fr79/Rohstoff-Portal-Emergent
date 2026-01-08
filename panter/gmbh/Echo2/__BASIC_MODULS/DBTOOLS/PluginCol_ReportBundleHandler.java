package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.reportBundle.ReportBundlePacker;
import panter.gmbh.indep.reportBundle.ReportBundleUnpacker;

public class PluginCol_ReportBundleHandler extends Basic_PluginColumn
{

	//layoutgrid
	MyE2_Grid m_gridReportselection = null;
	
	Vector<reportCheckBox> vCheckboxesExport = new Vector<PluginCol_ReportBundleHandler.reportCheckBox>();
	Vector<reportCheckBox> vCheckboxesExportMandant = new Vector<PluginCol_ReportBundleHandler.reportCheckBox>();
	MyE2_Column colReportsExport = new MyE2_Column();
	
	Vector<reportCheckBox> vCheckboxesImport = new Vector<PluginCol_ReportBundleHandler.reportCheckBox>();
	Vector<reportCheckBox> vCheckboxesImportMandant = new Vector<PluginCol_ReportBundleHandler.reportCheckBox>();
	MyE2_Column colReportsImport = new MyE2_Column();
	
	MyE2_Button btnExport = null;
	MyE2_Button btnImport = null;

	MyE2_CheckBox cbOverride = new MyE2_CheckBox(new MyE2_String("Reports überschreiben"));
	MyE2_TextField tfMandant = new MyE2_TextField(bibALL.get_ID_MANDANT(),20,2);
	 
	
	
	public PluginCol_ReportBundleHandler(ContainerForVerwaltungsTOOLS oMothercontainer)
	{
		super(oMothercontainer);
		
		m_gridReportselection = new MyE2_Grid(2);
		m_gridReportselection.add(colReportsExport);
		m_gridReportselection.add(colReportsImport);

		btnExport = new MyE2_Button("Reports exportieren...");
		btnExport.add_oActionAgent(new exportAgent());
		btnImport = new MyE2_Button("Reports importieren...");
		btnImport.add_oActionAgent(new importAgent());

		
		
		this.generateCheckboxesForExport();
		this.generateCheckboxesForImport();
		
		this.add(m_gridReportselection);

		this.add(this.get_TextArea4Output(), ContainerForVerwaltungsTOOLS.INSETS_INPUT);
		
	}
	
	
	
	
	
	
	private void generateCheckboxesForExport(){

		colReportsExport.removeAll();
		vCheckboxesExport.removeAllElements();
		vCheckboxesExportMandant.removeAllElements();
		
		String sPathListenAlle = bibALL.get_REPORTPATH_LISTEN_ALLE();
		String sPathListenMandant = bibALL.get_REPORTPATH_LISTEN_MANDANT();
		
		MyE2_Button btnSelectAllAll = new MyE2_Button("Alle Selektieren");
		btnSelectAllAll.add_oActionAgent(new selectAllAgent(vCheckboxesExport, true));
		
		MyE2_Button btnSelectAllMandant = new MyE2_Button("Alle Selektieren");
		btnSelectAllMandant.add_oActionAgent(new selectAllAgent(vCheckboxesExportMandant, true));
		
		MyE2_Button btnDeSelectAllAll = new MyE2_Button("Keine Selektieren");
		btnDeSelectAllAll.add_oActionAgent(new selectAllAgent(vCheckboxesExport, false));
		
		MyE2_Button btnDeSelectAllMandant = new MyE2_Button("Keine Selektieren");
		btnDeSelectAllMandant.add_oActionAgent(new selectAllAgent(vCheckboxesExportMandant, false));
		
		
		colReportsExport.add(new MyE2_Label(new MyE2_String("EXPORT Listen für den aktuellen Mandanten")));
		colReportsExport.add(btnSelectAllMandant);
		colReportsExport.add(btnDeSelectAllMandant);
		getFilesForList(sPathListenMandant, ".jrxml", colReportsExport, vCheckboxesExportMandant,null);

		colReportsExport.add(new MyE2_Label(new MyE2_String(".")));

		colReportsExport.add(new MyE2_Label(new MyE2_String("EXPORT Listen für alle Mandanten")));
		colReportsExport.add(btnSelectAllAll);
		colReportsExport.add(btnDeSelectAllAll);
		getFilesForList(sPathListenAlle, ".jrxml", colReportsExport, vCheckboxesExport, vCheckboxesExportMandant);

		colReportsExport.add(new MyE2_Label(new MyE2_String(".")));
		colReportsExport.add(btnExport);

	}

	
	
	
	private void generateCheckboxesForImport(){
		colReportsImport.removeAll();
		vCheckboxesImport.removeAllElements();
		vCheckboxesImportMandant.removeAllElements();
		
		String sPathListenAlle = bibALL.get_REPORTPATH_LISTEN_ALLE() + ReportBundleUnpacker.REPORT_BUNDLE_ZIP_PATH  ;
		String sPathListenMandant = bibALL.get_REPORTPATH_LISTEN_MANDANT() + ReportBundleUnpacker.REPORT_BUNDLE_ZIP_PATH;

		MyE2_Button btnSelectAllAll = new MyE2_Button("Alle Selektieren");
		btnSelectAllAll.add_oActionAgent(new selectAllAgent(vCheckboxesImport, true));
		
		MyE2_Button btnSelectAllMandant = new MyE2_Button("Alle Selektieren");
		btnSelectAllMandant.add_oActionAgent(new selectAllAgent(vCheckboxesImportMandant, true));
		
		MyE2_Button btnDeSelectAllAll = new MyE2_Button("Keine Selektieren");
		btnDeSelectAllAll.add_oActionAgent(new selectAllAgent(vCheckboxesImport, false));
		
		MyE2_Button btnDeSelectAllMandant = new MyE2_Button("Keine Selektieren");
		btnDeSelectAllMandant.add_oActionAgent(new selectAllAgent(vCheckboxesImportMandant, false));
		

		
		colReportsImport.add(new MyE2_Label(new MyE2_String("IMPORT Listen für den aktuellen Mandanten")));
		colReportsImport.add(btnSelectAllMandant);
		colReportsImport.add(btnDeSelectAllMandant);
		getFilesForList(sPathListenMandant, ".zip", colReportsImport, vCheckboxesImportMandant,null);

		colReportsImport.add(new MyE2_Label("."));
		colReportsImport.add(new MyE2_Label(new MyE2_String("IMPORT Listen für alle Mandanten")));
		colReportsImport.add(btnSelectAllAll);
		colReportsImport.add(btnDeSelectAllAll);
		getFilesForList(sPathListenAlle, ".zip", colReportsImport, vCheckboxesImport, null);
		
		
		colReportsImport.add(new MyE2_Label("."));
		colReportsImport.add(new MyE2_Label("Einstellungen für den Import"));
		colReportsImport.add(cbOverride);
		colReportsImport.add(tfMandant);
		
		colReportsImport.add(btnImport);
	
	}

	
	
	
	private void getFilesForList(String sPath,String sType, MyE2_Column col, Vector<reportCheckBox> vCB ,Vector<reportCheckBox> vCBExclude){
		// öffnen des Ordners
		File d = new File(sPath);
		
		// lesen der Dateien im Ordner
		String[] entries = d.list();
		if (entries != null){
			java.util.Arrays.sort(entries);
		}
		
		
		// falls der Ordner nicht existiert
		if (entries == null) return;
		
		
		boolean bSkip = false;
		
		// Loop through all entries in the directory
		for (int i = 0; i < entries.length; i++) {
			
			File f = new File(d, entries[i]);
			if(f.isFile() && f.getName().contains(sType)){
				bSkip = false;
				// prüfen, ob der Dateiname in der Ausschluss-Liste drin ist (nicht optimiert)
				if (vCBExclude != null){
					Iterator<reportCheckBox> iter = vCBExclude.iterator();
					while (iter.hasNext()){
						reportCheckBox cb = iter.next();
						if (cb.getName().equals(f.getName())){
							bSkip = true;
							break;
						}
					}
				}
				// falls der Report schon im Mandant vorkommt, dann nicht mehr im ALLE listen, sonst passen die Parameter unter Umständen dazu.
				if (bSkip) continue;
				
				reportCheckBox cb = new reportCheckBox(f.getName(), sPath + f.getName());
				vCB.add(cb);
				col.add(cb);
			}
		}
		
	}
	
	
	

	private class exportAgent extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			PluginCol_ReportBundleHandler oThis = PluginCol_ReportBundleHandler.this;
			ReportBundlePacker packer = null;

			
			// ALLE REPORTS
			Iterator<reportCheckBox> iter = oThis.vCheckboxesExport.iterator();
			while (iter.hasNext()){
				reportCheckBox box = iter.next();
				if (box.isSelected()){
					packer = new ReportBundlePacker();
					
					
					try {
						packer.InitReportPackerFromReportFile(box.getPath());
						packer.PackBundle();
						
						oThis.get_TextArea4Output().setText( oThis.get_TextArea4Output().getText() + "\n" + packer.getMessagesAsString());
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			
			// MANDANTEN REPORTS
			iter = oThis.vCheckboxesExportMandant.iterator();
			while (iter.hasNext()){
				
				reportCheckBox box = iter.next();
				if (box.isSelected()){
					
					packer = new ReportBundlePacker();
					packer.InitReportPackerFromReportFile(box.getPath());
					
					try {
						packer.PackBundle();
						
						oThis.get_TextArea4Output().setText( oThis.get_TextArea4Output().getText() + "\n" + packer.getMessagesAsString());
						

						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			oThis.generateCheckboxesForExport();
			oThis.generateCheckboxesForImport();
		}
		
	}

	
	private class importAgent extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			PluginCol_ReportBundleHandler oThis = PluginCol_ReportBundleHandler.this;
			
		    ReportBundleUnpacker packer = null;

			
			// ALLE REPORTS
			Iterator<reportCheckBox> iter = oThis.vCheckboxesImport.iterator();
			while (iter.hasNext()){
				reportCheckBox box = iter.next();
				if (box.isSelected()){
					packer = new ReportBundleUnpacker(box.getPath());
					packer.UnpackBundle(oThis.tfMandant.getText(), cbOverride.isSelected());
					
					oThis.get_TextArea4Output().setText( oThis.get_TextArea4Output().getText() + "\n" + packer.getMessagesAsString());
				}
			}
			
			
			// MANDANTEN REPORTS
			iter = oThis.vCheckboxesImportMandant.iterator();
			
			while (iter.hasNext()){
				reportCheckBox box = iter.next();
				if (box.isSelected()){
					packer = new ReportBundleUnpacker(box.getPath());
					packer.UnpackBundle(oThis.tfMandant.getText(), cbOverride.isSelected());
					
					oThis.get_TextArea4Output().setText( oThis.get_TextArea4Output().getText() + "\n" + packer.getMessagesAsString());
				}
			}
			
			oThis.generateCheckboxesForExport();
			oThis.generateCheckboxesForImport();
			
		}
		
	}
	
	
	/**
	 * ActionAgent zum Selektieren der Checkboxen
	 * @author manfred
	 *
	 */
	private class selectAllAgent extends XX_ActionAgent{

		Vector<reportCheckBox> m_vec = null;
		boolean 			   m_select = true;
		
		public selectAllAgent(Vector<reportCheckBox> vList,boolean bSelect) {
			super();
			this.m_vec = vList;
			this.m_select = bSelect;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if (m_vec == null) return;
			
			Iterator<reportCheckBox> iter = m_vec.iterator();
			
			while(iter.hasNext()){
				iter.next().setSelected(m_select);
			}
		}
	}
	
	
	/**
	 * Klasse für die spezielle Checkbox mit absolutem Pfad und getrennter Anzeige
	 * @author manfred
	 *
	 */
	private class reportCheckBox extends MyE2_CheckBox{
		String m_sPathComplete = null;
		String m_sName = null;
		

		public reportCheckBox(Object cText, String sPathComplete) {
			super(cText);
			this.m_sPathComplete = sPathComplete;
			this.m_sName = cText.toString();
		}
		
		public String getPath(){
			return m_sPathComplete;
		}
		
		public String getName(){
			return m_sName;
		}
		
	}

	
	
}
