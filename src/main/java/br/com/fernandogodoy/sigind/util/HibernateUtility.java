package br.com.fernandogodoy.sigind.util;

import br.com.fernandogodoy.sigind.models.cadastros.Cliente;
import br.com.fernandogodoy.sigind.models.cadastros.Email;
import br.com.fernandogodoy.sigind.models.cadastros.Empresa;
import br.com.fernandogodoy.sigind.models.cadastros.Fornecedor;
import br.com.fernandogodoy.sigind.models.cadastros.Funcao;
import br.com.fernandogodoy.sigind.models.cadastros.Funcionario;
import br.com.fernandogodoy.sigind.models.cadastros.Maquina;
import br.com.fernandogodoy.sigind.models.cadastros.Marca;
import br.com.fernandogodoy.sigind.models.cadastros.MatPrima;
import br.com.fernandogodoy.sigind.models.cadastros.Municipio;
import br.com.fernandogodoy.sigind.models.cadastros.Peca;
import br.com.fernandogodoy.sigind.models.cadastros.Pessoa;
import br.com.fernandogodoy.sigind.models.cadastros.Setor;
import br.com.fernandogodoy.sigind.models.cadastros.Telefone;
import br.com.fernandogodoy.sigind.models.cadastros.Uf;
import br.com.fernandogodoy.sigind.models.cadastros.Usuario;
import br.com.fernandogodoy.sigind.models.movimentos.Compra;
import br.com.fernandogodoy.sigind.models.movimentos.ContasPagar;
import br.com.fernandogodoy.sigind.models.movimentos.ContasReceber;
import br.com.fernandogodoy.sigind.models.movimentos.Devolucao;
import br.com.fernandogodoy.sigind.models.movimentos.DevolucaoPeca;
import br.com.fernandogodoy.sigind.models.movimentos.Expedicao;
import br.com.fernandogodoy.sigind.models.movimentos.MatPrimaCompra;
import br.com.fernandogodoy.sigind.models.movimentos.MatPrimaProd;
import br.com.fernandogodoy.sigind.models.movimentos.OrdemProd;
import br.com.fernandogodoy.sigind.models.movimentos.PecaOrdemProd;
import br.com.fernandogodoy.sigind.models.movimentos.PecaVenda;
import br.com.fernandogodoy.sigind.models.movimentos.PgContasPagar;
import br.com.fernandogodoy.sigind.models.movimentos.PgContasReceber;
import br.com.fernandogodoy.sigind.models.movimentos.Producao;
import br.com.fernandogodoy.sigind.models.movimentos.Venda;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class HibernateUtility {

    private static final SessionFactory factory;
    private static final ThreadLocal sessionThread = new ThreadLocal();
    private static final ThreadLocal transactionThread = new ThreadLocal();

 public static Session getSession() {
        Session session = (Session) sessionThread.get();
        if ((session == null) || (!(session.isOpen()))) {
            session = factory.openSession();
            sessionThread.set(session);
        }
        return ((Session) sessionThread.get());
    }

    public static void closeSession() {
        Session session = (Session) sessionThread.get();
        if ((session != null) && (session.isOpen())) {
            sessionThread.set(null);
            session.close();
        }
    }

    public static void beginTransaction() {
        Transaction transaction = getSession().beginTransaction();
        transactionThread.set(transaction);
    }

    public static void commitTransaction() {
        Transaction transaction = (Transaction) transactionThread.get();
        if ((transaction != null) && (!(transaction.wasCommitted())) && (!(transaction.wasRolledBack()))) {
            transaction.commit();
            transactionThread.set(null);
        }
    }

    public static void rollbackTransaction() {
        Transaction transaction = (Transaction) transactionThread.get();
        if ((transaction != null) && (!(transaction.wasCommitted())) && (!(transaction.wasRolledBack()))) {
            transaction.rollback();
            transactionThread.set(null);
        }
    }

    static {
        try {
            factory = new Configuration()
                    /***POSTGRESQL***/
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
                    .setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
                    .setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/sigind")
                    
//                    .setProperty("hibernate.connection.url", "jdbc:postgresql://192.168.1.23:5432/sigcom")
                    .setProperty("hibernate.connection.username", "postgres")
                    .setProperty("hibernate.connection.password", "postgres")
                    /***MYSQL***/
//                     .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect")
//                    .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
//                    .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/sigcom")
//                    .setProperty("hibernate.connection.username", "root")
//                    .setProperty("hibernate.connection.password", "root")
                    //.setProperty("hibernate.connection.datasource", "jdbc/dbSGC") //data source (so pra aplicacao web e tem q configurar no tomcat)
                    .setProperty("hibernate.hbm2ddl.auto", "update")
//                    .setProperty("hibernate.hbm2ddl.auto", "create")
                    .setProperty("hibernate.c3p0.max_size", "10")
                    .setProperty("hibernate.c3p0.min_size", "2")
                    .setProperty("hibernate.c3p0.timeout", "5000")
                    .setProperty("hibernate.c3p0.max_statements", "10")
                    .setProperty("hibernate.c3p0.idle_test_period", "3000")
                    .setProperty("hibernate.c3p0.acquire_increment", "2")
                    .setProperty("show_sql", "true")
                    .setProperty("use_outer_join", "true")
                    .setProperty("hibernate.generate_statistics", "true")
                    .setProperty("hibernate.use_sql_comments", "true")
                    .setProperty("hibernate.format_sql", "true")
                    //CADASTROS
                    .addAnnotatedClass(Marca.class)
                    .addAnnotatedClass(Setor.class)
                    .addAnnotatedClass(Municipio.class)
                    .addAnnotatedClass(Uf.class)
                    .addAnnotatedClass(Funcao.class)
                    .addAnnotatedClass(Pessoa.class)
                    .addAnnotatedClass(MatPrima.class)
                    .addAnnotatedClass(Telefone.class)
                    .addAnnotatedClass(Empresa.class)
                    .addAnnotatedClass(Fornecedor.class)
                    .addAnnotatedClass(Cliente.class)
                    .addAnnotatedClass(Funcionario.class)
                    .addAnnotatedClass(Usuario.class)
                    .addAnnotatedClass(Peca.class)
                    .addAnnotatedClass(Maquina.class)
                    .addAnnotatedClass(Email.class)
                    //MOVIMENTOS
                    .addAnnotatedClass(OrdemProd.class)
                    .addAnnotatedClass(Producao.class)
                    .addAnnotatedClass(Venda.class)
                    .addAnnotatedClass(Compra.class)
                    .addAnnotatedClass(Expedicao.class)
                    .addAnnotatedClass(Devolucao.class)
                    .addAnnotatedClass(ContasPagar.class)
                    .addAnnotatedClass(ContasReceber.class)
                    .addAnnotatedClass(PgContasPagar.class)
                    .addAnnotatedClass(PgContasReceber.class)
                    .addAnnotatedClass(PecaVenda.class)

                    .addAnnotatedClass(MatPrimaCompra.class)
                    .addAnnotatedClass(PecaOrdemProd.class)
                    .addAnnotatedClass(MatPrimaProd.class)
                    .addAnnotatedClass(DevolucaoPeca.class)

                    .buildSessionFactory();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void main(String [] args) {
        
    }
    
}
