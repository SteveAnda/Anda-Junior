package com.anda.controller;

import com.anda.common.pojo.EasyUITreeNode;
import com.anda.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ItemCatController {

    @Autowired
    ItemCatService itemCatService;

    @RequestMapping("/item/cat/list")
    @ResponseBody
    public List<EasyUITreeNode> getItemCatList(@RequestParam(value = "id",defaultValue = "0")long parentId){

        List<EasyUITreeNode> resultNodes = itemCatService.getItemCatList(parentId);

        return resultNodes;
    }
}
