package panter.gmbh.Echo2.__BASIC_MODULS.DRUCKER;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.DRUCKER;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class DRUCK_Test_process {

	private String filename_out = bibALL.get_CompleteOutPutPath(true) + "test.pdf";

	private Rec20 drucker_definition = null;

	public DRUCK_Test_process(Rec20 drucker_definition_record) throws DocumentException, myException, IOException, InterruptedException {
		super();

		this.drucker_definition = drucker_definition_record; 

		generate_dynamic_pdf_document();

		String command_line_4_druck = get_direct_druck_befehl();

		if(S.isFull(command_line_4_druck)){
			if(command_line_4_druck.startsWith("lp -d")){

				command_line_4_druck = command_line_4_druck.replaceAll("#datei#", filename_out);

				DEBUG.System_println("Drucker Test:"+ command_line_4_druck);

				Process p = Runtime.getRuntime().exec(command_line_4_druck);

				p.waitFor();

			}else{

			}
		}
	}

	public String get_direct_druck_befehl() throws myException{
		return this.drucker_definition.get_fs_dbVal(DRUCKER.direct_druck_befehl);
	}

	public Document generate_dynamic_pdf_document() throws FileNotFoundException, DocumentException, myException{

		Document document = new Document(PageSize.A4);

		PdfWriter.getInstance(document, new FileOutputStream(filename_out));

		document.open();

		Chunk glue = new Chunk(new VerticalPositionMark());

		FontSelector selector1 = new FontSelector();
		Font f1 = FontFactory.getFont(FontFactory.HELVETICA, 10);

		FontSelector selector2 = new FontSelector();
		Font f2 = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 8);

		Paragraph p1 = new Paragraph("Drucker Test");
		p1.add(new Chunk(glue));
		p1.add("Panter Datentechnik GmbH");

		Paragraph p2 = new Paragraph();
		Paragraph p3 = new Paragraph();


		int[] tableWidth = {240,400};
		PdfPTable table = new PdfPTable(2);
		table.setWidths(tableWidth);
		table.setWidthPercentage(100f);
		table.getDefaultCell().setBorder(0);
		table.addCell("* Name:" );
		table.addCell(this.drucker_definition.get_fs_dbVal(DRUCKER.name));
		table.addCell("* Standort:" );
		table.addCell(this.drucker_definition.get_fs_dbVal(DRUCKER.standort));
		table.addCell("* Beschreibung:" );
		table.addCell(this.drucker_definition.get_fs_dbVal(DRUCKER.beschreibung));
		table.addCell("* Befehl:" );
		table.addCell(this.drucker_definition.get_fs_dbVal(DRUCKER.direct_druck_befehl));
		table.setSpacingBefore(25);


		LineSeparator separatorLine = new LineSeparator();
		p2.add(separatorLine);
		p3.add(table);

		document.add(p1);
		document.add(p2);
		document.add(p3);

		document.close();

		return document;
	}

}
