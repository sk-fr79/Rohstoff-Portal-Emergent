/**
 * panter.gmbh.Echo2.RB.HIGHLEVEL
 * @author martin
 * @date 19.08.2019
 * 
 */
package panter.gmbh.Echo2.RB.HIGHLEVEL;

import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.RB.COMP.SelV2.RB_SelFieldV2Deep3S;
import panter.gmbh.Echo2.RB.COMP.SelV2.RB_SelectCascading;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_GRUPPE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 19.08.2019
 * erzeugt eine flexibles selectfield mit id_artikel_bez als wert und mit
 * 3-Tiefen (fuer Hauptartikel mit  Gruppe): ARTIKEL_GRUPPE -> ARTIKEL -> ARTKEL_BEZ  
 * 2-Tiefen (fuer Hauptartikel ohne Gruppe):  ARTIKEL -> ARTKEL_BEZ
 * flaches menue (shadow) fuer inaktive Artikel oder Artikelbezeichnungen
 */
public class RB_SelFieldV2ArtikelBez extends RB_SelFieldV2Deep3S {


	private HMAP<String, HMAP<String,HMAP<String,String>>>  	hmapSubMenues3 = new HMAP<>();
	private HMAP<String, HMAP<String,String>>  					hmapSubMenues = new HMAP<>();
	private HMAP<String, String>  								hmapShadow = new HMAP<>();
	
	
	/**
	 * 
	 * @author martin
	 * @date 20.08.2019
	 *
	 * @param includedIds
	 * @throws myException
	 */
	public RB_SelFieldV2ArtikelBez() throws myException {
		super();
	}

	

	
	/**
	 * 
	 * @author martin
	 * @date 21.08.2019
	 *
	 * @param includedArtikelbezIds to include in list (when list empty, then all included)
	 * @return
	 * @throws myException
	 */
	public RB_SelFieldV2ArtikelBez _initMenueMaps(VEK<Long> includedArtikelbezIds) throws myException {
		
		SEL selArtBez = new SEL(_TAB.artikel_bez)
								.ADDFIELD(ARTIKEL.anr1)
								.ADDFIELD(ARTIKEL.artbez1.tnfn(),"ARTIKEL_ARTBEZ1")
								.ADDFIELD(ARTIKEL.artbez2.tnfn(),"ARTIKEL_ARTBEZ2")
								.ADDFIELD(ARTIKEL.aktiv.tnfn(),"ARTIKEL_AKTIV")
								.ADDFIELD(ARTIKEL_GRUPPE.gruppe)
							.FROM(_TAB.artikel_bez)
							.LEFTOUTER(_TAB.artikel,ARTIKEL_BEZ.id_artikel,ARTIKEL.id_artikel)
							.LEFTOUTER(_TAB.artikel_gruppe,ARTIKEL.id_artikel_gruppe,ARTIKEL_GRUPPE.id_artikel_gruppe)
							.WHERE(new vgl(ARTIKEL.id_mandant, bibSES.get_ID_MANDANT_UF()))
							.ORDERUP(ARTIKEL_GRUPPE.gruppe)
							.ORDERUP(ARTIKEL.anr1)
							.ORDERUP(ARTIKEL_BEZ.anr2)
							.ORDERUP(ARTIKEL_BEZ.artbez1.fn())
							;

		RecList21 rlArtBez =  new RecList21(_TAB.artikel_bez)._fill(new SqlStringExtended(selArtBez.s()));
		
		//jetzt die gruppen, hauptartikel und shadow-artikel fuellen
		VEK<String> alleGruppen = new VEK<>();
		for (Rec21 r: rlArtBez) {
			if (S.isFull(r.getOverHeadValueUF(ARTIKEL_GRUPPE.gruppe))) {
				alleGruppen._addIfNotIn(r.getOverHeadValueUF(ARTIKEL_GRUPPE.gruppe));
			}
		}
		
		//jetzt die 3-stufigen
		for (String g: alleGruppen) {
			HMAP<String,HMAP<String,String>> map = new HMAP<>();

			//dann alle hauptartikel der Artikelgruppe suchen
			VEK<String> artikels = new VEK<>();
			
			for (Rec21 r: rlArtBez) {
				if (S.NN(r.getOverHeadValueUF(ARTIKEL_GRUPPE.gruppe)).equals(g)) {
					artikels._addIfNotIn(r.getOverHeadValueUF(ARTIKEL.anr1)+" "+r.getOverHeadValue("ARTIKEL_ARTBEZ1"));
				}
			}
			
			//jetzt einsortieren
			for (String artikel: artikels) {
				HMAP<String,String> mapHauptArtikel= new HMAP<>();
				for (Rec21 r: rlArtBez) {
					String btText = genButtonLabel(r);
					this.getHmButtonAndTooltips()._put(r.getFs(ARTIKEL_BEZ.id_artikel_bez), genButtonTooltip(r));
					
					if (S.NN(r.getOverHeadValueUF(ARTIKEL_GRUPPE.gruppe)).equals(g) && (r.getOverHeadValueUF(ARTIKEL.anr1)+" "+r.getOverHeadValue("ARTIKEL_ARTBEZ1")).equals(artikel)) {
						if ( S.NN(r.getOverHeadValue("ARTIKEL_AKTIV"),"N").equals("N")  || r.is_no_db_val(ARTIKEL_BEZ.aktiv) || !useArtikel(includedArtikelbezIds, r.getIdLong()) ) {
							hmapShadow.put(r.getFs(ARTIKEL_BEZ.id_artikel_bez), btText);
						} else {
							mapHauptArtikel.put(r.getFs(ARTIKEL_BEZ.id_artikel_bez),btText);
						}
					}
				}
				map.put(artikel,mapHauptArtikel);
			}
			hmapSubMenues3._put(g+" ("+S.ms("Artikelgruppe").CTrans()+")", map);
		}

		
		//dann die 2-stufigen

		//dann alle hauptartikel ohne Artikelgruppe suchen
		VEK<String> artikels = new VEK<>();
			
		for (Rec21 r: rlArtBez) {
			if (S.isEmpty(r.getOverHeadValueUF(ARTIKEL_GRUPPE.gruppe))) {
				artikels._addIfNotIn(r.getOverHeadValueUF(ARTIKEL.anr1)+" "+r.getOverHeadValue("ARTIKEL_ARTBEZ1"));
			}
		}
		
//		long count =0;
	   	DEBUG._printTimeStamp("_initMenueMaps 6");
		
		//jetzt einsortieren
		for (String artikel: artikels) {
			HMAP<String,String> mapHauptArtikel= new HMAP<>();
			for (Rec21 r: rlArtBez) {
				if ((r.getOverHeadValueUF(ARTIKEL.anr1)+" "+r.getOverHeadValue("ARTIKEL_ARTBEZ1")).equals(artikel)) {
//					count++;
					String btText = genButtonLabel(r);
					this.getHmButtonAndTooltips()._put(r.getFs(ARTIKEL_BEZ.id_artikel_bez), genButtonTooltip(r));
					if ( S.NN(r.getOverHeadValue("ARTIKEL_AKTIV"),"N").equals("N")  || r.is_no_db_val(ARTIKEL_BEZ.aktiv)  || !useArtikel(includedArtikelbezIds, r.getIdLong()) ) {
						hmapShadow.put(r.getFs(ARTIKEL_BEZ.id_artikel_bez), btText);
					} else {
						mapHauptArtikel.put(r.getFs(ARTIKEL_BEZ.id_artikel_bez),btText);
					}
				}
			}
			hmapSubMenues.put(artikel, mapHauptArtikel);
		}

	   //	DEBUG._printTimeStamp("_initMenueMaps 7 -- "+count);
		
		return this;
	}
	
	
	private String genButtonLabel(Rec21 artikel) throws myException {
		return "["+artikel.getOverHeadValueUF(ARTIKEL.anr1)+"]-["+artikel.getFs(ARTIKEL_BEZ.anr2)+"] "
					+artikel.getFs(ARTIKEL_BEZ.artbez1)+" ("+artikel.getFs(ARTIKEL_BEZ.id_artikel_bez)+")";
	}
	
