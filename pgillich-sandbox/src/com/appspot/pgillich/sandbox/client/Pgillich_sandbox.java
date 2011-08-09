package com.appspot.pgillich.sandbox.client;

import com.appspot.pgillich.sandbox.resource.HtmlResources;
import com.appspot.pgillich.sandbox.resource.ImageResources;
import com.gargoylesoftware.htmlunit.AlertHandler;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.debug.client.DebugInfo;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.client.ResourceException;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.InlineHyperlink;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.NamedFrame;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Pgillich_sandbox implements EntryPoint {
	private DockPanel bodyPanel;
	private MenuBar menuBar;
	private MenuItem mntmPeople;
	private MenuItem mntmHardware;
	private MenuItem mntmSoftware;
	private MenuItem mntmEngineering;
	private MenuItem mntmAbout;
	private DeckPanel pagesDeckPanel;
	private Image imgAppenginePowered;
	private PushButton pshbtnAppenginePowered;
	private InlineHTML htmlPeopleMyself;
	private Tree treePeople;
	private TreeItem trtmPeopleMyself;
	private TreeItem trtmPeopleFriends;
	private TreeItem trtmPeopleBlogs;
	private InlineHTML htmlPeopleFriends;
	private InlineHTML htmlPeopleBlogs;
	private DockPanel topPanel;
	private HTML htmlEngineeringSimulation;
	private Tree treeEngineering;
	private TreeItem trtmEngineeringSimulation;
	private TreeItem trtmEngineeringModeling;
	private TreeItem trtmEngineeringSystems;
	private TreeItem trtmEngineeringPaperwork;
	private HTML htmlEngineeringModeling;
	private HTML htmlEngineeringSystems;
	private HTML htmlEngineeringPaperwork;
	private MenuItem mntmNetworks;
	private TreeItem trtmPeopleCv;

	private Command cmdShowEngineering;
	private Command cmdShowPeople;
	private DeckPanel treeDeckPanel;
	private InlineHTML htmlPeopleCv;
	private TreeItem trtmPeopleBooks;
	private HTML htmlPeopleBooks;
	
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
						mntmNetworks = new MenuItem("Networks", false, (Command) null);
						menuBar.addItem(mntmNetworks);
					}
					{
						mntmHardware = new MenuItem("Hardware", false, (Command) null);
						menuBar.addItem(mntmHardware);
					}
					{
						mntmSoftware = new MenuItem("Software", false, (Command) null);
						menuBar.addItem(mntmSoftware);
					}
					{
						mntmAbout = new MenuItem("About", false, (Command) null);
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
				pagesDeckPanel.setAnimationEnabled(true);
				bodyPanel.add(pagesDeckPanel, DockPanel.CENTER);
				pagesDeckPanel.setSize("100%", "100%");
				{
					htmlPeopleMyself = new InlineHTML("People Myself");
					pagesDeckPanel.add(htmlPeopleMyself);
				}
				{
					htmlPeopleFriends = new InlineHTML();
					pagesDeckPanel.add(htmlPeopleFriends);
				}
				{
					htmlPeopleBlogs = new InlineHTML("People Blogs");
					pagesDeckPanel.add(htmlPeopleBlogs);
				}
				{
					htmlPeopleCv = new InlineHTML("People CV");
					pagesDeckPanel.add(htmlPeopleCv);
				}
				{
					htmlPeopleBooks = new HTML("Books", true);
					pagesDeckPanel.add(htmlPeopleBooks);
				}
				{
					htmlEngineeringSimulation = new HTML("Engineering Simulation");
					pagesDeckPanel.add(htmlEngineeringSimulation);
				}
				{
					htmlEngineeringModeling = new HTML();
					htmlEngineeringModeling.setHTML("Engineering Modeling");
					pagesDeckPanel.add(htmlEngineeringModeling);
				}
				{
					htmlEngineeringSystems = new HTML("Engineering Systems");
					pagesDeckPanel.add(htmlEngineeringSystems);
				}
				{
					htmlEngineeringPaperwork = new HTML("Engineering Paperwork");
					pagesDeckPanel.add(htmlEngineeringPaperwork);
				}
			}
			{
				treeDeckPanel = new DeckPanel();
				treeDeckPanel.setStyleName("treeDeckPanel");
				bodyPanel.add(treeDeckPanel, DockPanel.WEST);
				bodyPanel.setCellWidth(treeDeckPanel, "50px");
				{
					treePeople = new Tree();
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
				}
				{
					treeEngineering = new Tree();
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
					}
					{
						trtmEngineeringSimulation = new TreeItem("Simulation");
						treeEngineering.addItem(trtmEngineeringSimulation);
						registerTreeSelectedHtml(trtmEngineeringSimulation, htmlEngineeringSimulation, htmlResources.htmlEngineeringSimulation(), pagesDeckPanel);
					}
					{
						trtmEngineeringPaperwork = new TreeItem("Paperwork");
						treeEngineering.addItem(trtmEngineeringPaperwork);
						registerTreeSelectedHtml(trtmEngineeringPaperwork, htmlEngineeringPaperwork, htmlResources.htmlEngineeringPaperwork(), pagesDeckPanel);
					}
				}
			}			
		}
		
		selectFirst();
	}

	private void selectFirst() {
		treeDeckPanel.showWidget(treeDeckPanel.getWidgetIndex(treePeople));
		SelectionEvent.fire(treePeople, trtmPeopleMyself);
	}
	
	private void registerTreeSelectedHtml(final TreeItem selectorItem, final HTML htmlWidget, ExternalTextResource htmlResource, final DeckPanel deckPanel) {
		setAsyncHtml(htmlWidget, htmlResource);
		registerDockSelection(selectorItem, htmlWidget, deckPanel);
	}
	
	private void setAsyncHtml(final HTML htmlWidget, ExternalTextResource htmlResource) {
		try {
			htmlResource.getText(new ResourceCallback<TextResource>() {
				  public void onError(ResourceException e) {}
				  public void onSuccess(TextResource r) {
					try {
					  htmlWidget.setHTML(r.getText());
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
				if(selectorItem.equals(event.getSelectedItem()))
					deckPanel.showWidget(deckPanel.getWidgetIndex(htmlWidget)); 
			}
		});		
	}
}
