/**
 * panter.gmbh.Echo2.RB.COMP
 * @author martin
 * @date 09.12.2019
 * 
 */
package panter.gmbh.Echo2.RB.COMP;

import echopointng.PopUp;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.ResourceImageReference;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 09.12.2019
 *
 */
public class RB_dropDown extends E2_Grid {

    private MyE2_ContainerEx	containerEx	= new MyE2_ContainerEx();
    
    private E2_Grid 			gridForResult = new E2_Grid();
    private E2_Grid   			gridForDropDown = new  E2_Grid();
    
    private PopUp   					btDropDown = new PopUp();
    
	private VEK<E2_Button[]>    		lines = new VEK<>();
    
    
	private int    						resultHeight = 20;
	private int   						resultWidth = 300;
	
	private int    						buttonHeight = 20;
	private int   						buttonWidth = 20;
	
	private int   						dropDownHeight = 300;
	private int  						dropDownWidth = 500;
	
	private int  						offsetDownKorrection = 5;
	
	private VEK<Rec21>          		valueList = new VEK<>();
	
	
	private RenderHelper<String>    	rendererGridForResult = null;
	private RenderHelper<RecList21>     rendererGridForDropDown = null;
	
	
	
	public RB_dropDown() {
		super();
 		
		
		_setShapeStandard();
	}



	public RB_dropDown _setIcon(ResourceImageReference icon) {
		btDropDown.setToggleIcon(icon);
		btDropDown.setTogglePressedIcon(icon);
		btDropDown.setToggleRolloverIcon(icon);
		
		return this;
	}
	
	
	
	public RB_dropDown _setShapeStandard()  {
		
		_setIcon(E2_ResourceIcon.get_RI("popdownflat2.png"));

		btDropDown.setPopUpOnRollover(false);
		btDropDown.setPopUpAlwaysOnTop(true);
		
		this._setSize(resultWidth,buttonWidth);
		
		this.containerEx.setWidth(new Extent(dropDownWidth));
		this.containerEx.setHeight(new Extent(dropDownHeight));
		
		this.containerEx.add(gridForDropDown);
		
		gridForResult._setSize(resultWidth)._bo_dd();
		
		this._a(gridForResult._a(new RB_lab()._t("guten Morgen")), new RB_gld()._left_mid())._a(btDropDown, new RB_gld()._left_mid());
		
        this.btDropDown.setPopUp(containerEx);
        this.btDropDown.setPopUpLeftOffset(-(resultWidth+buttonWidth));
        this.btDropDown.setPopUpTopOffset(dropDownHeight+resultHeight+offsetDownKorrection);

        
        
        gridForDropDown._s(2)._w100()._bord_black();
        gridForDropDown._a(new RB_lab()._t("test 1"))
        				._a(new RB_lab()._t("test 2"))
        				._a(new RB_lab()._t("test 3"))
        				._a(new RB_lab()._t("test 4"))
        				._a(new RB_lab()._t("test 5"))
        				;
        

        
		return this;
	}
	
	
	
	public RB_dropDown _renderGridForResult(String id) throws myException {
		
		if (rendererGridForResult!=null) {
			rendererGridForResult.render(this,id);
		}
		return this;
	}

	
	public RB_dropDown _populateList(RecList21 records) throws myException {
		
		if (rendererGridForDropDown!=null) {
			rendererGridForDropDown.render(this,records);
		}
		return this;

	}



	public RenderHelper<String> getRendererGridForResult() {
		return rendererGridForResult;
	}



	public RB_dropDown _setRendererGridForResult(RenderHelper<String> rendererGridForResult) {
		this.rendererGridForResult = rendererGridForResult;
		return this;
	}



	public RenderHelper<RecList21> getRendererGridForDropDown() {
		return rendererGridForDropDown;
	}



	public RB_dropDown _setRendererGridForDropDown(RenderHelper<RecList21> rendererGridForDropDown) {
		this.rendererGridForDropDown = rendererGridForDropDown;
		return this;
	}



	public E2_Grid getGridForResult() {
		return gridForResult;
	}



	public RB_dropDown _setGridForResult(E2_Grid gridForResult) {
		this.gridForResult = gridForResult;
		return this;
	}



	public E2_Grid getGridForDropDown() {
		return gridForDropDown;
	}



	public RB_dropDown _setGridForDropDown(E2_Grid gridForDropDown) {
		this.gridForDropDown = gridForDropDown;
		return this;
	}
	
	
	
	
	
	public static interface RenderHelper<T> {
		
		public void render(RB_dropDown dd, T value) throws myException;
		
	}

	public PopUp getBtDropDown() {
		return btDropDown;
	}

	
	public RB_dropDown _collapse() {
		this.btDropDown.setExpanded(false);
		return this;
	}
	
	
}
