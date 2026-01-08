package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_CONSTANTS_AND_NAMES;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_ListBox;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class ContainerForVerwaltungsTOOLS extends Project_BasicModuleContainer
{

	public static Insets		INSETS_OUTPUT = 	new Insets(3,1,3,1);
	public static Insets		INSETS_INPUT = 		new Insets(5,10,5,5);
	public static Insets		INSETS_LIST = 		new Insets(5,5,5,2);
	
	private MyE2_TabbedPane		oTabbedPane = 		new MyE2_TabbedPane(new MyE2_TabbedPane.MyE2_TabModelLineWrap(new E2_FontPlain(-2),new E2_FontPlain(), 45),null);
	
	MyE2_ListBox oLB = null;
	
	protected void finalize()
	{
	}
	
	
	public ContainerForVerwaltungsTOOLS() throws myException
	{
		super(E2_CONSTANTS_AND_NAMES.NAME_MODUL_DBTOOLS);
		
		this.oTabbedPane.set_bAutoHeight(true);
		
		
		
		// jetzt den aktiven bereich aufbauen
		this.add(this.oTabbedPane,INSETS_INPUT);
			
		
		//2015-11-20: neue seite zum komplettaufbau der Datenbank-views / triggers /sequences
		try
		{
			this.oTabbedPane.add_Tabb(new MyE2_String("DATEN - BANK"),			new PluginCol_DB_refresh_complete(this));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Fehler: Tab: "+"Reports: "+e.getMessage()));
		}
	
		
		
		
		try
		{
			this.oTabbedPane.add_Tabb(new MyE2_String("Reports"),			new PluginCol_ReportCompiler(this));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Fehler: Tab: "+"Reports: "+e.getMessage()));
		}
		
		
		try
		{
			this.oTabbedPane.add_Tabb(new MyE2_String("Report Im/Export"), new PluginCol_ReportBundleHandler(this));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Fehler: Tab: "+"Report Im/Export: "+e.getMessage()));
		}

		
		try
		{
			this.oTabbedPane.add_Tabb(new MyE2_String("Seq/ NumTab"),	 	new PluginCol_Sequence_NumTab_Builder(this));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Fehler: Tab: "+"Seq/NumTab: "+e.getMessage()));
		}
		
		
		try
		{
			this.oTabbedPane.add_Tabb(new MyE2_String("Trigger"),		 	new PluginCol_TriggerBuilder(this));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Fehler: Tab: "+"Trigger: "+e.getMessage()));
		}
		

		
		try
		{
			this.oTabbedPane.add_Tabb(new MyE2_String("Views"),	 	 		new PluginCol_ViewBuilder(this));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Fehler: Tab: "+"Views: "+e.getMessage()));
		}
		
		
		try
		{
			this.oTabbedPane.add_Tabb(new MyE2_String("Watchlist"),	 	 	new PluginCol_FieldWatchList(this));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Fehler: Tab: "+"Watchlist: "+e.getMessage()));
		}
		
		try
		{
			this.oTabbedPane.add_Tabb(new MyE2_String("Aktionen"),		 	new PluginCol_ActionTriggerDef(this));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Fehler: Tab: "+"Trigger: "+e.getMessage()));
		}

		try
		{
			this.oTabbedPane.add_Tabb(new MyE2_String("Regeln"),	 	 	new PluginCol_FieldRule(this));
			//this.oTabbedPane.add_Tabb(new MyE2_String("Feldregeln"),	 	 	new MyE2_Grid(3));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Fehler: Tab: "+"Feldregeln: "+e.getMessage()));
		}

		
		
		try
		{
			this.oTabbedPane.add_Tabb(new MyE2_String("Groovy- Scripte"),	 	 	new PluginCol_Groovy_scripts(this));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Fehler: Tab: "+"Groovy- Scripte: "+e.getMessage()));
		}
		
		
		try
		{
			this.oTabbedPane.add_Tabb(new MyE2_String("Report- Varianten"),	 	 	new PluginCol_ReportVarianten(this));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Fehler: Tab: "+"Report- Varianten: "+e.getMessage()));
		}

		
		//2014-05-06: eMail-Sender-Regel fuer infomails aus masken
		try
		{
			this.oTabbedPane.add_Tabb(new MyE2_String("eMail- Versender- Scripte"),	 	 	new PluginCol_Mask_InfoMailSender(this));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Fehler: Tab: "+"eMail- Versender- Scripte: "+e.getMessage()));
		}
		// --------------------------------------------------------
		
		//20171127: messageProvider
		try
		{
			this.oTabbedPane.add_Tabb(new MyE2_String("Verteiler - System- Meldungen"),	 	 	new PluginCol_MessageProvider_Container(this));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Fehler: Tab: "+"Verteiler - System- Meldungen: "+e.getMessage()));
		}
		
		
		
		
		
		try
		{
			this.oTabbedPane.add_Tabb(new MyE2_String("Summen- Spalten"),	 	 	new PluginCol_List_Calc_Buttons(this));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Fehler: Tab: "+"Summen- Spalten: "+e.getMessage()));
		}

		
		try
		{
			this.oTabbedPane.add_Tabb(new MyE2_String("Jasper- UVZ-Relationen"),	new PluginCol_JasperCompileChain(this));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Fehler: Tab: "+"Jasper- UVZ-Relationen: "+e.getMessage()));
		}

		
		try
		{
			this.oTabbedPane.add_Tabb(new MyE2_String("Daten-Tools"),	 	 	new PluginCol_DataTools(this));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Fehler: Tab: "+"Daten-Tools.: "+e.getMessage()));
		}

		
		
		try
		{
			this.oTabbedPane.add_Tabb(new MyE2_String("Fremdkey- Indizes"),		new PluginCol_FK_Indexing(this));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Fehler: Tab: "+"Fremdkey-Ind.: "+e.getMessage()));
		}
		
		try
		{
			this.oTabbedPane.add_Tabb(new MyE2_String("Such- Indizes"),			new PluginCol_SearchIndexes(this));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Fehler: Tab: "+"Such-Ind.: "+e.getMessage()));
		}
		
		
		try
		{
			this.oTabbedPane.add_Tabb(new MyE2_String("Indizes Neubau"),		new PluginCol_SpeedIndexes(this));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Fehler: Tab: "+"Ind. Neubau: "+e.getMessage()));
		}
		
		
		//2014-06-27: neues tab fuer korrekturen
		try
		{
			this.oTabbedPane.add_Tabb(new MyE2_String("Korre"),		new PluginCol_Korrekturen(this));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Fehler: Tab: "+"Ind. Neubau: "+e.getMessage()));
		}
		
		
		
		
		try
		{
			this.oTabbedPane.add_Tabb(new MyE2_String("Daten Export"),	 	 	new PluginCol_ExportData(this));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Fehler: Tab: "+"Datenexp.: "+e.getMessage()));
		}
		
		
		try
		{
			this.oTabbedPane.add_Tabb(new MyE2_String("Daten Import"),	 	 	new PluginCol_ImportData(this));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Fehler: Tab: "+"Datenimp.: "+e.getMessage()));
		}
		
		
		try
		{
			this.oTabbedPane.add_Tabb(new MyE2_String("DB- Vergleich"), 		new PluginCol_CompareDatabases(this));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Fehler: Tab: "+"DB-Vergleich: "+e.getMessage()));
		}
		
		
		try
		{
			this.oTabbedPane.add_Tabb(new MyE2_String("Kontrolle"), 		new PluginCol_Container4Controlling(this));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Fehler: Tab: "+"Kontrolle: "+e.getMessage()));
		}
		
		
		try
		{
			this.oTabbedPane.add_Tabb(new MyE2_String("Fuhren- abzüge"), 		new PluginCol_Container4FuhrenAbzugsPrueflauf(this));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Fehler: Tab: "+"Fuhrenabzüge: "+e.getMessage()));
		}
		
		try
		{
			this.oTabbedPane.add_Tabb(new MyE2_String("System-\nprogramme"), 		new PluginCol_Aufraeumen(this));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Fehler: Tab: "+"System- programme: "+e.getMessage()));
		}

		
		try
		{
			if (bibALL.get_RECORD_USER().is_DEVELOPER_YES()) {
				this.oTabbedPane.add_Tabb(new MyE2_String("Test-\nProgramme"), 		new PluginCol_Test(this));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Fehler: Tab: "+"Test- Programme: "+e.getMessage()));
		}
		
		
		
	}
	
	

	
	
}
