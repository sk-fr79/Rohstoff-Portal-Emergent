package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.Echo2.components.E2_calendar.E2_TF_4_Date;
import panter.gmbh.Echo2.components.E2_calendar.E2_TF_4_Date_Enum;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG.WF_Handler;


/**
 * Dialog zum kopieren eines Laufzettels
 * @author manfred
 * @date 18.10.17
 *
 */
public class WF_Dialog_Copy_Laufzettel extends E2_BasicModuleContainer
{
	
	
	private E2_NavigationList				_NavList	 	= null;
	private String 							_idLaufzettel;
	private boolean 						_copyAnhang 	= true;
	
	Vector<String> 							_vSqlStatements = null;
	
	// Variablen
	private MyE2_Label 						lblFaelligkeit		= null;
	private E2_TF_4_Date					tfDate 				= null;
	private MyE2_Button  					oButtonSave 		= null;
	private MyE2_Button  					oButtonCancel 		= null;
	private MyE2_CheckBox					cbCopyAnhang		= null;
	private MyE2_TextArea					tfWFDescription    	= null;
	
	E2_Grid 								oGrid		 		= null;
	Rec21									oRec				= null;

	

	public WF_Dialog_Copy_Laufzettel(String idLaufzettel, E2_NavigationList navListToRefresh ) throws myException
	{
		super();
		this.set_MODUL_IDENTIFIER(E2_MODULNAME_ENUM.MODUL.WF_COPY_LAUFZETTEL.get_callKey());
		
		this._NavList = navListToRefresh;
		this._idLaufzettel = idLaufzettel;
		
		this.initMask();
		this.initData();
	}


	

	private void initMask() throws myException {
		
		E2_MutableStyle oStyleCheckboxNoBorder = new E2_MutableStyle();
		oStyleCheckboxNoBorder.setProperty(CheckBox.PROPERTY_BORDER, new Border(0, new E2_ColorBase(-1), Border.STYLE_NONE));

		// initialisieren der einzelnen Objekte
		this.tfWFDescription 		= new MyE2_TextArea("",500,2000,10);
		
		this.lblFaelligkeit			= new MyE2_Label(new MyE2_String("Neuer Fälligkeitstermin "));

		this.tfDate					= new E2_TF_4_Date();
		tfDate.setSelectionMode(E2_TF_4_Date_Enum.SLIP_SELECTION_MODE);
		
			
		
		cbCopyAnhang				= new MyE2_CheckBox(new MyString("").CTrans());
		cbCopyAnhang.setSelected(_copyAnhang);
		
		
		
		oButtonSave 				= new MyE2_Button("Laufzettel kopieren");
		oButtonSave.setFont(new E2_FontBold(1));
		oButtonSave.add_oActionAgent(new actionCopyLaufzettel());
		
		oButtonCancel 				= new MyE2_Button("Abbrechen");
		oButtonCancel.setFont(new E2_FontBold(1));
		oButtonCancel.add_oActionAgent(new actionCancel());
		

		Alignment al = new Alignment(Alignment.RIGHT,Alignment.CENTER);
		Insets oIn = new Insets(4,1,2,1);
		Insets oInSep = new Insets(0,5,0,0);
		
		
		
		//
		// Layout
		//
		MyE2_Label lblHeading = new MyE2_Label("Laufzettel kopieren", MyE2_Label.STYLE_TITEL_BIG_PLAIN());
		
		E2_Grid   oGrid = new E2_Grid()._s(2);
		oGrid._gld_add_insets(E2_INSETS.I_5_0_5_0);
		oGrid._a(lblHeading, LayoutDataFactory.get_GridLayoutGridLeftMID(E2_INSETS.I_5_10_5_10, 2));
		
		// neuer Beschreibungstext
		oGrid._a("Beschreibung",LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_5_0_5_0))._a(tfWFDescription,LayoutDataFactory.get_GridLayoutGridLeftMID(E2_INSETS.I_5_0_5_0));
		
