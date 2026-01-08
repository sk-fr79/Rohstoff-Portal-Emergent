package rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class __FU_BT_SucheHandelsDef_Zu_Fuhre extends MyE2_ButtonInLIST
{

	public __FU_BT_SucheHandelsDef_Zu_Fuhre()
	{
		super(E2_ResourceIcon.get_RI("wizard_mini.png"),true);
		
		this.setToolTipText(new MyE2_String("Prüfung der Handelssituation der Fuhre und gegebenenfalls Anzeige der gefundenen Einschätzung!").CTrans());
		
		this.add_oActionAgent(new _HD_ActionAgent_FindTaxDef__FUHREN_LISTE());
		//this.add_GlobalValidator(new ownActionValidator());    //validierer ausgeschaltet, da keine aktion, nur anzeige
		this.add_GlobalAUTHValidator_AUTO("STEUERERMITTLUNG_AUS_FUHRENLISTE");
	}

	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		__FU_BT_SucheHandelsDef_Zu_Fuhre oButton = new __FU_BT_SucheHandelsDef_Zu_Fuhre();
		oButton.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oButton));

		return oButton; 
	}
}
