package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWEGUNG;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

public class ATOM_LAG_LIST_SelektorWelcheTypenEinblenden extends E2_ListSelektorMultiselektionStatusFeld_STD
{
	/**
	 * Selektor für die einzelnen typen
	 * @author manfred
	 *
	 */
	public enum ENUM_SELEKTOR_BEWEGUNGSTYPEN {
		KUNDEN,SW,MK,LL,KH,UH
	}
	
	private Vector<ENUM_SELEKTOR_BEWEGUNGSTYPEN> vCheckboxen = new Vector<ATOM_LAG_LIST_SelektorWelcheTypenEinblenden.ENUM_SELEKTOR_BEWEGUNGSTYPEN>(); 
	
	/**
	 * Erzeugt Selektionsblock mit Beschriftung
	 * @param oNavigationList
	 * @throws myException
	 */
	public ATOM_LAG_LIST_SelektorWelcheTypenEinblenden(E2_NavigationList oNavigationList) throws myException {
		this(oNavigationList,true);
	}
	
	
	/**
	 * Erzeugt Selektionsblock mit/ohne Beschriftung
	 * @param oNavigationList
	 * @param bZeigeBeschriftungAn
	 * @throws myException
	 */
	public ATOM_LAG_LIST_SelektorWelcheTypenEinblenden(E2_NavigationList oNavigationList,boolean bZeigeBeschriftungAn) throws myException
	{
		super(1,bZeigeBeschriftungAn,new MyE2_String("Zeige: "),new Extent(40));
		
//		String sColDef = oNavigationList.get_oComponentMAP__REF().get_hmRealDBComponents().get("LIEFERANT_ID_ADRESSE").EXT_DB().get_oSQLField().get_cFieldAusdruck();
		
		String sColDef = "ADR_GEGEN.ID_ADRESSE_GEGEN";
		
		// die Selektion wird nicht auf die gesetzte Checkbox gemacht, sondern auf die NICHT-gesetzte,
		// damit man die AND-Verknüpfung nehmen kann anstatt der OR (schneller)

		// alle Sonderlager	
		String s_SW = "(" + sColDef + " = (SELECT ID_ADRESSE FROM " +bibE2.cTO() + ".JT_ADRESSE WHERE trim(SONDERLAGER) = trim('SW')  ) ) ";
		String s_MK = "(" + sColDef + " = (SELECT ID_ADRESSE FROM " +bibE2.cTO() + ".JT_ADRESSE WHERE trim(SONDERLAGER) = trim('MK')  ) ) ";
		String s_KH = "(" + sColDef + " = (SELECT ID_ADRESSE FROM " +bibE2.cTO() + ".JT_ADRESSE WHERE trim(SONDERLAGER) = trim('KH')  ) ) ";
		String s_UH = "(" + sColDef + " = (SELECT ID_ADRESSE FROM " +bibE2.cTO() + ".JT_ADRESSE WHERE trim(SONDERLAGER) = trim('UH')  ) ) ";
		String s_LL = "(" + sColDef + " = (SELECT ID_ADRESSE FROM " +bibE2.cTO() + ".JT_ADRESSE WHERE trim(SONDERLAGER) = trim('LL')  ) ) ";

		// Kunden und MIXED-Fuhren gehen in einem auf, da Mixed eine spezialität von Standardfuhren sind...
//		String s_KUNDEN = "(" + sColDef + " IN (SELECT ID_ADRESSE FROM " +bibE2.cTO() + ".JT_ADRESSE WHERE nvl(SONDERLAGER,'MI') = trim('MI')  ) ) ";
		String s_KUNDEN = "(" + sColDef + " IN (SELECT ID_ADRESSE FROM " +bibE2.cTO() + ".JT_ADRESSE WHERE ( nvl(SONDERLAGER,'ZWE') = trim('ZWE') or nvl(SONDERLAGER,'ZWA') = trim('ZWA') or nvl(SONDERLAGER,'MI') = trim('MI') )  ) ) ";
			
		
		this.addCheckbox(ENUM_SELEKTOR_BEWEGUNGSTYPEN.KUNDEN, true,	s_KUNDEN,	new MyE2_String("Fuhren mit Kundenbeteiligung"),	new MyE2_String("Alle Fuhren von und zu Kunden aus einem oder in ein Lager"));
		
//		this.addCheckbox(ENUM_SELEKTOR_BEWEGUNGSTYPEN.KUNDEN,true,	s_SW,  		new MyE2_String("Sortenwechsel"),   				new MyE2_String("Sortenwechsel"));
//		this.addCheckbox(ENUM_SELEKTOR_BEWEGUNGSTYPEN.MK	,true,	s_MK,  		new MyE2_String("Automatische Mengenkorrekturen"),	new MyE2_String("Automatische Mengenkorrekturen"));
		this.addCheckbox(ENUM_SELEKTOR_BEWEGUNGSTYPEN.LL	,true,	s_LL,  		new MyE2_String("Lager-Lager-Fuhren"),   			new MyE2_String("Lager zu Lager Fuhren"));
		this.addCheckbox(ENUM_SELEKTOR_BEWEGUNGSTYPEN.KH	,true,	s_KH,  		new MyE2_String("Korrekturbuchungen"),   			new MyE2_String("Korrekturbuchungen Hand"));
		this.addCheckbox(ENUM_SELEKTOR_BEWEGUNGSTYPEN.UH	,true,	s_UH,  		new MyE2_String("Umbuchungen"),   					new MyE2_String("Umbuchungen Hand"));
	}

	
	/**
	 * Anlegen der Selektorenelemente und dem parallelen Vektor der Typen, um gezielt die Checkboxen zu de- /selektieren zu können
	 * @param enumTyp
	 * @param bPreselected
	 * @param cBedingungsBlockFuer_OR_Statement
	 * @param cBeschriftung
	 * @param cToolTips
	 */
	private void addCheckbox(ENUM_SELEKTOR_BEWEGUNGSTYPEN enumTyp, boolean bPreselected, String cBedingungsBlockFuer_OR_Statement, MyString cBeschriftung, MyString cToolTips){
		vCheckboxen.add(enumTyp);
		this.ADD_STATUS_TO_Selector(bPreselected,cBedingungsBlockFuer_OR_Statement,cBeschriftung, cToolTips);
	}
	
	
	
