package jp.shiftsecurity.tech.formatter.ods;

import org.apache.commons.io.FileUtils;
import org.jopendocument.dom.spreadsheet.SpreadSheet;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class StaticKVSheetTest {
    private static final String R_ODS_FILE = "/static-kv.ods";
    private static final Map<String, String> KV_STATIC = new HashMap() {
        {
            put("key1", "foo");
            put("key2", "bar");
            put("key3", "hoge");
            put("key4", "fuga");
        }
    };

    @Test
    public void testLoadMap() throws Exception{
        File tmpFile = File.createTempFile("loadtest", "datasheet");
        InputStream in = this.getClass().getResourceAsStream(R_ODS_FILE);
        FileUtils.copyToFile(in, tmpFile);
        SpreadSheet spreadSheet = SpreadSheet.createFromFile(tmpFile);
        tmpFile.deleteOnExit();
        Map<String, String> kv = StaticKVSheet.loadMap(spreadSheet);
        assertEquals(kv.keySet(), KV_STATIC.keySet());
        for (String key: kv.keySet()) {
            String val1 = kv.get(key);
            String val2 = KV_STATIC.get(key);
            assertEquals(val1, val2);
        }
    }
}
