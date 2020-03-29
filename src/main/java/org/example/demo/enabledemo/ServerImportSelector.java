package org.example.demo.enabledemo;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * todo
 *
 * @author Wang Yuanhang
 * @date 2020/3/29 18:30
 */
public class ServerImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        String[] importClassName = new String[0];
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(EnableServer.class.getName());

        if (annotationAttributes == null) {
            return importClassName;
        }

        Server.type type = (Server.type) annotationAttributes.get("type");

        switch (type) {
            case HTTP:
                importClassName = new String[]{HttpServer.class.getName()};
                break;
            case FTP:
                importClassName = new String[]{FtpServer.class.getName()};
                break;
            default: break;
        }
        return importClassName;
    }

}
