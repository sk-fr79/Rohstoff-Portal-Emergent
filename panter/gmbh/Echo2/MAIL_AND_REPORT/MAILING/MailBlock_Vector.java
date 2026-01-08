package panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING;

import java.util.Vector;

import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.indep.exceptions.myException;

public class MailBlock_Vector extends Vector<MailBlock>
{

	public MailBlock_Vector()
	{
		super();
	}

	public void set_EnabledMailObjects(boolean bEnabled) throws myException
	{
		for (int i=0;i<this.size();i++)
		{
			for (int k=0;k<this.get(i).get_v_MailAdress4MailBlock().size();k++)
			{
				this.get(i).get_v_MailAdress4MailBlock().get(k).get_oCheckBox_MailForSending().set_bEnabled_For_Edit(bEnabled);
				this.get(i).get_v_MailAdress4MailBlock().get(k).get_oTextFieldWithMailAdress().set_bEnabled_For_Edit(bEnabled);
				
				this.get(i).set_EnableAdd_Buttons(bEnabled);
			}
		}
	}
	
	
	public Vector<MyE2_CheckBox>  get_vCheckBoxes()
	{
		Vector<MyE2_CheckBox> vCheckBoxRueck = new Vector<MyE2_CheckBox>();
		
		for (int i=0; i<this.size();i++)
		{
			for (int k=0;k<this.get(i).get_v_MailAdress4MailBlock().size();k++)
			{
				vCheckBoxRueck.add(this.get(i).get_v_MailAdress4MailBlock().get(k).get_oCheckBox_MailForSending());
			}
		}		

		return vCheckBoxRueck;
		
	}
	
	
	
	public MailBlock_Vector get_V_MailBlock_WithActivCheckBox(boolean bReset_IsSendedFlag, boolean bResetLabel)
	{
		MailBlock_Vector vRueck = new MailBlock_Vector();
		
		for (MailBlock oMailblock: this)	
		{
			if (oMailblock.get_b_EVEN_ONE_TargetAdress_isSelected_and_has_TargetAdress())
			{
				vRueck.add(oMailblock);
				if (bReset_IsSendedFlag) oMailblock.set_bWasSendEvenOneTimes(false);  
			}

			/*
			 * alle buttons wieder auf neutral stellen
			 */
			if (bResetLabel) oMailblock.setLabelNeutral();
		}

		return vRueck;
		
	}

	

	
	
}
