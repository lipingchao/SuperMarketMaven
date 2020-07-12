package com.lpc.controller;

import com.lpc.pojo.*;
import com.lpc.service.SmbmsUserService;
import com.lpc.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @User lpc/李金超
 * @ClassName billController  类名
 * @Author AUSUA  作者
 * @Date 2020/7/10 19:02  时间
 * @Version 1.0 版本
 */
@Controller
@RequestMapping("/sys")
public class SysController {

    @Autowired
    private SysService sysService;

    @Autowired
    private SmbmsUserService smbmsUserService;

    /**
     *订单列表
     * @param productName  模糊查询的名称
     * @param providerId
     * @param isPayment
     * @param request
     * @return
     */
    @GetMapping("/bill")
    public String billList(String productName,String providerId,String isPayment,HttpServletRequest request){
        List<Map<String ,Object>> list = sysService.billList(productName,providerId,isPayment);
        List<SmbmsProvider> listsb = sysService.providerList();
        request.setAttribute("billList",list);
        request.setAttribute("providerList",listsb);
        request.setAttribute("providerId",providerId);
        request.setAttribute("isPayment",isPayment);
        return "bill/billlist";
    }

    @GetMapping("/billdel")
    @ResponseBody
    public Map<String ,Object> billdel(Long billid){
        System.out.println(billid);
        Map<String,Object> objectMap = new HashMap<>();
        int billdel = 0;
        try {
            billdel = sysService.billdel(billid);
            if(billdel>0){
                objectMap.put("delResult","true");
                return objectMap;
            }else {
                objectMap.put("delResult","false");
                return objectMap;
            }
        } catch (Exception e) {
            e.printStackTrace();
            objectMap.put("delResult","notexist");
            return objectMap;
        }
    }

    /**
     * 查看
     * @param id 订单Id
     * @return
     */
    @GetMapping("/billview/{id}")
    public String billview(@PathVariable Long id,HttpServletRequest request){
        Map<String ,Object> map1 =  sysService.billview(id);
        request.setAttribute("bill",map1);
        return "bill/billview";
    }
    @GetMapping("/billmodify/{id}")
    public String billmodify(@PathVariable Long id,HttpServletRequest request){
        SmbmsBill smbmsBill = sysService.billmodify(id);
        request.setAttribute("bill",smbmsBill);
        return "bill/billmodify";
    }

    @GetMapping("/billselect")
    @ResponseBody
    public List<Map<String ,Object>>  billselect(){
        List<Map<String ,Object>> billselect = sysService.billselect();
        return billselect;
    }
    @PostMapping("/dobillmodify")
    public String dobillmodify(SmbmsBill smbmsBill,HttpServletRequest request){
        HttpSession session = request.getSession();
        SmbmsUser userOnLogin  = (SmbmsUser) session.getAttribute("userOnLogin");
        smbmsBill.setModifyby(Long.valueOf(userOnLogin.getId()));
        smbmsBill.setModifydate(new Date());
        sysService.updateByBill(smbmsBill);
        return "redirect:/sys/bill";
    }
    @GetMapping("/billadd")
    public String billadd(){
        return "bill/billadd";
    }

    @PostMapping("/dobilladd")
    public String dobilladd(SmbmsBill smbmsBill,HttpServletRequest request){
        HttpSession session = request.getSession();
        SmbmsUser userOnLogin = (SmbmsUser) session.getAttribute("userOnLogin");
        smbmsBill.setCreatedby(Long.valueOf(userOnLogin.getId()));
        smbmsBill.setCreationdate(new Date());
        sysService.dobilladd(smbmsBill);
        return"redirect:/sys/bill";
    }

