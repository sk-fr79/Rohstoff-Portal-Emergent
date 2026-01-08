package rohstoff.Echo2BusinessLogic.ABZUEGE;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.ColumnLayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_FieldSetter_AND_Validator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_FieldValidator_InputAllowed;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_SettingAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Icon;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentColumn;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentGrid;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextFieldLABEL;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_ButtonMarkForDelete;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_DBC_DaughterTable;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WAEHRUNG;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataToView;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;



public abstract class BL_Daughter_Abzuege extends MyE2_DBC_DaughterTable
{
	public static final String 	KENNER_ABZUGTYP_MULTI_COL = 	"KENNER_ABZUGTYP_MULTI_COL";
	public static final String 	KENNER_MENGE_MULTI_COL = 		"KENNER_MENGE_MULTI_COL";
	public static final String 	KENNER_EPREIS_MULTI_COL = 		"KENNER_EPREIS_MULTI_COL";
	public static final String 	KENNER_EPREIS_FW_MULTI_COL =	"KENNER_EPREIS_FW_MULTI_COL";
	public static final String 	KENNER_DUMMY_MULTI_COL =		"KENNER_DUMMY_MULTI_COL";
	public static final String 	KENNER_DEF_ABZUG = 				"KENNER_DEF_ABZUG";
	public static final String 	KENNER_DEF_SPALTE1 = 			"KENNER_DEF_SPALTE1";

	//zusatzfelder in jede zeile
	public static final String  HASHKEY_INFOBLOCK = 			"HASHKEY_INFOBLOCK";
	
    //2011-06-10: info-button zur erlaueterung der jeweiligen abzugsart, kann im mandantenstamm erfasst werden
    public static String		HASHKEY_LIST_INFO_BUTTON 	= 	"HASHKEY_LIST_INFO_BUTTON";

	
	private SQLFieldForPrimaryKey 	oSqlField = null; 
	private E2_ComponentMAP			oComponentMAP_from_Mother = null;
	private E2_ComponentMAP 		oMap = null;                         // die eigene componentmap
	
	private String 					cTABLE_NAME = null;
	
	
	/*
	 * die select-box abzugstyp wird von der rufenden einheit manipuliert, es wird
	 * die jeweils gueltige Mengeneinheit eingesetzt  
	 */
	private MyE2_DB_SelectField 	oSelABZUGTYP = 			null;
 
	

	/*
	 * falls die abzuege aus RG-positionen benutzt werden, dann wird der neukalkulations-
	 * button mit uebergeben, da er bei jeder aktion ausgeloest werden muss.
	 * sonst koennte die abzugsliste mit falschen voraussetzungen rechnen
	 */
	private MyE2_Button  			oButtonForRecalFremdwaehrungsPreise = null;
	
	//bei den Beanstandungsmeldungen werden die einheiten uebergeben
	String cEinheit = "";
	String cEinheit_Preis = "";
	
	
	private String   				cWaehrungsSymbolMandant = bibE2.get_cBASISWAEHRUNG_SYMBOL();
	private String   				cWaehrungsSymbolFremdwaehrung = bibE2.get_cBASISWAEHRUNG_SYMBOL();
	

	private buttonForAddNEW 		oButNew = 		new buttonForAddNEW();
	private button_refresh 			oButRefresh = 	new button_refresh();

	
	//sorgt dafuer, dass beim ersten XX_FieldValidator_InputAllowed (wird hier missbraucht) der titelblock gefuellt wird.
	//dies ist normalerweise beim ersten maskenaufbau
	private boolean   bTitelBlockIstGefuellt = false;   
	
	
	
	
	//neue abstrakte methoden
	public abstract  Component     		get__TitleComponentWithButtons() 	throws myException;
	public abstract  RECORD_WAEHRUNG 	get__RECORD_FREMDWAEHRUNG() 		throws myException;
	public abstract  RECORD_ARTIKEL  	get__RECORD_ARTIKEL() 				throws myException;
	public abstract  String             get__ActualFormatedValueMenge()     throws myException;
	public abstract  String             get__ActualFormatedValuePreis()     throws myException;
	public abstract  String             get__ActualFormatedValuePreis_FW()     throws myException;
	public abstract  String             get__ActualFormatedWaehrungsKurs()     throws myException;
	
	public abstract  void               fill__MaskWithCalcResults(BL_Daughter_Abzuege DaughterAbzuege, BL_AbzugsKalkulator oBL_Kalk) throws myException;
	public abstract  void               fill__MaskWithCalcResults_ON_EMPTY_LIST(BL_Daughter_Abzuege DaughterAbzuege) throws myException;
	
	//2011-08-04:  erweiterung, damit im Fuhrenbereich nur noch Abzuege auf Basis der Eigenwaehrung (statt Fremdwaehrung) verwendbar sind
	public abstract boolean             get_bSperreFremdWaehrung() throws myException;
	
	
	
