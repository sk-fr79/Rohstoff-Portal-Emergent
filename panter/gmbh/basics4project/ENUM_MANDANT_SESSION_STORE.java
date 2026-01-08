package panter.gmbh.basics4project;

import java.util.HashMap;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS.LAND_RC_SORTEN;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.MASCHINEN;
import panter.gmbh.basics4project.DB_ENUMS.MASCHINENTYP;
import panter.gmbh.basics4project.DB_ENUMS.MITARBEITER;
import panter.gmbh.basics4project.DB_ENUMS.TAX;
import panter.gmbh.basics4project.DB_ENUMS.TAX_GROUP;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS.WAEHRUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibTEXT;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.RecList22;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.dataTools.TERM.VglNull;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_SONDERLAGER;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_mandant;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_taxGroup;

/**
 * moeglichkeit, objecte in einer session abzuspeichern,
 * immer erzeugen beim ersten aufruf, ab da aus der session holen.
 * Alle objecte werden in einer HashMap vom Typ HashMap<ENUM_SESSION_STORE,IF_storable> gespeichert,
 * die wiederum mit dem containerKey in der sesson hinterlegt wird
 * 
 * !!! wichtig! diese klassen mit dem interface IF_storable MUESSEN einen leeren constructor enthalten !!!
 * @author martin
 *
 */
public enum ENUM_MANDANT_SESSION_STORE {
	
	VECTOR_ALL_DELIVERYADDRESSES_FROM_MANDANT(new ReaderAllDeliveryAdressesFromMandant())
	,VECTOR_ALL_EMPLOYES_FROM_MANDANT(new ReaderAllEmployesFromMandant())
	,HASHMAP_ALL_PROGRAMM_USERS(new ReaderAllProgrammUsers())
	,HASHMAP_ALL_PROGRAMM_VERSIONS(new ReaderAllProgrammVersions())
	,RECLIST21_WAEHRUNGEN(new ReaderAllWaehrungen())
	,ALL_SONDERLAGER_HASHMAP(new ReaderAllSonderLager())
	,MANDANTEN_ADRESS_REC21(new ReadRec21MandantenAdresse())
	,BASE_CURRENCY(new ReadRec21WaehrungMandant())
	,STRUCTURE_FOR_TAX_SELECT(new ReadStructureForTaxSelector())
	,REC21_MANDANT(new ReaderRecMandant())
	,ALLE_EIGENEN_KENNZEICHEN(new ReaderLKWKennzeichen())   //VEK<String> aller eigenen lkws
	,LANDER_MIT_RC_SORTEN(new ReaderLaenderMitRcSorten())   //2020-10-29
	;
	
	private static String  containerKey = "4e884334-a6ee-11e8-98d0-529269fb1459";
	
	private String   		m_key = null;
	private IF_storable 	m_storable = null;
	
	
	public String getKey() {
		return m_key;
	}


	public IF_storable getStorable() {
		return m_storable;
	}


	
	
	
	private ENUM_MANDANT_SESSION_STORE(IF_storable 	storable ) {
		this.m_key = this.name();
		this.m_storable = storable;
	}


	public Object getValueFromSession() throws myException {
		
		@SuppressWarnings("unchecked")
		HashMap<ENUM_MANDANT_SESSION_STORE,Object>  hm = (HashMap<ENUM_MANDANT_SESSION_STORE,Object>)bibE2.get_CurrSession().getAttribute(ENUM_MANDANT_SESSION_STORE.containerKey);
		
		if (hm==null) {
			hm = new HashMap<ENUM_MANDANT_SESSION_STORE,Object>();
			bibE2.get_CurrSession().setAttribute(ENUM_MANDANT_SESSION_STORE.containerKey,hm);
		}
		
		Object obj = hm.get(this);
		
		if (obj==null) {
			obj = this.m_storable.createAtFirstAppearence();
			hm.put(this, obj);
		}
		
		return obj;
		
	}
	