	private String genButtonTooltip(Rec21 artikel) throws myException {
		return "["+artikel.getOverHeadValueUF(ARTIKEL.anr1)+"]- ["+artikel.getFs(ARTIKEL_BEZ.anr2)+"] \n"
					+artikel.getFs(ARTIKEL_BEZ.artbez1)+"\n"
					+(S.isFull(artikel.getFs(ARTIKEL_BEZ.artbez2))?artikel.getFs(ARTIKEL_BEZ.artbez2)+"\n":"")
					+"--------------------------------------\n"
					+S.ms("Hauptartikel:").CTrans()+"\n"
					+artikel.getOverHeadValue("ARTIKEL_ARTBEZ1")+"\n"
					+(S.isFull(artikel.getOverHeadValue("ARTIKEL_ARTBEZ2"))?artikel.getOverHeadValue("ARTIKEL_ARTBEZ2")+"\n":"")
					+"--------------------------------------\n"
					+S.ms("ID-Artikel-Bez").CTrans()+":  "+artikel.getFs(ARTIKEL_BEZ.id_artikel_bez)+"\n"
					+S.ms("ID-Artikel").CTrans()+":  "+artikel.getFs(ARTIKEL_BEZ.id_artikel)+"\n"
					;
	}

	
	
	private boolean useArtikel(VEK<Long> positivListe, Long idArtBez) {
		return positivListe==null||positivListe.size()==0||positivListe.contains(idArtBez);
	}
	
	
	/**
	 * 
	 * @author martin
	 * @date 20.08.2019
	 *
	 * @param hmapSubMenues3
	 * @param hmapSubMenues
	 * @param hmapShadow
	 * @return
	 */
	public RB_SelFieldV2ArtikelBez _setMenueMaps(	HMAP<String, HMAP<String,HMAP<String,String>>> hmapSubMenues3, 
													HMAP<String, HMAP<String,String>> 	hmapSubMenues, 
													HMAP<String, String> hmapShadow) {
		
		this.hmapSubMenues3 = hmapSubMenues3;
		this.hmapSubMenues = hmapSubMenues;
		this.hmapShadow = hmapShadow;
		
		return this;
	}
	
	
	/**
	 * 
	 * @author martin
	 * @date 20.08.2019
	 *
	 * @param includedArtikelbezIds (unformated strings)
	 * @return
	 * @throws myException
	 */
	public RB_SelFieldV2ArtikelBez _init() throws myException {
		
		this._setWidthTextField(250)._setWidthOfDropDownBlock(300);
		this._populate(hmapSubMenues3,hmapSubMenues, new HMAP<String,String>(), hmapShadow );
		this._setAutomaticButtonWidth();
		return this;
	}

	
	
	

	public HMAP<String, HMAP<String, HMAP<String, String>>> getHmapSubMenues3() {
		return hmapSubMenues3;
	}


	public HMAP<String, HMAP<String, String>> getHmapSubMenues() {
		return hmapSubMenues;
	}


	public HMAP<String, String> getHmapShadow() {
		return hmapShadow;
	}


	@Override
	public RB_SelFieldV2ArtikelBez _render() throws myException {
		super._render();
		return this;
	}
	
	
	public void setShapeOfActionButtonOutside(RB_SelectCascading<String>.MenueButton button, String idUf) {
		if (S.isFull(this.getHmButtonAndTooltips().get(idUf))) {
			button.setToolTipText(this.getHmButtonAndTooltips().get(idUf));
		}
	}
	
	public void setHighLightStatusOfActualValueButtonOutside(RB_SelectCascading<String>.MenueButton bt) {
		bt.setBackground(new E2_ColorDDDark());
	}

	
	@Override
	public void resetHighLightStatusOfActualValueButtonOutside(RB_SelectCascading<String>.MenueButton bt) {
		bt.setBackground(new E2_ColorDark());
	}

}
