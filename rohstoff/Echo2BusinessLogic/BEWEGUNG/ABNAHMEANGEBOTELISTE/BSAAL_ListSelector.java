package rohstoff.Echo2BusinessLogic.BEWEGUNG.ABNAHMEANGEBOTELISTE;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Extent;
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
import panter.gmbh.Echo2.__BASIC_COMPONENTS.Component_USER_DROPDOWN_NEW;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.SELECTOR_COMPONENT_FirmenAuswahl;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.exceptions.myException;

public class BSAAL_ListSelector extends E2_ListSelectorContainer
{
	
	private String SQL_SELECT_BLOCK_RANGES = "SUBSTR(  NVL(TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_VON,'DD.MM.YYYY'),'-------'),1,6)||' - '||SUBSTR(  NVL(TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_BIS,'DD.MM.YYYY'),'------'),1,6)";
	
	
	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	private E2_SelectionComponentsVector 	oSelVector = null;
	
//	private MyE2_SelectField				oSelectMitarbeiterADR = null;
//	private MyE2_SelectField				oSelectMitarbeiterANG = null;

	private Component_USER_DROPDOWN_NEW  	oSelectHaendler = 	new Component_USER_DROPDOWN_NEW(false,220);
	private Component_USER_DROPDOWN_NEW  	oSelectErfasser = 	new Component_USER_DROPDOWN_NEW(false,220);

	private SELECTOR_COMPONENT_FirmenAuswahl   oSelKundenMitAngebotsSorten = null;


	
//	private MyE2_SelectField				oSelectAnfangsBuchstaben = null;
	private MyE2_SelectField				oSelectLand = null;
	private MyE2_SelectField				oSelJahre = null;
	private MyE2_SelectField				oSelMonate = null;
	private MyE2_SelectField				oSelectAdressKlasse = null;
	private MyE2_SelectField				oSelectANR1 = null;

	private MyE2_CheckBox					oCB_LeerePreise = null;
	private MyE2_CheckBox  					oCB_ShowDeletedRows = null;
	
	private selectFieldDateRange			oSelectDateRange = new selectFieldDateRange();
	
	
	
	public BSAAL_ListSelector(E2_NavigationList oNavigationList, String cMODULE_KENNER) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		this.oSelVector = 				new E2_SelectionComponentsVector(oNavigationList);

		
		//2011-01-08: neuer kundenselektor fuer die auswahl
		String cQueryKundenSelektor =  "SELECT DISTINCT   NVL(JT_ADRESSE.NAME1,'-')||'-'||  NVL(JT_ADRESSE.NAME2,'-')||" +
											"'-'||  NVL(JT_ADRESSE.ORT,'-') ||' ('||TO_CHAR(  NVL(JT_ADRESSE.ID_ADRESSE,0))||')' AS NAMEN , JT_ADRESSE.ID_ADRESSE "+
											" FROM "+bibE2.cTO()+".JT_ADRESSE WHERE NVL(JT_ADRESSE.AKTIV,'N')='Y' AND ID_ADRESSE IN "+
											" (SELECT ID_ADRESSE FROM "+bibE2.cTO()+".JT_ARTIKELBEZ_LIEF WHERE NVL(ANGEBOT,'N')='Y') "+
											" ORDER BY NAMEN";
		
		this.oSelKundenMitAngebotsSorten = new SELECTOR_COMPONENT_FirmenAuswahl(cQueryKundenSelektor, 60, 180, this.oSelVector, new MyE2_String("Lieferant: "));
		this.oSelKundenMitAngebotsSorten.REFRESH_KundenSelektor();

				
       	String cQuerySorten = "SELECT DISTINCT JT_ARTIKEL.ANR1 A,JT_ARTIKEL.ANR1 B " +
       					" FROM "+bibE2.cTO()+".JT_ARTIKEL,"+bibE2.cTO()+".JT_ARTIKELBEZ_LIEF,"+bibE2.cTO()+".JT_ARTIKEL_BEZ " +
       					" WHERE  JT_ARTIKEL.ID_ARTIKEL = JT_ARTIKEL_BEZ.ID_ARTIKEL " +
       					" AND  JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ = JT_ARTIKELBEZ_LIEF.ID_ARTIKEL_BEZ " +
       					" AND  JT_ARTIKELBEZ_LIEF.ANGEBOT='Y' ORDER BY ANR1";
       					
		
		this.oSelectANR1 = new MyE2_SelectField(cQuerySorten,false,true,false,false, new Extent(120));
		
		
		this.oSelectLand = new MyE2_SelectField("select laendername,laendercode from "+bibE2.cTO()+".jd_land order by laendername",false,true,false,false, new Extent(120));

