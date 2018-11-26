package com.gsvehicleselling.Controller;

import com.gsvehicleselling.Model.*;
import com.gsvehicleselling.Service.OrderService;
import com.gsvehicleselling.Service.UserService;
import com.gsvehicleselling.Service.VehicleService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;
    @Autowired
    VehicleService vehicleService;

    @RequestMapping("/home")
    public String admin(Model model){
        List<User> users = userService.getAllUser();
        model.addAttribute("users",users);
        return "admin";
    }

    @RequestMapping(value = "/modify/{userid}",method = RequestMethod.GET)
    public String modifyUser(@PathVariable("userid")int userid, Model model){
        User user = userService.getUser(userid);
        model.addAttribute("user",user);
        return "modifyuser";
    }

    @RequestMapping(value = "/deleteuser/{userid}",method = RequestMethod.GET)
    public String deleteUser(@PathVariable("userid")int userid){
        userService.delete(userid);
        return "redirect:/admin/home";
    }

    @RequestMapping(value = "/modify/{userid}",method = RequestMethod.POST)
    public String modify(@PathVariable("userid")int userid,HttpServletRequest request){
        String nickname = request.getParameter("nickname");
        String tel = request.getParameter("tel");
        String birthday = request.getParameter("birthday");
        String password = request.getParameter("password");
        double money = Double.valueOf(request.getParameter("money"));

        User user = userService.getUser(userid);
        user.setNickname(nickname);
        user.setBirthday(birthday);
        user.setPassword(password);
        user.setTel(tel);
        user.setMoney(money);
        userService.modifyUser(user);

        return "redirect:/admin/home";
    }

    @RequestMapping(value = "/orderlist",method = RequestMethod.GET)
    public String orderlist(Model model){

        List<Order> orders = orderService.getAll();
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
        return "adminorder";
    }

    @RequestMapping(value = "/vehiclelist",method = RequestMethod.GET)
    public String vehiclelist(Model model){
        List<Vehicle> vehicles = vehicleService.getAllVehicle();
        model.addAttribute("vehicles",vehicles);
        return "vehiclelist";
    }

    @RequestMapping(value = "/drivingmodellist",method = RequestMethod.GET)
    public String drivingmodellist(Model model){
        List<DrivingModel> drivingModels = vehicleService.getAllDrivingModel();
        model.addAttribute("drivingModels",drivingModels);
        return "drivingmodellist";
    }

    @RequestMapping(value = "/tyrelist",method =  RequestMethod.GET)
    public String tyrelist(Model model){
        List<Tyre> tyres = vehicleService.getAllTyre();
        model.addAttribute("tyres",tyres);
        return "tyrelist";
    }

    @RequestMapping(value = "/enginelist",method = RequestMethod.GET)
    public String enginelist(Model model){
        List<Engine> engines = vehicleService.getAllEngine();
        model.addAttribute("engines",engines);
        return "enginelist";
    }

    @RequestMapping(value = "/gearboxlist",method = RequestMethod.GET)
    public String gearboxlist(Model model){
        List<Gearbox> gearboxes = vehicleService.getAllGearbox();
        model.addAttribute("gearboxes",gearboxes);
        return "gearboxlist";
    }

    @RequestMapping(value = "/addvehicle",method = RequestMethod.GET)
    public String addVehicle(){
        System.out.println(System.getProperty("user.dir")+"\\image\\img\\Vehicle");
        return "addvehicle";
    }

    @RequestMapping(value = "/addvehicle",method = RequestMethod.POST)
    public String addVehicle(HttpServletRequest request) throws Exception {
//        String filePath = "F:\\IDEA_workspace\\GsVehicleSelling\\src\\main\\resources\\static\\img\\Vehicle";
        String filePath = System.getProperty("user.dir")+"\\image\\img\\Vehicle";
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        MultipartFile file = multipartRequest.getFile("image");
        String imagename = file.getOriginalFilename();
        uploadFile(file.getBytes(),filePath,imagename);
        String model = request.getParameter("type");
        String engineid = request.getParameter("engineid");
        String drivingmodeid = request.getParameter("drivingmodeid");
        String tyreid = request.getParameter("tyreid");
        String gearboxid = request.getParameter("gearboxid");
        String price = request.getParameter("price");
        Vehicle vehicle = new Vehicle();
        vehicle.setModel(model);
        vehicle.setTyreid(Integer.valueOf(tyreid));
        vehicle.setGearboxid(Integer.valueOf(gearboxid));
        vehicle.setEngineid(Integer.valueOf(engineid));
        vehicle.setDrivingmodeid(Integer.valueOf(drivingmodeid));
        vehicle.setPrice(Double.valueOf(price));
        vehicle.setImage(imagename);

        vehicleService.saveVehicle(vehicle);

        return "redirect:/admin/vehiclelist";
    }

    @RequestMapping(value = "/addengine",method = RequestMethod.GET)
    public String addEngine(){
        return "addengine";
    }

    @RequestMapping(value = "/addengine",method = RequestMethod.POST)
    public String addEngine(HttpServletRequest request) throws Exception {
        String filePath = "F:\\IDEA_workspace\\GsVehicleSelling\\src\\main\\resources\\static\\img\\Engine";
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        MultipartFile file = multipartRequest.getFile("image");
        String imagename = file.getOriginalFilename();
        uploadFile(file.getBytes(),filePath,imagename);
        String type = request.getParameter("type");
        String displacement = request.getParameter("displacement");
        String intake = request.getParameter("intake");
        String material = request.getParameter("material");
        String price = request.getParameter("price");

        Engine engine = new Engine();
        engine.setDisplacement(Double.valueOf(displacement));
        engine.setIntake(intake);
        engine.setMaterial(material);
        engine.setType(type);
        engine.setImage(imagename);
        engine.setPrice(Double.valueOf(price));

        vehicleService.saveEngine(engine);
        return "redirect:/admin/enginelist";
    }

    @RequestMapping(value = "/addgearbox",method = RequestMethod.GET)
    public String addGearbox(){
        return "addgearbox";
    }

    @RequestMapping(value = "/addgearbox",method = RequestMethod.POST)
    public String addGearbox(HttpServletRequest request) throws Exception {
        String filePath = "F:\\IDEA_workspace\\GsVehicleSelling\\src\\main\\resources\\static\\img\\Gearbox";
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        MultipartFile file = multipartRequest.getFile("image");
        String imagename = file.getOriginalFilename();
        uploadFile(file.getBytes(),filePath,imagename);

        String type = request.getParameter("type");
        String price = request.getParameter("price");
        Gearbox gearbox = new Gearbox();
        gearbox.setType(type);
        gearbox.setImage(imagename);
        gearbox.setPrice(Double.valueOf(price));

        vehicleService.saveGearbox(gearbox);
        return "redirect:/admin/gearboxlist";
    }

    @RequestMapping(value = "/addtyre",method = RequestMethod.GET)
    public String addtyreid(){
        return "addtyre";
    }

    @RequestMapping(value = "/addtyre",method = RequestMethod.POST)
    public String addtyreid(HttpServletRequest request) throws Exception {
        String filePath = "F:\\IDEA_workspace\\GsVehicleSelling\\src\\main\\resources\\static\\img\\Tyre";
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        MultipartFile file = multipartRequest.getFile("image");
        String imagename = file.getOriginalFilename();
        uploadFile(file.getBytes(),filePath,imagename);

        String brand = request.getParameter("brand");
        String price = request.getParameter("price");

        Tyre tyre = new Tyre();
        tyre.setBrand(brand);
        tyre.setImage(imagename);
        tyre.setPrice(Double.valueOf(price));

        vehicleService.saveTyre(tyre);
        return "redirect:/admin/tyrelist";
    }

    @RequestMapping(value = "/adddrivingmodel",method = RequestMethod.GET)
    public String adddrivingmodel(){
        return "adddrivingmodel";
    }

    @RequestMapping(value = "/adddrivingmodel",method = RequestMethod.POST)
    public String adddrivingmodel(HttpServletRequest request) throws Exception {
        String filePath = "F:\\IDEA_workspace\\GsVehicleSelling\\src\\main\\resources\\static\\img\\Drivingmodel";
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        MultipartFile file = multipartRequest.getFile("image");
        String imagename = file.getOriginalFilename();
        uploadFile(file.getBytes(),filePath,imagename);

        String type = request.getParameter("type");

        DrivingModel drivingModel = new DrivingModel();
        drivingModel.setType(type);
        drivingModel.setImage(imagename);

        vehicleService.saveDrivingModel(drivingModel);
        return "redirect:/admin/drivingmodellist";
    }

    @RequestMapping(value = "/deletevehicle/{vehicleid}",method = RequestMethod.GET)
    public String deleteVehicle(@PathVariable("vehicleid")int vehicleid){
        vehicleService.deleteVehicle(vehicleid);
        return "redirect:/admin/vehiclelist";
    }
    @RequestMapping(value = "/deletetyre/{tyreid}",method = RequestMethod.GET)
    public String deleteTyre(@PathVariable("tyreid")int tyreid){
        vehicleService.deleteTyre(tyreid);
        return "redirect:/admin/tyrelist";
    }
    @RequestMapping(value = "/deletegearbox/{gearboxid}",method = RequestMethod.GET)
    public String deleteGearbox(@PathVariable("gearboxid")int gearboxid){
        vehicleService.deleteGearbox(gearboxid);
        return "redirect:/admin/gearboxlist";
    }
    @RequestMapping(value = "/deleteengine/{engineid}",method = RequestMethod.GET)
    public String deleteEngine(@PathVariable("engineid")int engineid){
        vehicleService.deleteEngine(engineid);
        return "redirect:/admin/enginelist";
    }
    @RequestMapping(value = "/deletedrivingmodel/{drivingmodelid}",method = RequestMethod.GET)
    public String deleteDrivingmodel(@PathVariable("drivingmodelid")int drivingmodelid){
        vehicleService.deleteDrivingmodel(drivingmodelid);
        return "redirect:/admin/drivingmodellist";
    }



    public void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+"/"+fileName);
        out.write(file);
        out.flush();
        out.close();
    }

}
