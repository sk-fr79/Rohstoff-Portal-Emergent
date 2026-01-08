package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK;

import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_mapCollector_4_FZ_MaskModulContainer;

public abstract class FZ_MASK_ContainerSegmentExtender {
	
	private int   										i_left_offset = -1;
	private FZ_MASK_ContainerSegmentExtenderButton  	extender_button = null;
	private IF_mapCollector_4_FZ_MaskModulContainer     mapCollector = null;
	private FZ_MASK_ContainerSegmentInLine  			segment = null;
	
	
	public FZ_MASK_ContainerSegmentExtender(IF_mapCollector_4_FZ_MaskModulContainer  p_mapCollector, int left_offset) throws myException {
		super();
		this.i_left_offset = left_offset;
		this.mapCollector = p_mapCollector;
	}

	public FZ_MASK_ContainerSegmentExtender(IF_mapCollector_4_FZ_MaskModulContainer  p_mapCollector) throws myException {
		super();
		this.i_left_offset = -1;
		this.mapCollector = p_mapCollector;
	}

	
	public abstract void open_extension() throws myException;
	
	
	public FZ_MASK_MaskModulContainer get_fz_mask_modulContainer() throws myException {
		return this.mapCollector.get_my_maskContainer();
	}


	/**
	 * bei der ersten rueckgabe wird der button erzeugt
	 * @return
	 * @throws myException 
	 */
	public FZ_MASK_ContainerSegmentExtenderButton get_extenderButton() throws myException {
		if (this.extender_button==null) {
			this.extender_button=new FZ_MASK_ContainerSegmentExtenderButton(this.get_fz_mask_modulContainer(), this.mapCollector, this.segment);
		}
		return this.extender_button;
	}
	
	/**
	 * wenn beim ersten aufruf der offset -1 ist, dann ausrechnen anhand der segmente links vom aktuellen segment
	 * @return
	 */
	public int get_left_offset() {
		if (i_left_offset==-1) {
			//dann offset ermitteln
			int i_count = 0;
			for (FZ_MASK_ContainerSegmentInLine seg: mapCollector.get_VectorOfSegments_in_maskline()) {
				if (seg != this.segment) {
					i_count+=seg.get_col_span();
				} else {
					break;
				}
			}

			this.i_left_offset=i_count;
		}
		return i_left_offset;
	}


	
	public FZ_MASK_ContainerSegmentInLine get_Segment() {
		return segment;
	}


	
	public void set_Segment(FZ_MASK_ContainerSegmentInLine segment) {
		this.segment = segment;
	}

	
	public Color col_Back() throws myException {
		return this.mapCollector.get_my_maskContainer().get_color_for_closed_block();
	}

	
	public Color col_High() throws myException {
		return this.mapCollector.get_my_maskContainer().get_color_for_expanded_block();
	}

	
	public E2_Grid mask_Grid() throws myException {
		return this.mapCollector.get_my_maskContainer().mask_grid();
	}
	
	
	
	
	
}
