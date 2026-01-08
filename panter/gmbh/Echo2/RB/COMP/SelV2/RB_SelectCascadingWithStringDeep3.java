/**
 * panter.gmbh.Echo2.RB.COMP.SelV2
 * @author martin
 * @date 03.05.2019
 * 
 */
package panter.gmbh.Echo2.RB.COMP.SelV2;

import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;

/**
 * @author martin
 * @date 03.05.2019
 * variante mit bis zu drei ebenen
 */
public abstract class RB_SelectCascadingWithStringDeep3 extends RB_SelectCascading<String> {

	//das targetobjekt ist ein String , val1=db-value, formated, val2=Anzeige-String
	
	private HMAP<String, String> 								m_hmVisible = 	new HMAP<>();
	private HMAP<String, String> 								m_hmShadow = 	new HMAP<>();
	private HMAP<String, HMAP<String, String>>   				m_hmSubMenues2 = new HMAP<>();
	private HMAP<String, HMAP<String, HMAP<String, String>>>   	m_hmSubMenues3 = new HMAP<>();

	
	

	public RB_SelectCascadingWithStringDeep3( 	HMAP<String, HMAP<String, String>>   				hmSubMenues2, 
												HMAP<String, String> 								hmVisible, 
												HMAP<String, String> 								hmShadow) throws myException {
		
		this(null,hmSubMenues2,hmVisible,hmShadow);
	}
	
	
	/**
	 * 
	 * @author martin
	 * @date 20.08.2019
	 * @param hmSubMenues3
	 * @param hmSubMenues2
	 * @param hmVisible
	 * @param hmShadow
	 *
	 * @throws myException
	 */
	public RB_SelectCascadingWithStringDeep3(		HMAP<String, HMAP<String, HMAP<String, String>>> 	hmSubMenues3,
													HMAP<String, HMAP<String, String>>   				hmSubMenues2, 
													HMAP<String, String> 								hmVisible, 
													HMAP<String, String> 								hmShadow) throws myException {
		super();
		
		
		Menue m = new Menue("Hauptmenü");
		this.m_hmVisible.putAll(hmVisible);
		if (hmShadow!=null) {
			m_hmShadow.putAll(hmShadow);
		}
		if (hmSubMenues2!=null) {
			m_hmSubMenues2.putAll(hmSubMenues2);
		}
		
		if (hmSubMenues3!=null) {
			m_hmSubMenues3.putAll(hmSubMenues3);
		}

		//die dreier-ebene bearbeiten
		for (String subEbene3: m_hmSubMenues3.keySet()) {
			Menue subMenue = new Menue(subEbene3);
			HMAP<String,HMAP<String,String>> hmEbene2 = m_hmSubMenues3.get(subEbene3);
			
			for (String subMenueText: hmEbene2.keySet()) {
				Menue sub2 = new Menue(subMenueText);
				for (String skey: hmEbene2.get(subMenueText).keySet()) { 
					sub2._addItem(skey);
				}
				subMenue._addItem(sub2);
			}
			m._addItem(subMenue);
		}

		
		//die zweier-ebene bearbeiten
		for (String subMenueText: m_hmSubMenues2.keySet()) {
			Menue subEbene1 = new Menue(subMenueText);
			for (String skey: m_hmSubMenues2.get(subMenueText).keySet()) { 
				subEbene1._addItem(skey);
			}
			m._addItem(subEbene1);
		}
		
		for (String key: m_hmVisible.keySet()) { 
			m._addItem(key);
		}
		
		for (String key: m_hmShadow.keySet()) { 
			m._addItem(key);
		}

		//jetzt alle shadow-werte automatisch verstecken
		for (RB_SelectCascading<String>.MenueButton b: this.getAllMenueButtons()) {
			if (m_hmShadow.keySet().contains(b.getValue())) {
				b._setVisibleInRendering(false);
			}
		}
		
		this._renderMenue(m);
	}


	
	public RB_SelectCascadingWithStringDeep3 _rebuildWithSubmenuAtEnd() throws myException {
		//komplett neu aufbauen
		this._clearRenderingStatus();
		
		Menue m = new Menue("Hauptmenü");
		
		for (String key: m_hmVisible.keySet()) { 
			m._addItem(key);
		}

		//die dreier-ebene bearbeiten
		for (String subEbene3: m_hmSubMenues3.keySet()) {
			Menue subMenue = new Menue(subEbene3);
			HMAP<String,HMAP<String,String>> hmEbene2 = m_hmSubMenues3.get(subEbene3);
			
			for (String subMenueText: hmEbene2.keySet()) {
				Menue sub2 = new Menue(subMenueText);
				for (String skey: hmEbene2.get(subMenueText).keySet()) { 
					subMenue._addItem(skey);
				}
				subMenue._addItem(sub2);
			}
			m._addItem(subMenue);
		}

		
		//die zweier-ebene bearbeiten
		for (String subMenueText: m_hmSubMenues2.keySet()) {
			Menue subEbene1 = new Menue(subMenueText);
			for (String skey: m_hmSubMenues2.get(subMenueText).keySet()) { 
				subEbene1._addItem(skey);
			}
			m._addItem(subEbene1);
		}

		
		for (String key: m_hmShadow.keySet()) { 
			m._addItem(key);
		}

		//jetzt alle shadow-werte automatisch verstecken
		for (RB_SelectCascading<String>.MenueButton b: this.getAllMenueButtons()) {
			if (m_hmShadow.keySet().contains(b.getValue())) {
				b._setVisibleInRendering(false);
			}
		}
		
		this._renderMenue(m);
		
		return this;
		
	}
	
	
	
	
	public String findTextOfKey(String key) {
		String s = this.m_hmVisible.get(key);
		if (S.isEmpty(s)) {
			s =  this.m_hmShadow.get(key);
		}
		if (S.isEmpty(s)) {
			for (String subMenueText: m_hmSubMenues2.keySet()) {
				HMAP<String,String> submenue = m_hmSubMenues2.get(subMenueText);
				s = submenue.get(key);
				if (s != null) {
					break;
				}
			}
		}
		if (S.isEmpty(s)) {
			for (String subMenueText: m_hmSubMenues3.keySet()) {
				HMAP<String,HMAP<String,String>> submenue = m_hmSubMenues3.get(subMenueText);
				
				for (String subText2: submenue.keySet()) {
					
					HMAP<String,String> submenue2 = submenue.get(subText2);
					s = submenue2.get(key);
					if (s != null) {
						break;
					}
				}

				if (s != null) {
					break;
				}
			}
		}
		
		return s;
	}
	
