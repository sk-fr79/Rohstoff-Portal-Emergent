/**
 * panter.gmbh.Echo2.components
 * @author martin
 * @date 02.01.2020
 * 
 */
package panter.gmbh.Echo2.components;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 02.01.2020
 *
 */
public class E2_GridCell  {

	/**
	 * @author martin
	 * @date 02.01.2020
	 *
	 */
	
	private Object   			component = null;
	
	private Integer 			zeile = null;
	private Integer 			spalte = null;
	private Integer 			spaltenBreite = null;
	private Integer 			zeilenHoehe = null;
	private GridLayoutData 		gld = null;
	
	
	/**
	 * 
	 * @author martin
	 * @date 02.01.2020
	 *
	 * @param zeile
	 * @param spalte
	 * @param component
	 * @param spaltenBreite (nullable)
	 * @param zeilenHoehe	(nullable)
	 * @param gld (nullable)		
	 **/
	public E2_GridCell(Integer zeile, Integer spalte, Object component, Integer zeilenHoehe, Integer spaltenBreite, GridLayoutData gld) throws myException {
		super();
		this.component = component;
		
		if (!(component instanceof Component || component instanceof IF_ConvertibleToComponent)) {
			throw new myException("Only types Component or IF_ConvertibleToComponent are allowed in this context <014d483a-2d75-11ea-978f-2e728ce88125>");
		}
		
		
		this.zeile = zeile;
		this.spalte = spalte;
		this.spaltenBreite = spaltenBreite;
		this.zeilenHoehe = zeilenHoehe;
		this.gld = gld;
	}


	public Integer getZeile() {
		return zeile;
	}

	public Integer getSpalte() {
		return spalte;
	}

	public Integer getSpaltenBreite() {
		return spaltenBreite;
	}

	public Integer getZeilenHoehe() {
		return zeilenHoehe;
	}

	public GridLayoutData getGld() {
		return gld;
	}



	public int getColSpan() {
		if (gld != null) {
			if (gld.getColumnSpan()>0) {
				return gld.getColumnSpan();
			}
		}
		return 1;
	}



	public int getRowSpan() {
		if (gld != null) {
			if (gld.getRowSpan()>0) {
				return gld.getRowSpan();
			}
		}
		return 1;
	} 
	
	public Component getComponent() {
		if (this.component instanceof Component) {
			return (Component)this.component;
		} else {
			return ((IF_ConvertibleToComponent)this.component).component();
		}
	}

}


