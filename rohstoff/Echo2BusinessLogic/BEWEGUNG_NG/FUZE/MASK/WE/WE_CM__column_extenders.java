package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WE;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
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
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS.FZ__CONST;

public class WE_CM__column_extenders {
	
	public static class ExtDatum extends FZ_MASK_ContainerSegmentExtender {
		
		private  WE_CM__Collector cm_collector=null;
		
		public ExtDatum(FZ_MASK_MaskModulContainer fz_mask_modulContainer, WE_CM__Collector p_cm_collector) throws myException {
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


	public static class ExtStartlager extends FZ_MASK_ContainerSegmentExtender {
		
		private  WE_CM__Collector cm_collector=null;
		
		private boolean bStartStation = false;
		
		public ExtStartlager(FZ_MASK_MaskModulContainer fz_mask_modulContainer, WE_CM__Collector p_cm_collector, boolean true_start__or__false_ziel) throws myException {
			super(p_cm_collector);
			this.cm_collector = p_cm_collector;
			this.bStartStation = true_start__or__false_ziel;
		}

		@Override
		public void open_extension() throws myException {
			
			int i_lbl_span = 7;
			
			Color col_closed = cm_collector.get_my_maskContainer().get_color_for_closed_block();
			Color col_open = cm_collector.get_my_maskContainer().get_color_for_expanded_block();
			RB_gld endLayoutOpen 	= new RB_gld()._al(E2_ALIGN.LEFT_MID)._ins(E2_INSETS.I(2,0,2,0))._span(3)._col(col_open);
			RB_gld startLayoutOpen 	= new RB_gld()._col(col_closed)._span(this.get_left_offset());
			
			
			E2_Grid mg = cm_collector.get_my_maskContainer().mask_grid();

			WE_CM_Station 		station = 	null;

			if(bStartStation){
				station = 	cm_collector.get_startStation();
			}else{
				station = 	cm_collector.get_zielStation();
			}
			
			mg._startLine(new RB_gld()._col(col_closed)._span(this.get_left_offset()));
			if(bStartStation){
				mg._a(new RB_lab_resizable("Besitzer (Ladeseite)")._lw(), new RB_gld()._span(i_lbl_span)._col(col_open)._ins(2));
			}else{
				mg
				._a(new RB_lab_resizable("Besitzer (Abladeseite)")._lw(), new RB_gld()._span(i_lbl_span)._col(col_open)._ins(2));
			}	
			
			mg._endLine(station.getRbComponent(BEWEGUNG_STATION.id_adresse_besitzer), endLayoutOpen);
			
			mg._startLine(startLayoutOpen)
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
			._a(new RB_lab_resizable("÷ffnungzeiten")._lw(), new RB_gld()._span(i_lbl_span)._col(col_open)._ins(1))
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

	
	
	public static class ExtZiellager extends FZ_MASK_ContainerSegmentExtender {
		
		private  WE_CM__Collector cm_collector=null;
		
		public ExtZiellager(FZ_MASK_MaskModulContainer fz_mask_modulContainer, WE_CM__Collector p_cm_collector) throws myException {
			super(p_cm_collector);
			this.cm_collector = p_cm_collector;
		}

		@Override
		public void open_extension() throws myException {
			
			Color col_closed = cm_collector.get_my_maskContainer().get_color_for_closed_block();
			Color col_open = cm_collector.get_my_maskContainer().get_color_for_expanded_block();
			
			E2_Grid mg = cm_collector.get_my_maskContainer().mask_grid();

			WE_CM_Station 		stat_ziel = 	cm_collector.get_zielStation();
			

			RB_gld gl_backGround = new RB_gld()._span(this.get_left_offset())._col(col_closed);
			
			RB_gld glstd = new RB_gld()._ins(E2_INSETS.I(2,2,2,2))._col(col_open)._span(2);
//			RB_gld gl_highLight = new RB_gld()._ins(E2_INSETS.I(2,2,2,2))._col(col_open);
			
			WE_CO_label_show_main_adress showMainAdress = (WE_CO_label_show_main_adress)stat_ziel.getRbComponent(BEWEGUNG_STATION.id_adresse_basis);
	
			mg	._startLine(gl_backGround)._a(new RB_lab_resizable("Besitzer"),		glstd	)
				._endLine(stat_ziel.getRbComponent(BEWEGUNG_STATION.id_adresse_besitzer),glstd);
			
			mg	._startLine(gl_backGround)._a(new RB_lab_resizable("Firma"),		glstd		)
				._endLine((Component)showMainAdress,glstd);
			
			mg	._startLine(gl_backGround)._a(new RB_lab_resizable("Name 1"),			glstd)
				._endLine(stat_ziel.getRbComponent(BEWEGUNG_STATION.name1),				glstd);
			
			mg	._startLine(gl_backGround)._a(new RB_lab_resizable("Name 2"),			glstd)
				._endLine(stat_ziel.getRbComponent(BEWEGUNG_STATION.name2),				glstd);
			
			mg	._startLine(gl_backGround)._a(new RB_lab_resizable("Name 3"),			glstd)
				._endLine(stat_ziel.getRbComponent(BEWEGUNG_STATION.name3),				glstd);
			
			mg	._startLine(gl_backGround)._a(new RB_lab_resizable("Straﬂe/HNr"),		glstd)
				._endLine(glstd,new RB_gld()._ins(E2_INSETS.I(0,0,3,0))
					,stat_ziel.getRbComponent(BEWEGUNG_STATION.strasse)
					,stat_ziel.getRbComponent(BEWEGUNG_STATION.hausnummer));
			
			mg	._startLine(gl_backGround)._a(new RB_lab_resizable("Land/PLZ"),			glstd)
				._endLine(glstd,new RB_gld()._ins(E2_INSETS.I(0,0,3,0))
					,stat_ziel.getRbComponent(BEWEGUNG_STATION.laendercode)
					,stat_ziel.getRbComponent(BEWEGUNG_STATION.plz)
					,stat_ziel.getRbComponent(BEWEGUNG_STATION.ort));
			
			mg	._startLine(gl_backGround)._a(new RB_lab_resizable("Ort (Zusatz)"),		glstd)
				._endLine(stat_ziel.getRbComponent(BEWEGUNG_STATION.ortzusatz),			glstd);
			
			mg	._startLine(gl_backGround)._a(new RB_lab_resizable("÷ffnungszeiten"),	glstd)
				._endLine(stat_ziel.getRbComponent(BEWEGUNG_STATION.oeffnungszeiten),		glstd);
			
			mg	._startLine(gl_backGround)._a(new RB_lab_resizable("Bestellnummer"),		glstd)
				._endLine(stat_ziel.getRbComponent(BEWEGUNG_STATION.bestellnummer),		glstd);
			
			mg	._startLine(gl_backGround)._a(new RB_lab_resizable("Telefon"),			glstd)
				._endLine(stat_ziel.getRbComponent(BEWEGUNG_STATION.telefon),				glstd);
			
			mg	._startLine(gl_backGround)._a(new RB_lab_resizable("Telefax"),			glstd)
				._endLine(stat_ziel.getRbComponent(BEWEGUNG_STATION.fax),					glstd);
			
			mg	._startLine(gl_backGround)._a(new RB_lab_resizable("Wiegekarte (ext)"),	glstd)
				._endLine(stat_ziel.getRbComponent(BEWEGUNG_STATION.wiegekartenkenner),	glstd);
			
			mg	._startLine(gl_backGround)._a(new RB_lab_resizable("Wiegekarte (int)"),	glstd)
				._endLine(stat_ziel.getRbComponent(BEWEGUNG_STATION.id_wiegekarte),		glstd);
	
		}
		
	}

	
	
	public static class ExtSorte extends FZ_MASK_ContainerSegmentExtender {
		
		private  WE_CM__Collector cm_collector=null;
		
		public ExtSorte(FZ_MASK_MaskModulContainer fz_mask_modulContainer, WE_CM__Collector p_cm_collector) throws myException {
			super(p_cm_collector);
			this.cm_collector = p_cm_collector;
		}

		@Override
		public void open_extension() throws myException {

			int i_lbl_span = 7;

			WE_CM_Atom 			atom = 		cm_collector.get_left_atom();
			CM_Vektor 			vector = 	cm_collector.get_vektor();
			
			RB_gld glstd = new RB_gld()._ins(E2_INSETS.I(2,2,2,2))._col(this.col_High())._span(i_lbl_span);
	
			RB_gld gls = new RB_gld()._span(this.get_left_offset())._col(this.col_Back());
			
			
			this.mask_Grid()
			._startLine(gls)
			._a(new RB_lab_resizable("ID Artikel"),glstd	)
			._endLine(atom.getRbComponent(BEWEGUNG_ATOM.id_artikel.fk()),glstd);
			
			this.mask_Grid()
			._startLine(gls)
			._a(new RB_lab_resizable("ANR1-2"),glstd)
			._endLine(atom.getRbComponent(_TS_CONST._TS_KEYS.ANR12.k()),glstd);
			
			this.mask_Grid()
			._startLine(gls)
			._a(new RB_lab_resizable("Artbez1"),glstd		)
			._endLine(atom.getRbComponent(BEWEGUNG_ATOM.artbez1.fk()),glstd);
			
			this.mask_Grid()
			._startLine(gls)
			._a(new RB_lab_resizable("Artbez2"),glstd)
			._endLine(atom.getRbComponent(BEWEGUNG_ATOM.artbez2.fk()),glstd);
			
			this.mask_Grid()
			._startLine(gls)
			._a(new RB_lab_resizable("AVV-Code"),glstd)
			._endLine(vector.getRbComponent(BEWEGUNG_VEKTOR.id_eak_code),glstd);
			
			((FZ_SEARCH_AVV_CODES)vector.getRbComponent(BEWEGUNG_VEKTOR.id_eak_code)).set_layoutdata_color_to_components(this.col_High());
	
		}
		
	}


	
	
	public static class ExtKontraktAngebot extends FZ_MASK_ContainerSegmentExtender {
		
		private  WE_CM__Collector cm_collector=null;
		
		public ExtKontraktAngebot(FZ_MASK_MaskModulContainer fz_mask_modulContainer, WE_CM__Collector p_cm_collector) throws myException {
			super(p_cm_collector);
			this.cm_collector = p_cm_collector;
		}

		@Override
		public void open_extension() throws myException {
			WE_CM_Atom 			atom = 			cm_collector.get_left_atom();
			
			RB_gld gld_label = new RB_gld()._ins(E2_INSETS.I(2,2,2,2))._col(this.col_High())._span(5);
			
			RB_gld gld_data = new RB_gld()._ins(E2_INSETS.I(2,2,2,2))._col(this.col_High())._span(2);
	
			RB_gld gls = new RB_gld()._span(this.get_left_offset())._col(this.col_Back());

			RB_TextField  sf_id_vpos_kon = (RB_TextField)atom.getComp(BEWEGUNG_ATOM.id_vpos_kon);
			RB_TextField  sf_id_vpos_std = (RB_TextField)atom.getComp(BEWEGUNG_ATOM.id_vpos_std);
			
			E2_Grid mg = this.mask_Grid();
			
			mg._startLine(gls)
			._a(new RB_lab_resizable("Kontrakt (ID)"),gld_label)
			._endLine(sf_id_vpos_kon,gld_data);

			mg._startLine(gls)
			._a(new RB_lab_resizable("Angebot (ID)"),gld_label)
			._endLine(sf_id_vpos_std,gld_data);
			
			
		}
		
	}
	
	
	
	public static class ExtMenge extends FZ_MASK_ContainerSegmentExtender {
		
		private  WE_CM__Collector cm_collector=null;
		
		public ExtMenge(FZ_MASK_MaskModulContainer fz_mask_modulContainer, WE_CM__Collector p_cm_collector) throws myException {
			super(p_cm_collector);
			this.cm_collector = p_cm_collector;
		}

		@Override
		public void open_extension() throws myException {

			WE_CM_Atom 			atom = 		cm_collector.get_left_atom();
			RB_gld glstd = new RB_gld()._ins(E2_INSETS.I(0,2,2,2))._col(this.col_High())._span(1);
			RB_gld glstd2 = new RB_gld()._ins(E2_INSETS.I(0,2,2,2))._col(this.col_High())._span(2);
	
			RB_gld gls = new RB_gld()._span(this.get_left_offset())._col(this.col_Back());
			this.mask_Grid()._startLine(gls)._a(new RB_lab_resizable("Menge im Lager"),glstd	)._endLine(atom.getRbComponent(FZ__CONST.f_keys.LAGER_DIFF_MENGE_WE.k()),glstd2);
	
		}
		
	}

	
	

}
