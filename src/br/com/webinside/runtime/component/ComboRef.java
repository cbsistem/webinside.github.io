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
 * @version $Revision: 1.2 $
 */
public class ComboRef extends AbstractActionElement {

	private static final long serialVersionUID = 1L;

	/** DOCUMENT ME! */
    protected Element comboRef;

    /**
     * Creates a new ComboRef object.
     */
    public ComboRef() {
        comboRef = new Element("COMBOREF");
        comboRef.setAttribute("SEQ", "");
    }

    /**
     * Creates a new ComboRef object.
     *
     * @param element DOCUMENT ME!
     */
    public ComboRef(Element element) {
        if ((element == null) || (!element.getName().equals("COMBOREF"))) {
            element = new Element("COMBOREF");
        }
        if (element.getAttribute("SEQ") == null) {
            element.setAttribute("SEQ", "");
        }
        this.comboRef = element;
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
        element.addContent(comboRef);
        return ErrorCode.NOERROR;
    }

    /**
     * DOCUMENT ME!
     *
     * @param seq DOCUMENT ME!
     */
    public void setSeq(String seq) {
        comboRef.getAttribute("SEQ").setValue(seq);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getSeq() {
        return comboRef.getAttribute("SEQ").getValue();
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setCondition(String value) {
        XMLFunction.setElemValue(comboRef, "CONDITION", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getCondition() {
        return XMLFunction.getElemValue(comboRef, "CONDITION");
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setId(String value) {
        XMLFunction.setElemValue(comboRef, "COMBOID", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getId() {
        return XMLFunction.getElemValue(comboRef, "COMBOID");
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setSubID(String value) {
        XMLFunction.setElemValue(comboRef, "SUBID", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getSubID() {
        return XMLFunction.getElemValue(comboRef, "SUBID");
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setSelected(String value) {
        XMLFunction.setElemValue(comboRef, "SELECTED", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getSelected() {
        return XMLFunction.getElemValue(comboRef, "SELECTED");
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setSecureVar(String value) {
        XMLFunction.setElemValue(comboRef, "SECUREVAR", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getSecureVar() {
        return XMLFunction.getElemValue(comboRef, "SECUREVAR");
    }
    
    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setOptionParam(String value) {
        XMLFunction.setElemValue(comboRef, "OPTIONPARAM", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getOptionParam() {
        return XMLFunction.getElemValue(comboRef, "OPTIONPARAM");
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public AbstractActionElement cloneMe() {
        ComboRef obj = new ComboRef((Element) comboRef.clone());
        return obj;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getDescription() {
        return "Combo: " + getId();
    }

    /**
     * DOCUMENT ME!
     *
     * @param description DOCUMENT ME!
     */
    public void setDescription(String description) {
    }
}
