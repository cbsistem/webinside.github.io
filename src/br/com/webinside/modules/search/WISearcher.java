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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Field;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Searcher;

import br.com.webinside.runtime.integration.AbstractConnector;
import br.com.webinside.runtime.integration.DatabaseAliases;
import br.com.webinside.runtime.integration.InterfaceHeaders;
import br.com.webinside.runtime.integration.JavaParameter;
import br.com.webinside.runtime.util.WIMap;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public class WISearcher extends AbstractConnector {
    //par�metros de entrada
    /** DOCUMENT ME! */
    private static final String CASE_SENSITIVE_VAR =
        "tmp.searchResult.caseSensitive";
    /** DOCUMENT ME! */
    private static final String REVERSE_VAR = "tmp.searchResult.reverse";
    /** DOCUMENT ME! */
    private static final String ORDER_BY_VAR = "tmp.searchResult.orderBy";
    /** DOCUMENT ME! */
    private static final String INDEX_NAME_VAR = "tmp.indexName";
    /** DOCUMENT ME! */
    private static final String QUERY_VAR = "tmp.search.query";

    private static final String QUERY_OPERATOR = "tmp.search.queryOperator";

    //par�metros de sa�da
    /** DOCUMENT ME! */
    private static final String COUNT_VAR = "tmp.searchResult.count";
    /** DOCUMENT ME! */
    private static final String ELAPSED_TIME_VAR = "tmp.search.time";
    /** DOCUMENT ME! */
    public static final String ERROR_VAR = "tmp.search.error";
    private List inputJavaParameters;
    private List outputJavaParameters;
    private Analyzer analyzer;
    private Searcher searcher;
    private Query query;
    private Hits hits;
    private int queryOperator = QueryParser.DEFAULT_OPERATOR_OR;

    /**
     * Creates a new WISearcher object.
     */
    public WISearcher() {
        registerInputJavaParameters();
        registerOutputJavaParameters();
    }

    /**
     * Creates a new WISearcher object.
     *
     * @param indexName DOCUMENT ME!
     *
     * @throws IOException DOCUMENT ME!
     */
    public WISearcher(String indexName) throws IOException {
        this();
        analyzer = new StandardAnalyzer(Indexer.STOP_WORDS);
        searcher = new IndexSearcher(WISearchUtil.getIndexReader(indexName));
        registerInputJavaParameters();
        registerOutputJavaParameters();
    }

    /**
     * DOCUMENT ME!
     *
     * @param queryString DOCUMENT ME!
     *
     * @throws ParseException DOCUMENT ME!
     */
    public void setQuery(String queryString) throws ParseException {
        query = null;
        if (queryString == null) {
            queryString = "";
        }
        
        QueryParser parser = new QueryParser("contents", analyzer);
        parser.setOperator(queryOperator);
        query = parser.parse(queryString);
        
//        query = QueryParser.parse(queryString, "contents", analyzer);
    }

    /**
     * DOCUMENT ME!
     *
     * @throws IOException DOCUMENT ME!
     */
    public void doIt() throws IOException {
        hits = searcher.search(query);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Hits getHits() {
        return hits;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public int getHitsCount() {
        return hits.length();
    }

    /**
     * DOCUMENT ME!
     *
     * @param hitID DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws IOException DOCUMENT ME!
     */
    public Result getHitInfo(int hitID) throws IOException {
        return new Result(hits.doc(hitID));
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws IOException DOCUMENT ME!
     */
    public Hashtable[] getResults() throws IOException {
        int count = getHitsCount();
        Hashtable[] ht = new Hashtable[count];

        for (int i = 0; i < count; i++) {
            ht[i] = new Hashtable();

            // adiciona a propriedade "id"
            ht[i].put("id", String.valueOf(i));

            // recupera as propriedades de cada um dos documentos encontrados
            // pela pesquisa exceto o seu conte�do pois ele apenas � pesquis�vel
            // e disponibiliza-as no contexto do WI para poderem ser
            // referenciadas na p�gina de modelo de um grid Java
            Enumeration e = getHitInfo(i).getFields();
            while (e.hasMoreElements()) {
                Field field = (Field) e.nextElement();
                String fieldName = field.name();

                //se n�o for o campo que cont�m o conte�do ent�o processe...
                if (!fieldName.equalsIgnoreCase("contents")) {
                    String name = null;

                    //essa condi��o existe para dar suporte a alguma aplica��o
                    //legada que ainda n�o oferecia suporte � propriedade .content
                    if (fieldName.endsWith(".content")) {
                        name =
                            fieldName.substring(0, fieldName.indexOf(".content"));
                    } else if (!ht[i].containsKey(fieldName)) {
                        name = fieldName;
                    }
                    ht[i].put(name, field.stringValue());
                    //ht[i].put(field.name(), field.stringValue());
                }
            }
        }
        return ht;
    }

    private void registerInputJavaParameters() {
        inputJavaParameters = new ArrayList();
        JavaParameter jp1 =
            new JavaParameter(WISearcher.CASE_SENSITIVE_VAR,
                "Busca sens�vel ao caso");
        inputJavaParameters.add(jp1);
        JavaParameter jp2 =
            new JavaParameter(WISearcher.INDEX_NAME_VAR,
                "Nome do �ndice a ser usado");
        inputJavaParameters.add(jp2);
        JavaParameter jp3 =
            new JavaParameter(WISearcher.ORDER_BY_VAR,
                "Nome da propriedade a ser ordenada");
        inputJavaParameters.add(jp3);
        JavaParameter jp4 =
            new JavaParameter(WISearcher.REVERSE_VAR,
                "Ordernar descendentemente");
        inputJavaParameters.add(jp4);
        JavaParameter jp5 =
            new JavaParameter(WISearcher.QUERY_VAR, "<em>Query</em> de pesquisa");
        inputJavaParameters.add(jp5);
        JavaParameter jp6 =
            new JavaParameter(WISearcher.QUERY_OPERATOR, "Operador (AND | OR)");
        inputJavaParameters.add(jp6);
    }

    private void registerOutputJavaParameters() {
        outputJavaParameters = new ArrayList();
        JavaParameter jp1 =
            new JavaParameter(WISearcher.COUNT_VAR,
                "Quantidade de documentos retornados pela pesquisa.");
        outputJavaParameters.add(jp1);
        JavaParameter jp2 =
            new JavaParameter(WISearcher.ELAPSED_TIME_VAR,
                "Tempo gasto pela pesquisa em milisegundos.");
        outputJavaParameters.add(jp2);
        JavaParameter jp3 =
            new JavaParameter(WISearcher.ERROR_VAR,
                "Vari�vel que conter� o stack trace se ocorrer "
                + "algum erro durante a pesquisa");
        outputJavaParameters.add(jp3);
    }

    /**
     * DOCUMENT ME!
     *
     * @param context DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws IOException DOCUMENT ME!
     * @throws ParseException DOCUMENT ME!
     */
    public Hashtable[] getResultsIntoHashtable(WIMap context)
        throws IOException, ParseException {
        Hashtable[] ht = null;

        // no contexto do usu�rio deve haver uma vari�vel chamada
        // "tmp.indexName" ou "idx.indexName" indicando qual o �ndice
        // a ser usado para pesquisa e uma vari�vel chamada "idx.query"
        // ou "tmp.search.query"  contendo a express�o de busca
        String in = context.get("idx.indexName");
        if (in.equals("")) {
            in = context.get(WISearcher.INDEX_NAME_VAR);
        }
        String indexName = context.get("wi.proj.path") + "/WEB-INF/index/" + in;
        WISearcher ms = new WISearcher(indexName);

        String operator = context.get(WISearcher.QUERY_OPERATOR);
        if ("and".equalsIgnoreCase(operator)) {
            ms.setQueryOperator(QueryParser.DEFAULT_OPERATOR_AND);
        }

        String qry = context.get("idx.query");
        if (qry.equals("")) {
            qry = context.get(WISearcher.QUERY_VAR);
        }
        ms.setQuery(WISearchUtil.limparConteudo(qry));
        // coloca no contexto a vari�vel "tmp.search.query" que indica a
        // express�o de busca utilizada
        context.put(WISearcher.QUERY_VAR, qry);

        long inicio = System.currentTimeMillis();
        ms.doIt();
        long tempo = System.currentTimeMillis() - inicio;

        // coloca no contexto a vari�vel "tmp.search.time" que indica a
        // dura��o da realiza��o da pesquisa
        context.put(WISearcher.ELAPSED_TIME_VAR, String.valueOf(tempo));

        int count = ms.getHitsCount();

        // deprecated - usar tmp.searchResult.count por ser coloquialmente mais l�gica
        context.put("tmp.search.count", String.valueOf(count));
        // coloca no contexto a vari�vel "tmp.searchResult.count" que retorna
        // a quantidade de documentos encontrada pela busca
        context.put(WISearcher.COUNT_VAR, String.valueOf(count));

        // propriedades que esse grid possui...
        ht = ms.getResults();

        // a variavel "tmp.searchResult.orderBy" deve conter o nome de uma das propriedades
        // do documento que ser� usada como base para ordena��o do resultado da busca
        String orderBy = context.get(WISearcher.ORDER_BY_VAR);

        // a variavel "tmp.searchResult.reverse" indica se a ordena��o do resultado
        // da busca ser� feita de maneira descendente (reversa). O valor default para
        // essa vari�vel � false, sendo assim os dados a princ�pio v�m em ordem crescente.
        // Essa vari�vel s� tem fun��o quando em uso conjunto com "tmp.searchResult.orderBy".
        boolean reverse =
            context.get(WISearcher.REVERSE_VAR).equalsIgnoreCase("true");

        // a variavel "tmp.searchResult.caseSensitive" indica se a ordena��o do resultado
        // da busca ser� "sens�vel ao caso". O valor default para essa vari�vel � false.
        // Essa vari�vel s� tem fun��o quando em uso conjunto com "tmp.searchResult.orderBy".
        boolean caseSensitive =
            context.get(WISearcher.CASE_SENSITIVE_VAR).equalsIgnoreCase("true");
        if (!orderBy.equals("")) {
            Arrays.sort(ht, new WIComparator(orderBy, reverse, caseSensitive));
        }
        return ht;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public List getInputJavaParameters() {
        return inputJavaParameters;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public List getOutputJavaParameters() {
        return outputJavaParameters;
    }

    /**
     * DOCUMENT ME!
     *
     * @param context DOCUMENT ME!
     * @param databases DOCUMENT ME!
     * @param headers DOCUMENT ME!
     */
    public void execute(WIMap context, DatabaseAliases databases,
        InterfaceHeaders headers) {
    }

    protected int getQueryOperator() {
        return queryOperator;
    }
    
    protected void setQueryOperator(int queryOperator) {
        this.queryOperator = queryOperator;
    }
}
