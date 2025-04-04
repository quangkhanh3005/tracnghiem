package com.example.tracnghiem.Controller;

import com.example.tracnghiem.DTO.QuizDTO;
import com.example.tracnghiem.DTO.QuizRequest;
import com.example.tracnghiem.DTO.SubmitRequest;
import com.example.tracnghiem.DTO.SubmitRespone;
import com.example.tracnghiem.Model.Quiz;
import com.example.tracnghiem.Service.QuizService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/quiz")
public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }
    @PostMapping(value ="/create" , consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createQuiz(@RequestPart String quizRequest, @RequestPart MultipartFile image, @RequestParam int idUser ) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            QuizRequest quiz = mapper.readValue(quizRequest,QuizRequest.class);
            QuizDTO quizDTO=quizService.createQuiz(quiz,idUser,image);
            return ResponseEntity.ok(quizDTO);
        }
        catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/topic/{idTopic}")
    public ResponseEntity<?> getAllQuizByTopicId(@PathVariable int idTopic) {
        try {
            List<QuizDTO> quizDTOS=quizService.getQuizListByIdTopic(idTopic);
            return ResponseEntity.ok(quizDTOS);
        }
        catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{idQuiz}")
    public ResponseEntity<?> getQuiz(@PathVariable int idQuiz) {
        try {
            QuizDTO quizDTO=quizService.getQuizById(idQuiz);
            return ResponseEntity.ok(quizDTO);
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("find/{code}")
    public ResponseEntity<?> findQuiz(@PathVariable String code) {
        try {
            int idQuiz=quizService.findQuizByCode(code);
            return ResponseEntity.ok(idQuiz);
        }
        catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping
    public ResponseEntity<?> deleteQuiz(@RequestParam int idQuiz) {
        try {
            quizService.deteleQuiz(idQuiz);
            return ResponseEntity.ok().body("Xóa Quiz Thành Công!");
        }
        catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping(value="/{idQuiz}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateQuiz(
            @PathVariable int idQuiz,
            @RequestPart String quizRequest,
            @RequestParam int idUser,
            @RequestPart MultipartFile image) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            QuizRequest quizRequest1 = mapper.readValue(quizRequest, QuizRequest.class);
            QuizDTO quizDTO = quizService.updateQuiz(quizRequest1, idQuiz, idUser, image);
            return ResponseEntity.ok(quizDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body("Lỗi parse QuizRequest: " + e.getMessage());
        }
    }
    @PostMapping("submit")
    public ResponseEntity<?> submitQuiz(@RequestBody SubmitRequest submitRequest, @RequestParam int idUser) {
        try {
            SubmitRespone submitRespone=quizService.submitQuiz(submitRequest, idUser);
            return ResponseEntity.ok(submitRespone);
        }
        catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
