//package com.team11.castleproj;
//
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//
//@Configuration
//public class DatabaseInitializer {
//
//    @Autowired
//    private DataSource dataSource;
//
//    @PostConstruct
//    public void initialize() throws SQLException {
//        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
//        populator.addScript(new ClassPathResource("schema.sql"));
//        populator.populate(dataSource.getConnection());
//    }
//}
