
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_Factory4Records;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.LAND_SelektorQuery;
import panter.gmbh.Echo2.components.E2_GridLabel;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.basics4project.DB_ENUMS.BORDERCROSSING;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BORDERCROSSING;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_LIST_FORMATING_Agent;


public class BOR_LIST_ComponentMap extends E2_ComponentMAP {

	public BOR_LIST_ComponentMap() throws myException {
		super(new BOR_LIST_SqlFieldMAP());

		SQLFieldMAP oFM = this.get_oSQLFieldMAP();

		this.add_Component(BOR_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST, new E2_CheckBoxForList(), new MyE2_String("?"));
		this.add_Component(BOR_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST, new E2_ButtonListMarker(), new MyE2_String("?"));
		
		// hier kommen die Felder
		this.add_Component(new MyE2_DB_SelectField(oFM.get_(BORDERCROSSING.id_land_quelle), new LAND_SelektorQuery().get_dropDownList4DataComponent(true), false), new MyE2_String("Land Quelle"));
		this.add_Component(new MyE2_DB_SelectField(oFM.get_(BORDERCROSSING.id_land_ziel), new LAND_SelektorQuery().get_dropDownList4DataComponent(true), false), new MyE2_String("Land Ziel"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(BORDERCROSSING.erinnerung_bei_anlage)), new MyE2_String("Sofort ?"));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BORDERCROSSING.intervall_tage), true), new MyE2_String("Interv.Tage"));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BORDERCROSSING.offset_before_start), true), new MyE2_String("Tage vorher"));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BORDERCROSSING.title), true), new MyE2_String("Titel"));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BORDERCROSSING.message), true), new MyE2_String("Meldung"));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BORDERCROSSING.id_bordercrossing), true), new MyE2_String("ID"));
		
		// Spalte für die Artikel
		this.add_Component(BOR_LIST_BasicModuleContainer.NAME_OF_ARTIKEL_COL_IN_LIST, new MyE2_Column(),new MyE2_String("Artikel"));
		
		this.get__Comp(BORDERCROSSING.title).EXT().set_oColExtent(new Extent(200));
		this.get__Comp(BORDERCROSSING.message).EXT().set_oColExtent(new Extent(400));
		

		 this.set_oSubQueryAgent(new BOR_LIST_FORMATING_Agent());
//		this.set_Factory4Records(new factory4Records());
	}

	private class factory4Records extends E2_ComponentMAP_Factory4Records {

		@Override
		public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {

			return new RECORD_BORDERCROSSING(cID_MAINTABLE);
		}

	}

}