	/**
	 * setzt alle Checkboxen auf selected=FALSE
	 */
	public void selectAllCheckboxes(boolean bSelected){
		int count = get_vCheckBoxTypen().size();
		for(int i=0; i<count; i++){
			selectCheckbox(i, bSelected);
		}
	}
	
	
	/**
	 * de-/selektiert die angegebene Checkbox 
	 * @param enumCheckbox
	 * @param bSelected
	 */
	public void selectCheckbox(ENUM_SELEKTOR_BEWEGUNGSTYPEN enumCheckbox, boolean bSelected ){
		int count = vCheckboxen.size();
		
		for (int i= 0; i<count; i++){
			if (vCheckboxen.get(i).equals(enumCheckbox)){
				selectCheckbox(i, bSelected);
				break;
			}
		}
		
	}
	
	/**
	 * an-/ ausschalten einer Checkbox 
	 * @author manfred
	 * @date   13.02.2012
	 * @param index
	 * @param bTrue
	 */
	public void selectCheckbox(int index, boolean bSelected){
		int count = get_vCheckBoxTypen().size();
		if (index > count -1 || index < 0) return;
		
		get_vCheckBoxTypen().get(index).setSelected(bSelected);
	}
	

	/**
	 * gibt die Referenz auf die Checkbox zurück 
	 * @author manfred
	 * @date   17.07.2013
	 * @param index
	 * @return
	 */
	public MyE2_CheckBox getCheckbox(int index){
		int count = get_vCheckBoxTypen().size();
		return ((count == 0 || index > count -1 ) ? null : get_vCheckBoxTypen().get(index));
	}
	
	
	
	@Override
	public String get_WhereBlock() throws myException
	{
		
		/*		
		 * Der Block soll eine Bedinung wie folgt ergeben!
		 * 				
				) )
		 */		
		String cWHERE = " 1 = 2 ";
		boolean bHasWhereblock = false;
		String sAND = " OR ";
		for (MyE2_CheckBox oCB: get_vCheckBoxTypen())
		{
			if ( oCB.isSelected())
			{
				
				if (bHasWhereblock == false){
					bHasWhereblock = true;
				} else {
					sAND = " OR ";
				}
				
				cWHERE += sAND + oCB.EXT().get_C_MERKMAL();
			}
		}
		
		if (bHasWhereblock)
		{
			cWHERE = "(" + cWHERE + " )";
		}
		
		return cWHERE;
	}

	
	
}
