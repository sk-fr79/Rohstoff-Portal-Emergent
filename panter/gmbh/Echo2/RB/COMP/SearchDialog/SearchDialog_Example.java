package panter.gmbh.Echo2.RB.COMP.SearchDialog;

import java.util.Vector;

import org.apache.commons.lang.StringUtils;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_K;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.COMP.SearchDialog.SearchDialog_Base.SORTSTATUS;
import panter.gmbh.Echo2.RB.COMP.SearchDialog.SearchDialog_UserSetting_Sort;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.HIGHLEVEL.E2_BtEditAdress;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS.USERSETTINGS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_USER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.indep.S;
import panter.gmbh.indep.StringSeparator;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.MyResultValue;
import panter.gmbh.indep.dataTools.TERM.Nvl;
import panter.gmbh.indep.dataTools.TERM.TermLMR;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM._TermCONST;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

/**
 * Beispiel-Implementierung eines Suchdialogs
 * müsste eigentlich SearchDialog_Sample heissen :-(
 * 
 * @author manfred
 * @date 16.08.2017
 *
 */

public  class SearchDialog_Example extends SearchDialog_Base {


	
	public static enum ENUM_SELEKTOR_TEST {
		AKTIV,
		LOGIN,
		TELEFON,
		NAME,
		MODULKENNER,
		MODULSETTINGS, TELEFON1, SUPERVISOR
	}
	
	
	
