package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.LL;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.CM_Vektor;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.FZ_MASK_ContainerSegmentExtender;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.FZ_MASK_MaskModulContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.FZ_SEARCH_AVV_CODES;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.RB_lab_resizable;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.TS._TS_CONST;

public class LL_CM__column_extenders {
	
	public static class ExtDatum extends FZ_MASK_ContainerSegmentExtender {

		private  LL_CM__Collector cm_collector=null;

		public ExtDatum(FZ_MASK_MaskModulContainer fz_mask_modulContainer, LL_CM__Collector p_cm_collector) throws myException {
			super(p_cm_collector);
			this.cm_collector = p_cm_collector;
		}

		@Override
		public void open_extension() throws myException {

			Color col_closed = cm_collector.get_my_maskContainer().get_color_for_closed_block();
			Color col_open = cm_collector.get_my_maskContainer().get_color_for_expanded_block();

			RB_gld endLayoutOpen 	= new RB_gld()._al(E2_ALIGN.LEFT_MID)._ins(E2_INSETS.I(0))._span(3)._col(col_open);

			E2_Grid baseGrid = cm_collector.get_my_maskContainer().mask_grid();

			baseGrid._startLine(																new RB_gld()._col(col_closed)._span(this.get_left_offset()));
			baseGrid._endLine(new RB_lab_resizable()._tr("Planzeitraum (von - bis)")._i(),		new RB_gld()._col(col_open)._ins(1,8,1,1));

			baseGrid._startLine(																new RB_gld()._col(col_closed)._span(this.get_left_offset()));
			baseGrid._a(new RB_lab_resizable()._tr("Quelle")._lw()._i(),						new RB_gld()._ins(1)._col(col_open)._span(2)._al(E2_ALIGN.LEFT_MID));
			baseGrid._endLine(endLayoutOpen, new RB_gld()._ins(0,1,5,1)._col(col_open),cm_collector.get_vektor().getComp(BEWEGUNG_VEKTOR.l_datum_von),cm_collector.get_vektor().getComp(BEWEGUNG_VEKTOR.l_datum_bis));


			baseGrid._startLine(																new RB_gld()._col(col_closed)._span(this.get_left_offset()));
			baseGrid._a(new RB_lab_resizable()._tr("Ziel")._lw()._i(),							new RB_gld()._ins(1)._col(col_open)._span(2)._al(E2_ALIGN.LEFT_MID));
			baseGrid._endLine(endLayoutOpen, new RB_gld()._ins(0,1,5,1)._col(col_open),cm_collector.get_vektor().getComp(BEWEGUNG_VEKTOR.a_datum_von),cm_collector.get_vektor().getComp(BEWEGUNG_VEKTOR.a_datum_bis));
		
		}

	}

	
	public static class ExtMenge extends FZ_MASK_ContainerSegmentExtender {
		
		private  LL_CM__Collector cm_collector=null;

		public ExtMenge(FZ_MASK_MaskModulContainer fz_mask_modulContainer, LL_CM__Collector p_cm_collector) throws myException {
			super(p_cm_collector);
			this.cm_collector = p_cm_collector;
		}

		@Override
		public void open_extension() throws myException {
			
			Color col_closed = cm_collector.get_my_maskContainer().get_color_for_closed_block();
			Color col_open = cm_collector.get_my_maskContainer().get_color_for_expanded_block();
			
			E2_Grid baseGrid = cm_collector.get_my_maskContainer().mask_grid(); 
			
			baseGrid._startLine(new RB_gld()._col(col_closed)._span(this.get_left_offset()));
			baseGrid._a(new RB_lab_resizable()._tr("Ablademenge"),new RB_gld()._ins(1,3,1,1)._col(col_open)._span(4)._al(E2_ALIGN.LEFT_MID));
			baseGrid._endLine(cm_collector.get_ziel_atom().getComp(BEWEGUNG_ATOM.menge),new RB_gld()._col(col_open)._al(E2_ALIGN.LEFT_MID));	
		}
		
	}

	
	public static class ExtLadestation extends FZ_MASK_ContainerSegmentExtender {
		
		private  LL_CM__Collector cm_collector=null;
		
		public ExtLadestation(FZ_MASK_MaskModulContainer fz_mask_modulContainer, LL_CM__Collector p_cm_collector) throws myException {
			super(p_cm_collector);
			this.cm_collector = p_cm_collector;
		}