		oGrid._a(this.lblFaelligkeit,LayoutDataFactory.get_GridLayoutGridLeftMID(E2_INSETS.I_5_0_5_0))._a(this.tfDate,LayoutDataFactory.get_GridLayoutGridLeftMID(E2_INSETS.I_5_0_5_0));
		oGrid._endLine(null);
		oGrid._a("Anhänge übernehmen",LayoutDataFactory.get_GridLayoutGridLeftMID(E2_INSETS.I_5_0_5_0))._a(cbCopyAnhang,LayoutDataFactory.get_GridLayoutGridLeftMID(E2_INSETS.I_5_0_5_0));
		oGrid._a("")._endLine(null);
		oGrid._a("")._endLine(null);
		
		oGrid._a(
			new E2_Grid()._s(2)
				._a(this.oButtonSave,LayoutDataFactory.get_GridLayoutGridLeftMID(E2_INSETS.I_5_10_5_10))
				._a(this.oButtonCancel,LayoutDataFactory.get_GridLayoutGridLeftMID(E2_INSETS.I_5_10_5_10))
			,new RB_gld()._span(2)._left_mid())
		;
		
		
		this.add(oGrid,E2_INSETS.I_2_2_2_2);

		
		// nach dem Schliessen der Erfassung muss der parent aktualisiert werden
		this.add_CloseActions(new XX_ActionAgentWhenCloseWindow(this){
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				if (_NavList!= null){
					_NavList.RefreshList();
				}
			}
		});

		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800), new Extent(500), new MyE2_String("Laufzettel kopieren"));
	}
	
	
	
	/**
	 * Setzt das Daum im Datumsfeld
	 * @author manfred
	 * @date 18.10.2017
	 *
	 * @param value
	 * @throws myException
	 */
	private void setValue(String value) throws myException {
		if (!bibALL.isEmpty(value)){
			MyDate oDate = new MyDate(value, MyDate.DATE_FORMAT.YYYY_MM_DD_DASH);
			this.tfDate.set_oLastDateKlicked(new myDateHelper(oDate.get_cDateStandardFormat()));
			this.tfDate.get_oTextField().setText(this.tfDate.get_oLastDateKlicked().get_cDateFormatForMask());
		} 
	}

	
	
	/**
	 * liest das Datum aus dem Datumsfeld
	 * @author manfred
	 * @date 18.10.2017
	 *
	 * @return
	 * @throws myException
	 */
	private String getNewDeadlineValue() throws myException {

//		myDateHelper dt  = this.tfDate.get_oLastDateKlicked();
		
		String dateValue = ""; 
		dateValue = tfDate.get_oDBFormatedDateFromTextField();
//		if (dt != null){
//			dateValue = dt.get_cDateFormat_ISO_FORMAT(); 
//		}
		return dateValue;
	}
	
		
	
	/**
	 * setzen der Daten bei der Initialisierung der Maske
	 * @throws myException 
	 */
	private void initData() throws myException{
		if (S.isEmpty(_idLaufzettel)){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Die Daten für die Laufzettel-Kopie wurden nicht gesetzt."));
		}
		oRec = new  Rec21(_TAB.laufzettel)._fill_id(_idLaufzettel);
		
		this.tfWFDescription.setText(oRec.getUfs(LAUFZETTEL.text));
		
		
	}
	
	
	

	
	
	
		
	
	/**
	 * Cancel-Action
	 * @author manfred
	 * @date   07.02.2012
	 */
	private class actionCancel extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Der Kopiervorgang wurde abgebrochen")));
			CLOSE_AND_DESTROY_POPUPWINDOW(true);
		}
	}
	
	
	/**
	 * Speichern-Action
	 * @author manfred
	 * @date   07.02.2012
	 */
	private class actionCopyLaufzettel extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			WF_Dialog_Copy_Laufzettel oThis = WF_Dialog_Copy_Laufzettel.this; 
			WF_Handler oHandler = new WF_Handler();
			String sNewDeadline = oThis.getNewDeadlineValue();
			String sNewText = oThis.tfWFDescription.getText();
			
			MyE2_MessageVector mv = oHandler.copyWorkflow(oThis._idLaufzettel, sNewDeadline, sNewText, oThis.cbCopyAnhang.isSelected());
			
			if (mv.get_bIsOK()){
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Der Kopiervorgang wurde erfolgreicht durchgeführt")));
				CLOSE_AND_DESTROY_POPUPWINDOW(true);
			}
			
		}

	}



	
	
}
