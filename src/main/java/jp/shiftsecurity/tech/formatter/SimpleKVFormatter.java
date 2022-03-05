package jp.shiftsecurity.tech.formatter;

import jp.shiftsecurity.tech.formatter.ods.StaticKVSheet;
import org.jopendocument.dom.ODSingleXMLDocument;
import org.jopendocument.dom.spreadsheet.SpreadSheet;
import org.jopendocument.dom.template.JavaScriptTemplate;
import org.jopendocument.dom.template.TemplateException;

import java.util.Map;

public class SimpleKVFormatter {
    private Map<String, String> kv;
    public SimpleKVFormatter(Map<String, String> kv) {
        this.kv = kv;
    }
    public SimpleKVFormatter(SpreadSheet spreadSheet) {
        Map<String, String> kv = StaticKVSheet.loadMap(spreadSheet);
        this.kv = kv;
    }
    public ODSingleXMLDocument format(JavaScriptTemplate template) throws TemplateException {
        for (String key:kv.keySet()) {
            String value = kv.get(key);
            template.setField(key, value);
        }
        return template.createDocument();
    }
}
