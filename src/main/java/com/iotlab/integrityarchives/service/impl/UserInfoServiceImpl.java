package com.iotlab.integrityarchives.service.impl;


import com.iotlab.integrityarchives.common.service.impl.BaseServiceImpl;
import com.iotlab.integrityarchives.dao.UserInfoDao;
import com.iotlab.integrityarchives.entity.UserInfo;
import com.iotlab.integrityarchives.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@SuppressWarnings("all")
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo> implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;


    @Override
    @Transactional
    public void save(UserInfo userInfo) {
        try {
            //passwordHelper.encryptPassword(user); //加密
            userInfoDao.insert(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
            // throw new GlobalException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void update(UserInfo user) {
        if (user.getId() != 0) {
            try {
                this.updateNotNull(user);
            } catch (Exception e) {
                e.printStackTrace();
                //throw new GlobalException(e.getMessage());
            }
        }
    }

    @Override
    @Transactional
    public void delete(List<Long> ids) {
        if (!ids.isEmpty()) {
            try {
                //通用mapper自带的批量删除
                this.batchDelete(ids, "id", UserInfo.class);
            } catch (Exception e) {
                e.printStackTrace();
                // throw new GlobalException(e.getMessage());
            }
        }
    }


    @Override
    public UserInfo findByUserId(Integer id) {
        return userInfoDao.findByUserId(id);
    }


    @Override
    public List<UserInfo> findListByWord(String word) {
        return userInfoDao.findListByWord(word);
    }

    @Override
    public List<UserInfo> findByPage(UserInfo userInfo) {
        return userInfoDao.select(userInfo);
    }


}