/**
 * rohstoff.businesslogic.bewegung2.detail
 * @author sebastien
 * @date 21.03.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.detail;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;


public class B2_listBt_RecordDetail extends E2_Button {

	private Rec21 sourceRecord = null;
	private String knopf_text = "";
	
	public B2_listBt_RecordDetail(Rec21 p_sourceRecord, String strKnopfInhalt) throws myException{
		super();
		
		sourceRecord 	= p_sourceRecord;
		knopf_text 		= strKnopfInhalt;
		
		this._lwn()._fsa(-1)._bc(new E2_ColorBase())._t(knopf_text);
		this._aaa(()-> invoke_detail_popUp(this.sourceRecord));
	}

	public B2_listBt_RecordDetail() throws myException{
		super();
		
		this._lwn()._fsa(-1)._t(knopf_text);
		this._aaa(()-> invoke_detail_popUp(this.sourceRecord));
	}
	
	private void invoke_detail_popUp(Rec21 sourceRecord2) throws myException {
		B2_containerRecordDetail detail_container = new B2_containerRecordDetail(sourceRecord2);
		detail_container.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1600), new Extent(1000), S.ms("Anzeige der Datenstuktur ..."));
	}
}
