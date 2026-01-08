package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.UserSettings.IF_Saveable;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION.MASK.HADM_bt_newTicket;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.E2_MODUL_DESCRIPTOR_IF;
import panter.gmbh.basics4project.DB_ENUMS.ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_ENUMS.HILFETEXT;
import panter.gmbh.basics4project.DB_ENUMS.HILFETEXT_ZU_MODUL;
import panter.gmbh.basics4project.DB_ENUMS.VERSION;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_HILFETEXT_ZU_MODUL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_HILFETEXT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_HILFETEXT_ZU_MODUL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MEDIENTYP;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibHASHMAP;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.ZIP.ZIP_NG_Creator;
import panter.gmbh.indep.ZIP.ZIP_NG_NamePair;
import panter.gmbh.indep.dataTools.MyExportDataRows;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECLIST_ARCHIVMEDIEN_ext;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;

public class HAD__GridBedienPanel extends MyE2_Grid {

	private ownSelectFieldSaveable                  selFieldTicketTyp = new ownSelectFieldSaveable(HAD___CONST.TICKET_TYP.DOKUMENT.get_dd_Array(true), "", false);
	private ownSelectFieldSaveable                  selFieldTicketStatus = new ownSelectFieldSaveable(HAD___CONST.TICKET_STATUS.CLOSED.get_dd_Array(true), "", false);
	private ownSelectFieldSaveable                  selFieldVersionen = null;
	private MyE2_SelectField                        selFieldModule = null;
	
	private HAD__GridBedienPanelSorter  			selSorter = new HAD__GridBedienPanelSorter();
	
	private MyE2_TextField                          tfSuchtext = new MyE2_TextField("",100,300);
	private MyE2_Button                             bt_start = new MyE2_Button(E2_ResourceIcon.get_RI("ok.png"));
	private MyE2_Button                             bt_clear = new MyE2_Button(E2_ResourceIcon.get_RI("eraser.png"));
	
	private MyE2_Button  							bt_export = new MyE2_Button("EXPORT", MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder());
	private HAD__bt_PrintHandbook  					bt_print =  null;
	
	
	private HAD__GridWithData   					ownDataGrid = null;
	
	
	private String   								ersatz4id_mandant4Export = "$$ID_MANDANT$$";	

	
	public HAD__GridBedienPanel(HAD__GridWithData dataGridThisBelongsTo) throws myException {
		super(8, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	
		this.bt_print = new HAD__bt_PrintHandbook(dataGridThisBelongsTo);
		
		this.ownDataGrid = dataGridThisBelongsTo;
		
		this.bt_export.setToolTipText(new MyE2_String("Export der SQL-Anweisung und der Anlagen zur Übertragung der Hilfen in ein anderes Portal").CTrans());
		
		this.bt_export.add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				HAD__GridBedienPanel.this.generate_DownLoad_SQL_Export();
			}
		});
		
		SEL selVers = new SEL(VERSION.version_code, VERSION.id_version).FROM(_TAB.version).ORDERUP(VERSION.version_code);
		this.selFieldVersionen = new ownSelectFieldSaveable(selVers.s(), false, true, false, false) ;

		this.selFieldTicketTyp.add_action(new ownActionAgentSaveUserSetting());
		this.selFieldTicketStatus.add_action(new ownActionAgentSaveUserSetting());
		this.selFieldVersionen.add_action(new ownActionAgentSaveUserSetting());
		this.selSorter.add_action(new ownActionAgentSaveUserSetting());


		//selektor speichern/laden
		ArrayList<IF_Saveable> v_selects = new ArrayList<IF_Saveable>();
		v_selects.add(this.selFieldTicketTyp);
		v_selects.add(this.selFieldTicketStatus);
		v_selects.add(this.selFieldVersionen);
		v_selects.add(this.selSorter);
		new HAD__UserSetting(v_selects).RESTORE();
		
		
		
		
		//DEBUG ------------------------------
