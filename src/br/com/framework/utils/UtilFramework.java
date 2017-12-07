package br.com.framework.utils;

import java.io.Serializable;

import org.springframework.stereotype.Component;

/*
 * Trabalha com o Hibernate Envers.
 * Identifica qual Usuário está fazendo operações de Update, Select, Delete e Insert.
 * Obs.: Cada usuário que se loga tem uma sessão.
 * */


@Component
public class UtilFramework implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();
    
    // Carrega qual usuário está fazendo uma operação.
    public synchronized static ThreadLocal<Long> getThreadLocal(){
        return threadLocal;
    }

}
