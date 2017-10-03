package saxParse;

        import java.io.File;
        import javax.xml.parsers.SAXParser;
        import javax.xml.parsers.SAXParserFactory;

        import org.xml.sax.Attributes;
        import org.xml.sax.SAXException;
        import org.xml.sax.helpers.DefaultHandler;

public class SaxParse {

    public static void main(String[] args) {

        try {
            File inputFile = new File("file.html");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            UserHandler userhandler = new UserHandler();
            saxParser.parse(inputFile, userhandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class UserHandler extends DefaultHandler {

    boolean bFirstName = false;
    boolean bLastName = false;
    boolean bMarks = false;

    @Override
    public void startElement(
            String uri, String localName, String qName, Attributes attributes)
            throws SAXException {

        if (qName.equalsIgnoreCase("student")) {
            String st_num = attributes.getValue("st_numb");
            System.out.println("Student number : " + st_num);
        } else if (qName.equalsIgnoreCase("name")) {
            bFirstName = true;
        } else if (qName.equalsIgnoreCase("surname")) {
            bLastName = true;
        } else if (qName.equalsIgnoreCase("mark")) {
            bMarks = true;
        }
    }

    @Override
    public void endElement(String uri,
                           String localName, String qName) throws SAXException {

        if (qName.equalsIgnoreCase("student")) {
            System.out.println("\n");
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if (bFirstName) {
            System.out.println("Name: " + new String(ch, start, length));
            bFirstName = false;
        } else if (bLastName) {
            System.out.println("Surname: " + new String(ch, start, length));
            bLastName = false;
        } else if (bMarks) {
            System.out.println("Mark: " + new String(ch, start, length));
            bMarks = false;
        }
    }
}