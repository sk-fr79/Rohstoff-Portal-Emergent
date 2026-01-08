/**
 * panter.gmbh.Echo2.components
 * @author martin
 * @date 02.01.2020
 * 
 */
package panter.gmbh.Echo2.components;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 02.01.2020
 *
 */
public class E2_GridDefinition extends VEK<E2_GridCell> {

	
	private Integer 				baseColumnWidth = null;
	private Integer 				baseRowHeight = null;

	private  MyE2IF__Component      placeHolderComponent = null;   //must implement interface If_Copy 
	private  RB_gld                 paceholderLayout = null;
	
	
	private VEK<Integer> 		    colWidths = new VEK<Integer>();
	
	
	/**
	 * @author martin
	 * @date 02.01.2020
	 *
	 */
	public E2_GridDefinition() {
	}

	
	public E2_GridDefinition _add(int zeile, int spalte, Object component) throws myException {
		add( zeile,  spalte, component, null,null,null);
		return this;
	}
	
	
	public E2_GridDefinition _add(int zeile, int spalte, Object component, GridLayoutData gld) throws myException {
		add( zeile,  spalte, component, null,null,gld);
		return this;
	}
	
	public E2_GridDefinition _add(int zeile, int spalte, Object component, Integer zeilenHoehe, Integer spaltenBreite, GridLayoutData gld) throws myException {
		add( zeile,  spalte, component, zeilenHoehe,spaltenBreite,gld);
		return this;
	}
	

	
	//spezielles add fuer immer ein paar beschriftung/component
	public E2_GridDefinition _addPair(int zeile, int startSpalte, Object text, Object component, GridLayoutData gldText, GridLayoutData gldComp) throws myException {
		
		if (text instanceof String) {
			this._add(zeile, startSpalte, 	new RB_lab()._t((String)text), 		null, null, gldText);
		} else {
			this._add(zeile, startSpalte, 	text, 		null, null, gldText);
		}
		this._add(zeile, startSpalte+1, component,	null, null, gldComp);
		return this;
	}


	
	
	
	private void add(int zeile, int spalte, Object component, Integer zeilenHoehe, Integer spaltenBreite,GridLayoutData gld)  throws myException {
		E2_GridCell cell = new E2_GridCell(zeile, spalte, component, zeilenHoehe, spaltenBreite, gld);
		
		if (this.isPosible(cell)) {
			this._a(cell);
		} else {
			throw new myException("Adding cell: coordinates (row, col):  "+zeile+","+spalte+" is already taken !");
		}
	}
	
	
	public boolean isPosible(E2_GridCell cell) {
		boolean free = true;
        
		for (int zeile=cell.getZeile(); zeile<cell.getZeile()+cell.getRowSpan();zeile++) {
			for (int spalte=cell.getSpalte(); spalte<cell.getSpalte()+cell.getColSpan();spalte++) {
				if (!isFree(zeile, spalte)) {
					free=false;
					break;
				}
			}
		}
		return free;
	}
	
	
	
	public boolean isFree(int zeile, int spalte) {
		boolean free = true;
		
		for (E2_GridCell cell: this) {
			
			//koordinaten, die besetzt sind
			for (int i=0; i<cell.getColSpan();i++) {
				for (int k=0; k<cell.getRowSpan();k++) {
					if ((cell.getZeile()+k)==zeile &&  (cell.getSpalte()+i)==spalte ) {
						free = false;
						break;
					}
				}
			}
		}
		
		return free;
		
	}
	
	
	
