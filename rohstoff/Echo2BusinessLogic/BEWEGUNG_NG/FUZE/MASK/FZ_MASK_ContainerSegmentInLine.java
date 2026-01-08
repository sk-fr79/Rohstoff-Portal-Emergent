package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK;

import java.util.HashMap;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_mapCollector_4_FZ_MaskModulContainer;

/**
 * jeder RB_ComponentMapCollector fuer diese maske besitzt eine Zeile innerhalb des FZ_MASK_ModulContainers,
 * jedes Segment in der Zeile wird durch eine Instanz dieses Objekts repraesentiert.
 * Dann wird in jedem  RB_ComponentMapCollector eine reihe von segmenten registriert und automatisch angezeigt
 * 
 * @author martin
 *
 */
public class FZ_MASK_ContainerSegmentInLine {
   
	private IF_mapCollector_4_FZ_MaskModulContainer	my_rb_componentMapCollector = null;

	private Component   							headerComponent = null;    		// kommt in die titlezeile
	private Component   							dataComponent = null;	 		// enthält die eigentlichen eingabe-daten-komponente
	
	//weitere komponentenzeileb innerhalb der gleichen "line"
	private HashMap<Integer,Component> 				hm_addonLines = new HashMap();  //                             
	
	private FZ_MASK_ContainerSegmentExtender 		my_extender = null;				// falls unterhalb des segments eine extension ausklappen soll, dann muss dieser extender gesetzt sein

	private int  									i_col_span = 1;					// column-span
	
	private RB_gld					  				gld_head_main = new RB_gld();	//gridlayoutdata fuer die headercomponente (innerhalb des umfassenden 1x1 oder 1x2-grids (mit ausklapper)
	private RB_gld					  				gld_data = 		new RB_gld();	//gridlayoutdata fuer die dataComponente (innerhalb des umfassenden 1x1 grids)
	
	public FZ_MASK_ContainerSegmentInLine(	IF_mapCollector_4_FZ_MaskModulContainer 	rb_componentMapCollector, 
											Component 									p_headerComponent, 
											Component 									p_dataComponent, 
											int                                         col_span) {
		this(rb_componentMapCollector,p_headerComponent,p_dataComponent,col_span,null,null);
	}


	public FZ_MASK_ContainerSegmentInLine(	IF_mapCollector_4_FZ_MaskModulContainer 	rb_componentMapCollector, 
											Component 									p_headerComponent, 
											Component 									p_dataComponent, 
											int                                         col_span,
											RB_gld    									rb_gld_header,
											RB_gld    									rb_gld_data) {
		super();
		this.my_rb_componentMapCollector = 	rb_componentMapCollector;
		this.headerComponent = 				p_headerComponent;
		this.dataComponent = 				p_dataComponent;
		this.i_col_span = 					col_span;
		if (rb_gld_header!=null) {
			this.gld_head_main=rb_gld_header;
		}
		
		if (rb_gld_data!=null) {
			this.gld_data=rb_gld_data;
		}
	}
	
	
	public IF_mapCollector_4_FZ_MaskModulContainer get_rb_componentMapCollector() {
		return my_rb_componentMapCollector;
	}


	
	public Component get_headerComponent() {
		return headerComponent;
	}


	
	public Component get_dataComponent() {
	
		return dataComponent;
	}


	
	public FZ_MASK_ContainerSegmentExtender get_extender() {
		return my_extender;
	}



	/**
	 * angabe, ueber wieviele basisgrid-spalten sich die komponente erstreckt
	 * @return
	 */
	public int get_col_span() {
		return i_col_span;
	}


	
	public void set_extender(FZ_MASK_ContainerSegmentExtender my_extender) {
		this.my_extender = my_extender;
	}


	
	public RB_gld get_gld_head_main() {
		return gld_head_main;
	}


	
	public void set_gld_head_main(RB_gld gld_head_main) {
		this.gld_head_main = gld_head_main;
	}


	
	public RB_gld get_gld_data() {
		return gld_data;
	}


	
	public void set_gld_data(RB_gld gld_data) {
		this.gld_data = gld_data;
	}

	
	/**
	 * 
	 * @return die zahl der zeilen aus sicht dieses segments
	 */
	public int sizeAddons() {
		int iSize = 0;
		for (Integer i: this.hm_addonLines.keySet()) {
			if (i>=iSize) {
				iSize=iSize+1;
			}
		}
		return iSize;
	}
	

	/**
	 * 
	 * @param iLine (beginnt bei 0)
	 * @param c
	 */
	public FZ_MASK_ContainerSegmentInLine _addLine(int iLine, Component c) throws myException {
		if (iLine<0) {
			throw new myException(this,"addon-Segments are starting at 0 !");
		}
		if (c!=null) {
			this.hm_addonLines.put(iLine, c);
		}
		return this;
	}
	
	
	/**
	 * 
	 * @param i 
	 * @return Component in this segment in line i (null, when nothing in)
	 */
	public Component  get_addOnLine(int i) {
		return this.hm_addonLines.get(i);
	}
	
}
	