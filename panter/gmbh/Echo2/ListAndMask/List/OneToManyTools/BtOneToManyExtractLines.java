/**
 * panter.gmbh.Echo2.ListAndMask.List.OneToManyTools
 * @author martin
 * @date 16.08.2019
 * 
 */
package panter.gmbh.Echo2.ListAndMask.List.OneToManyTools;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 16.08.2019
 *
 * Button class for E2_NaviList. Extracts alle lines in list, which are corresponding to a one-Number
 *
 */
public abstract class BtOneToManyExtractLines extends E2_Button {


	private OneToMany  oneToMany = null;
	
	/**
	 * @author martin
	 * @date 16.08.2019
	 *
	 */
	public BtOneToManyExtractLines(OneToMany o2mRelation) {
		super();
		this.setButtonShapeOnListBuild();
		
		this.oneToMany = o2mRelation;
		
		this._aaa(new OwnAction());
	}

	
	public abstract void setButtonShapeOnListBuild() ;
	public abstract void setButtonShapeAfterClickAction() ;
	
	public abstract Long findOneId();
	public abstract E2_NavigationList  findNaviList();

	
	
	private class OwnAction extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			E2_NavigationList navi = findNaviList();
			if (navi.getStatusSaver()==null) {
				navi.saveStatus(bibMSG.MV());
				
				navi.get_vectorSegmentation().clear();
				
				Long id = findOneId();
				
				VEK<Long> manyIds = oneToMany.getManyIds(id);
				
				manyIds.stream().forEach(l-> navi.get_vectorSegmentation().add(l.toString()));
			
				navi._RebuildListWithActualIds();
			}
		}
		
	}
	
	
	
}
