package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.ST;


import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.BasicInterfaces.IF_wrappedMulticomponentsInGrid;
import panter.gmbh.Echo2.RB.COMP.RB;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.CM_Vektor;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.FZ_MASK_ContainerSegmentExtender;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.FZ_MASK_MaskModulContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.FZ_SEARCH_AVV_CODES;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.RB_lab_resizable;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.LL._LL_CONST;

public class ST_CM__column_extenders {

	public static class ExtDatum extends FZ_MASK_ContainerSegmentExtender {

		private  ST_CM__Collector cm_collector=null;

		public ExtDatum(FZ_MASK_MaskModulContainer fz_mask_modulContainer, ST_CM__Collector p_cm_collector) throws myException {
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

		private  ST_CM__Collector cm_collector=null;

		public ExtMenge(FZ_MASK_MaskModulContainer fz_mask_modulContainer, ST_CM__Collector p_cm_collector) throws myException {
			super(p_cm_collector);
			this.cm_collector = p_cm_collector;
		}

		@Override
		public void open_extension() throws myException {
			
			int i_lbl_span = 6;
			
			ST_CM_Station 		s_li = 	cm_collector.get_startStationLeft();
			ST_CM_Station 		s_re = cm_collector.get_zielStationRight();

			Color col_closed = cm_collector.get_my_maskContainer().get_color_for_closed_block();
			Color col_open = cm_collector.get_my_maskContainer().get_color_for_expanded_block();

			E2_Grid baseGrid = cm_collector.get_my_maskContainer().mask_grid(); 
			RB_gld ld_start = 	new RB_gld()._col(col_closed	)._span(this.get_left_offset());				
			RB_gld ld_text = 	new RB_gld()._col(col_open		)._span(i_lbl_span)._ins(2);        							//layout-data fuer die beschriftungsspalte
			RB_gld ld_data = 	new RB_gld()._col(col_open		)._span(7)._ins(2);       		 	//layout-data fuer die beschriftungsspalte

			baseGrid._startLine(ld_start)	._a(RB.lt("Kontrollmenge Ladeseite")._fsa(-2),		ld_text)
			._endLine(s_li.getComp(BEWEGUNG_STATION.kontrollmenge),ld_data)
			;

			baseGrid._startLine(ld_start)	._a(RB.lt("Kontrollmenge Abladeseite")._fsa(-2),	ld_text)
			._endLine(s_re.getComp(BEWEGUNG_STATION.kontrollmenge),ld_data)
			;

		}

	}


	/**
	 * @author martin
	 *
	 */
	public static class ExtStation_variante2 extends FZ_MASK_ContainerSegmentExtender {

		private  ST_CM__Collector 	cm_collector=null;

		private boolean is_quelle = false;

		public ExtStation_variante2(FZ_MASK_MaskModulContainer fz_mask_modulContainer, ST_CM__Collector p_cm_collector, boolean b_isQuelle_true) throws myException {
			super(p_cm_collector);
			this.is_quelle = b_isQuelle_true;
			this.cm_collector = p_cm_collector;
		}

