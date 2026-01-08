package panter.gmbh.Echo2.__BASIC_MODULS.CODEGENERATOR.TEMPLATES.RB_MODULES;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Vector;

import org.apache.commons.io.FileUtils;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibTEXT;
import panter.gmbh.indep.dataTools.DB_STATICS;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.dataTools.SQLFieldMAP.enumBlacklistAutomaticFields;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;


public class Generator {

	public enum TEMPLATE {
		XXX_CONST()
		,XXX_LIST_BasicModuleContainer()
		,XXX_LIST_BedienPanel()
		,XXX_LIST_bt_Copy()
		,XXX_LIST_bt_DeleteInListRow()
		,XXX_LIST_bt_ListToMask()
		,XXX_LIST_bt_ListToMaskInListRow()
		,XXX_LIST_bt_multiDelete()
		,XXX_LIST_bt_New()
		,XXX_LIST_ComponentMap()
		,XXX_LIST_DATASEARCH()
		,XXX_LIST_FORMATING_Agent()
		,XXX_LIST_Selector()
		,XXX_LIST_SqlFieldMAP()
		,XXX_MASK_ComponentMap()
		,XXX_MASK_ComponentMapCollector()
		,XXX_MASK_DataObject()
		,XXX_MASK_DataObjectCollector()
		,XXX_MASK_DaughterListForMotherMask()
		,XXX_MASK_DaughterListForMotherMaskStandalone()
		,XXX_MASK_DaughterListForMotherMaskClassicType()
		,XXX_MASK_MaskGrid()
		,XXX_MASK_MaskModulContainer()
		,XXX_READABLE_FIELD_NAME()
		,XXX_VALIDATORS()
		;
		
		private String fileNameTemplate = null;
		private String fileNameTargetCode = null;
		
		private TEMPLATE() {
			this.fileNameTemplate = this.name()+".template";
			this.fileNameTargetCode = this.name().substring(3)+".java";
		}
		
		public String fileNameTemplate() {
			return this.fileNameTemplate;
		}
		
		public String fileNameTargetCode(String modul_signature) {
			return modul_signature+this.fileNameTargetCode;
		}
	}
	

	/**
	 * auflistung der moeglichen platzhalter in den templates
	 * @author martin
	 *
	 */
	public enum PLACEHOLDER {
		TABLENAME("#TABLENAME#",true, new MyE2_String("Kompletter Tabellenname, z.B. JT_ADRESSE"))    
		,PACKAGE("#PACKAGE#",true, new MyE2_String("Package, z.B. panter.gmbh.Echo2..."))   
		,PACKAGE_PRAEFIX("#PREFIX#",true, new MyE2_String("Praefix für die Klassennamen (z.B. AD fuer ein Adress-Package)"))      
		,MODULEKEYNAME("#KEYNAME#",true, new MyE2_String("Key für die E2_MODULNAME_ENUM.MODUL (z.B. ADRESS -> wird zu ADRESS_LIST und ADRESS_MASK"))	 
		,BASETABLENAME("#BASETABLENAME#",true, new MyE2_String("Basistabellenname, GROSSGESCHRIEBEN, z.b. ADRESSE"))
		,BASETABLENAME_LOWERCASE("#BASETABLENAME_LOWERCASE#",true, new MyE2_String("Basistabellenname, kleingeschrieben, z.b. adresse"))	
		,FIELDNAME_LOWERCASE("#FIELDNAME_LOWERCASE#",false, new MyE2_String("Feldname, kleingeschrieben, z.B. id_adresse"))          //z.B. id_adresse
		,FIELDNAME_UPPERCASE("#FIELDNAME_UPPERCASE#",false, new MyE2_String("Feldname, grossgeschrieben, z.B. ID_ADRESSE"))          //z.B. id_adresse
		,FIELDCOMPONENT("#FIELDCOMPONENT#",false, new MyE2_String("Feldkomponente, je nach typ (nur der Name)"))          //z.B. id_adresse
		,LISTCOMPONENT("#LISTCOMPONENT#",false, new MyE2_String("Feldkomponente für die Liste, alte Bauart)"))          //z.B. MyE2_CheckBox
		,FIELDCOMPONENT_COMPLETE("#FIELDCOMPONENT_COMPLETE#",false, new MyE2_String("Feldkomponente, der komplette Aufruf"))          //z.B. id_adresse
		,USERTEXT4MASK("#USERTEXT4MASK#",true, new MyE2_String("Maskeninfo des Modulthemas (z.B. Adresse, ie: Löschen einer Adresse"))  
		,CALCULATED_FIELDWIDH("#CALCULATED_FIELDWIDH#",false, new MyE2_String("Berechnete Breite fuer ein Feld (z.B. in Spaltenansicht)"))          //z.B. id_adresse)
		,FIELDALIGN("#FIELDALIGN#",false, new MyE2_String("Prognose fuer die Ausrichtung der Felder"))          
		,FIELDSELEKTOR("#FIELDSELEKTOR#",false, new MyE2_String("Komponente für den Listselektor"))
		;
		
