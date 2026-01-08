package panter.gmbh.Echo2.RB.BASICS;

import java.util.HashMap;
import java.util.Map;

import nextapp.echo2.app.Button;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationListCompact;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.SQLFieldMAP;

/**
 * basisklasse fuer transporthashmaps, mittels derer in den einzelne
 * list-mask-modulen infos uebergeben werden
 * 
 * @author martin
 *
 */
public class RB_TransportHashMap extends HashMap<RB_TransportHashMap_ENUM, Object> {

	public RB_TransportHashMap() {
		super();
		this.put(RB_TransportHashMap_ENUM.PLACE_FOR_STUCTURED_EXTENSION, new HashMap<RB_TransportHashMapEnumExtender,Object>());
		this.put(RB_TransportHashMap_ENUM.PLACE_FOR_UNSTUCTURED_EXTENSION, new HashMap<String,Object>());
	}

	public E2_NavigationList getNavigationList() {
		if (this.get(RB_TransportHashMap_ENUM.NAVILIST) != null
				&& (this.get(RB_TransportHashMap_ENUM.NAVILIST) instanceof E2_NavigationList)) {
			return (E2_NavigationList) this.get(RB_TransportHashMap_ENUM.NAVILIST);
		}
		return null;
	}

	public E2_NavigationListCompact getNaviListCompact() {
		if (this.get(RB_TransportHashMap_ENUM.NAVILIST) != null
				&& (this.get(RB_TransportHashMap_ENUM.NAVILIST) instanceof E2_NavigationListCompact)) {
			return (E2_NavigationListCompact) this.get(RB_TransportHashMap_ENUM.NAVILIST);
		}
		return null;
	}

	
	public RB_ModuleContainerMASK getRBModulContainerMask() {
		if (this.get(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK) != null
				&& (this.get(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK) instanceof RB_ModuleContainerMASK)) {
			return (RB_ModuleContainerMASK) this.get(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK);
		}
		return null;
	}

	public E2_BasicModuleContainer getModulContainerList() {
		if (this.get(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST) != null
				&& (this.get(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST) instanceof E2_BasicModuleContainer)) {
			return (E2_BasicModuleContainer) this.get(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST);
		}
		return null;
	}

	public SQLFieldMAP getListSqlFieldMap() {
		if (this.get(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SQL_FIELDMAP) != null
				&& (this.get(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SQL_FIELDMAP) instanceof SQLFieldMAP)) {
			return (SQLFieldMAP) this.get(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SQL_FIELDMAP);
		}
		return null;
	}

	public E2_ListSelectorContainer getListSelector() {
		if (this.get(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTOR) != null && (this
				.get(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTOR) instanceof E2_ListSelectorContainer)) {
			return (E2_ListSelectorContainer) this.get(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTOR);
		}
		return null;
	}
	
	public E2_SelectionComponentsVector getListSelectionComponentVector() {
		if (this.get(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTIONCOMPONENTVECTOR) != null && (this
				.get(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTIONCOMPONENTVECTOR) instanceof E2_SelectionComponentsVector)) {
			return (E2_SelectionComponentsVector) this.get(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTIONCOMPONENTVECTOR);
		}
		return null;
	}

	public E2_Grid getListBedienPanel() {
		if (this.get(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_BEDIENPANEL) != null
				&& (this.get(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_BEDIENPANEL) instanceof E2_Grid)) {
			return (E2_Grid) this.get(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_BEDIENPANEL);
		}
		return null;
	}

	public E2_DataSearch getListSearcher() {
		if (this.get(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SEARCH) != null
				&& (this.get(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SEARCH) instanceof E2_DataSearch)) {
			return (E2_DataSearch) this.get(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SEARCH);
		}
		return null;
	}

	public RB_ComponentMapCollector getMaskComponentMapCollector() {
		if (this.get(RB_TransportHashMap_ENUM.MASK_COMPONENT_MAP_COLLECTOR) != null && (this
				.get(RB_TransportHashMap_ENUM.MASK_COMPONENT_MAP_COLLECTOR) instanceof RB_ComponentMapCollector)) {
			return (RB_ComponentMapCollector) this.get(RB_TransportHashMap_ENUM.MASK_COMPONENT_MAP_COLLECTOR);
		}
		return null;
	}