		this.oSelJahre = new MyE2_SelectField(bibALL.get_cSelJahre(),bibALL.get_cYearNow(),false, new Extent(55));
		this.oSelMonate = new MyE2_SelectField(bibALL.get_cSelArrayMonate(),bibALL.get_cMonthNow(),false, new Extent(55));
		this.oSelectDateRange = new selectFieldDateRange();
		this.oSelectDateRange.setWidth(new Extent(106));
		
		
		this.oSelectAdressKlasse = new MyE2_SelectField("select kurzbezeichnung,id_adressklasse_def  from "+bibE2.cTO()+".jt_adressklasse_def",false,true,false,false, new Extent(120));
		
		this.oCB_LeerePreise = new MyE2_CheckBox(new MyE2_String("Nur Leere Preise"));
		this.oCB_ShowDeletedRows = new MyE2_CheckBox(new MyE2_String("Zeige gelöschte Sätze"));
		
		this.oCB_LeerePreise.setWidth(new Extent(256));
		this.oCB_ShowDeletedRows.setWidth(new Extent(256));
		
		// sonderselektor der aktualisiert wird
		
		/*
		 * jetzt wird dem monat und jahr selector ein actionagent verpasst, den den date-range-selector aktualisiert
		 * dieser wird vor der selektionsaktion aufgerufen
		 */
		this.oSelJahre.add_oActionAgent(new ownActionAgentForRefresh());
		this.oSelMonate.add_oActionAgent(new ownActionAgentForRefresh());
		
		oSelVector.add(new E2_ListSelectorStandard(oSelectHaendler,"(NVL(JT_ADRESSE.ID_USER,-1) = #WERT#  OR NVL(JT_ADRESSE.ID_USER_ERSATZ,-1) = #WERT#)", null, null));
		oSelVector.add(new E2_ListSelectorStandard(oSelectErfasser,"JT_VKOPF_STD.ID_USER = #WERT#", null, null));
		oSelVector.add(new E2_ListSelectorStandard(oSelectLand,"JT_VKOPF_STD.LAENDERCODE = '#WERT#'", null, null));
		oSelVector.add(new E2_ListSelectorStandard(oSelJahre,	"TO_NUMBER(TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_VON,'YYYY')) = #WERT#", null, null));
		oSelVector.add(new E2_ListSelectorStandard(oSelMonate,	"TO_NUMBER(TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_VON,'MM')) = #WERT#", null, null));
		oSelVector.add(new E2_ListSelectorStandard(oSelectAdressKlasse,"JT_VKOPF_STD.ID_ADRESSE in (select JT_ADRESSKLASSE.ID_ADRESSE from JT_ADRESSKLASSE where ID_ADRESSKLASSE_DEF=#WERT#)", null, null));


		oSelVector.add(new E2_ListSelectorStandard(this.oSelKundenMitAngebotsSorten.get_oSelKunden(),"JT_VKOPF_STD.ID_ADRESSE=#WERT# ", null, null));
		
		oSelVector.add(new E2_ListSelectorStandard(oSelectANR1,"JT_VPOS_STD.ANR1='#WERT#'", null, null));

		oSelVector.add(new E2_ListSelectorStandard(this.oSelectDateRange,this.SQL_SELECT_BLOCK_RANGES+"='#WERT#'", null, null));
		oSelVector.add(new E2_ListSelectorStandard(this.oCB_LeerePreise,"JT_VKOPF_STD.ID_VKOPF_STD IN (SELECT DISTINCT ID_VKOPF_STD FROM "+bibE2.cTO()+".JT_VPOS_STD WHERE  EINZELPREIS IS NULL AND NVL(JT_VPOS_STD.DELETED,'N')='N')",""));

		oSelVector.add(new E2_ListSelectorStandard(this.oCB_ShowDeletedRows,"","  NVL(JT_VPOS_STD.DELETED,'N')='N'"));
		
		MyE2_Grid oGrid = new MyE2_Grid(6,0);
		
		Insets oIN = new Insets(0,2,2,2);
		Insets oIN2 = new Insets(0,2,20,2);

