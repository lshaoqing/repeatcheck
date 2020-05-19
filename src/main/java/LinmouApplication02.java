import com.fu.linmou.LinmouApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author linMou
 * @Description:
 * @Date: 2020/5/19 11:33
 * @Version: 1.0
 */
@SpringBootApplication
public class LinmouApplication02 {

    public static void main(String[] args) {
        System.setProperty("server.port", "0");
        SpringApplication.run(LinmouApplication.class, args);
    }

}
