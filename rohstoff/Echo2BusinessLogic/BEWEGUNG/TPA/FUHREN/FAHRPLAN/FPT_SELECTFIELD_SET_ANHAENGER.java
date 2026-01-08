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
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_LIST_ModulContainer;

/*
 * aktiver selektionsbutton, der in einem fahrplan die anhaenger von
 * zum editieren offenen fahrten fahrplanweit setzt
 */
public class FPT_SELECTFIELD_SET_ANHAENGER extends MyE2_SelectField
{

	private FU_LIST_ModulContainer  oModulcontainerLIST = null;
	
	public FPT_SELECTFIELD_SET_ANHAENGER(FU_LIST_ModulContainer  ModulcontainerLIST) throws myException
	{
		super("SELECT   NVL(KFZKENNZEICHEN,'--'),ID_MASCHINEN FROM "+
				bibE2.cTO()+".JT_MASCHINEN,"+bibE2.cTO()+".JT_MASCHINENTYP WHERE " +
				" JT_MASCHINEN.ID_MASCHINENTYP=JT_MASCHINENTYP.ID_MASCHINENTYP AND " +
				" JT_MASCHINENTYP.IST_ANHAENGER='Y' AND NVL(JT_MASCHINEN.AKTIV,'N')='Y' ORDER BY KFZKENNZEICHEN", 
				false, true,
				true, false);
		
		this.oModulcontainerLIST = ModulcontainerLIST;
		
		this.add_oActionAgent(new ownActionAgent());
		this.setWidth(new Extent(100));

	}

	private class ownActionAgent extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			FPT_SELECTFIELD_SET_ANHAENGER 	oThis = FPT_SELECTFIELD_SET_ANHAENGER.this;
			E2_NavigationList  				oNaviList = FPT_SELECTFIELD_SET_ANHAENGER.this.oModulcontainerLIST.get_oNavList();
			
			String cID_ANHAENGER = new MyLong(oThis.get_ActualWert(),new Long(0),new Long(0)).get_cUF_LongString();
			
			if (!cID_ANHAENGER.equals("0"))
			{
				
				Vector<String> vIDs = oNaviList.get_vectorSegmentation();
				Vector<String> vSQL = new Vector<String>();
				
				for (String cID:vIDs)
				{
					vSQL.add("UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE SET ID_MASCHINEN_ANH_FP="+cID_ANHAENGER+" WHERE ID_VPOS_TPA_FUHRE="+cID);
				}
				
				bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL, true));
				
				if (bibMSG.get_bIsOK())
				{
					bibMSG.add_MESSAGE(new MyE2_Info_Message_OT(new MyE2_String("Geänderte Anhänger-Einträge: ").CTrans()+vIDs.size()));
				}
				
				oNaviList._REBUILD_COMPLETE_LIST("");
			}
		}
		
	}
	
	

}