		private String 			c_placeHolder = null;
		private boolean 		b_for_replacement_basic = true;
		private MyE2_String  	c_erklaerung = null;
		
		private PLACEHOLDER(String p_placeHolder, boolean p_replacement_basic, MyE2_String p_erklaerung) {
			this.c_placeHolder = p_placeHolder;
			this.b_for_replacement_basic = p_replacement_basic;
			this.c_erklaerung = p_erklaerung;
		}
		public String get_placeHolder() {
			return c_placeHolder;
		}
		public boolean is_for_replacement_basic() {
			return b_for_replacement_basic;
		}
		public MyE2_String get_c_erklaerung() {
			return c_erklaerung;
		}
		
		
		
		public static PLACEHOLDER findPLACEHOLDER(String placeHolder) {
			for (PLACEHOLDER p: PLACEHOLDER.values()) {
				if (p.toString().equals(placeHolder)) {
					return p;
				}
			}
			
			return null;
		}
		
		
		
		public static VEK<String> genKeys() {
			VEK<String> vek = new VEK<String>();
			for (PLACEHOLDER p: PLACEHOLDER.values()) {
				vek._a(p.toString());
			}
			return vek;
		}

	}
	
	
	/**
	 * auflistung der tags/Endtag - paare fuer innere verabeitung die bloecke mehrfach liefert
	 * @author martin
	 *
	 */
	public enum TAG {
		fieldloop("fieldloop")
		;
		
		private String startTag = null;
		private String endTag = null;
		private TAG(String tag) {
			this.startTag="<"+tag+">";
			this.endTag="</"+tag+">";
		}
		public String get_startTag() {
			return startTag;
		}
		public String get_endTag() {
			return endTag;
		}
	}
	
	
	
	private  HashMap<PLACEHOLDER, String>  	hashMapPlaceHolders = null;
	private  Vector<PLACEHOLDER>    		v_placeHolders = new Vector<Generator.PLACEHOLDER>();
	
	private _TAB   							table = null;


	
	/**
	 * generator erzeugt fuer jede datei der TEMPLATE - liste eine ausgabedatei im output-ordner
	 * @param p_hashMapPlaceHolders
	 * @throws myException
	 */
	public Generator(HashMap<PLACEHOLDER, String> p_hashMapPlaceHolders) throws myException {
		super();
		this.hashMapPlaceHolders = p_hashMapPlaceHolders;
		
		this.table = _TAB.find_Table(this.hashMapPlaceHolders.get(PLACEHOLDER.TABLENAME));
		
		if (this.table==null) {
			throw new myException(this," ! Table was not found: "+this.hashMapPlaceHolders.get(PLACEHOLDER.TABLENAME));
		}

		Vector<File>  vErgebnisFiles = new Vector<File>();
		
		for (PLACEHOLDER  ph: PLACEHOLDER.values()) {
			this.v_placeHolders.add(ph);
		}
		
		
		for (TEMPLATE template: TEMPLATE.values()) {
			// im outputname wird Triple-X ersetzt durch den Basisnamen
			String cOutName = bibALL.get_CompleteOutPutPath(true)+this.hashMapPlaceHolders.get(PLACEHOLDER.PACKAGE_PRAEFIX)+template.fileNameTargetCode;

			File oFileOut = new File(cOutName);
			vErgebnisFiles.add(oFileOut);
			
			String templateClassText = new MODULE_ResourceStringLoader(template.fileNameTemplate()).get_cText();
			
			Vector<String> v_replacedLines = this.replacePlaceHolders(bibTEXT.get_ZeilenAusTextblock(templateClassText,true,false), this.v_placeHolders, this.hashMapPlaceHolders);
			
			String cNewText = "";
			for (String c: v_replacedLines) {
				if (S.isEmpty(c)) {
					cNewText+=(c+"\n");
				} else {
					cNewText+=(c+"\n");
				}
			}
			
			try {
				FileUtils.writeStringToFile(oFileOut, cNewText, "UTF-8");
			} catch (IOException e) {
				e.printStackTrace();
				throw new myException(e.getLocalizedMessage());
			}
		}
	}
	

