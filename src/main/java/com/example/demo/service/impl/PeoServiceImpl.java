package com.example.demo.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.PeoDao;
import com.example.demo.entity.Peo;
import com.example.demo.service.PeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PeoServiceImpl implements PeoService {
    @Autowired
    PeoDao peoDao;
    @Override
    public Peo selectById()  {
        Peo peo = peoDao.selectById();

        String name = peo.getName();
        Object parse1 = JSON.parse(name.trim());

        return peo;
    }
}
