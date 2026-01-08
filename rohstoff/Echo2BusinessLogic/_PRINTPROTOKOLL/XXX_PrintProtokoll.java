package rohstoff.Echo2BusinessLogic._PRINTPROTOKOLL;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.ActionAgent_DaughterTable_REFRESH;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_ButtonMarkForDelete;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_DBC_DaughterTable;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;


public abstract class XXX_PrintProtokoll extends MyE2_DBC_DaughterTable
{
	
	public static final String HASH_BUTTON_DOWNLOAD_ARCHIV  = 	"HASH_BUTTON_DOWNLOAD_ARCHIV";
	public static final String HASH_BUTTON_DELETE  = 			"HASH_BUTTON_DELETE";
	
	public static final String HASH_EMAIL_ANZEIGE_GRID  = 		"HASH_EMAIL_ANZEIGE_GRID";
	
	public static final String HASH_SPECIALFIELD_DRUCKDATZEIT = 	"HASH_SPECIALFIELD_DRUCKDATZEIT";
	public static final String HASH_SPECIALFIELD_USERKUERZEL = 		"HASH_SPECIALFIELD_USERKUERZEL";
//	public static final String HASH_SPECIALFIELD_IDTABLE_2 = 		"HASH_SPECIALFIELD_IDTABLE_2";

	public static final String HASH_BASEFIELD_BELEGINFO =    "BELEGINFO";
	//public static final String HASH_BASEFIELD_DRUCKDATUM =   "DRUCKDATUM";
	//public static final String HASH_BASEFIELD_KUERZEL =      "KUERZEL";
	public static final String HASH_BASEFIELD_BELEG =        "BELEG";
	public static final String HASH_BASEFIELD_MAIL =         "MAIL";
	public static final String HASH_BASEFIELD_ZEITSTEMPEL =  "ZEITSTEMPEL";

	
	
	private  String c_TableNameLeadingTable = 		null;
	private  String c_IDName_PK_LeadingTable = 		null;
	private  String c_TableNamePrintProtokoll = 	null;
	private  String c_IDName_PK_PrintProtokoll = 	null;
	private  String c_ID_ForeignKey 	 		= 	null;
	
	private  String c_PrintProtokoll_eMailTable =  null; 
	
	private  boolean b_ShowPrintProtokollMailList = false;
	
	/*
	 * in allen ableitungen der klasse enthaltene Tabellen-Felder
	 * 	ID_#BASETABLEBASENAME#_DRUCK
	 *	ID_#BASETABLEBASENAME#_RG     #BASETABLEBASENAME# ist z.B. VPOS_TPA_FUHRE oder VKOPF_RG
	 *	BELEGINFO
	 *	DRUCKDATUM
	 *	ERZEUGT_AM
	 *	ERZEUGT_VON
	 *	GEAENDERT_VON
	 *	ID_MANDANT
	 *	KUERZEL
	 *	LETZTE_AENDERUNG
	 *	BELEG
	 *	MAIL
	 *	ZEITSTEMPEL
	 */
	
	
	public XXX_PrintProtokoll(	) throws myException
	{
		super();
		
	}

