package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.REPORT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_REPORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORT;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;


/**
 * block mit allen auswertungen, wird nur noch zum verteilen gebraucht
 * @author martin
 *
 */
public class AW2_VectorAuswertungen extends Vector<AW2_Auswertung> {
	
	private boolean is_base_vector = false;
	
	public AW2_VectorAuswertungen() throws myException {
		super();
		
		this.is_base_vector = true;
		
		Vector<String> vJasperName = new Vector<String>();     //sorgt dafuer, dass jeder "zusammengesuchte" report nur einmal vorkommt
		
		SEL  sel = new SEL(_TAB.report).FROM(_TAB.report).WHERE(new vgl(REPORT.aktiv,"Y")).ORDERUP(REPORT.module_kenner).ORDERUP(REPORT.buttontext);
		
		RECLIST_REPORT  oReclistReport = new RECLIST_REPORT(sel.s());

		//zuerst die eigenen anzeigen (zum modul gehoerende), dann der rest
		Vector<AW2_Auswertung> v_meine = new Vector<AW2_Auswertung>();
		Vector<AW2_Auswertung> v_rest = new Vector<AW2_Auswertung>();
		
		for (int i=0;i<oReclistReport.get_vKeyValues().size();i++) {
			RECORD_REPORT  recRep = oReclistReport.get(i);
			if (recRep.get_MODULE_KENNER_cUF_NN("").equals(E2_MODULNAME_ENUM.MODUL.NAME_MODUL_ROHSTOFFAUSWERTUNGEN.get_callKey())) 	{
				v_meine.add(new AW2_Auswertung(recRep,new MyE2_String(""),false,true));
			} else if (recRep.is_PASS_NO_ID_YES())	{
				if (!vJasperName.contains(recRep.get_NAME_OF_REPORTFILE_cUF_NN(""))) {
					if (bibE2.get_bIsModuleAllowed4ThisUser(recRep.get_MODULE_KENNER_cUF_NN(""))) {
						String zusatztext = "extern";
					    MyE2_String tabText=	E2_MODULNAME_ENUM.find_Corresponding_TabText(recRep.get_MODULE_KENNER_cUF_NN(""),null);
						if (tabText!=null) {
							zusatztext = tabText.CTrans();
						}
						vJasperName.add(recRep.get_NAME_OF_REPORTFILE_cUF_NN(""));
						v_rest.add(new AW2_Auswertung(recRep,new MyE2_String(zusatztext,false),true,true).set_gruppe(zusatztext));
					}
				}
			}
		}
		this.addAll(v_meine);
		this.addAll(v_rest);
	}
	
	
	public AW2_VectorAuswertungen(Vector<AW2_RECORD_REPORT> rr) throws myException {
		super();
		
		this.is_base_vector = false;

		//zuerst die eigenen anzeigen (zum modul gehoerende), dann der rest
		Vector<AW2_Auswertung> v_meine = new Vector<AW2_Auswertung>();
		
		Vector<String>  v_dubletten_check = new Vector<String>();
				
		for (AW2_RECORD_REPORT  recRep: rr) {
			if (v_dubletten_check.contains(recRep.get_ID_REPORT_cUF())) {
				continue;
			}
			v_meine.add(new AW2_Auswertung(recRep,new MyE2_String(""),false,false));
			v_dubletten_check.add(recRep.get_ID_REPORT_cUF());
		}
		this.addAll(v_meine);
	}

	public boolean is_baseVector() {
		return is_base_vector;
	}

}
