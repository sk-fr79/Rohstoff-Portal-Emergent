package rohstoff.Echo2BusinessLogic.FIBU;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Button;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VectorSingle;

public class FIBU_BT_ZEIGE_ALL_ZU_BUCHUNGSBLOCK extends MyE2_DB_Button
{

	public FIBU_BT_ZEIGE_ALL_ZU_BUCHUNGSBLOCK(SQLField osqlField) throws myException
	{
		super(osqlField);
		this.setStyle(MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder());
		
		this.add_oActionAgent(new ownActionAgent());
		this.setToolTipText(new MyE2_String("Zeige in der Liste alle Buchungen zu diesem Block").CTrans());
	}
	
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			FIBU_BT_ZEIGE_ALL_ZU_BUCHUNGSBLOCK oThis = FIBU_BT_ZEIGE_ALL_ZU_BUCHUNGSBLOCK.this;
			
			RECORD_FIBU   recFIBU = new RECORD_FIBU(oThis.get_cActualRowID());
			RECLIST_FIBU  reclistBlock = new RECLIST_FIBU("SELECT * FROM "+bibE2.cTO()+".JT_FIBU WHERE NVL(STORNIERT,'N')='N' AND BUCHUNGSBLOCK_NR="+recFIBU.get_BUCHUNGSBLOCK_NR_cUF());
			
			VectorSingle vIDs = new VectorSingle();
			
			vIDs.addAll(bibALL.get_vBuildKeyVectorFromHashmap(reclistBlock.get_ID_FIBU_hmString_UnFormated(null)));
				
			if (vIDs.size()>0)
			{
				E2_NavigationList oNaviList = oThis.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to();
				
				oNaviList.get_oComponentMAP__REF().get_oSQLFieldMAP().clear_ORDER_SEGMENT();
				oNaviList.get_oComponentMAP__REF().get_oSQLFieldMAP().add_ORDER_SEGMENT(FIBU_CONST.ORDER_BUCHUNGSBLOCK_UP);				
				
				oNaviList.get_vActualID_Segment().removeAllElements();
				oNaviList.get_vActualID_Segment().addAll(vIDs);
					
				oNaviList._REBUILD_ACTUAL_SITE("");
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Alle nicht stornierten Buchungen zum Block: ",true,recFIBU.get_BUCHUNGSBLOCK_NR_cF(),false)));
			}
		}
	}
	
	
	
	
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		FIBU_BT_ZEIGE_ALL_ZU_BUCHUNGSBLOCK oRueck = null;
		try
		{
			oRueck= new FIBU_BT_ZEIGE_ALL_ZU_BUCHUNGSBLOCK(this.EXT_DB().get_oSQLField());
		} 
		catch (myException e)
		{
			e.printStackTrace();
			throw new myExceptionCopy(e.getMessage());
		}
		
		return oRueck;
	}

}
