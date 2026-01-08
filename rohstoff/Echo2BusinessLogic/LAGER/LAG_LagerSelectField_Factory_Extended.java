package rohstoff.Echo2BusinessLogic.LAGER;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class LAG_LagerSelectField_Factory_Extended {

	private RB_cb cb_showAktiv 			= null;
	private RB_cb cb_showInaktiv 		= null;
	private E2_Button bt_suche 			= null;
	private E2_Button bt_eraseSuche 	= null;
	private RB_TextField tf_suchfeld 	= null;
	
	private MyE2_SelectField o_Selectfield = null;

	
	private VEK<cLagerEntry> vLagerListe 	= null;
	private cLagerEntry      oHauptadresse  = null;
	
	private boolean 	b_showStrecke 	= false;
	private int 		m_selectFieldLength = 850;
	private SQLField    m_sqlField = null;
	
	

	public LAG_LagerSelectField_Factory_Extended() throws myException {
		this(null,false);
	}
	
	public LAG_LagerSelectField_Factory_Extended(SQLField oSqlField) throws myException {
		this(oSqlField,false);
	}
	
	public LAG_LagerSelectField_Factory_Extended(boolean bShowStrecke) throws myException {
		this(null,bShowStrecke);
	}
	
	public LAG_LagerSelectField_Factory_Extended(SQLField oSqlField, boolean bShowStrecke) throws myException {
		if (oSqlField != null) {
			MyE2_DB_SelectField field = new MyE2_DB_SelectField(oSqlField);
			o_Selectfield = field;
		} else {
			o_Selectfield = new MyE2_SelectField();
		}
		initSelectField(o_Selectfield);
		
	}
	
	
//	
//	public LAG_LagerSelectField_Factory_Extended init_Factory() throws myException {
//		o_Selectfield = new MyE2_SelectField();
//		initSelectField(o_Selectfield);
//		
//		return this;
//	}
//	
//	
//	public LAG_LagerSelectField_Factory_Extended init_Factory(SQLField oSqlField) throws myException {
//		MyE2_DB_SelectField  field = new MyE2_DB_SelectField(oSqlField);
//		o_Selectfield = field;
//		initSelectField(o_Selectfield);
//		return this;
//	}
		
	
	
//	public MyE2_DB_SelectField getDBSelectField(SQLField osqlField) throws myException{
//
//		
//		o_Selectfield = field;
//
//		initSelectField(o_Selectfield);
//		return field;
//	}


	
	/**
	 * liefert das Selectfield ohne Strecke 
	 * und mit unformatierten IDs zurück
	 * 
	 * @author manfred
	 * @date   26.03.2012
	 * @return
	 * @throws myException
	 */
	public MyE2_SelectField getSelectField() throws myException{
//		o_Selectfield = new MyE2_SelectField();
//		initSelectField(o_Selectfield);
		return o_Selectfield;
	}



	/**
	 * liefert das Selectfield mit unformatierten IDs zurück
	 * @author manfred
	 * @date   26.03.2012
	 * @param bShowStrecke
	 * @return
	 * @throws myException
	 */
	private MyE2_SelectField initSelectField(MyE2_SelectField oSel) throws myException{
//			o_Selectfield = new MyE2_SelectField(new Extent(m_selectFieldLength));
//		}
		
		o_Selectfield.setWidth(new Extent(m_selectFieldLength)) ;
		
		//neue filter schalter: aktiv, inaktiv, suchfeld
		getAktivSchalter()	._aaa(()->updateSelectField(o_Selectfield));
		getInaktivSchalter()._aaa(()->updateSelectField(o_Selectfield));
		getSucheButton()	._aaa(()->updateSelectField(o_Selectfield));
		getEraseButton()	._aaa(()->updateSelectField(o_Selectfield));

		fillBaseList();
		updateSelectField(o_Selectfield);
		
		
		Font f = new nextapp.echo2.app.Font(Font.MONOSPACE, Font.PLAIN,	new Extent(bibALL.get_FONT_SIZE() - 1, Extent.PT));
		o_Selectfield.setFont(f);

		return o_Selectfield;
		
	}

	
	

	public E2_Grid render()throws myException{
		// Breite des Dropdown Feldes festlegen
		this.o_Selectfield.setWidth(new Extent(m_selectFieldLength));

		// Grid rendern
		E2_Grid render_grid = new E2_Grid()._s(5)._gld_align_lt();
		render_grid
		._a(this.o_Selectfield, new RB_gld()._ins(1,0,1,0)._left_mid())
		._a(new MyE2_Label("Filter:"), new RB_gld()._ins(15,0,5,0)._left_mid())
		._a(this.getAktivSchalter(), new RB_gld()._ins(0,0,5,0)._left_mid())
		._a(this.getInaktivSchalter(), new RB_gld()._ins(5,0,1,0)._left_mid())
		._a(
				new E2_Grid()._s(3)
				._a(getSuchFeld(), new RB_gld()._ins(0,0,2,0)._left_mid())
				._a(getSucheButton(), new RB_gld()._ins(2,0,2,0)._left_mid())
				._a(getEraseButton(), new RB_gld()._ins(2,0,0,0)._left_mid())

				,new RB_gld()._ins(10,0,1,0)._left_mid());

		
		return render_grid;
	}

	public LAG_LagerSelectField_Factory_Extended setSelectfieldWidth (int len) throws myException {
		this.m_selectFieldLength = len;
		return this;
	}
	
	public RB_cb getAktivSchalter() throws myException{
		if(cb_showAktiv == null) {
			cb_showAktiv = new RB_cb()._t("Aktiv")._setSelected();
		}
		return cb_showAktiv;
	}

	public RB_cb getInaktivSchalter() throws myException{
		if(cb_showInaktiv == null) {
			cb_showInaktiv = new RB_cb()._t("Inaktiv")._setSelected();
		}
		return cb_showInaktiv;
	}

	public RB_TextField getSuchFeld() throws myException{
		if(tf_suchfeld == null) {
			tf_suchfeld = new RB_TextField(150);
		}
		return tf_suchfeld;
	}

	public E2_Button getSucheButton() throws myException{
		if(bt_suche == null) {
			bt_suche = new E2_Button()._image("suche.png");
			bt_suche._aaa(()->fillSelector(false, b_showStrecke));
		}
		return bt_suche;
	}

	public E2_Button getEraseButton() throws myException{
		if(bt_eraseSuche == null) {
			bt_eraseSuche = new E2_Button()._image("eraser.png");
			bt_eraseSuche._aaa(()->eraseSucheField());
		}
		return bt_eraseSuche;
	}

	
	
	private void eraseSucheField() throws myException {
		getSuchFeld()._t("");

	}


	private void updateSelectField(MyE2_SelectField oSelector) throws myException{		
		oSelector.set_ListenInhalt(fillSelector(false,b_showStrecke), false);
		oSelector.doActionPassivManual();
	}

	
	
	/**
	 * liest alle Läger in einen internen Vektor ein
	 * @author manfred
	 * @date 03.03.2020
	 *
	 */
	private void fillBaseList() {
		vLagerListe = new VEK<cLagerEntry>();
		
		String cQuery = "SELECT NVL(JT_ADRESSE.ORT,'-')||' '||NVL('('||JT_ADRESSE.PLZ||')',''), "
				+ "				NVL(JT_ADRESSE.NAME1,'') || NVL2(JT_ADRESSE.NAME2,' ' || JT_ADRESSE.NAME2,'') ||  "
				+ "				NVL2(JT_LIEFERADRESSE.BESCHREIBUNG, ' - ' || JT_LIEFERADRESSE.BESCHREIBUNG,'')  " 
				+ ",	NVL(JT_ADRESSE.AKTIV,'Y')  "
				+ ",	JT_ADRESSE.ID_ADRESSE "
				+ " FROM "	+ bibE2.cTO()+ ".JT_ADRESSE "
				+ " INNER JOIN " + bibE2.cTO() 	+ ".JT_LIEFERADRESSE  ON JT_ADRESSE.ID_ADRESSE=JT_LIEFERADRESSE.ID_ADRESSE_LIEFER  "
				+ " WHERE "
				+ " JT_LIEFERADRESSE.ID_ADRESSE_BASIS = ? "
				+ " AND ID_ADRESSE > 999"
				+ " ORDER BY 1 ASC";

		SqlStringExtended sqlExt1 = new SqlStringExtended(cQuery);
		sqlExt1.getValuesList().add(new Param_Long(Long.parseLong(bibALL.get_ID_ADRESS_MANDANT())));
		

		// eigene adresse
		String cQuery2 = "SELECT "
				+ "  NVL(JT_ADRESSE.NAME1,'-')||' '|| NVL(JT_ADRESSE.STRASSE,'-')||', '|| NVL(JT_ADRESSE.ORT,'-')"
				+ " , 'Hauptadresse' "
				+ " , NVL(JT_ADRESSE.AKTIV,'Y') "
				+ " , JT_ADRESSE.ID_ADRESSE "
				+ " FROM " 	+ bibE2.cTO()+ ".JT_ADRESSE"
				+ " WHERE ID_ADRESSE= ? ";
		
		SqlStringExtended sqlExt2 = new SqlStringExtended(cQuery2);
		sqlExt2.getValuesList().add(new Param_Long(Long.parseLong(bibALL.get_ID_ADRESS_MANDANT())));


		String [][] cDaten1 = new String[0][0];
		cDaten1 =  bibDB.EinzelAbfrageInArray(sqlExt1,(String)null);
		
		String [][] cDaten2 = new String[0][0];
		cDaten2 =  bibDB.EinzelAbfrageInArray(sqlExt2,(String)null);
		
		String sOrt = "";
		String sName = "";
		String sActive = "";
		boolean bActive = true;
		String sID = "";

		// zuerst das eigene Lager
		if (cDaten2 != null && cDaten2.length == 1) {
			sOrt = cDaten2[0][0];
			sName = cDaten2[0][1];
			sActive = cDaten2[0][2];
			bActive = (sActive != null && sActive.equalsIgnoreCase("N")) ? false : true;
			sID = cDaten2[0][3];

			vLagerListe._a(new cLagerEntry(sID, sOrt, sName, bActive, false).setbIsMainAdresse(true));
		}
		
		
		
		// dann alle restlichen Läger
		if (cDaten1 != null && cDaten1.length > 01) {
			for (int i = 0; i < cDaten1.length; i++) {
				sOrt = cDaten1[i][0];
				sName = cDaten1[i][1];
				sActive = cDaten1[i][2];
				bActive = (sActive != null && sActive.equalsIgnoreCase("N")) ? false : true;
				sID = cDaten1[i][3];
				vLagerListe._a(new cLagerEntry(sID, sOrt, sName, bActive, false));
			}

		}
		
	}
	
	
	
	
	
	/**
	 * 
	 * Füllt die Liste mit den gefundenen Werten 
	 * 
	 * @return
	 * @throws myException 
	 */
	private String[][] fillSelector(boolean bFormatted, boolean bShowStrecke) throws myException{

		
		
		VEK<String[]> vInAktivDisplayList = new VEK<String[]>();
		
		String searchtext = null;
		if (tf_suchfeld != null) {
			searchtext = tf_suchfeld.getText();
		}
		List<cLagerEntry> lResult = null; 
				
		if (!bibALL.isEmpty(searchtext)) {
			
			
			
			// Liste filtern
			Vector<cLagerEntry> vec = vLagerListe.getVector();
			lResult = 
					(List<cLagerEntry>) vec	.stream()
					 	.filter(x->x.contains(tf_suchfeld.getText())) 
					 	.collect(Collectors.toList())
					;
		} else {
			// ganze Liste
			Vector<cLagerEntry> vec = vLagerListe.getVector();
			lResult =vec.stream().collect(Collectors.toList())	;
		}
		
		// aktiv-Filter
		boolean bActive = getAktivSchalter().isSelected();
		boolean bInactive = getInaktivSchalter().isSelected();
		
		if (!bActive && !bInactive) {
			lResult = lResult.stream().filter(x->x.bActive == false && x.bActive == true  ).collect(Collectors.toList());
		} else if ( bActive && !bInactive ) {
			lResult = lResult.stream().filter(x->x.bActive == true  ).collect(Collectors.toList());
		} else if (bInactive && !bActive) {
			lResult = lResult.stream().filter(x->x.bActive == false ).collect(Collectors.toList());
		}
		
		
		
		int len_ort = (int)lResult.stream().mapToInt(x->x.LagerOrt.length()).max().orElse(0);
		int len_Name = (int)lResult.stream().mapToInt(x->x.LagerName.length()).max().orElse(0);
		int lenMax = len_ort + len_Name + 13;

		

		String[][] cWerte = null;

		int nSize = 1 + lResult.size();
			
		if (bShowStrecke){
			nSize++;
		}
			
		cWerte = new String[nSize][2];
		cWerte[0][0] = "-";
		cWerte[0][1] = "";

		int icount = 1;
			
						
		// gefilterte Lagerwerte anzeigen	
		for (int i = 0; i < lResult.size(); i++) {

			cLagerEntry oEntry = lResult.get(i);
			
			// cWerte[icount][0] = cArray[i][0];
			cWerte[icount][0] = oEntry.getEntry(len_ort);
			cWerte[icount][1] = oEntry.getIdLager();
			icount++;
		}
		
		// hier die Strecke rein
		if (bShowStrecke){
			cWerte[icount][0] = new MyE2_String("Streckenlager").CTrans();
			cWerte[icount][1] = "0";
			icount++;
		}
		
		bibMSG.MV()._addInfo("Die Lagerliste wurde geändert");

		return cWerte;
	}

	
	
	
	
	
	/**
	 * Lager-Eintrag, für die Selektion nach aktiv/inaktiv
	 * @author manfred
	 * @date 28.02.2020
	 *
	 */
	private class cLagerEntry {
	
		
		private boolean bIsMainAdresse = false;
		private boolean bActive = false;
		private String  LagerName = "";
		private String  LagerOrt = "";
		private String  idLager = null;
		private boolean bSonderlager = false;
		
		private String sTab = "____________________________________________________________________________________________________"
				+ "____________________________________________________________________________________________________"
				+ "____________________________________________________________________________________________________"
				+ "____________________________________________________________________________________________________";

		public cLagerEntry (String id,String Ort, String Name, boolean isActive, boolean isSonderlager) {
			this.bActive = isActive;
			this.LagerOrt = Ort;
			this.LagerName   = Name; 
			this.bSonderlager = isSonderlager;
			this.idLager = id;
		}
		

		
		public boolean contains(String searchString) {
			List<String> items = Arrays.asList(searchString.toUpperCase().split(" "));
			
			String stringtosearch = this.getIdLager().toUpperCase() + this.getLagerOrt().toUpperCase() + this.getLager().toUpperCase();
		    boolean found = false;
		    for (String item : items) {
		        if (stringtosearch.contains(item)) {
		            found = true;
		            break;
		        }
		    }
		    return found;
		}
		
		
		/**
		 * erzeugt den Eintrag für die Liste
		 * @author manfred
		 * @date 05.03.2020
		 *
		 * @param len_col1
		 * @return
		 */
		public String getEntry(int len_col1) {
			String entry = "";
			if (bIsMainAdresse || bSonderlager) {
				entry = LagerName + ":  " +  LagerOrt;
			} else {
				
				entry = getLagerOrt() + " " + sTab.substring(0,len_col1 - getLagerOrt().length() + 10 );
				entry += " " + getLager();
				entry += isbActive() ? "" : " * inaktiv ";
			}
			
			return entry;
		}
		
		public boolean isbActive() {
			return bActive;
		}

		public cLagerEntry setbActive(boolean bActive) {
			this.bActive = bActive;
			return this;
		}

		public String getLager() {
			return LagerName;
		}

		public cLagerEntry setLager(String lager) {
			LagerName = lager;
			return this;
		}

		public boolean isbSonderlager() {
			return bSonderlager;
		}
		
		public cLagerEntry setbSonderlager(boolean bSonderlager) {
			this.bSonderlager = bSonderlager;
			return this;
		}

		
		public boolean isbIsMainAdresse() {
			return bIsMainAdresse;
		}

		public cLagerEntry setbIsMainAdresse(boolean bIsMainAdresse) {
			this.bIsMainAdresse = bIsMainAdresse;
			return this;
		}

		public String getIdLager() {
			return idLager;
		}

		public cLagerEntry setIdLager(String idLager) {
			this.idLager = idLager;
			return this;
		}

		public String getLagerOrt() {
			return LagerOrt;
		}

		public cLagerEntry setLagerOrt(String lagerOrt) {
			LagerOrt = lagerOrt;
			return this;
		}

	}
	
	
}
