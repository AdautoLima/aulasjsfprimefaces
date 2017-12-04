package br.com.framework.implementacao.crud;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.framework.implementacao.session.HibernateUtil;
import br.com.framework.interfac.crud.InterfaceCrud;

@Component
@Transactional
public class ImplementacaoCrud<T> implements InterfaceCrud<T> {

    private static final long serialVersionUID = 1L;

    private static SessionFactory sessionFactory = 
            HibernateUtil.getSessionFactory();
    
    @Autowired
    private JdbcTemplateImpl jdbcTemplate;

    @Autowired
    private SimpleJdbcInsertImplements simpleJdbcInsert;

    @Autowired
    private SimpleJdbcTemplateImpl simpleJdbcTemplate;

    
      
    private void validarSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = HibernateUtil.getSessionFactory();
        }
        validarTransaction();
    }
    
    private void validarTransaction() {
        if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
            sessionFactory.getCurrentSession().beginTransaction();
        }
    }
    
    private void commitProcessoAjax() {
        sessionFactory.getCurrentSession().beginTransaction().commit();
    }
    
    private void rollBackProcessoAjax() {
        sessionFactory.getCurrentSession().beginTransaction().rollback();
    }
    
    
    public void setJdbcTemplate(JdbcTemplateImpl jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setSimpleJdbcInsert(SimpleJdbcInsertImplements simpleJdbcInsert) {
        this.simpleJdbcInsert = simpleJdbcInsert;
    }

    public void setSimpleJdbcTemplate(SimpleJdbcTemplateImpl simpleJdbcTemplate) {
        this.simpleJdbcTemplate = simpleJdbcTemplate;
    }

   
    
    
    
    
    // Roda instantaneamente o SQL no banco de dados.
    // Sem isso o comando fica em cash, só executa no fim da session.
    public void executeFlushSession() {
        sessionFactory.getCurrentSession().flush();
    }
    
    
    
    @Override
    public void save(T obj) throws Exception {
        validarSessionFactory();
        sessionFactory.getCurrentSession().save(obj);
        executeFlushSession();
    }
    
    @Override
    public void persist(T obj) throws Exception {
        validarSessionFactory();
        sessionFactory.getCurrentSession().persist(obj);
        executeFlushSession();
    }

    @Override
    public void saveOrUpdate(T obj) throws Exception {
        validarSessionFactory();
        sessionFactory.getCurrentSession().saveOrUpdate(obj);
        executeFlushSession();
    }

    @Override
    public void update(T obj) throws Exception {
        validarSessionFactory();
        sessionFactory.getCurrentSession().update(obj);
        executeFlushSession();
    }

    @Override
    public void delete(T obj) throws Exception {
        validarSessionFactory();
        sessionFactory.getCurrentSession().delete(obj);
        executeFlushSession();
    }

    @Override
    public T merge(T obj) throws Exception {       
        validarSessionFactory();
        obj = (T) sessionFactory.getCurrentSession().merge(obj);
        executeFlushSession();
        return obj;
    }

    @Override
    public List<T> findList(Class<T> entidade) throws Exception {       
        validarSessionFactory();
        StringBuilder query = new StringBuilder();
        query.append(" select distinct(entity) from ")
        .append(entidade.getSimpleName()).append(" entity ");
        List<T> lista = sessionFactory.getCurrentSession().createQuery(query.toString()).list();
        
        return lista;
    }

    @Override
    public Object findById(Class<T> entidade, Long id) throws Exception {        
        validarSessionFactory();
        Object obj = sessionFactory.getCurrentSession().load(getClass(), id);
        
        return obj;
    }

    @Override
    public T findByPorId(Class<T> entidade, Long id) throws Exception {     
        validarSessionFactory();
        T obj = (T) sessionFactory.getCurrentSession().load(getClass(), id);
        
        return obj;
    }

    @Override
    public List<T> findListByQueryDinamica(String s) throws Exception {        
        validarSessionFactory();
        List<T> lista = new ArrayList<T>(); 
        lista = sessionFactory.getCurrentSession().createQuery(s).list();
        
        return lista;
    }

    @Override
    public void executeUpdateQueryDinamica(String s) throws Exception {
        validarSessionFactory();
        sessionFactory.getCurrentSession().createQuery(s).executeUpdate();
        executeFlushSession();
    }

    @Override
    public void executeUpdateSQLDinamica(String s) throws Exception {
        validarSessionFactory();
        sessionFactory.getCurrentSession().createSQLQuery(s).executeUpdate();
        executeFlushSession();
    }

    @Override
    public void clearSession() throws Exception {
        sessionFactory.getCurrentSession().clear();
    }

    @Override
    public void evict(Object objs) throws Exception {
        validarSessionFactory();
        sessionFactory.getCurrentSession().evict(objs);
    }

    @Override
    public Session getSession() throws Exception {       
        validarSessionFactory();
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<?> getListSQLDinamica(String sql) throws Exception {       
        validarSessionFactory();
        List<?> lista = sessionFactory.getCurrentSession().createSQLQuery(sql).list();
        return lista;
    }

    @Override
    public JdbcTemplate getJdbcTemplate() {       
        return null;
    }

    @Override
    public SimpleJdbcTemplate getSimpleJdbcTemplate() {        
        return null;
    }

    @Override
    public SimpleJdbcInsert getSimpleJdbcInsert() {        
        return null;
    }

    @Override
    public Long totalRegistro(String table) throws Exception {        
        StringBuilder sql = new StringBuilder();
        sql.append(" select count(1) from ").append(table);
        return jdbcTemplate.queryForLong(sql.toString());
    }

    @Override
    public Query obterQuery(String query) throws Exception {      
        return null;
    }

    @Override
    public List<T> findListByQueryDinamica(String query, int iniciaNoRegistro, int maximoResultado) throws Exception {       
        return null;
    }
    
    public List<Object[]> getListSQLDinamicaArray(String sql) throws Exception {
        validarSessionFactory();
        List<Object[]> lista = (List<Object[]>) sessionFactory.getCurrentSession().createSQLQuery(sql).list();
        
        return lista;
    }

}
