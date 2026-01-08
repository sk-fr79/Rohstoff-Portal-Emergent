package panter.gmbh.Echo2.components.DB.OptionSelector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.Row;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

public abstract class OptionSelector_PopupContainer extends E2_BasicModuleContainer{

	private String  m_sCaption = "";
	private int     m_Width = 200;
	private int 	m_Height = 100;
	
	private boolean m_bEnabledForEdit = true;
	
	private IF_OptionSelector_PopupDetail m_oDetail = null;
	private XX_ActionAgent 	m_ActionAgentOK = null;
	private XX_ActionAgent 	m_ActionAgentCancel = null;
	
	private MyE2_Button    	m_btnOk = null;
	private MyE2_Button   	m_btnCancel = null;
	
	private MyE2_Column     m_colMain = null;
	private MyE2_Row       	m_rowButtons = null;
	
//	private E2_DB_OptionSelector m_oSelector = null;
	
	
	public OptionSelector_PopupContainer( String sCaption )
	{
		super();
		this.m_sCaption = sCaption;
		
		m_colMain = new MyE2_Column(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
		
		MutableStyle oStyleRow = new MutableStyle();
		oStyleRow.setProperty(PROPERTY_BORDER, null); 
		oStyleRow.setProperty(Row.PROPERTY_ALIGNMENT, new Alignment(Alignment.RIGHT,Alignment.CENTER));
		
		m_rowButtons = new MyE2_Row(oStyleRow);
		m_btnOk = new MyE2_Button(new MyString("Ok"), E2_ResourceIcon.get_RI("save.png"), E2_ResourceIcon.get_RI("save__.png"));
		m_btnCancel = new MyE2_Button(new MyString("Abbrechen"), E2_ResourceIcon.get_RI("cancel.png"), E2_ResourceIcon.get_RI("cancel__.png"));

		
		// Default Cancel Action, closes the dialog 
		m_btnCancel.add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				OptionSelector_PopupContainer.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);	
			}
		});
		
		
		m_rowButtons.add(m_btnOk,E2_INSETS.I_0_0_10_0);
		m_rowButtons.add(m_btnCancel,E2_INSETS.I_0_0_10_0);
		
		this.add(m_colMain);
		this.add(m_rowButtons,E2_INSETS.I_0_10_0_0);
	}
	
	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
		// TODO Auto-generated method stub
		//super.set_bEnabled_For_Edit(enabled);
		this.m_bEnabledForEdit = enabled;
		
		// OK-Button verstecken, wenn nicht editierbar
		this.m_btnOk.setVisible(m_bEnabledForEdit);
		
		this.m_oDetail.setEnabledForEdit(m_bEnabledForEdit);
	}
	
		
	public void ShowPopup() throws myException {
		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(m_Width), new Extent(m_Height), new MyE2_String(m_sCaption));
	}

	public void ClosePopup(boolean bStatus) throws myException {
		this.CLOSE_AND_DESTROY_POPUPWINDOW(bStatus);
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

		this.m_ActionAgentCancel = oAgentCancel;
		if (m_ActionAgentCancel != null){
			this.m_btnCancel.add_oActionAgent(m_ActionAgentCancel);
		}
	}
	
	
	/**
	 * Setzt den Actionagent für den OK-Button
	 * @param oAgentOk
	 */
	public void setActionAgentOK (XX_ActionAgent oAgentOk){
		if (m_ActionAgentOK != null){
			this.m_btnOk.remove_oActionAgent(m_ActionAgentOK);
		}
		this.m_ActionAgentOK = oAgentOk;
		if (m_ActionAgentOK != null){
			this.m_btnOk.add_oActionAgent(m_ActionAgentOK);
		}
	}
	
	
	/**
	 * Setzt den Detail-Block des Selektionsdialogs
	 * @param oColDetail
	 * @throws myException
	 */
	public void setDetailBlock (IF_OptionSelector_PopupDetail oColDetail) throws myException{
		this.m_colMain.removeAll();
		
		this.m_oDetail = oColDetail;
		if (m_oDetail != null){
			if (m_oDetail instanceof Component){
				this.m_colMain.add((Component)m_oDetail,E2_INSETS.I_0_0_0_0);
				
				m_oDetail.setEnabledForEdit(m_bEnabledForEdit);
				
			} else {
				throw new myException("OptionSelector_PopupContainer.setDetailBlock: Objekt is not of Type Component");
			}
		}
	}
	
	
	/**
	 * Gibt den Detailblock des Selektionsdialogs zurück
	 * @return
	 */
	public IF_OptionSelector_PopupDetail getDetailBlock(){
		return m_oDetail;
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

}
