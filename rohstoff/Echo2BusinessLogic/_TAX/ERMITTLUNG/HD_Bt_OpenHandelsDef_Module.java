package rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._TAX.RULES.TR__LIST_BasicModuleContainer;

public class HD_Bt_OpenHandelsDef_Module extends HD_BT_BASIC
{

	private String cWhereBlock = "(1<>1)";
	
	public HD_Bt_OpenHandelsDef_Module(Vector<String> vID_HandelsDefs) throws myException 
	{
		//2018-07-12: neue validierung: super(E2_ResourceIcon.get_RI("edit.png"), TAX_CONST.VALID_KEY_OEFFNE_HANDELSDEF_MODUL);
		super(E2_ResourceIcon.get_RI("edit.png"), ENUM_VALIDATION.HANDELSDEFINITIONEN_EDIT);

		this.add_oActionAgent(new ownActionAgent());
		this.setToolTipText(new MyE2_String("Öffne die Handelsdefinitionen, die gefunden wurden ...").CTrans());
		
		if (vID_HandelsDefs != null && vID_HandelsDefs.size()>0)
		{
			if (vID_HandelsDefs.size()==1)
			{
				this.cWhereBlock = _DB.HANDELSDEF+"."+_DB.HANDELSDEF$ID_HANDELSDEF+"="+vID_HandelsDefs.get(0);
			}
			else
			{
				this.cWhereBlock = _DB.HANDELSDEF+"."+_DB.HANDELSDEF$ID_HANDELSDEF+" in ("+bibALL.Concatenate(vID_HandelsDefs, ",", "1<>1")+")";
			}
		}
	}
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			new TR__LIST_BasicModuleContainer(HD_Bt_OpenHandelsDef_Module.this.cWhereBlock).CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000), new Extent(700), new MyE2_String("Regeln für Steuern in Bewegungsätzen"));
		}
	}
	

}
