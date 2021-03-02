package spring.context.Entities;

import org.springframework.stereotype.Component;

@Component("parrot-kesha")
public class Parrot{
    private String name = "Пити";

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}