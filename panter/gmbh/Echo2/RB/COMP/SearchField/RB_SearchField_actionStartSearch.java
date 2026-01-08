package panter.gmbh.Echo2.RB.COMP.SearchField;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class RB_SearchField_actionStartSearch extends RB_SearchField_AbstractActionStartSearch {

	public RB_SearchField_actionStartSearch(RB_SearchField p_rb_searchfield_this_belongs_to) {
		super(p_rb_searchfield_this_belongs_to);
	}


	@Override
	protected MyE2_MessageVector fillResultButtonArray() throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		if (!this.getRb_searchfield_this_belongs_to().is_allow_empty_searchfield()) {
			if (S.isEmpty(this.getRb_searchfield_this_belongs_to().get_tf_search_input().getText())) {
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte geben Sie Suchbegriffe ein !")));
			}
		}
		if (mv.get_bIsOK()) {
			mv.add_MESSAGE(this.getRb_searchfield_this_belongs_to().execute_searchquery_and_fill_resultbutton_array(this.getLastSearchTextInput()));
		}
		return mv;
	}



}
