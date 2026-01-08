/**
 * panter.gmbh.json.defClass
 * @author martin
 * @date 15.06.2020
 * 
 */
package panter.gmbh.json.defClass;

import java.util.Map;
import java.util.Vector;

/**
 * @author martin
 * @date 15.06.2020
 *
 */
public class JsonMaskTransportArtSetting {

	String name;
	Vector<String> enablelist;
	Vector<String> disablelist;
	Vector<String> disablelabellist;
	Vector<String> cleardisablelabellist;
	Vector<String> clearlist;
	Vector<String> cleardisablelist;
	
	Map<String, String> valuesmap;

	/**
	 * @author martin
	 * @date 15.06.2020
	 *
	 */
	public JsonMaskTransportArtSetting() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vector<String> getEnablelist() {
		return enablelist;
	}

	public void setEnablelist(Vector<String> enablelist) {
		this.enablelist = enablelist;
	}

	public Vector<String> getDisablelist() {
		return disablelist;
	}

	public void setDisablelist(Vector<String> disablelist) {
		this.disablelist = disablelist;
	}

	public Vector<String> getClearlist() {
		return clearlist;
	}

	public void setCleardisablelist(Vector<String> cleardisablelist) {
		this.cleardisablelist = cleardisablelist;
	}
	
	public Vector<String> getCleardisablelist() {
		return cleardisablelist;
	}

	public void setClearlist(Vector<String> clearlist) {
		this.clearlist = clearlist;
	}

	public Map<String, String> getValuesmap() {
		return valuesmap;
	}

	public void setValuesmap(Map<String, String> valuesmap) {
		this.valuesmap = valuesmap;
	}

	public Vector<String> getDisablelabellist() {
		return disablelabellist;
	}

	public void setDisablelabellist(Vector<String> disablelabellist) {
		this.disablelabellist = disablelabellist;
	}
	
	public Vector<String> getCleardisablelabellist() {
		return cleardisablelabellist;
	}

	public void setCleardisablelabellist(Vector<String> cleardisablelabellist) {
		this.cleardisablelabellist = cleardisablelabellist;
	}


}
