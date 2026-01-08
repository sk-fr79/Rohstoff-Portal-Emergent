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
import panter.gmbh.Echo2.bibE2;
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
import panter.gmbh.Echo2.RB.COMP.SearchDialog.SearchDialog_SelectorList;
import panter.gmbh.Echo2.RB.COMP.SearchDialog.SearchDialog_Selector_Date;
import panter.gmbh.Echo2.RB.COMP.SearchDialog.SearchDialog_Selector_SelectField;
import panter.gmbh.Echo2.RB.COMP.SearchDialog.SearchDialog_Selector_TextField;
import panter.gmbh.Echo2.RB.COMP.SearchDialog.SearchDialog_Example.ENUM_SELEKTOR_TEST;
import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS.USERSETTINGS;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
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
import panter.gmbh.indep.dataTools.query.Term;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author manfred
 * @date 20.09.2017
 *
 */
public class WK_SearchDialogFuhre extends SearchDialog_Base {

	public static enum ENUM_FIELDLIST {
		ID_GESAMT,
		ID_FUHRE,
		ID_FUHRE_ORT,
		BUCHUNGSNR_FUHRE,
		WEWA,
		TRANSPORTKENNZEICHEN,
		NAME,
		ADRESSE,
		LIEFERADRESSE,
		SORTE,
		DATUM,
		GEWICHT
	}
	
	public static enum ENUM_SELEKTORLIST {
		WEWA,
		ID_ADRESSE,
		BUCHUNGSNR_FUHRE,
		NAME,
		ORT,
		SORTE,
		KENNZEICHEN,
		DATUM_AB,
		DATUM_BIS
	}
	
	private String _idLager;

	String sViewName;
	String sColPraefix ;
	
	String colID ;
	String colWEWA ;
	String colName;
	String colAdresse;
	String colBuchungsnr;
	String colSorte;
	String colKennzeichen;
	String colHauptadresse;
	String colDatum;
	String colGewicht;
	String _title = "Suche nach der zugehörigen Fuhre";
	
	/**
	 * @author manfred
	 * @date 20.09.2017
	 *
	 * @throws myException
	 */
	public WK_SearchDialogFuhre(String idLager) throws myException {
		super();
		_idLager = idLager;
		
		set_title_of_popup(new MyE2_String(_title));
		_init();
	}
	