	public static void clearAllValuesFrom_ENUM_MANDANT_SESSION_STORE() {
		bibE2.get_CurrSession().setAttribute(ENUM_MANDANT_SESSION_STORE.containerKey,null);
	}




	public static interface IF_storable {
		public Object createAtFirstAppearence() throws myException;
	}
	
	

	
	//gehoert zu VECTOR_ALL_LAGERADRESSES_FROM_MANDANT 
	private static class ReaderAllDeliveryAdressesFromMandant extends VEK<Long> implements IF_storable {

		@Override
		public Object createAtFirstAppearence() throws myException {
			VEK<Long>  v_l = new VEK<>();
			SqlStringExtended  sql = new SqlStringExtended(new SEL("*").FROM(_TAB.lieferadresse).WHERE(new vglParam(LIEFERADRESSE.id_adresse_basis)).s())
										._addParameters(new VEK<ParamDataObject>()._a(new Param_Long(new MyLong(bibALL.get_ID_ADRESS_MANDANT()).get_oLong())));
			RecList21 rl = new RecList21(_TAB.lieferadresse)._fill(sql);
			
			for (Rec21 r: rl) {
				v_l._a(r.get_raw_resultValue_Long(LIEFERADRESSE.id_adresse_liefer));
			}
			return v_l;
		}
		
	}

	
	
	//gehoert zu VECTOR_ALL_LAGERADRESSES_FROM_MANDANT 
	private static class ReaderAllEmployesFromMandant extends VEK<Long> implements IF_storable {

		@Override
		public Object createAtFirstAppearence() throws myException {
			VEK<Long>  v_l = new VEK<>();
			SqlStringExtended  sql = new SqlStringExtended(new SEL("*").FROM(_TAB.mitarbeiter).WHERE(new vglParam(MITARBEITER.id_adresse_basis)).s())
										._addParameters(new VEK<ParamDataObject>()._a(new Param_Long(new MyLong(bibALL.get_ID_ADRESS_MANDANT()).get_oLong())));
			RecList21 rl = new RecList21(_TAB.mitarbeiter)._fill(sql);
			
			for (Rec21 r: rl) {
				v_l._a(r.get_raw_resultValue_Long(MITARBEITER.id_adresse_mitarbeiter));
			}
			return v_l;
		}
		
	}


	//gehoert zu VECTOR_ALL_LAGERADRESSES_FROM_MANDANT 
	private static class ReaderAllProgrammUsers extends HashMap<Long,Rec21> implements IF_storable {

		@Override
		public Object createAtFirstAppearence() throws myException {
			HashMap<Long,Rec21>  ret = new HashMap<Long,Rec21>();
			
			
			SqlStringExtended  sql = new SqlStringExtended(new SEL("*").FROM(_TAB.user).s());
			RecList21 rl = new RecList21(_TAB.user)._fill(sql);
			
			for (Rec21 r: rl) {
				ret.put(r.get_raw_resultValue_Long(USER.id_user),r);
			}
			return ret;
		}
		
	}


	//gehoert zu VECTOR_ALL_LAGERADRESSES_FROM_MANDANT 
	private static class ReaderAllProgrammVersions extends HashMap<Long,Rec21> implements IF_storable {

		@Override
		public Object createAtFirstAppearence() throws myException {
			HashMap<Long,Rec21>  ret = new HashMap<Long,Rec21>();
			
			
			SqlStringExtended  sql = new SqlStringExtended(new SEL("*").FROM(_TAB.version).s());
			RecList21 rl = new RecList21(_TAB.version)._fill(sql);
			
			for (Rec21 r: rl) {
				ret.put(r.get_raw_resultValue_Long(panter.gmbh.basics4project.DB_ENUMS.VERSION.id_version),r);
			}
			return ret;
		}
		
	}

	
	private static class ReaderAllWaehrungen extends HashMap<Long,Rec21> implements IF_storable {

