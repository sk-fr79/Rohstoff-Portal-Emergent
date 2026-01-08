package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Button;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_date_selektor;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButton;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButtonArrays;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButton_IF;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchField;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchField_actionStartSearch;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchFieldListSortButton;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_STD;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON_TRAKT;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_STD;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_STD_ANGEBOT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_STD;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.StringSeparator;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.MyRECORD_LIST;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM._TermCONST.ATTRIBUTES;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS.FZ__CONST.SEARCH_EK_OR_VK;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

public abstract class FZ_MASK_Search_Kontrakt_Angebot_in_ONE extends RB_SearchField {
	
	
	protected abstract MyLong get_id_adresse_station_in_search_moment() throws myException;

	protected abstract MyLong get_id_artikel_bez_in_searchmoment() throws myException;

	//anzeigen, was gerade geladen ist
	private MyE2_Grid  grid4Label_kontrakt_oder_angebot= new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	private MyE2_Label labelKon = new MyE2_Label(E2_ResourceIcon.get_RI("k.png"));
	private MyE2_Label labelAng = new MyE2_Label(E2_ResourceIcon.get_RI("a.png"));
	private MyE2_Label labelempty = new MyE2_Label(E2_ResourceIcon.get_RI("empty25.png"));
	
	//zwei such-bloeck: zuerst werden angebote gesucht ...
	private And    and_statement_collector_4_basic_angebot = new And();

	// ... dann kontrakte
	private And    and_statement_collector_4_basic_kontrakt = new And();
	
	
	//zwei such-bloeck: zuerst werden angebote gesucht ...
	private And    and_statement_collector_4_search_angebot = new And();
	private And    and_statement_collector_4_search_kontrakt = new And();
	
	
	
	
	private String select_base = 
				"SELECT NVL(A.NAME1,'')||' '||NVL(A.NAME2,'') AS NAME"
						+ ", A.ORT AS ORT"
						+ ", NVL(P.ANR1,'')||'-'||NVL(P.ANR2,'') AS ANR1_2"
						+ ", P.ARTBEZ1"
						+ ", P.ANZAHL"
						+ ", P.EINZELPREIS"
						+ ", TO_CHAR(PZ.GUELTIG_VON,'DD.MM.YYYY') AS GUELTIG_VON"
						+ ", TO_CHAR(PZ.GUELTIG_BIS,'DD.MM.YYYY') AS GUELTIG_BIS";
	
	
	private String  from_angebot = " FROM "+bibE2.cTO()+".JT_VPOS_STD_ANGEBOT PZ"+
										" INNER JOIN "+bibE2.cTO()+".JT_VPOS_STD P ON (P.ID_VPOS_STD=PZ.ID_VPOS_STD) "+
										" INNER JOIN "+bibE2.cTO()+".JT_VKOPF_STD K ON (K.ID_VKOPF_STD=P.ID_VKOPF_STD) "+
										" INNER JOIN "+bibE2.cTO()+".JT_ADRESSE  A ON (A.ID_ADRESSE=K.ID_ADRESSE) "+
										" INNER JOIN "+bibE2.cTO()+".JT_ARTIKEL_BEZ AB ON (AB.ID_ARTIKEL_BEZ=P.ID_ARTIKEL_BEZ) "+
										" INNER JOIN "+bibE2.cTO()+".JT_ARTIKEL ART ON (AB.ID_ARTIKEL=ART.ID_ARTIKEL) "+
										"";
	
	private String  from_kontrakt = " FROM "+bibE2.cTO()+".JT_VPOS_KON_TRAKT PZ"+
										" INNER JOIN "+bibE2.cTO()+".JT_VPOS_KON P ON (P.ID_VPOS_KON=PZ.ID_VPOS_KON) "+
										" INNER JOIN "+bibE2.cTO()+".JT_VKOPF_KON K ON (K.ID_VKOPF_KON=P.ID_VKOPF_KON) "+
										" INNER JOIN "+bibE2.cTO()+".JT_ADRESSE  A ON (A.ID_ADRESSE=K.ID_ADRESSE) "+
										" INNER JOIN "+bibE2.cTO()+".JT_ARTIKEL_BEZ AB ON (AB.ID_ARTIKEL_BEZ=P.ID_ARTIKEL_BEZ) "+
										" INNER JOIN "+bibE2.cTO()+".JT_ARTIKEL ART ON (AB.ID_ARTIKEL=ART.ID_ARTIKEL) "+
										"";
	
