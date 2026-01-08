package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.MyE2IF_IsMarkable;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.basics4project.DB_ENUMS.ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.PAIR;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.bibFONT;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_ArchivMedien;


/**
 * allgemeine klasse zur anzeige hochgeladener dokumente direkt in der liste (mit download-button)
 * @author martin
 *
 */
public abstract class E2_ShowAllDownloadsInList extends  E2_UniversalListComponent implements MyE2IF_IsMarkable {

	public static String 					LISTKEY4COMPONENTMAP = "ATTACHMENTS4ROW";
	public static MyE2_String 				LISTINFO4COMPONENTMAP = new MyE2_String("Anzeige der Anlagen");

	private int[] 							spaltenBreiten = {180,15};
	private RecList21  						rlArchivmedien = null;
	private int          					anzeigeAnzahlZeilen = 3;
	
	//private ArrayList<ownTextField>   		al_textfields = new ArrayList<ownTextField>();
	private VEK<PAIR<ownTextField, ownDownButton>>  zeilen = new VEK<>();
	
	
	public abstract XX_ActionValidator 	generateDownloadValidator(E2_Button downButton);
	
	
	public E2_ShowAllDownloadsInList() {
		this(bibARR.get_Array(150, 15));
	}
	
	public E2_ShowAllDownloadsInList(int[] breite)  {
		super();
		this.spaltenBreiten=breite;
		this._setWidth(spaltenBreiten[0]+spaltenBreiten[1]);
		this.EXT().set_bLineWrapListHeader(true);
		this._setSize(this.spaltenBreiten);
	}
	
	


	/**
	 * festlegen, wieviele zeilen gleichzeitig angezeigt werden
	 * @param zeilen
	 * @return
	 */
	public E2_ShowAllDownloadsInList set_Zeilen(int zeilen) {
		this.anzeigeAnzahlZeilen = zeilen;
		return this;
	}
	
	public void populate(SQLResultMAP resultMap) throws myException {

		_TAB tab = _TAB.find_Table(resultMap.get_oSQLFieldMAP().get_cMAIN_TABLE());
		
		if (tab != null) {
			String tableID = resultMap.get_cUNFormatedROW_ID();
			
			SEL sel = new SEL("*").FROM(ARCHIVMEDIEN.T())
							.WHERE(new vglParam(ARCHIVMEDIEN.tablename)).AND(new vglParam(ARCHIVMEDIEN.id_table))
							.ORDERUP(ARCHIVMEDIEN.downloadname);
			
			SqlStringExtended sqlExt = new SqlStringExtended(sel.s())
						._addParameters(new VEK<ParamDataObject>()
								._a(new Param_String("",tab.baseTableName()))
								._a(new Param_Long(Long.parseLong(tableID)))
						);

			
			this.rlArchivmedien = new RecList21(_TAB.archivmedien)._fill(sqlExt);
		}
		
		this.render(false);
	}
	
	
	private void render(boolean full) throws myException {
		int iZahlAngezeigt = 0;

		this._clear();
		this.zeilen.clear();
		
		if (this.rlArchivmedien==null) {
			this._a("@ERR@");
		} else {
		
			
//			VEK<Component[]> v_zeilen = new VEK<>();
			
			if (this.rlArchivmedien.size()>0) {
				for (Rec21 r: this.rlArchivmedien) {
					Rec21_ArchivMedien recAM = new Rec21_ArchivMedien(r);
					PAIR<ownTextField, ownDownButton> pair = new PAIR<ownTextField, ownDownButton>()._setVal1(new ownTextField(recAM))._setVal2(new ownDownButton(recAM));
					this.zeilen._a(pair);
				}
			}
			
			//jetzt die gewuenschte anzahl rendern
			if (zeilen.size()>this.anzeigeAnzahlZeilen) {
				//dann muss ein ein/ausklapp-button rein
				this._a(new ownButtonAllEinaus(full), new RB_gld()._span(2)._ins(1, 0, 1, 0));
			}
			for (PAIR<ownTextField, ownDownButton> p: zeilen ) {
				
				this._a(p.getVal1(), new RB_gld()._ins(1, 1, 4, 1))
					._a(p.getVal2(), new RB_gld()._ins(1, 1, 1, 1));
				iZahlAngezeigt++;
				if (iZahlAngezeigt>=this.anzeigeAnzahlZeilen && !full) {
					break;
				}
			}
		}
	}
	
	
	private class ownTextField extends RB_TextField {

