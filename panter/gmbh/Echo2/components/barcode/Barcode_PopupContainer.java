package panter.gmbh.Echo2.components.barcode;

import java.util.Vector;

import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonWithKey;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;
import echopointng.KeyStrokeListener;

public class Barcode_PopupContainer extends E2_BasicModuleContainer{

	private String  m_sCaption = "";
	private String  m_sBeschreibung ="";
	
	private int     m_Width = 290;
	private int 	m_Height = 180;
	
	private XX_ActionAgent 	m_ActionAgentOK = null;
	private XX_ActionAgent 	m_ActionAgentCancel = null;
	
	private MyE2_ButtonWithKey    	m_btnOk = null;

	private MyE2_Button   	m_btnCancel = null;
	private MyE2_TextField  m_tfBarcode = null;
	
	private MyE2_Label		m_lblStatus = null;
	
	private MyE2_Grid     m_gridMain = null;
	
	private Vector<String>  m_Prefix = new Vector<String>();
	
		
	
	public Barcode_PopupContainer( String sDialogTitel , String sBeschreibung)
	{
		super();
		this.m_sCaption = sDialogTitel;
		this.m_sBeschreibung = sBeschreibung;
		
		m_gridMain = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		m_btnOk = new MyE2_ButtonWithKey(new MyString("Ok"), E2_ResourceIcon.get_RI("save.png"), E2_ResourceIcon.get_RI("save__.png"),KeyStrokeListener.VK_RETURN);
		m_btnOk.add_oActionAgent(new actionAgentClose());
		
		m_btnCancel = new MyE2_Button(new MyString("Abbrechen"), E2_ResourceIcon.get_RI("cancel.png"), E2_ResourceIcon.get_RI("cancel__.png"));
		m_btnCancel.add_oActionAgent(new actionAgentClose());

		m_tfBarcode = new MyE2_TextField("",150,100);
		m_lblStatus = new MyE2_Label("");
		
		
		this.add (new MyE2_Label(m_sBeschreibung, MyE2_Label.STYLE_TITEL_BIG()),E2_INSETS.I_10_5_0_0);
		this.add(m_lblStatus,E2_INSETS.I_10_5_0_0);
		
		
		// zeile
		m_gridMain.add(new MyE2_Label("Barcode:"),E2_INSETS.I_10_10_10_10);
		m_gridMain.add(m_tfBarcode,E2_INSETS.I_10_10_10_10);

		// Zeile
		MyE2_Row rowButtons = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS_LEFT_TOP());
		rowButtons.add(m_btnOk);
		rowButtons.add(m_btnCancel);
		
		// zeile
		m_gridMain.add(new MyE2_Label());
		m_gridMain.add(rowButtons,E2_INSETS.I_10_0_0_0);

		this.add(m_gridMain);
		
		ApplicationInstance.getActive().setFocusedComponent(m_tfBarcode);
		
	}

		
	public void ShowPopup() throws myException {
		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(m_Width), new Extent(m_Height), new MyE2_String(m_sCaption));
	}

	public void ClosePopup(boolean bStatus) throws myException {
		this.CLOSE_AND_DESTROY_POPUPWINDOW(bStatus);
	}
	
	
	/**
	 * hinzufügen eines Prefixes, der im Scan-String vorhanden sein muss.
	 * Ist ein Prefix gesetzt und er ist nicht vorhanden, wird ein Fehler generiert.
	 * @param prefix
	 */
	public void addPrefix(String prefix){
		m_Prefix.add(prefix);
	}
	
	public void addPrefix(Vector<String> vPrefix){
		m_Prefix.addAll(vPrefix);
	}
	
	public void clearPrefix(){
		m_Prefix.removeAllElements();
	}
	
	
	
	/**
	 * Setzt zusätzlichen Actionagent für den Cancel-Button. 
	 * Default: es wird einfach der Dialog geschlossen. 
	 * @param oAgentCancel
	 */
	public void setActionAgentCancel(XX_ActionAgent oAgentCancel){
		if (m_ActionAgentCancel != null){
			m_btnCancel.remove_oActionAgent(m_ActionAgentCancel);
		}
		
		this.m_btnCancel.remove_AllActionAgents();
		this.m_ActionAgentCancel = oAgentCancel;
		if (m_ActionAgentCancel != null){
			this.m_btnCancel.add_oActionAgent(m_ActionAgentCancel);
		}
		// den close-Agenten wieder dazupacken
		this.m_btnCancel.add_oActionAgent(new actionAgentClose());
	}
	
	
	/**
	 * Setzt den Actionagent für den OK-Button,
	 * der vor dem Schliessen des Dialogs kommt.
	 * @param oAgentOk
	 */
	public void setActionAgentOK (XX_ActionAgent oAgentOk){
		if (m_ActionAgentOK != null){
			this.m_btnOk.remove_oActionAgent(m_ActionAgentOK);
		}
		this.m_ActionAgentOK = oAgentOk;
		
		
		this.m_btnOk.remove_AllActionAgents();
		
		// prüfroutine einbauen.
		this.m_btnOk.add_oActionAgent(new actionAgentCheckField());
		
		if (m_ActionAgentOK != null){
			this.m_btnOk.add_oActionAgent(m_ActionAgentOK);
		}
		// den close-Agenten wieder dazupacken
		this.m_btnOk.add_oActionAgent(new actionAgentClose());
	}
	
	
	public void set_Width(int m_Width) {
		this.m_Width = m_Width;
	}

	public int get_Width() {
		return m_Width;
	}

	public void set_Height(int m_Height) {
		this.m_Height = m_Height;
	}

	public int get_Height() {
		return m_Height;
	}

	/**
	 * Gibt den Wert des Scan-Feldes
	 * 
	 * @return
	 */
	public String getScanValue(){
		return m_tfBarcode.getText();
	}

	
	/**
	 * prüft, ob der Scanvalue den gewünschten Prefix beinhaltet.
	 * 
	 * @return
	 */
	 protected boolean checkScanValue(){
		boolean bRet = false;
		
		for(String s: m_Prefix){
			if (getScanValue() != null && getScanValue().startsWith(s)){
				bRet |= true;
			}
		}
		
		if (!bRet){
			m_lblStatus.setText("Gelesen: "  + m_tfBarcode.getText());
			m_tfBarcode.setText("");
			ApplicationInstance.getActive().setFocusedComponent(m_tfBarcode);
		}
		
		return bRet;
	}
	
	 
	
	/**
	 * Action zum schliessen des Fensters
	 * @author manfred
	 *
	 */
	private class actionAgentClose extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			// Schliessen des Fensters...
			Barcode_PopupContainer.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
		}
	}

	
	private class actionAgentCheckField extends XX_ActionAgent {
	
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if (Barcode_PopupContainer.this.checkScanValue() == false) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Dieser Barcode enthält nicht den erwarteten Kenner."));
			}
		}
	}
	
}
