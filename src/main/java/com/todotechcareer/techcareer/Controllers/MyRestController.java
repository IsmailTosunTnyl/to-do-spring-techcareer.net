package com.todotechcareer.techcareer.Controllers;

import com.todotechcareer.techcareer.Models.ToDo;
import com.todotechcareer.techcareer.Repository.ToDoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/api")
@Valid
@Tag(name = "ToDo API", description = "ToDo API Rest Controller")
public class MyRestController {

    private ToDoRepository toDoRepository;

    @Autowired
    public MyRestController(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @Operation(summary = "Meta data")
    @GetMapping("/")
    public Map<String,String> Hello() {
        return Map.of("message", "Hello from Spring API, this API used with React frontend for ToDo App",
                        "author", "Ismail Tosun",
                        "github", "https://github.com/IsmailTosunTnyl/to-do-spring-techcareer.net",
                "Version", "1.0.0"
                );
    }

    // Get all ToDos
    @Operation(summary = "Get all ToDos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all ToDo"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "Internal Server Error")))
    })
    @GetMapping("/todo")
    public ResponseEntity<List<ToDo>> getToDos() {
        return ResponseEntity.status(200).body(toDoRepository.findAll());
    }

    // Post new ToDo
    @Operation(summary = "Post new ToDo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all ToDo"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "Internal Server Error")))
    })
    @PostMapping("/todo")
    public ResponseEntity<?> saveToDo( @Valid @RequestBody  ToDo toDo) {

        try {
            ToDo t = toDoRepository.save(toDo);
            return ResponseEntity.status(200).body(t);
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }

    }


    // Update ToDo status
    @Operation(summary = "Update ToDo status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated ToDo status"),
            @ApiResponse(responseCode = "404", description = "ToDo not found",
                    content = @Content(schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "ToDo not found"))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "Internal Server Error")))
    })

    @PatchMapping("/todo/{id}")
    public ToDo updateToDoStatus(@PathVariable int id, @RequestBody @Schema(example = "{  \"content\": \"todo content\", \"status\": true }")
            ToDo updatedToDo) {
        ToDo toDoToUpdate = toDoRepository.findById(id).orElseThrow(() -> new RuntimeException("ToDo not found"));

        if (updatedToDo.isStatus() != toDoToUpdate.isStatus()) {
            toDoToUpdate.setStatus(updatedToDo.isStatus());
        }
        if (updatedToDo.getContent() != null) {
            toDoToUpdate.setContent(updatedToDo.getContent());
        }

        toDoRepository.save(toDoToUpdate);
        return toDoToUpdate;
    }


    // Delete ToDo by id
    @Operation(summary = "Delete ToDo by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted ToDo by id"),
            @ApiResponse(responseCode = "404", description = "ToDo not found",
                    content = @Content(schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "ToDo not found"))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "Internal Server Error")))
    })
    @DeleteMapping("/todo/{id}")
    public ToDo deleteToDoById(@PathVariable int id) {
        ToDo deletedTodo = toDoRepository.findById(id).orElseThrow(() -> new RuntimeException("ToDo not found"));
        toDoRepository.delete(deletedTodo);

        return deletedTodo;

    }
    @Operation(summary = "Delete all ToDo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted all  ToDo",
                    content = @Content(schema = @Schema(implementation = ToDo.class),
                            examples = @ExampleObject(value = "Deleted all  ToDo"))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "Internal Server Error")))
    })
    @DeleteMapping("/todo")
    public List<ToDo> deleteAllToDo() {
        List<ToDo> deletedTodo = toDoRepository.findAll();
        toDoRepository.deleteAll();

        return deletedTodo;

    }


    @Operation(summary = "Delete all done ToDo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted all done ToDo",
                    content = @Content(schema = @Schema(implementation = ToDo.class),
                            examples = @ExampleObject(value = "Deleted all done ToDo"))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "Internal Server Error")))
    })
    @DeleteMapping("/todo/done")
    public List<ToDo> deleteAllDoneToDo() {
        List<ToDo> deletedTodo = toDoRepository.findAllByStatus(true);
        toDoRepository.deleteAllByStatus(true);

        return deletedTodo;

    }





}
