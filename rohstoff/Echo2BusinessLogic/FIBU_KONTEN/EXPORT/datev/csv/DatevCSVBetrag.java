package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.csv;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class DatevCSVBetrag extends DatevCSVZahl {

	public DatevCSVBetrag(int vorkomma, int nachkomma, int maxLength) {
		super(vorkomma, nachkomma, maxLength);
	}

	@Override
	public void setValue(Object value) {
		if (value instanceof BigDecimal) {
			//super.setValue(((BigDecimal)value).doubleValue());
			
			DecimalFormat f = new DecimalFormat("#0.00");
			super.setValue(f.format(value));
		} else {
			super.setValue(value);
		}
	}
}
