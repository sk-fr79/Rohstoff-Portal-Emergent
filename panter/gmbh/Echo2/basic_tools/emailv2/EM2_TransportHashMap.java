/**
 * panter.gmbh.Echo2.basic_tools.emailv2
 * @author martin
 * @date 11.02.2021
 * 
 */
package panter.gmbh.Echo2.basic_tools.emailv2;

import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.basics4project.DB_ENUMS._TAB;

/**
 * @author martin
 * @date 11.02.2021
 *
 */
public class EM2_TransportHashMap extends RB_TransportHashMap {

	//keys fuer transporthashmap
	public static String tableIdKey = 				"tableIdKey";
	public static String tableKey  = 				"tableKey";
	
	public static String allowEditKey = 			"allowEditKey";
	public static String allowDeleteKey = 			"allowDeleteKey";
	public static String allowCopyKey = 			"allowCopyKey";
	public static String allowNewKey = 				"allowNewKey";
	public static String allowSendButton = 			"allowSendButton";
	public static String allowAddTarget = 			"allowAddTarget";
	
	
	
	/**
	 * @author martin
	 * @date 11.02.2021
	 *
	 */
	public EM2_TransportHashMap() {
		super();
	}
    
	public EM2_TransportHashMap _setTable(_TAB tab) {
		this._putSB(EM2_TransportHashMap.tableKey,tab);
		
		return this;
	}
	
	
	public EM2_TransportHashMap _setTableId(Long id) {
		this._putSB(EM2_TransportHashMap.tableIdKey,id);
		
		return this;
	}
	
	public EM2_TransportHashMap _setallowEdit(Boolean allow) {
		this._putSB(EM2_TransportHashMap.allowEditKey,allow);
		
		return this;
	}
	
	public EM2_TransportHashMap _setAllowDelete(Boolean allow) {
		this._putSB(EM2_TransportHashMap.allowDeleteKey,allow);
		
		return this;
	}
	
	public EM2_TransportHashMap _setAllowCopy(Boolean allow) {
		this._putSB(EM2_TransportHashMap.allowCopyKey,allow);
		
		return this;
	}
	
	public EM2_TransportHashMap _setAllowNew(Boolean allow) {
		this._putSB(EM2_TransportHashMap.allowNewKey,allow);
		
		return this;
	}
	
	public EM2_TransportHashMap _setAllowSendButton(Boolean allow) {
		this._putSB(EM2_TransportHashMap.allowSendButton,allow);
		
		return this;
	}
	
	public EM2_TransportHashMap _setAllowAddTarget(Boolean allow) {
		this._putSB(EM2_TransportHashMap.allowAddTarget,allow);
		
		return this;
	}
	
	
	
	public _TAB getTable() {
		return (_TAB) this.getSB(EM2_TransportHashMap.tableKey);
	}
	public Long getTableId() {
		return (Long)this.getSB(EM2_TransportHashMap.tableIdKey);
	}
	
	
	public Boolean getAllowEdit() {
		return getAllow(EM2_TransportHashMap.allowEditKey);
	}
	public Boolean getAllowDelete() {
		return getAllow(EM2_TransportHashMap.allowDeleteKey);
	}
	public Boolean getAllowCopy() {
		return getAllow(EM2_TransportHashMap.allowCopyKey);
	}
	public Boolean getAllowNew() {
		return getAllow(EM2_TransportHashMap.allowNewKey);
	}
	
	public Boolean getAllowSendButton() {
		return getAllow(EM2_TransportHashMap.allowSendButton);
	}
	
	public Boolean getAllowAddTarget() {
		return getAllow(EM2_TransportHashMap.allowAddTarget);
	}
	
	private boolean getAllow(String key) {
		if (this.getSB(key)==null) {
			return false;
		} else {
			return (boolean)this.getSB(key);
		}
	}
	
	
	
	
}
