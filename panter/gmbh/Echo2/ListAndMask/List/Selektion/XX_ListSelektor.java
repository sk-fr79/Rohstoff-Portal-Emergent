package panter.gmbh.Echo2.ListAndMask.List.Selektion;

import java.util.Hashtable;
import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.Selector_Report_Params.ENUM_Selector_Report_Params;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;


/*
 * abstrakter listenselektor
 */
public abstract class XX_ListSelektor
{
	public abstract String 		get_WhereBlock() throws myException;
	public abstract Component 	get_oComponentForSelection()  throws myException;
	public abstract Component 	get_oComponentWithoutText() throws myException;
	public abstract void    	add_ActionAgentToComponent(XX_ActionAgent oAgent); 
	
	/*
	 * pruefroutine, die evtl. vorhanden eingabe in die komponente prueft, bevor die
	 * selektion einen wert an den selektionsvector uebergibt. Wird beim neuaufbau 
	 * jedesmal fuer alle selektionskomponenten ausgefuehrt 
	 */
	public abstract void 		doInternalCheck();
	
	private boolean             bIstAlleinMerkmal = false;
	private MyString 			cAlleinstellungsInfo = null;

	
	private boolean             bSaveSettings = true;    //standard ist: wird gespeichert
	
	
	
	//2012-02-14: neutralisator-klasse
	private XX_ListSelektor_Neutralisator  oNeutralisator = null;
	
	/**
	 * der Parameter des Selektors, der an den Report übergeben werden kann.
	 * Meistens nur ein Wert, aber es können auch mehrere Parameterwerte sein, z.B. bei Selektor-Komponenten mit mehreren Objekten (checkboxen)  
	 */
	protected Vector<ENUM_Selector_Report_Params> 	m_vSelectorParams = new Vector<>();
	public void add_SelectorParam(ENUM_Selector_Report_Params oReportParam){
		m_vSelectorParams.add(oReportParam);
	};
	
	
	
	/**
	 * dummy-Methode, muss implementiert werden.
	 * @author manfred
	 * @date 12.05.2016
	 *
	 * @return HashTable mit den Key/Value-Paaren der aktuellen Werte
	 * @throws myException 
	 */
	public Hashtable<ENUM_Selector_Report_Params,Object> get_ParamValueTable() throws myException{
		return new Hashtable<>();
	}; 
	
	
	
	/*
	 * hat ein Selektor dieses Merkmal, dann wird (sobald dieser Selecktor eine bedingung liefert, diese alleine ausgefuehrt !!)
	 */
	public boolean get_bIstAlleinMerkmal()
	{
		return bIstAlleinMerkmal;
	}
	
	public void set_IstAlleinMerkmal(boolean istAlleinMerkmal, MyString AlleinStellungsInfo)
	{
		bIstAlleinMerkmal = istAlleinMerkmal;
		this.cAlleinstellungsInfo = AlleinStellungsInfo;
	}
	
	public MyString get_AlleinstellungsInfo()
	{
		return cAlleinstellungsInfo;
	}
	
	
	public boolean get_bSaveSettings()
	{
		return bSaveSettings;
	}
	public void set_bSaveSettings(boolean SaveSettings)
	{
		this.bSaveSettings = SaveSettings;
	}
	public XX_ListSelektor_Neutralisator get_oNeutralisator()
	{
		return oNeutralisator;
	}
	
	public void set_oNeutralisator(XX_ListSelektor_Neutralisator oNeutralisator)
	{
		this.oNeutralisator = oNeutralisator;
	}

	/**
	 * neutralisert den selektor wenn ein neutralisator vorhanden ist
	 * @throws myException 
	 */
	public void set_neutral_if_possible() throws myException {
		if (this.oNeutralisator!=null) {
			this.oNeutralisator.set_to_Neutral();
		}
	}
	
	
}
