package com.wj.taotao.service.impl;

import com.wj.taotao.common.EasyUITreeNode;
import com.wj.taotao.mapper.TbItemCatMapper;
import com.wj.taotao.pojo.TbItemCat;
import com.wj.taotao.pojo.TbItemCatExample;
import com.wj.taotao.service.IItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements IItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public List<EasyUITreeNode> getCatList(Long parentId) {
        TbItemCatExample tbItemCatExample = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = tbItemCatExample.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> list = itemCatMapper.selectByExample(tbItemCatExample);
        List<EasyUITreeNode> resultList = new ArrayList<>();
        for(TbItemCat tbItemCat : list){
            EasyUITreeNode easyUITreeNode = new EasyUITreeNode();
            easyUITreeNode.setId(tbItemCat.getId());
            easyUITreeNode.setState(tbItemCat.getIsParent() ? "closed" : "open");
            easyUITreeNode.setText(tbItemCat.getName());
            resultList.add(easyUITreeNode);
        }
        return resultList;
    }
}
