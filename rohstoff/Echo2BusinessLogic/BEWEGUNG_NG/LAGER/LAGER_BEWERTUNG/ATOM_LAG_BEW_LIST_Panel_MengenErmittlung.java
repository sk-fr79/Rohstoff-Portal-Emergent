package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWERTUNG;

import java.util.Observable;
import java.util.Observer;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.RowLayoutData;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextField;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.indep.exceptions.myException;

public class ATOM_LAG_BEW_LIST_Panel_MengenErmittlung extends MyE2_Column implements Observer
{

	/**
	 * 
	 */
	private static final long 				serialVersionUID 			= 7302202785421205379L;
	
	// Felder für die Ermittlung der Preise kleiner Betrag X
	private UB_TextField 						tfPreisMax 						= null;
	private UB_TextField 						tfErgebnis						= null;
	private MyE2_Label	 						lblEinheit						= null;
	
	// Felder für die Ermittlung der Durchschnittspreise kleiner Betrag X
	private UB_TextField 						tfPreisMaxAVG 					= null;
	private UB_TextField 						tfErgebnisMengeAVG				= null;
	private UB_TextField 						tfErgebnisPreisAVG				= null;
	private MyE2_Label	 						lblEinheitAVG					= null;
	

	// Felder für die Ermittlung des Durchschnittspreises einer Menge Y
	private UB_TextField 						tfMengeFuerDurchschnittsPreis 		= null;
	private UB_TextField 						tfErgebnisDurchschnittsPreis		= null;
	private UB_TextField 						tfErgebnisDurchschnittsPreisMenge	= null;
	private MyE2_Label	 						lblEinheitDurchschnittspreis		= null;
	private MyE2_Label	 						lblEinheitDurchschnittspreis1		= null;

	private ATOM_LAG_Mengenermittlung_ext 		m_oMengenErmittlung 				= null;
	private MyE2_CheckBox						cbRecalculate						= null;

	private String								m_MengenEinheit						= "Menge";
	
	/**
	 * 
	 */
	public ATOM_LAG_BEW_LIST_Panel_MengenErmittlung(	E2_NavigationList oNaviList, 
												ATOM_LAG_BEW_LIST_Selector ListSelector,
												ATOM_LAG_Mengenermittlung_ext oMengenErmittlung ) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER());
		this.m_oMengenErmittlung = oMengenErmittlung;
		
		Insets oInsets = new Insets(0,2,0,2);
		Insets oInsets2 = new Insets(35,2,5,2);
		
		MyE2_Row oRowForSimpleCalc = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());
		RowLayoutData oRowLayout = new RowLayoutData();
		oRowLayout.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
		oRowLayout.setInsets(oInsets);
		
		
		tfPreisMax = new UB_TextField("",80,10);
		tfPreisMax.setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		
		tfPreisMaxAVG = new UB_TextField("",80,10);
		tfPreisMaxAVG.setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		
		tfMengeFuerDurchschnittsPreis = new UB_TextField("",80,10);
		tfMengeFuerDurchschnittsPreis.setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		
		
		tfErgebnis = new UB_TextField();
		tfErgebnis.setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		tfErgebnis.set_bEnabled_For_Edit(false);
		
		tfErgebnisMengeAVG =new UB_TextField(); 
		tfErgebnisMengeAVG.setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		tfErgebnisMengeAVG.set_bEnabled_For_Edit(false);
		
		tfErgebnisPreisAVG = new UB_TextField();
		tfErgebnisPreisAVG.setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		tfErgebnisPreisAVG.set_bEnabled_For_Edit(false);
		
		tfErgebnisDurchschnittsPreis = new UB_TextField();
		tfErgebnisDurchschnittsPreis.setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		tfErgebnisDurchschnittsPreis.set_bEnabled_For_Edit(false);
		
		tfErgebnisDurchschnittsPreisMenge = new UB_TextField();
		tfErgebnisDurchschnittsPreisMenge.setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		tfErgebnisDurchschnittsPreisMenge.set_bEnabled_For_Edit(false);
		
		E2_MutableStyle oStyle = new E2_MutableStyle();
		oStyle.setProperty(CheckBox.PROPERTY_BORDER, new Border(0, new E2_ColorBase(-1), Border.STYLE_NONE));
		
		cbRecalculate = new MyE2_CheckBox(new MyE2_String("automatisch").CTrans(),oStyle);
		cbRecalculate.setToolTipText(new MyE2_String("Wenn aktiv, wird vor jeder Ermittlung der Lagerbestände eine Zuordnung der Lagerausgänge durchgeführt.").CTrans());
		
		// die Labels für die Menge
		lblEinheit = new MyE2_Label("");
		lblEinheitAVG = new MyE2_Label("");
		lblEinheitDurchschnittspreis = new MyE2_Label("");
		lblEinheitDurchschnittspreis1 = new MyE2_Label("");
		
		
		this.add(oRowForSimpleCalc, oInsets);
