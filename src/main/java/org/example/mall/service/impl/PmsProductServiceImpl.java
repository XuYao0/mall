package org.example.mall.service.impl;

import org.example.mall.mbg.mapper.PmsProductMapper;
import org.example.mall.mbg.model.PmsProduct;
import org.example.mall.mbg.model.PmsProductExample;
import org.example.mall.service.PmsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 商品管理Service实现类
 * 
 * @author Mall
 * @version 1.2
 */
@Service
public class PmsProductServiceImpl implements PmsProductService {

    @Autowired
    private PmsProductMapper productMapper;

    @Override
    public List<PmsProduct> listAllProducts() {
        PmsProductExample example = new PmsProductExample();
        example.setOrderByClause("create_time desc");
        return productMapper.selectByExample(example);
    }

    @Override
    public PmsProduct getProductById(Long id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public int createProduct(PmsProduct product) {
        product.setCreateTime(new Date());
        product.setUpdateTime(new Date());
        return productMapper.insertSelective(product);
    }

    @Override
    public int updateProduct(Long id, PmsProduct product) {
        product.setId(id);
        product.setUpdateTime(new Date());
        return productMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public int deleteProduct(Long id) {
        return productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<PmsProduct> searchProductsByName(String productName) {
        PmsProductExample example = new PmsProductExample();
        PmsProductExample.Criteria criteria = example.createCriteria();
        criteria.andProductNameLike("%" + productName + "%");
        example.setOrderByClause("create_time desc");
        return productMapper.selectByExample(example);
    }
}
