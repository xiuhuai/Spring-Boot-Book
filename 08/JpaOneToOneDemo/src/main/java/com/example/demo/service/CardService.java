package com.example.demo.service;



import com.example.demo.entity.Card;

import java.util.List;

/**
 * @author longzhonghua
 * @data 2019/01/27 21:56
 */
public interface CardService {
    public List<Card> getCardList();
    public Card findCardById(long id);
}