	/**
	 * ersetzt die placeholder (statisch und die tags) und gibt einen vector mit ersetzten zeilen zurueck
	 * @param vZeilenClassText
	 * @param vPlaceHolders
	 * @param hm_replacer
	 * @return
	 * @throws myException
	 */
	private Vector<String> replacePlaceHolders(Vector<String> vZeilenClassText, Vector<PLACEHOLDER> vPlaceHolders, HashMap<PLACEHOLDER, String> hm_replacer) throws myException {
		
		Vector<String>  vZeilenClassTextNeu = 	new Vector<String>();
		
		for (int i=0;i<vZeilenClassText.size();i++) {
			String neu = vZeilenClassText.get(i);
			//zuerst pruefen, ob eine zeile einen startTag enthaelt
			TagContainer  tc = this.checkTags(vZeilenClassText, i);
			
			if (tc != null) {
				//dann tag-block-bearbeitung
				i = i+tc.v_zeilen.size()+2;   		//immer die gefundenen zeilen innerhalb des tag-blocks + die 2 begrenzer-Zeilen <tag> und </tag>
				
				this.process_tag_container(tc,hm_replacer);   	//die gefundenen tag-bloecke durchnudeln
				vZeilenClassTextNeu.addAll(tc.v_zeilen_replaced);
				
			} else {
				for (PLACEHOLDER  ph: vPlaceHolders) {
					neu = bibALL.ReplaceTeilString(neu,ph.get_placeHolder(),this.hashMapPlaceHolders.get(ph));
				}
				vZeilenClassTextNeu.add(neu);
			}
		}
		
		return vZeilenClassTextNeu;
	}
	

