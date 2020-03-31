package com.vladyslav.social.repository;

import com.vladyslav.social.entity.Message;

import org.springframework.data.repository.CrudRepository;

public interface MessageRepos extends CrudRepository<Message, Integer> {
    Iterable<Message> findByTag(String filter);
}
