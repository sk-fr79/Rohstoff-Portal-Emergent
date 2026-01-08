package rohstoff.Echo2BusinessLogic.INTRASTAT;


import nextapp.echo2.app.Border;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_SelectFieldWithParameters;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class INSTAT_LIST_Selector extends E2_ListSelectorContainer
{
	
	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	
	private E2_SelectionComponentsVector 	oSelVector = null;
	private MyE2_SelectField	oSelectMonat = null;
	private MyE2_SelectField	oSelectJahr = null;
	private MyE2_SelectField	oSelectTyp = null;
	
	private MyE2_CheckBox		ocbShowNichtMelden = null;
	
	private MyE2_SelectFieldWithParameters	oSelectZaehler = null;
	private XX_ActionAgent m_oAgentZaehler = null;
	
	
	
	public INSTAT_LIST_Selector(E2_NavigationList oNavigationList, String cMODULE_KENNER) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);
		
		
		
		String [][] sArrMonths = bibALL.get_cSelArrayMonate();
		String [][] sArrJahre = bibALL.get_cSelJahre();
		String [][] sArrTyp = { {"*",""},       				
				{"Einfuhr","1"},
				{"Ausfuhr","2"},
				};
		
		m_oAgentZaehler = new actionAgentZeitraum();
		
		oSelectMonat = new MyE2_SelectField(sArrMonths,bibALL.get_cMonthNow(),false);
		oSelectMonat.add_oActionAgent(m_oAgentZaehler);
		
		oSelVector.add(new E2_ListSelectorStandard(oSelectMonat,"JT_INTRASTAT_MELDUNG.ANMELDEMONAT=SUBSTR('00'||'#WERT#',-2,2)",new MyString("Monat"),5));
		
		oSelectJahr = new MyE2_SelectField(sArrJahre,bibALL.get_cYearNow(),false);
		oSelectJahr.add_oActionAgent(m_oAgentZaehler);
		oSelVector.add(new E2_ListSelectorStandard(oSelectJahr,"JT_INTRASTAT_MELDUNG.ANMELDEJAHR='#WERT#'",new MyString("Jahr"),5));
		
		
		oSelectTyp = new MyE2_SelectField(sArrTyp,"*",false);
		oSelVector.add(new E2_ListSelectorStandard(oSelectTyp,"JT_INTRASTAT_MELDUNG.MELDEART='#WERT#'",new MyString("Meldeart"),5));
		
		oSelectZaehler = new MyE2_SelectFieldWithParameters(
				        "SELECT 'Noch nicht gemeldete', 0 from dual UNION " +
				        "SELECT DISTINCT to_char(ZAEHLER_MELDUNG), ZAEHLER_MELDUNG  from "
						+ bibE2.cTO()
						+ ".JT_INTRASTAT_MELDUNG "
						+ " WHERE ID_MANDANT = " 	+ bibALL.get_ID_MANDANT() 
						+ " AND JT_INTRASTAT_MELDUNG.ANMELDEMONAT = #MONAT# " 
						+ " AND JT_INTRASTAT_MELDUNG.ANMELDEJAHR = #JAHR# " 
						+ " ORDER BY 1", 
						false, true,false, false);

		oSelectZaehler.AddParameter("#MONAT#");
		oSelectZaehler.AddParameter("#JAHR#");
		
		m_oAgentZaehler.executeAgentCode(null);
//		oSelectZaehler.RefreshComboboxFromSQL();

		oSelVector.add(new E2_ListSelectorStandard(oSelectZaehler,	"nvl(" + bibE2.cTO()+ ".JT_INTRASTAT_MELDUNG.ZAEHLER_MELDUNG,0) = nvl(#WERT#,0)", null, null));

		ocbShowNichtMelden = new MyE2_CheckBox(new MyString("Nicht zu meldende Einträge anzeigen"), new MyE2_String("Zeigt die Einträge an, die nicht gemeldet werden sollen, z.B. weil der Artikel in der Ausschlussliste eingetragen war.")) ;
		oSelVector.add(new E2_ListSelectorStandard(ocbShowNichtMelden, "nvl(" + bibE2.cTO()+ ".JT_INTRASTAT_MELDUNG.NICHT_MELDEN,'N') = 'Y'", "nvl(" + bibE2.cTO()+ ".JT_INTRASTAT_MELDUNG.NICHT_MELDEN,'N') = 'N'"));
		
		
		
		MyE2_Grid oGridInnen = new MyE2_Grid(11,0);
		this.add(oGridInnen, E2_INSETS.I_4_4_4_4);
		
		oGridInnen.add(new MyE2_Label("Monat: "), new Insets(4,2,2,2));
		oGridInnen.add(oSelectMonat, new Insets(4,2,15,2));
		
		oGridInnen.add(new MyE2_Label("Jahr: "), new Insets(4,2,2,2));
		oGridInnen.add(oSelectJahr, new Insets(4,2,15,2));
		
		oGridInnen.add(new MyE2_Label("Meldeart: "), new Insets(4,2,2,2));
		oGridInnen.add(oSelectTyp, new Insets(4,2,15,2));
		
		oGridInnen.add(new MyE2_Label("Meldungszähler: "), new Insets(4,2,2,2));
		oGridInnen.add(oSelectZaehler, new Insets(4,2,15,2));
		
		oGridInnen.add(new MyE2_Label(" "), new Insets(4,2,2,2));
		oGridInnen.add(ocbShowNichtMelden, new Insets(4,2,15,2));
		
		
		//2013-03-05: neuer db-gestuetzter listselektor
		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(cMODULE_KENNER);
		this.oSelVector.add(oDB_BasedSelektor);
		oGridInnen.add(oDB_BasedSelektor.get_oComponentForSelection(new MyE2_String("Diverse: "),100), new Insets(4,2,10,2));

		
	}

	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}
	
	/**
	 * @return the oSelArtikel
	 */
	public MyE2_SelectField get_oSelMonat()
	{
		return oSelectMonat;
	}

	public MyE2_SelectField get_oSelJahr()
	{
		return oSelectJahr;
	} 

	public MyE2_SelectFieldWithParameters get_oSelZaehler()
	{
		return oSelectZaehler;
	}

	/**
	 * aktualisiert die Zähler-Eintragungen 
	 * @throws myException
	 */
	public void refresh_CMBZaehler () throws myException{
		m_oAgentZaehler.executeAgentCode(null);
	}
	

	private class actionAgentZeitraum extends XX_ActionAgent{
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			INSTAT_LIST_Selector oThis = INSTAT_LIST_Selector.this;
			
			String sJahr = oThis.oSelectJahr.get_ActualWert();
			String sMonat = oThis.oSelectMonat.get_ActualWert();
			if (bibALL.isEmpty(sMonat)){
				sMonat = "0";
			}
			if (bibALL.isEmpty(sJahr)){
				sJahr = "0";
			}
			
			
			String sZaehler = "";
			try {
				sZaehler = oThis.oSelectZaehler.get_ActualWert();
			} catch (Exception e) {
				
			}
			
			oThis.oSelectZaehler.SetParameter("#MONAT#", sMonat);
			oThis.oSelectZaehler.SetParameter("#JAHR#", sJahr);
			
			oThis.oSelectZaehler.RefreshComboboxFromSQL();
			oThis.oSelectZaehler.set_ActiveValue_OR_FirstValue(sZaehler);
		}
		
	}
	
	
	
}