	private void _init() throws myException {
		sViewName = "V" + bibALL.get_ID_MANDANT().trim() + "_FUHREN";
		sColPraefix = "V.";
		
		// ID ergibt sich aus id_fuhre || id_fuhre_ort
		// komplexe Spalten-Terme braucht man für die WHERE-Bedingung sowie den SELECT
		
		
		
		colID = sColPraefix + VPOS_TPA_FUHRE.id_vpos_tpa_fuhre.fn() + "||'#'||" + VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre_ort.fn();
		colWEWA = "CASE " + sColPraefix +  "ID_ADRESSE_LAGER_ZIEL WHEN " + _idLager + " THEN 'WE' ELSE CASE " + sColPraefix +  "ID_ADRESSE_LAGER_START WHEN " + _idLager + "  then 'WA' ELSE null END END";
		colName = "CASE  " + sColPraefix +  "ID_ADRESSE_LAGER_START WHEN " + _idLager + " THEN " + sColPraefix +  "A_NAME1 || ' ' || " + sColPraefix +  "A_NAME2 || ' ' || " + sColPraefix +  "A_NAME3 ELSE " + sColPraefix +  "L_NAME1 || ' ' || " + sColPraefix +  "L_NAME2 || ' ' || " + sColPraefix +  "L_NAME3 END";
		colAdresse = "CASE  " + sColPraefix +  "ID_ADRESSE_LAGER_START WHEN " + _idLager + " THEN " + sColPraefix +  "A_PLZ ||  ' ' ||   " + sColPraefix +  "A_ORT || ', ' || " + sColPraefix +  "A_STRASSE ||  ' ' || " + sColPraefix +  "A_HAUSNUMMER || '(' || " + sColPraefix +  "ID_ADRESSE_LAGER_ZIEL || ')'   ELSE " + sColPraefix +  "L_PLZ ||  ' ' ||   " + sColPraefix +  "L_ORT || ', ' || " + sColPraefix +  "L_STRASSE ||  ' ' || " + sColPraefix +  "L_HAUSNUMMER  || '(' || " + sColPraefix +  "ID_ADRESSE_LAGER_START || ')'   END";
		
		colHauptadresse =" CASE WHEN " + sColPraefix +  "ID_ADRESSE_LAGER_START = " + _idLager + " AND " + sColPraefix +  "ID_ADRESSE_LAGER_ZIEL != " + sColPraefix +  "ID_ADRESSE_ZIEL " +  
			  	" THEN  (SELECT  A.NAME1 || ' ' || NVL2( A.NAME2, ' ' ||  A.NAME2,'') || NVL2( A.NAME3, ' ' || A.NAME3, '') ||  ', ' ||   A.PLZ||  ' ' ||  A.ORT || ', ' || NVL( A.STRASSE,'') || NVL2( A.HAUSNUMMER,' ' ||  A.HAUSNUMMER,'')  FROM  JT_ADRESSE A WHERE A.ID_ADRESSE = " + sColPraefix +  "ID_ADRESSE_ZIEL )" +
			  	" ELSE CASE WHEN ID_ADRESSE_LAGER_ZIEL = " + _idLager + " AND ID_ADRESSE_LAGER_START != ID_ADRESSE_START  " +
			  		" THEN (SELECT  A.NAME1 || ' ' || NVL2( A.NAME2, ' ' ||  A.NAME2,'') || NVL2( A.NAME3, ' ' || A.NAME3, '') ||  ', ' || A.PLZ ||  ' ' ||  A.ORT || ', ' || NVL( A.STRASSE,'') || NVL2( A.HAUSNUMMER,' ' ||  A.HAUSNUMMER,'') FROM  JT_ADRESSE A WHERE A.ID_ADRESSE = " + sColPraefix +  "ID_ADRESSE_START ) " +
			  				"		ELSE to_nchar('-') END " +
			  " END " ;
		
		colBuchungsnr = sColPraefix + "BUCHUNGSNR_FUHRE";
		colSorte = "CASE  " + sColPraefix +  "ID_ADRESSE_LAGER_START WHEN " + _idLager + " THEN " + sColPraefix +  "ANR1_VK || '-' || " + sColPraefix +  "ANR2_VK || ' ' || " + sColPraefix +  "ARTBEZ1_VK || ' (' || to_nchar(b2.ID_ARTIKEL_BEZ) || ')'  ELSE CASE " + sColPraefix +  "ID_ADRESSE_LAGER_ZIEL WHEN " + _idLager + "  THEN " + sColPraefix +  "ANR1_EK || '-' || " + sColPraefix +  "ANR2_EK || ' ' || " + sColPraefix +  "ARTBEZ1_EK || ' (' || to_nchar(b1.ID_ARTIKEL_BEZ) || ')' ELSE null END END ";
		
		colKennzeichen = sColPraefix + "TRANSPORTKENNZEICHEN || nvl2( " + sColPraefix + "ANHAENGERKENNZEICHEN , ' / ' || ANHAENGERKENNZEICHEN , '') " ;
		
		colDatum = "CASE  " + sColPraefix +  "ID_ADRESSE_LAGER_START WHEN " + _idLager + " THEN " + sColPraefix +  "DATUM_AUFLADEN ELSE " + sColPraefix +  "DATUM_ABLADEN END";
		colGewicht = "CASE  " + sColPraefix +  "ID_ADRESSE_LAGER_START WHEN " + _idLager + " THEN " + sColPraefix +  "ANTEIL_LADEMENGE_LIEF ELSE " + sColPraefix +  "ANTEIL_ABLADEMENGE_ABN END";

		//
		// Spalten des Ergebnis-Grids definieren, die Zuordnung wird über den _colName (2. Param) durchgeführt
		//
		vecColumnList.clear();
		vecColumnList.add( new SearchDialog_ResultColumn( "Fuhre",ENUM_FIELDLIST.ID_GESAMT.name(),true,50));
		vecColumnList.add( new SearchDialog_ResultColumn( "WA/WE",ENUM_FIELDLIST.WEWA.name(),false,30));
		vecColumnList.add( new SearchDialog_ResultColumn( "Buchungsnummer",ENUM_FIELDLIST.BUCHUNGSNR_FUHRE.name(),false,30));
		vecColumnList.add( new SearchDialog_ResultColumn( "Kennzeichen",ENUM_FIELDLIST.TRANSPORTKENNZEICHEN.name(),false,100));
		vecColumnList.add( new SearchDialog_ResultColumn( "Kunde",ENUM_FIELDLIST.NAME.name(),false,250));
		vecColumnList.add( new SearchDialog_ResultColumn( "Liefer-Adresse",ENUM_FIELDLIST.LIEFERADRESSE.name(),false,250));
		vecColumnList.add( new SearchDialog_ResultColumn( "Firmen-Adresse",ENUM_FIELDLIST.ADRESSE.name(),false,250) );
		vecColumnList.add( new SearchDialog_ResultColumn( "Sorte",ENUM_FIELDLIST.SORTE.name(),false,250));
		vecColumnList.add( new SearchDialog_ResultColumn( "Gewicht",ENUM_FIELDLIST.GEWICHT.name(),false,80).set_AlignmentData(new Alignment(Alignment.RIGHT,Alignment.TOP)).set_AlignmentHeader(new Alignment(Alignment.RIGHT,Alignment.TOP)));
		vecColumnList.add( new SearchDialog_ResultColumn( "Datum",ENUM_FIELDLIST.DATUM.name(),false,80).set_AlignmentData(new Alignment(Alignment.RIGHT,Alignment.TOP)).set_AlignmentHeader(new Alignment(Alignment.RIGHT,Alignment.TOP)));

		
		
		// 
		// Selektoren
		// Falls die selektoren auf komplexe Spalten gemacht werden, müssen die Term-Cols genau den Select-Spalten entsprechen. Deshalb werden die Spalten in Stringvariablen definiert.
		// 
		SearchDialog_SelectorEntry selEntry ;

		// Kundenname
		this.selections_list.addSelectorEntry(
				new SearchDialog_SelectorEntry(ENUM_SELEKTORLIST.NAME.name())
				.set_Description("Kunde")
				.set_Active(false)
				.set_valueComponent(new SearchDialog_Selector_TextField("",200,100))
				.setTermList(new And(new TermLMR( new TermSimple("UPPER(" + colName + ")"), _TermCONST.COMP.LIKE.s(), "UPPER('%#WERT#%')")))
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
		
		
		// Lieferadresse
				this.selections_list.addSelectorEntry(
						new SearchDialog_SelectorEntry(ENUM_SELEKTORLIST.ID_ADRESSE.name())
							.set_Description("ID-Adresse")
							.set_Active(false)
							.set_valueComponent(new SearchDialog_Selector_TextField("",50,30))
							.setTermList(new And(new TermLMR( new TermSimple("UPPER(" + colAdresse + ")"), _TermCONST.COMP.LIKE.s(), "UPPER('%#WERT#%')")))
						);
				
		
		// Lieferadresse
		this.selections_list.addSelectorEntry(
				new SearchDialog_SelectorEntry(ENUM_SELEKTORLIST.ORT.name())
					.set_Description("Adresse")
					.set_Active(false)
					.set_valueComponent(new SearchDialog_Selector_TextField("",200,100))
					.setTermList(new And(new TermLMR( new TermSimple("UPPER(" + colAdresse + ")"), _TermCONST.COMP.LIKE.s(), "UPPER('%#WERT#%')")))
				);
					
					
		// BUchungsnummer
		this.selections_list.addSelectorEntry(
				new SearchDialog_SelectorEntry(ENUM_SELEKTORLIST.BUCHUNGSNR_FUHRE.name())
					.set_Description("Buchungsnummer")
					.set_Active(false)
					.set_valueComponent(new SearchDialog_Selector_TextField("",200,100) ) 
					.setTermList(new And(new TermLMR( new TermSimple("UPPER(" + colBuchungsnr + ")"), _TermCONST.COMP.LIKE.s(), "UPPER('%#WERT#%')")) )
				);
		
		// Sorte
		this.selections_list.addSelectorEntry(
				new SearchDialog_SelectorEntry(ENUM_SELEKTORLIST.SORTE.name())
					.set_Description("Sorte")
					.set_Active(false)
					.set_valueComponent(new SearchDialog_Selector_TextField("",200,100)) 
					.setTermList(new And(new TermLMR( new TermSimple("UPPER(" + colSorte + ")"), _TermCONST.COMP.LIKE.s(), "UPPER('%#WERT#%')"))) 
				);
		
		// Kennzeichen
		this.selections_list.addSelectorEntry(
				new SearchDialog_SelectorEntry(ENUM_SELEKTORLIST.KENNZEICHEN.name())
					.set_Description("Kennzeichen")
					.set_Active(false)
					.set_valueComponent(new SearchDialog_Selector_TextField("",200,100)) 
					.setTermList(new And(new TermLMR( new TermSimple("UPPER(" + colKennzeichen + ")"), _TermCONST.COMP.LIKE.s(), "UPPER('%#WERT#%')"))) 
				);
		
		// Datum von
		this.selections_list.addSelectorEntry(
				new SearchDialog_SelectorEntry(ENUM_SELEKTORLIST.DATUM_AB.name())
					.set_Description("Datum ab")
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
		this.set_key_4_save_sorting(ENUM_USER_SAVEKEY.KEY_SAVE_SORTING_SEARCHDIALOG_FUHRE);
		
		

		
	}
	
	
	
	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.SearchDialog.RB_SearchDialogBase#execute_searchquery_and_fill_resultbutton_array()
	 */
	@Override
	protected MyE2_MessageVector execute_searchquery_and_fill_resultbutton_array() throws myException {
		
		Vector<String>      v_wheres = new Vector<String>();
		
		
		SEL sel = new SEL()
				.ADDFIELD( colID,ENUM_FIELDLIST.ID_GESAMT.name())
				.ADDFIELD( "nvl(" + colWEWA + ",'-')", ENUM_FIELDLIST.WEWA.name())
				.ADDFIELD( colName, ENUM_FIELDLIST.NAME.name() ) 
				.ADDFIELD( colAdresse, ENUM_FIELDLIST.LIEFERADRESSE.name() )
				.ADDFIELD( colHauptadresse, ENUM_FIELDLIST.ADRESSE.name() ) 
				.ADDFIELD( colSorte, ENUM_FIELDLIST.SORTE.name() )
				.ADDFIELD( colDatum, ENUM_FIELDLIST.DATUM.name() )				
				.ADDFIELD( colGewicht, ENUM_FIELDLIST.GEWICHT.name())
				.ADDFIELD( "nvl(" + colBuchungsnr + ",'-')", ENUM_FIELDLIST.BUCHUNGSNR_FUHRE.name())
				.ADDFIELD( "nvl(" + colKennzeichen + ",'-') ", ENUM_FIELDLIST.TRANSPORTKENNZEICHEN.name())
				
				.FROM(sViewName , "V")
				.LEFTOUTER(_TAB.artikel_bez.n() + " b1 " ," b1." + ARTIKEL_BEZ.id_artikel_bez.fn(), sColPraefix + "ID_ARTIKEL_BEZ_EK")
				.LEFTOUTER(_TAB.artikel_bez.n() + " b2 " ," b2." + ARTIKEL_BEZ.id_artikel_bez.fn(), sColPraefix + "ID_ARTIKEL_BEZ_VK")
				;

		v_wheres.add(" WHERE 1=1 ");
		v_wheres.add( new And( new TermLMR( new Nvl(new TermSimple(sColPraefix + VPOS_TPA_FUHRE.alt_wird_nicht_gebucht.fn()),"'N'"),_TermCONST.COMP.EQ.s(),"'N'") ).s() ) ;
		v_wheres.add( new And( new TermLMR( new Nvl(new TermSimple(sColPraefix + VPOS_TPA_FUHRE.ist_storniert.fn()),"'N'"),_TermCONST.COMP.EQ.s(),"'N'") ).s() ) ;
		v_wheres.add( new And( new TermLMR( new Nvl(new TermSimple(sColPraefix + VPOS_TPA_FUHRE.deleted.fn()),"'N'"),_TermCONST.COMP.EQ.s(),"'N'") ).s() ) ;
		
		
		v_wheres.add(" " + this.getSelections_list().getStatement() + " " );
		String sql_where = bibALL.Concatenate(v_wheres, " AND ", "1=1");  //suchanteil und zusatzbedingungen
		RECLIST_MyRecord rl = null;

		//
		//  Query aufbauen
		//
		String c_sql_query = sel.s()  + sql_where;
		
		DEBUG.System_println(c_sql_query,DEBUG.DEBUG_FLAGS.DIVERS1.name());

		if (this.get_iMaxResults()>0) {
			rl = new RECLIST_MyRecord(c_sql_query,ENUM_FIELDLIST.ID_GESAMT.name(),this.get_iMaxResults()+1,true);
			if (rl.size() > this.get_iMaxResults()){
				MyE2_Warning_Message msg = new MyE2_Warning_Message(String.format("Das Ergebnis der Suche hat mehr als %d Zeilen. Bitte schränken Sie die Suche ein!",this.get_iMaxResults()) );
				bibMSG.add_MESSAGE(msg);
			}
		} else {
			rl = new RECLIST_MyRecord(c_sql_query,ENUM_FIELDLIST.ID_GESAMT.name());
		}
		
		SearchDialog_vecResultButtons  v_result_arrays = this.get_rb_vecResultButtons();
		v_result_arrays.clear();
		
		int cols = vecColumnList.size(); 
		
		for (int i=0;i<rl.size();i++) {
			String sort = "";

			MyRECORD rec = rl.get(i);
			SearchDialog_ResultButton[] res_zeile = new SearchDialog_ResultButton[cols];
			
			// Spalte Erzeugen
			String rowID = rec.get(ENUM_FIELDLIST.ID_GESAMT.name()).get_FieldValueUnformated();
			
			for(int j=0; j < vecColumnList.size();j++){
				SearchDialog_ResultColumn col = vecColumnList.get(j);

				MyResultValue f = rec.get(col.get_ColName());
				boolean bIsNumber = f.get_MetaFieldDef().get_bIsNumberTypeWithDecimals() || f.get_MetaFieldDef().get_bIsNumberTypeWithOutDecimals();

				String sValueUnformatted =  rec.get_UnFormatedValue(col.get_ColName(),"-");
				String sValueFormatted =  rec.get_FormatedValue(col.get_ColName(),"-");

				if (f != null){
					String sValue = (sValueUnformatted != null ? sValueUnformatted : sValueFormatted );
					if (col.isPK() || bIsNumber) {
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
