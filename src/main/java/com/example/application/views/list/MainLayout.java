package com.example.application.views.list;

import com.example.application.security.SecurityService;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightCondition;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

import javax.swing.plaf.basic.BasicGraphicsUtils;

public class MainLayout extends AppLayout {

    private SecurityService securityService;

    public MainLayout(SecurityService securityService){
        this.securityService=securityService;

        createHeader();
        createDrawer();
    }

    private void createDrawer() {
        RouterLink listView=new RouterLink("List", ListView.class);
        listView.setHighlightCondition(HighlightConditions.sameLocation());
        addToDrawer(new VerticalLayout(listView,new RouterLink("Dashboard", DashboardView.class)));
    }

    private void createHeader() {
        H1 logo= new H1("CRMS");
        logo.addClassNames(
                "text-l","m-m"
        );

       String username=securityService.getAuthenticatedUser().getUsername();
       Button logout=new Button("Log out " + username, e -> securityService.logout());
       HorizontalLayout header= new HorizontalLayout(new DrawerToggle(),logo,logout);
       header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
       header.expand(logo);
       header.setWidthFull();
       header.addClassNames("py-0","px-m");
       addToNavbar(header);
    }


}
