package rohstoff.businesslogic.bewegung2.lager.selector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.businesslogic.bewegung2.lager.B2_LAG_CONST;
import rohstoff.businesslogic.bewegung2.lager.B2_LAG_CONST.Table_alias;

public class B2_LIST_Selector_TypenEinblenden extends E2_ListSelektorMultiselektionStatusFeld_STD
{

	public enum ENUM_SELEKTOR_BEWEGUNGSTYPEN {
		KUNDEN,SW,MK,LL,KH,UH
	}
	
	private VEK<ENUM_SELEKTOR_BEWEGUNGSTYPEN> vCheckboxen = new VEK<ENUM_SELEKTOR_BEWEGUNGSTYPEN>(); 
	
	/**
	 * Erzeugt Selektionsblock mit Beschriftung
	 * @param oNavigationList
	 * @throws myException
	 */
	public B2_LIST_Selector_TypenEinblenden(E2_NavigationList oNavigationList) throws myException {
		this(oNavigationList,true);
	}
	
	
	/**
	 * Erzeugt Selektionsblock mit/ohne Beschriftung
	 * @param oNavigationList
	 * @param bZeigeBeschriftungAn
	 * @throws myException
	 */
	public B2_LIST_Selector_TypenEinblenden(E2_NavigationList oNavigationList,boolean bZeigeBeschriftungAn) throws myException
	{
		super(1,bZeigeBeschriftungAn, S.ms(""), new Extent(40));
		
		String sColDef = BG_STATION.id_adresse.fn(B2_LAG_CONST.Table_alias.STATION2.getAlias());
		
		
		//KORREKTUR_HAND
		String s_KH = "(" + sColDef + " = " +  bibSES.get_ID_ADRESSE_LAGER_KORREKTUR_HAND() +") ";
		
		//UMBUCHUNG HAND
		String s_UH = "(" + sColDef + " = " + bibSES.get_ID_ADRESSE_LAGER_UMBUCHUNG_HAND() + ")";
		
		//LAGER - LAGER
		String s_LL = "(" + sColDef + " = " + bibSES.get_ID_ADRESSE_LAGER_LAGERLAGER() + ")" ;
		
//		*String s_KUNDEN = "(" + sColDef + " IN (" 
//		+ bibSES.get_ID_ADRESSE_LAGER_ZWISCHENLAGER_WE() 
//		+ "," + bibSES.get_ID_ADRESSE_LAGER_ZWISCHENLAGER_WA()
//	    + "," + bibSES.get_ID_ADRESSE_LAGER_MIXED()+") )";
//				
//				+ "SELECT ID_ADRESSE FROM " +bibE2.cTO() + ".JT_ADRESSE "
//						+ "WHERE ( nvl(SONDERLAGER,'ZWE') = trim('ZWE') or "
//						+ "nvl(SONDERLAGER,'ZWA') = trim('ZWA') or "
//						+ "nvl(SONDERLAGER,'MI') = trim('MI') )  "
//						+ ""
//						+ ") ) ";
		
//		this.addCheckbox(ENUM_SELEKTOR_BEWEGUNGSTYPEN.KUNDEN,true,	s_KUNDEN,	S.ms("Fuhren mit Kundenbeteiligung"),	S.ms("Alle Fuhren von und zu Kunden aus einem oder in ein Lager"));
		this.addCheckbox(ENUM_SELEKTOR_BEWEGUNGSTYPEN.LL	,true,	s_LL,  		S.ms("Lager-Lager-Fuhren"),   			S.ms("Lager zu Lager Fuhren"));
		this.addCheckbox(ENUM_SELEKTOR_BEWEGUNGSTYPEN.KH	,true,	s_KH,  		S.ms("Korrekturbuchungen"),   			S.ms("Korrekturbuchungen Hand"));
		this.addCheckbox(ENUM_SELEKTOR_BEWEGUNGSTYPEN.UH	,true,	s_UH,  		S.ms("Umbuchungen"),   					S.ms("Umbuchungen Hand"));
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
		String cWHERE = "1=2";
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
