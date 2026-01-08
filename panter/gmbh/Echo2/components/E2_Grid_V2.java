/**
 * panter.gmbh.Echo2.components
 * @author martin
 * @date 20.03.2019
 * 
 */
package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 20.03.2019
 * grid mit dem interface IF_RB_Component
 */
public class E2_Grid_V2 extends E2_Grid implements IF_RB_Component {

	
	private RB_KF key = null;

	
	public E2_Grid_V2() {
		super();
	}

	@Override
	public void mark_Neutral() throws myException {
	}

	@Override
	public void set_Alignment(Alignment align) throws myException {
	}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
	}

	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
	}

	@Override
	public RB_KF rb_KF() throws myException {
		return this.key;
	}

	@Override
	public void set_rb_RB_K(RB_KF p_key) throws myException {
		this.key=p_key;
	}

	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() throws myException {
		return null;
	}

	@Override
	public E2_Grid_V2 _a() {
		super._a();
		return this;
	}

	@Override
	public E2_Grid_V2 _a(String text) {
		super._a(text);
		return this;
	}

	@Override
	public E2_Grid_V2 _a(MyString text) {
		super._a(text);
		return this;
	}

	@Override
	public E2_Grid_V2 _a(String text, GridLayoutData gl) {
		super._a(text, gl);
		return this;
	}

	@Override
	public E2_Grid_V2 _a(MyString text, GridLayoutData gl) {
		super._a(text, gl);
		return this;
	}

	@Override
	public E2_Grid_V2 _a(Component c) {
		super._a(c);
		return this;
	}

	@Override
	public E2_Grid_V2 _a(GridLayoutData layoutFirst, GridLayoutData layoutRest, Component... c) {
		super._a(layoutFirst, layoutRest, c);
		return this;
	}

	@Override
	public E2_Grid_V2 _a(GridLayoutData layoutFirst, Component... c) {
		super._a(layoutFirst, c);
		return this;
	}

	@Override
	public E2_Grid_V2 _a(Component c, GridLayoutData gl) {
		super._a(c, gl);
		return this;
	}

	@Override
	public E2_Grid_V2 _add_raw(Component c) {
		super._add_raw(c);
		return this;
	}

	@Override
	public E2_Grid_V2 _clear() {
		super._clear();
		return this;
	}

	@Override
	public E2_Grid_V2 _a_rt(Component c) {
		super._a_rt(c);
		return this;
	}

	@Override
	public E2_Grid_V2 _a_ct(Component c) {
		super._a_ct(c);
		return this;
	}

	@Override
	public E2_Grid_V2 _a_lt(Component c) {
		super._a_lt(c);
		return this;
	}

	@Override
	public E2_Grid_V2 _a_rm(Component c) {
		super._a_rm(c);
		return this;
	}

	@Override
	public E2_Grid_V2 _a_cm(Component c) {
		super._a_cm(c);
		return this;
	}

	@Override
	public E2_Grid_V2 _a_lm(Component c) {
		super._a_lm(c);
		return this;
	}

	@Override
	public E2_Grid_V2 _a_rb(Component c) {
		super._a_rb(c);
		return this;
	}

	@Override
	public E2_Grid_V2 _a_cb(Component c) {
		super._a_cb(c);
		return this;
	}

	@Override
	public E2_Grid_V2 _a_lb(Component c) {
		super._a_lb(c);
		return this;
	}

	@Override
	public E2_Grid_V2 _bc(Color back_col) {
		super._bc(back_col);
		return this;
	}

	@Override
	public E2_Grid_V2 _bo(Border bord) {
		super._bo(bord);
		return this;
	}

	@Override
	public E2_Grid_V2 _bo_b() {
		super._bo_b();
		return this;
	}

	@Override
	public E2_Grid_V2 _bo_dg() {
		super._bo_dg();
		return this;
	}

	@Override
	public E2_Grid_V2 _bo_green() {
		super._bo_green();
		return this;
	}

	@Override
	public E2_Grid_V2 _bo_red() {
		super._bo_red();
		return this;
	}

	@Override
	public E2_Grid_V2 _bo_col(Color col) {
		super._bo_col(col);
		return this;
	}

	@Override
	public E2_Grid_V2 _bo_d() {
		super._bo_d();
		return this;
	}

	@Override
	public E2_Grid_V2 _bo_dd() {
		super._bo_dd();
		return this;
	}

	@Override
	public E2_Grid_V2 _bo_ddd() {
		super._bo_ddd();
		return this;
	}

	@Override
	public E2_Grid_V2 _bo_no() {
		super._bo_no();
		return this;
	}

	@Override
	public E2_Grid_V2 _w(Extent width) {
		super._w(width);
		return this;
	}

	@Override
	public E2_Grid_V2 _w100() {
		super._w100();
		return this;
	}

	@Override
	public E2_Grid_V2 _st(MutableStyle style) {
		super._st(style);
		return this;
	}

	@Override
	public E2_Grid_V2 _setSize(int... i) {
		super._setSize(i);
		return this;
	}

	@Override
	public E2_Grid_V2 _setSize(Extent... colExt) {
		super._setSize(colExt);
		return this;
	}

	@Override
	public E2_Grid_V2 _setRowHight(int... i) {
		super._setRowHight(i);
		return this;
	}

	@Override
	public E2_Grid_V2 _setRowH(int i_rownumber, int row_height) {
		super._setRowH(i_rownumber, row_height);
		return this;
	}

	@Override
	public E2_Grid_V2 _startLine(GridLayoutData gl) throws myException {
		super._startLine(gl);
		return this;
	}

	@Override
	public E2_Grid_V2 _setSizeSingleLine() {
		super._setSizeSingleLine();
		return this;
	}

	@Override
	public E2_Grid_V2 _addSeparator(int left, int top, int right, int bottom) {
		super._addSeparator(left, top, right, bottom);
		return this;
	}

	@Override
	public E2_Grid_V2 _addSeparator() {
		super._addSeparator();
		return this;
	}

	@Override
	public E2_Grid_V2 _line(int space, int[] size, Component... comps) throws myException {
		super._line(space, size, comps);
		return this;
	}

	
	
}
