package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.SEARCH_FIELDS;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MaskSearchField.MyE2_MaskSearchField;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;



/**
 * Erweitert die Klasse MyE2_DB_MaskSearchField indem der Searchblock in einen DBSearchBlockStandard_EXT gewandelt wird.
 * 
 * @author manfred
 * @date   27.03.2013
 */
public abstract class MaskSearchField_WithAdditionalConditions extends MyE2_MaskSearchField implements I_SearchField_For_UpAndDownload{

	// Componente zum Beschreiben an/aus-schalten von Bedingungen
	private MyE2_CheckBox m_cbUseCondition = null;
	
	private SearchBlockStandard_WithAdditionalConditions m_oSearchBlock = null;
	
	protected int m_widthOfSearchField = 80;
	protected int m_widthOfTextField   = 500;
	
	
	public MaskSearchField_WithAdditionalConditions(
			String cSQLSelectBlock, String cSQLFromBlock,
			String cSQLOrderBlock, String cSQLWhereBlockJoiningTables,
			String cSQLWhereBlockForSelecting, String cSQLqueryForUniqueSearch,
			String charForUniqueSearch, String cCOMPLETE_SQL_FOR_label,
			Insets INSETS_For_Components, boolean showEraser)
			throws myException {
		
		super( cSQLSelectBlock, cSQLFromBlock, cSQLOrderBlock,
				cSQLWhereBlockJoiningTables, cSQLWhereBlockForSelecting,
				cSQLqueryForUniqueSearch, charForUniqueSearch,
				cCOMPLETE_SQL_FOR_label, INSETS_For_Components, showEraser);
		
		
		// einen neuen Searchblock definieren
		m_oSearchBlock = 
				new SearchBlockStandard_WithAdditionalConditions(	cSQLSelectBlock,
																	cSQLFromBlock,
																	cSQLWhereBlockJoiningTables,
																	cSQLWhereBlockForSelecting,
																	cSQLOrderBlock,
																	cSQLqueryForUniqueSearch,
																	charForUniqueSearch,600);
		
		m_cbUseCondition = new MyE2_CheckBox();
		m_cbUseCondition.add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				if (m_oSearchBlock != null){
					m_oSearchBlock.set_bUseAdditionalSearchConditions(m_cbUseCondition.isSelected());
				}
			}
		});
		
		
		
		this.set_oSeachBlock(m_oSearchBlock);
		this.get_oTextForAnzeige().setFont(new E2_FontPlain());
		
		this.addCheckboxForAdditionalSearch();
		
		
	}

	
	/**
	 * Fügt den Schalter für das ein/ausschalten der zusätzlichen Bedingungen dazu wenn nötig
	 * @author manfred
	 * @date   08.04.2013
	 * @throws myException
	 */
	protected void addCheckboxForAdditionalSearch() throws myException{
		if (m_oSearchBlock.get_IsAdditionalSearchConditionSet()){
			m_cbUseCondition.setToolTipText("Ein- Ausschalten der Bedingungen: " +	m_oSearchBlock.get_DescriptionOfAdditionalSearchConditions());
			m_cbUseCondition.__setText(new MyString("Suche Einschränken"));
			
			m_cbUseCondition.setSelected(true);
			m_oSearchBlock.set_bUseAdditionalSearchConditions(m_cbUseCondition.isSelected());
			
			this.set_AddOnComponent(this.m_cbUseCondition);
		}

		this.get_oTextFieldForSearchInput().setWidth(new Extent(m_widthOfSearchField));
		this.get_oTextForAnzeige().setWidth(new Extent(m_widthOfTextField));
	}
	
	
	@Override
	public String get_FoundObjectID() {
		String sRet = null;
		try {
			sRet = this.get_cActualMaskValue();
		} catch (myException e) {
			e.printStackTrace();
		}
		return sRet;
	}
	
	
	
	@Override
	public E2_BasicModuleContainer get_ownContainer() throws myException {
		return null;
	}

	
	@Override
	public void set_ConditionValue(UP_AND_DOWNLOAD_ENUM_CONDITIONS condition, String paramValue)  {
		SearchBlockStandard_WithAdditionalConditions oSearchBlock =  (SearchBlockStandard_WithAdditionalConditions)this.get_oSeachBlock();
		oSearchBlock.set_SearchConditionValue(condition, paramValue);
		
		// den Tooltip-Text anpassen
		String sTooltip = this.get_ConditionDescriptions();
		this.get_oTextFieldForSearchInput().setToolTipText(sTooltip);
		this.get_oTextForAnzeige().setToolTipText(sTooltip);
		
		try {
			addCheckboxForAdditionalSearch();
		} catch (myException e) {
			//
		}
		
		
	}

	@Override
	public String get_ConditionDescriptions() {
		SearchBlockStandard_WithAdditionalConditions oSearchBlock =  (SearchBlockStandard_WithAdditionalConditions)this.get_oSeachBlock();
		return oSearchBlock.get_DescriptionOfAdditionalSearchConditions();
	}
	


}
