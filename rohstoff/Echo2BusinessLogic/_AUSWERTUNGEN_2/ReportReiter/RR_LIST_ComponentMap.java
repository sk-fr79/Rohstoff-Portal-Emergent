
package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.ReportReiter;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_Factory4Records;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.DB_ENUMS.REPORT_REITER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORT_REITER;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
public class RR_LIST_ComponentMap extends E2_ComponentMAP 
{
	public RR_LIST_ComponentMap() throws myException
	{
		super(new RR_LIST_SqlFieldMAP());

		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();

		this.add_Component(RR_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,    new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(RR_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,    new E2_ButtonListMarker(),new MyE2_String("?"));
		//hier kommen die Felder  
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REPORT_REITER.id_report_reiter),true),     new MyE2_String("Id"));

		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REPORT_REITER.reitername),true),     new MyE2_String("Reitername"));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REPORT_REITER.reihenfolge),true),     new MyE2_String("Reihenfolge"));
		
		this.set_Factory4Records(new factory4Records());
		
		this.get__Comp(REPORT_REITER.id_report_reiter.fieldName()).EXT().set_bIsVisibleInList(false);
		this.get__Comp(REPORT_REITER.reitername.fieldName()).EXT().set_oColExtent(new Extent(150));
	}
	private class factory4Records extends E2_ComponentMAP_Factory4Records {
		@Override
		public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
			return new RECORD_REPORT_REITER(cID_MAINTABLE);
		}

	}


}

