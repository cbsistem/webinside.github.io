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
public class WIObjectGrid extends AbstractActionElement {

	private static final long serialVersionUID = 1L;

    /** DOCUMENT ME! */
    protected Element wiobjgrid;

    /**
     * Creates a new WIObjectGrid object.
     */
    public WIObjectGrid() {
        wiobjgrid = new Element("WIOBJECTGRID");
        wiobjgrid.setAttribute("SEQ", "");
    }

    /**
     * Creates a new WIObjectGrid object.
     *
     * @param element DOCUMENT ME!
     */
    public WIObjectGrid(Element element) {
        if ((element == null) || (!element.getName().equals("WIOBJECTGRID"))) {
            element = new Element("WIOBJECTGRID");
        }
        if (element.getAttribute("SEQ") == null) {
            element.setAttribute("SEQ", "");
        }
        this.wiobjgrid = element;
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
        element.addContent(wiobjgrid);
        return ErrorCode.NOERROR;
    }

    /**
     * DOCUMENT ME!
     *
     * @param seq DOCUMENT ME!
     */
    protected void setSeq(String seq) {
        wiobjgrid.getAttribute("SEQ").setValue(seq);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getSeq() {
        return wiobjgrid.getAttribute("SEQ").getValue();
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setCondition(String value) {
        XMLFunction.setElemValue(wiobjgrid, "CONDITION", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getCondition() {
        return XMLFunction.getElemValue(wiobjgrid, "CONDITION");
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setWIObjName(String value) {
        XMLFunction.setElemValue(wiobjgrid, "WIOBJNAME", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getWIObjName() {
        return XMLFunction.getElemValue(wiobjgrid, "WIOBJNAME");
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setDescription(String value) {
        XMLFunction.setElemValue(wiobjgrid, "DESCRIPTION", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getDescription() {
        return XMLFunction.getElemValue(wiobjgrid, "DESCRIPTION");
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setGridId(String value) {
        XMLFunction.setElemValue(wiobjgrid, "GRIDID", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getGridId() {
        return XMLFunction.getElemValue(wiobjgrid, "GRIDID");
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setRegisterCondition(String value) {
        XMLFunction.setElemValue(wiobjgrid, "REGISTERCONDITION", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getRegisterCondition() {
        return XMLFunction.getElemValue(wiobjgrid, "REGISTERCONDITION");
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setSubId(String value) {
        XMLFunction.setElemValue(wiobjgrid, "SUBID", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getSubId() {
        return XMLFunction.getElemValue(wiobjgrid, "SUBID");
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public AbstractActionElement cloneMe() {
        WIObjectGrid obj = new WIObjectGrid((Element) wiobjgrid.clone());
        return obj;
    }
}
