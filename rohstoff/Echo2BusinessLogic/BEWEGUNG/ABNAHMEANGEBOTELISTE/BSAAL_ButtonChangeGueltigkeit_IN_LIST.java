package rohstoff.Echo2BusinessLogic.BEWEGUNG.ABNAHMEANGEBOTELISTE;

import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Button;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.button.AbstractButton;
import nextapp.echo2.app.event.ActionListener;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.E2_Validator_ID_DBQuery;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Button;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class BSAAL_ButtonChangeGueltigkeit_IN_LIST extends MyE2_DB_Button
{

	private BSAAL__ModulContainerLIST oModulContainer = null;
	
	
	
	public BSAAL_ButtonChangeGueltigkeit_IN_LIST(SQLField osqlField,BSAAL__ModulContainerLIST ModulContainer) throws myException
	{
		super(osqlField);
		this.oModulContainer = ModulContainer;
		
		this.add_oActionAgent(new ownActionAgent());
		MutableStyle oStyleButton = new MutableStyle();
		oStyleButton.setProperty(Button.PROPERTY_LINE_WRAP, new Boolean(false));
		oStyleButton.setProperty(Button.PROPERTY_FONT, new E2_FontBoldItalic(-2));
		
		oStyleButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		oStyleButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, Color.WHITE, Border.STYLE_SOLID));
		oStyleButton.setProperty( AbstractButton.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-40), Border.STYLE_SOLID));
		oStyleButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		oStyleButton.setProperty( AbstractButton.PROPERTY_INSETS, new Insets(2,0,2,0)); 
		
		this.setStyle(oStyleButton);
		
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(oModulContainer.get_MODUL_IDENTIFIER(),BSAAL__CONST.BUTTON_AENDERE_DATUM_VON_BIS));
		
		this.add_IDValidator(new E2_Validator_ID_DBQuery("SELECT  NVL(JT_VKOPF_STD.ABGESCHLOSSEN,'N'),  NVL(JT_VKOPF_STD.DELETED,'N'),  NVL(JT_VPOS_STD.DELETED,'N') FROM " +
				bibE2.cTO()+".JT_VKOPF_STD," +bibE2.cTO()+".JT_VPOS_STD  WHERE "+
				"JT_VKOPF_STD.ID_VKOPF_STD=JT_VPOS_STD.ID_VKOPF_STD AND "+
				"JT_VPOS_STD.ID_VPOS_STD=#WERT#",
				bibALL.get_Array("N","N","N"),
				true, new MyE2_String("Gültigkeitsänderung nur in ungelöschten und offenen Positionen !")));			
		
	}
	
	
	
	public void setEnabled(boolean bEnabled)
	{
		super.setEnabled(bEnabled);
		if (!bEnabled)
		{
			this.setForeground(new E2_ColorGray(100));
		}
		else
		{
			this.setForeground(Color.BLACK);
		}
	}
	
	
	
	
	
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		BSAAL_ButtonChangeGueltigkeit_IN_LIST oButtCopy = null;
		
		try
		{
			oButtCopy = new BSAAL_ButtonChangeGueltigkeit_IN_LIST(this.EXT_DB().get_oSQLField(),this.oModulContainer);
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("MyE2_DB_Button:get_Copy:Copy-Error!");
		}
		
		oButtCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oButtCopy));
		if (this.getIcon() != null)
			oButtCopy.setIcon(this.getIcon());
		
		if (this.get_oText() != null)
			oButtCopy.set_Text(this.get_oText());
		
		oButtCopy.set_EXT_DB((MyE2EXT__DB_Component)this.EXT_DB().get_Copy(oButtCopy));
		
		oButtCopy.setStyle(this.getStyle());
		oButtCopy.setInsets(this.getInsets());
		
		Vector<ActionListener> vActionListeners = this.get_vExternalActionListeners();
		for (int i=0;i<vActionListeners.size();i++)
			oButtCopy.addActionListener((ActionListener)vActionListeners.get(i));

		
		for (int i=0;i<this.get_vGlobalValidators().size();i++)
			oButtCopy.add_GlobalValidator((XX_ActionValidator)this.get_vGlobalValidators().get(i));
				
		return oButtCopy;
	}

	
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			BSAAL_ButtonChangeGueltigkeit_IN_LIST oButton = BSAAL_ButtonChangeGueltigkeit_IN_LIST.this;
			
			String cID = oButton.get_cActualRowID();

			bibMSG.add_MESSAGE(oButton.valid_IDValidation(bibALL.get_Vector(cID)));
			if (bibMSG.get_bIsOK())
			{
				new BSAAL_POPUP_WINDOW_CHANGE_DATES(
											bibALL.get_Vector(cID),
											BSAAL_ButtonChangeGueltigkeit_IN_LIST.this.oModulContainer.get_oNaviList());
			}
		}
		
	}

}