	/**
	 * 
	 * @param primaryKeyfield_MotherTable
	 * @param fieldMAPMotherTable
	 * @param cConnectFieldToMotherTable
	 * @param cTablename
	 * @param ocomponentMAP_from_Mother
	 * @param ButtonForRecalcFremdwaehrungsPreise_input
	 * @throws myException
	 */
	public BL_Daughter_Abzuege(	SQLFieldForPrimaryKey 	primaryKeyfield_MotherTable, 
													SQLFieldMAP 			fieldMAPMotherTable, 
													String      			cConnectFieldToMotherTable,
													String 					cTablename,
													E2_ComponentMAP			ocomponentMAP_from_Mother,
													MyE2_Button   			ButtonForRecalcFremdwaehrungsPreise_input
													) throws myException
	{
		super();
		this.oSqlField = 						primaryKeyfield_MotherTable;
		this.oComponentMAP_from_Mother = 		ocomponentMAP_from_Mother;
		this.cTABLE_NAME	= 					cTablename;

		this.oButtonForRecalFremdwaehrungsPreise = ButtonForRecalcFremdwaehrungsPreise_input;
		
		String cID_NAME = "ID"+this.cTABLE_NAME.substring(2);
		
		SQLFieldMAP oInnerFieldMAP = new SQLFieldMAP(this.cTABLE_NAME,bibE2.get_CurrSession());
		oInnerFieldMAP.addCompleteTable_FIELDLIST(this.cTABLE_NAME,":"+cID_NAME+":"+cConnectFieldToMotherTable+":"+bibE2.get_FIELDLIST_EXCLUDE(),false,true,"");
		oInnerFieldMAP.add_SQLField(new SQLFieldJoinOutside(this.cTABLE_NAME,cConnectFieldToMotherTable,cConnectFieldToMotherTable,
												new MyE2_String("ID"),false,bibE2.get_CurrSession(),fieldMAPMotherTable.get_SQLField(cConnectFieldToMotherTable)), false);

		oInnerFieldMAP.add_SQLField(new SQLFieldForPrimaryKey(this.cTABLE_NAME,cID_NAME,cID_NAME,
																new MyE2_String("ID"),
																bibE2.get_CurrSession(),
																bibE2.get_SQL_SEQUENCE(this.cTABLE_NAME),true), false);
		oInnerFieldMAP.initFields();
		oInnerFieldMAP.add_ORDER_SEGMENT(this.cTABLE_NAME+".POS_NUMMER");         // immer nach pos-nummer sortieren
		
		this.oMap = new E2_ComponentMAP(oInnerFieldMAP);
		
		MyE2_DB_MultiComponentGrid	 oMulti_SPALTE1 = 				    new MyE2_DB_MultiComponentGrid(1);
		
		MyE2_DB_MultiComponentGrid	 oMulti_DEF_ABZUG = 				new MyE2_DB_MultiComponentGrid(2);
		MyE2_DB_MultiComponentColumn oMulti_ABZUG_GRUND = 				new MyE2_DB_MultiComponentColumn();
		MyE2_DB_MultiComponentColumn oMulti_MENGE = 					new MyE2_DB_MultiComponentColumn();
		MyE2_DB_MultiComponentColumn oMulti_EPREIS = 					new MyE2_DB_MultiComponentColumn();
		MyE2_DB_MultiComponentColumn oMulti_EPREIS_FW = 				new MyE2_DB_MultiComponentColumn();    //wird nur bei RG-positionen eingeblendet
		MyE2_DB_MultiComponentColumn oMulti_MENGEN_UND_PREISFAKTOREN = 	new MyE2_DB_MultiComponentColumn();   //wird ueberhaupt nicht eingeblendet, daber mit gerechnet
		

		String cSelAbzugsgrund = "SELECT ABZUGSGRUND,ID_ABZUGSGRUND FROM "+bibE2.cTO()+".JT_ABZUGSGRUND ORDER BY ABZUGSGRUND";
		MyE2_DB_SelectField 	 oSelABZUGSGRUND = 				new MyE2_DB_SelectField(oInnerFieldMAP.get_SQLField("ID_ABZUGSGRUND"),cSelAbzugsgrund,false,true);
		MyE2_DB_TextField  		 otfKURZTEXT = 					new MyE2_DB_TextField(oInnerFieldMAP.get_SQLField("KURZTEXT"),true,350);
		MyE2_DB_TextFieldLABEL   otfMENGE_VOR_ABZUG =  			new MyE2_DB_TextFieldLABEL(oInnerFieldMAP.get_SQLField("MENGE_VOR_ABZUG"),true,100);
		MyE2_DB_TextFieldLABEL   otfMENGE_NACH_ABZUG = 	 		new MyE2_DB_TextFieldLABEL(oInnerFieldMAP.get_SQLField("MENGE_NACH_ABZUG"),true,100);
		
		MyE2_DB_TextFieldLABEL   otfEPREIS_VOR_ABZUG =  		new MyE2_DB_TextFieldLABEL(oInnerFieldMAP.get_SQLField("EPREIS_VOR_ABZUG"),true,100);
		MyE2_DB_TextFieldLABEL   otfEPREIS_NACH_ABZUG =  		new MyE2_DB_TextFieldLABEL(oInnerFieldMAP.get_SQLField("EPREIS_NACH_ABZUG"),true,100);
		
		MyE2_DB_TextFieldLABEL   otfEPREIS_VOR_ABZUG_FW =  		new MyE2_DB_TextFieldLABEL(oInnerFieldMAP.get_SQLField("EPREIS_VOR_ABZUG_FW"),true,100);
		MyE2_DB_TextFieldLABEL   otfEPREIS_NACH_ABZUG_FW = 		new MyE2_DB_TextFieldLABEL(oInnerFieldMAP.get_SQLField("EPREIS_NACH_ABZUG_FW"),true,100);
		
		MyE2_DB_TextFieldLABEL   otfANZEIL_ABZUG_GESAMT =  		new MyE2_DB_TextFieldLABEL(oInnerFieldMAP.get_SQLField("ANTEIL_ABZUG_GESAMT"),true,100);
		MyE2_DB_TextFieldLABEL   otfANZEIL_ABZUG_GESAMT_FW =	new MyE2_DB_TextFieldLABEL(oInnerFieldMAP.get_SQLField("ANTEIL_ABZUG_GESAMT_FW"),true,100);
		
		MyE2_DB_TextFieldLABEL   otfMENGENFAKTOR_FUER_DRUCK =	new MyE2_DB_TextFieldLABEL(oInnerFieldMAP.get_SQLField("MENGENFAKTOR_FUER_DRUCK"),true,100);
		MyE2_DB_TextFieldLABEL   otfPREISFAKTOR_FUER_DRUCK =	new MyE2_DB_TextFieldLABEL(oInnerFieldMAP.get_SQLField("PREISFAKTOR_FUER_DRUCK"),true,100);
		MyE2_DB_TextFieldLABEL   otfPREISFAKTOR_FUER_DRUCK_FW =	new MyE2_DB_TextFieldLABEL(oInnerFieldMAP.get_SQLField("PREISFAKTOR_FUER_DRUCK_FW"),true,100);
		
		MyE2_DB_TextFieldLABEL   otfPOS_NUMMER = 				new MyE2_DB_TextFieldLABEL(oInnerFieldMAP.get_SQLField("POS_NUMMER"),true,40);
		
		//2011-05-06: neues feld, das anzeigt, ob der abzug aus einer waage-angabe stammt
		Vector<E2_ResourceIcon>  vIconsWaage = new Vector<E2_ResourceIcon>();
		Vector<String>  		 vIconsDBValue = bibALL.get_Vector("N", "Y");
		vIconsWaage.add(E2_ResourceIcon.get_RI("waage_leer.png"));
		vIconsWaage.add(E2_ResourceIcon.get_RI("waage.png"));
		
		MyE2_DB_Icon             oDBIconWaage =                 new MyE2_DB_Icon(oInnerFieldMAP.get_SQLField("WAAGE_ABZUG"), E2_ResourceIcon.get_RI("waage_leer.png")  , vIconsWaage, vIconsDBValue);
		oDBIconWaage.EXT().set_oLayout_ListElement(LayoutDataFactory.get_GridLayoutGridCenter(E2_INSETS.I_5_5_5_5));
		
//		oDBIconWaage.setLayoutData(LayoutDataFactory.get_GridLayoutGridCenter(E2_INSETS.I_5_5_5_5));
		oDBIconWaage.setToolTipText(new MyE2_String("Dieser Abzug wurde ursprünglich im Wiegeschein definiert !!").CTrans());
		
		//2011-06-09: nur die sicherbaren abzuege werden angezeigt,
		//die unsichtbaren bleiben im shadow
		//this.oSelABZUGTYP = 									new MyE2_DB_SelectField(oInnerFieldMAP.get_SQLField("ABZUGTYP"),BL_CONST_ABZUG.get_ABZUG_DD_ARRAY(true),true);
		boolean bSperreFremdwaehrung = this.get_bSperreFremdWaehrung();
		this.oSelABZUGTYP = 	new MyE2_DB_SelectField(oInnerFieldMAP.get_SQLField("ABZUGTYP"),BL_CONST_ABZUG.get_ABZUG_DD_ARRAY(true,true,bSperreFremdwaehrung),true);
		this.oSelABZUGTYP.set_odataToViewShadow(new dataToView(BL_CONST_ABZUG.get_ABZUG_DD_ARRAY(false,false,bSperreFremdwaehrung),true,bibE2.get_CurrSession()));
		
		
		MyE2_DB_TextFieldLABEL   otfABZUG_BELEGTEXT = 	 		new MyE2_DB_TextFieldLABEL(oInnerFieldMAP.get_SQLField("ABZUG_BELEGTEXT"),true,198,null,new E2_FontPlain(-2));
		MyE2_DB_TextField   	 otfABZUG_BELEGTEXTSchablone = 	new MyE2_DB_TextField(oInnerFieldMAP.get_SQLField("ABZUG_BELEGTEXT_SCHABLONE"),true,198,null,new E2_FontPlain(-2));
		MyE2_DB_TextField   	 otfABZUG = 					new MyE2_DB_TextField(oInnerFieldMAP.get_SQLField("ABZUG"));
		MyE2_DB_TextField   	 otfABZUG2 = 					new MyE2_DB_TextField(oInnerFieldMAP.get_SQLField("ABZUG2"));
		MyE2_DB_TextArea   		 otaLABGTEXT	=				new MyE2_DB_TextArea(oInnerFieldMAP.get_SQLField("LANGTEXT"),350,3,null,new E2_FontPlain(-2));
		
		/*
		 * einstellung der felder
		 */
		oSelABZUGSGRUND.setWidth(new Extent(300));
		oSelABZUGTYP.setWidth(new Extent(200));
		otfABZUG_BELEGTEXT.setWidth(new Extent(200));
		otfABZUG_BELEGTEXTSchablone.setWidth(new Extent(200));
		otfABZUG.setWidth(new Extent(100));
		
		ColumnLayoutData oLayoutRight = MyE2_Column.LAYOUT_RIGHT(E2_INSETS.I_1_1_1_1);
		
		/*
		 * action-agents fuer die dropdown-felder abzugstyp und abzugsgrund
		 */
		oSelABZUGSGRUND.add_oActionAgent(new Select_Abzugs_Grund_ActionAgent());
		oSelABZUGTYP.add_oActionAgent(new Select_Abzug_Typ_ActionAgent());

		
		/*
		 * layout-data fuer das multicomponent-grid
		 */
		GridLayoutData oGL1 = new GridLayoutData();
		oGL1.setColumnSpan(1);
		oGL1.setInsets(new Insets(1,1,5,1));

		GridLayoutData oGL2 = new GridLayoutData();
		oGL2.setColumnSpan(2);
		oGL2.setInsets(new Insets(1,1,1,1));
		
		oMulti_SPALTE1.add_Component(otfPOS_NUMMER,new MyE2_String("Pos"), null);
		oMulti_SPALTE1.add_Component(oDBIconWaage,new MyE2_String("Waage?"), null);
		
		oMulti_DEF_ABZUG.add_Component(oSelABZUGTYP,new MyE2_String("Definition"), null,oGL2);
		oMulti_DEF_ABZUG.add_Component(otfABZUG_BELEGTEXTSchablone,new MyE2_String("Text-Schablone"), null,oGL2);
		oMulti_DEF_ABZUG.add_Component(otfABZUG_BELEGTEXT,new MyE2_String("Text auf Beleg"), null,oGL2);
		oMulti_DEF_ABZUG.add_Component(otfABZUG,new MyE2_String("Wert"), null,oGL1);
		oMulti_DEF_ABZUG.add_Component(otfABZUG2,new MyE2_String("Wert(2)"), null,oGL1);
		
		oMulti_ABZUG_GRUND.add_Component(oSelABZUGSGRUND,new MyE2_String("Abzugsgrund"), null);
		oMulti_ABZUG_GRUND.add_Component(otfKURZTEXT,new MyE2_String("Kurztext"), null);
		oMulti_ABZUG_GRUND.add_Component(otaLABGTEXT,new MyE2_String("Langtext"), null);
		
		oMulti_MENGE.add_Component(otfMENGE_VOR_ABZUG, new MyE2_String("Menge vor"), null,oLayoutRight,oLayoutRight);
		oMulti_MENGE.add_Component(otfMENGE_NACH_ABZUG, new MyE2_String("Menge nach"), null,oLayoutRight,oLayoutRight);
		oMulti_MENGE.add_Component(new MyE2_Grid(3,MyE2_Grid.STYLE_GRID_DDARK_BORDER_W100()), new MyE2_String("Rechnung"), BL_Daughter_Abzuege.HASHKEY_INFOBLOCK,oLayoutRight,oLayoutRight);

		oMulti_EPREIS.add_Component(otfEPREIS_VOR_ABZUG, new MyE2_String("EPreis ",true,cWaehrungsSymbolMandant,false," vorher",true), null,oLayoutRight,oLayoutRight);
		oMulti_EPREIS.add_Component(otfEPREIS_NACH_ABZUG, new MyE2_String("EPreis ",true,cWaehrungsSymbolMandant,false," nacher",true), null,oLayoutRight,oLayoutRight);

		String cHelp = "#FW#";
				
		oMulti_EPREIS_FW.add_Component(otfEPREIS_VOR_ABZUG_FW, 	new MyE2_String("EPreis "+cHelp+" vor"), null,oLayoutRight,oLayoutRight);
		oMulti_EPREIS_FW.add_Component(otfEPREIS_NACH_ABZUG_FW, new MyE2_String("EPreis "+cHelp+" nach"), null,oLayoutRight,oLayoutRight);
		//infozeile
//		oMulti_EPREIS_FW.add_Component(new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100()), new MyE2_String("EPreis NettoMge"), BL_Daughter_Abzuege_FUHREN_AND_VPOS_RG.HASHKEY_EPREIS_AUF_NETTO_NACH_ABZUG,oLayoutRight,oLayoutRight);
//		oMulti_EPREIS_FW.add_Component(new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100()), new MyE2_String("Betragsabzug"), BL_Daughter_Abzuege_FUHREN_AND_VPOS_RG.HASHKEY_GESAMTPREIS_ABZUG,oLayoutRight,oLayoutRight);

		
		
		
		oMulti_MENGEN_UND_PREISFAKTOREN.add_Component(otfMENGENFAKTOR_FUER_DRUCK, 	new MyE2_String("Mengenfaktor "),									 		null,oLayoutRight,oLayoutRight);
		oMulti_MENGEN_UND_PREISFAKTOREN.add_Component(otfPREISFAKTOR_FUER_DRUCK, 	new MyE2_String("Preisfaktor ",true,cWaehrungsSymbolMandant,false),	 		null,oLayoutRight,oLayoutRight);
		oMulti_MENGEN_UND_PREISFAKTOREN.add_Component(otfPREISFAKTOR_FUER_DRUCK_FW, new MyE2_String("Preisfaktor #FW# "), 										null,oLayoutRight,oLayoutRight);
		oMulti_MENGEN_UND_PREISFAKTOREN.add_Component(otfANZEIL_ABZUG_GESAMT, 		new MyE2_String("Anteil Abzug Gesamt",true,cWaehrungsSymbolMandant,false), 	null,oLayoutRight,oLayoutRight);
		oMulti_MENGEN_UND_PREISFAKTOREN.add_Component(otfANZEIL_ABZUG_GESAMT_FW, 	new MyE2_String("Anteil Abzug Gesamt #FW#"), 								null,oLayoutRight,oLayoutRight);

		oMulti_MENGEN_UND_PREISFAKTOREN.EXT().set_bIsVisibleInList(false);
		oMulti_EPREIS.EXT().set_bIsVisibleInList(false);					//es wird alles in fremdwaehrung kalkuliert
		
		otfPOS_NUMMER.EXT().set_oColExtent(new Extent(40));
		oMulti_DEF_ABZUG.EXT().set_oColExtent(new Extent(200));
		oMulti_ABZUG_GRUND.EXT().set_oColExtent(new Extent(400));
		oMulti_MENGE.EXT().set_oColExtent(new Extent(150));
		oMulti_EPREIS.EXT().set_oColExtent(new Extent(150));
		oMulti_EPREIS_FW.EXT().set_oColExtent(new Extent(150));
		
		
		//aenderung 2011-01-11: abzuege koennen wie jede simpledaughter auch dazwischen geloescht werden
		MyE2_ButtonMarkForDelete 	oButtonForDel = 	new MyE2_ButtonMarkForDelete();
		
		//2011-04-13: vorauszahlungsabzuege koennen nicht reaktiviert werden, damit nicht die Situation entsteht, dass mehr als ein Vorauszahlungsabzug in der 
		//            abzugsliste stehen kann
		oButtonForDel.add_GlobalValidator(new ownValidator_Vorauszahlung_cannot_be_reactevated());
		
		oMap.add_Component("DEL_MARKER_ABZUG", oButtonForDel, new MyE2_String("?"));
		///
		MyE2_Button oInfoButton = new MyE2_ButtonInLIST(E2_ResourceIcon.get_RI("info.png"),true);
		oInfoButton.add_oActionAgent(new ownActionAgentShowInfos());
		
		oMap.add_Component(BL_Daughter_Abzuege.KENNER_DEF_SPALTE1,			oMulti_SPALTE1, new MyE2_String("Spalte Pos-Nr/Waagesymbol"));
		oMap.add_Component(BL_Daughter_Abzuege.KENNER_DEF_ABZUG,			oMulti_DEF_ABZUG,new MyE2_String("Abzugsart/Abzug"));
		oMap.add_Component(BL_Daughter_Abzuege.HASHKEY_LIST_INFO_BUTTON,	oInfoButton,new MyE2_String("?"));
		oMap.add_Component(BL_Daughter_Abzuege.KENNER_ABZUGTYP_MULTI_COL,	oMulti_ABZUG_GRUND,new MyE2_String("Grund Abzug"));
		oMap.add_Component(BL_Daughter_Abzuege.KENNER_MENGE_MULTI_COL,		oMulti_MENGE,new MyE2_String("Menge vorher/nachher"));
		oMap.add_Component(BL_Daughter_Abzuege.KENNER_EPREIS_MULTI_COL,		oMulti_EPREIS,new MyE2_String("EPreis ",true,cWaehrungsSymbolMandant,false," vorher/nachher",true));
		oMap.add_Component(BL_Daughter_Abzuege.KENNER_EPREIS_FW_MULTI_COL,	oMulti_EPREIS_FW,new MyE2_String("EPreis #FW# vorher/nachher"));
		oMap.add_Component(BL_Daughter_Abzuege.KENNER_DUMMY_MULTI_COL,		oMulti_MENGEN_UND_PREISFAKTOREN,new MyE2_String("Werte für Druck"));
		
		//das 2. mengenfeld ist unsichtbar, wird bei bedarf eingeschaltet
		((MyE2_DB_TextField)oMap.get_hmRealComponents().get("ABZUG2")).setVisible(false);
		
		this.set_oContainerExScrollHeight(new Extent(450));

		//mapsetter sorgt fuer die richtige einstellung der darstellung 2. wert sichtbar oder nicht
		oMap.set_oMAPSettingAgent(new ownMapSetter());
		
		this.INIT_DAUGHTER(this.oSqlField,this.oComponentMAP_from_Mother,oMap,null);
		
		this.get_vComponentForDifferentTasks().add(oButNew);
		this.get_vComponentForDifferentTasks().add(oButRefresh);
		
		
		// abschluss-manipulator setzen
		this.EXT().add_FieldSetters_AND_Validator__BeforeReadInputMAP( new XX_FieldSetter_AND_Validator()
		{

			public MyE2_MessageVector isValid(String cSTATUS_MAP, MyE2EXT__Component EXT_own) throws myException
			{
				MyE2_MessageVector oMV = new MyE2_MessageVector();
				BL_Daughter_Abzuege.this.bCalc_ganze_AbzugsListe(oMV);
				return oMV;
			}
		});
		
		
		//einen lade-manipulator setzen, der beim ersten aufruf den anzeigeblock fuellt
		this.EXT().add_ValidatorsEnabledAllowd(
				new XX_FieldValidator_InputAllowed()
				{
					public boolean isValid()
					{
						BL_Daughter_Abzuege oThis = BL_Daughter_Abzuege.this;
						
						if (!oThis.bTitelBlockIstGefuellt)
						{
							try 
							{
								oThis.add_to_Row_In_TOP(oThis.get__TitleComponentWithButtons());
							} 
							catch (myException e) 
							{
								e.printStackTrace();
								oThis.add_to_Row_In_TOP(new MyE2_Label("FEHLER !!!"));
							}
							oThis.bTitelBlockIstGefuellt=true;
						}
						return true;
					}
				});

		
		
		
	}
	
	
	
