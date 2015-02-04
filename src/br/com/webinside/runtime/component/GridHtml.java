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
public class GridHtml extends AbstractGridLinear {

	private static final long serialVersionUID = 1L;

    private static Document template;
    /** Tipos de grid Html */
    public static final String[] SUBTYPES =
    {"ATTACH", "FTP", "JAVA", "LOCAL", "POP3", "IMAP", "WIOBJECT"};

    /**
     * Creates a new GridHtml object.
     *
     * @param id DOCUMENT ME!
     */
    public GridHtml(String id) {
        super(id);
        this.setType("HTML");
    }

    /**
     * Creates a new GridHtml object.
     *
     * @param id DOCUMENT ME!
     * @param element DOCUMENT ME!
     */
    public GridHtml(String id, Element element) {
        super(id, element);
        this.setType("HTML");
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setWIType(String value) {
        XMLFunction.setElemValue(grid, "TYPE", value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getWIType() {
        return XMLFunction.getElemValue(grid, "TYPE");
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Document getTemplate() {
        if (template == null) {
            template = CompFunction.getTemplate("grid_html.xml");
        }
        return template;
    }
}
