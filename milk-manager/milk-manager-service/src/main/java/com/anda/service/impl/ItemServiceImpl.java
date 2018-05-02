package com.anda.service.impl;

import com.anda.mapper.TbItemMapper;
import com.anda.pojo.TbItem;
import com.anda.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Override
    public TbItem getItemById(long itemId) {
        System.out.println("-----===============================-------------ItemServiceImpl------------------getItemById方法执行-----------------------");
        return itemMapper.selectByPrimaryKey(itemId);
    }
}
