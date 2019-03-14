package com.wj.taotao.search.listener;

import com.wj.taotao.search.service.ISearchItemService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ItemChangeMessageListener implements MessageListener {


    @Autowired
    private ISearchItemService searchItemService;

    @Override
    public void onMessage(Message message) {
        try{
            Long itemId = null;
            if(message instanceof TextMessage){
                TextMessage textMessage = (TextMessage) message;
                itemId = Long.parseLong(textMessage.getText());
                searchItemService.updateItemById(itemId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
