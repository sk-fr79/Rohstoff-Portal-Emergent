package panter.gmbh.Echo2.__BASIC_MODULS.BENUTZER_VERWALTUNG;


import nextapp.echo2.app.Border;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;

public class USER__LIST_Selector extends E2_ExpandableRow
{
	
	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	
	private E2_SelectionComponentsVector 	oSelVector = null;
	
	public USER__LIST_Selector(E2_NavigationList oNavigationList) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);

		MyE2_CheckBox 		oCB_SUPER=		new MyE2_CheckBox(new MyE2_String("Supervisor"));
		MyE2_CheckBox 		oCB_TEXT=		new MyE2_CheckBox(new MyE2_String("Textsammler"));
		MyE2_CheckBox 		oCB_FAHRER=		new MyE2_CheckBox(new MyE2_String("Fahrer"));
		MyE2_CheckBox 		oCB_AKTIV=		new MyE2_CheckBox(new MyE2_String("Aktiv"));
		MyE2_CheckBox 		oCB_INAKTIV=		new MyE2_CheckBox(new MyE2_String("Inaktiv"));
		MyE2_CheckBox 		oCB_FAHRPLAN=	new MyE2_CheckBox(new MyE2_String("Fahrplaneingabe"));
		MyE2_CheckBox 		oCB_VERWALTUNG=	new MyE2_CheckBox(new MyE2_String("Verwaltung"));
		MyE2_CheckBox 		oCB_DEVELOPER=	new MyE2_CheckBox(new MyE2_String("Systementwickler"));
		
		
		E2_ListSelectorStandard oSelector = new E2_ListSelectorStandard("SELECT NVL(NAME1,'')||' '||NVL(NAME2,''),ID_MANDANT FROM "+bibE2.cTO()+".JD_MANDANT ORDER BY ID_MANDANT"," JD_USER.ID_MANDANT=#WERT#",true, new MyE2_String("Mandant:"), new Integer(12));
		this.oSelVector.add(oSelector);
		this.oSelVector.add(new E2_ListSelectorStandard(oCB_SUPER,"NVL(JD_USER.IST_SUPERVISOR,'N')='Y'",""));
		this.oSelVector.add(new E2_ListSelectorStandard(oCB_TEXT,"NVL(JD_USER.TEXTCOLLECTOR,'N')='Y'",""));
		this.oSelVector.add(new E2_ListSelectorStandard(oCB_FAHRER,"NVL(JD_USER.IST_FAHRER,'N')='Y'",""));
		this.oSelVector.add(new E2_ListSelectorStandard(oCB_AKTIV,"NVL(JD_USER.AKTIV,'N')='Y'",""));
		this.oSelVector.add(new E2_ListSelectorStandard(oCB_INAKTIV,"NVL(JD_USER.AKTIV,'N')='N'",""));
		this.oSelVector.add(new E2_ListSelectorStandard(oCB_FAHRPLAN,"NVL(JD_USER.HAT_FAHRPLAN_BUTTON,'N')='Y'",""));
		this.oSelVector.add(new E2_ListSelectorStandard(oCB_VERWALTUNG,"NVL(JD_USER.IST_VERWALTUNG,'N')='Y'",""));
		this.oSelVector.add(new E2_ListSelectorStandard(oCB_DEVELOPER,"NVL("+_DB.Z_USER$DEVELOPER+",'N')='Y'",""));
		
		this.add(oSelector.get_oComponentForSelection(), E2_INSETS.I_0_0_10_0);
		this.add(oCB_SUPER, E2_INSETS.I_0_0_10_0);
		this.add(oCB_TEXT, E2_INSETS.I_0_0_10_0);
		this.add(oCB_VERWALTUNG, E2_INSETS.I_0_0_10_0);
		this.add(oCB_FAHRER, E2_INSETS.I_0_0_10_0);
		this.add(oCB_AKTIV, E2_INSETS.I_0_0_10_0);
		this.add(oCB_INAKTIV, E2_INSETS.I_0_0_10_0);
		this.add(oCB_FAHRPLAN, E2_INSETS.I_0_0_10_0);
		this.add(oCB_DEVELOPER, E2_INSETS.I_0_0_10_0);
		
	}

	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}

}
