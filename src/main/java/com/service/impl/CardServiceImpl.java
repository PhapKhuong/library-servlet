package com.service.impl;

import com.bean.Card;
import com.repository.impl.CardRepositoryImpl;
import com.repository.itf.CardRepository;
import com.service.itf.CardService;

import java.util.List;

public class CardServiceImpl implements CardService {
    private CardRepository repository = new CardRepositoryImpl();

    @Override
    public List<Card> display() {
        return repository.display();
    }

    @Override
    public void create(Card card) {
        repository.create(card);
    }

    @Override
    public void update(String cardId) {
        repository.update(cardId);
    }

    @Override
    public List<Card> search(String str1, String str2) {
        return repository.search(str1, str2);
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }
}
