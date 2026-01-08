/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE
 * @author manfred
 * @date 20.09.2017
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE;

import java.util.Vector;

import org.apache.commons.lang.StringUtils;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.SearchDialog.SearchDialog_ResultButton;
import panter.gmbh.Echo2.RB.COMP.SearchDialog.SearchDialog_ResultButton_IF;
import panter.gmbh.Echo2.RB.COMP.SearchDialog.SearchDialog_ResultButton_action;
import panter.gmbh.Echo2.RB.COMP.SearchDialog.SearchDialog_Base;
import panter.gmbh.Echo2.RB.COMP.SearchDialog.SearchDialog_vecResultButtons;
import panter.gmbh.Echo2.RB.COMP.SearchDialog.RECLIST_MyRecord;
import panter.gmbh.Echo2.RB.COMP.SearchDialog.SearchDialog_ResultColumn;
import panter.gmbh.Echo2.RB.COMP.SearchDialog.SearchDialog_SelectorEntry;
import panter.gmbh.Echo2.RB.COMP.SearchDialog.SearchDialog_Selector_Date;
import panter.gmbh.Echo2.RB.COMP.SearchDialog.SearchDialog_Selector_SelectField;
import panter.gmbh.Echo2.RB.COMP.SearchDialog.SearchDialog_Selector_TextField;
import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.WAAGE_STANDORT;
import panter.gmbh.basics4project.DB_ENUMS.WAEGUNG;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSQL;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.MyResultValue;
import panter.gmbh.indep.dataTools.TERM.Nvl;
import panter.gmbh.indep.dataTools.TERM.TermLMR;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.TermStringConcatenatorSql;
import panter.gmbh.indep.dataTools.TERM._TermCONST;
import panter.gmbh.indep.dataTools.TERM._TermCONST.ATTRIBUTES;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author manfred
 * @date 20.09.2017
 *
 */
public class WK_SearchDialogWiegekarte extends SearchDialog_Base {

	public static enum ENUM_FIELDLIST {
		
		ID,
		WEWA,
		KUNDE,
		TRANSPORTKENNZEICHEN,
		SORTE,
		LAGER,
		TYP_WIEGEKARTE,
		GEWICHT,
		DATUM,
		STANDORT
	}

	
	public static enum ENUM_SELEKTORLIST {
		WEWA,
		KUNDE,
		KENNZEICHEN,
		LAGER,
		SORTE,
		DATUM_AB,
		DATUM_BIS
	}
	

//	String sViewName;
//	String sColPraefix ;
	
	String colID ;
	String colWEWA ;
	String colKunde;
	String colKennzeichen;
	String colSorte;
	String colLager;
	String colTypWK;
	String colGewicht;
	String colDatum;
	String colStandort;
	
	String _title = "Suche nach der zugehörigen Wiegekarte";
	
	/**
	 * @author manfred
	 * @date 20.09.2017
	 *
	 * @throws myException
	 */
	public WK_SearchDialogWiegekarte() throws myException {
		super();
		set_title_of_popup(new MyE2_String(_title));
		_init();
	}
	

