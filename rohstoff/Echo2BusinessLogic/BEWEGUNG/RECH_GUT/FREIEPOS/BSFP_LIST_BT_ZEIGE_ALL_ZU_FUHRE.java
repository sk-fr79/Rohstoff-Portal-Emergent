package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.FREIEPOS;

import java.util.HashMap;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Button;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VectorSingle;

public class BSFP_LIST_BT_ZEIGE_ALL_ZU_FUHRE extends MyE2_DB_Button
{

	public BSFP_LIST_BT_ZEIGE_ALL_ZU_FUHRE(SQLField osqlField) throws myException
	{
		super(osqlField);
		this.setStyle(MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder());
		
		//this.set_cSetTextWhenEmpty(new MyE2_String("< - >",false));
		
		this.add_oActionAgent(new ownActionAgent());
		this.setToolTipText(new MyE2_String("Zeige in der Liste alle Positionen zu dieser Fuhre").CTrans());
	}
	
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			BSFP_LIST_BT_ZEIGE_ALL_ZU_FUHRE oThis = BSFP_LIST_BT_ZEIGE_ALL_ZU_FUHRE.this;
			
			RECORD_VPOS_RG recVPOS = new RECORD_VPOS_RG(oThis.get_cActualRowID());
			
			VectorSingle vIDs = new VectorSingle();
			
			if (recVPOS.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre_zugeord() != null)
			{
				HashMap<String, String> hmIDVPOS_RG = recVPOS.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre_zugeord().get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord().get_ID_VPOS_RG_hmString_UnFormated("");
				vIDs.addAll(bibALL.get_vBuildKeyVectorFromHashmap(hmIDVPOS_RG));
				
				if (vIDs.size()>0)
				{
					E2_NavigationList oNaviList = oThis.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to();
					oNaviList.get_vActualID_Segment().removeAllElements();
					oNaviList.get_vActualID_Segment().addAll(vIDs);
					
					oNaviList._REBUILD_ACTUAL_SITE("");
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Alle Positionen die zur ausgewählten Fuhre gehören ..."));
				}
			}
		}
	}
	
	
	
	
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		BSFP_LIST_BT_ZEIGE_ALL_ZU_FUHRE oRueck = null;
		try
		{
			oRueck= new BSFP_LIST_BT_ZEIGE_ALL_ZU_FUHRE(this.EXT_DB().get_oSQLField());
		} 
		catch (myException e)
		{
			e.printStackTrace();
			throw new myExceptionCopy(e.getMessage());
		}
		
		return oRueck;
	}

}
