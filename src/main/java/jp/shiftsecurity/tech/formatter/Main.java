package jp.shiftsecurity.tech.formatter;

import org.jopendocument.dom.ODSingleXMLDocument;
import org.jopendocument.dom.spreadsheet.SpreadSheet;
import org.jopendocument.dom.template.JavaScriptTemplate;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        if(args.length < 3) {
            System.out.println("data.ods template.odt output.odt");
            System.exit(1);
        }
        String odsPath = args[0];
        String odtPath = args[1];
        String outPath = args[2];

        try {
            SpreadSheet spreadSheet = SpreadSheet.createFromFile(new File(odsPath));
            SimpleKVFormatter formatter = new SimpleKVFormatter(spreadSheet);

            JavaScriptTemplate template = new JavaScriptTemplate(odtPath);
            ODSingleXMLDocument doc = formatter.format(template);
            doc.saveAs(new File(outPath));
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}