	private TagContainer checkTags(Vector<String> v_zeilen, int i_actual_pos) throws myException {

		TagContainer  tc = null;
		
		for (TAG tag: TAG.values()) {
			boolean 		blockStartFound = 	false;
			boolean 		blockEndFound = 	false;
			if (v_zeilen.get(i_actual_pos).contains(tag.startTag)) {
				tc = new TagContainer(tag);
				blockStartFound = true;
				//dann den endtag suchen
				for (int i=i_actual_pos+1; i<v_zeilen.size();i++) {
					if (v_zeilen.get(i).contains(tag.get_endTag())) {
						blockEndFound=true;
						break;
					} else {
						tc.v_zeilen.add(v_zeilen.get(i));
					} 
				}
			}
			
			if (blockStartFound && !blockEndFound) {
				throw new myException(this,": Start of Block was found, not the end of Block!! : tag: "+tag.startTag);
			}
		}
		
		return tc;
	}
	
	
	private void  process_tag_container(TagContainer tc,HashMap<PLACEHOLDER, String> hm_replacer) throws myException {
		Vector<String> v_excludeFields = DB_STATICS.get_AutomaticWrittenFields_STD();
		for (enumBlacklistAutomaticFields exField: enumBlacklistAutomaticFields.values()) {
			v_excludeFields.add(exField.name());
		}

		if (tc.tag==TAG.fieldloop) {
			//dann alle felder durchlaufen und den Block fuer alle felder doppeln
			IF_Field 		fields[] = 			this.table.get_fields();
			Vector<String>  v_zeilenTemplates = tc.v_zeilen;

			for (IF_Field  field: fields) {

				//ein paar strings
				String praeFix = hm_replacer.get(PLACEHOLDER.PACKAGE_PRAEFIX);
				String baseTable = hm_replacer.get(PLACEHOLDER.BASETABLENAME);
				String baseTableLow = hm_replacer.get(PLACEHOLDER.BASETABLENAME).toLowerCase();
				String fn=field.fn().toLowerCase();
				String sTNfn = baseTable+"."+fn;

				
				if (v_excludeFields.contains(field.fieldName())) {
					continue;
				}
				
				
				//jeweils den Fieldname austauschen und dann ersetzen
				this.hashMapPlaceHolders.put(PLACEHOLDER.FIELDNAME_LOWERCASE, field.fieldName().toLowerCase());
				this.hashMapPlaceHolders.put(PLACEHOLDER.FIELDNAME_UPPERCASE, field.fieldName().toUpperCase());
				
				String c_field_component = "RB_TextField";
				String c_field_component_complete_call = "RB_TextField()._width(100)";

				String listComponent = "new MyE2_DB_Label_INGRID(oFM.get_("+field.baseTableName()+"."+field.fieldName().toLowerCase()+"),true)";
				
				String fieldSelektor = "new E2_ListSelectorMultiDropDownV3(_TAB.find_field(_TAB."+baseTableLow+",\""+fn+"\"),null)._setBeschriftung("+praeFix+"_READABLE_FIELD_NAME.getMaskText("+field.baseTableName()+"."+field.fieldName().toLowerCase()+"))";
				
				MyMetaFieldDEF def = field.generate_MetaFieldDef();
				
				//20180813: eine spaltenbreite und Alignment in spiel bringen
				this.hashMapPlaceHolders.put(PLACEHOLDER.CALCULATED_FIELDWIDH, this.findOutFieldWidth(def).toString());
				this.hashMapPlaceHolders.put(PLACEHOLDER.FIELDALIGN, this.findOutAlignment(def));

				boolean fieldIsDone = false;
				
				if (field == field._t().key()) {
					//dann einen label einsetzen
					c_field_component = "RB_lab";
					c_field_component_complete_call = "RB_lab()";
					fieldIsDone = true;
					
				} else if (field.fn().toUpperCase().startsWith("ID_")){
					//dann nachsehen, ob die tabelle JD_xx oder JT_xxx existiert und ein lookupfeld konstruieren
					String tableName1 = "JT_"+field.fn().toUpperCase().substring(3);
					String tableName2 = "JD_"+field.fn().toUpperCase().substring(3);
					
					_TAB lookupTable = null;
					
					try {	lookupTable = _TAB.find_Table(tableName1);	} catch (Exception e) {	}
					if (lookupTable==null) {
						try {	lookupTable = _TAB.find_Table(tableName2);	} catch (Exception e) {	}
					}
					
//					if (_TAB.find_Table(tableName1)!=null) {
//						lookupTable=_TAB.find_Table(tableName1);
//					} else if (_TAB.find_Table(tableName2)!=null) {
//						lookupTable=_TAB.find_Table(tableName2);
//					}
					if (lookupTable!=null) {
				        String tabReplace = "_TAB."+lookupTable.baseTableName().toLowerCase();  //z.B. _TAB.adresse
						String s = "RB_selField()._populate(new RecList21("+tabReplace+")._fillWithAll(),"+tabReplace+".key(),"+tabReplace+".key(),true)";
						c_field_component = "RB_selField";
						c_field_component_complete_call = s;
						fieldIsDone = true;
					}
				}
				
				
				if (!fieldIsDone) {
					if (def.get_bIsTextType() && def.get_FieldTextLENGTH()==1) {
						c_field_component = "RB_cb";
						c_field_component_complete_call = "RB_cb()";
						String termFN4User = praeFix+"_READABLE_FIELD_NAME.getMaskText("+sTNfn+")";
						listComponent = "new MyE2_DB_CheckBox(oFM.get_("+field.baseTableName()+"."+field.fieldName().toLowerCase()+"),true)";
						fieldSelektor = "new E2_ListSelektorMultiselektionV2()._addLabel(S.msUt("+termFN4User+"), 40)" + 
											"._addCheck("+sTNfn+", true, new vgl_YN("+sTNfn+", true).s(), S.msUt("+praeFix+"_READABLE_FIELD_NAME.getMaskText("+sTNfn+")+\" AN\"), S.msUt("+termFN4User+"), 70)" + 
											"._addCheck("+sTNfn+", true, new vgl_YN("+sTNfn+", false).s(), S.msUt("+praeFix+"_READABLE_FIELD_NAME.getMaskText("+sTNfn+")+\" AUS\"), S.msUt("+termFN4User+"), 70)";
						
					} else if (def.get_bIsTextType() && def.get_FieldTextLENGTH()<=20) {
						c_field_component = "RB_TextField";
						c_field_component_complete_call = "RB_TextField()._width(100)";
					} else if (def.get_bIsTextType() && def.get_FieldTextLENGTH()<=200) {
						c_field_component = "RB_TextField";
						c_field_component_complete_call = "RB_TextField()._width(400)";
					} else if (def.get_bIsTextType()) {
						c_field_component = "RB_TextArea";
						c_field_component_complete_call = "RB_TextArea()._width(400)._rows(5)";
					} else if (def.get_bIsDateType()) {
						c_field_component = "E2_TF_4_Date";
						c_field_component_complete_call = "RB_date_selektor(100,true)";
					}
				}
				
				this.hashMapPlaceHolders.put(PLACEHOLDER.FIELDCOMPONENT, c_field_component);
				this.hashMapPlaceHolders.put(PLACEHOLDER.FIELDCOMPONENT_COMPLETE, c_field_component_complete_call);
				this.hashMapPlaceHolders.put(PLACEHOLDER.LISTCOMPONENT, listComponent);
				this.hashMapPlaceHolders.put(PLACEHOLDER.FIELDSELEKTOR, fieldSelektor);
				
				tc.v_zeilen_replaced.addAll(this.replacePlaceHolders(v_zeilenTemplates, this.v_placeHolders, this.hashMapPlaceHolders));
			}
		}
	}
	
	
	
