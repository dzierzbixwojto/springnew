package com.wmusial.controller;

import com.wmusial.dao.BookDao;
import com.wmusial.dao.RentDao;
import com.wmusial.dao.UserDao;
import com.wmusial.model.Book;
import com.wmusial.model.Rent;
import com.wmusial.model.User;
import com.wmusial.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

/**
 * Created by dzier on 29.10.2016.
 */
@Controller
public class RentController {
    @Autowired
    private RentDao rentDao;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RentService rentService;


    @RequestMapping(value = "/rents", method = RequestMethod.GET)
    public String getRentsPage(Model model, Principal principal)
    {
        String email = principal.getName();
        User user  = userDao.findByEmail(email);

        List<Rent> rents;

        if(user.getRole()==User.Role.USER) {
            rents = rentDao.findByUserOrderByCreatedDateDesc(user); //zapisujemy do listy wszystkie renty - //zamiast findAll zmieniamy
        }else{
            rents= rentDao.findAll();
        }
        model.addAttribute("rentsList", rents); //dodajemy do wyswietlania do modelu (view)
        return "rents";
    }
@RequestMapping(value="/rent/book/{bookId}")
        public String createRent(@PathVariable Long bookId, Principal principal, RedirectAttributes redirectAttributes){


    String email= principal.getName();
    //majac user dao:
    User user = userDao.findByEmail(email);
    Book book = bookDao.findOne(bookId);

    if(book.getAvailable()<=0){
        redirectAttributes.addFlashAttribute("cantRent" , true);
        return "redirect:/books";
    }


    rentService.createRent(user,book);



        return "redirect:/rents";

    }

}