    /*供货商*/
    @GetMapping("/provider")
    public String provider(String queryProCode,String queryProName,HttpServletRequest request){
        List<SmbmsProvider> smbmsProviders = sysService.providerList(queryProCode,queryProName);
        request.setAttribute("queryProCode",queryProCode);
        request.setAttribute("queryProName",queryProName);
        request.setAttribute("proList",smbmsProviders);
        return "pro/providerlist";
    }
    @GetMapping("/delprovider")
    @ResponseBody
    public Map delprovider(Long proid){
        Map map = new HashMap();
        try {
            int delprovider = sysService.delprovider(proid);
            if(delprovider>0){
                map.put("delResult","true");
                return map;
            }else {
                map.put("delResult","false");
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("delResult","接口异常");
        }
        return map;
    }

    @GetMapping("/provideradd")
    public String provideradd(){
        return "pro/provideradd";
    }

    @PostMapping("/provideraddsave")
    public String provideraddsave(SmbmsProvider smbmsProvider){
        smbmsProvider.setCreationdate(new Date());
        sysService.provideraddsave(smbmsProvider);
        return "redirect:/sys/provider";
    }
    @GetMapping("/providermodify")
    public String providermodify(Long proid,HttpServletRequest request){
        SmbmsProvider smbmsProvider = sysService.providermodifyById(proid);
        request.setAttribute("provider",smbmsProvider);
        return "pro/providermodify";
    }
    @PostMapping("/providermodifysave")
    public String providermodifysave(SmbmsProvider smbmsProvider,HttpServletRequest request){
        HttpSession session = request.getSession();
        SmbmsUser userOnLogin = (SmbmsUser) session.getAttribute("userOnLogin");
        smbmsProvider.setModifyby(Long.valueOf(userOnLogin.getId()));
        smbmsProvider.setModifydate(new Date());
        sysService.providermodifysave(smbmsProvider);
        return "redirect:/sys/provider";
    }
    //proview?proid=16
    @GetMapping("/proview")
    public String proview(Long proid,HttpServletRequest request){
        SmbmsProvider smbmsProvider = sysService.proview(proid);
        request.setAttribute("provider",smbmsProvider);
        return "pro/providerview";
    }

    @GetMapping("/user")
    public String getAllUser(HttpServletRequest request, Model model,String queryname,String queryUserRole,@RequestParam(defaultValue = "1") Integer currentPage){
        HttpSession session = request.getSession();
        SmbmsUser userOnLogin = (SmbmsUser) session.getAttribute("userOnLogin");
        if(userOnLogin.getUserrole()==1){
            Page page=new Page();
            page.setCurrentpage(currentPage);
            request.setAttribute("currentPage",currentPage);
            Map<Object, Object> userByCount = sysService.findUserByCount(queryname, queryUserRole, page);
            request.setAttribute("totalPage",userByCount.get("totalPage"));
            request.setAttribute("totalCount", userByCount.get("totalCount"));
            List<SmbmsRole> roleList=sysService.findByRole();
            model.addAttribute("roleList", roleList);
            model.addAttribute("userList", (List<SmbmsUser>)userByCount.get("userList"));
            model.addAttribute("queryUserRole", queryUserRole);
            model.addAttribute("queryUserName", queryname);
            return "/user/userlist";
        }
        return "redirect:/401.jsp";
    }

    @GetMapping("/deleteUser")
    @ResponseBody
    public Map<String ,Object> deleteUser(Long uid){
        Map<String ,Object> map1 = null;
        try {
            map1 = new HashMap<>();
            Integer i = sysService.deleteUser(uid);
            if(i>0){
                map1.put("delResult","true");
                return map1;
            }else {
                map1.put("delResult","false");
                return map1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map1;
    }


    @GetMapping("viewUser")
    public String viewUser(Long uid,Model model){
        Map<String, Object> viewUser = sysService.getViewUser(uid);
        model.addAttribute("user",viewUser);
        return "user/userview";
    }
    @GetMapping("/modifyUser")
    public String modifyUser(Long uid,Model model){
        SmbmsUser smbmsUser = sysService.findByUserId(uid);
        model.addAttribute("user",smbmsUser);
        return "user/usermodify";
    }

    @GetMapping("/getrolelist")
    @ResponseBody
    public List<SmbmsRole> findAllRole(){
        return sysService.findByRole();
    }

    @PostMapping("/modifyusersave")
    public String modifyusersave(SmbmsUser smbmsUser){
        sysService.ModifyUser(smbmsUser);
        return "redirect:/sys/user";
    }
    @GetMapping("/useradd")
    public String useradd(){

        return "user/useradd";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.removeAttribute("userOnLogin");
        return "redirect:/login";
    }
    @GetMapping("/pwdmodify")
    public String pwdmodify(){
        return "user/pwdmodify";
    }
    @PostMapping("/savepwdmodify")
    public String savepwdmodify(String rnewpassword,HttpSession session){
        SmbmsUser userOnLogin = (SmbmsUser) session.getAttribute("userOnLogin");
        userOnLogin.setUserpassword(rnewpassword);
        smbmsUserService.savepwdmodify(userOnLogin);
        return "redirect:/login";
    }
}
