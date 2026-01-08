package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.Label;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.AgentsAndValidators.ENUM_BASED.VALID_ENUM_BUTTONS;
import panter.gmbh.Echo2.AgentsAndValidators.ENUM_BASED.VALID_ENUM_MODULES.RANGE_KEY;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_Factory4Records;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonEditRowInList;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentMULTIDELETE;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.TREASURE_CHEST.TS_Treasure_CONST.TS_DEFINITION;
import panter.gmbh.Echo2.RecursiveSearch.TREASURE_CHEST.TS_Treasure_Chest;
import panter.gmbh.Echo2.RecursiveSearch.TREASURE_CHEST.TS_Treasure_Chest_Container_IF;
import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.IMAGE_HANDLING.IMG_Button_Popup_Image_Viewer;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.SCANNER.SCAN_DESCRIPTION_IF;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.SCANNER.SCAN_POPUP_Button_Generic;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.ES_LIST_BasicModuleContainer;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.SEARCH_FIELDS.PopUp_CONNECT_ARCHIVE_ENTRY_TO_OTHER_OBJECTS;
import panter.gmbh.Echo2.components.E2_ComponentGrid;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentColumn;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.Echo2.staticStyles.Style_Table_Normal;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.DB_ENUMS.ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_EMAIL_SEND;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.archive.Archiver_CONST;
import panter.gmbh.indep.archive.Archiver_Delete_File_WhenAllowed;
import panter.gmbh.indep.archive.Archiver_Normalized_Tablename;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.GENERATE.GenTERM;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;

public class AM_BasicContainer extends	Project_BasicModuleContainer implements TS_Treasure_Chest_Container_IF, SCAN_DESCRIPTION_IF
{
	
	
	//2015-03-06: subqueryfeld fuer die pruefung, ob die archivdatei an eine oder mehrere JT_EMAIL_SEND via JT_EMAIL_SEND_ATTACH - eintraege verbunden ist
	public static final String COL_COUNT_EMAIL = 		"COL_COUNT_EMAIL";
	public static 		String COL_COUNT_EMAIL_QUERY = "(SELECT COUNT(*) FROM "+
																bibE2.cTO()+"."+_DB.EMAIL_SEND_ATTACH+" WHERE "+
																_DB.EMAIL_SEND_ATTACH$ID_ARCHIVMEDIEN+"="+_DB.Z_ARCHIVMEDIEN$ID_ARCHIVMEDIEN+")";  		
	public static final String COL_ID_ARCHIVMEDIEN2 = 	"COL_ID_ARCHIVMEDIEN2";
	
	//2015-01-19: der popup-container bekommt eine kennung (wenn ein MODULEKENNER uebermittelt wird), damit die spalten gespeichert werden koennen
	public static final String MODULKENNER_UP_AND_DOWN = E2_MODULNAME_ENUM.MODUL.POPUP_UPLOADS.get_callKey()+"_";
	
	
	/*
	 * tabelle, fuer die uploads/downloads gemacht werden
	 */
	private String 								cTABLE_NAME = "";
	private String 								cID_TABLE = "";
	
	
	private E2_NavigationList 					oNavigationList_ArchivFiles = null;
	
	
	// Variablen extrahiert
	SQLFieldMAP									oSQLFieldMap = null;
	E2_ComponentMAP 							oCompMap     = null;
	E2_SelectionComponentsVector 				oSelection   = null;
	
	private MyE2_Button 						butDel;
	private MyE2_Button 						butUpload;
	private MyE2_SelectField 					oSelFileType;
	private MyE2_SelectField 					oSelMedienkenner;
	private MyE2_SelectField 					oSelProgrammkenner;
	private MyE2_CheckBox 						oCB_nurBilder;
	private MyE2_CheckBox 						oCB_nurMit_EMAIL_ARCHIV;
	
	// Button zur direkten Anzeige von Bildern in den Upload-Dateien
	private IMG_Button_Popup_Image_Viewer 		butDisplayImages;
	
	
	//2015-01-30: plugin um die elemente in der liste, verschiede
	private UP_DOWN_AddOn_COL_ComponentFactory  addOn_Factory = null;
	
	//2015-03-24: erweiterung um email-methodiken, dazu optionale auslagerung in einen Container
	private MyE2_Grid     						grid4Upload = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
	
	//falls eine der anlagen zu einem emai-send-datensatz gehoert, dann das grid in einem reiter anzeigen
	private MyE2_TabbedPane    					tabbedPane = new MyE2_TabbedPane(null);
	
	private boolean    							bStatusWithTabbedPane = false;
	
	private ES_LIST_BasicModuleContainer        listEmailSendContainer = null;
	
	
	private String[][]  						ddProgrammkenner = null;
	
