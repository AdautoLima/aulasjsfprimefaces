package br.com.framework.utils;

import java.io.Serializable;

import org.springframework.stereotype.Component;

/*
 * Trabalha com o Hibernate Envers.
 * Identifica qual Usu�rio est� fazendo opera��es de Update, Select, Delete e Insert.
 * Obs.: Cada usu�rio que se loga tem uma sess�o.
 * */


@Component
public class UtilFramework implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();
    
    // Carrega qual usu�rio est� fazendo uma opera��o.
    public synchronized static ThreadLocal<Long> getThreadLocal(){
        return threadLocal;
    }

}
