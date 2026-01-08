/**
 * panter.gmbh.Echo2.RB.COMP.SearchFieldV2
 * @author martin
 * @date 19.09.2019
 * 
 */
package panter.gmbh.Echo2.RB.COMP.SearchFieldV2;

import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;

/**
 * @author martin
 * @date 19.09.2019
 *
 */
public class ResultButton extends E2_Button {

	public String 					sortString= null;
	public Rec21		 			rec21 = null;
	public RB_SearchFieldV2 		rbSearchFieldV2=null;
	
	public ResultButton(String p_sortString, Rec21 p_rec21, RB_SearchFieldV2 p_rbSearchFieldV2) {
		super();
		this.sortString = p_sortString;
		this.rec21 = p_rec21;
		this.rbSearchFieldV2 = p_rbSearchFieldV2;
	}

	public String getSortString() {
		return S.NN(sortString);
	}

	public ResultButton _setSortString(String sortString) {
		this.sortString = sortString;
		return this;
	}

	public Rec21 getRec21() {
		return rec21;
	}

	public ResultButton _setRec21(Rec21 rec21) {
		this.rec21 = rec21;
		return this;
	}

	public RB_SearchFieldV2 getRbSearchFieldV2() {
		return rbSearchFieldV2;
	}

	public ResultButton _setRbSearchFieldV2(RB_SearchFieldV2 rbSearchFieldV2) {
		this.rbSearchFieldV2 = rbSearchFieldV2;
		return this;
	}

	
}
