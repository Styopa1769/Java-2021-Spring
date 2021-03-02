package spring.context.Entities;

import org.springframework.stereotype.Component;

@Component
public class Dog {
    private String name = "Рэкс";

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