	/**
	 * @param ctable_name (Ohne JT_ oder JD_)
	 * @param cid_table
	 * @param Messageagent
	 * @param bUploadButton
	 * @throws myException
	 */
	public AM_BasicContainer(	String 	ctable_name, 
								String  cid_table, 
								String 	cMODULKENNER,
								boolean bUploadButton) throws myException
	{
		this(ctable_name,cid_table,cMODULKENNER,cMODULKENNER,bUploadButton,null,null);
	}
	
	
	
	
	/**
	 * @param ctable_name (Ohne JT_ oder JD_)
	 * @param cid_table
	 * @param cMODULKENNER
	 * @param bUploadButton
	 * @param cPredefinedMedienkenner (Std. null)
	 * @param COMPONENT_FACTORY  (std.: null)  //2015-01-30: falls die ID_EMAIL_SEND-spalte aktiv sein soll
	 * @throws myException
	 */
	public AM_BasicContainer(	String 								ctable_name, 
								String 								cid_table, 
								String 								cMODULKENNER,
								String  							cMODULKENNER_VALIDATION,
								boolean 							bUploadButton,
								String								cPredefinedMedienkenner,
								UP_DOWN_AddOn_COL_ComponentFactory  COMPONENT_FACTORY) throws myException
	{
		super(S.isEmpty(cMODULKENNER)?E2_MODULNAME_ENUM.MODUL.POPUP_UPLOADS.get_callKey():AM_BasicContainer.MODULKENNER_UP_AND_DOWN+cMODULKENNER);
		
		//DEBUG.System_println("Modulkenner (UPLOAD-POPUP-BUTTON): "+(S.isEmpty(cMODULKENNER)?E2_MODULNAME_ENUM.MODUL.POPUP_UPLOADS.get_callKey():AM_BasicContainer.MODULKENNER_UP_AND_DOWN+cMODULKENNER),DEBUG.DEBUG_FLAGS.MARTINS_EIGENER.name());
		
		if (bibALL.isEmpty(ctable_name))
			throw new myException("PopUp_For_UP_AND_DOWN_FILES:Constructor:TableName MUST NOT BE empty !");

		if (bibALL.isEmpty(cid_table) || !bibALL.isInteger(cid_table))
			throw new myException("PopUp_For_UP_AND_DOWN_FILES:Constructor:Table-ID MUST NOT BE empty and MUST be an Fixed NUMBER!");

		
		this.cTABLE_NAME = 					ctable_name;
		this.cID_TABLE = 					cid_table;
		/*
		 * nur der "kern" des tabellennamens
		 */
		if (cTABLE_NAME.toUpperCase().startsWith("JT_")) cTABLE_NAME = cTABLE_NAME.substring(3);
		if (cTABLE_NAME.toUpperCase().startsWith("JD_")) cTABLE_NAME = cTABLE_NAME.substring(3);

		
		
		this.addOn_Factory = COMPONENT_FACTORY;
		
		
//		//der modulname fuer die kenner-selektion ist hier die validierungs-range (
//		String cModuleName4ScannerFinding = VALID_ENUM_MODULES.PREFIX_4_ATTACHMENT_POPUPS+cTABLE_NAME;
		
//		new AM_BasicContainer_ScannerButtonsRenderer(this.grid4ScannerButtons, cModuleName4ScannerFinding, this);
		
		
		//2015-05-20: neue definition der validierung: via enums, nicht mehr ueber den modul-identifier
		//jetzt nachsehen, ob hier das allgemeine oder das 
		String cTablenameBase = new Archiver_Normalized_Tablename(this.cTABLE_NAME).get_TableBaseName();
		
		//jetzt nachsehen, ob es einen solchen VALID_ENUM_MODULES gibt
		this.set_RangeKey(new __FINDER_MODULE_KEY(cTablenameBase).get_Key());

		
		this.oNavigationList_ArchivFiles = new E2_NavigationList();

		
		/*
		 *  Definition der fieldMap
		 */
		oSQLFieldMap = new SQLFieldMAP("JT_ARCHIVMEDIEN",bibE2.get_CurrSession());
		
		oSQLFieldMap.addCompleteTable_FIELDLIST("JT_ARCHIVMEDIEN",":ID_ARCHIVMEDIEN:ID_MANDANT:TABLENAME:ID_TABLE:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true, "");
		oSQLFieldMap.add_SQLField(new SQLFieldForPrimaryKey("JT_ARCHIVMEDIEN","ID_ARCHIVMEDIEN","ID_ARCHIVMEDIEN",new MyE2_String("ID-Archivmedien"),bibE2.get_CurrSession(),
				"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_ARCHIVMEDIEN.NEXTVAL FROM DUAL",true), false);

		//2015-01-27: neue joins 
		oSQLFieldMap.add_JOIN_Table(bibE2.cTO(),_DB.MEDIENTYP, _DB.MEDIENTYP, SQLFieldMAP.LEFT_OUTER, _DB.MEDIENTYP$DATEIENDUNG, true, 
				_DB.Z_ARCHIVMEDIEN$ID_MEDIENTYP+"="+_DB.Z_MEDIENTYP$ID_MEDIENTYP, "T_", null);

//		String sql = "(SELECT COUNT(*) FROM "+bibE2.cTO()+"."+_DB.EMAIL_SEND_ATTACH+" WHERE "+_DB.EMAIL_SEND_ATTACH$ID_ARCHIVMEDIEN+"="+_DB.Z_ARCHIVMEDIEN$ID_ARCHIVMEDIEN+")";
		oSQLFieldMap.add_SQLField(new SQLField(AM_BasicContainer.COL_COUNT_EMAIL_QUERY, AM_BasicContainer.COL_COUNT_EMAIL, new MyE2_String("eMail"), bibE2.get_CurrSession()), true); 

		//2015-03-06: weiteres dummy-field, um der komponente der component-factory falls noetig die ID_ARCHIVMEDIEN zu uebergeben
		oSQLFieldMap.add_SQLField(new SQLField("JT_ARCHIVMEDIEN.ID_ARCHIVMEDIEN", AM_BasicContainer.COL_ID_ARCHIVMEDIEN2, new MyE2_String("id-2"), bibE2.get_CurrSession()), true);
		
				
		/*
		 * restrict: tabellen-Name und ID fix
		 */
		oSQLFieldMap.add_SQLField(new SQLFieldForRestrictTableRange("JT_ARCHIVMEDIEN","TABLENAME","TABLENAME",new MyE2_String("Tabellen-Name"),bibALL.MakeSql(this.cTABLE_NAME),bibE2.get_CurrSession()), false);
		oSQLFieldMap.add_SQLField(new SQLFieldForRestrictTableRange("JT_ARCHIVMEDIEN","ID_TABLE","ID_TABLE",new MyE2_String("Tabellen-ID"),this.cID_TABLE,bibE2.get_CurrSession()), false);
		oSQLFieldMap.initFields();
		/*
		 *  Definition der fieldMap fertig
		 */
		
		
		butDel = new MyE2_Button(E2_ResourceIcon.get_RI("delete.png"),true);
		butDel.add_oActionAgent(new ownActionAgentDelete(new MyE2_String("Löschen einer Datei"),this.oNavigationList_ArchivFiles));
		butDel.setToolTipText(new MyE2_String("Eine Anhang-Datei löschen").CTrans());
		butDel.add_GlobalValidator(new __VALIDATOR_4_ATTACHMENT_POPUP(VALID_ENUM_BUTTONS.ATTACHMENT_BUTTONS.DELETE_ATTACHMENT));
		butDel.add_GlobalValidator(new valid_Delete_ArchivMedium());
		
		butUpload = new MyE2_Button(E2_ResourceIcon.get_RI("up.png"),true);
		butUpload.add_oActionAgent(new ownActionAgentUpload());
		butUpload.setToolTipText(new MyE2_String("Eine neue Datei zu diesem Vorgang hochladen ...").CTrans());
		butUpload.add_GlobalValidator(new __VALIDATOR_4_ATTACHMENT_POPUP(VALID_ENUM_BUTTONS.ATTACHMENT_BUTTONS.UPLOAD_ATTACHMENT));
		
		
		butDisplayImages = new IMG_Button_Popup_Image_Viewer(cTABLE_NAME,cID_TABLE	);
		butDisplayImages.setToolTipText(new MyString("Anzeige der Bildanhänge in einem Popup-Fenster.").CTrans());

		
		
		/*
		 * buttons in der liste
		 */
		E2_ButtonEditRowInList 	butEdit =	 	new E2_ButtonEditRowInList(true);
		butEdit.get_oButtonEdit().add_GlobalValidator(new __VALIDATOR_4_ATTACHMENT_POPUP(VALID_ENUM_BUTTONS.ATTACHMENT_BUTTONS.EDIT_IN_LIST));
		
		MyE2_ButtonInLIST		butDownload	= 	new MyE2_ButtonInLIST(E2_ResourceIcon.get_RI("down.png"),true);
		butDownload.add_oActionAgent(new ownActionAgentDownload());
		butDownload.add_GlobalValidator(new __VALIDATOR_4_ATTACHMENT_POPUP(VALID_ENUM_BUTTONS.ATTACHMENT_BUTTONS.DOWNLOAD_ATTACHMENT));

		
		//2015-01-23: nachsehen, ob es einen programmkenner-selektor gibt, wenn ja, dann einen dropdown anzeigen
		this.ddProgrammkenner = Archiver_CONST.get_hm_DDVALUES_IN_UPLOAD().get(cTABLE_NAME);
		E2_AktiveSelectorInList oSelProgrammkennerInList = null;
		if (ddProgrammkenner!=null) {
			oSelProgrammkennerInList=new E2_AktiveSelectorInList(oSQLFieldMap.get_(_DB.ARCHIVMEDIEN$PROGRAMM_KENNER),ddProgrammkenner);
			oSelProgrammkennerInList.add_GlobalValidator(new __VALIDATOR_4_ATTACHMENT_POPUP(VALID_ENUM_BUTTONS.ATTACHMENT_BUTTONS.CHANGE_PROGKENNER));
		}

		
		
		/*
		 * Verknüpfen von Anhängen
		 */
		MyE2_ButtonInLIST		butConnectToOtherObject	= 	new MyE2_ButtonInLIST(E2_ResourceIcon.get_RI("attach_mini_link.png"),true);
		butConnectToOtherObject.setToolTipText(new MyString("Verknüpfen des Anhangs zu einem anderen Objekt.").CTrans());
		butConnectToOtherObject.add_oActionAgent(new ownActionAgentConnectToOtherObject());
		butConnectToOtherObject.add_GlobalValidator(new __VALIDATOR_4_ATTACHMENT_POPUP(VALID_ENUM_BUTTONS.ATTACHMENT_BUTTONS.CONNECT_TO_OTHER_MODULE));
		
		E2_MutableStyle  oStyleSmallFont = new E2_MutableStyle();
		oStyleSmallFont.setProperty(Label.PROPERTY_FONT, new E2_FontPlain(-2));
		
		// mehrfach-felder
		MyE2_DB_MultiComponentColumn oColDatei = new MyE2_DB_MultiComponentColumn();
		oColDatei.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMap.get_(_DB.ARCHIVMEDIEN$PFAD),oStyleSmallFont),new MyE2_String("Pfad"),null);
		oColDatei.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMap.get_(_DB.ARCHIVMEDIEN$DATEINAME),oStyleSmallFont),new MyE2_String("Datei im Archiv"),null);
		oColDatei.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMap.get_(_DB.ARCHIVMEDIEN$DOWNLOADNAME),oStyleSmallFont),new MyE2_String("Downloadname"),null);
		oColDatei.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMap.get_("T_DATEIENDUNG")),new MyE2_String("Typ"),null);
	
		MyE2_DB_MultiComponentColumn oColKENNER = new MyE2_DB_MultiComponentColumn();
		oColKENNER.add_Component(new MyE2_DB_Label(oSQLFieldMap.get_(_DB.ARCHIVMEDIEN$MEDIENKENNER)),new MyE2_String("Medienkenner"),null);
		//2015-01-23: programmkenner
		if (oSelProgrammkennerInList==null) {
			oColKENNER.add_Component(new MyE2_DB_Label(oSQLFieldMap.get_(_DB.ARCHIVMEDIEN$PROGRAMM_KENNER)),new MyE2_String("Verwendung"),null);
		} else {
			oColKENNER.add_Component(oSelProgrammkennerInList,new MyE2_String("Verwendung"),null);
		}
		oColKENNER.add_Component(new MyE2_DB_Label(oSQLFieldMap.get_(_DB.ARCHIVMEDIEN$VORGANG_TYP)),new MyE2_String("Vorgangsart"),null);
		
		MyE2_DB_MultiComponentColumn oColSCHALTER = new MyE2_DB_MultiComponentColumn();
		oColSCHALTER.add_Component(new MyE2_DB_CheckBox(oSQLFieldMap.get_(_DB.ARCHIVMEDIEN$IST_ORIGINAL)),new MyE2_String("Orig"),null);
		
		oCompMap = new E2_ComponentMAP(oSQLFieldMap);
		
		//2015-02-26: factory-klasse dazu
		oCompMap.set_Factory4Records(new ownRecordFactory());
		
		oCompMap.add_Component("NAME_OF_CHECKBOX_IN_LIST", new E2_CheckBoxForList(),new MyE2_String("?"));
		oCompMap.add_Component("NAME_OF_LISTMARKER_IN_LIST", new E2_ButtonListMarker(),new MyE2_String("?"));
		oCompMap.add_Component("DOWNLOAD_FILES",butDownload,new MyE2_String("Down"));
		oCompMap.add_Component(new MyE2_DB_Label_INROW(oSQLFieldMap.get_(_DB.ARCHIVMEDIEN$ERSTELLUNGSDATUM)),new MyE2_String("Erstellung am"));
		oCompMap.add_Component("EDIT_FILES",butEdit,new MyE2_String("Bearb."));
		oCompMap.add_Component(new MyE2_DB_TextArea(oSQLFieldMap.get_(_DB.ARCHIVMEDIEN$DATEIBESCHREIBUNG)),new MyE2_String("Beschreibung"));
		oCompMap.add_Component("HASH_HELP1",oColDatei,new MyE2_String("Pfad/Downloadname/Typ"));
		oCompMap.add_Component("HASH_HELP3",oColKENNER,new MyE2_String("Kennungen der Dateien"));
		oCompMap.add_Component("HASH_HELP4",oColSCHALTER,new MyE2_String("Orig/Intern"));
		
		//2015-01-27: zusatzkomponente aus der UP_DOWN_AddOn_COL_ComponentFactory
		oCompMap.add_Component(new UP_DOWN_ListComponent_AddOn(oSQLFieldMap.get_(AM_BasicContainer.COL_ID_ARCHIVMEDIEN2),this), new MyE2_String("Zusatz"));
		
		oCompMap.add_Component("CONNECT_TO_NEW_OBJECT",butConnectToOtherObject,new MyE2_String("Verknüpfe..."));

		((MyE2IF__Component)oCompMap.get("HASH_HELP3")).EXT().set_bIsVisibleInList(false);

		
		HashMap<String, MyE2IF__Component> oHMRealComp = oCompMap.get_REAL_ComponentHashMap();
		
		((MyE2IF__Component)oHMRealComp.get(_DB.ARCHIVMEDIEN$ERSTELLUNGSDATUM)).EXT().set_oColExtent(new Extent(80));
		((MyE2IF__Component)oHMRealComp.get(_DB.ARCHIVMEDIEN$DATEIBESCHREIBUNG)).EXT().set_oColExtent(new Extent(310));
		((MyE2IF__Component)oHMRealComp.get(_DB.ARCHIVMEDIEN$PFAD)).EXT().set_oColExtent(new Extent(100));
		((MyE2IF__Component)oHMRealComp.get(_DB.ARCHIVMEDIEN$DOWNLOADNAME)).EXT().set_oColExtent(new Extent(100));
		((MyE2IF__Component)oHMRealComp.get("T_DATEIENDUNG")).EXT().set_oColExtent(new Extent(40));
		((MyE2IF__Component)oHMRealComp.get(_DB.ARCHIVMEDIEN$VORGANG_TYP)).EXT().set_oColExtent(new Extent(100));
		((MyE2IF__Component)oHMRealComp.get(_DB.ARCHIVMEDIEN$MEDIENKENNER)).EXT().set_oColExtent(new Extent(100));
		((MyE2IF__Component)oHMRealComp.get(_DB.ARCHIVMEDIEN$DATEINAME)).EXT().set_oColExtent(new Extent(200));
		

		
		/*
		 * felder einstellen
		 */
		((MyE2_DB_TextArea)oHMRealComp.get(_DB.ARCHIVMEDIEN$DATEIBESCHREIBUNG)).set_iWidthPixel(300);
		((MyE2_DB_TextArea)oHMRealComp.get(_DB.ARCHIVMEDIEN$DATEIBESCHREIBUNG)).set_iRows(4);
		
		
		/*
		 * jetzt einen selektor installieren, der verschiedene download-typen definiert
		 */
		oSelection = new E2_SelectionComponentsVector(this.oNavigationList_ArchivFiles);

		//2016-03-16: die selektion speichern
		oSelection.set_SESSION_HASH_4_SAVE_SETTINGS(ENUM_USER_SAVEKEY.SESSION_HASH_USER_SETTING_SELECTOR_IN_UPLOADFILES.name()+"_"+this.cTABLE_NAME);
		
		
		oSelFileType = new MyE2_SelectField("SELECT DATEIENDUNG,ID_MEDIENTYP FROM "+bibE2.cTO()+".JT_MEDIENTYP ORDER BY DATEIENDUNG",false,true,false,false);
		E2_ListSelectorStandard			oSelectorFileType  = new E2_ListSelectorStandard(oSelFileType,"JT_ARCHIVMEDIEN.ID_MEDIENTYP=#WERT#", null, null);

		oSelMedienkenner = new MyE2_SelectField(Archiver_CONST.get_MEDIENKENNER_SEL_ARRAY_MIT_LEER(),null,true);
		if ( !bibALL.isEmpty(cPredefinedMedienkenner) ){
			oSelMedienkenner.set_ActiveValue_OR_FirstValue(cPredefinedMedienkenner);
		}
		E2_ListSelectorStandard			oSelectorMedienkenner  = new E2_ListSelectorStandard(oSelMedienkenner,_DB.Z_ARCHIVMEDIEN$MEDIENKENNER+"='#WERT#'", null, null);

		//2015-01-27: selektor fuer programmkenner
		oSelProgrammkenner = new MyE2_SelectField(Archiver_CONST.get_PROGRAMMKENNER_SEL_ARRAY_MIT_LEER(),null,true);
		E2_ListSelectorStandard			oSelectorProgrammkenner  = new E2_ListSelectorStandard(oSelProgrammkenner,_DB.Z_ARCHIVMEDIEN$PROGRAMM_KENNER+"='#WERT#'", null, null);
		
		this.oSelMedienkenner.setWidth(new Extent(120));
		this.oSelProgrammkenner.setWidth(new Extent(120));
		
		
		this.oCB_nurBilder = new MyE2_CheckBox(new MyE2_String("Nur Bilder"),new MyE2_String("Nur Bilder auf Pixelbasis auswählen"));
		E2_ListSelectorStandard         oSelNurBilder = new E2_ListSelectorStandard(oCB_nurBilder, 
						"JT_ARCHIVMEDIEN.ID_MEDIENTYP IN (SELECT MT.ID_MEDIENTYP FROM " +bibE2.cTO()+ ".JT_MEDIENTYP MT WHERE NVL(MT.IST_PIXELIMAGE,'N')='Y')", "");
		
		
		this.oCB_nurMit_EMAIL_ARCHIV = new MyE2_CheckBox(new MyE2_String("Nur eMail-Files"),new MyE2_String("Nur Archivdateien, die mit einem eMail-Versand verknüpft sind")); 
		GenTERM  oTerm = new GenTERM().AppendTerm(AM_BasicContainer.COL_COUNT_EMAIL_QUERY, ">", "0");
		E2_ListSelectorStandard         oSelnurMit_EMAIL_ARCHIV = new E2_ListSelectorStandard(this.oCB_nurMit_EMAIL_ARCHIV,oTerm.get_TERMS_WITH("AND"),""); 
		
		
		
		oSelection.add(oSelectorFileType);
		oSelection.add(oSelectorMedienkenner);
		oSelection.add(oSelectorProgrammkenner);
		oSelection.add(oSelNurBilder);
		oSelection.add(oSelnurMit_EMAIL_ARCHIV);
		
		
		
		
		// evtl. uploadbutton dazu
		Component HelpComponent = new MyE2_Label("");
		if (bUploadButton)HelpComponent = butUpload;
		
		E2_ComponentGroupHorizontal oBedienPanel = new E2_ComponentGroupHorizontal(1,new Insets(2,1,10,1));
		
		SCAN_POPUP_Button_Generic popupScanner = new SCAN_POPUP_Button_Generic(this);
		popupScanner.setToolTipText(new MyE2_String("Dokumenten ins Archiv scannen ...").CTrans());
		
		E2_ComponentGroupHorizontal oBedienButtons =  new E2_ComponentGroupHorizontal(
																1,
																new E2_ButtonToSelectVisibleListColumns_and_other_settings(this.oNavigationList_ArchivFiles),
																butDel,
																HelpComponent,
																//2015-03-25: eMail-button einfuegen
																new ES_bt_StartEmailSendung(this),
																//2014-12-09: scanner-button einfuegen
																//this.grid4ScannerButtons,
																popupScanner,
																// anzeige der hochgeladenen Bilder
																butDisplayImages,
																new Insets(2,1,10,1));

		//2012-08-06: neuer download von mehrfachen image-dokus fuer pdf
		E2_Button_MehrfachDownload oButton = new E2_Button_MehrfachDownload(this.oNavigationList_ArchivFiles, this.get_MODUL_IDENTIFIER());
		oButton.add_GlobalValidator(new __VALIDATOR_4_ATTACHMENT_POPUP(VALID_ENUM_BUTTONS.ATTACHMENT_BUTTONS.MULTI_DOWNLOAD));
		oButton.get_oSelectBildGroesse().setFont(new E2_FontPlain(-2));
		E2_ComponentGroupHorizontal oBedienMultiDownload = new E2_ComponentGroupHorizontal(
																1,
																oButton,
																new MyE2_Label(new MyE2_String("Größe"),new E2_FontPlain(-2)),
																oButton.get_oSelectBildGroesse(),
																new Insets(2,1,10,1));
		
		
		E2_ComponentGrid oSelGrid = new E2_ComponentGrid(5, MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I(1,1,10,1)), 
															new MyE2_Label(new MyE2_String("Dateityp:")),
															oSelFileType,
															new MyE2_Label(new MyE2_String("Entstehung:")),
															oSelMedienkenner,
															oCB_nurBilder,
															new MyE2_Label(""),new MyE2_Label(""),
															new MyE2_Label(new MyE2_String("Verwendung:")),
															oSelProgrammkenner,
															oCB_nurMit_EMAIL_ARCHIV
															);

		oBedienPanel.add(oBedienButtons, new Insets(2,0,10,0));
		oBedienPanel.add(oBedienMultiDownload, new Insets(2,0,10,0));
		oBedienPanel.add(oSelGrid, new Insets(2,0,10,0));
		
		/*
		 * selektor fertig
		 */
		
		//2015-01-23: der programmkenner-schalter soll nur bei bestimmten medienkennern erlaubt sein 
		oCompMap.add_oSubQueryAgent(new ownMapsSetterAllowChangeofProgrammKenner());
		
		this.oNavigationList_ArchivFiles.INIT_WITH_ComponentMAP(oCompMap,new Style_Table_Normal(new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID)), this.get_MODUL_IDENTIFIER());
		