	/**
	 * 
	 * @param exclude_own_adress
	 * @throws myException
	 */
	public SearchDialog_Example() throws myException {
		super();
		this.set_title_of_popup(new MyE2_String("Auswahl der Benutzer..."));
		this._init();
	}
	
	
	private void _init() throws myException {
		
		this.vecColumnList.add( new SearchDialog_ResultColumn(USER.field(USER.id_user.fieldName()), "User-ID",USER.id_user.fn(),100).set_StyleHeader(style_bold_kursive).set_StyleData(style_kursive).set_AlignmentData(new Alignment(Alignment.RIGHT,Alignment.TOP)).set_AlignmentHeader(new Alignment(Alignment.RIGHT,Alignment.TOP)));
		this.vecColumnList.add( new SearchDialog_ResultColumn("Infospalte", "INFO",false, 50));
		this.vecColumnList.add( new SearchDialog_ResultColumn(USER.field(USER.name.fieldName()),"Login",USER.name.fn(),200).set_StyleData(style_normal).set_StyleHeader(style_kursive) ) ;
		this.vecColumnList.add( new SearchDialog_ResultColumn(USER.field(USER.vorname.fieldName()),"Vorname",USER.vorname.fn(),300).set_StyleData(style_normal).set_StyleHeader(style_bold_kursive) ) ;
		this.vecColumnList.add( new SearchDialog_ResultColumn(USERSETTINGS.field(USERSETTINGS.modul_kenner.fieldName()),"Modulkenner",USERSETTINGS.modul_kenner.fn(),500).set_StyleData(style_normal).set_StyleHeader(style_bold_kursive));
		this.vecColumnList.add( new SearchDialog_ResultColumn(USERSETTINGS.field(USERSETTINGS.modulsettings.fieldName()),"Modulsetting",USERSETTINGS.modulsettings.fn(),500).set_StyleData(style_normal).set_StyleHeader(style_bold_kursive));
		this.vecColumnList.add( new SearchDialog_ResultColumn(USER.field(USER.ist_supervisor.fieldName()),"Supervisor",USER.ist_supervisor.fn(),20).set_StyleData(style_normal).set_StyleHeader(style_bold_kursive));
		
		
		
		SearchDialog_SelectorList selList= new SearchDialog_SelectorList();
		// bsp 1: 
		SearchDialog_SelectorEntry e = new SearchDialog_SelectorEntry(ENUM_SELEKTOR_TEST.AKTIV.name()).set_Description("Ist aktiv").set_Active(true).setTermList(new And((new vgl_YN(USER.aktiv, true))) )  ;
		this.selections_list.addSelectorEntry(e);
		
		// selectfield
		String[][] arrSelect = {{"Ist Supervisor","Y"},
								{"Ist kein Supervisor","N"}
								};
//		MyE2_SelectField oSel = new MyE2_SelectField(arrSelect, "" , false, new Extent(150));
		SearchDialog_Selector_SelectField oSel = new SearchDialog_Selector_SelectField(arrSelect, "" , false, new Extent(150));
		e = new SearchDialog_SelectorEntry(ENUM_SELEKTOR_TEST.SUPERVISOR.name()).set_Description("Ist Supervisor").set_Active(true).set_Value("Y")
				.setTermList(new And( new  TermLMR( new Nvl(USER.ist_supervisor.t(),"'N'"),_TermCONST.COMP.EQ.s(),"'#WERT#'") )).set_valueComponent(oSel);
					
		this.selections_list.addSelectorEntry(e);
		
		// implizit...
		this.selections_list.addSelectorEntry(new SearchDialog_SelectorEntry(ENUM_SELEKTOR_TEST.TELEFON.name())
														.set_Description("hat Telefon ")
														.set_Value("-")
														.set_Active(false)
														.setTermList(new And(new TermSimple(USER.telefon.tnfn()+" IS NOT NULL"))));
		
		this.selections_list.addSelectorEntry(new SearchDialog_SelectorEntry(ENUM_SELEKTOR_TEST.TELEFON1.name())
														.set_Description("hat KEIN Telefon ")
														.set_Active(false)
														.setTermList(new And(new TermSimple(USER.telefon.tnfn()+" IS  NULL"))));
		
		this.selections_list.addSelectorEntry(new SearchDialog_SelectorEntry(ENUM_SELEKTOR_TEST.LOGIN.name())
														.set_Description("Login ")
														.set_Value("manfred")
														.set_Active(false)
														.setTermList(new And(new TermLMR("Upper(" + USER.name.tnfn()+")",_TermCONST.COMP.LIKE.s(),"UPPER('%#WERT#%')")))
														.set_valueComponent(new SearchDialog_Selector_TextField("",200,100))
														);
		
		this.selections_list.addSelectorEntry(new SearchDialog_SelectorEntry(ENUM_SELEKTOR_TEST.NAME.name())
														.set_Description("Name1 ")
														.set_Value("Panter")
														.set_Active(true)
														.setTermList(new And(new TermLMR("Upper(" + USER.name1.tnfn()+")",_TermCONST.COMP.LIKE.s(),"UPPER('%#WERT#%')")))
														.set_valueComponent(new SearchDialog_Selector_TextField("",200,100)));
		
		this.selections_list.addSelectorEntry(new SearchDialog_SelectorEntry(ENUM_SELEKTOR_TEST.MODULKENNER.name())
														.set_Description("Settingseintrag ")
														.set_Value("KONTRAKT")
														.set_Active(true)
														.setTermList(new And(new TermLMR("Upper(" + USERSETTINGS.modulsettings.tnfn()+")",_TermCONST.COMP.LIKE.s(),"UPPER('%#WERT#%')")))
														.set_valueComponent(new SearchDialog_Selector_TextField("",200,100)));

		this.selections_list.set_default_insets(new Insets(2, 5, 5, 0));
//		this.set_iMaxResults(50);
		// speicherstatus hinzufuegen
		this.set_key_4_save_sorting(ENUM_USER_SAVEKEY.SAVEING_SORTING_SEARCHFIELD_RB_HL_SEARCHADRESS);
				
		
	}
	
	
	
	
	
