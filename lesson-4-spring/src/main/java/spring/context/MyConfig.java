package spring.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import spring.context.Entities.Cat;
import spring.context.Entities.Dog;
import spring.context.Entities.Parrot;

@Configuration
@ComponentScan("spring.context.Entities")
public class MyConfig {
    @Bean
    public Cat getCat(){
        return new Cat();
    }

    @Bean
    public Dog getDog(){
        return new Dog();
    }

    @Bean("Дружок")
    public Dog getMyDog(){
        Dog dog = new Dog();
        dog.setName("Дружок");
        return dog;
    }

    @Bean("parrot-pitty")
    public Object getParrot(){
        return new Parrot();
    }

    @Bean
    public Cat getCat(Parrot parrot){
        Cat cat = new Cat();
        cat.setName(parrot.getName() + "-killer");
        return cat;
    }
}
