package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_SALDO;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO_OnlyCodeNavigationList;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_Font;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.vectorForSegmentation;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.SALDO.ATOM_LagerSaldoErmittlung;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.SALDO.ATOM_SaldoDaten;

public class ATOM_SALDO_LIST_Summay extends E2_ListSelectorContainer
{
	
	private cFieldList      m_fieldList 			= new cFieldList();
	private cFieldList      m_fieldList_caption   	= new cFieldList();
	
	// Objekte
	private MyE2_Label 		m_lblMessage 		= null;
	private MyE2_Grid		m_oGridMain 		= null;
	private Insets 		  	m_oIN				= null;
	private Insets 	  		m_oIN2				= null;
	private GridLayoutData m_oLayout1ColRight  = null;
	private GridLayoutData m_oLayout1ColLeft  	= null;
	
	// die sortierte Treemap, die die Werte für die einzelnen Einheiten beinhaltet
	private  TreeMap<String, cFieldRow>  m_oRows = new TreeMap<String, ATOM_SALDO_LIST_Summay.cFieldRow>();
	private E2_NavigationList	oNaviList = null;
	private ATOM_LagerSaldoErmittlung  m_SaldoErmittlung = null;

	
	
	
	public ATOM_SALDO_LIST_Summay(E2_NavigationList oNavigationList, ATOM_LagerSaldoErmittlung oSaldoErmittlung) throws myException
	{
		super(new MyE2_String("Summen, gruppiert nach Einheiten"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));

		oNaviList = oNavigationList;
		m_SaldoErmittlung = oSaldoErmittlung;
		
		m_lblMessage = new MyE2_Label("");
		m_lblMessage.setFont(new E2_FontBold(0));
		m_lblMessage.setForeground(Color.RED);
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
		m_oGridMain = new MyE2_Grid(3,0);
		
		m_oIN = new Insets(0,2,15,2);
		m_oIN2 = new Insets(5,2,15,2);

		m_oLayout1ColRight = new GridLayoutData();
		m_oLayout1ColRight.setAlignment(Alignment.ALIGN_RIGHT);
		m_oLayout1ColRight.setColumnSpan(1);
		m_oLayout1ColRight.setInsets(m_oIN);

		m_oLayout1ColLeft = new GridLayoutData();
		m_oLayout1ColLeft.setAlignment(Alignment.ALIGN_LEFT);
		m_oLayout1ColLeft.setColumnSpan(1);
		m_oLayout1ColLeft.setInsets(m_oIN2);
		
		
		oCol.add(m_oGridMain );
		
		this.add(oCol,new Insets(2,5,2,5));
		
		// das Event an die Navigation-List anhängen, um die Berechnungen zu starten
//		oNavigationList.add_actionBeforeListStarted(new XX_ActionAgent() {
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

		// Wenn das Panel nicht ausgeklappt ist, dann ist die Berechnung nicht nötig
//		if (!this.get_bOpen()){
//			return;
//		}
		
		clearData();
		
		vectorForSegmentation vNeu = new vectorForSegmentation();
		vNeu.addAll(oNaviList.get_vectorSegmentation());
		vNeu.set_iSegmentGroesse(100);
				
		int iSegmente = vNeu.get_iZahlSegmente();

//		String sQueryForIDs = 	oNaviList.get_oComponentMAP__REF().get_oSQLFieldMAP().get_CompleteSQLQueryFor_ID_VECTOR("",true);
		
		String sIDEinheitReferenz = null;
		
		String sIDs = "";
		
		
		for (int i = 0; i< iSegmente; i++){
			
			// Werte für die In-Klausel generieren
			// Die Werte segmentieren, damit das Statement nicht zu groß wird
			//
			Vector<String> vSegment = vNeu.get_vSegment(i);
			try {
				sIDs = bibALL.Concatenate(vSegment, ",", "-1");
			} catch (myException e) {
				continue;
			}
			
			String[][] cLagerDaten = new String[0][0];
			String sSql = "";
			sSql = "SELECT s.ID_ADRESSE, a.ID_ARTIKEL, e.EINHEITKURZ "
				+ " FROM " + bibE2.cTO() + ".JT_BEWEGUNG_STATION s "
				+ " INNER JOIN " + bibE2.cTO() + ".JT_BEWEGUNG_ATOM a on a.ID_BEWEGUNG_ATOM = s.ID_BEWEGUNG_ATOM "
				+ " INNER JOIN " + bibE2.cTO() + ".JT_ARTIKEL art on a.ID_ARTIKEL = art.ID_ARTIKEL "
				+ " INNER JOIN " + bibE2.cTO() + ".JT_EINHEIT e   on e.ID_EINHEIT = art.ID_EINHEIT "
			    + " WHERE S.ID_BEWEGUNG_STATION in ( "
				+ sIDs
				+ " ) ";
			
			cLagerDaten = bibDB.EinzelAbfrageInArray(sSql, (String) null);
		
			// wenn nichts gefunden wird, einen leeren Vektor zurückgeben
			if (cLagerDaten == null || cLagerDaten.length == 0) {
				continue ;
			}
			
			for (int j = 0; j < cLagerDaten.length; j++) {
				
				String sID_Adresse 	= cLagerDaten[j][0];
				String sID_Sorte 	= cLagerDaten[j][1];
				String sEinheit 	= cLagerDaten[j][2];

				ATOM_SaldoDaten oDaten = m_SaldoErmittlung.getData(sID_Adresse, sID_Sorte);
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
					m_oGridMain.add(new MyE2_Label(new MyE2_String("Summe:")),1,m_oIN);
				} else {
					m_oGridMain.add(new MyE2_Label(new MyE2_String("")),1,m_oIN);
				}
				// 2. Spalte
				m_oGridMain.add(row.getField_Value().getObject(),m_oLayout1ColRight);
				// 3. Spalte 
				m_oGridMain.add(row.getField_Description().getObject(),m_oLayout1ColLeft);
				
				iRow++;
				
				// werte Aktualisieren
				row.bind();
			}
		} else {
			showMessage(new MyE2_String("Keine Summen-Werte vorhanden.").CTrans());
		}
		
	}
	
	
	
	/**
	 * Spezielle Row für die Darstellung der Summen.
	 * bildet die Daten einer Summenzeile im Grid ab
	 * 
	 * @author manfred
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
		 * Löscht den Wert
		 */
		void clear(){
			bdValue = BigDecimal.ZERO;
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
	
	
	/**
	 * Sammlung der Felder um zu aktualisieren und löschen
	 * @author manfred
	 * @date   10.10.2013
	 */
	private class cFieldList {
		ArrayList<cField> m_fieldList = null;
		
		public cFieldList() {
			m_fieldList = new ArrayList<ATOM_SALDO_LIST_Summay.cField>();
		}
		
		public void add(cField field){
			m_fieldList.add(field);
		}
		
		public void clearFieldList () {
			for (cField o: m_fieldList){
				o.clear();
			}
		}
		
		public void bindFieldList () {
			for (cField o: m_fieldList){
				o.bind();
			}
		}
	}

	
	
	
	/***
	 * 
	 * @author manfred
	 * @date   10.10.2013
	 */
	private abstract class cField {
		
		protected MyE2IF__Component 	m_object = null;
		protected Object 				m_value_object = null;
		protected E2_Font				m_font = null;
		protected cFieldDataConverter 	m_dataconverter = null;

		
		protected cField(){
			this(new E2_FontPlain());
		}
		
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
		
		protected cField_Label(){
			this(new E2_FontPlain());
		}
		
		
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
	 * @author manfred
	 * @date   10.10.2013
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
	 * @author manfred
	 * @date   10.10.2013
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

		private int 		m_anzahl_dezimalstellen = 3;
		private boolean 	m_zeige_tausender = true;
		
		public cFieldDataConverter_BigDecimal(){
			super();
		}
		
		public cFieldDataConverter_BigDecimal(int anzahl_dezimalstellen, boolean bZeigeTausenderpunkt){
			super(anzahl_dezimalstellen,bZeigeTausenderpunkt);
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

		private int 		m_anzahl_dezimalstellen = 3;
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