		@Override
		public void open_extension() throws myException {
			LL_CM_Station 		stat_start = 	cm_collector.get_atom_left__lager_left();
			
			Color col_closed = cm_collector.get_my_maskContainer().get_color_for_closed_block();
			Color col_open = cm_collector.get_my_maskContainer().get_color_for_expanded_block();
			
			E2_Grid baseGrid = cm_collector.get_my_maskContainer().mask_grid(); 
			
			baseGrid._startLine(new RB_gld()._col(col_closed)._span(this.get_left_offset()))
					._endLine(new RB_lab_resizable("Besitzer (Ladeseite)")._lw()._col_fore_dgrey(), new RB_gld()._span(4)._col(col_open)._ins(1));
			baseGrid._startLine(new RB_gld()._col(col_closed)._span(this.get_left_offset()))
					._endLine((Component)stat_start.getRbComponent(BEWEGUNG_STATION.id_adresse_besitzer), new RB_gld()._span(4)._col(col_open)._ins(1));
			
			
		}
		
	}

	
	
	
	public static class Ext_Station extends FZ_MASK_ContainerSegmentExtender {
		
		private  LL_CM__Collector cm_collector=null;
		
		private boolean bStartStation;
		
		public Ext_Station(FZ_MASK_MaskModulContainer fz_mask_modulContainer, LL_CM__Collector p_cm_collector, boolean true_start__or__false_ziel) throws myException {
			super(p_cm_collector);
			this.cm_collector = p_cm_collector;
			
			this.bStartStation = true_start__or__false_ziel;
		}

		@Override
		public void open_extension() throws myException {
			LL_CM_Station station = null;
			
			if(bStartStation){
				station = 	cm_collector.get_atom_left__lager_left();
			}else{
				station = 	cm_collector.get_atom_right__lager_ziel();
			}

			int i_lbl_span = 7;
			
			Color col_closed = cm_collector.get_my_maskContainer().get_color_for_closed_block();
			Color col_open = cm_collector.get_my_maskContainer().get_color_for_expanded_block();

			E2_Grid baseGrid = cm_collector.get_my_maskContainer().mask_grid(); 

			RB_gld endLayoutOpen 	= new RB_gld()._al(E2_ALIGN.LEFT_MID)._ins(E2_INSETS.I(2,0,2,0))._span(3)._col(col_open);
			RB_gld startLayoutOpen 	= new RB_gld()._col(col_closed)._span(this.get_left_offset());

			baseGrid._startLine(new RB_gld()._col(col_closed)._span(this.get_left_offset()));
			if(bStartStation){
				baseGrid._a(new RB_lab_resizable("Besitzer (Ladeseite)")._lw(), new RB_gld()._span(i_lbl_span)._col(col_open)._ins(1));
			}else{
				baseGrid
				._a(new RB_lab_resizable("Besitzer (Abladeseite)")._lw(), new RB_gld()._span(i_lbl_span)._col(col_open)._ins(1));
			}	
			baseGrid._endLine(station.getRbComponent(BEWEGUNG_STATION.id_adresse_besitzer), endLayoutOpen);

			baseGrid._startLine(startLayoutOpen)
			._a(new RB_lab_resizable("Name 1")._lw(), new RB_gld()._span(i_lbl_span)._col(col_open)._ins(1))
			._endLine(station.getRbComponent(BEWEGUNG_STATION.name1), endLayoutOpen)
			
			._startLine(new RB_gld()._col(col_closed)._span(this.get_left_offset()))
			._a(new RB_lab_resizable("Name 2")._lw(), new RB_gld()._span(i_lbl_span)._col(col_open)._ins(1))
			._endLine(station.getRbComponent(BEWEGUNG_STATION.name2), endLayoutOpen)
			
			._startLine(new RB_gld()._col(col_closed)._span(this.get_left_offset()))
			._a(new RB_lab_resizable("Name 3")._lw(), new RB_gld()._span(i_lbl_span)._col(col_open)._ins(1))
			._endLine(station.getRbComponent(BEWEGUNG_STATION.name3), endLayoutOpen)
			
			._startLine(new RB_gld()._col(col_closed)._span(this.get_left_offset()))
			._a(new RB_lab_resizable("Adresse")._lw(), new RB_gld()._span(i_lbl_span)._col(col_open)._ins(1))
			._endLine(endLayoutOpen,
					new RB_gld()._col(col_open)._al(E2_ALIGN.LEFT_MID)._ins(E2_INSETS.I(0,0,3,0)),
					station.getRbComponent(BEWEGUNG_STATION.strasse),station.getRbComponent(BEWEGUNG_STATION.hausnummer))
			
			._startLine(new RB_gld()._col(col_closed)._span(this.get_left_offset()))
			._a(new RB_lab_resizable("Land/PLZ/Ort")._lw(), new RB_gld()._span(i_lbl_span)._col(col_open)._ins(1))
			._endLine(endLayoutOpen,
					new RB_gld()._col(col_open)._al(E2_ALIGN.LEFT_MID)._ins(E2_INSETS.I(0,0,3,0)),
					station.getRbComponent(BEWEGUNG_STATION.laendercode),
					station.getRbComponent(BEWEGUNG_STATION.plz),
					station.getRbComponent(BEWEGUNG_STATION.ort))
			
			._startLine(new RB_gld()._col(col_closed)._span(this.get_left_offset()))
			._a(new RB_lab_resizable("Öffnungzeiten")._lw(), new RB_gld()._span(i_lbl_span)._col(col_open)._ins(1))
			._endLine(station.getRbComponent(BEWEGUNG_STATION.oeffnungszeiten), endLayoutOpen)
			
			._startLine(new RB_gld()._col(col_closed)._span(this.get_left_offset()))
			._a(new RB_lab_resizable("Bestellnummer")._lw(), new RB_gld()._span(i_lbl_span)._col(col_open)._ins(1))
			._endLine(station.getRbComponent(BEWEGUNG_STATION.bestellnummer), endLayoutOpen)
			
			._startLine(new RB_gld()._col(col_closed)._span(this.get_left_offset()))
			._a(new RB_lab_resizable("Telefon")._lw(), new RB_gld()._span(i_lbl_span)._col(col_open)._ins(1))
			._endLine(station.getRbComponent(BEWEGUNG_STATION.telefon), endLayoutOpen)
			
			._startLine(new RB_gld()._col(col_closed)._span(this.get_left_offset()))
			._a(new RB_lab_resizable("Faxnummer")._lw(), new RB_gld()._span(i_lbl_span)._col(col_open)._ins(1))
			._endLine(station.getRbComponent(BEWEGUNG_STATION.fax), endLayoutOpen);

		}
		
	}

	
	
	
	
