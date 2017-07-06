package cn.stt.request;

import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by stt on 2017/7/6.
 */
public class Demo {
    @NotBlank(message = "name is not null")
    private String name;
    @ApiParam(name = "content" ,required = true)
    private String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