	//2011-12-02: abzugsliste muss direkt hier eingeblendet werden, da, wenn sie im validierer eingeblendet wurde,
	//            sie im status "nur anzeigen" nicht eingeblendet wird (wird im validierer nochmals gemacht, fall 
	//            hier ein zustand mit exception auftritt
	public void fuege_titelblock_ein()
	{
		//2011-12-02: abzugsliste muss direkt hier eingeblendet werden, da, wenn sie im validierer eingeblendet wurde,
		//            sie im status "nur anzeigen" nicht eingeblendet wird (wird im validierer nochmals gemacht, fall 
		//            hier ein zustand mit exception auftritt
		if (!this.bTitelBlockIstGefuellt)
		{
			try 
			{
				this.add_to_Row_In_TOP(this.get__TitleComponentWithButtons());
				this.bTitelBlockIstGefuellt=true;
			} 
			catch (Exception e) 
			{
			}
		}

	}
	
	//2011-06-10: actionagent fuer die infos in den abzuegen
	private class ownActionAgentShowInfos extends XX_ActionAgent
	{
		private String cHILFETEXT = 	null;
		private String cAbzugDD_Text = 	null;
		
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			E2_ComponentMAP  oMAP = ((MyE2_Button)oExecInfo.get_MyActionEvent().getSource()).EXT().get_oComponentMAP();
			
