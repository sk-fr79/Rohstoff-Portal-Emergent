package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES;


import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLight;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.FZ_Grid_4_anzeigeInternBuchung;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.FZ_MASK_ContainerSegmentInLine;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.FZ_MASK_MaskModulContainer;

public interface IF_mapCollector_4_FZ_MaskModulContainer_v2 extends IF_mapCollector_4_FZ_MaskModulContainer {




	/**
	 * rendern der komponenten
	 * @throws myException
	 */
	public default 	void	render_body_line() throws myException {
		
		FZ_MASK_MaskModulContainer  cont = this.get_my_maskContainer();
		E2_Grid  					grid = cont.mask_grid();

		Color basic = cont.get_color_for_closed_block();
		Color high = cont.get_color_for_expanded_block();

		//		for (FZ_MASK_ContainerSegmentInLine segment: this.get_VectorOfSegments_in_maskline()) {
		for (int s=0;s< this.get_VectorOfSegments_in_maskline().size();s++) {
			FZ_MASK_ContainerSegmentInLine segment = this.get_VectorOfSegments_in_maskline().get(s);

			Color  col_actual = basic;
			if (this.get_actual_opened_segmentObject()!=null && segment==this.get_actual_opened_segmentObject()) {
				col_actual=high;
			}

			RB_gld ld = segment.get_gld_data()._c();
			//falls ein GridayoutData an der komponente klebt, das uebernehmen
			if (segment.get_dataComponent().getLayoutData()!=null && segment.get_dataComponent().getLayoutData() instanceof RB_gld) {
				ld = ((RB_gld)segment.get_headerComponent().getLayoutData())._c();
			}
			ld._col(col_actual)._span(segment.get_col_span());
			grid._a(segment.get_dataComponent(), ld);

			//falls die zeile nicht vollstaendig, zeile abschliessen
			if (s==this.get_VectorOfSegments_in_maskline().size()-1) {
				grid._endLine(ld);
			}
		}

		//		//zeile abschliessen (falls spalten ungefuellt sind
		//		grid._endLine(gl)

		//jetzt nachsehen, ob evtl. weitere zeilen in der struktur hinterlegt sind
		int iAnzahlZusatz = this.sizeAddons();
		for (int i=0;i<iAnzahlZusatz;i++) {
			//			for (FZ_MASK_ContainerSegmentInLine segment: this.get_VectorOfSegments_in_maskline()) {
			for (int s=0;s< this.get_VectorOfSegments_in_maskline().size();s++) {
				FZ_MASK_ContainerSegmentInLine segment = this.get_VectorOfSegments_in_maskline().get(s);

				Color  col_actual = basic;
				if (this.get_actual_opened_segmentObject()!=null && segment==this.get_actual_opened_segmentObject()) {
					col_actual=high;
				}

				RB_gld ld = segment.get_gld_data()._c();
				//falls ein GridayoutData an der komponente klebt, das uebernehmen
				if (segment.get_dataComponent().getLayoutData()!=null && segment.get_dataComponent().getLayoutData() instanceof RB_gld) {
					ld = ((RB_gld)segment.get_headerComponent().getLayoutData())._c();
				}

				Component comAddon = segment.get_addOnLine(i);
				if (comAddon==null) {
					comAddon=new RB_lab("");
				}

				ld._col(col_actual)._span(segment.get_col_span());
				grid._a(comAddon, ld);

			}
		}



		this.fill_status_grid();
		this.fill_button_grid();

		if (this.get_actual_opened_segmentObject()!=null) {
			this.get_actual_opened_segmentObject().get_extender().open_extension();
		}

		
		//TODO modification here
		//ganz unter der zeile die hidden-infos
		if (this.get_my_maskContainer().is_hidden_positions_to_show()) {
			
			Color col_back = 		this.get_my_maskContainer().get_color_for_closed_block();
			
			Object comp_hidden = new FZ_Grid_4_anzeigeInternBuchung(this);
			
			if (comp_hidden!=null) {
				this.get_my_maskContainer().mask_grid()
				._startLine(				new RB_gld()._col(col_back)._span(this.insets_for_hidden_addon_block()))
				._endLine(	comp_hidden, 	new RB_gld()._col(new E2_ColorLLight())._span(12));
				}
		}
	}

}
