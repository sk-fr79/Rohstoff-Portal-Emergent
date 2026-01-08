package panter.gmbh.Echo2.RB.BASICS;

/**
 * wenn innerhalb einer listen-masken-struktur in einer hashmap
 * infos transferiert werden, sind hier die schluesseltype hinterlegt
 * @author martin
 *
 */
public enum RB_TransportHashMap_ENUM {
	
	
    NAVILIST()
    ,MODULCONTAINER_MASK()
    ,MODULCONTAINER_MASK_LAST_LOAD_STATUS()
    ,MODULCONTAINER_LIST()
    ,MODULCONTAINER_LIST_SQL_FIELDMAP()
    ,MODULCONTAINER_LIST_SELECTOR()
    ,MODULCONTAINER_LIST_SELECTIONCOMPONENTVECTOR()
    ,MODULCONTAINER_LIST_BEDIENPANEL()
    ,MODULCONTAINER_LIST_SEARCH()
    ,MASK_COMPONENT_MAP_COLLECTOR()
    ,MASK_DATAOBJECTS_COLLECTOR()
    ,MASK_LEADING_MASKKEY() 				//die RB_ComponentMap mit der fuehrenden maske
    ,MASK_LEADING_TABLE_ON_MASK()           //die _TAB, die die fuehrende ID liefert
    ,MASK_GRID()
    ,MOTHERPARAMSHASH()   					//falls in einer tochter auf der maske die paramhash der mutter gebraucht wird
    ,MOTHERKEY_LONGVAL()   					//falls alles innerhalb einer tochterkomponente auf einer mothermask spielt, ist das der wert der mother
    ,MOTHERKEY_FIELD()   					//falls alles innerhalb einer tochterkomponente auf einer mothermask spielt, das lookupfeld in der tochter
    ,MODULCONTAINER_MASK_SAVEBUTTON()				//die standard-buttons innerhalb einer maske
    ,MODULCONTAINER_MASK_CANCELBUTTON()
    ,MODULCONTAINER_MASK_SAVEANDREOPENBUTTON()
    ,PLACE_FOR_ANYTHING_ELSE()
    ,PLACE_FOR_STUCTURED_EXTENSION()         // hier wird eine innere Hashmap erzeugt und steht fuer enum-basierte erweiterungen zur verfuegung
    ,PLACE_FOR_UNSTUCTURED_EXTENSION()         // hier wird eine innere Hashmap erzeugt und steht fuer String-Key-erweiterungen zur verfuegung
    ;
	
    private RB_TransportHashMap_ENUM() {
    	
    }
    
    public String getParamName() {
        return this.name();
    }

}
