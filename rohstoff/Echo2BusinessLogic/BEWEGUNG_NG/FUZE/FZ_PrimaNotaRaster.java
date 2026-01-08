package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.BETA.RASTER.Raster;
import panter.gmbh.Echo2.RB.COMP.BETA.RASTER.RasterBtOpenClose;
import panter.gmbh.Echo2.RB.COMP.BETA.RASTER.RasterRow;
import panter.gmbh.Echo2.RB.COMP.BETA.RASTER.RasterRowCell;
import panter.gmbh.Echo2.RB.COMP.BETA.RASTER.RasterRowCells;
import panter.gmbh.Echo2.RB.COMP.BETA.RASTER.RasterRows;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR_PN;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_adresse;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_artikel_bez;

public class FZ_PrimaNotaRaster extends Raster {
	
	private String 			f_idBewegungVektor = null;
	private String[][]      currentNumbers = null;
	
	/**
	 * @throws myException 
	 * 
	 */
	public FZ_PrimaNotaRaster(String p_idBewegungVektor) throws myException {
		super();
		
		this.f_idBewegungVektor = p_idBewegungVektor;
		
		this.setColor_content(new E2_ColorDark());
		this.setColor_freespace(new E2_ColorBase());
		this.setColor_title(new E2_ColorDDark());
		
		this._setRasterColWidth(30);
		this._setRasterColCount(50);
		this._initRaster();
		
		//selektion auf alle speicherungen
		SEL selPnBlock = new SEL(BEWEGUNG_VEKTOR_PN.current_number, BEWEGUNG_VEKTOR_PN.id_user, BEWEGUNG_VEKTOR_PN.saving_date_time).ADDFIELD("TO_CHAR("+BEWEGUNG_VEKTOR_PN.saving_date_time.tnfn()+",'DD.MM.YYYY HH24:MI:SS')", "SAVE_TIME").ADD_Distinct()
						.FROM(_TAB.bewegung_vektor_pn)
						.WHERE(new vgl(BEWEGUNG_VEKTOR_PN.id_bewegung_vektor, this.f_idBewegungVektor))
						.ORDERUP(BEWEGUNG_VEKTOR_PN.saving_date_time);
		
		this.currentNumbers = bibDB.EinzelAbfrageInArray(selPnBlock.s());

		
		//SEL selPn = new SEL(_TAB.bewegung_vektor_pn).FROM(_TAB.bewegung_vektor_pn).WHERE(new vgl(BEWEGUNG_VEKTOR_PN.id_bewegung_vektor, this.f_idBewegungVektor)).ORDERUP(BEWEGUNG_VEKTOR_PN.saving_date_time);
		
		//RecList20 rl_pn = new RecList20(_TAB.bewegung_vektor_pn)._fill(selPn.s());
		this.generateTitleRasterRowCells();
		this.buildRasterRows(null);
		this.render();
		
	}

	
	

	
	@Override
	public Color getBackgroundColor() {		return null;	}

	@Override
	public void generateTitleRasterRowCells() throws myException {
		RasterRowCells  title = new RasterRowCells();
		title.put(new RasterRowCell("T0", new ownBtOpenClose(), this)._setColSpan(1));
		title.put(new RasterRowCell("T1", new ownT("Startlager im Atom")		, this)._setColSpan(10));
		title.put(new RasterRowCell("T2", new ownT("Ziellager im Atom")		    , this)._setColSpan(10));
		title.put(new RasterRowCell("T3", new ownT("Menge")						._gld_align_rt(), this)._setColSpan(2));
		title.put(new RasterRowCell("T4", new ownT("Abzug")						._gld_align_rt(), this)._setColSpan(2));
		title.put(new RasterRowCell("T5", new ownT("E-Preis")					._gld_align_rt(), this)._setColSpan(2));
		title.put(new RasterRowCell("T6", new ownT("E-P-Res.")					._gld_align_rt(), this)._setColSpan(2));
		title.put(new RasterRowCell("T7", new ownT("Sorte")						, this)._setColSpan(10));
		title.put(new RasterRowCell("T8", new ownT("Besitzer/Besitzerwechsel")	, this)._setColSpan(10));	
		this.setTitelRasterRowCells(title);
	}

	
	private class ownT extends RB_lab {

