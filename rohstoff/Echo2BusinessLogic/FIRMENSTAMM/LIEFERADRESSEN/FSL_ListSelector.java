package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.LIEFERADRESSEN;


import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.Component_USER_DROPDOWN_NEW;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;

public class FSL_ListSelector extends E2_ListSelectorContainer {

	//2012-03-05: selektor fuer die lager
	private  E2_SelectionComponentsVector 						oSelVector = null ;

	private E2_NavigationList   								oNaviList = null;
	
	private Component_USER_DROPDOWN_NEW  						oSelektBearbeiter =  	null;
	private Component_USER_DROPDOWN_NEW  						oSelektHaendler = 		null;
	private E2_ListSelectorStandard 							oSelObjBearbeiter = null;
	private E2_ListSelectorStandard 							oSelObjHaendler = null;
	
	private ownSelectorMultiSelectStatus_eigene_fremde_lager  	oSelektLagerstatus = null;
	private ownSelectorMultiSelectStatus_aktiv_passiv  			oSelektAktiv = null;
	
	public FSL_ListSelector(E2_NavigationList NaviList) throws myException {
		super();
		
		
		
		this.oNaviList = NaviList;
		
		//2012-03-05: user-selektor
		this.oSelektBearbeiter =  	new Component_USER_DROPDOWN_NEW(false, 80, new E2_FontPlain(), new MyE2_String("Selektiere Lager mit Sachbearbeiter"));
		this.oSelektHaendler = 	 	new Component_USER_DROPDOWN_NEW(false, 80, new E2_FontPlain(), new MyE2_String("Selektiere Lager mit Händler"));
		this.oSelObjBearbeiter = 		new E2_ListSelectorStandard(oSelektBearbeiter, "JT_ADRESSE."+RECORD_ADRESSE.FIELD__ID_USER_LAGER_SACHBEARB+"=#WERT#", new MyE2_String("Sachbearb.:"), 5);
		this.oSelObjHaendler =			new E2_ListSelectorStandard(oSelektHaendler,   "JT_ADRESSE."+RECORD_ADRESSE.FIELD__ID_USER_LAGER_HAENDLER+"=#WERT#", new MyE2_String("Händler:"), 5);
		
		this.oSelVector = new E2_SelectionComponentsVector(this.oNaviList);
		this.oSelVector.add(oSelObjBearbeiter);
		this.oSelVector.add(oSelObjHaendler);

		//2014-01-03: neuer selektor in lieferadressen: anzeigen eigen/fremdwaren-lager
		this.oSelektLagerstatus = new ownSelectorMultiSelectStatus_eigene_fremde_lager();
		this.oSelVector.add(oSelektLagerstatus);
		//2014-01-16: aktiv/passiv anzeigen
		this.oSelektAktiv= new ownSelectorMultiSelectStatus_aktiv_passiv();
		this.oSelVector.add(oSelektAktiv);
		
		
		
		this.add(this.oSelObjBearbeiter.get_oComponentForSelection(),E2_INSETS.I_10_0_0_0);
		this.add(oSelObjHaendler.get_oComponentForSelection(),E2_INSETS.I_10_0_10_0);
		this.add(oSelektLagerstatus.get_oComponentForSelection(),E2_INSETS.I_10_0_10_0);
		this.add(oSelektAktiv.get_oComponentForSelection(),E2_INSETS.I_10_0_10_0);
		

		
	}

	
	private class ownSelectorMultiSelectStatus_eigene_fremde_lager extends E2_ListSelektorMultiselektionStatusFeld_STD {

		public ownSelectorMultiSelectStatus_eigene_fremde_lager()
		{
			super(4, false, null,new Extent(150));
			this.ADD_STATUS_TO_Selector(true, "("+_DB.LIEFERADRESSE+"."+_DB.LIEFERADRESSE$ID_ADRESSE_FREMDWARE+" IS NULL)", new MyE2_String("Eigen"), new MyE2_String("Eigenwaren-Lager anzeigen"));
			this.ADD_STATUS_TO_Selector(true, "("+_DB.LIEFERADRESSE+"."+_DB.LIEFERADRESSE$ID_ADRESSE_FREMDWARE+" IS NOT NULL)", new MyE2_String("Fremd"), new MyE2_String("Fremdwaren-Lager anzeigen"));
		}
		
	}

	private class ownSelectorMultiSelectStatus_aktiv_passiv extends E2_ListSelektorMultiselektionStatusFeld_STD {

		public ownSelectorMultiSelectStatus_aktiv_passiv()
		{
			super(4, false, null,new Extent(150));
			this.ADD_STATUS_TO_Selector(true, "(NVL("+_DB.ADRESSE+"."+_DB.ADRESSE$AKTIV+",'N')='Y')", 	new MyE2_String("Aktiv"), new MyE2_String("Aktive Lager anzeigen"));
			this.ADD_STATUS_TO_Selector(true, "(NVL("+_DB.ADRESSE+"."+_DB.ADRESSE$AKTIV+",'N')='N')", 	new MyE2_String("Inaktiv"), new MyE2_String("Inaktive Lager anzeigen"));
		}
		
	}

	
	/*
	 * wird vor jedem maskenaufbau aufgerufen 
	 */
	public void set_Neutral() throws myException {
		this.oSelektBearbeiter.set_ActiveValue_OR_FirstValue("");
		this.oSelektHaendler.set_ActiveValue_OR_FirstValue("");
		
		this.oSelektLagerstatus.get_oNeutralisator().set_to_Neutral();
		this.oSelektAktiv.get_oNeutralisator().set_to_Neutral();
		
	}
	

	
	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}

}
