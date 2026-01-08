package panter.gmbh.Echo2.__BASIC_COMPONENTS.MODULE_REPORTLISTS;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.AgentsAndValidators.ActionAgent_RadioFunction_CheckBoxList;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.Selector_Report_Params.ENUM_Selector_Report_Params;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.E2_MassMailer_STD;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock_Vector;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_FACT;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.V_JasperHASH;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_REPORT_PREPARING;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.StringSeparator;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;
import panter.gmbh.plugins.XX_DB_PLUGIN_PREPARE_REPORTING;
import panter.gmbh.reflection.bibReflect;
import echopointng.Separator;

/*
 * popup-fenster, das eine auswahl erlaubt, welche adressen in eine 
 * e-Mail-Aktion einbezogen werden sollen
 */
public class REP_WindowPane_START extends E2_BasicModuleContainer
{
	
	public static Insets		oInRightSpace = new Insets(0,2,10,2);
	public static Extent		EXTENT_WINDOW_WIDTH = new Extent(800);
	public static Extent		EXTENT_WINDOW_HEIGHT = new Extent(700);
	
	private E2_NavigationList	oNaviListForIDs = null;
	
	private MyE2_Grid			oGridBasic = new MyE2_Grid(4,0);

//	private MyE2_Grid			GridWithCheckBoxes	= 	 			new MyE2_Grid(4,0);
	private MyE2_CheckBox		oCB_ALL_IDsInList = 				new MyE2_CheckBox();
	private MyE2_CheckBox		oCB_ALL_IDsInVisiblePage = 			new MyE2_CheckBox();
	private MyE2_CheckBox		oCB_ALL_IDsSelected = 				new MyE2_CheckBox();
	
	private MyE2_Button			buttonStartPrinting = new MyE2_Button(new MyE2_String("Starte Druck"),MyE2_Button.StyleTextButtonSTD(new E2_FontBold(2)));
	private MyE2_Button			buttonStartMailing = new MyE2_Button(new MyE2_String("Starte eMail"),MyE2_Button.StyleTextButtonSTD(new E2_FontBold(2)));
	private MyE2_Button			buttonCancel = new MyE2_Button(new MyE2_String("Abbrechen"),MyE2_Button.StyleTextButtonSTD(new E2_FontBold(2)));
	

	/*
	 * downloadtyp selektieren
	 */
	private MyE2_SelectField			oSelectFileType = new MyE2_SelectField(new Extent(120)); 
	private Vector<REP_PARAMETERDEF> 	vReportParameterDefs = null;
	private RECORD_REPORT        		recReport = null;
	private V_JasperHASH         		VJasperHASH = new V_JasperHASH();
	
	
	//2014-03-27: bevorzugter typ 
	private String   					preferedDataTyp = "";
	
	// 2016-06-01: zusätzliche Parameter, die aus dem Selektions-Vektor der Liste kommen können
	Hashtable<ENUM_Selector_Report_Params,Object>  htSelectionVectorParameter = null;
	
	/**
	 * @param RecordREPORT
	 * @param onavigationList
	 */
	public REP_WindowPane_START(	RECORD_REPORT		RecordREPORT,
									E2_NavigationList 	onavigationList) throws myException	
	{
		this(RecordREPORT,onavigationList,null);
	}
	
	
	
