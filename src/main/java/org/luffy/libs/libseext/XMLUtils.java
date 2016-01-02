/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luffy.libs.libseext;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;

/**
 * @author luffy
 */
public class XMLUtils {

    public static Document xml2doc(InputStream inputStream) throws IOException, SAXException, ParserConfigurationException {
        return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream);
    }

    public static Document newDocument() throws ParserConfigurationException {
        return DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
    }

    public static void doc2stream(Document doc, StreamResult result) throws TransformerConfigurationException, TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        transformer.transform(source, result);
    }

    public static void doc2stream(Document doc, OutputStream out) throws TransformerConfigurationException, TransformerException {
        doc2stream(doc, new StreamResult(out));
    }

    public static String doc2str(Document doc) throws TransformerConfigurationException, TransformerException {
        StreamResult result = new StreamResult(new StringWriter());
        doc2stream(doc, result);
        String xmlString = result.getWriter().toString();
        return xmlString;
    }

    public static boolean hasChild(Node xml, String name) {
        NodeList nl = xml.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node n = nl.item(i);
            if (name.equalsIgnoreCase(n.getNodeName()))
                return true;
        }
        return false;
    }
}
