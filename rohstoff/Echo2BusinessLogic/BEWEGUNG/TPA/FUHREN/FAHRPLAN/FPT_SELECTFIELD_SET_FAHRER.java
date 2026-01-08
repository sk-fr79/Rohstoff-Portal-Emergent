package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message_OT;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_LIST_ModulContainer;
import rohstoff.Echo2BusinessLogic._4_ALL.SEL_Query4UserList;
import rohstoff.Echo2BusinessLogic._4_ALL.SEL_Query4UserList.USERTYP;


/*
 * aktiver selektionsbutton, der in einem fahrplan die Fahrer von
 * zum editieren offenen fahrten fahrplanweit setzt
 */
public class FPT_SELECTFIELD_SET_FAHRER extends MyE2_SelectField
{
	private FU_LIST_ModulContainer  oModulcontainerLIST = null;

	public FPT_SELECTFIELD_SET_FAHRER(FU_LIST_ModulContainer  ModulcontainerLIST) throws myException
	{
//		super("SELECT  NVL(VORNAME,'-')||' '|| NVL(NAME1,'-')||' ('|| NVL(KUERZEL,'-')||')',ID_USER FROM "+
//				bibE2.cTO()+".JD_USER WHERE ID_MANDANT="+bibALL.get_ID_MANDANT()+" AND NVL(IST_FAHRER,'N')='Y' AND NVL(AKTIV,'N')='Y'", 
//				false, true,
//				true, false);
		
		super(new SEL_Query4UserList().add_typ(USERTYP.FAHRER).s(),false,true,false,false);
		
		this.oModulcontainerLIST = ModulcontainerLIST;
		
		this.add_oActionAgent(new ownActionAgent());
		this.setWidth(new Extent(100));
	}

	private class ownActionAgent extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			
			FPT_SELECTFIELD_SET_FAHRER 	oThis = FPT_SELECTFIELD_SET_FAHRER.this;
			
			String cID_Fahrer_UF = new MyLong(oThis.get_ActualWert(),new Long(0),new Long(0)).get_cUF_LongString();
			
			if (!cID_Fahrer_UF.equals("0"))
			{
				
				RECORD_USER oRecUser = new RECORD_USER(cID_Fahrer_UF);
				String cName = oRecUser.get_VORNAME_cF_NN("")+" "+oRecUser.get_NAME1_cF_NN("");
				
				
				E2_NavigationList  			oNaviList = FPT_SELECTFIELD_SET_FAHRER.this.oModulcontainerLIST.get_oNavList();
				
				Vector<String> vIDs = oNaviList.get_vectorSegmentation();
				Vector<String> vSQL = new Vector<String>();
				
				for (String cID:vIDs)
				{
					vSQL.add("UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE SET FAHRER_FP="+bibALL.MakeSql(cName)+" WHERE ID_VPOS_TPA_FUHRE="+cID);
				}
				
				bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL, true));
				
				if (bibMSG.get_bIsOK())
				{
					bibMSG.add_MESSAGE(new MyE2_Info_Message_OT(new MyE2_String("Geänderte Fahrer-Einträge: ").CTrans()+vIDs.size()));
				}
				
				oNaviList._REBUILD_COMPLETE_LIST("");
			}
		}
			
		
		
	}
	
}