		@Override
		public Object createAtFirstAppearence() throws myException {
			SqlStringExtended  sql = new SqlStringExtended(new SEL("*").FROM(_TAB.waehrung).ORDERUP(WAEHRUNG.kurzbezeichnung).s());
			RecList21 rl = new RecList21(_TAB.waehrung)._fill(sql);
			return rl;
		}
		
	}

	
	private static class ReaderAllSonderLager extends HashMap<ENUM_SONDERLAGER,Rec21> implements IF_storable {

		@Override
		public Object createAtFirstAppearence() throws myException {
			HMAP<ENUM_SONDERLAGER, Rec21> hm = new HMAP<>();
			
			for (ENUM_SONDERLAGER e: ENUM_SONDERLAGER.values()) {
				try {
					SEL sel = new SEL(_TAB.adresse).FROM(_TAB.adresse).WHERE(new vglParam(ADRESSE.sonderlager));
					
					SqlStringExtended  sql = new SqlStringExtended(sel.s())._addParameters(new VEK<ParamDataObject>()._a(new Param_String("",e.db_val())));
					RecList21 rl = new RecList21(_TAB.adresse)._fill(sql);
					
					if (rl.size()==1) {
						hm._put(e, rl.get(0));
					}
					
				} catch (myException e1) {
					e1.printStackTrace();
				}
			}
			return hm;
			
		}
		
	}

	
	private static class ReadRec21MandantenAdresse extends HashMap<Long,Rec21_adresse> implements IF_storable {

		@Override
		public Object createAtFirstAppearence() throws myException {
			Rec21_adresse rl = new Rec21_adresse()._fill_id(bibALL.get_ID_ADRESS_MANDANT());
			return rl;
		}
		
	}
	
	
	
	private static class ReadRec21WaehrungMandant  implements IF_storable {

		@Override
		public Object createAtFirstAppearence() throws myException {
			Rec21 recAdress = new Rec21_adresse()._fill_id(bibALL.get_ID_ADRESS_MANDANT());
			return recAdress.get_up_Rec21(WAEHRUNG.id_waehrung);
		} 
		
		
	}
	

	/**
	 * uebergibt eine HMAP<EnTaxSelector><Object>, die die definitionen fuer den TAX-selector enthaelt
	 * @author martin
	 * @date 22.05.2019
	 *
	 */
	public enum EnTaxSelector {
		ACTIVE
		,INACTIVE
		,SUBMENUES
	}
	
	private static class ReadStructureForTaxSelector implements IF_storable {