	/**
	 * 
	 * @param SQL_fieldMAP_FromFuhre
	 * @param ocomponentMAP_from_Fuhre
	 * @param baseNameLeadingTable
	 * @param extentListRangeWidth
	 * @param extentListRangeHeight
	 * @throws myException
	 */
	public void init_PrintProtokoll(	SQLFieldMAP 		SQL_fieldMAP_FromFuhre, 
										E2_ComponentMAP		ocomponentMAP_from_Fuhre,
										String    			baseNameLeadingTable,
										Extent              extentListRangeWidth,
										Extent              extentListRangeHeight,
										boolean             bShowMailList) throws myException {
		/*
		 * tochtertabelle fuer das druckprotokoll
		 */

		this.c_TableNameLeadingTable = 		"JT_"+baseNameLeadingTable;
		this.c_IDName_PK_LeadingTable = 	"ID_"+baseNameLeadingTable;
		this.c_TableNamePrintProtokoll = 	"JT_"+baseNameLeadingTable+"_DRUCK";
		this.c_PrintProtokoll_eMailTable = 	"JT_"+baseNameLeadingTable+"_DRUCK_EM";
		this.c_IDName_PK_PrintProtokoll = 	"ID_"+baseNameLeadingTable+"_DRUCK";
		this.c_ID_ForeignKey 	 		= 	"ID_"+baseNameLeadingTable;
		
		this.b_ShowPrintProtokollMailList = bShowMailList;
		
		Project_SQLFieldMAP          oFieldMapDruck = new Project_SQLFieldMAP(c_TableNamePrintProtokoll,":"+c_ID_ForeignKey+":",false);
		this.set_bDaughterIsPassive(true);
		
		oFieldMapDruck.add_SQLField(new SQLFieldJoinOutside(c_TableNamePrintProtokoll,c_ID_ForeignKey,c_ID_ForeignKey,
									new MyE2_String("ID-Referenz"),false,bibE2.get_CurrSession(),SQL_fieldMAP_FromFuhre.get_SQLField(c_IDName_PK_LeadingTable)), false);

		//zusatzfeld fuer druckdatum
		oFieldMapDruck.add_SQLField(new SQLField("TO_CHAR(ERZEUGT_AM,'dd.mm.yyyy HH24:MI')",XXX_PrintProtokoll.HASH_SPECIALFIELD_DRUCKDATZEIT,new MyE2_String("Datum,Zeit des Drucks"),bibE2.get_CurrSession()), false);
		oFieldMapDruck.add_SQLField(new SQLField("ERZEUGT_VON",XXX_PrintProtokoll.HASH_SPECIALFIELD_USERKUERZEL,new MyE2_String("Kürzel"),bibE2.get_CurrSession()), false);

		oFieldMapDruck.initFields();
	
		
		
		//fuer den (uebersetzbaren) beleg-dropdown
		String[][] cDefArray = this.Define_DropDownArray_4_BELEGTYP();
		
		
		E2_ComponentMAP oMapDruck = new E2_ComponentMAP(oFieldMapDruck);
		MyE2_ButtonMarkForDelete 	oButtonForDel = 	new MyE2_ButtonMarkForDelete();
		MyE2_DB_SelectField    		oSelBelegTyp = 		new MyE2_DB_SelectField(oFieldMapDruck.get_SQLField(XXX_PrintProtokoll.HASH_BASEFIELD_BELEG),cDefArray,true);
		MyE2_DB_Label				oTF_Druckdatum = 	new MyE2_DB_Label(oFieldMapDruck.get_SQLField(XXX_PrintProtokoll.HASH_SPECIALFIELD_DRUCKDATZEIT));
		MyE2_DB_Label				lbl_Kuerzel = 		new MyE2_DB_Label(oFieldMapDruck.get_SQLField(XXX_PrintProtokoll.HASH_SPECIALFIELD_USERKUERZEL));
		MyE2_DB_Label				lbl_Zeitstempel = 	new MyE2_DB_Label(oFieldMapDruck.get_SQLField(XXX_PrintProtokoll.HASH_BASEFIELD_ZEITSTEMPEL));
		MyE2_DB_Label				oLabelInfo = 		new MyE2_DB_Label(oFieldMapDruck.get_SQLField(XXX_PrintProtokoll.HASH_BASEFIELD_BELEGINFO));
		MyE2_DB_CheckBox 			cbMail = 			new MyE2_DB_CheckBox(oFieldMapDruck.get_SQLField(XXX_PrintProtokoll.HASH_BASEFIELD_MAIL));
		MyE2_DB_Label				oLabelID = 			new MyE2_DB_Label(oFieldMapDruck.get_SQLField(this.get_IDName_PK_PrintProtokoll()));

		//2014-10-23: neue komponente zur anzeige evtl. versandter eMails
		MyE2_Grid                   oGridZeigeMails =   new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100()) ;
		

		oMapDruck.add_Component(XXX_PrintProtokoll.HASH_BUTTON_DELETE,oButtonForDel,new MyE2_String("?"));
		oMapDruck.add_Component(oSelBelegTyp,new MyE2_String("Beleg"));
		//2015-06-12: download-button ersetzt mit standard-anlagen-popup
//		oMapDruck.add_Component(XXX_PrintProtokoll.HASH_BUTTON_DOWNLOAD_ARCHIV, new BS_BUTTON_DownloadDruckbelege(), new MyE2_String("Lade"));
		oMapDruck.add_Component(XXX_PrintProtokoll.HASH_BUTTON_DOWNLOAD_ARCHIV, new BT_UpDownload_PrintProtokollAttachtments(this.c_TableNamePrintProtokoll), new MyE2_String("Lade"));
		oMapDruck.add_Component(oTF_Druckdatum,new MyE2_String("Druckdat."));
		oMapDruck.add_Component(lbl_Kuerzel,new MyE2_String("Kürzel ?"));
		oMapDruck.add_Component(lbl_Zeitstempel,new MyE2_String("Masken-Zeitstempel"));
		oMapDruck.add_Component(cbMail,new MyE2_String("Mail"));
		if (this.b_ShowPrintProtokollMailList) {
			oMapDruck.add_Component(XXX_PrintProtokoll.HASH_EMAIL_ANZEIGE_GRID,oGridZeigeMails,new MyE2_String("Mail von-nach"));
		}
		oMapDruck.add_Component(oLabelInfo,new MyE2_String("Info"));
		oMapDruck.add_Component(oLabelID, new MyE2_String("ID"));
	
		this.add_SpecialListComponents(oFieldMapDruck, oMapDruck);
		
