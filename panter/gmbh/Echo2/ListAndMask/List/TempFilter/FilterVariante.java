package panter.gmbh.Echo2.ListAndMask.List.TempFilter;

import java.util.Collection;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.myVectors.VEKSingle;

@SuppressWarnings("rawtypes")
public class FilterVariante {

	//welche ids sind in der auswahl
	private VEKSingle<String>   	vIdMembers = new VEKSingle<>();
	
	//schluessel der variante
	private Enum       				KEY = null;
	
	//variante aktiv ?
	private boolean    				aktiv = false;

	
	private String   				text4Checkbox = null;
	
	private RB_cb                   cb = new RB_cb()._check();
	
	public Vector<String> getvIdMembers() {
		return vIdMembers;
	}

	public Enum getKEY() {
		return KEY;
	}

	public boolean isAktiv() {
		return aktiv;
	}

	public FilterVariante _setKEY(Enum p_key) {
		KEY = p_key;
		this.cb._t(this.KEY.name());
		return this;
	}

	/**
	 * 
	 * @param text  (is translated)
	 * @return
	 */
	public FilterVariante _setTextTr(String text) {
		this.text4Checkbox = new MyE2_String(text,false).CTrans();
		this.cb._t(this.text4Checkbox);
		return this;
	}

	public FilterVariante _setTextTr(MyString text) {
		this.text4Checkbox = text.CTrans();
		this.cb._t(this.text4Checkbox);
		return this;
	}
	
	/**
	 * 
	 * @param text (not translated)
	 * @return
	 */
	public FilterVariante _setText(String text) {
		this.text4Checkbox = text;
		this.cb._t(this.text4Checkbox);
		return this;
	}
	
	public FilterVariante setAktiv(boolean p_aktiv) {
		this.aktiv = p_aktiv;
		return this;
	}
	
	public FilterVariante _addIDs(Collection<String> ids) {
		this.vIdMembers.addAll(ids);
		//this.cb._t(this.text4Checkbox);
		return this;
	}

	public RB_cb getCB() {
		return cb;
	}

	
	
	
}
