package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.EINHEIT;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON_TRAKT;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_STD;
import panter.gmbh.basics4project.DB_ENUMS.WAEHRUNG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EINHEIT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_STD;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_STD_ANGEBOT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WAEHRUNG;
import panter.gmbh.indep.exceptions.myException;


public class RECORD_VPOS_STD_ext extends RECORD_VPOS_STD {

	public RECORD_VPOS_STD_ext(RECORD_VPOS_STD recordOrig) {
		super(recordOrig);
	}

	
	public void fill_grid_with_infos(E2_Grid grid) throws myException {
		
		RB_gld gl  = new RB_gld()._ins(E2_INSETS.I(1,0,2,1));

		grid.removeAll();
		grid.setStyle(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		grid._setSize(50,50,50,50);
				
		RECORD_VPOS_STD_ANGEBOT	vkt = this.get_DOWN_RECORD_LIST_VPOS_STD_ANGEBOT_id_vpos_std().get(0);
		RECORD_ADRESSE   		ad  = this.get_UP_RECORD_VKOPF_STD_id_vkopf_std().get_UP_RECORD_ADRESSE_id_adresse();
		RECORD_ARTIKEL          art = this.get_UP_RECORD_ARTIKEL_id_artikel();
		RECORD_WAEHRUNG         waeh = this.get_UP_RECORD_WAEHRUNG_id_waehrung_fremd();
				
		String eh_kurz = "";
		if (art!=null) {
			RECORD_EINHEIT eh = art.get_UP_RECORD_EINHEIT_id_einheit();
			eh_kurz = eh.fs(EINHEIT.einheitkurz, ""); 
		}
				
		String waehrung_kurz = "";
		if (waeh!=null) {
			waehrung_kurz = waeh.fs(WAEHRUNG.kurzbezeichnung, ""); 
		}
				
		RB_gld gl1	=	new RB_gld()._span(4)._ins(2,0,2,1);
		RB_gld gl2	=	new RB_gld()._span(3)._right_top()._ins(2,0,2,1);
		
				
		
		E2_Grid g_datum = new E2_Grid()._setSize(70,10,70)	._a(new RB_lab(vkt.fs(VPOS_KON_TRAKT.gueltig_von,""))._fsa(-2)._i(), 	new RB_gld()._left_mid())
															._a(new RB_lab(" bis ")._fsa(-2)._i(), 									new RB_gld()._center_mid())
															._a(new RB_lab(vkt.fs(VPOS_KON_TRAKT.gueltig_bis,""))._fsa(-2)._i(), 	new RB_gld()._right_mid());
		
		grid._a(new RB_lab("Angebot (ID): " + this.ufs(VPOS_STD.id_vpos_std))._b()._fsa(-2), gl1);
		grid._a(new RB_lab(ad.fs(ADRESSE.name1,"")+" "+ad.fs(ADRESSE.ort,""))._fsa(-2)._i(),												gl1);
		grid._a(new RB_lab(this.fs(VPOS_KON.anr1,"")+"-"+this.fs(VPOS_KON.anr2,"")+" "+this.fs(VPOS_KON.artbez1,""))._fsa(-2)._i(),			gl1);
		
		grid._a(new RB_lab()._tr("Menge")._fsa(-2),gl)._a(new RB_lab(this.fs(VPOS_KON.anzahl,"")+" "+eh_kurz)._fsa(-2)._i(),				gl2);
		grid._a(new RB_lab()._tr("Preis")._fsa(-2),gl)._a(new RB_lab(this.fs(VPOS_KON.einzelpreis_fw,"")+" "+waehrung_kurz)._fsa(-2)._i(),	gl2);
		grid._a(new RB_lab()._tr("Gültig")._fsa(-2),gl)._a(g_datum, 																		gl2);
		
		
	}

	
//	public void fill_grid_with_infos_small(MyE2_Grid grid) throws myException {
//		
//		RB_gld gl  = new RB_gld()._ins(E2_INSETS.I(1,1,3,1))._left_mid();
//
//
//		grid.removeAll();
//		grid.setStyle(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
//		grid._setSize(50,50,50);
//				
//		RECORD_VPOS_STD_ANGEBOT	vkt = this.get_DOWN_RECORD_LIST_VPOS_STD_ANGEBOT_id_vpos_std().get(0);
//		RECORD_ADRESSE   		ad  = this.get_UP_RECORD_VKOPF_STD_id_vkopf_std().get_UP_RECORD_ADRESSE_id_adresse();
//		RECORD_WAEHRUNG         waeh = this.get_UP_RECORD_WAEHRUNG_id_waehrung_fremd();
//				
//		String waehrung_kurz = "";
//		if (waeh!=null) {
//			waehrung_kurz = waeh.fs(WAEHRUNG.kurzbezeichnung, ""); 
//		}
//				
//				
//				
//		//zeile1
//		grid._add(new lb_untrans(ad.fs(ADRESSE.name1,"")+" "+ad.fs(ADRESSE.ort,"")),gl._c()._span(3));
//
//		//zeile2
//		grid._add(new lb_untrans(this.fs(VPOS_KON.anr1,"")+"-"+this.fs(VPOS_KON.anr2,"")+" "+this.fs(VPOS_KON.artbez1,"")),gl._c()._span(3));
//
//		//zeile3
//		grid._add(new lb_untrans(this.fs(VPOS_KON.einzelpreis_fw,"")+" "+waehrung_kurz),gl._c()._left_mid());
//		grid._add(new lb_untrans(vkt.fs(VPOS_KON_TRAKT.gueltig_von,"")),gl._c()._center_mid());
//		grid._add(new lb_untrans(vkt.fs(VPOS_KON_TRAKT.gueltig_bis,"")),gl._c()._center_mid());
//		
//	}

	
	
//	private class lb_untrans extends MyE2_Label {
//		public lb_untrans(String cText) {
//			super(new MyE2_String(cText,false));
//			this.setFont(new E2_FontPlain(-2));
//		}
//	}

	
}
