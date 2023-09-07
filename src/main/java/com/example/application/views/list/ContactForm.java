package com.example.application.views.list;

import com.example.application.data.entity.Company;
import com.example.application.data.entity.Contact;
import com.example.application.data.entity.Status;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class ContactForm extends FormLayout {

    Binder<Contact> binder=new BeanValidationBinder<>(Contact.class);

    TextField firstName=new TextField("First name");
    TextField lastName=new TextField("Last name");
    EmailField emailField=new EmailField("Email");

    ComboBox<Status> status=new ComboBox<>("Status");
    ComboBox<Company> company=new ComboBox<>("Company");

    Button save=new Button("Save");
    Button delete=new Button("Delete");
    Button cancel=new Button("Cancel");
    private Contact contact;

    public ContactForm(List<Company> companies, List<Status> statuses){
        binder.bindInstanceFields(this);
        addClassName("contact-form");
        company.setItems(companies);
        company.setItemLabelGenerator(Company::getName);

        status.setItems(statuses);
        status.setItemLabelGenerator(Status::getName);

        add(firstName,lastName,emailField,status,company,creatButtonLayout());

    }

    //Read Contact Bean
    public void setContact(Contact contact){
        this.contact = contact;
        binder.readBean(contact);
    }

    private Component creatButtonLayout() {

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        //validate and save
        save.addClickShortcut(Key.ENTER);
        save.addClickListener(e->validateAndSave());

        //delete event
        delete.addClickListener(e->fireEvent(new DeleteEvent(this, contact)));

        //cancel event
        cancel.addClickListener(e-> fireEvent(new CloseEvent(this)));
        cancel.addClickShortcut(Key.ESCAPE);


        return new HorizontalLayout(save,delete,cancel);
    }

    //validate and save method
    private void validateAndSave(){
        try {
            binder.writeBean(contact);
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
        fireEvent(new SaveEvent(this,contact));

    }

    // Events
    public static abstract class ContactFormEvent extends ComponentEvent<ContactForm> {
        private Contact contact;

        protected ContactFormEvent(ContactForm source, Contact contact) {


            super(source, false);
            this.contact = contact;
        }

        public Contact getContact() {
            return contact;
        }
    }

    public static class SaveEvent extends ContactFormEvent {
        SaveEvent(ContactForm source, Contact contact) {
            super(source, contact);
        }
    }

    public static class DeleteEvent extends ContactFormEvent {
        DeleteEvent(ContactForm source, Contact contact) {
            super(source, contact);
        }

    }

    public static class CloseEvent extends ContactFormEvent {
        CloseEvent(ContactForm source) {
            super(source, null);
        }
    }

    public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener) {


        return addListener(DeleteEvent.class, listener);
    }

    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
        return addListener(SaveEvent.class, listener);
    }
    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
        return addListener(CloseEvent.class, listener);
    }


}


