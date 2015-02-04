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

import java.util.Hashtable;
import java.util.StringTokenizer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;

import br.com.webinside.modules.Util;
import br.com.webinside.runtime.integration.*;
import br.com.webinside.runtime.util.WIMap;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public class WIRemover extends AbstractConnector implements InterfaceParameters {
    //par�metro de entrada
    /** DOCUMENT ME! */
    private static final String IDLIST_VAR = "tmp.idList";
    /** DOCUMENT ME! */
    private static final String INDEX_NAME_VAR = "tmp.indexName";

    //par�metro de sa�da
    /** DOCUMENT ME! */
    private static final String ERROR_VAR = "tmp.remover.error";

    /**
     * DOCUMENT ME!
     *
     * @param context DOCUMENT ME!
     * @param databases DOCUMENT ME!
     * @param headers DOCUMENT ME!
     */
    public void execute(WIMap context, DatabaseAliases databases,
        InterfaceHeaders headers) {
        try {
            synchronized (this) {
                // o nome do indice a ser alterado
                String indexName =
                    context.get("wi.proj.path") + "/WEB-INF/index/"
                    + context.get(WIRemover.INDEX_NAME_VAR);

                // verifica se o indice a ser alterado ja foi aberto para pesquisa
                // e armazenado em cache.
                // Se sim, fecha-o para que possa ser feita a atualizacao.
                IndexReader ir = null;
                CachedIndex ci = null;
                Hashtable indexCache = CachedIndex.indexCache;
                if (indexCache != null) {
                    ci = (CachedIndex) indexCache.get(indexName);
                    ir = (ci != null) ? ci.getReader()
                                      : IndexReader.open(indexName);
                }

                // lista de IDDocs dos documentos a serem removidos do indice
                String idList = context.get(WIRemover.IDLIST_VAR);
                StringTokenizer st = new StringTokenizer(idList, ", ");
                while (st.hasMoreTokens()) {
                    String token = st.nextToken();
                    Term term = new Term("iddoc", token);
                    context.put("tmp.freq", String.valueOf(ir.delete(term)));
                }

                // se o indice nao foi recuperado do cache, feche-o
                if (ci == null) {
                    ir.close();
                } else {
                    ci.close();
                    indexCache.remove(indexName);
                }

                IndexWriter writer =
                    new IndexWriter(indexName, new StandardAnalyzer(), false);
                writer.optimize();
                writer.close();

                //        if(context.get("tmp.erase").equalsIgnoreCase("on")) {
                //          StringTokenizer stNames = new StringTokenizer(context.get("tmp.nameList"), ", ");
                //          st = new StringTokenizer(idList, ", ");
                //          while (stNames.hasMoreTokens()) {
                //            String docName = stNames.nextToken();
                //            String token = st.nextToken();
                //            File fl = new File(context.get("pvt.pubDir")+"/"+docName);
                //            fl.delete();
                //            fl = new File(context.get("pvt.pubDir")+"/imagens/"+token);
                //            File[] fls = fl.listFiles();
                //            for (int i=0;fls!=null && i<fls.length; i++) fls[i].delete();
                //            fl.delete();
                //          }
                //        }
            }
        } catch (Exception e) {
            context.put(WIRemover.ERROR_VAR, Util.stackTraceToString(e));
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public JavaParameter[] getInputParameters() {
        JavaParameter[] jp = new JavaParameter[2];
        jp[0] =
            new JavaParameter(WIRemover.INDEX_NAME_VAR,
                "Nome do �ndice a ser usado");
        jp[1] =
            new JavaParameter(WIRemover.IDLIST_VAR,
                "IDs dos documentos a serem exclu�dos",
                "Lista dos IDs dos documentos separados por v�rgula a serem exclu�dos do �ndice.");
        return jp;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public JavaParameter[] getOutputParameters() {
        JavaParameter[] jp = new JavaParameter[1];
        jp[0] =
            new JavaParameter(WIRemover.ERROR_VAR,
                "Vari�vel que conter� o stack trace se ocorrer algum erro "
                + "durante a remo��o de informa��es do �ndice.");
        return jp;
    }
}
