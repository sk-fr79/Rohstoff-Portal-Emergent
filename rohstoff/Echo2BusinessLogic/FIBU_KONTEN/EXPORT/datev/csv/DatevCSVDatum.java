package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.csv;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatevCSVDatum extends DatevCSVText {

	public DatevCSVDatum() {
		super(8, 10);
	}
	
	public void setValue(final String value) {
		this.value = value;
	}
	
	public void setValue(final Object value) {
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
		this.value = formatter.format((Date)value);
	}
	
	public String print() {
		return (this.value != null ? this.value : "");
	}
}
