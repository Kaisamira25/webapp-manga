package com.example.webappmanga.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Service
@Slf4j
public class SendMailTemplateService {
    private static final String mail_template_base_name = "templateVerifyCodeSignUp";
    private static final String mail_template_prefix = "/templates/Mail/";
    private static final String mail_template_suffix = ".html";
    private static final String utf_8 = "UTF-8";
    private static TemplateEngine templateEngine;
    
    static{
        templateEngine = emailTemplateEngine();
    }

    private static TemplateEngine emailTemplateEngine() {
        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(htmlTemplateResolver());
        templateEngine.setTemplateEngineMessageSource(emailMessageSource());
        return templateEngine;
    }

    private static ResourceBundleMessageSource emailMessageSource() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(mail_template_base_name);
        return messageSource;
    }

    private static ITemplateResolver htmlTemplateResolver() {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix(mail_template_prefix);
        templateResolver.setSuffix(mail_template_suffix);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding(utf_8);
        templateResolver.setCacheable(false);
        return templateResolver;
    }
    public String sendEmailWithTemplate(String title, String content, String urlVerify, String templateName){
        final Context context = new Context();
        context.setVariable("title",title);
        context.setVariable("urlVerify",urlVerify);
        context.setVariable("content",content);
        log.info("----->SendMailTemplateService | sendEmailWithTemplate: {}",templateName);
        return templateEngine.process(templateName,context);
    }

}