		@Override
		public Object createAtFirstAppearence() throws myException {
			RecList21 rlSteuerGruppen = new RecList21(_TAB.tax_group)._fillWithAll(new VEK<IF_Field>()._a(TAX_GROUP.kurztext));

			HMAP<String, String> 				valuesVisible = new HMAP<>();
			HMAP<String, String> 				valuesInactive = new HMAP<>();
			HMAP<String, HMAP<String, String>>  valuesSubMenues =new HMAP<>();
			
			//jetzt die gruppen-submenues bauen
			for (Rec21 tg: rlSteuerGruppen) {
				if (tg.is_yes_db_val(TAX_GROUP.aktiv)) {
					Rec21_taxGroup tg2 = new Rec21_taxGroup(tg);
					HMAP<String, String> subMenue = new HMAP<>();
					
					RecList21 subTax = tg2.get_down_reclist21(TAX.id_tax_group);
					
					for (Rec21 tax: subTax) {
						if (tax.is_yes_db_val(TAX.aktiv)) {
							subMenue.put(tax.getFs(TAX.id_tax),tax.getUfs(TAX.steuersatz)+" %,  "+tax.getFs(TAX.dropdown_text));
						}
					}
					if (subMenue.size()>0) {
						valuesSubMenues.put(tg2.getFs(TAX_GROUP.kurztext), subMenue);
					}
				} else {
					//steuern aus inaktive steuergruppen werden automatisch den inaktiven steuern zugeschlagen 
					Rec21_taxGroup tg2 = new Rec21_taxGroup(tg);
					RecList21 subTax = tg2.get_down_reclist21(TAX.id_tax_group);

					for (Rec21 tax: subTax) {
						valuesInactive.put(tax.getFs(TAX.id_tax),tax.getUfs(TAX.steuersatz)+" %,  "+tax.getFs(TAX.dropdown_text));
					}
				}
			}

			//jetzt die nicht gruppierten
			RecList21 rlSteuernOhneGruppe = new RecList21(_TAB.tax)._fill(new And(new vgl_YN(TAX.aktiv, true)).and(new VglNull(TAX.id_tax_group)).s(), TAX.dropdown_text.fn());
			//dann alle nicht-aktiven in die shadows
			RecList21 rlSteuernInaktiv = new RecList21(_TAB.tax)._fill(new And(new vgl_YN(TAX.aktiv, false)).s(),TAX.dropdown_text.fn());
			
			//einen leereintrag einfuegen
			valuesVisible.put("", "-");
			for (Rec21 tax: rlSteuernOhneGruppe) {
				valuesVisible.put(tax.getFs(TAX.id_tax),tax.getUfs(TAX.steuersatz)+" %,  "+tax.getFs(TAX.dropdown_text));

			}

			for (Rec21 tax: rlSteuernInaktiv) {
				valuesInactive.put(tax.getFs(TAX.id_tax),tax.getUfs(TAX.steuersatz)+" %,  "+tax.getFs(TAX.dropdown_text));
			}

			HMAP<EnTaxSelector, HMAP> mapReturn = new HMAP<>();
			mapReturn.put(EnTaxSelector.ACTIVE, valuesVisible);
			mapReturn.put(EnTaxSelector.INACTIVE, valuesInactive);
			mapReturn.put(EnTaxSelector.SUBMENUES, valuesSubMenues);
			
			return mapReturn;
		}
		
	}
	
	
	

	
	//gehoert zu VECTOR_ALL_LAGERADRESSES_FROM_MANDANT 
	private static class ReaderRecMandant  implements IF_storable {
		@Override
		public Object createAtFirstAppearence() throws myException {
			Rec21_mandant r = new Rec21_mandant()._fill_id(bibALL.get_ID_MANDANT());
			return r;
		}
	}

	//liesst alle maschinen vom typ lkw aus dem maschinenpark ein und normalisiert diese 
	private static class ReaderLKWKennzeichen  implements IF_storable {
		@Override
		public Object createAtFirstAppearence() throws myException {
			SEL sel = new SEL(MASCHINEN.kfzkennzeichen).FROM(_TAB.maschinen).INNERJOIN(_TAB.maschinentyp, MASCHINEN.id_maschinentyp, MASCHINENTYP.id_maschinentyp)
					.WHERE(new vglParam(MASCHINENTYP.ist_lkw))
					.AND(new vglParam(MASCHINEN.id_mandant))
					;
			
			SqlStringExtended sqlExt = new SqlStringExtended(sel.s());
			sqlExt._addParameter(new Param_String("","Y"));
			sqlExt._addParameter(new Param_Long(Long.parseLong(bibALL.get_ID_MANDANT())));
			
			
			VEK<Object[]>  result = bibDB.getResultLines(sqlExt, true);
			
			
			String positivListe = "ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÜ0123456789";
			
			VEK<String>  eigeneKennzeichen = new VEK<>();
			for (Object[] arr: result) {
				String kennzeichen = S.NN((String)arr[0]);
				if (S.isFull(kennzeichen)) {
					eigeneKennzeichen._a(bibTEXT.CleanStringKeepOnlyCharList(kennzeichen.toUpperCase().trim(), positivListe,true));
				}
			}
			return eigeneKennzeichen;
		}
	}
	
	
	
	private static class ReaderLaenderMitRcSorten implements IF_storable {

		@Override
		public Object createAtFirstAppearence() throws myException {
			
			SEL selLaender = new SEL(_TAB.land).FROM(_TAB.land).ADD_Distinct().INNERJOIN(_TAB.land_rc_sorten, LAND.id_land, LAND_RC_SORTEN.id_land).ORDERUP(LAND.laendercode);
			return new RecList22(_TAB.land)._fill(selLaender);
		}
		
	}
	
}
