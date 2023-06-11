package com.repository.itf;

import com.bean.Card;

import java.util.List;

public interface CardRepository {
    List<Card> display();

    void create(Card card);

    void update(String cardId);

    List<Card> search(String str1, String str2);

    void delete(String id);
}