		public ownTextField(Rec21_ArchivMedien recAM ) {
			super();
			this.setEnabled(false);
			this._w(E2_ShowAllDownloadsInList.this.spaltenBreiten[0]);
			this.setFont(new E2_FontPlain(-2));
			this.setBorder(new Border(0, new E2_ColorBase(), Border.STYLE_NONE));
			this.setBackground(new E2_ColorBase());
			this.setText(recAM.getUfs("???",ARCHIVMEDIEN.downloadname,ARCHIVMEDIEN.dateiname));
			this.setToolTipText(recAM.getUfs("???",ARCHIVMEDIEN.dateibeschreibung,ARCHIVMEDIEN.dateiname));
		}
	}
	
	
	private class ownDownButton extends  E2_Button {
		private Rec21_ArchivMedien recAM = null;

		public ownDownButton(Rec21_ArchivMedien p_recAM) throws myException {
			super();
			this.recAM = p_recAM;
			
			this._image(E2_ResourceIcon.get_RI("down_mini.png"));
			
			this.add_GlobalValidator(generateDownloadValidator(this));
			
			this.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					ownDownButton.this.recAM.starteDownLoad();
				}
			});
		}
	}
	
	
	
	
	
	private class ownButtonAllEinaus extends E2_Button {

		private boolean statusFull;

		public ownButtonAllEinaus(boolean p_statusFull) {
			super();
			this.statusFull = p_statusFull;
			this._t("...")._fsa(2)._b();
			this._center();
			int iAnzahl = E2_ShowAllDownloadsInList.this.rlArchivmedien.size();
			if (!statusFull) {
				this._ttt(S.ms("Alle").ut(" "+iAnzahl+" ").t("Dokumente anzeigen"));
			} else {
				this._ttt(S.ms("Anzahl reduzieren "));
			}
			this.add_oActionAgent(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					E2_ShowAllDownloadsInList.this.render(!statusFull);
				}
			});
		}
		
	}

	@Override
	public void make_Look_Deleted(boolean bIsDeleted) {
		for (PAIR<ownTextField, ownDownButton> p: this.zeilen) {
			p.getVal1().setFont(bibFONT.getLineThroughFont(p.getVal1().getFont(), true));
		}
	}
	
	@Override
	public void setForeColorActive(Color ForeColor) {
		for (PAIR<ownTextField, ownDownButton> p: this.zeilen) {
			p.getVal1().setForeground(ForeColor);
		}
	}
	
	@Override
	public void setFontActive(Font Font) {
//		for (ownTextField tf: this.al_textfields) {
//			tf.setFont(Font);
//		}
	}
	
	@Override
	public Color get_Unmarked_ForeColor() {

		for (PAIR<ownTextField, ownDownButton> p: this.zeilen) {
			return p.getVal1().getForeground();
		}
		return Color.BLACK;
	}
	
	@Override
	public Font get_Unmarked_Font() {
		for (PAIR<ownTextField, ownDownButton> p: this.zeilen) {
			return p.getVal1().getFont();
		}
		return new E2_FontPlain();
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
	}


	@Override
	public String key() throws myException {
		return E2_ShowAllDownloadsInList.LISTKEY4COMPONENTMAP;
	}

	@Override
	public String userText() throws myException {
		return E2_ShowAllDownloadsInList.LISTINFO4COMPONENTMAP.CTrans();
	}


	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
	}


}
