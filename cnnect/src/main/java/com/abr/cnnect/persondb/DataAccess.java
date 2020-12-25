package com.abr.cnnect.persondb;

import com.abr.cnnect.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class DataAccess implements PersonDB {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DataAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(Person person) {

        final String sql = "INSERT INTO person(id,name,lastname,email) VALUES(?,?,?,?)";
        jdbcTemplate.update(sql,
                UUID.randomUUID(),
                person.getName(),
                person.getLastName(),
                person.getEmail()
                );
        return 1;
    }

    @Override
    public List<Person> getAll() {
        final String sql = "SELECT id, name, lastname, email FROM person";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            return new Person(
                    UUID.fromString(resultSet.getString("id")),
                    resultSet.getString("name"),
                    resultSet.getString("lastname"),
                    resultSet.getString("email")


            );
        });
    }

    @Override
    public Optional<Person> selectPerson(UUID id) {
        final String sql = "SELECT id, name, lastname, email FROM person WHERE id = ?";
        Person person =  jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> {
            return new Person(
                    UUID.fromString(resultSet.getString("id")),
                    resultSet.getString("name"),
                    resultSet.getString("lastname"),
                    resultSet.getString("email")
            );

                }
                );
        return Optional.ofNullable(person);
    }

    @Override
    public boolean deletePerson(UUID id) {
        final String sql = "DELETE FROM person WHERE id = ?";
        jdbcTemplate.update(sql,id);
        return true;
    }

    @Override
    public boolean updatePersonEmail(UUID id, Person per) {
        final String sql = "UPDATE person SET email = ? WHERE id = ?";
        jdbcTemplate.update(sql, per.getEmail(), id);
        return true;
    }

}
