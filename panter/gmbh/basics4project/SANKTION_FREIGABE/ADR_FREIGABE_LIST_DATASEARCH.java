
package panter.gmbh.basics4project.SANKTION_FREIGABE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.SANKTION_PRUEFUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.SANKTION_FREIGABE.ADR_FREIGABE_CONST.TRANSLATOR;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class ADR_FREIGABE_LIST_DATASEARCH extends E2_DataSearch
{
	public ADR_FREIGABE_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
	{
		super(_TAB.sanktion_pruefung.n(),_TAB.sanktion_pruefung.keyFieldName(),TRANSLATOR.LIST.get_modul().get_callKey());

		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);

//		this.addSearchDef(SANKTION_PRUEFUNG.freigabe.fn(),"freigabe",true);
		this.addSearchDef(SANKTION_PRUEFUNG.freigabe_bemerkung.fn(),"Bemerkung",true);
		this.addSearchDef(SANKTION_PRUEFUNG.freigabe_datum.fn(),"Dat. Freigabe",true);
		this.addSearchDef(SANKTION_PRUEFUNG.freigabe_user.fn(),"Benutzer",true);
		this.addSearchDef(SANKTION_PRUEFUNG.hashwert_adresse.fn(),"Hashwert Adresse",true);
		this.addSearchDef(SANKTION_PRUEFUNG.hashwert_sanktion.fn(),"Hashwert Sanktion",true);
		this.addSearchDef(SANKTION_PRUEFUNG.id_adresse.fn(),"ID-Adresse",true);
		this.addSearchDef(SANKTION_PRUEFUNG.id_sanktion_pruefung.fn(),"ID Sperreintrag",true);

		this.add_SearchElement(this.genSuchQuery(ADRESSE.vorname,ADRESSE.name1,ADRESSE.name2,ADRESSE.name3),S.ms("Namen"));
		this.add_SearchElement(this.genSuchQuery(ADRESSE.plz),S.ms("PLZ"));
		this.add_SearchElement(this.genSuchQuery(ADRESSE.ort),S.ms("Ort"));
	}

	private String genSuchQuery(IF_Field... adressTextField) throws myException {

		String sql = new SEL(SANKTION_PRUEFUNG.id_sanktion_pruefung).FROM(_TAB.sanktion_pruefung).s()+" WHERE ";
		for (IF_Field f: adressTextField) {
			TermSimple t = new TermSimple("UPPER("+f.tnfn()+") LIKE UPPER('%#WERT#%')");
			sql=sql + SANKTION_PRUEFUNG.id_adresse.tnfn()+" IN ("+new SEL(ADRESSE.id_adresse).FROM(_TAB.adresse).WHERE(t).s()+" ) OR ";
		}

		sql = sql.substring(0, sql.length()-3);   //das letzte or wegschneiden    

		return sql;
	}


	private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber) {
			cSearch = "SELECT id_sanktion_pruefung  FROM "+bibE2.cTO()+"."+_TAB.sanktion_pruefung.n()+" WHERE TO_CHAR("+_TAB.sanktion_pruefung.n()+"."+cFieldName+")='#WERT#'";
		} else {
			cSearch = "SELECT id_sanktion_pruefung  FROM "+bibE2.cTO()+"."+_TAB.sanktion_pruefung.n()+" WHERE UPPER("+_TAB.sanktion_pruefung.n()+"."+cFieldName+") like upper('%#WERT#%')";
		}

		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}


}

