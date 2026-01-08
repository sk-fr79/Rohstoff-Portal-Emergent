package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import java.util.GregorianCalendar;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.Component_USER_DROPDOWN_NEW;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;



public class BST_K_LIST_Selector extends E2_ListSelectorContainer     //2011-12-12: selector wird abgeleitet von  E2_ListSelectorContainer statt von expandeable row
{

	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	private E2_SelectionComponentsVector 	oSelVector = null;
	
	private Component_USER_DROPDOWN_NEW		oSelectMitarbeiterADR = null;
	private Component_USER_DROPDOWN_NEW		oSelectMitarbeiterANG = null;
	private MyE2_SelectField				oSelectLand = null;
	private MyE2_SelectField				oSelJahre = null;
	private MyE2_SelectField				oSelMonate = null;
	private MyE2_SelectField				oSelectAdressKlasse = null;

	private MyE2_CheckBox					oCheckBoxAbgeschlossen = new MyE2_CheckBox(new MyE2_String("Beleg geschlossen"));
	private MyE2_CheckBox					oCheckBoxOffen = new MyE2_CheckBox(new MyE2_String("Beleg offen"));
	private MyE2_CheckBox  					oCB_ShowDeletedRows = new MyE2_CheckBox(new MyE2_String("Zeige gelöschte Sätze"));

	

