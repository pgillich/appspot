package com.appspot.pgillich.client;

import java.util.HashMap;
import java.util.Map;

import com.appspot.pgillich.client.example.graph.GwtRunner;
import com.appspot.pgillich.shared.HtmlResources;
import com.appspot.pgillich.shared.MotorResult;
import com.appspot.pgillich.shared.MotorStep;
import com.appspot.pgillich.shared.SharedConfig;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Node;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.client.ResourceException;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.visualization.client.ChartArea;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.DataView;
import com.google.gwt.visualization.client.LegendPosition;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.visualizations.corechart.AxisOptions;
import com.google.gwt.visualization.client.visualizations.corechart.CoreChart;
import com.google.gwt.visualization.client.visualizations.corechart.Options;
import com.google.gwt.visualization.client.visualizations.corechart.ScatterChart;
import com.google.gwt.visualization.client.visualizations.corechart.TextStyle;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * 
 * http://www.vogella.de/articles/GWT/article.html#server
 * 
 */
public class Pgillich implements EntryPoint {
	private static final String LINK_TO_THIS_PAGE = "Link to this page";
	private static final String LINK_TO_THIS_TITLE = "Copy this link";
	public static final String MOTOR_FORM_FIELD_PREFIX = "motor_";

	private final MotorServiceAsync motorService = GWT.create(MotorService.class);

	private Command cmdShowPeople;
	private Command cmdShowEngineering;
	private Command cmdShowNetworks;
	private Command cmdShowHardware;
	private Command cmdShowSoftware;
	private Command cmdShowAbout;

