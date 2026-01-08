package panter.gmbh.Echo2.ListAndMask.List.Selektion;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public abstract class E2_Selector_Monat_Jahr extends XX_ListSelektor {

	private MyE2_SelectField				oSelMonate = null;
	private MyE2_SelectField				oSelJahre = null;
	
	
	public E2_Selector_Monat_Jahr() throws myException {
		super();
		
		this.oSelMonate = 	new MyE2_SelectField(bibALL.get_cSelArrayMonate(),"",false);
		this.oSelJahre = 	new MyE2_SelectField(bibALL.get_cSelJahre(),"",false);
	}

	public E2_Selector_Monat_Jahr set_width_SelMonate(Extent  ext_width) {
		this.oSelMonate.setWidth(ext_width);
		return this;
	}

	public E2_Selector_Monat_Jahr set_width_SelJahr(Extent  ext_width) {
		this.oSelJahre.setWidth(ext_width);
		return this;
	}

	@Override
	public Component get_oComponentForSelection() throws myException {
		return this.get_oComponentWithoutText();
	}


	@Override
	public void add_ActionAgentToComponent(XX_ActionAgent oAgent) {
		this.oSelMonate.add_oActionAgent(oAgent);
		this.oSelJahre.add_oActionAgent(oAgent);
	}

	@Override
	public void doInternalCheck() {

	}

	
	
	public MyE2_SelectField get_oSelMonate() {
		return oSelMonate;
	}
	
	public MyE2_SelectField get_oSelJahre() {
		return oSelJahre;
	}

}
