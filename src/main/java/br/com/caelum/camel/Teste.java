package br.com.caelum.camel;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

public class Teste {

    public static void main(String[] args) throws Exception {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        File pom = new File("pom.xml");
        Document doc = builder.parse(new FileInputStream(pom));

        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        String xpathString = "/project/properties/camel-version";
        XPathExpression expr = xpath.compile(xpathString);

        String versao = expr.evaluate(doc);
    }
}