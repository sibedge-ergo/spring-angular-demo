package com.sibedge.ergo;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Service entrypoint.
 */
@SpringBootApplication
class DemoApplication {

    /**
     * Runs an service instance.
     *
     * @param args app extra options
     */
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(DemoApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }

}