		/**
		 * @param p_text_t
		 * @throws myException 
		 */
		public ownT(String p_text_t) throws myException {
			super();
			this._fsa(-2);
			this._col_fore_dgrey();
			this._gld_align_lt();
			this._set_ld(new RB_gld()._ins(1,1,3,1));
			this._t(p_text_t);
		}
		
	}

	
	private class ownBtOpenClose extends E2_Button {

		public ownBtOpenClose() throws myException {
			super();
			this._image("expandopenclose.png");
			this._aaa(new ownActionAgent());
			this._gld_align_cm()._gld_add_insets(2,2,2,2);
			
		}
		
		private class ownActionAgent extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				FZ_PrimaNotaRaster raster = FZ_PrimaNotaRaster.this;
				
				boolean oldOpen = false;
				
				for (RasterRow row: raster.getRasterRows().values()) {
					RasterBtOpenClose bt = (RasterBtOpenClose) row.getCellList().get("BT").getComponent4Cell();
					if (bt.isOpen()) {
						oldOpen = true;
						break;
					}
				}
				
				boolean newOpen = !oldOpen;
				
				
				for (RasterRow row: raster.getRasterRows().values()) {
					RasterBtOpenClose bt = (RasterBtOpenClose) row.getCellList().get("BT").getComponent4Cell();
					bt.set_open(newOpen);
				}
				raster.completeBuildAndRender();
				
			}
			
		}
	}
	
	
	@Override
	public void buildRasterRows(RasterRow callingRasterRow) throws myException {
		this.getRasterRows().clear();
		
		for (String[] curNum: this.currentNumbers) {
			this.getRasterRows().put(new ownRasterRowBloecke(curNum));
		}
		

	}

	
	public FZ_PrimaNotaRaster _showInPopupWindow(int startWidth, int startHeight) throws myException {
		ownBasicContainer cont = new ownBasicContainer();
		
		cont.add(this, E2_INSETS.I(5));
		
		cont.CREATE_AND_SHOW_POPUPWINDOW(new Extent(startWidth), new Extent(startHeight), new MyE2_String("Auflistung der Speichervorgänge ..."));
		
		return this;
	}
	
	private class ownBasicContainer extends E2_BasicModuleContainer {
	}
	
	

	
	

	
	private class ownRasterRowBloecke implements RasterRow {
		
		private RasterRowCells rasterRowCells = new RasterRowCells();
		private RasterRows     rasterRows = new RasterRows();  				//hier die drunter liegenden einzelnen atome
		
		private String  		rowKey = null;
		private String[]        currentBlock = null;     // enthaelt blocknummer, id-user, datum  und datum-string incl. uhrzeit 
		

		
		public ownRasterRowBloecke(String[] p_currentBlock) throws myException {
			super();
			this.setRowKey(p_currentBlock[0]);
			this.currentBlock = p_currentBlock;
			this.generateCellList(this);
			RasterBtOpenClose bt = (RasterBtOpenClose)this.getCellList().get("BT").getComponent4Cell();
			if (bt.isOpen()) {
				this.generateRasterRows(this);
			}

		}

		
		@Override
		public RasterRowCells getCellList() throws myException {
			return this.rasterRowCells;			
		}

		@Override
		public int getInsetColCount() throws myException {
			return 0;
		}

		@Override
		public RasterRows getRasterRows() throws myException {
			return this.rasterRows;
		}

		@Override
		public void buildRasterRows(RasterRow callingRasterRow) throws myException {
	
			//selektion auf alle speicherungen
			SEL selPnBlock = new SEL(_TAB.bewegung_vektor_pn)
							.FROM(_TAB.bewegung_vektor_pn)
							.WHERE(new vgl(BEWEGUNG_VEKTOR_PN.current_number, this.currentBlock[0]))
							.ORDERUP(BEWEGUNG_VEKTOR_PN.id_bewegung_vektor_pn);

			RecList20 rl = new RecList20(_TAB.bewegung_vektor_pn)._fill(selPnBlock.s());
			
			subListRow last = null;
			for (Rec20 r: rl) {
				last = new subListRow(r,this);
				this.rasterRows.put(last);
			}
			//in der letzten zeile ein groesser abstand nach unten
			if (last != null) {
				for (RasterRowCell cell: last.getCellList().values()) {
					((RB_lab)cell.getComponent4Cell())._gld_add_insets(new Insets(1, 1, 3, 5));
				}
			}
			
		}

		@Override
		public void buildCellList() throws myException {
			
			RasterBtOpenClose bt = new RasterBtOpenClose(FZ_PrimaNotaRaster.this).set_open(false);

			Rec20 recUser = new Rec20(_TAB.user)._fill_id(this.currentBlock[1]);

			RasterRowCell  cell0 = new RasterRowCell("BT", bt._set_ld(new RB_gld()._ins(1,4,1,4)), this)._setStatic(true)._setColSpan(1);
			RasterRowCell  cell1 = new RasterRowCell("T1", new RB_lab()._tr("Speichervorgang: ")._t_add(this.currentBlock[0])._set_ld(new RB_gld()._ins(1,4,1,4)), this)._setColSpan(10);
			RasterRowCell  cell2 = new RasterRowCell("T2", new RB_lab()._t(recUser.get_ufs_kette(" ",USER.vorname,USER.name1))._set_ld(new RB_gld()._ins(1,4,1,4)), this)._setColSpan(10);
			RasterRowCell  cell3 = new RasterRowCell("T3", new RB_lab()._t(this.currentBlock[3])._set_ld(new RB_gld()._ins(1,4,1,4)), this)._setColSpan(15);
			
			this.rasterRowCells.put(cell0);
			this.rasterRowCells.put(cell1);
			this.rasterRowCells.put(cell2);
			this.rasterRowCells.put(cell3);
			
		}

		@Override
		public String getRowKey() {
			return this.rowKey;
		}

		@Override
		public void setRowKey(String key) {
			this.rowKey = key;
		}

		@Override
		public Raster getRaster() {
			return FZ_PrimaNotaRaster.this;
		}

		@Override
		public RasterRow getMotherRasterRow() {
			return FZ_PrimaNotaRaster.this;
		}

		@Override
		public Color getBackgroundColor() {
			return new E2_ColorDDark();
		}
		
	}
	
	
	
	private class subListRow extends Rec20 implements RasterRow {

		private RasterRowCells rasterRowCells = new RasterRowCells();
		private RasterRow      callingRasterRow = null;

		
		/**
		 * @param baseRec
		 * @throws myException
		 */
		public subListRow(Rec20 recPn, RasterRow      p_callingRasterRow ) throws myException {
			super(recPn);
			this.callingRasterRow = p_callingRasterRow;
			this.generateCellList(this);

		}

			@Override
		public RasterRowCells getCellList() throws myException {
			return this.rasterRowCells;
		}

		@Override
		public int getInsetColCount() throws myException {
			return 1;
		}

		@Override
		public RasterRows getRasterRows() throws myException {
			return null;
		}

		@Override
		public void buildRasterRows(RasterRow callingRasterRow) throws myException {
		}

		
		@Override
		public void buildCellList() throws myException {
			this.rasterRowCells.clear();
			Rec20_adresse adresse_start = new Rec20_adresse(this.get_up_Rec20(BEWEGUNG_VEKTOR_PN.id_adresse_start, ADRESSE.id_adresse, true));
			Rec20_adresse adresse_ziel = new Rec20_adresse(this.get_up_Rec20(BEWEGUNG_VEKTOR_PN.id_adresse_ziel, ADRESSE.id_adresse, true));
			Rec20_adresse adresse_besitzer_start = new Rec20_adresse(this.get_up_Rec20(BEWEGUNG_VEKTOR_PN.id_adresse_besitzer_start, ADRESSE.id_adresse, true));
			Rec20_adresse adresse_besitzer_ziel = new Rec20_adresse(this.get_up_Rec20(BEWEGUNG_VEKTOR_PN.id_adresse_besitzer_ziel, ADRESSE.id_adresse, true));
			
			Rec20_artikel_bez  artbez = new Rec20_artikel_bez(this.get_up_Rec20(ARTIKEL_BEZ.id_artikel_bez));
			
			RasterRowCell besitzer = null;
			if (adresse_besitzer_start.get_ufs_dbVal(ADRESSE.id_adresse).equals(adresse_besitzer_ziel.get_ufs_dbVal(ADRESSE.id_adresse))) {
				besitzer=new RasterRowCell(BEWEGUNG_VEKTOR_PN.id_adresse_besitzer_start.fn(), new ownLab(
																			new MyE2_String("Besitzer: ").CTrans()+adresse_besitzer_start.get_infoText()
																										)._col_fore(Color.DARKGRAY)._gld_align_lm(), this)._setColSpan(10);
			} else {
				besitzer=new RasterRowCell(BEWEGUNG_VEKTOR_PN.id_adresse_besitzer_start.fn(), new ownLab(
																			new MyE2_String("Besitzer: ").CTrans()+adresse_besitzer_start.get_ufs_dbVal(ADRESSE.name1)+" -> "+adresse_besitzer_ziel.get_ufs_dbVal(ADRESSE.name1)
																										)._gld_align_lm(), this)._setColSpan(10);
			}
			
			Color frontColStart = Color.BLACK;
			if (adresse_start.is_sonderlager()) {
				frontColStart = Color.DARKGRAY;
			}
			Color frontColZiel = Color.BLACK;
			if (adresse_ziel.is_sonderlager()) {
				frontColZiel = Color.DARKGRAY;
			}

			
			this.rasterRowCells.put(new RasterRowCell(BEWEGUNG_VEKTOR_PN.id_adresse_start.fn(), 			new ownLab(adresse_start.get_infoText())._col_fore(frontColStart)._gld_align_lm(), 				this)._setColSpan(10));
			this.rasterRowCells.put(new RasterRowCell(BEWEGUNG_VEKTOR_PN.id_adresse_ziel.fn(), 				new ownLab(adresse_ziel.get_infoText())._col_fore(frontColZiel)._gld_align_lm(), 				this)._setColSpan(10));
			
			this.rasterRowCells.put(new RasterRowCell(BEWEGUNG_VEKTOR_PN.menge.fn(), 						new ownLab(this.get_fs_dbVal(BEWEGUNG_VEKTOR_PN.menge, "-"))._gld_align_rm(), 					this)._setColSpan(2));
			this.rasterRowCells.put(new RasterRowCell(BEWEGUNG_VEKTOR_PN.abzug_menge.fn(), 					new ownLab(this.get_fs_dbVal(BEWEGUNG_VEKTOR_PN.abzug_menge, "-"))._gld_align_rm(), 			this)._setColSpan(2));
			
			this.rasterRowCells.put(new RasterRowCell(BEWEGUNG_VEKTOR_PN.e_preis.fn(), 						new ownLab(this.get_fs_dbVal(BEWEGUNG_VEKTOR_PN.e_preis, "-"))._gld_align_rm(), 				this)._setColSpan(2));
			this.rasterRowCells.put(new RasterRowCell(BEWEGUNG_VEKTOR_PN.e_preis_result_netto_mge.fn(), 	new ownLab(this.get_fs_dbVal(BEWEGUNG_VEKTOR_PN.e_preis_result_netto_mge, "-"))._gld_align_rm(),this)._setColSpan(2));
			
			this.rasterRowCells.put(new RasterRowCell(BEWEGUNG_VEKTOR_PN.id_artikel_bez.fn(), 				new ownLab(artbez.__get_ANR1_ANR2_ARTBEZ1())._gld_align_lm(), 									this)._setColSpan(10));
			this.rasterRowCells.put(besitzer);
		}

		@Override
		public String getRowKey() throws myException {
			return this.get_ufs_dbVal(BEWEGUNG_VEKTOR_PN.id_bewegung_vektor_pn);
		}

		@Override
		public void setRowKey(String key) {
		}

		@Override
		public Raster getRaster() {
			return FZ_PrimaNotaRaster.this;
		}

		@Override
		public RasterRow getMotherRasterRow() {
			return this.callingRasterRow;
		}

		@Override
		public Color getBackgroundColor() {
			return new E2_ColorDark();
		}
		
		private class ownLab extends RB_lab {
			private String f_text_t = null;

			/**
			 * @param p_text_t
			 */
			public ownLab(String p_text_t) {
				super();
				this._fsa(-2);
				this._set_ld(new RB_gld()._ins(1,1,3,1));
				this.f_text_t = p_text_t;
				this._t(this.f_text_t);
			}
			
		}
		
	}
	
	
}
