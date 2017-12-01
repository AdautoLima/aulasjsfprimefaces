package br.com.framework.interfac.crud;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public interface InterfaceCrud<T> extends Serializable {
    // por default são todos públicos.
    void save(T obj) throws Exception;
    void persist(T obj) throws Exception;
    void saveOrUpdate(T obj) throws Exception;
    void update(T obj) throws Exception;
    void delete(T obj) throws Exception;
    T merge(T obj) throws Exception;
    List<T> findList(Class<T> obj) throws Exception;
    Object findById(Class<T> entidade, Long id) throws Exception;
    T findByPorId(Class<T> entidade, Long id) throws Exception;
    List<T> findListByQueryDinamica(String s) throws Exception;
    void executeUpdateQueryDinamica(String s) throws Exception;
    void executeUpdateSQLDinamica(String s) throws Exception;
    // fazer o Hibernate limpar a sessão dele (cash).
    void clearSession() throws Exception;
    // retira um objeto da sessão do hibernate.
    void evict (Object objs) throws Exception;
    Session getSession() throws Exception;
    // <?> recebe uma sql.
    List<?> getListSQLDinamica(String sql) throws Exception;
    
    // Para trabalhar com Jdbc do Spring.
    JdbcTemplate getJdbcTemplate();
    SimpleJdbcTemplate getSimpleJdbcTemplate();
    SimpleJdbcInsert getSimpleJdbcInsert();
    
    Long totalRegistro(String table) throws Exception;
    Query obterQuery(String query) throws Exception;
    // Carregamento dinamico com JSF e Primefaces.
    List<T> findListByQueryDinamica(String query, int iniciaNoRegistro, int maximoResultado) throws Exception;
    
}
