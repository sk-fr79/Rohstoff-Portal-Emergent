package panter.gmbh.basics4project;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.xmlDefTools.E2_ModuleContainerLIST_XML;
import panter.gmbh.Echo2.Container.xmlDefTools.ListDef;
import panter.gmbh.Echo2.Container.xmlDefTools.ListDef_NG;
import panter.gmbh.Echo2.Container.xmlDefTools.XStreamLoaderListDefs;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;

public class CallStringAnalyser {

	public static enum ModulTypes {
		echo2_starter,
		echo2_tableedit,
		echo2_xmledit
	}
	 
	
	private String completeCallStringWithUserInfo = null;
	
	//private String 							ModulTypIdentifier = 	null;
	private String 							CallString = 			null;   //je nach typ der modulkenner fuer die fest definierten (echo2_starter),
																			//   der Tablename fuer das echo2_tableedit
																			//   der name des xml-files echo2_xmledit 
	private MyE2_String 					UserText = 				null;
	private CallStringAnalyser.ModulTypes 	CallType = 				null;  

	
	public CallStringAnalyser(String complete_CallStringWithUserInfo) throws myException {
		super();
		this.completeCallStringWithUserInfo = complete_CallStringWithUserInfo;
		
		this.UserText = new MyE2_String(complete_CallStringWithUserInfo.substring(complete_CallStringWithUserInfo.indexOf("@@@@@")+5));
		String cCallingBlock = complete_CallStringWithUserInfo.substring(0,complete_CallStringWithUserInfo.indexOf("@@@@@"));

		if (cCallingBlock.startsWith(CallStringAnalyser.ModulTypes.echo2_starter.toString())) {
			this.CallType =CallStringAnalyser.ModulTypes.echo2_starter;
			this.CallString=cCallingBlock.substring((this.CallType.toString()+":").length());
			if (S.isEmpty(this.UserText)) {
				this.UserText=E2_MODULNAME_ENUM.find_Corresponding_TabText(this.CallString);  //tabtext aus der enum-definition nehmen
			}
		} else if (cCallingBlock.startsWith(CallStringAnalyser.ModulTypes.echo2_tableedit.toString())) {
			this.CallType =CallStringAnalyser.ModulTypes.echo2_tableedit; 
			this.CallString=cCallingBlock.substring((this.CallType.toString()+":").length());
			if (S.isEmpty(this.UserText)) {
				this.UserText=new MyE2_String(S.t("Tabelle:"),S.ut(this.CallString));
			}
		} else if (cCallingBlock.startsWith(CallStringAnalyser.ModulTypes.echo2_xmledit.toString())) {
			this.CallType =CallStringAnalyser.ModulTypes.echo2_xmledit; 
			this.CallString=cCallingBlock.substring((this.CallType.toString()+":").length());
			if (S.isEmpty(this.UserText)) {
				this.UserText=new MyE2_String(S.ut("XML:"+this.CallString));
			}

		} else {
			throw new myException(this,"undefinened Modul-Call-Typ! ");
		}
		
	}
	
	
	/**
	 * bsp: echo2_starter:MODUL_FUHRENFUELLER@@@@Fuhrenzentrale
	 * @return
	 */
	public String get_completeCallStringWithUserInfo() {
		return completeCallStringWithUserInfo;
	}
	
	
	/**
	 * je nach typ der modulkenner fuer die fest definierten (echo2_starter), Tablename fuer das echo2_tableedit, xml-files-name fuer echo2_xmledit 
	 * @return bsp: (aus: echo2_starter:MODUL_FUHRENFUELLER@@@@Fuhrenzentrale) MODUL_FUHRENFUELLER
	 */
	public String get_callString() {
		return CallString;
	}
	
	/**
	 * 
	 * @return bsp: (aus: echo2_starter:MODUL_FUHRENFUELLER@@@@Fuhrenzentrale) new MyE2_String("Fuhrenzentrale")
	 */
	public MyE2_String get_userText() {
		return UserText;
	}
	
	/**
	 * 
	 * @return enum: CallStringAnalyser.ModulTypes.(echo2_starter,echo2_tableedit,echo2_xmledit)
	 */
	public CallStringAnalyser.ModulTypes get_CallType() {
		return CallType;
	}

	
	public E2_BasicModuleContainer  generate_ThisContainer() throws myException {
		
		if (this.get_CallType()==CallStringAnalyser.ModulTypes.echo2_starter) {
			E2_MODULNAME_ENUM.MODUL calledEnum = E2_MODULNAME_ENUM.find_Corresponding_Enum(this.get_callString());
			if (calledEnum!=null) {
				return calledEnum.generate_E2_BasicModuleContainer();
			} else {
				throw new myExceptionForUser(new MyE2_String(S.ut(this.completeCallStringWithUserInfo),S.t(" ... unbekanntes Modul !")));
			}
		} else if (this.get_CallType()==CallStringAnalyser.ModulTypes.echo2_tableedit )	{
			return new E2_ModuleContainerLIST_XML(new Project_TableNamingAgent(),this.CallString,true,true, true, null, null);
		} else if (this.get_CallType()==CallStringAnalyser.ModulTypes.echo2_xmledit )	{
   			String cPath = bibALL.get_XML_LISTDEF_PATH(this.CallString);
			if (S.isEmpty(cPath))  {
				throw new myExceptionForUser(new MyE2_String(S.t("Das gesuchte XML-Def-File: "),S.ut(this.CallString),S.t(" ist nicht im System vorhanden !")));
			} else {
				ListDef_NG oListDef2 = new XStreamLoaderListDefs(cPath).get_oList();
				
				return new E2_ModuleContainerLIST_XML(new Project_TableNamingAgent(), oListDef2);
			}
		}
		
		throw new myExceptionForUser(new MyE2_String(S.ut(this.completeCallStringWithUserInfo),S.t(" ... unbekannte Zuordnung !")));
	}


			
	
}
