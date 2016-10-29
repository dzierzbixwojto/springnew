package com.wmusial.service;

import com.wmusial.model.Book;
import com.wmusial.model.User;

/**
 * Created by dzier on 29.10.2016.
 */
public interface RentService {
    void createRent(User user, Book book);
}