	public RB_DataobjectsCollector getMaskDataObjectsCollector() {
		if (this.get(RB_TransportHashMap_ENUM.MASK_DATAOBJECTS_COLLECTOR) != null
				&& (this.get(RB_TransportHashMap_ENUM.MASK_DATAOBJECTS_COLLECTOR) instanceof RB_DataobjectsCollector)) {
			return (RB_DataobjectsCollector) this.get(RB_TransportHashMap_ENUM.MASK_DATAOBJECTS_COLLECTOR);
		}
		return null;
	}

	public RB_KM getLeadingMaskKey() {
		if (this.get(RB_TransportHashMap_ENUM.MASK_LEADING_MASKKEY) != null
				&& (this.get(RB_TransportHashMap_ENUM.MASK_LEADING_MASKKEY) instanceof RB_KM)) {
			return (RB_KM) this.get(RB_TransportHashMap_ENUM.MASK_LEADING_MASKKEY);
		}
		return null;
	}

	public _TAB getLeadingTableOnMask() {
		if (this.get(RB_TransportHashMap_ENUM.MASK_LEADING_TABLE_ON_MASK) != null
				&& (this.get(RB_TransportHashMap_ENUM.MASK_LEADING_TABLE_ON_MASK) instanceof _TAB)) {
			return (_TAB) this.get(RB_TransportHashMap_ENUM.MASK_LEADING_TABLE_ON_MASK);
		}
		return null;
	}

	public E2_Grid getMaskGrid() {
		if (this.get(RB_TransportHashMap_ENUM.MASK_GRID) != null
				&& (this.get(RB_TransportHashMap_ENUM.MASK_GRID) instanceof E2_Grid)) {
			return (E2_Grid) this.get(RB_TransportHashMap_ENUM.MASK_GRID);
		}
		return null;
	}

	public MASK_STATUS getLastMaskLoadStatus() {
		if (this.get(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK_LAST_LOAD_STATUS) != null
				&& (this.get(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK_LAST_LOAD_STATUS) instanceof MASK_STATUS)) {
			return (MASK_STATUS) this.get(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK_LAST_LOAD_STATUS);
		}
		return null;
	}

	
	public RB_TransportHashMap getMotherTransportHashMap() {
		if (this.get(RB_TransportHashMap_ENUM.MOTHERPARAMSHASH) != null
				&& (this.get(RB_TransportHashMap_ENUM.MOTHERPARAMSHASH) instanceof RB_TransportHashMap)) {
			return (RB_TransportHashMap) this.get(RB_TransportHashMap_ENUM.MOTHERPARAMSHASH);
		}
		return null;
	}

	
	
	public Long getMotherKeyValue() {
		if (this.get(RB_TransportHashMap_ENUM.MOTHERKEY_LONGVAL) != null
				&& (this.get(RB_TransportHashMap_ENUM.MOTHERKEY_LONGVAL) instanceof Long)) {
			return (Long) this.get(RB_TransportHashMap_ENUM.MOTHERKEY_LONGVAL);
		}
		return null;
	}

	public IF_Field getMotherKeyLookupField() {
		if (this.get(RB_TransportHashMap_ENUM.MOTHERKEY_FIELD) != null
				&& (this.get(RB_TransportHashMap_ENUM.MOTHERKEY_FIELD) instanceof IF_Field)) {
			return (IF_Field) this.get(RB_TransportHashMap_ENUM.MOTHERKEY_FIELD);
		}
		return null;
	}


	
	public RB_TransportHashMap _setNavigationList(E2_NavigationList o) {
		this.put(RB_TransportHashMap_ENUM.NAVILIST, o);
		return this;
	}
	
	public RB_TransportHashMap _setRBModulContainerMask(RB_ModuleContainerMASK o) {
		this.put(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK, o);

		return this;
	}

