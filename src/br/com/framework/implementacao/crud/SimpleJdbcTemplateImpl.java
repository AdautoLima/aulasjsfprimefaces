package br.com.framework.implementacao.crud;

import java.io.Serializable;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
//propagation=Propagation.REQUIRED : Se n�o houver uma transa��o ele cria.
//rollbackFor=Exception.class : Se ocorrer algum erro na transa��o ela d� rollback.
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class SimpleJdbcTemplateImpl extends SimpleJdbcTemplate implements Serializable {
   
    private static final long serialVersionUID = 1L;
    
    public SimpleJdbcTemplateImpl(DataSource dataSource) {
        super(dataSource);
       
    }

    
    
    

}
