package panter.gmbh.Echo2.RB.COMP.BETA.RASTER;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.indep.exceptions.myException;

public class RasterBtOpenClose extends E2_Button {

	private Raster raster = null;
	private boolean open = true;

	/**
	 * @param p_raster
	 */
	public RasterBtOpenClose(Raster p_raster) {
		super();
		this.raster = p_raster;
		this._image(E2_ResourceIcon.get_RI("expandclose.png"));
		
		this._aaa(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				RasterBtOpenClose.this.open = !RasterBtOpenClose.this.open;
				if (RasterBtOpenClose.this.open) {
					RasterBtOpenClose.this._image(E2_ResourceIcon.get_RI("expandclose.png"));
				} else {
					RasterBtOpenClose.this._image(E2_ResourceIcon.get_RI("expandopen.png"));
				}
				RasterBtOpenClose.this.raster._clear();
				RasterBtOpenClose.this.raster.generateTitleRasterRowCells();
				RasterBtOpenClose.this.raster.generateRasterRows(RasterBtOpenClose.this.raster);
				RasterBtOpenClose.this.raster.render();
			}
		});
	}

	
	
	/**
	 * 
	 * @param p_raster
	 * @param vAgentsBefore actionagents called before extend
	 * @param vAgentsAfter actionagents called after extend
	 */
	public RasterBtOpenClose(Raster p_raster, Vector<XX_ActionAgent> vAgentsBefore, Vector<XX_ActionAgent> vAgentsAfter) {
		super();
		this.raster = p_raster;
		this._image(E2_ResourceIcon.get_RI("expandclose.png"));

		for (XX_ActionAgent a: vAgentsBefore) {
			this._aaa(a);
		}
		
		this._aaa(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				RasterBtOpenClose.this.open = !RasterBtOpenClose.this.open;
				if (RasterBtOpenClose.this.open) {
					RasterBtOpenClose.this._image(E2_ResourceIcon.get_RI("expandclose.png"));
				} else {
					RasterBtOpenClose.this._image(E2_ResourceIcon.get_RI("expandopen.png"));
				}
				RasterBtOpenClose.this.raster._clear();
				RasterBtOpenClose.this.raster.generateTitleRasterRowCells();
				RasterBtOpenClose.this.raster.generateRasterRows(RasterBtOpenClose.this.raster);
				RasterBtOpenClose.this.raster.render();
			}
		});
		
		for (XX_ActionAgent a: vAgentsAfter) {
			this._aaa(a);
		}

	}

	
	
	
	
	public boolean isOpen() {
		return open;
	}
	
	public RasterBtOpenClose set_open(boolean true_if_open__else_false){
		this.open = true_if_open__else_false;
		this._image(E2_ResourceIcon.get_RI(open?"expandclose.png":"expandopen.png"));
		return this;
	}
}
