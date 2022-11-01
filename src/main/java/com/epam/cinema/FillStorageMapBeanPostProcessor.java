package com.epam.cinema;

import com.epam.cinema.model.Event;
import com.epam.cinema.model.Ticket;
import com.epam.cinema.model.User;
import com.epam.cinema.repository.impl.EventRepositoryImpl;
import com.epam.cinema.repository.impl.TicketRepositoryImpl;
import com.epam.cinema.repository.impl.UserRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.List;

@Slf4j
public class FillStorageMapBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof UserRepositoryImpl) {
            log.info("postProcessAfterInitialization: userRepository bean");
            UserRepositoryImpl userRepository = (UserRepositoryImpl) bean;
            List<User> userList = userRepository.getStorage().extractUsers();
            userRepository.setUserList(userList);
        }
        if (bean instanceof EventRepositoryImpl) {
            log.info("postProcessAfterInitialization: eventRepository bean");
            EventRepositoryImpl eventRepository = (EventRepositoryImpl) bean;
            List<Event> events = eventRepository.getStorage().extractEvents();
            eventRepository.setEvents(events);
        }
        if (bean instanceof TicketRepositoryImpl) {
            log.info("postProcessAfterInitialization: ticketRepository bean");
            TicketRepositoryImpl ticketRepository = (TicketRepositoryImpl) bean;
            List<Ticket> tickets = ticketRepository.getStorage().extractTickets();
            ticketRepository.setTickets(tickets);
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
