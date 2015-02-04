/*
 * WEBINSIDE - Ferramenta de produtividade Java
 * Copyright (c) 2011-2012 LINEWEB Solu��es Tecnol�gicas Ltda.
 * Copyright (c) 2009-2010 Inc�gnita Intelig�ncia Digital Ltda.
 *
 * Este programa � software livre; voc� pode redistribu�-lo e/ou modific�-lo 
 * sob os termos da GNU LESSER GENERAL PUBLIC LICENSE (LGPL) conforme publicada 
 * pela Free Software Foundation; vers�o 2.1 da Licen�a.
 * Este programa � distribu�do na expectativa de que seja �til, por�m, SEM 
 * NENHUMA GARANTIA; nem mesmo a garantia impl�cita de COMERCIABILIDADE OU 
 * ADEQUA��O A UMA FINALIDADE ESPEC�FICA.
 * 
 * Consulte a GNU LGPL para mais detalhes.
 * Voc� deve ter recebido uma c�pia da GNU LGPL junto com este programa; se n�o, 
 * veja em http://www.gnu.org/licenses/ 
 */

package br.com.webinside.runtime.component;

import org.jdom.*;

import br.com.webinside.runtime.xml.*;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public class MailList extends AbstractActionElement {

	private static final long serialVersionUID = 1L;

    /** DOCUMENT ME! */
    protected Element listMail;

    /**
     * Creates a new MailList object.
     */
    public MailList() {
        listMail = new Element("LISTMAIL");
        listMail.setAttribute("SEQ", "");
    }

    /**
     * Creates a new MailList object.
     *
     * @param seq DOCUMENT ME!
     */
    public MailList(String seq) {
        if (seq == null) {
            seq = "";
        }
        listMail = new Element("LISTMAIL");
        listMail.setAttribute("SEQ", seq);
    }

    /**
     * Creates a new MailList object.
     *
     * @param element DOCUMENT ME!
     */
    public MailList(Element element) {
        if ((element == null) || (!element.getName().equals("LISTMAIL"))) {
            element = new Element("LISTMAIL");
        }
        if (element.getAttribute("SEQ") == null) {
            element.setAttribute("SEQ", "");
        }
        this.listMail = element;
    }

    /**
     * DOCUMENT ME!
     *
     * @param element DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    protected int insertInto(Element element) {
        if (element == null) {
            return ErrorCode.NULL;
        }
        element.addContent(listMail);
        return ErrorCode.NOERROR;
    }

    /**
     * DOCUMENT ME!
     *
     * @param seq DOCUMENT ME!
     */
    protected void setSeq(String seq) {
        listMail.getAttribute("SEQ").setValue(seq);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getSeq() {
        return listMail.getAttribute("SEQ").getValue();
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setDescription(String value) {
        XMLFunction.setElemValue(listMail, "DESCRIPTION", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getDescription() {
        return XMLFunction.getElemValue(listMail, "DESCRIPTION");
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setFolder(String value) {
        XMLFunction.setElemValue(listMail, "FOLDER", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getFolder() {
        return XMLFunction.getElemValue(listMail, "FOLDER");
    }
    
    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setCondition(String value) {
        XMLFunction.setElemValue(listMail, "CONDITION", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getCondition() {
        return XMLFunction.getElemValue(listMail, "CONDITION");
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setHostId(String value) {
        XMLFunction.setElemValue(listMail, "HOSTID", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getHostId() {
        return XMLFunction.getElemValue(listMail, "HOSTID");
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setGridId(String value) {
        XMLFunction.setElemValue(listMail, "GRIDID", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getGridId() {
        return XMLFunction.getElemValue(listMail, "GRIDID");
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public AbstractActionElement cloneMe() {
        MailList obj = new MailList((Element) listMail.clone());
        return obj;
    }
}
