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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom.Attribute;
import org.jdom.Element;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public class JdbcAliases {
    private Element aliases;

    /**
     * Creates a new JdbcAliases object.
     */
    public JdbcAliases() {
        aliases = new Element("ALIASES");
    }

    /**
     * Creates a new JdbcAliases object.
     *
     * @param ele DOCUMENT ME!
     */
    public JdbcAliases(Element ele) {
        if ((ele == null) || !ele.getName().equals("ALIASES")) {
            aliases = new Element("ALIASES");
        } else {
            aliases = ele;
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param id DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public JdbcAlias get(String id) {
        List vet = getAlias(id);
        if (vet.size() == 0) {
            return null;
        }
        return (JdbcAlias) vet.get(0);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public List get() {
        return getAlias("");
    }

    private List getAlias(String id) {
        if (id == null) {
            id = "";
        }
        List vet = new ArrayList();
        Iterator list = aliases.getChildren().iterator();
        while (list.hasNext()) {
            Element jdbc = (Element) list.next();
            Attribute attr = jdbc.getAttribute("DISABLED");
            if ((attr == null) || !attr.getValue().equalsIgnoreCase("on")) {
                if (id.equals("")) {
                    vet.add(new JdbcAlias(jdbc));
                } else {
                    Attribute attrid = jdbc.getAttribute("ID");
                    if ((attrid != null)
                                && attrid.getValue().equalsIgnoreCase(id)) {
                        vet.add(new JdbcAlias(jdbc));
                        return vet;
                    }
                }
            }
        }
        return vet;
    }
}
