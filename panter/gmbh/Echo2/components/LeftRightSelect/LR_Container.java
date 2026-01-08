package panter.gmbh.Echo2.components.LeftRightSelect;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.BasicInterfaces.IF_Refreshable;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;

public abstract class LR_Container<T> extends MyE2_Grid {
	private refreshGrid 	 			grid4selection_left = null;
	private refreshGrid  				grid4selection_right = null;
	private Vector<LR_CB<T>>      		v_cb_left = new Vector<>();    // checkboxen (alle aus dem pool)
	private Vector<LR_CB<T>>       		v_cb_right = new Vector<>();   // checkbox-auswahl rechts

	private MyE2_CheckBox  				cb_save_settings = new MyE2_CheckBox(new MyE2_String("Auswahl speichern")
																	,new MyE2_String("Wenn der Haken gesetzt wird, dann wird die aktuelle Auswahl für den Export beim Benutzer gespeichert!"));
			
	
	public LR_Container(MutableStyle oStyle, int spaltenbreite) throws myException {
		super(2,oStyle);
		
		this.cb_save_settings.setSelected(true);
		
		this.setColumnWidth(0, new Extent(spaltenbreite));
		this.setColumnWidth(1, new Extent(spaltenbreite));
		
		this.grid4selection_left = new refreshGrid(this.v_cb_left);
		this.grid4selection_right = new refreshGrid(this.v_cb_right);
		
		MyE2_ContainerEx containerEx_left = new MyE2_ContainerEx();
		containerEx_left.setWidth(new Extent(400));
		containerEx_left.setHeight(new Extent(520));
		containerEx_left.add(grid4selection_left);

		MyE2_ContainerEx containerEx_right = new MyE2_ContainerEx();
		containerEx_right.setWidth(new Extent(400));
		containerEx_right.setHeight(new Extent(520));
		containerEx_right.add(grid4selection_right);
		
		this.add_content_before_left_right_panel();

		this.add(containerEx_left,1, E2_INSETS.I(3,3,3,3));
		this.add(containerEx_right,1, E2_INSETS.I(3,3,3,3));
		
		this.add_content_after_left_right_panel();
	}


	public abstract void add_content_before_left_right_panel() throws myException;
	public abstract void add_content_after_left_right_panel() throws myException;
	

	private class refreshGrid extends MyE2_Grid  implements IF_Refreshable {
		private Vector<LR_CB<T>> v_my_vektor = null;
		public refreshGrid(Vector<LR_CB<T>> p_v_my_vektor) {
			super(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
			this.v_my_vektor = p_v_my_vektor;
		}

		@Override
		public void refresh() throws myException {
			this.removeAll();
			for (LR_CB<T> cb: this.v_my_vektor) {
				this.add(cb,E2_INSETS.I(0,0,0,0));
			}
		}
	}

	public LR_Container<T> refresh_left() throws myException {
		this.get_grid4selection_left().refresh();
		return this;
	}

	public LR_Container<T> refresh_right() throws myException {
		this.get_grid4selection_right().refresh();
		return this;
	}
	

	public refreshGrid get_grid4selection_left() {
		return grid4selection_left;
	}


	public refreshGrid get_grid4selection_right() {
		return grid4selection_right;
	}


	public Vector<LR_CB<T>> get_v_cb_left() {
		return v_cb_left;
	}


	public Vector<LR_CB<T>> get_v_cb_right() {
		return v_cb_right;
	}


	public MyE2_CheckBox get_cb_save_settings() {
		return cb_save_settings;
	}

}
