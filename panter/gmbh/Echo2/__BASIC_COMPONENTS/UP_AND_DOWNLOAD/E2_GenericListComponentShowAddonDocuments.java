package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD;

import java.util.ArrayList;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.MyE2IF_IsMarkable;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_PlaceHolder_NT;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.ARCHIVMEDIEN;
import panter.gmbh.indep.bibFONT;
import panter.gmbh.indep.archive.Archiver_Normalized_Tablename;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECLIST_ARCHIVMEDIEN_ext;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;


/**
 * allgemeine klasse zur anzeige hochgeladener dokumente direkt in der liste (mit download-button)
 * @author martin
 *
 */
public class E2_GenericListComponentShowAddonDocuments extends  MyE2_DB_PlaceHolder_NT implements MyE2IF_IsMarkable{

	public static String 					LISTKEY4COMPONENTMAP = "ATTACHMENTS4ROW";
	public static MyE2_String 				LISTINFO4COMPONENTMAP = new MyE2_String("Anzeige der Anlagen");

	private int[] 							iBreite = {180,15};
	private RECLIST_ARCHIVMEDIEN_ext  		rlArchivmedien = null;
	private int          					iAnzeigeAnzahlZeilen = 3;
	
	private ArrayList<ownTextField>   		al_textfields = new ArrayList<ownTextField>();
	
	public E2_GenericListComponentShowAddonDocuments() throws myException {
		super();
		this.EXT().set_oColExtent(new Extent(this.iBreite[0]+this.iBreite[1]));
		this.EXT().set_bLineWrapListHeader(true);
		this.set_Spalten(this.iBreite);
	}
	
	public E2_GenericListComponentShowAddonDocuments(int[] breite) throws myException {
		super();
		this.iBreite=breite;
		this.EXT().set_oColExtent(new Extent(this.iBreite[0]+this.iBreite[1]));
		this.EXT().set_bLineWrapListHeader(true);
		this.set_Spalten(this.iBreite);
	}
	
	


	/**
	 * festlegen, wieviele zeilen gleichzeitig angezeigt werden
	 * @param zeilen
	 * @return
	 */
	public E2_GenericListComponentShowAddonDocuments set_Zeilen(int zeilen) {
		this.iAnzeigeAnzahlZeilen = zeilen;
		return this;
	}
	
	
	
	@Override
	public void set_cActual_Formated_DBContent_To_Mask_WhenVisisble(String value_from_db, String cMASK_STATUS, SQLResultMAP oResultMAP) 	throws myException {
		
		
		//hier feststellen, wie die basistabelle heisst:
		String tableNameNormalized = new Archiver_Normalized_Tablename(oResultMAP.get_oSQLFieldMAP().get_cMAIN_TABLE()).get_TableBaseName();
		String tableID = oResultMAP.get_cUNFormatedROW_ID();
		
		SEL sel = new SEL("*").FROM(ARCHIVMEDIEN.T())
						.WHERE(new vgl(ARCHIVMEDIEN.tablename,tableNameNormalized)).AND(new vgl(ARCHIVMEDIEN.id_table,tableID))
						.ORDERUP(ARCHIVMEDIEN.downloadname);
		
		
		//DEBUG.System_println("E2_GenericListComponentShowAddonDocuments: Query:"+sel.s());
		
		this.rlArchivmedien = new RECLIST_ARCHIVMEDIEN_ext(sel.s());
		
		this.build_zeilen(false);
		
	}

	
	private void build_zeilen(boolean full) throws myException {
		int iZahlInRecList = 0;
		int iZahlAngezeigt = 0;

		this.removeAll();
		this.al_textfields.clear();
		
		if (this.rlArchivmedien.size()>0) {
			for (int i=0; i<this.rlArchivmedien.get_vKeyValues().size(); i++) {
				RECORD_ARCHIVMEDIEN_ext recAM = new RECORD_ARCHIVMEDIEN_ext(this.rlArchivmedien.get(this.rlArchivmedien.get_vKeyValues().get(i)));
				ownTextField tf = new ownTextField(recAM.get_DOWNLOADNAME_cUF_NN(recAM.get_DATEINAME_cUF_NN("<-->")));
				this.al_textfields.add(tf);
				this.add(tf, E2_INSETS.I(1,0,1,0));
				this.add(new ownDownButton(recAM), E2_INSETS.I(1,0,1,0));
				iZahlAngezeigt++;
				if (iZahlAngezeigt>=this.iAnzeigeAnzahlZeilen && !full) {
					break;
				}
				iZahlInRecList++;
			}
		}
		
		if (!full && iZahlInRecList<this.rlArchivmedien.size()) {
			this.add(new ownButtonAll(), MyE2_Grid.LAYOUT_CENTER_BOTTOM(E2_INSETS.I(1,0,1,0), new E2_ColorDDark(), 2));
			this.setRowHeight(this.getSize()-1, new Extent(10));
		}
	}
	
	
	private class ownTextField extends MyE2_TextField {

		public ownTextField(String cText) {
			super(cText, E2_GenericListComponentShowAddonDocuments.this.iBreite[0], 500);
			this.setFont(new E2_FontPlain(-2));
			this.setBorder(new Border(0, new E2_ColorBase(), Border.STYLE_NONE));
			this.setBackground(new E2_ColorBase());
			this.setToolTipText(cText);
		}
	}
	
	
	private class ownDownButton extends  MyE2_Button {
		private RECORD_ARCHIVMEDIEN_ext recAM = null;

		public ownDownButton(RECORD_ARCHIVMEDIEN_ext p_recAM) throws myException {
			super(E2_ResourceIcon.get_RI("down_mini.png"));
			this.recAM = p_recAM;
			
			this.add_GlobalValidator(this.recAM.generate_ButtonValidator4Download());
			
			this.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					ownDownButton.this.recAM.starte__downLoad();
				}
			});
		}
	}
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			E2_GenericListComponentShowAddonDocuments comp = new E2_GenericListComponentShowAddonDocuments(this.iBreite);
			comp.setColumnWidth(0, this.getColumnWidth(0));
			return comp;
		} catch (myException e) {
			throw new myExceptionCopy(this.getClass().getCanonicalName()+" ... get_Copy-Error ...!"+e.get_ErrorMessage());
		}
	}
	
	

	
	
	private class ownButtonAll extends MyE2_Button {

		public ownButtonAll() {
			super("...");
			int iAnzahl = E2_GenericListComponentShowAddonDocuments.this.rlArchivmedien.size();
			this.setToolTipText(new MyE2_String("Alle ",true,""+iAnzahl, false," Dokumente anzeigen",true).CTrans());
			this.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					E2_GenericListComponentShowAddonDocuments.this.build_zeilen(true);
				}
			});
		}
		
	}

	@Override
	public void make_Look_Deleted(boolean bIsDeleted) {
		for (ownTextField tf: this.al_textfields) {
			tf.setFont(bibFONT.getLineThroughFont(tf.getFont(), true));
		}
	}
	
	@Override
	public void setForeColorActive(Color ForeColor) {
		for (ownTextField tf: this.al_textfields) {
			tf.setForeground(ForeColor);
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
		for (ownTextField tf: this.al_textfields) {
			return tf.getForeground();
		}
		return Color.BLACK;
	}
	
	@Override
	public Font get_Unmarked_Font() {
		for (ownTextField tf: this.al_textfields) {
			return tf.getFont();
		}
		return new E2_FontPlain();
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
	}

	
}