	public boolean isInActiveBlock(String key) {
		return this.m_hmVisible.containsKey(key);
	}
	
	
	public boolean isInInActiveBlock(String key) {
		return this.m_hmShadow.containsKey(key);
	}
	
	
	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
	}

	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
	}




	@Override
	public void setShapeOfSubMenueButton(RB_SelectCascading<String>.MenueButton button, RB_SelectCascading<String>.Menue subMenu) throws myException {
		button.setFont(new E2_FontBoldItalic());
		button.setText(subMenu.getTitle());
		button.setLayoutData(new RB_gld()._ins(2,3,2,3));
	}

	@Override
	public void setShapeOfReturnButton(RB_SelectCascading<String>.MenueButton button, RB_SelectCascading<String>.Menue backMenue) throws myException {
		button.setFont(new E2_FontBoldItalic());
		button.setText(backMenue.getTitle()+"  (aktuell: "+button.getOwnMenue().getTitle()+")");
		button.setLayoutData(new RB_gld()._ins(2,3,2,3));
	}

	@Override
	public boolean isSame(String t1, String t2) {
		if (t1==null && t2==null) {
			return true;
		}
		if (t1==null || t2==null) {
			return false;
		}
		return t1.equals(t2);
	}

	@Override
	public void setHighLightStatusOfActualValueButton(RB_SelectCascading<String>.MenueButton bt) {
	}

	@Override
	public void resetHighLightStatusOfActualValueButton(RB_SelectCascading<String>.MenueButton bt) {
	}

	public HMAP<String, String> getHmVisible() {
		return m_hmVisible;
	}

	public HMAP<String, String> getHmShadow() {
		return m_hmShadow;
	}

	public HMAP<String, HMAP<String, String>> getHmSubMenues2() {
		return m_hmSubMenues2;
	}

	public HMAP<String, HMAP<String, HMAP<String, String>>> getHmSubMenues3() {
		return m_hmSubMenues3;
	}





}
