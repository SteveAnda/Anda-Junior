package com.anda.service.impl;

import com.anda.common.pojo.EasyUIDataGridResult;
import com.anda.mapper.TbItemMapper;
import com.anda.pojo.TbItem;
import com.anda.pojo.TbItemExample;
import com.anda.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ItemServiceImpl implements ItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemServiceImpl.class);

    @Autowired
    private TbItemMapper itemMapper;

    @Override
    public TbItem getItemById(long itemId) {

        LOGGER.info("======================ItemServiceImpl=====================getItemById方法执行");
        TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);
        return tbItem;
    }

    @Override
    public EasyUIDataGridResult getItemList(int pageNum, int pageSize) {

        //分页处理
        /**
         * @param pageNum  页码
         * @param pageSize 每页显示数量
         */
        PageHelper.startPage(pageNum, pageSize);
        //执行查询
        TbItemExample tbItemExample = new TbItemExample();
        List<TbItem> tbItems = itemMapper.selectByExample(tbItemExample);
        //创建返回结果对象
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(tbItems);
        //获取总记录数
        PageInfo<TbItem> pageInfo = new PageInfo(tbItems);
        result.setTotal(pageInfo.getTotal());

        return result;
    }
}
