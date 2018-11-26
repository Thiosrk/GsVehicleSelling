//package com.gsvehicleselling;
//
//
//import com.gsvehicleselling.Model.Vehicle;
//import com.gsvehicleselling.Repository.VehicleRepository;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//
//import java.util.Random;
//
//@EnableJpaRepositories(basePackages = "com.gsvehicleselling.Repository")
//@EntityScan(basePackages = "com.gsvehicleselling.Model")
//@SpringBootApplication
//public class Carinfospider implements CommandLineRunner {
//
//    public static final String url = "http://brand.vw.com.cn/carmodels/models.php";
//
//    @Autowired
//    VehicleRepository vehicleRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//
//
////        Vehicle vehicle = new Vehicle();
////        vehicle.setModel("a");
////        vehicle.setImage("a");
////        vehicle.setDrivingmode(DrivingModel.FF.toString());
////        vehicle.setEngineid(0);
////        vehicle.setGearboxid(0);
////        vehicle.setPrice(0.0);
////        vehicle.setTyreid(0);
////        System.out.println("getinfo");
//        Vehicle vehicle = vehicleRepository.getOne(2);
//        System.out.println("finish");
//        System.out.println(vehicle.getId());
//        System.out.println(vehicle.getDrivingmode());
//        System.out.println(vehicle.getEngineid());
//        System.out.println(vehicle.getGearboxid());
//        System.out.println(vehicle.getImage());
//        System.out.println(vehicle.getModel());
//        System.out.println(vehicle.getTyreid());
//        System.out.println(vehicle.getPrice());
//
//
////        HashMap<String,String> carimg = new HashMap<>();
////
////        Carinfospider spider = new Carinfospider();
////
////        WebDriver webDriver = spider.getDriver(url);
////
////        WebElement index = webDriver.findElement(By.xpath("//*[@id=\"main-box\"]/div[1]"));
////
////        List<WebElement> cars = index.findElements(By.tagName("li"));
////
////        int size = 0;
////
////        for (int i=0;i<cars.size();++i){
////            String name = cars.get(i).findElement(By.tagName("p")).getText();
////            if (carimg.containsKey(name)){
////                continue;
////            }else {
////                System.out.println(++size);
////                System.out.println(name);
////                String path = cars.get(i).findElement(By.tagName("img")).getAttribute("src");
////                String[] a = path.split("/");
////                System.out.println(a[a.length-1]);
////                carimg.put(name,a[a.length-1]);
////                Vehicle vehicle = new Vehicle();
////                vehicle.setModel(name);
////                vehicle.setImage(a[a.length-1]);
////                vehicleRepository.save(vehicle);
////            }
////
////        }
//    }
//
//    public static void main(String[] args) {
//
//        SpringApplication.run(Carinfospider.class,args);
//
//    }
//
//    public WebDriver getDriver(String url) {
//
//        try {
//
//// 等待数据加载的时间
//
//// 为了防止服务器封锁，这里的时间要模拟人的行为，随机且不能太短
//
//            long waitLoadBaseTime = 3000;
//
////            int waitLoadRandomTime = 2000;
//
//            Random random = new Random(System.currentTimeMillis());
//
//// 设置 chrome 的路径,直接放在chrome的安装路径即可
//
//            String chrome = "C:\\ChromeDriver\\chromedriver.exe";
//
////            String chrome = "/chromedriver.exe";
//
//            System.setProperty("webdriver.chrome.driver", chrome);
//
//            ChromeOptions options = new ChromeOptions();
//
//// 通过配置参数禁止data;的出现
//
//            options.addArguments(
//
//                    "--user-data-dir=C:/Users/Administrator/AppData/Local/Google/Chrome/User Data/Default");
//
//// 通过配置参数删除“您使用的是不受支持的命令行标记：--ignore-certificate-errors。稳定性和安全性会有所下降。”提示
//
//            options.addArguments("--start-maximized", "allow-running-insecure-content", "--test-type");
//
//            options.addArguments("--profile-directory=Default");
//
//// userdata 设置使用chrome的默认参数
//
//            options.addArguments("--user-data-dir=C:/Temp/ChromeProfile");
//
////也可以只用自己配置的chrom 设置地址：如下
//
////	options.addArguments("--user-data-dir=C:/Users/ZHL/AppData/Local/Google/Chrome/User Data");
//
//// 创建一个 Chrome 的浏览器实例
//
//            WebDriver driver = new ChromeDriver(options);
//
//// 让浏览器访问微博主页
//
//            driver.get(url);
//
//// 等待页面动态加载完毕
//
//            Thread.sleep(waitLoadBaseTime);
//
//            return driver;
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//            return null;
//
//        }
//
//    }
//
//
//}
