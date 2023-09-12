package com.rodriguez.boardGamesLibrary.api.controllers;

import com.rodriguez.boardGamesLibrary.api.dtos.BoardGameDto;
import com.rodriguez.boardGamesLibrary.api.dtos.DesignerDto;
import com.rodriguez.boardGamesLibrary.api.models.Designer;
import com.rodriguez.boardGamesLibrary.api.services.DesignerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path="/designers")
public class DesignerRestController {

    @Autowired
    private DesignerService designerService;

    @GetMapping
    public ResponseEntity<List<DesignerDto>> findAll(){
        List<DesignerDto> designers;
        try{
            designers = List.copyOf(designerService.findAll());
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
        return ResponseEntity.status(HttpStatus.OK).body(designers);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK) //por default
    public Designer findById(@PathVariable Long id){
        return designerService.byId(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Designer create(@RequestBody Designer designer){
        return designerService.save(designer);
    }

    @PutMapping(path ="/{id}", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Designer update(@PathVariable Long id, @RequestBody Designer designer){
        Designer currentDesigner = findById(id);

        if(currentDesigner!=null){
            crossData(currentDesigner,designer);
            return designerService.save(currentDesigner);
        }
        return null;
    }

    private void crossData(Designer currentDesigner, Designer newDesigner){
        currentDesigner.setName(newDesigner.getName());
        currentDesigner.setLastName(newDesigner.getLastName());
        currentDesigner.setCountry(newDesigner.getCountry());
    }

    @DeleteMapping(path="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        designerService.deleteById(id);
    }
}
