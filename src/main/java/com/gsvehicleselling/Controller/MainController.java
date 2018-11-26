package com.gsvehicleselling.Controller;

import com.gsvehicleselling.Model.*;
import com.gsvehicleselling.Service.UserService;
import com.gsvehicleselling.Service.VehicleService;
import com.gsvehicleselling.Util.VehicleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    UserService userService;
    @Autowired
    VehicleService vehicleService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String home(Model model,HttpServletRequest request){
        List<Vehicle> vehicles = vehicleService.getAllVehicle();
        List<Tyre> tyres = vehicleService.getAllTyre();
        List<Engine> engines = vehicleService.getAllEngine();
        List<Gearbox> gearboxes = vehicleService.getAllGearbox();
        List<DrivingModel> drivingModels = vehicleService.getAllDrivingModel();
        List<VehicleVO> vehicleVOS = new ArrayList<>();
        for (Vehicle vehicle : vehicles){
            int tyreid = vehicle.getTyreid();
            int gearboxid = vehicle.getGearboxid();
            int engineid = vehicle.getEngineid();
            int drivingmodelid = vehicle.getDrivingmodeid();
            VehicleVO vehicleVO = new VehicleVO(vehicle);
            vehicleVO.setEngine(VehicleUtil.findEngine(engineid,engines));
            vehicleVO.setGearbox(VehicleUtil.findGearbox(gearboxid,gearboxes));
            vehicleVO.setTyre(VehicleUtil.findTyre(tyreid,tyres));
            vehicleVO.setDrivingmode(VehicleUtil.findDrivingModel(drivingmodelid,drivingModels));
            vehicleVOS.add(vehicleVO);
        }
        model.addAttribute("vehicles",vehicleVOS);
        return "home";
    }

    @RequestMapping(value = "/components",method = RequestMethod.GET)
    public String getComponents(Model model){
        List<Tyre> tyres = vehicleService.getAllTyre();
        List<Engine> engines = vehicleService.getAllEngine();
        List<Gearbox> gearboxes = vehicleService.getAllGearbox();
        List<DrivingModel> drivingModels = vehicleService.getAllDrivingModel();
        model.addAttribute("tyres",tyres);
        model.addAttribute("engines",engines);
        model.addAttribute("gearboxes",gearboxes);
        model.addAttribute("drivingModels",drivingModels);
        return "components";
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(){
        return "register";
    }

    @RequestMapping(value = "/adduser",method = RequestMethod.POST)
    public String register(Model model,HttpServletRequest request){
//        int userID = Integer.valueOf(request.getParameter("userID"));
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String nickname = request.getParameter("nickname");
        String tel = request.getParameter("tel");
        String birthday = request.getParameter("birthday");
        if (password.equals(password2)){
            User user = new User(nickname,tel,password,birthday,0);
            try {
                userService.saveUser(user);
            }catch (Exception e){
                return "register";
            }
            return "login";
        }else {
//            System.out.println();
            return "register";
        }
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/validate",method = RequestMethod.POST)
    public void validate(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println(request.getContentType());
        String nickname = request.getParameter("nickname");
        String password = request.getParameter("password");
//        String nickname = params.get("nickname");
//        String password = params.get("password");
        System.out.println("nickname is : "+nickname);
        System.out.println("password is : "+password);
        User user = userService.getUser(nickname);
        HttpSession session = request.getSession();
        int flag = 0 ;//默认0不成功，1管理员，2用户
        if (user!=null && password.equals(user.getPassword())){
            session.setAttribute("userID",user.getId());
            session.setAttribute("nickName",user.getNickname());
//            model.addAttribute("user",user);
            if (user.getAuthority() == 1){
//                return new ModelAndView("redirect:/admin/home");
                flag = 1;
                System.out.println("管理员");
            }else if(user.getAuthority() == 0) {
//                return new ModelAndView("redirect:/user/
                flag = 2;
                System.out.println("用户");
            }
            else {
                flag = 0;
                System.out.println("有问题");
//                return new ModelAndView("redirect:/login");
            }
        }else {
//            return new ModelAndView("redirect:/login",map);
            flag = 0;
            System.out.println("密码错误");
        }
        System.out.println("flag is : "+flag);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(flag);//返回登录信息
        out.flush();
        out.close();

    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("userID");
        request.getSession().removeAttribute("nickName");
        return "redirect:/";
    }

}
