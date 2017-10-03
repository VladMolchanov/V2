package saxParse;

        import org.xml.sax.Attributes;
        import org.xml.sax.SAXException;
        import org.xml.sax.helpers.DefaultHandler;

public class Handler extends DefaultHandler {

    boolean bFirstName = false;
    boolean bLastName = false;
    boolean bMarks = false;

    @Override
    public void startElement(String uri,
                             String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("student")) {
            String rollNo = attributes.getValue("st_numb");
            System.out.println("Student number : " + rollNo);
        } else if (qName.equalsIgnoreCase("name")) {
            bFirstName = true;
        } else if (qName.equalsIgnoreCase("surname")) {
            bLastName = true;
        }
        else if (qName.equalsIgnoreCase("mark")) {
            bMarks = true;
        }
    }

    @Override
    public void endElement(String uri,
                           String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("student")) {
            System.out.println("End Element :" + qName);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if (bFirstName) {
            System.out.println("Name: "
                    + new String(ch, start, length));
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