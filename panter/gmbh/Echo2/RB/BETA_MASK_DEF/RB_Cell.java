package panter.gmbh.Echo2.RB.BETA_MASK_DEF;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public class RB_Cell{
	private int 		column_span 	= 1;
	private int 		row_span		= 1;
	private int 		column_coordinate;
	private int 		row_coordinate;
	
	private String 		usertext;
	private IF_Field 	field;
	
	private IF_RB_Component	component;
	
	private Alignment 	align;
	private int 		textSize;
	private boolean 	isBold;
	private boolean 	isItalic;

	private int 		left_insets;
	private int 		top_insets;
	private int 		right_insets;
	private int 		bottom_insets;
	
	public RB_Cell() throws myException{
		super();

	}
	
	public int get_column_span() {
		return column_span;
	}

	public int get_row_span() {
		return row_span;
	}

	public int get_column_coordinate() {
		return column_coordinate;
	}

	public int get_row_coordinate() {
		return row_coordinate;
	}
	
	public IF_RB_Component get_component() {
		return this.component;
	}
	
	public E2_Grid getRenderedContainer() throws myException{
		E2_Grid grid = new E2_Grid()._s(1);
		
		if(field != null && S.isAllFull(usertext)) {
			this.component._ttt(usertext);
			this.component.set_rb_RB_K(field.fk());
		} 
		else if(field != null && S.isFull(field.fieldName()) && S.isEmpty(usertext) ){
			this.component.set_rb_RB_K(field.fk());
		}
		else if(S.isFull(usertext) && field == null){
			this.component.rb_set_db_value_manual(usertext);
			this.component._ttt(usertext);
		}
		else {
			
		}
		
		if(this.component != null) {
			grid._a(this.component.c());
		}else {
			grid._a(new RB_lab("-")._col_fore_lgrey()._ttt("Keine Komponent"));
		}
		
		return grid;
	}
	
	public String getUsertext() {
		return usertext;
	}

	public IF_Field getField() {
		return field;
	}
	
	public Alignment getAlign() {
		return align;
	}
	
	public int getTextSize() {
		return textSize;
	}
	
	public RB_Cell _column_span(int column_span) {
		this.column_span = column_span;
		return this;
	}

	public RB_Cell _row_span(int row_span) {
		this.row_span = row_span;
		return this;
	}

	public RB_Cell _column(int column_coordinate) {
		this.column_coordinate = column_coordinate;
		return this;
	}


	public RB_Cell _row(int row_coordinate) {
		this.row_coordinate = row_coordinate;
		return this;
	}


	public RB_Cell _usertext(String usertext) {
		this.usertext = usertext;
		return this;
	}


	public RB_Cell _field(IF_Field field) {
		this.field = field;
		return this;
	}


	public RB_Cell _component(IF_RB_Component component) {
		this.component = component;
		return this;
	}

	
	public RB_Cell _align(Alignment align) {
		this.align = align;
		return this;
	}

	
	public RB_Cell _set_text_size(int textSize) {
		this.textSize = textSize;
		return this;
	}

	public boolean is_bold() {
		return isBold;
	}

	public RB_Cell _set_bold(boolean isBold) {
		this.isBold = isBold;
		return this;
	}

	public boolean is_italic() {
		return isItalic;
	}

	public RB_Cell _set_italic(boolean isItalic) {
		this.isItalic = isItalic;
		return this;
	}

	public int get_left_insets() {
		return left_insets;
	}

	public RB_Cell _set_left_insets(int left_insets) {
		this.left_insets = left_insets;
		return this;
	}

	public int get_top_insets() {
		return top_insets;
	}

	public RB_Cell _set_top_insets(int top_insets) {
		this.top_insets = top_insets;
		return this;
	}

	public int get_right_insets() {
		return right_insets;
	}

	public RB_Cell _set_right_insets(int right_insets) {
		this.right_insets = right_insets;
		return this;
	}

	public int get_bottom_insets() {
		return bottom_insets;
	}

	public Insets get_insets() {
		return E2_INSETS.I(left_insets, top_insets, right_insets, bottom_insets);
	}
	
	public RB_Cell _set_bottom_insets(int bottom_insets) {
		this.bottom_insets = bottom_insets;
		return this;
	}

	
	
	
}
