package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;

import java.io.File;
import java.util.Vector;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.indep.bibALL;

public class PluginCol_ReportCompiler extends Basic_PluginColumn
{

	public PluginCol_ReportCompiler(ContainerForVerwaltungsTOOLS oMothercontainer)
	{
		super(oMothercontainer);
		
		
		/*
		 * jetzt die button-aktivitaeten einbauen
		 */
		this.add(
				new E2_ComponentGroupHorizontal(0,
						new BT_BuildJaspers(new MyE2_String("Compile Mandanten-Reports"),bibALL.get_REPORTPATH_MANDANT(),
													this.get_TextArea4Output(),
													oMothercontainer.get_MODUL_IDENTIFIER(),"COMPILE_MANDANTEN_REPORTS"),
						new Insets(1)),ContainerForVerwaltungsTOOLS.INSETS_LIST);

//		this.add(
//				new E2_ComponentGroupHorizontal(0,
//						new BT_BuildJaspers(new MyE2_String("Compile Mandanten-Reports-Zusätze"),bibALL.get_REPORTPATH_ADDONS_MANDANT(),
//													this.get_TextArea4Output(),
//													oMothercontainer.get_MODUL_IDENTIFIER(),"COMPILE_MANDANTEN_REPORTS_ADDONS"),
//						new Insets(1)),ContainerForVerwaltungsTOOLS.INSETS_LIST);


		this.add(
				new E2_ComponentGroupHorizontal(0,
						new BT_BuildJaspers(new MyE2_String("Compile Reports im <ALLE> Ordner"),bibALL.get_REPORTPATH_ALLE(),
													this.get_TextArea4Output(),
													oMothercontainer.get_MODUL_IDENTIFIER(),"COMPILE_ALLE_REPORTS"),
						new Insets(1)),ContainerForVerwaltungsTOOLS.INSETS_LIST);

//		this.add(
//				new E2_ComponentGroupHorizontal(0,
//						new BT_BuildJaspers(new MyE2_String("Compile Zusätze im <ALLE> Ordner"),bibALL.get_REPORTPATH_ADDONS_ALLE(),
//													this.get_TextArea4Output(),
//													oMothercontainer.get_MODUL_IDENTIFIER(),"COMPILE_ALLE_REPORTS_ADDONS"),
//						new Insets(1)),ContainerForVerwaltungsTOOLS.INSETS_LIST);
//
//		this.add(
//				new E2_ComponentGroupHorizontal(0,
//						new BT_BuildJaspers(new MyE2_String("Compile Listen im <ALLE> Ordner"),bibALL.get_REPORTPATH_LISTEN_ALLE(),
//													this.get_TextArea4Output(),
//													oMothercontainer.get_MODUL_IDENTIFIER(),"COMPILE_LISTEN_ALLE"),
//						new Insets(1)),ContainerForVerwaltungsTOOLS.INSETS_LIST);

		
		//hier den butten hinterlegen, der alles kompiliert (alle Mandanten)
		Vector<String> vAllPaths = new Vector<String>();
		
		File ofileDir = new File(bibALL.get_REPORTPATH_BASE());
		String[] cOrdner = ofileDir.list();
		for (int i=0;i<cOrdner.length;i++)
		{
			if ((new File(bibALL.get_REPORTPATH_BASE()+cOrdner[i])).isDirectory())
			{
				vAllPaths.add(bibALL.get_REPORTPATH_BASE()+cOrdner[i]+File.separator);
//				vAllPaths.add(bibALL.get_REPORTPATH_BASE()+cOrdner[i]+File.separator+"LISTEN"+File.separator);
//				vAllPaths.add(bibALL.get_REPORTPATH_BASE()+cOrdner[i]+File.separator+"ADDONS"+File.separator);
			}
		}

		this.add(
				new E2_ComponentGroupHorizontal(0,
						new BT_BuildJaspers(new MyE2_String("Compile Alle Reports ALLER Mandanten"),vAllPaths,
													this.get_TextArea4Output(),
													oMothercontainer.get_MODUL_IDENTIFIER(),"COMPILE_LISTEN_ALLE"),
						new Insets(1)),ContainerForVerwaltungsTOOLS.INSETS_LIST);
	
		
		this.add(this.get_TextArea4Output(), ContainerForVerwaltungsTOOLS.INSETS_INPUT);
		
	}

}
