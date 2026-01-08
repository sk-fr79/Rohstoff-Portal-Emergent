package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_DB_BUTTON_OPEN_MASK_FromID;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class FU_LIST_BT_OPEN_KONTRAKT_POS extends E2_DB_BUTTON_OPEN_MASK_FromID 
{

	private String EK_VK = null;
	
	
	public FU_LIST_BT_OPEN_KONTRAKT_POS(SQLField field, E2_BasicModuleContainer_MASK ModulContainerMASK, String cEK_VK)	throws myException
	{
		super(field, ModulContainerMASK, new MyE2_String(cEK_VK.equals("EK")?"EK-Kontrakt-Position":"VK-Kontrakt-Position"),null, "EDIT_KONTRAKT_POS", "VIEW_KONTRAKT_POS");
		this.EK_VK = cEK_VK;
		this.add_IDValidator(new ownValidator());
		this.setAlignment(new Alignment(Alignment.CENTER, Alignment.CENTER));
	}

	
	
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		FU_LIST_BT_OPEN_KONTRAKT_POS oButtCopy = null;
		
		try
		{
			oButtCopy = new FU_LIST_BT_OPEN_KONTRAKT_POS(
											this.EXT_DB().get_oSQLField(),
											this.get_oBasicModulContainer_MASK(),
											this.EK_VK);
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("FU_LIST_BT_OPEN_KONTRAKT_POS:get_Copy:Copy-Error!");
		}
		
		oButtCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oButtCopy));
		if (this.getIcon() != null)
			oButtCopy.setIcon(this.getIcon());
		
		if (this.get_oText() != null)
			oButtCopy.set_Text(this.get_oText());
		
		oButtCopy.set_EXT_DB((MyE2EXT__DB_Component)this.EXT_DB().get_Copy(oButtCopy));
		
		oButtCopy.setStyle(this.getStyle());
		oButtCopy.setInsets(this.getInsets());
		oButtCopy.get_vActionAgentsAfterCancel().addAll(this.get_vActionAgentsAfterCancel());
		oButtCopy.get_vActionAgentsAfterSave().addAll(this.get_vActionAgentsAfterSave());
		
		return oButtCopy;
	}

	
	
	
	private class ownValidator extends XX_ActionValidator
	{

		public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException 
		{
			return null;
		}

		protected MyE2_MessageVector isValid(String cID_Unformated) throws myException 
		{
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			RECORD_VPOS_KON oKon = new RECORD_VPOS_KON(cID_Unformated);
			
			if (oKon.get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).is_ABGESCHLOSSEN_YES())
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Bearbeiten nur bei offenen Kontraktpositionen erlaubt !"));
			
			return oMV;
		}
		
	}
	
}