	private void _init() throws myException {
		colID = WIEGEKARTE.id_wiegekarte.tnfn() ;
		
		colWEWA = "CASE nvl(" + WIEGEKARTE.ist_lieferant.tnfn() + ",'N') WHEN to_nchar('Y') THEN 'WE' ELSE 'WA' END";
		
		colKunde = " CASE WHEN " + WIEGEKARTE.id_adresse_lieferant.tnfn() + " IS NOT NULL then " +
				   " ( SELECT " + ADRESSE.name1.t("A").s() + " || ' ' || " + ADRESSE.name2.t("A").s() + " ||' ' || " + ADRESSE.name3.t("A").s() + " ||' ' || " + ADRESSE.plz.t("A").s() + " || ' ' || " + ADRESSE.ort.t("A").s() + " ||' ('  ||" + ADRESSE.id_adresse.t("A").s() + "|| ')' FROM " + ADRESSE.T("A").s() + " wHERE " + ADRESSE.id_adresse.t("A").s() + " = " + WIEGEKARTE.id_adresse_lieferant.tnfn() +  
			       "  ) ELSE REPLACE( " + WIEGEKARTE.adresse_lieferant.tnfn() + ", chr(10), ' ' ) END " ;  
				
		colKennzeichen = WIEGEKARTE.kennzeichen.tnfn();
		
//		colLager = new SEL(_TAB.adresse).ADDFIELD(
//								new TermStringConcatenatorSql(" ", 
//										ADRESSE.name1.tnfn(), 
//										ADRESSE.name2.tnfn(), 
//										ADRESSE.name3.tnfn() ,
//										ADRESSE.plz.tnfn(), 
//										ADRESSE.ort.tnfn(),
//										"(",ADRESSE.id_adresse.tnfn() ,")" )
//								)
//						.WHERE(new vgl(ADRESSE.id_adresse,WIEGEKARTE.id_adresse_lager.tnfn()))	.s();
		
		
		colStandort = "(" + new SEL().FROM(_TAB.waage_standort) .ADDFIELD(WAAGE_STANDORT.standort).WHERE(new TermLMR(WAAGE_STANDORT.id_waage_standort, _TermCONST.COMP.EQ.s() ,WIEGEKARTE.id_waage_standort.tnfn())).s() + ")";
		
		colSorte =   "(" + new SEL().FROM(_TAB.artikel_bez).INNERJOIN(_TAB.artikel, ARTIKEL_BEZ.id_artikel, ARTIKEL.id_artikel)
								.ADDFIELD(
										new TermStringConcatenatorSql(" ", 
													ARTIKEL.anr1.tnfn() + "|| '-' || " + ARTIKEL_BEZ.anr2.tnfn(),
													ARTIKEL_BEZ.artbez1.tnfn(), 
													"'('||" + ARTIKEL_BEZ.id_artikel.tnfn()+"||'#'||" +ARTIKEL_BEZ.id_artikel_bez.tnfn() + "||')'") 
										)
								.WHERE(WIEGEKARTE.id_artikel_bez,ARTIKEL_BEZ.id_artikel_bez).s() + ")" ;
		
		
		colTypWK 	= " DECODE(" + WIEGEKARTE.typ_wiegekarte.tnfn() +" , 'W','Wiegeschein','S','Strecke','F','Fremdwiegung','LG','Lager', 'RE','Retoure','LE','Leerfuhre','D','Dokumentarisch','WL','Wiegekarte/Lieferschein','n/a') ";
		
		colGewicht 	= WIEGEKARTE.gewicht_nach_abzug_fuhre.tnfn();
		
		colDatum    = "(" + new SEL().FROM(_TAB.waegung).ADDFIELD(new FieldTerm(ATTRIBUTES.MIN,WAEGUNG.datum))
						.WHERE(WAEGUNG.id_wiegekarte, WIEGEKARTE.id_wiegekarte)	.s() + ")";
		
		
		//
		// Spalten des Ergebnis-Grids definieren, die Zuordnung wird über den _colName (2. Param) durchgeführt
		//
		
		/**
		ID_WIEGEKARTE,
		WEWA,
		KUNDE,
		TRANSPORTKENNZEICHEN,
		SORTE,
		LAGER,
		TYP_WIEGEKARTE,
		GEWICHT,
		DATUM
		 */
		
		vecColumnList.clear();
		vecColumnList.add( new SearchDialog_ResultColumn( "ID Wiegekarte",ENUM_FIELDLIST.ID.name(),true,30));
		vecColumnList.add( new SearchDialog_ResultColumn( "WA/WE",ENUM_FIELDLIST.WEWA.name(),false,30));
		vecColumnList.add( new SearchDialog_ResultColumn( "Kennzeichen",ENUM_FIELDLIST.TRANSPORTKENNZEICHEN.name(),false,100));
		vecColumnList.add( new SearchDialog_ResultColumn( "Kunde",ENUM_FIELDLIST.KUNDE.name(),false,250));
		vecColumnList.add( new SearchDialog_ResultColumn( "Sorte",ENUM_FIELDLIST.SORTE.name(),false,250));
		vecColumnList.add( new SearchDialog_ResultColumn( "Typ Wiegekarte",ENUM_FIELDLIST.TYP_WIEGEKARTE.name(),false,80));
		vecColumnList.add( new SearchDialog_ResultColumn( "Gewicht",ENUM_FIELDLIST.GEWICHT.name(),false,50).set_AlignmentData(new Alignment(Alignment.RIGHT,Alignment.TOP)).set_AlignmentHeader(new Alignment(Alignment.RIGHT,Alignment.TOP)));
		vecColumnList.add( new SearchDialog_ResultColumn( "Datum",ENUM_FIELDLIST.DATUM.name(),false,50).set_AlignmentData(new Alignment(Alignment.RIGHT,Alignment.TOP)).set_AlignmentHeader(new Alignment(Alignment.RIGHT,Alignment.TOP)) );
		
		
		// 
		// Selektoren
		// Falls die selektoren auf komplexe Spalten gemacht werden, müssen die Term-Cols genau den Select-Spalten entsprechen. Deshalb werden die Spalten in Stringvariablen definiert.
		// 
		SearchDialog_SelectorEntry selEntry ;

		// Kundenname
		this.selections_list.addSelectorEntry(
				new SearchDialog_SelectorEntry(ENUM_SELEKTORLIST.KUNDE.name())
				.set_Description("Kunde")
				.set_Active(false)
				.set_valueComponent(new SearchDialog_Selector_TextField("",200,100))
				.setTermList(new And(new TermLMR( new TermSimple("UPPER(" + colKunde + ")"), _TermCONST.COMP.LIKE.s(), "UPPER('%#WERT#%')")))
				);
		
		
		// Auswahl WE/WA
		String[][] arrSelect = {{"Wareneingang","WE"},	{"Warenausgang","WA"}};
		SearchDialog_Selector_SelectField oSelWEWA = new SearchDialog_Selector_SelectField(arrSelect, "" , false, new Extent(150));
		selEntry = new SearchDialog_SelectorEntry(ENUM_SELEKTORLIST.WEWA.name())
								.set_Description("Vorgang ist")
								.set_Active(false)
								.set_valueComponent(oSelWEWA)
								.setTermList(new And( new TermLMR(new TermSimple(colWEWA),_TermCONST.COMP.EQ.s(),"'#WERT#'")));
		this.selections_list.addSelectorEntry(selEntry);
		
		
		// Kennzeichen
		this.selections_list.addSelectorEntry(
				new SearchDialog_SelectorEntry(ENUM_SELEKTORLIST.KENNZEICHEN.name())
					.set_Description("Kennzeichen")
					.set_Active(false)
					.set_valueComponent(new SearchDialog_Selector_TextField("",200,100) ) 
					.setTermList(new And(new TermLMR( new TermSimple("UPPER(" + colKennzeichen + ")"), _TermCONST.COMP.LIKE.s(), "UPPER('%#WERT#%')")) )
				);
		
		// Sorte
		this.selections_list.addSelectorEntry(
				new SearchDialog_SelectorEntry(ENUM_SELEKTORLIST.SORTE.name())
					.set_Description("Sorte")
					.set_Active(false)
					.set_valueComponent(new SearchDialog_Selector_TextField("",200,100)) 
					.setTermList(new And(new TermLMR( new TermSimple("UPPER(" + colSorte + ")"), _TermCONST.COMP.LIKE.s(), "UPPER('%#WERT#%')"))) 
				);
		
		
		
		// Datum von
		this.selections_list.addSelectorEntry(
				new SearchDialog_SelectorEntry(ENUM_SELEKTORLIST.DATUM_AB.name())
					.set_Description("Datum von")
					.set_Active(false)
					.set_valueComponent(new SearchDialog_Selector_Date("",100,true,true)) 
					.setTermList(new And(new TermLMR( new TermSimple( colDatum ), _TermCONST.COMP.GE.s(), "to_date('#WERT#','YYYY-MM-DD')"))) 
				);
		
		// Datum von
		this.selections_list.addSelectorEntry(
				new SearchDialog_SelectorEntry(ENUM_SELEKTORLIST.DATUM_BIS.name())
					.set_Description("Datum bis")
					.set_Active(false)
					.set_valueComponent(new SearchDialog_Selector_Date("",100,true,true)) 
					.setTermList(new And(new TermLMR( new TermSimple( colDatum ), _TermCONST.COMP.LE.s(), "to_date('#WERT#','YYYY-MM-DD')"))) 
				);

		
		
		// speicherstatus hinzufuegen
		this.set_key_4_save_sorting(ENUM_USER_SAVEKEY.KEY_SAVE_SORTING_SEARCHDIALOG_WIEKGEARTE);
		
	}
	
	
	
	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.SearchDialog.RB_SearchDialogBase#execute_searchquery_and_fill_resultbutton_array()
	 */
	@Override
	protected MyE2_MessageVector execute_searchquery_and_fill_resultbutton_array() throws myException {
		
		Vector<String>      v_wheres = new Vector<String>();
		
		SEL sel = new SEL()
				.ADDFIELD( colID,ENUM_FIELDLIST.ID.name())
				.ADDFIELD( "nvl(" + colWEWA + ",'-')", ENUM_FIELDLIST.WEWA.name())
				.ADDFIELD( colKunde, ENUM_FIELDLIST.KUNDE.name() ) 
				.ADDFIELD( colGewicht, ENUM_FIELDLIST.GEWICHT.name() ) 
				.ADDFIELD( colSorte, ENUM_FIELDLIST.SORTE.name() )
				.ADDFIELD( "nvl(" + colKennzeichen + ",'-') ", ENUM_FIELDLIST.TRANSPORTKENNZEICHEN.name())
				.ADDFIELD(  colStandort , ENUM_FIELDLIST.STANDORT.name() )
				.ADDFIELD( colTypWK, ENUM_FIELDLIST.TYP_WIEGEKARTE.name() )
				.ADDFIELD( colDatum, ENUM_FIELDLIST.DATUM.name() )
				
				.FROM(_TAB.wiegekarte)
				;

		v_wheres.add(" WHERE 1=1 ");
		v_wheres.add( new And( new TermLMR( new Nvl(new TermSimple(WIEGEKARTE.storno.fn()),"'N'"),_TermCONST.COMP.EQ.s(),"'N'") ).s() ) ;
		
		
		v_wheres.add(" " + this.getSelections_list().getStatement() + " " );
		String sql_where = bibALL.Concatenate(v_wheres, " AND ", "1=1");  //suchanteil und zusatzbedingungen
		RECLIST_MyRecord rl = null;


		String c_sql_query = sel.s()  + sql_where;
		
		DEBUG.System_println(c_sql_query,DEBUG.DEBUG_FLAGS.DIVERS1.name());

		if (this.get_iMaxResults()>0) {
			rl = new RECLIST_MyRecord(c_sql_query,ENUM_FIELDLIST.ID.name(),this.get_iMaxResults()+1,true);
			if (rl.size() > this.get_iMaxResults()){
				MyE2_Warning_Message msg = new MyE2_Warning_Message(String.format("Das Ergebnis der Suche hat mehr als %d Zeilen. Bitte schränken Sie die Suche ein!",this.get_iMaxResults()) );
				bibMSG.add_MESSAGE(msg);
			}
		} else {
			rl = new RECLIST_MyRecord(c_sql_query,ENUM_FIELDLIST.ID.name());
		}
		
		SearchDialog_vecResultButtons  v_result_arrays = this.get_rb_vecResultButtons();
		v_result_arrays.clear();
		
		int cols = vecColumnList.size(); 
		
		for (int i=0;i<rl.size();i++) {
			String sort = "";

			MyRECORD rec = rl.get(i);
			SearchDialog_ResultButton[] res_zeile = new SearchDialog_ResultButton[cols];
			
			// Spalte Erzeugen
			String rowID = rec.get(ENUM_FIELDLIST.ID.name()).get_FieldValueUnformated();
			
			for(int j=0; j < vecColumnList.size();j++){
				SearchDialog_ResultColumn col = vecColumnList.get(j);
				
				MyResultValue f = rec.get(col.get_ColName());
				boolean bIsNumber = f.get_MetaFieldDef().get_bIsNumberTypeWithDecimals() || f.get_MetaFieldDef().get_bIsNumberTypeWithOutDecimals();

				String sValueUnformatted =  rec.get_UnFormatedValue(col.get_ColName());
				String sValueFormatted =   rec.get_FormatedValue(col.get_ColName(),"") ;

				if (f != null){
					String sValue = "" + (sValueUnformatted != null ? sValueUnformatted : sValueFormatted );
					if (col.isPK() || bIsNumber ) {
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

	
	
	
	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.SearchDialog.RB_SearchDialogBase#fill_grid_ResultSet(panter.gmbh.Echo2.components.MyE2_Grid, panter.gmbh.Echo2.RB.COMP.SearchDialog.RB_vecResultButtons)
	 */
	@Override
	public void fill_grid_ResultSet(MyE2_Grid grid_4_popup, SearchDialog_vecResultButtons vektor_buttons) throws myException {
		// Header erzeugen
		E2_Grid4MaskSimple gs = this.initResultGrid();
		
		// build GridRows
		gs.def_(new E2_ColorBase(-30));
		gs._align(new Alignment(Alignment.LEFT,Alignment.TOP));
		int cols = 1;
		
		
		SearchDialog_ResultButton rb;
		for (SearchDialog_ResultButton_IF[] arr: vektor_buttons) {
			cols = arr.length;

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

	
	
	
	@Override
	public void fill_grid_Selector(E2_Grid grid_4_searchParameter) throws myException {
		// Aufbau 
		grid_4_searchParameter._clear();
		grid_4_searchParameter._bo_dd();
		grid_4_searchParameter._ins(new Insets(10, 5, 5, 5));
		grid_4_searchParameter._s(1);
		grid_4_searchParameter._a(_title);
		
		// Anzeigen der definierten Selektoren
		grid_4_searchParameter._add_raw(getSelections_list().getGrid());

		
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.SearchDialog.RB_SearchDialogBase#get_result_string_from_record(panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS)
	 */
	@Override
	public String get_result_string_from_record(MyRECORD_IF_RECORDS p_result_record) throws myException {
		return null;
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.SearchDialog.RB_SearchDialogBase#get_result_record_from_string(java.lang.String)
	 */
	@Override
	public MyRECORD_IF_RECORDS get_result_record_from_string(String unformated_MaskValue) throws myException {
		return null;
	}

}