	public MyE2_MessageVector  execute_searchquery_and_fill_resultbutton_array() throws myException {
		
		Vector<String>      v_wheres = new Vector<String>();
		
		
		v_wheres.add(" WHERE 1=1 ");
		v_wheres.add(" " + this.getSelections_list().getStatement() + " " );
		
		String colID = USER.id_user.tnfn() + "||'#'||" + USERSETTINGS.id_usersettings.tnfn();
		
		SEL sel = new SEL()
				.ADDFIELD(colID,"ID_ALLE")
				.ADDFIELD(USER.id_user)
				.ADDFIELD( "CASE when " +USER.id_user.tnfn() + " > 1000 then 'hallo' else 'tschüss' end", "info")
				.ADDFIELD(USER.name)
				.ADDFIELD(USER.vorname)
				.ADDFIELD(USERSETTINGS.modulsettings)
				.ADDFIELD(USERSETTINGS.modul_kenner)
				.ADDFIELD(USERSETTINGS.id_usersettings)
				.ADDFIELD(USER.ist_supervisor)
				.FROM(_TAB.user)
				.LEFTOUTER(_TAB.usersettings,USER.id_user, USERSETTINGS.id_user);
		
		String sql_where = bibALL.Concatenate(v_wheres, " AND ", "1=1");  //suchanteil und zusatzbedingungen
		RECLIST_MyRecord rl = null;

//		Vector<String> v_names = new Vector<String>();
//		for (IF_Field f: USER.values()) {
//			v_names.add(f.tnfn());
//		}

		String c_sql_query = sel.s()  + sql_where;
		
		DEBUG.System_println(c_sql_query,DEBUG.DEBUG_FLAGS.DIVERS1.name());

		if (this.get_iMaxResults()>0) {
			rl = new RECLIST_MyRecord(c_sql_query,"ID_ALLE",this.get_iMaxResults()+1,true);
			if (rl.size() > this.get_iMaxResults()){
				MyE2_Warning_Message msg = new MyE2_Warning_Message(String.format("Das Ergebnis der Suche hat mehr als %d Zeilen. Bitte schränken Sie die Suche ein!",this.get_iMaxResults()) );
				bibMSG.add_MESSAGE(msg);
			}
		} else {
			rl = new RECLIST_MyRecord(c_sql_query,"ID_ALLE");
		}
		
		SearchDialog_vecResultButtons  v_result_arrays = this.get_rb_vecResultButtons();
		v_result_arrays.clear();
		
		int cols = vecColumnList.size(); 
		
		for (int i=0;i<rl.size();i++) {
			String sort = "";

			MyRECORD rec = rl.get(i);
			SearchDialog_ResultButton[] res_zeile = new SearchDialog_ResultButton[cols];
			
			// Spalte Erzeugen
			String rowID = rec.get("ID_ALLE").get_FieldValueUnformated();
			
			for(int j=0; j < vecColumnList.size();j++){
				SearchDialog_ResultColumn col = vecColumnList.get(j);
				
				MyResultValue f = rec.get(col.get_ColName());
				String sValueUnformatted =  rec.get_UnFormatedValue(col.get_ColName());
				String sValueFormatted =  rec.get_FormatedValue(col.get_ColName());

				if (f != null){
					String sValue = sValueUnformatted != null ? sValueFormatted : "";
					if (col.isPK()) {
						sort = StringUtils.leftPad(sValue , 20,"0");
					} else {
						sort = sValue.toUpperCase();
					}
				}
				res_zeile[j]  = new SearchDialog_ResultButton(this, rowID, sValueFormatted, col.get_StyleData(), null, new SearchDialog_ResultButton_action(rowID, this), sort);
			}
			v_result_arrays.add(res_zeile);
		}
		
		return new MyE2_MessageVector();
	}
	
	
	
	
	public void  fill_grid_ResultSet(MyE2_Grid grid_4_popup, SearchDialog_vecResultButtons vektor_buttons) throws myException {
		// Header erzeugen
		E2_Grid4MaskSimple gs = this.initResultGrid();
		
		// build GridRows
		// neue Farbe für die rows
		gs.def_(new E2_ColorBase(-30));
		gs._align(new Alignment(Alignment.LEFT,Alignment.TOP));
		int cols = 1;
		
		
		SearchDialog_ResultButton rb;
		for (SearchDialog_ResultButton_IF[] arr: vektor_buttons) {
			cols = arr.length;
			
/*			spezielle formatierungen
 * 			ACHTUNG bei der Spaltenzahl
 * 			col = anzahl tatsächlich angezeigter Spalten.
 * 
			// 1. Spalte ID
			if (arr[0].me() instanceof RB_ResultButton){
				rb = (RB_ResultButton)arr[0].me();
				String v = rb.getText();
				if (Integer.parseInt(v.replace(".","")) > 1000){
					
					rb.setStyle(style_bold_kursive);

					gs.def_(new E2_ColorBase(-30));
					gs.def_(Alignment.ALIGN_LEFT);
				} else {
					
					rb.setStyle(style_normal);

					gs.def_(new E2_ColorBase());
					gs.def_(Alignment.ALIGN_RIGHT);
					gs.def_(style_normal);
				}
				gs.add_(rb);
			} else {
				gs.add_(arr[0].me());
			}

			// restliche Spalten style normal
			gs 	//.def_(vecColumnList.get(1).get_bgColColumn())
				.def_(vecColumnList.get(1).get_AlignmentData())
				.def_(vecColumnList.get(1).get_StyleData())
				.add_(arr[1].me());
			
			gs	//.def_(vecColumnList.get(2).get_bgColColumn())
				.def_(vecColumnList.get(2).get_AlignmentData())
				.def_(vecColumnList.get(1).get_StyleData())
				.add_(arr[2].me());
			
			//gs.setWidth(new Extent(100, Extent.PERCENT));
*/			
			
			
			// oder einfaches dazufügen mit dem default Stil / Ausrichtung
			for (int a=0;  a < cols; a++){
				gs  .def_(vecColumnList.get(a).get_bgColColumn())
					.def_(vecColumnList.get(a).get_AlignmentData())
					.add_(arr[a].me());
			}
			
		}
		gs.setSize(vecColumnList.size());
		
		grid_4_popup.add(gs,E2_INSETS.I(0,0,0,0));
	}


	

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.SearchDialog.RB_SearchDialogBase#get_result_string_from_record(panter.gmbh.indep.dataTools.RECORD2.Rec20)
	 */
	@Override
	public String get_result_string_from_record(MyRECORD_IF_RECORDS p_result_record) throws myException {
		String sRet = null;
		sRet = ((RECORD_USER)p_result_record).get_ID_USER_cUF();
		return sRet;
	}
	

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.SearchDialog.RB_SearchDialogBase#get_result_record_from_string(java.lang.String)
	 */
	@Override
	public MyRECORD_IF_RECORDS get_result_record_from_string(String unformated_MaskValue) throws myException {
		return new RECORD_USER(unformated_MaskValue);
	}

	


	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.SearchDialog.RB_SearchDialogBase#fill_grid_4_selector(panter.gmbh.Echo2.components.E2_Grid)
	 */
	@Override
	public void fill_grid_Selector(E2_Grid grid_4_searchParameter) throws myException {
		// Aufbau 
		grid_4_searchParameter._clear();
		grid_4_searchParameter._bo_dd();
		grid_4_searchParameter._ins(new Insets(10, 5, 5, 5));
		grid_4_searchParameter._s(1);
		grid_4_searchParameter._a("Erweiterte Suche nach Benutzern");
		
		// Anzeigen der definierten Selektoren
//		grid_4_searchParameter._ins(new Insets(10, 5, 5, 5));
		grid_4_searchParameter._add_raw(getSelections_list().getGrid());
		// zusätzliche Zeilen...
//		grid_4_searchParameter._a("...");
	}

}
