package rohstoff.Echo2BusinessLogic.BEWEGUNG.ABNAHMEANGEBOTELISTE;

import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.app.Extent;
import nextapp.echo2.webcontainer.command.BrowserOpenWindowCommand;
import panter.gmbh.BasicTools.m2n.ENUM_M2N_CONTEXT;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainerWith_HTTP_PANE;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;
import panter.gmbh.Echo2.components.DB.MyE2_DB_PlaceHolder_NT;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.INTERNET;
import panter.gmbh.basics4project.DB_ENUMS.M2N;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
 
public class BSAAL_LIST_comp_showWebsites extends MyE2_DB_PlaceHolder_NT {

	public BSAAL_LIST_comp_showWebsites() throws myException {
		super();
	}

	@Override
	public void set_cActual_Formated_DBContent_To_Mask_WhenVisisble(String value_from_db, String cMASK_STATUS,	SQLResultMAP oResultMAP) throws myException {
		String id_adresse = oResultMAP.get_UnFormatedValue(BSAAL__CONST.SONDERSPALTEN.A_ID_ADRESSE.name());
		String id_user =    bibALL.get_ID_USER();
		
		String c_sql = "SELECT "+INTERNET.fullTabName()+".* FROM "+bibE2.cTO()+"."+INTERNET.fullTabName()
							+" INNER JOIN "+bibE2.cTO()+"."+ADRESSE.fullTabName()+	" ON ("+ADRESSE.id_adresse.tnfn()+"="+INTERNET.id_adresse.tnfn()
																					+" AND "+ADRESSE.id_adresse.tnfn()+"="+id_adresse
																					+")"
							+" INNER JOIN "+bibE2.cTO()+"."+M2N.fullTabName()+		" ON ("+M2N.table_id_2.tnfn()+"="+INTERNET.id_internet.tnfn()+
																					" AND "+M2N.m2n_context_enum.tnfn()+"='"+ENUM_M2N_CONTEXT.USER_WEBSEITE.name()+"'"+
																					" AND "+M2N.table_id_1.tnfn()+"="+id_user
																					+")";
		
		String c_sql4_std = "SELECT "+INTERNET.fullTabName()+".* FROM "+bibE2.cTO()+"."+INTERNET.fullTabName()
																					+" INNER JOIN "+bibE2.cTO()+"."+ADRESSE.fullTabName()+	" ON ("+ADRESSE.id_adresse.tnfn()+"="+INTERNET.id_adresse.tnfn()
																																			+" AND "+ADRESSE.id_adresse.tnfn()+"="+id_adresse
																																			+")"
																					+" WHERE "+new vgl_YN(INTERNET.ist_standard, true).s();

		RecList20 rl =  new RecList20(_TAB.internet)._fill(c_sql);
		
		if (rl.size()==0) {
			rl._fill(c_sql4_std);
		}
		if (rl.size()>0) {
			this._clear()._add(new popup_show_sites(rl), new RB_gld()._center_mid());
		} else {
			this._clear()._add(new RB_lab()._ri(E2_ResourceIcon.get_RI("world__.png"))._ttt("Es wurden keine Internetseiten für den Schnellzugriff definiert"), new RB_gld()._center_mid());
		}
	}

	
	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			return new BSAAL_LIST_comp_showWebsites();
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.getLocalizedMessage());
		}
	}
	
	
	
	private class popup_show_sites extends MyE2_PopUpMenue {
		
		private RecList20 rl_internet = null;
		
		public popup_show_sites(RecList20 p_rl_internet) throws myException	{
			super(E2_ResourceIcon.get_RI("world.png"),E2_ResourceIcon.get_RI("world__.png"),false);
			this.rl_internet = p_rl_internet;
			this.setToolTipText(new MyE2_String("Internet-Seiten (Benutzerdefinierte Auswahl)").CTrans());
			for (Rec20 r: this.rl_internet) {
				this.addButton(new own_url_button(r), true);
			}
		}
		
		private class own_url_button extends MyE2_Button {
			public own_url_button(Rec20 rec_url) throws myException {
				super(rec_url.get_ufs_dbVal(INTERNET.internet));
				this.reduce_length();
				this.add_oActionAgent(new actionOpenWeb(rec_url));
			}
			
			private  void reduce_length() {
				String url= this.getText();
				String c_ret = this.getText();
				if (c_ret.length()>40) {
					c_ret = c_ret.substring(0, 40)+"...";
					this.setText(c_ret);
					this.setToolTipText(url);
				}
			}

			
			private class actionOpenWeb extends XX_ActionAgent {
				private Rec20 rec_url = null;
				public actionOpenWeb(Rec20 p_rec_url) {
					super();
					this.rec_url = p_rec_url;
				}

				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					String url = this.rec_url.get_ufs_dbVal(INTERNET.internet);
					if (S.isFull(url)) {
						if (!((url.toLowerCase().startsWith("http://") || url.toLowerCase().startsWith("https://"))))	{
							url = "http://"+url;
						}
						
						if (rec_url.is_yes_db_val(INTERNET.open_own_browser)) {
							ApplicationInstance.getActive().enqueueCommand(
								     new BrowserOpenWindowCommand(url,"Infofenster","width=800,height=600"));
						} else {
							ownBasicContainer4Internet oPopUp = new ownBasicContainer4Internet(800,600);
							oPopUp.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800), new Extent(600), new MyE2_String("Internetansicht ..."));
							oPopUp.showWebsite(url);
						}
					}

				}
				
				// eigene klasse, damit das abspeichern der benutzerdefinierten groesse geht
				private class ownBasicContainer4Internet extends E2_BasicModuleContainerWith_HTTP_PANE	{
					public ownBasicContainer4Internet(int width, int height){
						super(width, height);
					}
				}
			}
		}
	}



	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
	}
}
