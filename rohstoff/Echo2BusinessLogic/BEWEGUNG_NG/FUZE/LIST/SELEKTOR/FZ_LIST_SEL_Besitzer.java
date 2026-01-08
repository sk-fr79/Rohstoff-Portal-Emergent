package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST.SELEKTOR;

import java.util.HashMap;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown2;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_SelectField.ListCellRendererInfo;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.MANDANT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

public class FZ_LIST_SEL_Besitzer extends  E2_ListSelectorMultiDropDown2 {
	
	
	public FZ_LIST_SEL_Besitzer(boolean bStart) throws myException {
		super();
		
		SEL sql = new SEL("JT_ADRESSE.NAME1||' '||JT_ADRESSE.ORT")
					.ADDFIELD(ADRESSE.id_adresse.t())
					.FROM(_TAB.bewegung_station)
					.LEFTOUTER(_TAB.adresse, BEWEGUNG_STATION.id_adresse_besitzer, ADRESSE.id_adresse)
					.ADD_Distinct()
					.WHERE(new vgl(BEWEGUNG_STATION.mengenvorzeichen,bStart?"-1":"1"))
					.ORDERUP("1");
		
	//	DEBUG.System_println(sql.s());
		
		String[][] sel_werte = bibDB.EinzelAbfrageInArray(sql.s(), "");
		if (sel_werte==null || sel_werte.length==0) {
			sel_werte=new String[1][2];
			sel_werte[0][0]=bibALL.get_RECORD_MANDANT().fs(MANDANT.name1, "<mandant>");
			sel_werte[0][1]=bibALL.get_RECORD_MANDANT().ufs(MANDANT.id_mandant);
		}
		
		RECORD_ADRESSE_extend  rec_adresse_mandant = new RECORD_ADRESSE_extend(bibALL.get_ID_ADRESS_MANDANT());
		String[][] pre_values = {{"--",""},{rec_adresse_mandant.get__FullNameAndAdress_Typ1(),bibALL.get_ID_ADRESS_MANDANT()}};        //new String[2][2];
		
		
		sel_werte  = bibARR.add_array_inFront(sel_werte, pre_values);
		
		MyE2_SelectField  sel4Select = new MyE2_SelectField(sel_werte, "",false);
		//mandant hervorheben
		HashMap<String, ListCellRendererInfo> hmFett = new HashMap<>();
		hmFett.put(rec_adresse_mandant.get__FullNameAndAdress_Typ1(), new ListCellRendererInfo()._f(new E2_FontBoldItalic()));
		sel4Select.setCellRenderer(new MyE2_SelectField.ownListCellRenderer2(hmFett));

		
		this.INIT(sel4Select, BEWEGUNG_STATION.id_adresse_besitzer.t(bStart?"STS":"STZ").s()+"=#WERT#", null);
		this.set_extOfSelectComponentDropDown(new Extent(200));
	}


	@Override
	public E2_BasicModuleContainer get_PopupContainer() throws myException {
		return new ownBasicContainer();
	}

	
	private class ownBasicContainer extends E2_BasicModuleContainer {}


	@Override
	public Component get_oComponentForSelection() throws myException {
		//return new E2_ListSelectorPosHelperTop("Besitzer "+(this.b_start?"Start":"Ziel"), this.get_oComponentWithoutText(), 110, 250);
		return this.get_oComponentWithoutText();

	}
	
}
