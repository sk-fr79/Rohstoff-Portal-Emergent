package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.SEARCH_FIELDS;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.Row;
import nextapp.echo2.app.text.TextComponent;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.Echo2.components.MyE2_TextArea_WithSelektor;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;


/**
 * Basisklasse für eine Zeile, die genau einen Verknüfungsvorschlag gibt 
 * @author manfred
 * @date   04.04.2013
 */
public class SingleLineSelektor_Base 	extends MyE2_Grid 
														implements I_SearchField_For_UpAndDownload{

	private String m_sObjectID = null;
	private String m_sTableName = null;
	private String m_sDescription = null;
	
	private String m_sLabelText = null;
	

	// GUI-Objekte
	private MyE2_CheckBox 	m_cbSelected = null;
	private MyE2_TextField m_tbInfo		= null;
	
	private MutableStyle   m_styleTbInfo = null;
	private MutableStyle   m_styleGrid = null;
	
	private int 		   m_tbWidth    = 200;
	private int 		   m_tbRows 	= 1;
	private int 		   m_tbMaxInputSize = 1000;
	
	private XX_ActionAgent m_oActionCheckbox = null;
	

	
	public SingleLineSelektor_Base() throws myException {
		this(null,null,null,null);
	}
	
	
	
	
	public SingleLineSelektor_Base(String sTablename, String sIDTable, String sDescription, String sLabelText) throws myException {
		super(2);
		m_sObjectID 	= sIDTable;
		m_sTableName 	= sTablename;
		m_sDescription 	= sDescription;
		m_sLabelText 	= sLabelText;
		
		// Komponente Initialisieren
		initComponent();
		
		// Felder initialisieren
		setConnectionData(m_sTableName, m_sObjectID, m_sDescription,m_sLabelText);
		
	}

	
	
	private void initComponent() throws myException{
		
		m_cbSelected = new MyE2_CheckBox();
		
		m_tbInfo =  new MyE2_TextField(m_sDescription,m_tbWidth, m_tbMaxInputSize);
		m_tbInfo.set_bEnabled_For_Edit(false);
		
		setDefaultStyles();
		setDefaultActionAgent();
		
		this.add(get_cbSelected());
		this.add(get_tbInfo());

	}
	

	/**
	 * Setzt die Daten für die möglichen Verknüpfungen
	 * @author manfred
	 * @date   04.04.2013
	 * @param sTablename
	 * @param sIDTable
	 * @param sDescription
	 */
	public void setConnectionData(String sTablename, String sIDTable, String sDescription,String sLabelText){
		m_sObjectID 		= sIDTable;
		m_sTableName 		= sTablename;
		m_sLabelText 		= sLabelText;
		
		m_sDescription 		= sDescription;
		this.m_tbInfo.setText(m_sDescription);
	}
	
	
	
	
	/**
	 * 
	 * @author manfred
	 * @date   04.04.2013
	 */
	private void setDefaultStyles(){

		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty( MyE2_Grid.PROPERTY_INSETS, new Insets(0)); 
		oStyle.setProperty( MyE2_Grid.PROPERTY_BORDER,  new Border(1, new E2_ColorBase(-1), Border.STYLE_SOLID));
		setMutableStyleForGrid(oStyle);
		
		oStyle = new MutableStyle();
		oStyle.setProperty( MyE2_TextField.PROPERTY_BACKGROUND, new E2_ColorBase(0));
		oStyle.setProperty( MyE2_TextField.PROPERTY_INSETS, new Insets(2,0,0,2)); 
		oStyle.setProperty( MyE2_TextField.PROPERTY_FONT, new E2_FontPlain());
		oStyle.setProperty( MyE2_TextField.PROPERTY_BORDER,  new Border(1, new E2_ColorBase(-20), Border.STYLE_SOLID));
		setMutableStyleForTextbox(oStyle);

	}
	
	
	
	private void setDefaultActionAgent(){
		setActionAgentForCheckbox(new XX_ActionAgent() {
			
			SingleLineSelektor_Base oThis = SingleLineSelektor_Base.this;
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				boolean bSelected = oThis.m_cbSelected.isSelected();
				if (bSelected){
					m_tbInfo.setBackground(new E2_ColorBase(+20));
				} else {
					m_tbInfo.setBackground(new E2_ColorBase(0));
				}
			}
		}
		,true);
	}
	
	
	
	/**
	 * Ersetzt einen ActionAgent für die Checkbox
	 * @author manfred
	 * @date   04.04.2013
	 * @param oAgent
	 * @param bReplace - wenn false wird der ActionAgent angehängt, sonst ersetzt er alle vorherigen
	 */
	protected void setActionAgentForCheckbox(XX_ActionAgent oAgent, boolean bReplace){
		if (bReplace){
			this.get_cbSelected().remove_AllActionAgents();
		}
		this.get_cbSelected().add_oActionAgent(oAgent);
	}
	
	

	
	/**
	 * Setzt den Style für das Grid
	 * @author manfred
	 * @date   04.04.2013
	 */
	public void setMutableStyleForGrid(MutableStyle oStyle){
		m_styleGrid = oStyle;
		this.setStyle(m_styleGrid);
	}
	
	
	/**
	 * Setzt den Style für die Textbox
	 * @author manfred
	 * @date   04.04.2013
	 */
	public void setMutableStyleForTextbox(MutableStyle oStyle){
		m_styleTbInfo = oStyle;
		get_tbInfo().setStyle(m_styleTbInfo);
	}


	/**
	 * gibt das Checkbox-Objekt zurück 
	 * @author manfred
	 * @date   04.04.2013
	 * @return
	 */
	public MyE2_CheckBox get_cbSelected() {
		return m_cbSelected;
	}


	/**
	 * gibt das Textfeld zurück
	 * @author manfred
	 * @date   04.04.2013
	 * @return
	 */
	public MyE2_TextField get_tbInfo() {
		return m_tbInfo;
	}

	
	/**
	 * Setzt den Text in der Textbox
	 * @author manfred
	 * @date   04.04.2013
	 * @param sDescription
	 */
	public void setInfoText(String sDescription){
		m_tbInfo.setText(sDescription);
	}
	
	
	
	
	@Override
	public String get_FoundObjectID() {
		return (m_cbSelected.isSelected() ? m_sObjectID : null );
	}

	
	@Override
	public String get_DBTableName() {
		return m_sTableName;
	}

	
	@Override
	public void set_ConditionValue(UP_AND_DOWNLOAD_ENUM_CONDITIONS condition,
			String conditionValue) {
	}

	
	@Override
	public String get_ConditionDescriptions() {
		return null;
	}



	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {

		SingleLineSelektor_Base oRueck = null;
		
		try
		{
			oRueck =  new SingleLineSelektor_Base();
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("SEARCH_SingleLineSelektor_Base:get_Copy:copy-error!"+ex.get_ErrorMessage().get_cMessage().COrig());
		}

		oRueck.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oRueck));
		
		oRueck.setStyle(this.getStyle());
		oRueck.setFont(this.getFont());
		
		oRueck.get_tbInfo().set_iMaxInputSize(this.get_tbInfo().get_iMaxInputSize());
		oRueck.get_tbInfo().set_iWidthPixel(this.get_tbInfo().get_iWidthPixel());
		oRueck.get_tbInfo().setText(this.get_tbInfo().getText());
		oRueck.get_tbInfo().setWidth(this.get_tbInfo().getWidth());
		oRueck.get_tbInfo().setAlignment(this.get_tbInfo().getAlignment());

		
		oRueck.setMutableStyleForGrid(this.m_styleGrid);
		oRueck.setMutableStyleForTextbox(this.m_styleTbInfo);
		
		oRueck.setConnectionData(m_sTableName, m_sObjectID, m_sDescription, m_sLabelText);
		
		return oRueck;
		
		
	}




	public String get_LabelText() {
		return m_sLabelText;
	}




	public void set_LabelText(String m_sLabelText) {
		this.m_sLabelText = m_sLabelText;
	}

	
	
	
}
