/**
 * 
 */
package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL;

import panter.gmbh.Echo2.components.MyE2_CheckBox;

/**
 * @author manfred
 * Klasse zum definieren von Auswahloptionen
 */
public class WF_CheckBoxOption extends MyE2_CheckBox {

	private static final long serialVersionUID = 7424153546769228389L;
	EnumDisplayOptions m_option = null;
	
	
	public EnumDisplayOptions getOption() {
		return m_option;
	}


	/**
	 * @param text		- Der Text der Checkbox
	 * @param Option	- Die Option, die gewählt wird 
	 */
	public WF_CheckBoxOption(String text, EnumDisplayOptions Option) {
		super(text, MyE2_CheckBox.STYLE_NORMAL_NO_BORDER_NO_INSETS());
		m_option = Option;
		
	}
	
	
	
}