	private String select_base_angebot = select_base+", P.ID_VPOS_STD, 'A' AS TYP "+from_angebot;
	private String select_base_kontrakt = select_base+", P.ID_VPOS_KON, 'K' AS TYP "+from_kontrakt;

	

//	//hier wird hinterlegt, was die zahl im suchfeld gerade bedeutet
//	protected String    			s_value_id_vpos_kon = "";
//	protected String    			s_value_id_vpos_std = "";
	
	private SEARCH_EK_OR_VK  		searchTyp = null;

	private 	RB_TextField        tf_kontrakt = null;
	private 	RB_TextField        tf_angebot = null;
	//es werden zwei simple RB_TextField - objekte uebergeben, die vom suchvorgang gefuellt werden, und die dann gespeichert werden

	
	/**
	 * 	
	/**
	 * kombifeld fuer id_vpos_std (angebot) und id_vpos_kon (kontrakt), wird formal unter der id_vpos_kon registriert
	 * @param p_searchTyp
	 * @param _tf_kontrakt
	 * @param _tf_angebot
	 * @throws myException
	 */
	public FZ_MASK_Search_Kontrakt_Angebot_in_ONE(SEARCH_EK_OR_VK  p_searchTyp, RB_TextField _tf_kontrakt, RB_TextField _tf_angebot) throws myException {
		super();
		
		this.tf_angebot = _tf_angebot;
		this.tf_kontrakt = _tf_kontrakt;
		
		this.searchTyp = p_searchTyp;
		this.get_tf_search_input().setAlignment(new Alignment(Alignment.RIGHT, Alignment.CENTER));
		
		this.and_statement_collector_4_basic_angebot.and(new FieldTerm(ATTRIBUTES.TO_CHAR_DATE_YYYY_MM_DD,"PZ",VPOS_STD_ANGEBOT.gueltig_von).s(),COMP.LE.s(),"#DATUM#")
													.and(new FieldTerm(ATTRIBUTES.TO_CHAR_DATE_YYYY_MM_DD,"PZ",VPOS_STD_ANGEBOT.gueltig_bis).s(),COMP.GE.s(),"#DATUM#")
													.and(new FieldTerm("A",ADRESSE.id_adresse).s(),COMP.EQ.s(),"#ID_ADRESSE#")
													.and(this.searchTyp==SEARCH_EK_OR_VK.EK
													?
													new vgl("K", VKOPF_STD.vorgang_typ, myCONST.VORGANGSART_ABNAHMEANGEBOT)
													:
													new vgl("K", VKOPF_STD.vorgang_typ, myCONST.VORGANGSART_ANGEBOT)	
													)
													;


		//vertraege muessen offen sein, gueltigkeit ist nicht relevant
		this.and_statement_collector_4_basic_kontrakt	.and(new vgl_YN("PZ", VPOS_KON_TRAKT.abgeschlossen, false))
														.and(new FieldTerm("A",ADRESSE.id_adresse).s(),COMP.EQ.s(),"#ID_ADRESSE#")
														.and(this.searchTyp==SEARCH_EK_OR_VK.EK
														?
														new vgl("K", VKOPF_KON.vorgang_typ, myCONST.VORGANGSART_EK_KONTRAKT)
														:
														new vgl("K", VKOPF_KON.vorgang_typ, myCONST.VORGANGSART_VK_KONTRAKT)	
														)
														;
		
		
		
		
		this.and_statement_collector_4_search_angebot.or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,"A",ADRESSE.name1), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search_angebot.or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,"A",ADRESSE.name2), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search_angebot.or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,"A",ADRESSE.name3), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search_angebot.or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,"A",ADRESSE.strasse), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search_angebot.or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,"A",ADRESSE.plz), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search_angebot.or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,"A",ADRESSE.ort), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search_angebot.or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,"ART",ARTIKEL.anr1), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search_angebot.or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,"ART",ARTIKEL.artbez1), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search_angebot.or(new vgl(new TermSimple("TO_CHAR(A.ID_ADRESSE)"), COMP.EQ, new TermSimple("'#WERT#'")));
		this.and_statement_collector_4_search_angebot.or(new vgl(new TermSimple("TO_CHAR(P.ID_VPOS_STD)"), COMP.EQ, new TermSimple("'#WERT#'")));

		this.and_statement_collector_4_search_kontrakt.or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,"A",ADRESSE.name1), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search_kontrakt.or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,"A",ADRESSE.name2), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search_kontrakt.or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,"A",ADRESSE.name3), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search_kontrakt.or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,"A",ADRESSE.strasse), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search_kontrakt.or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,"A",ADRESSE.plz), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search_kontrakt.or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,"A",ADRESSE.ort), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search_kontrakt.or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,"ART",ARTIKEL.anr1), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search_kontrakt.or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,"ART",ARTIKEL.artbez1), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		this.and_statement_collector_4_search_kontrakt.or(new vgl(new TermSimple("TO_CHAR(A.ID_ADRESSE)"), COMP.EQ, new TermSimple("'#WERT#'")));
		this.and_statement_collector_4_search_kontrakt.or(new vgl(new TermSimple("TO_CHAR(P.ID_VPOS_KON)"), COMP.EQ, new TermSimple("'#WERT#'")));
	
		this.set_iMaxResults(500);
		
		//speicherstatus hinzufuegen
		this.set_key_4_save_sorting(ENUM_USER_SAVEKEY.SAVEING_SORTING_SEARCHFIELD_RB_SEARCH_KONTRAKT_ODER_ANGEBOT);

		//sicherstellen, dass immer zuerst das datumsfeld gefuellt ist !
		this.get_buttonStartSearch().add_GlobalValidator(new ownSearchStartValidator());
		
		this._allowEmptySearchfield(true);
		

	}



	@Override
	public E2_BasicModuleContainer generate_container_4_popup_window() throws myException {
		return new ownBasicModuleContainer4Popup();
	}



	@Override
	public  XX_ActionAgent  	generate_StartSearchAction() throws myException {
		return new RB_SearchField_actionStartSearch(this);
	}
	
	
	@Override
	public  XX_ActionAgent  generate_EraseButtonAction() throws myException {
		return new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				FZ_MASK_Search_Kontrakt_Angebot_in_ONE.this.rb_set_db_value_manual("", true, true);
			}
		};
	}

	
	
	
	
	@Override
	public MyE2_MessageVector execute_searchquery_and_fill_resultbutton_array(String search_text) throws myException {

		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		Vector<String>  v_dynWheres = new Vector<>();
		
		MyLong id_artikel_bez = this.get_id_artikel_bez_in_searchmoment();
		if (id_artikel_bez != null && id_artikel_bez.isOK()) {
			v_dynWheres.add(new And(new FieldTerm("P",ARTIKEL_BEZ.id_artikel_bez).s(),COMP.EQ.s(),id_artikel_bez.get_cUF_LongString()).s());
		}
		
		
		Vector<String>      v_wheres_angebot = new Vector<String>();
		Vector<String>      v_wheres_kontrakt = new Vector<String>();
		
		v_wheres_angebot.addAll(v_dynWheres);
		v_wheres_kontrakt.addAll(v_dynWheres);
		
		if (S.isFull(search_text)) {
			StringSeparator 	v_separator = new StringSeparator(search_text," ");
			for (String s: v_separator) {
				v_wheres_angebot.add(" ("+bibALL.ReplaceTeilString(this.and_statement_collector_4_search_angebot.s(), "#WERT#", s.trim())+") ");
				v_wheres_kontrakt.add(" ("+bibALL.ReplaceTeilString(this.and_statement_collector_4_search_kontrakt.s(), "#WERT#", s.trim())+") ");
			}
		}
		
		v_wheres_angebot.add("("+this.and_statement_collector_4_basic_angebot.s()+")");
		v_wheres_kontrakt.add("("+this.and_statement_collector_4_basic_kontrakt.s()+")");

		
		String sql_where_angebot = bibALL.Concatenate(v_wheres_angebot, " AND ", "1=1");  //suchanteil und zusatzbedingungen
		String sql_where_kontrakt = bibALL.Concatenate(v_wheres_kontrakt, " AND ", "1=1");  //suchanteil und zusatzbedingungen
		

		MyDate date_fuhre = this.get_date4fuhre();
		MyLong id_adresse = this.get_id_adresse_station_in_search_moment();

		if (date_fuhre==null || id_adresse==null) {
			throw new myException("Date and start-adresse MUST be filled !!");    //darf nicht passieren, da der validierer beim start das verhindert
		}

		RECORD_ADRESSE_extend ra = new RECORD_ADRESSE_extend(id_adresse.get_lValue());
		String id_main_adress = ra.get_main_Adress().get_ID_ADRESSE_cUF();
		
		
		sql_where_angebot = bibALL.ReplaceTeilString(sql_where_angebot, "#DATUM#", date_fuhre.get_cDBFormatErgebnis_4_SQLString());
		sql_where_kontrakt = bibALL.ReplaceTeilString(sql_where_kontrakt, "#DATUM#", date_fuhre.get_cDBFormatErgebnis_4_SQLString());
		
		sql_where_angebot = bibALL.ReplaceTeilString(sql_where_angebot, "#ID_ADRESSE#", id_main_adress);
		sql_where_kontrakt = bibALL.ReplaceTeilString(sql_where_kontrakt, "#ID_ADRESSE#", id_main_adress);
		
		
		MyRECORD_LIST rl_angebot = null;
		MyRECORD_LIST rl_kontrakt = null;
		
		if (this.get_iMaxResults()>0) {
			rl_angebot = new MyRECORD_LIST(this.select_base_angebot+" WHERE "+sql_where_angebot, "ID_VPOS_STD", this.get_iMaxResults());
			rl_kontrakt = new MyRECORD_LIST(this.select_base_kontrakt+" WHERE "+sql_where_kontrakt, "ID_VPOS_KON", this.get_iMaxResults());
		} else {
			rl_angebot = new MyRECORD_LIST(this.select_base_angebot+" WHERE "+sql_where_angebot, "ID_VPOS_STD");
			rl_kontrakt = new MyRECORD_LIST(this.select_base_kontrakt+" WHERE "+sql_where_kontrakt, "ID_VPOS_KON");
		}
		
		RB_ResultButtonArrays  v_result_arrays = this.get_rb_ResultButtonArray();
		v_result_arrays.clear();
		
		E2_MutableStyle style = MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder_Umbruch();
		E2_MutableStyle styleRight = MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder_Umbruch();
		styleRight.setProperty(Button.PROPERTY_ALIGNMENT, new Alignment(Alignment.RIGHT,Alignment.TOP));
		
		if (rl_angebot!=null) {
			for (int i=0;i<rl_angebot.get_vKeyValues().size();i++) {
				MyRECORD rec = rl_angebot.get_(rl_angebot.get_vKeyValues().get(i));
				RB_ResultButton[] res_zeile = new RB_ResultButton[9];
				
				
				Vector<String> sort_texte = new Vector<String>();
				sort_texte.add(rec.ufs("TYP"));
				sort_texte.add(rec.ufs("TYP")+rec.ufs("NAME","-"));
				sort_texte.add(rec.ufs("TYP")+rec.ufs("ORT","-"));
				sort_texte.add(rec.ufs("TYP")+rec.ufs("ANR1_2"));
				sort_texte.add(rec.ufs("TYP")+rec.ufs("ARTBEZ1"));
				sort_texte.add(rec.ufs("TYP")+rec.ufs("ANZAHL"));
				sort_texte.add(rec.ufs("TYP")+rec.ufs("EINZELPREIS"));
				sort_texte.add(rec.ufs("TYP")+rec.ufs("GUELTIG_VON"));
				sort_texte.add(rec.ufs("TYP")+rec.ufs("GUELTIG_BIS"));
				
				RECORD_VPOS_STD rec_vp = new RECORD_VPOS_STD(rec.l(VPOS_STD.id_vpos_std));
				res_zeile[0]= new own_result_button(rec_vp, rec.fs("TYP","-"),			style,sort_texte.get(0));
				res_zeile[1]= new own_result_button(rec_vp, rec.fs("NAME","-"),		style,sort_texte.get(1));
				res_zeile[2]= new own_result_button(rec_vp, rec.fs("ORT","-"),			style,sort_texte.get(2));
				res_zeile[3]= new own_result_button(rec_vp, rec.fs("ANR1_2","-"),		style,sort_texte.get(3));
				res_zeile[4]= new own_result_button(rec_vp, rec.fs("ARTBEZ1","-"),		style,sort_texte.get(4));
				res_zeile[5]= new own_result_button(rec_vp, rec.fs("ANZAHL","-"),		styleRight,sort_texte.get(5));
				res_zeile[6]= new own_result_button(rec_vp, rec.fs("EINZELPREIS","-"),	styleRight,sort_texte.get(6));
				res_zeile[7]= new own_result_button(rec_vp, rec.fs("GUELTIG_VON","-"),	styleRight,sort_texte.get(7));
				res_zeile[8]= new own_result_button(rec_vp, rec.fs("GUELTIG_BIS","-"),	styleRight,sort_texte.get(8));

				v_result_arrays.add(res_zeile);
				
			}
		}
		

		if (rl_kontrakt!=null) {
			for (int i=0;i<rl_kontrakt.get_vKeyValues().size();i++) {
				MyRECORD rec = rl_kontrakt.get_(rl_kontrakt.get_vKeyValues().get(i));
				RB_ResultButton[] res_zeile = new RB_ResultButton[9];
				
			
				Vector<String> sort_texte = new Vector<String>();
				sort_texte.add(rec.fs("TYP"));
				sort_texte.add(rec.fs("TYP")+rec.fs("NAME","-"));
				sort_texte.add(rec.fs("TYP")+rec.fs("ORT","-"));
				sort_texte.add(rec.fs("TYP")+rec.fs("ANR1_2","-"));
				sort_texte.add(rec.fs("TYP")+rec.fs("ARTBEZ1","-"));
				sort_texte.add(rec.fs("TYP")+rec.fs("ANZAHL","-"));
				sort_texte.add(rec.fs("TYP")+rec.fs("EINZELPREIS","-"));
				sort_texte.add(rec.fs("TYP")+rec.fs("GUELTIG_VON","-"));
				sort_texte.add(rec.fs("TYP")+rec.fs("GUELTIG_BIS","-"));
				
				RECORD_VPOS_KON rec_vp = new RECORD_VPOS_KON(rec.l(VPOS_KON.id_vpos_kon));
				res_zeile[0]= new own_result_button(rec_vp, rec.fs("TYP","-"),			style,		sort_texte.get(0));
				res_zeile[1]= new own_result_button(rec_vp, rec.fs("NAME","-"),		style,		sort_texte.get(1));
				res_zeile[2]= new own_result_button(rec_vp, rec.fs("ORT","-"),			style,		sort_texte.get(2));
				res_zeile[3]= new own_result_button(rec_vp, rec.fs("ANR1_2","-"),		style,		sort_texte.get(3));
				res_zeile[4]= new own_result_button(rec_vp, rec.fs("ARTBEZ1","-"),		style,		sort_texte.get(4));
				res_zeile[5]= new own_result_button(rec_vp, rec.fs("ANZAHL","-"),		styleRight,	sort_texte.get(5));
				res_zeile[6]= new own_result_button(rec_vp, rec.fs("EINZELPREIS","-"),	styleRight,	sort_texte.get(6));
				res_zeile[7]= new own_result_button(rec_vp, rec.fs("GUELTIG_VON","-"),	styleRight,	sort_texte.get(7));
				res_zeile[8]= new own_result_button(rec_vp, rec.fs("GUELTIG_BIS","-"),	styleRight,	sort_texte.get(8));
				
				v_result_arrays.add(res_zeile);
				
			}
		}

		
		return mv;
	}

	@Override
	public void fill_grid_4_popup(MyE2_Grid grid_4_popup, RB_ResultButtonArrays vektor_buttons, ExecINFO oExecInfo) throws myException {
		
		E2_Grid4MaskSimple gs = new E2_Grid4MaskSimple(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		Vector<Component>  v_buttons = new Vector<Component>();
		v_buttons.add(new MyE2_Label(new MyE2_String("Typ")));
		v_buttons.add(new RB_SearchFieldListSortButton(this,1,  new MyE2_String("Lieferant"),null));
		v_buttons.add(new RB_SearchFieldListSortButton(this,2,  new MyE2_String("Ort"),null));
		v_buttons.add(new RB_SearchFieldListSortButton(this,3,  new MyE2_String("Sorte"),null));
		v_buttons.add(new RB_SearchFieldListSortButton(this,4,  new MyE2_String("Sortenbezeichnung"),null));
		v_buttons.add(new RB_SearchFieldListSortButton(this,5,  new MyE2_String("Menge"),null));
		v_buttons.add(new RB_SearchFieldListSortButton(this,6,  new MyE2_String("Preis"),null));
		v_buttons.add(new RB_SearchFieldListSortButton(this,7,  new MyE2_String("Gültig ab"),null));
		v_buttons.add(new RB_SearchFieldListSortButton(this,8,  new MyE2_String("Gültig bis"),null));
		
		Integer     i_actual_sort_col = this.get_rb_ResultButtonArray().get_actual_sort_col();
		SORTSTATUS  status_actual =  this.get_rb_ResultButtonArray().get_actual_sort_status();
 
		//sortierstatus beruecksichtigen
		if (i_actual_sort_col!=null && i_actual_sort_col>=0 &&	i_actual_sort_col<9 && status_actual!=null) {
			Component comp = v_buttons.get(i_actual_sort_col);
			if (comp instanceof RB_SearchFieldListSortButton) {
				((RB_SearchFieldListSortButton)comp).set_sortstatus_actual(status_actual);
			}
		}
		
		gs  .def_(new E2_ColorDark()).def_(new Alignment(Alignment.LEFT,Alignment.TOP))
			.add_(v_buttons.get(0))
			.add_(v_buttons.get(1))
			.add_(v_buttons.get(2))
			.add_(v_buttons.get(3))
			.add_(v_buttons.get(4)).def_(new Alignment(Alignment.RIGHT,Alignment.TOP))
			.add_(v_buttons.get(5))
			.add_(v_buttons.get(6))
			.add_(v_buttons.get(7))
			.add_(v_buttons.get(8))
			.def_(new E2_ColorBase());
		
		for (RB_ResultButton_IF[] arr: vektor_buttons) {
			gs		.def_(new Alignment(Alignment.LEFT,Alignment.TOP))
					.add_(arr[0].me())
					.add_(arr[1].me())
					.add_(arr[2].me())
					.add_(arr[3].me())
					.add_(arr[4].me()).def_(new Alignment(Alignment.RIGHT,Alignment.TOP))
					.add_(arr[5].me())
					.add_(arr[6].me())
					.add_(arr[7].me())
					.add_(arr[8].me());
		}
		gs._setSize(30,150,120,80,180,80,80,80,80);
	
		grid_4_popup.removeAll();
		grid_4_popup.add(gs,E2_INSETS.I(0,0,0,0));
	}

	@Override
	public String get_result_string_from_record(MyRECORD_IF_RECORDS p_result_record) throws myException {

		return null;
	}

	
	private class ownBasicModuleContainer4Popup extends E2_BasicModuleContainer {
	}
	
	
	
	private class own_result_button extends RB_ResultButton {
		
		private String sort_string = null;

		public own_result_button(	MyRECORD_IF_RECORDS 	p_result_record, 
									String 					cText,		
									E2_MutableStyle 		oStyle,
									String 					sortstring) throws myException {
			super(FZ_MASK_Search_Kontrakt_Angebot_in_ONE.this, p_result_record, cText, oStyle);
			this.sort_string = sortstring;
			this.add_oActionAgent(new ownResultButtonAction(p_result_record));
			
		}
		
		@Override
		public String get_sort_string() throws myException {
			return this.sort_string;
		}

		@Override
		public Component me() throws myException {
			return this;
		}
	}

	
	private class ownResultButtonAction extends XX_ActionAgent {

		private MyRECORD_IF_RECORDS 	result_record  = 		null;
	
		public ownResultButtonAction(MyRECORD_IF_RECORDS p_result_record) {
			super();
			this.result_record = p_result_record;
		}



		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			FZ_MASK_Search_Kontrakt_Angebot_in_ONE oThis = FZ_MASK_Search_Kontrakt_Angebot_in_ONE.this;

			String id_kon = "";
			String id_ang = "";
			if (this.result_record instanceof RECORD_VPOS_KON) {
				RECORD_VPOS_KON  rk = (RECORD_VPOS_KON) this.result_record;
				id_kon = rk.ufs(VPOS_KON.id_vpos_kon);
				oThis.get_tf_search_input().setText(id_kon);
				oThis.set_c_vergleichswert_dbfeld(id_kon);
				oThis.tf_kontrakt.setText(id_kon);
			} else {
				RECORD_VPOS_STD rk = (RECORD_VPOS_STD) this.result_record;
				id_ang = rk.ufs(VPOS_STD.id_vpos_std);
				oThis.get_tf_search_input().setText(id_ang);
				oThis.set_c_vergleichswert_dbfeld(id_ang);
				oThis.tf_angebot.setText(id_ang);
			}
			
			oThis.tf_kontrakt.setText(id_kon);
			oThis.tf_angebot.setText(id_ang);
			
			oThis.get_container_4_popupWindow().CLOSE_AND_DESTROY_POPUPWINDOW(true);
			
			String _render_help_string = S.isFull(id_kon)?"KON@"+id_kon:"ANG@"+id_ang;
			
			oThis.render_search_result_visible_on_mask(oThis.get_gridContainer_to_show_searchResult(), _render_help_string);

			
			MyE2_MessageVector mv = oThis.do_mask_settings_after_search(_render_help_string,true);
			if (mv != null) {
				bibMSG.add_MESSAGE(mv);
			}
			
			
		}

	}
	
	
	
	private class ownSearchStartValidator extends XX_ActionValidator_NG {

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			
			FZ_MASK_Search_Kontrakt_Angebot_in_ONE oThis = FZ_MASK_Search_Kontrakt_Angebot_in_ONE.this;
			
			
			if (oThis.get_date4fuhre()==null || oThis.get_id_adresse_station_in_search_moment()==null) {
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte zuerst das Datum der Warenbewegung und die Station angeben !")));
			}
			return mv;
		}
		
	}
	
	
	private MyDate get_date4fuhre() throws myException {
		//welches datum
		RB_date_selektor dateSel = (RB_date_selektor)this.rb_ComponentMap_this_belongsTo().getRbComponent(BEWEGUNG_ATOM.leistungsdatum);

		if (dateSel != null) {
			if (S.isFull(dateSel.rb_readValue_4_dataobject())) {
				MyDate date = new MyDate(dateSel.rb_readValue_4_dataobject());
				if (date.get_bOK()) {
					return date;
				}
			}
		}
		return null;
	}



	
	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {

		this.tf_kontrakt.setText("");
		this.tf_angebot.setText("");
		
		String anzeigeWertKombiFeld = ""; 
		
		//hier werden die eigentlichen datenfelder gefuellt
		if (dataObject.get_RecORD()!=null) {
			//dann nachsehen, was voll ist, kontrakt oder angebot (nur eins kann ungleich null sein)
			this.tf_kontrakt.setText(  ((MyRECORD)dataObject.get_RecORD()).ufs(BEWEGUNG_ATOM.id_vpos_kon,""));
			this.tf_angebot.setText(  ((MyRECORD)dataObject.get_RecORD()).ufs(BEWEGUNG_ATOM.id_vpos_std,""));
		}

		anzeigeWertKombiFeld = S.isFull(this.tf_kontrakt.getText())?this.tf_kontrakt.getText():S.NN(this.tf_angebot.getText());
		
		this.refresh_kontrakt_or_angebot_label();
		
		this.get_tf_search_input().setText(anzeigeWertKombiFeld);
		this.set_c_vergleichswert_dbfeld(anzeigeWertKombiFeld);
		
		String _render_help_string =S.isFull(this.tf_kontrakt.getText())?"KON@"+this.tf_kontrakt.getText():"ANG@"+S.NN(this.tf_angebot.getText());
		this.render_search_result_visible_on_mask(this.get_gridContainer_to_show_searchResult(), _render_help_string);

	}

	@Override
	public void render_search_result_visible_on_mask(E2_Grid grid_result_container, String c_result_value_4_db) throws myException {
		
		//jetzt den string auseinandernehmen
		String c_id_angebot = "";
		String c_id_kontrakt = "";
		
		if(S.isFull(c_result_value_4_db)) {
		
			if (c_result_value_4_db.startsWith("KON@")) {
				c_id_kontrakt = c_result_value_4_db.substring(4);
			} else if (c_result_value_4_db.startsWith("ANG@")) {
				c_id_angebot = c_result_value_4_db.substring(4);
			}
		}

		MyLong l_angebot = new MyLong(c_id_angebot);
		MyLong l_kontrakt = new MyLong(c_id_kontrakt);
		
		if (l_angebot.get_bOK()) {
			new RECORD_VPOS_STD_ext(new RECORD_VPOS_STD(l_angebot.get_lValue())).fill_grid_with_infos(grid_result_container);
		} else if (l_kontrakt.get_bOK()) {
			new RECORD_VPOS_KON_ext(new RECORD_VPOS_KON(l_kontrakt.get_lValue())).fill_grid_with_infos(grid_result_container);
		} else {
			grid_result_container.removeAll();
		}
		
	}


	
	public void refresh_kontrakt_or_angebot_label() {
		this.get_grid4Label_kontrakt_oder_angebot().removeAll();
		this.get_grid4Label_kontrakt_oder_angebot().add(this.get_actual_label(), new RB_gld()._ins(E2_INSETS.I(0,0,0,0)));
	}
	
	
	
	
	public MyE2_Label get_actual_label() {
		MyE2_Label lab = this.labelempty;
		
		if (S.isFull(this.tf_kontrakt.getText())) {
			lab = this.labelKon;
		}
		if (S.isFull(this.tf_angebot.getText())) {
			lab = this.labelAng;
		}
		
		return lab;
	}



	
	public MyE2_Grid get_grid4Label_kontrakt_oder_angebot() {
		return this.grid4Label_kontrakt_oder_angebot;
	}





//	@Override
//	public MyRECORD_IF_RECORDS get_result_record_from_string(String unformated_MaskValue) throws myException {
//		//hier keine direkte rueckgabe, da es entweder ein RECORD_VPOS_KON oder ein RECORD_VPOS_STD ist 
//		return null;
//	}


	
	public MyE2_Label get_labelKon() {
		return this.labelKon;
	}

	
	public MyE2_Label get_labelAng() {
		return this.labelAng;
	}
	
	public MyE2_Label get_labelempty() {
		return this.labelempty;
	}
	
	
	public void clear_component() throws myException {
		this.get_tf_search_input().setText("");
		this.set_c_vergleichswert_dbfeld("");
		this.get_gridContainer_to_show_searchResult()._clear();
		this.tf_kontrakt.setText("");
		this.tf_angebot.setText("");
		this.refresh_kontrakt_or_angebot_label();
	}

	public SEARCH_EK_OR_VK get_typ() {
		return searchTyp;
	}
	
}