		@Override
		public void open_extension() throws myException {
			ST_CM_Station station = null;

			if(is_quelle){
				station = cm_collector.get_startStationLeft();
			}else{
				station = cm_collector.get_zielStationRight();
			}

			int i_lbl_span = 7;

			Color col_closed = cm_collector.get_my_maskContainer().get_color_for_closed_block();
			Color col_open = cm_collector.get_my_maskContainer().get_color_for_expanded_block();

			E2_Grid baseGrid = cm_collector.get_my_maskContainer().mask_grid(); 

			RB_gld endLayoutOpen 	= new RB_gld()._al(E2_ALIGN.LEFT_MID)._ins(E2_INSETS.I(2,0,2,0))._span(3)._col(col_open);
			RB_gld startLayoutOpen 	= new RB_gld()._col(col_closed)._span(this.get_left_offset());

			baseGrid._startLine(new RB_gld()._col(col_closed)._span(this.get_left_offset()))
			._a(new RB_lab_resizable("Besitzer")._lw(), new RB_gld()._span(i_lbl_span)._col(col_open)._ins(2))
			._endLine(station.getRbComponent(BEWEGUNG_STATION.id_adresse_besitzer), endLayoutOpen);

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

	/**
	 * @author martin
	 *
	 */
	public static class ExtStation extends FZ_MASK_ContainerSegmentExtender {

		private  ST_CM__Collector 	cm_collector=null;

		public ExtStation(FZ_MASK_MaskModulContainer fz_mask_modulContainer, ST_CM__Collector p_cm_collector) throws myException {
			super(p_cm_collector);
			this.cm_collector = p_cm_collector;
		}

		@Override
		public void open_extension() throws myException {
			ST_CM_Station 		s_li = 	cm_collector.get_startStationLeft();
			ST_CM_Station 		s_re = cm_collector.get_zielStationRight();

			Color 	col_closed = 	cm_collector.get_my_maskContainer().get_color_for_closed_block();
			Color 	col_open = 		cm_collector.get_my_maskContainer().get_color_for_expanded_block();
			E2_Grid baseGrid = 		cm_collector.get_my_maskContainer().mask_grid(); 

			RB_gld ld = 		new RB_gld()._col(col_open	)._span(1)._ins(E2_INSETS.I(0,0,5,0));        								//layout-data fuer die beschriftungsspalte
			RB_gld ld_start = 	new RB_gld()._col(col_closed	)._span(this.get_left_offset());				
			RB_gld ld_text = 	new RB_gld()._col(col_open		)._span(2);        								//layout-data fuer die beschriftungsspalte
			RB_gld ld_data = 	new RB_gld()._col(col_open		)._span(7)._ins(E2_INSETS.I(0,0,5,0));       		 	//layout-data fuer die beschriftungsspalte


			IF_wrappedMulticomponentsInGrid  wrap = (Component... comps )-> {E2_Grid g = new E2_Grid(); for (Component c: comps) {g._a(c,ld);} return g._s(comps.length); };

			baseGrid._startLine(ld_start)	._a(new RB_lab_resizable(S.mt("Quelle der Strecke"))._fo_bold(),			ld_text._c()._span(9)._ins(E2_INSETS.I(0,2,5,2)))
			._a(new RB_lab_resizable(S.mt("Ziel der Strecke"))._fo_bold(),			ld_text._c()._span(7)._ins(E2_INSETS.I(0,2,5,2)))
			._endLine(ld_data)
			;

			baseGrid._startLine(ld_start)	._a(RB.lt("Hauptadresse")._fsa(-2),							ld_text)
			._a(s_li.getComp(BEWEGUNG_STATION.id_adresse_basis), 		l(7)._c()._ins(E2_INSETS.I(0,2,5,2)))
			._a(s_re.getComp(BEWEGUNG_STATION.id_adresse_basis), 		l(7)._c()._ins(E2_INSETS.I(0,2,5,2)))
			._endLine(ld_data)
			;

			baseGrid._startLine(ld_start)	._a(RB.lt("Besitzer")._fsa(-2),										ld_text)
			._a(s_li.getComp(BEWEGUNG_STATION.id_adresse_besitzer),  	l(7))
			._a(s_re.getComp(BEWEGUNG_STATION.id_adresse_besitzer),  	l(7))
			._endLine(ld_data)
			;


			baseGrid._startLine(ld_start)	._a(RB.lt("Name 1")._fsa(-2),											ld_text)
			._a(s_li.getComp(BEWEGUNG_STATION.name1), 					l(7))
			._a(s_re.getComp(BEWEGUNG_STATION.name1), 					l(7))
			._endLine(ld_data)
			;

			baseGrid._startLine(ld_start)	._a(RB.lt("Name 2")._fsa(-2),											ld_text)
			._a(s_li.getComp(BEWEGUNG_STATION.name2), 					l(7))
			._a(s_re.getComp(BEWEGUNG_STATION.name2), 					l(7))
			._endLine(ld_data)
			;

			baseGrid._startLine(ld_start)	._a(RB.lt("Name 3")._fsa(-2),											ld_text)
			._a(s_li.getComp(BEWEGUNG_STATION.name3), 					l(7))
			._a(s_re.getComp(BEWEGUNG_STATION.name3), 					l(7))
			._endLine(ld_data)
			;

			baseGrid._startLine(ld_start)	._a(RB.lt("Strasse/HNR")._fsa(-2),									ld_text)
			._a(wrap.grid(s_li.getComps(BEWEGUNG_STATION.strasse, BEWEGUNG_STATION.hausnummer)),	l(7))
			._a(wrap.grid(s_re.getComps(BEWEGUNG_STATION.strasse, BEWEGUNG_STATION.hausnummer)),	l(7))
			._endLine(ld_data)
			;

			baseGrid._startLine(ld_start)	._a(RB.lt("Land/PLZ/Ort")._fsa(-2),										ld_text)
			._a(wrap.grid(s_li.getComps(BEWEGUNG_STATION.laendercode, BEWEGUNG_STATION.plz, BEWEGUNG_STATION.ort)),	l(7))
			._a(wrap.grid(s_re.getComps(BEWEGUNG_STATION.laendercode, BEWEGUNG_STATION.plz, BEWEGUNG_STATION.ort)),	l(7))
			._endLine(ld_data)
			;

			baseGrid._startLine(ld_start)	._a(RB.lt("Ort (Zusatz)")._fsa(-2),									ld_text)
			._a(s_li.getComp(BEWEGUNG_STATION.ortzusatz), 				l(7))
			._a(s_re.getComp(BEWEGUNG_STATION.ortzusatz), 				l(7))
			._endLine(ld_data)
			;

			baseGrid._startLine(ld_start)	._a(RB.lt("Öffnungszeiten")._fsa(-2),									ld_text)
			._a(s_li.getComp(BEWEGUNG_STATION.oeffnungszeiten), 		l(7))
			._a(s_re.getComp(BEWEGUNG_STATION.oeffnungszeiten), 		l(7))
			._endLine(ld_data)
			;

			baseGrid._startLine(ld_start)	._a(RB.lt("Bestellnummer")._fsa(-2),									ld_text)
			._a(s_li.getComp(BEWEGUNG_STATION.bestellnummer), 			l(7))
			._a(s_re.getComp(BEWEGUNG_STATION.bestellnummer), 			l(7))
			._endLine(ld_data)
			;

			baseGrid._startLine(ld_start)	._a(RB.lt("Telefon")._fsa(-2),										ld_text)
			._a(s_li.getComp(BEWEGUNG_STATION.telefon), 				l(7))
			._a(s_re.getComp(BEWEGUNG_STATION.telefon), 				l(7))
			._endLine(ld_data)
			;

			baseGrid._startLine(ld_start)	._a(RB.lt("Telefax")._fsa(-2),										ld_text)
			._a(s_li.getComp(BEWEGUNG_STATION.fax), 					l(7))
			._a(s_re.getComp(BEWEGUNG_STATION.fax), 					l(7))
			._endLine(ld_data)
			;

			baseGrid._startLine(ld_start)	._a(RB.lt("Wiegekarte (ext)")._fsa(-2),								ld_text)
			._a(s_li.getComp(BEWEGUNG_STATION.wiegekartenkenner), 		l(7))
			._a(s_re.getComp(BEWEGUNG_STATION.wiegekartenkenner), 		l(7))
			._endLine(ld_data)
			;

			baseGrid._startLine(ld_start)	._a(RB.lt("Wiegekarte (int)")._fsa(-2),								ld_text)
			._a(s_li.getComp(BEWEGUNG_STATION.id_wiegekarte), 			l(7))
			._a(s_re.getComp(BEWEGUNG_STATION.id_wiegekarte), 			l(7))
			._endLine(ld_data)
			;

		}

		private RB_gld l(int span) throws myException {
			return new RB_gld()._left_top()._span(span)._ins(E2_INSETS.I(0,0,5,0))._col(cm_collector.get_my_maskContainer().get_color_for_expanded_block());
		}
	}





	public static class ExtSorte extends FZ_MASK_ContainerSegmentExtender {

		private  ST_CM__Collector cm_collector=null;

		public ExtSorte(FZ_MASK_MaskModulContainer fz_mask_modulContainer, ST_CM__Collector p_cm_collector) throws myException {
			super(p_cm_collector);
			this.cm_collector = p_cm_collector;
		}

		@Override
		public void open_extension() throws myException {

			int i_lbl_span = 7;

			Color 	col_closed = 	cm_collector.get_my_maskContainer().get_color_for_closed_block();
			Color 	col_open = 		cm_collector.get_my_maskContainer().get_color_for_expanded_block();
			E2_Grid baseGrid = 		cm_collector.get_my_maskContainer().mask_grid(); 

			RB_gld ld_start = 	new RB_gld()._col(col_closed)._span(this.get_left_offset());				
			RB_gld ld_text = 	new RB_gld()._col(col_open)._span(i_lbl_span);        			//layout-data fuer die beschriftungsspalte
			RB_gld ld_data = 	new RB_gld()._col(col_open)._span(14)._ins(2);       		 	//layout-data fuer die beschriftungsspalte


			ST_CM_Atom   		atom_start = 	cm_collector.get_startAtom();
			ST_CM_Atom   		atom_ziel = 	cm_collector.get_zielAtom();

			CM_Vektor 			vector = 		cm_collector.get_vektor();

			baseGrid._startLine(ld_start)
			._a(new RB_lab_resizable(),ld_text._c()._span(i_lbl_span)._ins(E2_INSETS.I(0,2,5,2)))
			._a(new RB_lab_resizable(S.mt("Sortenangaben Quellseite"))._fo_bold(),	ld_text._c()._span(14)._ins(E2_INSETS.I(0,2,5,2)))
			._endLine(new RB_lab_resizable(S.mt("Sortenangaben Zielseite"))._fo_bold(),ld_text._c()._span(7)._ins(E2_INSETS.I(0,2,5,2)))
			;

			baseGrid._startLine(ld_start)	._a(RB.lt("ID Artikel"),new RB_gld()._al(E2_ALIGN.LEFT_MID)._ins(2,5,2,2)._col(col_open)._span(i_lbl_span))
			._a(atom_start.getComp(BEWEGUNG_ATOM.id_artikel.fk()),		ld_data)	
			._endLine(atom_ziel.getComp(BEWEGUNG_ATOM.id_artikel.fk()),	ld_data);

			baseGrid._startLine(ld_start)	._a(RB.lt("ANR1-2"),new RB_gld()._al(E2_ALIGN.LEFT_MID)._ins(2,5,2,2)._col(col_open)._span(i_lbl_span))
			._a(atom_start.getComp(_LL_CONST._LL_KEYS.ANR12.k()),		ld_data)	
			._endLine(atom_ziel.getComp(_LL_CONST._LL_KEYS.ANR12.k()),	ld_data);

			baseGrid._startLine(ld_start)	._a(RB.lt("Artbez1"),new RB_gld()._al(E2_ALIGN.LEFT_MID)._ins(2,5,2,2)._col(col_open)._span(i_lbl_span))
			._a(atom_start.getComp(BEWEGUNG_ATOM.artbez1.fk()),		ld_data)	
			._endLine(atom_ziel.getComp(BEWEGUNG_ATOM.artbez1.fk()),ld_data);

			baseGrid._startLine(ld_start)	._a(RB.lt("Artbez2"),ld_text)
			._a(atom_start.getComp(BEWEGUNG_ATOM.artbez2.fk()),		ld_data)	
			._endLine(atom_ziel.getComp(BEWEGUNG_ATOM.artbez2.fk()),ld_data);

			baseGrid._startLine(ld_start)	
			._a(RB.lt("AVV-Code"),									new RB_gld()._al(E2_ALIGN.LEFT_MID)._ins(2,5,2,2)._col(col_open)._span(i_lbl_span))
			._endLine(vector.getComp(BEWEGUNG_VEKTOR.id_eak_code),	new RB_gld()._al(E2_ALIGN.LEFT_MID)._ins(2,5,2,2)._col(col_open)._span(i_lbl_span));

			((FZ_SEARCH_AVV_CODES)vector.getRbComponent(BEWEGUNG_VEKTOR.id_eak_code)).set_layoutdata_color_to_components(this.col_High());
		}

	}

	public static class Ext_kontrakt extends FZ_MASK_ContainerSegmentExtender {

		private  ST_CM__Collector cm_collector=null;

		public Ext_kontrakt(FZ_MASK_MaskModulContainer fz_mask_modulContainer, ST_CM__Collector p_cm_collector) throws myException {
			super(p_cm_collector);
			this.cm_collector = p_cm_collector;
		}

		@Override
		public void open_extension() throws myException {
			int i_lbl_span = 5;

			Color 	col_closed = 		cm_collector.get_my_maskContainer().get_color_for_closed_block();
			Color 	col_open = 			cm_collector.get_my_maskContainer().get_color_for_expanded_block();

			E2_Grid baseGrid = 			cm_collector.get_my_maskContainer().mask_grid(); 

			RB_gld ld_start = 			new RB_gld()._col(col_closed)._span(this.get_left_offset());				
			RB_gld ld_text = 			new RB_gld()._col(col_open)._span(i_lbl_span)._al(E2_ALIGN.LEFT_MID)._ins(2);        			//layout-data fuer die beschriftungsspalte
			RB_gld ld_data = 			new RB_gld()._col(col_open)._span(14)._ins(2);

			ST_CM_Atom  atom_start = 	cm_collector.get_startAtom();
			ST_CM_Atom  atom_ziel = 	cm_collector.get_zielAtom();

			baseGrid._startLine(ld_start)
//			._a(new RB_lab_resizable(),ld_text)
			._a(new RB_lab_resizable("Kontrakt ID"), ld_text)
			._endLine(new RB_lab_resizable("Angebot ID"), ld_text);

			baseGrid._startLine(new RB_gld()._col(col_closed)._span(4))
			._a(new RB_lab_resizable("Quelle")._i(), new RB_gld()._col(col_closed)._span(2)._al(E2_ALIGN.LEFT_MID)._ins(2))
			._a(new RB_lab_resizable(), new RB_gld()._col(col_closed)._span(25))
			._a(atom_start.getComp(BEWEGUNG_ATOM.id_vpos_kon), ld_text)
			._endLine(atom_start.getComp(BEWEGUNG_ATOM.id_vpos_std), ld_data);

			baseGrid._startLine(new RB_gld()._col(col_closed)._span(4))
			._a(new RB_lab_resizable("Ziel")._i(), new RB_gld()._col(col_closed)._span(2)._al(E2_ALIGN.LEFT_MID)._ins(2))
			._a(new RB_lab_resizable(), new RB_gld()._col(col_closed)._span(25))
			._a(atom_ziel.getComp(BEWEGUNG_ATOM.id_vpos_kon),ld_text)
			._endLine(atom_ziel.getComp(BEWEGUNG_ATOM.id_vpos_std), ld_data);

//			baseGrid._startLine(ld_start)
//			._endLine(new RB_lab_resizable()._b(), ld_text);
//
//			baseGrid._startLine(ld_start)
//			._a(new RB_lab_resizable("Kontrakt id"), ld_text)
//			._endLine(atom_ziel.rb_Comp(BEWEGUNG_ATOM.id_vpos_kon), ld_data);
//
//			baseGrid._startLine(ld_start)
//			._a(new RB_lab_resizable("Angebot id"), ld_text)
//			._endLine(atom_ziel.rb_Comp(BEWEGUNG_ATOM.id_vpos_std), ld_data);
		}
	}

	public static class Ext_Preis extends FZ_MASK_ContainerSegmentExtender {

		private  ST_CM__Collector cm_collector=null;
		
		public Ext_Preis(FZ_MASK_MaskModulContainer fz_mask_modulContainer, ST_CM__Collector p_cm_collector) throws myException {
			super(p_cm_collector);
			this.cm_collector = p_cm_collector;
		}

		@Override
		public void open_extension() throws myException {
			int i_lbl_span = 3;
			
			Color 	col_closed = 		cm_collector.get_my_maskContainer().get_color_for_closed_block();
			Color 	col_open = 			cm_collector.get_my_maskContainer().get_color_for_expanded_block();

			E2_Grid baseGrid = 			cm_collector.get_my_maskContainer().mask_grid(); 

			RB_gld ld_start = 			new RB_gld()._col(col_closed)._span(this.get_left_offset());				
			RB_gld ld_text = 			new RB_gld()._col(col_open)._span(i_lbl_span)._al(E2_ALIGN.LEFT_MID)._ins(2);        			//layout-data fuer die beschriftungsspalte
			RB_gld ld_data = 			new RB_gld()._col(col_open)._span(4)._ins(2);
			
			ST_CM_Atom  atom_start = 	cm_collector.get_startAtom();
			ST_CM_Atom  atom_ziel = 	cm_collector.get_zielAtom();
			
			baseGrid._startLine(ld_start)
			._a(new RB_lab_resizable(),ld_text._c()._span(i_lbl_span)._ins(E2_INSETS.I(0,2,5,2)))
			._a(new RB_lab_resizable(S.mt("Quellseite"))._fo_bold(),	ld_text._c()._span(4)._ins(E2_INSETS.I(0,2,5,2)))
			._endLine(new RB_lab_resizable(S.mt("Zielseite"))._fo_bold(),ld_text._c()._span(4)._ins(E2_INSETS.I(0,2,5,2)))
			;
			
			baseGrid._startLine(ld_start)
			._a(new RB_lab_resizable("Manuell Preis festlegen")._i(), new RB_gld()._col(col_open)._span(3)._al(E2_ALIGN.LEFT_MID)._ins(2))
//			._a(new RB_lab_resizable(), new RB_gld()._col(col_closed)._span(4))
			._a(atom_start.getComp(BEWEGUNG_ATOM.manuell_preis) , new RB_gld()._col(col_open)._span(4)._al(E2_ALIGN.CENTER_MID))
			._endLine(atom_ziel.getComp(BEWEGUNG_ATOM.manuell_preis), ld_data._al(E2_ALIGN.CENTER_MID));

			
		}
		
	}
}