			this.cHILFETEXT = 		"<keine Hilfe vorhanden>";
			this.cAbzugDD_Text = 	"<Abzug nicht definiert>";
			
			MyE2_DB_SelectField  SelABZUGTYP = (MyE2_DB_SelectField)oMAP.get_hmRealComponents().get("ABZUGTYP");
			
			if (S.isFull(SelABZUGTYP.get_ActualWert()))
			{
				HashMap<String,BL_CONST_ABZUG.AbzugsDef> oHM_ABZUG_DEFS = BL_CONST_ABZUG.get_HM_ABZUGS_DEFS();
				
				if (oHM_ABZUG_DEFS.containsKey(SelABZUGTYP.get_ActualWert()))
				{
					if (S.isFull(oHM_ABZUG_DEFS.get(SelABZUGTYP.get_ActualWert()).HILFETEXT))
					{
						this.cHILFETEXT = oHM_ABZUG_DEFS.get(SelABZUGTYP.get_ActualWert()).HILFETEXT;
						this.cAbzugDD_Text = SelABZUGTYP.get_ActualView();
						
					}
				}
			}
			
			new popupContainer();
		}
		
		
		private class popupContainer extends E2_BasicModuleContainer
		{
			public popupContainer() throws myException
			{
				super();
				
				MyE2_TextArea oTextInfo = new MyE2_TextArea( ownActionAgentShowInfos.this.cHILFETEXT,400,1000,10);
				oTextInfo.setBackground(new E2_ColorBase());
				oTextInfo.setDisabledBackground(new E2_ColorBase());
				
				oTextInfo.setEnabled(false);
				
				this.add(oTextInfo, E2_INSETS.I_2_2_2_2);
				
				this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(500), new Extent(300), new MyE2_String("Information zur Abzugsart: ",true,ownActionAgentShowInfos.this.cAbzugDD_Text,false));
				
			}
		}
		
	}
	
	
	
	
	
	//2011-04-13: globaler validierer, der sicherstellt, dass eine zeile mit vorauszahlungsabzug nicht wieder "entloescht" werden kann
	private class ownValidator_Vorauszahlung_cannot_be_reactevated extends XX_ActionValidator
	{
		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException
		{
			MyE2_Button 							oButtonDelete = (MyE2_Button)oComponentWhichIsValidated;
			E2_ComponentMAP   						oMAP = 			oButtonDelete.EXT().get_oComponentMAP();
			BL_Daughter_Abzuege  oThis = 		BL_Daughter_Abzuege.this;
			
			MyE2_MessageVector  oMV = new MyE2_MessageVector();

			if (oThis.get_bMapIsMarkedToDelete(oMAP))
			{
				if (oMAP.get_cActualDBValueFormated("ABZUGTYP").equals(BL_CONST_ABZUG.ABZUGTYP_EPREIS_PROZENT_VORAUSZAHLUNG_NG) ||
					oMAP.get_cActualDBValueFormated("ABZUGTYP").equals(BL_CONST_ABZUG.ABZUGTYP_EPREIS_PROZENT_VORAUSZAHLUNG)	
					)
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Ein Vorauszahlungsabzug kann nicht reaktiviert werden !!")));
				}
			}
			return oMV;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated)	throws myException
		{
			return null;
		}
	}
	
	
	
	
	
	public String BuildAbzugsInfo()
	{
		String cRueck = "";
		
		Vector<E2_ComponentMAP> vComponentMaps = 		this.get_oNavigationList().get_vComponentMAPS();
		Vector<E2_ComponentMAP> vComponentMapsNew = 	this.get_oNavigationList().get_vComponentMAPS_NEW();
		
		try
		{
			cRueck = cRueck+this.BuildPartAbzugsinfo(vComponentMaps);
			cRueck = cRueck+this.BuildPartAbzugsinfo(vComponentMapsNew);
		}
		catch (myExceptionForUser ex)
		{
			bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			return "";
		}
		catch (myException ex)
		{
			cRueck = "Fehler: "+ex.get_ErrorMessage().get_cMessage().CTrans();
		}
		
		return cRueck;
	}
	
	
	private String BuildPartAbzugsinfo(Vector<E2_ComponentMAP> vMaps) throws myException
	{
		String cRueck = "";
		
		for (int i=0;i<vMaps.size();i++)
		{
			E2_ComponentMAP 					oMAP = (E2_ComponentMAP)vMaps.get(i);
			HashMap<String, MyE2IF__Component>	hmReal=oMAP.get_REAL_ComponentHashMap();
			
			if (this.get_bMapIsMarkedToDelete(oMAP))
				continue;
			
			String cBelegText  = 	bibALL.null2leer(((MyE2_DB_TextField)hmReal.get("ABZUG_BELEGTEXT")).get_cActualMaskValue());
			String cKurzText  = 	bibALL.null2leer(((MyE2_DB_TextField)hmReal.get("KURZTEXT")).get_cActualMaskValue());
			String cLangText  = 	bibALL.null2leer(((MyE2_DB_TextArea)hmReal.get("LANGTEXT")).get_cActualMaskValue());
			String cText = cBelegText+" ------> "+cKurzText;
			if (!cLangText.trim().equals(""))
				cText = cText+"\n"+cLangText;
			
			cRueck = cRueck+cText+"\n";
		}

		return cRueck;
	}
	
	
	
	/*
	 * actionagent fuer die reaktion auf einen klick auf das feld abzugsgrund
	 */
	private class Select_Abzugs_Grund_ActionAgent extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			MyE2_DB_SelectField 					oSelField = 	(MyE2_DB_SelectField)bibE2.get_LAST_ACTIONEVENT().getSource();
			E2_ComponentMAP 						oOwnMAP = 		oSelField.EXT().get_oComponentMAP();
			HashMap<String, MyE2IF__Component> 		hmRealComponents = oOwnMAP.get_REAL_ComponentHashMap();
			MyE2_DB_TextField   					otfKURZTEXT = 	  (MyE2_DB_TextField)hmRealComponents.get("KURZTEXT");
			
			try
			{
				otfKURZTEXT.setText(oSelField.get_ActualView());
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler: Der Text konnte nicht ermittelt werden"));
			}
		}
	}
	
	
	private class ownMapSetter extends XX_MAP_SettingAgent
	{

		@Override
		public void __doSettings_AFTER(E2_ComponentMAP map, String STATUS_MASKE) throws myException
		{
		}

		@Override
		public void __doSettings_BEFORE(E2_ComponentMAP map, String STATUS_MASKE) throws myException
		{
			MyE2_DB_SelectField oSelField = (MyE2_DB_SelectField)map.get_hmRealComponents().get("ABZUGTYP");
			if (oSelField.get_ActualWert().equals(BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_MINUS_MENGE_PROZENT_AKTUELLEMENGE) ||
				oSelField.get_ActualWert().equals(BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_MINUS_MENGE_PROZENT_AUSGANGSMENGE))
			{
				((MyE2_DB_TextField)map.get_hmRealComponents().get("ABZUG2")).setVisible(true);
			}
		}
		
	}
	
	
	/*
	 * actionagent fuer die reaktion auf einen klick auf das feld abzugstyp
	 */
	private class Select_Abzug_Typ_ActionAgent extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			MyE2_DB_SelectField 				oSelField = 					(MyE2_DB_SelectField)bibE2.get_LAST_ACTIONEVENT().getSource();
			E2_ComponentMAP 					oOwnMAP = 						oSelField.EXT().get_oComponentMAP();

			HashMap<String,String> hmAbzugPlatzhalter = BL_CONST_ABZUG.get_HM__ABZUG_PLATZHALTER();
			
			try
			{
				String cWert = hmAbzugPlatzhalter.get(oSelField.get_ActualWert());
				
				((MyE2_DB_TextField)oOwnMAP.get_hmRealComponents().get("ABZUG_BELEGTEXT")).setText(cWert);
				((MyE2_DB_TextField)oOwnMAP.get_hmRealComponents().get("ABZUG_BELEGTEXT_SCHABLONE")).setText(cWert);
				
				((MyE2_DB_TextField)oOwnMAP.get_hmRealComponents().get("ABZUG2")).setVisible(false);
				
				if (oSelField.get_ActualWert().equals(BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_MINUS_MENGE_PROZENT_AKTUELLEMENGE) ||
					oSelField.get_ActualWert().equals(BL_CONST_ABZUG.ABZUGTYP_INHALT_PROZENT_MINUS_MENGE_PROZENT_AUSGANGSMENGE))
				{
					((MyE2_DB_TextField)oOwnMAP.get_hmRealComponents().get("ABZUG2")).setVisible(true);
				}
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler: Der Text konnte nicht ermittelt werden"));
			}
		}
	}
	
	
	
	
	/*
	 * muss ueberschrieben werden, nur der letzte eintrag ist editierbar, nicht die ganze liste
	 */
	public void set_bEnabled_For_Edit(boolean bEnabled) throws myException
	{
		/*
		 * erst mal alle disablen
		 */
		super.set_bEnabled_For_Edit(bEnabled);
		
		boolean Enabled = bEnabled && this.EXT().is_ValidEnableAlowed() && this.EXT_DB().get_bCanBeEnabled(true) && this.EXT().get_bCanBeEnabled();

		/*
		 * alle zeilen sperren, ausser der letzten
		 */
		Vector<E2_ComponentMAP> vAll_ComponentMAP = new Vector<E2_ComponentMAP>();
		vAll_ComponentMAP.addAll(this.get_oNavigationList().get_vComponentMAPS());
		vAll_ComponentMAP.addAll(this.get_oNavigationList().get_vComponentMAPS_NEW());
		
		if (vAll_ComponentMAP.size()>0)
		{
			vAll_ComponentMAP.get(vAll_ComponentMAP.size()-1).set_AllComponentsEnabled_For_Edit(Enabled, E2_ComponentMAP.STATUS_UNDEFINED);
		}
	}
	
	
	
	
	/*
	 * komplettes durchkalkulieren der abzugsliste
	 */
	public void  bCalc_ganze_AbzugsListe(MyE2_MessageVector oMV)
	{
		BL_Daughter_Abzuege oThis = BL_Daughter_Abzuege.this;

		try
		{
			if (oThis.oButtonForRecalFremdwaehrungsPreise != null)
			{
				oThis.oButtonForRecalFremdwaehrungsPreise.doActionPassiv();
			}
			
			Vector<E2_ComponentMAP> vAll_ComponentMAP_vor = new Vector<E2_ComponentMAP>();
			vAll_ComponentMAP_vor.addAll(this.get_oNavigationList().get_vComponentMAPS());
			vAll_ComponentMAP_vor.addAll(this.get_oNavigationList().get_vComponentMAPS_NEW());
	
			//aenderung 2011-01-11: loeschvorgang wie in anderen SimpleDaughters zeilenweise,
			//zur kalkulation werden nur die nicht loeschmarkierten abzugszeilen herangezogen
			Vector<E2_ComponentMAP> vAll_ComponentMAP = new Vector<E2_ComponentMAP>();
			for (int k=0;k<vAll_ComponentMAP_vor.size();k++)
			{
				if (!this.get_bMapIsMarkedToDelete(vAll_ComponentMAP_vor.get(k)))
				{
					vAll_ComponentMAP.add(vAll_ComponentMAP_vor.get(k));
				}
			}
			
			
			/*
			 * checken, ob in der vorigen ein Type und ein Abzug eingegeben wurde
			 */
			if (vAll_ComponentMAP.size()>0)
			{
				E2_ComponentMAP 							ooMap = 			(E2_ComponentMAP) vAll_ComponentMAP.get(vAll_ComponentMAP.size()-1);
				HashMap<String, MyE2IF__Component>			oHashALL = 			ooMap.get_REAL_ComponentHashMap();
				String 										cValueAbzugTyp = 	((MyE2IF__DB_Component)oHashALL.get("ABZUGTYP")).get_cActualMaskValue();
				String 										cValueAbzug = 	 	((MyE2IF__DB_Component)oHashALL.get("ABZUG")).get_cActualMaskValue();
				if (bibALL.isEmpty(cValueAbzugTyp) || bibALL.isEmpty(cValueAbzug))
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte den Abzug vollständig ausfüllen (Definition und Abzug) !"));
					return;
				}
				
				
				try
				{
					RECORD_WAEHRUNG  recWaehrung = this.get__RECORD_FREMDWAEHRUNG();
					RECORD_ARTIKEL   recArtikel =  this.get__RECORD_ARTIKEL();
					
					//nachsehen, ob alle da ist, was man braucht
					if (recWaehrung==null || recArtikel==null)
					{
						oMV.add_MESSAGE(new MyE2_Alarm_Message("Abzugskalkulation: Bitte zuerst Fremdwährung und Artikel festlegen !"));
					}
					else
					{
						if (S.isEmpty(recWaehrung.get_WAEHRUNGSSYMBOL_cUF_NN("")))
						{
							oMV.add_MESSAGE(new MyE2_Alarm_Message("Abzugskalkulation: Das Währungssymbol der Fremdwährung ist undefiniert !"));
						}
						
						if (recArtikel.get_UP_RECORD_EINHEIT_id_einheit()==null ||
							S.isEmpty(recArtikel.get_UP_RECORD_EINHEIT_id_einheit().get_EINHEITKURZ_cUF_NN("")) ||
							recArtikel.get_UP_RECORD_EINHEIT_id_einheit_preis()==null ||
							S.isEmpty(recArtikel.get_UP_RECORD_EINHEIT_id_einheit_preis().get_EINHEITKURZ_cUF_NN("")))
						{
							oMV.add_MESSAGE(new MyE2_Alarm_Message("Abzugskalkulation: Mengen- und Preiseinheit sind nicht genau definiert !"));
						}
					}
					
					//2012-10-16: muss rein, sonst gibts einen absturz wenn die fuhre abzuege hat, aber keine sorte (recArtikel ist null !!)
					if (oMV.get_bHasAlarms())
					{
						return;
					}
					
					//jetzt noch die mengen und preis-eintraege pruefen, damit er rechnen kann
					if ( (!bibALL.isNumber_empty_is_false(this.get__ActualFormatedValueMenge(),    true)) ||
					     (!bibALL.isNumber_empty_is_false(this.get__ActualFormatedValuePreis(),    true)) ||
					     (!bibALL.isNumber_empty_is_false(this.get__ActualFormatedValuePreis_FW(), true)) ||
					     (!bibALL.isNumber_empty_is_false(this.get__ActualFormatedWaehrungsKurs(), true)) ||
					     (!bibALL.isNumber_empty_is_false(recArtikel.get_MENGENDIVISOR_cF_NN(""),  true)))
					{
						oMV.add_MESSAGE(new MyE2_Alarm_Message("Abzugskalkulation: Menge/Preis/Preis FW/Währungskurs und Verhältnis Einheit/Preiseinheit MUSS vor einer Kalkulation feststehen !"));
					}
					
					if (oMV.get_bHasAlarms())
					{
						return;
					}
					
					
					
					this.cWaehrungsSymbolFremdwaehrung = 	recWaehrung.get_WAEHRUNGSSYMBOL_cUF_NN("-");
					this.cEinheit = 						recArtikel.get_UP_RECORD_EINHEIT_id_einheit().get_EINHEITKURZ_cUF_NN("");
					this.cEinheit_Preis = 					recArtikel.get_UP_RECORD_EINHEIT_id_einheit_preis().get_EINHEITKURZ_cUF_NN("");
					
					

					BL_AbzugsKalkulator oBL_Kalk = new BL_AbzugsKalkulator(	this.get__ActualFormatedValueMenge(),
																			this.get__ActualFormatedValuePreis(),
																			this.get__ActualFormatedValuePreis_FW(),
																			recArtikel.get_MENGENDIVISOR_cF(),
																			this.get__ActualFormatedWaehrungsKurs(),
																			this.cWaehrungsSymbolMandant,
																			this.cWaehrungsSymbolFremdwaehrung,
																			this.cEinheit,
																			this.cEinheit_Preis);

					
					
					/*
					 * die ausgangswerte in die erste zeile einfuegen
					 */
					HashMap<String, MyE2IF__Component>	 oHashALL1 = ((E2_ComponentMAP) vAll_ComponentMAP.get(0)).get_REAL_ComponentHashMap();
					((MyE2_DB_TextField)oHashALL1.get("MENGE_VOR_ABZUG")).set_cActualMaskValue(this.get__ActualFormatedValueMenge());
					((MyE2_DB_TextField)oHashALL1.get("EPREIS_VOR_ABZUG")).set_cActualMaskValue(this.get__ActualFormatedValuePreis());
					((MyE2_DB_TextField)oHashALL1.get("EPREIS_VOR_ABZUG_FW")).set_cActualMaskValue(this.get__ActualFormatedValuePreis_FW());
					
					for (int i=0;i<vAll_ComponentMAP.size();i++)
					{
						HashMap<String, MyE2IF__Component>	oHashALL2 = ((E2_ComponentMAP) vAll_ComponentMAP.get(i)).get_hmRealComponents();
		
					   oBL_Kalk.add_AbzugsKalkulationsZeile(
								((MyE2IF__DB_Component)oHashALL2.get("ABZUGTYP")).get_cActualDBValueFormated(),
								((MyE2IF__DB_Component)oHashALL2.get("MENGE_VOR_ABZUG")).get_cActualDBValueFormated(),
								((MyE2IF__DB_Component)oHashALL2.get("EPREIS_VOR_ABZUG")).get_cActualDBValueFormated(),
								((MyE2IF__DB_Component)oHashALL2.get("EPREIS_VOR_ABZUG_FW")).get_cActualDBValueFormated(),
								((MyE2IF__DB_Component)oHashALL2.get("ABZUG")).get_cActualDBValueFormated(),
								((MyE2IF__DB_Component)oHashALL2.get("ABZUG2")).get_cActualDBValueFormated(),
								null,
								((MyE2IF__DB_Component)oHashALL2.get("ABZUG_BELEGTEXT_SCHABLONE")).get_cActualDBValueFormated()
								);
						
						double dMengeNachAbzug = oBL_Kalk.get_dMengeNachAbzugLetzteAbzugsZeile();
						double dEPreisNachAbzug = oBL_Kalk.get_dEPreisNachAbzugLetzteAbzugsZeile();
						double dEPreisNachAbzug_FW = oBL_Kalk.get_dEPreisNachAbzugLetzteAbzugsZeile_FW();
						DecimalFormat df2 = new DecimalFormat("#,###,##0.00");
						DecimalFormat df3 = new DecimalFormat("#,###,##0.000");
						
						((MyE2_DB_TextField)oHashALL2.get("MENGE_NACH_ABZUG")).setText(df3.format(dMengeNachAbzug));
						((MyE2_DB_TextField)oHashALL2.get("EPREIS_NACH_ABZUG")).setText(df2.format(dEPreisNachAbzug));
						((MyE2_DB_TextField)oHashALL2.get("EPREIS_NACH_ABZUG_FW")).setText(df2.format(dEPreisNachAbzug_FW));
						
						
						//den belegtext reinschreiben (basiert auf dem schablonentext
						((MyE2_DB_TextField)oHashALL2.get("ABZUG_BELEGTEXT")).setText(oBL_Kalk.get_LastZeileBelegText());
	
						/*
						 * wenn die map nicht die letzte in der liste ist, dann in der folgenden die neuen anfangswerte setzen
						 */
						if (i<(vAll_ComponentMAP.size()-1))
						{
							HashMap<String, MyE2IF__Component>	oHashALL3 = ((E2_ComponentMAP) vAll_ComponentMAP.get(i+1)).get_REAL_ComponentHashMap();
							((MyE2_DB_TextField)oHashALL3.get("MENGE_VOR_ABZUG")).setText(df3.format(dMengeNachAbzug));
							((MyE2_DB_TextField)oHashALL3.get("EPREIS_VOR_ABZUG")).setText(df2.format(dEPreisNachAbzug));
							((MyE2_DB_TextField)oHashALL3.get("EPREIS_VOR_ABZUG_FW")).setText(df2.format(dEPreisNachAbzug_FW));
						}
						
						
						//hier die bilanzfelder (reine anzeige) in den einzelnen zeilen fuellen
						MyE2_Grid oGridInfo = (MyE2_Grid)oHashALL2.get(BL_Daughter_Abzuege.HASHKEY_INFOBLOCK);
						oGridInfo.removeAll();
						try
						{
							String cNettoMengeNachAbzug = 				MyNumberFormater.formatDez(oBL_Kalk.get_bdNettoMenge(true),3,true);
							String cBetragNachAbzug = 					MyNumberFormater.formatDez(new MyBigDecimal(oBL_Kalk.get_BETRAG_OHNE_VORAUSZAHLUNGSABZUG_FW(),2).get_bdWert(),2,true);
							String cEinzelpreisAufNettoMengeNachAbzug = MyNumberFormater.formatDez(oBL_Kalk.get_bdRESULTIERENDER_EINZELPREIS_AUF_LAGERMENGE_FW(),2,true);

							GridLayoutData glR = LayoutDataFactory.get_GridLayoutGridRight_TOP(E2_INSETS.I_1_1_5_1);
							GridLayoutData glL = LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_1_1_5_1);
							GridLayoutData glL2 = LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_1_1_1_1);

							//zeile 1
							oGridInfo.add_raw(new MyE2_Label(new MyE2_String("Netto-Mge"),MyE2_Label.STYLE_SMALL_PLAIN()),glL);
							oGridInfo.add_raw(new MyE2_Label(cNettoMengeNachAbzug,MyE2_Label.STYLE_SMALL_PLAIN()),glR);
							oGridInfo.add_raw(new MyE2_Label(S.NN(oBL_Kalk.get_cEinheit(),"<EH>"),MyE2_Label.STYLE_SMALL_PLAIN()),glL2);
							
							//zeile 2
							oGridInfo.add_raw(new MyE2_Label(new MyE2_String("Gesamt-Betr"),MyE2_Label.STYLE_SMALL_PLAIN()),glL);
							oGridInfo.add_raw(new MyE2_Label(cBetragNachAbzug,MyE2_Label.STYLE_SMALL_PLAIN()),glR);
							oGridInfo.add_raw(new MyE2_Label("#FW#",MyE2_Label.STYLE_SMALL_PLAIN()),glL2);
							
							//zeile 3
							oGridInfo.add_raw(new MyE2_Label(new MyE2_String("Res.EPreis"),MyE2_Label.STYLE_SMALL_PLAIN()),glL);
							oGridInfo.add_raw(new MyE2_Label(cEinzelpreisAufNettoMengeNachAbzug,MyE2_Label.STYLE_SMALL_PLAIN()),glR);
							oGridInfo.add_raw(new MyE2_Label("#FW#",MyE2_Label.STYLE_SMALL_PLAIN()),glL2);
							
							

						
						}
						catch (Exception ex)
						{
							//hier nix anzeigen
						}
						
						
						
					}
					
					
					this.fill__MaskWithCalcResults(this, oBL_Kalk);
					

				} 
				catch(myExceptionForUser exu)
				{
					oMV.add_MESSAGE(exu.get_ErrorMessage(), false);
					return;
				}
			}
			else
			{
				this.fill__MaskWithCalcResults_ON_EMPTY_LIST(this);
			}
			
			
			this.SET_FremwaehrungsSymbol(null);
			
			
			
		}
		catch (myException ex)
		{
			ex.printStackTrace();
			oMV.add_MESSAGE(ex.get_ErrorMessage());
		}
		return;
	}
	

	
	private void SET_FremwaehrungsSymbol(String cWaehrung) throws myException
	{
		// dann alle komponenten vom Typ MyE2_Label rausziehen und FW durch die waehrung ersetzen
		Vector<Component>  vLabs = this.get_vComponents(bibALL.get_Vector(MyE2_Label.class.getName()),null);
		for (Component oLab: vLabs)
		{
			if (oLab instanceof MyE2_Label)
			{
				((MyE2_Label)oLab).set_ReplaceText("#FW#", S.isEmpty(cWaehrung)?S.NN(this.cWaehrungsSymbolFremdwaehrung):cWaehrung);
				//((MyE2_Label)oLab).set_ReplaceText("#FW#", "123lalala");
			}
		}

	}
	

	
	
	
	
	////////////// button neueingabe /////////////////////////////////
	private class buttonForAddNEW extends MyE2_Button
	{
		
		public buttonForAddNEW()
		{
			super(E2_ResourceIcon.get_RI("new.png"),true);
			
			this.add_oActionAgent( new XX_ActionAgent()
					{
						public void executeAgentCode(ExecINFO oExecInfo)
						{
							BL_Daughter_Abzuege oThis = BL_Daughter_Abzuege.this;
							
							try
							{
								if (oThis.oButtonForRecalFremdwaehrungsPreise != null)
								{
									oThis.oButtonForRecalFremdwaehrungsPreise.doActionPassiv();
								}
								
								Vector<E2_ComponentMAP> vAll_ComponentMAP = new Vector<E2_ComponentMAP>();
								vAll_ComponentMAP.addAll(BL_Daughter_Abzuege.this.get_oNavigationList().get_vComponentMAPS());
								vAll_ComponentMAP.addAll(BL_Daughter_Abzuege.this.get_oNavigationList().get_vComponentMAPS_NEW());

								Vector<E2_ComponentMAP> vAll_ComponentMAP_undeleted = new Vector<E2_ComponentMAP>();
								for (int i=0;i<vAll_ComponentMAP.size();i++)
								{
									if (!oThis.get_bMapIsMarkedToDelete(vAll_ComponentMAP.get(i)))
									{
										vAll_ComponentMAP_undeleted.add(vAll_ComponentMAP.get(i));
									}
								}
								
								BL_Daughter_Abzuege.this.bCalc_ganze_AbzugsListe(bibMSG.MV());
								
								if (bibMSG.get_bIsOK())
								{

									//2011-04-13:  jetzt pruefen, ob die letzte zeile ein vorauszahlungsabzug ist, wenn ja geht nichts mehr danach
									if (vAll_ComponentMAP_undeleted.size()>0)
									{
										E2_ComponentMAP 	oMap_last = (E2_ComponentMAP)vAll_ComponentMAP_undeleted.get(vAll_ComponentMAP_undeleted.size()-1);
										if (oMap_last.get_cActualDBValueFormated("ABZUGTYP").equals(BL_CONST_ABZUG.ABZUGTYP_EPREIS_PROZENT_VORAUSZAHLUNG_NG) ||
											oMap_last.get_cActualDBValueFormated("ABZUGTYP").equals(BL_CONST_ABZUG.ABZUGTYP_EPREIS_PROZENT_VORAUSZAHLUNG)
												)
										{
											bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Nach einem Vorauszahlungsabzug kann kein weiterer Abzug erfolgen")));
											return;
										}
									}

									
									BL_Daughter_Abzuege.this.get_oNavigationList().add_Row_MAP_FOR_NEW_INPUT(true,false, true);
									BL_Daughter_Abzuege.this.get_oNavigationList().FILL_GRID_From_InternalComponentMAPs(true, true);
									
									
									/*
									 * die letzte eingefuegte MAP
									 */
									E2_ComponentMAP 					ooMap = (E2_ComponentMAP)BL_Daughter_Abzuege.this.get_oNavigationList().get_vComponentMAPS_NEW().get(BL_Daughter_Abzuege.this.get_oNavigationList().get_vComponentMAPS_NEW().size()-1);
									HashMap<String, MyE2IF__Component> 	ohmAll = ooMap.get_REAL_ComponentHashMap();
									
									/*
									 * pos-nummer einfuegen und die start-menge/preis einfuegen
									 */
									MyE2_DB_TextField otfPOS_NUMMER =  (MyE2_DB_TextField) ohmAll.get("POS_NUMMER");
									

									//2011-01-01 aendern
									String cNewPosNr="1";
									
									Vector<E2_ComponentMAP> vAll_ComponentMAP_vor = new Vector<E2_ComponentMAP>();
									vAll_ComponentMAP_vor.addAll(BL_Daughter_Abzuege.this.get_oNavigationList().get_vComponentMAPS());
									vAll_ComponentMAP_vor.addAll(BL_Daughter_Abzuege.this.get_oNavigationList().get_vComponentMAPS_NEW());
									
									if (vAll_ComponentMAP_vor.size()>1)
									{
										E2_ComponentMAP oMapVorletzte = vAll_ComponentMAP_vor.get(vAll_ComponentMAP_vor.size()-2);
										cNewPosNr = ""+(oMapVorletzte.get_bdActualDBValue("POS_NUMMER", new BigDecimal(100), new BigDecimal(100)).intValue()+10);
									}
									//otfPOS_NUMMER.setText(""+vAll_ComponentMAP.size()+1);
									otfPOS_NUMMER.setText(cNewPosNr);

									
									
									/*
									 * menge/preis
									 */
									MyE2_DB_TextField oFieldMengeStart = (MyE2_DB_TextField)ohmAll.get("MENGE_VOR_ABZUG");
									MyE2_DB_TextField oFieldPreisStart = (MyE2_DB_TextField)ohmAll.get("EPREIS_VOR_ABZUG");
									MyE2_DB_TextField oFieldPreisStart_FW = (MyE2_DB_TextField)ohmAll.get("EPREIS_VOR_ABZUG_FW");
									
									if (vAll_ComponentMAP.size()==0)
									{
										oFieldMengeStart.set_cActualMaskValue(BL_Daughter_Abzuege.this.get__ActualFormatedValueMenge());
										oFieldPreisStart.set_cActualMaskValue(BL_Daughter_Abzuege.this.get__ActualFormatedValuePreis());
										oFieldPreisStart_FW.set_cActualMaskValue(BL_Daughter_Abzuege.this.get__ActualFormatedValuePreis_FW());
									}
									else
									{
										/*
										 * 2.letzte MAP raussuchen und daraus werte uebernehmen
										 */
										E2_ComponentMAP 					oMaplast = (E2_ComponentMAP)vAll_ComponentMAP.get(vAll_ComponentMAP.size()-1);
										HashMap<String, MyE2IF__Component> 	ohmAlllast = oMaplast.get_REAL_ComponentHashMap();
										oFieldMengeStart.set_cActualMaskValue(((MyE2_DB_TextField)ohmAlllast.get("MENGE_NACH_ABZUG")).get_cActualMaskValue());
										oFieldPreisStart.set_cActualMaskValue(((MyE2_DB_TextField)ohmAlllast.get("EPREIS_NACH_ABZUG")).get_cActualMaskValue());
										oFieldPreisStart_FW.set_cActualMaskValue(((MyE2_DB_TextField)ohmAlllast.get("EPREIS_NACH_ABZUG_FW")).get_cActualMaskValue());
									}
								}
							}
							catch (myException ex)
							{
								bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
							}
						}
					});
			
			this.add_GlobalValidator(new XX_ActionValidator()
			{
				@Override
				public MyE2_MessageVector isValid(Component componentWhichIsValidated) throws myException
				{
					MyE2_MessageVector oMV = new MyE2_MessageVector();
					String cWert = BL_Daughter_Abzuege.this.get__ActualFormatedValueMenge();
					if (! bibALL.isNumber(cWert,true))
					{
						oMV.add_MESSAGE(new MyE2_Alarm_Message("Abzüge können erst eingetragen werden, wenn eine Menge vorhanden ist (2)!"));
					}
					return oMV;
				}

				@Override
				protected MyE2_MessageVector isValid(String unformated)	throws myException
				{
					return null;
				}
				
			});
					
			
		}
	}
	///////////////////////////////////////////////////////////////////////
	
	
	


	
	
	
	////////////// kalkukation ausfuehren /////////////////////////////////
	private class button_refresh extends MyE2_Button
	{
		
		public button_refresh()
		{
			super(E2_ResourceIcon.get_RI("reload.png"),true);
			
			this.add_oActionAgent( new XX_ActionAgent()
			{
				public void executeAgentCode(ExecINFO oExecInfo)
				{
					BL_Daughter_Abzuege.this.bCalc_ganze_AbzugsListe(bibMSG.MV());
				}
			});
		}
	}
	///////////////////////////////////////////////////////////////////////

	public buttonForAddNEW get_oButNew()
	{
		return oButNew;
	}




	public button_refresh get_oButRefresh()
	{
		return oButRefresh;
	}




	
}
