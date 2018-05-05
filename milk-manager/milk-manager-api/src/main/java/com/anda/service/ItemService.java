package com.anda.service;

import com.anda.common.pojo.EasyUIDataGridResult;
import com.anda.pojo.TbItem;

public interface ItemService {

    TbItem getItemById(long itemId);

    EasyUIDataGridResult getItemList(int pageNum, int pageSize);
}
