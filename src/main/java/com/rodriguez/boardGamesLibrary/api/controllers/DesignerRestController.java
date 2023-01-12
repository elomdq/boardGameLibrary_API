package com.rodriguez.boardGamesLibrary.api.controllers;

import com.rodriguez.boardGamesLibrary.api.models.Designer;
import com.rodriguez.boardGamesLibrary.api.services.DesignerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/designers")
public class DesignerRestController {

    @Autowired
    private DesignerService designerService;

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<Designer> findAll(){
        return designerService.findAll();
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
