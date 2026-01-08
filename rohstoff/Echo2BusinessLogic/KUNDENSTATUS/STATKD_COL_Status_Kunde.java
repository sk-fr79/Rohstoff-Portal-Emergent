package rohstoff.Echo2BusinessLogic.KUNDENSTATUS;

import java.math.BigDecimal;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.XX_ActionAgentJumpToTargetList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class STATKD_COL_Status_Kunde extends MyE2_Column {

	private enum JUMP_TYPE  {
		
			FUHREN_FORDERUNGEN, 
			FUHREN_FORDERUNGEN_GEPLANT,
			FUHREN_VERBINDLICHKEITEN, 
			FUHREN_VERBINDLICHKEITEN_GEPLANT, 
			FUHREN_ALLE, 
			FUHREN_GEPLANT_ALLE, 
			
	}
	
	
	
	private MyE2_Grid m_oGridInnen = null;
	
	private MyE2_Label m_lblDescForderungen = null;
	private MyE2_Label m_lblDescVerbindlichkeiten = null;
	private MyE2_Label m_lblDescFibu = null;
	private MyE2_Label m_lblFordFibu = null;
	private MyE2_Label m_lblVerbFibu = null;
	private MyE2_Label m_lblDescRech = null;
	private MyE2_Label m_lblFordRech = null;
	private MyE2_Label m_lblVerbRech = null;
	private MyE2_Label m_lblDescFP = null;
	private MyE2_Label m_lblFordFP = null;
	private MyE2_Label m_lblVerbFP = null;
	private MyE2_Label m_lblDescFuhre = null;
	private MyE2_Label m_lblFordFuhre = null;
	private MyE2_Label m_lblVerbFuhre = null;
	private MyE2_Label m_lblDescGepl = null;
	private MyE2_Label m_lblFordGepl = null;
	private MyE2_Label m_lblVerbGepl = null;
	private MyE2_Label m_lblDescGes = null;
	private MyE2_Label m_lblFordGes = null;
	private MyE2_Label m_lblVerbGes = null;
	private MyE2_Label m_lblDescGesAlle = null;
	private MyE2_Label m_lblGesAlle = null;
	
	// Inner Grid für Label und Sprungbutton
	private E2_Grid m_gridFuhren_alle = null;
	private E2_Grid m_gridFuhreForderungen = null;
	private E2_Grid m_gridFuhreVerbindlichkeiten = null;
	
	private E2_Grid m_gridFuhreGeplant_alle = null;
	private E2_Grid m_gridFuhreForderungenGeplant = null;
	private E2_Grid m_gridFuhreVerbindlichkeitenGeplant = null;
	
	// Sprungbuttons zu den Fuhren
	private btnJumpToFuhrenListe m_btnFuhreForderungen_alle = null;
	private btnJumpToFuhrenListe m_btnFuhreForderungen = null;
	private btnJumpToFuhrenListe m_btnFuhreForderungenGeplant = null;
	
	private btnJumpToFuhrenListe m_btnFuhreVerbindlichkeiten_alle = null;
	private btnJumpToFuhrenListe m_btnFuhreVerbindlichkeiten = null;
	private btnJumpToFuhrenListe m_btnFuhreVerbindlichkeitenGeplant = null;
	
	
	// 2 Vektoren, in einem werden die Referenzen auf die Headings abgelegt, im anderen auf die Texte 
	private Vector<MyE2_Label> m_vLabelHeading = new Vector<MyE2_Label>();
	private Vector<MyE2_Label> m_vLabelText = new Vector<MyE2_Label>();
	
	
	private MutableStyle m_Style_Heading = null;
	private MutableStyle m_Style_Text_Positive = null;
	private MutableStyle m_Style_Text_Negative = null;
	
	private String    m_IDAdresse = null;

	private E2_BasicModuleContainer m_parent_container = null;
	

	
	public STATKD_COL_Status_Kunde(E2_BasicModuleContainer caller) {
		super();
		m_parent_container = caller;
		this.initComponent();
	}

	public STATKD_COL_Status_Kunde() {
		this(null);
		
	}
	
	public STATKD_COL_Status_Kunde(MutableStyle oStyle,E2_BasicModuleContainer caller) {
		super(oStyle);
		m_parent_container = caller;
		this.initComponent();
	}

	
	public void setIDAdresse (String IDAdresse){
		this.m_IDAdresse = IDAdresse;
		
	}
	
	

	
	
	
	
	/**
	 * Lesen der Daten und aktualisieren der Anzeige
	 */
	public void refresh() throws myException {
		if (m_IDAdresse == null){
			throw new myException("STATKD_COL_Status_Kunde::refresh(): Es ist keine AdresseID angegeben worden.");
		}
		
		clearLabelValue(m_lblFordFibu );
		clearLabelValue(m_lblVerbFibu);
		
		clearLabelValue(m_lblFordRech);
		clearLabelValue(m_lblVerbRech );
		
		clearLabelValue(m_lblFordFP);
		clearLabelValue(m_lblVerbFP);
		
		clearLabelValue(m_lblFordFuhre);
		clearLabelValue(m_lblVerbFuhre);
		
		clearLabelValue(m_lblFordGepl);
		clearLabelValue(m_lblVerbGepl);

		clearLabelValue(m_lblFordGes);
		clearLabelValue(m_lblVerbGes);

		clearLabelValue(m_lblGesAlle);
		
		
		m_btnFuhreForderungen_alle.setCurrentRow(null);
		m_btnFuhreForderungen.setCurrentRow(null);
		m_btnFuhreForderungenGeplant.setCurrentRow(null);
		
		m_btnFuhreVerbindlichkeiten_alle.setCurrentRow(null);
		m_btnFuhreVerbindlichkeiten.setCurrentRow(null);
		m_btnFuhreVerbindlichkeitenGeplant.setCurrentRow(null);
		
		
		STATKD_StatusErmittlung oStatus = new STATKD_StatusErmittlung();

		
		if (m_IDAdresse.equals("")){
			return;
		}
		
		
		
		STATKD_DataRowStatus oRow = oStatus.ErmittleKundenStatus(m_IDAdresse);
		
		
		
		if (oRow != null){
			
			m_btnFuhreForderungen_alle.setCurrentRow(oRow);
			m_btnFuhreForderungen.setCurrentRow(oRow);
			m_btnFuhreForderungenGeplant.setCurrentRow(oRow);
			
			m_btnFuhreVerbindlichkeiten_alle.setCurrentRow(oRow);
			m_btnFuhreVerbindlichkeiten.setCurrentRow(oRow);
			m_btnFuhreVerbindlichkeitenGeplant.setCurrentRow(oRow);
			
			setLabelValue(m_lblFordFibu,oRow.get_FIBU_FORDERUNG() );
			setLabelValue(m_lblVerbFibu,oRow.get_FIBU_VERBINDLICHKEIT() );
			
			setLabelValue(m_lblFordRech,oRow.get_RECH_FORDERUNG() );
			setLabelValue(m_lblVerbRech,oRow.get_RECH_VERBINDLICHKEIT() );
			
			setLabelValue(m_lblFordFP, oRow.get_FREIEPOS_FORDERUNG());
			setLabelValue(m_lblVerbFP, oRow.get_FREIEPOS_VERBINDLICHKEIT());
			
			setLabelValue(m_lblFordFuhre, oRow.get_FUHRE_FORDERUNG());
			setLabelValue(m_lblVerbFuhre, oRow.get_FUHRE_VERBINDLICHKEIT());
			
			setLabelValue(m_lblFordGepl, oRow.get_FUHRE_FORDERUNG_GEPLANT());
			setLabelValue(m_lblVerbGepl, oRow.get_FUHRE_VERBINDLICHKEIT_GEPLANT());

			setLabelValue(m_lblFordGes, oRow.get_Gesamt_FORDERUNG());
			setLabelValue(m_lblVerbGes, oRow.get_Gesamt_VERBINDLICHKEIT());

			setLabelValue(m_lblGesAlle, oRow.get_Gesamt());

		}
	}
	
	

	private String BD2String(BigDecimal bd ){
		return bibALL.makeDez(bd.doubleValue(), 2, true);
	}
	
	
	private void clearLabelValue(MyE2_Label lbl){
		lbl.setStyle(m_Style_Text_Positive);
		lbl.setText("-");
	}
	
	/**
	 * Setzt den Wert des Labels und setzt auch den zugehprigen Style
	 * @author manfred
	 * @date   16.04.2013
	 * @param lbl
	 * @param bdValue
	 */
	private void setLabelValue(MyE2_Label lbl, BigDecimal bdValue){
		lbl.setStyle(m_Style_Text_Positive);
		lbl.setText(BD2String(bdValue)) ;
	
		if(bdValue.compareTo(BigDecimal.ZERO) < 0){
			lbl.setStyle(m_Style_Text_Negative);
		}	
	
	}
	
	
	
	/**
	 * Aufbau der Col mit dem inneren Grid 
	 */
	private void initComponent(){
		
		set_Style_Heading(MyE2_Label.STYLE_TITEL_BIG());
		
		set_Style_Text_Positive(MyE2_Label.STYLE_TITEL_BIG_PLAIN());
		
		set_Style_Text_Negative(MyE2_Label.STYLE_TITEL_BIG_PLAIN_RED());
		
		
		Insets oInsets = E2_INSETS.I_4_4_4_4;
		Alignment oAlign = new Alignment(Alignment.RIGHT, Alignment.CENTER);
		
		m_oGridInnen = new MyE2_Grid(8, 1);

		m_lblDescForderungen = new MyE2_Label("Forderungen");
		m_lblDescVerbindlichkeiten = new MyE2_Label("Verbindlichkeiten");
		
		m_lblDescFibu = new MyE2_Label("Fibu");
		m_lblFordFibu = new MyE2_Label("");
		m_lblVerbFibu = new MyE2_Label("");
		
		m_lblDescRech = new MyE2_Label("Rechnungen");
		m_lblFordRech =new MyE2_Label("");
		m_lblVerbRech = new MyE2_Label("");
		
		m_lblDescFP = new MyE2_Label("Freie Positionen");
		m_lblFordFP = new MyE2_Label("");
		m_lblVerbFP = new MyE2_Label("");
		
		m_gridFuhren_alle 					= new E2_Grid();
		m_gridFuhreForderungen 				= new E2_Grid();
		m_gridFuhreForderungenGeplant 		= new E2_Grid();
		m_gridFuhreGeplant_alle 			= new E2_Grid();
		m_gridFuhreVerbindlichkeiten 		= new E2_Grid();
		m_gridFuhreVerbindlichkeitenGeplant = new E2_Grid();
		
		
		m_lblDescFuhre = new MyE2_Label("Fuhren");
		m_lblFordFuhre = new MyE2_Label("");
		m_lblVerbFuhre = new MyE2_Label("");
		
		m_lblDescGepl = new MyE2_Label("Geplante Fuhren");
		m_lblFordGepl = new MyE2_Label("");
		m_lblVerbGepl = new MyE2_Label("");
		
		try {
			m_btnFuhreForderungen_alle 			= new btnJumpToFuhrenListe(JUMP_TYPE.FUHREN_ALLE,m_parent_container);
			m_btnFuhreForderungen 				= new btnJumpToFuhrenListe(JUMP_TYPE.FUHREN_FORDERUNGEN,m_parent_container);
			m_btnFuhreForderungenGeplant 		= new btnJumpToFuhrenListe(JUMP_TYPE.FUHREN_FORDERUNGEN_GEPLANT,m_parent_container);
			
			m_btnFuhreVerbindlichkeiten_alle	= new btnJumpToFuhrenListe(JUMP_TYPE.FUHREN_GEPLANT_ALLE,m_parent_container);
			m_btnFuhreVerbindlichkeiten 		= new btnJumpToFuhrenListe(JUMP_TYPE.FUHREN_VERBINDLICHKEITEN,m_parent_container);
			m_btnFuhreVerbindlichkeitenGeplant 	= new btnJumpToFuhrenListe(JUMP_TYPE.FUHREN_VERBINDLICHKEITEN_GEPLANT,m_parent_container);
		} catch (myException e1) {
			//
		}
		
		
		
		// Label und Sprungbutton in Grid zusammenfassen
		m_gridFuhren_alle._s(2)._a(m_lblDescFuhre)._a(m_btnFuhreForderungen_alle);
		m_gridFuhreForderungen._s(2)._a(m_lblFordFuhre)._a(m_btnFuhreForderungen);
		m_gridFuhreForderungenGeplant._s(2)._a(m_lblFordGepl)._a(m_btnFuhreForderungenGeplant);

		m_gridFuhreGeplant_alle._s(2)._a(m_lblDescGepl)._a(m_btnFuhreVerbindlichkeiten_alle);
		m_gridFuhreVerbindlichkeiten._s(2)._a(m_lblVerbFuhre)._a(m_btnFuhreVerbindlichkeiten);
		m_gridFuhreVerbindlichkeitenGeplant._s(2)._a(m_lblVerbGepl)._a(m_btnFuhreVerbindlichkeitenGeplant);
		
		m_lblDescGes = new MyE2_Label("Gesamt");
		m_lblFordGes = new MyE2_Label("");
		m_lblVerbGes = new MyE2_Label("");
		
		m_lblDescGesAlle = new MyE2_Label("Gesamt Alles");
		m_lblGesAlle = new MyE2_Label("");
		
		// grid-size

		
		
		// 1. Zeile Überschriften
		m_oGridInnen.add(new MyE2_Label(""),  1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblDescFibu , 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblDescRech , 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblDescFP , 1, 1, oInsets, oAlign);
//		m_oGridInnen.add(m_lblDescFuhre , 1,1, oInsets, oAlign);
//		m_oGridInnen.add(m_lblDescGepl , 1,1, oInsets, oAlign);
		m_oGridInnen.add(m_gridFuhren_alle, 1,1, oInsets, oAlign);
		m_oGridInnen.add(m_gridFuhreGeplant_alle , 1,1, oInsets, oAlign);
		m_oGridInnen.add(m_lblDescGes , 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblDescGesAlle , 1, 1, oInsets, oAlign);
		
		// 2. Zeile: Forderungen
		m_oGridInnen.add(m_lblDescForderungen, 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblFordFibu , 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblFordRech , 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblFordFP , 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_gridFuhreForderungen , 1,1, oInsets, oAlign);
		m_oGridInnen.add(m_gridFuhreForderungenGeplant , 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblFordGes , 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblGesAlle , 1, 2 , oInsets,oAlign);

		
		// 3. Zeile: Verbindlichkeiten
		m_oGridInnen.add(m_lblDescVerbindlichkeiten, 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblVerbFibu , 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblVerbRech , 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblVerbFP , 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_gridFuhreVerbindlichkeiten , 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_gridFuhreVerbindlichkeitenGeplant , 1, 1, oInsets, oAlign);
		m_oGridInnen.add(m_lblVerbGes , 1, 1, oInsets, oAlign);
		
		
		// die Labels einordnen in Heading und Text
		m_vLabelHeading.add(m_lblDescForderungen);
		m_vLabelHeading.add(m_lblDescFibu);
		m_vLabelHeading.add(m_lblDescFP);
		m_vLabelHeading.add(m_lblDescFuhre);
		m_vLabelHeading.add(m_lblDescGepl);
		m_vLabelHeading.add(m_lblDescGes);
		m_vLabelHeading.add(m_lblDescGesAlle);
		m_vLabelHeading.add(m_lblDescRech);
		m_vLabelHeading.add(m_lblDescVerbindlichkeiten);
		
		m_vLabelText.add(m_lblFordFibu );
		m_vLabelText.add(m_lblFordFP );
		m_vLabelText.add(m_lblFordFuhre );
		m_vLabelText.add(m_lblFordGepl );
		m_vLabelText.add(m_lblFordGes );
		m_vLabelText.add(m_lblFordRech );
		m_vLabelText.add(m_lblVerbFibu );
		m_vLabelText.add(m_lblVerbFP );
		m_vLabelText.add(m_lblVerbFuhre );
		m_vLabelText.add(m_lblVerbGepl );
		m_vLabelText.add(m_lblVerbGes );
		m_vLabelText.add(m_lblVerbRech );
		
		m_vLabelText.add(m_lblGesAlle );

		
		this.setStyleHeadingAll();
		
		try {
			m_IDAdresse = "";
			this.refresh();
			m_IDAdresse = null;

		} catch (myException e) {
			// do nothing;
		}
		
		this.add(m_oGridInnen);
	}
	
	
	/**
	 * gibt das innere Grid zurück, um Properties zu setzen
	 * @return
	 */
	public MyE2_Grid  getInnerGrid(){
		return m_oGridInnen;
	}

	
	/** 
	 * Setzt den Style für alle Heading-Felder 
	 * @author manfred
	 * @date   15.04.2013
	 */
	private void setStyleHeadingAll(){
		for (MyE2_Label lbl : m_vLabelHeading){
			lbl.setStyle(m_Style_Heading);
		}
	}
	
	
	/**
	 * Setzt den MutableStyle für die Überschriften-Labels
	 * @param m_Style_Heading the m_Style_Heading to set
	 */
	public void set_Style_Heading(MutableStyle m_Style_Heading) {
		this.m_Style_Heading = m_Style_Heading;
		
		this.setStyleHeadingAll();
	}

	
	/**
	 * Setzt den MutableStyle für die Daten-Labels mit positiven Werten
	 * @param Style_Text_Positive the m_Style_Text_Positive to set
	 */
	public void set_Style_Text_Positive(MutableStyle Style_Text_Positive) {
		this.m_Style_Text_Positive = Style_Text_Positive;
	}

	/**
	 * Setzt den MutableStyle für die Daten-Labels mit negativen Werten
	 * @param Style_Text_Negative the m_Style_Text_Negative to set
	 */
	public void set_Style_Text_Negative(MutableStyle Style_Text_Negative) {
		this.m_Style_Text_Negative = Style_Text_Negative;
	}

	
	/**
	 * Springe zu den betroffenen Fuhren.
	 * @author manfred
	 * @date 05.07.2018
	 *
	 */
	private class btnJumpToFuhrenListe extends MyE2_Button
	{
		STATKD_DataRowStatus currentRow = null;
		JUMP_TYPE 			 jumpType = null;
		
		public btnJumpToFuhrenListe( JUMP_TYPE type, E2_BasicModuleContainer parentContainer) throws myException
		{
			//super(E2_ResourceIcon.get_RI("liste_freie_positionen.png"));
			super(E2_ResourceIcon.get_RI("kompass_fuhre.png"));
			this.setToolTipText(new MyE2_String("Sprung zu allen Fuhren die zur Berechnung dieser Werte beteiligt sind.").CTrans());
			jumpType = type;
			
			this.__setImages(E2_ResourceIcon.get_RI("kompass_fuhre.png"), E2_ResourceIcon.get_RI("leer.png"));
			this.setStyle(MyE2_Button.StyleImageButton());
			
			this.add_oActionAgent(new actionAgentSprungZuFuhren(parentContainer));
		}

		
		/**
		 * setzt die aktuelle Status-Row 
		 * @author manfred
		 * @date 05.07.2018
		 *
		 * @param row
		 */
		public void setCurrentRow(STATKD_DataRowStatus row){
			this.currentRow = row;
		}
		
		
		
		
		private class actionAgentSprungZuFuhren extends XX_ActionAgentJumpToTargetList
		{

			public actionAgentSprungZuFuhren(E2_BasicModuleContainer parentContainer) throws myException 
			{
				super(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER, "Zeige Alle Fuhren die beteiligt sind",parentContainer);
			}

			@Override
			public Vector<String> get_vID_Target(ExecINFO oExecInfo) throws myException 
			{
				Vector<String>  vPOS = null;
				// empty Vector
				vPOS = new Vector<>();


				
				if (currentRow != null){
					switch(jumpType){
					
					case FUHREN_FORDERUNGEN:
						vPOS = currentRow.getIDs_Fuhre_Forderungen();
						break;
					case FUHREN_FORDERUNGEN_GEPLANT:
						vPOS = currentRow.getIDs_Fuhre_Forderungen_geplant();
						break;
					case FUHREN_VERBINDLICHKEITEN:
						vPOS = currentRow.getIDs_Fuhre_Verbindlichkeiten();
						break;
					case FUHREN_VERBINDLICHKEITEN_GEPLANT:
						vPOS = currentRow.getIDs_Fuhre_Verbindlichkeiten_geplant();
						break;
						
					case FUHREN_ALLE:
						vPOS.addAll(currentRow.getIDs_Fuhre_Forderungen());
						vPOS.addAll(currentRow.getIDs_Fuhre_Verbindlichkeiten());
						break;
					case FUHREN_GEPLANT_ALLE:
						vPOS.addAll(currentRow.getIDs_Fuhre_Verbindlichkeiten_geplant());
						vPOS.addAll(currentRow.getIDs_Fuhre_Forderungen_geplant());
						break;
					
					default:
						break;
					}
				}
				return vPOS;
			}


			
			@Override
			public MyE2_MessageVector  OVERRIDE_PRUEFE_OB_SPRUNG_MOEGLICH_IST(Vector<String> vTargetList) throws myException
			{
				MyE2_MessageVector  oMV = new MyE2_MessageVector();

				if (vTargetList.size()==0)
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Zielmodul: <",true,this.get_cLesbarerModulName(),false,"> enthält keine passenden Einträge !",true)));
				}
				return oMV;
			}

		}
	}
	
}