//		this.oNavigationList.Fill_NavigationList("");
		
		//hier die alten settings laden
		this.oSelection.LOAD_LAST_SETTINGS();
		
		this.oSelection.doActionPassiv();
		
		
		this.grid4Upload.add(oBedienPanel,new Insets(2,1,1,2));
		this.grid4Upload.add(this.oNavigationList_ArchivFiles);
		

		//jetzt nach dem zusatzcontainer fuer die eMail-aktionen schauen
		boolean bMustShowAtStart = new RECLIST_EMAIL_SEND(
				new ES__Select_EMAIL_SEND("DISTINCT "+_DB.EMAIL_SEND+".*",this.cTABLE_NAME, this.cID_TABLE)).get_vKeyValues().size()>0;
		
		//container wird generiert
	    this.listEmailSendContainer = new ES_LIST_BasicModuleContainer(this.cID_TABLE, this.cTABLE_NAME);
		this.listEmailSendContainer.set_Visible_in_AM_BasicContainer(bMustShowAtStart);
		
		
		this.render_Window();
		
		
	}

	
	public void render_Window() throws myException {
		
		this.RESET_Content(false);
		if (!this.listEmailSendContainer.is_Visible_in_AM_BasicContainer()) {
			//dann ist kein TabbedPane noetig, es wird nur die Anhang-liste gezeigt
			this.add(this.grid4Upload, E2_INSETS.I(0, 0, 0, 0));
			this.bStatusWithTabbedPane = false;
		} else {
			MyE2_Grid gridHelp = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
			gridHelp.add(this.listEmailSendContainer,E2_INSETS.I(0,0,0,0));
			
			this.listEmailSendContainer.refresh();
			
			this.tabbedPane = new MyE2_TabbedPane(null);
			this.tabbedPane.set_bAutoHeight(true);
			this.set_iVerticalOffsetForTabbedPane(120);
			this.tabbedPane.add_Tabb(new MyE2_String("Anlagen/Dateien"), this.grid4Upload);
			this.tabbedPane.add_Tabb(new MyE2_String("eMail-Sendungen"), gridHelp, new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					AM_BasicContainer.this.get_es_LIST_BasicModuleContainer().refresh();
				}
			});
			this.add(this.tabbedPane, E2_INSETS.I(0, 0, 0, 0));
			this.bStatusWithTabbedPane = true;

		}
	}
	
	
	
	private class ownMapsSetterAllowChangeofProgrammKenner extends XX_ComponentMAP_SubqueryAGENT {

		@Override
		public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) throws myException
		{
		}

		@Override
		public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException
		{
			boolean b_allow_edit_programmkenner1 = 	
					oUsedResultMAP.get_UnFormatedValue(_DB.ARCHIVMEDIEN$MEDIENKENNER).equals(Archiver_CONST.MEDIENKENNER.SCANNER_FILE.get_DB_WERT()) ||
					oUsedResultMAP.get_UnFormatedValue(_DB.ARCHIVMEDIEN$MEDIENKENNER).equals(Archiver_CONST.MEDIENKENNER.UPLOAD.get_DB_WERT()) ||
					oUsedResultMAP.get_UnFormatedValue(_DB.ARCHIVMEDIEN$MEDIENKENNER).equals(Archiver_CONST.MEDIENKENNER.CAM_CAPTURE.get_DB_WERT()) ||
					oUsedResultMAP.get_UnFormatedValue(_DB.ARCHIVMEDIEN$MEDIENKENNER).equals(Archiver_CONST.MEDIENKENNER.IMPORT_ABZUG.get_DB_WERT());
			
			boolean b_allow_edit_programmkenner2 = false;
			String c_actual_programm_kenner = S.NN(oUsedResultMAP.get_UnFormatedValue(ARCHIVMEDIEN.programm_kenner.fn()));
			Vector<String>  vergleich = new Vector<>();
			vergleich.add("");
			if (AM_BasicContainer.this.ddProgrammkenner!=null) {
				for (int i=0;i<AM_BasicContainer.this.ddProgrammkenner.length;i++) {
					vergleich.add(AM_BasicContainer.this.ddProgrammkenner[i][1]);
				}
				if (vergleich.contains(c_actual_programm_kenner)) {
					b_allow_edit_programmkenner2=true;
				}
			}
			
			
			//zuerst nachsehen, ob der Programmkenner ein label oder ein dropdown ist, wenn dropdown, dann inaktiv schalten, je nach medienkenner
			if (oMAP.get_hmRealDBComponents().get(_DB.ARCHIVMEDIEN$PROGRAMM_KENNER) instanceof E2_AktiveSelectorInList) {
				//die komponente ARCHIVMEDIEN$MEDIENKENNER ist normalerweise enabled
				//die bedienbarkeit wird bei allen Medienkennern ausser den folgenden ausgeschaltet
//				if (!(	oUsedResultMAP.get_UnFormatedValue(_DB.ARCHIVMEDIEN$MEDIENKENNER).equals(Archiver_CONST.MEDIENKENNER.SCANNER_FILE.get_DB_WERT()) ||
//						oUsedResultMAP.get_UnFormatedValue(_DB.ARCHIVMEDIEN$MEDIENKENNER).equals(Archiver_CONST.MEDIENKENNER.UPLOAD.get_DB_WERT()) ||
//						oUsedResultMAP.get_UnFormatedValue(_DB.ARCHIVMEDIEN$MEDIENKENNER).equals(Archiver_CONST.MEDIENKENNER.CAM_CAPTURE.get_DB_WERT()) ||
//						oUsedResultMAP.get_UnFormatedValue(_DB.ARCHIVMEDIEN$MEDIENKENNER).equals(Archiver_CONST.MEDIENKENNER.IMPORT_ABZUG.get_DB_WERT()))) {
//					((E2_AktiveSelectorInList)oMAP.get_hmRealDBComponents().get(_DB.ARCHIVMEDIEN$PROGRAMM_KENNER)).set_bDisabled();
//				}

				//nur wenn keines der beiden kriterien zutriff, ist das editieren verboten
				if (!(b_allow_edit_programmkenner1 || b_allow_edit_programmkenner2)) {
					((E2_AktiveSelectorInList)oMAP.get_hmRealDBComponents().get(_DB.ARCHIVMEDIEN$PROGRAMM_KENNER)).set_bDisabled();
				}
			}
		}
		
	}
	
	
	private class ownRecordFactory extends E2_ComponentMAP_Factory4Records {

		@Override
		public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
			return new RECORD_ARCHIVMEDIEN(cID_MAINTABLE);
		}
		
	}
	
	
	/**
	 * Eventhandler für die Verknüpfung der Archivdatei zu einem anderen Objekt
	 * @author manfred
	 * @date   08.04.2013
	 */
	private class ownActionAgentConnectToOtherObject extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			AM_BasicContainer oThis = AM_BasicContainer.this;

			MyE2_Button 		oButton = (MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource();
			E2_ComponentMAP 	oE2_ComponentMap = oButton.EXT().get_oComponentMAP();
			SQLResultMAP		oResult	= oE2_ComponentMap.get_oInternalSQLResultMAP();
            
            
			try
			{
				
				PopUp_CONNECT_ARCHIVE_ENTRY_TO_OTHER_OBJECTS oPopUp = new PopUp_CONNECT_ARCHIVE_ENTRY_TO_OTHER_OBJECTS(
																		bibALL.get_Vector(oResult.get_UnFormatedValue("ID_ARCHIVMEDIEN")),
																		oThis.get_MODUL_IDENTIFIER()
																		);
			
				oPopUp.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000),new Extent(700),new MyE2_String("Verknüpfen von Archivdateien... "));
			}
			catch (myException ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("Error opening Window for additional Connection "));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
		}
		
	}

	
	//2015-01-20: pruefen, ob der schalter "IST_ORIGINAL" gesetzt ist, wenn ja, dann wird loeschen nicht erlaubt
	private class valid_Delete_ArchivMedium extends XX_ActionValidator {

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			MyE2_MessageVector oMV_Rueck = new MyE2_MessageVector();
			Vector<String> vID_SelectedToDelete = AM_BasicContainer.this.oNavigationList_ArchivFiles.get_vSelectedIDs_Unformated(); 
			RECLIST_ARCHIVMEDIEN rlARCHIVMEDIEN = new RECLIST_ARCHIVMEDIEN(vID_SelectedToDelete);
			
			for (RECORD_ARCHIVMEDIEN recAM: rlARCHIVMEDIEN.values()) {
				if (recAM.is_IST_ORIGINAL_YES()) {
					oMV_Rueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Original-Belege dürfen nicht gelöscht werden !")));
				}
			}
			
			return oMV_Rueck;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated) throws myException {
			return null;
		}
		
	}

	
	
	private class ownActionAgentDownload extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			MyE2_Button 		oButton = (MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource();
			E2_ComponentMAP 	oE2_ComponentMap = oButton.EXT().get_oComponentMAP();
			SQLResultMAP		oResult	= oE2_ComponentMap.get_oInternalSQLResultMAP();
            
			try
			{

//				//2012-08-06:umstellung auf RECORD_ARCHIV_MEDIEN
				RECORD_ARCHIVMEDIEN_ext recMedien = new RECORD_ARCHIVMEDIEN_ext(oResult.get_UnFormatedValue("ID_ARCHIVMEDIEN"));
				recMedien.starte__downLoad();
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("E2_PopUp_For_UP_AND_DOWN_FILES:ownActionAgentDownload:doAction: "));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}

			
		}
		
	}

	
	private class ownActionAgentDelete extends ButtonActionAgentMULTIDELETE
	{
		

		public ownActionAgentDelete(MyE2_String actionName, E2_NavigationList onavigationList)
		{
			super(actionName, onavigationList);
		}

		public void Execute_After_DELETE(Vector<String> vID_toDeleteUnformated) throws myException
		{
			Vector<E2_ComponentMAP> vComponentMAPs = AM_BasicContainer.this.oNavigationList_ArchivFiles.get_vComponentMAPS();
			for (int i=0;i<vComponentMAPs.size();i++)
			{
				E2_ComponentMAP oMap = (E2_ComponentMAP)vComponentMAPs.get(i);
				SQLResultMAP 	oResult = oMap.get_oInternalSQLResultMAP();
				
				
				if (vID_toDeleteUnformated.contains(oResult.get_cUNFormatedROW_ID()))
				{
					// Manfred 2013-03-07: Prüfung, dass eine Archiv-Datei nur dann gelöscht wird, wenn kein weiterer Eintrag auf die Datei zeigt.
					String sPfad 	= oResult.get_UnFormatedValue(RECORD_ARCHIVMEDIEN.FIELD__PFAD) ;
					String sDatei 	= oResult.get_UnFormatedValue(RECORD_ARCHIVMEDIEN.FIELD__DATEINAME) ;
					
					//2015-01-20: martin: pruefung auf loeschung in eigene klasse ausgelagert
					new Archiver_Delete_File_WhenAllowed(sPfad, sDatei, true);
				}
				
			}
			
		}

		public MyE2_MessageVector CheckIdToDelete(Vector<String> vID_UnformatedToDelete) throws myException {return null;}
		public void Execute_Before_DELETE(Vector<String> vIDs_toDeleteUnformated) throws myException {}
		public Vector<String> get_vSQL_After_DELETE(String cID_toDeleteUnformated) throws myException {return null;}
		public Vector<String> get_vSQL_Before_DELETE(String cID_toDeleteUnformated) throws myException {return null;}
		
		
	}
	


	
	private class ownActionAgentUpload extends XX_ActionAgent {
 
		public void executeAgentCode(ExecINFO oExecInfo) {
			AM_BasicContainer oThis = AM_BasicContainer.this;
			try	{
				E2_PopUpWindow_for_Upload_to_Archiv oPopUPload = new E2_PopUpWindow_for_Upload_to_Archiv(
																								oThis.cTABLE_NAME,
																								oThis.cID_TABLE,
																								false,
																								"Datei ins Archiv hochladen",
																								"", 
																								oThis.oNavigationList_ArchivFiles);
				
				bibE2.GET_FIRST_CONTENTPANE_IN_SESSION().add(oPopUPload);
			} catch (myException ex) {
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}
		}
	}
	

	
	
	public String get_table_NAME() {
		return cTABLE_NAME;
	}




	public String get_id_TABLE() {
		return cID_TABLE;
	}




	public E2_NavigationList get_navigationList_ArchivFiles() {
		return oNavigationList_ArchivFiles;
	}




	public UP_DOWN_AddOn_COL_ComponentFactory get_AddOn_COMPONENT_FACTORY()
	{
		return addOn_Factory;
	}




	public void set_id_EMAIL_COMONENT_FACTORY(UP_DOWN_AddOn_COL_ComponentFactory idEMAIL_COMONENT_FACTORY)
	{
		this.addOn_Factory = idEMAIL_COMONENT_FACTORY;
	}




	public boolean get_bStatusWithTabbedPane() {
		return bStatusWithTabbedPane;
	}








