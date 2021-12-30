package ru.back.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.sql.DataSource;


@Configuration //Даём Spring понять, что это класс конфигурации
    @ComponentScan("ru.back") //Все необходимые компоненты в папке ru/back
    @EnableWebMvc //включение Spring MVC
    public class SpringConfig implements WebMvcConfigurer { //имплементируемся от класса конфигурации mvc

        private final ApplicationContext applicationContext;
        //Внедрение зависимости при помощи аннотации
        @Autowired
        public SpringConfig(ApplicationContext applicationContext) {
            this.applicationContext = applicationContext;
        }
        //Определение компонентов (bean) с кастомной логикой
        @Bean
        public SpringResourceTemplateResolver templateResolver() {
            SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
            templateResolver.setApplicationContext(applicationContext);
            templateResolver.setPrefix("/WEB-INF/views/");
            templateResolver.setSuffix(".html");
            templateResolver.setCharacterEncoding("UTF-8");
            return templateResolver;
        }

        @Bean
        public SpringTemplateEngine templateEngine() {
            SpringTemplateEngine templateEngine = new SpringTemplateEngine();
            templateEngine.setTemplateResolver(templateResolver());
            templateEngine.setEnableSpringELCompiler(true);
            return templateEngine;
        }
        //Переопределяется преобразователь представлений для использования Thymeleaf шаблонизатора
        @Override
        public void configureViewResolvers(ViewResolverRegistry registry) {
            ThymeleafViewResolver resolver = new ThymeleafViewResolver();
            resolver.setTemplateEngine(templateEngine());
            resolver.setCharacterEncoding("UTF-8");

            registry.viewResolver(resolver);
        }
        //Указывается драйвер БД и остальные данные для подключения
        @Bean
        public DataSource dataSource() {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();

            dataSource.setDriverClassName("org.postgresql.Driver");
            dataSource.setUrl("jdbc:postgresql://localhost:5432/1");
            dataSource.setUsername("postgres");
            dataSource.setPassword("1");

            return dataSource;
        }
        //Используется JDBC Template, более высокий уровень, чем JDBC API
        @Bean
        public JdbcTemplate jdbcTemplate() {
            return new JdbcTemplate(dataSource());
        }
}
