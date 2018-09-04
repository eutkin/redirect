package net.eutkin.redirect.web;

import lombok.extern.slf4j.Slf4j;
import net.eutkin.redirect.entity.Parameter;
import net.eutkin.redirect.entity.Source;
import net.eutkin.redirect.service.crud.CrudService;
import net.eutkin.redirect.view.ParameterView;
import net.eutkin.redirect.view.SourceView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
@Slf4j
public class RestCrudController {

    @Autowired
    private CrudService crudService;

    @PostMapping("/param")
    public ResponseEntity parameter(@RequestBody ParameterView parameterView) {
        List<Parameter> parameters = parameterView.toEntity();
        crudService.saveParameters(parameters);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/redirect")
    public ResponseEntity redirect(@RequestBody SourceView sourceView) {
        Source source = sourceView.toEntity();
        crudService.saveSource(source);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String exceptionHandler(Exception ex) {
        log.error(ex.getMessage(), ex);
        return "Произошла ошибка";
    }
}
