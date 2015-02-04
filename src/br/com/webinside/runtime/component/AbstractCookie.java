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
public abstract class AbstractCookie extends AbstractActionElement {

	private static final long serialVersionUID = 1L;
	
	protected Element cookie;

    /**
     * Creates a new AbstractCookie object.
     */
    public AbstractCookie() {
        cookie = new Element("COOKIE");
        cookie.setAttribute("SEQ", "");
    }

    /**
     * Creates a new AbstractCookie object.
     *
     * @param seq DOCUMENT ME!
     */
    public AbstractCookie(String seq) {
        if (seq == null) {
            seq = "";
        }
        cookie = new Element("COOKIE");
        cookie.setAttribute("SEQ", seq);
    }

    /**
     * Creates a new AbstractCookie object.
     *
     * @param element DOCUMENT ME!
     */
    public AbstractCookie(Element element) {
        if ((element == null) || (!element.getName().equals("COOKIE"))) {
            element = new Element("COOKIE");
        }
        if (element.getAttribute("SEQ") == null) {
            element.setAttribute("SEQ", "");
        }
        this.cookie = element;
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
        element.addContent(cookie);
        return ErrorCode.NOERROR;
    }

    /**
     * DOCUMENT ME!
     *
     * @param seq DOCUMENT ME!
     */
    protected void setSeq(String seq) {
        cookie.getAttribute("SEQ").setValue(seq);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getSeq() {
        return cookie.getAttribute("SEQ").getValue();
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setDescription(String value) {
        XMLFunction.setElemValue(cookie, "DESCRIPTION", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getDescription() {
        return XMLFunction.getElemValue(cookie, "DESCRIPTION");
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setCondition(String value) {
        XMLFunction.setElemValue(cookie, "CONDITION", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getCondition() {
        return XMLFunction.getElemValue(cookie, "CONDITION");
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setName(String value) {
        XMLFunction.setElemValue(cookie, "NAME", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getName() {
        return XMLFunction.getElemValue(cookie, "NAME");
    }
}
