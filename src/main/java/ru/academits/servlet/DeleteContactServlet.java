package ru.academits.servlet;

import ru.academits.PhoneBook;
import ru.academits.service.ContactService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteContactServlet extends HttpServlet {
    private static final long serialVersionUID = -4241734906178441799L;
    private ContactService contactService = PhoneBook.contactService;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] contactIdValues = req.getParameterValues("contactId");
        int contactId = Integer.parseInt(contactIdValues[0]);

        contactService.deleteContact(contactId);

        resp.sendRedirect("/phonebook");
    }
}
