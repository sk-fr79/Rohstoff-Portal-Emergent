package rohstoff.businesslogic.bewegung2.list.listSelector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown2;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.businesslogic.bewegung2.global.EnTabKeyInMask;

public class B2_listSelector_firmaBesitzer extends E2_ListSelectorMultiDropDown2{

	private EnTabKeyInMask pos;

	private IF_CanBePopulated_NG angeschlossene = null;


	public B2_listSelector_firmaBesitzer(IF_CanBePopulated_NG p_angeschlossene ,EnTabKeyInMask p_pos) throws myException{

		super();

		this.angeschlossene = p_angeschlossene;

		this.pos = p_pos;

		
		SEL u_hauptadresse = new SEL()
				.ADDFIELD("to_nchar('* HAUPTADRESSE: ') || NVL(AD.NAME1,'-')||'-'||  NVL(AD.NAME2,'-')|| '-'||  NVL(AD.ORT,'-') ||' ('||TO_CHAR(  NVL(AD.ID_ADRESSE,0))||')' AS NAMEN")
				.ADDFIELD("AD.ID_ADRESSE AS  ID_ADRESSE")
				.FROM(_TAB.adresse, "AD")
				.WHERE(new vgl("AD", ADRESSE.id_adresse,bibALL.get_ID_ADRESS_MANDANT()))
				;

		SEL u_adressen = new SEL()
				.ADDFIELD("NVL(AD.NAME1,'-')||'-'||  NVL(AD.NAME2,'-')|| '-'||  NVL(AD.ORT,'-') ||' ('||TO_CHAR(  NVL(AD.ID_ADRESSE,0))||')' AS NAMEN")
				.ADDFIELD("AD.ID_ADRESSE AS  ID_ADRESSE")
				.FROM(_TAB.bg_station, "st")
				.LEFTOUTER("JT_ADRESSE AD", "ST.id_adresse_basis","AD.id_adresse")
				.WHERE(new vgl("AD", ADRESSE.id_adresse,COMP.GT.ausdruck(),"1000"))
				.AND(new vgl("ST" , BG_STATION.pos_in_mask,pos.dbVal() ))
				.ORDER("NAMEN")
				;

		String cSql =  u_hauptadresse.s() + " UNION " + u_adressen.s();

		B2_SelectField selField = new B2_SelectField(cSql,false, true, false, false);
		selField._fo_s_plus(-2);	
		
		And bedingung = new And();
		if(pos == EnTabKeyInMask.S1) {
			bedingung.and("S1.id_adresse_basis", COMP.EQ, "#WERT#");
		}else if(pos == EnTabKeyInMask.S3) {
			bedingung.and("S3.id_adresse_basis", COMP.EQ, "#WERT#");
		}

		this.get_vAgents4ActiveComponentsAfterSelection().add(new ownRefreshAction());
		
		this.INIT(selField,bedingung.s(), null);
	}

	public VEK<String> getSelectedIds() throws myException
	{
		return new VEK<String>()._a(this.get_SelectedValues());
	}


	public E2_BasicModuleContainer get_PopupContainer() throws myException {
		return new ownBasicContainer();
	}

	@Override
	public Component get_oComponentForSelection() throws myException {
		E2_Grid grd = new E2_Grid()._setSize(20,110)._bo_no()._a(this.get_oComponentWithoutText(), 	new RB_gld()._left_mid());
		return grd;
	}

	public void reload() throws myException {
		if(this.angeschlossene != null) {
			this.angeschlossene.populate(new VEK<String>()._a(getSelectedIds()));
		}
	}

	private class ownBasicContainer extends E2_BasicModuleContainer{}
	
	private class ownRefreshAction extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			B2_listSelector_firmaBesitzer.this.reload();
		}

		
	}
}
