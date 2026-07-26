package hotelmanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("hotelmanage.mapper")
public class HotelManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(HotelManageApplication.class, args);
    }
}