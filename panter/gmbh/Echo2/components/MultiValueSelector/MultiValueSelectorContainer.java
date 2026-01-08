package panter.gmbh.Echo2.components.MultiValueSelector;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.LayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

public class MultiValueSelectorContainer extends E2_BasicModuleContainer{

	/**
	 *  		|-------------------------------------|
	 *			| Heading                             |
	 *			|-------------------------------------|
	 *			| inner Grid  mit Komponenten         |
	 *  		|     |       |           |           |
	 *  		|-------------------------------------|
	 *  		|     |       |           |           |
	 *  		|-------------------------------------|
	 *  		| Footer                              |
	 *  		|-------------------------------------|
	 *  
	 *  
	 */


	private MyE2_Grid											m_gridMain 			= null;

	private boolean 											m_ShowButtonSave 	= true;
	private boolean 											m_ShowButtonCancel 	= true;

	private LinkedHashMap<String, MultiValueSelectorItem> 		m_lhmComponents 	= null;
	private MyE2_Button											m_buttonSave 		= null;
	private MyE2_Button											m_buttonCancel 		= null;

	private boolean 											m_bFooterComponent 	= false;

	XX_ActionAgent 												m_ActionAgentSave 	= null;
	XX_ActionAgent												m_actionAgentCancel = null;
	private E2IF_MultiValueSelectorContainer m_parent;

	private IF_InBox  											inBox = null;
	
	//----------------- Constructors

