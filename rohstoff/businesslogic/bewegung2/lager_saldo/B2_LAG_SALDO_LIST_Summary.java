package rohstoff.businesslogic.bewegung2.lager_saldo;


import java.math.BigDecimal;
import java.util.TreeMap;
import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO_OnlyCodeNavigationList;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_Font;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.EINHEIT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.vectorForSegmentation;
import rohstoff.businesslogic.bewegung2.global.EnTabKeyInMask;
import rohstoff.businesslogic.bewegung2.global.EnTransportTyp;
import rohstoff.businesslogic.bewegung2.global.saldo.B2_LAG_SALDO_Ermittlung;
import rohstoff.businesslogic.bewegung2.global.saldo.B2_LAG_SALDO_SaldoDaten;

public class B2_LAG_SALDO_LIST_Summary extends E2_ListSelectorContainer
{

	// Objekte
	private RB_lab 		m_lblMessage 		= null;
	private E2_Grid		m_oGridMain 		= null;
	private Insets 	  	m_oIN				= null;
	private Insets 	  	m_oIN2				= null;
	private RB_gld 		m_oLayout1ColRight  = null;
	private RB_gld 		m_oLayout1ColLeft  	= null;

	// die sortierte Treemap, die die Werte für die einzelnen Einheiten beinhaltet
	private  TreeMap<String, cFieldRow>  m_oRows = new TreeMap<String, B2_LAG_SALDO_LIST_Summary.cFieldRow>();
	private E2_NavigationList	oNaviList = null;
	private B2_LAG_SALDO_Ermittlung  m_SaldoErmittlung = null;




	public B2_LAG_SALDO_LIST_Summary(E2_NavigationList oNavigationList, B2_LAG_SALDO_Ermittlung oSaldoErmittlung) throws myException
	{
		super(S.ms("Summen, gruppiert nach Einheiten"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));

		oNaviList = oNavigationList;
		m_SaldoErmittlung = oSaldoErmittlung;

		m_lblMessage = new RB_lab()._fo(new E2_FontBold(0))._col_fore(Color.RED);
		m_lblMessage.setVisible(false);

		init(oNaviList);
	}



