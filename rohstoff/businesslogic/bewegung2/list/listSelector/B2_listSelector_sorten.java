package rohstoff.businesslogic.bewegung2.list.listSelector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown2;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.TERM.VglNull;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.bewegung2.global.EnTabKeyInMask;

public class B2_listSelector_sorten extends  E2_ListSelectorMultiDropDown2
{
		
	public B2_listSelector_sorten(E2_SelectionComponentsVector SelVector,EnTabKeyInMask p_EnStationPos) throws myException
	{
		super();
		
		SEL u1 = new SEL().ADDFIELD("DISTINCT NVL(JT_ARTIKEL.ANR1,'<ANR1>')||'-'||NVL(JT_ARTIKEL_BEZ.ARTBEZ1,'<ARTBEZ1>')").ADDFIELD(ARTIKEL_BEZ.id_artikel_bez)
		.FROM(_TAB.bg_atom)
		.INNERJOIN(_TAB.artikel_bez, BG_ATOM.id_artikel_bez, ARTIKEL_BEZ.id_artikel_bez)
		.INNERJOIN(_TAB.artikel, ARTIKEL_BEZ.id_artikel, ARTIKEL.id_artikel)
		.WHERE(new VglNull(BG_ATOM.id_bg_del_info))
		.ORDER("1");
		
		B2_SelectField  selFieldKenner = new B2_SelectField(u1.s(),false,true,false,false);    
		selFieldKenner._fo_s_plus(-2);
		
		And  bed = new And( ((p_EnStationPos==EnTabKeyInMask.A1)?"AB1.":"AB2.")+ARTIKEL_BEZ.id_artikel_bez.fn(), 
			"#WERT#");
		this.INIT(selFieldKenner, bed.s(), null);
	}
	
	@Override
	public E2_BasicModuleContainer get_PopupContainer() throws myException {
		return new ownBasicContainer();
	}
	
	@Override
	public Component get_oComponentForSelection() throws myException {
		
		return this.get_oComponentWithoutText();
	}
	
	
	private class ownBasicContainer extends E2_BasicModuleContainer {}

}
