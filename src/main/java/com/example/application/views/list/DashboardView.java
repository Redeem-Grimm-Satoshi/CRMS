package com.example.application.views.list;

import com.example.application.data.service.CrmService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value = "Dashboard", layout = MainLayout.class)
@PageTitle("Dashboard")
public class DashboardView extends VerticalLayout {

    private CrmService crmService;

    public DashboardView(CrmService crmService){
        this.crmService=crmService;
        addClassName("dashboard-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(getContactStats(),getCompanychart());

    }

    private Component getCompanychart() {
        Chart chart=new Chart(ChartType.PIE);
        DataSeries dataSeries=new DataSeries();
        crmService.findCompany().forEach(company ->
                dataSeries.add(new DataSeriesItem(company.getName(), company.getEmployeeCount())));

        chart.getConfiguration().setSeries(dataSeries);
        return chart;

    }

    private Component getContactStats() {
        Span stat=new Span(crmService.countContact() + " Contacts ");
        stat.addClassNames("text-xl", "mt-m");
        return stat;
    }
}