	public static class ExtSorte extends FZ_MASK_ContainerSegmentExtender {
		
		private  LL_CM__Collector cm_collector=null;
		
		public ExtSorte(FZ_MASK_MaskModulContainer fz_mask_modulContainer, LL_CM__Collector p_cm_collector) throws myException {
			super(p_cm_collector);
			this.cm_collector = p_cm_collector;
		}

		@Override
		public void open_extension() throws myException {
			LL_CM_Atom   		atom_ziel = 	cm_collector.get_ziel_atom();
			CM_Vektor		vektor = 		cm_collector.get_vektor();
			
			int i_lbl_span = 7;
			
			Color col_closed = cm_collector.get_my_maskContainer().get_color_for_closed_block();
			Color col_open = cm_collector.get_my_maskContainer().get_color_for_expanded_block();

			E2_Grid baseGrid = cm_collector.get_my_maskContainer().mask_grid(); 

			baseGrid._startLine(new RB_gld()._col(col_closed)._span(this.get_left_offset()))
			._a(new RB_lab_resizable("Id Artikel")._lw(), new RB_gld()._span(i_lbl_span)._col(col_open)._ins(1))
			._endLine(atom_ziel.getRbComponent(BEWEGUNG_ATOM.id_artikel),new RB_gld()._span(5)._col(col_open)._ins(1));
			
			baseGrid._startLine(new RB_gld()._col(col_closed)._span(this.get_left_offset()))
			._a(new RB_lab_resizable("ANR1-2")._lw(), new RB_gld()._span(i_lbl_span)._col(col_open)._ins(1))
			._endLine(atom_ziel.getRbComponent(_TS_CONST._TS_KEYS.ANR12.k()),new RB_gld()._span(2)._col(col_open)._ins(1)._al(E2_ALIGN.LEFT_MID));

			baseGrid._startLine(new RB_gld()._col(col_closed)._span(this.get_left_offset()))
			._a(new RB_lab_resizable("Art bez 1")._lw(), new RB_gld()._span(i_lbl_span)._col(col_open)._ins(1))
			._endLine(atom_ziel.getRbComponent(BEWEGUNG_ATOM.artbez1),new RB_gld()._span(5)._col(col_open)._ins(1));
			
			baseGrid._startLine(new RB_gld()._col(col_closed)._span(this.get_left_offset()))
			._a(new RB_lab_resizable("Art bez 2")._lw(), new RB_gld()._span(i_lbl_span)._col(col_open)._ins(1))
			._endLine(atom_ziel.getRbComponent(BEWEGUNG_ATOM.artbez2),new RB_gld()._span(5)._col(col_open)._ins(1));
			
			baseGrid._startLine(new RB_gld()._col(col_closed)._span(this.get_left_offset()))
			._a(new RB_lab_resizable("AVV-Code")._lw(), new RB_gld()._span(i_lbl_span)._col(col_open)._ins(1))
			._endLine(vektor.getRbComponent(BEWEGUNG_VEKTOR.id_eak_code),new RB_gld()._span(5)._col(col_open)._ins(1))
			;
			
			
			((FZ_SEARCH_AVV_CODES)vektor.getRbComponent(BEWEGUNG_VEKTOR.id_eak_code)).set_layoutdata_color_to_components(this.col_High());

		}
		
	}

}
