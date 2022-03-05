package jp.shiftsecurity.tech.formatter.ods;

import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

import java.util.HashMap;
import java.util.Map;

public class StaticKVSheet {
    private static final String SHEET_NAME_KV = "kv";
    private static final int MAX_ROW = 1048576;

    public static Map<String, String> loadMap(SpreadSheet spreadSheet) {
        Sheet sheet = spreadSheet.getSheet(SHEET_NAME_KV);
        Map<String, String> kv = new HashMap();
        for(int row=1; row<sheet.getRowCount(); row++) {
            if(sheet.isCellValid(0, row)) {
                String key = sheet.getCellAt(0, row).getTextValue();
                String value = sheet.getCellAt(1, row).getTextValue();
                if(key == null || key.isEmpty()) break;
                else kv.put(key, value);
            } else break;
        }

        return kv;
    }
}