	private DockPanel topPanel;
	private DockPanel bodyPanel;
	private MenuBar menuBar;
	private MenuItem mntmPeople;
	private MenuItem mntmHardware;
	private MenuItem mntmSoftware;
	private MenuItem mntmEngineering;
	private MenuItem mntmAbout;
	private DeckPanel pagesDeckPanel;
	private DeckPanel treeDeckPanel;
	private Tree treePeople;
	private TreeItem trtmPeopleMyself;
	private TreeItem trtmPeopleFriends;
	private TreeItem trtmPeopleBlogs;
	private Tree treeEngineering;
	private TreeItem trtmEngineeringSimulation;
	private TreeItem trtmEngineeringModeling;
	private TreeItem trtmEngineeringSystems;
	private TreeItem trtmEngineeringMuseums;
	private TreeItem trtmEngineeringPaperwork;
	private MenuItem mntmNetworks;
	private TreeItem trtmPeopleCv;
	private TreeItem trtmPeopleBooks;
	private TreeItem trtmPeopleMovies;
	private HTML htmlPeopleMyself;
	private HTML htmlPeopleFriends;
	private HTML htmlPeopleBlogs;
	private HTML htmlPeopleCv;
	private HTML htmlEngineeringSimulation;
	private HTML htmlEngineeringModeling;
	private HTML htmlEngineeringSystems;
	private HTML htmlEngineeringMuseums;
	private HTML htmlEngineeringPaperwork;
	private HTML htmlPeopleBooks;
	private HTML htmlPeopleMovies;
	private HTML htmlAboutContact;
	private HTML htmlAboutCopyright;
	private HTML htmlAboutHistory;
	private HTML htmlAboutSource;
	private HTML htmlAboutTechnical;
	private HTML htmlAboutTraffic;
	private HTML htmlAboutWelcome;
	private HTML htmlEngineeringCatastrophes;
	private HTML htmlEngineeringEsp;
	private HTML htmlEngineeringFem;
	private HTML htmlEngineeringPhoto;
	private HTML htmlEngineeringSeries;
	private HTML htmlHardwareHome;
	private HTML htmlHardwareSale;
	private HTML htmlHardwareTools;
	private HTML htmlNetworksAutomotive;
	private HTML htmlNetworksCms;
	private HTML htmlNetworksCloud;
	private HTML htmlNetworksHosting;
	private HTML htmlNetworksSecurity;
	private HTML htmlSoftwareAutotest;
	private HTML htmlSoftwareCannyCad;
	private HTML htmlSoftwareFavorites;
	private HTML htmlSoftwareExamples;
	private Tree treeNetworks;
	private Tree treeHardware;
	private Tree treeSoftware;
	private Tree treeAbout;
	private TreeItem trtmAboutContact;
	private TreeItem trtmAboutCopyright;
	private TreeItem trtmAboutHistory;
	private TreeItem trtmAboutSource;
	private TreeItem trtmAboutTechnical;
	private TreeItem trtmAboutTraffic;
	private TreeItem trtmAboutWelcome;
	private TreeItem trtmSoftwareAutotest;
	private TreeItem trtmSoftwareCannyCad;
	private TreeItem trtmSoftwareFavorites;
	private TreeItem trtmSoftwareExamples;
	private TreeItem trtmSoftwareGraphTraversal;
	private TreeItem trtmHardwareHome;
	private TreeItem trtmHardwareSale;
	private TreeItem trtmHardwareTools;
	private TreeItem trtmNetworksSecurity;
	private TreeItem trtmNetworksAutomotive;
	private TreeItem trtmNetworksCms;
	private TreeItem trtmNetworksCloud;
	private TreeItem trtmNetworksHosting;
	private TreeItem trtmEngineeringPhoto;
	private TreeItem trtmEngineeringCatastrophes;
	private TreeItem trtmEngineeringSeries;
	private TreeItem trtmEngineeringDiesel;
	private TreeItem trtmEngineeringEsp;
	private TreeItem trtmEngineeringFem;
	private SimplePanel rightPaddingPanel;
	private Label lblRightPadding;
	private StackPanel stackPanelGraphTraversal;
	private FormPanel formPanelGraphTraversalMesh;
	private Grid gridGraphTraversal;
	private Label lblGraphTraversalNodes;
	private TextBox textGraphTraversalNodes;
	private Label lblEdges;
	private TextBox txtbxGraphTraversalEdges;
	private Button btnGraphTraversalStart;
	private VerticalPanel verticalPanelGraphTraversalResult;
	private HTML htmlGraphTraversalDescription;
	private HTML htmlPoweredByGae;
	private StackPanel stackPanelMotor;
	private HTML htmlMotorDescription;
	private FormPanel formPanelMotor;
	private Grid gridMotor;
	private Label lblMotorStart;
	private Button btnMotorStart;
	private VerticalPanel verticalPanelMotorResult;
	private ScrollPanel scrollPanelMotor;
	private HTML htmlCustomSearch;
	private HorizontalPanel horizontalPanelTopRight;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void showGui() {
		// ImageResources imageResources = GWT.create(ImageResources.class);
		HtmlResources htmlResources = GWT.create(HtmlResources.class);

		RootPanel rootPanel = RootPanel.get();
		rootPanel.setSize("100%", "100%");
		{
			cmdShowPeople = new Command() {
				@Override
				public void execute() {
					treeDeckPanel.showWidget(treeDeckPanel.getWidgetIndex(treePeople));

					if (treePeople.getSelectedItem() == null) {
						treePeople.setSelectedItem(trtmPeopleMyself, true);
					} else {
						treePeople.setSelectedItem(treePeople.getSelectedItem(), true);
					}
				}
			};

			cmdShowEngineering = new Command() {
				@Override
				public void execute() {
					treeDeckPanel.showWidget(treeDeckPanel.getWidgetIndex(treeEngineering));

					if (treeEngineering.getSelectedItem() == null) {
						treeEngineering.setSelectedItem(trtmEngineeringSystems, true);
					} else {
						treeEngineering.setSelectedItem(treeEngineering.getSelectedItem(), true);
					}
				}
			};

			cmdShowNetworks = new Command() {
				@Override
				public void execute() {
					treeDeckPanel.showWidget(treeDeckPanel.getWidgetIndex(treeNetworks));

					if (treeNetworks.getSelectedItem() == null) {
						treeNetworks.setSelectedItem(trtmNetworksSecurity, true);
					} else {
						treeNetworks.setSelectedItem(treeNetworks.getSelectedItem(), true);
					}
				}
			};

			cmdShowHardware = new Command() {
				@Override
				public void execute() {
					treeDeckPanel.showWidget(treeDeckPanel.getWidgetIndex(treeHardware));

					if (treeHardware.getSelectedItem() == null) {
						treeHardware.setSelectedItem(trtmHardwareHome, true);
					} else {
						treeHardware.setSelectedItem(treeHardware.getSelectedItem(), true);
					}
				}
			};

			cmdShowSoftware = new Command() {
				@Override
				public void execute() {
					treeDeckPanel.showWidget(treeDeckPanel.getWidgetIndex(treeSoftware));

					if (treeSoftware.getSelectedItem() == null) {
						treeSoftware.setSelectedItem(trtmSoftwareAutotest, true);
					} else {
						treeSoftware.setSelectedItem(treeSoftware.getSelectedItem(), true);
					}
				}
			};

			cmdShowAbout = new Command() {
				@Override
				public void execute() {
					treeDeckPanel.showWidget(treeDeckPanel.getWidgetIndex(treeAbout));

					if (treeAbout.getSelectedItem() == null) {
						treeAbout.setSelectedItem(trtmAboutContact, true);
					} else {
						treeAbout.setSelectedItem(treeAbout.getSelectedItem(), true);
					}
				}
			};

			bodyPanel = new DockPanel();
			rootPanel.add(bodyPanel);
			bodyPanel.setSize("100%", "100%");
			{
				topPanel = new DockPanel();
				bodyPanel.add(topPanel, DockPanel.NORTH);
				topPanel.setWidth("100%");
				{
					menuBar = new MenuBar(false);

					topPanel.add(menuBar, DockPanel.WEST);
					topPanel.setCellWidth(menuBar, "100%");
					menuBar.setWidth("");
					{
						mntmPeople = new MenuItem("People", false, cmdShowPeople);
						menuBar.addItem(mntmPeople);
					}
					{
						mntmEngineering = new MenuItem("Engineering", false, cmdShowEngineering);
						menuBar.addItem(mntmEngineering);
					}
					{
						mntmNetworks = new MenuItem("Networks", false, cmdShowNetworks);
						menuBar.addItem(mntmNetworks);
					}
					{
						mntmHardware = new MenuItem("Hardware", false, cmdShowHardware);
						menuBar.addItem(mntmHardware);
					}
					{
						mntmSoftware = new MenuItem("Software", false, cmdShowSoftware);
						menuBar.addItem(mntmSoftware);
					}
					{
						mntmAbout = new MenuItem("About", false, cmdShowAbout);
						menuBar.addItem(mntmAbout);
					}
				}
				{
					horizontalPanelTopRight = new HorizontalPanel();
					horizontalPanelTopRight.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
					topPanel.add(horizontalPanelTopRight, DockPanel.EAST);
					horizontalPanelTopRight.setWidth("300px");
					{
						/*
						 * htmlCustomSearch = new HTML(
						 * "<div id=\"cse-search-form\" style=\"width: 100px;\">Loading</div>"
						 * +
						 * "<script src=\"http://www.google.com/jsapi\" type=\"text/javascript\"></script>"
						 * + "<script type=\"text/javascript\"> " +
						 * "  alert('1'); google.load('search', '1', {language : 'en'});"
						 * +
						 * "  alert('2'); google.setOnLoadCallback(function() { alert('3');"
						 * +
						 * "    var customSearchControl = new google.search.CustomSearchControl('015667138580796937216:dqm6obogtjy');"
						 * +
						 * "    customSearchControl.setResultSetSize(google.search.Search.FILTERED_CSE_RESULTSET);"
						 * + "    var options = new google.search.DrawOptions();" +
						 * "    options.enableSearchboxOnly(\"http://www.google.com/cse?cx=015667138580796937216:dqm6obogtjy\");"
						 * +
						 * "    customSearchControl.draw('cse-search-form', options);"
						 * + "  }, true);" + "</script>" +
						 * "<link rel=\"stylesheet\" href=\"http://www.google.com/cse/style/look/default.css\" type=\"text/css\" />"
						 * , true);
						 */
						
						// http://www.google.com/cse/docs/cref.html
						htmlCustomSearch = new HTML(
								"<form id=\"cref\" action=\"http://www.google.com/cse\" style=\"margin-left: 10px; padding: 0px;\" class=\"gwt-TextBox\">"
										+ "  <input type=\"hidden\" name=\"cref\" value=\"http://pgillich.appspot.com/cse_context.xml\" />"
										+ "  <input type=\"text\" name=\"q\" size=\"20\" />"
/*										+ "  <input type=\"submit\" name=\"sa\" value=\"Search\" />"
*/										+ "</form>"
/*									+ "<script type=\"text/javascript\" src=\"http://www.google.com/cse/brand?form=cref\"></script>"
*/							, true);
						htmlCustomSearch.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
						horizontalPanelTopRight.add(htmlCustomSearch);
					}
					{
						htmlPoweredByGae = new HTML(
								"<a href=\"http://code.google.com/appengine/\"><img src=\"http://code.google.com/appengine/images/appengine-silver-120x30.gif\" \r\nalt=\"Powered by Google App Engine\" /></a>",
								true);
						horizontalPanelTopRight.add(htmlPoweredByGae);
						htmlPoweredByGae.setStyleName("buttonAppenginePowered");
					}
				}
			}
			{
				pagesDeckPanel = new DeckPanel();
				pagesDeckPanel.setStyleName("pagesDeckPanel");
				bodyPanel.add(pagesDeckPanel, DockPanel.CENTER);
				pagesDeckPanel.setSize("100%", "100%");
				{
					htmlAboutContact = new HTML();
					pagesDeckPanel.add(htmlAboutContact);
				}
				{
					htmlAboutCopyright = new HTML();
					pagesDeckPanel.add(htmlAboutCopyright);
				}
				{
					htmlAboutHistory = new HTML();
					pagesDeckPanel.add(htmlAboutHistory);
				}
				{
					htmlAboutSource = new HTML();
					pagesDeckPanel.add(htmlAboutSource);
				}
				{
					htmlAboutTechnical = new HTML();
					pagesDeckPanel.add(htmlAboutTechnical);
				}
				{
					htmlAboutTraffic = new HTML();
					pagesDeckPanel.add(htmlAboutTraffic);
				}
				{
					htmlAboutWelcome = new HTML();
					pagesDeckPanel.add(htmlAboutWelcome);
				}
				{
					htmlEngineeringCatastrophes = new HTML();
					pagesDeckPanel.add(htmlEngineeringCatastrophes);
				}
				{
					htmlEngineeringEsp = new HTML();
					pagesDeckPanel.add(htmlEngineeringEsp);
				}
				{
					htmlEngineeringFem = new HTML();
					pagesDeckPanel.add(htmlEngineeringFem);
				}
				{
					htmlEngineeringModeling = new HTML();
					pagesDeckPanel.add(htmlEngineeringModeling);
				}
				{
					htmlEngineeringPaperwork = new HTML();
					pagesDeckPanel.add(htmlEngineeringPaperwork);
				}
				{
					htmlEngineeringPhoto = new HTML();
					pagesDeckPanel.add(htmlEngineeringPhoto);
				}
				{
					htmlEngineeringSeries = new HTML();
					pagesDeckPanel.add(htmlEngineeringSeries);
				}
				{
					htmlEngineeringSimulation = new HTML();
					pagesDeckPanel.add(htmlEngineeringSimulation);
				}
				{
					htmlEngineeringSystems = new HTML();
					pagesDeckPanel.add(htmlEngineeringSystems);
				}
				{
					htmlEngineeringMuseums = new HTML();
					pagesDeckPanel.add(htmlEngineeringMuseums);
				}
				{
					htmlHardwareHome = new HTML();
					pagesDeckPanel.add(htmlHardwareHome);
				}
				{
					htmlHardwareSale = new HTML();
					pagesDeckPanel.add(htmlHardwareSale);
				}
				{
					htmlHardwareTools = new HTML();
					pagesDeckPanel.add(htmlHardwareTools);
				}
				{
					htmlNetworksAutomotive = new HTML();
					pagesDeckPanel.add(htmlNetworksAutomotive);
				}
				{
					htmlNetworksCms = new HTML();
					pagesDeckPanel.add(htmlNetworksCms);
				}
				{
					htmlNetworksCloud = new HTML();
					pagesDeckPanel.add(htmlNetworksCloud);
				}
				{
					htmlNetworksHosting = new HTML();
					pagesDeckPanel.add(htmlNetworksHosting);
				}
				{
					htmlNetworksSecurity = new HTML();
					pagesDeckPanel.add(htmlNetworksSecurity);
				}
				{
					htmlPeopleBlogs = new HTML();
					pagesDeckPanel.add(htmlPeopleBlogs);
				}
				{
					htmlPeopleBooks = new HTML();
					pagesDeckPanel.add(htmlPeopleBooks);
				}
				{
					htmlPeopleMovies = new HTML();
					pagesDeckPanel.add(htmlPeopleMovies);
				}
				{
					htmlPeopleCv = new HTML();
					pagesDeckPanel.add(htmlPeopleCv);
				}
				{
					htmlPeopleFriends = new HTML();
					pagesDeckPanel.add(htmlPeopleFriends);
				}
				{
					htmlPeopleMyself = new HTML();
					pagesDeckPanel.add(htmlPeopleMyself);
				}
				{
					htmlSoftwareAutotest = new HTML();
					pagesDeckPanel.add(htmlSoftwareAutotest);
				}
				{
					htmlSoftwareCannyCad = new HTML();
					pagesDeckPanel.add(htmlSoftwareCannyCad);
				}
				{
					htmlSoftwareFavorites = new HTML();
					pagesDeckPanel.add(htmlSoftwareFavorites);
				}
				{
					htmlSoftwareExamples = new HTML();
					pagesDeckPanel.add(htmlSoftwareExamples);
				}
				{
					stackPanelGraphTraversal = new StackPanel();
					pagesDeckPanel.add(stackPanelGraphTraversal);
					{
						htmlGraphTraversalDescription = new HTML(
								"<p>"
										+ "It's a simple example for a graph traversal. Shows the first founded path. "
										+ "The algorithm is optimized for mesh and line topology. Source code can be found on <a href=\"https://github.com/pgillich/appspot/tree/master/pgillich/src/com/appspot/pgillich/client/example/graph\">GitHub</a>."
										+ "</p>"
										+ "<p>"
										+ "Usage: Select the 'Mesh Topology' scatter and change the predefined parameters, if you want. "
										+ "Click on the Start button." + "</p>"

								, true);
						stackPanelGraphTraversal.add(htmlGraphTraversalDescription, "Description",
								false);
						htmlGraphTraversalDescription.setSize("100%", "100%");
					}
					{
						formPanelGraphTraversalMesh = new FormPanel();
						stackPanelGraphTraversal.add(formPanelGraphTraversalMesh, "Mesh Topology",
								false);
						formPanelGraphTraversalMesh.setSize("100%", "100%");
						{
							gridGraphTraversal = new Grid(3, 2);
							formPanelGraphTraversalMesh.setWidget(gridGraphTraversal);
							gridGraphTraversal.setSize("100%", "100%");
							{
								lblGraphTraversalNodes = new Label(
										"Nodes name1:x1,y1 name2:x2,y2 ...");
								gridGraphTraversal.setWidget(0, 0, lblGraphTraversalNodes);
							}
							{
								textGraphTraversalNodes = new TextBox();
								textGraphTraversalNodes.setName("nodes");
								textGraphTraversalNodes
										.setText("A:0,0 B:10,0 C:10,10 D:0,10 E:20,10 F:30,0 G:30,20 H:20,20 I:0,20");
								gridGraphTraversal.setWidget(0, 1, textGraphTraversalNodes);
								textGraphTraversalNodes.setWidth("100%");
							}
							{
								lblEdges = new Label("Edges node1-node2 node2-node3 ...");
								gridGraphTraversal.setWidget(1, 0, lblEdges);
							}
							{
								txtbxGraphTraversalEdges = new TextBox();
								txtbxGraphTraversalEdges.setName("edges");
								txtbxGraphTraversalEdges
										.setText("A-B B-C C-E E-F E-G E-H H-I I-D D-A D-C");
								gridGraphTraversal.setWidget(1, 1, txtbxGraphTraversalEdges);
								txtbxGraphTraversalEdges.setWidth("100%");
							}
							{
								btnGraphTraversalStart = new Button("Start");
								btnGraphTraversalStart.addClickHandler(new ClickHandler() {
									public void onClick(ClickEvent event) {
										do_btnGraphTraversalStart_onClick(event);
									}
								});
								gridGraphTraversal.setWidget(2, 1, btnGraphTraversalStart);
							}
						}
						{
							verticalPanelGraphTraversalResult = new VerticalPanel();
							stackPanelGraphTraversal.add(verticalPanelGraphTraversalResult,
									"Result", false);
							verticalPanelGraphTraversalResult.setSize("100%", "100%");
						}
					}
				}
				{
					stackPanelMotor = new StackPanel();
					pagesDeckPanel.add(stackPanelMotor);
					{
						htmlMotorDescription = new HTML(
								"<p>I've written a diesel engine simulation in middle of '90s. It was written in Borland C. <br/>" +
								"This demo is a Java porting of the C code and it runs on Google App Engine.<br/>" +
								"The source code can be found on GitHub, but please be patient -  it was one of my first programs with a lot of stylistic mistakes.</p>",
								true);
						stackPanelMotor.add(htmlMotorDescription, "Description", false);
						htmlMotorDescription.setSize("100%", "");
					}
					{
						verticalPanelMotorResult = new VerticalPanel();
						verticalPanelMotorResult.setSize("100%", "100%");
					}
					{
						formPanelMotor = new FormPanel();
						formPanelMotor.setMethod(FormPanel.METHOD_POST);
						stackPanelMotor.add(formPanelMotor, "Parameters", false);
						formPanelMotor.setSize("100%", "100%");
						{
							scrollPanelMotor = new ScrollPanel();
							formPanelMotor.setWidget(scrollPanelMotor);
							scrollPanelMotor.setSize("100%", "400px");
							{
								gridMotor = new Grid(1, 2);
								scrollPanelMotor.setWidget(gridMotor);
								gridMotor.setSize("100%", "100%");
								{
									lblMotorStart = new Label("Click on Start button!");
									gridMotor.setWidget(0, 0, lblMotorStart);
								}
								{
									btnMotorStart = new Button("Start");
									btnMotorStart.addClickHandler(new ClickHandler() {
										public void onClick(ClickEvent event) {
											do_btnMotorStart_onClick(event);
										}
									});
									gridMotor.setWidget(0, 1, btnMotorStart);
								}

								buildMotorForm(gridMotor);
							}
						}
					}
					stackPanelMotor.add(verticalPanelMotorResult, "Result", false);
				}
			}
			{
				treeDeckPanel = new DeckPanel();
				treeDeckPanel.setStyleName("treeDeckPanel");
				bodyPanel.add(treeDeckPanel, DockPanel.WEST);
				bodyPanel.setCellWidth(treeDeckPanel, "50px");
				{
					treePeople = new Tree();
					treePeople.setTitle("People");
					treeDeckPanel.add(treePeople);
					{
						trtmPeopleMyself = new TreeItem("Myself");
						treePeople.addItem(trtmPeopleMyself);
						registerTreeSelectedHtml(trtmPeopleMyself, htmlPeopleMyself,
								htmlResources.htmlPeopleMyself(), pagesDeckPanel);
						{
							trtmPeopleCv = new TreeItem("CV");
							trtmPeopleMyself.addItem(trtmPeopleCv);
							registerTreeSelectedHtml(trtmPeopleCv, htmlPeopleCv,
									htmlResources.htmlPeopleCv(), pagesDeckPanel);
						}
						trtmPeopleMyself.setState(true);
					}
					{
						trtmPeopleFriends = new TreeItem("Friends");
						treePeople.addItem(trtmPeopleFriends);
						registerTreeSelectedHtml(trtmPeopleFriends, htmlPeopleFriends,
								htmlResources.htmlPeopleFriends(), pagesDeckPanel);
					}
					{
						trtmPeopleBlogs = new TreeItem("Blogs");
						treePeople.addItem(trtmPeopleBlogs);
						registerTreeSelectedHtml(trtmPeopleBlogs, htmlPeopleBlogs,
								htmlResources.htmlPeopleBlogs(), pagesDeckPanel);
					}
					{
						trtmPeopleBooks = new TreeItem("Books");
						treePeople.addItem(trtmPeopleBooks);
						registerTreeSelectedHtml(trtmPeopleBooks, htmlPeopleBooks,
								htmlResources.htmlPeopleBooks(), pagesDeckPanel);
					}
					{
						trtmPeopleMovies = new TreeItem("Movies");
						treePeople.addItem(trtmPeopleMovies);
						registerTreeSelectedHtml(trtmPeopleMovies, htmlPeopleMovies,
								htmlResources.htmlPeopleMovies(), pagesDeckPanel);
					}
				}
				{
					treeEngineering = new Tree();
					treeEngineering.setTitle("Engineering");
					treeDeckPanel.add(treeEngineering);
					{
						trtmEngineeringSystems = new TreeItem("Systems Engineering");
						treeEngineering.addItem(trtmEngineeringSystems);
						registerTreeSelectedHtml(trtmEngineeringSystems, htmlEngineeringSystems,
								htmlResources.htmlEngineeringSystems(), pagesDeckPanel);
					}
					{
						trtmEngineeringModeling = new TreeItem("Modeling");
						treeEngineering.addItem(trtmEngineeringModeling);
						registerTreeSelectedHtml(trtmEngineeringModeling, htmlEngineeringModeling,
								htmlResources.htmlEngineeringModeling(), pagesDeckPanel);
						trtmEngineeringModeling.setState(true);
					}
					{
						trtmEngineeringSimulation = new TreeItem("Simulation");
						treeEngineering.addItem(trtmEngineeringSimulation);
						registerTreeSelectedHtml(trtmEngineeringSimulation,
								htmlEngineeringSimulation,
								htmlResources.htmlEngineeringSimulation(), pagesDeckPanel);
						{
							trtmEngineeringDiesel = new TreeItem("Diesel Engine");
							trtmEngineeringSimulation.addItem(trtmEngineeringDiesel);
							String linkToThis = generateLinkToThis(trtmEngineeringDiesel);
							htmlMotorDescription.setHTML(linkToThis
									+ htmlMotorDescription.getHTML());
							registerDockSelection(trtmEngineeringDiesel, stackPanelMotor,
									pagesDeckPanel);
						}
						{
							trtmEngineeringEsp = new TreeItem("ESP");
							trtmEngineeringSimulation.addItem(trtmEngineeringEsp);
							registerTreeSelectedHtml(trtmEngineeringEsp, htmlEngineeringEsp,
									htmlResources.htmlEngineeringEsp(), pagesDeckPanel);
						}
						{
							trtmEngineeringFem = new TreeItem("FEM");
							trtmEngineeringSimulation.addItem(trtmEngineeringFem);
							registerTreeSelectedHtml(trtmEngineeringFem, htmlEngineeringFem,
									htmlResources.htmlEngineeringFem(), pagesDeckPanel);
						}
						trtmEngineeringSimulation.setState(true);
					}
					{
						trtmEngineeringMuseums = new TreeItem("Museums");
						treeEngineering.addItem(trtmEngineeringMuseums);
						registerTreeSelectedHtml(trtmEngineeringMuseums, htmlEngineeringMuseums,
								htmlResources.htmlEngineeringMuseums(), pagesDeckPanel);
					}
					{
						trtmEngineeringPaperwork = new TreeItem("Paperwork");
						treeEngineering.addItem(trtmEngineeringPaperwork);
						registerTreeSelectedHtml(trtmEngineeringPaperwork,
								htmlEngineeringPaperwork, htmlResources.htmlEngineeringPaperwork(),
								pagesDeckPanel);
					}
					{
						trtmEngineeringPhoto = new TreeItem("Photography");
						treeEngineering.addItem(trtmEngineeringPhoto);
						registerTreeSelectedHtml(trtmEngineeringPhoto, htmlEngineeringPhoto,
								htmlResources.htmlEngineeringPhoto(), pagesDeckPanel);
					}
					{
						trtmEngineeringSeries = new TreeItem("TV Series");
						treeEngineering.addItem(trtmEngineeringSeries);
						registerTreeSelectedHtml(trtmEngineeringSeries, htmlEngineeringSeries,
								htmlResources.htmlEngineeringSeries(), pagesDeckPanel);
					}
					{
						trtmEngineeringCatastrophes = new TreeItem("Catastrophes");
						trtmEngineeringCatastrophes.setText("Disasters");
						treeEngineering.addItem(trtmEngineeringCatastrophes);
						registerTreeSelectedHtml(trtmEngineeringCatastrophes,
								htmlEngineeringCatastrophes,
								htmlResources.htmlEngineeringCatastrophes(), pagesDeckPanel);
					}
				}
				{
					treeNetworks = new Tree();
					treeDeckPanel.add(treeNetworks);
					treeNetworks.setTitle("Networks");
					{
						trtmNetworksSecurity = new TreeItem("Security");
						treeNetworks.addItem(trtmNetworksSecurity);
						registerTreeSelectedHtml(trtmNetworksSecurity, htmlNetworksSecurity,
								htmlResources.htmlNetworksSecurity(), pagesDeckPanel);
					}
					{
						trtmNetworksAutomotive = new TreeItem("Automotive");
						treeNetworks.addItem(trtmNetworksAutomotive);
						registerTreeSelectedHtml(trtmNetworksAutomotive, htmlNetworksAutomotive,
								htmlResources.htmlNetworksAutomotive(), pagesDeckPanel);
					}
					{
						trtmNetworksCms = new TreeItem("CMS");
						treeNetworks.addItem(trtmNetworksCms);
						registerTreeSelectedHtml(trtmNetworksCms, htmlNetworksCms,
								htmlResources.htmlNetworksCms(), pagesDeckPanel);
					}
					{
						trtmNetworksCloud = new TreeItem("Cloud Computing");
						treeNetworks.addItem(trtmNetworksCloud);
						registerTreeSelectedHtml(trtmNetworksCloud, htmlNetworksCloud,
								htmlResources.htmlNetworksCloud(), pagesDeckPanel);
					}
					{
						trtmNetworksHosting = new TreeItem("Server Hosting");
						treeNetworks.addItem(trtmNetworksHosting);
						registerTreeSelectedHtml(trtmNetworksHosting, htmlNetworksHosting,
								htmlResources.htmlNetworksHosting(), pagesDeckPanel);
					}
				}
				{
					treeHardware = new Tree();
					treeDeckPanel.add(treeHardware);
					treeHardware.setTitle("Hardware");
					{
						trtmHardwareHome = new TreeItem("Home");
						treeHardware.addItem(trtmHardwareHome);
						registerTreeSelectedHtml(trtmHardwareHome, htmlHardwareHome,
								htmlResources.htmlHardwareHome(), pagesDeckPanel);
					}
					{
						trtmHardwareTools = new TreeItem("Tools");
						treeHardware.addItem(trtmHardwareTools);
						registerTreeSelectedHtml(trtmHardwareTools, htmlHardwareTools,
								htmlResources.htmlHardwareTools(), pagesDeckPanel);
						{
							trtmHardwareSale = new TreeItem("Sale");
							treeHardware.addItem(trtmHardwareSale);
							registerTreeSelectedHtml(trtmHardwareSale, htmlHardwareSale,
									htmlResources.htmlHardwareSale(), pagesDeckPanel);
						}
					}
				}
				{
					treeSoftware = new Tree();
					treeDeckPanel.add(treeSoftware);
					treeSoftware.setTitle("Software");
					{
						trtmSoftwareAutotest = new TreeItem("Test Automation");
						treeSoftware.addItem(trtmSoftwareAutotest);
						registerTreeSelectedHtml(trtmSoftwareAutotest, htmlSoftwareAutotest,
								htmlResources.htmlSoftwareAutotest(), pagesDeckPanel);
					}
					{
						trtmSoftwareCannyCad = new TreeItem("CannyCAD");
						treeSoftware.addItem(trtmSoftwareCannyCad);
						registerTreeSelectedHtml(trtmSoftwareCannyCad, htmlSoftwareCannyCad,
								htmlResources.htmlSoftwareCannyCad(), pagesDeckPanel);
					}
					{
						trtmSoftwareFavorites = new TreeItem("Favorites");
						treeSoftware.addItem(trtmSoftwareFavorites);
						registerTreeSelectedHtml(trtmSoftwareFavorites, htmlSoftwareFavorites,
								htmlResources.htmlSoftwareFavorites(), pagesDeckPanel);
					}
					{
						trtmSoftwareExamples = new TreeItem("Examples");
						treeSoftware.addItem(trtmSoftwareExamples);
						registerTreeSelectedHtml(trtmSoftwareExamples, htmlSoftwareExamples,
								htmlResources.htmlSoftwareExamples(), pagesDeckPanel);
						{
							trtmSoftwareGraphTraversal = new TreeItem("Graph Traversal");
							trtmSoftwareExamples.addItem(trtmSoftwareGraphTraversal);
							String linkToThis = generateLinkToThis(trtmSoftwareGraphTraversal);
							htmlGraphTraversalDescription.setHTML(linkToThis
									+ htmlGraphTraversalDescription.getHTML());
							registerDockSelection(trtmSoftwareGraphTraversal,
									stackPanelGraphTraversal, pagesDeckPanel);

						}
						trtmSoftwareExamples.setState(true);
					}
				}
				{
					treeAbout = new Tree();
					treeDeckPanel.add(treeAbout);
					treeAbout.setTitle("About");
					{
						trtmAboutWelcome = new TreeItem("Welcome");
						treeAbout.addItem(trtmAboutWelcome);
						registerTreeSelectedHtml(trtmAboutWelcome, htmlAboutWelcome,
								htmlResources.htmlAboutWelcome(), pagesDeckPanel);
					}
					{
						trtmAboutContact = new TreeItem("Contact");
						treeAbout.addItem(trtmAboutContact);
						registerTreeSelectedHtml(trtmAboutContact, htmlAboutContact,
								htmlResources.htmlAboutContact(), pagesDeckPanel);
					}
					{
						trtmAboutCopyright = new TreeItem("Copyright");
						treeAbout.addItem(trtmAboutCopyright);
						registerTreeSelectedHtml(trtmAboutCopyright, htmlAboutCopyright,
								htmlResources.htmlAboutCopyright(), pagesDeckPanel);
					}
					{
						trtmAboutHistory = new TreeItem("History");
						treeAbout.addItem(trtmAboutHistory);
						registerTreeSelectedHtml(trtmAboutHistory, htmlAboutHistory,
								htmlResources.htmlAboutHistory(), pagesDeckPanel);
					}
					{
						trtmAboutSource = new TreeItem("Source Code");
						treeAbout.addItem(trtmAboutSource);
						registerTreeSelectedHtml(trtmAboutSource, htmlAboutSource,
								htmlResources.htmlAboutSource(), pagesDeckPanel);
					}
					{
						trtmAboutTechnical = new TreeItem("Technical Details");
						treeAbout.addItem(trtmAboutTechnical);
						registerTreeSelectedHtml(trtmAboutTechnical, htmlAboutTechnical,
								htmlResources.htmlAboutTechnical(), pagesDeckPanel);
					}
					{
						trtmAboutTraffic = new TreeItem("Traffic Analysis");
						treeAbout.addItem(trtmAboutTraffic);
						registerTreeSelectedHtml(trtmAboutTraffic, htmlAboutTraffic,
								htmlResources.htmlAboutTraffic(), pagesDeckPanel);
					}
				}
			}
			{
				rightPaddingPanel = new SimplePanel();
				bodyPanel.add(rightPaddingPanel, DockPanel.EAST);
				bodyPanel.setCellWidth(rightPaddingPanel, "65px");
				bodyPanel.setCellHorizontalAlignment(rightPaddingPanel,
						HasHorizontalAlignment.ALIGN_CENTER);
				rightPaddingPanel.setSize("65px", "100px");
				{
					lblRightPadding = new Label("");
					rightPaddingPanel.setWidget(lblRightPadding);
					lblRightPadding.setSize("65px", "100px");
				}
			}
		}

		selectFirst();
		initCustomSearch(htmlCustomSearch.getElement().getChild(0));
	}

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		Runnable onLoadCallback = new Runnable() {
			public void run() {
				showGui();
			}
		};

