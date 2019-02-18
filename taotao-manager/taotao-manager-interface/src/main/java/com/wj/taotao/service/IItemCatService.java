package com.wj.taotao.service;

import com.wj.taotao.common.EasyUITreeNode;

import java.util.List;

public interface IItemCatService {

    public List<EasyUITreeNode> getCatList(Long parentId);
}
