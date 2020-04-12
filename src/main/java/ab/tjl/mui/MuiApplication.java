package ab.tjl.mui;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "ab.tjl.mui.mapper")
public class MuiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MuiApplication.class, args);
    }

}
