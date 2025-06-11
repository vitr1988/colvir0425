package com.colvir.webinar19;

import com.colvir.webinar19.dto.CityDto;
import com.colvir.webinar19.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class CacheRunner {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(CacheRunner.class, args);
        CityService cityService = applicationContext.getBean(CityService.class);
        cityService.findAll();

        log.info("Delete All cities");
        cityService.deleteAll();

        log.info("Save cities");
        cityService.save(new CityDto(1L, "Moscow"));
        cityService.save(new CityDto(2L, "Saint-Petersburg"));
        cityService.save(new CityDto(3L, "Samara"));
        cityService.save(new CityDto(3L, "Murmansk"));
        cityService.save(new CityDto(4L, "Kazan"));
        cityService.save(new CityDto(5L, "Astrahan"));

//        log.info("Find cities");
//        CityDto moscow = cityService.findById(1L);
//        log.info("Moscow: {}", moscow);
//        CityDto moscow2 = cityService.findById(1L);
//        log.info("Moscow: {}", moscow2);
//        CityDto moscow3 = cityService.findById(1L);
//        log.info("Moscow: {}", moscow3);


        cityService.deleteById(4L);

        CityDto kazan = cityService.findById(4L);
        log.info("Kazan: {}", kazan);

        CityDto murmansk = cityService.findById(3L);
        log.info("Murmansk: {}", murmansk);
    }
}
