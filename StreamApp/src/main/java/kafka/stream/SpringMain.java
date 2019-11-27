package kafka.stream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringMain {
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(SpringMain.class, args);
		StreamOperation streamoperation=new StreamOperation();
		streamoperation.getStream();
	}

}
