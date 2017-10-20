package ru.reeson2003.my3d.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import ru.reeson2003.my3d.common.loader.BaseLoaderFactory;
import ru.reeson2003.my3d.common.loader.internal.InternalLoaderFactory;

/**
 * Created by Pavel Gavrilov on 20.10.2017.
 */
@SpringBootApplication
@Configuration
public class SpringBootServer {
    public static void main(String[] args) {
        BaseLoaderFactory.setFactory(new InternalLoaderFactory());
        SpringApplication.run(SpringBootServer.class, args);
    }
}