		//2014-10-23: Anzeige fuer eMail-Traffic
		if (this.b_ShowPrintProtokollMailList) {
			oMapDruck.add_oSubQueryAgent(new ownListSubQueryAgent());
		}
		
	
		oSelBelegTyp.EXT().set_oColExtent(new Extent(200));
		oTF_Druckdatum.EXT().set_oColExtent(new Extent(150));
		lbl_Kuerzel.EXT().set_oColExtent(new Extent(80));
		lbl_Zeitstempel.EXT().set_oColExtent(new Extent(120));
		cbMail.EXT().set_oColExtent(new Extent(40));
	
		this.manipulate_SpecialListComponentMAP(oMapDruck);
		

		//2014-11-04: statt unnützer neueingabe button einen refresh-button
		
		/*
		 * neueingabe-button definieren
		 */
//		MyE2_Button		oButtonNEW = new MyE2_Button(E2_ResourceIcon.get_RI("new.png"),true);
//		oButtonNEW.add_oActionAgent(new ActionAgent_DaughterTable_NEW(this, false));
		
		MyE2_Button oButtonReferesh = new MyE2_Button(E2_ResourceIcon.get_RI("reload.png"),true);
		oButtonReferesh.add_oActionAgent(new ActionAgent_DaughterTable_REFRESH(this));
		
		oButtonForDel.EXT().set_oCompTitleInList(oButtonReferesh);
		
		//this.get_vComponentForDifferentTasks().add(oButtonNEW);
		
		this.set_oContainerExScrollWidth(extentListRangeWidth);
		this.set_oContainerExScrollHeight(extentListRangeHeight);
		
		this.INIT_DAUGHTER(	SQL_fieldMAP_FromFuhre.get_oSQLFieldPKMainTable(),
							ocomponentMAP_from_Fuhre,
							oMapDruck,
							null);
			
		this.EXT().set_bDisabledFromBasic(true);
	}
	

	public abstract String[][]  Define_DropDownArray_4_BELEGTYP() throws myException;
	
	public abstract void       	add_SpecialListComponents(SQLFieldMAP oSQLFieldMapDruck, E2_ComponentMAP  oComponentMAPDruck)  throws myException;

	public abstract String[][]  query_MailAdresses4Protokoll_Record(String cID_RECORD_PROTOKOLL) throws myException;
	
	/**
	 * methode kann benutzt werden, z.b. um Breiten der Spalten anzuzeigen oder diese ganz wegzublenden
	 * @param oMAP_List
	 * @return
	 * @throws myException
	 */
	public abstract E2_ComponentMAP     manipulate_SpecialListComponentMAP(E2_ComponentMAP  oMAP_List)  throws myException;


	public String get_TableNameLeadingTable() {
		return c_TableNameLeadingTable;
	}

	public String get_IDName_PK_LeadingTable() {
		return c_IDName_PK_LeadingTable;
	}

	public String get_TableNamePrintProtokoll() {
		return c_TableNamePrintProtokoll;
	}

	public String get_IDName_PK_PrintProtokoll() {
		return c_IDName_PK_PrintProtokoll;
	}

	public String get_ID_ForeignKey() {
		return c_ID_ForeignKey;
	}

	public boolean get_bShowPrintProtokollMailList() {
		return b_ShowPrintProtokollMailList;
	}

	public void set_bShowPrintProtokollMailList(boolean bShowPrintProtokollMailList) {
		this.b_ShowPrintProtokollMailList = bShowPrintProtokollMailList;
	}
	
	private class ownListSubQueryAgent extends XX_ComponentMAP_SubqueryAGENT {

		@Override
		public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) throws myException {
		}

		@Override
		public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException {
			
			String[][] cMailAdresse = XXX_PrintProtokoll.this.query_MailAdresses4Protokoll_Record(oUsedResultMAP.get_cUNFormatedROW_ID());
			
			if (cMailAdresse == null || cMailAdresse.length==0) {
				((MyE2_Grid)oMAP.get__Comp(XXX_PrintProtokoll.HASH_EMAIL_ANZEIGE_GRID)).add(new MyE2_Label("--"),2, E2_INSETS.I(5,0,5,0));
			} else {
				for (int i=0;i<cMailAdresse.length;i++) {
					((MyE2_Grid)oMAP.get__Comp(XXX_PrintProtokoll.HASH_EMAIL_ANZEIGE_GRID)).add(new MyE2_Label("("+oUsedResultMAP.get_FormatedValue(XXX_PrintProtokoll.HASH_SPECIALFIELD_USERKUERZEL, "<uk>")+")", new E2_FontPlain(-2)), E2_INSETS.I_0_0_5_0);
					((MyE2_Grid)oMAP.get__Comp(XXX_PrintProtokoll.HASH_EMAIL_ANZEIGE_GRID)).add(new MyE2_Label(cMailAdresse[i][1], new E2_FontPlain(-2)), E2_INSETS.I_0_0_0_0);
				}
			}
		}
		
	}

	public String get_cIDName_PK_PrintProtokoll() {
		return c_IDName_PK_PrintProtokoll;
	}


	public String get_cPrintProtokoll_eMailTable() {
		return c_PrintProtokoll_eMailTable;
	}

	
}
