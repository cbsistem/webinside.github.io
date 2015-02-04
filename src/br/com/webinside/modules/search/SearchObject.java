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

import java.io.*;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import br.com.webinside.modules.Util;
import br.com.webinside.runtime.integration.*;
import br.com.webinside.runtime.util.WIMap;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public class SearchObject extends WISearcher implements InterfaceParameters {
    /** DOCUMENT ME! */
    private static final String OBJECT_ID_VAR = "tmp.prefix";

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
            Hashtable[] ht = getResultsIntoHashtable(context);

            //prefixo que ser� usado como identificador do objeto a ser criado
            String prefix = context.get(SearchObject.OBJECT_ID_VAR);

            //popula o contexto com vari�veis baseadas no prefixo que foi passado pela
            //vari�vel tmp.prefix, os valores dessas vari�veis ser�o prrenchidos de
            //acordo com os resultados vindo da pesquisa
            for (int i = 0; i < ht.length; i++) {
                Enumeration keys = ht[i].keys();
                while (keys.hasMoreElements()) {
                    String key = (String) keys.nextElement();
                    String value = (String) ht[i].get(key);
                    String newKey =
                        prefix + '[' + String.valueOf(i + 1) + "]." + key;
                    context.put(newKey, value);
                    if (i == 0) {
                        context.put(prefix + '.' + key, value);
                    }
                }
            }
            context.put(prefix + ".size()", ht.length);
        } catch (Exception e) {
            context.put(WISearcher.ERROR_VAR, Util.stackTraceToString(e));
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param context DOCUMENT ME!
     */
    public void executeOld(WIMap context) {
        try {
            String in = context.get("tmp.indexName");
            String indexName = context.get("wi.proj.root") + "/index/" + in;
            WISearcher ms = new WISearcher(indexName);

            String query = context.get("tmp.search.query");
            ms.setQuery(WISearchUtil.limparConteudo(query));
            // coloca no contexto a vari�vel "tmp.search.query" que indica a
            // express�o de busca
            context.put("tmp.search.query", query);

            long inicio = System.currentTimeMillis();
            ms.doIt();
            long tempo = System.currentTimeMillis() - inicio;

            // coloca no contexto a vari�vel "tmp.search.time" que indica a
            // dura��o da realiza��o da pesquisa
            context.put("tmp.search.time", String.valueOf(tempo));

            int count = ms.getHitsCount();

            // deprecated - usar tmp.searchResult.count por ser coloquialmente mais l�gico
            context.put("tmp.search.count", String.valueOf(count));
            // coloca no contexto a vari�vel "tmp.searchResult.count" que retorna
            // a quantidade de documentos encontrada pela busca
            context.put("tmp.searchResult.count", String.valueOf(count));

            // prefixo que ser� usado para identifica��o do objeto
            String prefix = context.get(SearchObject.OBJECT_ID_VAR);

            // resultado da busca...
            Hashtable[] ht = ms.getResults();

            // a variavel "tmp.searchResult.orderBy" indica o nome de uma das propriedades
            // do documento que ser� usada como base para ordena��o do resultado da busca
            String orderBy = context.get("tmp.searchResult.orderBy");

            // a variavel "tmp.searchResult.reverse" indica se a ordena��o do resultado
            // da busca ser� feita de maneira descendente (reversa). O valor default para
            // essa vari�vel � false.
            // Essa vari�vel s� tem fun��o quando em uso conjunto com "tmp.searchResult.orderBy".
            boolean reverse =
                context.get("tmp.searchResult.reverse").equalsIgnoreCase("true");

            // a variavel "tmp.searchResult.caseSensitive" indica se a ordena��o do resultado
            // da busca ser� "sens�vel ao caso". O valor default para essa vari�vel � false.
            // Essa vari�vel s� tem fun��o quando em uso conjunto com "tmp.searchResult.orderBy".
            boolean caseSensitive =
                context.get("tmp.searchResult.caseSensitive").equalsIgnoreCase("true");
            if (!orderBy.equals("")) {
                Arrays.sort(ht,
                    new WIComparator(orderBy, reverse, caseSensitive));
            }

            //popula o contexto com vari�veis baseadas num prefixo que foi passado pela
            //vari�vel tmp.prefix cujos valores ser�o de acordo com os resultados...
            for (int i = 0; i < ht.length; i++) {
                Enumeration keys = ht[i].keys();
                while (keys.hasMoreElements()) {
                    String key = (String) keys.nextElement();
                    String value = (String) ht[i].get(key);
                    String newKey =
                        prefix + '[' + String.valueOf(i + 1) + "]." + key;
                    context.put(newKey, value);
                    if (i == 0) {
                        context.put(prefix + '.' + key, value);
                    }
                }
            }

            context.put(prefix + ".size()", ht.length);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            context.put(WISearcher.ERROR_VAR, sw.toString());
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public JavaParameter[] getInputParameters() {
        List l = getInputJavaParameters();
        l.add(new JavaParameter(SearchObject.OBJECT_ID_VAR, "Objeto"));
        JavaParameter[] jp = new JavaParameter[l.size()];
        l.toArray(jp);
        return jp;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public JavaParameter[] getOutputParameters() {
        List l = getOutputJavaParameters();
        JavaParameter[] jp = new JavaParameter[l.size()];
        l.toArray(jp);
        return jp;
    }
}
