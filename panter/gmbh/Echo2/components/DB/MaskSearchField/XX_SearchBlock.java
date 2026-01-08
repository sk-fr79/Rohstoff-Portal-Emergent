package panter.gmbh.Echo2.components.DB.MaskSearchField;

import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.XX_SearchBlock_ALL;
/*
 * eine implementierung des searchblocks muss eine methode implementieren,
 * die einen vector an searchbuttons zurueckgibt, in deren 
 * .EXT().set_C_MERKMAL();  der wert zurueckkommt, der in der maske
 * im eingabefeld verarbeitet wird
 */
public abstract class XX_SearchBlock extends XX_SearchBlock_ALL
{
//	// zuatzvector, kann in den implementierungen benutzt werden, um zusaetzliche, von den gegebenheiten
//	// abhaengige where-bedinungen in die Abfrage zu senden
//	private Vector<String> vZusatzWhereBedingungen = new Vector<String>();
//		
//	private boolean  bAllowEmptySearchField = false;
//	
//	public boolean get_bAllowEmptySearchField()
//	{
//		return bAllowEmptySearchField;
//	}
//
//	public void set_bAllowEmptySearchField(boolean allowEmptySearchField)
//	{
//		bAllowEmptySearchField = allowEmptySearchField;
//	}
//		
//	// ein manipulator-objekt, das die buttons nochmal beinflussen kann (kann dazu jedenfalls eingesetzt werden
//	private XX_Manipulator_Result    oManipulator = null;
//
//	public XX_Manipulator_Result 	get_Manipulator() 
//	{
//		return oManipulator;
//	}
//	
//	public void set_oManipulator(XX_Manipulator_Result manipulator) 
//	{
//		oManipulator = manipulator;
//	};
//	
//	public abstract Vector<MyE2_Button[]> get_vResultButtons(String SearchText) throws myException;
//
//	public Vector<String> get_vZusatzWhereBedingungen()
//	{
//		return vZusatzWhereBedingungen;
//	}
//
//	public void set_vZusatzWhereBedingungen(Vector<String> zusatzWhereBedingungen)
//	{
//		vZusatzWhereBedingungen = zusatzWhereBedingungen;
//	}
//	/*
//	 * falls man die ergebnis-Buttons einzeln anordnen will, kann man eine 
//	 * eigene Container-Komponente erzeugen, die diese Buttons bereits enthaelt
//	 */
//	public abstract Component get_ContainerWithFoundButtons();
//	public abstract E2_BasicModuleContainer get_ContainerForShowResults();
}



