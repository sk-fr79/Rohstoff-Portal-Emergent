package panter.gmbh.Echo2.components.MultiValueSelector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Grid_100_percent;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.exceptions.myException;

/**
 * Wrapper zum Kapseln der einzelnen Objekte die im Multiselektor-Fenster darstellt werden.
 * Das Wrapper implementiert auch das Interface E2IF_MultiValueSelectorItem, damit die Methoden 
 * einfach durchgereicht werden können.
 * 
 * @author manfred
 * @date 13.11.2015
 *
 */
public class MultiValueSelectorItem implements E2IF_MultiValueSelectorItem {
	
	/**
	 * 
	 */
	private final MultiValueSelectorContainer m_parentContainer;
	
	String    		m_key 			= null;		
	
	Component 		m_comp 			= null;
	Component 		m_CompHeading 	= null;
	Component		m_commandComp 	= null;
	
	MyE2_Grid 		m_parentGrid 	= null;
	Insets			m_Insets		= E2_INSETS.I_0_0_0_0;

	
	public MultiValueSelectorItem(MultiValueSelectorContainer multiValueSelectorContainer, String sKey, Component oComp) {
		m_parentContainer 	= multiValueSelectorContainer;
		m_parentGrid 		= m_parentContainer.getContainerGrid();
		m_comp 				= oComp;
		m_key 				= sKey; 
	}
	
	
	public MultiValueSelectorItem(MultiValueSelectorContainer multiValueSelectorContainer, String sKey, Component oComp, Insets oInsets) {
		this(multiValueSelectorContainer, sKey,oComp);
		m_Insets = oInsets; 
	}
	
	public MultiValueSelectorItem(MultiValueSelectorContainer multiValueSelectorContainer, String sKey, Component headComp, Component oComp, Insets oInsets){
		this(multiValueSelectorContainer, sKey,oComp, oInsets);
		m_CompHeading = headComp;
	}
	
	public MultiValueSelectorItem(MultiValueSelectorContainer multiValueSelectorContainer, String sKey, Component headComp, Component oComp, Component oFootComp, Insets oInsets){
		this(multiValueSelectorContainer, sKey,headComp, oComp, oInsets);
		m_commandComp = oFootComp;
	}

	public MultiValueSelectorItem setHeading(Component comp){
		m_CompHeading = comp;
		return this;
	}
	
	public String getKey(){
		return m_key;
	}
	

	public Insets getInsets(){
		return m_Insets;
	}
	
	public Component getComponent() {
		return m_comp;
	}


	public Component getHeader() {
		return m_CompHeading;
	}


	public Component getFooter() {
		return m_commandComp;
	}


	public void setGrid(MyE2_Grid m_parentGrid) {
		this.m_parentGrid = m_parentGrid;
	}


	@Override
	public void setValue(Object o) throws myException {
		if (m_comp instanceof E2IF_MultiValueSelectorItem){
			((E2IF_MultiValueSelectorItem) m_comp).setValue(o);
		}
	}


	@Override
	public Object getValue() throws myException {
		if (m_comp instanceof E2IF_MultiValueSelectorItem){
			return ((E2IF_MultiValueSelectorItem) m_comp).getValue();
		} 
		return null;
	}
	
	
	
}