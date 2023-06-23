package org.air.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class InformationController {
    @GetMapping("/home")
    public String home(){
        return"Пак Елена, УВА-212. Предметная область: НЕБЕСНЫЕ ТЕЛА. Каждый предмет описывается следующими параметрами: уникальный код небесного тела, масса, радиус, категория небесного тела, скорость движения по орбите. Для объекта возможны действия: радиоактивное излучение (влияет на массу).";
    }

}
