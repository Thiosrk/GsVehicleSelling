package com.gsvehicleselling.Controller;

import com.gsvehicleselling.Model.*;
import com.gsvehicleselling.Service.OrderService;
import com.gsvehicleselling.Service.UserService;
import com.gsvehicleselling.Service.VehicleService;
import com.gsvehicleselling.Util.VehicleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    VehicleService vehicleService;
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/order",method = RequestMethod.POST)
    public String order(HttpServletRequest request){
        String date = request.getParameter("date");
        double price = Double.valueOf(request.getParameter("price"));
        int goodsid = Integer.valueOf(request.getParameter("goodsid"));
        String goodstype = request.getParameter("goodstype");
        String goodsname = request.getParameter("name");
        String consigneename = request.getParameter("consigneename");
        String consigneetel = request.getParameter("consigneetel");
        String consigneeaddress = request.getParameter("consigneeaddress");
        long userID = (long)request.getSession().getAttribute("userID");
        int userid = new Long(userID).intValue();
        String type = "";
        switch (goodstype) {
            case "汽车": {
                type = "vehicle";
                break;
            }
            case "发动机": {
                type = "engine";
                break;
            }
            case "变速箱": {
                type = "gearbox";
                break;
            }
            case "轮胎": {
                type = "tyre";
                break;
            }
            case "零部件": {
                type = "components";
                break;
            }
        }
        Order order = new Order(date,userid,goodsid,type,goodsname,price,0,consigneename,consigneetel,consigneeaddress);
//        System.out.println(order.toString());
        orderService.saveOrder(order);
        return "redirect:/user/home";
    }
    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String home(Model model){
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
        return "userhome";
    }

    @RequestMapping(value = "/orderlist/{userid}",method = RequestMethod.GET)
    public String orderlist(@PathVariable("userid")int userid,Model model){

        List<Order> orders = orderService.getByuserID(userid);
        List<Order> vehicleorders = new ArrayList<>();
        List<Order> engineorders = new ArrayList<>();
        List<Order> gearboxorders = new ArrayList<>();
        List<Order> tyreorders = new ArrayList<>();
        List<Order> componentsorders = new ArrayList<>();
        for (Order order : orders){
            switch (order.getGoodstype()){
                case "vehicle":{
                    vehicleorders.add(order);
                    break;
                }
                case "engine":{
                    engineorders.add(order);
                    break;
                }
                case "gearbox":{
                    gearboxorders.add(order);
                    break;
                }
                case "tyre":{
                    tyreorders.add(order);
                    break;
                }
                case "components":{
                    componentsorders.add(order);
                    break;
                }
            }
        }
        model.addAttribute("vehicleorders",vehicleorders);
        model.addAttribute("engineorders",engineorders);
        model.addAttribute("gearboxorders",gearboxorders);
        model.addAttribute("tyreorders",tyreorders);
        model.addAttribute("componentsorders",componentsorders);
        return "orderlist";
    }

    @RequestMapping(value = "/order/{goodstype}/{goodsid}",method = RequestMethod.GET)
    public String order(@PathVariable("goodsid")int goodsid,@PathVariable("goodstype")String goodstype,Model model){
        switch (goodstype){
            case "vehicle":{
                Vehicle vehicle = vehicleService.getVehicle(goodsid);
                GoodsVO goodsVO = new GoodsVO(vehicle);
                model.addAttribute("goods",goodsVO);
                break;
            }
            case "engine":{
                Engine engine = vehicleService.getEngine(goodsid);
                GoodsVO goodsVO = new GoodsVO(engine);
                model.addAttribute("goods",goodsVO);
                break;
            }
            case "gearbox":{
                Gearbox gearbox = vehicleService.getGearbox(goodsid);
                GoodsVO goodsVO = new GoodsVO(gearbox);
                model.addAttribute("goods",goodsVO);
                break;
            }
            case "tyre":{
                Tyre tyre = vehicleService.getTyre(goodsid);
                GoodsVO goodsVO = new GoodsVO(tyre);
                model.addAttribute("goods",goodsVO);
                break;
            }
            case "components":{
                Components components = vehicleService.getComponents(goodsid);
                GoodsVO goodsVO = new GoodsVO(components);
                model.addAttribute("goods",goodsVO);
                break;
            }
        }

        return "order";
    }

    @RequestMapping(value = "/pay/{orderid}/{userid}",method = RequestMethod.GET)
    public String pay(@PathVariable("orderid")int orderid,@PathVariable("userid")int userid){
        User user = userService.getUser(userid);
        Order order = orderService.getOrder(orderid);
        double price = order.getPrice();
        double money = user.getMoney();
        if (money >= price){
            user.setMoney(money-price);
            userService.modifyUser(user);
            order.setIspay(1);
            orderService.saveOrder(order);
            System.out.println("付款成功！");
            return "redirect:/user/orderlist/" + userid;
        }else {
            System.out.println("付款失败");
            return "redirect:/user/orderlist/" + userid;
        }
    }

    @RequestMapping(value = "/modify/{userid}",method = RequestMethod.POST)
    public String modify(@PathVariable("userid")int userid,HttpServletRequest request){
        String nickname = request.getParameter("nickname");
        String tel = request.getParameter("tel");
        String birthday = request.getParameter("birthday");
        String password = request.getParameter("password");

        User user = userService.getUser(userid);
        user.setNickname(nickname);
        user.setBirthday(birthday);
        user.setPassword(password);
        user.setTel(tel);
        userService.modifyUser(user);

        return "redirect:/user/home";
    }

    @RequestMapping(value = "/info/{userid}",method = RequestMethod.GET)
    public String info(@PathVariable("userid")int userid,Model model){
        User user = userService.getUser(userid);
        model.addAttribute("user",user);
        return "userinfo";
    }

    @RequestMapping(value = "/getbyEngine/{engineid}",method = RequestMethod.GET)
    public String getByEngine(@PathVariable("engineid")int engineid, Model model){
        List<Vehicle> vehicles = vehicleService.getVehicleByEngine(engineid);
        List<Tyre> tyres = vehicleService.getAllTyre();
        List<Engine> engines = vehicleService.getAllEngine();
        List<Gearbox> gearboxes = vehicleService.getAllGearbox();
        List<DrivingModel> drivingModels = vehicleService.getAllDrivingModel();
        List<VehicleVO> vehicleVOS = new ArrayList<>();
        for (Vehicle vehicle : vehicles){
            int tyreid = vehicle.getTyreid();
            int gearboxid = vehicle.getGearboxid();
//            int engineid = vehicle.getEngineid();
            int drivingmodelid = vehicle.getDrivingmodeid();
            VehicleVO vehicleVO = new VehicleVO(vehicle);
            vehicleVO.setEngine(VehicleUtil.findEngine(engineid,engines));
            vehicleVO.setGearbox(VehicleUtil.findGearbox(gearboxid,gearboxes));
            vehicleVO.setTyre(VehicleUtil.findTyre(tyreid,tyres));
            vehicleVO.setDrivingmode(VehicleUtil.findDrivingModel(drivingmodelid,drivingModels));
            vehicleVOS.add(vehicleVO);
        }
        model.addAttribute("vehicles",vehicleVOS);

        return "userhome";
    }

    @RequestMapping(value = "/getbyGearbox/{gearboxid}",method = RequestMethod.GET)
    public String getByGearbox(@PathVariable("gearboxid")int gearboxid,Model model){
        List<Vehicle> vehicles = vehicleService.getVehicleByGearbox(gearboxid);
        List<Tyre> tyres = vehicleService.getAllTyre();
        List<Engine> engines = vehicleService.getAllEngine();
        List<Gearbox> gearboxes = vehicleService.getAllGearbox();
        List<DrivingModel> drivingModels = vehicleService.getAllDrivingModel();
        List<VehicleVO> vehicleVOS = new ArrayList<>();
        for (Vehicle vehicle : vehicles){
            int tyreid = vehicle.getTyreid();
//            int gearboxid = vehicle.getGearboxid();
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

        return "userhome";
    }

    @RequestMapping(value = "/getbyDrivingmodel/{drivingmodelid}",method = RequestMethod.GET)
    public String getByDrivingmodel(@PathVariable("drivingmodelid")int drivingmodelid,Model model){
        List<Vehicle> vehicles = vehicleService.getVehicleByDrivingmodel(drivingmodelid);
        List<Tyre> tyres = vehicleService.getAllTyre();
        List<Engine> engines = vehicleService.getAllEngine();
        List<Gearbox> gearboxes = vehicleService.getAllGearbox();
        List<DrivingModel> drivingModels = vehicleService.getAllDrivingModel();
        List<VehicleVO> vehicleVOS = new ArrayList<>();
        for (Vehicle vehicle : vehicles){
            int tyreid = vehicle.getTyreid();
            int gearboxid = vehicle.getGearboxid();
            int engineid = vehicle.getEngineid();
//            int drivingmodelid = vehicle.getDrivingmodeid();
            VehicleVO vehicleVO = new VehicleVO(vehicle);
            vehicleVO.setEngine(VehicleUtil.findEngine(engineid,engines));
            vehicleVO.setGearbox(VehicleUtil.findGearbox(gearboxid,gearboxes));
            vehicleVO.setTyre(VehicleUtil.findTyre(tyreid,tyres));
            vehicleVO.setDrivingmode(VehicleUtil.findDrivingModel(drivingmodelid,drivingModels));
            vehicleVOS.add(vehicleVO);
        }
        model.addAttribute("vehicles",vehicleVOS);

        return "userhome";
    }

    @RequestMapping(value = "/getbyTyre/{tyreid}",method = RequestMethod.GET)
    public String getByTyre(@PathVariable("tyreid")int tyreid,Model model){
        List<Vehicle> vehicles = vehicleService.getVehicleByTyre(tyreid);
        List<Tyre> tyres = vehicleService.getAllTyre();
        List<Engine> engines = vehicleService.getAllEngine();
        List<Gearbox> gearboxes = vehicleService.getAllGearbox();
        List<DrivingModel> drivingModels = vehicleService.getAllDrivingModel();
        List<VehicleVO> vehicleVOS = new ArrayList<>();
        for (Vehicle vehicle : vehicles){
//            int tyreid = vehicle.getTyreid();
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

        return "userhome";
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
        return "usercomponents";
    }

    @RequestMapping(value = "/component/{componentid}",method = RequestMethod.GET)
    public String component(@PathVariable("componentid")int componentid,Model model){
        Components components = vehicleService.getComponents(componentid);
        model.addAttribute("components",components);
        return  "component";
    }

    @RequestMapping(value = "/vehicle",method = RequestMethod.GET)
    public String vehicle(){
        return  "uservehicle";
    }


}
