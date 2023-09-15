package com.rodriguez.boardGamesLibrary.api.controllers;

import com.rodriguez.boardGamesLibrary.api.dtos.DesignerDto;
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
    public ResponseEntity<DesignerDto> findById(@PathVariable Long id){
        DesignerDto designerDto;
        try{
            designerDto = designerService.findById(id);
            if(designerDto==null){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
        }catch(Exception ex){
            /*System.out.println(ex.getMessage());
            ex.printStackTrace();*/
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
        return ResponseEntity.status(HttpStatus.OK).body(designerDto);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<DesignerDto> create(@RequestBody DesignerDto designer){
        DesignerDto designerDto;
        try{
            designerDto =designerService.save(designer);
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(designerDto);
    }

    @PutMapping(path ="/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<DesignerDto> update(@PathVariable Long id, @RequestBody DesignerDto designer){
        DesignerDto currentDesigner;

        try{
            currentDesigner  = designerService.findById(id);

            if(currentDesigner!=null){
                crossData(currentDesigner,designer);
                currentDesigner = designerService.save(currentDesigner);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }

        return ResponseEntity.status(HttpStatus.OK).body(currentDesigner);
    }

    private void crossData(DesignerDto currentDesigner, DesignerDto newDesigner){
        currentDesigner.setName(newDesigner.getName());
        currentDesigner.setLastName(newDesigner.getLastName());
        currentDesigner.setCountry(newDesigner.getCountry());
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        try{
            designerService.deleteById(id);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
