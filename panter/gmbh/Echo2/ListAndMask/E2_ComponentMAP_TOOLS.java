package panter.gmbh.Echo2.ListAndMask;

import java.util.Vector;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public class E2_ComponentMAP_TOOLS {

	/**
	 * 
	 * @param oMap  -- E2_ComponentMAP, die neu sortiert wird
	 * @param vNewOrderKeyList  - Vector mit keys, die (alle vorhanden sein muessen) die neue Reihenfolge darstellen.
	 *                            Dabei fehlende werden hintenangehaengt in der alten Reihenfolge
	 * @return
	 * @throws myException
	 */
 	public static void NewColumnsOrder(E2_ComponentMAP oMap, Vector<String> vNewOrderKeyList) throws myException{
 		
 		Vector<String>  vOrderOLD = new Vector<String>();
 		Vector<String>  vOrderNEW = new Vector<String>();
 		
 		
 		vOrderOLD.addAll(oMap.get_vComponentHashKeys());
 		

 		
 		for (String cHash: vNewOrderKeyList) {
 			if (vOrderOLD.contains(cHash)) {
 				vOrderNEW.add(cHash);
 			} else {
 				throw new myException("E2_ComponentMAP_TOOLS:NewColumnsOrder:Key: <"+cHash+"> is not a member of E2_ComponentMAP!");
 			}
 		}
 		
 		
 		//jetzt noch alle fehlenden hintendran haengen
 		for (String cHash: vOrderOLD) {
 			if (!vOrderNEW.contains(cHash)) {
 				vOrderNEW.add(cHash);
 			}
 		}

 		oMap.get_vComponentHashKeys().removeAllElements();
 		oMap.get_vComponentHashKeys().addAll(vOrderNEW);
 		
 	}
	
 	/**
 	 * alle Spalten einer liste als hidden definieren, ausser den keys in vKeyListNotToHide
 	 * @param oMap
 	 * @param vKeyListNotToHide
 	 * @throws myException
 	 */
	public static void HideAllColums_WhichAreNotInVector(E2_ComponentMAP oMap, Vector<String> vKeyListNotToHide) throws myException{
		
		//zuerst pruefen, ob die uebergebenen spalten ueberhaupt registriert sind
 		for (String cHash: vKeyListNotToHide) {
 			if (!oMap.get_vComponentHashKeys().contains(cHash)) {
 				throw new myException("E2_ComponentMAP_TOOLS:HideAllColums:Key: <"+cHash+"> is not a member of E2_ComponentMAP!");
 			}
 		}
 
		
 		for (String cHash: oMap.get_vComponentHashKeys()) {
 			if (!vKeyListNotToHide.contains(cHash)) {
 				oMap.get__Comp(cHash).EXT().set_bIsVisibleInList(false);
 			}
 		}
	}
	
	
	/**
	 * geht alle kompoenten einer MAP durch, holt sich die real gerenderten und ersetzt deren layoutData durch eine kopie mit neuem hintergrund
	 * @param map
	 * @param colBack
	 * @throws myException
	 */
	public static void SetBackgroundColorInList(E2_ComponentMAP  map, Color colBack) throws myException {
		//map nach elementen mit real_rendered_component suchen 
		for (MyE2IF__Component comp: map.values()) {
			if (comp.EXT().get_real_rendered_component_in_list()!=null) {
				LayoutDataFactory.change_gridLayoutData( comp.EXT().get_real_rendered_component_in_list(), colBack);
				if ((comp instanceof Component) && ((Component)comp).getComponents()!=null) {
					Component[] compcollect = ((Component)comp).getComponents();
					for (Component c: compcollect) {
						LayoutDataFactory.change_gridLayoutData(c, colBack);
					}
				}
			}
		}

	}
	
	
	/**
	 * holt eine Componente aus der map und highlighted diese
	 * @param map
	 * @param field_key_to_highlight
	 * @param colBack
	 * @throws myException
	 */
	public static void SetBackgroundColorInList(E2_ComponentMAP  map, String field_key_to_highlight, Color colBack) throws myException {
		//map nach elementen mit real_rendered_component suchen 
		MyE2IF__Component comp = map.get_hmRealComponents().get(field_key_to_highlight);
		if (comp != null) {
			if (comp.EXT().get_real_rendered_component_in_list()!=null) {
				LayoutDataFactory.change_gridLayoutData( comp.EXT().get_real_rendered_component_in_list(), colBack);
				if ((comp instanceof Component) && ((Component)comp).getComponents()!=null) {
					Component[] compcollect = ((Component)comp).getComponents();
					for (Component c: compcollect) {
						LayoutDataFactory.change_gridLayoutData(c, colBack);
					}
				}
			}
		}
	}
	

	/**
	 * 
	 * @param map
	 * @param field_to_highlight
	 * @param colBack
	 * @throws myException
	 */
	public static void SetBackgroundColorInList(E2_ComponentMAP  map, IF_Field field_to_highlight, Color colBack) throws myException {
		E2_ComponentMAP_TOOLS.SetBackgroundColorInList(map,field_to_highlight.fn(),colBack);
	}
	

	/**
	 * genaue eine zeile in einer liste selektieren
	 * @param map
	 * @param id_to_check
	 * @throws myException
	 */
	public static boolean check_id_uncheck_others(E2_ComponentMAP map, String id_to_check) throws myException {
		Vector<E2_ComponentMAP> vVectorComponentMapThisBelongsTo = map.get_VectorComponentMAP_thisBelongsTo();
		
		int found_id = 0;
		
		if (vVectorComponentMapThisBelongsTo!=null)	{
			for (E2_ComponentMAP oMapSchleife: vVectorComponentMapThisBelongsTo) {
				oMapSchleife.setChecked_CheckBoxForList(false);
				if (oMapSchleife.get_oInternalSQLResultMAP()!=null) {
					String id = oMapSchleife.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
					if (id.equals(id_to_check)) {
						oMapSchleife.setChecked_CheckBoxForList(true);
						found_id++;
					}
				}
			}
		}
		
		return (found_id==1);
	}
	
	
	
	
	
	
}
