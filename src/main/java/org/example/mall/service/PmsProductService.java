package org.example.mall.service;

import org.example.mall.mbg.model.PmsProduct;

import java.util.List;

/**
 * 商品管理Service接口
 * 
 * @author Mall
 * @version 1.2
 */
public interface PmsProductService {

    /**
     * 获取所有商品列表
     * 
     * @return 商品列表
     */
    List<PmsProduct> listAllProducts();

    /**
     * 根据ID获取商品详情
     * 
     * @param id 商品ID
     * @return 商品详情
     */
    PmsProduct getProductById(Long id);

    /**
     * 创建商品
     * 
     * @param product 商品信息
     * @return 创建结果
     */
    int createProduct(PmsProduct product);

    /**
     * 更新商品信息
     * 
     * @param id 商品ID
     * @param product 商品信息
     * @return 更新结果
     */
    int updateProduct(Long id, PmsProduct product);

    /**
     * 删除商品
     * 
     * @param id 商品ID
     * @return 删除结果
     */
    int deleteProduct(Long id);

    /**
     * 根据商品名称搜索商品
     * 
     * @param productName 商品名称
     * @return 商品列表
     */
    List<PmsProduct> searchProductsByName(String productName);
}
