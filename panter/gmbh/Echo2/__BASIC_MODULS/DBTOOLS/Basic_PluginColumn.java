package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;

import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_TextArea;


public class Basic_PluginColumn extends MyE2_Column
{
	private MyE2_TextArea 							textArea4Output =	new MyE2_TextArea("",900,-1,20);
	private ContainerForVerwaltungsTOOLS			oMotherContainer=	null;
	
	public  Basic_PluginColumn(	ContainerForVerwaltungsTOOLS oMothercontainer)
	{
		this.oMotherContainer = oMothercontainer;
		this.textArea4Output.setFont(new E2_FontPlain(-2));
	}

	public MyE2_TextArea get_TextArea4Output()
	{
		return textArea4Output;
	}

	public ContainerForVerwaltungsTOOLS get_oMotherContainer()
	{
		return oMotherContainer;
	}
	
	
	/**
	 * fügt den neuen Text in einer neuen Zeile im Textfeld dazu
	 * @author manfred
	 * @date 07.10.2016
	 *
	 * @param sText
	 */
	public void addText (String sText){
		String sOld = this.textArea4Output.getText();
		this.textArea4Output.setText(sOld + System.lineSeparator() + sText);;
	}
	
	
}