//		DEBUG.System_println("selFieldTicketTyp: "+this.selFieldTicketTyp.get_ActualWert());
//		DEBUG.System_println("selFieldTicketStatus: "+this.selFieldTicketStatus.get_ActualWert());
//		DEBUG.System_println("selFieldVersionen: "+this.selFieldVersionen.get_ActualWert());
		//DEBUG ------------------------------
		
		//alle modulbezeichner sammeln
		Vector<E2_MODUL_DESCRIPTOR_IF>  vModuls = new Vector<E2_MODUL_DESCRIPTOR_IF>();
		for (MODUL  modlist: MODUL.values()) {
			vModuls.add(modlist);
		}
		
		vModuls.sort(new Comparator<E2_MODUL_DESCRIPTOR_IF>() {
			@Override
			public int compare(E2_MODUL_DESCRIPTOR_IF o1,E2_MODUL_DESCRIPTOR_IF o2) {
				return o1.get_userInfo().CTrans().compareTo(o2.get_userInfo().CTrans());
			}
		});
		
		//zaehlt die eintrage bei den einzelnen modulen
		String sql_anzahl = "SELECT "+HILFETEXT.modulkenner+", count(*) AS anzahl FROM "+HILFETEXT.fullTabName()+" group by "+HILFETEXT.modulkenner;
		String[][] verteilung = bibDB.EinzelAbfrageInArray(sql_anzahl, "");
		HashMap<String, String>  hm_verteilung = bibHASHMAP.get_HashMap(verteilung, 0, 1);
		
		
		Vector<String> v_inaktiv_markierte = new Vector<String>();
		String[][] values4DD = new String[vModuls.size()+1][2];
		values4DD[0][0] = "*"; values4DD[0][1]="";
		int i_count = 1;
		for (E2_MODUL_DESCRIPTOR_IF mod: vModuls) {
			values4DD[i_count][0] = mod.get_userInfo().CTrans(); values4DD[i_count][1]=mod.get_callKey();
			if (hm_verteilung.get(mod.get_callKey())==null) {
				v_inaktiv_markierte.add(values4DD[i_count][0]);
			}
			i_count++;
		}
		
		this.selFieldModule = new MyE2_SelectField(values4DD, "", false);
		this.selFieldModule.setCellRenderer(new MyE2_SelectField.ownListCellRenderer(v_inaktiv_markierte));  //module ohne eintrag grau
		
		this.selFieldTicketTyp.setWidth(new Extent(120));
		this.selFieldTicketStatus.setWidth(new Extent(120));
		this.selFieldVersionen.setWidth(new Extent(120));
		this.selFieldModule.setWidth(new Extent(200));
		this.selSorter.setWidth(new Extent(100));
		

		int[] spalten = {100,100,100,100,100,100,25,25,30,70,70};
		E2_Grid4MaskSimple  gridhelp = new E2_Grid4MaskSimple()
								.def_(E2_INSETS.I(4,0,2,0))
								.add_(new MyE2_Label(new MyE2_String("Typ"),new E2_FontItalic(-2)))
								.add_(new MyE2_Label(new MyE2_String("Status"),new E2_FontItalic(-2)))
								.add_(new MyE2_Label(new MyE2_String("Version"),new E2_FontItalic(-2)))
								.add_(new MyE2_Label(new MyE2_String("Modul"),new E2_FontItalic(-2)))
								.add_(new MyE2_Label(new MyE2_String("Sort."),new E2_FontItalic(-2)))
								.def_(3, 1)
								.add_(new MyE2_Label(new MyE2_String("Suche"),new E2_FontItalic(-2)))
								.def_(1, 1)
								.add_(new MyE2_Label(new MyE2_String(""),new E2_FontItalic(-2)))
								.add_(new MyE2_Label(new MyE2_String(""),new E2_FontItalic(-2)))
								.add_(new MyE2_Label(new MyE2_String(""),new E2_FontItalic(-2)))
								.def_(E2_INSETS.I(2,0,2,0))
								.add_(this.selFieldTicketTyp)
								.add_(this.selFieldTicketStatus)
								.add_(this.selFieldVersionen)
								.add_(this.selFieldModule)
								.add_(this.selSorter)
								.add_(this.tfSuchtext)
								.add_(this.bt_start)
								.add_(this.bt_clear)
								.def_(E2_INSETS.I(10,0,10,0))
								.add_(this.bt_print)
								.def_(E2_INSETS.I(2,0,2,0))								
								.add_(new HADM_bt_newTicket())
								.add_(bibALL.get_RECORD_USER().is_DEVELOPER_YES()?this.bt_export:new MyE2_Label(""))
								.setSize_(spalten)
								;
		
		this.add(gridhelp,E2_INSETS.I(2,2,2,2));
		
		
	}

	public MyE2_SelectField get_SelFieldTicketTyp() {
		return selFieldTicketTyp;
	}

	public MyE2_SelectField get_SelFieldTicketStatus() {
		return selFieldTicketStatus;
	}

	public MyE2_TextField get_TfSuchtext() {
		return tfSuchtext;
	}

	public MyE2_Button get_Bt_start() {
		return bt_start;
	}

	public MyE2_Button get_Bt_clear() {
		return bt_clear;
	}

	public MyE2_SelectField get_selFieldVersionen() {
		return selFieldVersionen;
	}

	public MyE2_SelectField get_selFieldModule() {
		return selFieldModule;
	}

	
	public HAD__GridBedienPanelSorter get_selSorter() {
		return this.selSorter;
	}

	
	private class ownSelectFieldSaveable extends MyE2_SelectField implements IF_Saveable {

		public ownSelectFieldSaveable(String[][] aDefArray, String cdefaultValue, boolean btranslate) throws myException {
			super(aDefArray, cdefaultValue, btranslate);
		}

		
		
		public ownSelectFieldSaveable(	String 	cSQL_Query_For_LIST,
										boolean bThirdColumnIS_STANDARD_MARKER,
										boolean bEmtyValueInFront, 
										boolean bValuesFormated,
										boolean btranslate) throws myException {
			super(cSQL_Query_For_LIST, bThirdColumnIS_STANDARD_MARKER, bEmtyValueInFront,bValuesFormated, btranslate);
		}



		@Override
		public String get_value_to_save() throws myException {
			return S.NN(this.get_ActualWert());
		}

		@Override
		public void restore_value(String value) throws myException {
			this.set_ActiveValue_OR_FirstValue(value);
			
		}

		@Override
		public void set_component_to_status_not_saved() throws myException {
			this.set_ActiveValue_OR_FirstValue("");
		}

		@Override
		public Component get_Comp() throws myException {
			return this;
		}

		@Override
		public void add_action(XX_ActionAgent agent) throws myException {
			this.add_oActionAgent(agent);
		}
		
	}
	
	
	
	private class ownActionAgentSaveUserSetting extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			//selektor speichern/laden
			ArrayList<IF_Saveable> v_selects = new ArrayList<IF_Saveable>();
			v_selects.add(HAD__GridBedienPanel.this.selFieldTicketTyp);
			v_selects.add(HAD__GridBedienPanel.this.selFieldTicketStatus);
			v_selects.add(HAD__GridBedienPanel.this.selFieldVersionen);
			v_selects.add(HAD__GridBedienPanel.this.selSorter);
			
			new HAD__UserSetting(v_selects).SAVE();

		}
	}
	
	
	
	private void generate_DownLoad_SQL_Export() throws myException {
		
		StringBuffer cSQL_TEXT = new StringBuffer();

		//zuerst nachsehen, ob eine checkbox (nur fuer developer-user) angekreuzt ist
		Vector<String>  v_positiv_ausgewaehlt = new Vector<String>();
		for (HAD__CheckBox cb: this.ownDataGrid.get_v_cbs()) {
			if (cb.isSelected()) {
				v_positiv_ausgewaehlt.add(cb.get_RecHilfeText().get_ID_HILFETEXT_cUF());
			}
		}
		
		//beschreibungsvector fuer das archiv aus anlagen
		Vector<ZIP_NG_NamePair> vNames = new Vector<ZIP_NG_NamePair>();
		
		int iCountHilfeBloecke = 0;
		int iCountArchivmedien = 0;
		
		for (RECORD_HILFETEXT rec: this.ownDataGrid.get_actualReclistHilfetext()) {

			//wenn etwas ausgewaehlt wurde, dann werden nur die ausgewaehlten exportiert
			if (v_positiv_ausgewaehlt.size()>0) {
				if (!v_positiv_ausgewaehlt.contains(rec.get_ID_HILFETEXT_cUF())) {
					continue;
				}
			}
			
			iCountHilfeBloecke++;
			
			HashMap<String, String>  hmErsatzHt = new HashMap<String, String>();
			hmErsatzHt.put(HILFETEXT.id_hilfetext.fn(), _DB.HILFETEXT$$SEQ_NEXT);
			// im statement muessen die zwei user ID_USER_BEARBEITER und ID_USER_URSPRUNG via subselect abgefragt werden
			RECORD_USER recUserUrsprung = rec.get_UP_RECORD_USER_id_user_ursprung();
			RECORD_USER recUserBearbeiter = rec.get_UP_RECORD_USER_id_user_bearbeiter();
			
			if (recUserUrsprung!=null) {
				String subsel1 = "(SELECT MAX(ID_USER) FROM JD_USER WHERE UPPER(NVL(VORNAME,''))='"+recUserUrsprung.get_VORNAME_cUF_NN("").toUpperCase()+"'"
																+" AND  UPPER(NVL(NAME1,''))='"+recUserUrsprung.get_NAME1_cUF_NN("").toUpperCase()+"'"
																+" AND  ID_MANDANT="+this.ersatz4id_mandant4Export+")";		
				hmErsatzHt.put(HILFETEXT.id_user_ursprung.fn(), subsel1);
			}
			
			if (recUserBearbeiter!=null) {
				String subsel2 = "(SELECT MAX(ID_USER) FROM JD_USER WHERE UPPER(NVL(VORNAME,''))='"+recUserBearbeiter.get_VORNAME_cUF_NN("").toUpperCase()+"'"
																+" AND  UPPER(NVL(NAME1,''))='"+recUserBearbeiter.get_NAME1_cUF_NN("").toUpperCase()+"'"
																+" AND  ID_MANDANT="+this.ersatz4id_mandant4Export+")";		
				hmErsatzHt.put(HILFETEXT.id_user_bearbeiter.fn(), subsel2);
			}
			
			cSQL_TEXT.append(new MyExportDataRows(rec.get_ID_HILFETEXT_cUF(), _DB.HILFETEXT, this.ersatz4id_mandant4Export, hmErsatzHt).get_cSQL_INSERT_STATEMENT()+";\n");
			
			
			//toechter
			
			//1: archivmedien
			RECLIST_ARCHIVMEDIEN_ext rl_arch = new RECLIST_ARCHIVMEDIEN_ext(_DB.HILFETEXT, rec.get_ID_HILFETEXT_cUF(), null,null);
			HashMap<String, String>  hmErsatzAm = new HashMap<String, String>();
			hmErsatzAm.put(ARCHIVMEDIEN.id_archivmedien.fn(),	 _DB.ARCHIVMEDIEN$$SEQ_NEXT);
			hmErsatzAm.put(ARCHIVMEDIEN.id_table.fn(), 			_DB.HILFETEXT$$SEQ_CURR);
			
			for (RECORD_ARCHIVMEDIEN ra: rl_arch) {
				//medientyp aus dem anderen mandanten identifizieren
				RECORD_MEDIENTYP  recMedientyp = ra.get_UP_RECORD_MEDIENTYP_id_medientyp();
				if (recMedientyp!=null) {
					String subsel3 = "(SELECT MAX(ID_MEDIENTYP) FROM JT_MEDIENTYP "
											+ " WHERE UPPER(NVL(DATEIENDUNG,''))='"+recMedientyp.get_DATEIENDUNG_cUF_NN("").toUpperCase()+"'"
											+" AND  ID_MANDANT="+this.ersatz4id_mandant4Export+")";
					hmErsatzAm.put(ARCHIVMEDIEN.id_medientyp.fn(), subsel3);
				}
				
				cSQL_TEXT.append(new MyExportDataRows(ra.get_ID_ARCHIVMEDIEN_cUF(), _DB.ARCHIVMEDIEN, this.ersatz4id_mandant4Export, hmErsatzAm).get_cSQL_INSERT_STATEMENT()+";\n");
				
				RECORD_ARCHIVMEDIEN_ext ra_ext = new RECORD_ARCHIVMEDIEN_ext(ra);
				if (ra_ext.is_existing_in_filesystem()) {
					vNames.add(new ZIP_NG_NamePair(ra_ext.get_DATEINAME_cUF_NN(""), ra_ext.get__cCompletePathAndFileName()));
					iCountArchivmedien++;
				}
			}
			
			
			//2: verteiler fuer zusatzmodule
			RECLIST_HILFETEXT_ZU_MODUL rl_zusatz= rec.get_DOWN_RECORD_LIST_HILFETEXT_ZU_MODUL_id_hilfetext();
			HashMap<String, String>  hmErsatzZHt = new HashMap<String, String>();
			hmErsatzZHt.put(HILFETEXT_ZU_MODUL.id_hilfetext_zu_modul.fn(),	 _DB.HILFETEXT_ZU_MODUL$$SEQ_NEXT);
			hmErsatzZHt.put(HILFETEXT_ZU_MODUL.id_hilfetext.fn(), 			 _DB.HILFETEXT$$SEQ_CURR);
			
			for (RECORD_HILFETEXT_ZU_MODUL rz: rl_zusatz) {
				cSQL_TEXT.append(new MyExportDataRows(rz.get_ID_HILFETEXT_ZU_MODUL_cUF(), _DB.HILFETEXT_ZU_MODUL, this.ersatz4id_mandant4Export, hmErsatzZHt).get_cSQL_INSERT_STATEMENT()+";\n");
			}
		
		}

		myTempFile  oTempFile = new myTempFile("exp_sql", ".txt", true);
		oTempFile.WriteTextBlock(cSQL_TEXT.toString(), myTempFile.CHARSET_ISO_8859_1);
		oTempFile.close();
		
		vNames.add(new ZIP_NG_NamePair("exp_sql.txt", oTempFile.getFileName()));
		
//		E2_Download  oDownload = new E2_Download();
//		oDownload.starteFileDownload(oTempFile.getFileName(),"sql-statement.txt", "application/text");

		bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Anzahl Hilfetext exportiert: ",true,""+iCountHilfeBloecke,false," / Archivmediendateien: ",true,""+iCountArchivmedien,false)));
		
		//gleich noch die archivmedien-dateien dazupacken
		ZIP_NG_Creator  createZIP = new ZIP_NG_Creator(vNames, "archivdateien_fuer_hilfe.zip");
		createZIP.startDownload();
		
	}
	
	
	
}
