package com.group1.merchant.service.impl;

import com.group1.core.entity.shop.Shop;
import com.group1.merchant.dao.ShopRepository;
import com.group1.merchant.service.ShopService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("shopService")
public class ShopServiceImpl implements ShopService {

    @Resource
    private ShopRepository shopRepository;

    @Override
    @Transactional

    public Shop save(Shop shop) {
        return shopRepository.save(shop);
    }

    @Override
    @Transactional
    public Shop update(Shop Shop) {
        return null;
    }

    @Override
    @Transactional
    public Shop findByShopId(String shop_id) {
        return null;
    }

    @Override
    @Transactional
    public Shop findByMerchant(String merchantId) {
        return null;
    }
}
