/**
 * rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.AbrechnungDienstleistung
 * @author martin
 * @date 06.09.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.AbrechnungDienstleistung;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Label;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.IF_agentSimpleV2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_V2;
import panter.gmbh.Echo2.ListAndMask.List.OneToManyTools.OneToMany;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.basics4project.DB_ENUMS.DLP_JOIN_WARENBEWG;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.PAIR;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VposTpaFuhre;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel_bez;

/**
 * @author martin
 * @date 06.09.2019
 *
 */
public class ADL__ListComponentFuhre extends  E2_UniversalListComponent {

	public static String key = "77224258-d0ba-11e9-826f-2a2ae2dbcce4";
	
	private ADL_ClearingDlpRelationsFuhre 	clearing = null;



	/**
	 * @author martin
	 * @date 06.09.2019
	 *
	 */
	public ADL__ListComponentFuhre() {
//		this.setTranslator(ADL_ENUM_TRANSLATOR.TAB_VPOS_TPA_FUHRE);
	}

	@Override
	public String key() throws myException {
		return ADL__ListComponentFuhre.key;
	}

	@Override
	public String userText() throws myException {
		return "DienstLstg.";
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
		this.clearing=null;
	}


	
	public void populate(SQLResultMAP resultMap) {

		this._clear();

		try {
			Long actualFuhreIdAusList = ((E2_ComponentMAP_V2) this.EXT().get_oComponentMAP()).getRec21().getIdLong();

			Rec21_VposTpaFuhre 	recFuhre = 								new Rec21_VposTpaFuhre(( (E2_ComponentMAP_V2) this.EXT().get_oComponentMAP()).getRec21());
			Rec21_VposTpaFuhre 	recFuhreMainIfDienstLeistungsFuhre = 	this.getMainFuhreIfPartOfServiceBlock(actualFuhreIdAusList);
			
			if (recFuhreMainIfDienstLeistungsFuhre!=null) {
				//dann ist die fuhre teil eines dienstleistungsblocks, aber nicht die hauptfuhre
				
				ADL_ClearingDlpRelationsFuhre clearingHelp = new ADL_ClearingDlpRelationsFuhre(recFuhreMainIfDienstLeistungsFuhre)._init();
				
				VEK<Long> allFuhrenIdsBelongToBlock = clearingHelp.getAllRelatedFuhrenIds(true);
				
				if (allFuhrenIdsBelongToBlock==null || allFuhrenIdsBelongToBlock.size()==0) {
					this._a(new RB_lab()._t("@ERROR")._fsa(6)._bi()._ttt(S.ms("Die Fuhre ist Teil eines Dienstleistungsblocks, kann aber nicht aufgeschluesselt werden !!")), new RB_gld()._center_mid());
				} else {
					
					VEK<String>    korrelationen = new VEK<String>();
					allFuhrenIdsBelongToBlock.stream().forEach(l->{korrelationen._a(l.toString());});
					
					E2_Button      btSammler = new E2_Button()._t(S.ms("DL"))._fsa(6)._b()._setShapeStyleStandard()._ttt(S.ms("Sucht die Fuhren (original und Dienstleistungen, die zusammenhängen!"));
					
					btSammler._aaa(()-> {
						this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to().saveStatus(bibMSG.MV());
						this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to().get_vectorSegmentation().clear();
						this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to().get_vectorSegmentation().addAll(korrelationen);
						this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to()._RebuildListWithActualIds();
					});

					this._a(btSammler._ttt(S.ms("Die Fuhre ist Teil eines Dienstleistungsblocks, hier können alle zughörigen angezeigt werden !!")), new RB_gld()._center_mid());
				}
				
				
			} else {
				
				clearing = new ADL_ClearingDlpRelationsFuhre(recFuhre)._init();

				if (clearing.isRelevant()) {
					
					
					
					
					//nur dann ist was darzustellen
					E2_Button btFehlendeRelationen = new E2_Button();
					E2_Button btKorrekteRelationen = new E2_Button();
					E2_Button btFalscheRelationen = new E2_Button();
					
					E2_Button btKorrekteRelationenInaktiv = new E2_Button();
					E2_Button btKorrekteRelationenMitFuhren = new E2_Button();
					E2_Button btFalscheRelationenMitFuhren = new E2_Button();
					
					
					btFehlendeRelationen._t(""+clearing.getDlpProfilesIdsMissing().size())._fsa(4)._setShapeStyleStandard(new E2_FontBold(4), new E2_ColorHelpBackground());
					btKorrekteRelationen._t(""+	(clearing.getDlpProfilesIdsCorrectExisting().size()-
												clearing.getIdsCorrectExistingDlpProfilesInactive().size()-
												clearing.getDlpProfilesIdsCorrectExistingActiveWithUndeletedFuhre().size())
												)._fsa(4)._setShapeStyleStandard(new E2_FontBold(4),Color.GREEN);
					btFalscheRelationen._t(""+clearing.getDlpProfilesIdsFalseExistingWithoutUndeletedFuhre().size())._fsa(4)._setShapeStyleStandard(new E2_FontBold(4), new E2_ColorAlarm());
					
					btKorrekteRelationenInaktiv._t(""+clearing.getIdsCorrectExistingDlpProfilesInactive().size())._fsa(4)._setShapeStyleStandard(new E2_FontBold(4),Color.YELLOW);
					btKorrekteRelationenMitFuhren._t(""+clearing.getDlpProfilesIdsCorrectExistingActiveWithUndeletedFuhre().size())._fsa(4)._setShapeStyleStandard(new E2_FontBold(4),Color.GREEN);
					btFalscheRelationenMitFuhren._t(""+clearing.getDlpProfilesIdsFalseExistingWithUndeletedFuhre().size())._fsa(4)._setShapeStyleStandard(new E2_FontBold(4), new E2_ColorAlarm());
					
					
					IF_agentSimpleV2 agentCreateRelations = ((e)-> {
							clearing._cleanUp();
							clearing._init();
							clearing._buildMissingRelations();
							clearing._init();
							//komponente neu laden
							populate(null);
					});
					
					IF_agentSimpleV2 agentStartPopup = ((e)-> {
						 new ADL_gridWithDienstleistungen(this,clearing)._showInPopup();
					});
					
					btFehlendeRelationen._aaa(agentCreateRelations)._ttt(S.ms("Anzahl gefundener Dienstleistungsprofile, die bei der Fuhre noch nicht vorbereitet wurden!"));
					btKorrekteRelationen._aaa(agentCreateRelations)._aaa(agentStartPopup)._ttt(S.ms("Anzahl gefundener Dienstleistungsprofile, die der Fuhre zugeordnet wurden!"));
					btFalscheRelationen._aaa(agentCreateRelations)._ttt(S.ms("Anzahl dieser Fuhre zugeordnete Profile, die der Fuhre zugeordnet sind, die aber nach der Profiltabelle nicht mit der Fuhre korrelieren !"));
					
					btKorrekteRelationenInaktiv._aaa(agentCreateRelations)._aaa(agentStartPopup)._ttt(S.ms("Korrekte Relationen, wurden deaktiviert"));
					btKorrekteRelationenMitFuhren._aaa(agentCreateRelations)._aaa(agentStartPopup)._ttt(S.ms("Korrekte Relationen mit vorhandener Dienstleistungsfuhre"));
					btFalscheRelationenMitFuhren._aaa(agentCreateRelations)._aaa(agentStartPopup)._ttt(S.ms("Nicht zutreffene Relationen mit vorhandener Dienstleistungsfuhre. \nFalsch zugeordnete Dienstleistungsfuhre müssen manuell gelöscht werden!"));
					
					E2_Button      btSammler = new E2_Button()._t(S.ms("DL-Fuhren"))._setShapeStandardTextButton()._fsa(-2)._i()._b()
													._ttt(S.ms("Sucht die Fuhren (original und Dienstleistungen, die zusammenhängen!"));
					VEK<String>    korrelationen = new VEK<String>()._a(actualFuhreIdAusList.toString());
					for (Rec21 recDlpJoin: clearing.getRecListDlpJoinWarenbeweg()) {
						if (recDlpJoin.getLongDbValue(DLP_JOIN_WARENBEWG.id_vpos_tpa_fuhre_dl)!=null) {
							korrelationen._a(recDlpJoin.getLongDbValue(DLP_JOIN_WARENBEWG.id_vpos_tpa_fuhre_dl).toString());
						}
					}
					
					btSammler._aaa(()-> {
						this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to().saveStatus(bibMSG.MV());
						this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to().get_vectorSegmentation().clear();
						this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to().get_vectorSegmentation().addAll(korrelationen);
						this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to()._RebuildListWithActualIds();
					});

					
					this._s(4)	._a(new RB_lab()._t(S.ms("Profile:"))._fsa(-2)._i(), new RB_gld()._ins(4)._left_mid())
								._a(btFehlendeRelationen, new RB_gld()._ins(4,2,4,2)._col_back_help())
								._a(btKorrekteRelationen, new RB_gld()._ins(4,2,4,2)._col_back_green())
								._a(btFalscheRelationen, new RB_gld()._ins(4,2,4,2)._col_back_alarm())
								._a(btSammler, 				new RB_gld()._ins(4,2,4,2)._left_mid())
								._a(btKorrekteRelationenInaktiv, new RB_gld()._ins(4,2,4,2)._col_back_help())
								._a(btKorrekteRelationenMitFuhren, new RB_gld()._ins(4,2,4,2)._col_back_green())
								._a(btFalscheRelationenMitFuhren, new RB_gld()._ins(4,2,4,2)._col_back_alarm())
								;
			
					//falls eine fuhre bereits den abschluss-check hinter sich hat, dann 
					
					
					
				} else {
					// dann bleibt der fall leer
					this._a(new RB_lab()._t("-")._fsa(6)._bi()
							._ttt(S.ms("Die Fuhre hat keine Dienstleistungsnachfolger")),
							new RB_gld()._center_mid());
				}
			}
		} catch (myException e) {
			e.printStackTrace();
			Label lab = new Label();
			lab.setIcon(E2_ResourceIcon.get_RI("warnschild_22.png"));
			lab.setToolTipText(e.getOriginalMessage());
			this._a(lab);
		} catch (Exception e) {
			e.printStackTrace();
			Label lab = new Label();
			lab.setIcon(E2_ResourceIcon.get_RI("warnschild_22.png"));
			lab.setToolTipText(e.getLocalizedMessage());
			this._a(lab);
		}
	}

	

	
	public Rec21_adresse getRecOfKundenLager() {
		return null;
	}

	
	public Rec21_artikel_bez getRecArtbezSorte() {
		return null;
	}

	
	public OneToMany getOneToMany() {
		return new OneToMany()	._setTabOne(_TAB.vpos_tpa_fuhre)
								._setTabMany(_TAB.vpos_tpa_fuhre)
								._setTabRelationTable(_TAB.dlp_join_warenbewg)
								._setRelationOneSideToRelation(new PAIR<IF_Field,IF_Field>(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre,DLP_JOIN_WARENBEWG.id_vpos_tpa_fuhre))
								._setRelationManySideToRelation(new PAIR<IF_Field,IF_Field>(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre,DLP_JOIN_WARENBEWG.id_vpos_tpa_fuhre_dl))
								;
		
	}

	
	public Rec21 getRecBewegungExtender() {
		return null;
	}

	
	public E2_BasicModuleContainer generatePopUp() throws myException {
		return null;
	}


	
	
	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
	}

	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		ADL__ListComponentFuhre copy = new ADL__ListComponentFuhre();
		copy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(copy));
		return copy;
	}


	
	public ADL_ClearingDlpRelationsFuhre getClearing() {
		return clearing;
	}

	
	/**
	 * wenn die id eine folgefuhre ist, wird dir zugehoerige main-fuhre zurueckgegeben, oder null
	 * @author martin
	 * @date 16.09.2019
	 *
	 * @return
	 * @throws myException 
	 */
	public Rec21_VposTpaFuhre getMainFuhreIfPartOfServiceBlock(Long idVposTpaFuhre) throws myException {
		SEL selDlFuhre = new SEL(_TAB.dlp_join_warenbewg).FROM(_TAB.dlp_join_warenbewg)
				.WHERE(new vglParam(DLP_JOIN_WARENBEWG.id_vpos_tpa_fuhre_dl));

		RecList21 rlJoinWarenbeweg = new RecList21(_TAB.dlp_join_warenbewg)._fill(new SqlStringExtended(selDlFuhre.s())._addParameter(new Param_Long(idVposTpaFuhre))); 
		
		//darf nur einen eintrag haben, sonst fehler
		if (rlJoinWarenbeweg.size()==0) {
			return null;
		} else if (rlJoinWarenbeweg.size()==1) {
			Rec21 recFuhreMain = rlJoinWarenbeweg.get(0).get_up_Rec21(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre);
			if (recFuhreMain!=null) {
				return new Rec21_VposTpaFuhre(recFuhreMain);
			} else {
				throw  new myException("Error: Cannot find main-Fuhre to Dl-Fuhre-id: "+ idVposTpaFuhre.toString()+ "! Code:<6426b0d4-0b80-11ea-8d71-362b9e155667>");
			}
		} else {
			throw  new myException("Error: fount multi-joins for Dl-Fuhre-id: "+ idVposTpaFuhre.toString()+ "! Code:<ab67f2b4-0b80-11ea-9a9f-362b9e155667>");
		}
		

	}
	
	
}
