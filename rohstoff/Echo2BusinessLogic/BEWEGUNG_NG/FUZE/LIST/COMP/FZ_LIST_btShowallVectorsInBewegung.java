package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST.COMP;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2IF_IsMarkable;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_VEKTOR;
import panter.gmbh.indep.bibFONT;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEKSingle;  



/**
 * ein button zeigt eine Warenbewegung in gänze in der Liste
 * @author martin
 *
 */
public class FZ_LIST_btShowallVectorsInBewegung extends MyE2_ButtonInLIST  implements MyE2IF_IsMarkable {

	private RECORD_BEWEGUNG_VEKTOR  rec_v = null;
	private E2_NavigationList       naviList = null;

	public FZ_LIST_btShowallVectorsInBewegung(String c_text, RECORD_BEWEGUNG_VEKTOR p_rec_v, E2_NavigationList  p_naviList) {
		super(c_text);
		this.rec_v = p_rec_v;
		this.naviList = p_naviList;
		this.add_oActionAgent(new ownActionAgent());
	}
	
	
	private class ownActionAgent extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			FZ_LIST_btShowallVectorsInBewegung oThis = FZ_LIST_btShowallVectorsInBewegung.this;
			
			VEKSingle<String> v_ids = new VEKSingle<String>()
								._aa(rec_v.get_UP_RECORD_BEWEGUNG_id_bewegung().get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_id_bewegung("",BEWEGUNG_VEKTOR.id_bewegung_vektor.tnfn(),true)
																				.get_ID_BEWEGUNG_VEKTOR_hmString_UnFormated("").keySet());
			
			if (v_ids.size()>0)	{
			
//				oThis.naviList.get_oComponentMAP__REF().get_oSQLFieldMAP().clear_ORDER_SEGMENT();
//				oThis.naviList.get_oComponentMAP__REF().get_oSQLFieldMAP().add_ORDER_SEGMENT(FIBU_CONST.ORDER_BUCHUNGSBLOCK_UP);				
				
				oThis.naviList.get_vActualID_Segment().removeAllElements();
				oThis.naviList.get_vActualID_Segment().addAll(v_ids);

				
				oThis.naviList._REBUILD_ACTUAL_SITE("");
				oThis.naviList.Check_ID_IF_IN_Page(v_ids);

				bibMSG.add_MESSAGE(
						new MyE2_Info_Message(
								new MyE2_String("Alle Bewegungsvektoren, die zur Warenbewegungen mit der ID: ",true
												,oThis.rec_v.get_UP_RECORD_BEWEGUNG_id_bewegung().fs(BEWEGUNG.id_bewegung),false
												," gehören.",true)));
			}
		}
	}


	@Override
	public void make_Look_Deleted(boolean bIsDeleted) {
		bibFONT.change_fontToLineThrough(this, bIsDeleted);
	}

	@Override
	public void setForeColorActive(Color ForeColor) {
		this.setForeground(ForeColor);
	}

	@Override
	public void setFontActive(Font font) {
		this.setFont(bibFONT.equal_LineThrough_status(font, this));
	}

	@Override
	public Color get_Unmarked_ForeColor() {
		return this.getForeground();
	}

	@Override
	public Font get_Unmarked_Font() {
		return this.getFont();
	}

}
