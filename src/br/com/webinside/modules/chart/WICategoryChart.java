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

package br.com.webinside.modules.chart;

import java.awt.Font;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;

import br.com.webinside.runtime.util.Function;
import br.com.webinside.runtime.util.WIMap;

/**
 * Classe abstrata que serve como base para a constru��o das classes concretas
 * que ir�o oferecer a implementa��o espec�fica gr�ficos que utilizem o
 * dataset do tipo <code>CategoryDataser</code>.
 * 
 * <p>
 * A implementa��o do m�todo <code>createChart()</code> dever� instanciar a
 * propriedade <code>chart</code> com um objeto do tipo
 * <code>JFreeChart</code> correspondente ao tipo de gr�fico a ser montado.
 * </p>
 * 
 * <p>
 * O m�todo <code>setParameters(WIMap ctx)</code> dever� instanciar as
 * propriedades espec�ficas do gr�fico que est� sendo montado de acordo com as
 * vari�veis que est�o num contexto de um projeto do WI. �s
 * subclasses que sobrescreverem esse m�todo � recomendado que fa�am a chamada
 * ao m�todo da superclasse.
 * </p>
 *
 * @author Luiz Ricardo
 */
public abstract class WICategoryChart extends WIChart {
    /** Nome da vari�vel de sess�o do WI que informa o r�tulo do eixo X. */
    public static final String XLABEL_VAR = "tmp.chart.xlabel";
    /** Nome da vari�vel de sess�o do WI que informa o r�tulo do eixo Y. */
    public static final String YLABEL_VAR = "tmp.chart.ylabel";
    /**
     * Noma da vari�vel de sess�o do WI que informa como est�o dispostos os
     * valores (<code>line</code> ou <code>column</code>) dentro do objeto
     * (dataset). Se n�o for informado nenhum valor para essa vari�vel ou se
     * for informado um valor diferente de <code>column</code>, ser� assumido
     * que os dados est�o dispostos em linha.
     */
    public static final String CATEGORYDATASET_DISPOSITION_VAR =
        "tmp.chart.categoryDatasetDisposition";
    /**
     * Noma da vari�vel de sess�o do WI que informa qual a orienta��o
     * (<code>vertical</code> ou <code>horizontal</code>) que o plot ir�
     * utilizar para desenhar o gr�fico. Se n�o for informado nenhum valor
     * para essa vari�vel ou se for informado um valor diferente de
     * <code>horizontal</code>, ser� assumido que a orienta��o � vertical.
     */
    public static final String PLOT_ORIENTATION_VAR =
        "tmp.chart.plotOrientation";
    public static final String CATEGORY_LABEL_UP_45 =
        "tmp.chart.categoryLabelUp45";
    public static final String TICK_LABEL_SIZE =
        "tmp.chart.tickLabelSize";

    protected String xLabel;
    protected String yLabel;
    private String plotOrientationStr;
    private CategoryDataset categoryDataset;
    private boolean categoryLabelUp45;
    private int tickLabelSize;

    /**
     * Creates a new WICategoryChart object.
     */
    public WICategoryChart() {
        addInParameter(WICategoryChart.XLABEL_VAR, "R�tulo do eixo X", "");
        addInParameter(WICategoryChart.YLABEL_VAR, "R�tulo do eixo Y", "");
        addInParameter(WICategoryChart.CATEGORYDATASET_DISPOSITION_VAR,
            "Disposi��o dos dados das categorias no objeto",
            "line (default): os dados das s�ries de uma categoria est�o numa "
            + "�nica linha do objeto. column: os dados das s�ries das "
            + "categorias est�o nas colunas.");
        addInParameter(WICategoryChart.PLOT_ORIENTATION_VAR,
            "Orienta��o do plot",
            "Indica a orienta��o que ser� seguida para desenhar o gr�fico. "
            + "Os valores poss�veis s�o: vertical (default) ou horizontal.");
        addInParameter(BarChart.STACKED_VAR, "Empilhar barras",
        "Indica se as barras devem ser empilhadas, o valor default � false.");
        addInParameter(WICategoryChart.CATEGORY_LABEL_UP_45,
                "R�tulo das s�ries na diagonal",
        		"Indica se o r�tulo das s�ries deve ficar na diagonal, o valor default � false.");
        addInParameter(WICategoryChart.TICK_LABEL_SIZE, 
        		"Tamanho da fonte das s�ries", 
        		"Indica o tamanho da fonte a ser usado nas s�ries, o valor padr�o � o default do JFreeChart");
    }

    /**
     * DOCUMENT ME!
     */
    public abstract void createChart();

    /**
     * M�todo que configura os par�metros que s�o comuns aos gr�ficos que
     * utilizam o dataset do tipo <code>CategoryDataset</code>. Os par�metros
     * s�o: os r�tulos dos eixos "x" e "y", a orienta��o do plot e a
     * disposi��o dos dados dentro do objeto de fonte de dados.
     *
     * @param context sess�o do WI de onde ser�o recuperados os valores para
     *        configurar as propriedades do gr�fico.
     */
    public void setParameters(WIMap context) {
        plotOrientationStr = context.get(WICategoryChart.PLOT_ORIENTATION_VAR);

        if (context.get(WICategoryChart.CATEGORYDATASET_DISPOSITION_VAR)
                    .equalsIgnoreCase("column")) {
            setCategoryDataset(ChartUtil.getCategoryDatasetFromColumn(context,
                    objectId));
        } else {
            setCategoryDataset(ChartUtil.getCategoryDatasetFromLine(context,
                    objectId));
        }

        setXLabel(context.get(WICategoryChart.XLABEL_VAR));

        setYLabel(context.get(WICategoryChart.YLABEL_VAR));
        
        setCategoryLabelUp45(context.get(WICategoryChart.CATEGORY_LABEL_UP_45).equalsIgnoreCase("true"));
        
        setTickLabelSize(Function.parseInt(context.get(WICategoryChart.TICK_LABEL_SIZE)));
        
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public CategoryDataset getCategoryDataset() {
        return categoryDataset;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public PlotOrientation getPlotOrientation() {
        return plotOrientationStr.equalsIgnoreCase("horizontal")
        ? PlotOrientation.HORIZONTAL
        : PlotOrientation.VERTICAL;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getXLabel() {
        return xLabel;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getYLabel() {
        return yLabel;
    }

    /**
     * DOCUMENT ME!
     *
     * @param dataset DOCUMENT ME!
     */
    public void setCategoryDataset(CategoryDataset dataset) {
        categoryDataset = dataset;
    }

    /**
     * DOCUMENT ME!
     *
     * @param string DOCUMENT ME!
     */
    public void setXLabel(String string) {
        xLabel = string;
    }

    /**
     * DOCUMENT ME!
     *
     * @param string DOCUMENT ME!
     */
    public void setYLabel(String string) {
        yLabel = string;
    }

	public void setCategoryLabelUp45(boolean categoryLabelUp45) {
		this.categoryLabelUp45 = categoryLabelUp45;
	}

	public void setTickLabelSize(int tickLabelSize) {
		this.tickLabelSize = tickLabelSize;
	}
    
    protected void defineCategoryFont(JFreeChart chart) {
        CategoryAxis domAxis = chart.getCategoryPlot().getDomainAxis();
        if (categoryLabelUp45) {
        	domAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        }
        if (tickLabelSize > 0) {
        	Font font = new Font(Font.SANS_SERIF, Font.PLAIN, tickLabelSize);
        	domAxis.setTickLabelFont(font);
        }	
    }
    
}
