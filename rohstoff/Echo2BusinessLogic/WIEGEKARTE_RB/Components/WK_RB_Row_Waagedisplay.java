package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components;


import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibNUM;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.Rec_WaageSettings;



public class WK_RB_Row_Waagedisplay extends E2_Grid {

	private MyE2_TextField 	m_tfGewicht = null;
	private MyE2_Button		m_btReadGewicht = null;
	private MyE2_Label		m_lblFehler = null;
	private XX_ActionAgent  m_oAgent = null;
	
	
	// Schalter im Objekt der von Aussen gesteuert und abgefragt wird,
	// wenn true, dann ein neues Temp-gewicht lesen lassen,
	// wenn false muss diese Waage kein Gewicht mehr lesen.
	private boolean 		m_bIsRequestingWeight = true;
	
	
	
	private Insets oIn = new Insets(0,0,10,0);
	
	private Rec_WaageSettings m_oRecSettings = null;
	
	
	public WK_RB_Row_Waagedisplay(Rec_WaageSettings oRecSettings) {
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
		this._setSize(100,100,500);
		this._a(m_tfGewicht,	new RB_gld()._ins(oIn));
		this._a(m_btReadGewicht,new RB_gld()._ins(oIn));
		this._a(m_lblFehler,	new RB_gld()._ins(oIn));
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
	 * Setzt den Text des Buttons 
	 * @param oText
	 */
	public void setButtonText(String oText){
		this.m_btReadGewicht.set_Text(oText);
	}
	
	
	
	
	/**
	 * Setzt den Gewichtswert
	 * @param sGewicht
	 */
	public void setGewicht(String sGewicht){
		this.setFehler(null);
		String sFormat = removeLeadingZeros(sGewicht.trim());

		try {
			MyBigDecimal mbd = new MyBigDecimal(sFormat);
			if (mbd != null) {
				sFormat = mbd.get_FormatedRoundedNumber(3);
			}
		} catch (Exception e) {
			sFormat = sGewicht;
		}
		
		this.m_tfGewicht.setText(sFormat);
	}
	
	
	/**
	 * remove leading Zeros from a string
	 * FROM https://www.geeksforgeeks.org/remove-leading-zeros-from-a-number-given-as-a-string/
	 * 
	 * @author manfred
	 * @date 18.05.2021
	 *
	 * @param number
	 * @return
	 */
	private String removeLeadingZeros(String number) {
	           // Regex to remove leading
	        // zeros from a string
	        String regex = "^0+(?!$)";
	 
	        // Replaces the matched
	        // value with given string
	        number = number.replaceAll(regex, "");
	 
	    return number;
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
	public Rec_WaageSettings getWaageSettings(){
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
