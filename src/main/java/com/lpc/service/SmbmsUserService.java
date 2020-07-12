package com.lpc.service;

import com.lpc.dao.DAO;
import com.lpc.pojo.SmbmsUser;
import com.lpc.pojo.SmbmsUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @User lpc/李金超
 * @ClassName SmbmsUserService  类名
 * @Author AUSUA  作者
 * @Date 2020/7/10 18:37  时间
 * @Version 1.0 版本
 */
@Service
public class SmbmsUserService {

    @Autowired
    private DAO dao;



    public SmbmsUser login(SmbmsUser smbmsUser)  {
        try {
            SmbmsUserExample smbmsUserExample = new SmbmsUserExample();
            smbmsUserExample.createCriteria().andUsercodeEqualTo(smbmsUser.getUsercode()).andUserpasswordEqualTo(smbmsUser.getUserpassword());
            ArrayList forList = (ArrayList) dao.findForList("SmbmsUserMapper.selectByExample", smbmsUserExample);
            if(null!=forList&&forList.size()>0){
                return (SmbmsUser) forList.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void savepwdmodify(SmbmsUser userOnLogin) {
        try {
            dao.update("SmbmsUserMapper.updateByPrimaryKeySelective",userOnLogin);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
