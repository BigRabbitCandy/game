package cn.bluewhale.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

// 扫描指定包下面的mapper接口
@MapperScan("cn.bluewhale.core.dao")

// 该 @SpringBootApplication 注解等价于以默认属性使用:
// @Configuration
// @EnableAutoConfiguration
// @ComponentScan
@SpringBootApplication
@EnableCaching // 开启缓存功能
//@PropertySource(value="classpath:application.properties")

public class CoreApplication extends SpringBootServletInitializer implements CommandLineRunner {

	//部署web项目是使用extends SpringBootServletInitializer

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(this.getClass());
	}

	public static void main(String[] args){SpringApplication.run(CoreApplication.class, args);}
	// springboot运行后此方法首先被调用
	// 实现CommandLineRunner抽象类中的run方法
	@Override
	public void run(String... args) throws Exception {
		System.out.println("springboot启动完成！");
	}
}