	public MultiValueSelectorContainer(E2IF_MultiValueSelectorContainer parent, MultiValueSelector_SaveKeySizeofPopup saveSizeKey) {
		super();

		this.set_cADDON_TO_CLASSNAME(saveSizeKey.name());
		
		m_parent = parent;

		m_gridMain = new MyE2_Grid(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		
		m_lhmComponents = new LinkedHashMap<String, MultiValueSelectorItem>();

		this.add(m_gridMain);
		
		m_buttonSave = new MyE2_Button("Speichern");
		m_buttonSave.setVisible(m_ShowButtonSave);

		m_buttonCancel = new MyE2_Button("Abbrechen");
		m_buttonCancel.setVisible(m_ShowButtonCancel);

		m_ActionAgentSave = new ownSaveAction();
		m_actionAgentCancel = new cancelActionAgent();
		
		m_buttonCancel.add_oActionAgent(m_actionAgentCancel);
		m_buttonSave.add_oActionAgent(m_ActionAgentSave);
		
	}


	//----------------- public methods

	/**
	 * Setzt die Dialogüberschrift
	 * 
	 * @author manfred
	 * @date   13.11.2015
	 *
	 * @param sHeading
	 * @return
	 */
	public MultiValueSelectorContainer setPopUpTitle(String sHeading){
		this.get_oWindowPane().set_oTitle(new MyString(sHeading));
		return this;
	}

	/**
	 * Setzt die Anzeige des Save-Buttons
	 * 
	 * @author manfred
	 * @date   13.11.2015
	 *
	 * @param bShowSave
	 * @return
	 */
	public MultiValueSelectorContainer showButtonSave(boolean bShowSave){
		m_ShowButtonSave = bShowSave;
		m_buttonSave.setVisible(bShowSave);

		return this;
	}

	/**
	 * Setzt die Anzeige des Cancel-Buttons
	 * 
	 * @param bShowCancel
	 * @return
	 */
	public MultiValueSelectorContainer showButtonCancel(boolean bShowCancel){
		m_ShowButtonCancel = bShowCancel;
		m_buttonCancel.setVisible(bShowCancel);
		return this;
	}

	/**
	 * Add of a component in the component map and in the grid.
	 * @param key
	 * @param newComp
	 * @return
	 */
	public MultiValueSelectorContainer addComponent(String key, Component newComp){
		if (newComp instanceof E2IF_MultiValueSelectorItem){
			MultiValueSelectorItem mvsi = new MultiValueSelectorItem(this, key,newComp);
			addToComponentMap(key, mvsi);
		} 
		return this;
	}

	/**
	 * Add of a component in the component map and in the grid with a heading label.
	 * @param key
	 * @param newComp
	 * @param headComponent
	 * @return
	 */
	public MultiValueSelectorContainer addComponent(String key, Component headComponent, Component newComp){
		if (newComp instanceof E2IF_MultiValueSelectorItem){
			MultiValueSelectorItem mvsi = new MultiValueSelectorItem(this, key, headComponent, newComp, null);
			addToComponentMap(key, mvsi);
		} 
		return this;
	}
	
	public MultiValueSelectorContainer addComponent(String key, Component newComp, Component headComponent, Insets insets){
		if (newComp instanceof E2IF_MultiValueSelectorItem){
			MultiValueSelectorItem mvsi = new MultiValueSelectorItem(this, key, headComponent, newComp, insets);
			addToComponentMap(key, mvsi);
		} 
		return this;
	}
	
	public MultiValueSelectorContainer addComponent(String key, Component headingLabel, Component newComp, Component footComponent){
		if (newComp instanceof E2IF_MultiValueSelectorItem){
			MultiValueSelectorItem mvsi = new MultiValueSelectorItem(this, key, headingLabel, newComp, footComponent, null);
			addToComponentMap(key, mvsi);
			m_bFooterComponent=true;
		} 
		return this;
	}
	
	public MultiValueSelectorContainer addComponent(String key, Component headingLabel, Component newComp, Component footComponent ,Insets insets){
		if (newComp instanceof E2IF_MultiValueSelectorItem){
			MultiValueSelectorItem mvsi = new MultiValueSelectorItem(this, key, newComp, headingLabel, footComponent, insets);
			addToComponentMap(key, mvsi);
		} 
		return this;
	}

	/**
	 * return the component map
	 * @return
	 */
	public LinkedHashMap<String, MultiValueSelectorItem> getComponentsMap() {
		return m_lhmComponents;
	}

	/**
	 * set the component map
	 * @param m_lhmComponents
	 */
	public void setComponentsMap(LinkedHashMap<String, MultiValueSelectorItem> m_lhmComponents) {
		this.m_lhmComponents = m_lhmComponents;
	}

	public XX_ActionAgent getSaveActionAgent() {
		return m_ActionAgentSave;
	}


	public void setSaveActionAgent(XX_ActionAgent actionAgentSave) {
		this.m_ActionAgentSave = actionAgentSave;
	}


	public XX_ActionAgent getM_actionAgentCancel() {
		return m_actionAgentCancel;
	}


	public void setM_actionAgentCancel(XX_ActionAgent m_actionAgentCancel) {
		this.m_actionAgentCancel = m_actionAgentCancel;
	}


	/**
	 * Get the returned value
	 * @return hashmap containing the value returned
	 * @throws myException 
	 */
	public HashMap<String,Object> getValues() throws myException{
		HashMap<String, Object> valueMap = new HashMap<String, Object>();
		for(String key: m_lhmComponents.keySet()){
			valueMap.put(key, m_lhmComponents.get(key).getValue());
		}
		return valueMap;
	}

	/**
	 * Set the value from the component linked to the key
	 * @param key from the component
	 * @param value to set
	 * @throws myException 
	 */
	public void setValue(String componentKey, Object value) throws myException{
		m_lhmComponents.get(componentKey).setValue(value);
	}

	/**
	 * Return the value from the component linked to the key
	 * @param key from the component
	 * @return value from the component linked to the key
	 * @throws myException 
	 */
	public Object getValueFromComponent(String componentKey ) throws myException{
		return m_lhmComponents.get(componentKey).getValue();
	}

	public void setGridWidth(int gridWidth){
		
	}
	
	public MyE2_Grid getContainerGrid() {
		return m_gridMain;
	}
	
	public void CREATE_AND_SHOW_POPUPWINDOW(Extent oextWidth, Extent oextHeight, MyE2_String oTitle) throws myException {
		
		fillGrid();
		
		m_parent.getValueFromParentComponents();
		super.CREATE_AND_SHOW_POPUPWINDOW(oextWidth, oextHeight, oTitle);
	}


	//----------------- Private methods
	private void addToComponentMap(String key, MultiValueSelectorItem mvsi) {
		mvsi.setGrid(m_gridMain);
		m_lhmComponents.put(key, mvsi);
	}

	private MyE2_Grid buildButtonGrid(){
		MyE2_Grid buttonGrid = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		buttonGrid.add(m_buttonSave,E2_INSETS.I(2,2,10,2));
		buttonGrid.add(m_buttonCancel,E2_INSETS.I(2,2,2,2));
		return buttonGrid;
	}
	
	private void fillGrid() {
		Set<String> keysTab = m_lhmComponents.keySet();
		int totalWidth = m_lhmComponents.size();
		m_gridMain.setSize(totalWidth);
		
		for(String key:keysTab){
			MultiValueSelectorItem mvsi = m_lhmComponents.get(key);
			Component header = mvsi.getHeader();
			if(! (header==null)) {
				m_gridMain.add(header);
			} else {
				m_gridMain.add(new MyE2_Label());
			}
		}
		
		for(String key:keysTab){
			if (this.inBox==null) {
				m_gridMain.add(m_lhmComponents.get(key).getComponent());
			} else {
				LayoutData  gl_old = m_lhmComponents.get(key).getComponent().getLayoutData();
				MyE2_Grid grid_box = this.inBox.in_box((MyE2IF__Component)m_lhmComponents.get(key).getComponent());
				grid_box.setLayoutData(gl_old);
				m_gridMain.add(grid_box);
			}
		}
		
		for(String key:keysTab){
			MultiValueSelectorItem mvsi = m_lhmComponents.get(key);
			Component footer = mvsi.getFooter();
			if(! (footer==null)){
				m_gridMain.add(footer);
			} 
			else {
				m_gridMain.add(new MyE2_Label());
			}
		}
		
		if(!m_bFooterComponent){
			m_gridMain.add(buildButtonGrid() , totalWidth,1, E2_INSETS.I(0,0,0,0),new Alignment(Alignment.LEFT, Alignment.CENTER));
		}		
	}
	
	//----------------- Private Class
	private class cancelActionAgent extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			CLOSE_AND_DESTROY_POPUPWINDOW(false);	
		}

	}

	/**
	 * 
	 * @author sebastien
	 *
	 */
	private class ownSaveAction extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			m_parent.refreshParentComponents();
			CLOSE_AND_DESTROY_POPUPWINDOW(true);	
		}
	}

	/**
	 * uebernimmt einen lamda-ausdruck, um die komponenten in eine box einzuschliessen
	 * @param inBox
	 */
	public void set_inBox(IF_InBox inBox) {
		this.inBox = inBox;
	}


	public MyE2_Button get_buttonSave() {
		return m_buttonSave;
	}


	public MyE2_Button get_buttonCancel() {
		return m_buttonCancel;
	}

	
}
