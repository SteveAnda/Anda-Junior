package com.anda.service.impl;

import com.anda.common.pojo.EasyUITreeNode;
import com.anda.mapper.TbItemCatMapper;
import com.anda.pojo.TbItemCat;
import com.anda.pojo.TbItemCatExample;
import com.anda.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService{

    @Autowired
    TbItemCatMapper tbItemCatMapper;

    @Override
    public List<EasyUITreeNode> getItemCatList(long parentId) {

        //根据父节点id查询子节点列表
        TbItemCatExample tbItemCatExample = new TbItemCatExample();
        //查询条件：父节点id = parentId
        tbItemCatExample.createCriteria().andParentIdEqualTo(parentId);

        List<TbItemCat> tbItemCats = tbItemCatMapper.selectByExample(tbItemCatExample);

        //转换成EasyUITreeNode列表
        List<EasyUITreeNode> resultNodes = new ArrayList<EasyUITreeNode>();

        for(TbItemCat tbItemCat : tbItemCats){
            EasyUITreeNode resultNode = new EasyUITreeNode();
            resultNode.setId(tbItemCat.getId());
            resultNode.setText(tbItemCat.getName());
            /*
            * 判断它下面是不是有子节点，如果有，它应该是“closed”
            * 如果没有，应该是“open”
            * */
            resultNode.setState(tbItemCat.getIsParent() ? "closed" : "open" );
            resultNodes.add(resultNode);
        }

        return resultNodes;
    }
}