//		MyE2_Column oCol1 = new MyE2_Column(MyE2_Column.STYLE_NO_BORDER());
//		oCol1.add(new ATOM_LAG_BEW_LIST_BT_RECALCULATE(oNaviList,m_oMengenErmittlung,this),oInsets);
//		oCol1.add(cbRecalculate,oInsets);
		
//		oRowForSimpleCalc.add(new LAG_BEW_LIST_BT_RECALCULATE(oNaviList,m_oMengenErmittlung,this), oRowLayout);
//		oRowForSimpleCalc.add(oCol1,oRowLayout);
		
		Insets oInsetsGrid = new Insets(2,2,2,2);
		Alignment oAlignLeft = new Alignment(Alignment.LEFT, Alignment.CENTER);
		Alignment oAlignRight = new Alignment(Alignment.RIGHT, Alignment.CENTER);
		
				
		MyE2_Grid oGrid = new MyE2_Grid(9);
		//oGrid.setBackground(Color.RED);
		oGrid.setBorder(new Border(1,null,Border.STYLE_NONE));
		
		oGrid.add( new MyE2_Label(new MyE2_String("Lagerbestände mit EK bis ")),1,1,oInsetsGrid,oAlignRight);
		oGrid.add( tfPreisMax,1,1,oInsetsGrid,oAlignRight);
		oGrid.add( new MyE2_Label(new MyE2_String("Euro")),1,1,oInsetsGrid,oAlignLeft);
		oGrid.add( new ATOM_LAG_BEW_LIST_BT_GET_RESTMENGEN(oNaviList, ListSelector, this ),1,1,oInsetsGrid,oAlignRight);
		oGrid.add( new MyE2_Label(new MyE2_String(" -> ")),1,1,oInsetsGrid,oAlignRight);
		oGrid.add( tfErgebnis,1,1,oInsetsGrid,oAlignRight);
		oGrid.add( lblEinheit,3,1,oInsetsGrid,oAlignLeft);
		
		oGrid.add( new MyE2_Label(new MyE2_String("Lagerbestände mit durchschntl. EK bis ")),1,1,oInsetsGrid,oAlignRight);
		oGrid.add( tfPreisMaxAVG,1,1,oInsetsGrid,oAlignRight);
		oGrid.add( new MyE2_Label(new MyE2_String("Euro")),1,1,oInsetsGrid,oAlignLeft);
		oGrid.add( new ATOM_LAG_BEW_LIST_BT_GET_RESTMENGEN_AVG(oNaviList, ListSelector, this ),1,1,oInsetsGrid,oAlignRight);
		oGrid.add( new MyE2_Label(new MyE2_String(" -> ")),1,1,oInsetsGrid,oAlignRight);
		oGrid.add( tfErgebnisMengeAVG,1,1,oInsetsGrid,oAlignRight);
		oGrid.add( lblEinheitAVG,1,1,oInsetsGrid,oAlignLeft);
		oGrid.add( tfErgebnisPreisAVG,1,1,oInsetsGrid,oAlignRight);
		oGrid.add( new MyE2_Label(new MyE2_String("Euro")),1,1,oInsetsGrid,oAlignLeft);
		
		oGrid.add( new MyE2_Label(new MyE2_String("Durchschnittspreis der Lagerbestände ")),1,1,oInsetsGrid,oAlignRight);
		oGrid.add( tfMengeFuerDurchschnittsPreis,1,1,oInsetsGrid,oAlignRight);
		oGrid.add( lblEinheitDurchschnittspreis,1,1,oInsetsGrid,oAlignLeft);
		oGrid.add( new ATOM_LAG_BEW_LIST_BT_GET_AVGPREIS_VON_MENGE(oNaviList, ListSelector, this ),1,1,oInsetsGrid,oAlignRight);
		oGrid.add( new MyE2_Label(new MyE2_String(" -> ")),1,1,oInsetsGrid,oAlignRight);
		oGrid.add( tfErgebnisDurchschnittsPreisMenge,1,1,oInsetsGrid,oAlignRight);
		oGrid.add( lblEinheitDurchschnittspreis1,1,1,oInsetsGrid,oAlignRight);
		oGrid.add( tfErgebnisDurchschnittsPreis,1,1,oInsetsGrid,oAlignRight);
		oGrid.add( new MyE2_Label(new MyE2_String("Euro")),1,1,oInsetsGrid,oAlignLeft);

		oRowForSimpleCalc.add(oGrid,oInsets2);

	}
	
	

	public boolean getAutoCalc(){
		return cbRecalculate.isSelected();
	}
	
	public void setAutoCalc(boolean bAuto){
		cbRecalculate.setSelected(bAuto);
	}
	

	/** setzen der Labels für die Mengeneinheit
	 * 
	 * @param sText
	 */
	private void setLabelsEinheit(String sText){
		m_MengenEinheit = sText;
		this.lblEinheit.setText(sText);
		this.lblEinheitAVG.setText(sText);
		this.lblEinheitDurchschnittspreis.setText(sText);
		this.lblEinheitDurchschnittspreis1.setText(sText);
	}
	
	/**
	 * @param sErgebnis the tfErgebnis to set
	 */
	public void clearPanelResults() {
		// einfache Suche
		this.tfErgebnis.setText( "" );

		// Suche mit Durchschnittspreis
		this.tfErgebnisMengeAVG.setText("");
		this.tfErgebnisPreisAVG.setText("");

		// Suche der Mengen und ermitteln des Durchschnittspreises
		this.tfErgebnisDurchschnittsPreisMenge.setText("");
		this.tfErgebnisDurchschnittsPreis.setText("");
		
		setLabelsEinheit("");
	}
	
	
	/**
	 * @return the m_oMengenErmittlung
	 */
	public ATOM_LAG_Mengenermittlung_ext get_oMengenErmittlung() {
		return m_oMengenErmittlung;
	}

	
	@Override
	public void update(Observable arg0, Object arg1)
	{
		// Bekommt eine Observer-Nachricht, wenn die Einträge gelöscht wurden
		clearPanelResults();
	}

	/**
	 * @return the tfMenge
	 */
	public String getPreisMax() {
		return tfPreisMax.getText();
	}
	/**
	 * @param tfPreisMax the tfMenge to set
	 */
	public void setPreisMax(String sPreisMax) {
		this.tfPreisMax.setText(sPreisMax);
	}
	
	/**
	 * @return the tfErgebnis
	 */
	public String getErgebnis() {
		return tfErgebnis.getText();
	}
	
	public void setErgebnis(String Ergebnis) {
		this.tfErgebnis.setText(Ergebnis);
	}

	
	// Ergebnis der einfachen Mengenermittlung bei Maximalpreis-Angabe setzen
	public String getPreisAvg(){
		return tfPreisMaxAVG.getText();
	}
	public void setPreisAvg(String sPreisAvg){
		this.tfPreisMaxAVG.setText(sPreisAvg);
	}
	
	
	

	// Ergebnisse der Mengenermittlung bei Durchschnittspreis-Angabe setzen
	public void setErgebnisMengeAVG(String ErgebnisMengeAVG) {
		this.tfErgebnisMengeAVG.setText( ErgebnisMengeAVG);
	}
	public String getErgebnisMengeAVG() {
		return tfErgebnisMengeAVG.getText();
	}
	
	public void setErgebnisPreisAVG(String ErgebnisPreisAVG) {
		this.tfErgebnisPreisAVG.setText(ErgebnisPreisAVG);
	}

	public String getErgebnisPreisAVG() {
		return tfErgebnisPreisAVG.getText();
	}

	
	/**
	 * @return the tfMenge
	 */
	public String getMengeDurchschnittspreis() {
		return tfMengeFuerDurchschnittsPreis.getText();
	}
	/**
	 * @param tfPreisMax the tfMenge to set
	 */
	public void setMengeDurchschnittspreis(String sMenge) {
		this.tfMengeFuerDurchschnittsPreis.setText(sMenge);
	}

	
	// Ergebnisse der Durschnittspreis-Berechnungen setzen/lesen
	public void setErgebnisMengeDurchschnittspreis(String MengeDurchschnittspreis) {
		this.tfErgebnisDurchschnittsPreisMenge.setText( MengeDurchschnittspreis);
	}
	public String getErgebnisMengeDurchschnittspreis() {
		return tfErgebnisDurchschnittsPreisMenge.getText();
	}
	
	public void setErgebnisPreisDurchnittspreis(String PreisDurchschnittspreis) {
		this.tfErgebnisDurchschnittsPreis.setText(PreisDurchschnittspreis);
	}

	public String getErgebnisPreisDurchnittspreis() {
		return tfErgebnisDurchschnittsPreis.getText();
	}
	

	/**
	 * Setzen der Mengeneinheit auf dem Panel
	 * @param sMengenEinheit
	 */
	public void setMengenEinheit(String sMengenEinheit){
		setLabelsEinheit(sMengenEinheit);
	}
	
	
}
