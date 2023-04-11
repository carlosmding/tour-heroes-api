package co.udea.api.hero.controller;

import co.udea.api.hero.model.Hero;
import co.udea.api.hero.service.HeroService;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/heroes")
public class HeroController {

    private final Logger log = LoggerFactory.getLogger(HeroController.class);

    private HeroService heroService;

    public HeroController(HeroService heroService){
        this.heroService = heroService;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Busca un heroe por su id",  response = Hero.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroe encontrado existosamente"),
            @ApiResponse(code = 400, message = "La petición es inválida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<Hero> getHero(@PathVariable Integer id){
        log.info("Rest request buscar heroe por id: "+ id);
        return ResponseEntity.ok(heroService.getHero(id));
    }

    @GetMapping("")
    @ApiOperation(value = "Busca todos los heroes disponibles",  response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroes encontrados existosamente"),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<List<Hero>> getHeroes(){
        log.info("Rest request para buscar lista de heroes");
        return ResponseEntity.ok(heroService.getHeroes());
    }

    @GetMapping("/find/{name}")
    @ApiOperation(value = "Busca heroes por el nombre ingresado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroe encontrado exitosamente"),
            @ApiResponse(code= 400, message = "La peticion es inválida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")
    })
    public ResponseEntity<List<Hero>> searchHeroes(@ApiParam("Nombre del heroe que desea buscar")
                                                       @PathVariable("name") String name){
        return ResponseEntity.ok(heroService.searchHeroes(name));
    }

    @PutMapping("/update")
    @ApiOperation(value = "Actualiza un heroe")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Hero actualizado exitosamente"),
            @ApiResponse(code= 400, message = "La peticion es inválida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public void updateHero (@RequestBody Hero hero){
        heroService.updateHero(hero);
    }

    @PutMapping("/add")
    @ApiOperation(value = "Crea un nuevo heroe")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Hero creado exitosamente"),
            @ApiResponse(code= 400, message = "La peticion es inválida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public void addHero (@RequestBody Hero hero){
        heroService.addHero(hero);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Borra un heroe")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Hero eliminado exitosamente"),
            @ApiResponse(code= 400, message = "La peticion es inválida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public void deleteHero (@PathVariable Integer id){
        log.info("Rest request para eliminar el heroe por id: "+ id);
        Hero hero = heroService.getHero(id);
        heroService.deleteHero(hero);
    }


}