	/**
	 * 
	 * @author martin
	 * @date 02.01.2020
	 *
	 * @return  wert fuer die .setSize() - uebergabe

	 */
	public int getColNumber() {
		int cols=0;
		
		for (E2_GridCell cell: this) {
			if (cell.getSpalte()+(cell.getColSpan())>cols) {
				cols = cell.getSpalte()+(cell.getColSpan());
			}
		}
		
		return cols;
	}
	
	
	public int getRowNumber() {
		int cols=0;
		
		for (E2_GridCell cell: this) {
			if (cell.getZeile()+(cell.getRowSpan())>cols) {
				cols = cell.getZeile()+(cell.getRowSpan());
			}
		}
		
		return cols;
	}
	
	
	
	
	public E2_Grid applyTo(E2_Grid grid) throws myException {
		
		//jetzt die zellen iterieren und allen, die noch nichts enthalten, einen label zufuegen
		E2_GridDefinition  fehlende = new E2_GridDefinition();
		
		int maxSpalte = this.getColNumber();
		int maxZeile = this.getRowNumber();
		
		MyE2IF__Component placeHolderComp = this.getPlaceHolderComponent();
		try {
			if (placeHolderComp!=null && placeHolderComp instanceof E2_IF_Copy) {
				placeHolderComp = (MyE2IF__Component) ((E2_IF_Copy) placeHolderComp).get_Copy(null);
				
				if (placeHolderComp==null) {
					placeHolderComp = new RB_lab();
				}
			} else {
				placeHolderComp = new RB_lab();
			}
		} catch (Exception e) {
			placeHolderComp = new RB_lab();
			e.printStackTrace();
		}
		
		for (int spalte=0;spalte<maxSpalte;spalte++) {
			for (int zeile=0;zeile<maxZeile;zeile++) {
				if (isFree(zeile,spalte)) {
					if (this.getPaceholderLayout()==null) {
						fehlende._add(zeile, spalte,  (MyE2IF__Component)((E2_IF_Copy) placeHolderComp).get_Copy(null));
					} else {
						fehlende._add(zeile, spalte,  (MyE2IF__Component)((E2_IF_Copy) placeHolderComp).get_Copy(null), this.getPaceholderLayout()._c());
					}
				}
			}
		}
		this.addAll(fehlende);

		
		
		//jetzt sollte das ganze grid gefuellt sein
		this.sort((e1,e2)-> {
			if (e1.getZeile()<e2.getZeile()) {
				return -1;
			} else if (e1.getZeile()==e2.getZeile()) {
				if ((e1.getSpalte()<e2.getSpalte())) {
					return -1;
				} else if (e1.getSpalte()==e2.getSpalte()) {
					return 0;
				} else {
					return 1;
				}
			} else {
				return 1;
			}
		});

		if (grid == null) {
			grid = new E2_Grid();
		}
		
		grid.removeAll();
		
		grid.setSize(this.getColNumber());

		for (E2_GridCell cell: this) {
			grid._a(cell.getComponent(),cell.getGld());
			
			if (cell.getZeilenHoehe()!=null) {
				grid.setRowHeight(cell.getZeile(), new Extent(cell.getZeilenHoehe()));
			} else if (this.getBaseRowHeight()!=null) {
				grid.setRowHeight(cell.getZeile(), new Extent(this.getBaseRowHeight()));
			}
			
			if (cell.getSpaltenBreite()!=null) {
				grid.setColumnWidth(cell.getSpalte(), new Extent(cell.getSpaltenBreite()));
			} else if (this.colWidths.size()>cell.getSpalte() && this.colWidths.get(cell.getSpalte())!=null)	 {
				grid.setColumnWidth(cell.getSpalte(), new Extent(this.colWidths.get(cell.getSpalte())));
			} else if (this.getBaseColumnWidth()!=null) {
				grid.setColumnWidth(cell.getSpalte(), new Extent(this.getBaseColumnWidth()));
			}
			
			
		}
		
		return grid;
	}


	public Integer getBaseColumnWidth() {
		return baseColumnWidth;
	}


	public E2_GridDefinition _setBaseColumnWidth(Integer baseColumnWidth) {
		this.baseColumnWidth = baseColumnWidth;
		return this;
	}


	public Integer getBaseRowHeight() {
		return baseRowHeight;
	}


	public E2_GridDefinition _setBaseRowHeight(Integer baseRowHeight) {
		this.baseRowHeight = baseRowHeight;
		return this;
	}


	public MyE2IF__Component getPlaceHolderComponent() {
		return placeHolderComponent;
	}


	public E2_GridDefinition _setPlaceHolderComponent(MyE2IF__Component placeHolderComponent) {
		this.placeHolderComponent = placeHolderComponent;
		return this;
	}


	public RB_gld getPaceholderLayout() {
		return paceholderLayout;
	}


	public E2_GridDefinition _setPaceholderLayout(RB_gld paceholderLayout) {
		this.paceholderLayout = paceholderLayout;
		return this;
	}
	



	public VEK<Integer> getColWidths() {
		return colWidths;
	}
	
	public E2_GridDefinition _addColW(Integer breit) {
		colWidths._a(breit);
		return this;
	}
}