	/**
	 * @param RecordREPORT
	 * @param onavigationList
	 * @param oselVector      -der Selektionsvektor aus der aufrufenden Liste 
	 */
	public REP_WindowPane_START(	RECORD_REPORT		RecordREPORT,
									E2_NavigationList 	onavigationList,
									Hashtable<ENUM_Selector_Report_Params,Object> oReportParams) throws myException
	{
		super();
		
		//this.oGridBasic.setBorder(new Border(1, Color.RED, Border.STYLE_SOLID));
		
		this.htSelectionVectorParameter = oReportParams;
		
		this.oNaviListForIDs = onavigationList;
		this.recReport = RecordREPORT;
		
		this.oSelectFileType.set_ListenInhalt(this.get_SelectArrayPossibleDownTypes(),true);
		this.oSelectFileType.setSelectedIndex(0);         // vorgabe
		if (S.isFull(this.preferedDataTyp)) {
			this.oSelectFileType.set_ActiveValue_OR_FirstValue(this.preferedDataTyp);
		}
		
		this.oGridBasic.setWidth(new Extent(99,Extent.PERCENT));
		
		
		MyE2_TextArea oTFInfo = new MyE2_TextArea("",600,1000,10);
		/*
		 * styles-vorrat fuer die labels
		 */
		MutableStyle oStyleSmallLabel = new MutableStyle();
		oStyleSmallLabel.setProperty(Label.PROPERTY_FONT,new E2_FontPlain(0));
		
		MutableStyle oStyleLabelTitel = new MutableStyle();
		oStyleLabelTitel.setProperty(Label.PROPERTY_FONT,new E2_FontBold(0));;

		
		boolean bEmail = (		this.recReport.is_ALLOW_EMAIL_EMPLOYES_YES() ||
								this.recReport.is_ALLOW_EMAIL_FREE_YES() ||
								this.recReport.is_ALLOW_EMAIL_SEARCH_CUSTOMER_YES() ||
								S.isFull(this.recReport.get_STATIC_MAIL_ADRESSES_cUF_NN("")));
						
		this.add(this.oGridBasic);
		this.set_Component_To_ButtonPane(new E2_ComponentGroupHorizontal(0,	
																		this.buttonStartPrinting,
																		this.buttonCancel,
																		bEmail?this.buttonStartMailing:new MyE2_Label(""),
																		new Insets(8,10,12,1)));
		
		this.buttonCancel.add_oActionAgent(new ownActionCancel());
		
		//2013-10-08: pruefe ob print-prepare-klassen zum report vorhanden ist und gegebenenfalls ausfuehren
		this.buttonStartPrinting.add_oActionAgent(new ownAction_PrepareStartPrinting());
		this.buttonStartPrinting.add_oActionAgent(new ownActionStartPrinting());
		this.buttonStartMailing.add_oActionAgent(new ownActionStartMail());
		

		// bereichsauswahl definieren
		ActionAgent_RadioFunction_CheckBoxList oRadio1 = new ActionAgent_RadioFunction_CheckBoxList(false);
		oRadio1.add_CheckBox(oCB_ALL_IDsInList);
		oRadio1.add_CheckBox(oCB_ALL_IDsInVisiblePage);
		oRadio1.add_CheckBox(oCB_ALL_IDsSelected);
		
		
		if (this.oNaviListForIDs!=null)
		{
			if (this.oNaviListForIDs.get_vSelectedIDs_Unformated().size()>0)
			{
				oCB_ALL_IDsSelected.setSelected(true);
			}
			else
			{
				oCB_ALL_IDsInVisiblePage.setSelected(true);
			}
		}
		
		
		Insets	oIN = 		new Insets(8,3,8,3);
		Insets	oIN4Reps = 	new Insets(8,1,8,1);
		Insets	oINHigh = 	new Insets(8,3,8,10);
		
		
		// beschriftungen und informationen
		if (S.isFull(this.recReport.get_TITEL_cUF_NN("")))
		{
			this.oGridBasic.add(new MyE2_Label(new MyE2_String(this.recReport.get_TITEL_cUF_NN("")),MyE2_Label.STYLE_TITEL_BIG()),4,oIN);
			
			String cInfoText = this.recReport.get_BESCHREIBUNG_cUF_NN("");
			if (S.isFull(cInfoText))
			{
				oTFInfo.setText(cInfoText);
				
				oTFInfo.setEnabled(false);
				
				oTFInfo.setWidth(new Extent(100, Extent.PERCENT));
				
				
				this.oGridBasic.add(oTFInfo,4,oIN);
				oTFInfo.setBackground(new E2_ColorBase());
			}
			oGridBasic.add(new Separator(),4,oIN);
		}
		

		if (this.recReport.is_PASS_NO_ID_NO() && this.oNaviListForIDs==null)
		{
			throw new myExceptionForUser("In diesem Modul können nur Reports erzeugt werden, die den Schalter <Keine ID-Übergabe> tragen !!");
		}
		
		
		
//		if (this.oNaviListForIDs!=null)
//		{
//			// bereich der auswahl sicherstellen (wird nicht eingeblendet bei reports ohne ID)
//			GridWithCheckBoxes.add(new MyE2_Label(new MyE2_String("Bitten wählen Sie den Bereich aus, der im Report verwendet werden soll"),MyE2_Label.STYLE_NORMAL_BOLD()),4,oIN);
//			
//			GridWithCheckBoxes.add(new MyE2_Label(new MyE2_String("Alle Datensätze in der momentanen Liste"),MyE2_Label.STYLE_NORMAL_PLAIN()),2,oIN);
//			GridWithCheckBoxes.add(new MyE2_Label(new MyE2_String(""+this.oNaviListForIDs.get_vectorSegmentation().size(),false),MyE2_Label.STYLE_NORMAL_PLAIN()),oIN);
//			GridWithCheckBoxes.add(oCB_ALL_IDsInList,oIN);
//			
//			GridWithCheckBoxes.add(new MyE2_Label(new MyE2_String("Alle Datensätze auf der momentanen Seite"),MyE2_Label.STYLE_NORMAL_PLAIN()),2,oIN);
//			GridWithCheckBoxes.add(new MyE2_Label(new MyE2_String(""+this.oNaviListForIDs.get_vActualID_Segment().size(),false),MyE2_Label.STYLE_NORMAL_PLAIN()),oIN);
//			GridWithCheckBoxes.add(oCB_ALL_IDsInVisiblePage,oIN);
//	
//			GridWithCheckBoxes.add(new MyE2_Label(new MyE2_String("Alle ausgewählten Datensätze"),MyE2_Label.STYLE_NORMAL_PLAIN()),2,oIN);
//			GridWithCheckBoxes.add(new MyE2_Label(new MyE2_String(""+this.oNaviListForIDs.get_vSelectedIDs_Unformated().size(),false),MyE2_Label.STYLE_NORMAL_PLAIN()),oIN);
//			GridWithCheckBoxes.add(oCB_ALL_IDsSelected,oIN);
//		}

		// auswahlmoeglichkeit wird nur in den reports gezeigt, die dafuer vorgesehen sind
		if (this.recReport.is_PASS_NO_ID_NO())
		{
			//this.oGridBasic.add(this.GridWithCheckBoxes,4,oIN);
			// bereich der auswahl sicherstellen (wird nicht eingeblendet bei reports ohne ID)
			if (this.oNaviListForIDs!=null)
			{
				this.oGridBasic.add(new MyE2_Label(new MyE2_String("Bitte wählen Sie den Bereich aus, der im Report verwendet werden soll"),MyE2_Label.STYLE_TITEL_BIG()),4,oINHigh);
				
				this.oGridBasic.add(new MyE2_Label(new MyE2_String("Alle Datensätze in der momentanen Liste"),MyE2_Label.STYLE_NORMAL_PLAIN()),1,oIN);
				this.oGridBasic.add(new MyE2_Label(new MyE2_String(""+this.oNaviListForIDs.get_vectorSegmentation().size(),false),MyE2_Label.STYLE_NORMAL_PLAIN()),1,oIN);
				this.oGridBasic.add(oCB_ALL_IDsInList,1,oIN);
				this.oGridBasic.add(new MyE2_Label(" "),1,oIN);
				
				this.oGridBasic.add(new MyE2_Label(new MyE2_String("Alle Datensätze auf der momentanen Seite"),MyE2_Label.STYLE_NORMAL_PLAIN()),1,oIN);
				this.oGridBasic.add(new MyE2_Label(new MyE2_String(""+this.oNaviListForIDs.get_vActualID_Segment().size(),false),MyE2_Label.STYLE_NORMAL_PLAIN()),1,oIN);
				this.oGridBasic.add(oCB_ALL_IDsInVisiblePage,1,oIN);
				this.oGridBasic.add(new MyE2_Label(" "),1,oIN);
		
				this.oGridBasic.add(new MyE2_Label(new MyE2_String("Alle ausgewählten Datensätze"),MyE2_Label.STYLE_NORMAL_PLAIN()),1,oIN);
				this.oGridBasic.add(new MyE2_Label(new MyE2_String(""+this.oNaviListForIDs.get_vSelectedIDs_Unformated().size(),false),MyE2_Label.STYLE_NORMAL_PLAIN()),1,oIN);
				this.oGridBasic.add(oCB_ALL_IDsSelected,1,oIN);
				this.oGridBasic.add(new MyE2_Label(" "),1,oIN);
			}
			
			this.oGridBasic.add(new Separator(),4,oIN);
		}
		
		
		
		
		/*
		 * dann die downloadtyp-auswahl anzeigen 
		 */
		this.oGridBasic.add(new MyE2_Label(new MyE2_String("Gewünscher Dateityp: "),MyE2_Label.STYLE_TITEL_BIG()),1,oIN);
		this.oGridBasic.add(this.oSelectFileType,3,oIN);
		this.oGridBasic.add(new Separator(),4,oIN);
		// --------------------------------------------------------------------------
		
		
		
		// jetzt die parameter (falls welche vorhanden sind) abfragen
		Vector<REP_PARAMETERDEF>		vREP_Params = this.get_VectorWithParameterDefs();
//		MyE2_Grid						oGridParams = new MyE2_Grid(3,0);	
		
		if (vREP_Params.size()>0)
		{
			oGridBasic.add(new MyE2_Label(new MyE2_String("Parameter-Eingabe für den Report ..."),MyE2_Label.STYLE_TITEL_BIG()),4,oIN);
			
			for (int i=0;i<vREP_Params.size();i++)
			{
				REP_PARAMETERDEF oParam = vREP_Params.get(i);
//				oGridParams.add(oParam.get_oLabel_UserInfo(),REP_WindowPane_START.oInRightSpace);
//				oGridParams.add(oParam.get_oComponent_ForUserInput(),REP_WindowPane_START.oInRightSpace);
//				oGridParams.add(oParam.get_oComponentHELP(),REP_WindowPane_START.oInRightSpace);
				oGridBasic.add(oParam.get_oLabel_UserInfo(),oIN4Reps);
				oGridBasic.add(oParam.get_oComponent_ForUserInput(),2,oIN4Reps);
				oGridBasic.add(oParam.get_oComponentHELP(),oIN4Reps);
			}
		//	oGridBasic.add(oGridParams,4,oIN);
			
		}
		
		
		//close-Action, teporaere daten loeschen
		this.add_CloseActions(new XX_ActionAgentWhenCloseWindow(this)
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				REP_WindowPane_START oThis = REP_WindowPane_START.this;
				if (oThis.VJasperHASH.size()>0)
				{
					if (oThis.VJasperHASH.get(0).containsKey("REPORTNUMMER") && 	S.isFull(((String)oThis.VJasperHASH.get(0).get("REPORTNUMMER"))))
					{
						bibDB.ExecSQL("DELETE FROM "+bibE2.cTO()+".JD_REPORTAKTION WHERE REPORTNUMMER="+(String)oThis.VJasperHASH.get(0).get("REPORTNUMMER"),true);
					}
				}
			}
		});
		
			
		this.CREATE_AND_SHOW_POPUPWINDOW_SPLIT(REP_WindowPane_START.EXTENT_WINDOW_WIDTH, REP_WindowPane_START.EXTENT_WINDOW_HEIGHT,new MyE2_String("Listen und Reports drucken ..."));
	}

	
	
	
	
	
	
	
	
	public String[][] get_SelectArrayPossibleDownTypes() throws myException
	{
		
		// im falle es ist ein "single-Id-Typ"
		// geht nur pdf, weil nur dort eine verkettung moeglich ist

		if (this.recReport.is_PASS_SINGLE_ID_YES())
		{
			String[][] cRueckPDF = new String[1][2];
			cRueckPDF[0][0]="PDF-Datei";
			cRueckPDF[0][1]=JasperFileDef.MIMETYP_PDF;
			return cRueckPDF;
		}
		
		
		
		int iCount = 0;
		if (this.recReport.is_ALLOW_PDF_YES()) 		iCount ++;
		if (this.recReport.is_ALLOW_XLS_YES()) 		iCount ++;
		if (this.recReport.is_ALLOW_HTML_YES()) 	iCount ++;
		if (this.recReport.is_ALLOW_TXT_YES()) 		iCount ++;
		if (this.recReport.is_ALLOW_XML_YES()) 		iCount ++;
		
		String[][] cRueck = new String[iCount][2];
		
		iCount = 0;
		if (this.recReport.is_ALLOW_PDF_YES()) {
			cRueck[iCount][0]="PDF-Datei";
			cRueck[iCount++][1]=JasperFileDef.MIMETYP_PDF;
		}

		
		if (this.recReport.is_ALLOW_XLS_YES()) {
			cRueck[iCount][0]="XLS-Datei";
			cRueck[iCount++][1]=JasperFileDef.MIMETYP_XLS;
		}

		if (this.recReport.is_ALLOW_TXT_YES()) {
			cRueck[iCount][0]="TXT-Datei";
			cRueck[iCount++][1]=JasperFileDef.MIMETYP_CSV;
		}

		if (this.recReport.is_ALLOW_HTML_YES()) {
			cRueck[iCount][0]="HTML-Datei";
			cRueck[iCount++][1]=JasperFileDef.MIMETYP_HTML;
		}

		if (this.recReport.is_ALLOW_XML_YES()) {
			cRueck[iCount][0]="XML-Datei";
			cRueck[iCount++][1]=JasperFileDef.MIMETYP_XML;
		}
		
		if (this.recReport.is_PREFER_PDF_YES()) {this.preferedDataTyp=JasperFileDef.MIMETYP_PDF; }
		if (this.recReport.is_PREFER_XLS_YES()) {this.preferedDataTyp=JasperFileDef.MIMETYP_XLS; }
		if (this.recReport.is_PREFER_TXT_YES()) {this.preferedDataTyp=JasperFileDef.MIMETYP_CSV; }
		if (this.recReport.is_PREFER_HTML_YES()) {this.preferedDataTyp=JasperFileDef.MIMETYP_HTML; }
		if (this.recReport.is_PREFER_XML_YES()) {this.preferedDataTyp=JasperFileDef.MIMETYP_XML; }
		
		return cRueck;
	}
	
	

	/*
	 * liefert einen Vector mit objecten vom Type REP_Parameterdef
	 */
	private Vector<REP_PARAMETERDEF> get_VectorWithParameterDefs() throws myException
	{
		if (this.vReportParameterDefs == null)
		{
			this.vReportParameterDefs = new Vector<REP_PARAMETERDEF>();

			StringSeparator oSep = new StringSeparator(REP_PARAMETERDEF.FIELD_LIST,"|");

			String cQuery = "SELECT "+bibALL.Concatenate(oSep,",", null)+" FROM "+bibE2.cTO()+
							".JT_REPORT_PARAMETER WHERE ID_REPORT="+this.recReport.get_ID_REPORT_cUF()+" ORDER BY TEXTUSERINTERFACE";
			
			String[][] cResult = bibDB.EinzelAbfrageInArray(cQuery,"");

			if (cResult == null)
				throw new myException("REP_LISTDEF:get_VectorWithParameterDefs:Error Quering Parameters !");
			
			for (int i=0;i<cResult.length;i++)
			{
				REP_PARAMETERDEF paramDef = new REP_PARAMETERDEF(cResult[i], this.htSelectionVectorParameter);
				
				this.vReportParameterDefs.add(paramDef);
			}
		}
		
		return this.vReportParameterDefs;
	}
	
	
	

	
	
	private void  fill_V_JasperHASH() throws myException, myExceptionForUser
	{

		this.VJasperHASH.removeAllElements();
		
		
		Vector<String> 			vID_DATA_TABLE = new Vector<String>();
		
		String cReportBaseName = this.recReport.get_NAME_OF_REPORTFILE_cUF_NN("").trim();
		
		if (cReportBaseName.equals(""))
		{
			throw new myExceptionForUser("Es wurde keine Reportdatei angegeben !!");
		}

		
		if (this.recReport.is_PASS_NO_ID_NO() && this.oNaviListForIDs==null)
		{
			throw new myExceptionForUser("In diesem Modul können nur Reports erzeugt werden, die den Schalter <Keine ID-Übergabe> tragen !!");
		}

		if (this.oNaviListForIDs!=null)
		{
			if 	(this.oCB_ALL_IDsInList.isSelected())
			{
				vID_DATA_TABLE.addAll(this.oNaviListForIDs.get_vectorSegmentation());
			}
			else if (this.oCB_ALL_IDsInVisiblePage.isSelected())
			{
				vID_DATA_TABLE.addAll(this.oNaviListForIDs.get_vActualID_Segment());
			}
			else if (this.oCB_ALL_IDsSelected.isSelected())
			{
				vID_DATA_TABLE.addAll(this.oNaviListForIDs.get_vSelectedIDs_Unformated());
			}
		}
		
		if (vID_DATA_TABLE.size()==0 && this.recReport.is_PASS_NO_ID_NO())
		{
			throw new myExceptionForUser("Sie haben keinen Eintrag aus der Liste ausgewählt !!");
		}
		
		
		
		String 						cReportNUMMER = 	null;

		Vector<REP_PARAMETERDEF> 	vREPORT_PARAMS = this.get_VectorWithParameterDefs();
		
		HashMap<String,String> 		oHMTemp = 			new HashMap<String, String>();

		// report-parameter testen
		for (int i=0;i<vREPORT_PARAMS.size();i++)
		{
			REP_PARAMETERDEF oRepDef = (REP_PARAMETERDEF)vREPORT_PARAMS.get(i);
			
			String cParamName = oRepDef.get_PARAMETERNAME_IN_REPORT();
			String cParamValue = oRepDef.get_validatedInput();
			
			if (cParamValue.equals("@@@"))
			{
				throw new myExceptionForUser(new MyE2_String(new MyE2_String("Falsche/Fehlende Eingabe: ").CTrans()+oRepDef.get_TEXTUSERINTERFACE().CTrans(),false));
			}
			else
			{
				oHMTemp.put(cParamName,cParamValue);
			}
		}
		
		
		// bei  multi-id-verarbeitung muss die gegentabelle gefuellt werden
		if (this.recReport.is_PASS_MULTI_ID_YES())
		{
			// dann werden die zugehoerigen IDs in die Tabelle JD_REPORTAKTION geschrieben
			cReportNUMMER = bibDB.EinzelAbfrage("SELECT "+bibE2.cTO()+".SEQ_REPORTNUMMER.NEXTVAL FROM DUAL");
			
			if (!bibALL.isLong(cReportNUMMER))
			{
				throw new myExceptionForUser("Ich konnte keine freie Report-Nummer ermitteln (SEQ_REPORTNUMMER)!");
			}
			else
			{
				String cSQL_Base = "INSERT INTO "+bibE2.cTO()+".JD_REPORTAKTION (REPORTNUMMER,ID_REPORTAKTION,ID_TABLE) VALUES (" +
											cReportNUMMER+",SEQ_REPORTAKTION.NEXTVAL,";
				for (int i=0,k=vID_DATA_TABLE.size();i<k;i++)
				{
					String cSQL = cSQL_Base+vID_DATA_TABLE.get(i)+")";
					if (!bibDB.ExecSQL(cSQL,false))
					{
						bibDB.Rollback();
						throw new myExceptionForUser("Fehler beim Schreiben der Reportliste");
					}
				}
				bibDB.Commit();
			}
		}
		
		
		/*
		 * multi-id heisst ein report, variable download-filetypen
		 */
		if (this.recReport.is_PASS_MULTI_ID_YES() || this.recReport.is_PASS_NO_ID_YES())
		{
			REP_JasperHASH oHMValues = new REP_JasperHASH(cReportBaseName, JasperFileDef_FACT.get_JasperFileDef(this.oSelectFileType.get_ActualWert()),this.recReport);

			oHMValues.putAll(oHMTemp);
			
			// jetzt den sortierstring festlegen
			if (this.oNaviListForIDs!=null)
			{
				oHMValues.put("ORDERSTRING",this.oNaviListForIDs.get_oComponentMAP__REF().get_oSQLFieldMAP().get_actualOrderBlock());
			}
			
			//reportnummer wird bei multipass-ids uebergeben
			if (this.recReport.is_PASS_MULTI_ID_YES())
			{
				oHMValues.put("REPORTNUMMER",cReportNUMMER);
			}
			//2014-03-26: neue benennung der downloadnamen
			oHMValues.set_cDownloadAndSendeNameStaticPart(bibALL.CleanString4Filename_NG(this.recReport.get_BUTTONTEXT_cUF_NN("list")));
			
			this.VJasperHASH.add(oHMValues);
		}
		
		
			

		
		/*
		 * get_PASS_SINGLE_ID heisst ein report mehrfach ausgefuehrt, kann nur mit pdf erfolgen,
		 * unabhaengig was fuer ein datei-typ angewaehlt wurde,
		 * fuer jede id wird eine eigene hashmap erzeugt mit der jeweiligen nummer
		 */
		if (this.recReport.is_PASS_SINGLE_ID_YES())
		{

			/*
			 * maximal 100 formulare aufs mal zulassen 
			 */
			if (vID_DATA_TABLE.size()<=100)
			{
				for (int i=0;i<vID_DATA_TABLE.size();i++)
				{
					REP_JasperHASH oHMValues = new REP_JasperHASH(cReportBaseName, new JasperFileDef_PDF(),this.recReport);
					oHMValues.putAll(oHMTemp);

					// jetzt den sortierstring festlegen
					String cOrderString = this.oNaviListForIDs.get_oComponentMAP__REF().get_oSQLFieldMAP().get_actualOrderBlock();
					oHMValues.put("ORDERSTRING",cOrderString);
					oHMValues.put("IDRECORD",vID_DATA_TABLE.get(i));
					oHMValues.putAll(oHMTemp);
					oHMValues.set_cDownloadAndSendeNameStaticPart(bibALL.CleanString4Filename_NG(this.recReport.get_BUTTONTEXT_cUF_NN("list")));
					
					this.VJasperHASH.add(oHMValues);
				}
			}
			else
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Für einen Einzelreport sind maximal 100 Datensätze zugelassen !"));
			}
		}				
		
	
	}
	
	
	
	
	
	
	
	
	private class ownActionCancel extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			REP_WindowPane_START.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Report/Liste wurde abgebrochen !"));
		}
	}

	
	private class ownActionStartPrinting extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			REP_WindowPane_START 	oThis = 		REP_WindowPane_START.this;
			
			try
			{
				oThis.fill_V_JasperHASH();
				oThis.VJasperHASH.CREATE_TEMP_FILES_FOR_ALL_HASHMAPS(null, false, true);
				oThis.VJasperHASH.DownloadFiles(null);    //2019-05-29: tausch der Methode
			}
			catch (myExceptionForUser ex)
			{
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
		}
	}
	
	
	/**
	 * 2013-10.08: neuer actionagent vor dem starten von liste:
	 * Hier wird geprueft, ob einer Liste in der Tabelle
	 * JT_REPORT_PREPARING eine vorbereitungsklasse zugeteilt ist, wenn ja, dann wird  oder werden diese
	 * klassen ausgefuehrt
	 * 
	 * @author martin
	 *
	 */
	private class ownAction_PrepareStartPrinting extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			REP_WindowPane_START 	oThis = 		REP_WindowPane_START.this;
			
			try
			{
				String cNameReportBase = oThis.recReport.get_NAME_OF_REPORTFILE_cUF_NN("");
				if (S.isFull(cNameReportBase)) {
					int iPosEndung = cNameReportBase.toUpperCase().indexOf(".JASPER");
					if (iPosEndung<=0) {
						iPosEndung = cNameReportBase.toUpperCase().indexOf(".JRXML");
					}
					
					if (iPosEndung>0 ) {
						cNameReportBase = cNameReportBase.substring(0,iPosEndung);
					}
					
					
					RECLIST_REPORT_PREPARING  recListPrep = 
							new RECLIST_REPORT_PREPARING("SELECT * FROM "+bibE2.cTO()+"."+
														_DB.REPORT_PREPARING+" WHERE UPPER("+_DB.REPORT_PREPARING$REPORTBASENAME+")='"+
														cNameReportBase.toUpperCase()+"' ORDER BY "+_DB.REPORT_PREPARING$REIHENFOLGE);
				
					for (int i=0;i<recListPrep.get_vKeyValues().size();i++) {
						String cClassName = recListPrep.get(i).get_PREPARECLASS_cUF_NN("");
						
						if (S.isFull(cClassName)) {
							Object obPrepare = bibReflect.createInstanceClassForName(cClassName);
							
							if (obPrepare instanceof XX_DB_PLUGIN_PREPARE_REPORTING) {
								if (((XX_DB_PLUGIN_PREPARE_REPORTING)obPrepare).CHECK_IF_MUST_BE_EXECUTED()) {
									bibMSG.add_MESSAGE(((XX_DB_PLUGIN_PREPARE_REPORTING)obPrepare).EXECUTED_BEFORE_REPORT());
								}
							}
							obPrepare = null;
						}
					}
				}
				
			}
			catch (myExceptionForUser ex)
			{
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
		}
	}
	
	
	
	
	
	
	private class ownActionStartMail extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			REP_WindowPane_START 	oThis = 		REP_WindowPane_START.this;
			
			try
			{
				oThis.fill_V_JasperHASH();
				
				if (!oThis.VJasperHASH.get_bAreALLDesignedForMail())
				{
					throw new myExceptionForUser("Die Reportdefinition erlaubt keine eMails !");
				}
				
				oThis.VJasperHASH.CREATE_TEMP_FILES_FOR_ALL_HASHMAPS(null, false,false);
				MailBlock_Vector vMailBlocks = oThis.VJasperHASH.BUILD_AND_GET_V_MAIL_BLOCKS();
				
				E2_MassMailer_STD  oMassMailer = new E2_MassMailer_STD("MAIL_BETREFF_LISTENMAILER","MAIL_BLOCK_LISTENMAILER","LISTEN");
				oMassMailer.baue_MailMaske(vMailBlocks, bibALL.get_RECORD_USER().get_EMAIL_cUF_NN(""), true, false, false, 0, null);
				
				oMassMailer.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000), new Extent(700), new MyE2_String("eMails versenden"));
				
			}
			catch (myExceptionForUser ex)
			{
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
			
		}
	}
	
	
	
}
