package ru.academits.servlet;

import ru.academits.PhoneBook;
import ru.academits.coverter.ContactConverter;
import ru.academits.model.Contact;
import ru.academits.service.ContactService;
import ru.academits.service.ContactValidation;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class AddContactServlet extends HttpServlet {
    private static final long serialVersionUID = -1457736207802513491L;
    private ContactService contactService = PhoneBook.contactService;
    private ContactConverter contactConverter = PhoneBook.contactConverter;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String contactParams = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Contact contact = contactConverter.convertFormStringParam(contactParams);

        ContactValidation contactValidation = contactService.addContact(contact);
        contactService.saveLastContactValidation(contactValidation);
        if (contactValidation.isValid()) {
            contactService.saveLastContact(new Contact());
        } else {
            contactService.saveLastContact(contact);
        }

        resp.sendRedirect("/phonebook");
    }
}
