package com.lpc.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lpc.constant.ConstantKey;
import com.lpc.dao.DAO;
import com.lpc.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @User lpc/李金超
 * @ClassName SysService  类名
 * @Author AUSUA  作者
 * @Date 2020/7/10 19:04  时间
 * @Version 1.0 版本
 */
@Service
public class SysService {

    @Autowired
    private DAO dao;
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 查询列表+模糊查询
     * @param productName
     * @param providerId
     * @param isPayment
     * @return
     */
    public List<Map<String, Object>> billList(String productName, String providerId, String isPayment) {
        try {
            List<Map<String,Object>> listMap = new ArrayList<>();
            SmbmsBillExample smbmsBillExample = new SmbmsBillExample();
            SmbmsBillExample.Criteria criteria = smbmsBillExample.createCriteria();
            boolean b1 = null != productName && !"".equals(productName);
            if(b1){
                criteria.andProductnameLike("%"+productName+"%");
            }
            boolean b = null != providerId && !"".equals(providerId) && !"0".equals(providerId);
            if(b){
                criteria.andProvideridEqualTo(Long.valueOf(providerId));
            }
            boolean b2 = null != isPayment && !"".equals(isPayment) && !"0".equals(isPayment);
            if(b2){
                criteria.andIspaymentEqualTo(Integer.valueOf(isPayment));
            }
            if(b1 || b || b2){
                List<SmbmsBill> forList = (List<SmbmsBill>) dao.findForList("SmbmsBillMapper.selectByExample", smbmsBillExample);
                for (SmbmsBill smbmsBill : forList) {
                    SmbmsProviderExample smbmsProviderExample = new SmbmsProviderExample();
                    smbmsProviderExample.createCriteria().andIdEqualTo(smbmsBill.getProviderid());
                    SmbmsProvider forObject = (SmbmsProvider) dao.findForObject("SmbmsProviderMapper.selectByExample", smbmsProviderExample);
                    Map<String, Object> mapVo = getMap(smbmsBill);
                    if (forObject!=null){
                        mapVo.put("proName",forObject.getProname());
                    }else {
                        mapVo.put("proName","");
                    }
                    listMap.add(mapVo);
                }
            }else {
                listMap = (List<Map<String, Object>>) redisTemplate.opsForValue().get(ConstantKey.BILLLIST);
                if(listMap==null||listMap.size()==0){
                    listMap = new ArrayList<>();
                    List<SmbmsBill> forList = (List<SmbmsBill>) dao.findForList("SmbmsBillMapper.selectByExample", smbmsBillExample);
                    for (SmbmsBill smbmsBill : forList) {
                        SmbmsProviderExample smbmsProviderExample = new SmbmsProviderExample();
                        smbmsProviderExample.createCriteria().andIdEqualTo(smbmsBill.getProviderid());
                        SmbmsProvider forObject = (SmbmsProvider) dao.findForObject("SmbmsProviderMapper.selectByExample", smbmsProviderExample);
                        Map<String, Object> mapVo = getMap(smbmsBill);
                        if (forObject!=null){
                            mapVo.put("proName",forObject.getProname());
                        }else {
                            mapVo.put("proName","");
                        }
                        listMap.add(mapVo);
                    }
                    redisTemplate.opsForValue().set(ConstantKey.BILLLIST,listMap);
                }
            }
            return listMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 默认无参查询全部
     * @return
     */
    public List<SmbmsProvider> providerList() {
        try {
            List<SmbmsProvider> forList = (List<SmbmsProvider>) redisTemplate.opsForValue().get(ConstantKey.PROVIDERLIST);
            if(forList==null||forList.size()==0){
                forList=       (List<SmbmsProvider>) dao.findForList("SmbmsProviderMapper.selectByExample", null);
                redisTemplate.opsForValue().set("providerList",forList);
            }
            return forList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 重写查询
     * @param queryProCode
     * @param queryProName
     * @return
     */
    public List<SmbmsProvider> providerList(String queryProCode,String queryProName) {
        try {
            List<SmbmsProvider> forList = null;
            SmbmsProviderExample smbmsProviderExample = new SmbmsProviderExample();
            SmbmsProviderExample.Criteria criteria = smbmsProviderExample.createCriteria();
            boolean b = null != queryProCode && !"".equals(queryProCode);
            if(b){
                criteria.andProcodeLike("%"+queryProCode+"%");
            }
            boolean b1 = null != queryProName && !"".equals(queryProName);
            if(b1){
                criteria.andPronameLike("%"+queryProName+"%");
            }
            if (b||b1){
                forList = (List<SmbmsProvider>) dao.findForList("SmbmsProviderMapper.selectByExample", smbmsProviderExample);
                redisTemplate.opsForValue().set(ConstantKey.PROVIDERLIST2,forList);
            }else {
                forList = (List<SmbmsProvider>) redisTemplate.opsForValue().get("providerList");
            }
            return forList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 订单删除
     * @param billid
     * @return
     */
    public int billdel(Long billid) {
        try {
            dao.delete("SmbmsBillMapper.selectByPrimaryKey", billid);
            redisTemplate.delete(ConstantKey.PROVIDERLIST);
            redisTemplate.delete(ConstantKey.PROVIDERLIST2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * 订单详情
     * @param id
     * @return
     */
    public Map<String, Object> billview(Long id) {
        try {
            SmbmsBill smbmsBill = (SmbmsBill) dao.findForObject("SmbmsBillMapper.selectByPrimaryKey", id);
            Map<String, Object> mapVo = getMap(smbmsBill);
            SmbmsProvider forObject = (SmbmsProvider) dao.findForObject("SmbmsProviderMapper.selectByPrimaryKey", smbmsBill.getProviderid());
            mapVo.put("proName",forObject.getProname());
            mapVo.put("productUnit",smbmsBill.getProductunit());
            mapVo.put("productCount",smbmsBill.getProductcount());
            return mapVo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通用获取（类似于Vo）
     * @param smbmsBill
     * @return
     */
    private Map<String,Object> getMap(SmbmsBill smbmsBill){
        Map<String ,Object> mapVo = new HashMap<>();
        mapVo.put("id",smbmsBill.getId());
        mapVo.put("billCode",smbmsBill.getBillcode());
        mapVo.put("productName",smbmsBill.getProductname());
        mapVo.put("totalPrice",smbmsBill.getTotalprice());
        mapVo.put("isPayment",smbmsBill.getIspayment());
        mapVo.put("creationDate",smbmsBill.getCreationdate());
        return mapVo;
    }

    /**
     * 下拉框 并插入Redis
     * @return
     */
    public List<Map<String ,Object>> billselect() {
        try {
            List<Map<String ,Object>> mapList = (List<Map<String, Object>>) redisTemplate.opsForValue().get("billselect");
            if(null==mapList||mapList.size()==0){
                List<SmbmsProvider> forList = (List<SmbmsProvider>) dao.findForList("SmbmsProviderMapper.selectByExample", new SmbmsProviderExample());
                for (SmbmsProvider smbmsProvider : forList) {
                    Map<String ,Object> map1 = new HashMap<>();
                    map1.put("id",smbmsProvider.getId());
                    map1.put("proName",smbmsProvider.getProname());
                    mapList.add(map1);
                }
                redisTemplate.opsForValue().set(ConstantKey.BILLSELECT,mapList);
            }
            return mapList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据Id查询订单详情
     * @param id
     * @return
     */
    public SmbmsBill billmodify(Long id) {
        try {
            SmbmsBill smbmsBill = (SmbmsBill) dao.findForObject("SmbmsBillMapper.selectByPrimaryKey", id);
            return smbmsBill;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 修改订单信息
     * @param smbmsBill
     */
    public void updateByBill(SmbmsBill smbmsBill) {
        try {
            dao.update("SmbmsBillMapper.updateByPrimaryKeySelective",smbmsBill);
            redisTemplate.delete(ConstantKey.BILLSELECT);
            redisTemplate.delete(ConstantKey.BILLLIST);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加订单
     * @param smbmsBill
     */
    public void dobilladd(SmbmsBill smbmsBill) {
        try {
            dao.save("SmbmsBillMapper.insert",smbmsBill);
            redisTemplate.delete(ConstantKey.BILLSELECT);
            redisTemplate.delete(ConstantKey.BILLLIST);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除厂家
     * @param id
     * @return
     * @throws Exception
     */
    public int delprovider(Long id) throws Exception {

        Integer delete = (Integer) dao.delete("SmbmsProviderMapper.deleteByPrimaryKey", id);
        redisTemplate.delete(ConstantKey.PROVIDERLIST);
        redisTemplate.delete(ConstantKey.PROVIDERLIST2);
        return delete;
    }

    /**
     * 添加厂家
     * @param smbmsProvider
     */
    public void provideraddsave(SmbmsProvider smbmsProvider) {
        try {
            dao.save("SmbmsProviderMapper.insert",smbmsProvider);
            redisTemplate.delete(ConstantKey.PROVIDERLIST);
            redisTemplate.delete(ConstantKey.PROVIDERLIST2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据Id查询厂家
     * @param id
     * @return
     */
    public SmbmsProvider providermodifyById(Long id) {
        try {
            SmbmsProvider forObject = (SmbmsProvider) dao.findForObject("SmbmsProviderMapper.selectByPrimaryKey", id);
            return forObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 修改厂家
     * @param smbmsProvider
     */
    public void providermodifysave(SmbmsProvider smbmsProvider) {
        try {
            dao.update("SmbmsProviderMapper.updateByPrimaryKeySelective",smbmsProvider);
            redisTemplate.delete(ConstantKey.PROVIDERLIST);
            redisTemplate.delete(ConstantKey.PROVIDERLIST2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据Id查询厂家具体信息
     * @param proid
     * @return
     */
    public SmbmsProvider proview(Long proid) {
        try {
            SmbmsProvider forObject = (SmbmsProvider) dao.findForObject("SmbmsProviderMapper.selectByPrimaryKey", proid);
            return forObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询全部权限
     * @return
     */
    public List<SmbmsRole> findByRole() {
        try {
            List<SmbmsRole> smbmsRole = (List<SmbmsRole>) redisTemplate.opsForValue().get(ConstantKey.ROLELIST);
            if(null==smbmsRole||smbmsRole.size()==0){
                smbmsRole= (List<SmbmsRole>) dao.findForList("SmbmsRoleMapper.selectByExample", null);
                redisTemplate.opsForValue().set(ConstantKey.ROLELIST,smbmsRole);
            }
            return smbmsRole;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 查询用户列表+分页+模糊
     * @param queryname
     * @param userRole
     * @param page
     * @return
     */
    public Map<Object, Object> findUserByCount(String queryname, String userRole, Page page) {
        SmbmsUserExample smbmsUserExample = new SmbmsUserExample();
        SmbmsUserExample.Criteria criteria = smbmsUserExample.createCriteria();
        if(null!=queryname&&!"".equals(queryname)){
            criteria.andUsernameLike("%"+queryname+"%");
        }
        if(null!=userRole&&!"".equals(userRole)&&!"0".equals(userRole)){
            criteria.andUserroleEqualTo(Integer.valueOf(userRole));
        }
        try {
            PageHelper.startPage(page.getCurrentpage(),5);
            List<SmbmsUser> forList = (List<SmbmsUser>) dao.findForList("SmbmsUserMapper.selectByExample", smbmsUserExample);
            PageInfo<SmbmsUser> pageInfo = new PageInfo<>(forList);
            List<SmbmsUser> list = pageInfo.getList();
            List<Map<String ,Object>> mapList = new ArrayList<>();
            for (SmbmsUser smbmsUser : list) {
                Map<String ,Object> mapVo = new HashMap<>();
                mapVo.put("id",smbmsUser.getId());
                mapVo.put("usercode",smbmsUser.getUsercode());
                mapVo.put("username",smbmsUser.getUsername());
                mapVo.put("gender",smbmsUser.getGender());
                mapVo.put("phone",smbmsUser.getPhone());
                int age = getAgeByBirth(smbmsUser.getBirthday());
                mapVo.put("age",age);
                mapVo.put("roleName",smbmsUser.getUsercode());
                mapList.add(mapVo);
            }
            Map<Object, Object> objectObjectMap = new HashMap<>();
            objectObjectMap.put("userList",mapList);
            objectObjectMap.put("totalCount",pageInfo.getTotal());
            objectObjectMap.put("currentPage",pageInfo.getPageNum());
            objectObjectMap.put("totalPage",pageInfo.getPages());
            return objectObjectMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据生日计算年龄
     * @param birthday
     * @return
     */
    private static int getAgeByBirth(Date birthday) {
        int age = 0;
        try {
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());
            Calendar birth = Calendar.getInstance();
            birth.setTime(birthday);
            if (birth.after(now)) {
                age = 0;
            } else {
                age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
                if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
                    age += 1;
                }
            }
            return age;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 删除用户
     * @param uid
     * @return
     */
    public Integer deleteUser(Long uid) {
        try {
            Integer delete = (Integer) dao.delete("SmbmsUserMapper.deleteByPrimaryKey", uid);
            return delete;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 查询用户信息
     * @param uid
     * @return
     */
    public Map<String, Object> getViewUser(Long uid) {
        try {
            SmbmsUser forObject = (SmbmsUser) dao.findForObject("SmbmsUserMapper.selectByPrimaryKey", uid);
            if(forObject!=null){
                Map<String ,Object> map1 = new HashMap<>();
                map1.put("userCode",forObject.getUsercode());
                map1.put("userName",forObject.getUsername());
                map1.put("gender",forObject.getGender());
                map1.put("birthday",forObject.getBirthday());
                map1.put("phone",forObject.getPhone());
                map1.put("address",forObject.getAddress());
                SmbmsRole forObject1 = (SmbmsRole) dao.findForObject("SmbmsRoleMapper.selectByPrimaryKey", forObject.getUserrole());
                map1.put("roleName",forObject1.getRolename());
                return map1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据Id查询用户
     * @param uid
     * @return
     */
    public SmbmsUser findByUserId(Long uid) {
        try {
            SmbmsUser forObject = (SmbmsUser) dao.findForObject("SmbmsUserMapper.selectByPrimaryKey", uid);
            return forObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 修改用户信息
     * @param smbmsUser
     */
    public void ModifyUser(SmbmsUser smbmsUser) {
        try {
            dao.update("SmbmsUserMapper.updateByPrimaryKeySelective",smbmsUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
