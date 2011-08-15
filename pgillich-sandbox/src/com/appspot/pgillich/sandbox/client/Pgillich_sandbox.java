package com.appspot.pgillich.sandbox.client;

import com.appspot.pgillich.sandbox.resource.HtmlResources;
import com.appspot.pgillich.sandbox.resource.ImageResources;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.client.ResourceException;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Pgillich_sandbox implements EntryPoint {
	private static final String URL_PARAMETER_TREE = "tree";
	private static final String URL_PARAMETER_ITEM = "item";
	private static final String LINK_TO_THIS_PAGE = "Link to this page";
	private static final String LINK_TO_THIS_TITLE = "Copy this link";
	
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
	private Image imgAppenginePowered;
	private PushButton pshbtnAppenginePowered;
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
	private HTML htmlEngineeringCatastrophes;
	private HTML htmlEngineeringDiesel;
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
	private HTML htmlSoftwareFavorites;
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
	private TreeItem trtmSoftwareAutotest;
	private TreeItem trtmSoftwareFavorites;
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
	

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		ImageResources imageResources = GWT.create(ImageResources.class);
		HtmlResources htmlResources = GWT.create(HtmlResources.class);
		
		RootPanel rootPanel = RootPanel.get();
		rootPanel.setSize("100%", "100%");
		{
			cmdShowPeople = new Command() {
				@Override
				public void execute() {
					treeDeckPanel.showWidget(treeDeckPanel.getWidgetIndex(treePeople));

					if(treePeople.getSelectedItem() == null) {
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
					
					if(treeEngineering.getSelectedItem() == null) {
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
					
					if(treeNetworks.getSelectedItem() == null) {
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
					
					if(treeHardware.getSelectedItem() == null) {
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
					
					if(treeSoftware.getSelectedItem() == null) {
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
					
					if(treeAbout.getSelectedItem() == null) {
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
					imgAppenginePowered = new Image(imageResources.imgAppenginePowered());
					pshbtnAppenginePowered = new PushButton(imgAppenginePowered);
					topPanel.add(pshbtnAppenginePowered, DockPanel.EAST);
					topPanel.setCellWidth(pshbtnAppenginePowered, "120px");
					pshbtnAppenginePowered.setStyleName("buttonAppenginePowered");
					pshbtnAppenginePowered.setSize("120px", "30px");
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
					htmlEngineeringCatastrophes = new HTML();
					pagesDeckPanel.add(htmlEngineeringCatastrophes);
				}
				{
					htmlEngineeringDiesel = new HTML();
					pagesDeckPanel.add(htmlEngineeringDiesel);
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
				}				{
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
					htmlSoftwareFavorites = new HTML();
					pagesDeckPanel.add(htmlSoftwareFavorites);
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
						registerTreeSelectedHtml(trtmPeopleMyself, htmlPeopleMyself, htmlResources.htmlPeopleMyself(), pagesDeckPanel);
						{
							trtmPeopleCv = new TreeItem("CV");
							trtmPeopleMyself.addItem(trtmPeopleCv);
							registerTreeSelectedHtml(trtmPeopleCv, htmlPeopleCv, htmlResources.htmlPeopleCv(), pagesDeckPanel);
						}							
						trtmPeopleMyself.setState(true);
					}
					{
						trtmPeopleFriends = new TreeItem("Friends");
						treePeople.addItem(trtmPeopleFriends);
						registerTreeSelectedHtml(trtmPeopleFriends, htmlPeopleFriends, htmlResources.htmlPeopleFriends(), pagesDeckPanel);
					}
					{
						trtmPeopleBlogs = new TreeItem("Blogs");
						treePeople.addItem(trtmPeopleBlogs);
						registerTreeSelectedHtml(trtmPeopleBlogs, htmlPeopleBlogs, htmlResources.htmlPeopleBlogs(), pagesDeckPanel);
					}
					{
						trtmPeopleBooks = new TreeItem("Books");
						treePeople.addItem(trtmPeopleBooks);
						registerTreeSelectedHtml(trtmPeopleBooks, htmlPeopleBooks, htmlResources.htmlPeopleBooks(), pagesDeckPanel);
					}
					{
						trtmPeopleMovies = new TreeItem("Movies");
						treePeople.addItem(trtmPeopleMovies);
						registerTreeSelectedHtml(trtmPeopleMovies, htmlPeopleMovies, htmlResources.htmlPeopleMovies(), pagesDeckPanel);
					}				}
				{
					treeEngineering = new Tree();
					treeEngineering.setTitle("Engineering");
					treeDeckPanel.add(treeEngineering);
					{
						trtmEngineeringSystems = new TreeItem("Systems Engineering");
						treeEngineering.addItem(trtmEngineeringSystems);
						registerTreeSelectedHtml(trtmEngineeringSystems, htmlEngineeringSystems, htmlResources.htmlEngineeringSystems(), pagesDeckPanel);
					}
					{
						trtmEngineeringModeling = new TreeItem("Modeling");
						treeEngineering.addItem(trtmEngineeringModeling);
						registerTreeSelectedHtml(trtmEngineeringModeling, htmlEngineeringModeling, htmlResources.htmlEngineeringModeling(), pagesDeckPanel);
						trtmEngineeringModeling.setState(true);
					}
					{
						trtmEngineeringSimulation = new TreeItem("Simulation");
						treeEngineering.addItem(trtmEngineeringSimulation);
						registerTreeSelectedHtml(trtmEngineeringSimulation, htmlEngineeringSimulation, htmlResources.htmlEngineeringSimulation(), pagesDeckPanel);
						{
							trtmEngineeringDiesel = new TreeItem("Diesel Engine");
							trtmEngineeringSimulation.addItem(trtmEngineeringDiesel);
							registerTreeSelectedHtml(trtmEngineeringDiesel, htmlEngineeringDiesel, htmlResources.htmlEngineeringDiesel(), pagesDeckPanel);
						}
						{
							trtmEngineeringEsp = new TreeItem("ESP");
							trtmEngineeringSimulation.addItem(trtmEngineeringEsp);
							registerTreeSelectedHtml(trtmEngineeringEsp, htmlEngineeringEsp, htmlResources.htmlEngineeringEsp(), pagesDeckPanel);
						}
						{
							trtmEngineeringFem = new TreeItem("FEM");
							trtmEngineeringSimulation.addItem(trtmEngineeringFem);
							registerTreeSelectedHtml(trtmEngineeringFem, htmlEngineeringFem, htmlResources.htmlEngineeringFem(), pagesDeckPanel);
						}
						trtmEngineeringSimulation.setState(true);
					}
					{
						trtmEngineeringMuseums = new TreeItem("Museums");
						treeEngineering.addItem(trtmEngineeringMuseums);
						registerTreeSelectedHtml(trtmEngineeringMuseums, htmlEngineeringMuseums, htmlResources.htmlEngineeringMuseums(), pagesDeckPanel);
					}
					{
						trtmEngineeringPaperwork = new TreeItem("Paperwork");
						treeEngineering.addItem(trtmEngineeringPaperwork);
						registerTreeSelectedHtml(trtmEngineeringPaperwork, htmlEngineeringPaperwork, htmlResources.htmlEngineeringPaperwork(), pagesDeckPanel);
					}
					{
						trtmEngineeringPhoto = new TreeItem("Photography");
						treeEngineering.addItem(trtmEngineeringPhoto);
						registerTreeSelectedHtml(trtmEngineeringPhoto, htmlEngineeringPhoto, htmlResources.htmlEngineeringPhoto(), pagesDeckPanel);
					}
					{
						trtmEngineeringSeries = new TreeItem("TV Series");
						treeEngineering.addItem(trtmEngineeringSeries);
						registerTreeSelectedHtml(trtmEngineeringSeries, htmlEngineeringSeries, htmlResources.htmlEngineeringSeries(), pagesDeckPanel);
					}
					{
						trtmEngineeringCatastrophes = new TreeItem("Catastrophes");
						trtmEngineeringCatastrophes.setText("Disasters");
						treeEngineering.addItem(trtmEngineeringCatastrophes);
						registerTreeSelectedHtml(trtmEngineeringCatastrophes, htmlEngineeringCatastrophes, htmlResources.htmlEngineeringCatastrophes(), pagesDeckPanel);
					}
				}
				{
					treeNetworks = new Tree();
					treeDeckPanel.add(treeNetworks);
					treeNetworks.setTitle("Networks");
					{
						trtmNetworksSecurity = new TreeItem("Security");
						treeNetworks.addItem(trtmNetworksSecurity);
						registerTreeSelectedHtml(trtmNetworksSecurity, htmlNetworksSecurity, htmlResources.htmlNetworksSecurity(), pagesDeckPanel);
					}
					{
						trtmNetworksAutomotive = new TreeItem("Automotive");
						treeNetworks.addItem(trtmNetworksAutomotive);
						registerTreeSelectedHtml(trtmNetworksAutomotive, htmlNetworksAutomotive, htmlResources.htmlNetworksAutomotive(), pagesDeckPanel);
					}
					{
						trtmNetworksCms = new TreeItem("CMS");
						treeNetworks.addItem(trtmNetworksCms);
						registerTreeSelectedHtml(trtmNetworksCms, htmlNetworksCms, htmlResources.htmlNetworksCms(), pagesDeckPanel);
					}
					{
						trtmNetworksCloud = new TreeItem("Cloud Computing");
						treeNetworks.addItem(trtmNetworksCloud);
						registerTreeSelectedHtml(trtmNetworksCloud, htmlNetworksCloud, htmlResources.htmlNetworksCloud(), pagesDeckPanel);
					}
					{
						trtmNetworksHosting = new TreeItem("Server Hosting");
						treeNetworks.addItem(trtmNetworksHosting);
						registerTreeSelectedHtml(trtmNetworksHosting, htmlNetworksHosting, htmlResources.htmlNetworksHosting(), pagesDeckPanel);
					}
				}
				{
					treeHardware = new Tree();
					treeDeckPanel.add(treeHardware);
					treeHardware.setTitle("Hardware");
					{
						trtmHardwareHome = new TreeItem("Home");
						treeHardware.addItem(trtmHardwareHome);
						registerTreeSelectedHtml(trtmHardwareHome, htmlHardwareHome, htmlResources.htmlHardwareHome(), pagesDeckPanel);
					}
					{
						trtmHardwareTools = new TreeItem("Tools");
						treeHardware.addItem(trtmHardwareTools);
						registerTreeSelectedHtml(trtmHardwareTools, htmlHardwareTools, htmlResources.htmlHardwareTools(), pagesDeckPanel);
					{
						trtmHardwareSale = new TreeItem("Sale");
						treeHardware.addItem(trtmHardwareSale);
						registerTreeSelectedHtml(trtmHardwareSale, htmlHardwareSale, htmlResources.htmlHardwareSale(), pagesDeckPanel);
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
						registerTreeSelectedHtml(trtmSoftwareAutotest, htmlSoftwareAutotest, htmlResources.htmlSoftwareAutotest(), pagesDeckPanel);
					}
					{
						trtmSoftwareFavorites = new TreeItem("Favorites");
						treeSoftware.addItem(trtmSoftwareFavorites);
						registerTreeSelectedHtml(trtmSoftwareFavorites, htmlSoftwareFavorites, htmlResources.htmlSoftwareFavorites(), pagesDeckPanel);
					}
				}
				{
					treeAbout = new Tree();
					treeDeckPanel.add(treeAbout);
					treeAbout.setTitle("About");
					{
						trtmAboutContact = new TreeItem("Contact");
						treeAbout.addItem(trtmAboutContact);
						registerTreeSelectedHtml(trtmAboutContact, htmlAboutContact, htmlResources.htmlAboutContact(), pagesDeckPanel);
					}
					{
						trtmAboutCopyright = new TreeItem("Copyright");
						treeAbout.addItem(trtmAboutCopyright);
						registerTreeSelectedHtml(trtmAboutCopyright, htmlAboutCopyright, htmlResources.htmlAboutCopyright(), pagesDeckPanel);
					}
					{
						trtmAboutHistory = new TreeItem("History");
						treeAbout.addItem(trtmAboutHistory);
						registerTreeSelectedHtml(trtmAboutHistory, htmlAboutHistory, htmlResources.htmlAboutHistory(), pagesDeckPanel);
					}
					{
						trtmAboutSource = new TreeItem("Source Code");
						treeAbout.addItem(trtmAboutSource);
						registerTreeSelectedHtml(trtmAboutSource, htmlAboutSource, htmlResources.htmlAboutSource(), pagesDeckPanel);
					}
					{
						trtmAboutTechnical = new TreeItem("Technical Details");
						treeAbout.addItem(trtmAboutTechnical);
						registerTreeSelectedHtml(trtmAboutTechnical, htmlAboutTechnical, htmlResources.htmlAboutTechnical(), pagesDeckPanel);
					}
					{
						trtmAboutTraffic = new TreeItem("Traffic Analysis");
						treeAbout.addItem(trtmAboutTraffic);
						registerTreeSelectedHtml(trtmAboutTraffic, htmlAboutTraffic, htmlResources.htmlAboutTraffic(), pagesDeckPanel);
					}
				}
			}			
			
			rightPaddingPanel = new SimplePanel();
			bodyPanel.add(rightPaddingPanel, DockPanel.EAST);
			bodyPanel.setCellWidth(rightPaddingPanel, "80px");
			bodyPanel.setCellHorizontalAlignment(rightPaddingPanel, HasHorizontalAlignment.ALIGN_CENTER);
			rightPaddingPanel.setSize("80px", "100px");
			
			lblRightPadding = new Label("");
			rightPaddingPanel.setWidget(lblRightPadding);
			lblRightPadding.setSize("80px", "100px");
		}
		
		selectFirst();
	}

	private void selectFirst() {
		TreeItem startTreeItem = trtmPeopleMyself;
		
		String treeName = Window.Location.getParameter(URL_PARAMETER_TREE);
		String itemName = Window.Location.getParameter(URL_PARAMETER_ITEM);
		
		if(treeName != null && itemName != null) {
			for(int t=0; t<treeDeckPanel.getWidgetCount(); t++) {
				if(treeName.equals(treeDeckPanel.getWidget(t).getTitle())) {
					Widget tw = treeDeckPanel.getWidget(t);
					if(tw instanceof Tree) {
						Tree tree = (Tree)tw;
						for(int i=0; i<tree.getItemCount(); i++) {
							TreeItem item = getItemByText(tree.getItem(i),itemName);
							if(item != null) {
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
		if(text.equals(parent.getText())) {
			return parent;
		}
			
		for(int i=0; i<parent.getChildCount(); i++) {
			TreeItem item = getItemByText(parent.getChild(i), text);
			if(item != null) {
				return item;
			}
		}
		
		return null;
	}
	
	private void registerTreeSelectedHtml(final TreeItem selectorItem, final HTML htmlWidget, ExternalTextResource htmlResource, final DeckPanel deckPanel) {
		setAsyncHtml(htmlWidget, htmlResource, selectorItem);
		registerDockSelection(selectorItem, htmlWidget, deckPanel);
	}
	
	private void setAsyncHtml(final HTML htmlWidget, ExternalTextResource htmlResource, TreeItem selectorItem) {
		final String linkToThis = "<span style='float:right'><a href='"+getTreeItemUrl(selectorItem, null)+"' title='"+LINK_TO_THIS_TITLE+"'>"+LINK_TO_THIS_PAGE+"</a></span>\n";
		
		try {
			htmlResource.getText(new ResourceCallback<TextResource>() {
				  public void onError(ResourceException e) {}
				  public void onSuccess(TextResource r) {
					try {
					  htmlWidget.setHTML(linkToThis+r.getText());
					} catch (Exception e) {
						htmlWidget.setHTML(e.getMessage());
					}		
				  }
				});
		} catch (Exception e) {
			htmlWidget.setHTML(e.getMessage());
		}		
	}
	
	private void registerDockSelection(final TreeItem selectorItem, final HTML htmlWidget, final DeckPanel deckPanel) {
		Tree selectorTree = selectorItem.getTree();
		
		selectorTree.addSelectionHandler(new SelectionHandler<TreeItem>() {
			public void onSelection(SelectionEvent<TreeItem> event) {
				if(selectorItem.equals(event.getSelectedItem())) {
					deckPanel.showWidget(deckPanel.getWidgetIndex(htmlWidget)); 
					Track.track(selectorItem.getTree().getTitle(), selectorItem.getText());
				}
			}
		});		
	}
	
	public void showDeckHtml() {
		
	}
	
	private String getTreeItemUrl(TreeItem item, String hash) {
		UrlBuilder url = Window.Location.createUrlBuilder();
		
		url.setParameter(URL_PARAMETER_TREE, item.getTree().getTitle());
		url.setParameter(URL_PARAMETER_ITEM, item.getText());
		url.setHash(hash);
		
		return url.buildString();
	}
}
