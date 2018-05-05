package com.anda.service;

import com.anda.common.pojo.EasyUITreeNode;

import java.util.List;

public interface ItemCatService {

    List<EasyUITreeNode> getItemCatList(long parentId);

}
