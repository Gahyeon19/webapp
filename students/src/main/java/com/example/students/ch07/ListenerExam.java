package com.example.students.ch07;

import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.SessionListener;

@Slf4j
@WebListener
public class ListenerExam
    implements ServletContextListener, ServletContextAttributeListener,
               HttpSessionListener, HttpSessionAttributeListener {
  @Override
  public void attributeAdded(ServletContextAttributeEvent scae) {
    log.info("@@@@@@@@@@Context Attribute added: {}", scae);
    log.info("@@@@@@@@@@Context Attribute added: {}", scae.getName());
    log.info("@@@@@@@@@@Context Attribute added: {}", scae.getValue());
//    ServletContextAttributeListener.super.attributeAdded(scae);
  }

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    log.info("@@@@@@@@@@Context Initialized: {}", sce);
//    ServletContextListener.super.contextInitialized(sce);
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    log.info("@@@@@@@@@@Context Destroyed: {}", sce);
//    ServletContextListener.super.contextDestroyed(sce);
  }

  @Override
  public void attributeAdded(HttpSessionBindingEvent se) {
    log.info("@@@@@@@@@@Session Attribute added: {}", se);
//    HttpSessionAttributeListener.super.attributeAdded(se);
  }

  @Override
  public void sessionCreated(HttpSessionEvent se) {
    log.info("@@@@@@@@@@Session Created: {}", se);
//    HttpSessionListener.super.sessionCreated(se);
  }
}