	protected void init(E2_NavigationList oNavigationList)
			throws myException
	{
		MyE2_Column oCol = new MyE2_Column();

		// Meldungszeile oberhalb des Grids anzeigen
		oCol.add(m_lblMessage);

		// Vollständig verbuchte Einträge ausblenden...
		m_oGridMain = new E2_Grid()._s(3)._bo_no();

		m_oIN = new Insets(0,2,15,2);
		m_oIN2 = new Insets(5,2,15,2);

		m_oLayout1ColRight = new RB_gld()._right_top()._span(1)._ins(m_oIN);

		m_oLayout1ColLeft =  new RB_gld()._left_top()._span(1)._ins(m_oIN2);

		oCol.add(m_oGridMain );

		this.add(oCol,new Insets(2,5,2,5));

		// das Event an die Navigation-List anhängen, um die Berechnungen zu starten
		oNavigationList.add_actionAfterContentVectorIsSet(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				if (oExecInfo instanceof ExecINFO_OnlyCodeNavigationList){
					ExecINFO_OnlyCodeNavigationList o = (ExecINFO_OnlyCodeNavigationList) oExecInfo;

					ReadLagerdatenFromNavigationListEntriesE(o.get_NavigationList());

				}
			}
		});

	}





	/**
	 * Ermitteln der Lagerdaten, die in der Navigation-List vorhandenen Einträge des Lagerkontos
	 * @throws myException 
	 */
	private void ReadLagerdatenFromNavigationListEntriesE(E2_NavigationList oNaviList) throws myException {

		clearData();

		vectorForSegmentation vNeu = new vectorForSegmentation();
		vNeu.addAll(oNaviList.get_vectorSegmentation());
		vNeu.set_iSegmentGroesse(100);

		int iSegmente = vNeu.get_iZahlSegmente();

		String sIDs = "";

		for (int i = 0; i< iSegmente; i++){

			Vector<String> vSegment = vNeu.get_vSegment(i);
			try {
				sIDs = bibALL.Concatenate(vSegment, ",", "-1");
			} catch (myException e) {
				continue;
			}

			String[][] cLagerDaten = new String[0][0];
						
			String sSql =
					"	SELECT " +
					"            AU.ID_ADRESSE AS SUBADRESSE, " +
					"            AU.ID_ARTIKEL AS SUBARTIKEL ," + 
					"			 E.EINHEITKURZ as SUBEINHEIT " +
					"       FROM  JT_BG_AUSWERT AU " +
					"     INNER JOIN JT_ARTIKEL A ON AU.ID_ARTIKEL = A.ID_ARTIKEL " +
					"     INNER JOIN JT_EINHEIT E ON A.ID_EINHEIT = E.ID_EINHEIT " +
					"         "
					+ "where id_bg_auswert in (" + sIDs + ")" ; 
			
			cLagerDaten = bibDB.EinzelAbfrageInArray(sSql, (String) null);

			// wenn nichts gefunden wird, einen leeren Vektor zurückgeben
			if (cLagerDaten == null || cLagerDaten.length == 0) {
				continue ;
			}

			for (int j = 0; j < cLagerDaten.length; j++) {

				String sID_Adresse 	= cLagerDaten[j][0];
				String sID_Sorte 	= cLagerDaten[j][1];
				String sEinheit 	= cLagerDaten[j][2];

				B2_LAG_SALDO_SaldoDaten oDaten = m_SaldoErmittlung.getData(sID_Adresse, sID_Sorte);
				BigDecimal bdSaldo = oDaten.get_SaldoLager();

				// die verschiedenen Werte zu den richtigen Rows(Einheiten) summieren
				cFieldRow oRow = null;
				if (m_oRows.containsKey(sEinheit)){
					oRow = m_oRows.get(sEinheit);
					oRow.add(bdSaldo);
				} else {
					oRow = new cFieldRow(sEinheit);
					oRow.add(bdSaldo);
					m_oRows.put(sEinheit, oRow);
				}
			}
		}

		// werte neu ermitteln
		refreshSummaryValues();
	}



	/**
	 * löscht alle Daten aus der treemap
	 */
	private void clearData() {
		m_lblMessage.setVisible(false);
		m_oRows.clear();
	}

	/**
	 * zeigt die Meldungszeile an
	 * @param sMessage
	 */
	private void showMessage(String sMessage){
		m_lblMessage.setVisible(true);
		m_lblMessage.setText(sMessage);
	}



	/**
	 * baut das Grid neu auf
	 */
	private void refreshSummaryValues() {

		m_oGridMain.removeAll();

		int iRow = 0;
		if (m_oRows.size() > 0){

			for (cFieldRow row : m_oRows.values()){
				// 1. Spalte
				if (iRow == 0){
					m_oGridMain._a(new RB_lab()._t(S.ms("Summe:"))	,new RB_gld()._ins(m_oIN));
				} else {
					m_oGridMain._a(new RB_lab()._t(S.ms("")),		new RB_gld()._ins(m_oIN));
				}
				// 2. Spalte
				m_oGridMain._a(row.getField_Value().getObject(),m_oLayout1ColRight);
				// 3. Spalte 
				m_oGridMain._a(row.getField_Description().getObject(),m_oLayout1ColLeft);

				iRow++;

				// werte Aktualisieren
				row.bind();
			}
		} else {
			showMessage(S.ms("Keine Summen-Werte vorhanden.").CTrans());
		}

	}



	/**
	 * Spezielle Row für die Darstellung der Summen.
	 * bildet die Daten einer Summenzeile im Grid ab
	 * 
	 * @author sebastien
	 * 
	 */
	private class cFieldRow{
		CLabel_String 		lblDescription 	= new CLabel_String();
		CLabel_BigDecimal 	lblValue		= new CLabel_BigDecimal();

		BigDecimal 			bdValue			= BigDecimal.ZERO;
		String				sDescription	= "";


		cFieldRow(String Description){
			sDescription = Description;
		}

		/*
		 * Addiert den Wert auf den Summenwert auf
		 */
		void add (BigDecimal bdToAdd ){
			bdValue = bdValue.add(bdToAdd!= null ? bdToAdd : BigDecimal.ZERO);
		}

		cField_Label getField_Description(){
			return lblDescription;
		}

		cField_Label getField_Value(){
			return lblValue;
		}

		/**
		 * Setzt die Werte für die Zeile
		 */
		void bind(){
			lblDescription.setValue(sDescription);
			lblValue.setValue(bdValue);
			lblDescription.bind();
			lblValue.bind();
		}
	}


	/***
	 * 
	 * @author sebastien
	 * @date   29.08.2019
	 */
	private abstract class cField {

		protected MyE2IF__Component 	m_object = null;
		protected Object 				m_value_object = null;
		protected E2_Font				m_font = null;
		protected cFieldDataConverter 	m_dataconverter = null;


		protected cField(E2_Font font){
			m_font = font;
		}



		protected abstract void 	bind();
		protected abstract void 	clear();

		// Get VALUE-OBJECT
		protected abstract Object getValue();
		protected abstract void 	setValue(Object value);

		// Get GUI
		protected abstract Object getObject();

	}



	private abstract class cField_Label extends cField {

		protected cField_Label(E2_Font font){
			super(font);
			m_object = new MyE2_Label("", m_font);
		}


		@Override
		protected MyE2_Label getObject() {
			return (MyE2_Label)m_object;
		}


		protected abstract void 	bind();
		protected abstract void 	clear();

		protected abstract Object getValue();
		protected abstract void 	setValue(Object value);



	}



	/***
	 * 
	 * @author sebastien
	 * @date   29.08.2019
	 */
	private class CLabel_String extends cField_Label{


		public CLabel_String() {
			this(new E2_FontPlain());
		}


		public CLabel_String(E2_Font font) {
			super(font);
			m_dataconverter = new cFieldDataConverter_String();
		}

		@Override
		protected String getValue() {
			return (String)m_value_object;
		}

		@Override
		protected void setValue(Object value) {
			if (value == null){
				m_value_object = null;
			} else 	if (value.getClass().equals(String.class)){
				m_value_object = value;
			}
		}

		@Override
		protected void bind() {
			getObject().set_Text(m_dataconverter.doTransformation(this.getValue()));
		}

		@Override
		protected void clear() {
			setValue("");
		}
	}



	/***
	 * 
	 * @author sebastien
	 * @date   29.08.2019
	 */
	private class CLabel_BigDecimal extends cField_Label{

		public CLabel_BigDecimal() {
			this(new E2_FontPlain());
		}


		public CLabel_BigDecimal(E2_Font font) {
			super(font);
			m_value_object = new BigDecimal(0);
			m_dataconverter = new cFieldDataConverter_BigDecimal();
			m_dataconverter.setDisplayOnNull("-");
		}


		@Override
		protected BigDecimal getValue() {
			return (BigDecimal)m_value_object;
		}

		@Override
		protected void setValue(Object value) {
			if (value == null){
				m_value_object = null;
			} else  if (value.getClass().equals(BigDecimal.class)){
				m_value_object = value;
			}
		}

		@Override
		protected void bind() {
			getObject().setText(m_dataconverter.doTransformation(this.getValue()));
		}


		@Override
		protected void clear() {
			m_value_object = BigDecimal.ZERO;
			//			m_value_object = null;
		}

	}







	private abstract class cFieldDataConverter {

		protected String 	m_null_value = null;

		public cFieldDataConverter() {
			//default Konstruktor
		}
		protected void   setDisplayOnNull(String sDisplayWhenNull){
			m_null_value = sDisplayWhenNull;
		}

		protected abstract String doTransformation(Object data);
	}



	private class cFieldDataConverter_BigDecimal extends cFieldDataConverter_Double{


		public cFieldDataConverter_BigDecimal(){
			super();
		}

		@Override
		protected String doTransformation(Object data) {
			String sRet = "?";
			if (data != null){
				if (data.getClass().equals(BigDecimal.class)){
					sRet = bibALL.makeDez( ((BigDecimal) data).doubleValue(),3,true);
				}
			} else {
				sRet = m_null_value;
			}
			return sRet;
		}

	}


	private class cFieldDataConverter_Double extends cFieldDataConverter{

		@SuppressWarnings("unused")
		private int 		m_anzahl_dezimalstellen = 3;
		@SuppressWarnings("unused")
		private boolean 	m_zeige_tausender = true;


		public cFieldDataConverter_Double(){
			this(3,true);
		}

		public cFieldDataConverter_Double(int anzahl_dezimalstellen, boolean bZeigeTausenderpunkt){
			m_anzahl_dezimalstellen = anzahl_dezimalstellen;
			m_zeige_tausender 		= bZeigeTausenderpunkt;
		}

		@Override
		protected String doTransformation(Object data) {
			String sRet = "?";
			if (data != null){
				if (data.getClass().equals(Double.class)){
					sRet = bibALL.makeDez((Double)data,3,true);
				}
			} else {
				sRet = m_null_value;
			}
			return sRet;
		}
	}


	private class cFieldDataConverter_String extends cFieldDataConverter{

		private boolean 	m_uppercase = false;

		public cFieldDataConverter_String(){
			this(false);
		}

		public cFieldDataConverter_String(boolean bUpperCase){
			m_uppercase = bUpperCase;
		}

		@Override
		protected String doTransformation(Object data) {
			String sRet = "?";

			if (data != null){
				if (data.getClass().equals(String.class)){
					sRet = m_uppercase ? ((String)data).toUpperCase() : (String)data;
				} else {
					sRet = data.toString();
				}
			} else {
				sRet = m_null_value;
			}

			return sRet;
		}
	}

}
