package com.thoughtworks.xstream.io.xml;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Parent;

/**
 * @author Laurent Bihanic
 */
public class JDomReader extends AbstractTreeReader {

    private Element currentElement;

    public JDomReader(Element root) {
        super(root);
    }

    public JDomReader(Document document) {
        super(document.getRootElement());
    }

    protected void reassignCurrentElement(Object current) {
        currentElement = (Element) current;
    }

    protected Object getParent() {
        // JDOM b9 and earlier:
        // return currentElement.getParent();

        // JDOM b10 and later:
        Parent parent = currentElement.getParent();
        return (parent instanceof Element) ? (Element)parent : null;
    }

    protected Object getChild(int index) {
        return currentElement.getChildren().get(index);
    }

    protected int getChildCount() {
        return currentElement.getChildren().size();
    }

    public String getNodeName() {
        return currentElement.getName();
    }

    public String getValue() {
        return currentElement.getValue();
    }

    public String getAttribute(String name) {
        return currentElement.getAttributeValue(name);
    }

}