		VisualizationUtils.loadVisualizationApi(onLoadCallback, ScatterChart.PACKAGE);
	}

	private void buildMotorForm(Grid gridMotor) {
		addParameterToMotorForm(gridMotor, "", "Környezeti paraméterek", "");
		addParameterToMotorForm(gridMotor, "p0", "p0 (Környezeti nyomás)  [bar]", "1.014");
		addParameterToMotorForm(gridMotor, "t0", "t0 (Környezeti hőmérséklet)  [°C]", "28");
		addParameterToMotorForm(gridMotor, "n", "n (Motorfordulatszám)  [1/min]", "1028");
		addParameterToMotorForm(gridMotor, "legfel", "legfel (Légfeleslegtényező) ", "1.05");
		addParameterToMotorForm(gridMotor, "pk[GYURUHORONY]",
				"pk[GYURUHORONY] (a kartertér nyomása)  [bar]", "1.1");
		addParameterToMotorForm(gridMotor, "szv[GYURUHORONY]",
				"szv[GYURUHORONY] (a kartergáz fajtérfogata)  [m3/kg]", "0.855");
		addParameterToMotorForm(gridMotor, "m_adag", "m_adag (ciklusadag)  [mg]", "38");
		addParameterToMotorForm(gridMotor, "be_kar", "be_kar (bef. karakterisztika)  [mg/ft°]",
				"1.5");
		addParameterToMotorForm(gridMotor, "Egesho", "Egesho (a tüzelőanyag égéshője)  [kJ]", "41");
		addParameterToMotorForm(gridMotor, "p_piezo",
				"p_piezo (a nyomásjeladó eltérése nyomása)  [bar]", "-0.2");
		addParameterToMotorForm(gridMotor, "te0", "te0 (az égéstér gázainak hőmérséklete)  [°C]",
				"100");
		addParameterToMotorForm(gridMotor, "", "Levegő hőmérséklete", "");
		addParameterToMotorForm(gridMotor, "tsz", "tsz (szívószelepnél)  [°C]", "35");
		addParameterToMotorForm(gridMotor, "", "A ki nem öblített gáz összetétele", "");
		addParameterToMotorForm(gridMotor, "N//2", "N//2   [%]", "75.65");
		addParameterToMotorForm(gridMotor, "O//2", "O//2   [%]", "20.29");
		addParameterToMotorForm(gridMotor, "Ar", "Ar  [%]", "0.9");
		addParameterToMotorForm(gridMotor, "CO//2", "CO//2  [%]", "0.03");
		addParameterToMotorForm(gridMotor, "H//2O", "H//2O  [%]", "3.13");
		addParameterToMotorForm(gridMotor, "", "Geometriai adatok", "");
		addParameterToMotorForm(gridMotor, "dfok", "dfok (Egységnyi elfordulás)  [ft°]", "0.5");
		addParameterToMotorForm(gridMotor, "hiba", "hiba (Iterációs határ)  [%]", "1");
		addParameterToMotorForm(gridMotor, "outint", "outint (Output intervallum) ", "2");
		addParameterToMotorForm(gridMotor, "Vc", "Vc (Kompressziótérfogat)  [m3]", "0.000054");
		addParameterToMotorForm(gridMotor, "r", "r (Forgattyúsugár)  [m]", "0.055");
		addParameterToMotorForm(gridMotor, "l", "l (Forgattyúkar)  [m]", "0.21");
		addParameterToMotorForm(gridMotor, "d", "d (Hengerátmérö)  [m]", "0.1");
		addParameterToMotorForm(gridMotor, "",
				"Vezérlés, a FHP-hoz viszonyitva, fötengely-fokban mérve", "");
		addParameterToMotorForm(gridMotor, "be_szog", "be_szog (előbefecskendezési szög)  [ft°]",
				"20");
		addParameterToMotorForm(gridMotor, "os", "os (Szivószelep nyit)  [ft°]", "705");
		addParameterToMotorForm(gridMotor, "cs", "cs (Szivószelep zár)  [ft°]", "220");
		addParameterToMotorForm(gridMotor, "ok", "ok (Kipufogószelep nyit)  [ft°]", "500");
		addParameterToMotorForm(gridMotor, "ck", "ck (Kipufogószelep zár)  [ft°]", "15");
		addParameterToMotorForm(gridMotor, "dsz", "dsz (Szivószelep átméröje)  [m]", "0.035");
		addParameterToMotorForm(gridMotor, "hsz", "hsz (Szivószelep nyitása)  [m]", "0.015");
		addParameterToMotorForm(gridMotor, "dk", "dk (Kipufogószelep átméröje)  [m]", "0.030");
		addParameterToMotorForm(gridMotor, "hk", "hk (Kipufogószelep nyitása)  [m]", "0.015");
		addParameterToMotorForm(gridMotor, "szM", "szM (A nyitási karakterisztika tényezője) ",
				"0.4");
		addParameterToMotorForm(gridMotor, "szA", "szA (A szívószelep alaktényezője) ", "0.3");
		addParameterToMotorForm(gridMotor, "kA", "kA (A kipufogószelep alaktényezője) ", "0.35");
		addParameterToMotorForm(gridMotor, "szcs", "szcs (A Szívószelep csilapítása) ", "0.3");
		addParameterToMotorForm(gridMotor, "kcs", "kcs (A Kipufogószelep csilapítása) ", "0.4");
		addParameterToMotorForm(gridMotor, "xi", "xi (A gyűrűhornyok keresztmetszete)  [m2]",
				"0.000004");
		addParameterToMotorForm(gridMotor, "", "Hőveszteség jellemzők", "");
		addParameterToMotorForm(gridMotor, "lamheng",
				"lamheng (A hengerfal hövezetési tényezöje)  [W/mK]", "40");
		addParameterToMotorForm(gridMotor, "lamhfej",
				"lamhfej (A hengerfej hövezetési tényezöje)  [W/mK]", "40");
		addParameterToMotorForm(gridMotor, "lamdug",
				"lamdug (A dugattyú hövezetési tényezöje)  [W/mK]", "40");
		addParameterToMotorForm(gridMotor, "alviz",
				"alviz (A hütöközegoldali höszállitási tényezö)  [W/m2K]", "800");
		addParameterToMotorForm(gridMotor, "allev",
				"allev (A munkaközegoldali höszállitási tényezö)  [W/m2K]", "800");
		addParameterToMotorForm(gridMotor, "dfalheng",
				"dfalheng (A hengerfal átlagos falvastagsága)  [m]", "0.01");
		addParameterToMotorForm(gridMotor, "dfalhfej",
				"dfalhfej (A hengerfej átlagos falvastagsága)  [m]", "0.01");
		addParameterToMotorForm(gridMotor, "dfaldug",
				"dfaldug (A dugattyútetö átlagos falvastagsága)  [m]", "0.01");
		addParameterToMotorForm(gridMotor, "Thk", "Thk ( A hűtőközeg hőmérséklete )  [°C]", "40");
		addParameterToMotorForm(gridMotor, "", "Az Ablakok", "");
		addParameterToMotorForm(gridMotor, "Nagyság", "Nagyság ( 0: Normál; 1: Nagyított ) ", "0");
		addParameterToMotorForm(gridMotor, "Hőfelszabadulások",
				"Hőfelszabadulások ( 0: KI; 1: BE ) ", "1");
		addParameterToMotorForm(gridMotor, "", "Modulok kiválasztása ( 0: KI ; 1: BE )", "");
		addParameterToMotorForm(gridMotor, "Mod_additiv",
				"Mod_additiv ( 0: Egymás utáni; 1: additív számítás ) ", "1");
		addParameterToMotorForm(gridMotor, "Mod_ho", "Mod_ho ( Hőveszteség számítása ) ", "1");
		addParameterToMotorForm(gridMotor, "Mod_karter",
				"Mod_karter ( az elszökő kartegáz figyelembevétele ) ", "1");
		addParameterToMotorForm(gridMotor, "Mod_eges", "Mod_eges ( égés számítása ) ", "1");
		addParameterToMotorForm(gridMotor, "GyulasModel", "GyulasModel ( gyulladási késedelem ) ",
				"4");
		addParameterToMotorForm(gridMotor, "EgesModell", "EgesModell ( égésmodell ) ", "1");
		addParameterToMotorForm(gridMotor, "Meres", "Meres ( hőfelszabadulás mérése ) ", "0");
		addParameterToMotorForm(gridMotor, "", "A gyulladási késedelem modellek konstansai:", "");
		addParameterToMotorForm(gridMotor, "GYULLAD_SZOG", "GYULLAD_SZOG  [ft°]", "355");
		addParameterToMotorForm(gridMotor, "WOLFER_E0", "WOLFER_E0 ", "38542");
		addParameterToMotorForm(gridMotor, "WOLFER_A1", "WOLFER_A1 ", "3.34299");
		addParameterToMotorForm(gridMotor, "WOLFER_n", "WOLFER_n ", "1.19");
		addParameterToMotorForm(gridMotor, "SCHMIDT_c", "SCHMIDT_c ", "0.03");
		addParameterToMotorForm(gridMotor, "SCHMIDT_n", "SCHMIDT_n ", "1.19");
		addParameterToMotorForm(gridMotor, "SCHMIDT_E0", "SCHMIDT_E0 ", "38542");
		addParameterToMotorForm(gridMotor, "BAERT_t0", "BAERT_t0 ", "0.86");
		addParameterToMotorForm(gridMotor, "BAERT_A", "BAERT_A ", "4.13");
		addParameterToMotorForm(gridMotor, "BAERT_B", "BAERT_B ", "10557");
		addParameterToMotorForm(gridMotor, "BAERT_n1", "BAERT_n1 ", "0.5");
		addParameterToMotorForm(gridMotor, "BAERT_C", "BAERT_C ", "1900");
		addParameterToMotorForm(gridMotor, "BAERT_n2", "BAERT_n2 ", "-2.1");
		addParameterToMotorForm(gridMotor, "LISEVSZKIJ_A", "LISEVSZKIJ_A ", "49260");
		addParameterToMotorForm(gridMotor, "LISEVSZKIJ_C", "LISEVSZKIJ_C  [Cetánszám]", "78");
		addParameterToMotorForm(gridMotor, "LISEVSZKIJ_k", "LISEVSZKIJ_k ", "-0.9");
		addParameterToMotorForm(gridMotor, "LISEVSZKIJ_epsz",
				"LISEVSZKIJ_epsz  [Kompresszióviszony]", "0.09");
		addParameterToMotorForm(gridMotor, "LISEVSZKIJ_l", "LISEVSZKIJ_l ", "-1.25");
		addParameterToMotorForm(gridMotor, "LISEVSZKIJ_m", "LISEVSZKIJ_m ", "0.6");
		addParameterToMotorForm(gridMotor, "LISEVSZKIJ_n", "LISEVSZKIJ_n ", "-0.4");
		addParameterToMotorForm(gridMotor, "LISEVSZKIJ_p", "LISEVSZKIJ_p ", "-3.2");
		addParameterToMotorForm(gridMotor, "KADOTA_z", "KADOTA_z ", "2.5");
		addParameterToMotorForm(gridMotor, "KADOTA_p", "KADOTA_p ", "-2.5");
		addParameterToMotorForm(gridMotor, "KADOTA_fi", "KADOTA_fi ", "-1.4");
		addParameterToMotorForm(gridMotor, "KADOTA_E", "KADOTA_E ", "5000");
		addParameterToMotorForm(gridMotor, "", "Az égésmodellek konstansai:", "");
		addParameterToMotorForm(gridMotor, "egesszog", "egesszog  [ft°]", "80");
		addParameterToMotorForm(gridMotor, "VIBE_m", "VIBE_m ", "0.01");
		addParameterToMotorForm(gridMotor, "VIBE_A", "VIBE_A ", "6.908");
		addParameterToMotorForm(gridMotor, "VIBE2_Pm", "VIBE2_Pm (p/d tömegarány) ", "0.5");
		addParameterToMotorForm(gridMotor, "VIBE2_Pt", "VIBE2_Pt (p/d időarány) ", "0.12");
		addParameterToMotorForm(gridMotor, "VIBE2_Ap", "VIBE2_Ap ", "6.9");
		addParameterToMotorForm(gridMotor, "VIBE2_mp", "VIBE2_mp ", "0.3");
		addParameterToMotorForm(gridMotor, "VIBE2_Ad", "VIBE2_Ad ", "6.9");
		addParameterToMotorForm(gridMotor, "VIBE2_md", "VIBE2_md ", "0.8");
		addParameterToMotorForm(gridMotor, "WHITEHOUSE_K", "WHITEHOUSE_K ", "0.01");
		addParameterToMotorForm(gridMotor, "WHITEHOUSE_x", "WHITEHOUSE_x ", "0.9");
		addParameterToMotorForm(gridMotor, "WHITEHOUSE_m", "WHITEHOUSE_m ", "0.2");
		addParameterToMotorForm(gridMotor, "WHITEHOUSE_Kr", "WHITEHOUSE_Kr ", "1200000");
		addParameterToMotorForm(gridMotor, "WHITEHOUSE_act", "WHITEHOUSE_act ", "8500");
		addParameterToMotorForm(gridMotor, "WHITEHOUSE_a", "WHITEHOUSE_a ", "1500");
		addParameterToMotorForm(gridMotor, "WHITEHOUSE_C", "WHITEHOUSE_C ", "1");

	}

	private int addParameterToMotorForm(Grid gridMotor, String name, String description,
			String value) {
		int r = gridMotor.insertRow(gridMotor.getRowCount());

		if (name == "") {
			Label lblLabel = new Label(description);
			gridMotor.setWidget(r, 0, lblLabel);
		} else {
			{
				Label lblLabel = new Label(description);
				gridMotor.setWidget(r, 0, lblLabel);
			}
			{
				DoubleBox inputBox = new DoubleBox();
				inputBox.setText(value);
				inputBox.setName(MOTOR_FORM_FIELD_PREFIX + name);
				gridMotor.setWidget(r, 1, inputBox);
			}
		}

		return r;
	}

	private void selectFirst() {
		TreeItem startTreeItem = trtmAboutWelcome;

		String treeName = Window.Location.getParameter(SharedConfig.URL_PARAMETER_TREE);
		String itemName = Window.Location.getParameter(SharedConfig.URL_PARAMETER_ITEM);

		if (treeName != null && itemName != null) {
			for (int t = 0; t < treeDeckPanel.getWidgetCount(); t++) {
				if (treeName.equals(treeDeckPanel.getWidget(t).getTitle())) {
					Widget tw = treeDeckPanel.getWidget(t);
					if (tw instanceof Tree) {
						Tree tree = (Tree) tw;
						for (int i = 0; i < tree.getItemCount(); i++) {
							TreeItem item = getItemByText(tree.getItem(i), itemName);
							if (item != null) {
								startTreeItem = item;

								break;
							}
						}
					}

					break;
				}
			}
		}

		Tree startTree = startTreeItem.getTree();

		treeDeckPanel.showWidget(treeDeckPanel.getWidgetIndex(startTree));
		SelectionEvent.fire(startTree, startTreeItem);
	}

	private TreeItem getItemByText(TreeItem parent, String text) {
		if (text.equals(parent.getText())) {
			return parent;
		}

		for (int i = 0; i < parent.getChildCount(); i++) {
			TreeItem item = getItemByText(parent.getChild(i), text);
			if (item != null) {
				return item;
			}
		}

		return null;
	}

	private void registerTreeSelectedHtml(final TreeItem selectorItem, final HTML htmlWidget,
			ExternalTextResource htmlResource, final DeckPanel deckPanel) {
		setAsyncHtml(htmlWidget, htmlResource, selectorItem);
		registerDockSelection(selectorItem, htmlWidget, deckPanel);
	}

	private void setAsyncHtml(final HTML htmlWidget, ExternalTextResource htmlResource,
			TreeItem selectorItem) {
		final String linkToThis = generateLinkToThis(selectorItem);

		try {
			htmlResource.getText(new ResourceCallback<TextResource>() {
				public void onError(ResourceException e) {
					htmlWidget.setHTML(e.getMessage());
				}

				public void onSuccess(TextResource r) {
					try {
						htmlWidget.setHTML(linkToThis + r.getText());
					} catch (Exception e) {
						htmlWidget.setHTML(e.getMessage());
					}
				}
			});
		} catch (Exception e) {
			htmlWidget.setHTML(e.getMessage());
		}
	}

	public String generateLinkToThis(TreeItem selectorItem) {
		return "<span style='float:right; font-size:small;'><a href='"
				+ getTreeItemUrl(selectorItem, null) + "' title='" + LINK_TO_THIS_TITLE + "'>"
				+ LINK_TO_THIS_PAGE + "</a></span>\n";
	}

	private void registerDockSelection(final TreeItem selectorItem, final Widget htmlWidget,
			final DeckPanel deckPanel) {
		Tree selectorTree = selectorItem.getTree();

		selectorTree.addSelectionHandler(new SelectionHandler<TreeItem>() {
			public void onSelection(SelectionEvent<TreeItem> event) {
				if (selectorItem.equals(event.getSelectedItem())) {
					deckPanel.showWidget(deckPanel.getWidgetIndex(htmlWidget));
					Track.track(selectorItem.getTree().getTitle(), selectorItem.getText());
				}
			}
		});
	}

	private static String getTreeItemUrl(TreeItem item, String hash) {
		UrlBuilder url = Window.Location.createUrlBuilder();

		url.setParameter(SharedConfig.URL_PARAMETER_TREE, item.getTree().getTitle());
		url.setParameter(SharedConfig.URL_PARAMETER_ITEM, item.getText());
		url.setHash(hash);

		return url.buildString();
	}

	private void callMotor(FormPanel form, final VerticalPanel resultPanel, final Button btnStart) {
		try {
			while (resultPanel.getWidgetCount() > 0) {
				resultPanel.remove(resultPanel.getWidgetCount() - 1);
			}
			btnStart.setEnabled(false);

			Map<String, String> values = new HashMap<String, String>();
			getNamedValues(form, MOTOR_FORM_FIELD_PREFIX, values);

			final StackPanel parentPanel = (StackPanel) resultPanel.getParent();

			motorService.motorServer(values, new AsyncCallback<MotorResult>() {

				@Override
				public void onFailure(Throwable caught) {
					System.out.println(caught.getMessage());
					resultPanel.add(new Label("Error: " + caught.getMessage()));
					btnStart.setEnabled(true);
					parentPanel.showStack(parentPanel.getWidgetIndex(resultPanel));
				}

				@Override
				public void onSuccess(MotorResult result) {
					btnStart.setEnabled(true);
					resultPanel.add(new Label(result.log));
					buildMotorCharts(resultPanel, result.steps);
					parentPanel.showStack(parentPanel.getWidgetIndex(resultPanel));
				}

			});
		} catch (Throwable e) {
			System.out.println(e.getMessage());
			resultPanel.add(new Label("Error: " + e.getMessage()));
			btnStart.setEnabled(true);
		}
	}

	private void buildMotorCharts(VerticalPanel resultPanel, MotorStep[] steps) {
		DataTable data = DataTable.create();

		int c_fok = data.addColumn(ColumnType.NUMBER, "fok");
		int c_p = data.addColumn(ColumnType.NUMBER, "p");
		int c_V = data.addColumn(ColumnType.NUMBER, "V");
		int c_T_Tabs0 = data.addColumn(ColumnType.NUMBER, "t");
		int c_m = data.addColumn(ColumnType.NUMBER, "m");
		int c_U = data.addColumn(ColumnType.NUMBER, "U");
		int c_szA_0 = data.addColumn(ColumnType.NUMBER, "szA_0");
		int c_szA_1 = data.addColumn(ColumnType.NUMBER, "szA_1");
		int c_s = data.addColumn(ColumnType.NUMBER, "s");
		int c_dHTR = data.addColumn(ColumnType.NUMBER, "dHTR");
		int c_dmdm_2 = data.addColumn(ColumnType.NUMBER, "dmdm_2");
		int c_dE_eges_dfok = data.addColumn(ColumnType.NUMBER, "dE_eges_dfok");
		int c_p_bar = data.addColumn(ColumnType.NUMBER, "p_bar");

		data.addRows(steps.length);
		for (int r = 0; r < steps.length; r++) {
			double fok = steps[r].fok;

			MotorStep step = steps[r];
			data.setValue(r, c_fok, fok);
			data.setValue(r, c_p, step.p);
			data.setValue(r, c_V, step.V * 1e3);
			data.setValue(r, c_T_Tabs0, step.T_Tabs0);
			data.setValue(r, c_m, step.m);
			data.setValue(r, c_U, step.U);
			data.setValue(r, c_szA_0, step.szA_0);
			data.setValue(r, c_szA_1, step.szA_1);
			data.setValue(r, c_s, step.s);
			data.setValue(r, c_dHTR, step.dHTR);
			data.setValue(r, c_dmdm_2, step.dmdm_2);
			data.setValue(r, c_dE_eges_dfok, step.dE_eges_dfok);
			data.setValue(r, c_p_bar, step.p_bar);

		}

		DataView view_fok_p = DataView.create(data);
		view_fok_p.setColumns(new int[] { c_fok, c_p_bar });
		CoreChart chart_fok_p = createMotorChart(view_fok_p, "Crank Angle Degrees - Pressure",
				"A [°]", "p [bar]");
		resultPanel.add(chart_fok_p);

		DataView view_V_p = DataView.create(data);
		view_V_p.setColumns(new int[] { c_V, c_p_bar });
		CoreChart chart_V_p = createMotorChart(view_V_p, "Volume - Pressure", "V [dm3]", "p [bar]");
		resultPanel.add(chart_V_p);

		DataView view_s_T_Tabs0 = DataView.create(data);
		view_s_T_Tabs0.setColumns(new int[] { c_s, c_T_Tabs0 });
		CoreChart chart_T_Tabs0 = createMotorChart(view_s_T_Tabs0,
				"Specific Entropy - Temperature", "s [J/kg·K]", "t [°C]");
		resultPanel.add(chart_T_Tabs0);

		DataView view_fok_dHTR = DataView.create(data);
		view_fok_dHTR.setColumns(new int[] { c_fok, c_dE_eges_dfok });
		CoreChart chart_fok_dHTR = createMotorChart(view_fok_dHTR,
				"Crank Angle Degree - Heat Release / Crank Angle Degree", "A [°]", "HTR/A [J/°]");
		resultPanel.add(chart_fok_dHTR);

		ScrollPanel scrollPanelFigures = new ScrollPanel();
		scrollPanelFigures.setSize("640px", "480px");
		{
			Grid gridFigures = new Grid(steps.length + 1, MotorStep.FIELD_NUM);
			gridFigures.setSize("100%", "100%");
			{
				int r = 0;

				gridFigures.setText(r, c_fok, "fok");
				gridFigures.setText(r, c_p, "p");
				gridFigures.setText(r, c_V, "V*1e3");
				gridFigures.setText(r, c_T_Tabs0, "T_Tabs0");
				gridFigures.setText(r, c_m, "m");
				gridFigures.setText(r, c_U, "U");
				gridFigures.setText(r, c_szA_0, "szA_0");
				gridFigures.setText(r, c_szA_1, "szA_1");
				gridFigures.setText(r, c_s, "s");
				gridFigures.setText(r, c_dHTR, "dHTR");
				gridFigures.setText(r, c_dmdm_2, "dmdm_2");
				gridFigures.setText(r, c_dE_eges_dfok, "dE_eges_dfok");
				gridFigures.setText(r, c_p_bar, "p_bar");

				for (r = 1; r <= steps.length; r++) {
					MotorStep step = steps[r - 1];

					gridFigures.setText(r, c_fok, String.valueOf(step.fok));
					gridFigures.setText(r, c_p, String.valueOf(step.p));
					gridFigures.setText(r, c_V, String.valueOf(step.V * 1e3));
					gridFigures.setText(r, c_T_Tabs0, String.valueOf(step.T_Tabs0));
					gridFigures.setText(r, c_m, String.valueOf(step.m));
					gridFigures.setText(r, c_U, String.valueOf(step.U));
					gridFigures.setText(r, c_szA_0, String.valueOf(step.szA_0));
					gridFigures.setText(r, c_szA_1, String.valueOf(step.szA_1));
					gridFigures.setText(r, c_s, String.valueOf(step.s));
					gridFigures.setText(r, c_dHTR, String.valueOf(step.dHTR));
					gridFigures.setText(r, c_dmdm_2, String.valueOf(step.dmdm_2));
					gridFigures.setText(r, c_dE_eges_dfok, String.valueOf(step.dE_eges_dfok));
					gridFigures.setText(r, c_p_bar, String.valueOf(step.p_bar));
				}
			}
			scrollPanelFigures.setWidget(gridFigures);
		}
		resultPanel.add(scrollPanelFigures);
	}

	private CoreChart createMotorChart(DataView view, String title, String xTitle, String yTitle) {
		double xMin = 0;
		double xMax = 0;
		double yMin = 0;
		double yMax = 0;

		for (int r = 0; r < view.getNumberOfRows(); r++) {
			if (r == 0) {
				xMin = xMax = view.getValueDouble(r, 0);
				yMin = yMax = view.getValueDouble(r, 1);
			} else {
				xMin = Math.min(xMin, view.getValueDouble(r, 0));
				xMax = Math.max(xMax, view.getValueDouble(r, 0));
				yMin = Math.min(yMin, view.getValueDouble(r, 1));
				yMax = Math.max(yMax, view.getValueDouble(r, 1));
			}
		}

		Options options = Options.create();
		options.setTitle(title);
		TextStyle titleStyle = TextStyle.create();
		titleStyle.setFontSize(20);
		options.setTitleTextStyle(titleStyle);
		options.setWidth(640);
		options.setHeight(480);
		ChartArea chartArea = ChartArea.create();
		chartArea.setWidth("80%");
		chartArea.setHeight("70%");
		options.setChartArea(chartArea);
		options.setPointSize(2);
		options.setLineWidth(1);
		options.setLegend(LegendPosition.NONE);

		AxisOptions xOptions = AxisOptions.create();
		xOptions.setMinValue(xMin);
		xOptions.setMaxValue(xMin);
		xOptions.setTitle(xTitle);
		options.setHAxisOptions(xOptions);

		AxisOptions yOptions = AxisOptions.create();
		yOptions.setMinValue(yMin);
		yOptions.setMaxValue(yMin);
		yOptions.setTitle(yTitle);
		options.setVAxisOptions(yOptions);

		ScatterChart lch = new ScatterChart(view, options);

		return lch;
	}

	private void getNamedValues(Widget widget, String prefix, Map<String, String> values) {
		if (widget instanceof HasName && widget instanceof HasText) {
			String prefix_name = ((HasName) widget).getName();

			if (!prefix.equals(prefix_name) && prefix_name.startsWith(prefix)) {
				String name = prefix_name.substring(prefix.length());
				String value = ((HasText) widget).getText();

				values.put(name, value);
			}
		}

		if (widget instanceof HasWidgets) {
			for (Widget child : ((HasWidgets) widget)) {
				getNamedValues(child, prefix, values);
			}
		}

		if (widget instanceof HasOneWidget) {
			Widget child = ((HasOneWidget) widget).getWidget();

			if (child != null) {
				getNamedValues(child, prefix, values);
			}
		}
	}

	protected void do_btnMotorStart_onClick(ClickEvent event) {
		Track.track(treeEngineering.getTitle(), trtmEngineeringDiesel.getText() + "/Start");

		callMotor(formPanelMotor, verticalPanelMotorResult, btnMotorStart);
	}

	protected void do_btnGraphTraversalStart_onClick(ClickEvent event) {
		Track.track(treeSoftware.getTitle(), trtmSoftwareGraphTraversal.getText() + "/Start");

		GwtRunner runner = new GwtRunner();
		runner.start(textGraphTraversalNodes.getText(), txtbxGraphTraversalEdges.getText(),
				verticalPanelGraphTraversalResult);
	}

	/**
	 * http://www.google.com/cse/brand?form=cref
	 * com.google.gwt.dom.client.Node
	 */
	public static native void initCustomSearch(Node n) /*-{
		var f = document.getElementById('cref');
		if (!f) {
			f = n;
		}
		if (f && f.q) {
			var q = f.q;
			var n = navigator;
			var l = location;
			var su = function() {
				var u = document.createElement('input');
				var v = document.location.toString();
				var existingSiteurl = /(?:[?&]siteurl=)([^&#]*)/.exec(v);
				if (existingSiteurl) {
					v = decodeURI(existingSiteurl[1]);
				}
				var delimIndex = v.indexOf('://');
				if (delimIndex >= 0) {
					v = v.substring(delimIndex + '://'.length, v.length);
				}
				u.name = 'siteurl';
				u.value = v;
				u.type = 'hidden';
				f.appendChild(u);
			};
			if (n.appName == 'Microsoft Internet Explorer') {
				var s = f.parentNode.childNodes;
				for ( var i = 0; i < s.length; i++) {
					if (s[i].nodeName == 'SCRIPT'
							&& s[i].attributes['src']
							&& s[i].attributes['src'].nodeValue == unescape('http:\x2F\x2Fwww.google.com\x2Fcse\x2Fbrand?form=cref')) {
						su();
						break;
					}
				}
			} else {
				su();
			}
	
			if (n.platform == 'Win32') {
				q.style.cssText = 'border: 1px solid #7e9db9; padding: 2px;';
			}
	
			if (window.history.navigationMode) {
				window.history.navigationMode = 'compatible';
			}
	
			var b = function() {
				if (q.value == '') {
					q.style.background = '#FFFFFF url(http:\x2F\x2Fwww.google.com\x2Fcse\x2Fintl\x2Fen\x2Fimages\x2Fgoogle_custom_search_watermark.gif) left no-repeat';
				}
			};
	
			var f = function() {
				q.style.background = '#ffffff';
			};
	
			q.onfocus = f;
			q.onblur = b;
	
			if (!/[&?]q=[^&]/.test(l.search)) {
				b();
			}
		}

	}-*/;
}
