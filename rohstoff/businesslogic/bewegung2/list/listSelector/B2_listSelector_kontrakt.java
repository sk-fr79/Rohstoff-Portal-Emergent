package rohstoff.businesslogic.bewegung2.list.listSelector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown2;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.bewegung2.global.EnTabKeyInMask;

public class B2_listSelector_kontrakt extends E2_ListSelectorMultiDropDown2{

	private EnTabKeyInMask bgPosition = null;
	
	public B2_listSelector_kontrakt(E2_SelectionComponentsVector oSelVector, EnTabKeyInMask p_EnStationPos) throws myException {
		super();
		
		this.bgPosition = p_EnStationPos;		  
		
		String cEK_VK = 	(p_EnStationPos==EnTabKeyInMask.A1)?"EK":"VK" ;
		
		SEL u1 = new SEL(" NVL(VK.BUCHUNGSNUMMER,'??')||'-'||NVL(VP.POSITIONSNUMMER,'')||' '||"+ 
				" '['||NVL(VP.ANR1,'')||'-'||NVL(VP.ANR2,'')||'] '||" + 
				" TO_CHAR(VP.ANZAHL,'999G999G999', 'NLS_NUMERIC_CHARACTERS='',.''')||' '||NVL(VP.EINHEITKURZ,'??') ||' '|| NVL(VP.ARTBEZ1,'')||''||'   ('||"+ 
				" NVL(TO_CHAR(KT.GUELTIG_VON,'dd.mm.'),'-')||' - '|| NVL(TO_CHAR(KT.GUELTIG_BIS,'dd.mm.'),'-')||')'")
				.ADDFIELD("VP.ID_VPOS_KON")
		.FROM(_TAB.vpos_kon, "VP")
		.LEFTOUTER("JT_VKOPF_KON VK", "VP.ID_VKOPF_KON",COMP.EQ.ausdruck(), "VK.ID_VKOPF_KON")
		.INNERJOIN("JT_VPOS_KON_TRAKT  KT", "KT.ID_VPOS_KON",COMP.EQ.ausdruck(), "VP.ID_VPOS_KON")
		.WHERE( new vgl("VK", VKOPF_KON.vorgang_typ, cEK_VK + "_KONTRAKT" ))
		.AND("NVL(VK.DELETED,'N')","'N'")
		.AND("NVL(VP.DELETED,'N')","'N'")
		
		.ORDERUP("KT.GUELTIG_BIS DESC,VP.ANR1");
		;
		
		B2_SelectField  selFieldKenner = new B2_SelectField(u1.s(),false,true,false,false);  
		selFieldKenner._fo_s_plus(-2);
		And bedingung = new And( ( (bgPosition==EnTabKeyInMask.A1)?"VP1":"VP2")+".ID_VPOS_KON","#WERT#");
		
		this.INIT(selFieldKenner, bedingung.s(), null);
		
	}

	@Override
	public E2_BasicModuleContainer get_PopupContainer() throws myException {
		return new ownBasicContainer();
	}
	
	@Override
	public Component get_oComponentForSelection() throws myException {
		
		this.get_oSelFieldBasis().setWidth(new Extent(100));
		
		 E2_Grid  gridHelp = new E2_Grid()._s(1)._a(this.get_oComponentWithoutText(), new RB_gld()._ins(0,2,10,2)._left_mid());
		return gridHelp;
	}
	
	private class ownBasicContainer extends E2_BasicModuleContainer {}
}
