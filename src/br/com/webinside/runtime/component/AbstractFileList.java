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
public abstract class AbstractFileList extends AbstractActionElement {

	private static final long serialVersionUID = 1L;
	
	/** DOCUMENT ME! */
    protected Element listFile;

    /**
     * Creates a new AbstractFileList object.
     */
    public AbstractFileList() {
        listFile = new Element("LISTFILE");
        listFile.setAttribute("SEQ", "");
    }

    /**
     * Creates a new AbstractFileList object.
     *
     * @param seq DOCUMENT ME!
     */
    public AbstractFileList(String seq) {
        if (seq == null) {
            seq = "";
        }
        listFile = new Element("LISTFILE");
        listFile.setAttribute("SEQ", seq);
    }

    /**
     * Creates a new AbstractFileList object.
     *
     * @param element DOCUMENT ME!
     */
    public AbstractFileList(Element element) {
        if ((element == null) || (!element.getName().equals("LISTFILE"))) {
            element = new Element("LISTFILE");
        }
        if (element.getAttribute("SEQ") == null) {
            element.setAttribute("SEQ", "");
        }
        this.listFile = element;
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
        element.addContent(listFile);
        return ErrorCode.NOERROR;
    }

    /**
     * DOCUMENT ME!
     *
     * @param seq DOCUMENT ME!
     */
    protected void setSeq(String seq) {
        listFile.getAttribute("SEQ").setValue(seq);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getSeq() {
        return listFile.getAttribute("SEQ").getValue();
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setDescription(String value) {
        XMLFunction.setElemValue(listFile, "DESCRIPTION", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getDescription() {
        return XMLFunction.getElemValue(listFile, "DESCRIPTION");
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setCondition(String value) {
        XMLFunction.setElemValue(listFile, "CONDITION", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getCondition() {
        return XMLFunction.getElemValue(listFile, "CONDITION");
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setDirectory(String value) {
        XMLFunction.setElemValue(listFile, "DIRECTORY", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getDirectory() {
        return XMLFunction.getElemValue(listFile, "DIRECTORY");
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setMask(String value) {
        XMLFunction.setElemValue(listFile, "MASK", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getMask() {
        return XMLFunction.getElemValue(listFile, "MASK");
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setGridId(String value) {
        XMLFunction.setElemValue(listFile, "GRIDID", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getGridId() {
        return XMLFunction.getElemValue(listFile, "GRIDID");
    }
}
