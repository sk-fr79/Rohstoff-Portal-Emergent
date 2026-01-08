/**
 * panter.gmbh.Echo2.components.DB
 * @author martin
 * @date 03.12.2018
 * 
 */
package panter.gmbh.Echo2.components.DB;

import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_IF_Rec21;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListSorterNG;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

/**
 * @author martin
 * @date 03.12.2018
 * generische klasse, um festzustellen, welcher benutzer die letzte aenderung vorgenommen hat, kann in jede liste eingefuegt werden
 */
public class E2_LabelCeateTimeStamp extends E2_Button implements MyE2IF_DB_SimpleComponent, E2_IF_Copy {
	
	private String 				m_sortUpTerm = null;
	private String 				m_sortDownTerm = null;
	private MyE2_String         m_text4Button = null;
	
	private OwnSorter           m_sorterButton = null;
	
	public E2_LabelCeateTimeStamp() {
		super();
		this.setStyle(MyE2_Button.StyleTextButton_LOOK_like_LABEL());
		this._aaa(new ownActionAgent());
	}

	/**
	 * 
	 * @author martin
	 * @date 12.12.2018
	 *
	 * @param sortUp
	 * @param SortDown
	 * @param text4button
	 * @return
	 */
	public E2_LabelCeateTimeStamp _setSortTerms(String sortUp, String SortDown, MyE2_String text4button) {
		this.m_sortUpTerm = sortUp;
		this.m_sortDownTerm = SortDown;
		this.m_text4Button = text4button;
		
		if (S.isAllFull(this.m_sortUpTerm,this.m_sortDownTerm)) {
			//dann einen sortbutton in das ext-objekt
			m_sorterButton = new OwnSorter();
			m_sorterButton._setSortTermUp(this.m_sortUpTerm)._setSortTermDown(this.m_sortDownTerm)._setButtonText(text4button);
			
			this.EXT().set_oCompTitleInList(m_sorterButton);
		}
		
		return this;
	}
	
	@Override
	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP resultmap)	throws myException {
		
		Rec21 rec = this.getRec21(resultmap.getLongId());
		
		String valToShow = "?";
		if (rec!=null && rec.is_ExistingRecord()) {
			valToShow = S.NN(rec.getFDbValueFormated(USER.erzeugt_am.fn(),""));
		}

		
		this.setText(valToShow);
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this.setText("");
	}

	protected Rec21 getRec21(Long id) throws myException {
		E2_ComponentMAP map = this.EXT().get_oComponentMAP();
		if (map instanceof E2_ComponentMAP_IF_Rec21) {
			return ((E2_ComponentMAP_IF_Rec21)map).getRec21();
		}else {
			throw new myException("Please override the method: E2_LabelCeateTimeStamp.getRec21(Long id)");
		}
	}

	@Override
	public E2_LabelCeateTimeStamp get_Copy(Object o) {
		try {
			E2_LabelCeateTimeStamp cop = new E2_LabelCeateTimeStamp()._setSortTerms(this.m_sortUpTerm, this.m_sortDownTerm,m_text4Button);
			cop.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(cop));
			return cop;
		} catch (myExceptionCopy e) {
			e.printStackTrace();
			return null;
		}
	}


	protected class OwnSorter extends E2_ButtonListSorterNG {

		@Override
		public String get_cSORT_STATEMENT_UP() { return m_sortUpTerm;}

		@Override
		public String get_cSORT_STATEMENT_DOWN() { return m_sortDownTerm; }

		@Override
		public E2_NavigationList getNavigationList() throws myException {
			return E2_LabelCeateTimeStamp.this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to();
		}
		
	}


	public OwnSorter getSorterButton() {
		return m_sorterButton;
	}
	
	
	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
	}
	
	private class ownActionAgent extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException	{
			if (EXT().get_oComponentMAP() != null) {
				EXT().get_oComponentMAP().set_CheckBoxForList_ToggleSelected();
			}
		}
	}
}
