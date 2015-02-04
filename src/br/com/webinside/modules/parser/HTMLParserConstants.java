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

package br.com.webinside.modules.parser;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public interface HTMLParserConstants {
    /** DOCUMENT ME! */
    int EOF = 0;
    /** DOCUMENT ME! */
    int TagName = 1;
    /** DOCUMENT ME! */
    int DeclName = 2;
    /** DOCUMENT ME! */
    int Comment1 = 3;
    /** DOCUMENT ME! */
    int Comment2 = 4;
    /** DOCUMENT ME! */
    int Word = 5;
    /** DOCUMENT ME! */
    int LET = 6;
    /** DOCUMENT ME! */
    int NUM = 7;
    /** DOCUMENT ME! */
    int Entity = 8;
    /** DOCUMENT ME! */
    int Space = 9;
    /** DOCUMENT ME! */
    int SP = 10;
    /** DOCUMENT ME! */
    int Punct = 11;
    /** DOCUMENT ME! */
    int ArgName = 12;
    /** DOCUMENT ME! */
    int ArgEquals = 13;
    /** DOCUMENT ME! */
    int TagEnd = 14;
    /** DOCUMENT ME! */
    int ArgValue = 15;
    /** DOCUMENT ME! */
    int ArgQuote1 = 16;
    /** DOCUMENT ME! */
    int ArgQuote2 = 17;
    /** DOCUMENT ME! */
    int Quote1Text = 19;
    /** DOCUMENT ME! */
    int CloseQuote1 = 20;
    /** DOCUMENT ME! */
    int Quote2Text = 21;
    /** DOCUMENT ME! */
    int CloseQuote2 = 22;
    /** DOCUMENT ME! */
    int CommentText1 = 23;
    /** DOCUMENT ME! */
    int CommentEnd1 = 24;
    /** DOCUMENT ME! */
    int CommentText2 = 25;
    /** DOCUMENT ME! */
    int CommentEnd2 = 26;
    /** DOCUMENT ME! */
    int DEFAULT = 0;
    /** DOCUMENT ME! */
    int WithinTag = 1;
    /** DOCUMENT ME! */
    int AfterEquals = 2;
    /** DOCUMENT ME! */
    int WithinQuote1 = 3;
    /** DOCUMENT ME! */
    int WithinQuote2 = 4;
    /** DOCUMENT ME! */
    int WithinComment1 = 5;
    /** DOCUMENT ME! */
    int WithinComment2 = 6;
    /** DOCUMENT ME! */
    String[] tokenImage =
    {
        "<EOF>", "<TagName>", "<DeclName>", "\"<!--\"", "\"<!\"", "<Word>",
        "<LET>", "<NUM>", "<Entity>", "<Space>", "<SP>", "<Punct>", "<ArgName>",
        "\"=\"", "<TagEnd>", "<ArgValue>", "\"\\\'\"", "\"\\\"\"",
        "<token of kind 18>", "<Quote1Text>", "<CloseQuote1>", "<Quote2Text>",
        "<CloseQuote2>", "<CommentText1>", "\"-->\"", "<CommentText2>", "\">\"",
    };
}
