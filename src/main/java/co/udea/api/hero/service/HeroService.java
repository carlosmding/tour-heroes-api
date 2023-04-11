package co.udea.api.hero.service;

import co.udea.api.hero.exception.BusinessException;
import co.udea.api.hero.model.Hero;
import co.udea.api.hero.repository.HeroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeroService {

    private final Logger log = LoggerFactory.getLogger(HeroService.class);

    private HeroRepository heroRepository;

    public HeroService(HeroRepository heroRepository){
        this.heroRepository = heroRepository;
    }

    public Hero getHero(Integer id){
        Optional<Hero> optionalHero = heroRepository.findById(id);
        if(!optionalHero.isPresent()){
            log.info("No se encuentra un heroe con ID: "+id);
            throw new BusinessException("El heroe no existe");
        }
        return optionalHero.get();
    }

    public List<Hero> getHeroes(){
        List<Hero> heroesList = heroRepository.findAll();
        return heroesList;
    }

    public List<Hero> searchHeroes(String name){
        List<Hero> heroesList = heroRepository.findByNameLike("%"+name+"%");
        return heroesList;
    }

    public void updateHero (Hero hero){
        heroRepository.save(hero);
    }

    public void addHero(Hero hero){
        heroRepository.save(hero);
    }

    public void deleteHero (Hero hero){
        heroRepository.delete(hero);
    }


}
