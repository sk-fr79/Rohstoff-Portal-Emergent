package rohstoff.Echo2BusinessLogic.WIEGEKARTE;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WAAGE_SETTINGS;
import panter.gmbh.indep.exceptions.myException;



public class WK_Row_Waagedisplay extends MyE2_Row {

	private MyE2_TextField 	m_tfGewicht = null;
	private MyE2_Button		m_btReadGewicht = null;
	private MyE2_Label		m_lblFehler = null;
	private XX_ActionAgent  m_oAgent = null;
	
	
	// Schalter im Objekt der von Aussen gesteuert und abgefragt wird,
	// wenn true, dann ein neues Temp-gewicht lesen lassen,
	// wenn false muss diese Waage kein Gewicht mehr lesen.
	private boolean 		m_bIsRequestingWeight = true;
	
	
	
	private Insets oIn = new Insets(0,0,10,0);
	
	private RECORD_WAAGE_SETTINGS m_oRecSettings = null;
	
	
	public WK_Row_Waagedisplay(RECORD_WAAGE_SETTINGS oRecSettings) {
		super();
		this.m_oRecSettings = oRecSettings;
		m_tfGewicht = new MyE2_TextField("-", 100, 20);
		m_lblFehler = new MyE2_Label();
		m_lblFehler.setVisible(false);
		m_lblFehler.setStyle(MyE2_Label.STYLE_NORMAL_BOLD());
		
		m_tfGewicht.setAlignment(Alignment.ALIGN_RIGHT);
		m_tfGewicht.setBackground(Color.GREEN);
		m_tfGewicht.setForeground(Color.BLACK);
		
		m_btReadGewicht = new MyE2_Button(new MyE2_String("Wägung durchführen"));
		
		this.add(m_tfGewicht,oIn);
		this.add(m_btReadGewicht,oIn);
		this.add(m_lblFehler,oIn);
	}
	
	
	
	/**
	 * Bindet einen Action Agent an den Button an.
	 * Wenn oAgent == null, wird der ActionAgent entbunden.
	 * @param oAgent
	 */
	public void setActionAgent(XX_ActionAgent oAgent){
		if (oAgent != null){
			this.m_btReadGewicht.add_oActionAgent(oAgent);
			m_oAgent = oAgent;
		} else {
			m_btReadGewicht.remove_oActionAgent(m_oAgent);
		}
	}

	
	
	/**
	 * Setzt den Text des Buttons 
	 * @param oText
	 */
	public void setButtonText(MyE2_String oText){
		this.m_btReadGewicht.set_Text(oText.toString());
	}
	
	
	/**
	 * Setzt den Gewichtswert
	 * @param sGewicht
	 */
	public void setGewicht(String sGewicht){
		this.setFehler(null);
		this.m_tfGewicht.setText(sGewicht);
	}
	
	
	/**
	 * löscht die Waagezeile
	 */
	public void clear(){
		this.setFehler(null);
		this.m_tfGewicht.setText("-");
	}
	
	
	/**
	 * Setzt eine Fehlermeldung
	 * wenn sFehler == null dann wird der Fehler gelöscht
	 * @param sFehler
	 */
	public void setFehler(MyE2_String oFehler){
		if (oFehler != null){
			m_lblFehler.setText(oFehler.CTrans());
			m_lblFehler.setVisible(true);
//			m_lblFehler.setBackground(Color.RED);
//			m_tfGewicht.setText("*FEHLER*");
			m_tfGewicht.setBackground(Color.RED);
		} else {
			m_lblFehler.setText("");
			m_lblFehler.setVisible(false);
			m_tfGewicht.setText("");
			m_tfGewicht.setBackground(Color.GREEN);
		}
	}
	
	
	/**
	 * gibt den WaageSettingsRecord zurück
	 * @return
	 */
	public RECORD_WAAGE_SETTINGS getWaageSettings(){
		return m_oRecSettings;
	}
	
	
	public void setRowEnabled(boolean enabled) throws myException{
		try {
			m_btReadGewicht.set_bEnabled_For_Edit(enabled);
		} catch (myException e) {
			throw new myException(new MyE2_String("Fehler beim Statuswechsel des Waage-Buttons.").toString());
		}
	}


	/**
	 * @param m_bIsRequestingWeight the m_bIsRequestingWeight to set
	 */
	public void setRequestingWeight(boolean m_bIsRequestingWeight) {
		this.m_bIsRequestingWeight = m_bIsRequestingWeight;
	}


	/**
	 * @return the m_bIsRequestingWeight
	 */
	public boolean isRequestingWeight() {
		return m_bIsRequestingWeight;
	}
	
}
