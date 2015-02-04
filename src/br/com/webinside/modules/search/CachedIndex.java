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

package br.com.webinside.modules.search;

import org.apache.lucene.index.IndexReader;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public class CachedIndex { // an entry in the cache
    /**
     * Keep a cache of open IndexReader's, so that an index does not have to
     * opened for each query.  The cache re-opens an index when it has changed
     * so that additions and deletions are visible ASAP.
     */
    public static java.util.Hashtable indexCache = new java.util.Hashtable(); // name->CachedIndex
    /** DOCUMENT ME! */
    IndexReader reader; // an open reader
    /** DOCUMENT ME! */
    long modified; // reader's modified date
    /** DOCUMENT ME! */
    String name;

    /**
     * Creates a new CachedIndex object.
     *
     * @param name DOCUMENT ME!
     *
     * @throws java.io.IOException DOCUMENT ME!
     */
    CachedIndex(String name) throws java.io.IOException {
        modified = IndexReader.getCurrentVersion(name); // get modified date
        reader = IndexReader.open(name); // open reader
        this.name = name;
    }

    /**
     * DOCUMENT ME!
     *
     * @throws java.io.IOException DOCUMENT ME!
     */
    public void close() throws java.io.IOException {
        reader.close();
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public IndexReader getReader() {
        return reader;
    }

    /**
     * DOCUMENT ME!
     *
     * @throws java.io.IOException DOCUMENT ME!
     */
    public void reopen() throws java.io.IOException {
        reader = IndexReader.open(name);
    }
}
