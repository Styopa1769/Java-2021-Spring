package spring.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.context.Entities.Cat;
import spring.context.Entities.Dog;
import spring.context.Entities.Parrot;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig.class);

        Cat cat = context.getBean(Cat.class);
        Dog dog1 = (Dog) context.getBean("dog");
        Dog dog2 = (Dog) context.getBean("Дружок");
        Parrot parrot = context.getBean("parrot-pitty", Parrot.class);


        System.out.println(cat.getName());
        System.out.println(dog1.getName());
        System.out.println(dog2.getName());
        System.out.println(parrot.getName());
    }
}
