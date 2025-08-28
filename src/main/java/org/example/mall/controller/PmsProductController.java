package org.example.mall.controller;

import org.example.mall.common.CommonResult;
import org.example.mall.mbg.model.PmsProduct;
import org.example.mall.service.PmsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品管理Controller
 * 
 * @author Mall
 * @version 1.2
 */
@RestController
@RequestMapping("/api/v1/pms/products")
public class PmsProductController {

    @Autowired
    private PmsProductService productService;

    /**
     * 获取所有商品列表
     * 
     * @return 商品列表
     */
    @GetMapping("/list")
    public CommonResult<List<PmsProduct>> listAllProducts() {
        List<PmsProduct> products = productService.listAllProducts();
        return CommonResult.success(products);
    }

    /**
     * 根据ID获取商品详情
     * 
     * @param id 商品ID
     * @return 商品详情
     */
    @GetMapping("/detail/{id}")
    public CommonResult<PmsProduct> getProductById(@PathVariable Long id) {
        PmsProduct product = productService.getProductById(id);
        if (product != null) {
            return CommonResult.success(product);
        } else {
            return CommonResult.failed("商品不存在");
        }
    }

    /**
     * 创建商品
     * 
     * @param product 商品信息
     * @return 创建结果
     */
    @PostMapping("/create")
    public CommonResult<String> createProduct(@RequestBody PmsProduct product) {
        int result = productService.createProduct(product);
        if (result > 0) {
            return CommonResult.success("商品创建成功");
        } else {
            return CommonResult.failed("商品创建失败");
        }
    }

    /**
     * 更新商品信息
     * 
     * @param id 商品ID
     * @param product 商品信息
     * @return 更新结果
     */
    @PutMapping("/update/{id}")
    public CommonResult<String> updateProduct(@PathVariable Long id, @RequestBody PmsProduct product) {
        int result = productService.updateProduct(id, product);
        if (result > 0) {
            return CommonResult.success("商品更新成功");
        } else {
            return CommonResult.failed("商品更新失败");
        }
    }

    /**
     * 删除商品
     * 
     * @param id 商品ID
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    public CommonResult<String> deleteProduct(@PathVariable Long id) {
        int result = productService.deleteProduct(id);
        if (result > 0) {
            return CommonResult.success("商品删除成功");
        } else {
            return CommonResult.failed("商品删除失败");
        }
    }

    /**
     * 根据商品名称搜索商品
     * 
     * @param name 商品名称
     * @return 商品列表
     */
    @GetMapping("/search/by-name")
    public CommonResult<List<PmsProduct>> searchProductsByName(@RequestParam String name) {
        List<PmsProduct> products = productService.searchProductsByName(name);
        return CommonResult.success(products);
    }
}