	private Integer findOutFieldWidth(MyMetaFieldDEF def ) {
		int size =10;
		
		if (def.get_bIsNumberTypeWithDecimals() || def.get_bIsNumberTypeWithOutDecimals()) {
			size = 100;
		} else if (def.get_bIsDateType()){
			size=100;
		} else if (def.get_bIsTextType()) {
			if (def.get_FieldTextLENGTH()==1) {
				size=30;
			} else if (def.get_FieldTextLENGTH()<=10) {
				size=100;
			} else if (def.get_FieldTextLENGTH()<=20) {
				size=200;
			} else if (def.get_FieldTextLENGTH()>20) {
				size=400;
			} 
		}
		
		
		return size;
	}
	
	private String findOutAlignment(MyMetaFieldDEF def ) {
		String al = "new Alignment(Alignment.LEFT, Alignment.TOP)";
		
		if (def.get_bIsNumberTypeWithDecimals() || def.get_bIsNumberTypeWithOutDecimals()) {
			 al = "new Alignment(Alignment.RIGHT, Alignment.TOP)";
		} else if (def.get_bIsDateType()){
			 al = "new Alignment(Alignment.CENTER, Alignment.TOP)";
		} else if (def.get_bIsTextType()) {
			if (def.get_FieldTextLENGTH()==1) {
				 al = "new Alignment(Alignment.CENTER, Alignment.TOP)";
			} else if (def.get_FieldTextLENGTH()<=10) {
				 al = "new Alignment(Alignment.CENTER, Alignment.TOP)";
			} else if (def.get_FieldTextLENGTH()>10) {
				 al = "new Alignment(Alignment.LEFT, Alignment.TOP)";
			} 
		}
		return al;
	}
	

	
	private class TagContainer {
		public Vector<String> 	v_zeilen = new Vector<String>();
		public TAG  			tag = null;
		public Vector<String>   v_zeilen_replaced = new Vector<String>();
		public TagContainer(TAG tag) {
			super();
			this.tag = tag;
		}
	}
	
	
	
	
	private class MODULE_ResourceStringLoader {
		String cTextInFile = null;

		public MODULE_ResourceStringLoader(String DefFileNAME) 	throws myException	{
			super();
			this.cTextInFile = loadTextResource(DefFileNAME);
		}
		
		private String loadTextResource(String fname)	throws myException	{
			String ret = null;
			InputStream is = getResourceStream("panter.gmbh.Echo2.__BASIC_MODULS.CODEGENERATOR.TEMPLATES.RB_MODULES", fname);
			try {
				if (is != null)	{
					StringBuffer sb = new StringBuffer();
					while (true) {
						int c = is.read();
						if (c == -1){
							break;
						}
						sb.append((char) c);
					}
					is.close();
					ret = sb.toString();
				} else {
					throw new myException(this,":loadTextResource:Error opening resourcefile: "+"panter.gmbh.Echo2.__BASIC_MODULS.CODEGENERATOR.TEMPLATES.RB_MODULES/"+fname+"");
				}
			} catch (IOException ex) {
				throw new myException(this,":loadTextResource:IOException with resourcefile: "+"panter.gmbh.Echo2.__BASIC_MODULS.CODEGENERATOR.TEMPLATES.RB_MODULES/"+fname+":"+ex.getLocalizedMessage());
			}
			return ret;
		}

		private InputStream getResourceStream(String pkgname, String fname)	{
			String resname = "/" + pkgname.replace('.', '/') + "/" + fname;
			@SuppressWarnings("rawtypes")
			Class clazz = getClass();
			InputStream is = clazz.getResourceAsStream(resname);
			return is;
		}

		public String get_cText()
		{
			return this.cTextInFile;
		}

	
	}
	
	
	
	
	
	
}
