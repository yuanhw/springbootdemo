package org.example.demo.enabledemo;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * todo
 *
 * @author Wang Yuanhang
 * @date 2020/3/29 18:51
 */
public class ServerImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        ServerImportSelector serverImportSelector = new ServerImportSelector();
        String[] imports = serverImportSelector.selectImports(importingClassMetadata);

        for (String anImport : imports) {
            AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(anImport).getBeanDefinition();
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition, registry);
        }
    }

}
