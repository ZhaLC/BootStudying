package com.zlc.BootStudying;

import com.zlc.BootStudying.config.ConfigBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@SpringBootApplication
@EnableConfigurationProperties({ConfigBean.class})
public class BootStudyingApplication {

	private Logger logger = LoggerFactory.getLogger(getClass());

	/*@Autowired
	ConfigBean configBean;

	@RequestMapping("/testConfigBean")
	public String testConfigBean(){
		return configBean.getName();
	}*/

	@RequestMapping("/aa")
	public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView("/index");
		return modelAndView;
	}

	public static void main(String[] args) {
		SpringApplication.run(BootStudyingApplication.class, args);
	}
}
