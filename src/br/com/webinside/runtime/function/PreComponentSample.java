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

package br.com.webinside.runtime.function;

import java.io.*;
import java.sql.*;
import br.com.webinside.runtime.core.*;
import br.com.webinside.runtime.database.impl.ConnectionSql;
import br.com.webinside.runtime.integration.*;
import br.com.webinside.runtime.util.*;

/**
 * Exemplo de conector Java de pr� ou p�s p�gina.
 *
 * @author Luiz Ruiz
 * @version $Revision: 1.1 $
 */
public class PreComponentSample implements InterfaceConnector, 
	InterfaceParameters {
    /**
     * Construtor padr�o.
     */
    public PreComponentSample() {
    }

    /**
     * M�todo chamado pelo WI.
     *
     * @param wiParams par�metros do WI.
     * Implementa��o da interface InterfaceConnector.
     */
    public void execute(ExecuteParams wiParams) {
        WIMap wiMap = wiParams.getWIMap();
        PrintWriter out = wiParams.getWriter();
        DatabaseAliases databases = wiParams.getDatabaseAliases();

        // ler uma vari�vel do contexto do WI
        String nomeProjeto = wiMap.get("wi.proj.id");
        out.println(nomeProjeto);

        // gravar uma vari�vel no contexto do WI
        wiMap.put("text", this.getClass().getName());

        // escrer na sa�da do servlet
        out.println("Sou o conector original do WI");

        // pegar uma conex�o JDBC
        DatabaseHandler dbgen =
            databases.get("Nome do banco de dados definido no Builder");
        ConnectionSql dbsql = (ConnectionSql) dbgen.getDatabaseConnection();
        Connection con = dbsql.getConnection();
        out.println("Conex�o:" + con.toString());
    }

    public boolean exit() {
        return false;
    }

    /**
     * M�todo que retorna os par�metros de entrada a serem mostrados
     * na tela do WIBuilder. Implementa��o da interface InterfaceParameters.
     * 
     * @return array de par�metros
     */
    public JavaParameter[] getInputParameters() {
        JavaParameter[] params = new JavaParameter[3];
        params[0] = new JavaParameter("tmp.nome", "Nome", 
                "Nome do cliente a ser cadastrado.");
        params[1] = new JavaParameter("tmp.endereco", "Endere�o", 
        "Endere�o do cliente a ser cadastrado.");
        params[2] = new JavaParameter("tmp.fone", "Telefone", 
        "N�mero do telefone do cliente.");
        return params;
    }

    /**
     * M�todo que retorna os par�metros de entrada a serem mostrados
     * na tela do WIBuilder. Implementa��o da interface InterfaceParameters.
     * 
     * @return array de par�metros
     */
    public JavaParameter[] getOutputParameters() {
        String msg = "Exemplo de conector Java para ser usado em pr� ou p�s " +
        		"p�gina.<br>Esse conector, <code><b>PreComponentSample</b></code> " +
        		"mostra como gravar vari�veis, acessar conex�es com banco de " +
        		"dados, ou escrever na sa�da do servlet.";
        JavaParameter[] outParam = new JavaParameter[1];
        outParam[0] = new JavaParameter("&nbsp;", msg, ""); 
        return outParam;
    }
}