		//2013-02-28: neuer db-gestuetzter listselektor
		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(cMODULE_KENNER);
		oSelVector.add(oDB_BasedSelektor);

		
		oGrid.add(new MyE2_Label(new MyE2_String("Betreuer Adresse (1 oder 2): ")),1,oIN);		
		oGrid.add(oSelectHaendler,1,oIN2);		
		oGrid.add(new MyE2_Label(new MyE2_String("Land:")),1,oIN);					
		oGrid.add(oSelectLand,1,oIN2);				
		oGrid.add(oSelKundenMitAngebotsSorten,1,oIN2);	
		oGrid.add(oDB_BasedSelektor.get_oComponentForSelection(new MyE2_String("Weitere:"), 100), new Insets(4,2,10,2));
		
		
		oGrid.add(new MyE2_Label(new MyE2_String("Erfasser (Angeb):")),1,oIN);		
		oGrid.add(oSelectErfasser,1,oIN2);		
		oGrid.add(new MyE2_Label(new MyE2_String("Sorte:")),1,oIN); 
		oGrid.add(oSelectANR1,1,oIN2);
		oGrid.add(this.oCB_LeerePreise,1,oIN);  
		oGrid.add(new MyE2_Label(""));
		
		
		oGrid.add(new MyE2_Label(new MyE2_String("Gültig:")),1,oIN);				
		oGrid.add(new E2_ComponentGroupHorizontal(0,oSelMonate,oSelJahre,oSelectDateRange,E2_INSETS.I_0_0_2_0),1,oIN2);		
		oGrid.add(new MyE2_Label(new MyE2_String("Adr.Klasse:")),1,oIN);			
		oGrid.add(oSelectAdressKlasse,1,oIN2); 		
		oGrid.add(this.oCB_ShowDeletedRows,1,oIN);
		oGrid.add(new MyE2_Label(""));
		

		
		this.add(oGrid);
	}

	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}

	public MyE2_SelectField get_oSelectAdressKlasse() 
	{
		return oSelectAdressKlasse;
	}

	public MyE2_SelectField get_oSelectLand() 
	{
		return oSelectLand;
	}

	public MyE2_SelectField get_oSelectMitarbeiterADR() 
	{
		return oSelectHaendler;
	}

	public MyE2_SelectField get_oSelectMitarbeiterANG() 
	{
		return oSelectErfasser;
	}

	public MyE2_SelectField get_oSelJahre() 
	{
		return oSelJahre;
	}

	public MyE2_SelectField get_oSelMonate() 
	{
		return oSelMonate;
	}

	
	
	
	public void  do_RefreshSelectDateRange() 
	{
		this.oSelectDateRange.refreshSelection();
	}
	
	
	
	
	/*
	 * actionagent fuer die 2 selektionsfelder monat/jahr. 
	 * wird als erstes ausgefuehrt, bevor die selektion erfolgt
	 */
	private class ownActionAgentForRefresh extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) 
		{
			BSAAL_ListSelector.this.do_RefreshSelectDateRange();
		}
	}
	
	
	
	/*
	 * spezielles selektionsdropdown, das alle vorkommenden bereich ausselektiert, die in der auswahl der angaben monat/jahr noch vorhanden ist 
	 */
	private class selectFieldDateRange extends MyE2_SelectField
	{
		

		public selectFieldDateRange() throws myException 
		{
			String[][] cEmpty = {{"*",""}};
			this.set_ListenInhalt(cEmpty,false);
			this.setSelectedIndex(0);
		}
		
		public void refreshSelection() 
		{
			try
			{
				BSAAL_ListSelector oThis = BSAAL_ListSelector.this;

				String[][] cEmpty = {{"*",""}};

				// auswahl nur moeglich, wenn monat und jahr selektiert sind
				if (oThis.oSelMonate.get_ActualWert().equals("") || oThis.oSelJahre.get_ActualWert().equals(""))
				{
					this.set_ListenInhalt(cEmpty,false);
					this.setSelectedIndex(0);
					return;
				}
				
				
				String cSQL_Query = "SELECT DISTINCT "+oThis.SQL_SELECT_BLOCK_RANGES+", " +oThis.SQL_SELECT_BLOCK_RANGES+
									" FROM "+bibE2.cTO()+".JT_VPOS_STD_ANGEBOT" +
											" WHERE " +
											" TO_NUMBER(TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_VON,'MM'))="+oThis.oSelMonate.get_ActualWert() +" AND "+
											"   NVL(DELETED,'N')='N' AND "+
											" TO_NUMBER(TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_VON,'YYYY'))="+oThis.oSelJahre.get_ActualWert()+" ORDER BY "+oThis.SQL_SELECT_BLOCK_RANGES;
				
				MyDBToolBox oDB = bibALL.get_myDBToolBox();
				String[][] cHelp = oDB.EinzelAbfrageInArray(cSQL_Query);
				bibALL.destroy_myDBToolBox(oDB);
				
				if (cHelp==null || cHelp.length==0)
				{
					this.set_ListenInhalt(cEmpty,false);
					this.setSelectedIndex(0);
					return;
				}

				String[][] cHelp2 = new String[cHelp.length+1][2];
				cHelp2[0][0]="*";
				cHelp2[0][1]="";
				for (int i=0;i<cHelp.length;i++)
				{
					cHelp2[i+1][0]=cHelp[i][0];
					cHelp2[i+1][1]=cHelp[i][1];
				}
				
				this.set_ListenInhalt(cHelp2,false);
				this.setSelectedIndex(0);
				
			}
			catch (myException ex)
			{
				this.setVisible(false);   // element unsichtbar
			}
			
		}
		
	}

	public MyE2_CheckBox get_oCB_ShowDeletedRows()
	{
		return oCB_ShowDeletedRows;
	}

	
}
