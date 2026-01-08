package panter.gmbh.Echo2.components.LeftRightSelect2;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.BasicInterfaces.IF_Refreshable;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;

public abstract class LR_Container2      extends MyE2_Grid {
	private refreshGrid 	 				grid4selection_left = null;
	private refreshGrid  					grid4selection_right = null;
	private Vector<LR_CB2>      			v_cb_left = new Vector<>();    // checkboxen (alle aus dem pool)
	private Vector<LR_CB2>       			v_cb_right = new Vector<>();   // checkbox-auswahl rechts

	
	public LR_Container2(MutableStyle oStyle, int spaltenbreiteLinks, int spaltenbreiteRechts, int ihoeheBlock) throws myException {
		super(2,oStyle);
//		this.set_style_and_size(oStyle, spaltenbreiteLinks, spaltenbreiteRechts, ihoeheBlock);
	}

	public LR_Container2() throws myException {
		super(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
//		this.set_style_and_size(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS(), 200, 200, 50);
	}

	
	public void set_style_and_size(MutableStyle oStyle, int spaltenbreiteLinks, int spaltenbreiteRechts, int ihoeheBlock)  throws myException{
		this.setStyle(oStyle);
		this.setColumnWidth(0, new Extent(spaltenbreiteLinks));
		this.setColumnWidth(1, new Extent(spaltenbreiteRechts));
		
		this.grid4selection_left = new refreshGrid(this.v_cb_left);
		this.grid4selection_right = new refreshGrid(this.v_cb_right);
		
		MyE2_ContainerEx containerEx_left = new MyE2_ContainerEx();
		containerEx_left.setWidth(new Extent(spaltenbreiteLinks));
		containerEx_left.setHeight(new Extent(ihoeheBlock));
		containerEx_left.add(grid4selection_left);

		MyE2_ContainerEx containerEx_right = new MyE2_ContainerEx();
		containerEx_right.setWidth(new Extent(spaltenbreiteRechts));
		containerEx_right.setHeight(new Extent(ihoeheBlock));
		containerEx_right.add(grid4selection_right);
		
		this.add_content_before_left_right_panel();

		this.add(containerEx_left,1, E2_INSETS.I(3,3,3,3));
		this.add(containerEx_right,1, E2_INSETS.I(3,3,3,3));
		
		this.add_content_after_left_right_panel();

	}
	
	
	public abstract void 				add_content_before_left_right_panel() throws myException;
	public abstract void 				add_content_after_left_right_panel() throws myException;
	public abstract LR_ObjectExtender  	generate_object_extender() throws myException;
	

	private class refreshGrid extends MyE2_Grid  implements IF_Refreshable {
		private Vector<LR_CB2> v_my_vektor = null;
		public refreshGrid(Vector<LR_CB2> p_v_my_vektor) {
			super(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
			this.v_my_vektor = p_v_my_vektor;
		}


		@Override
		public void refresh() throws myException {
			LR_Container2 oThis = LR_Container2.this;
			
			this.removeAll();
			for (LR_CB2 cb: this.v_my_vektor) {
				if (oThis instanceof LR_IF_wrap_Component) {
					this.add(((LR_IF_wrap_Component)oThis).component_4_list(cb,cb.get_place_4_everything()),E2_INSETS.I(0,0,0,0));	
				} else {
					this.add(cb,E2_INSETS.I(0,0,0,0));
				}
			}
		}
		
	}

	public LR_Container2 refresh_left() throws myException {
		this.get_grid4selection_left().refresh();
		return this;
	}

	public LR_Container2 refresh_right() throws myException {
		this.get_grid4selection_right().refresh();
		return this;
	}
	

	public refreshGrid get_grid4selection_left() {
		return grid4selection_left;
	}


	public refreshGrid get_grid4selection_right() {
		return grid4selection_right;
	}


	public Vector<LR_CB2> get_v_cb_left() {
		return v_cb_left;
	}


	public Vector<LR_CB2> get_v_cb_right() {
		return v_cb_right;
	}



}
