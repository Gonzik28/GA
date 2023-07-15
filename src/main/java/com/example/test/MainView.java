package com.example.test;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("main")
public class MainView extends VerticalLayout {
    private final StatisticService statisticService;

    public MainView(StatisticService statisticService) {
        this.statisticService = statisticService;
        TextField taskField = new TextField();
        Button addButton = new Button("Add");
        addButton.addClickListener(click -> {
            try{
                long inc = (taskField.getValue().isEmpty()) ? 1l : parser(taskField.getValue());
                Statistic statistic = this.statisticService.create(inc);
                taskField.setValue(String.valueOf(statistic.getSum()));
            }catch(RuntimeException ex){
                taskField.setValue(ex.getMessage());
            }
        });
        addButton.addClickShortcut(Key.ENTER);
        add(
                new H1("Test:"),
                taskField,
                new HorizontalLayout(
                        taskField,
                        addButton
                )
        );
    }
    static long parser(String number) {
        try {
           return Long.parseLong(number);
        } catch (NumberFormatException exception){
            throw new RuntimeException("Enter a number (whole)");
        }
    }

}
