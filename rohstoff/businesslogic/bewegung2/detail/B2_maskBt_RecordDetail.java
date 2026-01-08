/**
 * rohstoff.businesslogic.bewegung2.detail
 * @author sebastien
 * @date 04.04.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.detail;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author sebastien
 * @date 04.04.2019
 *
 */
public class B2_maskBt_RecordDetail extends E2_Button {

	
	private Rec21 sourceRecord;


	public B2_maskBt_RecordDetail() throws myException{
		super();
	
		
		this._lwn()._fsa(-1);
		this._aaa(()-> invoke_detail_popUp());
	}
	
	private void invoke_detail_popUp() throws myException {
		B2_containerRecordDetail detail_container = new B2_containerRecordDetail(this.sourceRecord);
		detail_container.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1600), new Extent(1000), S.ms("Anzeige der Datenstuktur ..."));
	}
	

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		if(dataObject != null) {
			this.sourceRecord = dataObject.rec21();
			this._t(bibALL.convertID2FormattedID(dataObject.rec21().get_key_value()));
		}
	}
}
