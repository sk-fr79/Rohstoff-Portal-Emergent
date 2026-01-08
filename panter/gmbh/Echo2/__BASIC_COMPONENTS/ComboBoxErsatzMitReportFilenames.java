package panter.gmbh.Echo2.__BASIC_COMPONENTS;

import java.io.File;
import java.util.Collections;

import nextapp.echo2.app.Extent;

import panter.gmbh.Echo2.components.DB.MyE2_DB_ComboBoxErsatz;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;

public class ComboBoxErsatzMitReportFilenames extends MyE2_DB_ComboBoxErsatz 
{

	private String[][] 				arrayReportNames = null;

	public ComboBoxErsatzMitReportFilenames(SQLField osqlField, int ColExtentInPixel, int iTextFieldWidth) throws myException 
	{
		super(osqlField);
		
		
		//auslesen der moeglichen report-Namen und zuweisen an das array
		//jetzt eine komponente zur Auswahl der moeglichen reports-files
		VectorSingle  vReportPfade = new VectorSingle();
		vReportPfade.add(bibALL.get_REPORTPATH_MANDANT());
		vReportPfade.add(bibALL.get_REPORTPATH_MANDANT()+"LISTEN/");
		vReportPfade.add(bibALL.get_REPORTPATH_ALLE());
		vReportPfade.add(bibALL.get_REPORTPATH_ALLE()+"LISTEN/");
		
		VectorSingle   vReports = new VectorSingle();
		
		for (String cPfad:vReportPfade)
		{
			
			File ofileDir = new File(cPfad);
			String[] cFiles = ofileDir.list();
			for (int i=0;i<cFiles.length;i++)
			{
				if (!(new File(cPfad+cFiles[i])).isDirectory())
				{
					if (cFiles[i].toLowerCase().endsWith(".jasper"))
					{
						vReports.add(cFiles[i].substring(0,cFiles[i].length()-7));
					}
				}
			}
		}
		
		//damit stehen im Vector vReports alle moeglichen Reports, die in der Pipeline benutzt werden koennen, zur verfuegung
		this.arrayReportNames = new String[vReports.size()][2];
		Collections.sort(vReports);
		for (int i=0;i<vReports.size();i++)
		{
			this.arrayReportNames[i][0] = vReports.get(i);
			this.arrayReportNames[i][1] = vReports.get(i);
		}


		this.set_Varianten(this.arrayReportNames, "Keine Reports", null, false);
		
		this.EXT().set_oColExtent(new Extent(400));
		this.get_oTextField().set_iWidthPixel(300);

		
	}

}