	public RB_TransportHashMap _setModulContainerList(E2_BasicModuleContainer o) {
		this.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST, o);
		return this;
	}

	public RB_TransportHashMap _setListSqlFieldMap(SQLFieldMAP o) {
		this.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SQL_FIELDMAP, o);
		return this;
	}

	public RB_TransportHashMap _setListSelector(E2_ListSelectorContainer o) {
		this.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTOR, o);
		return this;
	}

	public RB_TransportHashMap _setListSelectionComponentsVector(E2_SelectionComponentsVector o) {
		this.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTIONCOMPONENTVECTOR, o);
		return this;
	}

	
	public RB_TransportHashMap _setListBedienPanel(E2_Grid o) {
		this.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_BEDIENPANEL, o);
		return this;
	}

	public RB_TransportHashMap _setListSearcher(E2_DataSearch o) {
		this.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SEARCH, o);
		return this;
	}

	public RB_TransportHashMap _setMaskComponentMapCollector(RB_ComponentMapCollector o) {
		this.put(RB_TransportHashMap_ENUM.MASK_COMPONENT_MAP_COLLECTOR, o);
		return this;
	}

	public RB_TransportHashMap _setMaskDataObjectsCollector(RB_DataobjectsCollector o) {
		this.put(RB_TransportHashMap_ENUM.MASK_DATAOBJECTS_COLLECTOR, o);
		return this;
	}

	public RB_TransportHashMap _setLeadingMaskKey(RB_KM o) {
		this.put(RB_TransportHashMap_ENUM.MASK_LEADING_MASKKEY, o);
		return this;
	}

	public RB_TransportHashMap _setLeadingTableOnMask(_TAB o) {
		this.put(RB_TransportHashMap_ENUM.MASK_LEADING_TABLE_ON_MASK, o);
		return this;
	}

	public RB_TransportHashMap _setMaskGrid(E2_Grid o) {
		this.put(RB_TransportHashMap_ENUM.MASK_GRID, o);
		return this;
	}

	public RB_TransportHashMap _setMotherTransportHashMap(RB_TransportHashMap o) {
		this.put(RB_TransportHashMap_ENUM.MOTHERPARAMSHASH,o);
		return this;
	}
	
	public RB_TransportHashMap _setMaskStatusOnLoad(MASK_STATUS o) {
		this.put(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK_LAST_LOAD_STATUS,o);
		return this;
	}

	public RB_TransportHashMap _setMotherKeyValue(Long o) {
		this.put(RB_TransportHashMap_ENUM.MOTHERKEY_LONGVAL,o);
		return this;
	}
	
	public RB_TransportHashMap _setMotherKeyLookupField(IF_Field o) {
		this.put(RB_TransportHashMap_ENUM.MOTHERKEY_FIELD,o);
		return this;
	}

	
	public RB_TransportHashMap _setButtonMaskSave(Button o) {
		this.put(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK_SAVEBUTTON,o);
		return this;
	}

	public RB_TransportHashMap _setButtonMaskSaveAndReload(Button o) {
		this.put(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK_SAVEANDREOPENBUTTON,o);
		return this;
	}

	public RB_TransportHashMap _setButtonMaskCancel(Button o) {
		this.put(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK_CANCELBUTTON,o);
		return this;
	}

	
	public RB_TransportHashMap _setPlace4Everything(Object o) {
		this.put(RB_TransportHashMap_ENUM.PLACE_FOR_ANYTHING_ELSE,o);
		return this;
	}

	public Object getPlace4Everything() {
		if (this.get(RB_TransportHashMap_ENUM.PLACE_FOR_ANYTHING_ELSE) != null) {
			return this.get(RB_TransportHashMap_ENUM.PLACE_FOR_ANYTHING_ELSE);
		}
		return null;
	}

	
	
	public Button getButtonMaskSave() {
		if (this.get(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK_SAVEBUTTON) != null
				&& (this.get(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK_SAVEBUTTON) instanceof Button)) {
			return (Button) this.get(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK_SAVEBUTTON);
		}
		return null;
	}

	public Button getButtonMaskCancel() {
		if (this.get(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK_CANCELBUTTON) != null
				&& (this.get(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK_CANCELBUTTON) instanceof Button)) {
			return (Button) this.get(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK_CANCELBUTTON);
		}
		return null;
	}

	public Button getButtonMaskSaveAndReload() {
		if (this.get(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK_SAVEANDREOPENBUTTON) != null
				&& (this.get(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK_SAVEANDREOPENBUTTON) instanceof Button)) {
			return (Button) this.get(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK_SAVEANDREOPENBUTTON);
		}
		return null;
	}

	
	
	@SuppressWarnings("unchecked")
	public Object getFromExtender(RB_TransportHashMapEnumExtender e) {
		return ((HashMap<RB_TransportHashMapEnumExtender,Object>)this.get(RB_TransportHashMap_ENUM.PLACE_FOR_STUCTURED_EXTENSION)).get(e);
	}
	
	@SuppressWarnings("unchecked")
	public RB_TransportHashMap _setToExtender(RB_TransportHashMapEnumExtender e, Object o) {
		((HashMap<RB_TransportHashMapEnumExtender,Object>)this.get(RB_TransportHashMap_ENUM.PLACE_FOR_STUCTURED_EXTENSION)).put(e,o);
		return this;
	}

	/**
	 * put stringbase value to transport-hashmap
	 * @author martin
	 * @date 04.02.2020
	 *
	 * @param key
	 * @param val
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public RB_TransportHashMap _putSB(String key, Object val) {
		((HashMap<String,Object>)this.get(RB_TransportHashMap_ENUM.PLACE_FOR_UNSTUCTURED_EXTENSION)).put(key, val);
		return this;
	}
	
	
	/**
	 *  
	 * @author martin
	 * @date 04.02.2020
	 *
	 * @param key
	 * @return stringbasee - object from Transporthashmap
	 */
	@SuppressWarnings("unchecked")
	public Object getSB(String key) {
		return ((HashMap<String,Object>)this.get(RB_TransportHashMap_ENUM.PLACE_FOR_UNSTUCTURED_EXTENSION)).get(key);
	}

	
	/**
	 * Deep Copy,
	 * eigentliche Values werden nicht kopiert
	 * @author manfred
	 * @date 19.08.2020
	 *
	 * @return
	 */
	public RB_TransportHashMap _getCopyDeep() {
		RB_TransportHashMap hmCopy = new  RB_TransportHashMap();
		 
		hmCopy.put(RB_TransportHashMap_ENUM.PLACE_FOR_STUCTURED_EXTENSION, new HashMap<RB_TransportHashMapEnumExtender,Object>());
		hmCopy.put(RB_TransportHashMap_ENUM.PLACE_FOR_UNSTUCTURED_EXTENSION, new HashMap<String,Object>());
		
		for (Map.Entry<RB_TransportHashMap_ENUM, Object>  entry : entrySet() ) {
			if (entry.getKey().equals(RB_TransportHashMap_ENUM.PLACE_FOR_STUCTURED_EXTENSION) ) {
				HashMap<RB_TransportHashMapEnumExtender,Object> structured = (HashMap<RB_TransportHashMapEnumExtender, Object>) entry.getValue();
				 for ( Map.Entry<RB_TransportHashMapEnumExtender,Object> objStructured: structured.entrySet() ) {
					 hmCopy._setToExtender(objStructured.getKey(), objStructured.getValue());
				 }
				 
			} else if ( entry.getKey().equals(RB_TransportHashMap_ENUM.PLACE_FOR_STUCTURED_EXTENSION) 	) {
				HashMap<String,Object> unstructured = (HashMap<String, Object>) entry.getValue();
				 for ( Map.Entry<String,Object> objUnstructured: unstructured.entrySet() ) {
					 hmCopy._putSB(objUnstructured.getKey(), objUnstructured.getValue());
				 }
				
			} else {
				hmCopy.put(entry.getKey(), entry.getValue());
			}
		}
		
		return hmCopy;
	}
	
}