	public BST_K_LIST_Selector(E2_NavigationList oNavigationList, String cMODULE_KENNER) throws myException
	{
		super(new MyE2_String("Selektionsbereich ausgeblendet ..."), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		this.oSelVector = 				new E2_SelectionComponentsVector(oNavigationList);

//		this.oSelectMitarbeiterADR = new MyE2_SelectField(
//				"select   NVL(NAME,'-')||' ('|| NVL(KUERZEL,'-')||')',id_user from "+bibE2.cTO()+".jd_user where id_mandant="+bibALL.get_ID_MANDANT()+" order by name",
//				false,true,false,false);
//
//		this.oSelectMitarbeiterANG = new MyE2_SelectField(
//				"select   NVL(NAME,'-')||' ('|| NVL(KUERZEL,'-')||')',id_user from "+bibE2.cTO()+".jd_user where id_mandant="+bibALL.get_ID_MANDANT()+" order by name",
//				false,true,false,false);


		this.oSelectMitarbeiterADR = new Component_USER_DROPDOWN_NEW(false, 120);
		this.oSelectMitarbeiterANG = new Component_USER_DROPDOWN_NEW(false, 120);
		
		
		this.oSelectLand = new MyE2_SelectField(
				"select laendername,laendercode from "+bibE2.cTO()+".jd_land order by laendername",
				false,true,false,false);

		this.oSelJahre = new MyE2_SelectField(bibALL.get_cSelJahre(),bibALL.get_cYearNow(),false);
		this.oSelMonate = new MyE2_SelectField(bibALL.get_cSelArrayMonate(),bibALL.get_cMonthNow(),false);
		
		
		this.oSelectAdressKlasse = new MyE2_SelectField(
				"select kurzbezeichnung,id_adressklasse_def  from "+bibE2.cTO()+".jt_adressklasse_def",
				false,true,false,false);

		
		// zusatzselektor fuer tpa mit einer fuhre eines datums
		String[][] cSelektDatum = new String[12][2];
		cSelektDatum[0][0]= "-"; cSelektDatum[0][1]= "";
		int iCount = 1;
		for (int i=-5;i<6;i++)
		{
			GregorianCalendar oNow = new GregorianCalendar();
			oNow.add(GregorianCalendar.DATE,i);
			myDateHelper oDH = new myDateHelper(oNow);
			if (i==0)
				cSelektDatum[iCount][0]="->>>"+oDH.get_cDateFormatForMask();
			else
				cSelektDatum[iCount][0]=oDH.get_cDateFormatForMask();
			
			cSelektDatum[iCount][1]=oDH.get_cDateFormat_ISO_FORMAT();
			iCount++;
		}
		MyE2_SelectField oSelDate = new MyE2_SelectField(cSelektDatum,"",false);
		String cQueryBlock = "JT_VKOPF_TPA.ID_VKOPF_TPA IN (SELECT JT_VPOS_TPA.ID_VKOPF_TPA FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE,"+bibE2.cTO()+".JT_VPOS_TPA " +
									" WHERE JT_VPOS_TPA_FUHRE.ID_VPOS_TPA = JT_VPOS_TPA.ID_VPOS_TPA AND " +
									" JT_VPOS_TPA_FUHRE.DATUM_ABHOLUNG>='#WERT#' AND " +
									" JT_VPOS_TPA_FUHRE.DATUM_ANLIEFERUNG_ENDE<='#WERT#')";
		
		oSelVector.add(new E2_ListSelectorStandard(oSelDate,cQueryBlock, null, null));

		
		
		oSelVector.add(new E2_ListSelectorStandard(oSelectMitarbeiterADR,"JT_ADRESSE.ID_USER = #WERT#", null, null));
		oSelVector.add(new E2_ListSelectorStandard(oSelectMitarbeiterANG,"JT_VKOPF_TPA.ID_USER = #WERT#", null, null));
		oSelVector.add(new E2_ListSelectorStandard(oSelJahre,	"TO_NUMBER(TO_CHAR(JT_VKOPF_TPA.ERSTELLUNGSDATUM,'YYYY')) = #WERT#", null, null));
		oSelVector.add(new E2_ListSelectorStandard(oSelMonate,	"TO_NUMBER(TO_CHAR(JT_VKOPF_TPA.ERSTELLUNGSDATUM,'MM')) = #WERT#", null, null));
		oSelVector.add(new E2_ListSelectorStandard(oSelectLand,"JT_VKOPF_TPA.LAENDERCODE = '#WERT#'", null, null));
		oSelVector.add(new E2_ListSelectorStandard(oSelectAdressKlasse,"JT_VKOPF_TPA.ID_ADRESSE in (select JT_ADRESSKLASSE.ID_ADRESSE from "+bibE2.cTO()+".JT_ADRESSKLASSE where ID_ADRESSKLASSE_DEF=#WERT#)", null, null));
		oSelVector.add(new E2_ListSelectorStandard(this.oCheckBoxAbgeschlossen,"  NVL(JT_VKOPF_TPA.ABGESCHLOSSEN,'N')='Y'",""));
		oSelVector.add(new E2_ListSelectorStandard(this.oCheckBoxOffen,		"  NVL(JT_VKOPF_TPA.ABGESCHLOSSEN,'N')='N'",""));
		oSelVector.add(new E2_ListSelectorStandard(this.oCB_ShowDeletedRows,"","  NVL(JT_VKOPF_TPA.DELETED,'N')='N'"));

		//2013-02-28: neuer db-gestuetzter listselektor
		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(cMODULE_KENNER);
		oSelVector.add(oDB_BasedSelektor);

		
		MyE2_Grid oGrid = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_VERTICAL());
		
		
		Insets oIN = new Insets(0,2,2,2);
		Insets oIN2 = new Insets(0,2,20,2);

		oGrid.add(new MyE2_Label(new MyE2_String("Bearbeiter (Adr):")),1,oIN);		
		oGrid.add(new MyE2_Label(new MyE2_String("Bearbeiter (Angeb):")),1,oIN);	
		oGrid.add(oSelectMitarbeiterADR,1,oIN2);		
		oGrid.add(oSelectMitarbeiterANG,1,oIN2);

		oGrid.add(new MyE2_Label(new MyE2_String("Land:")),1,oIN);					
		oGrid.add(new MyE2_Label(new MyE2_String("Adr.Klasse:")),1,oIN);			
		oGrid.add(oSelectLand,1,oIN2);	
		oGrid.add(oSelectAdressKlasse,1,oIN2); 

		oGrid.add(new MyE2_Label(new MyE2_String("Erstellt Monat:")),1,oIN);		
		oGrid.add(new MyE2_Label(new MyE2_String("Erstellt Jahr:")),1,oIN);			
		oGrid.add(oSelMonate,1,oIN2);				
		oGrid.add(oSelJahre,1,oIN2);	
		
		oGrid.add(this.oCheckBoxAbgeschlossen,1,oIN2);								
		oGrid.add(this.oCheckBoxOffen,1,oIN2);

		oGrid.add(this.oCB_ShowDeletedRows,1,oIN2);
		oGrid.add(new E2_ComponentGroupHorizontal(0, new MyE2_Label(new MyE2_String("Enthält Datum:")), oSelDate, new Insets(0,0,2,0)),1,oIN2);
		
		oGrid.add(oDB_BasedSelektor.get_oComponentForSelection(new MyE2_String("Weitere:"), 100), new Insets(4,2,10,2));
		
		
		
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
		return oSelectMitarbeiterADR;
	}

	public MyE2_SelectField get_oSelectMitarbeiterANG() 
	{
		return oSelectMitarbeiterANG;
	}

	public MyE2_SelectField get_oSelJahre() 
	{
		return oSelJahre;
	}

	public MyE2_SelectField get_oSelMonate() 
	{
		return oSelMonate;
	}

	public MyE2_CheckBox get_oCB_ShowDeletedRows()
	{
		return oCB_ShowDeletedRows;
	}

	
	

}
