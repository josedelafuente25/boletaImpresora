package com.micropos.boletaelectronica.util;

import java.io.File;
import java.io.StringBufferInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Xml {
    private NodeList olst;
    private Document oxml;

    public Xml() {
    }

    public Xml(String str) throws Exception {
        load(str);
    }

    public void load(String str) throws Exception {
        try {
            this.oxml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(str)));
            this.olst = (NodeList) XPathFactory.newInstance().newXPath().compile("tabla/fila").evaluate(this.oxml, XPathConstants.NODESET);
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Error en funcion load xml mensaje:");
            sb.append(e.getMessage());
            throw new Exception(sb.toString());
        }
    }

    public String toString() {
        String str = ">";
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("<tabla>");
            for (int i = 0; i < count().intValue(); i++) {
                sb.append("<fila>");
                for (int i2 = 0; i2 < countField().intValue(); i2++) {
                    String name = getName(Integer.valueOf(i2));
                    String str2 = get(name, Integer.valueOf(i));
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("<");
                    sb2.append(name);
                    sb2.append(str);
                    sb.append(sb2.toString());
                    sb.append(str2);
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("</");
                    sb3.append(name);
                    sb3.append(str);
                    sb.append(sb3.toString());
                }
                sb.append("</fila>");
            }
            sb.append("</tabla>");
            return sb.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public Integer count() {
        return Integer.valueOf(this.olst.getLength());
    }

    public Integer countField() {
        return Integer.valueOf(this.olst.item(0).getChildNodes().getLength());
    }

    public String get(String str, Integer num) throws Exception {
        if (num.intValue() < count().intValue()) {
            return value(((Element) this.olst.item(num.intValue())).getElementsByTagName(str).item(0));
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Fila solicitada en xml es mayor (");
        sb.append(num.toString());
        sb.append(") al maximo (");
        sb.append(count().toString());
        sb.append(")");
        throw new Exception(sb.toString());
    }

    public void set(String str, Integer num, String str2) throws Exception {
        if (num.intValue() < count().intValue()) {
            ((Element) this.olst.item(num.intValue())).getElementsByTagName(str).item(0).setTextContent(str2);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Fila solicitada en xml es mayor (");
        sb.append(num.toString());
        sb.append(") al maximo (");
        sb.append(count().toString());
        sb.append(")");
        throw new Exception(sb.toString());
    }

    public String get(Integer num, Integer num2) throws Exception {
        String str = ")";
        String str2 = ") al maximo (";
        if (num2.intValue() >= count().intValue()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Fila solicitada en xml es mayor (");
            sb.append(num2.toString());
            sb.append(str2);
            sb.append(count().toString());
            sb.append(str);
            throw new Exception(sb.toString());
        } else if (num.intValue() < countField().intValue()) {
            return value(this.olst.item(num2.intValue()).getChildNodes().item(num.intValue()));
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Columna solicitada en xml es mayor (");
            sb2.append(num.toString());
            sb2.append(str2);
            sb2.append(countField().toString());
            sb2.append(str);
            throw new Exception(sb2.toString());
        }
    }

    public String getName(Integer num, Integer num2) throws Exception {
        String str = ")";
        String str2 = ") al maximo (";
        if (num2.intValue() >= count().intValue()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Fila solicitada en xml es mayor (");
            sb.append(num2.toString());
            sb.append(str2);
            sb.append(count().toString());
            sb.append(str);
            throw new Exception(sb.toString());
        } else if (num.intValue() < countField().intValue()) {
            return this.olst.item(num2.intValue()).getChildNodes().item(num.intValue()).getNodeName();
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Columna solicitada en xml es mayor (");
            sb2.append(num.toString());
            sb2.append(str2);
            sb2.append(countField().toString());
            sb2.append(str);
            throw new Exception(sb2.toString());
        }
    }

    public String get(String str) throws Exception {
        return get(str, Integer.valueOf(0));
    }

    public String get(Integer num) throws Exception {
        return get(num, Integer.valueOf(0));
    }

    public String getName(Integer num) throws Exception {
        return getName(num, Integer.valueOf(0));
    }

    public String value(Node node) {
        return node != null ? node.getTextContent() : "";
    }

    public String xmlnodo(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(str);
        String str3 = ">";
        sb.append(str3);
        sb.append(str2);
        sb.append("</");
        sb.append(str);
        sb.append(str3);
        return sb.toString();
    }

    public String writexml(String str, String str2) throws Exception {
        String str3 = "&";
        String str4 = "&amp;";
        try {
            String replaceAll = stripNonValidXMLCharacters(str).replaceAll(str4, str3).replaceAll(str3, str4);
            StringBuilder sb = new StringBuilder();
            sb.append("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>");
            sb.append(replaceAll);
            StreamSource streamSource = new StreamSource(new StringBufferInputStream(sb.toString()));
            StreamSource streamSource2 = new StreamSource(new File(str2));
            StringWriter stringWriter = new StringWriter();
            TransformerFactory.newInstance().newTransformer(streamSource2).transform(streamSource, new StreamResult(stringWriter));
            StringBuilder sb2 = new StringBuilder();
            sb2.append("<!DOCTYPE HTML>");
            sb2.append(stringWriter.toString());
            return sb2.toString();
        } catch (Exception e) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Error en trasformar xml fucion:writexml mensaje:");
            sb3.append(e.getMessage());
            throw new Exception(sb3.toString());
        }
    }

    public String writexml2(String str, String str2) throws Exception {
        String str3 = "&";
        String str4 = "&amp;";
        try {
            String replaceAll = stripNonValidXMLCharacters(str).replaceAll(str4, str3).replaceAll(str3, str4).replaceAll("\\\\", "\\\\\\\\");
            StringBuilder sb = new StringBuilder();
            sb.append("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>");
            sb.append(replaceAll);
            StreamSource streamSource = new StreamSource(new StringBufferInputStream(sb.toString()));
            StreamSource streamSource2 = new StreamSource(new File(str2));
            StringWriter stringWriter = new StringWriter();
            TransformerFactory.newInstance().newTransformer(streamSource2).transform(streamSource, new StreamResult(stringWriter));
            return stringWriter.toString();
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Error en trasformar xml fucion:writexml mensaje:");
            sb2.append(e.getMessage());
            throw new Exception(sb2.toString());
        }
    }

    public String stripNonValidXMLCharacters(String str) {
        boolean z;
        int i;
        int i2;
        if (str == null || str.isEmpty()) {
            return str;
        }
        int length = str.length();
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < length; i3 = i + 1) {
            char charAt = str.charAt(i3);
            if (Character.isHighSurrogate(charAt)) {
                i = i3 + 1;
                if (i < length && Character.isLowSurrogate(str.charAt(i))) {
                    z = true;
                    i2 = str.codePointAt(i3);
                    if ((i2 < 32 && i2 <= 126) || i2 == 193 || i2 == 201 || i2 == 205 || i2 == 211 || i2 == 218 || i2 == 225 || i2 == 233 || i2 == 237 || i2 == 243 || i2 == 250 || i2 == 209 || i2 == 241 || i2 == 186 || i2 == 180 || i2 == 252 || i2 == 220) {
                        sb.append(charAt);
                        if (z) {
                            sb.append(str.charAt(i));
                        }
                    }
                }
            }
            i = i3;
            i2 = charAt;
            z = false;
            if (i2 < 32) {
            }
        }
        return sb.toString();
    }
}

