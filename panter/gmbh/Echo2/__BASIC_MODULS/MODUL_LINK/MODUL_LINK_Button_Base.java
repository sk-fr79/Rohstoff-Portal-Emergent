package panter.gmbh.Echo2.__BASIC_MODULS.MODUL_LINK;

import java.util.Vector;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MODUL_CONNECT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

/*
 * Abstrakte Basisklasse für einen Button mit Tooltip und einem Label aus der Beschreibung in der 
 * Tabelle MODUL_CONNECT
 */
public abstract class MODUL_LINK_Button_Base extends MODUL_LINK_Object_Base {

	protected MyE2_Button m_button = null;
	protected MyE2_Label m_label_description = null;
	
	// zeigt extra Label neben dem Button an
	protected boolean m_display_label = true;
	
	// zeigt einen Beschreibungstext als Tooltip an
	private boolean m_display_tooltip = true;
	
	// zeigt den Text im Button an, sonst nur ICON
	protected boolean m_showTextInButton = true;
	
	
	
	/**
	 * Konstruktor für den Button 
	 * @param oRec
	 * @param ContainerToClose
	 * @throws myException
	 */
	public MODUL_LINK_Button_Base( RECORD_MODUL_CONNECT oRec, Vector<E2_BasicModuleContainer> ContainerToClose) throws myException {
		super(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		this.setLinkRecord(oRec);
		m_ContainerToClose = ContainerToClose;
		
	}
		


	public MODUL_LINK_Button_Base( Vector<RECORD_MODUL_CONNECT> vRec, Vector<E2_BasicModuleContainer> ContainerToClose) throws myException {
		super(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		this.addLinkRecords(vRec);
		m_ContainerToClose = ContainerToClose;
		
	}

	
	/**
	 * Initialisiert den Button
	 * @throws myException
	 */
	protected void initButton() throws myException{
		
		try {
			m_button = createButton();
		} catch (myException e) {
			e.printStackTrace();
		}

		if (is_DisplayLabel()) {
			
			try {
				m_label_description = createLabel();
			} catch (myException e) {
				e.printStackTrace();
			}
			
		}
	
		if (m_button != null || m_label_description != null) {

			Insets in = E2_INSETS.I_0_0_0_0;

			if (m_button != null) {
				m_button.add_oActionAgent(set_actionAgentButton());

				this.add(m_button, in);
				in = E2_INSETS.I_10_0_0_0;
			}
			
			if (m_label_description != null ) {
				this.add(m_label_description, in);
			}

		}
	}

	
	/**
	 * Erzeugen des angezeigten Buttons Default-Button
	 * 
	 * @return
	 * @throws myException
	 */
	protected MyE2_Button createButton() throws myException {
		MyE2_Button o = null;
		RECORD_MODUL_CONNECT m_rec = this.getLinkRecord();
		
		if (m_rec != null){

			// Button erzeugen
			o = new MyE2_Button(E2_ResourceIcon.get_RI("kompass.png"),E2_ResourceIcon.get_RI("leer.png"));
			
			// prüfen, ob ein Text in den Button gesetzt werden muss
			String sText = null;
			if ( is_showTextInButton() ){
				if(!bibALL.isEmpty(m_rec.get_BESCHREIBUNG_cUF()) ){
					sText = m_rec.get_BESCHREIBUNG_cUF_NN("?");
					
//					o.setText( m_rec.get_BESCHREIBUNG_cUF_NN("?"));
//					o.setStyle(MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder());
				} else {
					// Standard-Beschreibung eintragen
					if(v_rec.size() == 1){
						sText = v_rec.get(0).get_BESCHREIBUNG_cUF();
					} else if (v_rec.size() > 1){
						sText = v_rec.get(0).get_BESCHREIBUNG_cUF()+ " (" + Integer.toString(v_rec.size()) + ")";
					}
				}
			}

			if (!bibALL.isEmpty(sText)){
				// maximal 30 Zeichen + ...
				if (sText.length() > 33){
					sText = sText.substring(0, 29) + "...";
				}
				o.setText( sText);
				o.setStyle(MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder());
			}

			
			// prüfen, ob der Tooltip geschrieben werden soll (falls es mehr als ein Ziel gibt, wird auch die Anzahl der Ziele angezeigt) 
			if (is_DisplayTooltip()) {
				if(v_rec.size() == 1){
					o.setToolTipText(v_rec.get(0).get_BESCHREIBUNG_cUF());
				} else if (v_rec.size() > 1){
					o.setToolTipText(v_rec.get(0).get_BESCHREIBUNG_cUF()+ " (" + Integer.toString(v_rec.size()) + ")");
				}
			}
		}
		return o;
	}

	
	/**
	 * Erzeugen des angezeigten Labels rechts neben dem Button
	 * 
	 * @return
	 * @throws myException
	 */
	protected MyE2_Label createLabel() throws myException {
		MyE2_Label o = null;
		
		// nur erzeugen, wenn auch definiert
		if(! is_DisplayLabel() ){
			
			if (v_rec.size() == 1){
				o = new MyE2_Label(v_rec.get(0).get_BESCHREIBUNG_cUF());
			} else if (v_rec.size() > 1){
				o = new MyE2_Label(v_rec.get(0).get_BESCHREIBUNG_cUF() + " (" + Integer.toString(v_rec.size()) + ")");
			}
			
		}
		return o;
		
	}

	
	/**
	 * @param m_display_label the m_display_label to set
	 */
	public void set_DisplayLabel(boolean m_display_label) {
		this.m_display_label = m_display_label;
	}

	/**
	 * @return the m_display_label
	 */
	public boolean is_DisplayLabel() {
		return m_display_label;
	}


	/**
	 * @param bShowLabel
	 */
	public void set_showTextInButton(boolean bShowLabel){
		m_showTextInButton =  bShowLabel;
	}
	
	
	/**
	 * 
	 * @return m_showLabelInButton
	 */
	public boolean is_showTextInButton()
	{
		return m_showTextInButton;
	}

	
	/**
	 * @param m_display_tooltip the m_display_tooltip to set
	 */
	public void set_DisplayTooltip(boolean m_display_tooltip) {
		this.m_display_tooltip = m_display_tooltip;
	}

	
	/**
	 * @return the m_display_tooltip
	 */
	public boolean is_DisplayTooltip() {
		return m_display_tooltip;
	}

	
	/**
	 * Erzeugen des ActionAgents des Buttons Standardmäßig ist der Agent leer
	 * und zeigt nur eine Meldung an
	 * 
	 * @return
	 */
	protected abstract XX_ActionAgent set_actionAgentButton()
			throws myException;

	
	@Override
	public void set_bEnabled(boolean bEnable) throws myException {
		this.m_button.set_bEnabled_For_Edit(bEnable);
	}
	

}
