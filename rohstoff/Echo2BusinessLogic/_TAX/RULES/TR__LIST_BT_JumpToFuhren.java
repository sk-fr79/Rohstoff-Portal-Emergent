package rohstoff.Echo2BusinessLogic._TAX.RULES;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.XX_ActionAgentJumpToTargetList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_HANDELSDEF;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VectorSingle;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class TR__LIST_BT_JumpToFuhren extends MyE2_ButtonInLIST {
	
	private Integer  intAnzahlMaxFuhren = null;
	
	public TR__LIST_BT_JumpToFuhren(Integer  iAnzahlMaxFuhren) throws myException
	{
		super(E2_ResourceIcon.get_RI(iAnzahlMaxFuhren==null?"kompass.png":"kompass_10.png"),true);
		
		this.intAnzahlMaxFuhren = iAnzahlMaxFuhren;
		
		this.setToolTipText(new MyE2_String(iAnzahlMaxFuhren==null?"Sprung zu allen Fuhren dieser Regel (basierend auf allen Bewegungen, auch Fuhrenorte) -- LANGSAM":"Sprung zu 10 Fuhren dieser Regel basieren nur auf den Hauptfuhren -- SCHNELL").CTrans());

		this.add_oActionAgent(new ownActionAgentMarkiereZeile());
		this.add_oActionAgent(new ownAction_SpringZuAllenFuhrenDieser_RegelDefinition());
	}

	
	
	private class ownActionAgentMarkiereZeile extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			TR__LIST_BT_JumpToFuhren.this.EXT().get_oComponentMAP().set_CheckBoxForList_ToggleSelected();
		}
	}
	
	
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try {
			TR__LIST_BT_JumpToFuhren oBT_Copy = new TR__LIST_BT_JumpToFuhren(this.intAnzahlMaxFuhren);
			oBT_Copy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oBT_Copy));
			return oBT_Copy;
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
	}
	
	
	private class ownAction_SpringZuAllenFuhrenDieser_RegelDefinition extends XX_ActionAgentJumpToTargetList
	{
		
		public ownAction_SpringZuAllenFuhrenDieser_RegelDefinition() throws myException 
		{
			super(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER, "Fuhrenzentrale");
		}
		
		@Override
		public Vector<String> get_vID_Target(ExecINFO oExecInfo) throws myException 
		{
			TR__LIST_BT_JumpToFuhren oThis = TR__LIST_BT_JumpToFuhren.this;
			
			VectorSingle  vPOS = new VectorSingle();
			
			//zuerst die ID holen
			RECORD_HANDELSDEF   recHandelsDef = new RECORD_HANDELSDEF(oThis.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
			
			//String cSQL = this.createSqlStatementHandelsDef(recHandelsDef);
			TR___CreateStatement_to_find_Fuhre oStatementCreator = new TR___CreateStatement_to_find_Fuhre(recHandelsDef, oThis.intAnzahlMaxFuhren);
			oStatementCreator.createSqlStatementHandelsDef();
			
			vPOS.addAll(bibVECTOR.get_VectorFromArray(bibDB.EinzelAbfrageInArray(oStatementCreator.get_cSQL_FAST())));
			if (vPOS.size()==0) {
				vPOS.addAll(bibVECTOR.get_VectorFromArray(bibDB.EinzelAbfrageInArray(oStatementCreator.get_cSQL_SLOW())));
			}
			
			return vPOS;
		}
		
		
		@Override
		public MyE2_MessageVector  OVERRIDE_PRUEFE_OB_SPRUNG_MOEGLICH_IST(Vector<String> vTargetList) throws myException
		{
			MyE2_MessageVector  oMV = new MyE2_MessageVector();
			
			if (vTargetList.size()==0)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Zielmodul: <",true,this.get_cLesbarerModulName(),false,"> enthält keine passenden Einträge !",true)));
			}
			return oMV;
		}
	}
}
