/**
 * panter.gmbh.Echo2.RB.HIGHLEVEL
 * @author martin
 * @date 03.05.2019
 * 
 */
package panter.gmbh.Echo2.RB.HIGHLEVEL;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLight;
import panter.gmbh.Echo2.RB.COMP.SelV2.RB_SelFieldV2;
import panter.gmbh.Echo2.RB.COMP.SelV2.RB_SelectCascading;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 06.05.2019
 *
 */
public class RB_HL_SelectUsers extends RB_SelFieldV2 {

	public RB_HL_SelectUsers() throws myException {
		super();
		
		And bedAktivUndSachbearbeiter = new And()
							.and(new vglParam(USER.aktiv))
							.and(new vglParam(USER.ist_verwaltung))
							.and(new vglParam(USER.id_mandant))
							;
		
		And bedInAktiv = new And()
							.and(new vglParam(USER.aktiv))
							.and(new vglParam(USER.id_mandant))
							;
		
		And bedSonstige= new And()
							.and(new vglParam(USER.aktiv))
							.and(new vglParam(USER.ist_verwaltung))
							.and(new vglParam(USER.id_mandant))
							;

		SEL selAktivUndSachbearbeiter = new SEL(_TAB.user).FROM(_TAB.user).WHERE(bedAktivUndSachbearbeiter).ORDERUP(USER.name1).ORDERUP(USER.vorname);
		SEL selInAktiv =				new SEL(_TAB.user).FROM(_TAB.user).WHERE(bedInAktiv).ORDERUP(USER.name1).ORDERUP(USER.vorname);
		SEL selSonstige =				new SEL(_TAB.user).FROM(_TAB.user).WHERE(bedSonstige).ORDERUP(USER.name1).ORDERUP(USER.vorname);

		DEBUG._print(selAktivUndSachbearbeiter.s());
		DEBUG._print(selInAktiv.s());
		DEBUG._print(selSonstige.s());
		
		SqlStringExtended extAktivUndSachbearbeiter = new SqlStringExtended(selAktivUndSachbearbeiter.s())._addParameters(
																										new VEK<ParamDataObject>()
																											._a(new Param_String("","Y"))
																											._a(new Param_String("","Y"))
																											._a(new Param_Long(new MyLong(bibALL.get_ID_MANDANT()).get_lValue()))
																												);

		SqlStringExtended extInAktiv = new SqlStringExtended(selInAktiv.s())._addParameters(
														new VEK<ParamDataObject>()
															._a(new Param_String("","N"))
															._a(new Param_Long(new MyLong(bibALL.get_ID_MANDANT()).get_lValue()))
																);

		SqlStringExtended extSonstige = new SqlStringExtended(selSonstige.s())._addParameters(
														new VEK<ParamDataObject>()
														._a(new Param_String("","Y"))
														._a(new Param_String("","N"))
														._a(new Param_Long(new MyLong(bibALL.get_ID_MANDANT()).get_lValue()))
																);
		
		
		VEK<Rec21>   vAktivUndSachbearbeiter = 	new RecList21(_TAB.user)._fill(extAktivUndSachbearbeiter).getVEK();
		VEK<Rec21>   vInactiv = 				new RecList21(_TAB.user)._fill(extInAktiv).getVEK();
		VEK<Rec21>   vNichtSachbearbeiter = 	new RecList21(_TAB.user)._fill(extSonstige).getVEK();
		
		HMAP<String, String> 				hmItemsSachbearbeiter = 		new HMAP<>();
		HMAP<String, String> 				hmItemsNichtSachbearbeiter = 	new HMAP<>();
		HMAP<String, String> 				hmItemsInactive = 				new HMAP<>();
		HMAP<String, HMAP<String, String>>  valuesSubMenues =new HMAP<>();
		valuesSubMenues._put("Ausserhalb Büro", hmItemsNichtSachbearbeiter);
		
		hmItemsSachbearbeiter._put("", "-");
		for (Rec21 user: vAktivUndSachbearbeiter) {
			hmItemsSachbearbeiter._put(user.getFs(USER.id_user),getUserCode(user,false));
		}
		
		for (Rec21 user: vNichtSachbearbeiter) {
			hmItemsNichtSachbearbeiter._put(user.getFs(USER.id_user),getUserCode(user,false));
		}
		
		for (Rec21 user: vInactiv) {
			hmItemsInactive._put(user.getFs(USER.id_user),getUserCode(user,false));
		}

		this._setWidthTextField(100)._setWidthOfDropDownBlock(350)
									._render()
									._populate(hmItemsSachbearbeiter, hmItemsInactive, valuesSubMenues)
									._setActualValue("");

		this.getSelectCascadingWithString()._rebuildWithSubmenuAtEnd();
		
	}
	
	
	private String getUserCode(Rec21 user, boolean withId) throws myException {
		String s_user = S.NN(user.getUfs(USER.name1, USER.name),"<name>");
		s_user = s_user+", "+S.NN(user.getUfs(USER.vorname)," ");
		s_user = s_user+" ("+S.NN(user.getUfs(USER.kuerzel)," <kuerzel> ");
		if (withId) {
			s_user = s_user+"  / ID:"+user.getFs(USER.id_user)+")";
		} else {
			s_user = s_user+")";
		}
		
		return s_user;
	}
	
	

	@Override
	public void setShapeOfActionButtonOutside(RB_SelectCascading<String>.MenueButton button, String key) {
		
		if (key.equals("")) {
			button.setToolTipText("Benutzerfeld leeren ...");
		} else {

			if (new MyLong(key).isOK()) {
				try {
					Rec21 r = new Rec21(_TAB.user)._fill_id(new MyLong(key).getLong());
					button.setToolTipText(getUserCode(r,true));
					
				} catch (myException e) {
					e.printStackTrace();
					button.setToolTipText("Error: <8977726c-700b-11e9-a923-1681be663d3e>");
				}
				
			}
		}
		
		button.setInsets(new Insets(2, 2, 2, 2));
		button.setBorder(new Border(1, new E2_ColorDark(), Border.STYLE_SOLID));
		button.setTextAlignment(new Alignment(Alignment.LEFT, Alignment.CENTER));
		button.setLayoutData(new RB_gld()._ins(2));
	}
	
	public void setHighLightStatusOfActualValueButtonOutside(RB_SelectCascading<String>.MenueButton button) {
		button.setBackground(new E2_ColorLight());
	}
	public void resetHighLightStatusOfActualValueButtonOutside(RB_SelectCascading<String>.MenueButton button) {
		button.setBackground(new E2_ColorDark());

	}


	
	
}
