package com.example.glofox.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.example.glofox.dto.ClassesDTO;
import com.example.glofox.repository.InMemoryClassesRepository;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // This allows non-static @BeforeAll methods
class InMemoryClassesRepositoryTest {

	private InMemoryClassesRepository repository;

    @BeforeAll
    public void setUp() {
        repository = new InMemoryClassesRepository();
    }

    @Test
    public void testSaveAndFindAll() {
        // Arrange
        ClassesDTO class1 = new ClassesDTO("Yoga", LocalDate.now(), LocalDate.now().plusDays(5), 20);
        ClassesDTO class2 = new ClassesDTO("Pilates", LocalDate.now(), LocalDate.now().plusDays(7), 15);

        // Act
        repository.save(class1);
        repository.save(class2);
        List<ClassesDTO> allClasses = repository.findAll();

        // Assert
        assertTrue(allClasses.contains(class1));
        assertTrue(allClasses.contains(class2));
    }
    
    @Test
    public void testFindByNameAndDate_Success() {
        // Arrange
        LocalDate startDate = LocalDate.now();
        ClassesDTO yogaClass = new ClassesDTO("Yoga", startDate, startDate.plusDays(5), 20);
        repository.save(yogaClass);

        // Act
        Optional<ClassesDTO> foundClass = repository.findByNameAndDate("Yoga", startDate.plusDays(1));

        // Assert
        assertTrue(foundClass.isPresent());
        assertEquals("Yoga", foundClass.get().getClassName());
    }
    
    @Test
    public void testFindByNameAndDate_Failure() {
        // Arrange
        LocalDate startDate = LocalDate.now();
        ClassesDTO yogaClass = new ClassesDTO("Yoga", startDate, startDate.plusDays(5), 20);
        repository.save(yogaClass);

        // Act
        Optional<ClassesDTO> foundClass = repository.findByNameAndDate("Pilates", startDate.plusDays(1));

        // Assert
        assertFalse(foundClass.isPresent());
    }

}
