package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.CLEARING;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor_Neutralisator;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.Component_USER_DROPDOWN_NEW;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.SELECTOR_COMPONENT_FirmenAuswahl;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_SelectField_WithAutoToolTip;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class BSKC_ListSelector extends E2_ListSelectorContainer
{
	
	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	private E2_SelectionComponentsVector 	oSelVector = null;
	
//	private MyE2_SelectField				oSelectMitarbeiterADR = null;
//	private MyE2_SelectField				oSelectMitarbeiterKON = null;
	
	private Component_USER_DROPDOWN_NEW		oSelectMitarbeiterADR = null;
	private Component_USER_DROPDOWN_NEW		oSelectMitarbeiterKON = null;

	private MyE2_SelectField					oSelectSorte = null;
	private MyE2_SelectField_WithAutoToolTip	oSelectSorteExact = null;
	private MyE2_SelectField					oSelectLand = null;
	private MyE2_SelectField					oSelJahre = null;
	private MyE2_SelectField					oSelMonate = null;
	private MyE2_CheckBox						oCB_OFFEN = null;
	private MyE2_CheckBox						oCB_HasRelatedOFFEN_Gegenseite = null;
	
	private SELECTOR_COMPONENT_FirmenAuswahl   	oSelKundenMitKontrakten = null;


	
	
	public BSKC_ListSelector(E2_NavigationList oNavigationList, String cEK_VK, String cMODUL_KENNER) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		String cAndereSeite = "VK";
		
		if (cEK_VK.equals("VK"))
		{
			cAndereSeite = "EK";
		}
		
		this.oSelVector = 				new E2_SelectionComponentsVector(oNavigationList);

		this.oSelectMitarbeiterADR = 					new Component_USER_DROPDOWN_NEW(false,120);
		this.oSelectMitarbeiterKON = 					new Component_USER_DROPDOWN_NEW(false,120);
		

		String cBelegtyp = (cEK_VK.equals("EK")?myCONST.VORGANGSART_EK_KONTRAKT:myCONST.VORGANGSART_VK_KONTRAKT);
		
		String cQueryKundenSelektor =  "SELECT DISTINCT   NVL(JT_ADRESSE.NAME1,'-')||'-'||  NVL(JT_ADRESSE.NAME2,'-')||" +
										"'-'||  NVL(JT_ADRESSE.ORT,'-') ||' ('||TO_CHAR(  NVL(JT_ADRESSE.ID_ADRESSE,0))||')' AS NAMEN , JT_ADRESSE.ID_ADRESSE "+
										" FROM "+bibE2.cTO()+".JT_ADRESSE WHERE ID_ADRESSE IN "+
										" (SELECT DISTINCT ID_ADRESSE FROM "+bibE2.cTO()+".JT_VKOPF_KON WHERE NVL(DELETED,'N')='N' AND VORGANG_TYP="
										+ bibALL.MakeSql(cBelegtyp)+" ) "+
										" ORDER BY NAMEN";

		oSelKundenMitKontrakten = new SELECTOR_COMPONENT_FirmenAuswahl(cQueryKundenSelektor,50, 180, this.oSelVector, new MyE2_String("Firma"));
		this.oSelKundenMitKontrakten.REFRESH_KundenSelektor();

		
		
		this.oSelectSorte = new MyE2_SelectField("select DISTINCT SUBSTR(ANR1,1,2),SUBSTR(ANR1,1,2) FROM "+bibE2.cTO()+".JT_ARTIKEL ORDER BY SUBSTR(ANR1,1,2)",
				false,true,false,false);
				
		this.oSelectSorteExact = new MyE2_SelectField_WithAutoToolTip("select DISTINCT ANR1||' '||NVL(ARTBEZ1,'') AS A,ANR1 AS B FROM "+bibE2.cTO()+".JT_ARTIKEL WHERE ANR1 IS NOT NULL ORDER BY ANR1",
				false,true,false,false);
		
		this.oSelectSorte.setWidth(new Extent(50));
		this.oSelectSorteExact.setWidth(new Extent(100));

		
		this.oSelectLand = new MyE2_SelectField("select laendername,laendercode from "+bibE2.cTO()+".jd_land order by laendername",
				false,true,false,false, new Extent(50));

		this.oSelJahre = new MyE2_SelectField(bibALL.get_cSelJahre(),bibALL.get_cYearNow(),false);
		this.oSelMonate = new MyE2_SelectField(bibALL.get_cSelArrayMonate2Digits(),bibALL.get_cMonthNow(),false);
		
		this.oCB_OFFEN = new MyE2_CheckBox(new MyE2_String("Nur offene "+cEK_VK+" Pos"));
		this.oCB_OFFEN.setSelected(true);
		
		
		this.oCB_HasRelatedOFFEN_Gegenseite = new MyE2_CheckBox(new MyE2_String("Hat offene "+cAndereSeite+"-Zuordnungen"));
		
		oSelVector.add(new E2_ListSelectorStandard(oSelectMitarbeiterADR,"JT_ADRESSE.ID_USER = #WERT#", null, null));
		oSelVector.add(new E2_ListSelectorStandard(oSelectMitarbeiterKON,"JT_VKOPF_KON.ID_USER = #WERT#", null, null));
		oSelVector.add(new E2_ListSelectorStandard(oSelectSorte,"SUBSTR(JT_ARTIKEL.ANR1,1,2) = '#WERT#'", null, null));
		oSelVector.add(new E2_ListSelectorStandard(oSelectSorteExact,"JT_ARTIKEL.ANR1 = '#WERT#'", null, null));
		oSelVector.add(new E2_ListSelectorStandard(oSelectLand,"JT_VKOPF_KON.LAENDERCODE = '#WERT#'", null, null));
//		oSelVector.add(new E2_ListSelectorStandard(oSelJahre,	"TO_NUMBER(TO_CHAR(JT_VPOS_KON_TRAKT.GUELTIG_VON,'YYYY')) = #WERT#", null, null));
//		oSelVector.add(new E2_ListSelectorStandard(oSelMonate,	"TO_NUMBER(TO_CHAR(JT_VPOS_KON_TRAKT.GUELTIG_VON,'MM')) = #WERT#", null, null));
		oSelVector.add(new selectMonthIsTouched(oSelMonate,oSelJahre));
		oSelVector.add(new E2_ListSelectorStandard(this.oCB_OFFEN,"  NVL(JT_VPOS_KON_TRAKT.ABGESCHLOSSEN,'N') = 'N'",""));
		oSelVector.add(new E2_ListSelectorStandard(this.oCB_HasRelatedOFFEN_Gegenseite,
				"JT_VPOS_KON.ID_VPOS_KON IN " +
				" (SELECT DISTINCT(ID_VPOS_KON_"+ cEK_VK+ " ) FROM "+bibE2.cTO()+".JT_EK_VK_BEZUG WHERE ID_VPOS_KON_"+ cAndereSeite+ " IN " +
									" (SELECT JT_VPOS_KON.ID_VPOS_KON FROM "+bibE2.cTO()+".JT_VPOS_KON, "+bibE2.cTO()+".JT_VPOS_KON_TRAKT  WHERE  " +
									" JT_VPOS_KON.ID_VPOS_KON=JT_VPOS_KON_TRAKT.ID_VPOS_KON  AND   NVL(ABGESCHLOSSEN,'N')='N' )" +
									")",
				""));
		
		oSelVector.add(new E2_ListSelectorStandard(this.oSelKundenMitKontrakten.get_oSelKunden(),
						"JT_VPOS_KON.ID_VKOPF_KON IN  (SELECT ID_VKOPF_KON FROM "+bibE2.cTO()+".JT_VKOPF_KON WHERE NVL(JT_VKOPF_KON.ID_ADRESSE,0)=#WERT#)", null, null));

		
		MyE2_Grid oGrid = new MyE2_Grid(8,0);
		
		Insets oIN = new Insets(0,2,2,2);
		Insets oIN2 = new Insets(0,2,15,2);
		
		//2013-02-28: neuer db-gestuetzter listselektor
		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(cMODUL_KENNER);
		oSelVector.add(oDB_BasedSelektor);
		
		
		// zeile 1
		oGrid.add(new MyE2_Label(new MyE2_String("Bearbeiter (Adr):")),1,oIN);		
		oGrid.add(oSelectMitarbeiterADR,1,oIN2);		
		oGrid.add(new MyE2_Label(new MyE2_String("Aktiv in:")),1,oIN);			
		oGrid.add(oSelMonate,1,oIN2);
		oGrid.add(oSelJahre,1,oIN2);	
		oGrid.add(new E2_ComponentGroupHorizontal(0, new MyE2_Label(new MyE2_String("Land:")),oSelectLand,E2_INSETS.I_0_0_2_0),1,oIN2);
		oGrid.add(oSelKundenMitKontrakten,1,oIN2);
		oGrid.add(oDB_BasedSelektor.get_oComponentForSelection(new MyE2_String("Verschiedene: "), 100),1,oIN);

		
		// zeile 2
		oGrid.add(new MyE2_Label(new MyE2_String("Bearbeiter (Kon):")),1,oIN);		
		oGrid.add(oSelectMitarbeiterKON,1,oIN2);			
		oGrid.add(new MyE2_Label(new MyE2_String("Sorte:")),1,oIN);					
		oGrid.add(oSelectSorte,1,oIN2);
		oGrid.add(oSelectSorteExact,1,oIN2);
		oGrid.add(this.oCB_OFFEN,2,oIN2);				
		oGrid.add(this.oCB_HasRelatedOFFEN_Gegenseite,1,oIN);

		
		this.add(oGrid);
	}

	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}
	
	
	
	private class selectMonthIsTouched extends XX_ListSelektor {

		
		private MyE2_SelectField oSelMonate = null;
		private MyE2_SelectField oSelJahr = null;
		
		
		
		/**
		 * @param oSelMonate
		 * @param oSelJahr
		 */
		public selectMonthIsTouched(MyE2_SelectField oSelMonate, MyE2_SelectField oSelJahr) {
			super();
			this.oSelMonate = oSelMonate;
			this.oSelJahr = oSelJahr;
			
			this.set_oNeutralisator(new XX_ListSelektor_Neutralisator() {
				
				@Override
				public void set_to_Neutral() throws myException {
					selectMonthIsTouched.this.oSelMonate.setSelectedIndex(0);
					selectMonthIsTouched.this.oSelJahr.setSelectedIndex(0);
				}
			});
		}

		
		/* (non-Javadoc)
		 * @see panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor#get_WhereBlock()
		 */
		@Override
		public String get_WhereBlock() throws myException {
			
			String where = "";
			
			String selectedMonth = this.oSelMonate.get_ActualWert();
			String selectedYear = this.oSelJahr.get_ActualWert();
			
			
			
			if (S.isAllFull(selectedMonth,selectedYear)) {
				String jahrMonat = selectedYear+"-"+selectedMonth;
				where = "TO_CHAR(JT_VPOS_KON_TRAKT.GUELTIG_VON,'YYYY-MM')<='"+ jahrMonat+"' AND "+ "TO_CHAR(JT_VPOS_KON_TRAKT.GUELTIG_BIS,'YYYY-MM')>='"+jahrMonat+"'";
			} else {
				if (S.isFull(selectedMonth)) {
					where = "TO_CHAR(JT_VPOS_KON_TRAKT.GUELTIG_VON,'MM')<='"+ selectedMonth +"' AND "+ "TO_CHAR(JT_VPOS_KON_TRAKT.GUELTIG_BIS,'YYYY-MM')>='"+selectedMonth+"'";
				} if (S.isFull(selectedYear)) {
					where = "TO_CHAR(JT_VPOS_KON_TRAKT.GUELTIG_VON,'YYYY')<='"+ selectedYear +"' AND "+ "TO_CHAR(JT_VPOS_KON_TRAKT.GUELTIG_BIS,'YYYY-MM')>='"+selectedYear+"'";
				} 
			}
			
			
			return where;
		}

		/* (non-Javadoc)
		 * @see panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor#get_oComponentForSelection()
		 */
		@Override
		public Component get_oComponentForSelection() throws myException {
			return null;
		}

		/* (non-Javadoc)
		 * @see panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor#get_oComponentWithoutText()
		 */
		@Override
		public Component get_oComponentWithoutText() throws myException {
			return null;
		}

		/* (non-Javadoc)
		 * @see panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor#add_ActionAgentToComponent(panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent)
		 */
		@Override
		public void add_ActionAgentToComponent(XX_ActionAgent oAgent) {
			this.oSelMonate.add_oActionAgent(oAgent);
			this.oSelJahr.add_oActionAgent(oAgent);
		}

		/* (non-Javadoc)
		 * @see panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor#doInternalCheck()
		 */
		@Override
		public void doInternalCheck() {
		}
		
	}
	
	
	
}
