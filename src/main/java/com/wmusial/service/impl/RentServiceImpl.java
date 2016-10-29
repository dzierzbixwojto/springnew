package com.wmusial.service.impl;

import com.wmusial.dao.BookDao;
import com.wmusial.dao.RentDao;
import com.wmusial.model.Book;
import com.wmusial.model.Rent;
import com.wmusial.model.User;
import com.wmusial.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dzier on 29.10.2016.
 */
@Service
public class RentServiceImpl implements RentService{

    @Autowired
    private RentDao rentDao;
    @Autowired
    private BookDao bookDao;

    @Override
    @Transactional
    public void createRent(User user, Book book) {
        Rent rent = new Rent(user,book);

        rentDao.save(rent);
        book.decrementQuantity();
        bookDao.save(book);
    }
}