//	public void set_attachMentSeeker(ES__AttachementSeeker attachmentSeeker) {
//		this.listEmailSendContainer.set_AttachMentSeeker(attachmentSeeker);
//	}
//



	public ES_LIST_BasicModuleContainer get_es_LIST_BasicModuleContainer() {
		return listEmailSendContainer;
	}




	@Override
	public Vector<TS_Treasure_Chest> get_my_treasure_chests() throws myException {
		Vector<TS_Treasure_Chest> vRueck = new Vector<TS_Treasure_Chest>();
		vRueck.add(new ownTreasureChest());
		return vRueck;
	}

	public class ownTreasureChest extends TS_Treasure_Chest {

		@Override
		public TS_DEFINITION get_TREASURE_CHEST_DEF() {
			return TS_DEFINITION.UPLOADFILES_TREASURE_CHEST;
		}

		@Override
		public Object get_TREASURE_CHEST() throws myException {
			return AM_BasicContainer.this;
		}
	}
	
	
	
	
	
	
	@Override
	/**
	 * nutzt die information aus der range-key-enum um eine besser lesbare titlezeile anzuzeigen
	 */
	public void CREATE_AND_SHOW_POPUPWINDOW(Extent				oextWidth,
											Extent				oextHeight,
											MyE2_String 		oTitle) throws myException 	{
		super.CREATE_AND_SHOW_POPUPWINDOW(oextWidth,oextHeight,false,null,oTitle);
		
		if (this.get_rangeKey()!=null) {
			
			String title = "Zusatzdateien";
			
			String title_detail = new AM_BasicContainer_SelectTitelInfo(title, this.cTABLE_NAME, this.cID_TABLE).get_Titeltext();
			if (S.isFull(title_detail)) {
				title = title_detail;
			}
			
			this.get_oWindowPane().setTitle(title);
			
			
		}
		
	}




	@Override
	public String get_ArchivBaseTable() throws myException {
		return this.get_table_NAME();
	}




	@Override
	public String get_ArchiveIdTable() throws myException {
		return get_id_TABLE();
	}




	@Override
	public E2_NavigationList get_ArchivNaviList() throws myException {
		return this.get_navigationList_ArchivFiles();
	}




	@Override
	public boolean get_ScanIs4Download() {
		return false;
	}




	@Override
	public RANGE_KEY get_RANGE_KEY() throws myException {
		return this.get_rangeKey();
	}




	
	public MyE2_TabbedPane get_tabbedPane() {
		return tabbedPane;
	}
	

	
}
