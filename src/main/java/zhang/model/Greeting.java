package zhang.model;

import lombok.Data;

@Data
public class Greeting {

    private long id;
    private String content;
    public Greeting(){}
    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }
}
