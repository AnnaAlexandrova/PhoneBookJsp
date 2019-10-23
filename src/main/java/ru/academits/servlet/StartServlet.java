package ru.academits.servlet;

import ru.academits.PhoneBook;
import ru.academits.model.Contact;
import ru.academits.service.ContactService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class StartServlet extends HttpServlet {
    private static final long serialVersionUID = -5546674637156297202L;
    private ContactService contactService = PhoneBook.contactService;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] requestValue = req.getParameterValues("queryString");
        if (requestValue != null) {
            String queryString = URLDecoder.decode(requestValue[0], StandardCharsets.UTF_8).toUpperCase();
            List<Contact> contactList = contactService.searchContacts(queryString);

            req.setAttribute("contactList", contactList);
        } else {
            req.setAttribute("contactList", contactService.getAllContacts());
        }
        req.setAttribute("contactValidation", contactService.getLastContactValidation());
        req.setAttribute("currentContact", contactService.getLastContact());
        req.getRequestDispatcher("phonebook.jsp").forward(req, resp);
    }
}
