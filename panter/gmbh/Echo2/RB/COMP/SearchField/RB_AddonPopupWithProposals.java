/**
 * panter.gmbh.Echo2.RB.COMP.SearchField
 * @author martin
 * @date 11.01.2019
 * 
 */
package panter.gmbh.Echo2.RB.COMP.SearchField;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_PopUp;
import panter.gmbh.basics4project.DB_ENUMS.SUCHVORSCHLAG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.TRIPLE;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 11.01.2019
 * addon mit button, der und hinterlegte suchfelder und spezialsuche ausfuehren kann
 */
public class RB_AddonPopupWithProposals extends E2_PopUp {

	private EnSearchProposalKeys	m_proposalKey = null;
	private RB_SearchField 			searchField;
	
	public RB_AddonPopupWithProposals(EnSearchProposalKeys proposalKey, RB_SearchField sf) {
		super();
		
		this.m_proposalKey = proposalKey;
		this.searchField = sf;
		this._set_icon_activ(E2_ResourceIcon.get_RI("suche_spezial.png"));
		
		this.setPopUpBorder(new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));
		
		this._setCol_popup_border(new E2_ColorBase())._setCol_rol_over_background(new E2_ColorBase());
		
		this.setStyle(E2_PopUp.getDefaultStyle());
		
