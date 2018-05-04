package com.anda.service.impl;

import com.anda.mapper.TbItemMapper;
import com.anda.pojo.TbItem;
import com.anda.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemServiceImpl implements ItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemServiceImpl.class);

    @Autowired
    private TbItemMapper itemMapper;

    @Override
    public TbItem getItemById(long itemId) {

        LOGGER.info("======================ItemServiceImpl=====================getItemById方法执行");
        return itemMapper.selectByPrimaryKey(itemId);
    }
}
