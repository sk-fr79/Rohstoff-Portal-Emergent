package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_mapCollector_4_FZ_MaskModulContainer;


public class FZ_MASK_ContainerSegmentExtenderButton extends E2_Button {
	
	public enum STATUS {
		OPEN
		,CLOSED
	}
	
	private IF_mapCollector_4_FZ_MaskModulContainer  	mapCollector = null;
	private FZ_MASK_MaskModulContainer  				maskContainer = null;
	private FZ_MASK_ContainerSegmentInLine   			maskSegment = null;
	
	private E2_ResourceIcon    							icon_closed = 	E2_ResourceIcon.get_RI("expandopen.png");
	private E2_ResourceIcon    							icon_open = 	E2_ResourceIcon.get_RI("expandclose.png");
	
	private STATUS 										status = STATUS.CLOSED;

	

	public FZ_MASK_ContainerSegmentExtenderButton(	FZ_MASK_MaskModulContainer 				p_maskContainer, 
													IF_mapCollector_4_FZ_MaskModulContainer p_mapCollector, 
													FZ_MASK_ContainerSegmentInLine 			p_maskSegment) {
		super();
		this.mapCollector = p_mapCollector;
		this.maskContainer = p_maskContainer;
		this.maskSegment = p_maskSegment;
		this._image(this.icon_closed);
		this.add_oActionAgent(new ownActionOpenClose());
	}

	/**
	 * 
	 * @return MapCollector zu dem der button gehoert
	 */
	public IF_mapCollector_4_FZ_MaskModulContainer getMapCollector() {
		return mapCollector;
	}

	/**
	 * 
	 * @return maskContainer
	 */
	public FZ_MASK_MaskModulContainer getMaskContainer() {
	
		return maskContainer;
	}


	/**
	 * 
	 * @return Segment
	 */
	public FZ_MASK_ContainerSegmentInLine getMaskSegment() {
		return maskSegment;
	}
	
	
	public void set_status_CLOSED() {
		this.status=STATUS.CLOSED;
		this._image(this.icon_closed);
	}
	
	
	
	private class ownActionOpenClose extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			FZ_MASK_ContainerSegmentExtenderButton oThis = FZ_MASK_ContainerSegmentExtenderButton.this;
			IF_mapCollector_4_FZ_MaskModulContainer ownCollector = FZ_MASK_ContainerSegmentExtenderButton.this.mapCollector;
			
			//zuerst alle buttons auf status closed
			for (FZ_MASK_ContainerSegmentInLine segment:ownCollector.get_VectorOfSegments_in_maskline()) {
				if (segment != oThis.maskSegment) {
					if (segment.get_extender()!=null) {
						segment.get_extender().get_extenderButton().set_status_CLOSED();
					}
				}
			}
			
			
			if (oThis.status==STATUS.OPEN) {
				oThis.mapCollector.set_actual_opened_segmentObject(null);
				oThis.status=STATUS.CLOSED;
				oThis._image(oThis.icon_closed);
			} else {
				oThis.mapCollector.set_actual_opened_segmentObject(oThis.maskSegment);
				oThis.status=STATUS.OPEN;
				oThis._image(oThis.icon_open);
			}
			oThis.maskContainer.rebuild_container_grid();
			
			
		}
	}
	
	
}