		this.rebuild();
		
	}

	
	@Override
    protected void rebuild() {
        this.removeAll();
        
        this.get_grid_aussen().removeAll();
        this.get_oContainerEx().removeAll();
        
        this.get_oContainerEx().setWidth(this.getWidth());
        this.get_oContainerEx().setHeight(this.getHeight());
        
    	this.get_grid_aussen()._bo_b()._a(this.get_gridInnen(), new RB_gld()._ins(0));
    	this.get_oContainerEx().add(this.get_grid_aussen());
        this.setPopUp(this.get_oContainerEx());

        this.get_grid_innen()._w100()._clear();

        this.get_grid_innen()._a(new BtEditProposals(), new RB_gld()._ins(2));
		
        if (this.m_proposalKey!=null) {    //this.m_proposalKey ist beim der rebuild-aufruf des konstuktors der superklasse noch null
        	HMAP<Long, Rec21>  proposals = this.m_proposalKey.getProposals();
        
			for (Rec21 r: proposals.values()) {
				this.get_grid_innen()._a(new StartSearchFromProposalButton(r), new RB_gld()._ins(2));
			}
        }
        
        this.activate_color();
    }

	
	//button um zu editieren (immer oben) 
	private class BtEditProposals extends E2_Button {
		
		public BtEditProposals() {
			super();
			
			this._t(S.ms("Vorschläge Bearbeiten"))._i()._aaa(()-> {
				new OwnContainerProposalsEditor().CREATE_AND_SHOW_POPUPWINDOW(S.ms("Suchvorschläge bearbeiten"));

			});
		}
		
	}
	
	
	//button zum ausloesen der suche
	private class StartSearchFromProposalButton extends E2_Button {
		
		public StartSearchFromProposalButton(Rec21 proposal) {
			super();
			
			try {
				this._t(proposal.getUfs(SUCHVORSCHLAG.beschriftung));
				this._width(new Extent(100, Extent.PERCENT));
				
				this._aaa(()-> {
					searchField.get_tf_search_input()._t(proposal.getUfs(SUCHVORSCHLAG.such_worte));
					RB_AddonPopupWithProposals.this.closeMeAndMotherPopup();
				});
				
				this._aaa(searchField.generate_StartSearchAction());
				
			} catch (myException e) {
				e.printStackTrace();
			}
		}
	}
	

	
	private class OwnContainerProposalsEditor extends E2_BasicModuleContainer {

		private E2_Grid  				gridProposals = new E2_Grid()._setSize(150,150,20,20) ;
		
		//zuerst die suchbegriffe, dann die listenanzeige(benutzertext)
		private VEK<TRIPLE<RB_TextField, RB_TextField, Rec21>> inputFields = new VEK<>();
		
		public OwnContainerProposalsEditor() throws myException {
			super();
			
			this.add(gridProposals, E2_INSETS.I(2,2,2,2));
			
			this.set_oExtWidth(new Extent(400));
			this.set_oExtHeight(new Extent(500));
			
			
			this.rebuild();
		}

		
		private void rebuild() {
			this.gridProposals._clear();
			
			RB_AddonPopupWithProposals oThis = RB_AddonPopupWithProposals.this;

			HMAP<Long, Rec21> hm = oThis.m_proposalKey.getProposals(); 
			
			inputFields.clear();
			
			try {
				
				RB_gld ld = new RB_gld()._ins(2)._center_mid();
				RB_gld ldTitle = new RB_gld()._ins(2)._center_mid()._col_back_dd(); 
				 
				
				//eine zeile fuer neuerfassung
				RB_TextField tfSuchText = 		new RB_TextField()._w(150);
				RB_TextField tfBenutzerText = 	new RB_TextField()._w(150);
				Rec21 		 rec = 				new Rec21(_TAB.suchvorschlag);

				TRIPLE<RB_TextField, RB_TextField, Rec21> tr = new TRIPLE<RB_TextField, RB_TextField, Rec21>(tfSuchText, tfBenutzerText, rec);
				
				this.gridProposals._a(new RB_lab()._tr("Such-Vorschlag")._i(),ldTitle)._a(new RB_lab()._tr("Beschriftung")._i(),ldTitle)._a()._a();
				
				this.gridProposals._a(tr.getVal1(),ld)._a(tr.getVal2(),ld)._a(new SaveButton(tr),ld)._a();

				for (Rec21 r: hm.values()) {
					RB_TextField tfSuch = 			new RB_TextField()._w(150)._t(r.getUfs(SUCHVORSCHLAG.such_worte));
					RB_TextField tfText4Button= 	new RB_TextField()._w(150)._t(r.getUfs(SUCHVORSCHLAG.beschriftung));
					TRIPLE<RB_TextField, RB_TextField, Rec21> t = new TRIPLE<RB_TextField, RB_TextField, Rec21>(tfSuch, tfText4Button, r);
					
					this.gridProposals._a(tfSuch,ld)._a(tfText4Button,ld)._a(new SaveButton(t),ld)._a(new DeleteButton(t),ld);
				}
				
			} catch (myException e) {
				e.printStackTrace();
				bibMSG.add_MESSAGE(e.get_ErrorMessage());
			}
			
		}

		
		private class SaveButton extends E2_Button {

			TRIPLE<RB_TextField, RB_TextField, Rec21> m_proposal = null;
			
			public SaveButton(TRIPLE<RB_TextField, RB_TextField, Rec21> proposal) {
				super();
				RB_AddonPopupWithProposals oThis = RB_AddonPopupWithProposals.this;

				
				this._image("save.png", false);
				this.m_proposal = proposal;
				
				Rec21 recToSave = this.m_proposal.getVal3();
				this._aaa(()-> {
					
					if (S.isEmpty(m_proposal.getVal1().getText()) || S.isEmpty(m_proposal.getVal2().getText())) {
						bibMSG.MV()._addAlarm(S.ms("Bitte alles ausfüllen !"));
						return;
					}
					if (m_proposal.getVal1().getText().length()>100) {
						bibMSG.MV()._addAlarm(S.ms("Der Suchbegriff ist zu lang!"));
						return;
					}
					if (m_proposal.getVal2().getText().length()>100) {
						bibMSG.MV()._addAlarm(S.ms("Der Listentext ist zu lang!"));
						return;
					}
					m_proposal.getVal3()._setNewVal(SUCHVORSCHLAG.such_enum, oThis.m_proposalKey.db_val(), bibMSG.MV());
					m_proposal.getVal3()._setNewVal(SUCHVORSCHLAG.such_worte, m_proposal.getVal1().getText(), bibMSG.MV());
					m_proposal.getVal3()._setNewVal(SUCHVORSCHLAG.beschriftung,  m_proposal.getVal2().getText(), bibMSG.MV());
					m_proposal.getVal3()._setNewVal(SUCHVORSCHLAG.id_user,       bibALL.get_RECORD_USER().get_ID_USER_lValue(-1l), bibMSG.MV());
					m_proposal.getVal3()._setNewVal(SUCHVORSCHLAG.sortierung,    0l, bibMSG.MV());
					if (bibMSG.get_bIsOK()) {
						recToSave._SAVE(true, bibMSG.MV());
						if (bibMSG.MV().isOK()) {
							bibMSG.MV()._addInfo(S.ms("Suchvorschlag gespeichert!"));
						}
					}
					OwnContainerProposalsEditor.this.rebuild();
				});
			}
		}
		
		private class DeleteButton extends E2_Button {

			TRIPLE<RB_TextField, RB_TextField, Rec21> m_proposal = null;
			
			public DeleteButton(TRIPLE<RB_TextField, RB_TextField, Rec21> proposal) {
				super();
				this._image("delete.png", false);
				this.m_proposal = proposal;

				Rec21 recToSave = this.m_proposal.getVal3();
				this._aaa(()-> {
					bibMSG.MV()._add(recToSave.DELETE());
					OwnContainerProposalsEditor.this.rebuild();
				});
			}
		}
		
	}

	
}
