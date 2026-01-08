/**
 * rohstoff.Echo2BusinessLogic.BAM_FUHREN
 * @author martin
 * @date 18.06.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.BAM_FUHREN;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_V2;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListSorterNG;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.basics4project.DB_ENUMS.FBAM;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

/**
 * @author martin
 * @date 18.06.2020
 *
 */
public class BAMF_ListCompShowDateFields extends E2_UniversalListComponent {

	private static String key = "<multi-dates>44733eb8-b168-11ea-b3de-0242ac130004";
	
	/**
	 * @author martin
	 * @date 18.06.2020
	 *
	 */
	public BAMF_ListCompShowDateFields()  {
		this._clear()._setSize(100);
		
		E2_Grid gridTitle = new E2_Grid()._setSize(100);
		gridTitle._a(new OwnSorter(FBAM.bam_datum,"Fehler-Dat."), new RB_gld()._center_mid()._ins(0,1,0,2))._a(new OwnSorter(FBAM.datum_ausstellung,"Ausst.Datum"), new RB_gld()._center_mid());
		this.EXT().set_oCompTitleInList(gridTitle);

		
	}

	@Override
	public String key() throws myException {
		return key;
	}

	@Override
	public String userText() throws myException {
		return "Datumsfelder";
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear()._setSize(100);
	}

	@Override
	public void populate(SQLResultMAP resultMap) {
		try {
			E2_ComponentMAP_V2 map = (E2_ComponentMAP_V2)this.EXT().get_oComponentMAP();
			Rec21 recBAM = map.getRec21();
			
			if (recBAM!=null) {
				E2_Button btBamDat = 			new E2_Button()._t(recBAM.getFs(FBAM.bam_datum, "<Fehler-Dat.>"))			._aaa(new ActionSelectLine())._setShapeStyleLikeLabel();
				E2_Button btBamausstellDatum = 	new E2_Button()._t(recBAM.getFs(FBAM.datum_ausstellung, "<Austell-Dat.>"))	._aaa(new ActionSelectLine())._setShapeStyleLikeLabel();
				if (recBAM.getRawVal(FBAM.bam_datum)==null) {
					btBamDat._fsa(-2)._i();
				}
				if (recBAM.getRawVal(FBAM.datum_ausstellung)==null) {
					btBamausstellDatum._fsa(-2)._i();
				}
				this._a(btBamDat, new RB_gld()._center_mid()._ins(0,1,0,2));
				this._a(btBamausstellDatum, new RB_gld()._center_mid());

			}
		} catch (Exception e) {
			this._clear()._a(new RB_lab("@Error@"));
		}
		
	}

	
	
	
	public BAMF_ListCompShowDateFields get_Copy(Object objHelp) throws myExceptionCopy {
		
		BAMF_ListCompShowDateFields copy = new BAMF_ListCompShowDateFields();
		return copy;
		
	}
	
	
	private class ActionSelectLine extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			try {
				E2_ComponentMAP_V2 map = (E2_ComponentMAP_V2)EXT().get_oComponentMAP();

				if (map != null) {
					EXT().get_oComponentMAP().set_CheckBoxForList_ToggleSelected();
				}
			} catch (Exception e) {
				e.printStackTrace();
				bibMSG.MV()._addAlarm(e.getLocalizedMessage()+"<2627748a-b16b-11ea-b3de-0242ac130004>");
			}
			
		}
		
	}


	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
		this.setEnabled(true);
	}
	
	
	
	protected class OwnSorter extends E2_ButtonListSorterNG {
		
		public OwnSorter(IF_Field field, String text) {
			super();
			
			String sortUp = field.tnfn()+" ASC";
			String sortDwn = field.tnfn()+" DESC";
			
			this._setSortTermUp(sortUp)._setSortTermDown(sortDwn);
			this._setButtonText(S.ms(text));
		}

		@Override
		public E2_NavigationList getNavigationList() throws myException {
			return BAMF_ListCompShowDateFields.this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to();
		}
		
	}
	
}
