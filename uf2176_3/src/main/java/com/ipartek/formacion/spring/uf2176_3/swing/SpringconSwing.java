package com.ipartek.formacion.spring.uf2176_3.swing;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

//Main.java
@SpringBootApplication
public class SpringconSwing implements CommandLineRunner {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SpringconSwing.class).web(WebApplicationType.NONE).headless(false)
				.bannerMode(Banner.Mode.OFF).run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame();
			frame.setVisible(true);
		});
	}
}
