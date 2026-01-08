package panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;

public abstract class XX_SearchBlock_ALL
{
	// zuatzvector, kann in den implementierungen benutzt werden, um zusaetzliche, von den gegebenheiten
	// abhaengige where-bedinungen in die Abfrage zu senden
	private Vector<String> vZusatzWhereBedingungen = new Vector<String>();
		
	private boolean  bAllowEmptySearchField = false;
	
	/*
	 * hashmap hat den Integer-Wert der spaltennummer im Abfrageergebnis als hashkey
	 */
	private HashMap<Integer,DefSpalteLayout_And_Else>  hmDefinition4Sort = 	new HashMap<Integer, DefSpalteLayout_And_Else>();
	private Boolean                   			bResultListIsSortable = new Boolean(true);

	
	public boolean get_bAllowEmptySearchField()
	{
		return bAllowEmptySearchField;
	}

	public void set_bAllowEmptySearchField(boolean allowEmptySearchField)
	{
		bAllowEmptySearchField = allowEmptySearchField;
	}
		
	// ein manipulator-objekt, das die buttons nochmal beinflussen kann (kann dazu jedenfalls eingesetzt werden
	private XX_Manipulator_Result    oManipulator = null;

	public XX_Manipulator_Result 	get_Manipulator() 
	{
		return oManipulator;
	}
	
	public void set_oManipulator(XX_Manipulator_Result manipulator) 
	{
		oManipulator = manipulator;
	};
	
	public abstract Vector<XX_Button4SearchResultList[]> get_vResultButtons(String SearchText) throws myException;

	public Vector<String> get_vZusatzWhereBedingungen()
	{
		return vZusatzWhereBedingungen;
	}

	public void set_vZusatzWhereBedingungen(Vector<String> zusatzWhereBedingungen)
	{
		vZusatzWhereBedingungen = zusatzWhereBedingungen;
	}
	/*
	 * falls man die ergebnis-Buttons einzeln anordnen will, kann man eine 
	 * eigene Container-Komponente erzeugen, die diese Buttons bereits enthaelt
	 */
	//public abstract Component get_ContainerWithFoundButtons();
	public abstract E2_BasicModuleContainer get_ContainerForShowResults();

	
	//der getter wird abstract, da dieser manuell zusammengestellt werden kann in haendisch erzeugten SearchBlocks
	public HashMap<Integer,DefSpalteLayout_And_Else>  get_hmSortierInfo() throws myException
	{
		return this.hmDefinition4Sort;
	}
	
	
	public Boolean                    			get_bResultListIsSortable()
	{
		return this.bResultListIsSortable;
	}

	public void  set_hmSortierInfo(HashMap<Integer,DefSpalteLayout_And_Else> hmInfo) throws myException
	{
		this.hmDefinition4Sort = hmInfo;
	}
	
	public void set_bResultListIsSortable(Boolean bIsSortable)
	{
		this.bResultListIsSortable = bIsSortable;
	}
	
}
